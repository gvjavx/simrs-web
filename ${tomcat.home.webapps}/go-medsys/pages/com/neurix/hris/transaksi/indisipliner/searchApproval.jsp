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
        #tglAkhirBlokir1{z-index: 9000!important}
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

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

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

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval Indisipliner</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN44">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Indisipliner Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="indisiplinerId" name="indisipliner.indisiplinerId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                            <s:hidden name="indisiplinerPerson.approvalId"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>NIP </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="personId" name="indisipliner.nip" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                </table>
                                <br>

                            </div>
                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>
                                        </td>
                                        <td>

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_indisipliner"/>'">
                                                <i class="fa fa-repeat"></i> Reset
                                            </button>
                                        </td>

                                    </tr>
                                </table>
                            </div>


                            <center>
                                <table id="showdata" width="90%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="indisipliner">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultID" value="#session.listOfResultIndisipliner" scope="request" />
                                            <display:table name="listOfResultID" class="tableIndisipliner table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_Notifikasi.action" id="row" pagesize="20" style="font-size:10">

                                                <%--<display:column media="html" title="Closed" style="text-align:center;font-size:9">
                                                    <s:if test="#attr.row.closed">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.indisiplinerApprove">
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.indisiplinerId}"/>" nama="<s:property value="%{#attr.row.nama}"/>" href="javascript:;" class="item-closed">
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                </display:column>--%>
                                                <display:column media="html" title="Approve">
                                                    <s:if test="#attr.row.indisiplinerApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.indisiplinerId}"/>" nama="<s:property value="%{#attr.row.nama}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </a>
                                                    </s:else>
                                                </display:column>
                                                <display:column property="indisiplinerId" sortable="true" title="Id" />
                                               <display:column property="nip" sortable="true" title="NIP"  />
                                                <display:column property="nama" sortable="true" title="Nama Pegawai"  />
                                                <display:column property="divisiName" sortable="true" title="Bidang"  />
                                               <display:column property="tipeIndisipliner" sortable="true" title="Tipe Indisipliner"  />
                                               <display:column property="keteranganPelanggaran" sortable="true" title="note"  />
                                               <display:column property="stTanggalAwalBlokirAbsensi" sortable="true" title="Tanggal Dari"  />
                                               <display:column property="stTanggalAkhirBlokirAbsensi" sortable="true" title="Tanggal Selesai"  />
                                               <display:column property="dampak" sortable="true" title="Dampak"  />
                                                <display:setProperty name="paging.banner.item_name">Approval</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">Approval</display:setProperty>
                                                <display:setProperty name="export.excel.filename">Approval.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">Approval.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">Approval.pdf</display:setProperty>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>
                    </form>
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
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Indisipliner</h4>
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
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="Divisi1" name="indisipliner.divisiId"
                                      listKey="departmentId" listValue="departmentName" disabled="true" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
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
                        <label class="control-label col-sm-3" >Tanggal : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tanggal1" name="nip">
                            </div>
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
                                <input type="text" class="form-control nip" id="tglAkhirBlokir1" name="nip">
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
                </form>
                <table style="width: 100%;" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a id="btnNotApprove" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
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
                <h4 class="modal-title">Keterangan Not Approve Indisipliner</h4>
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
            </div>
            <div class="modal-footer">
                <a id="btnNotApprove1" type="button" class="btn btn-default btn-danger"><i class="fa fa-check"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<div id="modal-closed" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Closed Indisipliner</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormClosed">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Indisipliner ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="IndisiplinerId4" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="Nip4" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="Nama4" name="nip">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="Unit4" name="indisipliner.branchId" required="true"
                                      listKey="branchId" listValue="branchName" readonly="true" headerKey="" disabled="true" headerValue="[Select one]" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="Divisi4" name="indisipliner.divisiId"
                                      listKey="departmentId" listValue="departmentName" disabled="true" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="Posisi4" name="indisipliner.posisiId" required="false" readonly="true"
                                      listKey="stPositionId" disabled="true" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tipe Indisipliner : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'SP0':'Teguran','SP1':'SP1','SP2':'SP2','SP3':'SP3'}" id="tipeIndisipliner4" name="indisipliner.tipeIndisipliner" readonly="true"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tanggal4" name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Masa Berlaku : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAwalPantau4" name="nip">
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAkhirPantau4" name="nip">
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
                                <input readonly type="text" class="form-control nip" id="tglAwalBlokir4" name="nip">
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAkhirBlokir4" name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan Pelanggaran : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="keteranganPelanggaran4" readonly name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dampak : </label>
                        <div class="col-sm-8">
                            <textarea id="dampak4" readonly rows="4" class="form-control nip" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan Closed : </label>
                        <div class="col-sm-8">
                            <textarea id="keteranganClosed4" rows="4" class="form-control" ></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnClosed" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i>Closed</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var id = document.getElementById("IndisiplinerId1").value;
        var nipid=document.getElementById("Nip1").value;
        var tanggal = document.getElementById("tglAkhirBlokir1").value;
        if(tanggal!=("")){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);
                IndisiplinerAction.saveApprove(id, "Y",who,nipid,tanggal, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        }else{
            alert("tanggal akhir blokir belum diisi.");
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
                // var myDate = new Date(item.tanggalAwalPantau);
                // var myDate1 = new Date(item.tanggalAkhirPantau);
                // var myDate2 = new Date(item.tanggalAwalBlokirAbsensi);
                // var myDate3 = new Date(item.tanggalAkhirBlokirAbsensi);
                // var myDate4 = new Date(item.tanggal);
                $('#Nama321').val(item.nama);
                $('#Nip1').val(item.nip);
                // $('#tglAwalPantau1').val((myDate.getDate()) +'-'+ ("0" + (myDate.getMonth() + 1)).slice(-2) +'-'+myDate.getFullYear());
                // $('#tglAkhirPantau1').val((myDate1.getDate()) +'-'+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +'-'+myDate1.getFullYear());
                // $('#tglAwalBlokir1').val((myDate2.getDate()) +'-'+ ("0" + (myDate2.getMonth() + 1)).slice(-2) +'-'+myDate2.getFullYear());
                // $('#tglAkhirBlokir1').val((myDate3.getDate()) +'-'+ ("0" + (myDate3.getMonth() + 1)).slice(-2) +'-'+myDate3.getFullYear());
                // $('#tanggal1').val((myDate4.getDate()) +'-'+ ("0" + (myDate4.getMonth() + 1)).slice(-2) +'-'+myDate4.getFullYear());
                $('#tglAwalPantau1').val(item.stTanggalAwalPantau);
                $('#tglAkhirPantau1').val(item.stTanggalAkhirPantau);
                $('#tglAwalBlokir1').val(item.stTanggalAwalBlokirAbsensi);
                $('#tglAkhirBlokir1').val(item.stTanggalAkhirBlokirAbsensi);
                $('#tanggal1').val(item.stTanggal);
                $('#Unit321').val(item.branchId).change();
                $('#Divisi1').val(item.divisiId).change();
                $('#Posisi1').val(item.positionId).change();
                $('#tipeIndisipliner1').val(item.tipeIndisipliner).change();
                $('#keteranganPelanggaran1').val(item.keteranganPelanggaran);
                $('#dampak1').val(item.dampak);
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Indisipliner');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('#btnNotApprove').on('click', function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Indisipliner');
        $('#modal-edit-not-approve').modal('show');
    });
    $('#btnNotApprove1').click(function(){
        var who = "atasan";
        var IndisiplinerId = document.getElementById("IndisiplinerId1").value;
        var note = document.getElementById("keterangan").value;
        var nipid=document.getElementById("Nip1").value;
        if(note != ''){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                IndisiplinerAction.saveApprove(IndisiplinerId, note, who,nipid, function(listdata) {
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

    $('.tableIndisipliner').on('click', '.item-closed', function() {
        var IndisiplinerId = $(this).val().replace(/\n|\r/g, "");
        var IndisiplinerId = $(this).attr('data');
        var nip = $(this).attr('nip');
        $('#IndisiplinerId4').val(IndisiplinerId);
        IndisiplinerAction.approveAtasan(IndisiplinerId, function(listdata) {
            $.each(listdata, function (i, item) {
                // var myDate = new Date(item.tanggalAwalPantau);
                // var myDate1 = new Date(item.tanggalAkhirPantau);
                // var myDate2 = new Date(item.tanggalAwalBlokirAbsensi);
                // var myDate3 = new Date(item.tanggalAkhirBlokirAbsensi);
                // var myDate4 = new Date(item.tanggal);
                $('#Nama4').val(item.nama);
                $('#Nip4').val(item.nip);
                // $('#tglAwalPantau4').val((myDate.getDate()) +'-'+ ("0" + (myDate.getMonth() + 1)).slice(-2) +'-'+myDate.getFullYear());
                // $('#tglAkhirPantau4').val((myDate1.getDate()) +'-'+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +'-'+myDate1.getFullYear());
                // $('#tglAwalBlokir4').val((myDate2.getDate()) +'-'+ ("0" + (myDate2.getMonth() + 1)).slice(-2) +'-'+myDate2.getFullYear());
                // $('#tglAkhirBlokir4').val((myDate3.getDate()) +'-'+ ("0" + (myDate3.getMonth() + 1)).slice(-2) +'-'+myDate3.getFullYear());
                // $('#tanggal4').val((myDate4.getDate()) +'-'+ ("0" + (myDate4.getMonth() + 1)).slice(-2) +'-'+myDate4.getFullYear());
                $('#tglAwalPantau4').val(item.stTanggalAwalPantau);
                $('#tglAkhirPantau4').val(item.stTanggalAkhirPantau);
                $('#tglAwalBlokir4').val(item.stTanggalAwalBlokirAbsensi);
                $('#tglAkhirBlokir4').val(item.stTanggalAkhirBlokirAbsensi);
                $('#tanggal4').val(item.stTanggal);
                $('#Unit4').val(item.branchId).change();
                $('#Divisi4').val(item.divisiId).change();
                $('#Posisi4').val(item.positionId).change();
                $('#tipeIndisipliner4').val(item.tipeIndisipliner).change();
                $('#keteranganPelanggaran4').val(item.keteranganPelanggaran);
                $('#dampak4').val(item.dampak);
            });
        });
        $('#modal-closed').find('.modal-title').text('Closed Indisipliner');
        $('#modal-closed').modal('show');
        $('#myFormClosed').attr('action', 'closed');
    });
    $('#btnClosed').click(function(){
        var who = $('#myFormClosed').attr('action');
        var id = document.getElementById("IndisiplinerId4").value;
        var nipid=document.getElementById("Nip4").value;
        var keteranganClosed=document.getElementById("keteranganClosed4").value;
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
    $(document).ready(function(){
        $('#tglAkhirBlokir1').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>


