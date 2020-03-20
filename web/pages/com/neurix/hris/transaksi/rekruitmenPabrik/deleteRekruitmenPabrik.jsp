<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenPabrikAction.js"/>'></script>

    <script type="text/javascript">
        $(document).ready(function() {
            window.close = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_rekruitmenPabrik.action'/>";
            };
        })
        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
            }  else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
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
            <s:form id="rekruitmenPabrikFormDelete" method="post"  theme="simple" namespace="/rekruitmenPabrik" action="saveDelete_rekruitmenPabrik.action" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Delete Rekruitmen Pabrik</legend>

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
                            <label class="control-label"><small>Rekruitmen Pegawai Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="rekruitmenPabrikId" name="rekruitmenPabrik.rekruitmenPabrikId" required="true" readonly="true" cssClass="form-control"/>                                </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Branch :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmenPabrik.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="rekruitmenPabrik.divisiId"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true" />
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <br>
                <center>
                    <table id="showdata" width="80%">
                        <tr>
                            <td align="center">
                                <table style="width: 80%;" class="rekruitmenDetailTable table table-bordered">
                                </table>
                            </td>
                        </tr>
                    </table>
                </center>
                <br>
                <div id="actions" class="form-actions">
                    <table align="center">
                        <tr>
                            <td>
                                <sj:submit targets="crud" type="button" cssClass="btn btn-danger" formIds="rekruitmenPabrikFormDelete" id="save12" name="save"
                                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                    <i class="fa fa-trash"></i>
                                    Delete
                                </sj:submit>
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary" onclick="window.location.href='<s:url action="search_rekruitmenPabrik.action"/>'">
                                    <i class="fa fa-refresh"></i> Close
                                </button>
                            </td>
                        </tr>
                    </table>
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
                                                                      close();
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

    window.loadPerson =  function(){
        //alert(nip);
        $('.rekruitmenDetailTable').find('tbody').remove();
        $('.rekruitmenDetailTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        RekruitmenPabrikAction.searchPegawaiCalon(function(listdata) {

            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Nip</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc''>Posisi Lama</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc''>Posisi Baru</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Status Pegawai</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Tipe Pegawai</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td >' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.divisi + '</td>' +
                        '<td align="center">' + item.posisiLamaName + '</td>' +
                        '<td align="center">' + item.posisiBaruName + '</td>' +
                        '<td align="center">' + item.statusPegawaiName + '</td>' +
                        '<td align="center">' + item.tipePegawaiName + '</td>' +
                        "</tr>";
            });
            $('.rekruitmenDetailTable').append(tmp_table);
        });
    };
    $(document).ready(function() {
        loadPerson();
    });
</script>


