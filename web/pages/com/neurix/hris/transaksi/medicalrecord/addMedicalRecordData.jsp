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
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

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

    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("funcId").value != '' &&
                    document.getElementById("funcName").value != '' &&
                    document.getElementById("parent").value != '') {

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
                if (document.getElementById("funcId").value == '') {
                    msg = 'Field <strong>FunctionId</strong> is required.' + '<br/>';
                }

                if (document.getElementById("funcName").value == '') {
                    msg = msg + 'Field <strong>Function Name</strong> is required.' + '<br/>';
                }

                if (document.getElementById("parent").value == '') {
                    msg = msg + 'Field <strong>Parent</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
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

//        function resetField() {
//            document.getElementById("funcId").value = '';
//            document.getElementById("funcName").value = '';
//            document.getElementById("parent").value = '';
//            document.getElementById("url").value = '';
//            document.getElementById("statusMenuFunc").checked=false;
//            setStatusMenu();
//        }
//
//        //        function okSuccessButton() {
//        //            //alert('OK Button pressed!');
//        //            $('#info_dialog').dialog('close');
//        //        };

    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
< class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Medical Record
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <div class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <td class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Add Medical Record Form</h3>
                    </div>

                    <div class="box-body">
                        <td class="form-group">
                            <s:form id="saveMedicalRecordForm" method="post" namespace="/medicalrecord" action="save_medicalrecord"
                                    cssClass="well form-horizontal">

                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Unit </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                            <s:select list="#comboBranch.listOfComboBranches" id="branchId" name="medicalRecord.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" disabled="true"/>
                                            <s:hidden name="medicalRecord.branchId"></s:hidden>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nip</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="nip" name="medicalRecord.nip" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                            <script>
                                                var functions, mapped;
                                                $('#nip').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);
                                                        MedicalRecordAction.initComboUser(query, function (listdata) {
                                                            data = listdata;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = item.username;
                                                            mapped[labelItem] = { id: item.userId, label: labelItem };
                                                            functions.push(labelItem);
                                                        });

                                                        process(functions);
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaMember = selectedObj.label;
                                                        document.getElementById("nama").value = namaMember;
                                                        document.getElementById("sendiri").value = namaMember;

                                                        return selectedObj.id;
                                                    }
                                                });
                                                //
                                                //
                                            </script>
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
                                            <label>Tanggal Berobat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="tanggalBerobat" name="medicalRecord.tanggalBerobat" cssClass="form-control"
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
                                            <s:textfield id="sendiri" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px; display: none" readonly="true"/>
                                            <s:textfield id="keluarga" name="medicalRecord.namaBerobat" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px; display: none"/>
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
                                            <label>Print Surat jalan </label>
                                        </td>
                                        <td>
                                            <input type="checkbox" id="checkbox" onclick="checkKlik()" style="margin-left: 20px;">
                                            <s:hidden id="flagSuratJaminan" name="medicalRecord.flagSuratJaminan"></s:hidden>
                                        </td>
                                    </tr>

                                </table>
                                <br>

                                <div align="center">
                                    <td>
                                        <div id="crud">
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="saveMedicalRecordForm" id="save" name="save"
                                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                                <i class="icon-ok-sign icon-white"></i>
                                                                Save
                                                            </sj:submit>

                                                            <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false" title="Saving ...">
                                                                Please don't close this window, server is processing your request ...
                                                                </br>
                                                                </br>
                                                                </br>
                                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>"
                                                                     name="image_indicator_write">
                                                            </sj:dialog>

                                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                       buttons="{
                                                                            'OK':function() {
                                                                                    $('#info_dialog').dialog('close');
                                                                                    if (document.getElementById('add').value == 'true') {
                                                                                        resetField();
                                                                                    }

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
                                                                    <label class="control-label" align="left"> <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                                                                    name="icon_error"> System Found : <p id="errorMessage"></p></label>
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

                                                        </td>
                                                        <td>
                                                            <s:url var="urlAdd" namespace="/medicalrecord" action="addBiaya_medicalrecord" escapeAmp="false">
                                                            </s:url>
                                                            <sj:a id="btnAdd" cssClass="btn btn-default" onClickTopics="showDialogMenu" href="%{urlAdd}"
                                                                  button="true">
                                                                <i class="fa fa-plus"></i>
                                                                Add Biaya Pengobatan
                                                            </sj:a>
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="add_medicalrecord"/>'">
                                                                <i class="fa fa-repeat"></i> Reset
                                                            </button>
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_medicalrecord"/>'">
                                                                <i class="fa fa-back"></i> Back
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </div>
                                    </td>
                                </div>
                                <div align="center">
                                    <table id="showdata" width="40%">
                                        <tr>
                                            <td align="center">
                                                <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                           height="500" width="900" autoOpen="false"
                                                           title="Shift">
                                                    <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                </sj:dialog>

                                                <s:set name="listOfResult" value="#session.listOfResultPengobatan" scope="request" />
                                                <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                               requestURI="paging_displaytag_alat.action" export="true" id="row" pagesize="10" style="font-size:10">
                                                    <display:column media="html" title="Edit">
                                                        <s:if test="#attr.row.flagYes">
                                                            <s:url var="urlEdit" namespace="/shift" action="edit_shift" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.shiftId"/></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                            </sj:a>
                                                        </s:if>
                                                    </display:column>

                                                    <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                        <s:url var="urlViewDelete" namespace="/shift" action="delete_shift" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.shiftId" /></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                        </sj:a>

                                                    </display:column>

                                                    <display:column media="html" title="Add Bukti Pengobatan">
                                                        <s:if test="#attr.row.flagYes">
                                                            <s:url var="urlEdit" namespace="/medicalrecord" action="uploadFile_medicalrecord" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.pengobatanId"/></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                            </sj:a>
                                                        </s:if>
                                                    </display:column>
                                                    <display:column property="pengobatanId" sortable="true" title="Pengobatan Id" />
                                                    <display:column property="biayaPengobatanId" sortable="true" title="Biaya Pengobatan Id" />
                                                    <display:column property="jumlah" sortable="true" title="jumlah"  />
                                                    <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>

                                                </display:table>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <br><br>
                            </s:form>
                        </td>
                    </div>
                </div>
            </td>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
<script type="application/javascript">
    document.getElementById("flagSuratJaminan").value = "N";
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
        var nama = document.getElementById("nama").value;
        if (tb == 'RI'){
//            document.getElementById('btnAdd').style.display = 'block';
            document.getElementById('checkbox').checked = true;
        } else {
//            document.getElementById('btnAdd').style.display = 'none';
            document.getElementById('checkbox').checked = false;
        }
    }


    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'dd-mm-yy'
        });
//        $('#errorTimestampFrom').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
    });
</script>
</html>



