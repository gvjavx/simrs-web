<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        $.subscribe('beforeProcessSavePengajuanBatalCuti', function (event, data) {
            var keterangan;
            keterangan = document.getElementById("keteranganBatal0").value;
            var nip = document.getElementById("nipId0").value;
            var tglDari = document.getElementById("tgl210").value;
            var tglSelesai = document.getElementById("tgl110").value;
            console.log(tglDari);
            console.log(tglSelesai);
            if (keterangan!=="") {
                CutiPegawaiAction.cekIfAbsensi(nip, tglDari, tglSelesai, function(listdata){
                    if (listdata=="tidak"){
                        if (confirm('Do you want to cancel this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialogCancelCutiPegawai');
                        } else {
                            // Cancel Submit comes with 1.8.0
                            event.originalEvent.options.submit = false;
                        }
                    }else {
                        alert("Data Tersebut Tidak Bisa Dibatalkan Karena Telah Masuk Absensi")
                        event.originalEvent.options.submit = false;
                    }
                });
            }
            else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( keterangan === '') {
                    msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                }
                document.getElementById('errorMessage22').innerHTML = msg;
                $.publish('showErrorDialogCancelCutiPegawai');
            }
        });

        $.subscribe('beforeProcessDeleteCancelCutiPegawai', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialogCancelCutiPegawai');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });

        $.subscribe('successDialogCancelCutiPegawai', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialogCancelCutiPegawai');
            }
        });
        $.subscribe('errorDialogCancelCutiPegawai', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogCancelCutiPegawai');
        });

        function cancelBtn() {
            $('#view_dialog_menu_cuti_pegawai').dialog('close');
        }
        function callSearch() {
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu_cuti_pegawai').dialog('close');
            $('#info_dialog').dialog('close');
        }

    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="pengajuanBatalForm" method="post" theme="simple" namespace="/cutiPegawai" action="pengajuanBatal_cutiPegawai" cssClass="well form-horizontal">

                <s:hidden name="addOrCancel"/>
                <s:hidden name="delete"/>



                <legend align="left">Pengajuan Batal Cuti Pegawai</legend>


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
                                <label class="control-label"><small>Cuti Pegawai ID :</small></label>
                            </div>
                        </td>
                        <td>
                            <table>
                                <div style="display: none">
                                    <s:textfield  id="cutiPegawaiId0" name="cutiPegawai.cutiPegawaiId" required="true" readonly="true" cssClass="form-control"/>
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
                                <s:textfield  id="nipId0" name="cutiPegawai.nip" required="true" readonly="true" cssClass="form-control"/>
                                <s:textfield  id="golonganId12" name="" required="true" readonly="true" cssStyle="display:none;" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="namaId0" name="cutiPegawai.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
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
                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid10" name="cutiPegawai.posisiId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>                                    <%--<s:hidden name="cutiPegawai.cutiPegawaiId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId10" name="cutiPegawai.divisiId"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
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
                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid0" name="cutiPegawai.unitId" required="true" readonly="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'diluar_tanggungan':'Diluar Tanggungan'}" id="jenisCuti0" name="cutiPegawai.jenisCuti"
                                          headerKey="normal" headerValue="Normal" cssClass="form-control" disabled="true"/>
                                <s:textfield  id="jenisCuti33" name="cutiPegawai.jenisCuti" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Cuti :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>--%>
                                <%--<s:select list="#comboCuti.listComboCuti" id="cuti124" name="cutiPegawai.cutiId" readonly="true"--%>
                                          <%--listKey="cutiId" listValue="cutiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />--%>
                                <%--<s:textfield  id="jenisCuti" name="cutiPegawai.cutiName" required="false" readonly="true" cssClass="form-control" cssStyle="display: none"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr id="cuti1">
                        <td>
                            <label class="control-label"><small>Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>
                                <s:select list="#comboCuti.listComboCuti" id="cuti0" name="cutiPegawai.cutiId"
                                          listKey="cutiId" listValue="cutiName" headerKey="" headerValue="[Select one]" required="true" cssClass="form-control" disabled="true" />
                                <s:textfield  id="cutiId15" name="cutiPegawai.cutiId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:textfield  id="jenisCuti" name="cutiPegawai.cutiName" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr id="cuti2" style="display: none">
                        <td>
                            <label class="control-label"><small>Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboCuti" namespace="/cuti" name="initComboCuti2_cuti"/>
                                <s:select list="#comboCuti.listComboCuti2" id="cutiIdTanggungan11" name="cutiPegawai.cutiTanggunganId"
                                          listKey="cutiId" listValue="cutiName" headerKey="" headerValue="[Select one]" required="true" cssClass="form-control" disabled="true" />
                                <s:textfield  id="cutiId15Tanggungan" name="cutiPegawai.cutiTanggunganId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:textfield  id="jenisCutiTanggungan" name="cutiPegawai.cutiTanggunganName" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Sisa Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="sisaCuti0" name="cutiPegawai.sisaCutiHari" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Tanggal Awal Cuti :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<div class="input-group date">--%>
                                    <%--<div class="input-group-addon">--%>
                                        <%--<i class="fa fa-calendar"></i>--%>
                                    <%--</div>--%>
                                    <%----%>
                                <%--</div>--%>
                                <%--<s:textfield id="tgl2" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right" readonly="true"--%>
                                                 <%--required="true"  cssStyle=""/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Tanggal Selesai Cuti :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<div class="input-group date">--%>
                                    <%--<div class="input-group-addon">--%>
                                        <%--<i class="fa fa-calendar"></i>--%>
                                    <%--</div>--%>
                                    <%--<s:textfield id="tgl1" name="cutiPegawai.strTanggalSelesai" cssClass="form-control pull-right" readonly="true"--%>
                                                 <%--required="true"  cssStyle=""/></div>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Awal Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="tgl2" name="cutiPegawai.strTanggalDari2" required="false" readonly="true" cssClass="form-control" style="display: none"/>
                                <s:textfield  id="tgl210" name="cutiPegawai.strTanggalDari" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Selesai Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tgl1" name="cutiPegawai.strTanggalSelesai1" cssClass="form-control pull-right" readonly="true"
                                             required="true"  style="display: none"/>
                                <s:textfield id="tgl110" name="cutiPegawai.strTanggalSelesai" cssClass="form-control pull-right" readonly="true"
                                             required="true"  cssStyle=""/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Lama Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td width="60px">
                                        <s:textfield  id="lamaCuti" name="cutiPegawai.lamaHariCuti" required="false" readonly="true" cssClass="form-control"/>
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        hari
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Pegawai Pengganti Sementara :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield  id="pegawaiPenggantiSementara" name="cutiPegawai.pegawaiPenggantiSementara" required="false" readonly="true" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Batal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="3" id="keteranganBatal0" required="false" name="cutiPegawai.cancelNote"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="pengajuanBatalForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSavePengajuanBatalCuti" onCompleteTopics="closeDialogCancelCutiPegawai,successDialogCancelCutiPegawai"
                                   onSuccessTopics="successDialogCancelCutiPegawai" onErrorTopics="errorDialogCancelCutiPegawai" >
                            <i class="fa fa-check"></i>
                            Batalkan
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
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

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogCancelCutiPegawai"
                                                   closeTopics="closeDialogCancelCutiPegawai" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Save Data ...">
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialogCancelCutiPegawai" modal="true" resizable="false"
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

                                        <sj:dialog id="error_dialog12" openTopics="showErrorDialogCancelCutiPegawai" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog12').dialog('close'); }
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage22"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialogCancelCutiPegawai" modal="true" resizable="false"
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
        $('#tgl1').val("");
        $('#tgl2').val("");
        var namacuti= $('#cuti124').val();
        var branchid =$('#branchid').val();
        var nip=$('#nipId').val();
        var golonganid=document.getElementById("golonganId12").value;

        var tgldari = $('#tgl110').val();
        console.log(tgldari);
        var jenisCuti = $('#jenisCuti1').val();
        if (jenisCuti == "diluar_tanggungan"){
            $('#cuti2').show();
            $('#cuti1').hide();

            $('#cutiIdTanggungan11').val("CT007").change();
            $('#cutiId15Tanggungan').val("CT007");
            $('#sisaCuti').val('1095');
        }else {
            $('#cuti1').show();
            $('#cuti2').hide();
        }
    });

</script>
