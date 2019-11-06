<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style type="text/css">
        #tgl1,#tgl2{z-index: 2000!important}
    </style>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var nip = document.getElementById("nip").value;



            if (nip != '' ) {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }


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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/ijinKeluar" action="saveEdit_ijinKeluar" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Ijin Keluar</legend>


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
                            <div style="display: none">
                                <label class="control-label"><small>Ijin Keluar ID :</small></label>
                            </div>
                        </td>
                        <td>
                            <table>
                                <div style="display: none">
                                    <s:textfield  id="ijinKeluarId" name="ijinKeluar.ijinKeluarId" required="true" readonly="true" cssClass="form-control"/>
                                        <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>
                                </div>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nip" name="ijinKeluar.nip" required="true" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nama" name="ijinKeluar.nama" required="false" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="unit" name="ijinKeluar.unitId" required="false" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="divisi" name="ijinKeluar.divisiId" required="false" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="posisi" name="ijinKeluar.posisiId" required="false" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Golongan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId12" name="ijinKeluar.golonganId"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>
                                <s:select list="#comboCuti.listComboCuti" id="cuti" name="ijinKeluar.cutiId" readonly="true" disabled="true"
                                          listKey="cutiId" listValue="cutiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Sisa Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="sisaCuti" name="ijinKeluar.sisaCuti" required="false" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Awal Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl2" name="ijinKeluar.tanggalDari" cssClass="form-control pull-right"
                                                 required="false"  cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Selesai Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl1" name="ijinKeluar.tanggalSelesai" cssClass="form-control pull-right"
                                                 required="false"  cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Lama Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td width="60px">
                                        <s:textfield  id="lamaCuti" name="ijinKeluar.lamaHariCuti" required="false" readonly="true" cssClass="form-control"/>
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        hari
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

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

<script>
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    var today = dd+'/'+mm+'/'+yyyy;
    //$('#tanggalAwalCuti').val(today);
    $(document).ready(function() {

        function callSearch2() {
            //alert('okok');
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        };
        $('#tgl1').datepicker({
            dateFormat: 'dd/mm/yy',
        });
        $('#tgl2').datepicker({
            dateFormat: 'dd/mm/yy',
        });
    });

    $('#tgl1').on('change',function(){
        var startdate = $('#tgl2').datepicker('getDate');
        var enddate = $('#tgl1').datepicker('getDate');
        if (startdate<enddate) {
            var days   = (enddate - startdate)/1000/60/60/24;
            $('#lamaCuti').val(days);
        }
        else {
            alert ("tanggal selesai kurang dari tanggal mulai , mohon ulangi ");
            $('#tgl1').val("");
        }
    });
    $('#tgl2').on('change',function(){
        var hariini = new Date();
        var startdate = $('#tgl2').datepicker('getDate');
        var enddate = $('#tgl1').datepicker('getDate');
        if (startdate<hariini){
            alert ("tanggal mulai kurang dari tanggal sekarang , mohon ulangi ");
        }
        else if(startdate<enddate) {
            var days   = (enddate - startdate)/1000/60/60/24;
            $('#lamaCuti').val(days);
        }
        else {
            alert ("tanggal selesai kurang dari tanggal mulai , mohon ulangi ");
            $('#tgl2').val("");
        }
    });
</script>
