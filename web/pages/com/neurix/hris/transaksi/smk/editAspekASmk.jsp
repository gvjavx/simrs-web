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

        function checkDec(el){
            var ex = /^[0-9]+\.?[0-9]*$/;
            if(ex.test(el.value)==false){
                el.value = el.value.substring(0,el.value.length - 1);
            }
        }

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
                window.history.back();
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var nip             = document.getElementById("evaluasiPegawaiAspekDetailId").value;
                var target = document.getElementById("target").value;
                var realisasi = document.getElementById("realisasi").value;
                var nilai = document.getElementById("nilai").value;

                var isnum = /^-?\d*[.]?\d*$/.test(nilai);
                if (nip != '' && target != '' && realisasi != '' && nilai != '') {
                    if(isnum == true){
                        if (confirm('Do you want to save this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        } else {
                            // Cancel Submit comes with 1.8.0
                            event.originalEvent.options.submit = false;
                        }
                    }else{
                        event.originalEvent.options.submit = false;
                        alert('Koma (,) harus menggunakan huruf titik(.)');
                    }
                } else {

                    event.originalEvent.options.submit = false;

                    var msg = "";

                    if (nip == '') {
                        msg += 'Field <strong>Nip</strong> is required.' + '<br/>';
                    }

                    if (target == '') {
                        msg += 'Field <strong>Target</strong> is required.' + '<br/>';
                    }

                    if (realisasi == '') {
                        msg += 'Field <strong>Realisasi</strong> is required.' + '<br/>';
                    }

                    if (nilai == '') {
                        msg += 'Field <strong>Nilai</strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });
        });


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<div id="modal-nilai" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:400px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Id</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="nilaiId" name="nip">
                            <input style="display: none" readonly type="text" class="form-control nip" id="activityId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="nilaiBulan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai</label>
                        <div class="col-sm-8">
                            <input type="text" onkeyup="checkDec(this);" class="form-control nip" id="nilaiValue" name="nip">
                        </div>
                    </div>
                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnSaveNilai" class="btn btn-success"><i class="fa fa-save"></i> Simpan</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-view" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:800px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <div class="col-sm-offset-5 col-sm-3">
                            <a type="button" class="btn btn-primary btnAddKejadian">Tambah Peristiwa</a>
                        </div>
                    </div>

                    <div class="form-group" style="display: none" >
                        <label class="control-label col-sm-4" >Id</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="activityMonthlyId" name="nip">
                        </div>
                    </div>

                </form>

                <table style="width: 100%;" id="peristiwaTable" class="peristiwaTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-view-peristiwa" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:600px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-2" >Id</label>
                        <div class="col-sm-5">
                            <input readonly type="text" class="form-control nip" id="idBulanPeristiwa" name="nip">
                            <input readonly type="text" class="form-control nip" id="evaluasiActivityPeristiwaId" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" >Tanggal</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control nip" id="tanggalPeristiwa" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" >Peristiwa</label>
                        <div class="col-sm-9">
                            <textarea id="keteranganPeristiwa" rows="4" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnSavePeristiwa" class="btn btn-success"><i class="fa fa-save"></i> Simpan</a>
                <a type="button" id="btnSaveEditPeristiwa" class="btn btn-success"><i class="fa fa-save"></i> Simpan Edit</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>


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
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/smk" action="saveEditAspekADetail_smk.action" cssClass="well form-horizontal">

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
                                        <s:textfield  id="evaluasiPegawaiAspekDetailId" name="smk.evaluasiPegawaiAspekDetailId" readonly="true" cssClass="form-control"/>
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
                                                  headerKey="" headerValue="[Pilih Tahun]" cssClass="form-control" disabled="true" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Uraian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textarea  id="uraian" name="smk.indikatorKinerja" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Sub Aspek :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboCheckList" namespace="/smkCheckList" name="initComboCheckList_smkCheckList"/>
                                        <s:select list="#initComboCheckList.listComboSmkCheckList" id="tipeAspekA"
                                                  name="smk.subAspekA" disabled="true"
                                                  listKey="checkListId" listValue="checkListName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Target :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="%{smk.editRealisasi}">
                                            <s:textfield id="target" name="smk.target" readonly="false" cssClass="form-control"/>
                                        </s:if>
                                        <s:else>
                                            <s:textfield id="target" name="smk.target" readonly="true" cssClass="form-control"/>
                                        </s:else>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Realisasi:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="%{smk.editRealisasi}">
                                            <s:textfield id="realisasi" name="smk.realisasi" readonly="false" cssClass="form-control"/>
                                        </s:if>
                                        <s:else>
                                            <s:textfield id="realisasi" name="smk.realisasi" readonly="true" cssClass="form-control"/>
                                        </s:else>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Nilai :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="%{smk.editNilai}">
                                            <s:textfield onkeyup="checkDec(this);" id="nilai" name="smk.nilai" readonly="false" cssClass="form-control"/>
                                        </s:if>
                                        <s:else>
                                            <s:textfield onkeyup="checkDec(this);" id="nilai" name="smk.nilai" readonly="true" cssClass="form-control"/>
                                        </s:else>
                                    </table>
                                </td>
                            </tr>
                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>bobot :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="bobot" name="smk.bobot" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>

                        <br>
                        <center>
                            <table id="showdata" width="10%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="700" autoOpen="false"
                                                   title="Peristiwa">
                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                            <s:set name="listAspekMonthly" value="#session.listAspekMonthly" scope="request" />
                                        <display:table name="listAspekMonthly" class="table table-condensed table-striped table-hover listAspekMonthly"
                                                       requestURI="paging_displaytag_department.action" id="row" pagesize="14" style="font-size:10">
                                        <display:column media="html" title="View">
                                        <s:if test="#attr.row.flagYes">
                                        <s:url var="urlEdit" namespace="/smk" action="editAspekMonthly_smk" escapeAmp="false">
                                        </s:url>
                                        <a href="javascript:;" id = "<s:property value="%{#attr.row.aspekActivityMonthly}"/>"
                                           id2 = "<s:property value="%{#attr.row.evaluasiPegawaiAspekId}"/>" class="viewNilaiBulan">
                                            <s:if test="%{smk.editNilai}">
                                                <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_edit">
                                            </s:if>
                                            <s:else>
                                                <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                            </s:else>
                                        </a>
                                        </s:if>
                                        </display:column>

                                        <s:if test="%{smk.editNilai}">
                                        <display:column media="html" title="Edit">
                                        <s:if test="#attr.row.flagYes">
                                        <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                           id="<s:property value="%{#attr.row.aspekActivityMonthly}"/>"
                                           bulan="<s:property value="%{#attr.row.bulan}"/>"
                                           activityId="<s:property value="%{#attr.row.evaluasiPegawaiAspekId}"/>"
                                           nilai="<s:property value="%{#attr.row.nilai}"/>"
                                           class="editNilaiBulan">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                        </a>
                                        </s:if>
                                        </display:column>
                                        </s:if>

                                            <display:column property="bulan" sortable="true" title="Bulan" />
                                            <display:column property="nilai" sortable="true" title="Nilai"  />
                                        <display:footer>
                                        <s:if test="%{smk.editNilai}">
                                <tr>
                                    <td style="text-align: center" colspan="3">Rata - Rata </td>
                                    <td  >
                                        <s:property value="#attr.row.rataRata" />
                                    </td>
                                </tr>
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td style="text-align: center" colspan="2">Rata - Rata </td>
                                        <td  >
                                            <s:property value="#attr.row.rataRata" />
                                        </td>
                                    </tr>
                                </s:else>
                                </display:footer>
                                </display:table>
                                </td>
                                </tr>
                            </table>
                        </center>
                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <s:if test="%{smk.editRealisasi}">
                                            <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="sppdForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-check"></i>
                                                Save
                                            </sj:submit>
                                        </s:if>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.history.back();">
                                            <i class="fa fa-refresh"></i> Cancel
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
        $('#btnSaveEditPeristiwa').hide();

        $('.peristiwaTable').on('click', '.editPeristiwa', function(){
            var id = $(this).attr('id');
            var peristiwa = document.getElementById('activityMonthlyId').value;
            $('#idBulanPeristiwa').val(peristiwa);
            $('#evaluasiActivityPeristiwaId').val(id);
            $('#btnSaveEditPeristiwa').show();

            $('#btnSavePeristiwa').hide();
            SmkAction.getItemPeristiwa(id, function(listdata) {
                $('#tanggalPeristiwa').val(listdata.stTanggalKejadian);
                $('#keteranganPeristiwa').val(listdata.kejadian);
            });

            $('#modal-view-peristiwa').find('.modal-title').text('View');
            $('#modal-view-peristiwa').modal('show');
        });

        $('.peristiwaTable').on('click', '.deletePeristiwa', function(){
            var id = $(this).attr('id');
            var idMonthly = document.getElementById('activityMonthlyId').value;

            if (confirm('Apakah Anda ingin menghapus data peristiwa?')) {
                SmkAction.saveDeletePeristiwa(id, function(listdata) {
                    alert('Data Berhasil Dihapus');
                    refreshPeristiwaTable(idMonthly);
                });
            }

        });

        $('.listAspekMonthly').on('click', '.editNilaiBulan', function(){
            var id = $(this).attr('id');
            var bulan = $(this).attr('bulan');
            var activityId = $(this).attr('activityId');
            var nilai = $(this).attr('nilai');

            $('#nilaiId').val(id);
            $('#nilaiBulan').val(bulan);
            $('#activityId').val(activityId);
            $('#nilaiValue').val(nilai);

            $('#modal-nilai').find('.modal-title').text('Penilaian');
            $('#modal-nilai').modal('show');
        });

        $('.listAspekMonthly').on('click', '.viewNilaiBulan', function(){
            var aspekActivitymonthly = $(this).attr('id');
            var id2 = $(this).attr('id2');
            $('#activityMonthlyId').val(aspekActivitymonthly);
            $('.peristiwaTable').find('tbody').remove();
            $('.peristiwaTable').find('thead').remove();
            $('.peristiwaTable').find('tfoot').remove();

            SmkAction.editAspekMonthly(aspekActivitymonthly, function(listdata) {
                tmp_table = "<thead style='color: white' ><tr class='active'>"+
                        "<th width='5%' style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                        "<th width='15%' style='text-align: center; background-color:  #3c8dbc''>Tanggal</th>"+
                        "<th width='70%' style='text-align: center; background-color:  #3c8dbc''>Peristiwa</th>"+
                        "<th width='5%' style='text-align: center; background-color:  #3c8dbc''>Edit</th>"+
                        "<th width='5%' style='text-align: center; background-color:  #3c8dbc''>Delete</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {

                    tmp_table += '<tr  ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.stTanggalKejadian + '</td>' +
                            '<td align="center">' + item.kejadian + '</td>' +
                            '<td align="center">' +
                            '<a href="javascript:;" id="'+item.evaluasiActivityPeristiwaId+'" class="editPeristiwa">' +
                            '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">' +
                            '</a>' +
                            '</td>' +
                            '<td align="center">' +
                            '<a href="javascript:;" id="'+item.evaluasiActivityPeristiwaId+'" class="deletePeristiwa">' +
                            '<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_edit">' +
                            '</a>' +
                            '</td>' +
                            "</tr>";
                });

                $('.peristiwaTable').append(tmp_table);
            });

            $('#modal-view').find('.modal-title').text('View Peristiwa');
            $('#modal-view').modal('show');
        });

        $('#tanggalPeristiwa').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#btnSaveNilai').click(function(){
            var idDetail = document.getElementById('evaluasiPegawaiAspekDetailId').value;
            var id = document.getElementById('nilaiId').value;
            var bulan = document.getElementById('nilaiBulan').value;
            var nilai = document.getElementById('nilaiValue').value;
            var activityId = document.getElementById('activityId').value;
            var bobot = document.getElementById('bobot').value;

            if (confirm('Apakah Anda ingin merubah Data?')) {
                SmkAction.saveEditAspek(idDetail, activityId, id, bulan, nilai, bobot, function(listdata) {
                    alert('Data Berhasil Dirubah');
                    $('#modal-nilai').modal('hide');
                    //$('#myForm')[0].reset();
                    location.reload();
                });
            }
        });

        $('#btnSavePeristiwa').click(function(){
            var id = document.getElementById('idBulanPeristiwa').value;
            var tanggal = document.getElementById('tanggalPeristiwa').value;
            var peristiwa = document.getElementById('keteranganPeristiwa').value;

            if (confirm('Apakah Anda ingin merubah Data?')) {
                SmkAction.saveAddPeristiwa(id, tanggal, peristiwa, function(listdata) {
                    if(listdata == 'success'){
                        alert('Data peristiwa berhasil ditambahkan');
                        refreshPeristiwaTable(id);
                        $('#modal-view-peristiwa').modal('hide');
                    }else{
                        alert('Terjadi Kesalahan');
                    }
                });
            }
        });

        $('#btnSaveEditPeristiwa').click(function(){
            var id = document.getElementById('evaluasiActivityPeristiwaId').value;
            var tanggal = document.getElementById('tanggalPeristiwa').value;
            var peristiwa = document.getElementById('keteranganPeristiwa').value;

            var idMonthly = document.getElementById('idBulanPeristiwa').value;
            if (confirm('Apakah Anda ingin merubah Data?')) {
                SmkAction.saveEditPeristiwa(id, tanggal, peristiwa, function(listdata) {
                    if(listdata == 'success'){
                        alert('Data Berhasil Dirubah');
                        refreshPeristiwaTable(idMonthly);
                        $('#modal-view-peristiwa').modal('hide');
                    }else{
                        alert('Terjadi Kesalahan');
                    }
                });
            }
        });

        $('.btnAddKejadian').on('click', function(){
            var peristiwa = document.getElementById('activityMonthlyId').value;
            $('#idBulanPeristiwa').val(peristiwa);

            $('#evaluasiActivityPeristiwaId').val("");
            $('#tanggalPeristiwa').val("");
            $('#keteranganPeristiwa').val("");

            $('#btnSavePeristiwa').show();
            $('#btnSaveEditPeristiwa').hide();

            $('#modal-view-peristiwa').find('.modal-title').text('View');
            $('#modal-view-peristiwa').modal('show');
        });
    });

    window.refreshPeristiwaTable = function(aspekActivitymonthly){
        $('.peristiwaTable').find('tbody').remove();
        $('.peristiwaTable').find('thead').remove();
        $('.peristiwaTable').find('tfoot').remove();

        SmkAction.editAspekMonthly(aspekActivitymonthly, function(listdata) {
            tmp_table = "<thead style='color: white' ><tr class='active'>"+
                    "<th width='5%' style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th width='15%' style='text-align: center; background-color:  #3c8dbc''>Tanggal</th>"+
                    "<th width='70%' style='text-align: center; background-color:  #3c8dbc''>Peristiwa</th>"+
                    "<th width='5%' style='text-align: center; background-color:  #3c8dbc''>Edit</th>"+
                    "<th width='5%' style='text-align: center; background-color:  #3c8dbc''>Delete</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr  ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.stTanggalKejadian + '</td>' +
                        '<td align="center">' + item.kejadian + '</td>' +
                        '<td align="center">' +
                        '<a href="javascript:;" id="'+item.evaluasiActivityPeristiwaId+'" class="editPeristiwa">' +
                        '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" >' +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        '<a href="javascript:;" id="'+item.evaluasiActivityPeristiwaId+'" class="deletePeristiwa">' +
                        '<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" >' +
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });

            $('.peristiwaTable').append(tmp_table);
        });
    }


</script>




