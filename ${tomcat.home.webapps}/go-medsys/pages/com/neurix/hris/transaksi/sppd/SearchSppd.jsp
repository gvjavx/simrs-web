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
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_alat'/>";
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
            Sppd
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/sppd" action="search_sppd.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Sppd Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="sppdId" name="sppd.sppdId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Dinas :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'LN':'Luar Negeri'}" name="sppd.dinas"
                                                  headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="sppd.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="sppd.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>No Doc SPPD :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="sppdName" name="sppd.sppdName" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield name="sppd.personName" id="sppdNama" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none" id="personNip" name="sppd.personNip" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#sppdNama').typeahead({
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
                                            document.getElementById("personNip").value = selectedObj.id;

                                            return namaAlat;
                                        }
                                    });

                                </script>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="sppd.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tanggal berangkat :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalBerangkat" name="sppd.stTanggalSppdBerangkat" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tanggal Kembali :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalKembali" name="sppd.stTanggalSppdKembali" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="sppdForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <a href="add_sppd.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add SPPD</a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_sppd"/>'">
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
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="800" width="1000" autoOpen="false"
                                                   title="Sppd ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfSppd" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfSppd" class=" tableSppd table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_sppd.action" export="true" id="row" pagesize="14" style="font-size:10">

                                            <display:column media="html" title="Print">
                                                <s:if test="#attr.row.sppdClosed">
                                                    <s:url var="urlEdit" namespace="/sppd" action="printReportSppd_sppd" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.sppdId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        <s:param name="dinas"><s:property value="#attr.row.dinas"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" >
                                                    </s:a>
                                                </s:if>
                                            </display:column>
                                            <display:column media="html" title="Print Surat">
                                                <s:if test="#attr.row.sppdEditStatus">
                                                    <s:url var="urlEdit" namespace="/sppd" action="printViewReportSppd_sppd" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.sppdId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" >
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Closed">
                                                <s:if test="#attr.row.sppdClosed">
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" >
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" style="%{attr.row.sppdId}" title="Approve SDM" >
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.sppdId}"/>" href="javascript:;" class="item-approve" cssClass="item-edit">
                                                    <s:if test="#attr.row.sppdEditStatus">
                                                        <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </s:else>
                                                </a>
                                            </display:column>

                                            <display:column media="html" style="%{attr.row.sppdId}" title="Approve Atasan" >
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.sppdId}"/>" href="javascript:;" class="item-approveAtasan">
                                                    <s:if test="#attr.row.sppdEditStatusAtasan">
                                                        <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </s:else>
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Detail Person" >
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.sppdId}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_edit">
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.sppdEditStatus">
                                                    <s:a action="edit_sppd.action">
                                                        <s:param name="id"><s:property value="#attr.row.sppdId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <s:param name="closed"><s:property value="#attr.row.closed" /></s:param>
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete">
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.sppdId}"/>" href="javascript:;"
                                                   class="item-delete" closed="<s:property value="%{#attr.row.closed}"/>">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>">
                                                </a>
                                            </display:column>

                                            <display:column property="sppdId" sortable="true" title="Sppd ID"  />
                                            <display:column property="dinas" sortable="true" title="Dinas" />
                                            <display:column property="noSurat" sortable="true" title="No Surat" />
                                            <display:column property="personName" sortable="true" title="Nama Ketua" />
                                            <%--<display:column property="divisiName" sortable="true" title="Divisi" />
                                            <display:column property="personNip" sortable="true" title="NIP" />
                                            <display:column property="personName" sortable="true" title="Nama" />
                                            <display:column property="tanggalSppdBerangkat" sortable="true" title="Tanggal SPPD"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>--%>
                                            <display:setProperty name="paging.banner.item_name">Sppd</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">Sppd</display:setProperty>
                                            <display:setProperty name="export.excel.filename">Sppd.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">Sppd.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">Sppd.pdf</display:setProperty>

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
    <div id="modal-edit" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:1000px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Approve SPPD</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="myForm">

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Dinas : </label>
                            <div class="col-sm-3">
                                <input readonly type="text" class="form-control" style="display: none;" id="sppdIdForDetail">
                                <input readonly type="text" class="form-control" style="display: none;" id="tanggalBerangkat1">
                                <input readonly type="text" class="form-control" style="display: none;" id="tanggalKembali1">
                                <s:select disabled="true" readonly="true" list="#{'LN':'Luar Negeri'}" id="dinas1" name="sppd.dinas"
                                          headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />

                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-3" >Berangkat Dari: </label>
                            <div class="col-sm-3">
                                <input readonly type="text" class="form-control nip" id="berangkatDari1" name="nip">
                                <input style="display: none" readonly type="text" class="form-control" id="berangkatDariId">
                            </div>
                            <label class="control-label col-sm-2" >Tujuan : </label>
                            <div class="col-sm-3">
                                <input readonly type="text" class="form-control nip" id="tujuan1" name="nip">
                                <input style="display: none" readonly type="text" class="form-control nip" id="tujuanId" name="nip">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-3" >Keperluan : </label>
                            <div class="col-sm-8">
                                <textarea rows="3" readonly class="form-control" id="keperluan1" name="nip"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Closed : </label>
                            <div class="col-sm-3">
                                <input readonly type="text" class="form-control nip" id="sppdClosed" name="sppdClosed">
                            </div>
                        </div>
                    </form>

                    <div class="table-responsive text-nowrap" style="white-space: nowrap">
                        <table id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-success btnAnggota">Add Anggota</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-anggota" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nip" name="nip">
                            <input style="display: none" type="text" class="form-control nip" id="nipNama" name="nip">
                            <input style="display: none" type="text" class="form-control" id="nip1" name="nip1">
                            <input style="display: none" type="text" class="form-control" id="nipOld" name="nip1">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            // var prov = document.getElementById("provinsi1").value;
                            $('#nip').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);
                                    //UserAction.initComboUser(query, branc, dev, function (listdata) {
                                    /*BiodataAction.initComboUser(query, function (listdata) {
                                     data = listdata;
                                     //alert('aa');
                                     });*/

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
                                    document.getElementById("nip1").value = selectedObj.id;
                                    document.getElementById("nipNama").value = selectedObj.pegawai;

                                    $('#positionId1').val(selectedObj.positionId).change();
                                    $('#branchId1').val(selectedObj.branchId).change();
                                    $('#divisiId1').val(selectedObj.divisiId).change();
                                    return namaAlat;
                                }
                            });

                        </script>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Unit :</label>
                        <div class="col-sm-6">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="sppd.branchId" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Jabatan :</label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="sppdPerson.positionId" disabled="true"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Divisi :</label>
                        <div class="col-sm-6">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="sppd.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Berangkat:</label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" id="tanggalBerangkatAnggota" class="form-control pull-right"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Kembali : </label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tanggalKembaliAnggota"  cssClass="tanggalKembaliAnggota form-control pull-right"
                                             required="false"  cssStyle=""/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Dari : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="kotaDari" name="nip" readonly>
                            <input type="text" class="form-control" style="display: none" id="kotaDari1" name="nip" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Kota Tujuan : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="kotaTujuan" name="nip" readonly>
                            <input type="text" class="form-control" style="display: none" id="kotaTujuan1" name="nip" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Berangkat Via : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'Kereta Api':'Kereta Api' , 'Bis' : 'Bis', 'Pesawat' : 'Pesawat', 'Mobil' : 'Mobil'}" cssClass="form-control" id="berangkatViaAnggota" name="sppdPerson.berangkatVia"
                                      headerKey="" headerValue="[Pilih Transportasi]" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Pulang Via : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'Kereta Api':'Kereta Api' , 'Bis' : 'Bis', 'Pesawat' : 'Pesawat', 'Mobil' : 'Mobil'}" cssClass="form-control" id="pulangViaAnggota" name="sppdPerson.berangkatVia"
                                      headerKey="" headerValue="[Pilih Transportasi]" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Penginapan : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'Hotel':'Hotel' , 'Mess' : 'Mess'}" cssClass="form-control" id="penginapanAnggota" name="sppdPerson.kembaliVia"
                                      headerKey="" headerValue="" />
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveAnggota" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

    <div id="modal-approveAtasan" class="modal fade" role="dialog">
        <div class="modal-dialog" style="width:1000px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Approve SPPD</h4>
                </div>

                <div class="modal-body">
                    <form class="form-horizontal" id="formApproveAtasan">
                        <div class="form-group" style="display: none">
                            <label class="control-label col-sm-2" >Sppd Id : </label>
                            <div class="col-sm-4">
                                <input readonly type="text" class="form-control nip" id="sppdIdApproveAtasan" name="nip">
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <label class="control-label col-sm-2" >Note For Not Approve : </label>
                            <div class="col-sm-4">
                                <textarea rows="4" class="form-control" id="notApproveAtasan"></textarea>
                            </div>
                        </div>--%>
                    </form>
                    <div class="table-responsive text-nowrap" style="white-space: nowrap">
                        <table style="width: 100%;" id="sppdPersonTableApproveAtasan" class="sppdPersonTableApproveAtasan table table-bordered"></table>
                    </div>
                </div>

                <div class="modal-footer">
                    <a id="btnApproveAtasan" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                    <%--<a id="btnNotApproveAtasan" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>--%>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-approve" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:1000px;">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Approve SPPD</h4>
                </div>

                <div class="modal-body">
                    <form class="form-horizontal" id="formApprove">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Sppd Id : </label>
                            <div class="col-sm-4">
                                <input readonly type="text" class="form-control nip" id="sppdIdApprove" name="nip">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Note For Not Approve : </label>
                            <div class="col-sm-4">
                                <textarea rows="4" class="form-control" id="notApprove"></textarea>
                            </div>
                        </div>
                    </form>
                    <div class="table-responsive text-nowrap" style="white-space: nowrap">
                        <table style="width: 100%;" id="sppdPersonTableApprove" class="sppdPersonTableApprove table table-bordered"></table>
                    </div>

                </div>

                <div class="modal-footer">
                    <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                    <a id="btnNotApprove" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

</html>

<script>
    $(document).ready(function() {
        $('.sppdPersonTable').on('click', '.deleteAnggota', function(){
            /*String sppdId = ;
            String nip = "";*/
            var sppdId = $(this).attr('sppdId');
            var nip = $(this).attr('nip');
            var tipePerson = $(this).attr('tipePerson');

            if(tipePerson != "Ketua"){
                if (confirm('Do you want to save this record?')) {
                    SppdAction.deleteAnggota(sppdId, nip, function(listdata) {
                        alert('Anggota berhasil dihapus');
                        $('#modal-edit').modal('hide');
                        location.reload();
                    });
                } else {
                }
            }else{
                alert("Maaf, Ketua tidak bisa dihapus");
            }
        });

        $('#btnApprove').click(function(){
            var values = new Array();
            $.each($("input[name='checkApprove[]']:checked"), function() {
                values.push($(this).val());
            });

            var who = "Sdm";
            var note = document.getElementById("notApprove").value;
            var sppdId = document.getElementById("sppdIdApprove").value;

            if(values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    $.each($("input[name='checkApprove[]']:checked"), function() {
                        var berangkat = document.getElementById("berangkat"+$(this).val()).value;
                        var kembali = document.getElementById("kembali"+$(this).val()).value;
                        var noteSdm = document.getElementById("note"+$(this).val()).value;
                        var penginapan = document.getElementById("penginapan"+$(this).val()).value;
                        //alert($(this).val() + " - " + noteSdm);
                        SppdAction.saveApprove(sppdId, $(this).val(), "Y", who, "", noteSdm, berangkat, kembali, penginapan, function(listdata) {
                            $('#modal-approve').modal('hide');
                            $('#formApprove')[0].reset();
                            location.reload();
                        });
                    });
                    alert('Data Successfully Updated');
                }
            }else{
                alert('Silahkan Centang Person yang akan di setujui(Approve) !');
            }
        });

        $('#btnApproveAtasan').click(function(){
            var values = new Array();
            $.each($("input[name='checkApproveAtasan[]']:checked"), function() {
                values.push($(this).val());
            });

            var who = "atasan";
            //var note = document.getElementById("notApproveAtasan").value;
            var sppdId = document.getElementById("sppdIdApproveAtasan").value;

            if(values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    $.each($("input[name='checkApproveAtasan[]']:checked"), function() {
                        var berangkat = document.getElementById("berangkat"+$(this).val()).value;
                        var kembali = document.getElementById("kembali"+$(this).val()).value;
                        var noteSdm = document.getElementById("note"+$(this).val()).value;
                        var penginapan = document.getElementById("penginapan"+$(this).val()).value;
                        //alert($(this).val() + " - " + noteSdm);
                        SppdAction.saveApprove(sppdId, $(this).val(), "Y", who, "", noteSdm, berangkat, kembali, penginapan, function(listdata) {
                            $('#modal-approve').modal('hide');
                            $('#formApprove')[0].reset();
                            location.reload();
                        });
                    });
                    alert('Data Successfully Updated');
                }
            }else{
                alert('Silahkan Centang Person yang akan di setujui(Approve) !');
            }
        });

        $('#btnNotApprove').click(function(){
            var values = new Array();
            $.each($("input[name='checkApprove[]']:checked"), function() {
                values.push($(this).val());
            });

            var who = "Sdm";
            var note = document.getElementById("notApprove").value;
            var sppdId = document.getElementById("sppdIdApprove").value;
            if(note != '' && values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    $.each($("input[name='checkApprove[]']:checked"), function() {
                        SppdAction.saveApprove(sppdId, $(this).val(), note, who, function(listdata) {
                            $('#modal-approve').modal('hide');
                            $('#formApprove')[0].reset();
                            location.reload();
                        });
                    });
                    alert('Data Successfully Updated');
                }
            }else{
                if(note == ''){
                    alert('Silahkan isi keterangan Not Approve !');
                }else{
                    alert('Silahkan Centang Person yang akan di reject(Not Approve) !');
                }
            }
        });

        $('#tanggalBerangkat').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKembali').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('.tableSppd').on('click', '.item-delete', function(){
            var sppdId = $(this).attr('data');
            var closed = $(this).attr('closed');

            if(closed == "N"){
                if (confirm('Apakah anda ingin menghapus SPPD?')) {
                    SppdAction.deleteSppd(sppdId, function(listdata) {
                        location.reload();
                        alert('SPPD berhasil dihapus');
                    });

                }

            }else{
                alert("Hanya SPPD yang belum terclose yang bisa dihapus");
            }
        });

        $('.tableSppd').on('click', '.item-edit', function(){
            var sppdId = $(this).val().replace(/\n|\r/g, "");
            var sppdId = $(this).attr('data');
            $('#sppdId1').val(sppdId);
            //alert(sppdId);
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#tanggalBerangkat1').val(item.stTanggalSppdBerangkat);
                    $('#tanggalKembali1').val(item.stTanggalSppdKembali);

                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchId).change();
                    $('#positionId1').val(item.positionId).change();
                    $('#dinas1').val(item.dinas).change();
                    $('#divisiId1').val(item.divisiId).change();
                    $('#sppdIdForDetail').val(item.sppdId);
                    $('#keperluan1').val(item.tugasSppd);
                    $('#sppdClosed').val(item.closed);
                    $('#berangkatDari1').val(item.berangkatDari);
                    $('#berangkatDariId').val(item.berangkatDariId);
                    $('#tujuan1').val(item.tujuanKe);
                    $('#tujuanId').val(item.tujuanKeId);
                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.pulangVia);
                    $('#notApprove').val(item.notApprovalNote);
                    $('#approveAtasan').val(item.approvalName);
                    $('#approveAtasanId').val(item.approvalId);
                    if(item.sppdApproveStatus == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                        $("#sppdPersonTable").find("input,button,textarea,select").attr("disabled", "disabled");

                        loadPerson(sppdId, 'Y');
                    }else{
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                        //location.reload();
                        $("#myForm :input").attr("disabled", false);
                        loadPerson(sppdId, 'N');
                    }


                });
            });
            //alert( $('#branchId1').text);
            $('#modal-edit').find('.modal-title').text('Detail SPPD Person');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'atasan');
        });

        $('.tableSppd').on('click', '.item-approve', function(){

            var sppdId = $(this).attr('data');
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    if(item.sppdEditStatus == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                    }else{
                        // mengecek status kelompok position untuk mendisable approve sdm, hanya sdm dan admin yang bisa approve
                        SppdAction.cekKelompokPosition(function(listdata) {
                            if(listdata == 'ADMIN'){
                                $('#btnNotApprove').show();
                                $('#btnApprove').show();
                            }else{
                                $('#btnNotApprove').hide();
                                $('#btnApprove').hide();
                            }
                        });
                        /*$('#btnNotApprove').show();
                        $('#btnApprove').show();*/
                    }
                });
            });

            $('#sppdIdApprove').val(sppdId);
            loadPersonApprove(sppdId, 'Y');
            $('#modal-approve').find('.modal-title').text('Approve SPPD');
            $('#modal-approve').modal('show');
        });

        $('.tableSppd').on('click', '.item-approveAtasan', function(){

            var sppdId = $(this).attr('data');
            SppdAction.cekApproveAtasan(sppdId, function(listdata) {
                if(listdata == true){
                    $('#btnNotApproveAtasan').hide();
                    $('#btnApproveAtasan').hide();
                }else{
                    // mengecek status kelompok position untuk mendisable approve sdm, hanya sdm dan admin yang bisa approve
                    SppdAction.cekKelompokPosition(function(listdata) {
                        if(listdata == 'ADMIN'){
                            $('#btnNotApproveAtasan').show();
                            $('#btnApproveAtasan').show();
                        }else{
                            $('#btnNotApproveAtasan').hide();
                            $('#btnApproveAtasan').hide();
                        }
                    });
                }
            });

            $('#sppdIdApproveAtasan').val(sppdId);
            loadPersonApproveAtasan(sppdId, 'Y');
            $('#modal-approveAtasan').find('.modal-title').text('Approve atasan SPPD');
            $('#modal-approveAtasan').modal('show');
        });

        $('.btnAnggota').on('click', function(){
            var tujuanId = document.getElementById("tujuanId").value;
            var tujuan1        = document.getElementById("tujuan1").value;
            var berangkatDariId   = document.getElementById("berangkatDariId").value;
            var berangkatDari = document.getElementById("berangkatDari1").value;
            var tanggalBerangkat = document.getElementById("tanggalBerangkat1").value;
            var tanggalKembali = document.getElementById("tanggalKembali1").value;

            $('#kotaTujuan').val(tujuan1);
            $('#kotaTujuan1').val(tujuanId);
            $('#kotaDari').val(berangkatDari);
            $('#kotaDari1').val(berangkatDariId);
            $('#tanggalBerangkatAnggota').val(tanggalBerangkat);
            $('#tanggalKembaliAnggota').val(tanggalKembali);

            $('#modal-anggota').find('.modal-title').text('Add Anggota');
            $('#modal-anggota').modal('show');
        });

        $('.tableSppd').on('click', '.item-editSdm', function(){
            var sppdId = $(this).val().replace(/\n|\r/g, "");
            var sppdId = $(this).attr('data');
            $('#sppdId1').val(sppdId);
            //alert(sppdId);
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#tanggalBerangkat1').val(item.stTanggalSppdBerangkat);
                    $('#tanggalKembali1').val(item.stTanggalSppdKembali);

                    $('#dinas1').val(item.dinas).change();
                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchId).change();
                    $('#positionId1').val(item.positionId).change();
                    $('#divisiId1').val(item.divisiId).change();
                    $('#keperluan1').val(item.tugasSppd);
                    $('#sppdClosed').val(item.closed);
                    $('#berangkatDari1').val(item.berangkatDari);
                    $('#tujuan1').val(item.tujuanKe);
                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.pulangVia);
                    $('#notApprove').val(item.notApprovalSdmNote);
                    $('#approveAtasan').val(item.approvalName);
                    $('#approveAtasanId').val(item.approvalId);
                    if(item.sppdApproveSdm == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                        loadPerson(sppdId, "Y");
                    }else{
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                        loadPerson(sppdId, "N");
                    }
                });
            });
            //alert( $('#branchId1').text);
            $('#modal-edit').find('.modal-title').text('Approve SPPD');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'Sdm');
        });
    });

    window.loadPerson =  function(id, status){
        //alert(nip);
        var branch  = $('select[name=branchText] option:selected').text();
        var divisi  = $('select[name=divisiText] option:selected').text();
        var closed  = document.getElementById("sppdClosed").value;
        //alert(branch);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {

            tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Hapus</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>T. Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>T. Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Kembali Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note Transport</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note Transport SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Penginapan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Not Approve</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM Nama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Not Approve SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama Pengganti</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';
                var hapus = '';

                if(item.sppdApproveSdm == true){
                    sdm = item.linkSdm ;
                }
                if(item.sppdApprove == true){
                    atasan = item.linkAtasan ;
                }
                if(closed == "N"){
                    hapus = "<a href='javascript:;' class ='deleteAnggota' tipePerson='"+item.tipePerson+"' sppdId='"+item.sppdId+"' nip='"+item.personId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                            "</a>" ;
                    $('.btnAnggota').show();
                }else{
                    $('.btnAnggota').hide();
                }
                var myDate = new Date(item.tanggalBerangkat);
                var myDate1 = new Date(item.tanggalKembali);
                tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                        '<td align="center">' +
                            hapus +
                        '</td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName  + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                        '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                        '<td align="center">' + item.berangkatVia + '</td>' +
                        '<td align="center">' + item.kembaliVia+ '</td>' +
                        '<td align="center">' + item.noteAtasanTransport+ '</td>' +
                        '<td align="center">' + item.noteSdmTransport+ '</td>' +
                        '<td align="center">' + item.penginapan+ '</td>' +
                        '<td align="center">' + item.approvalName + '</td>' +
                        '<td align="center"><img src="' + atasan + '"</td>' +
                        '<td align="center">' + item.notApprovalNote + '</td>' +
                        '<td align="center">' + item.approvalSdmName + '</td>' +
                        '<td align="center"><img src="' + sdm + '"</td>' +
                        '<td align="center">' + item.notApprovalSdmNote + '</td>' +
                        '<td align="center">' + item.pejabatSementaraNama + '</td>' +

                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);

        });
    }

    window.loadPersonApprove = function(id, status){
        //alert(nip);
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();
        //alert(branch);
        $('.sppdPersonTableApprove').find('tbody').remove();
        $('.sppdPersonTableApprove').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {
            tmp_table = "<thead valign='middle' style='font-size: 12px; color: white; white-space: nowrap' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Check</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Kembali Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note Transport</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note Transport SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Penginapan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Not Approve</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM Nama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Not Approve SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama Pengganti</th>"+
                    "</tr></thead>";

            var i = i ;
            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';
                var combo = '';
                var berangkat = '';
                var kembali = '';
                var noteSdm = '';
                var penginapan = '';

                if(item.sppdApproveSdm == true){
                    sdm = item.linkSdm ;
                    combo = '<input disabled type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.personId+'" class="checkApprove" style="display: none" >';
                }else{
                    if(item.sppdApprove == true && item.sppdApproveStatus == true ){
                        combo = '<input type="checkbox" id="checkApprove" name="checkApprove[]" value="'+item.personId+'" class="checkApprove" >';
                    }else{
                        combo = '<input type="checkbox" disabled id="checkApprove" name="checkApprove[]" value="'+item.personId+'" class="checkApprove" >';
                    }
                }

                noteSdm = '<input type="text" id="note'+item.personId+'" value="'+item.noteSdmTransport+'">'

                berangkat = '<select class="" id="berangkat'+item.personId+'" >' +
                        '<option value="Pesawat">Pesawat</option>' +
                        '<option value="Kereta Api">Kereta Api</option>' +
                        '<option value="Bis">Bis</option> ' +
                        '<option selected value="'+item.berangkatVia+'">'+item.berangkatVia+'</option> ' +
                        '</select>';

                kembali = '<select class="" id="kembali'+item.personId+'" >' +
                        '<option value="Pesawat">Pesawat</option>' +
                        '<option value="Kereta Api">Kereta Api</option>' +
                        '<option value="Bis">Bis</option> ' +
                        '<option selected value="'+item.kembaliVia+'">'+item.kembaliVia+'</option> ' +
                        '</select>';

                penginapan = '<select class="" id="penginapan'+item.personId+'" >' +
                        '<option value="Hotel">Hotel</option>' +
                        '<option value="Mess">Mess</option>' +
                        '<option selected value="'+item.penginapan+'">'+item.penginapan+'</option> ' +
                        '</select>';

                if(item.sppdApprove == true){
                    atasan = item.linkAtasan ;
                }
                var myDate = new Date(item.tanggalBerangkat);
                var myDate1 = new Date(item.tanggalKembali);

                var divisiName = "-";
                if(item.divisiName != null){
                    divisiName = item.divisiName ;
                }

                tmp_table += '<tr style="font-size: 11px; white-space: nowrap" ">' +
                        '<td align="center">'+combo+' </td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + divisiName + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                        '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                        '<td align="center">' + berangkat + '</td>' +
                        '<td align="center">' + kembali+ '</td>' +
                        '<td align="center">' + item.noteAtasanTransport+ '</td>' +
                        '<td align="center">' + noteSdm+ '</td>' +
                        '<td align="center">' + penginapan+ '</td>' +
                        '<td align="center">' + item.approvalName + '</td>' +
                        '<td align="center"><img src="' + atasan + '"</td>' +
                        '<td align="center">' + item.notApprovalNote + '</td>' +
                        '<td align="center">' + item.approvalSdmName + '</td>' +
                        '<td align="center"><img src="' + sdm + '"</td>' +
                        '<td align="center">' + item.notApprovalSdmNote + '</td>' +
                        '<td align="center">' + item.pejabatSementaraNama + '</td>' +
                        "</tr>";
                //$("#berangkat"+item.personId).val("Bis").change();
            });
            $('.sppdPersonTableApprove').append(tmp_table);

        });
    }

    window.loadPersonApproveAtasan = function(id, status){
        $('.sppdPersonTableApproveAtasan').find('tbody').remove();
        $('.sppdPersonTableApproveAtasan').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {
            tmp_table = "<thead valign='middle' style='font-size: 12px; color: white; white-space: nowrap' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Check</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Kembali Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note Transport</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note Transport SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Penginapan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Not Approve</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM Nama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Not Approve SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama Pengganti</th>"+
                    "</tr></thead>";

            var i = i ;
            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';
                var combo = '';
                var berangkat = '';
                var kembali = '';
                var noteSdm = '';
                var penginapan = '';

                if(item.sppdApprove == false ){
                    combo = '<input type="checkbox" id="checkApprove" name="checkApproveAtasan[]" value="'+item.personId+'" class="checkApprove" >';
                }

                noteSdm = '<input type="text" id="note'+item.personId+'" value="'+item.noteSdmTransport+'">';

                berangkat = '<select class="" id="berangkat'+item.personId+'" >' +
                        '<option value="Pesawat">Pesawat</option>' +
                        '<option value="Kereta Api">Kereta Api</option>' +
                        '<option value="Bis">Bis</option> ' +
                        '<option selected value="'+item.berangkatVia+'">'+item.berangkatVia+'</option> ' +
                        '</select>';

                kembali = '<select class="" id="kembali'+item.personId+'" >' +
                        '<option value="Pesawat">Pesawat</option>' +
                        '<option value="Kereta Api">Kereta Api</option>' +
                        '<option value="Bis">Bis</option> ' +
                        '<option selected value="'+item.kembaliVia+'">'+item.kembaliVia+'</option> ' +
                        '</select>';

                penginapan = '<select class="" id="penginapan'+item.personId+'" >' +
                        '<option value="Hotel">Hotel</option>' +
                        '<option value="Mess">Mess</option>' +
                        '<option selected value="'+item.penginapan+'">'+item.penginapan+'</option> ' +
                        '</select>';

                if(item.sppdApprove == true){
                    atasan = item.linkAtasan ;
                }
                var myDate = new Date(item.tanggalBerangkat);
                var myDate1 = new Date(item.tanggalKembali);

                tmp_table += '<tr style="font-size: 11px; white-space: nowrap" ">' +
                        '<td align="center">'+combo+' </td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName  + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                        '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                        '<td align="center">' + berangkat + '</td>' +
                        '<td align="center">' + kembali+ '</td>' +
                        '<td align="center">' + item.noteAtasanTransport+ '</td>' +
                        '<td align="center">' + noteSdm+ '</td>' +
                        '<td align="center">' + penginapan+ '</td>' +
                        '<td align="center">' + item.approvalName + '</td>' +
                        '<td align="center"><img src="' + atasan + '"</td>' +
                        '<td align="center">' + item.notApprovalNote + '</td>' +
                        '<td align="center">' + item.approvalSdmName + '</td>' +
                        '<td align="center"><img src="' + sdm + '"</td>' +
                        '<td align="center">' + item.notApprovalSdmNote + '</td>' +
                        '<td align="center">' + item.pejabatSementaraNama + '</td>' +
                        "</tr>";
                //$("#berangkat"+item.personId).val("Bis").change();
            });
            $('.sppdPersonTableApproveAtasan').append(tmp_table);

        });
    }

    $('.sppdPersonTable').on('click', '.item-apply', function(){
        var nip = $(this).attr('data');
        var id = $(this).attr('datai');
        var pengganti = document.getElementById("anamaPengganti"+id).value;
        SppdAction.searchAnggotaAdd(nip, pengganti, function(listdata) {
            alert('berhasil');
        });
    });

    $('#btnSaveAnggota').on('click', function() {
        var sppdId = document.getElementById("sppdIdForDetail").value;
        var nip = document.getElementById("nip1").value;
        var nama = document.getElementById("nipNama").value;
        var unit = document.getElementById("branchId1").value;
        var jabatan = document.getElementById("positionId1").value;
        var divisi = document.getElementById("divisiId1").value;
        var tanggalBerangkat = document.getElementById("tanggalBerangkatAnggota").value;
        var tanggalKembali = document.getElementById("tanggalKembaliAnggota").value;
        var berangkatVia = document.getElementById("berangkatViaAnggota").value;
        var kembaliVia = document.getElementById("pulangViaAnggota").value;
        var kota1Id = document.getElementById("kotaDari1").value;
        var kota2Id = document.getElementById("kotaTujuan1").value;
        var dinas = document.getElementById("dinas1").value;
        var penginapan = document.getElementById("penginapanAnggota").value;

        SppdAction.cekAvailableSppd(nip, tanggalBerangkat, tanggalKembali, function(listdata) {
            if(listdata == "-"){
                if (confirm('Apakah anda ingin menambah anggota SPPD?')) {
                    SppdAction.addAnggota(sppdId, nip, nama, unit, jabatan, divisi, tanggalBerangkat, tanggalKembali, kota1Id,
                            kota2Id, berangkatVia, kembaliVia, penginapan, dinas, function(listdata) {
                                alert('Anggota berhasil ditambahkan');
                                loadPerson(sppdId);
                                $('#modal-anggota').modal('hide');
                                location.reload();
                            });
                }
            }else{
                alert(listdata);
            }
        });
    });

    function updatePengganti(nip, id){
        var pengganti = document.getElementById("anamaPengganti"+id).value;
        SppdAction.searchAnggotaAdd(nip, pengganti, function(listdata) {
            //alert('berhasil');
        });
    }

    $('#tanggalBerangkatAnggota').datepicker({
        dateFormat: 'dd-mm-yy',
        changeMonth: true,
        changeYear: true
    });

    $('#tanggalKembaliAnggota').datepicker({
        dateFormat: 'dd-mm-yy',
        changeMonth: true,
        changeYear: true
    });

    $(document).on('keydown', '.namaPengganti', function() {
        var id = this.id;
        var branch         = $('select[name=branchText] option:selected').val();
        var divisi         = $('select[name=divisiText] option:selected').val();
        //alert(id);
        $('#' + id).typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};

                var data = [];
                dwr.engine.setAsync(false);
                UserAction.initComboUser(query,branch, divisi, function (listdata) {
                    data = listdata;
                });
                //alert(prov);
                $.each(data, function (i, item) {
                    //alert(item.userId);
                    var labelItem = item.username;
                    mapped[labelItem] = { id: item.userId, label: labelItem, branchId : item.branchId, divisiId: item.divisiId, positionId : item.positionId };
                    functions.push(labelItem);
                });

                process(functions);
            },

            updater: function (item) {
                var selectedObj = mapped[item];
                var namaAlat = selectedObj.label;
                //updatePengganti(,//selectedObj.id)
                $('#a'+id).val(selectedObj.id);
                return namaAlat;
            }
        });
    });

</script>