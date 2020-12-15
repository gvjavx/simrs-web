<%--
  Created by IntelliJ IDEA.
  User: reza
  Date: 05/12/19
  Time: 10.21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<%@ taglib prefix="S" uri="/struts-tags" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
//            window.location.reload(true);
            document.ruanganForm.action = "search_ruangan.action";
            document.ruanganForm.submit();
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var idRuangan = document.getElementById("id_ruangan3").value;
//            var nameRuangan = document.getElementById("nama_ruangan3").value;
//            var noRuangan = document.getElementById("no_ruangan3").value;
//            var statusRuangan = document.getElementById("statusRuangan3").value;
//            var kelasRuangan = document.getElementById("idKelasRuangan3").value;
//            var tarifRuangan = document.getElementById("tarif3").value;
//            var kuota = document.getElementById("kuota3").value;
//            var keterangan = document.getElementById("keterangan3").value;
//            var sisaKuota = document.getElementById("sisaKuota3").value

            console.log(idRuangan);
            console.log("Test");

            if (idRuangan != '') {

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

                if (idRuangan == '') {
                    msg += 'Field <strong>Id Ruangan</strong> is required.' + '<br/>';
                }
//                if (noRuangan == '') {
//                    msg += 'Field <strong>No. Ruangan</strong> is required.' + '<br/>';
//                }
//                if (statusRuangan == '') {
//                    msg += 'Field <strong>Status Ruangan</strong> is required.' + '<br/>';
//                }
//                if (kelasRuangan == '') {
//                    msg += 'Field <strong>Kelas Ruangan</strong> is required.' + '<br/>';
//                }
//                if (tarifRuangan == '') {
//                    msg += 'Field <strong>Tarif Ruangan</strong> is required.' + '<br/>';
//                }
//                if (kuotan == '') {
//                    msg += 'Field <strong>Kuota</strong> is required.' + '<br/>';
//                }
//                if (sisaKuota1 == '') {
//                    msg += 'Field <strong>Sisa Kuota</strong> is required.' + '<br/>';
//                }

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

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="deleteRuanganForm" method="post" theme="simple" namespace="/ruangan" action="saveDelete_ruangan" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>


                <legend align="left">Delete Ruangan</legend>


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
                            <label class="control-label"><small>ID Ruangan</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield cssStyle="margin-top: 7px"  id="id_ruangan3" name="ruangan.idRuangan"
                                             required="false" disabled="true" readonly="false" cssClass="form-control"/>
                                <s:hidden id="id_ruangan1" name="ruangan.idRuangan" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Nama Ruangan</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nama_ruangan3" cssStyle="margin-top: 7px"
                                             name="ruangan.namaRuangan" required="false"
                                             readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>No. Ruangan</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="no_ruangan3" name="ruangan.noRuangan"
                                             required="false" readonly="true"
                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
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
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId3" name="ruangan.branchId" disabled="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kelas :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboKelas" namespace="/ruangan" name="initComboKelasRuangan_ruangan"/>
                                <s:select list="#initComboKelas.listOfComboKelasRuangan" id="idKelasRuangan3" name="ruangan.idKelasRuangan" disabled="true"
                                          listKey="idKelasRuangan" listValue="namaKelasRuangan" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label">--%>
                                <%--<small>Status Ruangan :</small>--%>
                            <%--</label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:select list="#{'N':'Tidak Tersedia'}" id="statusRuangan3" name="ruangan.statusRuangan" disabled="true"--%>
                                          <%--headerKey="Y" headerValue="Tersedia" cssClass="form-control" />--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Tarif Ruangan</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tarif_ruangan3" cssStyle="margin-top: 7px"
                                             name="ruangan.tarif" required="false"
                                             readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Kuota</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield cssStyle="margin-top: 7px"  id="kuota3" name="ruangan.kuota" required="false" type="number" readonly="true" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Sisa Kuota</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield cssStyle="margin-top: 7px"  id="sisaKuota3" name="ruangan.sisaKuota" required="false" type="number" readonly="true" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Keterangan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="keterangan3" name="ruangan.keterangan"
                                            required="false" readonly="true"
                                            cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="deleteRuanganForm" id="delete" name="delete"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Delete
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
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
                                                                    //$(this).dialog('close');
                                                                      callSearch();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been deleted successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close');  window.location.reload(true)}
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
</body>
</html>

