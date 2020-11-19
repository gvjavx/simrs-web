<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinAction.js"/>'></script>
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

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Lembur
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval Lembur</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN77">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>
                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label>Lembur Id </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="lemburId" name="lembur.lemburId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                                    <s:hidden name="lemburPerson.approvalId"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>NIP </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="personId" name="lembur.nip" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                                    <script type='text/javascript'>
                                                        var functions, mapped;
                                                        $('#personId').typeahead({
                                                            minLength: 1,
                                                            source: function (query, process) {
                                                                functions = [];
                                                                mapped = {};
                                                                var data = [];
                                                                dwr.engine.setAsync(false);
                                                                MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                                    data = listdata;
                                                                });
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
                                                                var namaAlat = selectedObj.id;
                                                                document.getElementById("personId").value = selectedObj.id;
                                                                return namaAlat;
                                                            }
                                                        });
                                                    </script>
                                                </td>
                                            </tr>
                                        </table>
                                        <br><br>
                                        <table>
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                               onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>

                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="viewNotifikasi_notifikasi.action?tipeNotif=TN77"/>'">
                                                        <i class="fa fa-repeat"></i> Reset
                                                    </button>
                                                </td>

                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br><br>

                            <center>
                                <table id="showdata" width="80%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Medical Record">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfLembur" value="#session.listOfResultLembur" scope="request" />
                                            <display:table name="listOfLembur" class="tableLembur table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_lembur.action" id="row" pagesize="90" style="font-size:10">
                                                <display:column media="html" title="<center>Approve Atasan</center>" style="text-align:center">
                                                    <s:if test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.lemburApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.lemburId}"/>" nama="<s:property value="%{#attr.row.pegawaiName}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </a>
                                                    </s:else>
                                                </display:column>
                                                <display:column property="lemburId" sortable="true" title="Lembur Id" />
                                                <display:column property="tipeLembur" sortable="true" title="Tipe Lembur"  />
                                                <display:column property="nip" sortable="true" title="NIP"  />
                                                <display:column property="pegawaiName" sortable="true" title="Nama"  />
                                                <display:column property="positionName" sortable="true" title="Jabatan"  />
                                                <display:column property="stTanggalAwal" sortable="true" title="Tanggal"  />
                                                <display:column property="jamAwal" sortable="true" title="<center>Jam Awal</center>" style="text-align:center" />
                                                <display:column property="jamAkhir" sortable="true" title="<center>Jam Akhir</center>" style="text-align:center" />
                                                <display:column property="lamaJam" sortable="true" title="<center>Lama</center>" style="text-align:center" />
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
                <h4 class="modal-title">Approve Lembur</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Lembur ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="lemburId2" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="Nip2" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="Name2" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="position2" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" readonly="true" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisi2"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Golongan : </label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#initComboTipe.listComboGolongan" id="golongan2" name="" disabled="true"
                                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tipe Pegawai : </label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                            <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai21" name="" listKey="tipePegawaiId" listValue="tipePegawaiName"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true"/>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-3" >Status Giling : </label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<s:select list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}" id="statusGiling23" name=""--%>
                                      <%--headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tipe Lembur : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'Non Rutin':'Non Rutin','Rutin':'Rutin'}" id="tipeLembur2" name=""
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group M R I">
                        <label class="control-label col-sm-3" >Tanggal : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tglAwal2" name="" cssClass="form-control pull-right"
                                             required="false" cssStyle="" size="7"/>
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tglAkhir2" name="" size="7" cssClass="form-control pull-right"
                                             required="false" cssStyle=""/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group I">
                        <label class="control-label col-sm-3" >Jam : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <s:textfield id="jamAwal2" name="" size="7" cssClass="form-control pull-right"
                                             required="false" cssStyle=""/>
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <s:textfield id="jamAkhir2" name="" size="7" cssClass="form-control pull-right"
                                             required="false" cssStyle=""/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group M R">
                        <label class="control-label col-sm-3" >Lama ( jam ) : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <s:textfield id="lamaJam2" name="" size="12" cssClass="form-control pull-right"
                                             required="false" cssStyle=""/>
                                <div class="input-group-addon">
                                    Jam
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group I">
                        <label class="control-label col-sm-3" >Lama Tiap Hari : </label>
                        <div class="col-sm-8">
                            <s:textfield  id="lamaTiapHari2" name="" readonly="true" required="false" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <s:textarea rows="4" id="keterangan2" name="" required="false" cssClass="form-control" readonly="true"/>
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
<div id="modal-edit-not-approve" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Keterangan Not Approve Lembur</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeterangan">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="keteranganNotApprove" name="nip"></textarea>
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

<script type="application/javascript">
    var tipeLembur;
    $(document).ready(function() {
        $('#tglAwal2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tglAkhir2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var lemburId = document.getElementById("lemburId2").value;
        var nip=document.getElementById("Nip2").value;
        var tglAwal=document.getElementById("tglAwal2").value;
        var tglAkhir=document.getElementById("tglAkhir2").value;
        var lama;
        var jamAwal4=$('#jamAwal2').val();
        var jamAkhir4=$('#jamAkhir2').val();
        if(tipeLembur=="Non Rutin"){
            lama=document.getElementById("lamaTiapHari2").value;
        } else if (tipeLembur=="Rutin"){
            lama=document.getElementById("lamaJam2").value;
        }
        var lamaLembur=0;
        dwr.engine.setAsync(false);
        LemburAction.cekLembur(nip,tglAwal,function (data) {
            lamaLembur=lama+data;
        });
        if (lamaLembur>3){
            if (confirm('Lembur ini sudah lebih dari 3 Jam '+'( '+lamaLembur+' Jam ) , Lanjutkan ? ')) {
                if (confirm('Are you sure you want to save this Record?')) {
                    LemburAction.saveApprove(lemburId, "Y",who,nip,tglAwal,tglAkhir,lama,"",jamAwal4,jamAkhir4, function(listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        location.reload();
                    });
                }
            }
        }else{
            if (confirm('Are you sure you want to save this Record?')) {
                LemburAction.saveApprove(lemburId, "Y",who,nip,tglAwal,tglAkhir,lama,"",jamAwal4,jamAkhir4, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        }

    });

    $('.tableLembur').on('click', '.item-edit', function(){
        $('#modal-edit').find('.modal-title').text('Approve Lembur Atasan');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('.tableLembur').on('click', '.item-edit', function() {
        var LemburId = $(this).attr('data');
        var nama = $(this).attr('nama');
        $('#lemburId2').val(LemburId);
        LemburAction.approveAtasan(LemburId, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#Nip2').val(item.nip);
                $('#Name2').val(item.pegawaiName);
                $('#tglAwal2').val(item.stTanggalAwal);
                $('#tglAkhir2').val(item.stTanggalAkhir);
                $('#jamAwal2').val(item.jamAwal);
                $('#jamAkhir2').val(item.jamAkhir);
                $('#lamaJam2').val(item.lamaJam);
                $('#keterangan2').val(item.keterangan);
                $('#lamaTiapHari2').val(item.lamaJam);
                $('#position2').val(item.positionId).change();
                $('#divisi2').val(item.divisiId).change();
                $('#golongan2').val(item.golonganId).change();
                $('#tipePegawai21').val(item.tipePegawaiId).change();
                $('#statusGiling23').val(item.statusGiling).change();
                $('#tipeLembur2').val(item.tipeLembur).change();
                if (item.lemburApproveStatus == true) {
                    $('#btnApprove').hide();
                } else {
                    $('#btnApprove').show();
                }
                tipeLembur = item.tipeLembur;
                if (tipeLembur=="Non Rutin"){
                    $('.R').css("display", "none");
                    $('.I').removeAttr("style");
                }
                else if (tipeLembur=="Rutin"){
                    $('.I').css("display", "none");
                    $('.R').removeAttr("style");
                }
                else {
                    $('.R').css("display", "none");
                    $('.I').css("display", "none");
                }
            });
        });
    });
    $('#btnNotApprove').on('click', function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Lembur');
        $('#modal-edit-not-approve').modal('show');
    });

    $('#btnNotApprove1').click(function(){
        var who = $('#myForm').attr('action');
        var lemburId = document.getElementById("lemburId2").value;
        var nip=document.getElementById("Nip2").value;
        var tglAwal=document.getElementById("tglAwal2").value;
        var tglAkhir=document.getElementById("tglAkhir2").value;
        var keterangan =document.getElementById("keteranganNotApprove").value;
        var jamawal2=$('#jamAwal2').val();
        var jamakhir2=$('#jamAkhir2').val();
        var lama;
        if(tipeLembur=="I"){
            lama=document.getElementById("lamaTiapHari2").value;
        } else if (tipeLembur=="R"){
            lama=document.getElementById("lamaJam2").value;
        }
        if (confirm('Are you sure you want to save this Record?')) {
            dwr.engine.setAsync(false);
            LemburAction.saveApprove(lemburId, "N",who,nip,tglAwal,tglAkhir,lama,keterangan,jamawal2,jamakhir2, function(listdata) {
                alert('Data Successfully Updated');
                $('#modal-edit').modal('hide');
                $('#myForm')[0].reset();
                location.reload();
            });
        }
    });

    $(document).ready(function(){
        $('.R').css("display", "none");
        $('.I').css("display", "none");
        $('.M').css("display", "none");

        $('body').click(function(){
            var jamawal=$('#jamAwal2').val();
            var jamakhir=$('#jamAkhir2').val();
            if(jamawal!==""&&jamakhir!==""){
                onChange();
            }
        });
        function onChange() {
            var nip44,jamAwal44,jamAkhir44,tglAwal,tglAkhir;
            nip44=$('#Nip2').val();
            jamAwal44=$('#jamAwal2').val();
            jamAkhir44=$('#jamAkhir2').val();
            tglAwal=document.getElementById('tglAwal2').value;
            tglAkhir=document.getElementById('tglAkhir2').value;

            if (nip44!=""&&jamAwal44!=""&&jamAkhir44!=""&&tglAwal!=""&&tglAkhir!=""){
                LemburAction.calcJamLembur(nip44,tglAwal,tglAkhir,jamAwal44,jamAkhir44, function (data) {
                    if (data != "") {
                        $('#lamaTiapHari2').val(data);
                    }
                });
            }
        }
        $('#jamAwal2').timepicker();
        $('#jamAkhir2').timepicker();
    });
</script>


