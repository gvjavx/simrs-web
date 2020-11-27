<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/PayrollSkalaGajiAction.js"/>'></script>--%>
    <script type="text/javascript">
    function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
//            window.location.reload(true);
            document.dokterForm.action = "search_dokter.action";
            document.dokterForm.submit();
        };

        $.subscribe('beforeProcessSave2', function (event, data) {
            // var idDokter = document.getElementById("idDokter1").value;
            var namaDokter = document.getElementById("namaDokter1").value;
            var kuota = document.getElementById("kuota1").value;
            var kodeDpjp = document.getElementById("kodeDpjp1").value;
            var pelayanan = document.getElementById("idPelayanan1").value;
            // var position = document.getElementById("positionId1").value;

            var flagcall = document.getElementById("flagCall1").value;
            var flagtele = document.getElementById("flagTele1").value;
            var kuotatele = document.getElementById("kuotaTele1").value;
            var kuotaonsite = document.getElementById("kuotaOnSite1").value;
            var sip = document.getElementById("sip1").value;
            // var kuotabpjs = document.getElementById("kuotaBpjs1").value;


            if ( namaDokter != '' && pelayanan != '' && kuota != '' && kodeDpjp != ''  && flagcall != ''
                && flagtele != '' && kuotatele != '' && kuotaonsite != '' && sip != '' ) {
                if (confirm('Do you want to update this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if (namaDokter == '') {
                    msg += 'Field <strong>ID Dokter </strong> is required.' + '<br/>';
                }
                if (pelayanan == '') {
                    msg += 'Field <strong>pelayanan </strong> is required.' + '<br/>';
                }
                if (kuota == '') {
                    msg += 'Field <strong>kuota </strong> is required.' + '<br/>';
                }
                if (kodeDpjp == '') {
                    msg += 'Field <strong>kode Dpjp </strong> is required.' + '<br/>';
                }
                if (flagcall == '') {
                    msg += 'Field <strong>flag call </strong> is required.' + '<br/>';
                }
                if (kuotatele == '') {
                    msg += 'Field <strong>kuota tele </strong> is required.' + '<br/>';
                }
                if (kuotaonsite == '') {
                    msg += 'Field <strong>kuota onsite </strong> is required.' + '<br/>';
                }
                if (sip == '') {
                    msg += 'Field <strong>surat ujin praktek </strong> is required.' + '<br/>';
                }
                // if (kuotabpjs == '') {
                //     msg += 'Field <strong>kode Dpjp </strong> is required.' + '<br/>';
                // }




                document.getElementById('errorValidationMessage2').innerHTML = msg;

                $.publish('showErrorValidationDialog1');
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
            <s:form id="editDokterForm" method="post" theme="simple" namespace="/dokter"
                    action="saveEdit_dokter" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Edit Dokter</legend>


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
                            <label class="control-label"><small>ID Dokter :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="idDokter1" name="dokter.idDokter" required="true" disabled="true" cssClass="form-control"/>
                                <s:hidden  name="dokter.idDokter" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Dokter :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="namaDokter1" name="dokter.namaDokter" cssStyle="margin-top: 5px"
                                             required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Pelayanan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboPelayanan" namespace="/dokter" name="initComboPelayanan_dokter"/>
                                <s:select list="#initComboPelayanan.listOfComboPelayanan" id="idPelayanan1"
                                          name="dokter.idPelayanan"
                                          listKey="idPelayanan" listValue="namaPelayanan"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kuota :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kuota1" name="dokter.kuota" required="true" cssStyle="margin-top: 5px" disabled="false" type="number" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kode DPJP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kodeDpjp1" name="dokter.kodeDpjp"
                                             required="true" disabled="false" type="number" cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Flag Call :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'N':'Non-Active'}" id="flagCall1" name="dokter.flagCall"
                                          headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="margin-top: 5px" />
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Flag Tele :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'N':'Non-Active'}" id="flagTele1" name="dokter.flagTele"
                                          headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>

                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kuota Telemedik :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kuotaTele1" name="dokter.kuotaTele" required="true"
                                             disabled="false" type="number" cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kuota On Site :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kuotaOnSite1" name="dokter.kuotaOnSite" required="true"
                                             disabled="false" type="number" cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Surat Ijin Praktek :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="sip1" name="dokter.sip" required="true"
                                             disabled="false" type="number" cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Kuota Bpjs :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield id="kuotaBpjs1" name="dokter.kuotaBpjs" required="true"--%>
                                             <%--disabled="false" type="number" cssClass="form-control" cssStyle="margin-top: 5px"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editDokterForm" id="save2" name="save"
                                   onBeforeTopics="beforeProcessSave2" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
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
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Update Data ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="250" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been updated successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog1" modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessage2"></div></center>
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


