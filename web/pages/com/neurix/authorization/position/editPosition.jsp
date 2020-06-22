<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionBagianAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var idPosition = document.getElementById("positionId2").value;
            var namePosition = document.getElementById("positionName2").value;
            var department = document.getElementById("departmentId2").value;
            var bagian = document.getElementById("bagianId2").value;
            var kelompok = document.getElementById("kelompokId2").value;

            console.log(namePosition);
            console.log(department);
            console.log(bagian);
            console.log(kelompok);

            if (namePosition != '' && department!='' && bagian!='' && kelompok!='') {
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

                if (namePosition == '') {
                    msg += 'Field <strong>Nama Posisi</strong> is required.' + '<br/>';
                }
                if (department == '') {
                    msg += 'Field <strong>Bidang/Devisi</strong> is required.' + '<br/>';
                }
                if (bagian == '') {
                    msg += 'Field <strong>Bagian</strong> is required.' + '<br/>';
                }
                if (kelompok == '') {
                    msg += 'Field <strong>Kelompok Jabatan</strong> is required.' + '<br/>';
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
            <s:form id="editForm" method="post" theme="simple" namespace="/admin/position" action="saveEdit_position" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Edit Posisi</legend>

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
                            <label class="control-label"><small>Posisi Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="positionId2" name="position.positionId" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="positionName2" name="position.positionName" required="false" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kodering :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kodering2" name="position.kodering" required="false" readonly="true" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang/Devisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboMasaTanam1" namespace="/department" name="initDepartment_department"/>
                                <s:select list="#session.listOfResultDepartment" id="departmentId2" name="position.departmentId" onchange="listPosisiHistory(); cekBidangLain()"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <div id="namaBidangLain2" class="form-group" style="display: none">
                        <label class="control-label col-sm-4">Nama Bidang Lain:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bidangLain2">
                        </div>
                    </div>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId2" name="position.bagianId" onchange="cekPosisiLain()"
                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <div id="namaJabatanLain2" class="form-group" style="display: none">
                        <label class="control-label col-sm-4">Nama Jabatan Lain: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="jabatanLain2">
                        </div>
                    </div>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kelompok Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId2" name="position.kelompokId"
                                          listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editForm" id="save" name="save"
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
<script>
    window.listPosisiHistory = function (branch, divisi) {
//        var branch = document.getElementById("branch1").value;
        var divisi = document.getElementById("departmentId2").value;
        console.log("Test divisi "+divisi);
        $('#bagianId2').empty();
        $('#bagianId2').append($("<option></option>")
                .attr("value", '')
                .text(''));
        console.log("Test");
        PositionBagianAction.searchPositionBagian(divisi, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#bagianId2').append($("<option></option>")
                        .attr("value", item.bagianId)
                        .text(item.bagianName));
            });
        });
    };
    window.cekBidangLain = function(){
        var divisi = document.getElementById("departmentId2").value;
        console.log("Test2");
        if (divisi=='0'){
            $('#bidangLain2').val("");
            $('#namaBidangLain2').show();
        }
        else{
            $('#bidangLain2').val("");
            $('#namaBidangLain2').hide();
        }
    };
    window.cekPosisiLain = function(){
        var position = document.getElementById("bagianId1").value;
        if (position=='0'){
            $('#jabatanLain2').val("");
            $('#namaJabatanLain2').show();
        }
        else{
            $('#jabatanLain2').val("");
            $('#namaJabatanLain2').hide();
        }
    };
</script>
