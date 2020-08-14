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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>

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
        #modal-edit-inisialisasi{
            z-index: 9999;
        }
        #modal-inisialisasi{
            overflow-y:scroll;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_cutiPegawai'/>";
        }
        $(document).ready(function () {
            $.subscribe('beforeProcessSaveCutiBersama', function (event, data) {
                var values,tanggalAwal,tanggalAkhir,lamaCuti,alamat,keterangan;
                tanggalAwal=$('#tgl25').val();
                tanggalAkhir=$('#tgl15').val();
                lamaCuti=$('#lamaCuti5').val();
                alamat=$('#alamatCuti15').val();
                keterangan=$('#keterangan15').val();

                values = $('input:checkbox:checked').length;
                values=values-1;
                if (values!=0&&tanggalAwal!=""&&tanggalAkhir!=""&&lamaCuti!=""&&alamat!=""&&keterangan!="") {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (values == 0) {
                        msg+='tidak ada pegawai yang di ceklist \n';
                    }
                    if (tanggalAwal == "") {
                        msg+='tanggal awal masih kosong \n';
                    }
                    if (tanggalAkhir == "") {
                        msg+='tanggal akhir masih kosong \n';
                    }
                    if (lamaCuti == "") {
                        msg+='lama cuti masih kosong \n';
                    }
                    if (alamat == "") {
                        msg+='alamat masih kosong \n';
                    }
                    if (keterangan == "") {
                        msg+='keterangan masih kosong \n';
                    }
                    alert(msg);
                }
            });
            $.subscribe('beforeProcessSaveResetTahunan', function (event, data) {
                var values;
                values = $('input:checkbox:checked').length;
                values=values-1;
                if (values!=0) {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (values == 0) {
                        msg+='tidak ada pegawai yang di ceklist \n';
                    }
                    alert(msg);
                }
            });
            $.subscribe('beforeProcessSaveInisialisasi', function (event, data) {
                var values;
                values = $('input:checkbox:checked').length;
                values=values-1;
                if (values!=0) {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (values == 0) {
                        msg+='tidak ada pegawai yang di ceklist \n';
                    }
                    alert(msg);
                }
            });
            $.subscribe('beforeProcessSaveResetPanjang', function (event, data) {
                var values;
                values = $('input:checkbox:checked').length;
                if (values!=0) {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (values == 0) {
                        msg+='tidak ada pegawai yang di ceklist \n';
                    }
                    alert(msg);
                }
            });
            $.subscribe('successDialog2', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    alert('data berhasil disimpan');
                    $('#modal-list').modal('hide');
                }
            });
            $.subscribe('successDialog3', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    alert('Reset Cuti Tahunan Berhasil');
                    $('#modal-reset').modal('hide');
                }
            });
            $.subscribe('successDialog4', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    alert('Reset Cuti Panjang Berhasil');
                    $('#modal-reset-panjang').modal('hide');
                }
            });
            $.subscribe('successDialog5', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    alert('Inisialisasi Cuti Berhasil');
                    $('#modal-inisialisasi').modal('hide');
                }
            });
            $.subscribe('errorDialog1', function (event, data) {
                $('#modal-list').modal('hide');
                document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
                $.publish('showErrorDialog');
            });

            $.subscribe('errorDialog2', function (event, data) {
                $('#modal-reset').modal('hide');
                document.getElementById('errorMessage1').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
                $.publish('showErrorDialog1');
            });

            $.subscribe('errorDialog3', function (event, data) {
                $('#modal-reset-panjang').modal('hide');
                document.getElementById('errorMessage2').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
                $.publish('showErrorDialog2');
            });

//            $.subscribe('errorDialog1', function (event, data) {
//                if (event.originalEvent.request.status == 500){
//                    alert('Peringatan!!! Terdapat ');
//                    $('#modal-inisialisasi').modal('hide');
//                }
//            });
        })
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
            Cuti Pegawai
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
                                    <s:form id="cutiPegawaiForm" method="post"  theme="simple" namespace="/cutiPegawai" action="search_cutiPegawai.action" cssClass="form-horizontal">

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
                                                    <label class="control-label"><small>Jenis Cuti :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>
                                                        <s:select list="#comboCuti.listComboCuti" id="cuti" name="cutiPegawai.cutiId"
                                                                  listKey="cutiId" listValue="cutiName" headerKey="" headerValue="" cssClass="form-control" />
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
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="cutiPegawai.unitId" required="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip32" name="cutiPegawai.nip" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <script type='text/javascript'>
                                                var functions, mapped;
                                                $('#nip32').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        var unit = $('#branchid').val();
                                                        if (unit==""){
                                                            alert("unit is empty");
                                                            $('#namaId').val("");
                                                            $('#nip32').val("");
                                                        }else {
                                                            dwr.engine.setAsync(false);
                                                            CutiPegawaiAction.initComboAllPersonil(query, unit, function (listdata) {
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
                                                        document.getElementById("namaId").value = selectedObj.nama;
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
                                                        <s:textfield id="namaId" name="cutiPegawai.namaPegawai" required="true" readonly="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <%--<tr>
                                                <td>
                                                    <label class="control-label"><small>Jabatan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                                        <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid" name="cutiPegawai.posisiId" required="false"
                                                                  listKey="stPositionId" listValue="positionName" headerKey="" headerValue=""/>
                                                    </table>
                                                </td>
                                            </tr>--%>
                                            <%--<tr>--%>
                                                <%--<td>--%>
                                                    <%--<label class="control-label"><small>Bidang :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                    <%--<table>--%>
                                                        <%--<s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>--%>
                                                        <%--<s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="cutiPegawai.divisiId"--%>
                                                                  <%--listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />--%>
                                                    <%--</table>--%>

                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <%--<tr>
                                                <td>
                                                    <label class="control-label"><small>Bagian :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>
                                                        <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"
                                                                  listKey="bagian" listValue="bagianName" name="cutiPegawai.bagianId" headerKey="" headerValue="" />
                                                    </table>
                                                </td>
                                            </tr>--%>
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
                                                            <s:textfield id="loginTimestampFrom" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                                         required="false" size="7"  cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" name="cutiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
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
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="cutiPegawaiForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/cutiPegawai" action="add_cutiPegawai" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuView" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Cuti
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_cutiPegawai"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                    <s:if test="isAdmin()">
                                                        <td>
                                                            <div class="btn-group">
                                                                <button class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
                                                                    <i class="fa fa-cogs"></i>
                                                                    Utility
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu">
                                                                    <li id="btnViewCuti">
                                                                        <s:a href="#"><i class="fa fa-search"></i> View Cuti</s:a>
                                                                    </li>
                                                                    <li id="btnCutiBersama">
                                                                        <s:a href="#"><i class="fa fa-calendar-plus-o"></i> Cuti Bersama</s:a>
                                                                    </li>
                                                                    <li id="btnResetTahunan">
                                                                        <s:a href="#"><i class="fa fa-calendar-check-o"></i> Reset Tahunan</s:a>
                                                                    </li>
                                                                    <li id="btnResetPanjang">
                                                                        <s:a href="#"><i class="fa fa-calendar-check-o"></i> Reset Panjang</s:a>
                                                                    </li>
                                                                    <li id="btnInisialisasiCuti">
                                                                        <s:a href="#"><i class="fa fa-user-plus"></i> Perbaikan Data Cuti</s:a>
                                                                    </li>
                                                                    <%--<li id="btnCutiMinus">--%>
                                                                        <%--<s:a href="#"><i class="fa fa-search"></i> Daftar Cuti Minus</s:a>--%>
                                                                    <%--</li>--%>
                                                                    <li id="btnCetakSisaCuti">
                                                                        <s:a href="#"><i class="fa fa-search"></i> Cetak Sisa Cuti</s:a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </td>
                                                    </s:if>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="96%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="waiting_dialog2" openTopics="showDialogLoading"
                                                                   closeTopics="closeDialogLoading" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Save Data ...">
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
                                                                   height="700" width="900" autoOpen="false"
                                                                   title="Cuti Pegawai ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_cuti_pegawai" openTopics="showDialogMenuView" modal="true"
                                                                   height="700" width="750" autoOpen="false"
                                                                   title="Cuti Pegawai">
                                                        </sj:dialog>

                                                        <s:set name="listOfCutiPegawai" value="#session.listOfResultCutiPegawai" scope="request" />
                                                        <display:table name="listOfCutiPegawai" class="tableCutiPegawai table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_cutiPegawai.action" export="true" id="row" pagesize="14" style="font-size:10">

                                                            <display:column media="html" title="Pengajuan Batal">
                                                                <s:if test="#attr.row.pengajuanBatal">
                                                                    <s:if test='#attr.row.flagPengajuanBatal == "Y"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test='#attr.row.flagPengajuanBatal == "N"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <s:url var="urlCancel" namespace="/cutiPegawai" action="batal_cutiPegawai" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                        </s:url>
                                                                        <sj:a onClickTopics="showDialogMenuView" href="%{urlCancel}">
                                                                            <img border="0" src="<s:url value="/pages/images/delete_task.png"/>" name="icon_trash">
                                                                        </sj:a>
                                                                    </s:else>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Batal">
                                                                <s:if test="#attr.row.cancel">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.notApprove">
                                                                </s:elseif>
                                                                <s:elseif test="#attr.row.canCancel">
                                                                    <s:url var="urlCancel" namespace="/cutiPegawai" action="cancel_cutiPegawai" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenuView" href="%{urlCancel}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.cancel">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.flagYes">
                                                                    <s:if test="#attr.row.cutiPegawaiApprove">
                                                                    </s:if>
                                                                    <s:elseif test="#attr.row.notApprove">
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <s:url var="urlEdit" namespace="/cutiPegawai" action="edit_cutiPegawai" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.cutiPegawaiId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <sj:a onClickTopics="showDialogMenuView" href="%{urlEdit}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                        </sj:a>
                                                                    </s:else>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Cetak Surat" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.cancel">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.finish">
                                                                    <s:url var="urlCetakSuratCuti" namespace="/cutiPegawai" action="cetakSuratCuti_cutiPegawai" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlCetakSuratCuti}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                                <s:url var="urlViewDelete" namespace="/cutiPegawai" action="delete_cutiPegawai" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                                </sj:a>
                                                            </display:column>
                                                            <display:column property="cutiPegawaiId" sortable="true" title="Id" />
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="namaPegawai" sortable="true" title="Nama"  />
                                                            <display:column property="posisiName" sortable="true" title="Jabatan"/>
                                                            <display:column property="divisiName" sortable="true" title="Bidang"/>
                                                            <display:column property="cutiName" sortable="true" title="Nama Cuti"/>
                                                            <display:column property="lamaHariCuti" sortable="true" title="<center>Lama Cuti</center>" style="text-align:center"/>
                                                            <display:column property="strTanggalDari" sortable="true" title="Tanggal Dari" style="text-align:center"/>
                                                            <display:column property="strTanggalSelesai" sortable="true" title="Tanggal Selesai" style="text-align:center"/>
                                                            <display:column property="keterangan" sortable="true" title="Keterangan"/>
                                                            <%--<display:column property="pegawaiPenggantiSementara" sortable="true" title="Pegawai Pengganti"/>--%>
                                                            <s:if test="#attr.row.notApprove">
                                                                <display:column media="html" title="Approve Atasan"  style="text-align:center">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                </display:column>
                                                            </s:if>
                                                            <s:else>
                                                                <display:column media="html" title="Approve Atasan"  style="text-align:center">
                                                                    <s:if test="#attr.row.cancel">
                                                                    </s:if>
                                                                    <s:elseif test="#attr.row.cutiPegawaiApprove">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:else>
                                                                    </s:else>
                                                                </display:column>
                                                            </s:else>
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
<div id="modal-edit-inisialisasi" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormView">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >NIP </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nipEdit" name="cutiPegawai.nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="namaEdit" name="cutiPegawai.namaPegawai" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Sisa Cuti Tahunan </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="stSisaCutiTahunan" name="cutiPegawai.stSisaCutiTahunan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Sisa Cuti Panjang</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="stSisaCutiPanjang" name="cutiPegawai.stSisaCutiPanjang">
                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btnSaveTmpInisialisasi">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-list" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Detail Cuti Karyawan</h4>
            </div>
            <div class="modal-body">
                <s:form id="saveCutiBersama" method="post" theme="simple" namespace="/cutiPegawai" action="saveCutiBersama_cutiPegawai" cssClass="form-horizontal">
                    <div class="container">
                        <div class="col-md-offset-2">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Awal Cuti :</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl25" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Akhir Cuti :</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl15" name="cutiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Lama Cuti :</small></label> </div>
                                    <div class="col-md-4"><s:textfield  id="lamaCuti5" name="cutiPegawai.lamaHariCuti" required="false" readonly="true" cssClass="form-control"/></div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Alamat Cuti :</small></label> </div>
                                    <div class="col-md-4"><s:textfield  id="alamatCuti15" name="cutiPegawai.alamatCuti" required="false" cssClass="form-control"/></div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Keterangan :</small></label> </div>
                                    <div class="col-md-4"><s:textarea rows="2" id="keterangan15" name="cutiPegawai.keterangan" required="true" cssClass="form-control"/>  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <center>
                        <br>
                        <br>
                        <table id="showdata2" width="80%">
                            <tr>
                                <td align="center" colspan="4">
                                    <table style="width: 100%;" class="listCutiTable table table-bordered" id="listCutiTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <center>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                   formIds="saveCutiBersama" id="btnSaveCutiBersama" name="save"
                                   onBeforeTopics="beforeProcessSaveCutiBersama"
                                   onCompleteTopics="closeDialog,successDialog2"
                                   onSuccessTopics="successDialog2"
                                   onErrorTopics="errorDialog1">
                            <i class="fa fa-check"></i>
                            Save Cuti Bersama
                        </sj:submit>

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
</div>
<div id="modal-reset" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Reset Cuti Karyawan</h4>
            </div>
            <div class="modal-body">
                <s:form id="saveResetTahunan" method="post" theme="simple" namespace="/cutiPegawai" action="saveResetTahunan_cutiPegawai" cssClass="form-horizontal">
                    <center>
                    <table id="showdata2" width="80%">
                        <tr>
                            <td align="center" colspan="4">
                                <table style="width: 100%;" class="listResetTahunanTable table table-bordered" id="listResetTahunanTable">
                                </table>
                            </td>
                        </tr>
                    </table>

                    <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                               formIds="saveResetTahunan" id="btnSaveResetTahunan" name="save"
                               onBeforeTopics="beforeProcessSaveResetTahunan"
                               onCompleteTopics="closeDialog,successDialog3"
                               onSuccessTopics="successDialog3"
                               onErrorTopics="errorDialog2">
                        <i class="fa fa-check"></i>
                        Save Reset Tahunan
                    </sj:submit>

                        <sj:dialog id="error_dialog1" openTopics="showErrorDialog1" modal="true" resizable="false"
                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                   buttons="{
                                                                        'OK':function() { $('#error_dialog1').dialog('close'); }
                                                                    }"
                        >
                            <div class="alert alert-error fade in">
                                <label class="control-label" align="left">
                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage1"></p>
                                </label>
                            </div>
                        </sj:dialog>
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
<div id="modal-reset-panjang" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Reset Cuti Karyawan</h4>
            </div>
            <div class="modal-body">
                <s:form id="saveResetPanjang" method="post" theme="simple" namespace="/cutiPegawai" action="saveResetPanjang_cutiPegawai" cssClass="form-horizontal">
                <center>
                    <table id="showdata3" width="80%">
                        <tr>
                            <td align="center" colspan="4">
                                <table style="width: 100%;" class="listResetPanjangTable table table-bordered" id="listResetPanjangTable">
                                </table>
                            </td>
                        </tr>
                    </table>

                    <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                               formIds="saveResetPanjang" id="btnSaveResetPanjang" name="save"
                               onBeforeTopics="beforeProcessSaveResetPanjang"
                               onCompleteTopics="closeDialog,successDialog4"
                               onSuccessTopics="successDialog4"
                               onErrorTopics="errorDialog3">
                        <i class="fa fa-check"></i>
                        Save Reset Panjang
                    </sj:submit>

                    <sj:dialog id="error_dialog2" openTopics="showErrorDialog2" modal="true" resizable="false"
                               height="250" width="600" autoOpen="false" title="Error Dialog"
                               buttons="{
                                                                        'OK':function() { $('#error_dialog2').dialog('close'); }
                                                                    }"
                    >
                        <div class="alert alert-error fade in">
                            <label class="control-label" align="left">
                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage2"></p>
                            </label>
                        </div>
                    </sj:dialog>
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
<div id="modal-view-cuti" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Cuti</h4>
            </div>
            <div class="modal-body">
                <center>
                    <table id="showdata4" width="80%">
                        <tr>
                            <td align="center" colspan="4">
                                <table style="width: 100%;" class="listViewCuti table table-bordered" id="listViewCuti">
                                </table>
                            </td>
                        </tr>
                    </table>
                </center>
            </div>
            <br>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-inisialisasi" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Inisialisasi Cuti Karyawan</h4>
            </div>
            <div class="modal-body">
                <s:form id="saveInisialisasi" method="post" theme="simple" namespace="/cutiPegawai" action="saveInisialisasi_cutiPegawai" cssClass="form-horizontal">
                <center>
                    <table id="showdata5" width="80%">
                        <tr>
                            <td align="center" colspan="4">
                                <table style="width: 100%;" class="listInisialisasiCutiTable table table-bordered" id="listInisialisasiCutiTable">
                                </table>
                            </td>
                        </tr>
                    </table>

                    <button type="button" id="btnSavePerbaikanSisaCuti" class="btn btn-success">Save Perbaikan</button>
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
<div id="modal-cuti-minus" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Daftar Karyawan Cuti Minus</h4>
            </div>
            <div class="modal-body">
                <s:form id="cutiMinus" method="post" theme="simple" namespace="/cutiPegawai" action="cutiMinus_cutiPegawai" cssClass="form-horizontal">
                <center>
                    <table id="showdata6" width="80%">
                        <tr>
                            <td align="center" colspan="4">
                                <table style="width: 100%;" class="listCutiMinusTable table table-bordered" id="listCutiMinusTable">
                                </table>
                            </td>
                        </tr>
                    </table>
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
        $('#btn_reset').click(function(){
            if (confirm('Apakah anda setuju untuk reset?')) {
             CutiPegawaiAction.resetSisaCuti(function(data){
                 if (data=="sukses"){
                     alert("Cuti berhasil di Reset");
                     location.reload();
                 }else{
                     alert("Cuti Gagal di Reset")
                 }
                })
            }
        });
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd/mm/yy'
        });
    });
    $("#btnProses").click(function() {
        $("#btnProsesSave").trigger( "click" );
    });
    $('#btnCutiBersama').on('click', function () {
        $('.listAbsensiTable').find('tbody').remove();
        $('.listAbsensiTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        var unit=$('#branchid').val();
        if(unit!=""){
            CutiPegawaiAction.searchCutiBersama(unit,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'><input type='checkbox' id='checkAll'></th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #bc6a3c'>Sisa Cuti Panjang</th>"+
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    var combo = '<input type="checkbox" id="check" name="cutiPegawai.checkedValue" value="'+item.nip+':'+item.sisaCutiTahunan+':'+item.sisaCutiPanjang+'" class="check" >';
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + combo + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                        '<td align="center">' + item.sisaCutiPanjang + '</td>' +
                        "</tr>";
                });
                $('.listCutiTable').append(tmp_table);
                $("#checkAll").change(function(){
                    $('input:checkbox').not(this).prop('checked', this.checked);
                });
            });
            $('#modal-list').find('.modal-title').text('Cuti Bersama Karyawan');
            $('#modal-list').modal('show');
        }else{
            alert("Unit belum diisi")
        }
    });
    $('#btnResetTahunan').on('click', function () {
        $('.listResetTahunanTable').find('tbody').remove();
        $('.listResetTahunanTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        var unit=$('#branchid').val();
        var today = new Date();
        var month = today.getMonth()+1;
//        alert(date);
//        if(month == 1){
//            alert(cutiAktif);
            if(unit!=""){
                var cutiAktif="";
                CutiPegawaiAction.getCutiAktif(unit,function(listdata){
                    cutiAktif = listdata;
                });
                if( cutiAktif =='N'){
                    CutiPegawaiAction.searchCutiBersama(unit,function(listdata) {
                        if (listdata!=""){
                            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'><input type='checkbox' id='checkAllTahunan'></th>"+
                                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Setelah Reset</th>"+
                                    "</tr></thead>";
                            var i = i;
                            $.each(listdata, function (i, item) {
                                var combo = '<input type="checkbox" id="check" name="cutiPegawai.checkedValue" value="'+item.nip+':'+item.sisaCutiTahunan+':'+item.setelahResetCutiTahunan+'" class="check" >';
                                tmp_table += '<tr style="font-size: 12px;" ">' +
                                        '<td align="center">' + (i + 1) + '</td>' +
                                        '<td align="center">' + combo + '</td>' +
                                        '<td align="center">' + item.nip + '</td>' +
                                        '<td align="center">' + item.namaPegawai + '</td>' +
                                        '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                                        '<td align="center">' + item.setelahResetCutiTahunan + '</td>' +
                                        "</tr>";
                            });
                            $('.listResetTahunanTable').append(tmp_table);
                            $("#checkAllTahunan").change(function(){
                                $('input:checkbox').not(this).prop('checked', this.checked);
                            });

                            $('#modal-reset').find('.modal-title').text('Reset Cuti Tahunan Karyawan');
                            $('#modal-reset').modal('show');
                        }else{
                            alert("tidak ada data untuk di reset ");
                        }
                    });
                }else{
                    alert(cutiAktif);
                }
            }else{
                alert("Unit belum diisi")
            }
//        }else{
//            alert('Reset Cuti Tahunan Hanya Bisa Dilakukan di bulan Januari');
//        }

    });
    $('.tableCutiPegawai').on('click', '.item-edit', function() {
        var CutiPegawaiId = $(this).val().replace(/\n|\r/g, "");
        var CutiPegawaiId = $(this).attr('data');
        var nip = $(this).attr('nip');
        $('#Nip1').val(nip);
        var namapegawai;
        $('#CutiPegawaiId1').val(CutiPegawaiId);
        CutiPegawaiAction.approveAtasan(CutiPegawaiId, function(listdata) {
            $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalDari);
                    var myDate1 = new Date(item.tanggalSelesai);
                    namapegawai = item.namaPegawai;
                    $('#Nama321').val(namapegawai);
                    $('#TanggalAwal1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    $('#TanggalAkhir1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());
                    $('#Divisi1').val(item.divisiId).change();
                    $('#Posisi1').val(item.posisiId).change();
                    $('#Unit321').val(item.unitId).change();
                    $('#LamaHariCuti321').val(item.lamaHariCuti).change();
                    $('#CutiName321').val(item.cutiName).change();
                    $('#keterangan').val(item.noteApproval).change();
                    $('#PejabatSementara1').val(item.pegawaiPenggantiSementara);
                    if(item.cutiPegawaiApproveStatus == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                    }else{
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                }
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Cuti Pegawai');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('#btnNotApprove').on('click', function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Cuti Pegawai');
        $('#modal-edit-not-approve').modal('show');
    });
    $('#btnNotApprove1').click(function(){
        var who = $('#myForm').attr('action');
        var CutiPegawaiId = document.getElementById("CutiPegawaiId1").value;
        var note = document.getElementById("keterangan").value;
        var nipid=document.getElementById("Nip1").value;
        //alert(note);
        if(note != ''){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                CutiPegawaiAction.saveApprove(CutiPegawaiId, note, who,nipid, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        }else{
            alert('Silahkan isi keterangan Not Approve !');
        }
    });
    $('#tgl15').datepicker({
        dateFormat: 'dd/mm/yy'
    });
    $('#tgl25').datepicker({
        dateFormat: 'dd/mm/yy'
    });

    $('#tgl15').on('change',function(){
        var tglawal = document.getElementById("tgl25").value;
        var tglakhir = document.getElementById("tgl15").value;
        var enddate =$('#tgl15').datepicker('getDate');
        var startdate =$('#tgl25').datepicker('getDate');
        var days;
        var jmllibur;
        if (startdate==null){
            alert("Isikan Tanggal Awal Cuti");
            $('#tgl15').val("");
        }
        else{

            dwr.engine.setAsync(false);
            IjinKeluarAction.calculateLibur(tglawal,tglakhir, function (listdata) {
                jmllibur = listdata;
            });
            if (startdate<=enddate) {
                days = (enddate - startdate)/1000/60/60/24;
                days = days+1;
                days = days-jmllibur;
                $('#lamaCuti5').val(days);
            }
        }
    });
    $('#btnResetPanjang').on('click', function () {
        $('.listResetPanjangTable').find('tbody').remove();
        $('.listResetPanjangTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        var unit=$('#branchid').val();
        if(unit!=""){
//            var cutiAktif="";
//            CutiPegawaiAction.getCutiAktif(unit,function(listdata){
//                cutiAktif = listdata;
//            });
//            if( cutiAktif =='N'){
                CutiPegawaiAction.searchResetPanjang(unit,function(listdata) {
                    if (listdata!=""){
                        tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'><input type='checkbox' id='checkAllPanjang'></th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Aktif</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Panjang</th>"+
                                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Setelah Reset</th>"+
                                "</tr></thead>";
                        var i = i;
                        $.each(listdata, function (i, item) {
                            var combo = '<input type="checkbox" id="checkPanjang" name="cutiPegawai.checkedValue" value="'+item.nip+':'+item.sisaCutiPanjang+':'+item.setelahResetCutiPanjang+'" class="check">';
                            tmp_table += '<tr style="font-size: 12px;" ">' +
                                    '<td align="center">' + (i + 1) + '</td>' +
                                    '<td align="center">' + combo + '</td>' +
                                    '<td align="center">' + item.nip + '</td>' +
                                    '<td align="center">' + item.namaPegawai + '</td>' +
                                    '<td align="center">' + item.stTanggalAktif + '</td>' +
                                    '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                                    '<td align="center">' + item.sisaCutiPanjang + '</td>' +
                                    '<td align="center">' + item.setelahResetCutiPanjang + '</td>' +
                                    "</tr>";
                        });
                        $('.listResetPanjangTable').append(tmp_table);
                        $("#checkAllPanjang").change(function(){
                            $('input:checkbox').not(this).prop('checked', this.checked);
                        });
                        $('#modal-reset-panjang').find('.modal-title').text('Reset Cuti Panjang Karyawan');
                        $('#modal-reset-panjang').modal('show');
                    } else{
                        alert("tidak ada data untuk di reset");
                    }

                });
//            }else{
//                alert(cutiAktif);
//            }

        }else{
            alert("Unit belum diisi");
        }

    });
    $('#btnViewCuti').on('click', function () {
        var nip= $('#nip32').val();
        $('.listViewCuti').find('tbody').remove();
        $('.listViewCuti').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        if (nip!=""){
            CutiPegawaiAction.searchViewCuti(nip,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Panjang</th>"+
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                        '<td align="center">' + item.sisaCutiPanjang + '</td>' +
                        "</tr>";
                });
                $('.listViewCuti').append(tmp_table);
            });
            $('#modal-view-cuti').find('.modal-title').text('View Cuti Pegawai');
            $('#modal-view-cuti').modal('show');
        } else {
            alert("NIP masih kosong.");
        }
    });
    window.loadPersonCuti =  function(){
        $('.listInisialisasiCutiTable').find('tbody').remove();
        $('.listInisialisasiCutiTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        $('.listInisialisasiCutiTable').empty();
        var unit=$('#branchid').val();
        if(unit!=""){
            CutiPegawaiAction.searchInisialisasiCuti(unit,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Panjang</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Setelah Edit Sisa Tahunan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Setelah Edit Sisa Panjang</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Edit</th>"+
                    "</tr></thead>";
                var i = i;
                if (listdata!=""){
                    $.each(listdata, function (i, item) {
                        var combo = '<input type="checkbox" id="check" name="cutiPegawai.checkedValue" value="'+item.nip+':'+item.setelahResetCutiTahunan+'" class="check" >';
                        tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.nip + '</td>' +
                            '<td align="center">' + item.namaPegawai + '</td>' +
                            '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                            '<td align="center">' + item.sisaCutiPanjang + '</td>' +
                            '<td align="center">' + item.stSetelahResetCutiTahunan + '</td>' +
                            '<td align="center">' + item.stSetelahResetCutiPanjang + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-edit' data ='"+item.nip+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/edit_task.png'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>' +
                            "</tr>";
                    });
                    $('.listInisialisasiCutiTable').append(tmp_table);
                }else{
                    alert("Tidak ada data untuk diperbaiki");
                }

            });

        }
        else{
        }
    };
    $('#btnInisialisasiCuti').on('click', function () {
        CutiPegawaiAction.destroySessionInisialisasiCuti();
        loadPersonCuti();
        var unit=$('#branchid').val();
        if(unit!="") {
            $('#modal-inisialisasi').find('.modal-title').text('Inisialisasi Cuti Karyawan');
            $('#modal-inisialisasi').modal('show');
        }else{
            alert("Unit belum diisi");
        }
    });
    $('#btnSaveTmpInisialisasi').on('click', function () {
        var nip=$("#nipEdit").val();
        var stSisaCutiTahunan=$("#stSisaCutiTahunan").val();
        var stSisaCutiPanjang=$("#stSisaCutiPanjang").val();
        if(stSisaCutiTahunan!=""||stSisaCutiPanjang!="") {
            CutiPegawaiAction.editTmpInisialisasiCuti(nip ,stSisaCutiTahunan,stSisaCutiPanjang,function(data) {
                if (data!=""){
                    alert(data);
                }
                $('#modal-edit-inisialisasi').modal('hide');
                $("#stSisaCutiTahunan").val("");
                $("#stSisaCutiPanjang").val("");
                loadPersonCuti();
            });
        }else{
            alert("Belum mengisi sisa cuti yang dirubah ");
        }
    });
    $('#btnSavePerbaikanSisaCuti').on('click', function () {
        CutiPegawaiAction.saveInisialisasi(function(data) {
            if (data!=""){
                alert("User Sudah Pernah Memperbaiki Cuti");
            }else{
                alert("perbaikan berhasil");
                $('#modal-edit-inisialisasi').modal('hide');
                $('#modal-inisialisasi').modal('hide');
            }
        });
    });
    $('.listInisialisasiCutiTable').on('click', '.item-edit', function(){
        var nip = $(this).attr('data');
        dwr.engine.setAsync(false);
        CutiPegawaiAction.searchNipInisialisasiCuti(nip ,function(listdata) {
            $.each(listdata, function (i, item) {
                $('#nipEdit').val(item.nip);
                $('#namaEdit').val(item.namaPegawai);
            });
        });
        $('#modal-edit-inisialisasi').find('.modal-title').text('Edit Data Cuti Pegawai');
        $('#modal-edit-inisialisasi').modal('show');
        $('#myForm').attr('action', 'edit');
    });

    $('#btnCutiMinus').on('click', function () {
        $('.listCutiMinusTable').find('tbody').remove();
        $('.listCutiMinusTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        var unit=$('#branchid').val();
        if(unit!=""){
            CutiPegawaiAction.searchCutiMinus(unit,function(listdata) {
                if (listdata!=""){
                    tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Aktif</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Panjang</th>"+
                        "</tr></thead>";
                    var i = i;
                    $.each(listdata, function (i, item) {
                        tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.nip + '</td>' +
                            '<td align="center">' + item.namaPegawai + '</td>' +
                            '<td align="center">' + item.stTanggalAktif + '</td>' +
                            '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                            '<td align="center">' + item.sisaCutiPanjang + '</td>' +
                            "</tr>";
                    });
                    $('.listCutiMinusTable').append(tmp_table);
                    $('#modal-cuti-minus').modal('show');
                } else{
                    alert("tidak ada data cuti yang minus");
                }

            });

        }else{
            alert("Unit belum diisi");
        }

    });

    $('#btnCetakSisaCuti').on('click', function () {
        var nip= $('#nip32').val();
        var nama= $('#namaId').val();
        $('.listViewCuti').find('tbody').remove();
        $('.listViewCuti').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        if (nip!=""){
            var msg='Apakah Anda ingin mencetak Sisa Cuti '+nama+'  ?';
            if (confirm(msg)) {
                window.location.href = "cetakSisaCutiPegawai_cutiPegawai.action?nip="+nip;
            }
        } else {
            alert("NIP masih kosong.");
        }
    });
</script>

