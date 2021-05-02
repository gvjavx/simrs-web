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
            window.location.href="<s:url action='initForm_smkJabatan'/>";
        }

        $.subscribe('beforeProcessSave1', function (event, data) {
            var unit        = document.getElementById("branchId").value;
            var divisi      = document.getElementById("divisiId").value;
            var jabatan     = document.getElementById("positionId").value;
            var tipeAspek   = document.getElementById("tipeAspekId").value;

            if (unit != '' && jabatan != '' && tipeAspek != '') {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }


            } else {

                event.originalEvent.options.submit = false;

                var msg = "";

                if (unit == '') {
                    msg += 'Field <strong> Unit</strong> is required.' + '<br/>';
                }


                if (jabatan == '') {
                    msg += 'Field <strong>Jabatan</strong> is required.' + '<br/>';
                }

                if (tipeAspek == '') {
                    msg += 'Field <strong>Tipe Aspek</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SmkJabatanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:if test="isAddOrEdit()">
                <s:if test="isAdd()">
                    Add SMK Jabatan
                </s:if>
                <s:else>
                    Edit SMK Jabatan
                </s:else>
            </s:if>
            <s:elseif test="isDelete()">
                Delete SMK Jabatan
            </s:elseif>
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center" class="someclass">
            <tr>
                <s:if test="isAddOrEdit() || isDelete()">
                    <s:url id="urlProcess" namespace="/smkJabatan" action="save_smkJabatan" includeContext="false"/>
                </s:if>
                <s:else>
                    <s:url id="urlProcess" namespace="/smkJabatan" action="search_smkJabatan" includeContext="false"/>
                </s:else>
                <td align="center">
                    <s:form id="smkPegawaiBForm" method="post"  theme="simple" action="%{urlProcess}" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden id="add" name="add"/>
                        <s:hidden name="delete"/>

                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >
                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>SMK Jabatan Id:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="jabatanSmkId" name="smkJabatan.jabatanSmkId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><savesmall>Unit :</savesmall></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:if test="isAddOrEdit()">
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" onchange="listPosisi()"
                                                      name="smkJabatan.branchId" listKey="branchId" listValue="branchName"
                                                      headerKey="" headerValue="[Pilih Unit]" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                      name="smkJabatan.branchId" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:elseif>
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
                                        <s:if test="isAddOrEdit()">
                                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smkJabatan.divisiId" onchange="listPosisi()"
                                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Pilih Divisi]" cssClass="form-control" />
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smkJabatan.divisiId" disabled="true"
                                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:if test="isAddOrEdit()">
                                            <select id="positionId" name="smkJabatan.positionId" onchange="cekJabatan(this)" class="form-control"></select>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <select id="positionId" name="smkJabatan.positionId" onchange="cekJabatan(this)" class="form-control" readonly="true"></select>
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Aspek :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipeAspek" namespace="/tipeAspek" name="initComboTipeAspek_tipeAspek"/>
                                        <s:if test="isAddOrEdit()">
                                            <s:select list="#initComboTipeAspek.tipeAspekList" id="tipeAspekId"
                                                      name="smkJabatan.tipeAspekId" onchange="cekAspek(this)"
                                                      listKey="tipeAspekId" listValue="tipeAspekName" headerKey="" headerValue="[Pilih Tipe Aspek]" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:select list="#initComboTipeAspek.tipeAspekList" id="tipeAspekId"
                                                      name="smkJabatan.tipeAspekId" disabled="true"
                                                      listKey="tipeAspekId" listValue="tipeAspekName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>

                            <tr class="totalBobots" >
                                <td >
                                    <label class="control-label"><small>Total Bobot :</small></label>
                                </td>
                                <td >
                                    <table>
                                        <s:if test="isAddOrEdit()">
                                            <s:textfield  id="totalBobot" name="smkJabatan.bobot" required="false" readonly="true" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:textfield  id="totalBobot" name="smkJabatan.bobot" required="false" readonly="true" cssClass="form-control"/>
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>


                        </table>

                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td class="tombol">
                                        <center>
                                            <s:if test="isAddOrEdit()">
                                                <button id="btnAddSubTipe" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                                                </button>
                                            </s:if>
                                        </center>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <center>
                                            <table width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <table style="width: 100%;" class="smkSubTipeTable table table-bordered">
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <s:if test="isAddOrEdit()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="smkPegawaiBForm" id="save1" name="save1"
                                                       onBeforeTopics="beforeProcessSave1" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-save"></i>
                                                Save SMK Jabatan
                                            </sj:submit>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="window.location.href='<s:url action="cancelIndikator_smkJabatan"/>'">
                                                <i class="fa fa-refresh"></i> Cancel
                                            </button>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="smkPegawaiBForm" id="save1" name="save1"
                                                       onBeforeTopics="beforeProcessSave1" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-trash"></i>
                                                Delete SMK Jabatan
                                            </sj:submit>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="window.location.href='<s:url action="cancelIndikator_smkJabatan"/>'">
                                                <i class="fa fa-refresh"></i> Cancel
                                            </button>
                                        </s:elseif>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="actions" class="form-actions">
                            <table>
                                <tr>
                                    <div id="crudl">
                                        <td>
                                            <table>
                                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false" title="Saving ...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      clos();
                                                                   }
                                                            }"
                                                >
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                    Record has been saved successfully.
                                                </sj:dialog>

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

                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                            <br/>
                                                            <center><div id="errorValidationMessage"></div></center>
                                                        </label>
                                                    </div>
                                                </sj:dialog>
                                            </table>
                                        </td>
                                    </div>
                                </tr>
                            </table>
                        </div>
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
<div id="modal-smkJabatan" class="modal fade" role="dialog">
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
                        <label class="control-label col-sm-3" >Nama Indikator : </label>
                        <div class="col-sm-9">
                            <input type="text" style="display: none" class="form-control nip" id="subTipeNamaOld">
                            <input type="text" class="form-control nip" id="subTipeNama">
                            <input type="text" style="display: none" class="form-control nip" id="kategoriId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bobot : </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control nip" id="subTipeBobot">
                        </div>
                    </div>

                    <%--<div class="form-group" id="aspek">
                        <div class="col-sm-3" style="text-align: right">
                            <input type="checkbox" id="checkApprove" name="checkApprove" class="checkApprove" onchange="cekCentang()" >
                            <label class="control-label " >Sub Aspek: </label>
                        </div>
                        <div class="col-sm-9">
                            <s:action id="initComboCheckList" namespace="/smkCheckList" name="initComboCheckList_smkCheckList"/>
                            <s:select list="#initComboCheckList.listComboSmkCheckList" id="tipeAspekA"
                                      name="smkJabatan.subAspekA" disabled="true"
                                      listKey="checkListId" listValue="checkListName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>--%>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Add</button>
                <button id="btnSaveBC" type="button" class="btn btn-default btn-success">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>
    window.loadSubTipe =  function(){
        var tipeAspek   = document.getElementById("tipeAspekId").value;
        $('.smkSubTipeTable').find('tbody').remove();
        $('.smkSubTipeTable').find('thead').remove();
        $('.smkSubTipeTable').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        SmkJabatanAction.searchSmkJabatanSubTipeA(function(listdata) {
            <s:if test="isAddOrEdit()">
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Indikator</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sub Aspek</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Bobot</th>"+
                    "</tr></thead>";
            </s:if>
            <s:else>
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Indikator</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sub Aspek</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Bobot</th>"+
                    "</tr></thead>";
            </s:else>

            var i = 1 ;
            var x = 1 ;
            var jumlah = 0 ;
            $.each(listdata, function (i, item) {
                <s:if test="isAddOrEdit()">
                var hapus = '<td></td>';
                var edit = '<td></td>';

                if(item.jabatanSmkDetailId != '-'){
                    hapus = '<td align="center">' +
                            "<a href='javascript:;' class ='item-delete' data ='"+item.kategoriName+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>';
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+item.indikatorKinerja+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        hapus +
                        '<td align="center">' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + item.subAspekANama + '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        "</tr>";
                </s:if>
                <s:else>
                tmp_table += '<tr style="font-size: 12px;">' +
                        '<td align="center">' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + item.subAspekANama+ '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        "</tr>";
                </s:else>
                x++;
                jumlah += item.bobot;
            });
            <s:if test="isAddOrEdit()">
            tmp_table += '<tfoot><tr><td colspan="4" style="font-size: 12px;text-align: center">Jumlah Bobot' +
                    '</td><td style="font-size: 12px; text-align: center">'+jumlah+'</td></tr></tfoot>';
            </s:if>
            <s:else >
            tmp_table += '<tfoot><tr><td colspan="2" style="font-size: 12px;text-align: center">Jumlah Bobot' +
                    '</td><td style="font-size: 12px; text-align: center">'+jumlah+'</td></tr></tfoot>';
            </s:else>
            if(tipeAspek == 'A'){
                $('#totalBobot').val(jumlah);
            }

            $('.smkSubTipeTable').append(tmp_table);
        });
    }

    window.loadSubTipeB =  function(){
        var tipeAspek   = document.getElementById("tipeAspekId").value;
        var branchId   = document.getElementById("branchId").value;

        $('.smkSubTipeTable').find('tbody').remove();
        $('.smkSubTipeTable').find('thead').remove();
        $('.smkSubTipeTable').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SmkJabatanAction.aspekB(function(listdata) {
            <s:if test="isAddOrEdit()">
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Indikator</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Bobot</th>"+
                    "</tr></thead>";
            </s:if>
            <s:else>
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Indikator</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Bobot</th>"+
                    "</tr></thead>";
            </s:else>

            var i = 1 ;
            var x = 1 ;
            var jumlah = 0 ;
            $.each(listdata, function (i, item) {
                <s:if test="isAddOrEdit()">
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-editBC' data ='"+item.kategoriAspekId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '<td align="center">' + item.kategoriName + '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        "</tr>";
                </s:if>
                <s:else>
                tmp_table += '<tr style="font-size: 12px;">' +
                        '<td align="center">' + item.kategoriName + '</td>' +
                        '<td align="center">' + item.bobot+ '</td>' +
                        "</tr>";
                </s:else>
                x++;
                jumlah += item.bobot;
            });
            <s:if test="isAddOrEdit()">
            tmp_table += '<tfoot><tr><td colspan="2" style="font-size: 12px;text-align: center">Jumlah Bobot' +
                    '</td><td style="font-size: 12px; text-align: center">'+jumlah+'</td></tr></tfoot>';
            </s:if>
            <s:else >
            tmp_table += '<tfoot><tr><td colspan="1" style="font-size: 12px;text-align: center">Jumlah Bobot' +
                    '</td><td style="font-size: 12px; text-align: center">'+jumlah+'</td></tr></tfoot>';
            </s:else>

            $('#totalBobot').val(jumlah);
            $('.smkSubTipeTable').append(tmp_table);
        });
    }

    window.cekCentang = function(){
        if($('#checkApprove').is(":checked")){
            $('#tipeAspekA').removeAttr('disabled');
        }else{
            $('#tipeAspekA').prop('disabled', 'true');
        }
    }

    window.cekSubTipeA = function(){
        var tipeAspek   = document.getElementById("tipeAspekId").value;
        if(tipeAspek == 'A'){
            <s:if test="isAddOrEdit()">
            $('#tipeAspekA').removeAttr('disabled');
            $('#checkApproveName').val("Y");
            $('#checkApprove').removeAttr('disabled');
            </s:if>
            $('.tombol').show();
        }else{
            var rows = $('table.someclass tr');
            rows.filter('.totalBobots').show();
        }
    }

    window.cekJabatan = function(id){
        var hasil = "";
        var branchId   = document.getElementById("branchId").value;
        var tipeAspek   = document.getElementById("tipeAspekId").value;

        SmkJabatanAction.cekJabatan(branchId, id.value, function(listdata) {
            $.each(listdata, function (i, item) {
                hasil = item.positionName ;
            });
        });
        if(tipeAspek == 'A'){
            loadSubTipe();
        }
    }


    $(document).ready(function() {
        window.cekAspek = function(aspekId){
            var branchId   = document.getElementById("branchId").value;
            var tipeAspek   = document.getElementById("tipeAspekId").value;
            if(branchId == ''){
                alert('Pilih Unit ');
            }else{
                var jumlahBobot = 0 ;
                var rows = $('table.someclass tr');
                if(aspekId.value == 'A'){
                    //rows.filter('.totalBobots').hide();
                    $('#tipeAspekA').removeAttr('disabled');
                    $('#checkApprove').removeAttr('disabled');
                    $('.tombol').show();
                    $('#checkApproveName').val("Y");
                    loadSubTipe();
                }else if(aspekId.value == 'B1'){
                    $('#tipeAspekA').prop('disabled', 'true');
                    $('#checkApprove').prop('disabled', 'true');
                    $('#checkApproveName').val("N");
                    $('.tombol').hide();
                    SmkJabatanAction.getDataAspek(branchId, tipeAspek, function(listdata) {
                    });
                    loadSubTipeB();
                }else if(aspekId.value == 'B2'){
                    $('#checkApproveName').val("N");
                    $('#tipeAspekA').prop('disabled', 'disabled');
                    $('#checkApprove').prop('disabled', 'disabled');
                    $('.tombol').hide();
                    SmkJabatanAction.getDataAspek(branchId,tipeAspek, function(listdata) {
                    });
                    loadSubTipeB();
                }else if(aspekId.value == 'C'){
                    $('#checkApproveName').val("N");
                    $('#tipeAspekA').prop('disabled', 'disabled');
                    $('#checkApprove').prop('disabled', 'disabled');
                    $('.tombol').hide();
                    SmkJabatanAction.getDataAspek(branchId, tipeAspek, function(listdata) {
                    });
                    loadSubTipeB();
                }
            }
        }

        var tipeAspek   = document.getElementById("tipeAspekId").value;
        cekAspek(tipeAspek);
        $('.tombol').hide();
        $('.smkSubTipeTable').on('click', '.item-delete', function(){
            var id = $(this).attr('data');

            SmkJabatanAction.searchSmkJabatanDetailEdit(id,function(listdata) {
                $.each(listdata, function (i, item) {

                    if(item.subAspekA != ''){
                        $('#checkApprove').prop('checked',true);
                    }else{
                        $('#checkApprove').prop('checked',false);
                    }

                    $('#tipeAspekA').val(item.subAspekA).change();
                    $('#subTipeNama').val(item.indikatorKinerja);
                    $('#kategoriId').val(item.kategoriId);
                    $('#subTipeBobot').val(item.bobot);
                    $('#btnSave').show();
                    $('#btnSaveBC').delete();
                });
            });

            $('#subTipeNama').attr('readonly', true);
            $('#subTipeBobot').attr('readonly', true);
            $('#tipeAspekA').attr('disabled', true);

            $("#btnSave").html('Delete');
            $('#modal-smkJabatan').find('.modal-title').text('Delete Sub Tipe A');
            $('#modal-smkJabatan').modal('show');
            $('#myForm').attr('action', 'deleteSubTipeA');
        });

        $('.smkSubTipeTable').on('click', '.item-edit', function(){
            var id = $(this).attr('data');
            $('#subTipeNama').attr('readonly', false);
            $('#subTipeBobot').attr('readonly', false);
            $('#tipeAspekA').attr('disabled', false);
            $('#btnSaveBC').hide;
            $('#btnSave').show;

            SmkJabatanAction.searchSmkJabatanDetailEdit(id,function(listdata) {
                $.each(listdata, function (i, item) {

                    if(item.subAspekA != ''){
                        $('#checkApprove').prop('checked',true);
                    }else{
                        $('#checkApprove').prop('checked',false);
                    }

                    if(item.jabatanSmkDetailId == '-'){
                        document.getElementById("subTipeNama").readOnly = true;
                        $('#aspek').hide();
                    }else{
                        document.getElementById("subTipeNama").readOnly = false;
                    }
                    $('#btnSave').show();
                    $('#btnSaveBC').hide();
                    $('#tipeAspekA').val(item.subAspekA).change();
                    $('#subTipeNama').val(item.indikatorKinerja);
                    $('#subTipeNamaOld').val(item.indikatorKinerja);
                    $('#subTipeBobot').val(item.bobot);
                });
            });

            $("#btnSave").html('Save');
            $('#modal-smkJabatan').find('.modal-title').text('Edit Sub Tipe A');
            $('#modal-smkJabatan').modal('show');
            $('#myForm').attr('action', 'editSubTipe');
        });

        $('.smkSubTipeTable').on('click', '.item-editBC', function(){
            var id = $(this).attr('data');
            $('#subTipeNama').attr('readonly', true);
            $('#subTipeBobot').attr('readonly', false);
            $('#tipeAspekA').attr('disabled', false);
            $('#aspek').hide();
            $('#btnSave').hide();
            $('#btnSaveBC').show();
            SmkJabatanAction.searchSmkJabatanDetail(id,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#kategoriId').val(item.kategoriAspekId);
                    $('#subTipeNama').val(item.kategoriName);
                    $('#subTipeNamaOld').val(item.kategoriName);
                    $('#subTipeBobot').val(item.bobot);
                });
            });

            $("#btnSaveBC").html('Save');
            $('#modal-smkJabatan').find('.modal-title').text('Edit Aspek B');
            $('#modal-smkJabatan').modal('show');
            $('#myForm').attr('action', 'editSubTipeBC');
        });

        cekSubTipeA();
        window.clos = function() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.href="<s:url action='initForm_smkJabatan.action'/>";
        };

        $('#btnAddSubTipe').click(function(){
            $('#myForm')[0].reset();
            $('#modal-smkJabatan').modal('show');
            $('#myForm').attr('action', 'addSubTipe');
            $('#modal-smkJabatan').find('.modal-title').text('Add Sub Tipe A');
            $('#subTipeNama').attr('readonly', false);
            $('#subTipeBobot').attr('readonly', false);
            $('#subTipeTarget').attr('readonly', false);
            $('#subTipeSatuan').attr('readonly', false);
            $('#tipeAspekA').prop('disabled', 'true');
            $("#btnSave").html('Add');
            $("#btnSave").show();
            $("#btnSaveBC").hide();
            $("#aspek").show();
        });

        $('#btnSave').click(function(){
            var url = $('#myForm').attr('action');
            var data = $('#myForm').serialize();

            var subTipeNama         = document.getElementById("subTipeNama").value;
            var subTipeNamaOld      = document.getElementById("subTipeNamaOld").value;
            var subTipeBobot        = document.getElementById("subTipeBobot").value;
            var subTipeAspek        = document.getElementById("tipeAspekA").value;
            var subTipeAspekNama    = document.getElementById("tipeAspekA");
            var subTipeAspekNama1   = subTipeAspekNama.options[subTipeAspekNama.selectedIndex].text;
            if(subTipeAspek == ''){
                subTipeAspekNama1 = '';
            }

            if(url == 'addSubTipe'){
                if(subTipeNama != '' && subTipeBobot != ''){
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        SmkJabatanAction.saveAdd(subTipeNama, subTipeBobot, subTipeAspek, subTipeAspekNama1, function(listdata) {
                            alert('Data Successfully Added');
                            $('#modal-smkJabatan').modal('hide');
                            $('#myForm')[0].reset();
                            loadSubTipe();
                        });
                    }
                }else{
                    var msg = '';
                    if(subTipeNama == ''){
                        msg += 'Nama Indikator, ';
                    }
                    if(subTipeBobot== ''){
                        msg += 'Bobot, ';
                    }

                    msg += 'Harus Diisi!';
                    alert(msg);
                }
            }else if(url == 'editSubTipe'){
                if(subTipeNama != ''){
                    dwr.engine.setAsync(false);
                    SmkJabatanAction.saveEditSmkJabatan(subTipeNamaOld, subTipeNama, subTipeBobot, subTipeAspek, subTipeAspekNama1, function(listdata) {
                        $('#modal-smkJabatan').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Data Successfully Updated');
                        loadSubTipe();
                    });
                }
            }else{
                if (confirm('Are you sure you want to delete this Record?')) {
                    SmkJabatanAction.saveSmkJabatanDetailDelete(subTipeNama, function (listdata) {
                        $('#modal-smkJabatan').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSubTipe();
                    });
                }
            }
        });

        $('#btnSaveBC').click(function(){
            var url = $('#myForm').attr('action');
            var data = $('#myForm').serialize();
            var subTipeNama         = document.getElementById("subTipeNama").value;
            var subTipeBobot        = document.getElementById("subTipeBobot").value;
            var id = document.getElementById("kategoriId").value;

            if(url == 'addSubTipe'){

            }else if(url == 'editSubTipeBC'){
                if(subTipeNama != ''){
                    dwr.engine.setAsync(false);
                    SmkJabatanAction.saveEditSmkJabatanBC(id, subTipeBobot, function(listdata) {
                        $('#modal-smkJabatan').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Data Successfully Updated');
                        loadSubTipeB();
                    });
                }
            }
        });
        var branch = document.getElementById("branchId").value;
        var divisi = document.getElementById("divisiId").value;
        if(branch != ''){
            listPosisi(branch, divisi);
        }
    });

    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchId").value;
        var divisi = document.getElementById("divisiId").value;

        $('#positionId').empty();
        PositionAction.searchPosition2(branch, divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionId').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
            });
        });
    }

</script>

