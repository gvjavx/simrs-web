<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
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


    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("companyId").value != '' &&
                    document.getElementById("companyName").value != '' &&
                    document.getElementById("address").value != '' &&
                    document.getElementById("npwp").value != '') {

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
                if (document.getElementById("companyId").value == '') {
                    msg = 'Field <strong>Company Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("companyName").value == '') {
                    msg = msg +  'Field <strong>Company Name</strong> is required.' + '<br/>';
                }

                if (document.getElementById("address").value == '') {
                    msg = msg +  'Field <strong>Address</strong> is required.' + '<br/>';
                }

                if (document.getElementById("npwp").value == '') {
                    msg = msg +  'Field <strong>NPWP</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialog', function (event, data) {
            //alert('ok');
            //$.publish('showInfoDialog');
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

        function onChangeButton() {
            document.getElementById('firstButton').style.display='none';
            document.getElementById('saveButton').style.visibility='visible';
            $('#companyName').prop('readonly', false);
            $('#address').prop('readonly', false);
            $('#npwp').prop('readonly', false);
            $('#serviceOnOff').prop('readonly', false);
            $('#mailServer').prop('readonly', false);
            $('#portServer').prop('readonly', false);
            $('#userNameServer').prop('readonly', false);
            $('#passwordServer').prop('readonly', false);
            $('#defaultMailSender').prop('readonly', false);
            $('#defaultMailSubject').prop('readonly', false);
            $('#defaultMailContent').prop('readonly', false);
            $('#biayaJabatanPersentase').prop('readonly', false);
            $('#iuranPerusahaanJkmJkk').prop('readonly', false);
            $('#remainderJubileum').prop('readonly', false);
            $('#remainderPensiun').prop('readonly', false);
            $('#kursDolar').prop('readonly', false);
            $('#stMinimumLuasan').prop('readonly', false);

            $('#asumsiThr').prop('readonly', false);
            $('#asumsiPendidikan').prop('readonly', false);
            $('#asumsiJasprod').prop('readonly', false);
            $('#periodeGaji').prop('disabled', false);
            $('#paramDapen').prop('readonly', false);

            $('#maxBpjsTk').prop('readonly', false);
            $('#maxBpjsPensiun').prop('readonly', false);
            $('#maxBpjsKesehatan').prop('readonly', false);
        }

        function onLoadPage() {

            $('#companyName').prop('readonly', true);
            $('#address').prop('readonly', true);
            $('#npwp').prop('readonly', true);
            $('#serviceOnOff').prop('readonly', true);
            $('#mailServer').prop('readonly', true);
            $('#portServer').prop('readonly', true);
            $('#userNameServer').prop('readonly', true);
            $('#passwordServer').prop('readonly', true);
            $('#defaultMailSender').prop('readonly', true);
            $('#defaultMailSubject').prop('readonly', true);
            $('#defaultMailContent').prop('readonly', true);
            $('#stMinimumLuasan').prop('readonly', true);
            $('#biayaJabatanPersentase').prop('readonly', true);
            $('#iuranPerusahaanJkmJkk').prop('readonly', true);
            $('#remainderJubileum').prop('readonly', true);
            $('#remainderPensiun').prop('readonly', true);
            $('#kursDolar').prop('readonly', true);
            $('#periodeGaji').prop('disabled', true);
            $('#paramDapen').prop('readonly', true);

            $('#asumsiThr').prop('readonly', true);
            $('#asumsiPendidikan').prop('readonly', true);
            $('#asumsiJasprod').prop('readonly', true);

            $('#maxBpjsTk').prop('readonly', true);
            $('#maxBpjsPensiun').prop('readonly', true);
            $('#maxBpjsKesehatan').prop('readonly', true);

            document.getElementById('firstButton').style.visibility='visible';
            document.getElementById('saveButton').style.visibility='hidden';
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
            Company Information
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">

                    </div>


                    <%--<form role="form" method="post" id="comapnyForm" action="save_company.action">--%>
                    <s:form id="companyForm" method="post" namespace="/admin/company" theme="simple" action="save_company" cssClass="well">
                        <div class="box-body">
                                <div class="container" style="margin: 0 auto; width: 80%">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="companyId">Company Id :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="companyId" cssClass="form-control" name="company.companyId" required="true" readonly="true"/>
                                            <%--<input type="email" class="form-control" id="email" placeholder="Enter email">--%>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="companyName">Company Name :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="companyName" name="company.companyName" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.address">Address :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="address" name="company.address" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.npwp">NPWP :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="npwp" name="company.npwp" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.serviceOnOff">Service On Off :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="serviceOnOff" name="company.serviceOnOff" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.mailServer">Mail Server :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="mailServer" name="company.mailServer" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.portServer">Port Server :</label>
                                        <div class="col-sm-8">
                                            <s:textfield cssClass="form-control" id="portServer" name="company.portServer" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.userNameServer">Username Server :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="userNameServer" name="company.userNameServer" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.passwordServer">Password Server :</label>
                                        <div class="col-sm-8">
                                            <s:password id="passwordServer" name="company.passwordServer" required="true" showPassword="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.periodeGaji">Periode Gaji :</label>
                                        <div class="col-sm-8">
                                            <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                            <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periodeGaji"
                                                      name="company.periodeGaji" required="true" headerKey=""
                                                      headerValue="[Select one]"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.paramDapen">Parameter Dapen :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="paramDapen" name="company.paramDapen" type="number" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSubject">Default Mail Subject :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="defaultMailSubject" name="company.defaultEmailSubject" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSender">Default Mail Sender :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="defaultMailSender" name="company.defaultEmailSender" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailContent">Default Mail Content :</label>
                                        <div class="col-sm-8">
                                            <s:textarea cols="10" rows="5" id="defaultMailContent" name="company.defaultEmailContent" required="true" cssClass="
                                            form-control"/>
                                        </div>
                                    </div>

                                    <%--<div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSender">Persentase Biaya Jabatan:</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="biayaJabatanPersentase" name="company.biayaJabatanPersentase" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSender">Iuran Persusahaan Jkm Jkk:</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="iuranPerusahaanJkmJkk" name="company.iuranPerusahaanJkmJkk" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSender">Remainder Yubilium :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="remainderJubileum" name="company.remainderJubileum" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSender">Reminder Pensiun :</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="remainderPensiun" name="company.remainderPensiun" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="company.defaultEmailSender">Kurs Dolar: </label>
                                        <div class="col-sm-8">
                                            <s:textfield id="kursDolar" style="margin-bottom: 30px;" name="company.kursDolar" cssClass="form-control"
                                                         required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >Asumsi Persentase Thr:</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="asumsiThr" name="company.payrollThrPersentase" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >Asumsi Persentase Pendidikan:</label>
                                        <div class="col-sm-8">
                                            <s:textfield id="asumsiPendidikan" name="company.payrollPendidikanPersentase" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3">Asumsi Kali Jasprod: </label>
                                        <div class="col-sm-8">
                                            <s:textfield style="margin-bottom: 30px;" id="asumsiJasprod" name="company.payrollJasprodKali" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3">Max BPJS Ketenagakerjaan: </label>
                                        <div class="col-sm-8">
                                            <s:textfield id="maxBpjsTk" name="company.maxBpjsTk" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3">Max BPJS Pensiun: </label>
                                        <div class="col-sm-8">
                                            <s:textfield id="maxBpjsPensiun" name="company.maxBpjsPensiun" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3">Max BPJS Kesehatan: </label>
                                        <div class="col-sm-8">
                                            <s:textfield id="maxBpjsKesehatan" name="company.maxBpjsKesehatan" cssClass="form-control" required="true"/>
                                        </div>
                                    </div>--%>

                                    <script>onLoadPage()</script>

                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-10">
                                            <br>
                                            <button id="firstButton" type="button" class="btn btn-primary" onclick="onChangeButton();">
                                                <i class="fa fa-edit"></i> Edit
                                            </button>

                                            <div id="saveButton" style="visibility: hidden;">
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="companyForm" id="save" name="save"
                                                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                    <i class="fa fa-check"></i>
                                                    Save
                                                </sj:submit>
                                            </div>
                                            
                                        </div>
                                    </div>


                                    <table id="saveButtona" style="visibility: hidden">
                                        <tr>
                                            <div id="crud">

                                                <td>
                                                    <table>
                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Saving ...">
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
                                                                                $('#info_dialog').dialog('close');
                                                                                location.reload();
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

                            </div>
                        </s:form>

                </div>
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


