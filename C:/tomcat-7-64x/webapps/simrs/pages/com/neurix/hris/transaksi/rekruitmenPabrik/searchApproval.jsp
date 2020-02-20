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
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenPabrikAction.js"/>'></script>

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
            Rekruitmen Pabrik
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
                        <h3 class="box-title">Search Approval Rekruitmen Pabrik</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Rekruitmen Pabrik Detail Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="rekruitmenPabrikDetailId" name="rekruitmenPabrikDetail.rekruitmenPabrikDetailId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                            <s:hidden name="rekruitmenPabrikPerson.approvalId"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>NIP </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="personId" name="rekruitmenPabrikDetail.nip" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                </table>
                                <br>

                            </div>
                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <button type="button" class="btn btn-success" id="btnApproveAll">
                                                <i class="fa fa-share-alt"></i> Approve
                                            </button>
                                        </td>
                                        <td>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_rekruitmenPabrikDetail"/>'">
                                                <i class="fa fa-repeat"></i> Reset
                                            </button>
                                        </td>

                                    </tr>
                                </table>
                            </div>


                            <center>
                                <table id="showdata" width="80%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Rekruitmen Pabrik">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultRPD" value="#session.listOfResultRekruitmenPabrikDetail" scope="request" />
                                            <display:table name="listOfResultRPD" class="tableRekruitmenPabrikDetail table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_Notifikasi.action" export="true" id="row" pagesize="20" style="font-size:10">
                                                <display:column media="html" title="<input type='checkbox' id='checkAll'>">
                                                    <input type="checkbox" id="check" name="checkApprove[]" value="<s:property value="%{#attr.row.rekruitmenPabrikDetailId}"/>" class="check">
                                                </display:column>
                                                <display:column property="rekruitmenPabrikId" sortable="true" title="Rekruitmen Pabrik Id" />
                                                <display:column property="rekruitmenPabrikDetailId" sortable="true" title="Rekruitmen Pabrik Detail Id" />
                                                <display:column property="nip" sortable="true" title="NIP"  />
                                                <display:column property="namaPegawai" sortable="true" title="Nama Pegawai"/>
                                                <display:column property="posisiLamaName" sortable="true" title="Jabatan Lama"  />
                                                <display:column property="posisiBaruName" sortable="true" title="Jabatan Baru"  />
                                                <display:column property="statusGiling" sortable="true" title="Status Giling"  />
                                                <display:column media="html" title="Approve">
                                                    <s:if test="#attr.row.rekruitmenPabrikDetailApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.nip}"/>" rpd="<s:property value="%{#attr.row.rekruitmenPabrikDetailId}"/>" class="item-edit" cssClass="item-edit">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </a>
                                                    </s:else>
                                                </display:column>
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
<div id="modal-edit-approve" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormApprove">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Rekruitmen Pabrik Detail Id : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="rekruitmenPabrikDetailIdApprove" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nipApprove" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nipNamaApprove" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Lama : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiLamaApprove" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Baru : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiBaruApprove" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" cssClass="form-control"/>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveApprove" type="button" class="btn btn-default btn-success">Approve</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<script>
    $(document).ready(function(){
        $('.tableRekruitmenPabrikDetail').on('click', '.item-edit', function(){
            var nip = $(this).attr('data');
            var rpdId = $(this).attr('rpd');
            dwr.engine.setAsync(false);
            RekruitmenPabrikAction.searchRekruitmenPabrikPersonGm(nip,rpdId,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipApprove').val(item.nip);
                    $('#rekruitmenPabrikDetailIdApprove').val(item.rekruitmenPabrikDetailId);
                    $('#nipNamaApprove').val(item.namaPegawai);
                    $('#posisiLamaApprove').val(item.posisiLama).change();
                    $('#posisiBaruApprove').val(item.posisiBaru).change();
                });
            });
            $('#modal-edit-approve').find('.modal-title').text('Approve SDM');
            $('#modal-edit-approve').modal('show');
            $('#myFormView').attr('action', 'approvePerson');
        });
        $('#btnSaveApprove').click(function(){
            var rekId = $('#rekruitmenPabrikDetailIdApprove').val();
            if (confirm('Are you sure you want to save this Record?')) {
                RekruitmenPabrikAction.approveRekruitmenPabrikPersonGm(rekId ,"gm","Y",function(listdata) {
                    $('#modal-edit-approve').modal('hide');
                    $('#myFormApprove')[0].reset();
                    location.reload()
                });
                alert('Data Successfully Updated');
            }
        });
        $("#checkAll").change(function(){
            $('input:checkbox').not(this).prop('checked', this.checked);
        });
       $('#btnApproveAll').click(function(){
           var values = new Array();
           var status ;
           $.each($("input[name='checkApprove[]']:checked"), function() {
               values.push($(this).val());
           });
           if(values.length > 0){
               dwr.engine.setAsync(false);
               $.each($("input[name='checkApprove[]']:checked"), function() {
                   RekruitmenPabrikAction.approveRekruitmenPabrikPersonGm($(this).val(),"gm","Y",function(listdata) {
                       location.reload();
                   });
               });
               alert('Data successfully saved !');
           }else{
               alert('Silahkan Centang Salah Satu Data !');
           }
       })

    });

</script>