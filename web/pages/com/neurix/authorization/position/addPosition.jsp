<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionBagianAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type="text/javascript">

        var error = false;
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var namePosition = document.getElementById("positionName1").value;
            var department = document.getElementById("departmentId1").value;
            var bagian = document.getElementById("bagianId1").value;
            var kelompok = document.getElementById("kelompokId1").value;
            var kodering = $("#kodering-position-final").val();

            if (namePosition != '' && department!='' && bagian!='' && kelompok!='' && kodering != '') {

                PositionAction.getOnePositionByKodering(kodering, function (res) {
                    if (res.status == "found" || res.status == "error"){
                        error = true;
                        document.getElementById('errorValidationMessage5').innerHTML = msg;
                        $.publish('showErrorValidationDialog5');
                    }
                });

                if (error == false){
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');

                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }
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
                if (kodering == '') {
                    msg += 'Field <strong>Kodering</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage5').innerHTML = msg;
                $.publish('showErrorValidationDialog5');

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
            <s:form id="addForm" method="post" theme="simple" namespace="/admin/position" action="save_position" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Add Posisi</legend>


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
                            <label class="control-label"><small>Bidang/Devisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboMasaTanam1" namespace="/department" name="initDepartment_department"/>
                                <s:select list="#session.listOfResultDepartment" id="departmentId1" name="position.departmentId" onchange="listPosisiHistory();cekBidangLain();"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue=" - " cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <div id="namaBidangLain" class="form-group" style="display: none">
                        <label class="control-label col-sm-4">Nama Bidang Lain:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bidangLain">
                        </div>
                    </div>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{}" id="bagianId1" name="position.bagianId"
                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue=" - " onchange="cekPosisiLain();showKoderingSubBidang();setOptionUnitCost()"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <div id="namaJabatanLain" class="form-group" style="display: none">
                        <label class="control-label col-sm-4">Nama Jabatan Lain: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="jabatanLain">
                        </div>
                    </div>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kelompok Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId1" name="position.kelompokId"
                                          listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue=" - " cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table>
                                <hr/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis :</small></label>
                        </td>
                        <td>
                            <table>

                                <s:select list="#{'N':'Jabatan'}" id="jenis" name="position.flagCostUnit"
                                          headerKey="Y" headerValue="Cost Unit" cssClass="form-control" onchange="showKodering()"/>
                            </table>
                        </td>
                    </tr>
                    <tr id="sec-cost-unit" style="display: none">
                        <td>
                            <label class="control-label"><small>Cost Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{}" id="kodering-cost-unit" name="position.koderingCostUnit"
                                          headerKey="" headerValue=" - " cssClass="form-control"
                                          onchange="setKoderingFromUnitCost()"
                                />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="positionName1" name="position.positionName" required="false" readonly="false" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr id="sec-kodering">
                        <td>
                            <label class="control-label"><small>Kodering :</small></label>
                        </td>
                        <td>
                            <table>
                                <div style="float: left;background-color: sandybrown; padding: 10px; display: none" id="kodering-sub-bidang"></div>
                                <s:textfield id="kodering" type="number" required="false" readonly="false" cssClass="form-control"
                                             cssStyle="width: 100px" max="99" min="0"
                                             onchange="setKodering()"
                                />
                                <s:hidden name="position.kodering" id="kodering-position-final"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger"  onclick="cancelBtn();">
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
                                                                      callSearch2();
                                                                      link();
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

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog5" modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessage5"></div></center>
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
        var divisi = document.getElementById("departmentId1").value;
        $('#bagianId1').empty();
        $('#bagianId1').append($("<option></option>")
                .attr("value", '')
                .text(' - '));
        PositionBagianAction.searchPositionBagian(divisi, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#bagianId1').append($("<option></option>")
                        .attr("value", item.bagianId)
                        .text(item.bagianName));
            });
        });
    };
    window.cekBidangLain = function(){
        var divisi = document.getElementById("departmentId1").value;
        if (divisi=='0'){
            $('#bidangLain').val("");
            $('#namaBidangLain').show();
        }
        else{
            $('#bidangLain').val("");
            $('#namaBidangLain').hide();
        }
    };
    window.cekPosisiLain = function(){
        var position = document.getElementById("bagianId1").value;
        if (position=='0'){
            $('#jabatanLain').val("");
            $('#namaJabatanLain').show();
        }
        else{
            $('#jabatanLain').val("");
            $('#namaJabatanLain').hide();
        }
    };

    function showKoderingSubBidang() {
        var subbid = $("#bagianId1 option:selected").val();
        PositionAction.getBagianById(subbid, function (res) {
            $("#kodering-sub-bidang").show();
            $("#kodering-sub-bidang").html(res.kodering + ".");
        });
    }

    function showKodering() {
        var isUnitCost = $("#jenis option:selected").val();

        // jika bukan unit cost
        if (isUnitCost != 'Y'){
            $("#sec-kodering").hide();
            $("#sec-cost-unit").show();

        } else {
            $("#sec-kodering").show();
            $("#sec-cost-unit").hide();
        }
    }

    function setKodering() {
        var kodering        = $("#kodering").val();
        var koderingsubbid  = $("#kodering-sub-bidang").text();
        $("#kodering-position-final").val(koderingsubbid+kodering);
        //alert($("#kodering-position-final").val());
    }

    function setKoderingFromUnitCost() {
        var koderingCostUnit = $("#kodering-cost-unit option:selected").val();
        $("#kodering-position-final").val(koderingCostUnit);
        //alert($("#kodering-position-final").val());
    }

    function setOptionUnitCost() {
        var subbid = $("#bagianId1 option:selected").val();

        $('#kodering-cost-unit').empty();
        $('#kodering-cost-unit').append($("<option></option>")
            .attr("value", '')
            .text(' - '));

        PositionAction.getUnitCostBySubBid(subbid, function (res) {
            $.each(res, function (i, item) {
                $('#kodering-cost-unit').append($("<option></option>")
                    .attr("value", item.kodering)
                    .text(item.positionName));
            });
        });
    }
</script>