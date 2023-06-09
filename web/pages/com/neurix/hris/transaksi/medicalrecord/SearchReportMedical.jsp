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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalAction.js"/>'></script>
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
        $.subscribe('beforeProcessSave', function (event, data) {
            var tanggalAwal    = document.getElementById("tglFrom").value;
            var tanggalAkhir  = document.getElementById("tglTo").value;

            if ( tanggalAkhir != '' && tanggalAwal != '') {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( tanggalAwal == '') {
                    msg += 'Field Tanggal awal masih kosong \\n';
                }
                if ( tanggalAkhir == '') {
                    msg += 'Field Tanggal akhir masih kosong \\n';
                }
                alert(msg);
            }
        });
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
            Report Medical
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="medicalRecordForm" method="post"  theme="simple" namespace="/medicalrecord" action="searchReportMedical_medicalrecord.action" cssClass="well form-horizontal">

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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdMedical" name="medicalRecord.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                                  listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="medicalRecord.bagian" />
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
                                            <s:textfield id="tglFrom" name="medicalRecord.stTanggalDari" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglTo" name="medicalRecord.stTanggalSampai" cssClass="form-control pull-right"
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
                                        <s:textfield id="nip" name="medicalRecord.nip" required="false" readonly="false" cssClass="form-control"/>
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
                                        <s:textfield name="medicalRecord.pegawaiName" id="namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="medicalRecordForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" onBeforeTopics="beforeProcessSave">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success" id="btnCetakRekap">
                                            <i class="fa fa-print"></i> Print Rekapitulasi Medical
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="reportMedical_medicalrecord"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="80%">
                                <tr>
                                    <td align="center">
                                        <s:set name="listOfMedical" value="#session.listOfResultMedical" scope="request" />
                                        <display:table name="listOfMedical" class="tableMedical table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_report.action" export="true" id="row" pagesize="40" style="font-size:10">
                                            <display:column media="html" title="Cetak Surat" style="text-align:center;font-size:9">
                                                <s:url var="urlCetakMedical" namespace="/medicalrecord" action="printReportMedical_medicalrecord" escapeAmp="false">
                                                    <s:param name="nip"><s:property value="#attr.row.nip" /></s:param>
                                                    <s:param name="tglFrom"><s:property value="#attr.row.stTanggalDari" /></s:param>
                                                    <s:param name="tglTo"><s:property value="#attr.row.stTanggalSampai" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <s:a href="%{urlCetakMedical}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                </s:a>
                                            </display:column>
                                            <display:column property="nip" sortable="true" title="NIP"  />
                                            <display:column property="nama" sortable="true" title="Nama"  />
                                            <display:column property="jumlahBiaya" sortable="true" title="Jumlah Biaya Medical" />
                                            <display:setProperty name="paging.banner.item_name">ReportMedical</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">ReportMedical</display:setProperty>
                                            <display:setProperty name="export.excel.filename">ReportMedical.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">ReportMedical.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">ReportMedical.pdf</display:setProperty>
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
        $('#btnCetakRekap').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var branchId = document.getElementById("branchIdMedical").value;
            var bagian = document.getElementById("bagian").value;
            if (tglFrom!=""&&tglTo!=""&&branchId!=""){
                var msg='Apakah Anda ingin mencetak rekapitulasi Medical Record tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';

                if (confirm(msg)) {
                    window.location.href = "printReportRekapitulasiMedical_medicalrecord.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&branchId="+branchId+"&bagianId="+bagian;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
        $('#tglFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tglTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>