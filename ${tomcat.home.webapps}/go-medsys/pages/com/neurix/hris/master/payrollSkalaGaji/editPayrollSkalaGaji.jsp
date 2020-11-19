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
            var golonganId = document.getElementById("golonganId1").value;
            var nilai = document.getElementById("nilai1").value;
            var santunanKhusus = document.getElementById("santunanKhusus1").value;
            var rumah = document.getElementById("rumah1").value;
            var listrik = document.getElementById("listrik1").value;
            var air = document.getElementById("air1").value;
            var bbm = document.getElementById("bbm1").value;

            if (golonganId != ''&& nilai != '' && santunanKhusus != '' && rumah != '' && listrik != '' && air != '' && bbm != '' ) {
                if(isNaN(nilai) == false){
                    /*PayrollSkalaGajiAction.cekIfExistDwr(golonganId, function(data){
                     if(data != 'exist'){*/
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }
                    /*}else{
                     document.getElementById('errorValidationMessage').innerHTML = 'Data Sudah Ada';
                     $.publish('showErrorValidationDialog');
                     event.originalEvent.options.submit = false;
                     }
                     });*/
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (isNaN(nilai)) {
                        msg += 'Field <strong>Gaji Level</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(santunanKhusus)) {
                        msg += 'Field <strong>santunan Khusus</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(rumah)) {
                        msg += 'Field <strong>Tunj rumah</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(listrik)) {
                        msg += 'Field <strong>Tunj listrik</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(air)) {
                        msg += 'Field <strong>Tunj air</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(bbm)) {
                        msg += 'Field <strong>Tunj bbm</strong> Harus angka tanpa koma.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');
                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if (golonganId == '') {
                    msg += 'Field <strong>Level </strong> is required.' + '<br/>';
                }

                if (nilai == '') {
                    msg += 'Field <strong>Gaji Level</strong> is required.' + '<br/>';
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
            <s:form id="formEdit" method="post" theme="simple" namespace="/payrollSkalaGaji" action="saveEdit_payrollSkalaGaji" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Skala Gaji</legend>


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
                            <label class="control-label"><small>Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="skalaGajiId1" readonly="true" name="payrollSkalaGaji.skalaGajiId" required="true"  cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Level :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#initComboTipe.listComboGolongan" id="golonganId1" name="payrollSkalaGaji.golonganId" disabled="true"
                                          listKey="golonganId" listValue="stLevel" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                <%--<s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>--%>
                                <%--<s:select list="#initComboTipe.listComboGolongan" id="golonganId1" name="payrollSkalaGaji.golonganId"--%>
                                          <%--listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                <s:textfield type="number" min="0" id="golonganId12" name="payrollSkalaGaji.golonganId" required="true" cssClass="form-control" cssStyle="display: none"/>

                            </table>
                        </td>
                    </tr>

                    <%--<tr>
                        <td>
                            <label class="control-label"><small>Tahun :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'2018':'2018', '2019' : '2019', '2020' : '2020'}" id="tahun" name="payrollSkalaGaji.tahun"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Poin :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="point1" name="payrollSkalaGaji.point" required="true"  cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>--%>

                    <tr>
                        <td>
                            <label class="control-label"><small>Gaji Level :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="nilai1" name="payrollSkalaGaji.nilai" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Santunan Khusus :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="santunanKhusus1" name="payrollSkalaGaji.santunanKhusus" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tunj. Rumah :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="rumah1" name="payrollSkalaGaji.rumah" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tunj. Listrik :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="listrik1" name="payrollSkalaGaji.listrik" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tunj. Air :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="air1" name="payrollSkalaGaji.air" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tunj. Bbm :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="bbm1" name="payrollSkalaGaji.bbm" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tahun :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll12"
                                          name="payrollSkalaGaji.tahun" required="true" headerKey=""
                                          headerValue="[Select one]"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>No. SK :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="noSk1" name="payrollSkalaGaji.noSk" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="formEdit" id="save" name="save"
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
                                                   title="Searching ...">
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
