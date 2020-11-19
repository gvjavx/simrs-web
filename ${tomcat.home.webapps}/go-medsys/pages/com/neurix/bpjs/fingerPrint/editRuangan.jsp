<%--
  Created by IntelliJ IDEA.
  User: reza
  Date: 05/12/19
  Time: 10.20
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
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var idRuangan = document.getElementById("id_ruangan2").value;
            var nameRuangan = document.getElementById("nama_ruangan2").value;
            var noRuangan = document.getElementById("no_ruangan2").value;
            var statusRuangan = document.getElementById("status_ruangan2").value;
            var kelasRuangan = document.getElementById("kelas_ruangan2").value;
            var keterangan = document.getElementById("keterangan2").value;
            var tarifRuangan = document.getElementById("tarif_ruangan2").value;

            if (idRuangan != '' && nameRuangan != '' && noRuangan != '' && statusRuangan != '' && kelasRuangan != '' && keterangan != ''
                && tarifRuangan != '') {
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
                    msg += 'Field <strong>ID Ruangan</strong> is required.' + '<br/>';
                }
                if (nameRuangan == '') {
                    msg += 'Field <strong>Nama Ruangan</strong> is required.' + '<br/>';
                }
                if (noRuangan == '') {
                    msg += 'Field <strong>No. Ruangan</strong> is required.' + '<br/>';
                }
                if (statusRuangan == '') {
                    msg += 'Field <strong>Status Ruangan</strong> is required.' + '<br/>';
                }
                if (kelasRuangan == '') {
                    msg += 'Field <strong>Kelas Ruangan</strong> is required.' + '<br/>';
                }
                if (tarifRuangan == '') {
                    msg += 'Field <strong>Tarif Ruangan</strong> is required.' + '<br/>';
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
            document.getElementById('errorMessage').innerHTML = "Status = "
                + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
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
            <s:form id="editRuanganForm" method="post" theme="simple" namespace="/ruangan" action="saveEdit_ruangan"
                    cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Ruangan</legend>


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
                            <label class="control-label"><small>ID ruangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="id_ruangan2" name="ruangan.idRuangan" readonly="true"
                                             required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>


                    <%--<S:hidden id="" name="ruangan.idRuangan">--%>

                    <%--</S:hidden>--%>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Nama ruangan : </small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nama_ruangan2" cssStyle="margin-top: 5px"
                                             name="ruangan.namaRuangan" required="false"
                                             readonly="false" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>No. Ruangan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="no_ruangan2" name="ruangan.noRuangan"
                                             required="false" readonly="false"
                                             cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Status Ruangan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'Y':'Tersedia','N':'Tidak Tersedia'}" cssStyle="margin-top: 5px"
                                          id="status_ruangan2" name="ruangan.statusRuangan"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Kelas Ruangan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboKelas" namespace="/checkupdetail"
                                          name="getListComboKelasRuangan_checkupdetail"/>
                                <s:select cssStyle="margin-top: 7px"
                                          list="#initComboKelas.listOfKelasRuangan" id="kelas_ruangan2"
                                          name="ruangan.idKelasRuangan"
                                          listKey="idKelasRuangan"
                                          listValue="namaKelasRuangan"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control select2"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Keterangan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="keterangan2" name="ruangan.keterangan"
                                            required="false" readonly="false"
                                            cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Tarif Ruangan</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tarif_ruangan2" cssStyle="margin-top: 7px"
                                             name="ruangan.tarif" required="false"
                                             readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editRuanganForm" id="edit" name="edit"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default"
                                style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
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
</body>
</html>
