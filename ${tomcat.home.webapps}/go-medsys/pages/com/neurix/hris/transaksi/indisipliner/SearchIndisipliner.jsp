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
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IndisiplinerAction.js"/>'></script>

    <style>
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
            window.location.href="<s:url action='initForm_indisipliner'/>";
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
            Indisipliner
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="indisiplinerForm" method="post"  theme="simple" namespace="/indisipliner" action="search_indisipliner.action" cssClass="form-horizontal">

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
                                                    <label class="control-label"><small>Indisipliner ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="indisiplinerId" name="indisipliner.indisiplinerId" required="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchId" name="indisipliner.branchId" required="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip" name="indisipliner.nip" required="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <script type='text/javascript'>
                                                var functions, mapped;
                                                $('#nip').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        var unit = $('#branchId').val();
                                                        if (unit==""){
                                                            alert("unit is empty");
                                                            $('#nama').val("");
                                                            $('#nip').val("");
                                                        }else {
                                                            dwr.engine.setAsync(false);
                                                            IndisiplinerAction.initComboPersonil(query, unit, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.nip+" "+item.namaPegawai;
                                                                mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                                functions.push(labelItem);
                                                            });
                                                            process(functions);
                                                        }
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaMember = selectedObj.label;
                                                        document.getElementById("nama").value = selectedObj.nama;
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nama" name="indisipliner.nama" required="true" readonly="true" cssClass="form-control"/>
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
                                                                  listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="indisipliner.bagianId" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'SP0':'Teguran','SP1':'SP1','SP2':'SP2','SP3':'SP3'}" id="tipeIndisipliner" name="indisipliner.tipeIndisipliner"
                                                                  headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal Cuti :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <div class="input-group date">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampFrom" name="indisipliner.stTanggalDari" cssClass="form-control pull-right"
                                                                         required="false" size="7"  cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" name="indisipliner.stTanggalSelesai" cssClass="form-control pull-right"
                                                                         required="false" size="7"  cssStyle=""/>
                                                        </div>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="indisiplinerForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/indisipliner" action="add_indisipliner" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Indisipliner
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-warning" id="btnPrintRekapIndisipliner">
                                                            <i class="fa fa-print"></i> Print Rekap Indisipliner
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_indisipliner"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"
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
                                                                   height="700" width="700" autoOpen="false"
                                                                   title="Indisipliner">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_indisipliner" openTopics="showDialogMenuView" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Indisipliner">
                                                        </sj:dialog>

                                                        <s:set name="listOfIndisipliner" value="#session.listOfResultIndisipliner" scope="request" />
                                                        <display:table name="listOfIndisipliner" class="tableIndisipliner table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_indisipliner.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="Print" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.closed">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.indisiplinerApprove">
                                                                    <s:url var="urlCetakSuratIndisipliner" namespace="/indisipliner" action="cetakSurat_indisipliner" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.indisiplinerId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlCetakSuratIndisipliner}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Closed" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.closed">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.indisiplinerApprove">
                                                                    <a href="javascript:;"  data="<s:property value="%{#attr.row.indisiplinerId}"/>" nama="<s:property value="%{#attr.row.nama}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Detail" style="text-align:center;font-size:9">
                                                                <s:url var="urlView" namespace="/indisipliner" action="view_indisipliner" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.indisiplinerId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlView}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                                </sj:a>
                                                            </display:column>
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="nama" sortable="true" title="Nama"  />
                                                            <display:column property="branchName" sortable="true" title="Unit"/>
                                                            <display:column property="bagianName" sortable="true" title="Bagian"/>
                                                            <display:column property="stTanggal" sortable="true" title="Tanggal Pelanggaran"/>
                                                            <display:column property="tipeIndisipliner" sortable="true" title="Tipe"/>
                                                            <display:column property="keteranganPelanggaran" sortable="true" title="Note"/>
                                                            <s:if test="#attr.row.cekatasan">
                                                            </s:if>
                                                            <s:elseif test="#attr.row.notApprove">
                                                                <display:column media="html" title="Approve Atasan">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                </display:column>
                                                            </s:elseif>
                                                            <s:else>
                                                                <display:column media="html" title="Approve Atasan">
                                                                    <s:if test="#attr.row.indisiplinerApprove">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                </display:column>
                                                            </s:else>
                                                                        <display:setProperty name="paging.banner.item_name">Indisipliner</display:setProperty>
                                                                        <display:setProperty name="paging.banner.items_name">Indisipliner</display:setProperty>
                                                                        <display:setProperty name="export.excel.filename">Indisipliner.xls</display:setProperty>
                                                                        <display:setProperty name="export.csv.filename">Indisipliner.csv</display:setProperty>
                                                                        <display:setProperty name="export.pdf.filename">Indisipliner.pdf</display:setProperty>
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Closed Indisipliner</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Indisipliner ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="IndisiplinerId1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="Nip1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="Nama321" name="nip">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="Unit321" name="indisipliner.branchId" required="true"
                                      listKey="branchId" listValue="branchName" readonly="true" headerKey="" disabled="true" headerValue="[Select one]" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bagian :</label>
                        <div class="col-sm-8">
                            <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>
                            <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="Bagian1" required="true"
                                      listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="indisipliner.bagianId" disabled="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="Posisi1" name="indisipliner.posisiId" required="false" readonly="true"
                                      listKey="stPositionId" disabled="true" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tipe Indisipliner : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'SP0':'Teguran','SP1':'SP1','SP2':'SP2','SP3':'SP3'}" id="tipeIndisipliner1" name="indisipliner.tipeIndisipliner" readonly="true"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Masa Berlaku : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAwalPantau1" name="nip">
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAkhirPantau1" name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Blokir Absensi : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAwalBlokir1" name="nip">
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAkhirBlokir1" name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan Pelanggaran : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="keteranganPelanggaran1" readonly name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dampak : </label>
                        <div class="col-sm-8">
                            <textarea id="dampak1" readonly rows="4" class="form-control nip" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan Closed : </label>
                        <div class="col-sm-8">
                            <textarea id="keteranganClosed1" rows="4" class="form-control" ></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i>Closed</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('#btnApprove').click(function(){
            var who = $('#myForm').attr('action');
            var id = document.getElementById("IndisiplinerId1").value;
            var nipid=document.getElementById("Nip1").value;
            var keteranganClosed=document.getElementById("keteranganClosed1").value;
            if (keteranganClosed!=""){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    IndisiplinerAction.saveClosed(id, "Y",nipid,keteranganClosed, function(listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        location.reload();
                    });
                }
            }else{
                alert("mohon isi keterangan")
            }
        });
        $('.tableIndisipliner').on('click', '.item-edit', function() {
            var IndisiplinerId = $(this).val().replace(/\n|\r/g, "");
            var IndisiplinerId = $(this).attr('data');
            var nip = $(this).attr('nip');
            $('#IndisiplinerId1').val(IndisiplinerId);
            //alert(IndisiplinerId);
            IndisiplinerAction.approveAtasan(IndisiplinerId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalAwalPantau);
                    var myDate1 = new Date(item.tanggalAkhirPantau);
                    var myDate2 = new Date(item.tanggalAwalBlokirAbsensi);
                    var myDate3 = new Date(item.tanggalAkhirBlokirAbsensi);
                    $('#Nama321').val(item.nama);
                    $('#Nip1').val(item.nip);
                    // $('#tglAwalPantau1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    // $('#tglAkhirPantau1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());
                    // $('#tglAwalBlokir1').val((myDate2.getDate()) +' - '+ ("0" + (myDate2.getMonth() + 1)).slice(-2) +' - '+myDate2.getFullYear());
                    // $('#tglAkhirBlokir1').val((myDate3.getDate()) +' - '+ ("0" + (myDate3.getMonth() + 1)).slice(-2) +' - '+myDate3.getFullYear());
                    $('#tglAwalPantau1').val(item.stTanggalAwalPantau);
                    $('#tglAkhirPantau1').val(item.stTanggalAkhirPantau);
                    $('#tglAwalBlokir1').val(item.stTanggalAwalBlokirAbsensi);
                    $('#tglAkhirBlokir1').val(item.stTanggalAkhirBlokirAbsensi);
                    // $('#tanggal1').val(item.stTanggal);
                    $('#Unit321').val(item.branchId).change();
                    $('#Bagian1').val(item.bagianId).change();
                    $('#Posisi1').val(item.positionId).change();
                    $('#tipeIndisipliner1').val(item.tipeIndisipliner).change();
                    $('#keteranganPelanggaran1').val(item.keteranganPelanggaran);
                    $('#dampak1').val(item.dampak);
                });
            });
            $('#modal-edit').find('.modal-title').text('Closed Indisipliner');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'closed');
        });
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#btnPrintRekapIndisipliner').click(function(){
            var tglFrom = document.getElementById("loginTimestampFrom").value;
            var tglTo = document.getElementById("loginTimestampTo").value;
            var branchId = document.getElementById("branchId").value;
            var bagian = document.getElementById("bagian").value;
            var nip = document.getElementById("nip").value;
            if (tglFrom!=""&&tglTo!=""&&branchId!=""){
                var msg='Apakah Anda ingin mencetak rekapitulasi indisipliner tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (confirm(msg)) {
                    window.location.href = "printReportRekapitulasiIndisipliner_indisipliner.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&branchId="+branchId+"&bagian="+bagian+"&nip="+nip;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
    })
</script>