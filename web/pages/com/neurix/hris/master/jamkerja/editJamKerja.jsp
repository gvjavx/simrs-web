<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        $.subscribe('beforeProcessSave', function (event, data) {
            var branchId = document.getElementById("unitId2").value;
            var hariKerja = document.getElementById("hariKerja2").value;
            var jamAwal = document.getElementById("jamAwal2").value;
            var jamAkhir = document.getElementById("jamAkhir2").value;
            var istirahatAwal = document.getElementById("istirahatAwal2").value;
            var istirahatAkhir = document.getElementById("istirahatAkhir2").value;

            if (branchId != '' && hariKerja != '' && jamAwal != '' && jamAkhir != '' && istirahatAwal != '' && istirahatAkhir != '') {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            }else {
                event.originalEvent.options.submit = false;

                var msg = "";
                if (branchId =='') {
                    msg = 'Field <strong>Unit</strong> is required.' + '<br/>';
                }
                if (hariKerja =='') {
                    msg = 'Field <strong>Hari Kerja</strong> is required.' + '<br/>';
                }
                if (jamAwal =='') {
                    msg = 'Field <strong>Jam Kerja Awal</strong> is required.' + '<br/>';
                }
                if (jamAkhir =='') {
                    msg = 'Field <strong>Jam Kerja Akhir</strong> is required.' + '<br/>';
                }
                if (istirahatAwal =='') {
                    msg = 'Field <strong>Jam istirahat Awal</strong> is required.' + '<br/>';
                }
                if (istirahatAkhir.value =='') {
                    msg = 'Field <strong>Jam istirahat Akhir</strong> is required.' + '<br/>';
                }


                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');
            }

        })

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
            //window.location.reload(true);
        };

        function callSearch() {
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            window.location.reload(true);
        };

    </script>

</head>

<s:form id="editForm" method="post" theme="simple" namespace="/jamkerja" action="saveEdit_jamkerja" cssClass="well form-horizontal">

    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>



    <legend align="left">Edit Jam Kerja</legend>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>

    <div class="form-group">
        <label class="control-label col-sm-3">Jam Kerja Id :</label>
        <div class="col-sm-7">
            <s:textfield cssClass="form-control" id="jamKerjaId" name="jamKerja.jamKerjaId" readonly="true" required="true" />
            <s:hidden id="createdDate" name="jamKerja.createdDate" />
            <s:hidden id="createdWho" name="jamKerja.createdWho" />
                <%--<input type="text" class="form-control" name="kodeAlat" id="alat.kodeAlat" readonly >--%>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tipe Pegawai:</label>
        <div class="col-sm-7">
            <s:action id="comboTipePegawai" namespace="/jamkerja" name="initComboTipePegawai_jamkerja"/>
            <s:select list="#comboTipePegawai.listOfComboTipePegawai" id="tipePegawaiId2" name="jamKerja.tipePegawaiId"
                      listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]"
                      cssClass="form-control"/>
        </div>
    </div>

    <%--<div class="form-group">
        <label class="control-label col-sm-3">Status Giling:</label>
        <div class="col-sm-7">
            <s:select cssClass="form-control" list="#{'DMG':'DMG', 'LMG':'LMG'}" id="statusGiling" name="jamKerja.statusGiling"
                      headerKey="" headerValue="[Select one]"/>
        </div>
    </div>--%>
    <div class="form-group">
        <label class="control-label col-sm-3">Unit:</label>
        <div class="col-sm-7">
            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="unitId2" name="jamKerja.branchId" required="true"
                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Hari Kerja :</label>
        <div class="col-sm-7" align="left">
            <s:select cssClass="form-control" list="#{2:'Senin', 3:'Selasa', 4:'Rabu', 5:'Kamis', 6:'Jumat', 7:'Sabtu', 1:'Minggu'}" id="hariKerja2" name="jamKerja.hariKerja"
                      headerKey="" headerValue="[Select one]"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Jam Kerja :</label>
        <div class="col-sm-7">
            <div class="input-group date">
                <div class="input-group-addon">
                    <i class="fa fa-clock-o"></i>
                </div>
                <s:textfield id="jamAwal2" name="jamKerja.jamAwalKerja" size="8" cssClass="form-control pull-right"
                             required="false" cssStyle=""/>
                <div class="input-group-addon">
                    s/d
                </div>
                <div class="input-group-addon">
                    <i class="fa fa-clock-o"></i>
                </div>
                <s:textfield id="jamAkhir2" name="jamKerja.jamAkhirKerja" size="12" cssClass="form-control pull-right"
                             required="false" cssStyle=""/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Jam Istirahat :</label>
        <div class="col-sm-7">
            <div class="input-group date">
                <div class="input-group-addon">
                    <i class="fa fa-clock-o"></i>
                </div>
                <s:textfield id="istirahatAwal2" name="jamKerja.istirahatAwal" size="8" cssClass="form-control pull-right"
                             required="false" cssStyle=""/>
                <div class="input-group-addon">
                    s/d
                </div>
                <div class="input-group-addon">
                    <i class="fa fa-clock-o"></i>
                </div>
                <s:textfield id="istirahatAkhir2" name="jamKerja.istirahatAkhir" size="12" cssClass="form-control pull-right"
                             required="false" cssStyle=""/>
            </div>
        </div>
    </div>

    <br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editForm" id="save" name="save"
                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                <i class="icon-ok-sign icon-white"></i>
                Save
            </sj:submit>
            <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                <i class="icon-remove-circle"/> Cancel
            </button>
        </div>
    </div>


    <div id="actions" class="form-actions">
        <table>
            <tr>
                <div id="crud">
                    <td>
                        <table>
                            <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                       <%--resizable="false"--%>
                                       <%--height="350" width="600" autoOpen="false" title="Saving ...">--%>
                                <%--Please don't close this window, server is processing your request ...--%>
                                <%--</br>--%>
                                <%--</br>--%>
                                <%--</br>--%>
                                <%--<center>--%>
                                    <%--<img border="0" src="<s:url value="/pages/images/loading4.gif"/>" name="image_indicator_write">--%>
                                <%--</center>--%>
                            <%--</sj:dialog>--%>
                                <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                           closeTopics="closeDialog" modal="true"
                                           resizable="false"
                                           height="250" width="700" autoOpen="false"
                                           title="Search Data ...">
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
</body>
</html>
<script>
    $(document).ready(function(){
        $('#jamAwal2').timepicker();
        $('#jamAkhir2').timepicker();
        $('#istirahatAwal2').timepicker();
        $('#istirahatAkhir2').timepicker();
    })
</script>

