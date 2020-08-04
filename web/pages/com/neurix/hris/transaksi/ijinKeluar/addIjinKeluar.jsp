<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <style type="text/css">
        #tgl3{z-index: 2000!important}
        #tgl2{z-index: 2000!important}
        .typeahead{z-index: 3000!important}
    </style>
    <script type="text/javascript">
        var unit = '<s:property value="IjinKeluar.unitId"/>';

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

            iDateDiff -= iAdjust // take into account both days on weekend

            return (iDateDiff + 1); // add 1 because dates are inclusive
        }

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };
        $.subscribe('beforeProcessSaveIjin', function (event, data) {
            var unit = document.getElementById("branchId").value;
            var nipid = document.getElementById("nipId").value;
            var nama = document.getElementById("namaAddId").value;
            var ijinid = document.getElementById("ijinId1").value;
            var tglawal = document.getElementById("tgl3").value;
            var tglakhir = document.getElementById("tgl2").value;
            var keterangan = document.getElementById("keterangan").value;
            var ket="";
            var cek="";
            LemburAction.testTanggal(tglawal,tglakhir,nipid, function (data) {
                if (data !== "") {
                    ket=data;
                }
            });
            dwr.engine.setAsync(false);
            IjinKeluarAction.cekNipIjinKeluar(nipid,function(data){
                if (data!=""){
                    cek = data;
                }
            });

            if (unit !== ''&& nipid !== ''&& ijinid !== ''&& tglawal !== ''&& tglakhir !== ''&& nama !== ''&& ket === '' && cek === '') {
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

                if (unit === '') {
                    msg += 'Field <strong>Unit Name</strong> is required.' + '<br/>';
                }
                if (nipid === '') {
                    msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                }
                if (ijinid === '') {
                    msg += 'Field <strong>Ijin </strong> is required.' + '<br/>';
                }
                if (tglawal === '') {
                    msg += 'Field <strong>Tanggal Awal</strong> is required.' + '<br/>';
                }
                if (tglakhir === '') {
                    msg += 'Field <strong>Tanggal Akhir</strong> is required.' + '<br/>';
                }
                if (nama === '') {
                    msg += '<strong>NIP</strong> belum terdaftar.' + '<br/>';
                }
                if (ket !== "") {
                    $('#tgl3').val("");
                    $('#tgl2').val("");
                    $('#lamaId').val("");
                    msg += '<strong>'+ket+'</strong>' + '<br/>';
                }
                if (cek != '') {
                    msg += 'Ada dispensasi yang masih di ajukan<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('beforeProcessDeleteIjin', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialogIjin', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialogIjin', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#dialog_menu_ijin_keluar').dialog('close'); window.location.reload(true);
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addFormIjin" method="post" theme="simple" namespace="/ijinKeluar" action="saveAdd_ijinKeluar" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>


                <s:textfield  id="check" name="ijinKeluar.self" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                <legend align="left">Add Dispensasi</legend>


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
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="ijinKeluar.unitId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                <s:textfield  id="branchId33" name="ijinKeluar.unitId" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>

                        <td>
                            <table>
                                <s:textfield  id="nipId" name="ijinKeluar.nip" required="true" readonly="false" cssClass="form-control"/>
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
                                dwr.engine.setAsync(false);
                                var unit1 = document.getElementById('branchId').value;
                                if (unit1!==''){
                                    IjinKeluarAction.initComboPersonil(query, unit1, function (listdata) {
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
                                var namaMember = selectedObj.label;
                                $('#divisiId12').val(selectedObj.divisi).change();
                                $('#divisiId33').val(selectedObj.divisi).change();
                                $('#namaAddId').val(selectedObj.nama).change();
                                $('#positionId12').val(selectedObj.position).change();
                                $('#positionId33').val(selectedObj.position).change();
                                $('#golonganId124').val(selectedObj.golongan).change();
                                $('#golonganId33').val(selectedObj.golongan).change();
                                var nip = selectedObj.id;
                                $('#ijinId1').empty();
                                $('#ijinId1').append($("<option></option>")
                                    .attr("value","")
                                    .text(""));
                                IjinAction.searchIjin(nip, function(listdata){
                                    $.each(listdata, function (i, item) {
                                        $('#ijinId1').append($("<option></option>")
                                            .attr("value",item.ijinId)
                                            .text(item.ijinName));
                                        $('#ijinName1').val(item.ijinName);
                                    });
                                });
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
                                <s:textfield  id="namaAddId" name="ijinKeluar.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
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
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId12" name="ijinKeluar.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                                <s:textfield  id="divisiId33" cssStyle="display: none" name="ijinKeluar.divisiId" required="false" readonly="true" cssClass="form-control"/>
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
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId12" name="ijinKeluar.positionId" readonly="true" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                <s:textfield  id="positionId33" cssStyle="display: none" name="ijinKeluar.positionId" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Level :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId124" name="ijinKeluar.golonganId"
                                          listKey="golonganId" listValue="stLevel" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />
                                <s:textfield  id="golonganId33" cssStyle="display: none" name="ijinKeluar.golonganId" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Ijin :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboIjin" namespace="/ijin" name="initComboIjin_ijin"/>
                                <s:select list="#initComboIjin.listOfComboIjin" id="ijinId1" name="ijinKeluar.ijinId"
                                          listKey="ijinId" listValue="ijinName"  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                <s:textfield cssStyle="display: none" id="ijinName1" name="ijinKeluar.ijinName" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                            <script>
                                $(document).ready(function() {
                                    $('#ijinId1').change(function() {
                                        var namaijin= $('#ijinId1').val();
                                        dwr.engine.setAsync(false);
                                        IjinAction.initComboLamaIjin(namaijin, function (listdata) {
                                            data = listdata;
                                        });
                                        $.each(data, function (i, item) {
                                            if (namaijin == "IJ030"){
                                                $('#maxIjin').val("-").change();
                                            }else {
                                                $('#maxIjin').val(item.jumlahIjin).change();
                                            }
                                            $('#ijinName1').val(item.ijinName).change();
                                        });

                                        if (namaijin == "IJ013") {
                                            $('#uploadSurat').show();
                                        } else {
                                            $('#uploadSurat').hide();
                                        }
                                    })
                                });
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Max Ijin :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="maxIjin" name="" required="false"  readonly="true" cssClass="form-control"/>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" style="text-align: left !important;"><small>Tanggal Awal :</small></label>
                        </td>
                        <td>
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tgl3" name="ijinKeluar.stTanggalAwal" cssClass="form-control pull-right"
                                             required="false" onchange="getTanggalAkhir(this.value)" cssStyle=""/>
                                    <%--<input type="text" class="form-control pull-right" id="loginTimestampFrom" name="userSessionLog.stLoginTimestampFrom">--%>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" style="text-align: left !important;"><small>Tanggal Akhir :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl2" name="ijinKeluar.stTanggalAkhir" cssClass="form-control pull-right"
                                                 required="false" />
                                        <%--<input type="text" class="form-control pull-right" id="loginTimestampFrom" name="userSessionLog.stLoginTimestampFrom">--%>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Lama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="lamaId" name="ijinKeluar.lamaIjin" required="false"  readonly="true" cssClass="form-control"/>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" style="text-align: left !important;"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textarea rows="4" id="keterangan" name="ijinKeluar.keterangan" required="true" disabled="false" cssClass="form-control"/>
                                    </td>
                                </tr>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr id="uploadSurat" style="display: none">
                        <td>
                            <label class="control-label" style="text-align: left !important;"><small>Surat Dokter :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <input type="file" id="file" class="form-control" name="fileUpload"/>
                                        <input type="text" id="cpiddoc" class="form-control" accept="application/pdf,image/jpeg"
                                               name="ijinKeluar.uploadFile" readonly style="display: none;" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addFormIjin" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveIjin" onCompleteTopics="closeDialog,successDialogIjin"
                                   onSuccessTopics="successDialogIjin" onErrorTopics="errorDialogIjin" >
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
                                                                        'OK':function() { $('#error_dialog').dialog('close'); window.location.reload(true)}
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
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); window.location.reload(true)}
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
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    var today = dd+'/'+mm+'/'+yyyy;
    function convertDate(inputFormat) {
        function pad(s) { return (s < 10) ? '0' + s : s; }
        var d = new Date(inputFormat);
        return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/');
    }

    $(document).ready(function() {
        if ($('#check').val()=="Y"){
            $('#branchId').attr('readonly','true');
            $('#branchId').attr('disabled','true');
            $('#nipId').attr('readonly','true');
            var nip = $('#nipId').val();
            $('#ijinId1').empty();
            $('#ijinId1').append($("<option></option>")
                .attr("value","")
                .text(""));
            IjinAction.searchIjin(nip, function(listdata){
                $.each(listdata, function (i, item) {
                    $('#ijinId1').append($("<option></option>")
                        .attr("value",item.ijinId)
                        .text(item.ijinName));
                    $('#ijinName1').val(item.ijinName);
                });
            });
        }
        else {
            $('#branchId33').attr('disabled','true');
        }

        nipid=document.getElementById("nipId").value;
        $('#nipId').change(function() {
            var nip;
            nip=document.getElementById("nipId").value;
            dwr.engine.setAsync(false);
            IjinKeluarAction.initComboSisaIjinKeluarId(nip, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                var labelItem = item.sisaCutiHari;
                mapped[labelItem] = { id: item.ijinKeluarId, nip: item.nip, sisaCutiHari : item.sisaCutiHari};
                var selectedObj = mapped[item];
                $('#sisaCuti').val(labelItem).change();
            });
        });
        function callSearch2() {
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
    });
    $('#tgl3').datepicker({
        dateFormat: 'dd/mm/yy'
    });
   $('#tgl2').datepicker({
        dateFormat: 'dd/mm/yy'
   });
    $('#tgl2').on('change',function(){
        var nip=document.getElementById("nipId").value;
        var tglawal=document.getElementById("tgl3").value;
        var tglakhir = document.getElementById("tgl2").value;
        var startdate =$('#tgl3').datepicker('getDate');
        var enddate =$('#tgl2').datepicker('getDate');
        var days = calcBusinessDays(startdate,enddate);
        var jmllibur;
        dwr.engine.setAsync(false);
        IjinKeluarAction.calculateLibur(tglawal,tglakhir, function (listdata) {
            jmllibur = listdata;
        });
        $('#lamaId').val(days-jmllibur);
        var max =parseInt(document.getElementById("maxIjin").value);
        if (enddate<startdate){
            alert ('Tanggal yang dipilih salah');
                    $('#tgl2').val("");
        }
        if (max != "-"){
            console.log("test opname");
            if (days>max){
                alert ("maaf anda melebihi ijin maksimal");
                $('#lamaId').val("");
                $('#tgl2').val("");
            }
        }
    });

    window.getTanggalAkhir = function (tanggal) {
        var ijinId = $('#ijinId1').val();
        if (ijinId == 'IJ013'){
            var date = $('#tgl3').datepicker('getDate');
            console.log(date);
            date.setDate(date.getDate()+45);
            var d = new Date(date),
                    month = '' + (d.getMonth() + 1),
                    day = '' + (d.getDate()),
                    year = '' + (d.getFullYear());
            if (month.length < 2)
                month = '0' + month;
            if (day.length < 2)
                day = '0' + day;

            var dateFinal = [day,month,year].join('/');
            $('#tgl2').val(dateFinal);

            console.log(dateFinal);

            var startdate = $('#tgl3').datepicker('getDate');
            var enddate = date;
            console.log(startdate);
            console.log(enddate);
            if(startdate<enddate) {
                var days   = (enddate - startdate)/1000/60/60/24;
                $('#lamaId').val(days);
            }
            else {
                alert ("tanggal selesai kurang dari tanggal mulai , mohon ulangi ");
                $('#tgl2').val("");
            }
        }
    };
</script>
