
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
            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_smk.action'/>";
            };
        });


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Edit Evaluasi Pegawai (SMK)
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/smk" action="saveEdit_smk.action" cssClass="well form-horizontal">

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
                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="evaluasiPegawaiId" name="smk.evaluasiPegawaiId" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Periode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'2017':'2017', '2018':'2018', '2019':'2019'}" id="periodeId" name="smk.periode"
                                                  headerKey="" headerValue="[Pilih Tahun]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Nip :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssStyle="display: none" id="personName1" name="smk.nip" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield  id="personName2" required="false" readonly="false" cssClass="form-control"/>
                                            <%--<s:textfield cssStyle="display: none" id="personName" name="smk.personName" required="false" readonly="false" cssClass="form-control"/>--%>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#personName2').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                data = listdata;
                                                //alert('aa');
                                            });
                                            //alert(prov);
                                            $.each(data, function (i, item) {
                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                var labelNip = item.nip;
                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem,
                                                    branchId : item.branch, divisiId: item.divisi, positionId : item.positionId,
                                                    golonganId : item.golonganId, point : item.point, tipePegawaiId : item.tipePegawai,
                                                    statusPegawaiId: item.statusPegawai};
                                                functions.push(labelItem);
                                            });


                                            process(functions);
                                        },

                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaAlat = selectedObj.id;
                                            document.getElementById("personName1").value = selectedObj.id;
                                            document.getElementById("personName").value = selectedObj.pegawai;

                                            $('#branchId').val(selectedObj.branchId).change();
                                            $('#divisiId').val(selectedObj.divisiId).change();
                                            $('#golonganId').val(selectedObj.golonganId).change();
                                            $('#point').val(selectedObj.point).change();
                                            $('#positionId').val(selectedObj.positionId).change();
                                            $('#tipePegawai1').val(selectedObj.tipePegawaiId).change();
                                            $('#statusPegawai1').val(selectedObj.statusPegawaiId).change();
                                            branc = selectedObj.branchId;
                                            dev = selectedObj.divisiId ;
                                            cekJabatan();
                                            return namaAlat;
                                        }
                                    });
                                </script>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="personName" name="smk.pegawaiName" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="smk.branchId"
                                                  readonly="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="smk.positionId"
                                                  readonly="true"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smk.divisiId" readonly="true"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
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
                                        <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="smk.golonganId" readOnly="true"
                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Point :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="point" name="smk.point" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Closed :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'N', 'Y':'Y'}" id="close" name="smk.closed"
                                                  headerKey="" headerValue="Select" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>
                        </table>

                        <br>
                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="60%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 100%;" class="tableAspekA table table-bordered">
                                            <%--<thead>
                                                <tr>
                                                    <td>
                                                        Ulil
                                                    </td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        Azmi
                                                    </td>
                                                </tr>
                                            </tbody>--%>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <br>
                            <table width="60%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 100%;" class="tableAspekB table table-bordered">

                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <br>
                            <table width="60%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 100%;" class="tableAspekC table table-bordered">

                                        </table>
                                    </td>
                                </tr>
                            </table>

                        </center>

                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <s:if test="%{smk.editNilai}">
                                        <td>
                                            <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="sppdForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-check"></i>
                                                Save
                                            </sj:submit>
                                        </td>
                                    </s:if>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_smk.action"/>'">
                                            <i class="fa fa-close"></i> Cancel
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

    $(document).ready(function() {
        tableAspekA();
        tableAspekB();
        tableAspekC();
    });

    window.loadAspek = function(){
        $("#showdata").show();
    }

    window.tableAspekA = function(){
        $('.tableAspekA').find('tbody').remove();
        $('.tableAspekA').find('thead').remove();
        $('.tableAspekA').find('tfoot').remove();

        var nip         = document.getElementById("personName1").value;
        var periode         = document.getElementById("periodeId").value;

        SmkAction.getAspekA(nip, periode, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+
                    "</tr></thead>";
            var x = 1 ;
            var jumlahBobot = 0;
            var jumlahPrestasi = 0;
            var pegawaiAspekId = '';
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        "<a href='editAspekA_smk?id="+item.evaluasiPegawaiAspekDetailId+"&checklist="+item.subAspekA+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' + x + '</td>' +
                        '<td >' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        '<td align="center">' + item.target+ '</td>' +
                        '<td align="center">' + item.realisasi+ '</td>' +
                        '<td align="center">' + item.nilai+ '</td>' +
                        '<td align="center">' + item.nilaiPrestasiItem+ '</td>' +
                        '<td align="center"></td>' +
                        '<td align="center"></td>' +
                        "</tr>";
                x++;
                jumlahBobot += item.bobot;
                jumlahPrestasi += item.nilaiPrestasiItem;
                pegawaiAspekId = item.evaluasiPegawaiAspekId ;
            });
            tmp_table += '<tfoot >' +
                    '<tr style="font-size: 12px;">' +
                    '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                    '<td style="text-align: center"> '+jumlahBobot+'</td>' +
                    '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                    '<td style="text-align: center">'+jumlahPrestasi+'</td>' +
                    '</tr>' +
                    '</tfoot>';
            SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {});
            $('.tableAspekA').append(tmp_table);
        });
    }

    window.tableAspekB = function(){
        $('.tableAspekB').find('tbody').remove();
        $('.tableAspekB').find('thead').remove();
        $('.tableAspekB').find('tfoot').remove();
        var nip         = document.getElementById("personName1").value;
        var periode     = document.getElementById("periodeId").value;

        SmkAction.getAspek(nip, periode, "B", function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+
                    "</tr></thead>";
            var x = 1 ;
            var jumlahBobot = 0;
            var jumlahPrestasi = 0;
            var pegawaiAspekId = '';
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        "<a href='editAspek_smk?id="+item.evaluasiPegawaiAspekDetailId+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' + x + '</td>' +
                        '<td >' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td align="center">' + item.nilai+ '</td>' +
                        '<td align="center">' + item.nilaiPrestasiItem+ '</td>' +
                        '<td align="center"></td>' +
                        '<td align="center"></td>' +
                        "</tr>";
                x++;
                jumlahBobot += item.bobot;
                jumlahPrestasi += item.nilaiPrestasiItem;
                pegawaiAspekId = item.evaluasiPegawaiAspekId ;
            });
            tmp_table += '<tfoot >' +
                    '<tr style="font-size: 12px;">' +
                    '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                    '<td style="text-align: center"> '+jumlahBobot+'</td>' +
                    '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                    '<td style="text-align: center">'+jumlahPrestasi+'</td>' +
                    '</tr>' +
                    '</tfoot>';
            $('.tableAspekB').append(tmp_table);
            SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {
            });
        });
    }

    window.tableAspekC = function(){
        $('.tableAspekC').find('tbody').remove();
        $('.tableAspekC').find('thead').remove();
        $('.tableAspekC').find('tfoot').remove();

        var nip         = document.getElementById("personName1").value;
        var periode     = document.getElementById("periodeId").value;

        var jumlahBobot = 0;
        var jumlahPrestasi = 0;
        var pegawaiAspekId = '';

        SmkAction.getAspek(nip, periode, "C", function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+
                    "</tr></thead>";
            var x = 1 ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        "<a href='editAspek_smk?id="+item.evaluasiPegawaiAspekDetailId+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' + x + '</td>' +
                        '<td >' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td align="center">' + item.nilai+ '</td>' +
                        '<td align="center">' + item.nilaiPrestasiItem+ '</td>' +
                        '<td align="center"></td>' +
                        '<td align="center"></td>' +
                        "</tr>";
                x++;
                jumlahBobot += item.bobot;
                jumlahPrestasi += item.nilaiPrestasiItem;
                pegawaiAspekId = item.evaluasiPegawaiAspekId ;
            });
            tmp_table += '<tfoot >' +
                    '<tr style="font-size: 12px;">' +
                    '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                    '<td style="text-align: center"> '+jumlahBobot+'</td>' +
                    '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                    '<td style="text-align: center">'+jumlahPrestasi+'</td>' +
                    '</tr>' +
                    '</tfoot>';
            $('.tableAspekC').append(tmp_table);
            SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {
            });
        });
    }

</script>




