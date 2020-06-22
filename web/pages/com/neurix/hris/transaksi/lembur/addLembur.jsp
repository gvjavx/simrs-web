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
            var jamAwal = document.getElementById("jamAwal").value;
            var jamAkhir = document.getElementById("jamAkhir").value;
            var lamaJam = document.getElementById("lamaJam").value;
//            var tipeLembur = document.getElementById("tipeLembur").value;
            var ket="";
            var ket2="";
            LemburAction.testTanggal(tglAwal,tglAkhir,nip, function (data) {
                if (data != "") {
                    ket=data;
                }
            });


            console.log(nip);
            console.log(tglAwal);
            console.log(tglAkhir);
            console.log(tipeLembur);
            console.log(ket);
            console.log(ket2);
            console.log(jamAwal);
            console.log(jamAkhir);
            console.log(lamaJam);

            if (nip != '' && tglAwal !=''&& tglAkhir !=''&& tipeLembur !=''&& ket == ''&&ket2 == ''&& jamAwal != ''&& jamAkhir != ''&& lamaJam == '') {
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
                    msg += 'Field <strong>NIP</strong>kosong.' + '<br/>';
                }
                if (tipeLembur === '') {
                    msg += 'Field <strong>Tipe Lembur</strong>kosong.' + '<br/>';
                }
                if (tglAwal === '') {
                    msg += 'Field <strong>Tanggal Awal</strong>kosong.' + '<br/>';
                }
                if (tglAkhir === '') {
                    msg += 'Field <strong>Tanggal Akhir </strong>kosong.' + '<br/>';
                }
                if (jamAwal === '') {
                    msg += 'Field <strong>Jam Awal </strong>kosong.' + '<br/>';
                }
                if (jamAkhir === '') {
                    msg += 'Field <strong>Jam Akhir </strong>kosong.' + '<br/>';
                }
                if (ket != "") {
                    $('#tglAwal').val("");
                    $('#tglAkhir').val("");
                    msg += '<strong>'+ket+'</strong>' + '<br/>';
                }
                if (ket2 != "") {
                    msg += '<strong> Masih ada lembur yang belum di Approve dari NIP ini </strong>' + '<br/>';
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
            <s:form id="addFormLembur" method="post" theme="simple" namespace="/lembur" action="saveAdd_lembur" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Add Lembur</legend>
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
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="check" name="lembur.self" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="lembur.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                <s:textfield  id="branchId33" name="lembur.branchId" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>

                        <td>
                            <table>
                                <s:textfield  id="nipId" name="lembur.nip" required="true" readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        $('#nipId').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};

                                var data = [];
                                var unit = $('#branchId').val();
                                if(unit==""){
                                    alert("unit is empty");
                                    $('#nipId').val("");
                                    $('#namaAddId').val("");
                                }else {
                                    dwr.engine.setAsync(false);
                                    LemburAction.initComboPersonil(query, unit, function (listdata) {
                                        data = listdata;
                                    });

                                    $.each(data, function (i, item) {
                                        var labelItem = item.nip+" "+item.namaPegawai;
                                        mapped[labelItem] = {id: item.nip,nama:item.namaPegawai, label: labelItem, divisi:item.divisi,jabatan:item.positionId,golongan:item.golonganId,tipePegawai:item.tipePegawai,statusGiling:item.masaGiling,statusPegawai:item.statusPegawai};
                                        functions.push(labelItem);
                                    });
                                    process(functions);
                                }
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                var namaMember = selectedObj.label;
                                if (selectedObj.statusPegawai=="PKWT"&&$('#branchId').val()!="KD01"){
                                    $('#divisiId').val(selectedObj.divisi).change();
                                    $('#divisiId1').val(selectedObj.divisi).change();
                                    $('#namaAddId').val(selectedObj.nama).change();
                                    $('#positionId').val(selectedObj.jabatan).change();
                                    $('#positionId1').val(selectedObj.jabatan).change();
                                    $('#golonganId').val(selectedObj.golongan).change();
                                    $('#golonganId1').val(selectedObj.golongan).change();
                                    $('#tipePegawai').val(selectedObj.tipePegawai).change();
                                    $('#tipePegawai1').val(selectedObj.tipePegawai).change();
                                    $('#statusGiling12').val(selectedObj.statusGiling).change();
                                    $('#statusGiling1').val(selectedObj.statusGiling).change();
                                    return selectedObj.id;
                                } else if (selectedObj.statusPegawai!="KS"){
                                    $('#divisiId').val(selectedObj.divisi).change();
                                    $('#divisiId1').val(selectedObj.divisi).change();
                                    $('#namaAddId').val(selectedObj.nama).change();
                                    $('#positionId').val(selectedObj.jabatan).change();
                                    $('#positionId1').val(selectedObj.jabatan).change();
                                    $('#golonganId').val(selectedObj.golongan).change();
                                    $('#golonganId1').val(selectedObj.golongan).change();
                                    $('#tipePegawai').val(selectedObj.tipePegawai).change();
                                    $('#tipePegawai1').val(selectedObj.tipePegawai).change();
                                    $('#statusGiling12').val(selectedObj.statusGiling).change();
                                    $('#statusGiling1').val(selectedObj.statusGiling).change();
                                    return selectedObj.id;
                                } else {
                                    alert("Pimpinan tidak bisa mengambil Lembur");
                                    $('#nipId').val("");
                                }
                            }
                        });
                    </script>
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
                            <label class="control-label"><small>Level :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="lembur.golonganId" disabled="true"
                                          listKey="golonganId" listValue="stLevel" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true"/>
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
                    <%--<tr>
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
                    </tr>--%>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Lembur :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'I':'Non Rutin','R':'Rutin'}" id="tipeLembur" name="lembur.tipeLembur"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
        function change () {
            var tipeLembur = $('#tipeLembur').val();
            var branchAsli;
            var branch = $('#branchId').val();
            var branch33 = $('#branchId33').val();
            if ($('#check').val()=="Y") {
                branchAsli=branch33;
            }else{
                branchAsli=branch;
            }
            if (tipeLembur=="I"){
                $('.R').css("display", "none");
                $('.I').removeAttr("style");
                $('#lamaJam').attr("name","");
                $('#lamaTiapHari').attr("name","lembur.lamaJam");
            }
            else if (tipeLembur=="R"){
                if(branchAsli=="KD01"){
                    alert("Kantor Direksi tidak boleh mengambil lembur rutin");
                    $('#tipeLembur').val("");
                    change();
                }else{
                    $('.I').css("display", "none");
                    $('.R').removeAttr("style");
                    $('#lamaTiapHari').attr("name","");
                    $('#lamaJam').attr("name","lembur.lamaJam");
                }
            }
            else {
                $('.R').css("display", "none");
                $('.I').css("display", "none");
            }
        }
        if ($('#check').val()=="Y"){
            $('#branchId').attr('readonly','true');
            $('#branchId').attr('disabled','true');
            $('#nipId').attr('readonly','true');
        }else{
            $('#branchId33').attr('disabled','true');
        }
        $('body').click(function(){
            var jamawal=$('#jamAwal').val();
            var jamakhir=$('#jamAkhir').val();
            if(jamawal!==""&&jamakhir!==""){
                onChange();
            }
        });
        $('.R').css("display", "none");
        $('.I').css("display", "none");

        $('#tipeLembur').change(function(){
            change();
        });
        $('#tglAwal').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tglAkhir').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        function onChange() {
            var nip44,jamAwal44,jamAkhir44,tglAwal,tglAkhir;
            nip44=$('#nipId').val();
            jamAwal44=$('#jamAwal').val();
            jamAkhir44=$('#jamAkhir').val();
            tglAwal=document.getElementById('tglAwal').value;
            tglAkhir=document.getElementById('tglAkhir').value;

            if (nip44!=""&&jamAwal44!=""&&jamAkhir44!=""&&tglAwal!=""&&tglAkhir!=""){
                LemburAction.calcJamLembur(nip44,tglAwal,tglAkhir,jamAwal44,jamAkhir44, function (data) {
                    if (data != "") {
                        $('#lamaTiapHari').val(data);
                    }
                });
            }
        }
        $('#jamAwal').timepicker();
        $('#jamAkhir').timepicker();
    });
</script>

