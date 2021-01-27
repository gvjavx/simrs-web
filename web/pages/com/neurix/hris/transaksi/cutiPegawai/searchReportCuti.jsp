<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <style>
        .checkApprove {width: 20px; height: 20px;}
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_laporan'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Report Cuti
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="cutiForm" method="post"  theme="simple" namespace="/cutiPegawai" action="searchReportCuti_cutiPegawai.action" cssClass="well form-horizontal">
                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>
                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdCuti" name="cutiPegawai.unitId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<label class="control-label"><small>Bagian :</small></label>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<table>--%>
                                        <%--<s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>--%>
                                        <%--<s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"--%>
                                                  <%--listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="cutiPegawai.bagian" />--%>
                                    <%--</table>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Sub Bidang/Divisi  :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                        <s:select list="#comboBagian.comboListOfPositionBagian" id="bagian" name="cutiPegawai.bagian"
                                                  listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
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
                                            <s:textfield id="tglFrom" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglTo" name="cutiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
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
                                        <s:textfield id="nip" name="cutiPegawai.nip" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    $('#nip').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};
                                            var data = [];
                                            var unit = $('#branchIdAbsensi').val();
                                            if (unit==""){
                                                alert("unit is empty");
                                                $('#namaPegawai').val("");
                                            }else {
                                                dwr.engine.setAsync(false);
                                                CutiPegawaiAction.initComboPersonilOnlyName(query, unit, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.namaPegawai;
                                                    var labelNip = item.nip;
                                                    mapped[labelItem] = {pegawai: item.namaPegawai, id: item.nip};
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            }
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaPegawai = selectedObj.id;
                                            document.getElementById("nip").value = selectedObj.id;
                                            document.getElementById("namaPegawai").value = selectedObj.pegawai;
                                            return namaPegawai;
                                        }
                                    });
                                </script>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield name="cutiPegawai.namaPegawai" id="namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="cutiForm" id="search" name="search"
                                                 onClickTopics="beforeProcessSearch" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success" id="btnCetakRekap">
                                            <i class="fa fa-print"></i> Print Rekapitulasi Cuti
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="reportCuti_cutiPegawai"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="50%">
                                <tr>
                                    <td align="center">
                                        <%--<sj:dialog id="waiting_dialog_loading" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                   <%--resizable="false"--%>
                                                   <%--height="350" width="600" autoOpen="false" title="Loading ...">--%>
                                            <%--Please don't close this window, server is processing your request ...--%>
                                            <%--</br>--%>
                                            <%--</br>--%>
                                            <%--</br>--%>
                                            <%--<center>--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">--%>
                                            <%--</center>--%>
                                        <%--</sj:dialog>--%>
                                            <sj:dialog id="waiting_dialog_loading" openTopics="showDialog"
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

                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="620" width="900" autoOpen="false"
                                                   title="Absensi">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                        <s:set name="listOfCuti" value="#session.listOfResultCuti" scope="request" />
                                        <display:table name="listOfCuti" class="tableCuti table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_report_cutiPegawai.action" export="true" id="row" pagesize="40" style="font-size:10">
                                            <display:column media="html" title="<center>Cetak Surat</center>" style="text-align:center;font-size:9">
                                                <s:url var="urlCetakCuti" namespace="/cutiPegawai" action="printReportCuti_cutiPegawai" escapeAmp="false">
                                                    <s:param name="nip"><s:property value="#attr.row.nip" /></s:param>
                                                    <s:param name="tglFrom"><s:property value="#attr.row.stTanggalDari" /></s:param>
                                                    <s:param name="tglTo"><s:property value="#attr.row.stTanggalSelesai" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <s:a href="%{urlCetakCuti}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                </s:a>
                                            </display:column>
                                            <display:column property="nip" sortable="true" title="NIP"  />
                                            <display:column property="namaPegawai" sortable="true" title="Nama"  />
                                            <display:column property="sisaCutiTahunan" sortable="true" title="<center>Sisa Cuti Tahunan</center>"  style="text-align:center" />
                                            <display:column property="sisaCutiPanjang" sortable="true" title="<center>Sisa Cuti Panjang</center>" style="text-align:center" />
                                            <display:setProperty name="paging.banner.item_name">ReportCuti</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">ReportCuti</display:setProperty>
                                            <display:setProperty name="export.excel.filename">ReportCuti.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">ReportCuti.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">ReportCuti.pdf</display:setProperty>
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

<script>
    $(document).ready(function() {
        $.subscribe('beforeProcessSearch', function (event, data) {
            var tglAwal  = document.getElementById("tglFrom").value;
            var tglAkhir = document.getElementById("tglTo").value;
            if (tglAwal == '' || tglAkhir == '' ) {
                alert("Tanggal Awal dan Akhir harus diisi.");
            } else {
                if(tglAwal > tglAkhir){
                    alert("Tanggal Awal tidak boleh lebih esok dari Tanggal Akhir");
                } else {
                    $.publish('showDialog');
                }
            }
        });

        $('#tglFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tglTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#btnCetakRekap').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var branchId = document.getElementById("branchIdCuti").value;
            var bagian = document.getElementById("bagian").value;
            var nip = document.getElementById("nip").value;
            if (tglFrom!=""&&tglTo!=""&&branchId!=""){
                var msg='Apakah Anda ingin mencetak rekapitulasi cuti tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (bagian!=""){
                    var msg='Apakah Anda ingin mencetak rekapitulasi cuti tanggal '+tglFrom+' sampai tanggal '+tglTo+' '+bagian+'  ?';
                }
                if (confirm(msg)) {
                    window.location.href = "printReportRekapitulasiCuti_cutiPegawai.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&branchId="+branchId+"&bagian="+bagian+"&nip="+nip;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });

    });
</script>