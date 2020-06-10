<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<%@ taglib prefix="S" uri="/struts-tags" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <style type="text/css">
        #tgl1{z-index: 2000!important}
    </style>
    <script type="text/javascript">

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

            iDateDiff -= iAdjust// take into account both days on weekend

            return (iDateDiff + 1); // add 1 because dates are inclusive
        }

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        $.subscribe('beforeProcessSaveCuti', function (event, data) {
            var nipid  = document.getElementById("nipId").value;
            var unitid = document.getElementById("unitId12").value;
            var cutiid = document.getElementById("cutiId").value;
            var tanggalAwal    = document.getElementById("tgl2").value;
            var tanggalAkhir  = document.getElementById("tgl1").value;
            var sisaCuti  = document.getElementById("sisaCuti").value;
            var lamaCuti  = document.getElementById("lamaCuti").value;
            var ket="";
            var cek="";
            var intSisaCuti = parseInt(sisaCuti);
            var intLamaCuti = parseInt(lamaCuti);
            var todayDate = new Date().toISOString().slice(0,10);
            LemburAction.testTanggal(tanggalAwal,tanggalAkhir,nipid, function (data) {
                if (data != "") {
                    ket=data;
                }
            });
            CutiPegawaiAction.cekNipCuti(nipid,function(data){
              if (data!=""){
                  cek = data;
              }
            });
            if (intSisaCuti - intLamaCuti < 0){
                var sisaCutiMsg ='Maaf, Sisa cuti yang anda ajukan sudah habis';
                document.getElementById('errorMessageAddCuti').innerHTML = sisaCutiMsg;
                $.publish('showErrorValidationDialog');
                event.originalEvent.options.submit = false;

            }
            else{
                if ( nipid != ''&& cutiid != ''&& tanggalAkhir != '' && tanggalAwal != ''&&ket==""&&unitid!=""&&cek=="") {
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
                    if ( unitid == '') {
                        msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                    }
                    if ( nipid == '') {
                        msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                    }
                    if ( cutiid == '') {
                        msg += 'Field <strong>Cuti</strong> is required.' + '<br/>';
                    }
                    if ( tanggalAwal == '') {
                        msg += 'Field <strong>Tanggal awal</strong> is required.' + '<br/>';
                    }
                    if ( tanggalAkhir == '') {
                        msg += 'Field <strong>Tanggal Akhir</strong> is required.' + '<br/>';
                    }
                    if (ket != "") {
                        $('#tgl1').val("");
                        $('#tgl2').val("");
                        $('#lamaCuti').val("");
                        msg += '<strong>'+ket+'</strong>' + '<br/>';
                    }
                    if ( cek != '') {
                        msg += 'Ada cuti yang masih di ajukan<br/>';
                    }
                    document.getElementById('errorMessageAddCuti').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }

//                CutiPegawaiAction.cekStatusTanggal(tanggalAwal, function(data){
//                    if (data=='Boleh'){
//                        if ( nipid != ''&& cutiid != ''&& tanggalAkhir != '' && tanggalAwal != ''&&ket==""&&unitid!=""&&cek=="") {
//                            if (confirm('Do you want to save this record?')) {
//                                event.originalEvent.options.submit = true;
//                                $.publish('showDialog');
//
//                            } else {
//                                // Cancel Submit comes with 1.8.0
//                                event.originalEvent.options.submit = false;
//                            }
//                        } else {
//                            event.originalEvent.options.submit = false;
//                            var msg = "";
//                            if ( unitid == '') {
//                                msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
//                            }
//                            if ( nipid == '') {
//                                msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
//                            }
//                            if ( cutiid == '') {
//                                msg += 'Field <strong>Cuti</strong> is required.' + '<br/>';
//                            }
//                            if ( tanggalAwal == '') {
//                                msg += 'Field <strong>Tanggal awal</strong> is required.' + '<br/>';
//                            }
//                            if ( tanggalAkhir == '') {
//                                msg += 'Field <strong>Tanggal Akhir</strong> is required.' + '<br/>';
//                            }
//                            if (ket != "") {
//                                $('#tgl1').val("");
//                                $('#tgl2').val("");
//                                $('#lamaCuti').val("");
//                                msg += '<strong>'+ket+'</strong>' + '<br/>';
//                            }
//                            if ( cek != '') {
//                                msg += 'Ada cuti yang masih di ajukan<br/>';
//                            }
//                            document.getElementById('errorMessageAddCuti').innerHTML = msg;
//                            $.publish('showErrorValidationDialog');
//                        }
//                    }else{
//                        var msg2 ='Maaf, Tanggal Pengajuan Cuti Harus Kurang Dari Tanggal Awal Cuti';
//                        document.getElementById('errorMessageAddCuti').innerHTML = msg2;
//                        $.publish('showErrorValidationDialog');
//                        event.originalEvent.options.submit = false;
//                    }
//                });
            }

        });

        $.subscribe('beforeProcessDeleteCuti', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });

        $.subscribe('successDialogCuti', function (event, data) {
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
            $('#view_dialog_menu_cuti_pegawai').dialog('close');
        }
        var unit = '<s:property value="CutiPegawai.unitId"/>'
    </script>
</head>
<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addFormCuti" method="post" theme="simple" namespace="/cutiPegawai" action="saveAdd_cutiPegawai" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Add Cuti Pegawai</legend>


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
                                <s:textfield  id="check" name="cutiPegawai.self" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="unitId12" name="cutiPegawai.unitId" required="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                <s:textfield  id="unitId33" name="cutiPegawai.unitId" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>

                        <td>
                            <table>
                                <s:textfield  id="nipId" name="cutiPegawai.nip" required="true" readonly="false" cssClass="form-control"/>
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
                                <s:textfield  id="namaAddId" name="cutiPegawai.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
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
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId12" name="cutiPegawai.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                                <s:textfield  id="divisiId33" name="cutiPegawai.divisiId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
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
                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid12" disabled="true" name="cutiPegawai.posisiId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                <s:textfield  id="positionid33" name="cutiPegawai.posisiId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Profesi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                                <s:select list="#comboProfesi.listComboProfesi" id="profesiid12" name="cutiPegawai.profesiId" disabled="true" readonly="true"
                                          listKey="profesiId" listValue="profesiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                <s:textfield  id="profesiid33" name="cutiPegawai.profesiId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'diluar_tanggungan':'Diluar Tanggungan'}" id="jenisCuti1" name="cutiPegawai.jenisCuti"
                                          headerKey="normal" headerValue="Normal" cssClass="form-control" />
                            </table>
                            <script>
                                $(document).ready(function() {
                                    $('#jenisCuti1').change(function() {
                                        var jenisCuti= $('#jenisCuti1').val();

                                        if (jenisCuti == "diluar_tanggungan") {
                                            $('#cuti2').show();
                                            $('#cuti1').hide();

                                            function jeniscuti(){
                                                namacuti= $('#cutiId').val();
                                                branchid =$('#unitId12').val();
                                                nip=$('#nipId').val();
                                                golonganid=document.getElementById("golonganId12").value;
                                                dwr.engine.setAsync(false);
                                                CutiAction.initComboCutiTipe(namacuti, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    $('#jenisCutiTanggungan').val(item.tipeHari).change();
                                                    $('#cutiMax').val(item.jumlahCuti);
                                                });
                                            }

                                            if ($('#check').val()=="Y"){
                                                $('#unitId12').attr({ readonly:"true", disabled:"true" });
                                                $('#nipId').attr('readonly','true');
                                                var jenisCuti1 = $('#jenisCuti1').val();
                                                var nip=$('#nipId').val();
                                                var data2=[];
                                                console.log("Test "+jenisCuti1);
                                                dwr.engine.setAsync(false);
                                                CutiPegawaiAction.initComboSetCuti(nip, jenisCuti1,function (listdata) {
                                                    data2 = listdata;
                                                });
                                                $.each(data2, function (i, item) {
                                                    console.log(item.cutiId);
                                                    console.log(item.sisaCutiHari)
                                                    $('#cutiIdTanggungan').val(item.cutiId).change();
                                                    $('#cutiId15Tanggungan').val(item.cutiId);
                                                    $('#sisaCuti').val('1095');
                                                });
                                                jeniscuti();
                                            }else{
                                                $('#unitId33').attr('disabled','true');
                                            }

                                            var nip = $('#nipId').val();//document.getElementById("nipId").value;
                                            var cutiId= "CT007";
                                            var branchid = $('#unitId33').val();//document.getElementById("unitId33").value;
                                            if (nip!=null){
                                                dwr.engine.setAsync(false);
                                                CutiPegawaiAction.initComboSisaCutiPegawaiId(nip,cutiId,branchid, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = '1095';
                                                    $('#sisaCuti').val(labelItem).change();
                                                });
                                            }

                                        } else {
                                            $('#cuti1').show();
                                            $('#cuti2').hide();

                                            function jeniscuti(){
                                                namacuti= $('#cutiId').val();
                                                branchid =$('#unitId12').val();
                                                nip=$('#nipId').val();
                                                golonganid=document.getElementById("golonganId12").value;
                                                dwr.engine.setAsync(false);
                                                CutiAction.initComboCutiTipe(namacuti, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    $('#jenisCuti').val(item.tipeHari).change();
                                                    $('#cutiMax').val(item.jumlahCuti);
                                                });
                                            }

                                            if ($('#check').val()=="Y"){
                                                $('#unitId12').attr({ readonly:"true", disabled:"true" });
                                                $('#nipId').attr('readonly','true');
                                                var jenisCuti1 = $('#jenisCuti1').val();
                                                var nip=$('#nipId').val();
                                                var data2=[];
                                                console.log("Test "+jenisCuti1);
                                                dwr.engine.setAsync(false);
                                                CutiPegawaiAction.initComboSetCuti(nip, jenisCuti1,function (listdata) {
                                                    data2 = listdata;
                                                });
                                                $.each(data2, function (i, item) {
                                                    $('#cutiId').val(item.cutiId).change();
                                                    $('#cutiId15').val(item.cutiId);
                                                    $('#sisaCuti').val(item.sisaCutiHari);

                                                });
                                                jeniscuti();
                                            }else{
                                                $('#unitId33').attr('disabled','true');
                                            }

                                            var nip = document.getElementById("nipId").value;
                                            var cutiId= document.getElementById("cutiId").value;
                                            var branchid = document.getElementById("unitId33").value;
                                            if (nip!=null){
                                                dwr.engine.setAsync(false);
                                                CutiPegawaiAction.initComboSisaCutiPegawaiId(nip,cutiId,branchid, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.sisaCutiHari;
                                                    $('#sisaCuti').val(labelItem).change();
                                                });
                                            }
                                        }
                                    })
                                });
                            </script>
                        </td>
                    </tr>
                    <tr id="cuti1">
                        <td>
                            <label class="control-label"><small>Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>
                                <s:select list="#comboCuti.listComboCuti" id="cutiId" name="cutiPegawai.cutiId"
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
                                <s:select list="#comboCuti.listComboCuti2" id="cutiIdTanggungan" name="cutiPegawai.cutiTanggunganId"
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
                                <td width="90px">
                                    <s:textfield  id="sisaCuti" name="cutiPegawai.sisaCuti" required="false" readonly="true" cssClass="form-control"/>
                                </td>
                                <td>&nbsp;&nbsp;&nbsp;</td>
                                <td>
                                    hari
                                </td>
                            </table>
                        </td>
                    </tr>
                    <tr style="display: none;">
                        <td>
                            <label class="control-label"><small>Cuti Max :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="cutiMax" name="" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tgl. Awal Cuti:</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl2" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                 required="false"  cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tgl. Selesai Cuti:</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl1" name="cutiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
                                                 required="false"  cssStyle=""/>
                                </div>
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
                    <S:hidden name="cutiPegawai.pegawaiPenggantiSementara" />
                    <%--<tr>
                        <td>
                            <label class="control-label"><small>Pegawai Pengganti Sementara :</small></label>
                        </td>
                        <td>
                            <s:textfield  id="pegawaiPenggantiSementara" name="cutiPegawai.pegawaiPenggantiSementara" cssClass="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Pegawai Pengganti Sementara :</small></label>
                        </td>
                        <td>
                            <s:textfield  id="pegawaiPenggantiSementara12" name="" readonly="true" required="false" cssClass="form-control"/>
                        </td>
                    </tr>--%>
                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addFormCuti" id="saveCuti" name="saveCuti"
                                   onBeforeTopics="beforeProcessSaveCuti" onCompleteTopics="closeDialog,successDialogCuti"
                                   onSuccessTopics="successDialogCuti" onErrorTopics="errorDialog" >
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
                                                    <center><div id="errorMessageAddCuti"></div></center>
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
        function jeniscuti(){
            namacuti= $('#cutiId').val();
            branchid =$('#unitId12').val();
            nip=$('#nipId').val();
            golonganid=document.getElementById("golonganId12").value;
            dwr.engine.setAsync(false);
            CutiAction.initComboCutiTipe(namacuti, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                $('#jenisCuti').val(item.tipeHari).change();
                $('#cutiMax').val(item.jumlahCuti);
            });
        }

//        function jeniscuti(){
//            namacuti= $('#cutiId').val();
//            branchid =$('#unitId12').val();
//            jenisCuti1 = $('#jenisCuti1').val();
//            nip=$('#nipId').val();
//            golonganid=document.getElementById("golonganId12").value;
//            if (jenisCuti1 == 'normal'){
//                dwr.engine.setAsync(false);
//                console.log("Test");
//                CutiAction.initComboCutiTipe(namacuti, function (listdata) {
//                    data = listdata;
//                });
//                $.each(data, function (i, item) {
//                    $('#jenisCuti').val(item.tipeHari).change();
//                    $('#cutiMax').val(item.jumlahCuti);
//                });
//            }else {
//                dwr.engine.setAsync(false);
//                console.log("Tes");
//                namacuti = 'CT007';
//                dwr.engine.setAsync(false);
//                CutiAction.initComboCutiTipe(namacuti, function (listdata) {
//                    data = listdata;
//                });
//                $.each(data, function (i, item) {
//                    $('#jenisCuti').val(item.tipeHari).change();
//                    $('#cutiMax').val(item.jumlahCuti);
//                    console.log(item.tipeHari);
//                    console.log(item.jumlahCuti);
//                });
//            }
//        }

//        if ($('#check').val()=="Y"){
//            $('#unitId12').attr({ readonly:"true", disabled:"true" });
//            $('#nipId').attr('readonly','true');
//            var nip=$('#nipId').val();
//            var data2=[];
//            dwr.engine.setAsync(false);
//            CutiPegawaiAction.initComboSetCuti(nip, function (listdata) {
//                data2 = listdata;
//            });
//            $.each(data2, function (i, item) {
//                $('#cutiId').val(item.cutiId).change();
//                $('#cutiId15').val(item.cutiId);
//                $('#sisaCuti').val(item.sisaCutiHari);
//
//            });
//            jeniscuti();
//        }else{
//            $('#unitId33').attr('disabled','true');
//        }

        if ($('#check').val()=="Y"){
            $('#unitId12').attr({ readonly:"true", disabled:"true" });
            $('#nipId').attr('readonly','true');
            var jenisCuti1 = $('#jenisCuti1').val();
            var nip=$('#nipId').val();
            var data2=[];
            console.log("Test "+jenisCuti1);
            dwr.engine.setAsync(false);
            CutiPegawaiAction.initComboSetCuti(nip, jenisCuti1,function (listdata) {
                data2 = listdata;
            });
            $.each(data2, function (i, item) {
                $('#cutiId').val(item.cutiId).change();
                $('#cutiId15').val(item.cutiId);
                $('#sisaCuti').val(item.sisaCutiHari);

            });
            jeniscuti();
        }else{
            $('#unitId33').attr('disabled','true');
        }

        var nip = document.getElementById("nipId").value;
        var cutiId= document.getElementById("cutiId").value;
        var branchid = document.getElementById("unitId33").value;
        if (nip!=null){
            dwr.engine.setAsync(false);
            CutiPegawaiAction.initComboSisaCutiPegawaiId(nip,cutiId,branchid, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                var labelItem = item.sisaCutiHari;
                $('#sisaCuti').val(labelItem).change();
            });
        }
        // $('#cutiId').change(function() {
        //     var nip=document.getElementById("nipId").value;
        //     var cutiid = document.getElementById("cutiId").value;
        //     var branchid = document.getElementById("unitId12").value;
        //     if (nip==""){
        //         alert("Tolong isi NIP dahulu");
        //         $('#cutiId').val("");
        //     } else if (nip!=""&&cutiid!=""){
        //         dwr.engine.setAsync(false);
        //         CutiPegawaiAction.initComboSisaCutiPegawaiId(nip,cutiid,branchid, function (listdata) {
        //             data = listdata;
        //         });
        //         $.each(data, function (i, item) {
        //             var labelItem = item.sisaCutiHari;
        //             $('#sisaCuti').val(labelItem).change();
        //         });
        //     }
        // });
        function callSearch2() {
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
    });
    $('#tgl1').datepicker({
        dateFormat: 'dd/mm/yy'
    });
    $('#tgl2').datepicker({
        dateFormat: 'dd/mm/yy'
    });

    $('#tgl1').on('change',function(){
        var nip = document.getElementById("nipId").value;
        var tglawal = document.getElementById("tgl2").value;
        var tglakhir = document.getElementById("tgl1").value;
        var posisi = document.getElementById("positionid33").value;
        var enddate =$('#tgl1').datepicker('getDate');
        var startdate =$('#tgl2').datepicker('getDate');
        var max =parseInt(document.getElementById("cutiMax").value);
        var days;
        var jmllibur;
        if (startdate==null){
            alert("Isikan Tanggal Awal Cuti");
            $('#tgl1').val("");
        }
        else{
            dwr.engine.setAsync(false);
            IjinKeluarAction.calculateLibur(tglawal,tglakhir, function (listdata) {
                jmllibur = listdata;
            });
            if (startdate<=enddate) {
                var kalender="kerja";
                if (posisi=='130'||posisi=='161'){
                    jmllibur=0;
                    kalender="kalender";
                }
                if (kalender=="kalender"){
                    days = (enddate - startdate)/1000/60/60/24;
                    days = days+1;
                    days = days-jmllibur;
                }
                else if (kalender=="kerja"){
                    days = calcBusinessDays(startdate,enddate);
                    days = days-jmllibur;
                }
                else {
                    alert("Belum memilih Jenis Cuti");
                    $('#tgl1').val("");
                }
                $('#lamaCuti').val(days);
            }
        }
    });
</script>
<script type='text/javascript'>
    $(document).ready(function() {
        var functions, mapped;
        $('#nipId').typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};

                var data = [];
                var unit = $('#unitId12').val();
                if (unit==""){
                    alert("Unit is empty");
                    $('#nipId').val("");
                    $('#namaAddId').val("");

                } else {
                    dwr.engine.setAsync(false);
                    CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
                        data = listdata;
                    });
                    $.each(data, function (i, item) {
                        var labelItem = item.nip+" "+item.namaPegawai;
                        mapped[labelItem] = { id: item.nip,nama:item.namaPegawai, label: labelItem,divisi: item.divisi,branch:item.branch,positionId:item.positionId,golonganId:item.golonganId , tanggalaktif:item.stTanggalAktif, profesiId:item.profesiId};
                        functions.push(labelItem);
                    });
                    process(functions);
                }

            },
            updater: function (item) {
                var selectedObj = mapped[item];
                var namaMember = selectedObj.label;
                var data2=[];
                document.getElementById("namaAddId").value=selectedObj.nama;
                $('#positionid12').val(selectedObj.positionId).change();
                $('#positionid33').val(selectedObj.positionId).change();
                $('#divisiId12').val(selectedObj.divisi).change();
                $('#divisiId33').val(selectedObj.divisi).change();
                $('#profesiid12').val(selectedObj.profesiId).change();
                $('#profesiid33').val(selectedObj.profesiId).change();
                document.getElementById("golonganId12").value=selectedObj.golonganId;
                dwr.engine.setAsync(false);
                CutiPegawaiAction.initComboSetCuti(selectedObj.id, function (listdata) {
                    data2 = listdata;
                });
                $.each(data2, function (i, item) {
                    $('#cutiId').val(item.cutiId).change();
                    $('#cutiId15').val(item.cutiId);
                    $('#sisaCuti').val(item.sisaCutiHari);
                });
                return selectedObj.id;
            }
        });
        var namacuti,branchid,golonganid,nip;
        $('#cutiId').change(function() {
            namacuti= $('#cutiId').val();
            branchid =$('#unitId12').val();
            nip=$('#nipId').val();
            golonganid=document.getElementById("golonganId12").value;

            dwr.engine.setAsync(false);
            CutiAction.initComboCutiTipe(namacuti, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                $('#jenisCuti').val(item.tipeHari).change();
                $('#cutiMax').val(item.jumlahCuti);
            });
        });
        $('#pegawaiPenggantiSementara').typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};
                var data = [];
                var unit = $('#unitId12').val();
                if (unit=="") {
                    alert("Unit is empty");
                    $('#pegawaiPenggantiSementara12').val("");
                    $('#pegawaiPenggantiSementara').val("");
                }else{
                    dwr.engine.setAsync(false);
                    CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
                        data = listdata;
                    });
                    $.each(data, function (i, item) {
                        var labelItem = item.nip+" "+item.namaPegawai;
                        mapped[labelItem] = { id: item.nip,nama:item.namaPegawai, label: labelItem,divisi: item.divisi,branch:item.branch,positionId:item.positionId,golonganId:item.golonganId , tanggalaktif:item.stTanggalAktif};
                        functions.push(labelItem);
                    });
                    process(functions);
                }
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                $('#pegawaiPenggantiSementara12').val(selectedObj.nama);
                return selectedObj.id;
            }
        });
    });
</script>

