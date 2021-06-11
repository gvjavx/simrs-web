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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>

    <script type='text/javascript'>

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        function link(){
            window.location.href="<s:url action='initForm_laporan'/>";
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var unit    = document.getElementById("branchId").value;
            var periodeTahun = document.getElementById("periodeTahun").value;
            var periodeBulan = document.getElementById("periodeBulan").value;
            var tipeLaporan = document.getElementById("tipeLaporan").value;
            var master = document.getElementById("masterId").value;
            var reportId = document.getElementById("reportId").value;

            if ( unit != '' && periodeTahun != ''&& periodeBulan != ''&&tipeLaporan!=''&&reportId!='') {
                event.originalEvent.options.submit = false;
                var url = "printReportAging_laporanAkuntansi.action?laporanAkuntansi.tipeLaporan="+tipeLaporan+"&laporanAkuntansi.unit="+unit+"&laporanAkuntansi.tahun="+periodeTahun+"&laporanAkuntansi.bulan="+periodeBulan+"&laporanAkuntansi.masterId="+master+"&laporanAkuntansi.reportId="+reportId;
                window.open(url,'_blank');
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( unit == '') {
                    msg += 'Field <strong>Unit </strong> masih belum dipilih' + '<br/>';
                }
                if ( periodeTahun == '') {
                    msg += 'Field <strong>Tahun </strong> masih belum dipilih' + '<br/>';
                }
                if ( periodeBulan == '') {
                    msg += 'Field <strong>Bulan </strong> masih belum dipilih' + '<br/>';
                }
                if ( tipeLaporan == '') {
                    msg += 'Field <strong>Tipe Laporan </strong> masih belum dipilih' + '<br/>';
                }
                if ( reportId == '') {
                    msg += 'Field <strong>Error </strong> hubungi admin' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');
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
            Laporan Aging
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Laporan Aging</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="laporanAkuntansiForm" method="post"  theme="simple" namespace="/laporanAkuntansi" action="printReport_laporanAkuntansi.action" cssClass="form-horizontal">
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
                                                        <s:if test='laporanAkuntansi.unit == "01"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranchAkuntansi_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="laporanAkuntansi.unit"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranchAkuntansi_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="laporanAkuntansi.unit" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="laporanAkuntansi.unit" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Laporan :</small></label>
                                                </td>
                                                <td>
                                                    <s:select list="#{'hutang_usaha':'Hutang Usaha', 'piutang_usaha' : 'Piutang Usaha', 'uang_muka':'Uang Muka','piutang_pasien':'Piutang Pasien','uang_muka_p':'Uang Muka Pasien','pph_dokter_kso':'PPH21 Dokter','pph_pegawai':'PPH21 Pegawai','pph_rekanan':'PPH21 Rekanan'}"
                                                              id="tipeLaporan" name="laporanAkuntansi.tipeLaporanId"
                                                              headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                    <s:hidden name="laporanAkuntansi.reportId" id="reportId" />
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
                                                                  id="periodeBulan" name="laporanAkuntansi.bulan"
                                                                  headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periodeTahun"
                                                                  name="laporanAkuntansi.tahun" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                                <script>
                                                    var dt = new Date();
                                                    $('#periodeBulan').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                    $('#periodeTahun').val(dt.getFullYear());
                                                </script>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Vendor :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="masterId" name="laporanAkuntansi.masterId" required="true" cssClass="form-control"/>
                                                    </table>
                                                    <script>
                                                        $(document).ready(function() {
                                                            var tipePerson = $('#tipeLaporan').val();
                                                            var functions, mapped;
                                                            console.log(tipePerson);
                                                            $('#masterId').typeahead({
                                                                minLength: 1,
                                                                source: function (query, process) {
                                                                    functions = [];
                                                                    mapped = {};
                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    MasterAction.initTypeaheadMaster(query,function (listdata) {
                                                                        data = listdata;
                                                                    });
                                                                    $.each(data, function (i, item) {
                                                                        var labelItem = item.nomorVendor + " | " + item.nama;
                                                                        mapped[labelItem] = {
                                                                            id: item.nomorVendor,
                                                                            nama: item.nama
                                                                        };
                                                                        functions.push(labelItem);
                                                                    });
                                                                    process(functions);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    $('#masterName').val(selectedObj.nama);
                                                                    return selectedObj.id;
                                                                }
                                                            });
                                                            // if (tipePerson=='hutang_usaha'||tipePerson=='piutang_usaha'||tipePerson=='uang_muka'){
                                                            //
                                                            // }else if (tipePerson=='piutang_pasien'||tipePerson=='uang_muka_p') {
                                                            //     $('#masterId').typeahead({
                                                            //         minLength: 1,
                                                            //         source: function (query, process) {
                                                            //             functions = [];
                                                            //             mapped = {};
                                                            //
                                                            //             var data = [];
                                                            //             dwr.engine.setAsync(false);
                                                            //
                                                            //             PasienAction.getListComboPasien(query, function (listdata) {
                                                            //                 data = listdata;
                                                            //             });
                                                            //
                                                            //             $.each(data, function (i, item) {
                                                            //                 var labelItem = item.idPasien+" | "+item.nama;
                                                            //                 mapped[labelItem] = {
                                                            //                     id: item.idPasien,
                                                            //                     nama:item.nama
                                                            //                 };
                                                            //                 functions.push(labelItem);
                                                            //             });
                                                            //             process(functions);
                                                            //
                                                            //         },
                                                            //         updater: function (item) {
                                                            //             var selectedObj = mapped[item];
                                                            //             $('#masterName').val(selectedObj.nama);
                                                            //             return selectedObj.id;
                                                            //         }
                                                            //     });
                                                            // }
                                                        });
                                                    </script>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="masterName" name="laporanAkuntansi.masterName" required="true" readonly="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="laporanAkuntansiForm" id="print" name="print"
                                                                   onBeforeTopics="beforeProcessSave">
                                                            <i class="fa fa-print"></i>
                                                            Print
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="searchReportNeracaMutasi_laporanAkuntansi.action"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="350" width="600" autoOpen="false" title="Loading ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <center>
                                                                <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                            </center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="620" width="900" autoOpen="false"
                                                                   title="Laporan Akuntansi">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
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
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>