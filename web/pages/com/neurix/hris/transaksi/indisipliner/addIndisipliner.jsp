<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style type="text/css">
        #tglAwalPantau4{z-index: 2000!important}
        #tanggal4{z-index: 2000!important}
        #tglAkhirPantau4{z-index: 2000!important}
        #tanggalAwalBlokirAbsensi4{z-index: 2000!important}
        #tanggalAkhirBlokirAbsensi4{z-index: 2000!important}
        #modal-history{z-index: 3000!important}
        .modal-backdrop {  z-index: 1000 !important;  }
        .typeahead{z-index: 3000!important}
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IndisiplinerAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        $.subscribe('beforeProcessSaveIndisipliner', function (event, data) {
            var unit  = document.getElementById("branchId4").value;
            var nip = document.getElementById("nip4").value;
            var tipe = document.getElementById("tipeIndisipliner4").value;

            if ( unit != ''&& nip != ''&& tipe != '') {
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

                if ( unit == '') {
                    msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                }
                if ( nip== '') {
                    msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                }
                if ( tipe == '') {
                    msg += 'Field <strong>Tipe Indisipliner</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialogIndisipliner', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        }
    </script>
</head>
<body bgcolor="#FFFFFF">
<div id="modal-history" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">History Indisipliner</h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="historyIndisiplinerTable table table-bordered">

                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addFormIndisipliner" method="post" theme="simple" namespace="/indisipliner" action="save_indisipliner" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Add Indisipliner</legend>
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
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchId4" name="indisipliner.branchId" required="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>

                        <td>
                            <table>
                                <s:textfield  id="nip4" name="indisipliner.nip" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                        <td>
                            <a href="javascript:void(0)">
                                <img sizes="30" id="btnViewHistory" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                            </a>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        $('#nip4').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};
                                var data = [];
                                dwr.engine.setAsync(false);
                                var unit = document.getElementById('branchId4').value;
                                if (unit!==''){
                                    IndisiplinerAction.initComboPersonil(query, unit, function (listdata) {
                                        data = listdata;
                                    });
                                    $.each(data, function (i, item) {
                                        var labelItem = item.nip+" "+item.namaPegawai;
                                        mapped[labelItem] = {id: item.nip,nama:item.namaPegawai, label: labelItem, divisi:item.divisi,golongan:item.golongan,position:item.positionId};
                                        functions.push(labelItem);
                                    });
                                    process(functions);
                                }
                                else {
                                    alert ("Unit is empty");
                                    $('#nipId').val("");
                                }
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                $('#divisiId4').val(selectedObj.divisi).change();
                                $('#divisiId2').val(selectedObj.divisi).change();
                                $('#nama4').val(selectedObj.nama).change();
                                $('#positionid4').val(selectedObj.position).change();
                                $('#golonganId4').val(selectedObj.golongan).change();
                                return selectedObj.id;
                            }
                        });
                    </script>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nama4" name="indisipliner.nama" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid4" disabled="true" name="" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId4" name="indisipliner.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                                <s:textfield  id="divisiId2" name="indisipliner.divisiId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Golongan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId4" name=""
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'SP0':'Teguran','SP1':'SP1','SP2':'SP2','SP3':'SP3'}" id="tipeIndisipliner4" name="indisipliner.tipeIndisipliner"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggal4" size="10" name="indisipliner.stTanggal" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Masa Berlaku :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tglAwalPantau4" size="10" name="indisipliner.stTanggalAwalPantau" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tglAkhirPantau4" size="10" name="indisipliner.stTanggalAkhirPantau" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Blokir Absen :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggalAwalBlokirAbsensi4" size="10" name="indisipliner.stTanggalAwalBlokirAbsensi" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggalAkhirBlokirAbsensi4" size="10" name="indisipliner.stTanggalAkhirBlokirAbsensi" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Pelanggaran :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="keteranganPelanggaran4" name="indisipliner.keteranganPelanggaran" required="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Dampak :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="dampak4" name="indisipliner.dampak" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addFormIndisipliner" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveIndisipliner" onCompleteTopics="closeDialog,successDialogIndisipliner"
                                   onSuccessTopics="successDialogIndisipliner" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
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
                                                                      callSearch();
                                                                      link();
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
<script>
    $(document).ready(function() {
        $('#tglAwalPantau4').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tanggal4').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tglAkhirPantau4').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tanggalAwalBlokirAbsensi4').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tanggalAkhirBlokirAbsensi4').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#btnViewHistory').click(function () {
            var nip = $('#nip4').val();
            if (nip!=""){
                $('.historyIndisiplinerTable').find('tbody').remove();
                $('.historyIndisiplinerTable').find('thead').remove();
                dwr.engine.setAsync(false);
                var tmp_table = "";
                IndisiplinerAction.searchHistoryIndisipliner(nip,function(listdata) {
                    if (listdata!=""){
                        tmp_table = "<thead style='font-size: 14px' >" +
                                "<tr>" +
                                "<th colspan='6' align='center' style='outline: 0px;text-align: center'>History Indisipliner</th>" +
                                "</tr>"+
                                "<tr class='active'>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Awal Pantau</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Awal Pantau</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tipe Indisipliner</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Keterangan</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Dampak</th>"+
                                "</tr></thead>";
                        var i = i;
                        $.each(listdata, function (i, item) {
                            var myDate = new Date(item.tanggalAwalPantau);
                            var myDate1 = new Date(item.tanggalAkhirPantau);
                            tmp_table += '<tr style="font-size: 12px;" ">' +
                                    '<td align="center">' + (i + 1) + '</td>' +
                                    '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                                    '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                                    '<td align="center">' + item.tipeIndisipliner + '</td>' +
                                    '<td align="center">' + item.keteranganPelanggaran + '</td>' +
                                    '<td align="center">' + item.dampak + '</td>' +
                                    "</tr>";
                        });
                        $('.historyIndisiplinerTable').append(tmp_table);
                        $("#historyIndisiplinerTable").find("td:contains('null')").html("-");
                        $('#modal-history').find('.modal-title').text('View Detail Indisipliner');
                        $('#modal-history').modal('show');
                    }else {
                        alert("data indisipliner Kosong")
                    }
                });

            }else{
                alert("Isi NIP terlebih dahulu");
            }
        });
        $('#tipeIndisipliner4').change(function(){
            var tipe = $('#tipeIndisipliner4').val();
            if(tipe!=""){
                if(tipe=="SP0"){
                    $('#dampak4').val("Blokir absensi, ybs tidak dapat melakukan absen finger print");
                }else if (tipe=="SP1"){
                    $('#dampak4').val("Tidak mendapatkan kenaikan berkala selama 1 tahun dan nilai prestasinya 0");
                }else if (tipe=="SP2"){
                    $('#dampak4').val("Tidak mendapatkan kenaikan berkala selama 2 tahun dan nilai prestasinya 0");
                }else if (tipe=="SP3"){
                    $('#dampak4').val("Tidak mendapatkan kenaikan skala gaji pokok selama 2 tahun  dan atau penurunan golongan 1 tingkat ");
                }
            }
        });
    });


</script>

