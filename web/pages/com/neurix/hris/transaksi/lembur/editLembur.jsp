<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <script type='text/javascript' src="<s:url value="/pages/plugins/daterangepicker/moment.js"/>"></script>
    <style type="text/css">
        #tglAwal{z-index: 2000!important}
        #tglAkhir{z-index: 2000!important}
        #jamAwal{z-index: 2000!important}
        #jamAkhir{z-index: 2000!important}
    </style>
    <script type="text/javascript">
        var unit = '<s:property value="Lembur.unitId"/>';
        function calcBusinessDays(dDate1, dDate2) { // input given as Date objects
            var iWeeks, iDateDiff, iAdjust = 0;
            if (dDate2 < dDate1) return -1; // error code if dates transposed
            var iWeekday1 = dDate1.getDay(); // day of week
            var iWeekday2 = dDate2.getDay();
            iWeekday1 = (iWeekday1 == 0) ? 7 : iWeekday1; // change Sunday from 0 to 7
            iWeekday2 = (iWeekday2 == 0) ? 7 : iWeekday2;
            if ((iWeekday1 > 5) && (iWeekday2 > 5)) iAdjust = 1; // adjustment if both days on weekend
            iWeekday1 = (iWeekday1 > 5) ? 5 : iWeekday1; // only count weekdays
            iWeekday2 = (iWeekday2 > 5) ? 5 : iWeekday2;

            // calculate differnece in weeks (1000mS * 60sec * 60min * 24hrs * 7 days = 604800000)
            iWeeks = Math.floor((dDate2.getTime() - dDate1.getTime()) / 604800000);

            if (iWeekday1 <= iWeekday2) {
                iDateDiff = (iWeeks * 5) + (iWeekday2 - iWeekday1)
            } else {
                iDateDiff = ((iWeeks + 1) * 5) - (iWeekday1 - iWeekday2)
            }
            iDateDiff -= iAdjust; // take into account both days on weekend
            return (iDateDiff + 1); // add 1 because dates are inclusive
        }
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        $.subscribe('beforeProcessSaveLembur', function (event, data) {
            var nip = document.getElementById("nipId").value;
            var tglAwal = document.getElementById("tglAwal").value;
            var tglAkhir = document.getElementById("tglAkhir").value;
            var tipeLembur = $('#tipeLembur').val();
            var ket="";
            LemburAction.testTanggal(tglAwal,tglAkhir,nip, function (data) {
                if (data !== "") {
                    ket=data;
                }
            });
            if (nip !== ''/*&& tglAwal !== ''&& tglAkhir !== ''&& tipeLembur !== ''&& ket === ''*/) {
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

                if (nip === '') {
                    msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                }
                if (tglAwal === '') {
                    msg += 'Field <strong>Tanggal Awal</strong> is required.' + '<br/>';
                }
                if (tglAkhir === '') {
                    msg += 'Field <strong>Tanggal Akhir </strong> is required.' + '<br/>';
                }
                if (tipeLembur === '') {
                    msg += 'Field <strong>Tipe Lembur</strong> is required.' + '<br/>';
                }
                if (ket !== "") {
                    $('#tglAwal').val("");
                    $('#tglAkhir').val("");
                    msg += '<strong>'+ket+'</strong>' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;
                $.publish('showErrorValidationDialog');
            }
        });
        $.subscribe('beforeProcessDeleteLembur', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                event.originalEvent.options.submit = false;
            }
        });
        $.subscribe('successDialogLembur', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });
        $.subscribe('errorDialogLembur', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });
        function cancelBtn() {
            $('#dialog_menu_lembur').dialog('close');
        }
    </script>
</head>
<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addFormLembur" method="post" theme="simple" namespace="/lembur" action="saveEdit_lembur" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <s:textfield  id="lemburId" name="lembur.lemburId" required="false" readonly="true" cssClass="form-control" cssStyle="display: none"/>
                <legend align="left">Edit Lembur</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>

                        <td>
                            <table>
                                <s:textfield  id="nipId" name="lembur.nip" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="namaAddId" name="lembur.pegawaiName" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="lembur.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" readonly="true" cssClass="form-control"/>
                            </table>
                            <s:textfield  id="positionId1" name="lembur.positionId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId"
                                          listKey="departmentId" listValue="departmentName" name="lembur.divisiId" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />
                                <s:textfield  id="divisiId1" name="lembur.divisiId" required="false" cssStyle="display: none" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Golongan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="lembur.golonganId" disabled="true"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true"/>
                                <s:textfield  id="golonganId1" name="lembur.golonganId" cssStyle="display: none" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Pegawai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                                <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai" name="lembur.tipePegawaiId" listKey="tipePegawaiId" listValue="tipePegawaiName"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true"/>
                                <s:textfield  id="tipePegawai1" name="lembur.tipePegawaiId" required="false" cssStyle="display: none" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Status Giling :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}" id="statusGiling12" name="lembur.statusGiling"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true"/>
                                <s:textfield  id="statusGiling1" cssStyle="display: none" name="lembur.statusGiling" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Lembur :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="tipeLembur" name="lembur.tipeLembur" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:textfield  id="tipeLemburName" name="lembur.tipeLemburName" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr class="M R I">
                        <td>
                            <label class="control-label"><small>Tanggal :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tglAwal" name="lembur.stTanggalAwal" cssClass="form-control pull-right"
                                                 required="false" cssStyle="" size="12"/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tglAkhir" name="lembur.stTanggalAkhir" size="12" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr class="I">
                        <td>
                            <label class="control-label"><small>Jam :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="jamAwal" name="lembur.jamAwal" size="12" cssClass="form-control pull-right" onchange="onChange()"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="jamAkhir" name="lembur.jamAkhir" size="12" cssClass="form-control pull-right" onchange="onChange()"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr class="M R">
                        <td>
                            <label class="control-label"><small>Lama ( Jam ) :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="lamaJam" name="lembur.lamaJam" size="12" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        Jam
                                    </div>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr class="I">
                        <td>
                            <label class="control-label"><small>Lama Tiap Hari (jam) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="lamaTiapHari" name="lembur.lamaJam" readonly="true" required="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textarea rows="4" id="keterangan" name="lembur.keterangan" required="false" cssClass="form-control"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addFormLembur" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveLembur" onCompleteTopics="closeDialog,successDialogLembur"
                                   onSuccessTopics="successDialogLembur" onErrorTopics="errorDialogLembur" >
                            <i class="fa fa-check"></i>
                            Save
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
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                   closeTopics="closeDialog" modal="true"
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
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
    $(document).ready(function(){
        $('.R').css("display", "none");
        $('.I').css("display", "none");
        $('.M').css("display", "none");
        var tipeLembur = $('#tipeLembur').val();
        if (tipeLembur=="M"){
            $('.R').css("display", "none");
            $('.I').css("display", "none");
            $('.M').removeAttr("style");
            $('#lamaTiapHari').attr("name","");
            $('#lamaJam').attr("name","lembur.lamaJam");
        }
        else if (tipeLembur=="I"){
            $('.M').css("display", "none");
            $('.R').css("display", "none");
            $('.I').removeAttr("style");
            $('#lamaJam').attr("name","");
            $('#lamaTiapHari').attr("name","lembur.lamaJam");
        }
        else if (tipeLembur=="R"){
            $('.M').css("display", "none");
            $('.I').css("display", "none");
            $('.R').removeAttr("style");
            $('#lamaTiapHari').attr("name","");
            $('#lamaJam').attr("name","lembur.lamaJam");
        }
        else {
            $('.R').css("display", "none");
            $('.I').css("display", "none");
            $('.M').css("display", "none");
        }
        $('#tglAwal').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tglAkhir').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        function onChange() {
            var jamawal,jamakhir,hasil;
            jamawal=document.getElementById('jamAwal').value;
            jamakhir=document.getElementById('jamAkhir').value;
            if (jamawal!=""&&jamakhir!=""){
                var jamAwalLembur = moment(jamawal,'HH:mm');
                var jamAkhirLembur = moment(jamakhir,'HH:mm');
                var dur = moment.duration(jamAkhirLembur - jamAwalLembur);
                hasil=dur.hours();
                if (dur.minutes()<15){hasil=hasil+0;}
                else if (dur.minutes()<30){hasil=hasil+0.25;}
                else if (dur.minutes()<45){hasil=hasil+0.50;}
                else if (dur.minutes()<60){hasil=hasil+0.75;}
                var tmpHasil= parseInt(hasil);
                if(tmpHasil<0){
                    var tmp1 = "2000-01-01 "+jamawal;
                    var tmp2 = "2000-01-02 "+jamakhir;
                    jamAwalLembur = moment(tmp1,'YYYY-MM-DD HH:mm');
                    jamAkhirLembur = moment(tmp2,'YYYY-MM-DD HH:mm');
                    alert(jamAwalLembur);
                    alert(jamAkhirLembur);
                    dur = moment.duration(jamAkhirLembur - jamAwalLembur);
                    alert(dur);
                    hasil=dur.hours();
                    if (dur.minutes()<15){hasil=hasil+0;}
                    else if (dur.minutes()<30){hasil=hasil+0.25;}
                    else if (dur.minutes()<45){hasil=hasil+0.50;}
                    else if (dur.minutes()<60){hasil=hasil+0.75;}
                }
                $('#lamaTiapHari').val(hasil);
            }
        }
        $('#jamAwal').timepicker();
        $('#jamAkhir').timepicker();

        $('body').click(function(){
            var jamawal=$('#jamAwal').val();
            var jamakhir=$('#jamAkhir').val();
            if(jamawal!==""&&jamakhir!==""){
                onChange();
            }
        });
    });
</script>