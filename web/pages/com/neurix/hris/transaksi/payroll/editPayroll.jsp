<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserLoginAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <style>
        .nopadding {
            padding-right:0;
            padding-left:0;
        }
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

        $.subscribe('errorDialogSearch', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function setRequestAction(){

            var tipe = $('#tipeForm').val();

            document.editPayrollForm.action = 'edit_payroll.action';

            if('reload' == tipe) {

                if(confirm('Apakah akan reload initial data untuk edit kembali ?')) {

                    $('#waiting_dialog_search').dialog('open'); //show dialog loading

                    document.getElementById('nip').value = '';
                    document.getElementById('pegawaiName').value = '';

                    document.getElementById('editSearch').value = false;
                    document.getElementById('reloadData').value = true;
                    document.editPayrollForm.submit();

                } else {

                    return false;

                }

            } else if ('search' == tipe) {

                document.getElementById('editSearch').value = true;
                document.editPayrollForm.submit();

            } else if ('reset' == tipe) {

                document.getElementById('nip').value = '';
                document.getElementById('pegawaiName').value = '';
                document.getElementById('editSearch').value = false;
                document.getElementById('reloadData').value = false;
                document.editPayrollForm.submit();
            }
        }

        // $.subscribe('beforeProcessReload', function (event, data) {
        //
        //     // $('#tipeForm').val('reload');
        //
        //     // document.getElementById('tipeForm').value =  'reload';
        //     // alert(document.getElementById('tipeForm').value);
        //
        //     if (confirm('Apakah akan reload initial data untuk edit kembali ?')) {
        //         event.originalEvent.options.submit = true;
        //         $.publish('showDialog');
        //     } else {
        //         // Cancel Submit comes with 1.8.0
        //         event.originalEvent.options.submit = false;
        //     }
        //
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
            Edit Payroll
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-edit"></i> Edit Payroll</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="editPayrollForm" method="post" theme="simple" cssClass="form-horizontal" onsubmit="return setRequestAction();">
                                        <%--<s:hidden name="addOrEdit"/>--%>
                                        <%--<s:hidden name="delete"/>--%>

                                        <s:hidden id="editSearch" name="editSearch"/>
                                        <s:hidden id="reloadData" name="reloadData"/>
                                        <s:hidden name="payrollHeader.payrollHeaderId"/>
                                        <s:hidden id="tipeForm"/>
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
                                                    <label class="control-label"><small>Tipe Payroll :</small></label>
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
                                                    <label class="control-label"><small>Nama Pegawai:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:textfield cssStyle="display: none" id="txtTipeId" name="payroll.tipe" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="txtBranchId" name="payroll.branchId" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="txtBulanPayroll" name="payroll.bulan" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="txtTahunPayroll" name="payroll.tahun" required="false" readonly="false" cssClass="form-control"/>--%>
                                                            <%--<s:textfield cssStyle="display: none" id="personName1" name="payroll.nip" required="false" readonly="false" cssClass="form-control"/>--%>
                                                        <s:hidden id="pegawaiId" name="payrollHeader.nip" />
                                                        <s:textfield id="pegawaiName" name="payrollHeader.pegawaiName" cssClass="form-control"/>
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

                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="editPayrollForm" id="search" name="search" onclick="$('#tipeForm').val('search');"
                                                                   onClickTopics="showDialogSearch" onCompleteTopics="closeDialog" onErrorTopics="errorDialogSearch">
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>

                                                    </td>

                                                    <td>

                                                        <sj:submit type="button" cssClass="btn btn-success" formIds="editPayrollForm" id="reload" name="reload" onclick="$('#tipeForm').val('reload'); "
                                                                   onCompleteTopics="closeDialog" onErrorTopics="errorDialogSearch">
                                                            <i class="fa fa-refresh"></i>
                                                            Reload
                                                        </sj:submit>

                                                        <%--<s:url var="urlEdit" namespace="/payroll" action="edit_payroll" escapeAmp="false">--%>
                                                            <%--<s:param name="id"><s:property value="%{payrollHeader.payrollHeaderId}"/></s:param>--%>
                                                            <%--<s:param name="bulan"><s:property value="%{payrollHeader.bulan}"/></s:param>--%>
                                                            <%--<s:param name="tahun"><s:property value="%{payrollHeader.tahun}"/></s:param>--%>
                                                            <%--<s:param name="branchId"><s:property value="%{payrollHeader.branchId}"/></s:param>--%>
                                                            <%--<s:param name="tipePayroll"><s:property value="%{payrollHeader.tipePayroll}"/></s:param>--%>
                                                        <%--</s:url>--%>

                                                        <%--<button type="button" class="btn btn-success" onclick="--%>
                                                                <%--if (confirm('Apakah akan reload initial data untuk edit kembali ?')) {--%>
                                                                    <%--window.location.href='<s:url action="edit_payroll"><s:param name="id"><s:property value="%{payrollHeader.payrollHeaderId}"/></s:param><s:param name="bulan"><s:property value="%{payrollHeader.bulan}"/></s:param><s:param name="tahun"><s:property value="%{payrollHeader.tahun}"/></s:param><s:param name="branchId"><s:property value="%{payrollHeader.branchId}"/></s:param><s:param name="tipePayroll"><s:property value="%{payrollHeader.tipePayroll}"/></s:param><s:param name="reloadData">true</s:param></s:url>';--%>
                                                                <%--};">--%>
                                                            <%--<i class="fa fa-refresh"></i> Reload--%>
                                                        <%--</button>--%>
                                                    </td>

                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-danger" formIds="editPayrollForm" id="reset" name="reset" onclick="$('#tipeForm').val('reset'); "
                                                                   onClickTopics="showDialogSearch" onCompleteTopics="closeDialog" onErrorTopics="errorDialogSearch">
                                                            <i class="fa fa-refresh"></i>
                                                            Reset
                                                        </sj:submit>

                                                            <%--<s:url var="urlEdit" namespace="/payroll" action="edit_payroll" escapeAmp="false">--%>
                                                            <%--<s:param name="id"><s:property value="%{payrollHeader.payrollHeaderId}"/></s:param>--%>
                                                            <%--<s:param name="bulan"><s:property value="%{payrollHeader.bulan}"/></s:param>--%>
                                                            <%--<s:param name="tahun"><s:property value="%{payrollHeader.tahun}"/></s:param>--%>
                                                            <%--<s:param name="branchId"><s:property value="%{payrollHeader.branchId}"/></s:param>--%>
                                                            <%--<s:param name="tipePayroll"><s:property value="%{payrollHeader.tipePayroll}"/></s:param>--%>
                                                            <%--</s:url>--%>

                                                        <%--<button type="button" class="btn btn-danger" onclick="--%>
                                                                <%--window.location.href='<s:url action="edit_payroll"><s:param name="id"><s:property value="%{payrollHeader.payrollHeaderId}"/></s:param><s:param name="bulan"><s:property value="%{payrollHeader.bulan}"/></s:param><s:param name="tahun"><s:property value="%{payrollHeader.tahun}"/></s:param><s:param name="branchId"><s:property value="%{payrollHeader.branchId}"/></s:param><s:param name="tipePayroll"><s:property value="%{payrollHeader.tipePayroll}"/></s:param></s:url>';--%>
                                                                <%--">--%>
                                                            <%--<i class="fa fa-refresh"></i> Reset--%>
                                                        <%--</button>--%>
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

                                                        <s:set name="listDataPayroll" value="#session.listDataPayroll" scope="request" />
                                                        <display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_edit_payroll.action" export="true" id="row" pagesize="1000" style="font-size:9">

                                                            <s:if test='%{#attr.row.flagKoreksi == "Y" }'>

                                                                <display:column class="bg-danger" media="html" title="Edit">
                                                                    <a href="javascript:;"
                                                                       data="<s:property value="%{#attr.row.payrollId}"/>" class="item-edit">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </a>
                                                                </display:column>

                                                                <display:column class="bg-danger" property="nip" sortable="true" title="NIP"  />
                                                                <display:column class="bg-danger" property="namaPegawai" sortable="true" title="Nama" />
                                                                <display:column class="bg-danger" property="divisiName" sortable="true" title="Bidang" />
                                                                <display:column class="bg-danger" property="posisiName" sortable="true" title="Jabatan" />
                                                                <display:column class="bg-danger" style="text-align:right;" property="componentA" sortable="true" title="Gaji" />
                                                                <display:column class="bg-danger" style="text-align:right;" property="componentB" sortable="true" title="Tot.Tunj." />
                                                                <display:column class="bg-danger" style="text-align:right;" property="componentC" sortable="true" title="Tot.Pot.Tnp.PPh" />
                                                                <display:column class="bg-danger" style="text-align:right;" property="tunjanganPph" sortable="true" title="PPh" />
                                                                <display:column class="bg-danger" style="text-align:right;" property="gajiBersih" sortable="true" title="Gaji.Bersih" />
                                                                <display:column class="bg-danger" style="text-align:center;" property="multifikator" sortable="true" title="Prop.Gaji" />
                                                                <display:column class="bg-danger" style="color: #00cc00;" property="stTanggalPraPensiun" sortable="true" title="PMP" />
                                                                <display:column class="bg-danger" style="color: red;" property="stTanggalPensiun" sortable="true" title="Pensiun" />

                                                            </s:if>
                                                            <s:else>

                                                                <display:column media="html" title="Edit">
                                                                    <a href="javascript:;"
                                                                       data="<s:property value="%{#attr.row.payrollId}"/>" class="item-edit">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </a>
                                                                </display:column>

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

                                                            </s:else>

                                                            <display:setProperty name="paging.banner.item_name">PayrollDetail</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">PayrollDetail</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">PayrollDetail.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">PayrollDetail.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">PayrollDetail.pdf</display:setProperty>
                                                            <%--<display:column media="html" title="Reproses">
                                                                <a href="javascript:;"
                                                                   nip="<s:property value="%{#attr.row.nip}"/>"
                                                                   branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                   bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                   tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                   nama="<s:property value="%{#attr.row.nama}"/>"
                                                                   payrollId="<s:property value="%{#attr.row.payrollId}"/>"
                                                                   class="item-reproses">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_reset.png"/>" name="icon_reset">
                                                                </a>
                                                            </display:column>--%>
                                                            <%--<display:column style="text-align:center;" media="html" title="Promosi">
                                                                <s:if test="#attr.row.flagPromosiOn">
                                                                    <a href="javascript:;" payrollId="<s:property value="%{#attr.row.payrollId}"/>"
                                                                       nip="<s:property value="%{#attr.row.nip}"/>" class="item-promosi">
                                                                        <span style="font-size: 17px" class="glyphicon glyphicon-user"></span>
                                                                    </a>
                                                                </s:if>
                                                            </display:column>--%>



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
                                                                <sj:dialog id="waiting_dialog_search" openTopics="showDialogSearch" closeTopics="closeDialog" modal="true"
                                                                           resizable="false"
                                                                           height="350" width="600" autoOpen="false" title="Searching ...">
                                                                    Please don't close this window, server is processing your request ...
                                                                    </br>
                                                                    <center>
                                                                        <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                             src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                             name="image_indicator_write">
                                                                        <br>
                                                                        <img class="spin" border="0"
                                                                             style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                             src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                             name="image_indicator_write">
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


<%--updated by ferdi 01-12-2020 to display modal detail payroll to edit save--%>
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
                        <div class="col-sm-4">
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-5" >Koreksi :</label>--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<input style="display: none" readonly type="text" class="form-control nip" id="flagKoreksi" name="flagKoreksi">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Catatan Koreksi :</label>
                                <div class="col-sm-6">
                                    <input style="display: none" readonly type="text" class="form-control nip" id="flagKoreksi" name="flagKoreksi">
                                    <textarea rows="6" readonly type="text" class="form-control nip" id="noteKoreksi" name="noteKoreksi"></textarea>
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
                                            <input style="text-align: right" type="text" class="form-control nip" id="selisihPph" name="selisihPph" onfocusout="validasiNilai(this.id, this.value)">
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
                                    <input style="text-align: right"  type="text" class="form-control nip" id="kopkar" name="kopkar" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran Sp</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="iuranSp" name="iuranSp" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran PIIKB</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="iuranPiiKb" name="iuranPiiKb" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bank BRI</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="bankBri" name="bankBri" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bank Mandiri</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="bankMandiri" name="bankMandiri" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Infaq</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="infaq" name="infaq" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Perkes dan Obat</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="perkesDanObat" name="perkesDanObat" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Listrik</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="listrik" name="listrik" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran Profesi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="iuranProfesi" name="iuranProfesi" onfocusout="validasiNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Potongan Lain</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="potonganLain" name="potonganLain" onfocusout="validasiNilai(this.id, this.value)">
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

</body>
</html>
<script type='text/javascript'>

    window.clos = function() {
        //$('#waiting_dialog').dialog('close');
        $('#view_dialog_menu').dialog('close');
        $('#info_dialog').dialog('close');
        window.location.href="<s:url action='initForm_payroll.action'/>";
    };

    //updated by ferdi, 01-12-2020, to save item payroll
    $('#btnSave').click(function() {
        var payrollId = document.getElementById("payrollPegawaiId").value;
        var nip = document.getElementById("nip").value;
        var bulan = document.getElementById("bulan").value;
        var tahun = document.getElementById("tahun").value;

        //komponen rincian potongan
        var kopkar = document.getElementById("kopkar").value;
        var iuranSp = document.getElementById("iuranSp").value;
        var iuranPiikb = document.getElementById("iuranPiiKb").value;
        var bankBri = document.getElementById("bankBri").value;
        var bankMandiri = document.getElementById("bankMandiri").value;
        var infaq = document.getElementById("infaq").value;
        var perkesDanObat = document.getElementById("perkesDanObat").value;
        var listrik = document.getElementById("listrik").value;
        var iuranProfesi = document.getElementById("iuranProfesi").value;
        var potonganLain = document.getElementById("potonganLain").value;

        var selisihPph21 = "0";

        if (bulan=="12"){
            selisihPph21 = document.getElementById("selisihPph").value;
        }

        if (confirm('Apakah Anda ingin merubah Data?')) {

            PayrollAction.saveEditData(payrollId, nip, bulan, kopkar, iuranSp, iuranPiikb,bankBri, bankMandiri, infaq, perkesDanObat,listrik, iuranProfesi, potonganLain,selisihPph21, function(result) {

                if (result) {
                    alert('Data Berhasil Dirubah.');
                    $('#modal-edit').modal('hide');
                    $('#formEdit')[0].reset();
                } else {
                    alert('Error saat penyimpanan data, tolong di cek kembali atau hubungin admin.');
                }

            });

        }

    });

    //updated by ferdi, 01-12-2020, to view edit item payroll each nip
    $('.tablePayroll').on('click', '.item-edit', function(){

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
            $('#noteKoreksi').val(itemData.noteKoreksi);
            $('#flagKoreksi').val(itemData.flagKoreksi);
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

    window.reloadEdit = function(){
        if (confirm('Apakah akan reload initial data untuk edit kembali ?')) {
            window.location.href='<s:url action="edit_payroll"><s:param name="id"><s:property value="%{payrollHeader.payrollHeaderId}"/></s:param><s:param name="bulan"><s:property value="%{payrollHeader.bulan}"/></s:param><s:param name="tahun"><s:property value="%{payrollHeader.tahun}"/></s:param><s:param name="branchId"><s:property value="%{payrollHeader.branchId}"/></s:param><s:param name="tipePayroll"><s:property value="%{payrollHeader.tipePayroll}"/></s:param><s:param name="reloadData">true</s:param></s:url>';
        }
    }

    window.validasiNilai = function(id, a){
        a = a.replace(/\,/g,''); // 1125, but a string, so convert it to number
        // a = parseInt(a,10);
        var isnum = /^\d+$/.test(a);

        if(isnum == false){
            //alert("1");
            alert('Hanya dapat diisi angka 0 - 9, Tolong dicek kembali');
            $('#'+id).val(0);

        }
    }
</script>