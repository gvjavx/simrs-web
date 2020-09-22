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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_ijinKeluar'/>";
        }
        var unit = '<s:property value="CutiPegawai.unitId"/>'


        $(document).ready(function(){
            $.subscribe('beforeProcessSaveCutiBersama', function (event, data) {
                var tanggalAwal  = document.getElementById("tglAwal3").value;
                var tanggalAkhir = document.getElementById("tglAkhir3").value;
                var keterangan = document.getElementById("keterangan5").value;

                var nilai;
                nilai = $('input:checkbox:checked').length;
                nilai=nilai-1;
                if (nilai!=0) {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (nilai == 0) {
                        msg+='tidak ada pegawai yang di ceklist \n';
                    }
                    if (tanggalAwal == "") {
                        msg+='tanggal awal masih kosong \n';
                    }
                    if (tanggalAkhir == "") {
                        msg+='tanggal akhir masih kosong \n';
                    }
                    if (keterangan == "") {
                        msg+='keterangan masih kosong \n';
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
            Dispensasi/Cuti/Lainnya
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
                                    <s:form id="ijinKeluarForm" method="post"  theme="simple" namespace="/ijinKeluar" action="search_ijinKeluar.action" cssClass=" form-horizontal">
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
                                                    <label class="control-label"><small>Dispensasi Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="ijinKeluarId" name="ijinKeluar.ijinKeluarId" required="true" disabled="false" cssClass="form-control"/>
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
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="ijinKeluar.unitId" required="true" listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip" name="ijinKeluar.nip" required="true" disabled="false" cssClass="form-control"/>
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
                                                        var unit = $('#branchid').val();
                                                        if (unit!=""){
                                                            dwr.engine.setAsync(false);
                                                            IjinKeluarAction.initComboPersonil(query, unit, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.nip+" "+item.namaPegawai;
                                                                mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                                functions.push(labelItem);
                                                            });

                                                            process(functions);
                                                        } else {
                                                            alert("Unit is empty");
                                                            $('#nip').val("");
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
                                                        <s:textfield id="namaId" name="ijinKeluar.namaPegawai" required="true" readonly="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Ijin :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboIjin" namespace="/ijin" name="initComboIjin_ijin"/>
                                                        <s:select list="#initComboIjin.listOfComboIjin" id="ijinId" name="ijinKeluar.ijinId"
                                                                  listKey="ijinId" listValue="ijinName" headerKey="" headerValue="" cssClass="form-control" />
                                                        <s:textfield  id="ijinName" name="ijinKeluar.ijinName" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Bidang :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="ijinKeluar.divisiId"
                                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal Ijin :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <div class="input-group date">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampFrom" size="7" name="ijinKeluar.stTanggalAwal" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" size="7" name="ijinKeluar.stTanggalAkhir" cssClass="form-control pull-right"
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
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="ijinKeluarForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/ijinKeluar" action="add_ijinKeluar" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuIjinKeluar" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Dispensasi
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_ijinKeluar"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                    <%--<s:if test="isAdmin()">
                                                        <td>
                                                            <div class="btn-group">
                                                                <button class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
                                                                    <i class="fa fa-cogs"></i>
                                                                    Utility
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu">
                                                                    <li id="btnDispensasiMasal">
                                                                        <s:a href="#"><i class="fa fa-search"></i>Dispensasi Masal</s:a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </td>
                                                    </s:if>--%>
                                                </tr>
                                            </table>
                                        </div>

                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="80%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="waiting_dialog2" openTopics="showDialogLoading"
                                                                   closeTopics="closeDialogLoading" modal="true"
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

                                                        <%--<sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"--%>
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
                                                        <sj:dialog id="dialog_menu_ijin_keluar" openTopics="showDialogMenuIjinKeluar" modal="true"
                                                                   height="700" width="700" autoOpen="false"
                                                                   title="Dispensasi ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_ijin_keluar" openTopics="showDialogMenuView" modal="true"
                                                                   height="710" width="730" autoOpen="false"
                                                                   title="Dispensasi">
                                                        </sj:dialog>
                                                        <s:set name="listOfIjinKeluar" value="#session.listOfResultIjinKeluar" scope="request" />
                                                        <display:table name="listOfIjinKeluar" class="tableIjinKeluar table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_ijinKeluar.action" export="true" id="row" pagesize="14" style="font-size:10">

                                                            <display:column media="html" title="Pengajuan Batal">
                                                                <s:if test="#attr.row.pengajuanBatal">
                                                                    <s:if test='#attr.row.flagPengajuanBatal == "Y"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test='#attr.row.flagPengajuanBatal == "N"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <s:url var="urlPengajuan" namespace="/ijinKeluar" action="pengajuanBatal_ijinKeluar" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                        </s:url>
                                                                        <sj:a onClickTopics="showDialogMenuView" href="%{urlPengajuan}">
                                                                            <img border="0" src="<s:url value="/pages/images/delete_task.png"/>" name="icon_trash">
                                                                        </sj:a>
                                                                    </s:else>
                                                                </s:if>
                                                            </display:column>


                                                            <display:column media="html" title="Batal">
                                                                <%--<s:if test=""--%>
                                                                <%--<s:url var="urlCancel" namespace="/ijinKeluar" action="cancel_ijinKeluar" escapeAmp="false">--%>
                                                                    <%--<s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>--%>
                                                                    <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                                <%--</s:url>--%>
                                                                <%--<sj:a onClickTopics="showDialogMenuView" href="%{urlCancel}">--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                                <%--</sj:a>--%>
                                                                <s:if test="#attr.row.cancel">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.notApprove">
                                                                </s:elseif>
                                                                <s:elseif test="#attr.row.notApproveSdm">
                                                                </s:elseif>
                                                                <s:elseif test="#attr.row.canCancel">
                                                                    <s:url var="urlCancel" namespace="/ijinKeluar" action="cancel_ijinKeluar" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenuView" href="%{urlCancel}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Cetak Surat" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.cancel">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.finish">
                                                                    <s:if test="#attr.row.cetakSurat">
                                                                        <s:url var="urlCetakSuratIjinTidakMasuk" namespace="/ijinKeluar" action="cetakSuratIjinTidakMasuk_ijinKeluar" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlCetakSuratIjinTidakMasuk}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                        </s:a>
                                                                    </s:if>
                                                                </s:elseif>
                                                            </display:column>

                                                            <display:column media="html" title="Edit" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.cancel">
                                                            </s:if>
                                                            <s:elseif test="#attr.row.finish">
                                                                <s:if test='#attr.row.ijinId == "IJ013"'>
                                                                    <s:url var="urlViewDelete" namespace="/ijinKeluar" action="edit_ijinKeluar" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:if>
                                                            </s:elseif>
                                                            </display:column>

                                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                                <s:url var="urlViewDelete" namespace="/ijinKeluar" action="delete_ijinKeluar" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                                </sj:a>
                                                            </display:column>

                                                            <display:column style="text-align:center;" media="html" title="Surat Dokter">
                                                                <s:if test="#attr.row.dispenLahir">
                                                                    <s:if test="#attr.row.suratDokter">
                                                                        <a href="javascript:;" data="<s:property value="%{#attr.row.uploadFile}"/>" class="item-surat">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                                        </a>
                                                                    </s:if>
                                                                </s:if>
                                                            </display:column>
                                                            <display:column property="ijinKeluarId" sortable="true" title="Id" />
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="namaPegawai" sortable="true" title="Nama"  />
                                                            <display:column property="positionName" sortable="true" title="Jabatan"  />
                                                            <display:column property="ijinName" sortable="true" title="Nama Ijin"  />
                                                            <display:column property="stTanggalAwal" sortable="true" title="Tanggal Awal"  />

                                                            <s:if test='#attr.row.ijinId == "IJ013"'>
                                                                <display:column property="tanggalAkhirBaru" sortable="true" title="Tanggal Akhir"  />
                                                            </s:if>
                                                            <s:else>
                                                                <display:column property="stTanggalAkhir" sortable="true" title="Tanggal Akhir"  />
                                                            </s:else>
                                                            <%--<display:column property="stTanggalAkhir" sortable="true" title="Tanggal Akhir"  />--%>

                                                            <s:if test='#attr.row.ijinId == "IJ013"'>
                                                                <display:column property="lamaIjinBaru" sortable="true" title="Lama"  />
                                                            </s:if>
                                                            <s:else>
                                                                <display:column property="lamaIjin" sortable="true" title="Lama"  />
                                                            </s:else>
                                                            <%--<display:column property="lamaIjin" sortable="true" title="Lama"  />--%>

                                                            <s:if test="#attr.row.notApprove">
                                                                <display:column media="html" title="Approve Atasan">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                </display:column>
                                                            </s:if>
                                                            <s:else>
                                                                    <display:column media="html" title="Approve Atasan">
                                                                        <s:if test="#attr.row.cancel">
                                                                        </s:if>
                                                                        <s:elseif test="#attr.row.ijinKeluarApprove">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                        </s:elseif>
                                                                        <s:else>
                                                                        </s:else>
                                                                    </display:column>
                                                            </s:else>
                                                            <display:setProperty name="paging.banner.item_name">IjinKeluar</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">IjinKeluar</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">IjinKeluar.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">IjinKeluar.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">IjinKeluar.pdf</display:setProperty>
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

<script>
    $(document).ready(function() {
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>

<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Ijin Tidak Masuk</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Ijin Tidak Masuk ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="IjinKeluarId1" name="nip">
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
                            <input type="text" readonly class="form-control" id="Name321" name="nip">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="Unit1" name="ijinKeluar.unitId" readonly="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Ijin : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="Ijin1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Lama : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="Lama1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Awal : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="TanggalAwal1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Akhir : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="TanggalAkhir1" name="nip">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a id="btnNotApprove" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-edit-not-approve" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Keterangan Not Approve Ijin Tidak Masuk</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeterangan">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="keterangan" name="nip"></textarea>
                        </div>
                    </div>
                </form>
                <table style="width: 100%;" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a id="btnNotApprove1" type="button" class="btn btn-default btn-danger"><i class="fa fa-check"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
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
                <s:form id="saveCutiBersama" method="post" theme="simple" namespace="/ijinKeluar" action="saveDispensasiMasal_ijinKeluar.action" cssClass="form-horizontal">
                    <div class="container">
                        <div class="col-md-offset-2">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Ijin :</small></label> </div>
                                    <div class="col-md-4">
                                        <s:action id="initComboIjin" namespace="/ijin" name="initComboIjin_ijin"/>
                                        <s:select list="#initComboIjin.listOfComboIjin" id="ijinId3" name="" disabled="true"
                                                  listKey="ijinId" listValue="ijinName" headerKey="" headerValue="" cssClass="form-control" />
                                        <s:textfield  id="ijinName3" name="ijinKeluar.ijinName" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                        <s:textfield  id="ijinId23" name="ijinKeluar.ijinId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                    </div>
                                    <script>
                                        $(document).ready(function() {
                                            $('#ijinId3').change(function() {
                                                var namaijin= $('#ijinId3').val();
                                                dwr.engine.setAsync(false);
                                                IjinAction.initComboLamaIjin(namaijin, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    $('#maxIjin3').val(item.jumlahIjin).change();
                                                    $('#ijinName3').val(item.ijinName).change();
                                                });
                                            })
                                        });
                                    </script>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Max Ijin:</small></label> </div>
                                    <div class="col-md-4">
                                        <s:textfield  id="maxIjin3" name="" required="false"  readonly="true" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Awal:</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAwal3" name="ijinKeluar.stTanggalAwal" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Akhir:</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAkhir3" name="ijinKeluar.stTanggalAkhir" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Lama Ijin:</small></label> </div>
                                    <div class="col-md-4">
                                        <s:textfield  id="lamaId5" name="ijinKeluar.lamaIjin" required="false"  readonly="true" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-10">
                                    <div class="col-md-3"><label class="control-label"><small>Keterangan :</small></label> </div>
                                    <div class="col-md-4">
                                        <s:textarea rows="2" id="keterangan5" name="ijinKeluar.keterangan" required="true" cssClass="form-control"/>
                                    </div>
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
                                    <table style="width: 100%;" class="listDispensasiTable table table-bordered" id="listDispensasiTable">
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
                               onErrorTopics="errorDialog">
                        <i class="fa fa-check"></i>
                        Save Dispensasi Masal
                    </sj:submit>
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

<div id="modal-view-document" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Surat Dokter</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    $('#tglAwal3').datepicker({
        dateFormat: 'dd/mm/yy'
    });
    $('#tglAkhir3').datepicker({
        dateFormat: 'dd/mm/yy'
    });
    $('#tglAkhir3').on('change',function(){
        var tglawal=document.getElementById("tglAwal3").value;
        var tglakhir = document.getElementById("tglAkhir3").value;
        var startdate =$('#tglAwal3').datepicker('getDate');
        var enddate =$('#tglAkhir3').datepicker('getDate');
        var days = calcBusinessDays(startdate,enddate);
        var jmllibur;
        dwr.engine.setAsync(false);
        IjinKeluarAction.calculateLibur(tglawal,tglakhir, function (listdata) {
            jmllibur = listdata;
        });
        $('#lamaId5').val(days);
        var max =parseInt(document.getElementById("maxIjin3").value);
        if (enddate<startdate){
            alert ('Tanggal yang dipilih salah');
            $('#tglAkhir3').val("");
            $('#lamaId5').val("");
        }
//        if (days>max){
//            alert ("maaf anda melebihi ijin maksimal");
//            $('#lamaId5').val("");
//            $('#tglAkhir3').val("");
//        }
    });


    function calcBusinessDays(dDate1, dDate2) { // input given as Date objects
        var iWeeks, iDateDiff, iAdjust = 0;
        if (dDate2 < dDate1) return -1; // error code if dates transposed
        var iWeekday1 = dDate1.getDay(); // day of week
        var iWeekday2 = dDate2.getDay();
        iWeekday1 = (iWeekday1 == 0) ? 7 : iWeekday1; // change Sunday from 0 to 7
        iWeekday2 = (iWeekday2 == 0) ? 7 : iWeekday2;
        if ((iWeekday1 > 5) && (iWeekday2 > 5)) iAdjust = 1; // adjustment if both days on weekend
        iWeekday1 = (iWeekday1 > 5) ? 5 : iWeekday1; // only count weekdays
        iWeekday2 = (iWeekday2 > 5) ? 5 : iWeekday2;

        // calculate differnece in weeks (1000mS * 60sec * 60min * 24hrs * 7 days = 604800000)
        iWeeks = Math.floor((dDate2.getTime() - dDate1.getTime()) / 604800000);

        if (iWeekday1 <= iWeekday2) {
            iDateDiff = (iWeeks * 5) + (iWeekday2 - iWeekday1)
        } else {
            iDateDiff = ((iWeeks + 1) * 5) - (iWeekday1 - iWeekday2)
        }

        iDateDiff -= iAdjust // take into account both days on weekend

        return (iDateDiff + 1); // add 1 because dates are inclusive
    }


    $('#btnDispensasiMasal').on('click', function () {
        $('.listDispensasiTable').find('tbody').remove();
        $('.listDispensasiTable').find('thead').remove();
        dwr.engine.setAsync(false);

        $('#tglAwal3').val("");
        $('#tglAkhir3').val("");
        $('#ijinId3').val("IJ017");
        $('#ijinId23').val("IJ017");
        $('#ijinName3').val("Acara Perusahaan");
        $('#maxIjin3').val("0");
        $('#keterangan5').val("");


        var tmp_table = "";
        var unit=$('#branchid').val();
        if(unit!=""){
            IjinKeluarAction.listDispensasiMasal(unit,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'><input type='checkbox' id='checkAll'></th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    var combo = '<input type="checkbox" id="check" name="IjinKeluar.checkedValue" value="'+item.nip+'" class="check" >';
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + combo + '</td>' +
                            '<td align="center">' + item.nip + '</td>' +
                            '<td align="center">' + item.namaPegawai + '</td>' +
                            "</tr>";
                });
                $('.listDispensasiTable').append(tmp_table);
                $("#checkAll").change(function(){
                    $('input:checkbox').not(this).prop('checked', this.checked);
                });
            });
            $('#modal-list').find('.modal-title').text('Dispensasi Masal');
            $('#modal-list').modal('show');
        }else{
            alert("Unit belum diisi")
        }
    });



    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var ijinId = document.getElementById("IjinKeluarId1").value;
        var nip=document.getElementById("Nip1").value;
        if (confirm('Are you sure you want to save this Record?')) {
            dwr.engine.setAsync(false);
            IjinKeluarAction.saveApprove(ijinId, "Y",who,nip, function(listdata) {
                alert('Data Successfully Updated');
                $('#modal-edit').modal('hide');
                $('#myForm')[0].reset();
                location.reload();
            });
        }
    });
    $('#btnNotApprove').click(function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Ijin Tidak Masuk');
        $('#modal-edit-not-approve').modal('show');
    });
    $('#btnNotApprove1').click(function(){
        var who = $('#myForm').attr('action');
        var IjinKeluarId = document.getElementById("IjinKeluarId1").value;
        var note = document.getElementById("keterangan").value;
        var nip=document.getElementById("Nip1").value;
        if(note != ''){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);
                IjinKeluarAction.saveApprove(IjinKeluarId, note, who,nip, function(listdata) {
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
    $('.tableIjinKeluar').on('click', '.item-edit', function(){
        $('#modal-edit').find('.modal-title').text('Approve Ijin Atasan');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('.tableIjinKeluar').on('click', '.item-edit-sdm', function(){
        $('#modal-edit').find('.modal-title').text('Approve Ijin SDM');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'sdm');
    });
    $('.tableIjinKeluar').on('click', '.item-edit', function() {
        var IjinKeluarId = $(this).val().replace(/\n|\r/g, "");
        var IjinKeluarId = $(this).attr('data');
        var nama = $(this).attr('nama');
        var ijinid = $(this).attr('ijin');
        $('#IjinKeluarId1').val(IjinKeluarId);
        IjinKeluarAction.approveAtasan(IjinKeluarId, function (listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalAwal);
                var myDate1 = new Date(item.tanggalAkhir);
                $('#Nip1').val(item.nip);
                $('#Name321').val(item.namaPegawai);
                $('#TanggalAwal1').val((myDate.getDate()) + ' - ' + ("0" + (myDate.getMonth() + 1)).slice(-2) + ' - ' + myDate.getFullYear());
                $('#TanggalAkhir1').val((myDate1.getDate()) + ' - ' + ("0" + (myDate1.getMonth() + 1)).slice(-2) + ' - ' + myDate1.getFullYear());
                $('#Unit1').val(item.unitId).change();
                $('#keterangan').val(item.noteApproval).change();
                $('#PejabatSementara1').val(item.pegawaiPenggantiSementara);
            });
        });
        IjinAction.initComboIjinId(ijinid, function(listdata) {
            $.each(listdata, function (i, item) {
                $('#Ijin1').val(item.ijinName);
                $('#Lama1').val(item.jumlahIjin+' hari');
            })
        });
    });
    $('.tableIjinKeluar').on('click', '.item-edit-sdm', function() {
        var IjinKeluarId = $(this).val().replace(/\n|\r/g, "");
        var IjinKeluarId = $(this).attr('data');
        var nama = $(this).attr('nama');
        var ijinid = $(this).attr('ijin');
        $('#IjinKeluarId1').val(IjinKeluarId);
        IjinKeluarAction.approveAtasan(IjinKeluarId, function (listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalAwal);
                var myDate1 = new Date(item.tanggalAkhir);
                $('#Nip1').val(item.nip);
                $('#Name321').val(item.namaPegawai);
                $('#TanggalAwal1').val((myDate.getDate()) + ' - ' + ("0" + (myDate.getMonth() + 1)).slice(-2) + ' - ' + myDate.getFullYear());
                $('#TanggalAkhir1').val((myDate1.getDate()) + ' - ' + ("0" + (myDate1.getMonth() + 1)).slice(-2) + ' - ' + myDate1.getFullYear());
                $('#Unit1').val(item.unitId).change();
                $('#keterangan').val(item.noteApproval).change();
                $('#PejabatSementara1').val(item.pegawaiPenggantiSementara);
            });
        });
        IjinAction.initComboIjinId(ijinid, function(listdata) {
            $.each(listdata, function (i, item) {
                $('#Ijin1').val(item.ijinName);
                $('#Lama1').val(item.jumlahIjin+' hari');
            })
        });
    });

    $('.tableIjinKeluar').on('click', '.item-surat', function(){
        var id = $(this).attr('data');
        console.log("Test");
        console.log(id);
//        $('.tableCuti').find('tbody').remove();
//        $('.tableCuti').find('thead').remove();
        dwr.engine.setAsync(false);
        $("#my-image").attr("src","/mnt/surat/" + id);
        $('#modal-view-document').modal('show');
        $('#ViewDocument').attr('action', 'editPerson');
    })
</script>
