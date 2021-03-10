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
        function cancelBtn() {
            $('#view_dialog_menu_absensi').dialog('close');
        }
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/absensi" action="saveEdit_absensi" cssClass="well form-horizontal">
                <legend align="left">View Absensi</legend>
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
                                    <s:textfield id="jamPulang" name="absensiPegawai.jamKeluar" cssClass="form-control pull-right"
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
                        <td>
                            <a href="javascript:void(0)">
                                <img sizes="30" id="btnViewLembur" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                            </a>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-close"/> Close
                        </button>
                    </div>
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
                    $('#lemburPerJam').val(item.stBiayaLemburPerJam).change();
                    $('#jumlahUpahLembur').val(item.stBiayaLembur).change();
                });
            });
            $('#modal-view-lembur').find('.modal-title').text('View Lembur');
            $('#modal-view-lembur').modal('show');
        });
    });

</script>