<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style type="text/css">
        #modal-history{z-index: 3000!important}
        .modal-backdrop {  z-index: 1000 !important;  }
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IndisiplinerAction.js"/>'></script>
    <script>
        function cancelBtn() {
            $('#view_dialog_menu_indisipliner').dialog('close');
        }
    </script>
</head>
<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/indisipliner" action="" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">View Indisipliner</legend>
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
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchId1" name="indisipliner.branchId" required="true"
                                          readonly="true" listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>

                        <td>
                            <table>
                                <s:textfield  id="nip3" name="indisipliner.nip" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                        <td>
                            <a href="javascript:void(0)">
                                <img sizes="30" id="btnViewHistory" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nama1" name="indisipliner.nama" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid1" disabled="true" name="indisipliner.positionId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>
                                <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"
                                          listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="indisipliner.bagianId" disabled="true"/>
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
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId1" name="indisipliner.golonganId"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'SP0':'Teguran','SP1':'SP1','SP2':'SP2','SP3':'SP3'}" id="tipeIndisipliner1" name="indisipliner.tipeIndisipliner" readonly="true"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield readonly="true" id="tanggal4" size="10" name="indisipliner.stTanggal" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Masa Berlaku :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tglAwalPantau1" size="10" name="indisipliner.stTanggalAwalPantau" cssClass="form-control pull-right" readonly="true"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tglAkhirPantau1" size="10" name="indisipliner.stTanggalAkhirPantau" cssClass="form-control pull-right" readonly="true"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Blokir Absen :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggalAwalBlokirAbsensi1" size="10" name="indisipliner.stTanggalAwalBlokirAbsensi" readonly="true" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                    <div class="input-group-addon">
                                        s/d
                                    </div>
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggalAkhirBlokirAbsensi1" size="10" name="indisipliner.stTanggalAkhirBlokirAbsensi" readonly="true" cssClass="form-control pull-right"
                                                 required="false" cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Pelanggaran :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="keteranganPelanggaran1" name="indisipliner.keteranganPelanggaran" readonly="true" required="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Dampak :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="dampak1" name="indisipliner.dampak" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Not Approve :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="keteranganNotApprove" name="indisipliner.notApprovalNote" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Closed :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="keteranganClosed" name="indisipliner.closedNote" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
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
<div id="modal-history" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">History Indisipliner</h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="historyIndisiplinerTable table table-bordered">

                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('#btnViewHistory').click(function () {
            var nip = $('#nip4').val();
            if (nip!=""){
                $('.historyIndisiplinerTable').find('tbody').remove();
                $('.historyIndisiplinerTable').find('thead').remove();
                dwr.engine.setAsync(false);
                var tmp_table = "";
                IndisiplinerAction.searchHistoryIndisipliner(nip,function(listdata) {
                    if (listdata!=""){
                        tmp_table = "<thead style='font-size: 14px' >" +
                                "<tr>" +
                                "<th colspan='6' align='center' style='outline: 0px;text-align: center'>History Indisipliner</th>" +
                                "</tr>"+
                                "<tr class='active'>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Awal Pantau</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Awal Pantau</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tipe Indisipliner</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Keterangan</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Dampak</th>"+
                                "</tr></thead>";
                        var i = i;
                        $.each(listdata, function (i, item) {
                            var myDate = new Date(item.tanggalAwalPantau);
                            var myDate1 = new Date(item.tanggalAkhirPantau);
                            tmp_table += '<tr style="font-size: 12px;" ">' +
                                    '<td align="center">' + (i + 1) + '</td>' +
                                    '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                                    '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                                    '<td align="center">' + item.tipeIndisipliner + '</td>' +
                                    '<td align="center">' + item.keteranganPelanggaran + '</td>' +
                                    '<td align="center">' + item.dampak + '</td>' +
                                    "</tr>";
                        });
                        $('.historyIndisiplinerTable').append(tmp_table);
                        $("#historyIndisiplinerTable").find("td:contains('null')").html("-");
                        $('#modal-history').find('.modal-title').text('View Detail Indisipliner');
                        $('#modal-history').modal('show');
                    }else{
                        alert("data Indisipliner kosong");
                    }
                });

            }else{
                alert("Isi NIP terlebih dahulu");
            }
        });
    })

</script>
