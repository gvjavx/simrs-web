<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
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
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };

        function cancelBtn2() {
            $('#view_dialog_function2').dialog('close');
        };

        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Medical Record
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="searchForm" method="post"  theme="simple" namespace="/medicalrecord" action="search_medicalrecord.action" cssClass="form-horizontal">

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
                                                <label class="control-label"><small>Tipe Pengobatan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'RI':'Rawat Inap','RJ':'Rawat Jalan'}" id="tipePengobatan" name="medicalRecord.tipePengobatan"
                                                              headerKey="" headerValue="" cssClass="form-control"/>
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
                                                    <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="medicalRecord.branchId" required="true"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Bagian :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>
                                                    <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"
                                                              listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="indisipliner.bagianId" />
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>NIP :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="nip32" name="medicalRecord.nip" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <script type='text/javascript'>
                                            var functions, mapped;
                                            $('#nip32').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};

                                                    var data = [];
                                                    var unit = $('#branchid').val();
                                                    if (unit==""){
                                                        alert("unit is empty");
                                                        $('#namaId').val("");
                                                        $('#nip32').val("");
                                                    }else {
                                                        dwr.engine.setAsync(false);
                                                        CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
                                                            data = listdata;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = item.nip+" "+item.namaPegawai;
                                                            mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                            functions.push(labelItem);
                                                        });

                                                        process(functions);
                                                    }

                                                },
                                                updater: function (item) {
                                                    var selectedObj = mapped[item];
                                                    var namaMember = selectedObj.label;
                                                    document.getElementById("namaId").value = selectedObj.nama;
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
                                                    <s:textfield id="namaId" name="medicalRecord.namaPegawai" required="true" readonly="true" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Tanggal :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="loginTimestampFrom" name="medicalRecord.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false" size="7"  cssStyle=""/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="loginTimestampTo" name="medicalRecord.stTanggalSampai" cssClass="form-control pull-right"
                                                                     required="false" size="7"  cssStyle=""/>
                                                    </div>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-success" onclick="window.location.href='<s:url action="add_medicalrecord"/>' ">
                                                        <i class="fa fa-plus"></i> Add Medical Record
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_medicalrecord"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="80%">
                                            <tr>
                                                <td align="center">
                                                    <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"
                                                               resizable="false"
                                                               height="350" width="600" autoOpen="false" title="Loading ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <center>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                               height="800" width="800" autoOpen="false"
                                                               title="Medical Record">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>

                                                    <s:set name="listOfResult" value="#session.listOfResult" scope="request" />
                                                    <display:table name="listOfResult" class="table table-condensed table-striped table-hover tabelMedical"
                                                                   requestURI="paging_displaytag_medicalrecord.action" export="true" id="row" pagesize="20" style="font-size:10">
                                                        <display:column media="html" title="Koreksi">
                                                            <s:if test="%{#attr.row.koreksi}">
                                                                <s:if test="%{#attr.row.admin}">
                                                                    <a href="javascript:;"  data="<s:property value="%{#attr.row.medicalRecordId}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                                        <img border="0" src="<s:url value="/pages/images/view_detail_project.png"/>" name="icon_edit">
                                                                    </a>
                                                                </s:if>
                                                            </s:if>
                                                        </display:column>
                                                        <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                            <s:url var="urlViewDoc" namespace="/medicalrecord" action="initApprove_medicalrecord" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.medicalRecordId" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                <s:param name="view">Y</s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">
                                                                <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                            </sj:a>

                                                        </display:column>
                                                        <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                            <s:if test="%{#attr.row.edit}">
                                                                <s:url var="urlViewDelete" value="edit_medicalrecord.action" >
                                                                    <s:param name="id"><s:property value="#attr.row.medicalRecordId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    <s:param name="delete">Y</s:param>
                                                                </s:url>
                                                                <s:a href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                </s:a>
                                                            </s:if>
                                                            <s:elseif test="%{#attr.row.delete}">
                                                                <s:url var="urlViewDelete" value="edit_medicalrecord.action" >
                                                                    <s:param name="id"><s:property value="#attr.row.medicalRecordId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    <s:param name="delete">Y</s:param>
                                                                </s:url>
                                                                <s:a href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                </s:a>
                                                            </s:elseif>
                                                        </display:column>
                                                        <display:column media="html" title="Edit">
                                                            <s:if test="%{#attr.row.edit}">
                                                                <s:url var="urlEdit" value="edit_medicalrecord.action">
                                                                    <s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <s:a href="%{urlEdit}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:if>
                                                            <s:else>
                                                            </s:else>
                                                        </display:column>

                                                        <display:column media="html" title="Approve">
                                                            <s:if test="%{#attr.row.approve}">
                                                                <s:url var="urlApprove" namespace="/medicalrecord" action="initApprove_medicalrecord" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlApprove}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                </sj:a>
                                                            </s:if>
                                                            <s:else>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Print<br> Surat Jaminan">
                                                            <s:if test="%{#attr.row.print}">
                                                                <s:url var="urlPrint" namespace="/medicalrecord" action="printSuratJaminan_medicalrecord" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <s:a href="%{urlPrint}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:if>
                                                            <s:else>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Print<br> Surat Medical" style="text-align:center;font-size:9">
                                                            <s:if test="%{#attr.row.printSuratMedical}">
                                                                <s:url var="urlCetakSuratMedical" namespace="/medicalrecord" action="cetakSuratMedicalRecord_medicalrecord" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.medicalRecordId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <s:a href="%{urlCetakSuratMedical}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:if>

                                                        </display:column>
                                                        <display:column property="medicalRecordId" sortable="true" title="Medical Record Id" />
                                                        <display:column property="nip" sortable="true" title="NIP"  />
                                                        <display:column property="namaBerobat" sortable="true" title="Nama Pegawai"  />
                                                        <display:column property="tanggalBerobat" sortable="true" title="tanggal Berobat"  />
                                                        <display:column property="tipePengobatanName" sortable="true" title="Tipe Pengobatan"  />
                                                        <display:column property="tipeBerobatName" sortable="true" title="Tipe Berobat"  />
                                                        <display:column property="iconApprove" sortable="true" title="Status"/>
                                                        <display:column property="note" sortable="true" title="Note"  />
                                                    </display:table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                </s:form>
                            </td>
                        </tr>
                    </table>
                </div>
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

<div id="modal-koreksi" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Koreksi Data Medical</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <s:hidden id="idMedicalRecord"></s:hidden>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Keterangan Koreksi : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="keteranganKoreksi" name=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveKoreksi" type="button" class="btn btn-default btn-success">Koreksi</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    var tmp_data = new Array();
    var namaMt = [];

    function cek(id, flag){
        alert(id + " " + flag);
    }

    function ok(id, flag){
        //alert(id + " " + flag);

        dwr.engine.setAsync(false);
        AlatAction.init(id, flag, function (response) {
            namaMt = response;
        });

        //alert('idnya : ' + namaMt.kodeAlat + ' flag : ' + namaMt.flag);
        $('input[id=kodeAlat]').val(namaMt.kodeAlat);
        $('input[id=namaAlat]').val(namaMt.namaAlat);
        $('input[id=flag]').val(namaMt.flag);


        $('#modal-edit').modal('show');
    }

    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tabelMedical').on('click', '.item-edit', function(){
            var medicalId = $(this).attr('data');
            $('#idMedicalRecord').val(medicalId);
            $('#modal-koreksi').modal('show');
            // var data;
            // if (confirm('Apakah anda ingin mengoreksi data ini ?')) {
            //     MedicalRecordAction.koreksi(medicalId, function (rt) {
            //         data = rt;
            //     });
            //     window.location.reload();
            //     if (rt=="01"){
            //         alert("data berhasil dikoreksi");
            //     } else{
            //         alert("data gagal dikoreksi");
            //     }
            // } else {
            // }
        });
        $('#btnSaveKoreksi').click(function () {
            var medicalId=$('#idMedicalRecord').val();
            var keterangan=$('#keteranganKoreksi').val();
            var data;
            if (confirm('Apakah anda ingin mengoreksi data ini ?')) {
                MedicalRecordAction.koreksi(medicalId,keterangan, function (rt) {
                    data = rt;
                });
                window.location.reload();
                if (rt=="01"){
                    alert("data berhasil dikoreksi");
                } else{
                    alert("data gagal dikoreksi");
                }
            } else {
            }
        })
    });

</script>


