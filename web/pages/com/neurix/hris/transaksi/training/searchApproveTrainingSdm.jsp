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
    <script type='text/javascript' src='<s:url value="/dwr/interface/TrainingAction.js"/>'></script>
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
                if (document.getElementById("trainingName").value == '') {
                    msg = 'Field <strong>Training Name</strong> is required.' + '<br/>';
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
            document.training.action='initForm_training.action';
            document.training.submit();
        };


        var tipeOrangBerobat = document.getElementById("tipeOrangBerobat").value;
        if(tipeOrangBerobat == 'K'){
            document.getElementById('keluarga').style.display = 'block';
            document.getElementById('sendiri').style.display = 'none';
        }else{
            document.getElementById('sendiri').style.display = 'block';
            document.getElementById('keluarga').style.display = 'none';
        }


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
                        <h3 class="box-title">Approve Training for SDM</h3>
                    </div>

                    <form role="form" method="post" name="training" id="training" action="saveEdit_training.action">
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
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" onchange="changeBranch()" disabled="true"/>
                                            <s:hidden name="training.createdDate"></s:hidden>
                                            <s:hidden name="training.createdWho"></s:hidden>
                                            <s:hidden name="training.flag"></s:hidden>
                                            <s:hidden name="training.action"></s:hidden>
                                            <s:hidden name="training.trainingId"></s:hidden>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama Training</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="trainingName" name="training.trainingName" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" disabled="true"/>
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
                                                      headerKey="" headerValue="[Select One]" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" onchange="showAdd()" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Instansi </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="instansi" name="training.instansi" cssClass="form-control" cssStyle="margin-top: -25px;margin-left: 20px;" disabled="true"/>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <label>Tanggal Mulai </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="tanggalStart" name="training.stTanggalStart" cssClass="form-control"
                                                         required="false"  cssStyle="margin-top: -25px;margin-left: 20px;" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Tanggal Selesai</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="tanggalEnd" name="training.stTanggalEnd" cssClass="form-control"
                                                         required="false"  cssStyle="margin-top: -25px;margin-left: 20px;" disabled="true" />
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
                                <table id="showdata" width="60%">
                                    <tr>
                                        <td align="center">
                                            <br>
                                            <label>Daftar Nama Training</label>
                                            <br>
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="420" width="600" autoOpen="false"
                                                       title="Approve">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>
                                            <s:set name="listOfResult" value="#session.listOfResultPerson" scope="request" />
                                            <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_training.action" export="false" id="row" pagesize="10" style="font-size:10">

                                                <display:column media="html" title="Approve">
                                                    <s:if test="%{#attr.row.approveSdm}">
                                                        <s:url var="urlApprove" namespace="/training" action="initApproveSdm_training" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.trainingPersonId"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlApprove}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>


                                                <display:column property="trainingPersonId" sortable="true" title="T. Person Id" style=""/>
                                                <display:column property="personId" sortable="true" title="NIP" />
                                                <display:column property="personName" sortable="true" title="Nama"/>
                                                <display:column property="iconApproveAtasan" sortable="true" title="Approve Atasan"  />
                                                <display:column property="iconApproveSdm" sortable="true" title="Approve SDM"  />
                                                <display:column property="iconApproveKepala" sortable="true" title="Approve Kabid/GM"  />
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
            document.getElementById("flagSuratJaminan").value = "Y"
        } else {
//            document.getElementById('btnAdd').style.display = 'none';
            document.getElementById('checkbox').checked = false;
            document.getElementById("flagSuratJaminan").value = "N"
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

</script>


