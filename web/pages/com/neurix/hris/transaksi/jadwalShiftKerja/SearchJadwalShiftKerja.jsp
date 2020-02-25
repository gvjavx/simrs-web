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
    <script type='text/javascript' src='<s:url value="/dwr/interface/JadwalShiftKerjaAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_jadwalShiftKerja'/>";
        }
        var unit = '<s:property value="JadwalShiftKerja.unitId"/>'
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
            Jadwal Shift Kerja
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
                                    <s:form id="jadwalShiftKerjaForm" method="post"  theme="simple" namespace="/jadwalShiftKerja" action="search_jadwalShiftKerja.action" cssClass=" form-horizontal">
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
                                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="jadwalShiftKerja.branchId" required="true" listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Jadwal :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="jadwalName" name="jadwalShiftKerja.jadwalShiftKerjaName" cssClass="form-control"
                                                                     required="false" cssStyle=""/>
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
                                                            <s:textfield id="loginTimestampFrom" name="jadwalShiftKerja.stTanggalAwal" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" name="jadwalShiftKerja.stTanggalAkhir" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
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
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="jadwalShiftKerjaForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="add_jadwalShiftKerja.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Jadwal Shift Kerja</a>
                                                    </td>
                                                    <%--<td>
                                                        <button type="button" id="btnAddJadwalOtomatis" class="btn btn-warning">
                                                            <i class="fa fa-calendar-plus-o"></i> Tambah Otomatis
                                                        </button>
                                                    </td>--%>
                                                    <%--<td>
                                                        <button type="button" class="btn btn-warning" id="btnCetakJadwal">
                                                            <i class="fa fa-print"></i> Print Jadwal Kerja
                                                        </button>
                                                    </td>--%>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_jadwalShiftKerja"/>'">
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
                                                                   height="670" width="900" autoOpen="false"
                                                                   title="Jadwal Shift Kerja">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_jadwalShiftKerja" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="900" autoOpen="false"
                                                                   title="Jadwal Shift Kerja">
                                                        </sj:dialog>
                                                        <s:set name="listOfJadwalShiftKerja" value="#session.listOfResultJadwalShiftKerja" scope="request" />
                                                        <display:table name="listOfJadwalShiftKerja" class="tableJadwalShiftKerja table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_jadwalShiftKerja.action" export="true" id="row" style="font-size:10">
                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.tamp">
                                                                    <s:if test="#attr.row.flagYes">
                                                                        <s:url var="urlEdit" namespace="/jadwalShiftKerja" action="edit_jadwalShiftKerja" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.jadwalShiftKerjaId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlEdit}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                        </s:a>
                                                                    </s:if>
                                                                </s:if>
                                                            </display:column>
                                                            <display:column property="jadwalShiftKerjaId" sortable="true" title="ID Jadwal" />
                                                            <display:column property="jadwalShiftKerjaName" sortable="true" title="Nama Jadwal"  />
                                                            <display:column property="branchName" sortable="true" title="Unit"  />
                                                            <display:column property="tanggal" sortable="true" title="Tanggal"  />
                                                            <display:column property="namaPegawai" sortable="true" title="Nama"  />
                                                            <display:column property="positionName" sortable="true" title="Posisi"  />
                                                            <display:column property="shiftName" sortable="true" title="Shift"  />
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
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
<div id="modal-add-auto" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tambah Otomatis Jadwal Shift Kerja</h4>
            </div>
            <div class="modal-body">
                <s:form id="saveAddJadwalAuto" method="post" theme="simple" namespace="/jadwalShiftKerja" action="saveAddJadwalAuto_jadwalShiftKerja" cssClass="form-horizontal">
                <center>
                    <div class="container">
                        <div class="">
                            <div class="row">
                                <div class="col-md-7">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Awal Jadwal :</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAwalJadwal" name="jadwalShiftKerja.stTanggalAwal" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-7">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Akhir Jadwal :</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAkhirJadwal" name="jadwalShiftKerja.stTanggalAkhir" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <button type="button" id="btnProses" class="btn btn-success">
                        <i class="fa fa-check"></i> Proses
                    </button>
                </center>
            </div>
            <br>
            </s:form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function() {
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });

        $('#btnAddJadwalOtomatis').on('click', function () {
            $('#modal-add-auto').find('.modal-title').text('Tambah Otomatis Jadwal Shift Kerja');
            $('#modal-add-auto').modal('show');
        });
        $('#btnProses').on('click', function () {
            var tglAwal =$('#loginTimestampFrom').val();
            var tglAkhir =$('#loginTimestampTo').val();
            if (tglAwal!=""&&tglAkhir!=""){
                if (confirm("Apakah anda ingin melakukan proses penambahan jadwal ?")){
                    dwr.engine.setAsync(false);
                    JadwalShiftKerjaAction.saveAddJadwalAuto(tglAwal,tglAkhir,function(data) {
                        if (data!=""){
                            alert(data);
                        } else{
                            alert("jadwal berhasil ditambahkan");
                            $('#modal-add-auto').modal('hide');
                            $('#loginTimestampFrom').val("");
                            $('#loginTimestampTo').val("");
                        }
                    });
                }
            }else{
                var msg = "";
                if (tglAwal == "") {
                    msg+='tanggal awal masih kosong \n';
                }
                if (tglAkhir == "") {
                    msg+='tanggal akhir masih kosong \n';
                }
                alert(msg);
            }
        });
        $('#btnCetakJadwal').click(function(){
            var tglFrom = document.getElementById("loginTimestampFrom").value;
            var tglTo = document.getElementById("loginTimestampTo").value;
            if (tglFrom!=""&&tglTo!=""){
                if (confirm('Apakah Anda ingin mencetak jadwal kerja pada tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?')) {
                    window.location.href = "printJadwalShiftKerja_jadwalShiftKerja.action?tglFrom="+tglFrom+"&tglTo="+tglTo;
                }
            }else{
                alert("ada tanggal yang masih kosong");
            }
        });
    });
</script>