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
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
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
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };

        function cancelBtn2() {
            $('#view_dialog_function2').dialog('close');
        };

        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });

        function showLoadingDialog(){
            $('#myModalLoading').modal('show');
        }

        function showAlert(){
            var verif = document.getElementById('verif').value;
            var erVerif = document.getElementById('erVerif').value;
            if(verif !=  ""){
                document.getElementById('succesAlert').style.display = 'block';
                var sc = document.getElementById('succesAlert').value;
                if ( sc != ""){
                    sc = "";
                }
                $("#succesAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("succesAlert").slideUp(500);
                });
            }else if(erVerif != "") {
                document.getElementById('errorAlert').style.display = 'block';
                erVerif = null;
                $("#errorAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("errorAlert").slideUp(500);
                });
            }
        }

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
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

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Sppd ID : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="sppdId1" name="nip">
                            <input type="text" class="form-control nip" id="personId1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="nama1" readonly name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="divisiId1">Unit :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="branchId1" readonly name="nip">
                            <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="branchText1"  readonly="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="divisiId1">Divisi :</label>
                        <div class="col-sm-8">
                            <%--<s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="divisiText1"  readonly="true" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />--%>
                                <input type="text" class="form-control nip" id="divisiId1" readonly name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="divisiId1">Jabatan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="positionId1" readonly name="nip">
                            <%--<s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="sppdPerson.positionId" disabled="true" readonly="true"
                            listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dinas : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="dinas1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Dari: </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="berangkatDari1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tujuan : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="tujuan1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Berangkat: </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="tanggalBerangkat" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Kembali: </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="tanggalKembali" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Via : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="berangkatVia1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Kembali Via : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="kembaliVia1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Note Transportasi : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="noteTransportasi"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Penginapan : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="penginapan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama Pengganti: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="namaPengganti1"  name="nip">
                            <input style="display: none" type="text" class="form-control nip" id="nip1"  name="nip">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;

                            $('#namaPengganti1').typeahead({
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
                                    document.getElementById("nip1").value = selectedObj.id;

                                    $('#positionIdPengganti1').val(selectedObj.positionId).change();
                                    $('#branchIdPengganti1').val(selectedObj.branchId).change();
                                    $('#divisiIdPengganti1').val(selectedObj.divisiId).change();
                                    return namaAlat;
                                }
                            });

                        </script>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="divisiId1">Branch :</label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranchPengganti" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranchPengganti.listOfComboBranch" id="branchIdPengganti1" name="branchText" disabled="true" readonly="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="divisiId1">Divisi :</label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisiPengganti" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisiPengganti.listComboDepartment" id="divisiIdPengganti1" name="divisiText" disabled="true" readonly="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="divisiId1">Position :</label>
                        <div class="col-sm-8">
                            <s:action id="comboPositionPengganti" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPositionPengganti.listOfComboPosition" id="positionIdPengganti1" name="sppdPerson.positionId" disabled="true" readonly="true"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Note For Not Approve : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="notApprove"></textarea>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a id="btnNotApprove" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
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
            Sppd
            <small>e-HEALTH</small>
        </h1>
        <%--<ol class="breadcrumb">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
        <%--<li class="active">Here</li>--%>
        <%--</ol>--%>
    </section>


    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval SPPD</h3>
                    </div>

                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="sppdForm" method="post"  theme="simple" namespace="/notifikasi" action="viewNotifikasiSppd_notifikasi.action" cssClass="well form-horizontal">

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
                                                    <s:select list="#{'LN':'Luar Negeri'}" id="flag" name="sppd.dinas" disabled="true"
                                                              headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />
                                                </table>

                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Keperluan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea rows="3" id="keperluan" name="sppd.tugasSppd" required="false" readonly="true" cssClass="form-control"/>
                                                </table>

                                            </td>
                                        </tr>



                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No Doc SPPD :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield  id="sppdName" name="sppd.noSurat" required="false" readonly="true" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <%--<tr>
                                            <td>
                                                <label class="control-label"><small>Tanggal Berangkat :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield readonly="true" id="tanggalBerangkat" name="sppd.stTanggalSppdBerangkat" cssClass="form-control pull-right"
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
                                                        <s:textfield readonly="true" id="tanggalKembali" name="sppd.stTanggalSppdKembali" cssClass="form-control pull-right"
                                                                     required="false"  cssStyle=""/>
                                                    </div>
                                                </table>
                                            </td>
                                        </tr>--%>


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
                                                <%--<td>
                                                    <a href="add_sppd.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add SPPD</a>
                                                </td>--%>
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
                                        <table id="showdata" width="60%">
                                            <tr>
                                                <td align="center">
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                               height="500" width="900" autoOpen="false"
                                                               title="Medical Record">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>

                                                    <s:set name="listOfResult" value="#session.listOfResultPerson" scope="request" />
                                                    <display:table name="listOfResult" class="table tableSppd table-condensed table-striped table-hover"
                                                                   id="row" pagesize="20" style="font-size:10">

                                                        <display:column media="html" title="Approve" style="text-align:center;font-size:9">
                                                            <a href="javascript:;" sppdId="<s:property value="%{#attr.row.sppdId}"/>" data="<s:property value="%{#attr.row.personId}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                                <s:if test="#attr.row.sppdApprove">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:else>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                </s:else>
                                                            </a>
                                                        </display:column>

                                                        <display:column property="personId" sortable="true" title="Person Id" />
                                                        <display:column property="personName" sortable="true" title="Person Name"  />
                                                        <display:column property="branchName" sortable="true" title="Branch"  />
                                                        <display:column property="divisiName" sortable="true" title="Divisi"  />
                                                        <display:column property="positionName" sortable="true" title="Position"  />
                                                        <display:column media="html" title="Status Approve Atasan" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.sppdApprove">
                                                                <s:if test="#attr.row.sppdApproveStatus">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                                </s:if>
                                                                <s:else>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                                                </s:else>
                                                            </s:if>
                                                        </display:column>
                                                        <%--<display:column media="html" title="Status Approve SDM" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.sppdApproveSdm">
                                                                <s:if test="#attr.row.sppdApproveSdmStatus">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                                </s:if>
                                                                <s:else>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                                                </s:else>
                                                            </s:if>
                                                        </display:column>--%>
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

<script type="application/javascript">
    var branch = '';
    var divisi = '';
    var tmp_data = new Array();
    var namaMt = [];

    $(document).ready(function() {
        $('#btnApprove').click(function(){
            var who = $('#myForm').attr('action');
            var nip = document.getElementById("personId1").value;
            var noteTransportasi = document.getElementById("noteTransportasi").value;
            var sppdId = document.getElementById("sppdId1").value;
            var nipTmp = '' ;

            var nipPengganti = document.getElementById("namaPengganti1").value;
            var nipPengganti1 = document.getElementById("nip1").value;

            if(nipPengganti != ''){
                nipTmp = nipPengganti1;
            }

            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                SppdAction.saveApprove(sppdId, nip, "Y",who,nipTmp, noteTransportasi, "", "", "", function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                    //loadPerson('', 'Y');
                });
            }
        });

        $('#btnNotApprove').click(function(){
            var who = $('#myForm').attr('action');
            var nip = document.getElementById("personId1").value;
            var note = document.getElementById("notApprove").value;
            var sppdId = document.getElementById("sppdId1").value;
            var noteTransportasi = document.getElementById("noteTransportasi").value;
            //alert(note);
            if(note != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);

                    SppdAction.saveApprove(sppdId, nip, note, who,"", noteTransportasi, "", "", "", function(listdata) {
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

        $('.tableSppd').on('click', '.item-edit', function(){
            var personId = $(this).attr('data');
            var sppdId = $(this).attr('sppdId');
            $('#personId1').val(personId);
            SppdAction.searchAnggota2(sppdId, personId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchName);
                    $('#positionId1').val(item.positionName);
                    $('#divisiId1').val(item.divisiName);
                    $('#noteTransportasi').val(item.noteAtasanTransport);
                    $('#penginapan').val(item.penginapan);
                    $('#sppdId1').val(item.sppdId).change();
                    $('#personId1').val(item.personId).change();

                    $('#dinas1').val(item.dinas);
                    $('#berangkatDari1').val(item.berangkatDariNama);
                    $('#tujuan1').val(item.tujuanKeNama);

                    var myDate = new Date(item.tanggalBerangkat);
                    var myDate1 = new Date(item.tanggalKembali);
                    $('#tanggalBerangkat').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    $('#tanggalKembali').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.kembaliVia);
                    $('#notApprove').val(item.notApprovalNote);
                    $('#approveAtasan').val(item.approvalName);
                    $('#approveAtasanId').val(item.approvalId);

                    branch = item.branchId;
                    divisi = item.divisiId ;

                    if(item.sppdApprove == true){
                        $('#noteTransportasi').attr("readonly", true);
                        $('#namaPengganti1').val(item.pejabatSementaraNama);
                        $('#branchIdPengganti1').val(item.pejabatSementaraBranch).change;
                        $('#divisiIdPengganti1').val(item.pejabatSementaraDivisi);
                        $('#positionIdPengganti1').val(item.pejabatSementaraPosition);
                        $('#notApprove').val(item.notApprovalNote);
                        $('#notApprove').attr("disabled", true);
                        $('#namaPengganti1').attr("disabled", true);
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                        $("#sppdPersonTable").find("input,button,textarea,select").attr("disabled", "disabled");
                    }else{
                        $('#namaPengganti1').attr("disabled", false);
                        $('#notApprove').attr("disabled", false);
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                        $("#myForm :input").attr("disabled", false);
                    }
                });
            });
            $('#modal-edit').find('.modal-title').text('Approve SPPD');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'atasan');
        });

    });

</script>
