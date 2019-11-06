<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style type="text/css">
        #tgl1,#tgl2{z-index: 2000!important}
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }

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
            iWeeks = Math.floor((dDate2.getTime() - dDate1.getTime()) / 604800000)
            if (iWeekday1 <= iWeekday2) {
                iDateDiff = (iWeeks * 5) + (iWeekday2 - iWeekday1)
            } else {
                iDateDiff = ((iWeeks + 1) * 5) - (iWeekday1 - iWeekday2)
            }
            iDateDiff -= iAdjust;// take into account both days on weekend
            return (iDateDiff + 1); // add 1 because dates are inclusive
        }

        $.subscribe('beforeProcessSaveEditCutiPegawai', function (event, data) {
            var tanggalAwal    = document.getElementById("tgl2").value;
            var tanggalAkhir  = document.getElementById("tgl1").value;
            var nip = document.getElementById("nipId").value;
            var ket ="";
            LemburAction.testTanggal(tanggalAwal,tanggalAkhir,nip, function (data) {
                if (data !== "") {
                    ket=data;
                }
            });
            if (tanggalAwal!==""&&tanggalAkhir!==""&&nip!==""&&ket===""){
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialogEditCutiPegawai');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            }else{
                event.originalEvent.options.submit = false;
                var msg = "";
                if (nip === "") {
                    msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                }
                if (tanggalAwal === "") {
                    msg += 'Field <strong>Tanggal Awal</strong> is required.' + '<br/>';
                }
                if (tanggalAkhir === "") {
                    msg += 'Field <strong>Tanggal Akhir</strong> is required.' + '<br/>';
                }
                if (ket !== "") {
                    $('#tgl1').val("");
                    $('#tgl2').val("");
                    $('#lamaCuti').val("");
                    msg += '<strong>'+ket+'</strong>' + '<br/>';
                }
                document.getElementById('errorMessageEditCuti').innerHTML = msg;
                $.publish('showErrorValidationDialogEditCutiPegawai');
            }

        });

        $.subscribe('beforeProcessDeleteEditCutiPegawai', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialogEditCutiPegawai');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialogEditCutiPegawai', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialogEditCutiPegawai');
            }
        });

        $.subscribe('errorDialogEditCutiPegawai', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogEditCutiPegawai');
        });

        function cancelBtn() {
            $('#view_dialog_menu_cuti_pegawai').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/cutiPegawai" action="saveEdit_cutiPegawai" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Cuti Pegawai</legend>


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
                                    <s:textfield  id="cutiPegawaiId" name="cutiPegawai.cutiPegawaiId" required="true" readonly="true" cssClass="form-control"/>
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
                                <s:textfield  id="nipId" name="cutiPegawai.nip" required="true" readonly="true" cssClass="form-control"/>
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
                                <s:textfield  id="namaId1" name="cutiPegawai.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid12" name="cutiPegawai.posisiId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>                                    <%--<s:hidden name="cutiPegawai.cutiPegawaiId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId12" name="cutiPegawai.divisiId"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />                                    <%--<s:hidden name="cutiPegawai.cutiPegawaiId"/>--%>

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
                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="cutiPegawai.unitId" required="true" readonly="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />                                    <%--<s:hidden name="cutiPegawai.cutiPegawaiId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>
                                <s:select list="#comboCuti.listComboCuti" id="cuti124" name="cutiPegawai.cutiId" readonly="true"
                                          listKey="cutiId" listValue="cutiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                <s:textfield  id="jenisCuti" name="cutiPegawai.cutiName" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Sisa Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="sisaCuti" name="cutiPegawai.sisaCutiHari" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Awal Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl2" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                 required="true"  cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Selesai Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl1" name="cutiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
                                                 required="true"  cssStyle=""/></div>
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
                    <tr>
                        <td>
                            <label class="control-label"><small>Alamat Cuti :</small></label>
                        </td>
                        <td>
                            <s:textfield  id="alamatCuti12" name="cutiPegawai.alamatCuti" required="false" cssClass="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <s:textarea rows="2" id="keterangan12" name="cutiPegawai.keterangan" required="true" cssClass="form-control"/>                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Pegawai Pengganti Sementara :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="pegawaiPenggantiSementara" name="cutiPegawai.pegawaiPenggantiSementara" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveEditCutiPegawai" onCompleteTopics="closeDialogEditCutiPegawai,successDialogEditCutiPegawai"
                                   onSuccessTopics="successDialogEditCutiPegawai" onErrorTopics="errorDialogEditCutiPegawai" >
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
                                        <sj:dialog id="waiting_dialog" openTopics="showDialogEditCutiPegawai" closeTopics="closeDialogEditCutiPegawai" modal="true"
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialogEditCutiPegawai" modal="true" resizable="false"
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

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialogEditCutiPegawai" modal="true" resizable="false"
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

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialogEditCutiPegawai" modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorMessageEditCuti"></div></center>
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
        if (namacuti=="CT006"){
            dwr.engine.setAsync(false);
            CutiPegawaiAction.initComboCutiPanjangFull(golonganid,branchid, function (listdata) {
                data=listdata;
            });
            $.each(data, function (i,item) {
                $('#jenisCuti').val(item.tipeHari).change();
                $('#cutiMax').val(item.jumlahCuti);
            });
        }
        else {
            dwr.engine.setAsync(false);
            CutiAction.initComboCutiTipe(namacuti, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                $('#jenisCuti').val(item.tipeHari).change();
                $('#cutiMax').val(item.jumlahCuti);
            });
        }
        function callSearch2() {
            //alert('okok');
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        };
        $('#tgl1').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tgl2').datepicker({
            dateFormat: 'dd/mm/yy'
        });
    });

    $('#tgl1').on('change',function(){
        var startdate = $('#tgl2').datepicker('getDate');
        var enddate = $('#tgl1').datepicker('getDate');
        var tglAwal = $('#tgl2').val();
        var tglAkhir = $('#tgl1').val();
        var days;
        var jmllibur;
        dwr.engine.setAsync(false);
        IjinKeluarAction.calculateLibur(tglAwal,tglAkhir, function (listdata) {
            jmllibur = listdata;
        });
        if (startdate<=enddate) {
            var kalender=document.getElementById("jenisCuti").value;
            if (kalender=="kalender"){
                 days = (enddate - startdate)/1000/60/60/24;
                days = days+1;
                days = days-jmllibur;
            }
            else if ( kalender=="kerja") {
                days = calcBusinessDays(startdate,enddate);
                days = days-jmllibur;
            }
            else {
                alert("Belum memilih Jenis Cuti");
                $('#tgl1').val("");
            }
            $('#lamaCuti').val(days);
        }
        else {
            alert ("tidak bisa sebelum tanggal sekarang , mohon ulangi ");
            $('#tgl1').val("");
        }
    });
    $('#tgl2').on('change',function(){
    });

</script>
