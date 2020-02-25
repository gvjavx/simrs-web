<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/tipeJurnal" action="saveDelete_tipeJurnal" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">View Tipe Jurnal</legend>
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
                            <label class="control-label"><small>Tipe Jurnal Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="tipeJurnalIdView" name="tipeJurnal.tipeJurnalId" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Tipe Jurnal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tipeJurnalNameView" name="tipeJurnal.tipeJurnalName" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <div id="kodeRekeningList">
                    <br>
                    <center>
                        <table id="showdata" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;"
                                           class="kodeRekeningTable table table-bordered">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </div>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Close
                        </button>
                    </div>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
<script>
    window.loadKodeRekening = function () {
        $('.kodeRekeningTable').find('tbody').remove();
        $('.kodeRekeningTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table_kode_rekening = "";
        KodeRekeningAction.searchKodeRekening("",function (listdata) {
            tmp_table_kode_rekening = "<thead style='font-size: 14px' ><tr class='active'>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>COA</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Nama Kode Rekening</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Posisi Rekening</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table_kode_rekening += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.kodeRekening + '</td>' +
                    '<td align="center">' + item.namaKodeRekening + '</td>' +
                    '<td align="center">' + item.posisiName + '</td>' +
                    "</tr>";
            });
            $('.kodeRekeningTable').append(tmp_table_kode_rekening);
        });
    };
    $(document).ready(function () {
        loadKodeRekening();
    })
</script>