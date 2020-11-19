<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style>
        .modal.fade {
            z-index: 20000 !important;
        }
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <script type="text/javascript">
        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
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
            $('#view_dialog_menu_absensi').dialog('close');
        }
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/absensi" action="saveDelete_absensi" cssClass="well form-horizontal">
                <legend align="left">Delete Absensi</legend>
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
                            <div style="display: none">
                                <label class="control-label"><small>Absensi ID :</small></label>
                            </div>
                        </td>
                        <td>
                            <table>
                                <div style="display: none">
                                    <s:textfield  id="absensiId" name="absensiPegawai.absensiPegawaiId" required="true" readonly="true" cssClass="form-control"/>
                                </div>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nipId" name="absensiPegawai.nip" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="namaId1" name="absensiPegawai.nama" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select disabled="true" cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="absensiPegawai.branchId" required="true" readonly="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
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
                                <s:select disabled="true" list="#comboDivisi.listComboDepartment" id="divisiId12" name="absensiPegawai.divisiId"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />

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
                                <s:select disabled="true" cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid12" name="absensiPegawai.posisiId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
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
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggal" name="absensiPegawai.stTanggal" cssClass="form-control pull-right"
                                                 readonly="true"  cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jam Kerja :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="jamAwal" name="absensiPegawai.jamMasuk" cssClass="form-control pull-right"
                                                 required="false" size="7"  readonly="true" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="jamPulang" name="absensiPegawai.jamPulang" cssClass="form-control pull-right"
                                                 required="false" size="7"  readonly="true" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Ijin Keluar :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="mulaiIzin" name="absensiPegawai.mulaiIzin" cssClass="form-control pull-right"
                                                 required="false" size="7"  readonly="true" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="pulangIzin" name="absensiPegawai.pulangIzin" cssClass="form-control pull-right"
                                                 required="false" size="7"  readonly="true" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Lembur :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="awalLembur" name="absensiPegawai.awalLembur" cssClass="form-control pull-right"
                                                 required="false" size="7"  readonly="true" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon" readonly="true">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="selesaiLembur" name="absensiPegawai.selesaiLembur" cssClass="form-control pull-right"
                                                 required="false" size="7"  readonly="true" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Biaya Lembur :</small></label>
                        </td>
                        <td width="100px">
                            <table>
                                <s:textfield  id="cutiId1" size="20" name="absensiPegawai.stBiayaLembur" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                   onCompleteTopics="closeDialog,successDialog" onBeforeTopics="beforeProcessDelete"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-trash"></i>
                            Delete
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-close"/> Close
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
                                                   height="350" width="600" autoOpen="false" title="Deleting ...">
                                            Please don't close this window, server is processing your request ...
                                            </br>
                                            </br>
                                            </br>
                                            <center>
                                                <img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>" name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
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
<div id="modal-view-lembur" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal View Lembur</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="ViewLembur">
                    <center>
                        <table id="showdata" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="listPengaliTable table table-bordered" id="listPengaliTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Pengajuan Lembur : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" readonly="true" id="pengajuanLembur" name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Realisasi Lembur : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" readonly="true" id="realisasiLembur" name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Jumlah Jam Perhitungan : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" readonly="true" id="jamLembur" name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Tarif Lembur Per Jam : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" readonly="true" id="lemburPerJam" name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Jumlah Upah Lembur : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" readonly="true" id="jumlahUpahLembur" name="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#btnViewLembur').click(function () {
            var nip = $('#nipId').val();
            var tanggal = $('#tanggal').val();
            dwr.engine.setAsync(false);
            AbsensiAction.searchDetailLembur(nip,tanggal, function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#pengajuanLembur').val(item.lamaLembur);
                    $('#realisasiLembur').val(item.realisasiJamLembur);
                    $('#jamLembur').val(item.jamLembur).change();
                    $('#lemburPerJam').val(item.lemburPerJam).change();
                    $('#jumlahUpahLembur').val(item.biayaLembur).change();
                });
            });
            $('#modal-view-lembur').find('.modal-title').text('View Lembur');
            $('#modal-view-lembur').modal('show');
        });
    });

</script>