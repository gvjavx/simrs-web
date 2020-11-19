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
    <script type='text/javascript' src='<s:url value="/dwr/interface/TrainingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
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
            var noWo = document.getElementById('trainingName').value;
            if (noWo !== '') {
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
                if (document.getElementById("trainingName").value === '') {
                    msg = 'Field <strong>Training Name</strong> is required.' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;
                $.publish('showErrorValidationDialog');
            }
        });
        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status === 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function callSearchFunction() {
            $('#info_dialog').dialog('close');
            document.training.action='initForm_training.action';
            document.training.submit();
        };

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
            Training
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
                        <h3 class="box-title">Add Training</h3>
                    </div>
                    <form role="form" method="post" name="training" id="training" action="save_training.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

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
                                            <s:select list="#comboBranch.listOfComboBranches" id="unitId" name="training.unitId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue=""
                                                      cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" onchange="changeBranch()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama Training</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="trainingName" name="training.trainingName" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Tipe Training </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:select list="#{'internal':'Internal','eksternal':'Eksternal'}" id="tipeTraining" name="training.tipeTraining"
                                                      headerKey="" headerValue="" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" onchange="showAdd()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Instansi </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="instansi" name="training.instansi" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Kota </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="kota" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                            <s:textfield id="kota11" readonly="true" name="training.kota" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px; display: none"/>
                                        </td>
                                    </tr>
                                    <script type='text/javascript'>
                                        var functions, mapped;
                                        // var prov = document.getElementById("provinsi1").value;
                                        $('#kota').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};

                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                ProvinsiAction.initComboKota(query,"", function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    //alert(item.kotaName);
                                                    var labelItem = item.kotaName;
                                                    mapped[labelItem] = { id: item.kotaId, label: labelItem };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                var namaAlat = selectedObj.label;
                                                document.getElementById("kota11").value = selectedObj.id;
                                                kab = selectedObj.id ;
                                                return namaAlat;
                                            }
                                        });
                                    </script>
                                    <tr>
                                        <td>
                                            <label>Tanggal Mulai Training </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="tanggalStart" name="training.stTanggalStart" cssClass="form-control"
                                                             required="false"  cssStyle="margin-top: -25px;margin-left: 20px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Tanggal Selesai Training </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="tanggalEnd" name="training.stTanggalEnd" cssClass="form-control"
                                                         required="false"  cssStyle="margin-top: -25px;margin-left: 20px;"/>
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
                                                    <table>
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="training" id="save" name="save"
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
                                                            <center><img id="image_write" border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write"></center>
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
                                                        <s:url var="urlAdd" namespace="/training" action="addPerson_training" escapeAmp="false">
                                                            <s:param name="unit">KP</s:param>
                                                        </s:url>
                                                        <sj:a id="btnAdd" cssClass="btn btn-success" onClickTopics="showDialogMenuEdit" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Person
                                                        </sj:a>
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>

                                                        <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="add_training"/>'">
                                                            <i class="fa fa-repeat"></i> Reset
                                                        </button>

                                                    </table>
                                                </td>
                                                <td>
                                                    <table>

                                                        <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_training"/>'">
                                                            <i class="fa fa-back"></i> Back
                                                        </button>

                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                </div>
                            </div>
                            <center>
                                <table id="showdata" width="40%">
                                    <tr>
                                        <td align="center">
                                            <br>
                                            <label>Daftar Peserta Training</label>
                                            <br>
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Training">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuEdit" modal="true"
                                                       height="350" width="500" autoOpen="false"
                                                       title="Training">
                                            </sj:dialog>

                                            <s:set name="listOfResult" value="#session.listOfResultPerson" scope="request" />
                                            <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_training.action" export="false" id="row" pagesize="10" style="font-size:10">
                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/training" action="editAddPerson_training" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.trainingPersonId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <s:param name="delete">Y</s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenuEdit" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </display:column>
                                                <display:column property="trainingPersonId" sortable="true" title="T. Person Id" />
                                                <display:column property="personId" sortable="true" title="NIP" />
                                                <display:column property="personName" sortable="true" title="Nama"  />
                                                <display:column property="divisiName" sortable="true" title="Bidang"  />
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
    /*function showAdd(){
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
    }*/



    function checkTheBox(){
        var tb = document.getElementById("tipePengobatan").value;
        if (tb == 'RI'){
            document.getElementById('checkbox').checked = true;
            document.getElementById("flagSuratJaminan").value = "Y"
        } else {
            document.getElementById('checkbox').checked = false;
            document.getElementById("flagSuratJaminan").value = "N"
        }
    }
/*    function changeBranch(){
        var branchId = document.getElementById("branchId").value;
        unit = branchId;
        document.getElementById('br').innerHTML = unit;
//        alert(unit);
    }*/

    $(document).ready(function() {
        $('#tanggalStart').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tanggalEnd').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        var kotaId = document.getElementById("kota11").value;
        if ( kotaId !==""){
            ProvinsiAction.findKota(kotaId, function (kotaName) {
                $('#kota').val(kotaName);
            });
        }
    });
    var unit = "";
    /*$('#btnAdd').click(function(){
        if (confirm('Do you want to save this record ?')) {
        }else{
            $('#view_dialog_menu').dialog('close');
        }
    })*/
</script>
