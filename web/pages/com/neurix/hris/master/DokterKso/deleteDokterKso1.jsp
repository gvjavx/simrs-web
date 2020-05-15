<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterKsoAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>--%>

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
            window.location.href="<s:url action='initForm_dokterkso'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.close = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='search_dokterkso.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var dokterKsoId = document.getElementById("dokterKsoId3").value;
                var nip = document.getElementById("nip3").value;
                var branchId = document.getElementById("branchId3").value;
                var masterId = document.getElementById("masterId3").value;
                var jenisKso = document.getElementById("jenisKso3").value;
                var positionId = document.getElementById("positionId3").value;
                var persenKso = document.getElementById("persenKso3").value;
                var persenKs = document.getElementById("persenKs3").value;

                if (dokterKsoId != '' && nip != ''&& branchId != '' && masterId != '' && jenisKso != ''
                        && persenKso != '' && persenKs != '' && positionId != '') {
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
                    if (dokterKsoId == '') {
                        msg += 'Field <strong>Dokter KSO ID </strong> is required.' + '<br/>';
                    }
                    if (nip == '') {
                        msg += 'Field <strong>Nip </strong> is required.' + '<br/>';
                    }
                    if (branchId == '') {
                        msg += 'Field <strong>Unit </strong> is required.' + '<br/>';
                    }
                    if (masterId == '') {
                        msg += 'Field <strong>Master ID </strong> is required.' + '<br/>';
                    }
                    if (jenisKso == '') {
                        msg += 'Field <strong>Jenis KSO </strong> is required.' + '<br/>';
                    }
                    if (positionId == '') {
                        msg += 'Field <strong>Position ID </strong> is required.' + '<br/>';
                    }
                    if (persenKso == '') {
                        msg += 'Field <strong>Persen KSO </strong> is required.' + '<br/>';
                    }
                    if (persenKs == '') {
                        msg += 'Field <strong>Persen KS </strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('beforeProcessAddPerson', function (event, data) {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            });
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
            Edit Dokter KSO
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-pencil"></i> Edit Dokter KSO</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="deleteDokterKsoForm" method="post" theme="simple" namespace="/dokterkso" action="saveDelete_dokterkso" cssClass="well form-horizontal">
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
                                                    <label class="control-label"><small>Dokter KSO ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="dokterKsoId3" name="dokterKso.dokterKsoId" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                                        <s:hidden id="dokterKsoTindakanId3" name="dokterKso.dokterKsoTindakanId" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP Dokter :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip3" name="dokterKso.nip" cssClass="form-control" readonly="true"
                                                                     maxlength="12"
                                                        />
                                                        <script>
                                                            $(document).ready(function() {
                                                                var functions, mapped;
                                                                $('#nip3').typeahead({
                                                                    minLength: 1,
                                                                    source: function (query, process) {
                                                                        functions = [];
                                                                        mapped = {};
                                                                        var data = [];
                                                                        dwr.engine.setAsync(false);
                                                                        DokterAction.initTypeaheadDokter(query,function (listdata) {
                                                                            data = listdata;
                                                                        });
                                                                        $.each(data, function (i, item) {
                                                                            var labelItem = item.idDokter + " | " + item.namaDokter;
                                                                            mapped[labelItem] = {
                                                                                id: item.idDokter,
                                                                                nama: item.namaDokter
                                                                            };
                                                                            functions.push(labelItem);
                                                                        });
                                                                        process(functions);
                                                                    },
                                                                    updater: function (item) {
                                                                        var selectedObj = mapped[item];
                                                                        $('#namaDokter3').val(selectedObj.nama);
                                                                        return selectedObj.id;
                                                                    }
                                                                });
                                                            });
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Dokter:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaDokter3" name="dokterKso.namaDokter" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Master Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan', 'apotek' : 'Apotek',--%>
                                                            <%--'rawat_inap' : 'Rawat Inap', 'radiologi' : 'Radiologi', 'lab' : 'LAB'}" id="tipePelayanan" name="pelayanan.tipePelayanan"--%>
                                                            <%--listKey="positionId" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                                        <s:select list="#{'bpjs':'BPJS', 'umum' : 'Umum'}"
                                                                  id="masterId3" name="dokterKso.masterId"
                                                                  headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                                                        <s:hidden id="masterId3" name="dokterKso.masterId" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                                            <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"--%>
                                                            <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                                        <s:if test='dokterKso.branchUser == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                                            <s:select list="#initComboBranch.listOfComboBranches" id="branchId3" name="dokterKso.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                                            <s:select list="#initComboBranch.listOfComboBranches" id="branchId3" name="dokterKso.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId3" name="dokterKso.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Divisi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboPosition" namespace="/dokterkso" name="initComboPosition_dokterkso"/>
                                                        <s:select list="#initComboPosition.listOfComboPositions" id="positionId3" name="dokterKso.positionId" disabled="true"
                                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        <s:hidden id="positionId3" name="dokterKso.positionId" />
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Jenis KSO:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'tindakan':'Tindakan', 'obat' : 'Obat', 'kamar' : 'Kamar'}"
                                                                  id="jenisKso3" name="dokterKso.jenisKso" disabled="true"
                                                                  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Persen KSO:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="persenKso3" name="dokterKso.persenKso" type="number" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Persen KS:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="persenKs3" name="dokterKso.persenKs" readonly="true" type="number" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tarif Ina :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="tarifIna3" name="dokterKso.tarifIna" disabled="true"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                        <s:hidden id="tarifIna3" name="dokterKso.tarifIna" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <table style="width: 100%;" class="tindakanDetailTable table table-bordered" id="tindakanDetailTable">
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit targets="o" type="button" cssClass="btn btn-primary" formIds="deleteDokterKsoForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Delete
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_dokterkso.action"/>'">
                                                            <i class="fa fa-arrow-left"></i> Back
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="350" width="600" autoOpen="false" title="Saving ...">
                                            Please don't close this window, server is processing your request ...
                                            </br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                      close();
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
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
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
        window.listResult= function () {
            $('.tindakanDetailTable').empty();
            dwr.engine.setAsync(false);
            DokterKsoAction.searchTindakanDetailSession(function(listdata) {
                tmp_table = "<thead><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Tindakan ID</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Nama Tindakan</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Persen KSO</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.tindakanId + '</td>' +
                            '<td align="center">' + item.tindakanName + '</td>' +
                            '<td align="center">' + item.persenKso + '</td>' +
                            "</tr>";
                });
                $('.tindakanDetailTable').append(tmp_table);
                $('#tindakanDetailTable').DataTable({
                    "pageLength": 20,
                    "bDestroy":true
                });
            });
        };
        listResult();
    })
</script>
