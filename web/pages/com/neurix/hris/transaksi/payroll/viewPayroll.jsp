<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>--%>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>--%>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>--%>

    <style>
        .checkBox {width: 20px; height: 20px;}
        .checkApprove {width: 20px; height: 20px;}
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .modal { overflow: auto !important; }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
        .ui-dialog{
            z-index: 6000;
        }
    </style>
    <script type='text/javascript'>

        <%--function link(){--%>
            <%--window.location.href="<s:url action='initForm_alat'/>";--%>
        <%--}--%>

        <%--$.subscribe('successDialog', function (event, data) {--%>
            <%--if (event.originalEvent.request.status == 200) {--%>
                <%--jQuery(".ui-dialog-titlebar-close").hide();--%>
                <%--$.publish('showInfoDialog');--%>
            <%--}--%>
        <%--});--%>

        // $(document).ready(function() {
        //     $('#popUpPensiunTanggal').datepicker({
        //         dateFormat: 'dd-mm-yy',
        //         changeMonth: true,
        //         changeYear: true
        //     });

            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_payroll.action'/>";
            };

        //     $.subscribe('beforeProcessSave', function (event, data) {
        //         var branch = document.getElementById("branchId").value;
        //         var bulan = document.getElementById("bulanPayroll").value;
        //         var tahun = document.getElementById("tahunPayroll").value;
        //
        //         if (branch != '' && bulan != '' && tahun != '') {
        //             if (confirm('Do you want to save this record?')) {
        //                 event.originalEvent.options.submit = true;
        //                 $.publish('showDialog');
        //             } else {
        //                 event.originalEvent.options.submit = false;
        //             }
        //         } else {
        //
        //             event.originalEvent.options.submit = false;
        //             var msg = "";
        //             if (branch == '') {
        //                 msg += 'Field <strong>Branch</strong> is required.' + '<br/>';
        //             }
        //
        //             if (bulan == '') {
        //                 msg += 'Field <strong>Bulan</strong> is required.' + '<br/>';
        //             }
        //
        //             if (tahun == '') {
        //                 msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
        //             }
        //
        //             document.getElementById('errorValidationMessage').innerHTML = msg;
        //
        //             $.publish('showErrorValidationDialog');
        //
        //         }
        //     });
        // });


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
            View Payroll
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i>Pencarian Payroll</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="viewPayrollForm" method="post"  theme="simple" namespace="/payroll" action="view_payroll.action" cssClass="form-horizontal">

                                        <%--<s:hidden name="addOrEdit"/>--%>
                                        <%--<s:hidden name="delete"/>--%>
                                        <s:hidden name="payrollHeader.payrollHeaderId"/>
                                        <s:hidden name="editSearch" value="true"/>
                                        <s:hidden name="payrollHeader.approvalAksFlag"/>
                                        <s:hidden name="payrollHeader.approvalSdmFlag"/>
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
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchesId" name="payrollHeader.branchId" disabled="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        <s:hidden id="branchId" name="payrollHeader.branchId" />
                                                            <%--<s:select list="#initComboBranch.listOfComboBranch" value="KD01" id="branchId" name=""--%>
                                                            <%--listKey="branchId" listValue="branchName" headerKey="" disabled="true"--%>
                                                            <%--headerValue="[Select one]" cssClass="form-control"/>--%>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Periode :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                                        '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                  id="bulanPayroll" name="payrollHeader.bulan" disabled="true"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                        <s:hidden id="bulanGaji" name="payrollHeader.bulan" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/payroll" name="initComboPeriodeTahunKurang10_payroll"/>
                                                            <%--<s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>--%>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll"
                                                                  name="payrollHeader.tahun" required="true" headerKey="" disabled="true"
                                                                  headerValue="[Select one]"/>
                                                        <s:hidden id="tahunGaji" name="payrollHeader.tahun" />
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PY':'Payroll', 'TH':'THR', 'CP':'Cuti Panjang', 'CT':'Cuti Tahunan', 'JP':'Jasa Operasional', 'JB':'PMP',
                                                        'PN':'SHT(Pensiun)', 'IN':'Insentif','RP':'Rapel'}" id="tipe" name="payrollHeader.tipePayroll" cssClass="form-control" disabled="true" />
                                                        <s:hidden id="tipePayroll" name="payrollHeader.tipePayroll" />
                                                            <%--<s:select list="#{'PR':'Payroll', 'T':'THR', 'PD':'Pendidikan', 'R':'Rapel', 'IN':'Insentif',--%>
                                                            <%--'JP':'Jasprod', 'JB':'Jubileum', 'PN':'Pensiun'}" id="tipe" name="payrollHeader.tipePayroll"--%>
                                                            <%--cssClass="form-control" disabled="true" />--%>
                                                    </table>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Pegawai :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:textfield cssStyle="display: none" id="txtTipeId" name="payroll.tipe" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="txtBranchId" name="payroll.branchId" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="txtBulanPayroll" name="payroll.bulan" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="txtTahunPayroll" name="payroll.tahun" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="personName1" name="payroll.nip" required="false" readonly="false" cssClass="form-control"/>--%>
                                                        <s:hidden id="pegawaiId" name="payrollHeader.nip" />
                                                        <s:textfield id="pegawaiName" name="payrollHeader.pegawaiName" required="false" readonly="false" cssClass="form-control"/>
                                                            <%--<s:textfield cssStyle="display: none" id="personName" name="payroll.name" required="false" readonly="false" cssClass="form-control"/>--%>
                                                    </table>
                                                </td>
                                                <script type='text/javascript'>
                                                    var functions, mapped;
                                                    // var prov = document.getElementById("provinsi1").value;
                                                    // var branchId = document.getElementById("branchId").value;
                                                    $('#pegawaiName').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            PayrollAction.initComboPersonil(query,'', function (listdata) {
                                                                data = listdata;
                                                                //alert('aa');
                                                            });
                                                            //alert(prov);
                                                            $.each(data, function (i, item) {
                                                                var labelItem =item.nip + " || " + item.namaPegawai;

                                                                mapped[labelItem] = {pegawaiName : item.namaPegawai, pegawaiId : item.nip, label: labelItem};
                                                                functions.push(labelItem);
                                                            });


                                                            process(functions);
                                                        },

                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            var itemLabel = selectedObj.label;
                                                            document.getElementById("pegawaiId").value = selectedObj.pegawaiId;
                                                            document.getElementById("pegawaiName").value = selectedObj.pegawaiName;

                                                            return itemLabel;
                                                        }
                                                    });

                                                </script>
                                            </tr>

                                        </table>



                                        <br>

                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="viewPayrollForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>

                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="
                                                                    window.location.href='<s:url action="view_payroll"><s:param name="id"><s:property value="%{payrollHeader.payrollHeaderId}"/></s:param><s:param name="bulan"><s:property value="%{payrollHeader.bulan}"/></s:param><s:param name="tahun"><s:property value="%{payrollHeader.tahun}"/></s:param><s:param name="branchId"><s:property value="%{payrollHeader.branchId}"/></s:param><s:param name="tipePayroll"><s:property value="%{payrollHeader.tipePayroll}"/></s:param><s:param name="approvedSdm"><s:property value="%{payrollHeader.approvalSdmFlag}"/></s:param><s:param name="approvedAks"><s:property value="%{payrollHeader.approvalAksFlag}"/></s:param></s:url>';
                                                                ">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>

                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-default" onclick="menujuLink('cancelPage_payroll.action', 'Payroll');">
                                                            <i class="fa fa-close"></i> Close
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <br>

                                        <center>
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                                   <%--height="500" width="500" autoOpen="false"--%>
                                                                   <%--title="Payroll ">--%>
                                                            <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                                        <%--</sj:dialog>--%>

                                                        <%--<s:set name="listDataPayroll" value="#session.listDataPayroll" scope="request" />--%>
                                                        <%--<display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"--%>
                                                                       <%--requestURI="paging_displaytag_view_payroll.action" export="true" id="row" pagesize="40" style="font-size:10">--%>
                                                            <%--<s:if test="#attr.row.flagPrint == 'Y'">--%>
                                                                <%--<display:column media="html" title="Print">--%>
                                                                    <%--<a href="javascript:;" data="<s:property value="%{#attr.row.payrollId}"/>" class="item-print">--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_print.png"/>" name="icon_slip">--%>
                                                                    <%--</a>--%>
                                                                <%--</display:column>--%>
                                                            <%--</s:if>--%>
                                                            <%--<display:column media="html" title="View">--%>
                                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.payrollId}"/>" class="item-view">--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_search.png"/>" >--%>
                                                                <%--</a>--%>
                                                            <%--</display:column>--%>

                                                            <%--<display:column property="nip" sortable="true" title="NIP"  />--%>
                                                            <%--<display:column property="nama" sortable="true" title="Nama" />--%>
                                                            <%--<display:column property="divisiName" sortable="true" title="Bidang" />--%>
                                                            <%--<display:column property="posisiName" sortable="true" title="Jabatan" />--%>
                                                            <%--<display:column property="golonganName" sortable="true" title="Golongan" />--%>
                                                            <%--<display:column style="text-align:right;" property="componentA" sortable="true" title="Gaji Kotor" />--%>
                                                            <%--<display:column style="text-align:right;" property="componentB" sortable="true" title="Total.Tunj." />--%>
                                                            <%--<display:column style="text-align:right;" property="componentC" sortable="true" title="Total.Pot. Tnp. PPh" />--%>
                                                            <%--<display:column style="text-align:right;" property="tunjanganPph" sortable="true" title="PPh" />--%>
                                                            <%--<display:column style="text-align:right;" property="gajiBersih" sortable="true" title="Gaji Bersih" />--%>
                                                            <%--<display:column style="text-align:center;" property="multifikator" sortable="true" title="Proporsi Gaji" />--%>
                                                            <%--<display:column style="color: #00cc00;" property="stTanggalPraPensiun" sortable="true" title="Jubileum" />--%>
                                                            <%--<display:column style="color: red;" property="stTanggalPensiun" sortable="true" title="Pensiun" />--%>

                                                            <%--<display:setProperty name="paging.banner.item_name">PayrollDetail</display:setProperty>--%>
                                                            <%--<display:setProperty name="paging.banner.items_name">PayrollDetail</display:setProperty>--%>
                                                            <%--<display:setProperty name="export.excel.filename">PayrollDetail.xls</display:setProperty>--%>
                                                            <%--<display:setProperty name="export.csv.filename">PayrollDetail.csv</display:setProperty>--%>
                                                            <%--<display:setProperty name="export.pdf.filename">PayrollDetail.pdf</display:setProperty>--%>
                                                            <%--&lt;%&ndash;<display:column style="text-align:right;" property="totalRapel" sortable="true" title="Rapel" />&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<display:column style="text-align:right;" property="totalThr" sortable="true" title="Thr" />&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<display:column style="text-align:right;" property="totalPendidikan" sortable="true" title="Pendidikan" />&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<display:column style="text-align:right;" property="totalJasProd" sortable="true" title="Jasprod" />&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<display:column style="color: #4d9900;" property="tanggalJubileum" sortable="true" title="Jubileum" />&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<display:column style="color: #b3b300;" property="stTanggalPensiun" sortable="true" title="Pensiun" />&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<display:column style="text-align:center;" media="html" title="Promosi">--%>
                                                                <%--<s:if test="#attr.row.flagPromosiOn">--%>
                                                                    <%--<a href="javascript:;" payrollId="<s:property value="%{#attr.row.payrollId}"/>"--%>
                                                                       <%--nip="<s:property value="%{#attr.row.nip}"/>" class="item-promosi">--%>
                                                                        <%--<span style="font-size: 17px" class="glyphicon glyphicon-user"></span>--%>
                                                                    <%--</a>--%>
                                                                <%--</s:if>--%>
                                                            <%--</display:column>&ndash;%&gt;--%>



                                                        <%--</display:table>--%>

                                                            <s:set name="listDataPayroll" value="#session.listDataPayroll" scope="request" />
                                                            <display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"
                                                                           requestURI="paging_displaytag_view_payroll.action" export="true" id="row" pagesize="1000" style="font-size:9">

                                                                <display:column media="html" title="View">
                                                                    <a href="javascript:;"
                                                                       data="<s:property value="%{#attr.row.payrollId}"/>" class="item-view">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_search.png"/>" >
                                                                    </a>
                                                                </display:column>
                                                                <s:if test="#attr.row.flagPrint == 'Y'">
                                                                    <display:column media="html" title="Print">
                                                                        <a href="javascript:;" data="<s:property value="%{#attr.row.payrollId}"/>" class="item-print">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_print.png"/>" name="icon_slip">
                                                                        </a>
                                                                    </display:column>
                                                                </s:if>

                                                                <display:column property="nip" sortable="true" title="NIP"  />
                                                                <display:column property="namaPegawai" sortable="true" title="Nama" />
                                                                <display:column property="divisiName" sortable="true" title="Bidang" />
                                                                <display:column property="posisiName" sortable="true" title="Jabatan" />
                                                                <display:column style="text-align:right;" property="componentA" sortable="true" title="Gaji" />
                                                                <display:column style="text-align:right;" property="componentB" sortable="true" title="Tot.Tunj." />
                                                                <display:column style="text-align:right;" property="componentC" sortable="true" title="Tot.Pot.Tnp.PPh" />
                                                                <display:column style="text-align:right;" property="tunjanganPph" sortable="true" title="PPh" />
                                                                <display:column style="text-align:right;" property="gajiBersih" sortable="true" title="Gaji.Bersih" />
                                                                <display:column style="text-align:center;" property="multifikator" sortable="true" title="Prop.Gaji" />
                                                                <display:column style="color: #00cc00;" property="stTanggalPraPensiun" sortable="true" title="PMP" />
                                                                <display:column style="color: red;" property="stTanggalPensiun" sortable="true" title="Pensiun" />

                                                                <display:setProperty name="paging.banner.item_name">PayrollDetail</display:setProperty>
                                                                <display:setProperty name="paging.banner.items_name">PayrollDetail</display:setProperty>
                                                                <display:setProperty name="export.excel.filename">PayrollDetail.xls</display:setProperty>
                                                                <display:setProperty name="export.csv.filename">PayrollDetail.csv</display:setProperty>
                                                                <display:setProperty name="export.pdf.filename">PayrollDetail.pdf</display:setProperty>
                                                            </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>

                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                           resizable="false"
                                                                           height="350" width="600" autoOpen="false" title="Searching ...">
                                                                    Please don't close this window, server is processing your request ...
                                                                    </br>
                                                                    </br>
                                                                    </br>
                                                                    <center>
                                                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_write">
                                                                    </center>
                                                                </sj:dialog>

                                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                           buttons="{
                                                              'OK':function() {
                                                                      clos();
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
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

<%--updated by ferdi 01-12-2020 to display modal detail payroll to view saved data--%>
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:100%">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Detail Payroll Person</h4>
            </div>
            <div class="modal-body" align="left">
                <form class="form-horizontal" id="formEdit">
                    <div id="biodataMod" class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <input style="display: none" readonly type="text" class="form-control nip" id="payrollPegawaiId" name="payrollPegawaiId">
                                    <input style="display: none" readonly type="text" class="form-control nip" id="tipePegawai" name="tipePegawai">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Unit </label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="unitId" name="branchId">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bulan / Tahun </label>
                                <div class="col-sm-3">
                                    <input readonly type="text" class="form-control nip" id="bulan" name="bulan">
                                </div>
                                <div class="col-sm-4">
                                    <input readonly type="text" class="form-control nip" id="tahun" name="tahun">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >NIP</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="nip" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Gelar</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="gelar" name="gelar">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Nama</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="nama" name="nama">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >NPWP</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="npwp" name="npwp">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tipe Pegawai</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="tipePegawaiName" name="tipePegawaiName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Jenis Pegawai</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="jenisPegawai" name="jenisPegawai">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bidang</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="divisi" name="divisi">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Level</label>
                                <div class="col-sm-7">
                                    <input readonly style="padding-left: 8px; padding-right: 0px" type="text" class="form-control nip" id="golongan" name="golongan">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Jabatan</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="jabatan" name="jabatan">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Status Kel/Anak</label>
                                <div class="col-sm-4">
                                    <input readonly type="text" class="form-control nip" id="statusKeluarga" name="statusKeluarga">
                                </div>
                                <div class="col-sm-3">
                                    <input readonly type="text" class="form-control nip" id="jumlahAnak" name="jumlahAnak">
                                </div>
                            </div>

                            <div class="form-group">
                                <label style="padding-left: 0px; padding-right: 0px" class="control-label col-sm-5" >Tipe Dana Pensiun</label>
                                <div class="col-sm-7">
                                    <input style="text-align: left" readonly type="text" class="form-control nip" id="tipeDanaPensiun" name="tipeDanaPensiun">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Gol.Dapen/Masa Gol.</label>
                                <div class="col-sm-5">
                                    <input readonly style="padding-left: 8px; padding-right: 0px" type="text" class="form-control nip" id="golonganDapenId" name="golonganDapenId">
                                </div>

                                <div class="col-sm-2">
                                    <input readonly type="text" class="form-control nip" id="masaGolDapen" name="masaGolDapen">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Multifikator</label>
                                <div class="col-sm-7">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="multifikator" name="multifikator">
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-5" >Gaji Pensiun*</label>--%>
                            <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="gajiPensiun" name="gajiPensiun">--%>
                            <%--</div>--%>
                            <%--</div>--%>

                        </div>
                        <div id="koreksiAks" class="col-sm-4">
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Koreksi :</label>
                                <div class="col-sm-6">
                                    <input type="checkbox" id="cekFlagKoreksi" class="checkBox" onchange="needKoreksi()" />
                                    <input style="display: none" type="text" class="form-control nip" id="flagKoreksi" name="flagKoreksi">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Catatan Koreksi :</label>
                                <div class="col-sm-6">
                                    <textarea rows="6" type="text" class="form-control nip" id="noteKoreksi" name="noteKoreksi"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="totalAMod">
                        <div class="col-sm-4" id="komponenA">
                            <div align="center">
                                <h4>A. Gaji </h4>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Gaji</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="gaji" name="gaji">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Sankhus</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="sankhus" name="sankhus">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Jabatan</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjJabStruktural" name="tunjJabStruktural">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Struktural</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjStruktural" name="tunjStruktural">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Fungsional</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjStrategis" name="tunjStrategis">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Peralihan Gapok</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjPeralihanGapok" name="tunjPeralihanGapok">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Peralihan Sankhus</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjPeralihanSankhus" name="tunjPeralihanSankhus">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Peralihan Tunj</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjPeralihanTunj" name="tunjPeralihanTunj">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Siaga</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjSiaga" name="tunjSiaga">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Lokasi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjLokasi" name="tunjLokasi">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Supervisi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjSupervisi" name="tunjSupervisi">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Tambahan(PKWT)</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjTambahan" name="tunjTambahan">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Lembur</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjLembur" name="tunjLembur">
                                </div>
                                <div class="col-sm-1 pull-right nopadding">
                                    <button type="button" id="detailLembur" class="btn btn-primary">View</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Pemondokan</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="pemondokan" name="pemondokan">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Komunikasi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="komunikasi" name="komunikasi">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Total A</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalA" name="totalA">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" ><b>Gaji Bersih (A+B)- C</b></label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" align="right" readonly type="text" class="form-control nip" id="gajiBersih" name="gajiBersih">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div align="center">
                                <h4>B. Tunjangan RLAB dan SANSOS</h4>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Rumah </label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right"  type="text" class="form-control nip" id="tunjRumah" name="tunjRumah">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Listrik</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjListrik" name="tunjListrik">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Air</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjAir" name="tunjAir">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. BBM</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjBbm" name="tunjBbm">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5"><b>Total. RLAB</b></label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalRlab" name="totalRlab">
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Dapen</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjDapen" name="tunjDapen">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Bpjs Ks</label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right" readonly type="text" class="form-control nip" id="tunjBpjsKs" name="tunjBpjsKs">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Bpjs Tk</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjBpjsTk" name="tunjBpjsTk">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Pph</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjPph" name="tunjPph">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Total B</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalB" name="totalB">
                                </div>
                            </div>
                            <br>

                            <br>
                            <s:if test="%{payrollHeader.editPphBulan12}">
                                <div class ="pphBulan12">
                                    <div align="center">
                                        <h4>Selisih PPH </h4>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5 nopadding" id="labelPphSeharusnya" >Pph Seharusnya </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" type="text" readonly class="form-control nip" id="pphSeharusnya" name="pphSeharusnya">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5 nopadding" id="labelPph11Bulan" >Pph 11 Bulan </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pph11Bulan" name="pph11Bulan">
                                        </div>
                                        <div class="col-sm-1 pull-right nopadding">
                                            <button type="button" class="btn btn-primary" id="btnViewTotalPPh11">View</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" id="labelSelisih" >Selisih pph 21 </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" readonly type="text" class="form-control nip" id="selisihPph" name="selisihPph">
                                        </div>
                                    </div>
                                </div>
                            </s:if>
                        </div>
                        <div class="col-sm-4">
                            <div align="center">
                                <h4>C. Potongan</h4>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Dp. Peg</label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right"  type="text" class="form-control nip" id="iuranDapenPeg" name="iuranDapenPeg">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Dp. Pers</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranDapenPersh" name="iuranDapenPersh">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Tk Pegawai</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsTkPeg" name="iuranBpjsTkPeg">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Tk Persh. </label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsTkPers" name="iuranBpjsTkPers">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Ks Peg. </label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsKsPeg" name="iuranBpjsKsPeg">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Ks Pers. </label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsKsPers" name="iuranBpjsKsPers">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Pot. Pph</label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right" readonly type="text" class="form-control nip" id="potPphGaji" name="potPphGaji">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Pot. Lain-lain</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalPotonganLain" name="totalPotonganLain">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Total C</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalC" name="totalC">
                                </div>
                            </div>
                            <br>
                            <br>
                            <div align="center">
                                <h4>  Rincian Potongan Lainnya</h4>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Kopkar</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="kopkar" name="kopkar" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran Sp</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranSp" name="iuranSp" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran PIIKB</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranPiiKb" name="iuranPiiKb" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bank BRI</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="bankBri" name="bankBri" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bank Mandiri</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="bankMandiri" name="bankMandiri" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Infaq</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="infaq" name="infaq" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Perkes dan Obat</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="perkesDanObat" name="perkesDanObat" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Listrik</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="listrik" name="listrik" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran Profesi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranProfesi" name="iuranProfesi" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Potongan Lain</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="potonganLain" name="potonganLain" >
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <a id="btnSave" type="btn btn-success" class="btn btn-default btn-success"><i class="fa fa-save"></i> Save</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-pph-11bulan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <center>
                    <table style="width: 100%;" id="tablePph11Bulan" class="tablePph11Bulan table table-bordered">
                    </table>
                </center>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-lembur" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:800px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <%--<font size="1" face="Courier New" >--%>
                <center>
                    <table style="width: 100%;" id="tableLembur" class="tableLembur table table-bordered">
                    </table>
                </center>
            </div>
            <div class="modal-footer">
                <%--<a type="button" class="btn btn-success" id="btnRefreshLembur" >Refresh</a>--%>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>


<%--<div id="modal-edit" class="modal fade" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:100%">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title">Approve SPPD</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" align="left">--%>
                <%--<form class="form-horizontal" id="formEdit">--%>
                    <%--<div id="biodataMod" class="row">--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<input style="display: none" readonly type="text" class="form-control nip" id="payrollId2" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Bulan / Tahun </label>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="bulan" name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-4">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="tahun" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >NIP</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="nip" name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<input style="display: none" readonly type="text" class="form-control nip" id="branchId2" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >NPWP</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="npwp" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group" style="display: none;">--%>
                                <%--<label class="control-label col-sm-5" >Tipe Pegawai</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="tipePegawai" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tipe Pegawai</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="tipePegawaiName" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Nama</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="nama" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Bidang</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="divisi" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Level</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly style="padding-left: 8px; padding-right: 0px" type="text" class="form-control nip" id="golongan" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Jabatan</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="jabatan" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >S.Keluarga/Anak</label>--%>
                                <%--<div class="col-sm-4">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="statusKeluarga" name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="jumlahAnak" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label style="padding-left: 0px; padding-right: 0px" class="control-label col-sm-5" >Tipe Dana Pensiun</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input style="text-align: left" readonly type="text" class="form-control nip" id="tipeDanaPensiun" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Gol.Dapen/Masa Gol.</label>--%>
                                <%--<div class="col-sm-5">--%>
                                    <%--<input readonly style="padding-left: 8px; padding-right: 0px" type="text" class="form-control nip" id="golonganDapenId" name="nip">--%>
                                <%--</div>--%>

                                <%--<div class="col-sm-2">--%>
                                    <%--<input readonly type="text" class="form-control nip" id="masaGolDapen" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-3">--%>

                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Gaji Pensiun*</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="gajiPensiun" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row" id="totalAMod">--%>
                        <%--<div class="col-sm-4" id="komponenA">--%>
                            <%--<div align="center">--%>
                                <%--<h4>A. Gaji </h4>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Gaji</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="gaji" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Sankhus</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjUmk" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Jabatan</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjJabStruktural" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Struktural</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjStruktural" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Fungs</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjStrategis" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Peralihan</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" type="text" class="form-control nip" id="tunjPeralihan" readonly name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-1 pull-right nopadding">--%>
                                    <%--<button type="button" id="detailPeralihan" class="btn btn-primary">View</button>--%>
                                <%--</div>--%>
                                <%--<script>--%>
                                    <%--$('#detailPeralihan').click(function(){--%>
                                        <%--$('#modal-peralihan').find('.modal-title').text('Detail Peralihan');--%>
                                        <%--$('#modal-peralihan').modal('show');--%>
                                    <%--})--%>
                                <%--</script>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Lain</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input readonly style="text-align: right" readonly type="text" class="form-control nip" id="tunjLain" name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-1 nopadding">--%>
                                    <%--<button type="button" id="detailTunjlain" class="btn btn-primary detailTunjlain">View</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Tambahan(PKWT)</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjTambahan" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Lembur</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjLembur" name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-1 pull-right nopadding">--%>
                                    <%--<button type="button" id="detailLembur" class="btn btn-primary">View</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Pemondokan</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" type="text" class="form-control nip" id="pemondokan" readonly name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Komunikasi</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" type="text" class="form-control nip" id="komunikasi" readonly name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tambahan Lain</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tambahanLain" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Total A</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="totalA" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" ><b>Gaji Bersih (A+B)- C</b></label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" align="right" readonly type="text" class="form-control nip" id="gajiBersih" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div align="center">--%>
                                <%--<h4>B. Tunjangan RLAB dan SANSOS</h4>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Rumah </label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input readonly style="text-align: right"  type="text" class="form-control nip" id="tunjRumah" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Listrik</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjListrik" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Air</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjAir" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. BBM</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjBbm" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5"><b>Total. RLAB</b></label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="totalRlab" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<br>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Dapen</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjDapen" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Bpjs Ks</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input readonly style="text-align: right" readonly type="text" class="form-control nip" id="tunjBpjsKs" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Bpjs Tk</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjBpjsTk" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Tunj. Pph</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="tunjPph" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Total B</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="totalB" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<br>--%>
                            <%--<div align="center">--%>
                                <%--<h4>D. PTT</h4>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5 nopadding" >Nilai PTT</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" type="text" readonly class="form-control nip" id="nilaiPtt" name="nip">--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-1 pull-right nopadding">--%>
                                    <%--<button type="button" id="btnDetailPtt" class="btn btn-primary">View</button>--%>
                                <%--</div>--%>
                                <%--<script>--%>
                                    <%--function loadPtt() {--%>
                                        <%--$('.tabelPtt').find('tbody').remove();--%>
                                        <%--$('.tabelPtt').find('thead').remove();--%>

                                        <%--var tmp_table = "";--%>
                                        <%--var tmp_table2 = "<tbody>";--%>
                                        <%--var hasil = 0;--%>

                                        <%--PayrollAction.getDetailPtt( function(listdata){--%>
                                            <%--tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +--%>
                                                <%--"<th style='text-align: center; background-color:  #3c8dbc''>No </th>" +--%>
                                                <%--"<th style='text-align: center; background-color:  #3c8dbc''>Nama PTT</th>" +--%>
                                                <%--"<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>" +--%>
                                                <%--"</tr></thead><tbody>";--%>
                                            <%--$.each(listdata, function (i, item) {--%>
                                                <%--tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +--%>
                                                    <%--'<td align="left">' + (i+1) + '</td>' +--%>
                                                    <%--'<td align="left">' + item.tipePttName+ '</td>' +--%>
                                                    <%--'<td align="right">' + item.nilai+ '</td>' +--%>
                                                    <%--"</tr>";--%>
                                            <%--});--%>
                                            <%--tmp_table += "</tbody>";--%>
                                            <%--$('.tabelPtt').append(tmp_table);--%>
                                        <%--});--%>
                                    <%--}--%>
                                    <%--$('#btnDetailPtt').click(function(){--%>
                                        <%--loadPtt();--%>
                                        <%--$('#modal-ptt').find('.modal-title').text('Detail PTT');--%>
                                        <%--$('#modal-ptt').modal('show');--%>
                                    <%--})--%>
                                <%--</script>--%>
                            <%--</div>--%>
                            <%--<br>--%>
                            <%--<s:if test="payrollBulan12">--%>
                                <%--<div class ="pphBulan12">--%>
                                    <%--<div align="center">--%>
                                        <%--<h4>Selisih PPH </h4>--%>
                                    <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-5 nopadding" id="labelTotalPtt" >Total Ptt Setahun. </label>--%>
                                        <%--<div class="col-sm-6">--%>
                                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="totalPtt" name="nip">--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-1 pull-right nopadding">--%>
                                            <%--<button type="button" class="btn btn-primary" id="btnViewTotalPtt11">View</button>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-5 nopadding" id="labelPphSeharusnya" >Pph Seharusnya </label>--%>
                                        <%--<div class="col-sm-6">--%>
                                            <%--<input style="text-align: right" type="text" readonly class="form-control nip" id="pphSeharusnya" name="nip">--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-1 pull-right nopadding">--%>
                                            <%--<button type="button" class="btn btn-primary" id="btnViewPPhSeharusnya">View</button>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-5 nopadding" id="labelPph11Bulan" >Pph 11 Bulan </label>--%>
                                        <%--<div class="col-sm-6">--%>
                                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pph11Bulan" name="nip">--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-1 pull-right nopadding">--%>
                                            <%--<button type="button" class="btn btn-primary" id="btnViewTotalPPh11">View</button>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-5" id="labelSelisih" >Selisih pph 21 </label>--%>
                                        <%--<div class="col-sm-6">--%>
                                            <%--<input style="text-align: right" type="text" readonly class="form-control nip" id="selisihPph" name="nip">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</s:if>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div align="center">--%>
                                <%--<h4>C. Potongan</h4>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iur. Dp. Peg</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input readonly style="text-align: right"  type="text" class="form-control nip" id="iuranDapenPeg" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iur. Dp. Pers</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="iuranDapenPersh" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iur. Bpjs Tk Pegawai</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsTkPeg" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iur. Bpjs Tk Persh. </label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsTkPers" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iur. Bpjs Ks Peg. </label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsKsPeg" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iur. Bpjs Ks Pers. </label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsKsPers" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Pot. Pph</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input readonly style="text-align: right" readonly type="text" class="form-control nip" id="pphGaji1" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Pot. Lain-lain</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="totalPotonganLain" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Total C</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="totalC" name="nip">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<br>--%>
                            <%--<br>--%>
                            <%--<div align="center">--%>
                                <%--<h4>C. Rincian Potongan</h4>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Kopkar</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="kopkar" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iuran Sp</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="iuranSp" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iuran PIIKB</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="iuranPiiKb" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Bank BRI</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="bankBri" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Bank Mandiri</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="bankMandiri" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Infaq</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="infaq" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Perkes dan Obat</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="perkesDanObat" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Listrik</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="listrik" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Iuran Profesi</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="iuranProfesi" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Potongan Lain</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="text-align: right"  type="text" class="form-control nip" id="potonganLain" name="nip" readonly>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="modal-lembur" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:800px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--&lt;%&ndash;<font size="1" face="Courier New" >&ndash;%&gt;--%>
                <%--<center>--%>
                    <%--<table style="width: 100%;" id="tableLembur" class="tableLembur table table-bordered">--%>
                    <%--</table>--%>
                <%--</center>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="modal-gaji" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:800px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="formGaji">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-2" >Tanggal</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<div class="input-group date">--%>
                                <%--<div class="input-group-addon">--%>
                                    <%--<i class="fa fa-calendar"></i>--%>
                                <%--</div>--%>
                                <%--<input type="text" id="tanggaGajiAwal"  class="form-control pull-right"--%>
                                       <%--required="false"  cssStyle=""/>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >s/d</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<div class="input-group date">--%>
                                <%--<div class="input-group-addon">--%>
                                    <%--<i class="fa fa-calendar"></i>--%>
                                <%--</div>--%>
                                <%--<input type="text" id="tanggaGajiAkhir" class="form-control pull-right"--%>
                                       <%--required="false"  cssStyle=""/>--%>
                            <%--</div>--%>
                            <%--<label style="display:none;" id="tmpJmlGajiPkwt"></label>--%>
                            <%--<label style="display:none;" id="tmpJmlGajiPkwtNilai"></label>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-2" ></label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<a type="button" class="btn btn-primary btnCariGaji" style="margin-top: 10px"><i class="fa fa-search"></i> Cari</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
                <%--&lt;%&ndash;<font size="1" face="Courier New" >&ndash;%&gt;--%>
                <%--<table style="width: 100%;" id="tabelDetailGaji" class="tabelDetailGaji table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="modal-pphGaji" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:700px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>
                    <%--<h4>A. Penerimaan</h4>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Peralihan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Strategis</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjStrategis" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Kompensasi</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjKompensasi" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Transport</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjTransport" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Air Listrik</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjAirListrik" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Perumahan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPerumahan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Pengobatan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPengobatan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Pph</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPph" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Lembur</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjLembur" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Lain lain</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjLain" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Asumsi Thr</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right; background-color: #52cfeb" readonly type="text" class="form-control nip" id="pphGajiAsumsiThr" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Asumsi Pendidikan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right ; background-color: #52cfeb" readonly type="text" class="form-control nip" id="pphGajiAsumsiPendidikan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Asumsi Jasprod</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right; background-color: #52cfeb" readonly type="text" class="form-control nip" id="pphGajiAsumsiJasprod" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Thr</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiThr" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Pendidikan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPendidikan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Jasprod</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJasprod" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Insentif</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiInsentif" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Rapel</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiRapel" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Jubilium</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJubileum" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--&lt;%&ndash;<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Pensiun</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPensiun" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>&ndash;%&gt;--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Iuran Perusahan (Jkm+Jkk)</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJkmJkk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Pakaian Dinas</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPakaianDinas" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Bruto (A)</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBruto" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<br>--%>
                    <%--<br>--%>
                    <%--<h4>B. Pengurangan Penerimaan</h4>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4">Biaya Jabatan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBiayaJabatan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Total Biaya Pensiun</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiIuranPensiun" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Dana Pensiun</label>--%>
                        <%--<div class="col-sm-5">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBpjsDanaPensiun" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Bpjs JHT</label>--%>
                        <%--<div class="col-sm-5">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBpjsJht" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Bpjs Pensiun</label>--%>
                        <%--<div class="col-sm-5">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBpjsPensiun" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >PTKP</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPtkp" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Reduce (B)</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJumlahB" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<br>--%>
                    <%--<br>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >PKP (A-B)</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPkp" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Hutang Pph</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiHutangPph" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Pph Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiNilai" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
                <%--&lt;%&ndash;<font size="1" face="Courier New" >&ndash;%&gt;--%>
                <%--<table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-insentif" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Peralihan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Strategis</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjStrategis" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji Bruto (A)</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTotal" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Potongan Insentif</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifPot" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Pot. Insentif Individu</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifPotIndividu" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >SMK Standart</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifSmkStandart" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >SMK Huruf</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifSmkHuruf" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >SMK Angka</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifSmkAngka" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label style="padding-left: 0px" class="control-label col-sm-4" >Insentif Diterima</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="insentifDiterima" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-jubileum" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Masa Kerja</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumMasKer1" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >s/d</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumMasKer2" name="nip">--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-1" style="padding-left: 0px;">--%>
                            <%--<a href="javascript:;" class="detailJubileumMasaKerja">--%>
                                <%--<span style="font-size: 25px" class="glyphicon glyphicon-search"></span>--%>
                            <%--</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Golongan</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="jubileumGolongan" name="nip">--%>
                        <%--</div>--%>
                        <%--<label class="control-label col-sm-1" >/</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPoint" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>


                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Peralihan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Besarnya Jubileum  </label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTotal" name="nip">--%>
                        <%--</div>--%>
                        <%--<label class="control-label col-sm-2" style="text-align: left" >X 5</label>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Grand Total</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumGrandTotal" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >PPh beban karyawan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPph">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Netto diterimakan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumNetto">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a style="display: none" type="button" class="btn btn-primary" id="printJubileum"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-pop-up-pensiun" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:400px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal">--%>

                    <%--<div style="display: none" class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Payroll Id. </label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: left" type="text" class="form-control" id="popUpPensiunPayrollId" class="popUpPensiunPayrollId">--%>
                            <%--<input style="text-align: left" type="text" class="form-control" id="popUpPensiunMasaKerjaTahun" >--%>
                            <%--<input style="text-align: left" type="text" class="form-control" id="popUpPensiunMasaKerjaBulan" >--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >No Surat. </label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: left" type="text" class="form-control" id="popUpPensiunNoSurat" class="popUpPensiunNoSurat">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5">Tanggal SK. </label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: left" type="text" class="form-control" id="popUpPensiunTanggal" class="popUpPensiunTanggal">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-primary" id="btnPopUpPrintPensiun"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-jubileum-masaKerja" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog ">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-2" style="padding-right:0px;">PKWT</label>--%>
                        <%--<div class="col-sm-2" style="padding-right: 0px">--%>
                            <%--<input style="text-align: left; padding-left: 5px; padding-right: 3px;" readonly type="text" class="form-control nip" id="jubileumPkwt1" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >s/d</label>--%>

                        <%--<div class="col-sm-2" style="padding-right: 0px">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumPkwt2" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >=</label>--%>

                        <%--<div class="col-sm-4" style="padding-left: 0px">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumJumlahPkwt" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group" style="padding-right:0px;">--%>
                        <%--<label class="control-label col-sm-2" style="padding-left:0px; padding-right:0px;">Pegawai Tetap</label>--%>
                        <%--<div class="col-sm-2" style="padding-right:0px;">--%>
                            <%--<input style="text-align: left; padding-left: 5px; padding-right:0px;" readonly type="text" class="form-control nip" id="jubileumTetap1" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >s/d</label>--%>

                        <%--<div class="col-sm-2" style="padding-right:0px;">--%>
                            <%--<input style="text-align: left; padding-left: 5px; padding-right:0px;" readonly type="text" class="form-control nip" id="jubileumTetap2" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >=</label>--%>

                        <%--<div class="col-sm-4" style="padding-left: 0px">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumJumlahTetap" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-8" >Total Masa Kerja</label>--%>
                        <%--<div class="col-sm-4" style="padding-left: 0px">--%>
                            <%--<input readonly type="text" class="form-control nip" id="jubileumTotalMasaKerja" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-pensiun" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:650px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tanggal Aktif</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="pensiunTgl1" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >s/d</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="pensiunTgl2" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Golongan</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="pensiunGolongan" name="nip">--%>
                        <%--</div>--%>
                        <%--<label class="control-label col-sm-1" >/</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunPoint" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Masa Kerja</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunMasaKerjaTahun" name="nip">--%>
                        <%--</div>--%>
                        <%--<label style="text-align: left; padding-left: 0px" class="control-label col-sm-1" >Tahun</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunMasaKerjaBulan" name="nip">--%>
                        <%--</div>--%>
                        <%--<label style="text-align: left; padding-left: 0px" class="control-label col-sm-1" >Bulan</label>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Peralihan</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Jumlah (A)</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunJumlah" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Pensiun</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunUangPensiun" name="nip">--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">--%>
                            <%--<input style="text-align: center;" readonly type="text" class="form-control nip" id="pensiunFaktorPensiun" name="nip">--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunKaliPensiun" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Penghargaan</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunUangPenghargaan" name="nip">--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">--%>
                            <%--<input style="text-align: center;" readonly type="text" class="form-control nip" id="pensiunFaktorPenghargaan" name="nip">--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunKaliPenghargaan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Perumahan</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: center" readonly type="text" class="form-control nip" id="pensiunTotalPerumahan" name="nip">--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control" value="15%" name="nip">--%>
                        <%--</div>--%>


                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control" id="pensiunPerumahan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-offset-5  control-label col-sm-3" >Total Pensiun</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunBiaya" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-offset-5  control-label col-sm-3" >PPH Pensiun</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunPph" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-offset-5  control-label col-sm-3" >Netto diterima</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunNetto" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a style="display: none" type="button" class="btn btn-primary" id="printPensiun"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-thr" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Jumlah Bulan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrBulanAktif">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Strategis</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjStrategis" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Peralihan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>


                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Total</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTotal" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Pph</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjPph" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Final Tunjangan Thr</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="thrTotalBersih" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
                <%--&lt;%&ndash;<font size="1" face="Courier New" >&ndash;%&gt;--%>
                <%--<table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-primary" id="printThr"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-pendidikan" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Jumlah Bulan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanBulanAktif">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right"  readonly type="text" class="form-control nip" id="pendidikanTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Peralihan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Strategis</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjStrategis" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Kompensasi</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjKompensasi" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Air Listrik</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjAirListrik" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Total</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTotal" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Pph</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjPph" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Final Tunj.Pendidikan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTotalBersih" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>


                <%--</form>--%>
                <%--&lt;%&ndash;<font size="1" face="Courier New" >&ndash;%&gt;--%>
                <%--<table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-primary" id="printPendidikan"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-jasprod" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Masa Kerja</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jasprodMasKer1" name="nip">--%>
                        <%--</div>--%>

                        <%--<label style="padding-left: 0px" class="control-label col-sm-1" >Tahun</label>--%>

                        <%--<div class="col-sm-1" style="padding-left: 10px;">--%>
                            <%--<a href="javascript:;" class="detailJasprodMasaKerja" >--%>
                                <%--<span style="font-size: 25px" class="glyphicon glyphicon-search"></span>--%>
                            <%--</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Golongan</label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="jasprodGolongan" name="nip">--%>
                        <%--</div>--%>
                        <%--<label class="control-label col-sm-1" >/</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPoint" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodGaji" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Peralihan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjPeralihan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Strategis</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjStrategis" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji Bruto (A)</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTotalBruto" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji X Masa Kerja</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPengali" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Faktor</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodFaktor" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Nilai SMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodNilaiSmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Persen SMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPersenSmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >A. Perhitungan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPerhitungan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label style="padding-left: 0px" class="control-label col-sm-4" >B. Gaji Kotor X Faktor</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodGajiKotor" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >C. Tambahan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTambahan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Nilai Jasprod</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodNilai" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
                <%--&lt;%&ndash;<font size="1" face="Courier New" >&ndash;%&gt;--%>
                <%--<table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-primary" id="printJasprod"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-jasprod-masaKerja" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-3" style="padding-right:0px;">Tanggal Aktif</label>--%>
                        <%--<div class="col-sm-4" >--%>
                            <%--<input style="text-align: left; padding-left: 5px; padding-right: 3px;" readonly type="text" class="form-control nip" id="jasprodTglAktif" name="nip">--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >s/d</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jasprodTglAktifSekarang" name="nip">--%>
                        <%--</div>--%>

                    <%--</div>--%>

                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-rapel" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Gaji Golongan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelGajiGolongan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan UMK</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjUmk" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjJabStruktural" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Strategis</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStrategis" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tunjangan Air Listrik</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjAirListrik" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<hr>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Total</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTotal" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-primary" id="printRapel"> <i class="fa fa-save"></i> Print</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-detailTunjLain" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal">--%>
                <%--</form>--%>
                <%--<div class="table-responsive">--%>
                    <%--<table id="tableDetailTunjLain" class="tableDetailTunjLain table table-bordered">--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="modal-ptt" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<br>--%>
                <%--<div class="table-responsive">--%>
                    <%--<table id="tabelPtt" class="tabelPtt table table-bordered">--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="modal-ptt-setahun" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<center>--%>
                    <%--<table style="width: 100%;" id="tablePttSetahun" class="tablePttSetahun table table-bordered">--%>
                    <%--</table>--%>
                <%--</center>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-pph-11bulan" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<center>--%>
                    <%--<table style="width: 100%;" id="tablePph11Bulan" class="tablePph11Bulan table table-bordered">--%>
                    <%--</table>--%>
                <%--</center>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-pph-seharusnya" class="modal fade modal-md" role="dialog">--%>
    <%--<div class="modal-dialog ">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="container">--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >Total Pend. Bruto</label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotPendBruto">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >Total Tunj. PPh </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotTunjPph">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >Total Pend. Tidak Teratur </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotPendTidTer">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" ><b>Total Bruto</b> </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotBrut">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >Total Iuran Pegawai </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotIuran">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >Total Bijab </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotBijab">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" ><b>Total Reduce</b> </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modTotReduce">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >Netto Setahun </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modNetSetahun">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >PTKP </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modPtkp">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" >PKP </label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modPkp">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-2" ><b>PPH Seharusnya </b></label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<input style="text-align: right" readonly type="text" class="form-control" id="modPphSeh">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<br>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="modal-peralihan" class="modal fade" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Peralihan Gapok</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right"  type="text" class="form-control" readonly id="nilaiModPeralihanGapok">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Peralihan Sankhus</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right"  type="text" class="form-control" readonly id="nilaiModPeralihanSankhus">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5" >Peralihan Tunjangan</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<input style="text-align: right"  type="text" class="form-control" readonly id="nilaiModPeralihanTunjangan">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
                <%--<br>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
</body>

</html>

<script>

    $('#btnSave').click(function() {
        var payrollId = document.getElementById("payrollPegawaiId").value;
        var nip = document.getElementById("nip").value;
        var bulan = document.getElementById("bulan").value;
        var tahun = document.getElementById("tahun").value;

        //komponen rincian potongan
        var flagKoreksi = document.getElementById("flagKoreksi").value;
        var noteKoreksi = document.getElementById("noteKoreksi").value;

        console.log(flagKoreksi);
        if (confirm('Apakah Anda ingin menyimpan koreksi ?')) {

            PayrollAction.saveKoreksiAks(payrollId, nip, bulan, flagKoreksi, noteKoreksi, function(result) {

                if (result) {
                    alert('Catatan Koreksi telah tersimpan.');
                    $('#modal-edit').modal('hide');
                    $('#formEdit')[0].reset();
                } else {
                    alert('Error saat penyimpanan data, tolong di cek kembali atau hubungin admin.');
                }

            });

        }

    });

    //updated by ferdi, 01-12-2020, to view saved item payroll each nip
    $('.tablePayroll').on('click', '.item-view', function(){

        var payrollId = $(this).attr('data');

        PayrollAction.getDetailEdit(payrollId, function(itemData){
            // console.log(itemData);

            if(itemData.tipePayroll == "PY"){
                $('.detailLembur').show();
            }else{
                $('.detailLembur').hide();

                $('#kopkar').attr('readonly', true);
                $('#iuranSp').attr('readonly', true);
                $('#iuranPiiKb').attr('readonly', true);
                $('#bankBri').attr('readonly', true);
                $('#bankMandiri').attr('readonly', true);
                $('#infaq').attr('readonly', true);
                $('#perkesDanObat').attr('readonly', true);
                $('#listrik').attr('readonly', true);
                $('#iuranProfesi').attr('readonly', true);
                $('#potonganLain').attr('readonly', true);

            }

            $('#payrollPegawaiId').val(itemData.payrollId);
            $('#tipePegawai').val(itemData.tipePegawaiName);
            $('#unitId').val(itemData.branchName);
            $('#bulan').val(itemData.periodePayroll);
            $('#tahun').val(itemData.tahunPayroll);
            $('#nip').val(itemData.nip);
            $('#nama').val(itemData.namaPegawai);
            $('#gelar').val(itemData.gelar);

            if(itemData.npwpPegawai != null){
                $('#npwp').val(itemData.npwpPegawai);
            }else{
                $('#npwp').val("-");
            }
            $('#divisi').val(itemData.divisiName);
            $('#golongan').val(itemData.golonganName);
            $('#multifikator').val(itemData.multifikator);
            $('#jabatan').val(itemData.posisiName);
            $('#statusKeluarga').val(itemData.statusKeluarga);
            $('#jumlahAnak').val(itemData.jumlahAnak);
            // $('#gajiPensiun').val(itemData.gajiPensiun);
            $('#tipeDanaPensiun').val(itemData.danaPensiunName);
            $('#jenisPegawai').val(itemData.jenisPegawai);
            $('#tipePegawaiName').val(itemData.tipePegawaiName);
            $('#golonganDapenId').val(itemData.golonganDapen);
            $('#masaGolDapen').val(itemData.masaKerjaGol);

            //komponen A
            $('#gaji').val(itemData.gajiPokok);
            $('#sankhus').val(itemData.santunanKhusus);
            $('#tunjJabStruktural').val(itemData.tunjJabatan);
            $('#tunjStruktural').val(itemData.tunjStruktural);
            $('#tunjStrategis').val(itemData.tunjFungsional);
            $('#tunjPeralihanGapok').val(itemData.tunjPeralihanGapok);
            $('#tunjPeralihanSankhus').val(itemData.tunjPeralihanSankhus);
            $('#tunjPeralihanTunj').val(itemData.tunjPeralihanTunj);
            $('#tunjSiaga').val(itemData.tunjSiaga);
            $('#tunjLokasi').val(itemData.tunjLokal);
            $('#tunjSupervisi').val(itemData.tunjSupervisi);
            $('#tunjTambahan').val(itemData.tunjTambahan);
            $('#pemondokan').val(itemData.tunjPemondokan);
            $('#komunikasi').val(itemData.tunjKomunikasi);
            $('#tunjLembur').val(itemData.tunjLembur);
            $('#totalA').val(itemData.componentA);
            $('#gajiBersih').val(itemData.gajiBersih);

            //komponen B
            //RLAB
            $('#tunjRumah').val(itemData.tunjRumah);
            $('#tunjListrik').val(itemData.tunjListrik);
            $('#tunjAir').val(itemData.tunjAir);
            $('#tunjBbm').val(itemData.tunjBbm);
            $('#totalRlab').val(itemData.totalRLAB);
            $('#tunjDapen').val(itemData.tunjanganDapen);
            $('#tunjBpjsKs').val(itemData.tunjanganBpjsKs);
            $('#tunjBpjsTk').val(itemData.tunjanganBpjsTk);
            $('#tunjPph').val(itemData.tunjanganPph);
            $('#totalB').val(itemData.componentB);

            //komponen C
            $('#iuranDapenPeg').val(itemData.iuranDapenKary);
            $('#iuranDapenPersh').val(itemData.iuranDapenPersh);
            $('#iuranBpjsTkPeg').val(itemData.totalIuranBpjsTkKary);
            $('#iuranBpjsTkPers').val(itemData.totalIuranBpjsTkPers);
            $('#iuranBpjsKsPeg').val(itemData.iuranBpjsKsKary);
            $('#iuranBpjsKsPers').val(itemData.iuranBpjsKsPers);
            $('#potPphGaji').val(itemData.pphGaji);
            $('#totalPotonganLain').val(itemData.totalPotonganLain);
            $('#totalC').val(itemData.componentC);

            //detail komponen C potongan lain -lain
            $('#kopkar').val(itemData.kopkar);
            $('#iuranSp').val(itemData.iuranSp);
            $('#iuranPiiKb').val(itemData.iuranPiikb);
            $('#bankBri').val(itemData.bankBri);
            $('#bankMandiri').val(itemData.bankMandiri);
            $('#infaq').val(itemData.infaq);
            $('#perkesDanObat').val(itemData.perkesDanObat);
            $('#listrik').val(itemData.listrik);
            $('#iuranProfesi').val(itemData.iuranProfesi);
            $('#potonganLain').val(itemData.potonganLain);

            //pph bulan 12
            $('#pph11Bulan').val(itemData.totalPphUntilNov);
            $('#pphSeharusnya').val(itemData.pphSeharusnya);
            $('#selisihPph').val(itemData.selisihPph);

            //koreksi dari aks
            if (itemData.flagPrint == "Y"){
                $('#koreksiAks').hide();
            } else {
                if (itemData.flagKoreksi == "Y") {
                    document.getElementById("cekFlagKoreksi").checked = true;
                    $("#noteKoreksi").prop("disabled",false);

                    $('#noteKoreksi').val(itemData.noteKoreksi);
                    $('#flagKoreksi').val(itemData.flagKoreksi);
                } else {
                    document.getElementById("cekFlagKoreksi").checked = false;
                    $("#noteKoreksi").prop("disabled",true);
                }
            }

        });

        $('#modal-edit').find('.modal-title').text('Detail Payroll');
        $('#modal-edit').modal('show');
    });

    //updated by ferdi, 01-12-2020, to view total pph 21 until nov
    $('#btnViewTotalPPh11').on('click', function(){

        var payrollId = document.getElementById("payrollPegawaiId").value;

        var nip = document.getElementById("nip").value;
        var nip = document.getElementById("nip").value;
        var tahun = document.getElementById("tahun").value;
        var jumlah = "" ;

        $('.tablePph11Bulan').find('tbody').remove();
        $('.tablePph11Bulan').find('thead').remove();
        $('.tablePph11Bulan').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        PayrollAction.searchTotalPPh11Bulan(payrollId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Tipe</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>PPH</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.bulan + '</td>' +
                    '<td align="center">' + item.tipePayroll + '</td>' +
                    '<td align="center">' + item.pphGaji+ '</td>' +
                    "</tr>";
                jumlah = item.pphGaji;
            });
            tmp_table += '<tfoot >' +
                '<tr style="font-size: 12px;">' +
                '<td colspan = "2" style="text-align: center"> Jumlah PPh 11 Bulan</td>' +
                '<td style="text-align: center">'+jumlah+'</td>' +
                '</tr>' +
                '</tfoot>';
            $('.tablePph11Bulan').append(tmp_table);
        });
        $('#modal-pph-11bulan').find('.modal-title').text('Total PPH 11 Bulan');
        $('#modal-pph-11bulan').modal('show');
    });

    //updated by ferdi, 01-12-2020, to get list of lembur each item pegawai
    $('#detailLembur').on('click', function(){
        var nip = document.getElementById("nip").value;
        var branchId = document.getElementById("branchId").value;
        var bulan = document.getElementById("bulan").value;
        var tahun = document.getElementById("tahun").value;
        var jumlah = "" ;

        $('.tableLembur').find('tbody').remove();
        $('.tableLembur').find('thead').remove();
        $('.tableLembur').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        PayrollAction.searchDetailLembur(nip, branchId, bulan, tahun, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Jam Masuk</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Jam Keluar</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Lama Lembur</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Jam Lembur</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Biaya Lembur</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.stTanggal + '</td>' +
                    '<td align="center">' + item.jamMasuk+ '</td>' +
                    '<td align="center">' + item.jamKeluar+ '</td>' +
                    '<td align="center">' + item.lamaLembur+ '</td>' +
                    '<td align="center">' + item.jamLembur+ '</td>' +
                    '<td align="center">' + item.stBiayaLembur+ '</td>' +
                    "</tr>";
                jumlah = item.jumlahLembur;
            });
            tmp_table += '<tfoot >' +
                '<tr style="font-size: 12px;">' +
                '<td colspan = "5" style="text-align: center"> Jumlah Biaya Lembur</td>' +
                '<td style="text-align: center">'+jumlah+'</td>' +
                '</tr>' +
                '</tfoot>';
            $('.tableLembur').append(tmp_table);
        });
        $('#modal-lembur').find('.modal-title').text('List Lembur');
        $('#modal-lembur').modal('show');
    });

    window.menujuLink = function(param, param2){
        if (confirm('Kembali ke halaman utama '+param2+'?')) {
            window.location.href=param;
        }
    }

    $('.tablePayroll').on('click', '.item-print', function(){
        var payrollId = $(this).attr('data');
        var tipe = document.getElementById("tipePayroll").value;
        var branch = document.getElementById("branchId").value;

        if(tipe == "PY"){ //payroll bulanan
            //alert("Payroll");
            window.location.href = 'printReportPayroll_payroll?id='+payrollId;
        } else if(tipe == "TH"){ //THR
            window.location.href = 'printReportThr_payroll?id='+payrollId;
        } else if(tipe == "JP"){ //jasop
            window.location.href = 'printReportJasop_payroll?id='+payrollId;
        } else if(tipe == "IN"){ //insentive
            window.location.href = 'printReportInsentif_payroll?id='+payrollId;
        } else if(tipe == "CP"){ //cuti panjang
            window.location.href = 'printReportCutiPanjang_payroll?id='+payrollId;
        } else if(tipe == "CT"){ //cuti tahunnan
            window.location.href = 'printReportCutiTahunan_payroll?id='+payrollId;
        } else if(tipe == "RP"){ //rapel
            window.location.href = 'printReportRapel_payroll?id='+payrollId;
        } else if(tipe == "JB"){ //PMP / jubelium /prapensiun
            window.location.href = 'printReportPMP_payroll?id='+payrollId;
        } else if(tipe == "PN"){ //pensiun
            window.location.href = 'printReportPensiun_payroll?id='+payrollId;
        }
    });

    window.needKoreksi = function () {
        if (document.getElementById("cekFlagKoreksi").checked == true) {
            $("#flagKoreksi").val("Y");
            $("#noteKoreksi").prop("disabled",false);
        } else {
            $("#flagKoreksi").val("N");
            $("#noteKoreksi").prop("disabled",true);
        }
    }


    // $('.detailTunjlain').on('click', function(){
    //     var nip = document.getElementById("nip").value;
    //     var bulan = document.getElementById("bulan").value;
    //     var tahun = document.getElementById("tahun").value;
    //
    //     $('.tableDetailTunjLain').find('tbody').remove();
    //     $('.tableDetailTunjLain').find('thead').remove();
    //
    //     var tmp_table = "";
    //     var tmp_table2 = "<tbody>";
    //     var hasil = 0;
    //
    //     PayrollAction.payrollDetailTunjLain(bulan,tahun, nip, function(listdata){
    //         tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>No </th>" +
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Nama Tunjangan</th>" +
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>" +
    //                 "</tr></thead><tbody>";
    //         $.each(listdata, function (i, item) {
    //             tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
    //                     '<td align="left">' + (i+1) + '</td>' +
    //                     '<td align="left">' + item.namaTunjangan+ '</td>' +
    //                     '<td align="right">' + item.nilai+ '</td>' +
    //                     "</tr>";
    //         });
    //         tmp_table += "</tbody>";
    //         $('.tableDetailTunjLain').append(tmp_table);
    //     });
    //
    //     $('#modal-detailTunjLain').find('.modal-title').text('Detail Tunj. Lain');
    //     $('#modal-detailTunjLain').modal('show');
    // });
    //
    // $(document).ready(function() {
    //     var url_string = window.location.href ;
    //     var url = new URL(url_string);
    //     var bulan = url.searchParams.get("bulan");
    //     var tahun = url.searchParams.get("tahun");
    //     var branchId = url.searchParams.get("branchId");
    //     var tipe = url.searchParams.get("tipe");
    //
    //     $('#bulanPayroll').val(bulan).change();
    //     $('#tahunPayroll').val(tahun).change();
    //     $('#branchId').val(branchId).change();
    //     $('#tipe').val(tipe).change();
    //
    //     if(bulan != null){
    //         $('#txtTipeId').val(tipe);
    //         $('#txtBulanPayroll').val(bulan);
    //         $('#txtTahunPayroll').val(tahun);
    //         $('#txtBranchId').val(branchId);
    //     }else{
    //         var branchId = document.getElementById("txtBranchId").value;
    //         var tahun = document.getElementById("txtTahunPayroll").value;
    //         var bulan = document.getElementById("txtBulanPayroll").value;
    //         var tipe = document.getElementById("txtTipeId").value;
    //         var nip = document.getElementById("personName1").value;
    //         $('#personName2').val(nip);
    //         $('#bulanPayroll').val(bulan).change();
    //         $('#tahunPayroll').val(tahun).change();
    //         $('#branchId').val(branchId).change();
    //         $('#tipe').val(tipe).change();
    //     }
    //     if (tipe!="PR"){
    //         $('#detailTunjlain').hide();
    //         $('#detailLembur').hide();
    //         $('#btnDetailPtt').hide();
    //         if (bulan=="12"){
    //             $('#btnViewTotalPtt11').hide();
    //             $('#btnViewPPhSeharusnya').hide();
    //             $('#btnViewTotalPPh11').hide();
    //         }
    //     }
    //
    //     window.menujuLink = function(param, param2){
    //         if (confirm('Kembali ke halaman utama '+param2+'?')) {
    //             window.location.href=param;
    //         }
    //     }
    //
    //
    //     $('.detailJasprod').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDetailEditJasprod(payrollId, function(listdata){
    //             if(listdata != null){
    //                 $('#jasprodMasKer1').val(listdata.masaKerja);
    //                 $('#jasprodGaji').val(listdata.gajiGolongan);
    //                 $('#jasprodTunjUmk').val(listdata.tunjanganUmk);
    //                 $('#jasprodTunjStruktural').val(listdata.tunjanganStruktural);
    //                 $('#jasprodTunjPeralihan').val(listdata.tunjPeralihan);
    //                 $('#jasprodTunjStrategis').val(listdata.tunjanganStrategis);
    //
    //                 $('#jasprodTotalBruto').val(listdata.gajiBruto);
    //                 $('#jasprodPengali').val(listdata.gajiMasaKerja);
    //                 $('#jasprodFaktor').val(listdata.stFaktor);
    //                 $('#jasprodNilaiSmk').val(listdata.stNilaiSmk);
    //                 $('#jasprodPersenSmk').val(listdata.stPersenSmk);
    //                 $('#jasprodPerhitungan').val(listdata.perhitungan);
    //                 $('#jasprodGajiKotor').val(listdata.gajiMasaKerjaFaktor);
    //                 $('#jasprodTambahan').val(listdata.tambahan);
    //                 $('#jasprodNilai').val(listdata.nilaiJasprod);
    //             }else{
    //                 $('#jasprodGaji').val('0,00');
    //                 $('#jasprodTunjUmk').val('0,00');
    //                 $('#jasprodTunjStruktural').val('0,00');
    //                 $('#jasprodTunjJabStruktural').val('0,00');
    //                 $('#jasprodTunjStrategis').val('0,00');
    //             }
    //
    //         });
    //
    //         $('#modal-jasprod').find('.modal-title').text('Komposisi Jasprod');
    //         $('#modal-jasprod').modal('show');
    //     });
    //
    //     $('.detailInsentif').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //         PayrollAction.getDetailEditInsentif(payrollId, function(listdata){
    //             if(listdata != 'Y'){
    //
    //                 $('#insentifGaji').val(listdata.gajiGolongan);
    //                 $('#insentifTunjUmk').val(listdata.tunjanganUmk);
    //                 $('#insentifTunjStruktural').val(listdata.tunjanganStruktural);
    //                 $('#insentifTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //                 $('#insentifTunjPeralihan').val(listdata.tunjanganPeralihan);
    //                 $('#insentifTunjStrategis').val(listdata.tunjanganStrategis);
    //
    //                 $('#insentifTotal').val(listdata.jumlahBruto);
    //
    //                 $('#insentifPot').val(listdata.potonganinsentifNilai);
    //                 $('#insentifPotIndividu').val(listdata.potonganinsentifIndividu);
    //
    //                 $('#insentifSmkStandart').val(listdata.smkStandart);
    //                 $('#insentifSmkHuruf').val(listdata.smkHuruf);
    //                 $('#insentifSmkAngka').val(listdata.smkAngka);
    //
    //                 $('#insentifDiterima').val(listdata.insentifyangDiterima);
    //             }
    //
    //         });
    //
    //         $('#modal-insentif').find('.modal-title').text('Komposisi Insentif');
    //         $('#modal-insentif').modal('show');
    //     });
    //
    //     $('.detailRapel').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDetailEditRapel(payrollId, function(listdata){
    //             $('#rapelGajiGolongan').val(listdata.gajiGolongan);
    //             $('#rapelTunjUmk').val(listdata.tunjanganUmk);
    //             $('#rapelTunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#rapelTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //             $('#rapelTunjStrategis').val(listdata.tunjanganStrategis);
    //             $('#rapelTunjAirListrik').val(listdata.tunjanganAirListrik);
    //             $('#rapelTotal').val(listdata.totalRapel);
    //         });
    //
    //         $('#modal-rapel').find('.modal-title').text('Komposisi Rapel');
    //         $('#modal-rapel').modal('show');
    //     });
    //
    //     $('.detailThr').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDetailEditThr(payrollId, function(listdata){
    //             $('#thrBulanAktif').val(listdata.bulanAktif);
    //             $('#thrGaji').val(listdata.gajiGolongan);
    //             $('#thrTunjUmk').val(listdata.tunjanganUmk);
    //             $('#thrTunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#thrTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //             $('#thrTunjStrategis').val(listdata.tunjanganStrategis);
    //             $('#thrTunjPeralihan').val(listdata.tunjanganPeralihan);
    //             $('#thrTunjPph').val(listdata.tunjanganPph);
    //             $('#thrTotal').val(listdata.totalThr);
    //             $('#thrTotalBersih').val(listdata.totalThrBersih);
    //         });
    //
    //         $('#modal-thr').find('.modal-title').text('Komposisi THR');
    //         $('#modal-thr').modal('show');
    //     });
    //
    //     $('.detailPendidikan').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDetailEditPendidikan(payrollId, function(listdata){
    //             $('#pendidikanBulanAktif').val(listdata.bulanAktif);
    //             $('#pendidikanGaji').val(listdata.gaji);
    //             $('#pendidikanTunjUmk').val(listdata.tunjanganUmk);
    //             $('#pendidikanTunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#pendidikanTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //             $('#pendidikanTunjPeralihan').val(listdata.tunjanganPeralihan);
    //             $('#pendidikanTunjStrategis').val(listdata.tunjanganStrategis);
    //             $('#pendidikanTunjAirListrik').val(listdata.tunjanganAirListrik);
    //             $('#pendidikanTunjKompensasi').val(listdata.tunjanganKompensasi);
    //             $('#pendidikanTunjPph').val(listdata.tunjanganPph);
    //             $('#pendidikanTotal').val(listdata.totalPendidikan);
    //
    //             $('#pendidikanTotalBersih').val(listdata.totalPendidikanBersih);
    //         });
    //
    //         $('#modal-pendidikan').find('.modal-title').text('Komposisi Pendidikan');
    //         $('#modal-pendidikan').modal('show');
    //     });
    //
    //     $('.detailPphGaji').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDetailEditPph(payrollId, function(listdata){
    //             $('#pphGajiGaji').val(listdata.gaji);
    //             $('#pphGajiTunjUmk').val(listdata.tunjanganUmk);
    //             $('#pphGajiTunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#pphGajiTunjPeralihan').val(listdata.tunjanganPeralihan);
    //             $('#pphGajiTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //
    //             $('#pphGajiTunjStrategis').val(listdata.tunjanganStrategis);
    //             $('#pphGajiTunjKompensasi').val(listdata.tunjanganKompensasi);
    //             $('#pphGajiTunjTransport').val(listdata.tunjanganTransport);
    //             $('#pphGajiTunjAirListrik').val(listdata.tunjanganAirListrik);
    //             $('#pphGajiTunjPerumahan').val(listdata.tunjanganPerumahan);
    //             $('#pphGajiTunjPengobatan').val(listdata.tunjanganPengobatan);
    //             $('#pphGajiTunjPph').val(listdata.tunjanganPphTahun);
    //             $('#pphGajiTunjLembur').val(listdata.tunjanganLembur);
    //             $('#pphGajiTunjLain').val(listdata.tunjanganLainLain);
    //             $('#pphGajiAsumsiThr').val(listdata.asumsiThr);
    //             $('#pphGajiAsumsiPendidikan').val(listdata.asumsiPendidikan);
    //             $('#pphGajiAsumsiJasprod').val(listdata.asumsiJasprod);
    //             $('#pphGajiThr').val(listdata.thr);
    //             $('#pphGajiPendidikan').val(listdata.pendidikan);
    //             $('#pphGajiJasprod').val(listdata.jasprod);
    //             $('#pphGajiInsentif').val(listdata.insentif);
    //             $('#pphGajiRapel').val(listdata.rapel);
    //             $('#pphGajiJubileum').val(listdata.jubileum);
    //             /*$('#pphGajiPensiun').val(listdata.pensiun);*/
    //             $('#pphGajiJkmJkk').val(listdata.iuranJkmJkk);
    //             $('#pphGajiPakaianDinas').val(listdata.pakaianDinas);
    //             $('#pphGajiBruto').val(listdata.bruto);
    //
    //             $('#pphGajiBiayaJabatan').val(listdata.biayaJabatan);
    //             $('#pphGajiPtkp').val(listdata.ptkp);
    //             $('#pphGajiIuranPensiun').val(listdata.iuranPensiun);
    //             $('#pphGajiBpjsDanaPensiun').val(listdata.danaPensiun);
    //             $('#pphGajiBpjsJht').val(listdata.bpjsJht);
    //             $('#pphGajiBpjsPensiun').val(listdata.bpjsPensiun);
    //
    //             $('#pphGajiJumlahB').val(listdata.jumlahB);
    //             $('#pphGajiPkp').val(listdata.pkp);
    //             $('#pphGajiHutangPph').val(listdata.hutangPph);
    //
    //         });
    //
    //         $('#modal-pphGaji').find('.modal-title').text('Perhitungan Pph Gaji');
    //         $('#modal-pphGaji').modal('show');
    //     });
    //
    //     $('.detailJubileum').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDetailEditJubileum(payrollId, function(listdata){
    //             $('#jubileumGaji').val(listdata.gajiGolongan);
    //             $('#jubileumTunjUmk').val(listdata.tunjanganUmk);
    //             $('#jubileumTunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#jubileumTunjJabStruktural').val(listdata.tunjanganJabStruktural);
    //             $('#jubileumPeralihan').val(listdata.tunjanganPeralihan);
    //             $('#jubileumTotal').val(listdata.besarJubileum);
    //             $('#jubileumGrandTotal').val(listdata.totalJubileum);
    //             $('#jubileumPph').val(listdata.pphJubileum);
    //             $('#jubileumNetto').val(listdata.nettoJubileum);
    //         });
    //
    //         $('#modal-jubileum').find('.modal-title').text('Komposisi Jubileum');
    //         $('#modal-jubileum').modal('show');
    //     });
    //
    //     $('.detailJasprodMasaKerja').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         /*PayrollAction.getDetailAdd(nip, function(listdata){
    //             $('#jasprodTglAktif').val(listdata.stTanggalAktif);
    //             $('#jasprodTglAktifSekarang').val(listdata.stTanggalAktifSekarang);
    //             $('#jubileumJumlahPkwt').val(listdata.lamaPkwt);
    //         });*/
    //
    //         $('#modal-jasprod-masaKerja').find('.modal-title').text('Masa Kerja');
    //         $('#modal-jasprod-masaKerja').modal('show');
    //     });
    //
    //     $('.detailPensiun').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //         PayrollAction.getDetailEditPensiun(payrollId, function(listdata){
    //             $('#pensiunGaji').val(listdata.gajiGolongan);
    //             $('#pensiunTunjUmk').val(listdata.tunjanganUmk);
    //             $('#pensiunTunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#pensiunTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //             $('#pensiunPeralihan').val(listdata.tunjanganPeralihan);
    //
    //             $('#pensiunTgl1').val(listdata.stTanggalAktif);
    //
    //             //$('#pensiunMasaKerjaTahun').val(listdata.masaKerjaTahun);
    //             //$('#pensiunMasaKerjaBulan').val(listdata.masaKerjaBulan);
    //             $('#pensiunKaliMasaKerja').val(listdata.kaliMasaKerja);
    //
    //             $('#pensiunJumlah').val(listdata.jumlahTunjangan);
    //             $('#pensiunUangPensiun').val(listdata.jumlahTunjangan);
    //             $('#pensiunFaktorPensiun').val(listdata.faktorPensiun);
    //             $('#pensiunKaliPensiun').val(listdata.tunjanganPensiun);
    //             $('#pensiunUangPenghargaan').val(listdata.jumlahTunjangan);
    //             $('#pensiunFaktorPenghargaan').val(listdata.faktorPenghargaan);
    //             $('#pensiunKaliPenghargaan').val(listdata.tunjanganPenghargaan);
    //
    //             $('#pensiunTotalPerumahan').val(listdata.hasil);
    //             $('#pensiunPerumahan').val(listdata.penggantianPerumahan);
    //             $('#pensiunBiaya').val(listdata.totalPensiun);
    //             $('#pensiunPph').val(listdata.pphPensiun);
    //             $('#pensiunNetto').val(listdata.nettoPensiun);
    //         });
    //
    //         $('#modal-pensiun').find('.modal-title').text('Komposisi Pensiun');
    //         $('#modal-pensiun').modal('show');
    //     });
    //
    //     $('.tablePayroll').on('click', '.item-edit', function(){
    //         var payrollId = $(this).val().replace(/\n|\r/g, "");
    //         var payrollId = $(this).attr('data');
    //         $('#payrollId').val(payrollId);
    //         PayrollAction.getDetailEdit(payrollId, function(listdata){
    //             if(listdata.tipePegawai == "TP03" && listdata.strukturGaji != "G"){
    //                 $('.detailGaji').show();
    //             }else{
    //                 $('.detailGaji').hide();
    //             }
    //
    //             if(listdata.flagJasprod == 'Y'){
    //                 $('.detailJasprod').show();
    //             }else{
    //                 $('.detailJasprod').hide();
    //             }
    //
    //             if(listdata.flagInsentif == 'Y'){
    //                 $('.detailInsentif').show();
    //             }else{
    //                 $('.detailInsentif').hide();
    //             }
    //
    //             if(listdata.flagThr == 'Y'){
    //                 $('.detailThr').show();
    //             }else{
    //                 $('.detailThr').hide();
    //             }
    //             if(listdata.flagPayroll == "Y"){
    //                 $('.detailLembur').show();
    //                 $('.detailPphGaji').show();
    //             }else{
    //                 $('.detailPphGaji').show();
    //                 //$('.detailPphGaji').hide();
    //                 $('.detailLembur').hide();
    //                 $('#tunjLain').attr('readonly', true);
    //                 $('#tunjLain').attr('readonly', true);
    //                 $('#tunjPeralihan').attr('readonly', true);
    //                 $('#pemondokan').attr('readonly', true);
    //                 $('#komunikasi').attr('readonly', true);
    //                 $(".detailTunjlain").hide();
    //
    //
    //                 $('#kopkar').attr('readonly', true);
    //                 $('#iuranSp').attr('readonly', true);
    //                 $('#iuranPiiKb').attr('readonly', true);
    //                 $('#bankBri').attr('readonly', true);
    //                 $('#bankMandiri').attr('readonly', true);
    //                 $('#infaq').attr('readonly', true);
    //                 $('#perkesDanObat').attr('readonly', true);
    //                 $('#listrik').attr('readonly', true);
    //                 $('#iuranProfesi').attr('readonly', true);
    //                 $('#potonganLain').attr('readonly', true);
    //                 $('#pphGaji').attr('readonly', true);
    //             }
    //
    //             $('#payrollId2').val(listdata.payrollId);
    //             $('#bulan').val(listdata.bulan);
    //             $('#tahun').val(listdata.tahun);
    //             $('#nip').val(listdata.nip);
    //             $('#branchId2').val(listdata.branchId);
    //             $('#nama').val(listdata.nama);
    //             if(listdata.npwp != null){
    //                 $('#npwp').val(listdata.npwp);
    //             }else{
    //                 $('#npwp').val("-");
    //             }
    //             $('#divisi').val(listdata.departmentName);
    //             $('#golongan').val(listdata.golonganName);
    //             $('#point').val(listdata.point);
    //             $('#jabatan').val(listdata.positionName);
    //             $('#statusKeluarga').val(listdata.statusKeluarga);
    //             $('#jumlahAnak').val(listdata.jumlahAnak);
    //             $('#gajiPensiun').val(listdata.gajiPensiun);
    //             $('#tipeDanaPensiun').val(listdata.danaPensiunName);
    //             $('#tipePegawai').val(listdata.tipePegawai);
    //             $('#tipePegawaiName').val(listdata.tipePegawaiName);
    //             $('#golonganDapenId').val(listdata.golonganDapenName);
    //             $('#masaGolDapen').val(listdata.stMasaKerjaGol);
    //
    //             /*$('#multifikator').val(listdata.multifikator);
    //              $('#gajiBpjs').val(listdata.gajiBpjs);*/
    //
    //             //komponen A
    //             $('#gaji').val(listdata.gajiGolongan);
    //             $('#tunjUmk').val(listdata.tunjanganUmk);
    //             $('#tunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
    //             $('#tunjStruktural').val(listdata.tunjanganStruktural);
    //             $('#tunjStrategis').val(listdata.tunjanganStrategis);
    //             $('#tunjPeralihan').val(listdata.tunjanganPeralihan);
    //             $('#tunjTambahan').val(listdata.tunjanganTambahan);
    //             $('#tunjLain').val(listdata.tunjanganLain);
    //             $('#pemondokan').val(listdata.pemondokan);
    //             $('#komunikasi').val(listdata.komunikasi);
    //             $('#tambahanLain').val(listdata.tambahanLain);
    //             $('#tunjLembur').val(listdata.tunjanganLembur);
    //
    //
    //             //komponen B
    //             //RLAB
    //             $('#tunjRumah').val(listdata.tunjanganRumah);
    //             $('#tunjListrik').val(listdata.tunjanganListrik);
    //             $('#tunjAir').val(listdata.tunjanganAir);
    //             $('#tunjBbm').val(listdata.tunjanganBbm);
    //             $('#totalRlab').val(listdata.totalRlab);
    //             $('#tunjDapen').val(listdata.iuranDapenPersh);
    //             $('#tunjBpjsKs').val(listdata.tunjanganBpjsKs);
    //             $('#tunjBpjsTk').val(listdata.tunjanganBpjsTk);
    //             $('#tunjPph').val(listdata.tunjanganPph);
    //
    //             //komponen C
    //             $('#iuranDapenPeg').val(listdata.iuranDapenPeg);
    //             $('#iuranDapenPersh').val(listdata.iuranDapenPersh);
    //             $('#iuranBpjsTkPeg').val(listdata.iuranBpjsTkKary);
    //             $('#iuranBpjsTkPers').val(listdata.iuranBpjsTkPers);
    //             $('#iuranBpjsKsPeg').val(listdata.iuranBpjsKsKary);
    //             $('#iuranBpjsKsPers').val(listdata.iuranBpjsKsPersh);
    //             $('#pphGaji1').val(listdata.pphGaji);
    //             $('#totalPotonganLain').val(listdata.totalPotonganLain);
    //
    //             $('#nilaiPtt').val(listdata.lainLain);
    //             $('#tipePttId1').val(listdata.idLainLain).change();
    //
    //             //detail komponen C potongan lain -lain
    //             $('#kopkar').val(listdata.kopkar);
    //             $('#iuranSp').val(listdata.iuranSp);
    //             $('#iuranPiiKb').val(listdata.iuranPiikb);
    //             $('#bankBri').val(listdata.bankBri);
    //             $('#bankMandiri').val(listdata.bankMandiri);
    //             $('#infaq').val(listdata.infaq);
    //             $('#perkesDanObat').val(listdata.perkesDanObat);
    //             $('#listrik').val(listdata.listrik);
    //             $('#iuranProfesi').val(listdata.iuranProfesi);
    //             $('#potonganLain').val(listdata.potonganLain);
    //
    //             $('#nilaiModPeralihanGapok').val(listdata.stPeralihanGapok);
    //             $('#nilaiModPeralihanSankhus').val(listdata.stPeralihanSankhus);
    //             $('#nilaiModPeralihanTunjangan').val(listdata.stPeralihanTunjangan);
    //
    //             //Total
    //             $('#totalA').val(listdata.totalA);
    //             $('#totalB').val(listdata.totalB);
    //             $('#totalC').val(listdata.totalC);
    //             $('#gajiBersih').val(listdata.totalGajiBersih);
    //
    //             //pph bulan 12
    //             $('#totalPtt').val(listdata.totalLain11Bulan);
    //             $('#pph11Bulan').val(listdata.pph11Bulan);
    //             $('#pphSeharusnya').val(listdata.pphSeharusnya);
    //             $('#selisihPph').val(listdata.selisihPph);
    //         });
    //         //alert( $('#branchId1').text);
    //         $('#modal-edit').find('.modal-title').text('Detail Payroll');
    //         $('#modal-edit').modal('show');
    //         $('#myForm').attr('action', 'atasan');
    //     });
    //
    //     $('.tablePayroll').on('click', '.item-print', function(){
    //         var payrollId = $(this).attr('data');
    //         var tipe = document.getElementById("tipe").value;
    //         var branch = document.getElementById("branchId").value;
    //
    //         if(tipe == "PY"){
    //             //alert("Payroll");
    //             window.location.href = 'printReportPayroll_payroll?id='+payrollId+'&tipe='+tipe;
    //         }else if(tipe == "JB"){
    //             window.location.href = 'printReportJubileum_payroll?id='+payrollId+'&branchId='+branch;
    //         }else if(tipe == "PN"){
    //             window.location.href = 'printReportPayroll_payroll?id='+payrollId+'&branchId='+branch+'&tipe='+tipe;
    //         }else if(tipe == "IN"){
    //             window.location.href = 'printReportInsentif_payroll?id='+payrollId;
    //         }else if(tipe == "JP"){
    //             window.location.href = 'printReportJasprod_payroll?id='+payrollId;
    //         }else if(tipe == "T"){
    //             window.location.href = 'printReportThr_payroll?id='+payrollId;
    //         } else if(tipe == "CT"){
    //             window.location.href = 'printReportCutiTahunan_payroll?id='+payrollId;
    //         }else if(tipe == "CP"){
    //             window.location.href = 'printReportCutiPanjang_payroll?id='+payrollId;
    //         }
    //     });
    //
    //     $('#btnViewPPhSeharusnya').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         var tahun = document.getElementById("tahun").value;
    //         var totalA = document.getElementById("totalA").value;
    //         var totalRlab = document.getElementById("totalRlab").value;
    //         var tunjDapen = document.getElementById("tunjDapen").value;
    //         var tunjBpjsKs = document.getElementById("tunjBpjsKs").value;
    //         var tunjBpjsTk = document.getElementById("tunjBpjsTk").value;
    //         var iuranDapen = document.getElementById("iuranDapenPeg").value;
    //         var iuranBpjsKs = document.getElementById("iuranBpjsKsPeg").value;
    //         var iuranBpjsTk = document.getElementById("iuranBpjsTkPeg").value;
    //         var statusKeluarga = document.getElementById("statusKeluarga").value;
    //         var anak = document.getElementById("jumlahAnak").value;
    //         dwr.engine.setAsync(false);
    //         var tmp_table = "";
    //         PayrollAction.searchDetailPPhSeharusnya(nip,tahun,totalA,totalRlab,tunjDapen,tunjBpjsKs,tunjBpjsTk,iuranDapen,iuranBpjsKs,iuranBpjsTk,statusKeluarga,anak, function(data) {
    //             $('#modTotPendBruto').val(data.stTotPendBruto);
    //             $('#modTotTunjPph').val(data.stTotTunjPPh);
    //             $('#modTotPendTidTer').val(data.stTotPendTidakTeratur);
    //             $('#modTotBrut').val(data.stTotBrut);
    //             $('#modTotIuran').val(data.stTotIuran);
    //             $('#modTotBijab').val(data.stTotBijab);
    //             $('#modTotReduce').val(data.stTotReduce);
    //             $('#modNetSetahun').val(data.stNetSetahun);
    //             $('#modPtkp').val(data.stPtkp);
    //             $('#modPkp').val(data.stPkp);
    //             $('#modPphSeh').val(data.stPphSeharusnya);
    //         });
    //         $('#modal-pph-seharusnya').find('.modal-title').text('Perhitungan PPH 21');
    //         $('#modal-pph-seharusnya').modal('show');
    //     });
    //     $('#btnViewTotalPtt11').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         var tahun = document.getElementById("tahun").value;
    //         var jumlah = "" ;
    //
    //         $('.tablePttSetahun').find('tbody').remove();
    //         $('.tablePttSetahun').find('thead').remove();
    //         $('.tablePttSetahun').find('tfoot').remove();
    //         dwr.engine.setAsync(false);
    //         var tmp_table = "";
    //         PayrollAction.searchTotalPtt11Bulan(nip, tahun, function(listdata) {
    //             tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Jenis PTT</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>"+
    //                 "</tr></thead>";
    //             var i = i ;
    //             $.each(listdata, function (i, item) {
    //                 tmp_table += '<tr style="font-size: 12px;" ">' +
    //                     '<td align="center">' + item.bulan + '</td>' +
    //                     '<td align="center">' + item.tipePttName+ '</td>' +
    //                     '<td align="center">' + item.nilai+ '</td>' +
    //                     "</tr>";
    //                 jumlah = item.stJumlahPtt;
    //             });
    //             tmp_table += '<tfoot >' +
    //                 '<tr style="font-size: 12px;">' +
    //                 '<td colspan = "2" style="text-align: center"> Jumlah PTT 11 Bulan</td>' +
    //                 '<td style="text-align: center">'+jumlah+'</td>' +
    //                 '</tr>' +
    //                 '</tfoot>';
    //             $('.tablePttSetahun').append(tmp_table);
    //         });
    //         $('#modal-ptt-setahun').find('.modal-title').text('PTT 11 Bulan');
    //         $('#modal-ptt-setahun').modal('show');
    //     });
    //     $('#btnViewTotalPPh11').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         var tahun = document.getElementById("tahun").value;
    //         var jumlah = "" ;
    //
    //         $('.tablePph11Bulan').find('tbody').remove();
    //         $('.tablePph11Bulan').find('thead').remove();
    //         $('.tablePph11Bulan').find('tfoot').remove();
    //         dwr.engine.setAsync(false);
    //         var tmp_table = "";
    //
    //         PayrollAction.searchTotalPPh11Bulan(nip,tahun, function(listdata) {
    //             tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc'>Tipe</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>PPH</th>"+
    //                 "</tr></thead>";
    //             var i = i ;
    //             $.each(listdata, function (i, item) {
    //                 tmp_table += '<tr style="font-size: 12px;" ">' +
    //                     '<td align="center">' + item.bulan + '</td>' +
    //                     '<td align="center">' + item.jenisPayroll + '</td>' +
    //                     '<td align="center">' + item.stNilai+ '</td>' +
    //                     "</tr>";
    //                 jumlah = item.stJumlahNilai;
    //             });
    //             tmp_table += '<tfoot >' +
    //                 '<tr style="font-size: 12px;">' +
    //                 '<td colspan = "2" style="text-align: center"> Jumlah PPh 11 Bulan</td>' +
    //                 '<td style="text-align: center">'+jumlah+'</td>' +
    //                 '</tr>' +
    //                 '</tfoot>';
    //             $('.tablePph11Bulan').append(tmp_table);
    //         });
    //         $('#modal-pph-11bulan').find('.modal-title').text('Total PPH 11 Bulan');
    //         $('#modal-pph-11bulan').modal('show');
    //     });
    //
    //     $('#btnDetailLain').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         var payrollId = document.getElementById("payrollId").value;
    //         var keterangan1 = document.getElementById("lainLainKet1").value;
    //         var keterangan2 = document.getElementById("lainLainKet2").value;
    //         var keterangan3 = document.getElementById("lainLainKet3").value;
    //         var keterangan4 = document.getElementById("lainLainKet4").value;
    //         var keterangan5 = document.getElementById("lainLainKet5").value;
    //
    //         var biaya1 = document.getElementById("lainLainBiaya1").value;
    //         var biaya2= document.getElementById("lainLainBiaya2").value;
    //         var biaya3 = document.getElementById("lainLainBiaya3").value;
    //         var biaya4 = document.getElementById("lainLainBiaya4").value;
    //         var biaya5 = document.getElementById("lainLainBiaya5").value;
    //         var total = document.getElementById("jumlahDetailLainLain").value;
    //
    //         if(biaya1 == ''){
    //             biaya1 = '0';
    //         }
    //         if(biaya2 == ''){
    //             biaya2 = '0';
    //         }
    //         if(biaya3 == ''){
    //             biaya3 = '0';
    //         }
    //         if(biaya4 == ''){
    //             biaya4 = '0';
    //         }
    //         if(biaya5 == ''){
    //             biaya5 = '0';
    //         }
    //
    //         PayrollAction.savePotonganLainData(payrollId, keterangan1, keterangan2, keterangan3, keterangan4, keterangan5, biaya1, biaya2, biaya3,
    //                 biaya4, biaya5, total, function(listdata){
    //                     alert('Record has been saved successfully');
    //                 });
    //     });
    //
    //     $('.detailGaji').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //
    //         $('#modal-gaji').find('.modal-title').text('Detail Gaji');
    //         $('#modal-gaji').modal('show');
    //     });
    //     $('#tanggaGajiAkhir').datepicker({
    //         dateFormat: 'dd-mm-yy',
    //         changeMonth: true,
    //         changeYear: true
    //     });
    //     $('#tanggaGajiAwal').datepicker({
    //         dateFormat: 'dd-mm-yy',
    //         changeMonth: true,
    //         changeYear: true
    //     });
    //     $('.btnCariGaji').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         var branchId = document.getElementById("branchId").value;
    //         var tglAwal = document.getElementById("tanggaGajiAwal").value;
    //         var tglAkhir = document.getElementById("tanggaGajiAkhir").value;
    //
    //         if(tglAwal == '' || tglAkhir == ''){
    //             alert('Tanggal Awal dan Tanggal Akhir harus diisi!');
    //         }else{
    //             $('.tabelDetailGaji').find('tbody').remove();
    //             $('.tabelDetailGaji').find('thead').remove();
    //             $('.tabelDetailGaji').find('tfoot').remove();
    //             dwr.engine.setAsync(false);
    //             var tmp_table = "";
    //             PayrollAction.listGajiHarianData(nip, branchId, tglAwal, tglAkhir, function(listdata) {
    //                 tmp_table = "<thead style='color: white' ><tr class='active'>"+
    //                         "<th style='text-align: center; background-color:  #3c8dbc'><input type='checkbox' class='checkApprove' id='checkAll'></th>"+
    //                         "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
    //                         "<th style='text-align: center; background-color:  #3c8dbc''>Absen Masuk</th>"+
    //                         "<th style='text-align: center; background-color:  #3c8dbc''>Absen Keluar</th>"+
    //                         "<th style='text-align: center; background-color:  #3c8dbc''>Hari</th>"+
    //                         "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
    //                         "</tr></thead>";
    //                 var i = i ;
    //                 $.each(listdata, function (i, item) {
    //                     document.getElementById("tmpJmlGajiPkwt").innerHTML  = item.gaji;
    //                     document.getElementById("tmpJmlGajiPkwtNilai").innerHTML  = item.gajiNilai;
    //                     var combo = "";
    //                     if(item.status == "Y"){
    //                         combo = '<input disabled type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.stTnggal+'" class="checkApprove" ' +
    //                                 '>';
    //                     }else{
    //                         if(item.checked == "Y"){
    //                             combo = '<input checked type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.stTnggal+'" class="checkApprove" ' +
    //                                     '';
    //                         }else{
    //                             combo = '<input type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.stTnggal+'" class="checkApprove" ' +
    //                                     '>';
    //                         }
    //                     }
    //
    //                     tmp_table += '<tr  ">' +
    //                             '<td align="center">' + combo + '</td>' +
    //                             '<td align="center">' + item.stTnggal + '</td>' +
    //                             '<td align="center">' + item.absenMasuk + '</td>' +
    //                             '<td align="center">' + item.absenKeluar  + '</td>' +
    //                             '<td align="center">' + item.hari + '</td>' +
    //                             '<td align="center">' + item.status + '</td>' +
    //                             "</tr>";
    //                 });
    //                 var label = $('#tmpJmlGajiPkwt');
    //                 tmp_table += '' +
    //                         '<tfoot> ' +
    //                         '<tr>' +
    //                         '<td colspan="3">Grand Total</td>' +
    //                         '<td><label id="jmlAbsenPkwt"></label></td>' +
    //                         '<td><label id="jmlGajiPkwt">'+label.text()+'</label></td>' +
    //                         '<td><label id="totalGajiPkwt"></label></td>' +
    //                         '</tr>' +
    //                         '</tfoot>';
    //                 $('.tabelDetailGaji').append(tmp_table);
    //                 hitungCentang();
    //                 $("#checkAll").change(function(){
    //                     $('input:checkbox').not(this).not("[disabled]").prop('checked', this.checked);
    //                     hitungCentang();
    //                 });
    //             });
    //         }
    //     });
    //
    //     $('.detailBiayaLainLain').on('click', function(){
    //         var payrollId = document.getElementById("payrollId").value;
    //
    //         PayrollAction.getDataPotonganLain(payrollId, function(listdata){
    //             if(listdata != null){
    //                 $('#lainLainKet1').val(listdata.keterangan1);
    //                 $('#lainLainKet2').val(listdata.keterangan2);
    //                 $('#lainLainKet3').val(listdata.keterangan3);
    //                 $('#lainLainKet4').val(listdata.keterangan4);
    //                 $('#lainLainKet5').val(listdata.keterangan5);
    //
    //                 $('#lainLainBiaya1').val(listdata.nilai1);
    //                 $('#lainLainBiaya2').val(listdata.nilai2);
    //                 $('#lainLainBiaya3').val(listdata.nilai3);
    //                 $('#lainLainBiaya4').val(listdata.nilai4);
    //                 $('#lainLainBiaya5').val(listdata.nilai5);
    //                 $('#jumlahDetailLainLain').val(listdata.total);
    //             }else{
    //                 $('#lainLainKet1').val("");
    //                 $('#lainLainKet2').val("");
    //                 $('#lainLainKet3').val("");
    //                 $('#lainLainKet4').val("");
    //                 $('#lainLainKet5').val("");
    //
    //                 $('#lainLainBiaya1').val("0");
    //                 $('#lainLainBiaya2').val("0");
    //                 $('#lainLainBiaya3').val("0");
    //                 $('#lainLainBiaya4').val("0");
    //                 $('#lainLainBiaya5').val("0");
    //                 $('#jumlahDetailLainLain').val("0");
    //
    //             }
    //         });
    //
    //         $('#modal-biayaLainLain').find('.modal-title').text('Detail Lain - Lain');
    //         $('#modal-biayaLainLain').modal('show');
    //     });
    //
    //     $('#detailLembur').on('click', function(){
    //         var nip = document.getElementById("nip").value;
    //         var branchId = document.getElementById("branchId").value;
    //         var unit = document.getElementById("branchId").value;
    //         var bulan = document.getElementById("bulan").value;
    //         var tahun = document.getElementById("tahun").value;
    //         var jumlah = "" ;
    //
    //         $('.tableLembur').find('tbody').remove();
    //         $('.tableLembur').find('thead').remove();
    //         $('.tableLembur').find('tfoot').remove();
    //         dwr.engine.setAsync(false);
    //         var tmp_table = "";
    //
    //         PayrollAction.searchDetailLembur(nip, branchId, bulan, tahun, function(listdata) {
    //             tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Jam Masuk</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Jam Keluar</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Lama Lembur</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Jam Lembur</th>"+
    //                 "<th style='text-align: center; background-color:  #3c8dbc''>Biaya Lembur</th>"+
    //                 "</tr></thead>";
    //             var i = i ;
    //             $.each(listdata, function (i, item) {
    //                 tmp_table += '<tr style="font-size: 12px;" ">' +
    //                     '<td align="center">' + item.stTanggal + '</td>' +
    //                     '<td align="center">' + item.jamMasuk+ '</td>' +
    //                     '<td align="center">' + item.jamKeluar+ '</td>' +
    //                     '<td align="center">' + item.lamaLembur+ '</td>' +
    //                     '<td align="center">' + item.jamLembur+ '</td>' +
    //                     '<td align="center">' + item.stBiayaLembur+ '</td>' +
    //                     "</tr>";
    //                 jumlah = item.jumlahLembur;
    //             });
    //             tmp_table += '<tfoot >' +
    //                 '<tr style="font-size: 12px;">' +
    //                 '<td colspan = "5" style="text-align: center"> Jumlah Biaya Lembur</td>' +
    //                 '<td style="text-align: center">'+jumlah+'</td>' +
    //                 '</tr>' +
    //                 '</tfoot>';
    //             $('.tableLembur').append(tmp_table);
    //         });
    //         $('#modal-lembur').find('.modal-title').text('List Lembur');
    //         $('#modal-lembur').modal('show');
    //     });
    //
    //     $('#btnApprove').click(function(){
    //         var values = new Array();
    //         $.each($("input[name='checkApprove[]']:checked"), function() {
    //             values.push($(this).val());
    //         });
    //
    //         var label = $('#tmpJmlGajiPkwtNilai');
    //         var gaji = parseInt(label.text()) * parseInt(values.length);
    //
    //         if(values.length > 0){
    //             if (confirm('Are you sure you want to save this Record?')) {
    //                 dwr.engine.setAsync(false);
    //                 var nip = document.getElementById('nip').value;
    //                 var payrollId = document.getElementById('payrollId').value;
    //                 PayrollAction.removeDataGajiPkwt(payrollId);
    //                 $.each($("input[name='checkApprove[]']:checked"), function() {
    //                     PayrollAction.saveDataGajiPkwt(payrollId, $(this).val(), function(listdata) {
    //                         $('#modal-gaji').modal('hide');
    //                         $('#formGaji')[0].reset();
    //                         $('.tabelDetailGaji').find('tbody').remove();
    //                         $('.tabelDetailGaji').find('thead').remove();
    //                         $('.tabelDetailGaji').find('tfoot').remove();
    //                     });
    //                 });
    //                 $('#gaji').val(gaji);
    //                 changeJubileumNPensiun();
    //             }
    //         }else{
    //             alert('Silahkan Centang Tanggal yang akan di Approve !');
    //         }
    //     });
    //
    //     $('.tablePayroll').on('click', '.item-promosi', function(){
    //         var nip = $(this).attr('nip');
    //         var payrollId = $(this).attr('payrollId');
    //         var masaKerja = 0 ;;
    //         PayrollAction.getDetailEdit(payrollId, function(listdata){
    //             $('#promosiNip').val(listdata.nip);
    //             $('#promosiNama').val(listdata.nama);
    //             $('#promosiBagian').val(listdata.departmentName);
    //             $('#promosiJabatan').val(listdata.positionName);
    //             if(listdata.golonganName == null){
    //                 $('#promosiGolongan').val("-");
    //             }else{
    //                 $('#promosiGolongan').val(listdata.golonganName);
    //             }
    //             $('#promosiPoin').val(listdata.point);
    //             masaKerja = listdata.masaKerjaTahun;
    //         });
    //
    //         $('.tabelDetailPromosi').find('tbody').remove();
    //         $('.tabelDetailPromosi').find('thead').remove();
    //         $('.tabelDetailPromosi').find('tfoot').remove();
    //         dwr.engine.setAsync(false);
    //         var tmp_table = "";
    //         PayrollAction.listPromosi(nip, function(listdata) {
    //             tmp_table = "<thead style='color: white' ><tr class='active'>"+
    //                     "<th style='text-align: center; background-color:  #3c8dbc'>Tahun</th>"+
    //                     "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>"+
    //                     "<th style='text-align: center; background-color:  #3c8dbc''>Nilai Prestasi</th>"+
    //                     "<th style='text-align: center; background-color:  #3c8dbc''>Poin</th>"+
    //                     "</tr></thead>";
    //             var i = i ;
    //             var totalPoin = 0;
    //             $.each(listdata, function (i, item) {
    //                 tmp_table += '<tr  ">' +
    //                         '<td align="center">' + item.periode + '</td>' +
    //                         '<td align="center">' + item.nilai + '</td>' +
    //                         '<td align="center">' + item.nilaiPrestasi + '</td>' +
    //                         '<td align="center">' + item.poin+ '</td>' +
    //                         "</tr>";
    //                 totalPoin += item.poin;
    //             });
    //             tmp_table += '' +
    //                     '<tfoot> ' +
    //                     '<tr>' +
    //                     '<td colspan="3" align="right">Total Poin</td>' +
    //                     '<td align="center">'+totalPoin+'</td>' +
    //                     '</tr>' +
    //                     '<tr>' +
    //                     '<td colspan="3" align="right">Masa Kerja (Tahun)</td>' +
    //                     '<td align="center">'+masaKerja+'</td>' +
    //                     '</tr>' +
    //                     '</tfoot>';
    //             $('.tabelDetailPromosi').append(tmp_table);
    //         });
    //
    //         $('#modal-promosi').find('.modal-title').text('Detail Pencapaian SMK');
    //         $('#modal-promosi').modal('show');
    //     });
    //
    // });
    //
    // window.hitungCentang = function(){
    //     var label = $('#tmpJmlGajiPkwtNilai');
    //     var values = new Array();
    //     $.each($("input[name='checkApprove[]']:checked"), function() {
    //         values.push($(this).val());
    //     });
    //     var gaji = parseInt(label.text()) * parseInt(values.length);
    //
    //     document.getElementById("jmlAbsenPkwt").innerHTML  = values.length;
    //     document.getElementById("totalGajiPkwt").innerHTML  = gaji;
    //
    // }
    //
    // window.hitungLainLain = function() {
    //     var biaya1 = document.getElementById("lainLainBiaya1").value;
    //     var biaya2= document.getElementById("lainLainBiaya2").value;
    //     var biaya3 = document.getElementById("lainLainBiaya3").value;
    //     var biaya4 = document.getElementById("lainLainBiaya4").value;
    //     var biaya5 = document.getElementById("lainLainBiaya5").value;
    //
    //     if(biaya1 == ''){
    //         biaya1 = '0';
    //     }
    //     if(biaya2 == ''){
    //         biaya2 = '0';
    //     }
    //     if(biaya3 == ''){
    //         biaya3 = '0';
    //     }
    //     if(biaya4 == ''){
    //         biaya4 = '0';
    //     }
    //     if(biaya5 == ''){
    //         biaya5 = '0';
    //     }
    //
    //     var biaya = 0 ;
    //     hasil = parseInt(biaya1) + parseInt(biaya2) + parseInt(biaya3) +parseInt(biaya4) + parseInt(biaya5) ;
    //
    //     document.getElementById("jumlahDetailLainLain").value = hasil;
    //     document.getElementById("lainLain").value = hasil;
    // }
    //
    // $('.detailJubileumMasaKerja').on('click', function(){
    //     var nip = document.getElementById("nip").value;
    //
    //     PayrollAction.searchJubileumMasaKerjaDetail(nip, function(listdata){
    //         $('#jubileumPkwt1').val(listdata.stTanggalPkwtAwal);
    //         $('#jubileumPkwt2').val(listdata.stTanggalPkwtAkhir);
    //         $('#jubileumJumlahPkwt').val(listdata.lamaPkwt);
    //         $('#jubileumTetap1').val(listdata.stTanggalPegawaiTetapAwal);
    //         $('#jubileumTetap2').val(listdata.stTanggalPegawaiTetapAkhir);
    //         $('#jubileumJumlahTetap').val(listdata.lamaPegawaiTetap);
    //         $('#jubileumTotalMasaKerja').val(listdata.masaKerja);
    //     });
    //
    //     $('#modal-jubileum-masaKerja').find('.modal-title').text('Detail Jubileum');
    //     $('#modal-jubileum-masaKerja').modal('show');
    // });
    //
    // $('#printJasprod').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     window.location.href = 'printReportJasprod_payroll?id='+payrollId;
    // });
    //
    // $('#printThr').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     window.location.href = 'printReportThr_payroll?id='+payrollId;
    // });
    //
    // $('#printRapel').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     window.location.href = 'printReportRapel_payroll?id='+payrollId;
    // });
    //
    // $('#printPendidikan').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     window.location.href = 'printReportPendidikan_payroll?id='+payrollId;
    // });
    //
    // $('#printJubileum').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     window.location.href = 'printReportJubileum_payroll?id='+payrollId;
    // });
    //
    // $('#printJubileum').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     window.location.href = 'printReportJubileum_payroll?id='+payrollId;
    // });
    //
    // $('#printPensiun').click(function(){
    //     var payrollId = document.getElementById("payrollId").value;
    //     var tahun = document.getElementById("pensiunMasaKerjaTahun").value;
    //     var bulan = document.getElementById("pensiunMasaKerjaBulan").value;
    //     window.location.href = 'printReportPensiun_payroll?id='+payrollId+'&tahun='+tahun+'&bulan='+bulan;
    // });

</script>