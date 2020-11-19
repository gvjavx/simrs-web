
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SmkAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SmkJabatanAction.js"/>'></script>

    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            var nip = document.getElementById("personName2").value;

            BiodataAction.detailBiodata(nip, function(listdata) {
                $('#golonganId').val(listdata.golonganId);
                $('#point').val(listdata.point);
                $('#tipePegawai1').val(listdata.tipePegawai);
                $('#statusPegawai').val(listdata.statusPegawai);
            });
            //cekJabatan();

            window.setTipeSmk = function(tipe){
                if(tipe == 'US'){
                    $('#dataAspekA').hide();
                    $('#dataAspekB').hide();
                    $('#dataAspekC').hide();
                    $('#dataAspekHistory').hide();

                    $('#btnAddSubTipe').hide();
                    $('#labelA').hide();
                    $('#labelB').hide();
                    $('#labelC').hide();
                    $('#labelHistory').hide();

                    $('#personName2').prop("disabled", true);
                    $('#usaha').show();
                    $('#nip1').hide();

                    $('#nama1').hide();
                    $('#unit1').show();
                    $('#bagian1').hide();
                    $('#jabatan1').hide();
                    $('#golongan1').hide();
                    $('#point1').hide();
                    $('#tipePegawai2').hide();
                    $('#statusPegawai1').hide();
                    $('#masaKerjaBln').hide();

                    listPosisi();
                }else{
                    $('#btnAddSubTipe').show();
                    $('#labelA').show();
                    $('#labelB').show();
                    $('#labelC').show();
                    $('#labelHistory').show();

                    $('#dataAspekA').show();
                    $('#dataAspekB').show();
                    $('#dataAspekC').show();
                    $('#dataAspekHistory').show();

                    $('#personName2').prop("disabled", false);
                    $('#usaha').hide();

                    $('#nip1').show();
                    $('#nama1').show();
                    $('#unit1').show();
                    $('#bagian1').show();
                    $('#jabatan1').show();
                    $('#golongan1').show();
                    $('#point1').show();
                    $('#tipePegawai2').show();
                    $('#masaKerjaBln').show();
                    //$('#statusPegawai1').show();
                }
            }

            window.clos = function() {
                //$('personName2').val();
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_smk.action'/>";
            };
            window.cekUserSmk = function(nip, periode){
                var hasil = false ;
                SmkAction.cekUserSmk(nip, periode, function(listdata) {
                    hasil = listdata ;
                });
                return hasil;
            }

            $.subscribe('beforeProcessSave', function (event, data) {
                var nip             = document.getElementById("personName2").value;
                var pegawaiName     = document.getElementById("personName").value;
                var branch          = document.getElementById("branchId").value;
                var divisi          = document.getElementById("divisiId").value;
                var jabatan         = document.getElementById("positionId").value;
                var golongan        = document.getElementById("golonganId").value;
                var point           = document.getElementById("point").value;
                var periode         = document.getElementById("periodeId").value;
                var tipeSmk         = document.getElementById("tipeSmk").value;
                var unitUsaha       = document.getElementById("unitUsaha").value;

                if(tipeSmk == "US"){
                    if (unitUsaha != '' && periode != '') {
                        if (confirm('Do you want to save this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        } else {
                            event.originalEvent.options.submit = false;
                        }
                    } else {
                        event.originalEvent.options.submit = false;
                        var msg = "";

                        if (nip == '') {
                            msg += 'Field <strong>Nip</strong> is required.' + '<br/>';
                        }

                        if (unitUsaha == '') {
                            msg += 'Nilai <strong>Unit Usaha</strong> is required.' + '<br/>';
                        }

                        document.getElementById('errorValidationMessage').innerHTML = msg;

                        $.publish('showErrorValidationDialog');

                    }
                }else{
                    var noAspekA = 0;
                    var noAspekB = 0;
                    var totalAspekA = document.getElementById("tmpAspekA").value;
                    var totalAspekB = document.getElementById("tmpAspekB").value;
                    var totalAspekC = document.getElementById("tmpAspekC").value;
                    var totalSeluruhAspek = Number(totalAspekA) +Number(totalAspekB) + Number(totalAspekC);

                    noAspekA = noAspekA + Number(totalAspekA);
                    noAspekB = noAspekB + Number(totalAspekB);

                    if(noAspekA > 0 && noAspekB > 0){
                        if(totalSeluruhAspek <= 100){
                            if (nip != '' && periode != '') {
                                if(cekUserSmk(nip, periode) == true){
                                    if (confirm('Do you want to save this record?')) {
                                        event.originalEvent.options.submit = true;
                                        $.publish('showDialog');
                                    } else {
                                        event.originalEvent.options.submit = false;
                                    }
                                }else{
                                    alert(pegawaiName + ' data sudah ada pada periode ' + periode);
                                    event.originalEvent.options.submit = false;
                                }
                            } else {

                                event.originalEvent.options.submit = false;

                                var msg = "";

                                if (nip == '') {
                                    msg += 'Field <strong>Nip</strong> is required.' + '<br/>';
                                }

                                if (periode == '') {
                                    msg += 'Field <strong>Periode</strong> is required.' + '<br/>';
                                }



                                document.getElementById('errorValidationMessage').innerHTML = msg;

                                $.publish('showErrorValidationDialog');

                            }
                        }else{
                            event.originalEvent.options.submit = false;

                            var msg = "";

                            msg += 'Total Aspek A, B dan C tidak boleh lebih dari 100' + '<br/><br/>';
                            msg += 'Total Aspek A: '+ totalAspekA + '<br/>';
                            msg += 'Total Aspek B: '+ totalAspekB + '<br/>';
                            msg += 'Total Aspek C: '+ totalAspekC + '<br/>';

                            document.getElementById('errorValidationMessage').innerHTML = msg;

                            $.publish('showErrorValidationDialog');
                        }
                    }else{
                        event.originalEvent.options.submit = false;

                        var msg = "";

                        msg = 'Aspek A dan Aspek B harus diisi terlebih dahulu. (untuk aspek B diisi melalui master)<br/><br/>';

                        document.getElementById('errorValidationMessage').innerHTML = msg;

                        $.publish('showErrorValidationDialog');
                    }

                }

            });
        });


    </script>

    <div id="modal-smkJabatan" class="modal fade" role="dialog">
        <div class="modal-dialog modal-md">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add Data</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="myForm">

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Nama Indikator : </label>
                            <div class="col-sm-9">
                                <input type="text" style="display: none" class="form-control nip" id="subTipeNamaOld">
                                <input type="text" class="form-control nip" id="subTipeNama">
                                <input type="text" style="display: none" class="form-control nip" id="kategoriId">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Bobot : </label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control nip" id="subTipeBobot">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Satuan : </label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control nip" id="subTipeSatuan">
                            </div>
                        </div>

                        <div class="form-group" style="display: none" >
                            <label class="control-label col-sm-3" >Nilai : </label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control nilai" id="subTipeNilai">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveAspekA" type="button" class="btn btn-default btn-success">Add</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Add Evaluasi Pegawai (SMK)
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/smk" action="saveAdd_smk.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>

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
                                    <label class="control-label"><small>Tipe SMK :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'US' : 'Unit Usaha', 'P':'Personal'}"
                                                  id="tipeSmk" name="smk.tipeSmk" onchange="setTipeSmk(this.value)"
                                                  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Periode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}" id="periodeId" name="smk.periode"
                                                  headerKey="" headerValue="[Pilih Tahun]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr id="usaha" style="display:none;">
                                <td>
                                    <label class="control-label"><small>Nilai Unit Usaha:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <input type="text" id="unitUsaha" name="smk.unitUsaha" class="form-control">
                                    </table>
                                </td>
                            </tr>

                            <tr id="nip1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Nip :</small></label>
                                </td>
                                <td>
                                    <table>
                                            <input type="text" id="personName2" name="smk.nip" class="form-control">
                                        <s:textfield cssStyle="display:none;" id="role" required="false" readonly="true" cssClass="form-control" name="smk.role"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="nama1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="personName" name="smk.pegawaiName" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="unit1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <%--<s:select list="#session.branchForSmk" id="branchId" name="smk.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                        <select id="branchId" name="smk.branchId" class="form-control" onchange="listPosisi(), masaKerjaBulan()"></select>
                                    </table>

                                </td>
                            </tr>

                            <tr id="bagian1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Divisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smk.divisiId" readonly="true"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>


                            <tr id="jabatan1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <%--<s:select list="#comboPosition.listOfComboPosition" id="positionId" name="smk.positionId"
                                                  readonly="true"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                        <select id="positionId" name="smk.positionId" class="form-control" onchange="masaKerjaBulan()"></select>
                                    </table>

                                </td>
                            </tr>

                            <tr id="golongan1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                        <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="smk.golonganId" readOnly="true"
                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr id="point1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Point :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="point" name="smk.poin" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="masaKerjaBln" style="display: none">
                                <td>
                                    <label class="control-label"><small>Masa Kerja (Bln):</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="masaKerja" name="smk.masaKerjaBln" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="point1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="bagianId" name="smk.bagianId" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="tipePegawai2" style="display: none">
                                <td>
                                    <label class="control-label"><small>Tipe Pegawai :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                                        <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai1" name="smk.tipePegawaiId" readOnly="true"
                                                  listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr id="statusPegawai1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Status Pegawai :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'KNS':'Karyawan Non Staff', 'KS':'Karyawan Staff'}"
                                                  id="statusPegawai" name="smk.statusPegawai" readOnly="true"
                                                  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr style="display:none ">
                                <td >
                                    <label class="control-label"><small>Aspek A:</small></label>
                                </td>
                                <td >
                                    <table>
                                        <s:textfield  id="tmpAspekA" required="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display: none">
                                <td >
                                    <label class="control-label"><small>Aspek B:</small></label>
                                </td>
                                <td >
                                    <table>
                                        <s:textfield  id="tmpAspekB" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display:none ">
                                <td >
                                    <label class="control-label"><small>Aspek C:</small></label>
                                </td>
                                <td >
                                    <table>
                                        <s:textfield  id="tmpAspekC" required="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <script type='text/javascript'>
                                var functions, mapped;
                                var unit  = document.getElementById("branchId").value;
                                var posisi  = document.getElementById("positionId").value;
                                var role  = document.getElementById("role").value;

                                $('#personName2').typeahead({
                                    minLength: 1,
                                    source: function (query, process) {
                                        functions = [];
                                        mapped = {};
                                        var periode = document.getElementById("periodeId").value;

                                        var data = [];
                                        dwr.engine.setAsync(false);
                                        if(role == 'ADMIN'){
                                            BiodataAction.initComboPersonil(query, periode, function (listdata) {
                                                data = listdata;
                                                //alert('aa');
                                            });
                                        }else{
                                            SmkAction.comboUserSmk(query, unit, posisi, function (listdata) {
                                                data = listdata;
                                                //alert('aa');
                                            });
                                        }
                                        //alert(prov);
                                        $.each(data, function (i, item) {
                                            var labelItem =item.nip+ " || "+ item.namaPegawai;
                                            var labelNip = item.nip;
                                            mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem,
                                                branchId : item.branch, divisiId: item.divisi, positionId : item.positionId,
                                                golonganId : item.golonganId, point : item.point, tipePegawaiId : item.tipePegawai,
                                                statusPegawaiId: item.statusPegawai, bagianId:item.bagianId};
                                            functions.push(labelItem);
                                        });


                                        process(functions);
                                    },

                                    updater: function (item) {
                                        var periode = document.getElementById("periodeId").value;
                                        var selectedObj = mapped[item];
                                        var namaAlat = selectedObj.id;
                                        document.getElementById("personName2").value = selectedObj.id;
                                        document.getElementById("personName").value = selectedObj.pegawai;
                                        listBranch(selectedObj.id, periode);
                                        var branchId = document.getElementById("branchId").value;
                                        $('#branchId').val(branchId).change();
                                        listPosisi();
                                        masaKerjaBulan();
                                        $('#divisiId').val(selectedObj.divisiId).change();
                                        $('#golonganId').val(selectedObj.golonganId).change();
                                        $('#point').val(selectedObj.point).change();
                                        $('#bagianId').val(selectedObj.bagianId);
                                        //$('#positionId').val(selectedObj.positionId).change();
                                        $('#tipePegawai1').val(selectedObj.tipePegawaiId).change();
                                        $('#statusPegawai').val(selectedObj.statusPegawaiId).change();
                                        branc = selectedObj.branchId;
                                        dev = selectedObj.divisiId ;
                                        cekJabatan();
                                        SmkJabatanAction.getNilaiAspek(selectedObj.branchId, selectedObj.positionId, function(listdata) {
                                            $('#tmpAspekB').val(listdata.totalAspekB);
                                            $('#tmpAspekC').val(listdata.totalAspekC);
                                        });
                                        return namaAlat;
                                    }
                                });
                            </script>
                        </table>
                        <center>
                            <br>
                            <br>
                            <button style="display: none" id="btnAddSubTipe" type="button" class="btn btn-default btn-info"
                                    data-toggle="tooltip" data-placement="right" title="Hanya dapat digunakan untuk aspek A"><i class="fa fa-plus"></i>
                            </button>

                                <h4 id="labelA" style="text-align: left; padding-left: 122px; display: none">A. Aspek Tugas dan Sasaran Kerja</h4>
                                <table style="width: 80%;" id="dataAspekA" class="dataAspekA table table-bordered">
                                </table>

                            <br>
                            <br>

                                <h4 id="labelB" style="text-align: left; padding-left: 122px; display: none">B. Aspek Perilaku kerja</h4>
                                <table style="width: 80%;" id="dataAspekB" class="dataAspekB table table-bordered">
                                </table>

                            <br>
                            <br>

                                <h4 id="labelC" style="text-align: left; padding-left: 122px; display: none">C. Aspek Kepemimpinan dan Manajerial</h4>
                                <table style="width: 80%;" id="dataAspekC" class="dataAspekC table table-bordered">
                                </table>

                            <br>
                            <br>
                        </center>

                        <div style="text-align: left; padding-left: 122px">
                            <h4 id="labelHistory" style="text-align: left; display: none">History SMK</h4>
                            <table style="width: 20%;" id="dataAspekHistory" class="dataAspekHistory table table-bordered">
                            </table>
                        </div>

                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="sppdForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_smk.action"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_smk.action"/>'">
                                            <i class="fa fa-close"></i> kembali
                                        </button>
                                    </td>
                                </tr>
                            </table>
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
                                                                      clos();
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

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>

</html>

<script>

    window.loadAspekA = function(){
        $('.dataAspekA').find('tbody').remove();
        $('.dataAspekA').find('thead').remove();
        $('.dataAspekA').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_tableA = "";
        SmkJabatanAction.loadAspekA(function(listdata) {
            tmp_tableA = "<thead style='color: white' ><tr class='active'>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Uraian</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Bobot (B)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Satuan</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Target</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Realisasi</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai (N)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai Prestasi(BxN)</th>"+
                    "</tr></thead>";
            var i = i ;
            var jumlahBobot = 0 ;
            var jumlahNilaiPrestasi = 0 ;
            $.each(listdata, function (i, item) {
                var hapus = '<td></td>';
                var edit = '<td></td>';

                if(item.jabatanSmkDetailId != '-'){
                    hapus = '<td align="center">' +
                            "<a href='javascript:;' class ='item-delete' data ='"+item.indikatorKinerja+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>';
                }

                tmp_tableA += '<tr >' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+item.indikatorKinerja+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        hapus +
                        '<td >' + item.indikatorKinerja+ '</td>' +
                        '<td width="40px" align="center">' + item.bobot  + '</td>' +
                        '<td width="40px" align="center">' + item.satuan + '</td>' +
                        '<td align="center"> - </td>' +
                        '<td align="center"> - </td>' +
                        '<td align="center">' + item.nilai.toFixed(2) + '</td>' +
                        '<td align="center">' + item.nilaiPrestasi.toFixed(2) + '</td>' +
                        "</tr>";
                jumlahBobot += item.bobot;
                jumlahNilaiPrestasi += item.nilaiPrestasi;
            });
            tmp_tableA += "<tfoot><tr>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td align='right'>Jumlah Bobot</td>" +
                    "<td align='center'>"+jumlahBobot+"</td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td align='right' colspan='2'>Jumlah Prestasi</td>" +
                    "<td align='center' colspan='2'>"+ jumlahNilaiPrestasi +"</td>" +
                    "</tr></tfoot>";
            $('.dataAspekA').append(tmp_tableA);
            $('#tmpAspekA').val(jumlahBobot.toFixed(2));
        });
    }

    window.cekJabatan = function(){
        var nip         = document.getElementById("personName2").value;
        var periode     = document.getElementById("periodeId").value;
        var branchId    = document.getElementById("branchId").value;
        var positionId  = document.getElementById("positionId").value;

        $('.dataAspekA').find('tbody').remove();
        $('.dataAspekA').find('thead').remove();
        $('.dataAspekA').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_tableA = "";
        SmkAction.showDataAspekA(nip, branchId, positionId, periode, function(listdata) {
            tmp_tableA = "<thead style='color: white' ><tr class='active'>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Uraian</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Bobot (B)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Satuan</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Target</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Realisasi</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai (N)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai Prestasi(BxN)</th>"+
                    "</tr></thead>";
            var i = i ;
            var jumlahBobot = 0 ;
            var jumlahNilai = 0 ;
            $.each(listdata, function (i, item) {
                var hapus = '<td></td>';
                var edit = '<td></td>';

                if(item.jabatanSmkDetailId != '-'){
                    hapus = '<td align="center">' +
                            "<a href='javascript:;' class ='item-delete' data ='"+item.indikatorKinerja+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>';
                }

                tmp_tableA += '<tr >' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+item.indikatorKinerja+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        hapus +
                        '<td >' + item.indikatorKinerja+ '</td>' +
                        '<td width="40px" align="center">' + item.bobot  + '</td>' +
                        '<td width="40px" align="center">' + item.satuan + '</td>' +
                        '<td align="center"> - </td>' +
                        '<td align="center"> - </td>' +
                        '<td align="center">' + item.nilai.toFixed(2) + '</td>' +
                        '<td align="center">' + item.nilaiPrestasi.toFixed(2) + '</td>' +
                        "</tr>";
                jumlahBobot += item.bobot;
                jumlahNilai += item.nilaiPrestasi;
             });
            tmp_tableA += "<tfoot><tr>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td align='right'>Jumlah Bobot</td>" +
                    "<td align='center'>"+jumlahBobot+"</td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td align='right' colspan='2'>Jumlah Prestasi</td>" +
                    "<td align='center' colspan='2'>"+jumlahNilai.toFixed(2)+"</td>" +
                    "</tr></tfoot>";
            $('.dataAspekA').append(tmp_tableA);
            $('#tmpAspekA').val(jumlahBobot);
        });

        $('.dataAspekB').find('tbody').remove();
        $('.dataAspekB').find('thead').remove();
        $('.dataAspekB').find('tfoot').remove();
        var dataAspekB = "";
        SmkAction.showDataAspekB(branchId, positionId, function(listdata) {
            dataAspekB = "<thead style='color: white' ><tr class='active'>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Uraian</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Bobot (B)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Target</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Realisasi</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai (N)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai Prestasi(BxN)</th>"+
                    "</tr></thead>";
            var i = i ;
            var jumlahBobot = 0 ;
            $.each(listdata, function (i, item) {
                dataAspekB += '<tr >' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.indikatorKinerja+ '</td>' +
                        '<td width="40px" align="center">' + item.bobot  + '</td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td align="center">' + 0 + '</td>' +
                        '<td align="center">' + 0 + '</td>' +
                        "</tr>";
                jumlahBobot += item.bobot;
             });
            dataAspekB += "<tfoot><tr>" +
                    "<td></td>" +
                    "<td align='right'>Jumlah Bobot</td>" +
                    "<td align='center'>"+jumlahBobot+"</td>" +
                    "<td></td>" +
                    "<td align='right' colspan='2'>Jumlah Prestasi</td>" +
                    "<td align='center' colspan='2'> 0 </td>" +
                    "</tr></tfoot>";
            $('.dataAspekB').append(dataAspekB);
        });

        $('.dataAspekC').find('tbody').remove();
        $('.dataAspekC').find('thead').remove();
        $('.dataAspekC').find('tfoot').remove();
        var dataAspekC = "";
        SmkAction.showDataAspekC(branchId, positionId, function(listdata) {
            dataAspekC = "<thead style='color: white' ><tr class='active'>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Uraian</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Bobot (B)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Target</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Realisasi</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai (N)</th>"+
                    "<th width='' style='text-align: center; background-color:  #3c8dbc''>Nilai Prestasi(BxN)</th>"+
                    "</tr></thead>";
            var i = i ;
            var jumlahBobot = 0 ;
            $.each(listdata, function (i, item) {
                dataAspekC += '<tr >' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.indikatorKinerja+ '</td>' +
                        '<td width="40px" align="center">' + item.bobot  + '</td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td align="center">' + 0 + '</td>' +
                        '<td align="center">' + 0 + '</td>' +
                        "</tr>";
                jumlahBobot += item.bobot;
             });
            dataAspekC += "<tfoot><tr>" +
                    "<td></td>" +
                    "<td align='right'>Jumlah Bobot</td>" +
                    "<td align='center'>"+jumlahBobot+"</td>" +
                    "<td></td>" +
                    "<td align='right' colspan='2'>Jumlah Prestasi</td>" +
                    "<td align='center' colspan='2'>0</td>" +
                    "</tr></tfoot>";
            $('.dataAspekC').append(dataAspekC);
        });

        $('.dataAspekHistory').find('tbody').remove();
        $('.dataAspekHistory').find('thead').remove();
        var dataAspekHistory = "";
        SmkAction.showDataAspekHistory(nip, periode, function(listdata) {
            dataAspekHistory = "<thead style='color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Prestasi</th>"+
                    "</tr></thead>";
            var i = i ;
            var jumlahBobot = 0 ;
            $.each(listdata, function (i, item) {
                dataAspekHistory += '<tr >' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.periode+ '</td>' +
                        '<td >' + item.nilaiPrestasi+ '</td>' +
                        "</tr>";
                jumlahBobot += item.bobot;
             });
            $('.dataAspekHistory').append(dataAspekHistory);
        });

    }

    var branc = '' ;
    var dev = '';

    $('#btnAddSubTipe').click(function(){
        var nip = document.getElementById("personName2").value;
        $('#myForm')[0].reset();
        if(nip != '' && nip != null){
            $('#modal-smkJabatan').modal('show');
            $('#myForm').attr('action', 'addSubTipe');
            $('#modal-smkJabatan').find('.modal-title').text('Add Sub Tipe A');
            $('#subTipeNama').attr('readonly', false);
            $('#subTipeBobot').attr('readonly', false);
            $('#subTipeTarget').attr('readonly', false);
            $('#subTipeSatuan').attr('readonly', false);
            $('#tipeAspekA').prop('disabled', 'true');
            $("#btnSaveAspekA").html('Add');
            $("#btnSave").show();
            $("#btnSaveBC").hide();
        }else{
            alert("Pilih pegawai terlebih dahulu");
        }
    });

    $('#btnSaveAspekA').click(function(){
        var url = $('#myForm').attr('action');
        var data = $('#myForm').serialize();

        var subTipeNama         = document.getElementById("subTipeNama").value;
        var subTipeNamaOld      = document.getElementById("subTipeNamaOld").value;
        var subTipeBobot        = document.getElementById("subTipeBobot").value;
        var subTipeSatuan       = document.getElementById("subTipeSatuan").value;
        var subTipeNilai        = document.getElementById("subTipeNilai").value;
        /*var subTipeAspek        = document.getElementById("tipeAspekA").value;
        var subTipeAspekNama    = document.getElementById("tipeAspekA");*/
        /*var subTipeAspekNama1   = subTipeAspekNama.options[subTipeAspekNama.selectedIndex].text;
        if(subTipeAspek == ''){
            subTipeAspekNama1 = '';
        }*/

        var isnum = /^-?\d*[.]?\d*$/.test(subTipeBobot);

        if(isnum == true){
            if(url == 'addSubTipe'){
                if(subTipeNama != '' && subTipeBobot != ''){
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        SmkJabatanAction.saveAdd(subTipeNama, subTipeBobot, "", "", subTipeSatuan, function(listdata) {
                            alert('Data Successfully Added');
                            $('#modal-smkJabatan').modal('hide');
                            $('#myForm')[0].reset();
                            loadAspekA();
                        });
                    }
                }else{
                    var msg = '';
                    if(subTipeNama == ''){
                        msg += 'Nama Indikator, ';
                    }
                    if(subTipeBobot== ''){
                        msg += 'Bobot, ';
                    }

                    msg += 'Harus Diisi!';
                    alert(msg);
                }
            }else if(url == 'editSubTipe'){
                if(subTipeNama != ''){
                    dwr.engine.setAsync(false);
                    SmkJabatanAction.saveEditSmkJabatan(subTipeNamaOld, subTipeNama, subTipeBobot, "", "", subTipeSatuan, subTipeNilai, function(listdata) {
                        $('#modal-smkJabatan').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Data Successfully Updated');
                        loadAspekA();
                    });
                }
            }else{
                if (confirm('Are you sure you want to delete this Record?')) {
                    SmkJabatanAction.saveSmkJabatanDetailDelete(subTipeNama, function (listdata) {
                        $('#modal-smkJabatan').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadAspekA();
                    });
                }
            }
        }else{
            alert("Bobot hanya bisa diisi angka / (koma menggunakan titik(.)");
        }

    });

    $('.dataAspekA').on('click', '.item-edit', function(){
        var id = $(this).attr('data');
        $('#subTipeNama').attr('readonly', false);
        $('#subTipeBobot').attr('readonly', false);
        $('#tipeAspekA').attr('disabled', false);
        $('#btnSaveBC').hide;
        $('#btnSave').show;

        SmkJabatanAction.searchSmkJabatanDetailEdit(id,function(listdata) {
            $.each(listdata, function (i, item) {

                if(item.subAspekA != ''){
                    $('#checkApprove').prop('checked',true);
                }else{
                    $('#checkApprove').prop('checked',false);
                }

                if(item.indikatorKinerja == 'Unit Usaha' || item.indikatorKinerja == 'Bidang' || item.indikatorKinerja == 'Bagian'){
                    document.getElementById("subTipeNama").readOnly = true;
                    $('#aspek').hide();
                }else{
                    document.getElementById("subTipeNama").readOnly = false;
                }
                $('#btnSaveAspekA').show();
                $('#tipeAspekA').val(item.subAspekA).change();
                $('#subTipeNama').val(item.indikatorKinerja);
                $('#subTipeNamaOld').val(item.indikatorKinerja);
                $('#subTipeBobot').val(item.bobot);
                $('#subTipeSatuan').val(item.satuan);
                $('#subTipeNilai').val(item.nilai);
            });
        });

        $("#btnSaveAspekA").html('Save');
        $('#modal-smkJabatan').find('.modal-title').text('Edit Sub Tipe A');
        $('#modal-smkJabatan').modal('show');
        $('#myForm').attr('action', 'editSubTipe');
    });

    $('.dataAspekA').on('click', '.item-delete', function(){
        var id = $(this).attr('data');

        SmkJabatanAction.searchSmkJabatanDetailEdit(id,function(listdata) {
            $.each(listdata, function (i, item) {

                if(item.subAspekA != ''){
                    $('#checkApprove').prop('checked',true);
                }else{
                    $('#checkApprove').prop('checked',false);
                }

                $('#tipeAspekA').val(item.subAspekA).change();
                $('#subTipeNama').val(item.indikatorKinerja);
                $('#kategoriId').val(item.kategoriId);
                $('#subTipeBobot').val(item.bobot);
                $('#btnSaveAspekA').show();
            });
        });

        $('#subTipeNama').attr('readonly', true);
        $('#subTipeBobot').attr('readonly', true);
        $('#tipeAspekA').attr('disabled', true);

        $("#btnSaveAspekA").html('Delete');
        $('#modal-smkJabatan').find('.modal-title').text('Delete Sub Tipe A');
        $('#modal-smkJabatan').modal('show');
        $('#myForm').attr('action', 'deleteSubTipeA');
    });

    window.listPosisi = function () {
        var nip     = document.getElementById("personName2").value;
        var periode = document.getElementById("periodeId").value;
        var branchId= document.getElementById("branchId").value;
        var tipeSmk= document.getElementById("tipeSmk").value;

        $('#positionId').empty();
        if(tipeSmk != 'US'){
            SmkAction.listPosisiDetail(nip, periode, branchId, function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#positionId').append($("<option></option>")
                            .attr("value", item.positionId)
                            .text(item.positionName));
                });
            });
        }else{
            BranchAction.getDataBranch(function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#branchId').append($("<option></option>")
                            .attr("value", item.branchId)
                            .text(item.branchName));
                });
            });
        }
    }

    window.listBranch = function (nip, periode) {
        $('#branchId').empty();
        /*$('#branchId').append($("<option></option>")
                .attr("value", '')
                .text(''));*/
        SmkAction.listBranch(nip, periode, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#branchId').append($("<option></option>")
                        .attr("value", item.branchId)
                        .text(item.branchName));
            });
        });
    }

    window.masaKerjaBulan = function () {
        var nip         = document.getElementById("personName2").value;
        var periode     = document.getElementById("periodeId").value;
        var branchId    = document.getElementById("branchId").value;
        var positionId  = document.getElementById("positionId").value;

        SmkAction.masaKerjaBulan(nip, periode, branchId, positionId, function (listdata) {
            $('#masaKerja').val(listdata);
        });
    }
</script>






