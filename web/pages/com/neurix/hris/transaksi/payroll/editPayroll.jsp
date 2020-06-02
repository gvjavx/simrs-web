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
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>

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

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.loadRapelDetail =  function(rapelId){

                $('.tabelRapelDetail').find('tbody').remove();
                $('.tabelRapelDetail').find('thead').remove();
                dwr.engine.setAsync(false);
                var tmp_table = "";

                PayrollAction.loadDataRapelDetail(rapelId, function(listdata) {

                    tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>"+
                            "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Gadas Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Gadas Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Gadas</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Umk Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Umk Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Umk</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Struktural Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Struktural Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Struktural</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Jab. Struktural Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Jab. Struktural Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Jab. Struktural</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Strategis Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Strategis Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Strategis</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Air Listrik Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Air Listrik Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Air Listrik</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Perumahan Lama</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Perumahan Baru</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Selisih Perumahan</th>"+
                            "</tr></thead>";
                    var i = i ;
                    $.each(listdata, function (i, item) {

                        tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                                '<td align="center">' + item.rapelBulan + '</td>' +
                                '<td align="center">' + item.rapelTahun + '</td>' +
                                '<td align="center">' + item.gajiGolonganLama  + '</td>' +
                                '<td align="center">' + item.gajiGolonganBaru + '</td>' +
                                '<td align="center">' + item.gajiGolongan + '</td>' +
                                '<td align="center">' + item.umkLama  + '</td>' +
                                '<td align="center">' + item.umkBaru + '</td>' +
                                '<td align="center">' + item.umk + '</td>' +
                                '<td align="center">' + item.strukturalLama  + '</td>' +
                                '<td align="center">' + item.strukturalBaru + '</td>' +
                                '<td align="center">' + item.struktural + '</td>' +
                                '<td align="center">' + item.jabStrukturalLama  + '</td>' +
                                '<td align="center">' + item.jabStrukturalBaru + '</td>' +
                                '<td align="center">' + item.jabStruktural + '</td>' +
                                '<td align="center">' + item.strategisLama  + '</td>' +
                                '<td align="center">' + item.strategisBaru + '</td>' +
                                '<td align="center">' + item.strategis + '</td>' +
                                '<td align="center">' + item.airListrikLama  + '</td>' +
                                '<td align="center">' + item.airListrikBaru + '</td>' +
                                '<td align="center">' + item.airListrik + '</td>' +
                                '<td align="center">' + item.perumahanLama  + '</td>' +
                                '<td align="center">' + item.perumahanBaru + '</td>' +
                                '<td align="center">' + item.perumahan + '</td>' +
                                "</tr>";
                    });
                    $('.tabelRapelDetail').append(tmp_table);

                });
            }

            window.numberWithCommas=function(x) {
                return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }

            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_payroll.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var branch = document.getElementById("branchId").value;
                var bulan = document.getElementById("bulanPayroll").value;
                var tahun = document.getElementById("tahunPayroll").value;

                if (branch != '' && bulan != '' && tahun != '') {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                } else {

                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (branch == '') {
                        msg += 'Field <strong>Branch</strong> is required.' + '<br/>';
                    }

                    if (bulan == '') {
                        msg += 'Field <strong>Bulan</strong> is required.' + '<br/>';
                    }

                    if (tahun == '') {
                        msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });
        });


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<div id="modal-promosi" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >NIP</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiNip" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiNama">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bagian</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiBagian">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jabatan</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiJabatan">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan / Poin</label>
                        <div class="col-sm-4">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiGolongan">
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiPoin">
                        </div>
                    </div>

                    <hr>

                    <table style="width: 100%;" id="tabelDetailPromosi" class="tabelDetailPromosi table table-bordered"></table>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
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
                                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/payroll" action="edit_payroll.action" cssClass="form-horizontal">
                                        <s:hidden name="addOrEdit"/>
                                        <s:hidden name="delete"/>
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
                                                        <s:select list="#initComboBranch.listOfComboBranch" value="KD01" id="branchId" name=""
                                                                  listKey="branchId" listValue="branchName" headerKey="" disabled="true"
                                                                  headerValue="[Select one]" cssClass="form-control"/>
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
                                                                  id="bulanPayroll" name="" disabled="true" value="01"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll"
                                                                  name="tahun" required="true" headerKey="" disabled="true"
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PR':'Payroll', 'T':'THR', 'PD':'Pendidikan', 'R':'Rapel', 'IN':'Insentif',
                                        'JP':'Jasprod', 'JB':'Jubileum', 'PN':'Pensiun'}" id="tipe" name="payroll.tipe"
                                                                  cssClass="form-control" disabled="true" />
                                                    </table>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield cssStyle="display: none" id="txtTipeId" name="payroll.tipe" required="false" readonly="false" cssClass="form-control"/>
                                                        <s:textfield cssStyle="display: none" id="txtBranchId" name="payroll.branchId" required="false" readonly="false" cssClass="form-control"/>
                                                        <s:textfield cssStyle="display: none" id="txtBulanPayroll" name="payroll.bulan" required="false" readonly="false" cssClass="form-control"/>
                                                        <s:textfield cssStyle="display: none" id="txtTahunPayroll" name="payroll.tahun" required="false" readonly="false" cssClass="form-control"/>
                                                        <s:textfield cssStyle="display: none" id="personName1" name="payroll.nip" required="false" readonly="false" cssClass="form-control"/>
                                                        <s:textfield id="personName2" name="payroll.nama" required="false" readonly="false" cssClass="form-control"/>
                                                        <s:textfield cssStyle="display: none" id="personName" name="payroll.name" required="false" readonly="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                                <script type='text/javascript'>
                                                    var functions, mapped;
                                                    // var prov = document.getElementById("provinsi1").value;
                                                    $('#personName2').typeahead({
                                                        minLength: 1,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                                data = listdata;
                                                                //alert('aa');
                                                            });
                                                            //alert(prov);
                                                            $.each(data, function (i, item) {
                                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                                var labelNip = item.nip;
                                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch, divisiId: item.divisi, positionId : item.positionId };
                                                                functions.push(labelItem);
                                                            });


                                                            process(functions);
                                                        },

                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            var namaAlat = selectedObj.label;
                                                            document.getElementById("personName1").value = selectedObj.id;
                                                            document.getElementById("personName").value = selectedObj.pegawai;

                                                            branc = selectedObj.branchId;
                                                            dev = selectedObj.divisiId ;
                                                            return namaAlat;
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
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="sppdForm" id="save" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>

                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="menujuLink('cancelPage_payroll.action', 'Payroll');">
                                                            <i class="fa fa-close"></i> Cancel
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
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="500" width="500" autoOpen="false"
                                                                   title="Payroll ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listDataPayroll" value="#session.listDataPayroll" scope="request" />
                                                        <display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_edit_payroll.action" export="true" id="row" pagesize="1000" style="font-size:10">

                                                            <display:column media="html" title="Edit">
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.payrollId}"/>" class="item-edit">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </a>
                                                            </display:column>

                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="nama" sortable="true" title="Nama" />
                                                            <display:column property="departmentName" sortable="true" title="Bidang" />
                                                            <display:column property="positionName" sortable="true" title="Jabatan" />
                                                            <display:column property="golonganName" sortable="true" title="Golongan" />
                                                            <display:column style="text-align:right;" property="totalA" sortable="true" title="Gaji Kotor" />
                                                            <display:column style="text-align:right;" property="totalB" sortable="true" title="RLAB & Sansos" />
                                                            <display:column style="text-align:right;" property="totalC" sortable="true" title="Pot. Tnp. PPh" />
                                                            <display:column style="text-align:right;" property="pphGaji" sortable="true" title="PPh" />
                                                            <display:column style="text-align:right;" property="totalGajiBersih" sortable="true" title="Gaji Bersih" />
                                                            <display:column style="text-align:right;" property="totalRapel" sortable="true" title="Rapel" />
                                                            <display:column style="text-align:right;" property="totalThr" sortable="true" title="Thr" />
                                                            <display:column style="text-align:right;" property="totalPendidikan" sortable="true" title="Pendidikan" />
                                                            <display:column style="text-align:right;" property="totalJasProd" sortable="true" title="Jasprod" />
                                                            <display:column style="color: #00cc00;" property="tanggalJubileum" sortable="true" title="Jubileum" />
                                                            <display:column style="color: red;" property="stTanggalPensiun" sortable="true" title="Pensiun" />
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

<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:100%">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve SPPD</h4>
            </div>
            <div class="modal-body" align="left">
                <form class="form-horizontal" id="formEdit">
                    <div id="biodataMod" class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <input style="display: none" readonly type="text" class="form-control nip" id="payrollId2" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bulan / Tahun </label>
                                <div class="col-sm-3">
                                    <input readonly type="text" class="form-control nip" id="bulan" name="nip">
                                </div>
                                <div class="col-sm-4">
                                    <input readonly type="text" class="form-control nip" id="tahun" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >NIP</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="nip" name="nip">
                                </div>
                                <div class="col-sm-3">
                                    <input style="display: none" readonly type="text" class="form-control nip" id="branchId2" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >NPWP</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="npwp" name="nip">
                                </div>
                            </div>

                            <div class="form-group" style="display: none;">
                                <label class="control-label col-sm-5" >Tipe Pegawai</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="tipePegawai" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tipe Pegawai</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="tipePegawaiName" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Nama</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="nama" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bidang</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="divisi" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Level</label>
                                <div class="col-sm-7">
                                    <input readonly style="padding-left: 8px; padding-right: 0px" type="text" class="form-control nip" id="golongan" name="nip">
                                </div>

                                <%--<div class="col-sm-1">
                                    <input readonly type="text" class="form-control nip" id="point" name="nip">
                                </div>--%>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Jabatan</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control nip" id="jabatan" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >S.Keluarga/Anak</label>
                                <div class="col-sm-4">
                                    <input readonly type="text" class="form-control nip" id="statusKeluarga" name="nip">
                                </div>
                                <div class="col-sm-3">
                                    <input readonly type="text" class="form-control nip" id="jumlahAnak" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label style="padding-left: 0px; padding-right: 0px" class="control-label col-sm-5" >Tipe Dana Pensiun</label>
                                <div class="col-sm-7">
                                    <input style="text-align: left" readonly type="text" class="form-control nip" id="tipeDanaPensiun" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Gol.Dapen/Masa Gol.</label>
                                <div class="col-sm-5">
                                    <input readonly style="padding-left: 8px; padding-right: 0px" type="text" class="form-control nip" id="golonganDapenId" name="nip">
                                </div>

                                <div class="col-sm-2">
                                    <input readonly type="text" class="form-control nip" id="masaGolDapen" name="nip">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-5" >Gaji Pensiun*</label>
                                <div class="col-sm-7">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="gajiPensiun" name="nip">
                                </div>
                            </div>

                            <%--<div class="form-group">
                                <label class="control-label col-sm-1" >Multifikator</label>
                                <div class="col-sm-2">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="multifikator" name="nip">
                                </div>
                            </div>--%>

                            <%--<div class="form-group">
                                <label class="control-label col-sm-1" >Gaji Bpjs **</label>
                                <div class="col-sm-2">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="gajiBpjs" name="nip">
                                </div>
                            </div>--%>
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
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="gaji" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Sankhus</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjUmk" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Jabatan</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjJabStruktural" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Struktural</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjStruktural" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Fungs</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjStrategis" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Peralihan</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" type="text" class="form-control nip" id="tunjPeralihan" readonly onfocusout="updateNilai(this.id, this.value)" name="nip">
                                </div>
                                <div class="col-sm-1 pull-right nopadding">
                                    <button type="button" id="detailPeralihan" class="btn btn-primary">View</button>
                                </div>
                                <script>
                                    $('#detailPeralihan').click(function(){
                                        $('#modal-peralihan').find('.modal-title').text('Detail Peralihan');
                                        $('#modal-peralihan').modal('show');
                                    })
                                </script>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Lain</label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right" readonly type="text" class="form-control nip" id="tunjLain" name="nip">
                                </div>
                                <div class="col-sm-1 nopadding">
                                    <button type="button" id="detailTunjlain" class="btn btn-primary detailTunjlain">View</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Tambahan(PKWT)</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjTambahan" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Lembur</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjLembur" name="nip">
                                </div>
                                <div class="col-sm-1 pull-right nopadding">
                                    <button type="button" id="detailLembur" class="btn btn-primary">View</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Pemondokan</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" type="text" class="form-control nip" id="pemondokan" onfocusout="updateNilai(this.id, this.value)" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Komunikasi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" type="text" class="form-control nip" id="komunikasi" onfocusout="updateNilai(this.id, this.value)" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tambahan Lain</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tambahanLain" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Total A</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalA" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" ><b>Gaji Bersih (A+B)- C</b></label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" align="right" readonly type="text" class="form-control nip" id="gajiBersih" name="nip">
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
                                    <input readonly style="text-align: right"  type="text" class="form-control nip" id="tunjRumah" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Listrik</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjListrik" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Air</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjAir" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. BBM</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjBbm" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5"><b>Total. RLAB</b></label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalRlab" name="nip">
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Dapen</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjDapen" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Bpjs Ks</label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right" readonly type="text" class="form-control nip" id="tunjBpjsKs" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Bpjs Tk</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjBpjsTk" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Tunj. Pph</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="tunjPph" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Total B</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalB" name="nip">
                                </div>
                            </div>
                            <br>
                            <div align="center">
                                <h4>D. PTT</h4>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5 nopadding" >Nilai PTT</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" type="text" readonly class="form-control nip" id="nilaiPtt" onfocusout="updateNilai(this.id, this.value)" name="nip">
                                </div>
                                <div class="col-sm-1 pull-right nopadding">
                                    <button type="button" id="btnDetailPtt" class="btn btn-primary">View</button>
                                </div>
                                <script>
                                    function loadPtt() {
                                        $('.tabelPtt').find('tbody').remove();
                                        $('.tabelPtt').find('thead').remove();

                                        var tmp_table = "";
                                        var tmp_table2 = "<tbody>";
                                        var hasil = 0;

                                        PayrollAction.getDetailPtt( function(listdata){
                                            tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +
                                                "<th style='text-align: center; background-color:  #3c8dbc''>No </th>" +
                                                "<th style='text-align: center; background-color:  #3c8dbc''>Nama PTT</th>" +
                                                "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>" +
                                                "<th style='text-align: center; background-color:  #3c8dbc''>Delete</th>" +
                                                "</tr></thead><tbody>";
                                            $.each(listdata, function (i, item) {
                                                tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                                                    '<td align="left">' + (i+1) + '</td>' +
                                                    '<td align="left">' + item.tipePttName+ '</td>' +
                                                    '<td align="right">' + item.nilai+ '</td>' +
                                                    '<td align="center">' +
                                                    "<a href='javascript:;' class ='item-delete-ptt' data ='"+item.tipePttId+"'>" +
                                                    "<img border='0' src='<s:url value='/pages/images/delete_task.png'/>'>"+
                                                    '</a>' +
                                                    '</td>' +
                                                    "</tr>";
                                            });
                                            tmp_table += "</tbody>";
                                            $('.tabelPtt').append(tmp_table);
                                        });
                                    }
                                    $(document).ready(function(){
                                        $('.tabelPtt').on('click', '.item-delete-ptt', function () {
                                            if (confirm('Apakah anda ingin menghapus PTT ini ?')){
                                                var tipePttId = $(this).attr('data');
                                                dwr.engine.setAsync(false);
                                                PayrollAction.deletePttSession(tipePttId,function(result) {
                                                    alert("sukses menghapus PTT");
                                                    $('#nilaiPtt').val(result);
                                                    updateNilai("nilaiPtt", result);
                                                    loadPtt();
                                                });
                                            }
                                        });
                                    })
                                    $('#btnDetailPtt').click(function(){
                                        loadPtt();
                                        $('#modal-ptt').find('.modal-title').text('Detail PTT');
                                        $('#modal-ptt').modal('show');
                                    })
                                </script>
                            </div>
                            <br>
                            <s:if test="payrollBulan12">
                                <div class ="pphBulan12">
                                    <div align="center">
                                        <h4>Selisih PPH </h4>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5 nopadding" id="labelTotalPtt" >Total Ptt Setahun. </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" readonly type="text" class="form-control nip" id="totalPtt" name="nip">
                                        </div>
                                        <div class="col-sm-1 pull-right nopadding">
                                            <button type="button" class="btn btn-primary" id="btnViewTotalPtt11">View</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5 nopadding" id="labelPphSeharusnya" >Pph Seharusnya </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" type="text" readonly class="form-control nip" id="pphSeharusnya" name="nip">
                                        </div>
                                        <div class="col-sm-1 pull-right nopadding">
                                            <button type="button" class="btn btn-primary" id="btnViewPPhSeharusnya">View</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5 nopadding" id="labelPph11Bulan" >Pph 11 Bulan </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pph11Bulan" name="nip">
                                        </div>
                                        <div class="col-sm-1 pull-right nopadding">
                                            <button type="button" class="btn btn-primary" id="btnViewTotalPPh11">View</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" id="labelSelisih" >Selisih pph 21 </label>
                                        <div class="col-sm-6">
                                            <input style="text-align: right" type="text" readonly class="form-control nip" id="selisihPph" name="nip">
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
                                    <input readonly style="text-align: right"  type="text" class="form-control nip" id="iuranDapenPeg" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Dp. Pers</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranDapenPersh" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Tk Pegawai</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsTkPeg" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Tk Persh. </label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsTkPers" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Ks Peg. </label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsKsPeg" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iur. Bpjs Ks Pers. </label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="iuranBpjsKsPers" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Pot. Pph</label>
                                <div class="col-sm-6">
                                    <input readonly style="text-align: right" readonly type="text" class="form-control nip" id="pphGaji1" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Pot. Lain-lain</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalPotonganLain" name="nip">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Total C</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right" readonly type="text" class="form-control nip" id="totalC" name="nip">
                                </div>
                            </div>
                            <br>
                            <br>
                            <div align="center">
                                <h4>C. Rincian Potongan</h4>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Kopkar</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="kopkar" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran Sp</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="iuranSp" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran PIIKB</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="iuranPiiKb" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bank BRI</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="bankBri" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Bank Mandiri</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="bankMandiri" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Infaq</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="infaq" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Perkes dan Obat</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="perkesDanObat" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Listrik</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="listrik" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Iuran Profesi</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="iuranProfesi" name="nip" onfocusout="updateNilai(this.id, this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-5" >Potongan Lain</label>
                                <div class="col-sm-6">
                                    <input style="text-align: right"  type="text" class="form-control nip" id="potonganLain" name="nip" onfocusout="updateNilai(this.id, this.value)">
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

<div id="modal-ptt" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tipe PTT</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="tipePttId1">
                                <option value="0">[Select One]</option>
                                <option value="t">Tantiem</option>
                                <option value="R">Rekreasi</option>
                                <option value="tk">Tunjangan Khusus</option>
                                <option value="bPer">Biaya Pernikahan</option>
                                <option value="bPin">Biaya Pindah</option>
                                <option value="bPis">Biaya Pisah</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Nilai</label>
                        <div class="col-sm-6">
                            <input style="text-align: right"  type="text" class="form-control" id="nilaiModPtt">
                        </div>
                    </div>
                </form>
                <br>
                <div class="col-md-offset-9" id="btnAddPttMod">
                    <a type="button" class="btn btn-success"><i class="fa fa-plus"></i> Add</a>
                </div>
                <script>
                    $('#btnAddPttMod').click(function () {
                        var tipePtt = $('#tipePttId1').val();
                        var nilai = $('#nilaiModPtt').val();
                        if (tipePtt!="0"&&nilai!=""){
                            PayrollAction.saveTmpPtt(tipePtt,nilai, function(result){
                                $('#nilaiPtt').val(result);
                                updateNilai("nilaiPtt", result)
                                $('#tipePttId1').val("0");
                                $('#nilaiModPtt').val("");
                            });
                            loadPtt();
                        } else{
                            var msg="";
                            if (tipePtt!=''){
                                msg+="Tipe PTT masih belum dipilih \n"
                            }
                            if (nilai!=''){
                                msg+="Nilai masih belum dipilih \n"
                            }
                        }
                    })
                </script>
                <br>
                <div class="table-responsive">
                    <table id="tabelPtt" class="tabelPtt table table-bordered">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-peralihan" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Peralihan Gapok</label>
                        <div class="col-sm-6">
                            <input style="text-align: right"  type="text" class="form-control" onfocusout="updateNilai(this.id, this.value)" id="nilaiModPeralihanGapok">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Peralihan Sankhus</label>
                        <div class="col-sm-6">
                            <input style="text-align: right"  type="text" class="form-control" onfocusout="updateNilai(this.id, this.value)" id="nilaiModPeralihanSankhus">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Peralihan Tunjangan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right"  type="text" class="form-control" onfocusout="updateNilai(this.id, this.value)" id="nilaiModPeralihanTunjangan">
                        </div>
                    </div>
                </form>
                <br>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%--Modal detail tunjangan Lain Irfan--%>
<div id="modal-detailTunjLain" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                </form>
                <div class="table-responsive">
                    <table id="tableDetailTunjLain" class="tableDetailTunjLain table table-bordered">
                    </table>
                </div>
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

<div id="modal-ptt-setahun" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <center>
                    <table style="width: 100%;" id="tablePttSetahun" class="tablePttSetahun table table-bordered">
                    </table>
                </center>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
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

<div id="modal-pph-seharusnya" class="modal fade modal-md" role="dialog">
    <div class="modal-dialog ">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Total Pend. Bruto</label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotPendBruto">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Total Tunj. PPh </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotTunjPph">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Total Pend. Tidak Teratur </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotPendTidTer">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" ><b>Total Bruto</b> </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotBrut">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Total Iuran Pegawai </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotIuran">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Total Bijab </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotBijab">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" ><b>Total Reduce</b> </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modTotReduce">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Netto Setahun </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modNetSetahun">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >PTKP </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modPtkp">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >PKP </label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modPkp">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-sm-2" ><b>PPH Seharusnya </b></label>
                            <div class="col-sm-3">
                                <input style="text-align: right" readonly type="text" class="form-control" id="modPphSeh">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-pengobatan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" >
                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Jumlah Biaya Pengobatan</label>
                        <div class="col-sm-6" >
                            <input style="text-align: left; padding-left: 5px; padding-right: 3px;" type="text" class="form-control nip" id="fieldBiayaPengobatan" name="nip">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnSimpanBiayaPengobatan" type="button" class="btn btn-success" >Simpan</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-gaji" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:800px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="formGaji">

                    <div class="form-group">
                        <label class="control-label col-sm-2" >Tanggal</label>
                        <div class="col-sm-3">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" id="tanggaGajiAwal"  class="form-control pull-right"
                                       required="false"  cssStyle=""/>
                            </div>
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-3">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" id="tanggaGajiAkhir" class="form-control pull-right"
                                       required="false"  cssStyle=""/>
                            </div>
                            <label style="display:none;" id="tmpJmlGajiPkwt"></label>
                            <label style="display:none;" id="tmpJmlGajiPkwtNilai"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" ></label>
                        <div class="col-sm-3">
                            <a type="button" class="btn btn-primary btnCariGaji" style="margin-top: 10px"><i class="fa fa-search"></i> Cari</a>
                        </div>
                    </div>

                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="tabelDetailGaji" class="tabelDetailGaji table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-success btn-primary"><i class="fa fa-save"></i> Save</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-pphGaji" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:550px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <h4>A. Penerimaan</h4>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Gaji</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan UMK</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Struktural</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Peralihan</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Strategis</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunj. Kompensasi</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjKompensasi" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Transport</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjTransport" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Air Listrik</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjAirListrik" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunj. Perumahan</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPerumahan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunj. Pengobatan</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPengobatan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Pph</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjPph" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Lembur</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjLembur" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Lain lain</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiTunjLain" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Asumsi Thr</label>
                        <div class="col-sm-7">
                            <input style="text-align: right; background-color: #52cfeb" readonly type="text" class="form-control nip" id="pphGajiAsumsiThr" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Asumsi Pendidikan</label>
                        <div class="col-sm-7">
                            <input style="text-align: right ; background-color: #52cfeb" readonly type="text" class="form-control nip" id="pphGajiAsumsiPendidikan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Asumsi Jasprod</label>
                        <div class="col-sm-7">
                            <input style="text-align: right; background-color: #52cfeb" readonly type="text" class="form-control nip" id="pphGajiAsumsiJasprod" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Thr</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiThr" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Pendidikan</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPendidikan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Jasprod</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJasprod" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Insentif</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiInsentif" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Rapel</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiRapel" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Jubilium</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJubileum" name="nip">
                        </div>
                    </div>
                    <%--<div class="form-group">
                        <label class="control-label col-sm-5" >Pensiun</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPensiun" name="nip">
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Iuran Perusahan (Jkm+Jkk)</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJkmJkk" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Pakaian Dinas</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPakaianDinas" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Bruto (A)</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBruto" name="nip">
                        </div>
                    </div>
                    <br>
                    <br>
                    <h4>B. Pengurangan Penerimaan</h4>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Biaya Jabatan</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBiayaJabatan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Total Biaya Pensiun</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiIuranPensiun" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Dana Pensiun</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBpjsDanaPensiun" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Bpjs JHT</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBpjsJht" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Bpjs Pensiun</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiBpjsPensiun" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >PTKP</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPtkp" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Reduce (B)</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiJumlahB" name="nip">
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >PKP (A-B)</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiPkp" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Hutang Pph</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiHutangPph" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Pph Gaji</label>
                        <div class="col-sm-7">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphGajiNilai" name="nip">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="detailPphBulan btn btn-primary" >Detail</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-insentif" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan Mulai</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifBulanMulai" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan Sampai</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifBulanSampai" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTahun" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifMasaKerja" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji Bruto (A)</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifTotal" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Potongan Insentif</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifPot" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Pot. Insentif Individu</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifPotIndividu" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >SMK Standart</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifSmkStandart" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >SMK Huruf</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifSmkHuruf" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >SMK Angka</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifSmkAngka" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label style="padding-left: 0px" class="control-label col-sm-4" >Insentif Diterima</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="insentifDiterima" name="nip">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jubileum" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumMasKer1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumMasKer2" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px;">
                            <a href="javascript:;" class="detailJubileumMasaKerja" >
                                <span style="font-size: 25px" class="glyphicon glyphicon-search"></span>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="jubileumGolongan" name="nip">
                        </div>
                        <label class="control-label col-sm-1" >/</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPoint" name="nip">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Besarnya Jubileum  </label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTotal" name="nip">
                        </div>
                        <label class="control-label col-sm-2" style="text-align: left" >X 5</label>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Grand Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumGrandTotal" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >PPh beban karyawan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPph">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Netto diterimakan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumNetto">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jubileum-masaKerja" class="modal fade modal2" role="dialog">
    <div class="modal-dialog ">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">PKWT</label>
                        <div class="col-sm-2" style="padding-right: 0px">
                            <input style="text-align: left; padding-left: 5px; padding-right: 3px;" readonly type="text" class="form-control nip" id="jubileumPkwt1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-2" style="padding-right: 0px">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumPkwt2" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >=</label>

                        <div class="col-sm-4" style="padding-left: 0px">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumJumlahPkwt" name="nip">
                        </div>
                    </div>

                    <div class="form-group" style="padding-right:0px;">
                        <label class="control-label col-sm-2" style="padding-left:0px; padding-right:0px;">Pegawai Tetap</label>
                        <div class="col-sm-2" style="padding-right:0px;">
                            <input style="text-align: left; padding-left: 5px; padding-right:0px;" readonly type="text" class="form-control nip" id="jubileumTetap1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-2" style="padding-right:0px;">
                            <input style="text-align: left; padding-left: 5px; padding-right:0px;" readonly type="text" class="form-control nip" id="jubileumTetap2" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >=</label>

                        <div class="col-sm-4" style="padding-left: 0px">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumJumlahTetap" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-8" >Total Masa Kerja</label>
                        <div class="col-sm-4" style="padding-left: 0px">
                            <input readonly type="text" class="form-control nip" id="jubileumTotalMasaKerja" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>


<div id="modal-pensiun" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:650px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Aktif</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="pensiunTgl1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="pensiunTgl2" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="pensiunGolongan" name="nip">
                        </div>
                        <label class="control-label col-sm-1" >/</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunPoint" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-2">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunMasaKerjaTahun" name="nip">
                        </div>
                        <label style="text-align: left; padding-left: 0px" class="control-label col-sm-1" >Tahun</label>
                        <div class="col-sm-2">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunMasaKerjaBulan" name="nip">
                        </div>
                        <label style="text-align: left; padding-left: 0px" class="control-label col-sm-1" >Bulan</label>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah (A)</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunJumlah" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Pensiun</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunUangPensiun" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">
                            <input style="text-align: center;" readonly type="text" class="form-control nip" id="pensiunFaktorPensiun" name="nip">
                        </div>

                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunKaliPensiun" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Penghargaan</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunUangPenghargaan" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">
                            <input style="text-align: center;" readonly type="text" class="form-control nip" id="pensiunFaktorPenghargaan" name="nip">
                        </div>

                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunKaliPenghargaan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Perumahan</label>
                        <div class="col-sm-3">
                            <input style="text-align: center" readonly type="text" class="form-control nip" id="pensiunTotalPenghargaan" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">
                            <input style="text-align: center" readonly type="text" class="form-control" value="15%" name="nip">
                        </div>


                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control" id="pensiunPerumahan" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-sm-offset-4  control-label col-sm-3" >Total Pensiun</label>

                        <div class="col-sm-4">
                            <input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunBiaya" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-offset-4  control-label col-sm-3" >PPH Pensiun</label>

                        <div class="col-sm-4">
                            <input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunPph" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-sm-offset-4  control-label col-sm-3" >Netto diterima</label>

                        <div class="col-sm-4">
                            <input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunNetto" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-thr" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah Bulan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrBulanAktif">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjPeralihan" name="nip">
                        </div>
                    </div>


                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTotal" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Pph</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjPph" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Final Tunjangan Thr</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTotalBersih" name="nip">
                        </div>
                    </div>


                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-pendidikan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah Bulan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanBulanAktif">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Kompensasi</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjKompensasi" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Air Listrik</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjAirListrik" name="nip">
                        </div>
                    </div>

                    <hr>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTotal" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Pph</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjPph" name="nip">
                        </div>
                    </div>

                   <%-- <div class="form-group">
                        <label class="control-label col-sm-4" >Final Tunj.Pendidikan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTotalBersih" name="nip">
                        </div>
                    </div>--%>


                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jasprod" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jasprodMasKer1" name="nip">
                        </div>

                        <label style="padding-left: 0px" class="control-label col-sm-1" >Tahun</label>

                        <div class="col-sm-1" style="padding-left: 10px;">
                            <a href="javascript:;" class="detailJasprodMasaKerja" >
                                <span style="font-size: 25px" class="glyphicon glyphicon-search"></span>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="jasprodGolongan" name="nip">
                        </div>
                        <label class="control-label col-sm-1" >/</label>
                        <div class="col-sm-2">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPoint" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab. Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjStrategis" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji Bruto (A)</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTotalBruto" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji X Masa Kerja</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPengali" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Faktor</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodFaktor" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai SMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodNilaiSmk" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Persen SMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPersenSmk" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >A. Perhitungan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPerhitungan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label style="padding-left: 0px" class="control-label col-sm-4" >B. Gaji Kotor X Faktor</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodGajiKotor" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >C. Tambahan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTambahan" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai Jasprod</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodNilai" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jasprod-masaKerja" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-3" style="padding-right:0px;">Tanggal Aktif</label>
                        <div class="col-sm-4" >
                            <input style="text-align: left; padding-left: 5px; padding-right: 3px;" readonly type="text" class="form-control nip" id="jasprodTglAktif" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-4">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jasprodTglAktifSekarang" name="nip">
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-pphPengobatan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myFormPphPengobatan">
                    <div class="form-group">
                        <label class="control-label col-sm-6" >Jumlah Pengobatan</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control" id="pphPengobatanJumlah">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Jumlah PPh Pengobatan harus dibayar</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control" id="pphPengobatanHarusDibayar">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >PPh Pengobatan sudah dibayar</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control" id="pphPengobatanTerbayar">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Kurang PPh Pengobatan</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pphPengobatanKurang">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Bayar PPh pengobatan</label>
                        <div class="col-sm-5">
                            <input style="text-align: right" type="text" class="form-control nip" readonly id="pphPengobatanBayar">
                        </div>
                        <div class="col-md-1" style="padding-left: 0px;">
                            <input onchange="disableReadOnlyPphPengobatan()" type="checkbox" id="checkKalkulasiPphPengobatan" name="checkKalkulasiPphPengobatan" class="checkApprove">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btnPphPengobatanSave btn btn-success">Save</a>
                <a type="button" class="btnPphPengobatanApply btn btn-warning">Apply</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-5" >Rapel Id</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Golongan Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelGolonganLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Golongan Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelGolonganBaru" name="nip">
                        </div>
                    </div>

                    <%--<div class="form-group">
                        <label class="control-label col-sm-5" >Jumlah Bulan Rapel</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelJumlahBulan" name="nip">
                        </div>
                    </div>--%>

                    <hr>

                    <%--<div class="form-group">
                        <label class="control-label col-sm-5" >Gaji Golongan Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelGajiGolonganBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan UMK Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjUmkBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Struktural Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStrukturalBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunj. Jab.Struktural Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjJabStrukturalBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Strategis Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStrategisBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Air Listrik Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjAirListrikBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Perumahan Baru</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjPerumahanBaru" name="nip">
                        </div>
                    </div>

                    <hr>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Gaji Golongan Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelGajiGolonganLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan UMK Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjUmkLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Struktural Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStrukturalLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunj. Jab.Struktural Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjJabStrukturalLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Strategis Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStrategisLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Air Listrik Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjAirListrikLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5" >Tunjangan Perumahan Lama</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjPerumahanLama" name="nip">
                        </div>
                    </div>

                    <hr>--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Rapel Gaji Bulan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelBulan">
                        </div>
                        <a href="javascript:;" class="control-label col-sm-1 detailRapelBulan" style="padding-left: 0px">Detail</a>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5">Rapel Insentif</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelInsentif" name="nip">
                        </div>
                        <a href="javascript:;" class="control-label col-sm-1 detailRapelInsentif" style="padding-left: 0px">Detail</a>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Rapel Pendidikan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelPendidikan" name="nip">
                        </div>
                        <a href="javascript:;" class="control-label col-sm-1 detailRapelPendidikan" style="padding-left: 0px">Detail</a>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Rapel THR</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelThr" name="nip">
                        </div>
                        <a href="javascript:;" class="control-label col-sm-1 detailRapelThr" style="padding-left: 0px">Detail</a>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Rapel Jubileum</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelJubileum" name="nip">
                        </div>
                        <a href="javascript:;" class="control-label col-sm-1 detailRapelJubileum" style="padding-left: 0px">Detail</a>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Rapel Lembur</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control" id="rapelLembur" >
                        </div>
                        <a href="javascript:;" class="control-label col-sm-1 detailRapelLembur" style="padding-left: 0px">Detail</a>
                    </div>

                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-5" >Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTotal" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel-jubileum" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:700px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Gaji Dasar Baru - Gaji Dasar Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailJubileumGadasBaru" name="nip">
                        </div>


                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumGadasLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumSelisihGadas" >
                        </div>

                    </div>

                    <%--Tunj Umk--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Umk Baru - Tunj. Umk Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailJubileumUmkBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumUmkLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumSelisihUmk" >
                        </div>
                    </div>

                    <%--Tunj Struktural--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Struktural Baru - Tunj. Struktural Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailJubileumStrukturalBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumStrukturalLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumSelisihStruktural" >
                        </div>
                    </div>

                    <%--Tunj Jabatan Struktural--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Jab. Struktural Baru - Jab. Struktural Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailJubileumJabStrukturalBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumJabStrukturalLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumSelisihJabStruktural" >
                        </div>
                    </div>

                    <%--Tunj Strategis--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Peralihan Baru - Tunj. Peralihan Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailJubileumPeralihanBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumPeralihanLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumSelisihPeralihan" >
                        </div>
                    </div>

                    <hr>

                    <%--Total Detail Rapel THR--%>
                    <div class="form-group">
                        <label class="control-label col-sm-10" style="padding-right:0px;">Total jumlah selisih Jubileum</label>
                        <div class=" col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumSubTotal" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-10" style="padding-right:0px;">Rapel Jubileum (Selisih x 5)</label>
                        <div class=" col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailJubileumTotal" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-pph-bulan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                </form>
                <div class="table-responsive">
                    <table id="tableDetailPphBulan" class="tableDetailPphBulan table table-bordered">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel-lembur" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <div class="table-responsive">
                    <table id="tableDetailAbsensi" class="tableDetailAbsensi table table-bordered">
                    </table>
                </div>

                <%--Total Detail Rapel Lembur--%>
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-10" style="padding-right:0px;">Total Detail Rapel Lembur</label>
                        <div class=" col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailLemburTotal" >
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel-bulan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:900px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6" >Gaji Golongan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelGajiGolongan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Tunjangan Air Listrik</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjAirListrik" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >Tunjangan Perumahan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="rapelTunjPerumahan" name="nip">
                        </div>
                    </div>

                    <div class="table-responsive text-nowrap" style="white-space: nowrap">
                        <table id="tabelRapelDetail" class="tabelRapelDetail table table-bordered">
                        </table>
                    </div>

                    <hr>

                    <%--Total Detail Rapel Insentif--%>
                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Jumlah Rapel</label>
                        <div class=" col-sm-6">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailBulanJumlah" >
                        </div>
                    </div>

                    <%--<div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Total Rapel x jml Bulan</label>
                        <div class=" col-sm-6">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailBulanJumlahKali" >
                        </div>
                    </div>--%>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel-insentif" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:400px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Insentif Masa Kerja</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifJumlahMasaKerja">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Bruto Insentif Baru</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifBrutoBaru">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Potongan Insentif</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifPotonganInsentif">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Potongan Insentif Individu</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifPotonganInsentifIndividu">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">SMK Standart</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifSmkStandart">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">SMK Huruf</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifSmkHuruf">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">SMK Angka</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifSmkAngka">
                        </div>
                    </div>

                    <hr>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Jumlah Insentif Baru</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifYangDiterimaBaru" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Jumlah Insentif Lama</label>
                        <div class="col-sm-6" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailInsentifYangDiterimaLama" name="nip">
                        </div>
                    </div>


                    <hr>

                    <%--Total Detail Rapel Insentif--%>
                    <div class="form-group">
                        <label class="control-label col-sm-6" style="padding-right:0px;">Total Rapel Insentif</label>
                        <div class=" col-sm-6">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailInsentifyangDiterimaSelisih" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel-thr" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:700px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Gaji Dasar Baru - Gaji Dasar Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailThrGadasBaru" name="nip">
                        </div>


                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrGadasLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrSelisihGadas" >
                        </div>

                    </div>

                    <%--Tunj Umk--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Umk Baru - Tunj. Umk Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailThrUmkBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrUmkLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrSelisihUmk" >
                        </div>
                    </div>

                    <%--Tunj Struktural--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Struktural Baru - Tunj. Struktural Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailThrStrukturalBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrStrukturalLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrSelisihStruktural" >
                        </div>
                    </div>

                    <%--Tunj Jabatan Struktural--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Jab. Struktural Baru - Jab. Struktural Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailThrJabStrukturalBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrJabStrukturalLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrSelisihJabStruktural" >
                        </div>
                    </div>

                    <%--Tunj Strategis--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Strategis Baru - Tunj. Strategis Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailThrStrategisBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrStrategisLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrSelisihStrategis" >
                        </div>
                    </div>

                    <hr>

                    <%--Total Detail Rapel THR--%>
                    <div class="form-group">
                        <label class="control-label col-sm-10" style="padding-right:0px;">Total Detail Rapel THR</label>
                        <div class=" col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailThrTotal" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-rapel-pendidikan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:700px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Gaji Dasar Baru - Gaji Dasar Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailPendidikanGadasBaru" name="nip">
                        </div>


                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanGadasLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanSelisihGadas" >
                        </div>

                    </div>

                    <%--Tunj Umk--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Umk Baru - Tunj. Umk Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailPendidikanUmkBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanUmkLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanSelisihUmk" >
                        </div>
                    </div>

                    <%--Tunj Struktural--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Struktural Baru - Tunj. Struktural Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailPendidikanStrukturalBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanStrukturalLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanSelisihStruktural" >
                        </div>
                    </div>

                    <%--Tunj Jabatan Struktural--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Jab. Struktural Baru - Jab. Struktural Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailPendidikanJabStrukturalBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanJabStrukturalLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanSelisihJabStruktural" >
                        </div>
                    </div>

                    <%--Tunj Strategis--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Strategis Baru - Tunj. Strategis Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailPendidikanStrategisBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanStrategisLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanSelisihStrategis" >
                        </div>
                    </div>

                    <%--Tunj Air Listrik--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5" style="padding-right:0px;">Tunj. Air Listrik Baru - Tunj. Air Listrik Lama</label>
                        <div class="col-sm-2" >
                            <input style="text-align: right; padding-left: 5px; padding-right: 9px;" readonly type="text"
                                   class="form-control" id="rapelDetailPendidikanAirListrikBaru" name="nip">
                        </div>

                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanAirListrikLama" >
                        </div>
                        <div class="col-sm-1">
                            <label class="control-label" style="text-align: center;"> = </label>
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanSelisihAirListrik" >
                        </div>
                    </div>

                    <hr>

                    <%--Total Detail Rapel THR--%>
                    <div class="form-group">
                        <label class="control-label col-sm-10" style="padding-right:0px;">Total Detail Rapel Pendidikan</label>
                        <div class=" col-sm-2">
                            <input style="text-align: right; padding-left: 5px" readonly type="text" class="form-control nip" id="rapelDetailPendidikanTotal" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
</body>

</html>

<script>
    $('#btnSave').click(function(){
        var payrollId = document.getElementById("payrollId2").value;
        var nip = document.getElementById("nip").value;
        var bulan = document.getElementById("bulan").value;
        var tahun = document.getElementById("tahun").value;
        //Komponen A
        var tunjanganPeralihan = document.getElementById("tunjPeralihan").value;
        var pemondokan = document.getElementById("pemondokan").value;
        var komunikasi = document.getElementById("komunikasi").value;

        //peralihan
        //macam peralihan
        var peralihanGapok = document.getElementById("nilaiModPeralihanGapok").value;
        var peralihanSankhus = document.getElementById("nilaiModPeralihanSankhus").value;
        var peralihanTunjangan = document.getElementById("nilaiModPeralihanTunjangan").value;

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
        var tunjPph = document.getElementById("tunjPph").value;
        var pphGaji = document.getElementById("pphGaji1").value;
        var nilaiPtt = document.getElementById("nilaiPtt").value;
        var totalPotLainLain = document.getElementById("totalPotonganLain").value;

        var totalPttSetahun = "0";
        var pphSeharusnya = "0";
        var pph11Bulan = "0";
        var selisihPph21 = "0";

        if (bulan=="12"){
            totalPttSetahun = document.getElementById("totalPtt").value;
            pphSeharusnya = document.getElementById("pphSeharusnya").value;
            pph11Bulan = document.getElementById("pph11Bulan").value;
            selisihPph21 = document.getElementById("selisihPph").value;
        }

        //komponen total
        var totalA = document.getElementById("totalA").value;
        var totalB = document.getElementById("totalB").value;
        var totalC = document.getElementById("totalC").value;
        var gajiBersih = document.getElementById("gajiBersih").value;

        var flagPensiun = "N";
        var flagJubileum = "N";

        if (confirm('Apakah Anda ingin merubah Data?')) {
            var url_string = window.location.href ;
            var url = new URL(url_string);

            var tipe = document.getElementById("tipe").value;
            if(tipe == "PR" || tipe == "R"){
                PayrollAction.saveEditData(payrollId, nip, tunjanganPeralihan,pemondokan,
                        komunikasi, kopkar, iuranSp, iuranPiikb,bankBri, bankMandiri, infaq, perkesDanObat,
                        listrik, iuranProfesi, potonganLain,
                        flagJubileum, flagPensiun, tunjPph, pphGaji,nilaiPtt,totalA,totalB,totalC,gajiBersih,totalPttSetahun,pphSeharusnya,pph11Bulan,selisihPph21,totalPotLainLain,bulan,tahun,peralihanGapok,peralihanSankhus,peralihanTunjangan, function(listdata) {
                            alert('Data Berhasil Dirubah');
                            $('#modal-edit').modal('hide');
                            $('#formEdit')[0].reset();
                        });
            }else if(tipe == "JP"){
                PayrollAction.saveEditDataJasprod(payrollId, koperasi, dansos, lainLain, flagKalkulasiPph, pphGaji, function(listdata) {
                            alert('Data Berhasil Dirubah');
                            $('#modal-edit').modal('hide');
                            $('#formEdit')[0].reset();
                        });
            }
        } else {
            // Do nothing!
        }

    });

    $(document).ready(function() {
        var url_string = window.location.href ;
        var url = new URL(url_string);
        var bulan = url.searchParams.get("bulan");
        var tahun = url.searchParams.get("tahun");
        var branchId = url.searchParams.get("branchId");
        var tipe = url.searchParams.get("tipe");

        $('#bulanPayroll').val(bulan).change();
        $('#tahunPayroll').val(tahun).change();
        $('#branchId').val(branchId).change();
        $('#tipe').val(tipe).change();


        if(bulan != null){
            $('#txtBulanPayroll').val(bulan);
            $('#txtTahunPayroll').val(tahun);
            $('#txtBranchId').val(branchId);
            $('#txtTipeId').val(tipe);
        }else{
            var branchId = document.getElementById("txtBranchId").value;
            var tahun = document.getElementById("txtTahunPayroll").value;
            var bulan = document.getElementById("txtBulanPayroll").value;
            var tipe = document.getElementById("txtTipeId").value;
            var nip = document.getElementById("personName1").value;
            $('#personName2').val(nip);
            $('#bulanPayroll').val(bulan).change();
            $('#tahunPayroll').val(tahun).change();
            $('#branchId').val(branchId).change();
        }

        if (tipe!="PR"){
            $('#detailTunjlain').hide();
            $('#detailLembur').hide();
            $('#btnDetailPtt').hide();
            $('#btnSave').hide();
            if (bulan=="12"){
                $('#btnViewTotalPtt11').hide();
                $('#btnViewPPhSeharusnya').hide();
                $('#btnViewTotalPPh11').hide();
            }
        }

        window.menujuLink = function(param, param2){
            if (confirm('Kembali ke halaman utama '+param2+'?')) {
                window.location.href=param;
            }
        }


        $('.detailJasprod').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;

            PayrollAction.getDetailEditJasprod(payrollId, function(listdata){
                if(listdata != null){
                    $('#jasprodMasKer1').val(listdata.masaKerja);
                    $('#jasprodGaji').val(listdata.gajiGolongan);
                    $('#jasprodTunjUmk').val(listdata.tunjanganUmk);
                    $('#jasprodTunjStruktural').val(listdata.tunjanganStruktural);
                    $('#jasprodTunjJabStruktural').val(listdata.tunjanganJabStruktural);
                    $('#jasprodTunjPeralihan').val(listdata.tunjPeralihan);
                    $('#jasprodTunjStrategis').val(listdata.tunjanganStrategis);

                    $('#jasprodTotalBruto').val(listdata.gajiBruto);
                    $('#jasprodPengali').val(listdata.gajiMasaKerja);
                    $('#jasprodFaktor').val(listdata.stFaktor);
                    $('#jasprodNilaiSmk').val(listdata.stNilaiSmk);
                    $('#jasprodPersenSmk').val(listdata.stPersenSmk);
                    $('#jasprodPerhitungan').val(listdata.perhitungan);
                    $('#jasprodGajiKotor').val(listdata.gajiMasaKerjaFaktor);
                    $('#jasprodTambahan').val(listdata.tambahan);
                    $('#jasprodNilai').val(listdata.nilaiJasprod);
                }else{
                    $('#jasprodGaji').val('0,00');
                    $('#jasprodTunjUmk').val('0,00');
                    $('#jasprodTunjStruktural').val('0,00');
                    $('#jasprodTunjJabStruktural').val('0,00');
                    $('#jasprodTunjStrategis').val('0,00');
                }

            });

            $('#modal-jasprod').find('.modal-title').text('Komposisi Jasprod');
            $('#modal-jasprod').modal('show');
        });

        $('.detailInsentif').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;
            PayrollAction.getDetailEditInsentif(payrollId, function(listdata){
                if(listdata != 'Y'){

                    $('#insentifMasaKerja').val(listdata.masaKerja + " Bulan");
                    $('#insentifBulanMulai').val(listdata.bulanMulai);
                    $('#insentifBulanSampai').val(listdata.bulanSampai);
                    $('#insentifTahun').val(listdata.tahunInsentif);

                    $('#insentifGaji').val(listdata.gajiGolongan);
                    $('#insentifTunjUmk').val(listdata.tunjanganUmk);
                    $('#insentifTunjStruktural').val(listdata.tunjanganStruktural);
                    $('#insentifTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
                    $('#insentifTunjPeralihan').val(listdata.tunjanganPeralihan);
                    $('#insentifTunjStrategis').val(listdata.tunjanganStrategis);

                    $('#insentifTotal').val(listdata.jumlahBruto);

                    $('#insentifPot').val(listdata.potonganinsentifNilai);
                    $('#insentifPotIndividu').val(listdata.potonganinsentifIndividu);

                    $('#insentifSmkStandart').val(listdata.smkStandart);
                    $('#insentifSmkHuruf').val(listdata.smkHuruf);
                    $('#insentifSmkAngka').val(listdata.smkAngka);

                    $('#insentifDiterima').val(listdata.insentifyangDiterima);
                }

            });

            $('#modal-insentif').find('.modal-title').text('Komposisi Insentif');
            $('#modal-insentif').modal('show');
        });

        $('.detailRapelBulan').on('click', function(){
            var rapelId = document.getElementById("rapelId").value;

            loadRapelDetail(rapelId);

            $('#modal-rapel-bulan').find('.modal-title').text('Detail Rapel Bulan');
            $('#modal-rapel-bulan').modal('show');
        });

        $('.detailRapel').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;

            PayrollAction.getDetailEditRapel(payrollId, function(listdata){
                // Golongan Lama, Baru dan total bulan
                $('#rapelId').val(listdata.rapelId);
                $('#rapelGolonganBaru').val(listdata.golonganBaru);
                $('#rapelGolonganLama').val(listdata.golonganLama);
                $('#rapelJumlahBulan').val(listdata.jumlahBulan + " Bulan");

                // Tunjangan Baru
                $('#rapelGajiGolonganBaru').val(listdata.gajiGolonganBaru);
                $('#rapelTunjUmkBaru').val(listdata.tunjanganUmkBaru);
                $('#rapelTunjStrukturalBaru').val(listdata.tunjanganStrukturalBaru);
                $('#rapelTunjJabStrukturalBaru').val(listdata.tunjanganJabatanStrukturalBaru);
                $('#rapelTunjStrategisBaru').val(listdata.tunjanganStrategisBaru);
                $('#rapelTunjAirListrikBaru').val(listdata.tunjanganAirListrikBaru);
                $('#rapelTunjPerumahanBaru').val(listdata.tunjanganPerumahanBaru);

                // Tunjangan Lama
                $('#rapelGajiGolonganLama').val(listdata.gajiGolonganLama);
                $('#rapelTunjUmkLama').val(listdata.tunjanganUmkLama);
                $('#rapelTunjStrukturalLama').val(listdata.tunjanganStrukturalLama);
                $('#rapelTunjJabStrukturalLama').val(listdata.tunjanganJabatanStrukturalLama);
                $('#rapelTunjStrategisLama').val(listdata.tunjanganStrategisLama);
                $('#rapelTunjAirListrikLama').val(listdata.tunjanganAirListrikLama);
                $('#rapelTunjPerumahanLama').val(listdata.tunjanganPerumahanLama);

                // Total Selisih baru dan lama
                $('#rapelGajiGolongan').val(listdata.gajiGolongan);
                $('#rapelTunjUmk').val(listdata.tunjanganUmk);
                $('#rapelTunjStruktural').val(listdata.tunjanganStruktural);
                $('#rapelTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
                $('#rapelTunjStrategis').val(listdata.tunjanganStrategis);
                $('#rapelTunjAirListrik').val(listdata.tunjanganAirListrik);
                $('#rapelTunjPerumahan').val(listdata.tunjanganPerumahan);

                $('#rapelDetailBulanJumlah').val(listdata.totalRapel);
                $('#rapelDetailBulanJumlahKali').val(listdata.totalRapelBulan);

                // Rapel tunjangan lain
                $('#rapelThr').val(listdata.totalRapelThr);
                $('#rapelPendidikan').val(listdata.totalRapelPendidikan);
                $('#rapelInsentif').val(listdata.totalRapelInsentif);
                $('#rapelJubileum').val(listdata.totalRapelJubileum);
                $('#rapelBulan').val(listdata.totalRapelBulan);
                $('#rapelLembur').val(listdata.totalRapelLembur);
                $('#rapelDetailLemburTotal').val(listdata.totalRapelLembur);
                $('#rapelTotal').val(listdata.totalRapelFinal);
            });

            $('#modal-rapel').find('.modal-title').text('Komposisi Rapel');
            $('#modal-rapel').modal('show');
        });

        $('.detailRapelThr').on('click', function(){
            var rapelId = document.getElementById("rapelId").value;

            PayrollAction.getDetailEditRapelThr(rapelId, function(listdata){
                $('#rapelDetailThrGadasLama').val(listdata.thrGajiGolonganLama);
                $('#rapelDetailThrUmkLama').val(listdata.thrUmkLama);
                $('#rapelDetailThrStrukturalLama').val(listdata.thrStrukturalLama);
                $('#rapelDetailThrJabStrukturalLama').val(listdata.thrJabStrukturalLama);
                $('#rapelDetailThrStrategisLama').val(listdata.thrStrategisLama);

                $('#rapelDetailThrGadasBaru').val(listdata.thrGajiGolonganBaru);
                $('#rapelDetailThrUmkBaru').val(listdata.thrUmkBaru);
                $('#rapelDetailThrStrukturalBaru').val(listdata.thrStrukturalBaru);
                $('#rapelDetailThrJabStrukturalBaru').val(listdata.thrJabStrukturalBaru);
                $('#rapelDetailThrStrategisBaru').val(listdata.thrStrategisBaru);

                $('#rapelDetailThrSelisihGadas').val(listdata.thrGajiGolonganSelisihBaru);
                $('#rapelDetailThrSelisihUmk').val(listdata.thrUmkSelisihBaru);
                $('#rapelDetailThrSelisihStruktural').val(listdata.thrStrukturalSelisihBaru);
                $('#rapelDetailThrSelisihJabStruktural').val(listdata.thrJabStrukturalSelisihBaru);
                $('#rapelDetailThrSelisihStrategis').val(listdata.thrStrategisSelisihBaru);
                $('#rapelDetailThrTotal').val(listdata.totalRapelSelisihBaru);
            });

            $('#modal-rapel-thr').find('.modal-title').text('Detail Rapel THR');
            $('#modal-rapel-thr').modal('show');
        });

        $('.detailRapelInsentif').on('click', function(){
            var rapelId = document.getElementById("rapelId").value;

            PayrollAction.getDetailEditRapelInsentif(rapelId, function(listdata){
                $('#rapelDetailInsentifJumlahMasaKerja').val(listdata.masaKerja + " Bulan");
                $('#rapelDetailInsentifPotonganInsentif').val(listdata.potonganInsentifNilai);
                $('#rapelDetailInsentifPotonganInsentifIndividu').val(listdata.potonganInsentifIndividu);
                $('#rapelDetailInsentifBrutoBaru').val(listdata.brutoInsentif);
                $('#rapelDetailInsentifSmkStandart').val(listdata.smkStandart);
                $('#rapelDetailInsentifSmkHuruf').val(listdata.smkHuruf);
                $('#rapelDetailInsentifSmkAngka').val(listdata.smkAngka);

                $('#rapelDetailInsentifYangDiterimaBaru').val(listdata.insentifYangDiterimaBaru);
                $('#rapelDetailInsentifYangDiterimaLama').val(listdata.insentifYangDiterimaLama);

                $('#rapelDetailInsentifyangDiterimaSelisih').val(listdata.insentifYangDiterimaSelisih);
            });

            $('#modal-rapel-insentif').find('.modal-title').text('Detail Rapel Insentif');
            $('#modal-rapel-insentif').modal('show');
        });

        $('.detailRapelPendidikan').on('click', function(){
            var rapelId = document.getElementById("rapelId").value;

            PayrollAction.getDetailEditRapelPendidikan(rapelId, function(listdata){
                $('#rapelDetailPendidikanGadasLama').val(listdata.pendidikanGajiGolonganLama);
                $('#rapelDetailPendidikanUmkLama').val(listdata.pendidikanUmkLama);
                $('#rapelDetailPendidikanStrukturalLama').val(listdata.pendidikanStrukturalLama);
                $('#rapelDetailPendidikanJabStrukturalLama').val(listdata.pendidikanJabStrukturalLama);
                $('#rapelDetailPendidikanStrategisLama').val(listdata.pendidikanStrategisLama);
                $('#rapelDetailPendidikanAirListrikLama').val(listdata.pendidikanAirListrikLama);

                $('#rapelDetailPendidikanGadasBaru').val(listdata.pendidikanGajiGolonganBaru);
                $('#rapelDetailPendidikanUmkBaru').val(listdata.pendidikanUmkBaru);
                $('#rapelDetailPendidikanStrukturalBaru').val(listdata.pendidikanStrukturalBaru);
                $('#rapelDetailPendidikanJabStrukturalBaru').val(listdata.pendidikanJabStrukturalBaru);
                $('#rapelDetailPendidikanStrategisBaru').val(listdata.pendidikanStrategisBaru);
                $('#rapelDetailPendidikanAirListrikBaru').val(listdata.pendidikanAirListrikBaru);

                $('#rapelDetailPendidikanSelisihGadas').val(listdata.pendidikanGajiGolonganSelisihBaru);
                $('#rapelDetailPendidikanSelisihUmk').val(listdata.pendidikanUmkSelisihBaru);
                $('#rapelDetailPendidikanSelisihStruktural').val(listdata.pendidikanStrukturalSelisihBaru);
                $('#rapelDetailPendidikanSelisihJabStruktural').val(listdata.pendidikanJabStrukturalSelisihBaru);
                $('#rapelDetailPendidikanSelisihStrategis').val(listdata.pendidikanStrategisSelisihBaru);
                $('#rapelDetailPendidikanSelisihAirListrik').val(listdata.pendidikanAirListrikSelisihBaru);
                $('#rapelDetailPendidikanTotal').val(listdata.totalRapelSelisihBaru);
            });

            $('#modal-rapel-pendidikan').find('.modal-title').text('Detail Rapel Pendidikan');
            $('#modal-rapel-pendidikan').modal('show');
        });

        $('.detailRapelJubileum').on('click', function(){
            var rapelId = document.getElementById("rapelId").value;

            PayrollAction.getDetailEditRapelJubileum(rapelId, function(listdata){
                $('#rapelDetailJubileumGadasLama').val(listdata.jubileumGajiGolonganLama);
                $('#rapelDetailJubileumUmkLama').val(listdata.jubileumUmkLama);
                $('#rapelDetailJubileumStrukturalLama').val(listdata.jubileumStrukturalLama);
                $('#rapelDetailJubileumJabStrukturalLama').val(listdata.jubileumJabStrukturalLama);
                $('#rapelDetailJubileumPeralihanLama').val(listdata.jubileumPeralihanLama);

                $('#rapelDetailJubileumGadasBaru').val(listdata.jubileumGajiGolonganBaru);
                $('#rapelDetailJubileumUmkBaru').val(listdata.jubileumUmkBaru);
                $('#rapelDetailJubileumStrukturalBaru').val(listdata.jubileumStrukturalBaru);
                $('#rapelDetailJubileumJabStrukturalBaru').val(listdata.jubileumJabStrukturalBaru);
                $('#rapelDetailJubileumPeralihanBaru').val(listdata.jubileumPeralihanBaru);

                $('#rapelDetailJubileumSelisihGadas').val(listdata.jubileumGajiGolonganSelisihBaru);
                $('#rapelDetailJubileumSelisihUmk').val(listdata.jubileumUmkSelisihBaru);
                $('#rapelDetailJubileumSelisihStruktural').val(listdata.jubileumStrukturalSelisihBaru);
                $('#rapelDetailJubileumSelisihJabStruktural').val(listdata.jubileumJabStrukturalSelisihBaru);
                $('#rapelDetailJubileumSelisihPeralihan').val(listdata.jubileumPeralihanSelisihBaru);
                $('#rapelDetailJubileumSubTotal').val(listdata.totalRapelSelisihBaru);

                $('#rapelDetailJubileumTotal').val(listdata.totalRapelJubileum);
            });

            $('#modal-rapel-jubileum').find('.modal-title').text('Detail Rapel Jubileum');
            $('#modal-rapel-jubileum').modal('show');
        });

        $('.detailRapelLembur').on('click', function(){
            var rapelId = document.getElementById("rapelId").value;

            $('.tableDetailAbsensi').find('tbody').remove();
            $('.tableDetailAbsensi').find('thead').remove();

            var tmp_table = "";
            var tmp_table2 = "<tbody>";
            var hasil = 0;

            PayrollAction.getDetailEditRapelLembur(rapelId, function(listdata){
                tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +
                        "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Jam Lembur</th>" +

                        "<th style='text-align: center; background-color:  #3c8dbc''>Biaya Lama</th>" +

                        "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Dasar Baru</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tunj Umk Baru</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tunj Peralihan Baru</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Biaya Baru</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Selisih</th>" +

                        "</tr></thead><tbody>";

                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                            '<td >' + (i + 1)+ '</td>' +
                            '<td align="center">' + item.strTanggal + '</td>' +
                            '<td align="center">' + item.jamLembur+ '</td>' +
                            '<td align="center">' + item.strBiayaLemburLama+ '</td>' +

                            '<td align="center">' + item.strGajiGolonganBaru+ '</td>' +
                            '<td align="center">' + item.strTunjanganUmkBaru+ '</td>' +
                            '<td align="center">' + item.strTunjanganPeralihanBaru+ '</td>' +
                            '<td align="center">' + item.strBiayaLemburBaru+ '</td>' +
                            '<td align="center">' + item.strSelisihBiayaLemburBaru+ '</td>' +

                            "</tr>";
                    hasil = hasil + parseInt(item.selisihBiayaLemburBaru)
                });
                tmp_table += "</tbody>";
                $('.tableDetailAbsensi').append(tmp_table);

                if(window.myDataTable){
                    window.myDataTable.destroy();
                    window.myDataTable = $("#tableDetailAbsensi").DataTable();
                }else{
                    window.myDataTable = $("#tableDetailAbsensi").DataTable();
                }
            });

            $('#modal-rapel-lembur').find('.modal-title').text('Detail Rapel Lembur');
            $('#modal-rapel-lembur').modal('show');
        });

        $('.detailPengobatan').on('click', function(){
            var nip = document.getElementById("nip").value;
            var branchId = document.getElementById("branchId").value;
            var bulan = document.getElementById("bulan").value;
            var tahun = document.getElementById("tahun").value;
            var jumlah = "" ;

            $('#modal-pengobatan').find('.modal-title').text('Edit Biaya Pengobatan');
            $('#modal-pengobatan').modal('show');
        });

        $('.detailThr').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;

            PayrollAction.getDetailEditThr(payrollId, function(listdata){
                $('#thrBulanAktif').val(listdata.bulanAktif);
                $('#thrGaji').val(listdata.gajiGolongan);
                $('#thrTunjUmk').val(listdata.tunjanganUmk);
                $('#thrTunjStruktural').val(listdata.tunjanganStruktural);
                $('#thrTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
                $('#thrTunjStrategis').val(listdata.tunjanganStrategis);
                $('#thrTunjPeralihan').val(listdata.tunjanganPeralihan);
                $('#thrTunjPph').val(listdata.tunjanganPph);
                $('#thrTotal').val(listdata.totalThr);
                $('#thrTotalBersih').val(listdata.totalThrBersih);
            });

            $('#modal-thr').find('.modal-title').text('Komposisi THR');
            $('#modal-thr').modal('show');
        });

        $('.detailPendidikan').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;

            PayrollAction.getDetailEditPendidikan(payrollId, function(listdata){
                $('#pendidikanBulanAktif').val(listdata.bulanAktif);
                $('#pendidikanGaji').val(listdata.gaji);
                $('#pendidikanTunjUmk').val(listdata.tunjanganUmk);
                $('#pendidikanTunjStruktural').val(listdata.tunjanganStruktural);
                $('#pendidikanTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
                $('#pendidikanTunjPeralihan').val(listdata.tunjanganPeralihan);
                $('#pendidikanTunjStrategis').val(listdata.tunjanganStrategis);
                $('#pendidikanTunjAirListrik').val(listdata.tunjanganAirListrik);
                $('#pendidikanTunjKompensasi').val(listdata.tunjanganKompensasi);
                $('#pendidikanTunjPph').val(listdata.tunjanganPph);
                $('#pendidikanTotal').val(listdata.totalPendidikan);
                //$('#pendidikanTotalBersih').val(Number(listdata.totalPendidikan) + Number(listdata.tunjanganPph));

                $('#pendidikanBulanAktif').val(listdata.bulanAktif);
            });

            $('#modal-pendidikan').find('.modal-title').text('Komposisi Pendidikan');
            $('#modal-pendidikan').modal('show');
        });

        $('.detailPphGaji').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;

            /*PayrollAction.getDetailEditPph(payrollId, function(listdata){*/
            PayrollAction.getDetailPphEditSession(payrollId, function(listdata){
                $('#pphGajiGaji').val(listdata.gaji);
                $('#pphGajiTunjUmk').val(listdata.tunjanganUmk);
                $('#pphGajiTunjStruktural').val(listdata.tunjanganStruktural);
                $('#pphGajiTunjPeralihan').val(listdata.tunjanganPeralihan);
                $('#pphGajiTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);

                $('#pphGajiTunjStrategis').val(listdata.tunjanganStrategis);
                $('#pphGajiTunjKompensasi').val(listdata.tunjanganKompensasi);
                $('#pphGajiTunjTransport').val(listdata.tunjanganTransport);
                $('#pphGajiTunjAirListrik').val(listdata.tunjanganAirListrik);
                $('#pphGajiTunjPerumahan').val(listdata.tunjanganPerumahan);
                $('#pphGajiTunjPengobatan').val(listdata.tunjanganPengobatan);
                $('#pphGajiTunjPph').val(listdata.tunjanganPphTahun);
                $('#pphGajiTunjLembur').val(listdata.tunjanganLembur);
                $('#pphGajiTunjLain').val(listdata.tunjanganLainLain);
                $('#pphGajiAsumsiThr').val(listdata.asumsiThr);
                $('#pphGajiAsumsiPendidikan').val(listdata.asumsiPendidikan);
                $('#pphGajiAsumsiJasprod').val(listdata.asumsiJasprod);
                $('#pphGajiThr').val(listdata.thr);
                $('#pphGajiPendidikan').val(listdata.pendidikan);
                $('#pphGajiJasprod').val(listdata.jasprod);
                $('#pphGajiInsentif').val(listdata.insentif);
                $('#pphGajiRapel').val(listdata.rapel);
                $('#pphGajiJubileum').val(listdata.jubileum);
                /*$('#pphGajiPensiun').val(listdata.pensiun);*/
                $('#pphGajiJkmJkk').val(listdata.iuranJkmJkk);
                $('#pphGajiPakaianDinas').val(listdata.pakaianDinas);
                $('#pphGajiBruto').val(listdata.bruto);

                $('#pphGajiBiayaJabatan').val(listdata.biayaJabatan);
                $('#pphGajiPtkp').val(listdata.ptkp);
                $('#pphGajiIuranPensiun').val(listdata.iuranPensiun);
                $('#pphGajiBpjsDanaPensiun').val(listdata.danaPensiun);
                $('#pphGajiBpjsJht').val(listdata.bpjsJht);
                $('#pphGajiBpjsPensiun').val(listdata.bpjsPensiun);

                $('#pphGajiJumlahB').val(listdata.jumlahB);
                $('#pphGajiPkp').val(listdata.pkp);
                $('#pphGajiHutangPph').val(listdata.hutangPph);

            });

            $('#modal-pphGaji').find('.modal-title').text('Perhitungan Pph Gaji');
            $('#modal-pphGaji').modal('show');
        });

        $('.detailPphPengobatan').on('click', function(){
            $('#modal-pphPengobatan').find('.modal-title').text('PPh Pengobatan');
            $('#modal-pphPengobatan').modal('show');
        });

        $('.detailPphBulan').on('click', function(){
            var nip = document.getElementById("nip").value;

            $('.tableDetailPphBulan').find('tbody').remove();
            $('.tableDetailPphBulan').find('thead').remove();

            var tmp_table = "";
            var tmp_table2 = "<tbody>";
            var hasil = 0;

            PayrollAction.payrollDetailPphTahun('2019', nip, function(listdata){
                tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tipe </th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Biaya Kotor</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>PPh</th>" +
                        "</tr></thead><tbody>";

                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                            '<td align="left">' + item.tipe + '</td>' +
                            '<td align="right">' + item.totalA+ '</td>' +
                            '<td align="right">' + item.pphGaji+ '</td>' +
                            "</tr>";
                });
                tmp_table += "</tbody>";
                $('.tableDetailPphBulan').append(tmp_table);

            });

            $('#modal-pph-bulan').find('.modal-title').text('Detail Seluruh PPH');
            $('#modal-pph-bulan').modal('show');
        });

        $('.btnPphPengobatanApply').on('click', function(){
            var nilai = document.getElementById("pphPengobatanBayar").value;
            updateNilai("", nilai);
        });

        $('.btnPphPengobatanSave').on('click', function(){
            var nilai = document.getElementById("pphPengobatanBayar").value;
            updateNilai("", nilai);
            alert('Data Berhasil Dirubah');
            $('#modal-pphPengobatan').modal('hide');
        });

        $('.detailJubileum').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;
            PayrollAction.getDetailEditJubileum(payrollId, function(listdata){
                $('#jubileumGaji').val(listdata.gajiGolongan);
                $('#jubileumTunjUmk').val(listdata.tunjanganUmk);
                $('#jubileumTunjStruktural').val(listdata.tunjanganStruktural);
                $('#jubileumTunjJabStruktural').val(listdata.tunjanganJabStruktural);
                $('#jubileumPeralihan').val(listdata.tunjanganPeralihan);
                $('#jubileumTotal').val(listdata.besarJubileum);
                $('#jubileumGrandTotal').val(listdata.totalJubileum);
                $('#jubileumPph').val(listdata.pphJubileum);
                $('#jubileumNetto').val(listdata.nettoJubileum);
            });

            $('#modal-jubileum').find('.modal-title').text('Komposisi Jubileum');
            $('#modal-jubileum').modal('show');
        });

        $('.detailJasprodMasaKerja').on('click', function(){
            var nip = document.getElementById("nip").value;
            /*PayrollAction.getDetailAdd(nip, function(listdata){
                //$('#jasprodTglAktif').val(listdata.stTanggalAktif);
                //$('#jasprodTglAktifSekarang').val(listdata.stTanggalAktifSekarang);
                //$('#jubileumJumlahPkwt').val(listdata.lamaPkwt);
            });*/

            $('#modal-jasprod-masaKerja').find('.modal-title').text('Masa Kerja');
            $('#modal-jasprod-masaKerja').modal('show');
        });

        $('.detailPensiun').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;
            PayrollAction.getDetailEditPensiun(payrollId, function(listdata){
                $('#pensiunGaji').val(listdata.gajiGolongan);
                $('#pensiunTunjUmk').val(listdata.tunjanganUmk);
                $('#pensiunTunjStruktural').val(listdata.tunjanganStruktural);
                $('#pensiunTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
                $('#pensiunPeralihan').val(listdata.tunjanganPeralihan);

                $('#pensiunTgl1').val(listdata.stTanggalAktif);

                $('#pensiunKaliMasaKerja').val(listdata.kaliMasaKerja);

                $('#pensiunJumlah').val(listdata.jumlahTunjangan);
                $('#pensiunUangPensiun').val(listdata.jumlahTunjangan);
                $('#pensiunFaktorPensiun').val(listdata.faktorPensiun);
                $('#pensiunKaliPensiun').val(listdata.tunjanganPensiun);
                $('#pensiunUangPenghargaan').val(listdata.jumlahTunjangan);
                $('#pensiunFaktorPenghargaan').val(listdata.faktorPenghargaan);
                $('#pensiunKaliPenghargaan').val(listdata.tunjanganPenghargaan);

                $('#pensiunTotalPenghargaan').val(listdata.jumlahBiayaPensiun);
                $('#pensiunPerumahan').val(listdata.penggantianPerumahan);

                $('#pensiunBiaya').val(listdata.totalPensiun);
                $('#pensiunPph').val(listdata.pphPensiun);
                $('#pensiunNetto').val(listdata.nettoPensiun);
            });

            $('#modal-pensiun').find('.modal-title').text('Komposisi Pensiun');
            $('#modal-pensiun').modal('show');
        });

        $('.detailTunjlain').on('click', function(){
            var nip = document.getElementById("nip").value;
            var bulan = document.getElementById("bulan").value;
            var tahun = document.getElementById("tahun").value;

            $('.tableDetailTunjLain').find('tbody').remove();
            $('.tableDetailTunjLain').find('thead').remove();

            var tmp_table = "";
            var tmp_table2 = "<tbody>";
            var hasil = 0;

            PayrollAction.payrollDetailTunjLain(bulan,tahun, nip, function(listdata){
                tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>No </th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Nama Tunjangan</th>" +
                        "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>" +
                        "</tr></thead><tbody>";
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                            '<td align="left">' + (i+1) + '</td>' +
                            '<td align="left">' + item.namaTunjangan+ '</td>' +
                            '<td align="right">' + item.nilai+ '</td>' +
                            "</tr>";
                });
                tmp_table += "</tbody>";
                $('.tableDetailTunjLain').append(tmp_table);
            });

            $('#modal-detailTunjLain').find('.modal-title').text('Detail Tunj. Lain');
            $('#modal-detailTunjLain').modal('show');
        });

        $('.tablePayroll').on('click', '.item-edit', function(){
            var payrollId = $(this).val().replace(/\n|\r/g, "");
            var payrollId = $(this).attr('data');
            $('#payrollId').val(payrollId);
            PayrollAction.getDetailEdit(payrollId, function(listdata){
                console.log(listdata);
                if(listdata.tipePegawai == "TP03" && listdata.strukturGaji != "G"){
                    $('.detailGaji').show();
                }else{
                    $('.detailGaji').hide();
                }

                if(listdata.flagJasprod == 'Y'){
                    $('.detailJasprod').show();
                }else{
                    $('.detailJasprod').hide();
                }

                if(listdata.flagInsentif == 'Y'){
                    $('.detailInsentif').show();
                }else{
                    $('.detailInsentif').hide();
                }

                if(listdata.flagThr == 'Y'){
                    $('.detailThr').show();
                }else{
                    $('.detailThr').hide();
                }
                if(listdata.flagPayroll == "Y"){
                    $('.detailLembur').show();
                    $('.detailPphGaji').show();
                }else{
                    $('.detailPphGaji').show();
                    //$('.detailPphGaji').hide();
                    $('.detailLembur').hide();
                    // $('#tunjLain').attr('readonly', true);
                    $('#tunjLain').attr('readonly', true);
                    $('#tunjPeralihan').attr('readonly', true);
                    $('#pemondokan').attr('readonly', true);
                    $('#komunikasi').attr('readonly', true);
                    $(".detailTunjlain").hide();


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
                    $('#pphGaji').attr('readonly', true);
                }

                $('#payrollId2').val(listdata.payrollId);
                $('#bulan').val(listdata.bulan);
                $('#tahun').val(listdata.tahun);
                $('#nip').val(listdata.nip);
                $('#branchId2').val(listdata.branchId);
                $('#nama').val(listdata.nama);
                if(listdata.npwp != null){
                    $('#npwp').val(listdata.npwp);
                }else{
                    $('#npwp').val("-");
                }
                $('#divisi').val(listdata.departmentName);
                $('#golongan').val(listdata.golonganName);
                $('#point').val(listdata.point);
                $('#jabatan').val(listdata.positionName);
                $('#statusKeluarga').val(listdata.statusKeluarga);
                $('#jumlahAnak').val(listdata.jumlahAnak);
                $('#gajiPensiun').val(listdata.gajiPensiun);
                $('#tipeDanaPensiun').val(listdata.danaPensiunName);
                $('#tipePegawai').val(listdata.tipePegawai);
                $('#tipePegawaiName').val(listdata.tipePegawaiName);
                $('#golonganDapenId').val(listdata.golonganDapenName);
                $('#masaGolDapen').val(listdata.stMasaKerjaGol);

                /*$('#multifikator').val(listdata.multifikator);
                 $('#gajiBpjs').val(listdata.gajiBpjs);*/

                //komponen A
                $('#gaji').val(listdata.gajiGolongan);
                $('#tunjUmk').val(listdata.tunjanganUmk);
                $('#tunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
                $('#tunjStruktural').val(listdata.tunjanganStruktural);
                $('#tunjStrategis').val(listdata.tunjanganStrategis);
                $('#tunjPeralihan').val(listdata.tunjanganPeralihan);
                $('#tunjTambahan').val(listdata.tunjanganTambahan);
                $('#tunjLain').val(listdata.tunjanganLain);
                $('#pemondokan').val(listdata.pemondokan);
                $('#komunikasi').val(listdata.komunikasi);
                $('#tambahanLain').val(listdata.tambahanLain);
                $('#tunjLembur').val(listdata.tunjanganLembur);

                //komponen B
                //RLAB
                $('#tunjRumah').val(listdata.tunjanganRumah);
                $('#tunjListrik').val(listdata.tunjanganListrik);
                $('#tunjAir').val(listdata.tunjanganAir);
                $('#tunjBbm').val(listdata.tunjanganBbm);
                $('#totalRlab').val(listdata.totalRlab);
                $('#tunjDapen').val(listdata.iuranDapenPersh);
                $('#tunjBpjsKs').val(listdata.tunjanganBpjsKs);
                $('#tunjBpjsTk').val(listdata.tunjanganBpjsTk);
                $('#tunjPph').val(listdata.tunjanganPph);

                //komponen C
                $('#iuranDapenPeg').val(listdata.iuranDapenPeg);
                $('#iuranDapenPersh').val(listdata.iuranDapenPersh);
                $('#iuranBpjsTkPeg').val(listdata.iuranBpjsTkKary);
                $('#iuranBpjsTkPers').val(listdata.iuranBpjsTkPers);
                $('#iuranBpjsKsPeg').val(listdata.iuranBpjsKsKary);
                $('#iuranBpjsKsPers').val(listdata.iuranBpjsKsPersh);
                $('#pphGaji1').val(listdata.pphGaji);
                $('#totalPotonganLain').val(listdata.totalPotonganLain);

                $('#nilaiPtt').val(listdata.lainLain);
                $('#tipePttId1').val(listdata.idLainLain).change();

                //detail komponen C potongan lain -lain
                $('#kopkar').val(listdata.kopkar);
                $('#iuranSp').val(listdata.iuranSp);
                $('#iuranPiiKb').val(listdata.iuranPiikb);
                $('#bankBri').val(listdata.bankBri);
                $('#bankMandiri').val(listdata.bankMandiri);
                $('#infaq').val(listdata.infaq);
                $('#perkesDanObat').val(listdata.perkesDanObat);
                $('#listrik').val(listdata.listrik);
                $('#iuranProfesi').val(listdata.iuranProfesi);
                $('#potonganLain').val(listdata.potonganLain);

                $('#nilaiModPeralihanGapok').val(listdata.stPeralihanGapok);
                $('#nilaiModPeralihanSankhus').val(listdata.stPeralihanSankhus);
                $('#nilaiModPeralihanTunjangan').val(listdata.stPeralihanTunjangan);

                //Total
                $('#totalA').val(listdata.totalA);
                $('#totalB').val(listdata.totalB);
                $('#totalC').val(listdata.totalC);
                $('#gajiBersih').val(listdata.totalGajiBersih);

                //pph bulan 12
                $('#totalPtt').val(listdata.totalLain11Bulan);
                $('#pph11Bulan').val(listdata.pph11Bulan);
                $('#pphSeharusnya').val(listdata.pphSeharusnya);
                $('#selisihPph').val(listdata.selisihPph);
            });
            //alert( $('#branchId1').text);
            $('#modal-edit').find('.modal-title').text('Detail Payroll');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'atasan');
        });

        $('#btnDetailLain').on('click', function(){
            var nip = document.getElementById("nip").value;
            var payrollId = document.getElementById("payrollId").value;
            var keterangan1 = document.getElementById("lainLainKet1").value;
            var keterangan2 = document.getElementById("lainLainKet2").value;
            var keterangan3 = document.getElementById("lainLainKet3").value;
            var keterangan4 = document.getElementById("lainLainKet4").value;
            var keterangan5 = document.getElementById("lainLainKet5").value;

            var biaya1 = document.getElementById("lainLainBiaya1").value;
            var biaya2= document.getElementById("lainLainBiaya2").value;
            var biaya3 = document.getElementById("lainLainBiaya3").value;
            var biaya4 = document.getElementById("lainLainBiaya4").value;
            var biaya5 = document.getElementById("lainLainBiaya5").value;
            var total = document.getElementById("jumlahDetailLainLain").value;

            if(biaya1 == ''){
                biaya1 = '0';
            }
            if(biaya2 == ''){
                biaya2 = '0';
            }
            if(biaya3 == ''){
                biaya3 = '0';
            }
            if(biaya4 == ''){
                biaya4 = '0';
            }
            if(biaya5 == ''){
                biaya5 = '0';
            }

            PayrollAction.savePotonganLainData(payrollId, keterangan1, keterangan2, keterangan3, keterangan4, keterangan5, biaya1, biaya2, biaya3,
                    biaya4, biaya5, total, function(listdata){
                        alert('Record has been saved successfully');
                    });
        });

        $('.detailGaji').on('click', function(){
            var nip = document.getElementById("nip").value;

            $('#modal-gaji').find('.modal-title').text('Detail Gaji');
            $('#modal-gaji').modal('show');
        });
        $('#tanggaGajiAkhir').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });
        $('#tanggaGajiAwal').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });
        $('.btnCariGaji').on('click', function(){
            var nip = document.getElementById("nip").value;
            var branchId = document.getElementById("branchId").value;
            var tglAwal = document.getElementById("tanggaGajiAwal").value;
            var tglAkhir = document.getElementById("tanggaGajiAkhir").value;

            if(tglAwal == '' || tglAkhir == ''){
                alert('Tanggal Awal dan Tanggal Akhir harus diisi!');
            }else{
                $('.tabelDetailGaji').find('tbody').remove();
                $('.tabelDetailGaji').find('thead').remove();
                $('.tabelDetailGaji').find('tfoot').remove();
                dwr.engine.setAsync(false);
                var tmp_table = "";
                PayrollAction.listGajiHarianData(nip, branchId, tglAwal, tglAkhir, function(listdata) {
                    tmp_table = "<thead style='color: white' ><tr class='active'>"+
                            "<th style='text-align: center; background-color:  #3c8dbc'><input type='checkbox' class='checkApprove' id='checkAll'></th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Absen Masuk</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Absen Keluar</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Hari</th>"+
                            "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                            "</tr></thead>";
                    var i = i ;
                    $.each(listdata, function (i, item) {
                        document.getElementById("tmpJmlGajiPkwt").innerHTML  = item.gaji;
                        document.getElementById("tmpJmlGajiPkwtNilai").innerHTML  = item.gajiNilai;
                        var combo = "";
                        if(item.status == "Y"){
                            combo = '<input disabled type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.stTnggal+'" class="checkApprove" ' +
                                    'onchange="hitungCentang()">';
                        }else{
                            if(item.checked == "Y"){
                                combo = '<input checked type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.stTnggal+'" class="checkApprove" ' +
                                        'onchange="hitungCentang()">';
                            }else{
                                combo = '<input type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.stTnggal+'" class="checkApprove" ' +
                                        'onchange="hitungCentang()">';
                            }
                        }

                        tmp_table += '<tr  ">' +
                                '<td align="center">' + combo + '</td>' +
                                '<td align="center">' + item.stTnggal + '</td>' +
                                '<td align="center">' + item.absenMasuk + '</td>' +
                                '<td align="center">' + item.absenKeluar  + '</td>' +
                                '<td align="center">' + item.hari + '</td>' +
                                '<td align="center">' + item.status + '</td>' +
                                "</tr>";
                    });
                    var label = $('#tmpJmlGajiPkwt');
                    tmp_table += '' +
                            '<tfoot> ' +
                            '<tr>' +
                            '<td colspan="3">Grand Total</td>' +
                            '<td><label id="jmlAbsenPkwt"></label></td>' +
                            '<td><label id="jmlGajiPkwt">'+label.text()+'</label></td>' +
                            '<td><label id="totalGajiPkwt"></label></td>' +
                            '</tr>' +
                            '</tfoot>';
                    $('.tabelDetailGaji').append(tmp_table);
                    hitungCentang();
                    $("#checkAll").change(function(){
                        $('input:checkbox').not(this).not("[disabled]").prop('checked', this.checked);
                        hitungCentang();
                    });
                });
            }
        });

        $('.detailBiayaLainLain').on('click', function(){
            var payrollId = document.getElementById("payrollId").value;

            PayrollAction.getDataPotonganLain(payrollId, function(listdata){
                if(listdata != null){
                    $('#lainLainKet1').val(listdata.keterangan1);
                    $('#lainLainKet2').val(listdata.keterangan2);
                    $('#lainLainKet3').val(listdata.keterangan3);
                    $('#lainLainKet4').val(listdata.keterangan4);
                    $('#lainLainKet5').val(listdata.keterangan5);

                    $('#lainLainBiaya1').val(listdata.nilai1);
                    $('#lainLainBiaya2').val(listdata.nilai2);
                    $('#lainLainBiaya3').val(listdata.nilai3);
                    $('#lainLainBiaya4').val(listdata.nilai4);
                    $('#lainLainBiaya5').val(listdata.nilai5);
                    $('#jumlahDetailLainLain').val(listdata.total);
                }else{
                    $('#lainLainKet1').val("");
                    $('#lainLainKet2').val("");
                    $('#lainLainKet3').val("");
                    $('#lainLainKet4').val("");
                    $('#lainLainKet5').val("");

                    $('#lainLainBiaya1').val("0");
                    $('#lainLainBiaya2').val("0");
                    $('#lainLainBiaya3').val("0");
                    $('#lainLainBiaya4').val("0");
                    $('#lainLainBiaya5').val("0");
                    $('#jumlahDetailLainLain').val("0");

                }
            });

            $('#modal-biayaLainLain').find('.modal-title').text('Detail Lain - Lain');
            $('#modal-biayaLainLain').modal('show');
        });

        $('#detailLembur').on('click', function(){
            var nip = document.getElementById("nip").value;
            var branchId = document.getElementById("branchId").value;
            var unit = document.getElementById("branchId").value;
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

        $('#btnApprove').click(function(){
            var values = new Array();
            $.each($("input[name='checkApprove[]']:checked"), function() {
                values.push($(this).val());
            });

            var label = $('#tmpJmlGajiPkwtNilai');
            var gaji = parseInt(label.text()) * parseInt(values.length);

            if(values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    var nip = document.getElementById('nip').value;
                    var payrollId = document.getElementById('payrollId').value;
                    PayrollAction.removeDataGajiPkwt(payrollId);
                    $.each($("input[name='checkApprove[]']:checked"), function() {
                        PayrollAction.saveDataGajiPkwt(payrollId, $(this).val(), function(listdata) {
                            $('#modal-gaji').modal('hide');
                            $('#formGaji')[0].reset();
                            $('.tabelDetailGaji').find('tbody').remove();
                            $('.tabelDetailGaji').find('thead').remove();
                            $('.tabelDetailGaji').find('tfoot').remove();
                        });
                    });
                    $('#gaji').val(gaji);
                    changeJubileumNPensiun();
                }
            }else{
                alert('Silahkan Centang Tanggal yang akan di Approve !');
            }
        });

        $('#btnSimpanBiayaLembur').click(function(){
            var nip = document.getElementById("nip").value;
            var branch = document.getElementById("branchId").value;
            var biayaLembur = document.getElementById("fieldBiayaLembur").value;

            if (confirm('Apakah Anda ingin merubah biaya lembur?')) {
                updateNilai('fieldBiayaLembur', biayaLembur);
                $('#modal-dataLembur').modal('hide');
            }
        });

        $('#btnSimpanBiayaPengobatan').click(function(){
            var nip = document.getElementById("nip").value;
            var branch = document.getElementById("branchId").value;
            var biayaPengobatan = document.getElementById("fieldBiayaPengobatan").value;

            if (confirm('Apakah Anda ingin merubah biaya Pengobatan?')) {
                updateNilai('fieldBiayaPengobatan', biayaPengobatan);
                $('#modal-pengobatan').modal('hide');
            }
        });

        $('.tablePayroll').on('click', '.item-promosi', function(){
            var nip = $(this).attr('nip');
            var payrollId = $(this).attr('payrollId');
            var masaKerja = 0 ;;
            PayrollAction.getDetailEdit(payrollId, function(listdata){
                $('#promosiNip').val(listdata.nip);
                $('#promosiNama').val(listdata.nama);
                $('#promosiBagian').val(listdata.departmentName);
                $('#promosiJabatan').val(listdata.positionName);
                if(listdata.golonganName == null){
                    $('#promosiGolongan').val("-");
                }else{
                    $('#promosiGolongan').val(listdata.golonganName);
                }
                $('#promosiPoin').val(listdata.point);
                masaKerja = listdata.masaKerjaTahun;
            });

            $('.tabelDetailPromosi').find('tbody').remove();
            $('.tabelDetailPromosi').find('thead').remove();
            $('.tabelDetailPromosi').find('tfoot').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            PayrollAction.listPromosi(nip, function(listdata) {
                tmp_table = "<thead style='color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Tahun</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Nilai Prestasi</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Poin</th>"+
                        "</tr></thead>";
                var i = i ;
                var totalPoin = 0;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr  ">' +
                            '<td align="center">' + item.periode + '</td>' +
                            '<td align="center">' + item.nilai + '</td>' +
                            '<td align="center">' + item.nilaiPrestasi + '</td>' +
                            '<td align="center">' + item.poin+ '</td>' +
                            "</tr>";
                    totalPoin += item.poin;
                });
                tmp_table += '' +
                        '<tfoot> ' +
                        '<tr>' +
                        '<td colspan="3" align="right">Total Poin</td>' +
                        '<td align="center">'+totalPoin+'</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td colspan="3" align="right">Masa Kerja (Tahun)</td>' +
                        '<td align="center">'+masaKerja+'</td>' +
                        '</tr>' +
                        '</tfoot>';
                $('.tabelDetailPromosi').append(tmp_table);
            });

            $('#modal-promosi').find('.modal-title').text('Detail Pencapaian SMK');
            $('#modal-promosi').modal('show');
        });

    });

    window.hitungCentang = function(){
        var label = $('#tmpJmlGajiPkwtNilai');
        var values = new Array();
        $.each($("input[name='checkApprove[]']:checked"), function() {
            values.push($(this).val());
        });
        var gaji = parseInt(label.text()) * parseInt(values.length);

        document.getElementById("jmlAbsenPkwt").innerHTML  = values.length;
        document.getElementById("totalGajiPkwt").innerHTML  = gaji;

    }

    window.hitungLainLain = function() {
        var biaya1 = document.getElementById("lainLainBiaya1").value;
        var biaya2= document.getElementById("lainLainBiaya2").value;
        var biaya3 = document.getElementById("lainLainBiaya3").value;
        var biaya4 = document.getElementById("lainLainBiaya4").value;
        var biaya5 = document.getElementById("lainLainBiaya5").value;

        if(biaya1 == ''){
            biaya1 = '0';
        }
        if(biaya2 == ''){
            biaya2 = '0';
        }
        if(biaya3 == ''){
            biaya3 = '0';
        }
        if(biaya4 == ''){
            biaya4 = '0';
        }
        if(biaya5 == ''){
            biaya5 = '0';
        }

        var biaya = 0 ;
        hasil = parseInt(biaya1) + parseInt(biaya2) + parseInt(biaya3) +parseInt(biaya4) + parseInt(biaya5) ;

        document.getElementById("jumlahDetailLainLain").value = hasil;
        document.getElementById("lainLain").value = hasil;
    }

    window.cekJubileum = function(nip){
        var hasil = false;
        PayrollAction.cekJubileum(nip,function(listdata){
            hasil = listdata;
        });
        return hasil;
    }

    window.cekPensiun = function(nip){
        var hasil = false;
        PayrollAction.cekPensiun(nip,function(listdata){
            hasil = listdata;
        });
        return hasil;
    }

    window.loadDataModal = function(){
        //var payrollId = $(this).val().replace(/\n|\r/g, "");
        var payrollId = document.getElementById("payrollId").value;
        PayrollAction.getDetailEdit(payrollId, function(listdata){
            console.log(listdata);

            $('#bulan').val(listdata.bulan);
            $('#tahun').val(listdata.tahun);
            $('#nip').val(listdata.nip);
            if(listdata.npwp != null){
                $('#npwp').val(listdata.npwp);
            }else{
                $('#npwp').val("-");
            }
            $('#branchId2').val(listdata.branchId);
            $('#nama').val(listdata.nama);
            $('#divisi').val(listdata.departmentName);
            $('#golongan').val(listdata.golonganName);
            $('#point').val(listdata.point);
            $('#jabatan').val(listdata.positionName);
            $('#statusKeluarga').val(listdata.statusKeluarga);
            $('#jumlahAnak').val(listdata.jumlahAnak);
            $('#gajiPensiun').val(listdata.gajiPensiun);
            $('#tipePegawai').val(listdata.tipePegawai);
            $('#tipePegawaiName').val(listdata.tipePegawaiName);

            $('#multifikator').val(listdata.multifikator);
            $('#gajiBpjs').val(listdata.gajiBpjs);

            //komponen A
            $('#gaji').val(listdata.gajiGolongan);
            $('#tunjUmk').val(listdata.tunjanganUmk);
            $('#tunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
            $('#tunjStruktural').val(listdata.tunjanganStruktural);
            $('#tunjStrategis').val(listdata.tunjanganStrategis);
            $('#tunjPeralihan').val(listdata.tunjanganPeralihan);
            $('#tunjTambahan').val(listdata.tunjanganTambahan);
            $('#tunjLain').val(listdata.tunjanganLain);
            $('#pemondokan').val(listdata.pemondokan);
            $('#komunikasi').val(listdata.komunikasi);

            //komponen B
            //RLAB
            $('#tunjRumah').val(listdata.tunjanganRumah);
            $('#tunjListrik').val(listdata.tunjanganListrik);
            $('#tunjAir').val(listdata.tunjanganAir);
            $('#tunjBbm').val(listdata.tunjanganBbm);
            $('#totalRlab').val(listdata.totalRlab);
            $('#tunjDapen').val(listdata.iuranDapenPersh);
            $('#tunjBpjsKs').val(listdata.tunjanganBpjsKs);
            $('#tunjBpjsTk').val(listdata.tunjanganBpjsTk);
            $('#tunjPph').val(listdata.tunjanganPph);

            //komponen C
            $('#iuranDapenPeg').val(listdata.iuranDapenPeg);
            $('#iuranDapenPersh').val(listdata.iuranDapenPersh);
            $('#iuranBpjsTkPeg').val(listdata.iuranBpjsTkKary);
            $('#iuranBpjsTkPers').val(listdata.iuranBpjsTkPers);
            $('#iuranBpjsKsPeg').val(listdata.iuranBpjsKsKary);
            $('#iuranBpjsKsPers').val(listdata.iuranBpjsKsPersh);
            $('#pphGaji1').val(listdata.pphGaji);
            $('#totalPotonganLain').val(listdata.totalPotonganLain);

            //detail komponen C potongan lain -lain
            $('#kopkar').val(listdata.kopkar);
            $('#iuranSp').val(listdata.iuranSp);
            $('#iuranPiiKb').val(listdata.iuranPiikb);
            $('#bankBri').val(listdata.bankBri);
            $('#bankMandiri').val(listdata.bankMandiri);
            $('#infaq').val(listdata.infaq);
            $('#perkesDanObat').val(listdata.perkesDanObat);
            $('#listrik').val(listdata.listrik);
            $('#iuranProfesi').val(listdata.iuranProfesi);
            $('#potonganLain').val(listdata.potonganLain);

            //Komponen D
            $('#nilaiPtt').val(listdata.lainLain);
            $('#tipePttId1').val(listdata.idLainLain).change();

            $('#nilaiModPeralihanGapok').val(listdata.stPeralihanGapok);
            $('#nilaiModPeralihanSankhus').val(listdata.stPeralihanSankhus);
            $('#nilaiModPeralihanTunjangan').val(listdata.stPeralihanTunjangan);

            //Total
            $('#totalA').val(listdata.totalA);
            $('#totalB').val(listdata.totalB);
            $('#totalC').val(listdata.totalC);
            $('#gajiBersih').val(listdata.totalGajiBersih);

            //pph bulan 12
            $('#totalPtt').val(listdata.totalLain11Bulan);
            $('#pph11Bulan').val(listdata.pph11Bulan);
            $('#pphSeharusnya').val(listdata.pphSeharusnya);
            $('#selisihPph').val(listdata.selisihPph);


        });
    }

    window.reloadDataModal = function(){
        var payrollId = document.getElementById("payrollId2").value;

        PayrollAction.reloadDetailEdit(payrollId, function(listdata){
            console.log(listdata);
            if(listdata.tipePegawai == "TP03" && listdata.strukturGaji != "G"){
                $('.detailGaji').show();
            }else{
                $('.detailGaji').hide();
            }

            $('#bulan').val(listdata.bulan);
            $('#tahun').val(listdata.tahun);
            $('#nip').val(listdata.nip);
            if(listdata.npwp != null){
                $('#npwp').val(listdata.npwp);
            }else{
                $('#npwp').val("-");
            }
            $('#branchId2').val(listdata.branchId);
            $('#nama').val(listdata.nama);
            $('#divisi').val(listdata.departmentName);
            $('#golongan').val(listdata.golonganName);
            $('#point').val(listdata.point);
            $('#jabatan').val(listdata.positionName);
            $('#statusKeluarga').val(listdata.statusKeluarga);
            $('#jumlahAnak').val(listdata.jumlahAnak);
            $('#gajiPensiun').val(listdata.gajiPensiun);
            $('#tipePegawai').val(listdata.tipePegawai);
            $('#tipePegawaiName').val(listdata.tipePegawaiName);

            $('#multifikator').val(listdata.multifikator);
            $('#gajiBpjs').val(listdata.gajiBpjs);

            //komponen A
            $('#gaji').val(listdata.gajiGolongan);
            $('#tunjUmk').val(listdata.tunjanganUmk);
            $('#tunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
            $('#tunjStruktural').val(listdata.tunjanganStruktural);
            $('#tunjStrategis').val(listdata.tunjanganStrategis);
            $('#tunjPeralihan').val(listdata.tunjanganPeralihan);
            $('#tunjTambahan').val(listdata.tunjanganTambahan);
            $('#tunjLain').val(listdata.tunjanganLain);
            $('#pemondokan').val(listdata.pemondokan);
            $('#komunikasi').val(listdata.komunikasi);

            //komponen B
            //RLAB
            $('#tunjRumah').val(listdata.tunjanganRumah);
            $('#tunjListrik').val(listdata.tunjanganListrik);
            $('#tunjAir').val(listdata.tunjanganAir);
            $('#tunjBbm').val(listdata.tunjanganBbm);
            $('#totalRlab').val(listdata.totalRlab);
            $('#tunjDapen').val(listdata.iuranDapenPersh);
            $('#tunjBpjsKs').val(listdata.tunjanganBpjsKs);
            $('#tunjBpjsTk').val(listdata.tunjanganBpjsTk);
            $('#tunjPph').val(listdata.tunjanganPph);

            //komponen C
            $('#iuranDapenPeg').val(listdata.iuranDapenPeg);
            $('#iuranDapenPersh').val(listdata.iuranDapenPersh);
            $('#iuranBpjsTkPeg').val(listdata.iuranBpjsTkKary);
            $('#iuranBpjsTkPers').val(listdata.iuranBpjsTkPers);
            $('#iuranBpjsKsPeg').val(listdata.iuranBpjsKsKary);
            $('#iuranBpjsKsPers').val(listdata.iuranBpjsKsPersh);
            $('#pphGaji1').val(listdata.pphGaji);
            $('#totalPotonganLain').val(listdata.totalPotonganLain);

            //detail komponen C potongan lain -lain
            $('#kopkar').val(listdata.kopkar);
            $('#iuranSp').val(listdata.iuranSp);
            $('#iuranPiiKb').val(listdata.iuranPiikb);
            $('#bankBri').val(listdata.bankBri);
            $('#bankMandiri').val(listdata.bankMandiri);
            $('#infaq').val(listdata.infaq);
            $('#perkesDanObat').val(listdata.perkesDanObat);
            $('#listrik').val(listdata.listrik);
            $('#iuranProfesi').val(listdata.iuranProfesi);
            $('#potonganLain').val(listdata.potonganLain);

            //Komponen D
            $('#nilaiPtt').val(listdata.lainLain);
            $('#tipePttId1').val(listdata.idLainLain).change();

            $('#nilaiModPeralihanGapok').val(listdata.stPeralihanGapok);
            $('#nilaiModPeralihanSankhus').val(listdata.stPeralihanSankhus);
            $('#nilaiModPeralihanTunjangan').val(listdata.stPeralihanTunjangan);

            //Total
            $('#totalA').val(listdata.totalA);
            $('#totalB').val(listdata.totalB);
            $('#totalC').val(listdata.totalC);
            $('#gajiBersih').val(listdata.totalGajiBersih);

            $('#totalA').val(listdata.totalA);

            //pph bulan 12
            $('#totalPtt').val(listdata.totalLain11Bulan);
            $('#pph11Bulan').val(listdata.pph11Bulan);
            $('#pphSeharusnya').val(listdata.pphSeharusnya);
            $('#selisihPph').val(listdata.selisihPph);
        });
    }

    window.disableReadOnly = function(){
        if($('#checkKalkulasiPph').is(":checked")){
            $("#pphGaji").prop('readonly', true);
            $("#tunjPph").prop('readonly', true);
            alert('Kalkulasi Pph Enabled');

            var url_string = window.location.href ;
            var url = new URL(url_string);
            var tipe = document.getElementById("tipe").value;

            if(tipe == "PR" || tipe == "R"){
                changeJubileumNPensiun();
            }else if(tipe == "JP"){
                kalkulasiPphJasprod();
            }
        }else{
            $("#pphGaji").prop('readonly', false);
            $("#tunjPph").prop('readonly', false);
            alert('Kalkulasi Pph Disabled');
        }
    }

    window.disableReadOnlyPphPengobatan = function(){
        if($('#checkKalkulasiPphPengobatan').is(":checked")){
            $("#pphPengobatanBayar").prop('readonly', true);
            alert('Kalkulasi Pph Pengobatan Aktif');

            var tipe = document.getElementById("tipe").value;
            if(tipe == "PR" || tipe == "R"){
                changeJubileumNPensiun();
            }else if(tipe == "JP"){
                kalkulasiPphJasprod();
            }
        }else{
            $("#pphPengobatanBayar").prop('readonly', false);
            alert('Kalkulasi Pph Tidak Aktif');
        }
    }

    window.changeJubileumNPensiun = function(){
        var payrollId = document.getElementById("payrollId").value;
        var tipePegawai = document.getElementById("tipePegawai").value;

        var tunjanganPeralihan = document.getElementById("tunjPeralihan").value;
        var kompensasi = document.getElementById("kompensasi").value;
        var transport = document.getElementById("transport").value;
        var uangMukaLain = document.getElementById("uangMukaLain").value;
        var kurangBpjs = document.getElementById("kekuranganIuranBpjs").value;
        var koperasi = document.getElementById("koperasi").value;
        var dansos = document.getElementById("dansos").value;
        var sp = document.getElementById("sp").value;
        var bazis = document.getElementById("bazis").value;
        var bapor = document.getElementById("bapor").value;
        var lainLain = document.getElementById("lainLain").value;
        var tunjLain = document.getElementById("tunjLain").value;
        var gaji = document.getElementById("gaji").value;
        var lembur = document.getElementById("fieldBiayaLembur").value;
        var pengobatan = document.getElementById("fieldBiayaPengobatan").value;
        var pphGaji = document.getElementById("pphGaji").value;
        var tunjPph = document.getElementById("tunjPph").value;
        var pphObat = document.getElementById("pphPengobatanBayar").value;

        var flagPensiun = "N";
        var flagJubileum = "N";
        var flagListrikAir = "N";
        var flagPerumahan = "N";
        var flagKalkulasiPph = "N";
        var flagKalkulasiPphObat = "N";

        if($('#checkApproveJubileum').is(":checked")){
            var hasil = cekJubileum(nip);
            if(hasil == ''){
                flagJubileum = "Y";
            }else{
                alert('Tunjangan Jubilium Sudah dibayarkan pada tanggal : ' + hasil);
            }
        }else{
            flagJubileum = "N";
        }

        if($('#checkApprovePensiun').is(":checked")){
            var hasil = cekPensiun(nip);
            if(hasil == ''){
                flagPensiun = "Y";
            }else{
                alert('Tunjangan Pensiun Sudah dibayarkan pada tanggal : ' + hasil);
            }
        }else{
            flagPensiun = "N";
        }

        if($('#checkKalkulasiPph').is(":checked")){
            flagKalkulasiPph = "Y";
        }else{
            flagKalkulasiPph = "N";
        }

        if($('#checkKalkulasiPphPengobatan').is(":checked")){
            flagKalkulasiPphObat = "Y";
        }else{
            flagKalkulasiPphObat = "N";
        }

        if($('#checkListrikAir').is(":checked")){
            flagListrikAir = "Y";
        }else{
            flagListrikAir = "N";
        }

        if($('#checkPerumahan').is(":checked")){
            flagPerumahan = "Y";
        }else{
            flagPerumahan = "N";
        }
        PayrollAction.saveEditSessionDataUsingPayrollId(payrollId, tunjanganPeralihan, kompensasi, transport, uangMukaLain, kurangBpjs, koperasi, dansos,
                sp, bazis, bapor, lainLain, tunjLain, flagJubileum, flagPensiun, tipePegawai, gaji, lembur, pengobatan, flagListrikAir,
                flagPerumahan, flagKalkulasiPph, pphGaji, tunjPph, pphObat, flagKalkulasiPphObat, function(listdata) {
                    alert('Data Berhasil Dirubah');
                    reloadDataModal();
                });
    }

    window.kalkulasiPphJasprod = function(){
        var payrollId = document.getElementById("payrollId").value;

        var koperasi = document.getElementById("koperasi").value;
        var dansos = document.getElementById("dansos").value;
        var lainLain = document.getElementById("lainLain").value;
        var pphGaji = document.getElementById("pphGaji").value;

        var flagKalkulasiPph = "N";

        if($('#checkKalkulasiPph').is(":checked")){
            flagKalkulasiPph = "Y";
        }else{
            flagKalkulasiPph = "N";
        }

        PayrollAction.saveEditSessionDataUsingPayrollJasprod(payrollId, koperasi, dansos, lainLain, flagKalkulasiPph, pphGaji, function(listdata) {
            reloadDataModal();
            alert('Data Berhasil Dirubah');
        });

    }

    $('.detailJubileumMasaKerja').on('click', function(){
        var nip = document.getElementById("nip").value;

        PayrollAction.searchJubileumMasaKerjaDetail(nip, function(listdata){
            $('#jubileumPkwt1').val(listdata.stTanggalPkwtAwal);
            $('#jubileumPkwt2').val(listdata.stTanggalPkwtAkhir);
            $('#jubileumJumlahPkwt').val(listdata.lamaPkwt);
            $('#jubileumTetap1').val(listdata.stTanggalPegawaiTetapAwal);
            $('#jubileumTetap2').val(listdata.stTanggalPegawaiTetapAkhir);
            $('#jubileumJumlahTetap').val(listdata.lamaPegawaiTetap);
            $('#jubileumTotalMasaKerja').val(listdata.masaKerja);
        });

        $('#modal-jubileum-masaKerja').find('.modal-title').text('Detail Jubileum');
        $('#modal-jubileum-masaKerja').modal('show');
    });

    $('#btnRefreshLembur').on('click', function(){
        var nip = document.getElementById("nip").value;
        var branchId = document.getElementById("branchId").value;
        var nama = document.getElementById("nama").value;
        var bulan = document.getElementById("bulanPayroll").value;
        var tahun = document.getElementById("tahunPayroll").value;

        if (confirm('Apakah Anda ingin mereload biaya lembur ' + nama + '?')) {
            PayrollAction.reloadBiayaLembur(nip, branchId, bulan, tahun, function(listdata) {
                alert('Reload biaya lembur ' + nama + ' telah berhasil ');
                $('#tunjLembur').val(listdata);
                $('#modal-lembur').modal('hide');
            });
        }
    });
    $('#btnViewPPhSeharusnya').on('click', function(){
        var nip = document.getElementById("nip").value;
        var tahun = document.getElementById("tahun").value;
        var totalA = document.getElementById("totalA").value;
        var totalRlab = document.getElementById("totalRlab").value;
        var tunjDapen = document.getElementById("tunjDapen").value;
        var tunjBpjsKs = document.getElementById("tunjBpjsKs").value;
        var tunjBpjsTk = document.getElementById("tunjBpjsTk").value;
        var iuranDapen = document.getElementById("iuranDapenPeg").value;
        var iuranBpjsKs = document.getElementById("iuranBpjsKsPeg").value;
        var iuranBpjsTk = document.getElementById("iuranBpjsTkPeg").value;
        var statusKeluarga = document.getElementById("statusKeluarga").value;
        var anak = document.getElementById("jumlahAnak").value;
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PayrollAction.searchDetailPPhSeharusnya(nip,tahun,totalA,totalRlab,tunjDapen,tunjBpjsKs,tunjBpjsTk,iuranDapen,iuranBpjsKs,iuranBpjsTk,statusKeluarga,anak, function(data) {
            $('#modTotPendBruto').val(data.stTotPendBruto);
            $('#modTotTunjPph').val(data.stTotTunjPPh);
            $('#modTotPendTidTer').val(data.stTotPendTidakTeratur);
            $('#modTotBrut').val(data.stTotBrut);
            $('#modTotIuran').val(data.stTotIuran);
            $('#modTotBijab').val(data.stTotBijab);
            $('#modTotReduce').val(data.stTotReduce);
            $('#modNetSetahun').val(data.stNetSetahun);
            $('#modPtkp').val(data.stPtkp);
            $('#modPkp').val(data.stPkp);
            $('#modPphSeh').val(data.stPphSeharusnya);
        });
        $('#modal-pph-seharusnya').find('.modal-title').text('Perhitungan PPH 21');
        $('#modal-pph-seharusnya').modal('show');
    });
    $('#btnViewTotalPtt11').on('click', function(){
        var nip = document.getElementById("nip").value;
        var tahun = document.getElementById("tahun").value;
        var jumlah = "" ;

        $('.tablePttSetahun').find('tbody').remove();
        $('.tablePttSetahun').find('thead').remove();
        $('.tablePttSetahun').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PayrollAction.searchTotalPtt11Bulan(nip, tahun, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Jenis PTT</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Nilai</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.bulan + '</td>' +
                    '<td align="center">' + item.tipePttName+ '</td>' +
                    '<td align="center">' + item.nilai+ '</td>' +
                    "</tr>";
                jumlah = item.stJumlahPtt;
            });
            tmp_table += '<tfoot >' +
                '<tr style="font-size: 12px;">' +
                '<td colspan = "2" style="text-align: center"> Jumlah PTT 11 Bulan</td>' +
                '<td style="text-align: center">'+jumlah+'</td>' +
                '</tr>' +
                '</tfoot>';
            $('.tablePttSetahun').append(tmp_table);
        });
        $('#modal-ptt-setahun').find('.modal-title').text('PTT 11 Bulan');
        $('#modal-ptt-setahun').modal('show');
    });
    $('#btnViewTotalPPh11').on('click', function(){
        var nip = document.getElementById("nip").value;
        var tahun = document.getElementById("tahun").value;
        var jumlah = "" ;

        $('.tablePph11Bulan').find('tbody').remove();
        $('.tablePph11Bulan').find('thead').remove();
        $('.tablePph11Bulan').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        PayrollAction.searchTotalPPh11Bulan(nip,tahun, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Tipe</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>PPH</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.bulan + '</td>' +
                    '<td align="center">' + item.jenisPayroll + '</td>' +
                    '<td align="center">' + item.stNilai+ '</td>' +
                    "</tr>";
                jumlah = item.stJumlahNilai;
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
    window.updateNilai = function(id, a){
        a = a.replace(/\,/g,''); // 1125, but a string, so convert it to number
        a = parseInt(a,10);
        var isnum = /^\d+$/.test(a);

        var nip = document.getElementById("nip").value;
        var payrollId = document.getElementById("payrollId2").value;
        var bulan = document.getElementById("bulan").value;
        var tahun = document.getElementById("tahun").value;

        //Komponen A
        var tunjanganPeralihan = document.getElementById("tunjPeralihan").value;
        var pemondokan = document.getElementById("pemondokan").value;
        var komunikasi = document.getElementById("komunikasi").value;

        // Komponen D
        var nilaiPtt = document.getElementById("nilaiPtt").value;
        var idPtt = document.getElementById("tipePttId1").value;


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

        //macam peralihan
        var peralihanGapok = document.getElementById("nilaiModPeralihanGapok").value;
        var peralihanSankhus = document.getElementById("nilaiModPeralihanSankhus").value;
        var peralihanTunjangan = document.getElementById("nilaiModPeralihanTunjangan").value;

        var flagPensiun = "N";
        var flagJubileum = "N";


        var url_string = window.location.href ;
        var url = new URL(url_string);
        var tipe = document.getElementById("tipe").value;

        if(isnum == true){
            //alert("1");
            /*PayrollAction.saveEditData(payrollId, tunjanganPeralihan, kompensasi, transport, uangMukaLain, kurangBpjs, koperasi, dansos,
                    sp, bazis, bapor, lainLain, tunjLain, flagJubileum, flagPensiun, tipePegawai, gaji, lembur, pengobatan, flagListrikAir, flagPerumahan, function(listdata) {
                        loadDataModal();
                    });*/
            if(tipe == "PR" || tipe == "R"){
                PayrollAction.saveEditSessionDataUsingPayrollId(payrollId, nip, tunjanganPeralihan,pemondokan,
                        komunikasi, kopkar, iuranSp, iuranPiikb,bankBri, bankMandiri, infaq, perkesDanObat,
                        listrik, iuranProfesi, potonganLain,
                        flagJubileum, flagPensiun, nilaiPtt,bulan,tahun,peralihanGapok,peralihanSankhus,peralihanTunjangan, function(listdata) {
                            reloadDataModal();
                        });
            }else if(tipe == "JP"){
                //alert("2");
                //alert(payrollId + " - " + koperasi + " - " + dansos + " - " + lainLain + " - " + flagKalkulasiPph + " - " + pphGaji + " - " + tunjPph);
                PayrollAction.saveEditSessionDataUsingPayrollJasprod(payrollId, koperasi, dansos, lainLain, flagKalkulasiPph, pphGaji, function(listdata) {
                            reloadDataModal();
                        });
            }
        }else{
            alert('Hanya dapat diisi angka 0 - 9');
            $('#'+id).val(0);
        }
    }


    window.updateDataPayroll = function(id, a){
        a = a.replace(/\,/g,''); // 1125, but a string, so convert it to number
        a = parseInt(a,10);
        var isnum = /^\d+$/.test(a);

        var payrollId = document.getElementById("payrollId2").value;
        var tipePegawai = document.getElementById("tipePegawai").value;

        var tunjPph = document.getElementById("tunjPph").value;
        var pphGaji = document.getElementById("pphGaji").value;




        if(isnum == true){
            /*PayrollAction.saveEditDataJan(payrollId, pengobatan, tunjPph, pphGaji, function(listdata) {
                        loadDataModal();
                    });*/
        }else{
            alert('Hanya dapat diisi angka 0 - 9');
            $('#'+id).val(0);
        }
    }
</script>