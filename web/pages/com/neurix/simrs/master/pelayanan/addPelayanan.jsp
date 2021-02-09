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
            window.location.reload(true);
        };

    $.subscribe('beforeProcessSaveAdd', function (event, data) {
        var namapelayanan = document.getElementById("namaPelayananAdd1").value;
        var unit = document.getElementById("unitAdd").value;
        var devisi = document.getElementById("devisiAdd").value;
        var tipepelayanan = document.getElementById("tipePelayananAdd").value;

        if (namapelayanan != '' && unit != '' && devisi != '' && tipepelayanan != '') {
            if (confirm('Do you want to save this record?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialogAdd');
            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        } else {
            event.originalEvent.options.submit = false;
            var msg = "";
            if (namapelayanan == '') {
                msg += 'Field <strong>Nama pelayanan </strong> is required.' + '<br/>';
            }
            if (unit == ''){
                msg += 'Field <strong>unit </strong> is required.' + '<br/>';
            }
            if (devisi == ''){
                msg += 'Field <strong>Nama devisi </strong> is required.' + '<br/>';
            }
            if (tipepelayanan == ''){
                msg += 'Field <strong>tipe pelayanan </strong> is required.' + '<br/>';
            }

            document.getElementById('errorValidationMessageAdd').innerHTML = msg;

            $.publish('showErrorValidationDialogAdd');
        }
    });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
            }
        );

        $.subscribe('errorDialogAdd', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessageAdd').innerHTML = "Status = "
                + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogAdd');
        }

        );

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center" >
            <s:form id="addPelayananForm" method="post" theme="simple"
                    namespace="/pelayanan" action="saveAdd_pelayanan" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Add Pelayanan</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <tr>
                        <td width="30%">
                            <label class="control-label"><small>Nama Pelayanan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="namaPelayananAdd1" name="pelayanan.namaPelayanan"
                                             required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="unitAdd" name="pelayanan.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control" cssStyle="margin-top: 5px" readonly="true" disabled="true"/>
                                <s:hidden id="branchId" name="pelayanan.branchId" />
                                    <%--<s:select list="#{'RS Gatoel'}" id="flag" name="pelayanan.flag"--%>
                                              <%--headerKey="RS Gatoel" headerValue="RS Gatoel" cssClass="form-control select2"  />--%>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboPosition" namespace="/pelayanan" name="initComboPosition_pelayanan"/>
                                <s:select list="#initComboPosition.listOfComboPositions" id="devisiAdd" name="pelayanan.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control" cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan',
                                                                'apotek' : 'Instalasi Farmasi RJ',
                                                                 'apotek_ri' : 'Instalasi Farmasi RI',
                                                                'rawat_inap' : 'Rawat Inap',
                                                                 'radiologi' : 'Radiologi', 'lab' : 'Laboratorium', 'gizi':'Instalasi Gizi'}"
                                          id="tipePelayananAdd" name="pelayanan.tipePelayanan"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" cssStyle="margin-top: 5px"
                                onchange="showKategoriPelayanan(this.value)"
                                />
                            </table>
                        </td>
                    </tr>

                    <tr style="display: none" id="form_kategori">
                        <td>
                            <label class="control-label"><small>Kategori :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'spesialis_nephrologi':'Poli Spesialis Nephrologi','spesilais_bedah_sarah':'Poli Spesialis Bedah Saraf',
                                'spesialis_btkv':'Poli Spesialis BTKV','klinik_nyeri':'Klinik Nyeri',
                                'spesialis_ortopedi':'Poli Spesialis Orthopaedi','spesialis_urologi':'Poli Spesialis Urologi',
                                'spesialis_jiwa':'Poli Spesialis Kedokteran Jiwa','spesialis_penyakit_dalam':'Poli Spesialis Penyakit Dalam',
                                'spesialis_jantung':'Poli Spesialis Jantung','spesialis_paru':'Poli Spesialis Paru',
                                'hemodialisa':'Poli Hemodialisa','fisioterapi':'Poli Fisioterapi','spesialis_onkologi':'Poli Spesialis Onkologi',
                                'rehab_medik':'Poli Spesialis Rehabilitasi Medis','dokter_umum':'Poli Umum','spesialis_gigi':'Poli Spesialis Bedah Gigi dan Mulut',
                                'spesialis_obstetri':'Poli Spesialis Kandungan','spesialis_neurologi':'Poli Spesialis Saraf',
                                'spesialis_tht':'Poli Spesialis THT','spesialis_anak':'Poli Spesialis Anak',
                                'spesialis_bedah':'Poli Spesialis Bedah Umum','spesialis_gigi':'Poli Gigi','spesialis_mata':'Poli Spesialis Mata','spesialis_kulit_kelamin':'Poli Spesialis Kulit dan Kelamin'}"
                                          id="kategoriPelayananAdd" name="pelayanan.kategoriPelayanan"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" cssStyle="margin-top: 5px"
                                />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Eksekutif :</small></label>
                        </td>
                        <td>
                            <table>
                                <input type="checkbox" id="isEksekutifAdd" class="checkEksekutif" onchange="cekEksekutif1()"  cssStyle="margin-top: 5px"/>
                                <s:hidden id="eksekutif" name="pelayanan.isEksekutif"  />
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addPelayananForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveAdd" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialogAdd" >
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
                                        <sj:dialog id="waiting_dialog" openTopics="showDialogAdd"
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
                                                                      callSearch2();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialogAdd" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close');}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessageAdd"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_validation_dialog_add" openTopics="showErrorValidationDialogAdd" modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog_add').dialog('close');}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessageAdd"></div></center>
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
<script>
    window.cekEksekutif1 = function () {
        if (document.getElementById("isEksekutifAdd").checked == true) {
            $("#eksekutif").val("Y");
        } else {
            $("#eksekutif").val("N");
        }
    }
    function showKategoriPelayanan(valueTipe){
        // console.log(valueTipe);
        if(valueTipe=='rawat_jalan'){
            $('#form_kategori').show();
        }else {
            $('#form_kategori').hide();
            $('#kategoriPelayananAdd').val('');

        }
    }

</script>

