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
                                        <s:select list="#{'LN':'Luar Negeri'}" id="flag" name="sppd.dinas"
                                                  headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Branch :</small></label>
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

                            <tr>
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
                            <table id="showdata" width="70%">
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
                                                    </s:url>
                                                    <s:a href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" >
                                                    </s:a>
                                                    <%--<a href="printReportSppd?id=<s:property value="%{#attr.row.sppdId}"/>">
                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" >
                                                    </a>--%>
                                                </s:if>
                                                <s:else>
                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" >--%>
                                                </s:else>
                                            </display:column>

                                            <display:column media="html" title="Closed">
                                                <s:if test="#attr.row.sppdClosed">
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" >
                                                </s:if>
                                                <s:else>
                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" >--%>
                                                </s:else>
                                            </display:column>

                                            <display:column media="html" title="Biaya">
                                                <s:if test="#attr.row.sppdClosed">
                                                    <s:url var="urlEdit" namespace="/sppd" action="edit_sppd" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.sppdId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if><s:else>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_edit">--%>
                                            </s:else>
                                            </display:column>

                                            <display:column media="html" style="%{attr.row.sppdId}" title="Detail Person" >
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.sppdId}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                    <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                </a>
                                            </display:column>

                                            <%--<display:column media="html" title="Approve SDM">
                                                <s:if test="#attr.row.sppdApproveStatus">
                                                    <s:if test="#attr.row.sppdApprove">
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.sppdId}"/>" href="javascript:;" class="item-editSdm" cssClass="item-edit">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </a>
                                                    </s:if>
                                                </s:if>
                                            </display:column>--%>

                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.sppdApproveSdm">
                                                    <s:a action="edit_sppd.action">
                                                        <s:param name="id"><s:property value="#attr.row.sppdId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column property="sppdId" sortable="true" title="Sppd ID"  />
                                            <display:column property="dinas" sortable="true" title="Dinas" />
                                            <%--<display:column property="divisiName" sortable="true" title="Divisi" />
                                            <display:column property="personNip" sortable="true" title="NIP" />
                                            <display:column property="personName" sortable="true" title="Nama" />
                                            <display:column property="tanggalSppdBerangkat" sortable="true" title="Tanggal SPPD"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>--%>
                                            <display:column property="noSurat" sortable="true" title="No Surat" />

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
    <div class="modal-dialog " style="width:1400px;">

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
                        <div class="col-sm-8">
                            <s:select list="#{'LN':'Luar Negeri'}" id="dinas1" name="sppd.dinas" disabled="true"
                                      headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keperluan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" readonly class="form-control" id="keperluan1" name="nip"></textarea>

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Berangkat : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalBerangkat1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tanggal Kembali : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalKembali1" name="nip">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Dari: </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatDari1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tujuan : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tujuan1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatVia1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Kembali Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="kembaliVia1" name="nip">
                        </div>
                    </div>

                </form>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
</html>

<script>
    $(document).ready(function() {

        $('#btnApprove').click(function(){
            var who = $('#myForm').attr('action');
            var nip = document.getElementById("sppdId1").value;
            //alert(who);
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                SppdAction.saveApprove(nip, "Y",who, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                    loadPerson('', 'Y');
                });
            }
        });

        $('#btnNotApprove').click(function(){
            var who = $('#myForm').attr('action');
            var nip = document.getElementById("sppdId1").value;
            var note = document.getElementById("notApprove").value;
            //alert(note);
            if(note != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);

                    SppdAction.saveApprove(nip, note, who, function(listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        location.reload();
                        loadPerson('', 'Y');
                    });
                }
            }else{
                alert('Silahkan isi keterangan Not Approve !');
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


        $('.tableSppd').on('click', '.item-edit', function(){
            var sppdId = $(this).val().replace(/\n|\r/g, "");
            var sppdId = $(this).attr('data');
            $('#sppdId1').val(sppdId);
            //alert(sppdId);
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchId).change();
                    $('#positionId1').val(item.positionId).change();
                    $('#dinas1').val(item.dinas).change();
                    $('#divisiId1').val(item.divisiId).change();
                    $('#keperluan1').val(item.tugasSppd);
                    $('#berangkatDari1').val(item.berangkatDari);
                    $('#tujuan1').val(item.tujuanKe);
                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.pulangVia);
                    $('#notApprove').val(item.notApprovalNote);
                    $('#approveAtasan').val(item.approvalName);
                    $('#approveAtasanId').val(item.approvalId);
                    if(item.sppdApproveStatus == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                        $("#sppdPersonTable").find("input,button,textarea,select").attr("disabled", "disabled");
                        //location.reload();
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

        $('.tableSppd').on('click', '.item-editSdm', function(){
            var sppdId = $(this).val().replace(/\n|\r/g, "");
            var sppdId = $(this).attr('data');
            $('#sppdId1').val(sppdId);
            //alert(sppdId);
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                    $('#dinas1').val(item.dinas).change();
                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchId).change();
                    $('#positionId1').val(item.positionId).change();
                    $('#divisiId1').val(item.divisiId).change();
                    $('#keperluan1').val(item.tugasSppd);
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
                        //location.reload();
                        loadPerson(sppdId, "Y");
                    }else{
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                        //location.reload();
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
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();
        //alert(branch);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {

            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #76a0ef'>No</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef'>Name</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Branch</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Position</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Status</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Approval Date</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Note For Not Approval</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Approval SDM Name</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Approval SDM Date</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Note For Not Approval SDM</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Nip Pengganti</th>"+
                    "<th style='text-align: center; background-color:  #76a0ef''>Nama Pengganti</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + branch + '</td>' +
                        '<td align="center">' + divisi + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + '<input readonly type="text" value="'+item.pejabatSementara+'" name="anamaPengganti'+i+'" id="anamaPengganti'+i+'" class="form-control">' + '</td>' +
                        '<td align="center">' + '<input  value="'+item.pejabatSementaraNama+'" type="text" onchange="updatePengganti('+'\''+item.personId+'\''+',\''+i+'\')" class="form-control namaPengganti" id="namaPengganti'+i+'" name="namaPersonPengganti">' + '</td>' +

                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);

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

    function updatePengganti(nip, id){
        var pengganti = document.getElementById("anamaPengganti"+id).value;
        SppdAction.searchAnggotaAdd(nip, pengganti, function(listdata) {
            //alert('berhasil');
        });
    }

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

