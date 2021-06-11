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
        #tgl8{z-index: 1900!important}
        .typeahead{z-index: 2000!important}
        .modal-backdrop {z-index: -1;}
        #modal-tambah-anggota{z-index: 3500!important}
    </style>
    <script type="text/javascript">
        var unit = '<s:property value="IjinKeluar.unitId"/>';
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };
        $.subscribe('beforeProcessSaveIjin', function (event, data) {
            var unit = document.getElementById("branchId").value;
            var nipid = document.getElementById("nipId").value;
            var tglawal = document.getElementById("tgl8").value;
            var jamAwal = document.getElementById("jamAwal").value;
            var jamAkhir = document.getElementById("jamAkhir").value;
            var keterangan = document.getElementById("keterangan12").value;
            var hasil = document.getElementById("lamaIjin").value;
            var keperluan = document.getElementById("keperluan").value;
            var a=false;
            if (keperluan=="P"){
                if (hasil<=4&&hasil>=0){
                    a=true;
                }else{
                    a=false;
                }
            }else if (keperluan=="K"){
                a=true;
            }

            if (unit != ''&& nipid != ''&& jamAwal != ''&& tglawal != ''&& jamAkhir != ''&& keterangan != ''&&a) {
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
                if (keperluan=="P"){
                    if (hasil > 4) {
                        msg += '<strong>Jam </strong> tidak boleh melebihi 4 jam' + '<br/>';
                    }if (hasil < 0) {
                        msg += '<strong>Jam </strong> salah' + '<br/>';
                    }
                }
                if (unit == '') {
                    msg += 'Field <strong>Unit Name</strong> is required.' + '<br/>';
                }
                if (nipid == '') {
                    msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                }
                if (jamAwal == '') {
                    msg += 'Field <strong>Jam Keluar </strong> is required.' + '<br/>';
                }
                if (tglawal == '') {
                    msg += 'Field <strong>Tanggal </strong> is required.' + '<br/>';
                }
                if (jamAkhir == '') {
                    msg += 'Field <strong>Jam Kembali </strong> is required.' + '<br/>';
                }
                if (keterangan == '') {
                    msg += 'Field <strong>Keterangan </strong> is required.' + '<br/>';
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
            $('#dialog_menu_ijin_keluar').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addFormIjin" method="post" theme="simple" namespace="/ijinKeluar" action="saveAddKantor_ijinKeluar" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Add Ijin Keluar Kantor</legend>
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
                                <s:textfield  id="check" name="ijinKeluar.self" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
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
                                if (unit1!=''){
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
                                $('#golonganId12').val(selectedObj.golongan).change();
                                $('#golonganId33').val(selectedObj.golongan).change();
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
                                <s:textfield  id="divisiId33" name="ijinKeluar.divisiId" cssStyle="display: none" required="false" readonly="true" cssClass="form-control"/>
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
                                <s:textfield  id="positionId33" name="ijinKeluar.positionId" cssStyle="display: none" required="false" readonly="true" cssClass="form-control"/>
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
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId12" name="ijinKeluar.golonganId" disabled="true"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                                <s:textfield  id="golonganId33" name="ijinKeluar.golonganId" cssStyle="display: none" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keperluan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'K':'Kantor'}" id="keperluan" name="ijinKeluar.keperluan"
                                          headerKey="P" headerValue="Pribadi" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" style="text-align: left !important;"><small>Tanggal :</small></label>
                        </td>
                        <td>
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tgl8" name="ijinKeluar.stTanggalAwal" cssClass="form-control pull-right"
                                             required="false"  cssStyle=""/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jam :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="jamAwal" name="ijinKeluar.jamKeluar" size="8" cssClass="form-control pull-right" onchange="onChange()"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <s:textfield id="jamAkhir" name="ijinKeluar.jamKembali" size="12" cssClass="form-control pull-right" onchange="onChange()"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Lama Keluar :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="lamaIjin" name="ijinKeluar.lamaIjin" required="false" readonly="true" cssClass="form-control"/>
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
                                        <s:textarea rows="4" id="keterangan12" name="ijinKeluar.keterangan" required="false" cssClass="form-control"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <div id="anggotaIjin">
                    <br>
                    <h4>
                        <button
                                id="btnAddAnggota" type="button" class="btn btn-default btn-info"><i class="fa fa-plus"></i>
                        </button>
                        Tambah Anggota
                    </h4>
                    <center>
                        <table id="showdata" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 80%;"
                                           class="AnggotaIjinTable table table-bordered">
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
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addFormIjin" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveIjin" onCompleteTopics="closeDialog,successDialogIjin"
                                   onSuccessTopics="successDialogIjin" onErrorTopics="errorDialogIjin" >
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
<div id="modal-tambah-anggota" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tambah anggota</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-3 unitanggota">Unit :</label>
                        <div class="col-sm-6">
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdAnggota"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">NIP :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nipAnggota" name="">
                            <script type='text/javascript'>
                                var functions, mapped;
                                $('#nipAnggota').typeahead({
                                    minLength: 1,
                                    source: function (query, process) {
                                        functions = [];
                                        mapped = {};
                                        var data = [];
                                        var unit = $('#branchIdAnggota').val();
                                        if(unit==""){
                                            alert("unit masih kosong");
                                            $('#nipAnggota').val("");
                                            $('#namaAnggota').val("");
                                            $('#posisiAnggotaId').val("");
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
                                        $('#namaAnggota').val(selectedObj.nama).change();
                                        $('#posisiAnggotaId').val(selectedObj.jabatan).change();
                                        return selectedObj.id;
                                    }
                                });
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Nama :</label>
                        <div class="col-sm-6">
                            <table>
                                <input type="text" class="form-control" id="namaAnggota" name="" disabled>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Posisi :</label>
                        <div class="col-sm-6">
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="posisiAnggotaId" name="" readonly="true" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveAnggota" type="button" class="">Tambah</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('#anggotaIjin').hide();
        $('#keperluan').change(function() {
            var keperluan = $('#keperluan').val();
            if (keperluan=="P"){
                $('#anggotaIjin').hide();
            }
            else if ((keperluan=="K")){
                $('#anggotaIjin').show();
            }
        });
        $('#view_dialog_menu').click(function(){
            var jamAwal = $('#jamAwal').val();
            var jamAkhir = $('#jamAkhir').val();
            var unit = $('#branchId').val();
            var nip = $('#nipId').val();
            var tanggal = $('#tgl8').val();
            if (jamAwal!==""&&jamAkhir!==""&&unit!==""&&nip!==""&&tanggal!==""){
                    IjinKeluarAction.calcHour(unit,nip,tanggal,jamAwal,jamAkhir,function(data){
                        $('#lamaIjin').val(data);
                        /*if (data!=="0"){
                        } else{
                            alert("Jam akhir tidak boleh melebihi jam awal");
                            $('#lamaIjin').val("");
                            $('#jamAwal').val("");
                            $('#jamAkhir').val("");
                        }*/
                    })
            }
        });
        $('#dialog_menu_ijin_keluar_kantor').click(function(){
            var jamAwal = $('#jamAwal').val();
            var jamAkhir = $('#jamAkhir').val();
            var unit = $('#branchId').val();
            var nip = $('#nipId').val();
            var tanggal = $('#tgl8').val();
            if (jamAwal!==""&&jamAkhir!==""&&unit!==""&&nip!==""&&tanggal!==""){
                IjinKeluarAction.calcHour(unit,nip,tanggal,jamAwal,jamAkhir,function(data){
                    if (data!=="0"){
                        $('#lamaIjin').val(data);
                    } else{
                        alert("Jam akhir tidak boleh melebihi jam awal");
                        $('#lamaIjin').val("");
                        $('#jamAwal').val("");
                        $('#jamAkhir').val("");
                    }
                })
            }
        });
        if ($('#check').val()=="Y"){
            $('#branchId').attr('readonly','true');
            $('#branchId').attr('disabled','true');
            $('#nipId').attr('readonly','true');
        }else{
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
        $('#tgl8').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#jamAwal').timepicker();
        $('#jamAkhir').timepicker();

        $('#btnAddAnggota').click(function () {
            $('#nipAnggota').val("");
            $('#namaAnggota').val("");
            $('#branchIdAnggota').val("");
            $('#nipAnggota').removeAttr("readonly");
            $('#branchIdAnggota').show();
            $('.unitanggota').show();
            $("#btnSaveAnggota").attr('class', 'btn btn-default btn-success');
            $("#btnSaveAnggota").text("tambah");
            $('#modal-tambah-anggota').modal('show');
            $('#myForm').attr('action', 'addAnggota');
            $('#modal-tambah-anggota').find('.modal-title').text('Tambah anggota');
        });
        $('.AnggotaIjinTable').on('click', '.item-delete-anggota', function () {
            var id = $(this).attr('data');
            dwr.engine.setAsync(false);
            IjinKeluarAction.searchAnggotaIjin(id, function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipAnggota').val(item.nip);
                    $('#namaAnggota').val(item.namaPegawai);
                    $('#branchIdAnggota').val(item.unitName).change();
                });
            });
            $('#nipAnggota').attr("readonly","true");
            $('#branchIdAnggota').hide();
            $('.unitanggota').hide();
            $("#btnSaveAnggota").attr('class', 'btn btn-default btn-danger');
            $("#btnSaveAnggota").text("hapus");
            $('#modal-tambah-anggota').find('.modal-title').text('Hapus Anggota');
            $('#modal-tambah-anggota').modal('show');
            $('#myForm').attr('action', 'delete');
        });
        $('#btnSaveAnggota').click(function () {
            var url = $('#myForm').attr('action');
            var nip = $('#nipAnggota').val();
            var nama = $('#namaAnggota').val();
            var posisi = $('#posisiAnggotaId').val();
            if (url == 'addAnggota') {
                if (nip != '') {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        IjinKeluarAction.saveAddAnggota(nip,nama,posisi, function () {
                            alert('Data Successfully Added');
                            $('#modal-tambah-anggota').modal('hide');
                            $('#myForm')[0].reset();
                            loadAnggota();
                        });
                    }
                } else {
                    alert('NIP harus diisi');
                }
            }else {
                if (confirm('Are you sure you want to delete this Record?')) {
                    IjinKeluarAction.saveDeleteAnggota(nip, function () {
                        $('#modal-tambah-anggota').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadAnggota();
                    });
                }
            }
        });
        window.loadAnggota = function () {
            $('.AnggotaIjinTable').find('tbody').remove();
            $('.AnggotaIjinTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table_anggota = "";
            IjinKeluarAction.searchAnggotaIjin("",function (listdata) {
                tmp_table_anggota = "<thead style='font-size: 14px' ><tr class='active'>" +
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>NIP</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Nama</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table_anggota += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.namaPegawai + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete-anggota' data ='" + item.nip + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_delete'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.AnggotaIjinTable').append(tmp_table_anggota);
            });
        }
    });

</script>
