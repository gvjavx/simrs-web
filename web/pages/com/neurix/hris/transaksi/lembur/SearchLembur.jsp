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
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_lembur'/>";
        }
        var unit = '<s:property value="Lembur.unitId"/>'
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
            Lembur
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
                                    <s:form id="lemburForm" method="post"  theme="simple" namespace="/lembur" action="search_lembur.action" cssClass=" form-horizontal">
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
                                                    <label class="control-label"><small>No. Lembur Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="lemburId" name="lembur.lemburId" required="true" disabled="false" cssClass="form-control"/>
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
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="lembur.unitId" required="true"
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
                                                        <s:textfield id="nip" name="lembur.nip" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaId" name="lembur.nama" required="true" readonly="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Status Giling :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}" id="statusGiling" name="lembur.statusGiling"
                                                                  headerKey="" headerValue="" cssClass="form-control" />
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
                                                            <s:textfield id="loginTimestampFrom" name="lembur.stTanggalAwal" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" name="lembur.stTanggalAkhir" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                        </div>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Status Approval :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'0':'Menunggu Approve','Y':'Telah di Approve','N':'Tidak di Approve'}" id="statusAproval" name="lembur.approvalFlag"
                                                                  headerKey="" headerValue="Semua Status" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <script>
                                            var functions, mapped;
                                            $('#nip').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};

                                                    var data = [];
                                                    var unit = $('#branchid').val();
                                                    if (unit==""){
                                                        alert("unit is empty");
                                                        $('#namaId').val("");
                                                        $('#nip').val("");
                                                    }else {
                                                        dwr.engine.setAsync(false);
                                                        CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
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
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="lemburForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/lembur" action="add_lembur" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Lembur
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_lembur"/>'">
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
                                                        <sj:dialog id="dialog_menu_lembur" openTopics="showDialogMenu" modal="true"
                                                                   height="670" width="700" autoOpen="false"
                                                                   title="Lembur">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_lembur" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="900" autoOpen="false"
                                                                   title="Lembur">
                                                        </sj:dialog>
                                                        <s:set name="listOfLembur" value="#session.listOfResultLembur" scope="request" />
                                                        <display:table name="listOfLembur" class="tableLembur table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_lembur.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="Delete">
                                                                <s:if test="#attr.row.terRealisasi">
                                                                </s:if>
                                                                <s:else>
                                                                    <s:url var="urlEdit" namespace="/lembur" action="delete_lembur" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.lemburId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_edit">
                                                                    </sj:a>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.terRealisasi">
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test="#attr.row.lemburApprove">
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:url var="urlEdit" namespace="/lembur" action="edit_lembur" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.lemburId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                        </sj:a>
                                                                    </s:else>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column property="lemburId" sortable="true" title="Lembur Id" />
                                                            <display:column property="tipeLemburName" sortable="true" title="Tipe Lembur"  />
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="pegawaiName" sortable="true" title="Nama"  />
                                                            <display:column property="positionName" sortable="true" title="Jabatan"  />
                                                            <display:column property="stTanggalAwal" sortable="true" title="Tanggal"  />
                                                            <display:column property="jamAwal" sortable="true" title="Jam Awal"  />
                                                            <display:column property="jamAkhir" sortable="true" title="Jam Akhir"  />
                                                            <display:column property="lamaJam" sortable="true" title="Pengajuan"  />
                                                            <display:column property="jamRealisasi" sortable="true" title="Realisasi"  />
                                                            <display:column media="html" title="Approve Atasan">
                                                                <s:if test="#attr.row.lemburApprove">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.notApprove">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:else>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column media="html" title="Refresh">
                                                                <s:if test="#attr.row.lemburApprove">
                                                                    <s:if test="#attr.row.adaAbsen">
                                                                        <s:if test="#attr.row.terRealisasi">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_success">
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a href="javascript:;"  tanggal="<s:property value="%{#attr.row.stTanggalAwal}"/>" nip="<s:property value="%{#attr.row.nip}"/>" jamAwal="<s:property value="%{#attr.row.jamAwal}"/>" jamAkhir="<s:property value="%{#attr.row.jamAkhir}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                                                <img border="0" src="<s:url value="/pages/images/icon_reset.png"/>" name="icon_edit">
                                                                            </a>
                                                                        </s:else>
                                                                    </s:if>
                                                                </s:if>
                                                            </display:column>
                                                            <s:if test="#attr.row.cekAdmin">
                                                            <display:column media="html" title="Sesuaikan">
                                                                    <s:if test="#attr.row.lemburApprove">
                                                                        <s:if test="#attr.row.sesuai">
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a href="javascript:;"  tanggal="<s:property value="%{#attr.row.stTanggalAwal}"/>" nip="<s:property value="%{#attr.row.nip}"/>" jamAwal="<s:property value="%{#attr.row.jamAwal}"/>" jamAkhir="<s:property value="%{#attr.row.jamAkhir}"/>" href="javascript:;" class="item-sesuai" cssClass="item-sesuai">
                                                                                <img border="0" src="<s:url value="/pages/images/icon_multi_edit.png"/>" name="icon_sesuai">
                                                                            </a>
                                                                        </s:else>
                                                                    </s:if>
                                                            </display:column>
                                                            </s:if>
                                                            <%--<s:if test="#attr.row.cekAdmin">
                                                                <display:column media="html" title="Add Absen">
                                                                    <s:if test="#attr.row.lemburApprove">
                                                                        <s:if test="#attr.row.adaAbsen">
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a href="javascript:;"  tanggal="<s:property value="%{#attr.row.stTanggalAwal}"/>" nip="<s:property value="%{#attr.row.nip}"/>" jamAwal="<s:property value="%{#attr.row.jamAwal}"/>" jamAkhir="<s:property value="%{#attr.row.jamAkhir}"/>" href="javascript:;" class="item-add-absen" cssClass="item-add-absen">
                                                                                <img border="0" src="<s:url value="/pages/images/User_new.png"/>" name="icon_add_absen">
                                                                            </a>
                                                                        </s:else>
                                                                    </s:if>
                                                                </display:column>
                                                            </s:if>--%>
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

<div id="modal-sesuaikan" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Sesuaikan Lembur</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <s:hidden id="tanggalLembur"></s:hidden>
                    <s:hidden id="nipLembur"></s:hidden>
                    <s:hidden id="jamAwalLembur"></s:hidden>
                    <s:hidden id="jamAkhirLembur"></s:hidden>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Keterangan Sesuaikan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="keteranganSesuaikan" name=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveSesuaikan" type="button" class="btn btn-default btn-success">Sesuaikan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


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
<script>
    $('.tableLembur').on('click', '.item-edit', function() {
        var tanggal = $(this).attr('tanggal');
        var nip = $(this).attr('nip');
        var jamAwal = $(this).attr('jamAwal');
        var jamAkhir = $(this).attr('jamAkhir');
        if (confirm("apakah anda ingin merefresh data ini ?")){
            LemburAction.refreshLembur(nip,tanggal,jamAwal,jamAkhir, function (data) {
                if (data=="sukses"){
                    alert("Refresh berhasil");
                    window.location.reload();
                }else{
                    alert(data);
                }
            });
        }
    });
    $('.tableLembur').on('click', '.item-sesuai', function() {
        var tanggal = $(this).attr('tanggal');
        var nip = $(this).attr('nip');
        var jamAwal = $(this).attr('jamAwal');
        var jamAkhir = $(this).attr('jamAkhir');

        $('#tanggalLembur').val(tanggal);
        $('#nipLembur').val(nip);
        $('#jamAwalLembur').val(jamAwal);
        $('#jamAkhirLembur').val(jamAkhir);
        $('#modal-sesuaikan').modal('show');

        /*if (confirm("apakah anda ingin menyesuaikan data ini ?")){
            LemburAction.sesuaikanLembur(nip,tanggal,jamAwal,jamAkhir, function (data) {
                if (data=="sukses"){
                    alert("Penyesuaian berhasil");
                    window.location.reload();
                }else{
                    alert("Data absensi tidak ada")
                }
            });
        }*/
    });

    $('#btnSaveSesuaikan').click(function () {
        var tanggal=$('#tanggalLembur').val();
        var nip=$('#nipLembur').val();
        var jamAwal=$('#jamAwalLembur').val();
        var jamAkhir=$('#jamAkhirLembur').val();
        var keterangan=$('#keteranganSesuaikan').val();
        var data;
        if (confirm("apakah anda ingin menyesuaikan data ini ?")){
            LemburAction.sesuaikanLembur(nip,tanggal,jamAwal,jamAkhir,keterangan, function (data) {
                if (data=="sukses"){
                    alert("Penyesuaian berhasil");
                    window.location.reload();
                }else{
                    alert("Data absensi tidak ada")
                }
            });
        }
    });


    $('.tableLembur').on('click', '.item-add-absen', function() {
        var tanggal = $(this).attr('tanggal');
        var nip = $(this).attr('nip');
        var jamAwal = $(this).attr('jamAwal');
        var jamAkhir = $(this).attr('jamAkhir');
        if (confirm("apakah anda ingin menambahkan absen pada data ini ?")){
            LemburAction.addAbsensiLembur(nip,tanggal,jamAwal,jamAkhir, function (data) {
                if (data=="sukses"){
                    alert("Add absensi berhasil");
                    window.location.reload();
                }else{
                    alert("error")
                }
            });
        }
    });
    $(document).ready(function(){
        $('.R').css("display", "none");
        $('.I').css("display", "none");
        $('.M').css("display", "none");
    });
</script>
