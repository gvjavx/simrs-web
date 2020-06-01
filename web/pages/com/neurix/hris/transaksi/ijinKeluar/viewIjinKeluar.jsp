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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinAction.js"/>'></script>
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
            $('#view_dialog_menu_ijin_keluar').dialog('close');
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



                <legend align="left">View Ijin Tidak Masuk</legend>


                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >

                    <tr style="display: none">
                        <td>
                            <div>
                                <label class="control-label"><small>Ijin Keluar ID :</small></label>
                            </div>
                        </td>
                        <td>
                            <table>
                                <div>
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
                                <s:textfield  id="nipId" name="ijinKeluar.nip" required="true" readonly="true" cssClass="form-control"/>
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
                                <s:textfield  id="namaId1" name="ijinKeluar.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select disabled="true" cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="ijinKeluar.unitId" required="true" readonly="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId12" name="ijinKeluar.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId12" name="ijinKeluar.positionId" readonly="true" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId12" name="ijinKeluar.golonganId" disabled="true"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Ijin :</small></label>
                        </td>
                        <td>
                            <table>
                                    <s:action id="initComboIjin" namespace="/ijin" name="initComboIjin_ijin"/>
                                    <s:select disabled="true" list="#initComboIjin.listOfComboIjin" id="ijinId1" name="ijinKeluar.ijinId" readonly="true"
                                              listKey="ijinId" listValue="ijinName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>                                    <%--<s:hidden name="ijinKeluar.ijinKeluarId"/>--%>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Awal Ijin :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl2" name="ijinKeluar.stTanggalAwal" cssClass="form-control pull-right"
                                                 required="false"  cssStyle="" readonly="true" disabled="true"/>
                                </div>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Selesai Ijin :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl11" name="ijinKeluar.stTanggalAkhir" cssClass="form-control pull-right"
                                                 required="false"  cssStyle="" readonly="true" disabled="true"/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Lama Ijin :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td width="60px">
                                        <s:textfield  id="lamaCuti1" name="ijinKeluar.lamaIjin" required="false" readonly="true" cssClass="form-control"/>
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        hari
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <s:if test="isDispenLahir()">
                        <tr>
                            <td>
                                <label class="control-label"><small>Tanggal Melahirkan :</small></label>
                            </td>
                            <td>
                                <table>
                                    <div class="input-group date">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="tglMelahirkan1" name="ijinKeluar.stTglMelahirkan" cssClass="form-control pull-right"
                                                     required="false"  disabled="true" cssStyle=""/>
                                    </div>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label class="control-label"><small>Tanggal Selesai (Baru) :</small></label>
                            </td>
                            <td>
                                <table>
                                    <div class="input-group date">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="tgl1" name="ijinKeluar.tanggalAkhirBaru" readonly="true" disabled="true" cssClass="form-control pull-right"
                                                     required="false"  cssStyle=""/>
                                        <s:textfield id="tgl1" name="ijinKeluar.tanggalAkhirBaru" readonly="true" cssClass="form-control pull-right"
                                                     required="false" cssStyle="display: none"/>
                                    </div>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label class="control-label"><small>Lama Ijin (Baru) :</small></label>
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td width="60px">
                                            <s:textfield  id="lamaCuti" name="ijinKeluar.lamaIjinBaru" required="false" readonly="true" cssClass="form-control"/>
                                        </td>
                                        <td>&nbsp;&nbsp;&nbsp;</td>
                                        <td>
                                            hari
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </s:if>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea id="keterangan" name="ijinKeluar.keterangan" required="false" readonly="true" cssClass="form-control" rows="3"></s:textarea>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Not Approve Note :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea id="notApprove" name="ijinKeluar.notApprovalNote" required="false" readonly="true" cssClass="form-control" rows="3"></s:textarea>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Batal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea id="cancelNote" name="ijinKeluar.cancelNote" required="false" readonly="true" cssClass="form-control" rows="3"></s:textarea>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-close"/> Close
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

/*
    var nip = document.getElementById("nipId").value;
    UserAction.initComboUserId(nip, function(listdata) {
        $.each(listdata, function (i, item) {
            //alert(item.username);
            $('#namaId1').val(item.username).change();
            $('#divisi').val(item.divisiName).change();
            $('#posisi').val(item.positionName).change();
            $('#unit').val(item.branchName).change();
        })
    });*/

/*    var id = document.getElementById("ijin").value;
    IjinAction.initComboIjinId(id, function(listdata) {
        $.each(listdata, function (i, item) {
            $('#ijinName1').val(item.ijinName);
        })
    });*/

</script>

