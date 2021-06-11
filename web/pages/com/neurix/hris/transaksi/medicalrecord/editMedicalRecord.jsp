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
    <%--<script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>--%>
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

        $.subscribe('beforeProcessSave', function (event, data) {

            var noWo = document.getElementById('nip').value;

            if (noWo != '') {

                if (confirm('Do you want to save this record ?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }

            } else {

                event.originalEvent.options.submit = false;

                var msg = "";
                if (document.getElementById("nip").value == '') {
                    msg = 'Field <strong>NIP</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;
                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function callSearchFunction() {
            $('#info_dialog').dialog('close');
            document.saveMedicalRecord.action='initForm_medicalrecord.action';
            document.saveMedicalRecord.submit();
        };


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

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
                        <h3 class="box-title">Edit Medical Record</h3>
                    </div>

                    <form role="form" method="post" name="saveMedicalRecord" id="saveMedicalRecord" action="saveEdit_medicalrecord.action">
                        <div class="box-body">
                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Unit </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                            <s:select list="#comboBranch.listOfComboBranches" id="branchId1" name="medicalRecord.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" disabled="true"/>
                                            <s:textfield id="branchId" name="medicalRecord.branchId" cssClass="form-control" cssStyle="display:none;margin-top: -25px;margin-left: 20px;" readonly="true" />
                                            <s:hidden name="medicalRecord.noSuratJaminan"></s:hidden>
                                            <s:hidden name="medicalRecord.createdDate"></s:hidden>
                                            <s:hidden name="medicalRecord.createdWho"></s:hidden>
                                            <s:hidden name="medicalRecord.flag"></s:hidden>
                                            <s:hidden name="medicalRecord.action"></s:hidden>
                                            <s:hidden name="medicalRecord.medicalRecordId"></s:hidden>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>NIP</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="nip" name="medicalRecord.nip" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="nama" name="medicalRecord.namaBerobat" cssClass="form-control" readonly="true" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Status Pegawai </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:select list="#{'KS':'Karyawan Staff','KNS':'Karyawan Non Staff'}" id="statusPegawai1" name="medicalRecord.statusPegawai"
                                                      headerKey="" headerValue="[Select One]" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" disabled="true"/>
                                            <s:textfield id="statusPegawai" cssClass="form-control" readonly="true" cssStyle=" display: none;margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <label>Golongan  </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="golonganId" name="medicalRecord.golonganId" cssClass="form-control" readonly="true" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Tanggal Berobat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="tanggalBerobat" name="medicalRecord.stTanggalBerobat" cssClass="form-control"
                                                         required="false"  cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <label>Tipe Berobat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:select list="#{'S':'Sendiri','K':'keluarga'}" id="tipeOrangBerobat" name="medicalRecord.tipeOrangBerobat"
                                                      headerKey="" headerValue="[Select One]" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" onchange="showAdd()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama Berobat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="keluargaId123" name="medicalRecord.keluargaId" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;display: none" readonly="true"/>
                                            <s:textfield id="sendiri" name="medicalRecord.sendiri" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px; display: none" readonly="true"/>
                                            <s:textfield id="keluarga" name="medicalRecord.namaKeluarga" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                            <script>
                                                var functions, mapped;
                                                var userId = $('#nip').val();
                                                $('#keluarga').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);
                                                        MedicalRecordAction.initComboKeluarga(query, nip, function (listKeluarga) {
                                                            data = listKeluarga;
                                                        });
                                                        if (data.length==0){
                                                            alert("data keluarga tidak ditemukan")
                                                            $('#keluarga').val("");
                                                        }else{
                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.name+" || "+item.statusKeluargaName;
                                                                mapped[labelItem] = { id: item.keluargaId, label: item.name, status: item.statusKeluargaId};
                                                                functions.push(labelItem);
                                                            });
                                                        }


                                                        process(functions);
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaMember = selectedObj.label;
                                                        var statusPegawai = selectedObj.status;
                                                        document.getElementById("keluargaId123").value = selectedObj.id;
                                                        return namaMember;
                                                    }
                                                });
                                            </script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Tipe Pengobatan </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:select list="#{'RI':'Rawat Inap','RJ':'Rawat Jalan'}" id="tipePengobatan" name="medicalRecord.tipePengobatan"
                                                      headerKey="" headerValue="[Select One]" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" onchange="checkTheBox()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Rumah Sakit Id</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="rsId" name="medicalRecord.rsId" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                            <script>
                                                var functions, mapped;
                                                $('#rsId').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);
                                                        MedicalRecordAction.initComboRsKelas(query, status, nip, golonganId, function (listdataRs) {
                                                            data = listdataRs;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = item.rsName;
                                                            mapped[labelItem] = { id: item.rsKerjaSama, label: item.rsName, kelas: item.rsKelasName, kelasId: item.rsKelas };
                                                            functions.push(labelItem);
                                                        });

                                                        process(functions);
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaRs = selectedObj.label;
                                                        var namaKelas = selectedObj.kelas;
                                                        var kelasId = selectedObj.kelasId;
                                                        document.getElementById("rsKelasName").value = namaKelas;
                                                        document.getElementById("rsName").value = namaRs;
                                                        document.getElementById("rsKelasId").value = kelasId;
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama Rumah Sakit</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="rsName" name="medicalRecord.rsName" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Kelas Layanan RS</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="rsKelasName" name="medicalRecord.rsKelasName" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" readonly="true"/>
                                            <s:hidden id="rsKelasId" name="medicalRecord.rsKelasId"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Print Surat Jaminan </label>
                                        </td>
                                        <td>
                                            <input type="checkbox" id="checkbox" onclick="checkKlik()" style="margin-left: 20px;">
                                            <s:hidden id="flagSuratJaminan" name="medicalRecord.flagSuratJaminan"></s:hidden>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                            </div>
                                <div id="actions" class="form-actions">
                                    <div id="crud">
                                        <center>
                                            <table>
                                                <tr>
                                                    <td>
                                                        <table align="center">
                                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="saveMedicalRecord" id="save" name="save"
                                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;">
                                                                <i class="icon-ok-sign icon-white"></i>
                                                                Save
                                                            </sj:submit>

                                                            <sj:dialog id="waiting_dialog_save" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false" title="Saving ...">
                                                                Please don't close this window, server is processing your request ...
                                                                </br>
                                                                </br>
                                                                </br>
                                                                <center>
                                                                    <img id="image_write" border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                                </center>
                                                            </sj:dialog>

                                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                       buttons="{
                                                                          'OK':function() {
                                                                                  callSearchFunction();
                                                                               }
                                                                        }"
                                                            >
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                                Record has been saved successfully.
                                                            </sj:dialog>

                                                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                                       position="center" height="250" width="600" autoOpen="false" title="Error Dialog"
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
                                                                       position="center" height="280" width="500" autoOpen="false" title="Warning"
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

                                                            <sj:dialog id="info_dialog_update_session" openTopics="showInfoDialogUpdateSession" modal="true" resizable="false"
                                                                       position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                       buttons="{
                                                                                        'OK':function() { $('#info_dialog_update_session').dialog('close'); }
                                                                                    }"
                                                            >
                                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error">
                                                                Found failure when saving, please try again or call your admin.
                                                            </sj:dialog>

                                                        </table>
                                                    </td>
                                                    <td>
                                                        <table>

                                                            <s:url var="urlAdd" namespace="/medicalrecord" action="addBiayaEdit_medicalrecord" escapeAmp="false">
                                                                <s:param name="project">edit</s:param>
                                                            </s:url>
                                                            <sj:a id="btnAdd" cssClass="btn btn-success" onClickTopics="showDialogMenuEdit" href="%{urlAdd}">
                                                                <i class="fa fa-plus"></i>
                                                                Add Biaya Pengobatan
                                                            </sj:a>

                                                        </table>
                                                    </td>
                                                    <td>
                                                        <table>

                                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_medicalrecord"/>' ">
                                                                <i class="icon-chevron-left icon-black"></i> Back
                                                            </button>

                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </div>
                                </div>



                                <center>
                                <table id="showdata1" width="40%">
                                    <tr>
                                        <td align="center">
                                            <br>
                                            <label>Data Biaya Pengobatan</label>
                                            <br>
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Shift">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResult" value="#session.listOfResultPengobatan" scope="request" />
                                            <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_medicalrecord.action" export="false" id="row" pagesize="10" style="font-size:10">

                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/medicalrecord" action="editAddBiayaPengobatan_medicalrecord" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.pengobatanId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            <s:param name="project">edit</s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenuEdit" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>

                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlViewDelete" namespace="/medicalrecord" action="editAddBiayaPengobatan_medicalrecord" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.pengobatanId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <s:param name="delete">Y</s:param>
                                                        <s:param name="project">edit</s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenuEdit" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>

                                                <display:column media="html" title="Add Bukti <br> Pengobatan" style="text-align:center;font-size:9">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/medicalrecord" action="uploadFile_medicalrecord" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.pengobatanId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            <s:param name="project">edit</s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenuEdit" href="%{urlEdit}">
                                                            <i class="fa fa-upload" style="font-size: 21px"></i>
                                                        </sj:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>

                                                <display:column property="pengobatanId" sortable="true" title="Pengobatan Id" />
                                                <display:column property="biayaPengobatanId" sortable="true" title="Biaya Pengobatan Id" />
                                                <display:column property="namaBiayaPengobatan" sortable="true" title="Biaya Pengobatan" />
                                                <display:column property="status" sortable="true" title="Status" />
                                                <display:column property="jumlah" sortable="true" title="jumlah" />
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>

                            <center>
                                <table id="showdata" width="40%">
                                    <tr>
                                        <td align="center">
                                            <br>
                                            <label>Bukti Pengobatan</label>
                                            <br>
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Shift">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuEdit" modal="true"
                                                       height="350" width="500" autoOpen="false"
                                                       title="Medical Record">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultBukti" value="#session.listOfResultBuktiPengobatan" scope="request" />
                                            <display:table name="listOfResultBukti" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_medicalrecord.action" export="false" id="row" pagesize="10" style="font-size:10">

                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlViewDelete" namespace="/medicalrecord" action="deleteAddBukti_medicalrecord" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.buktiId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <s:param name="project">edit</s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenuEdit" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>

                                                <display:column property="pengobatanId" sortable="true" title="Pengobatan Id" />
                                                <display:column property="buktiId" sortable="true" title="Bukti Pengobatan Id" />
                                                <display:column property="fileName" sortable="true" title="File Name" />
                                                <display:column property="status" sortable="true" title="Status" />
                                                <display:column property="keterangan" sortable="true" title="Keterangan"  />

                                                <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDoc" namespace="/medicalrecord" action="viewDoc_medicalrecord" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.buktiId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">
                                                        <img border="0" src="<s:url value="/pages/images/view_detail_project.png"/>" name="icon_trash">
                                                    </sj:a>
                                                </display:column>
                                                <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>

                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>

                    </form>

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

<script type="application/javascript">
    //    document.getElementById("flagSuratJaminan").value = "N";
    function checkKlik(){
        var ck = document.getElementById("checkbox");
        var surat = document.getElementById("flagSuratJaminan");
        if(ck.checked){
            surat.value = "Y";
        } else {
            surat.value = "N"
        }
    }

    function showAdd(){
        var tb = document.getElementById("tipeOrangBerobat").value;
        var nama = document.getElementById("sendiri");
        var keluarga = document.getElementById("keluarga");
        if (tb == 'S'){
            nama.style.display = 'block';
            keluarga.style.display = 'none';
        }
        if(tb == 'K'){
            keluarga.style.display = 'block';
            nama.style.display = 'none';
        }
    }



    function checkTheBox(){
        var tb = document.getElementById("tipePengobatan").value;
        if (tb == 'RI'){
//            document.getElementById('btnAdd').style.display = 'block';
            document.getElementById('checkbox').checked = true;
            document.getElementById("flagSuratJaminan").value = "Y";

            var statusPegawai = document.getElementById('statusPegawai').value;
            var personId = document.getElementById('nip').value;
            var golongan = document.getElementById('golonganId').value;

            status = statusPegawai;
            nip = personId;
            golonganId = golongan;tip
        } else {
//            document.getElementById('btnAdd').style.display = 'none';
            document.getElementById('checkbox').checked = false;
            document.getElementById("flagSuratJaminan").value = "N";

            var rsKelasId = document.getElementById('rsKelasId').value = "";
            var rsId = document.getElementById('rsId').value = "";
            var rsKelasName = document.getElementById('rsKelasName').value = "";
            var rsName = document.getElementById('rsName').value = "";

            status = "";
            nip = "";
            golonganId = "";
        }
    }

    function changeBranch(){
        var branchId = document.getElementById("branchId").value;
        unit = branchId;
//        alert(unit);
    }


    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'dd-mm-yy',
            maxDate: 2
        });
//        $('#errorTimestampFrom').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
    });

    var unit = ""
    var status = "";
    var nip = "";
    var golonganId = "";
</script>

</script>


