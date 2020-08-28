<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var vendorId = document.getElementById("vendorIdEdit").value;
            var vendorName    = document.getElementById("vendorNameEdit").value;
            var tipeVendor = document.getElementById("tipeVendorEdit").value;
            var noRekening = document.getElementById("noRekeningEdit").value;
            var alamat = document.getElementById("alamatEdit").value;
            var npwp = document.getElementById("npwpEdit").value;
            var email = document.getElementById("emailEdit").value;
            var notelp = document.getElementById("noTelpEdit").value;
            if (vendorName != ''&&alamat!=''&&npwp!=''&&email!=''&&notelp!=''&&vendorId!=''&&tipeVendor!=''&&noRekening!='') {
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
                if (vendorId == '') {
                    msg += 'Field <strong>Vendor ID</strong> not found.' + '<br/>';
                }
                if (vendorName == '') {
                    msg += 'Field <strong>Nama Vendor</strong> is required.' + '<br/>';
                }
                if (alamat == '') {
                    msg += 'Field <strong>Alamat</strong> is required.' + '<br/>';
                }
                if (npwp == '') {
                    msg += 'Field <strong>NPWP</strong> is required.' + '<br/>';
                }
                if (email == '') {
                    msg += 'Field <strong>email</strong> is required.' + '<br/>';
                }
                if (notelp == '') {
                    msg += 'Field <strong>No. Telp.</strong> is required.' + '<br/>';
                }
                if (tipeVendor == '') {
                    msg += 'Field <strong>Tipe Vendor</strong> is required.' + '<br/>';
                }
                if (noRekening == '') {
                    msg += 'Field <strong>No. Rekening</strong> is required.' + '<br/>';
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

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/masterVendor" action="saveEdit_masterVendor" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Edit Vendor</legend>
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
                            <label class="control-label"><small>Vendor Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="vendorIdEdit" name="masterVendor.nomorMaster" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Vendor :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'bpjs':'Perusahaan yg berelasi/kerjasama/mitra','lahan':'Penyewa Lahan','lain':'Lain - Lain'}" id="tipeVendorEdit" name="masterVendor.tipeVendor" disabled="true"
                                          headerKey="umum" headerValue="Rekanan/Swasta/Vendor/Asuransi" cssClass="form-control" />
                            </table>
                        </td>
                        <s:hidden name="masterVendor.tipeVendor" />
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Vendor :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="vendorNameEdit" name="masterVendor.nama" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Alamat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="2" id="alamatEdit" name="masterVendor.alamat" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NPWP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="npwpEdit" name="masterVendor.npwp" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>No. Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" id="noRekeningEdit" name="masterVendor.noRekening" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Email :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="emailEdit" name="masterVendor.email" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>No. Telp. :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" id="noTelpEdit" name="masterVendor.noTelp" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Vendor Obat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'N':'N'}" id="vendorObatEdit" name="masterVendor.vendorObat" disabled="true"
                                          headerKey="Y" headerValue="Y" cssClass="form-control" />
                                <s:hidden name="masterVendor.vendorObat"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
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
                                                   title="Save Data ...">
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