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

    <script type='text/javascript' src='<s:url value="/dwr/interface/DetailRekananOpsAction.js"/>'></script>

    <script type='text/javascript'>

        function formatRupiah(angka) {
            if(angka != null && angka != ''){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                if (angka < 0){
                    return "-"+ribuan;
                } else {
                    return ribuan;
                }
            }else{
                return 0;
            }
        }

        function link(){
            window.location.href="<s:url action='initForm_detailrekananops'/>";
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
            Detail Rekanan Operasional
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Per Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="detailRekananOpsForm" method="post"  theme="simple"
                                            namespace="/detailrekananops" action="search_detailrekananops.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td width="30%">
                                                    <label class="control-label"><small>Nama Rekanan : </small></label>
                                                </td>
                                                <td width="90%">
                                                    <s:textfield name="detailRekananOps.namaRekanan" cssClass="form-control" disabled="true" ></s:textfield>
                                                    <s:hidden name="detailRekananOps.idDetailRekananOps" id="idDetailRekananOps"></s:hidden>
                                                    <s:hidden name="detailRekananOps.branchId" id="branchId"></s:hidden>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <%--<td >--%>
                                                        <%--<sj:submit type="button" cssStyle="margin-right: 5px" cssClass="btn btn-primary" formIds="detailRekananOpsForm" id="search" name="search"--%>
                                                                   <%--onClickTopics="showDialog" onCompleteTopics="closeDialog" >--%>
                                                            <%--<i class="fa fa-search"></i>--%>
                                                            <%--Search--%>
                                                        <%--</sj:submit>--%>
                                                    <%--</td>--%>
                                                    <td>
                                                        <%--<s:url var="urlAdd" namespace="/detailrekananops" action="add_detailrekananops" escapeAmp="false">--%>
                                                        <%--</s:url>--%>
                                                        <%--<sj:a cssClass="btn btn-success" cssStyle="margin-right: 5px" onClickTopics="showDialogMenu" href="%{urlAdd}">--%>
                                                            <%--<i class="fa fa-plus"></i>--%>
                                                            <%--Tambah Tarif Tindakan--%>
                                                        <%--</sj:a>--%>
                                                            <button type="button" class="btn btn-success" cssStyle="margin-right: 5px"
                                                                    onclick="showModal('add')">
                                                                <i class="fa fa-plus"></i> Tambah Tarif Tindakan
                                                            </button>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" cssStyle="margin-right: 5px"
                                                                onclick="window.location.href='<s:url
                                                                action="initForm_detailrekananops"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="90%" align="center" class="table table-bordered table-striped">
                                                <thead>
                                                    <td>Nama Tindakan</td>
                                                    <td>Pelayanan</td>
                                                    <td>Diskon Non Bpjs</td>
                                                    <td>Tarif Non Bpjs</td>
                                                    <td>Diskon Bpjs</td>
                                                    <td>Tarif Bpjs</td>
                                                    <td>Action</td>
                                                </thead>
                                                <tbody>
                                                <s:iterator value="#session.listOfTindakan" var="row">
                                                    <tr>
                                                        <td><s:property value="namaTindakan"/></td>
                                                        <td><s:property value="namaPelayanan"/></td>
                                                        <td><s:property value="stDiskonNonBpjs"/> % </td>
                                                        <td><script>document.write(formatRupiah('<s:property value="stTarif"/>'))</script></td>
                                                        <td><s:property value="stDiskonBpjs"/> % </td>
                                                        <td><script>document.write(formatRupiah('<s:property value="stTarifBpjs"/>'))</script></td>
                                                        <td align="center" width="10%">
                                                            <img class="hvr-grow"
                                                                 onclick="showModal('edit', '<s:property value="idDetailRekananOps"/>')"
                                                                 style="cursor: pointer"
                                                                 src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                                            <img class="hvr-grow"
                                                                 onclick="showModal('delete', '<s:property value="idDetailRekananOps"/>')"
                                                                 style="cursor: pointer"
                                                                 src="<s:url value="/pages/images/cancel-flat-new.png"/>">
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                                </tbody>
                                            </table>
                                        </center>
                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog"
                                                                           modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                                >
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                                 name="icon_error"> Please check this field :
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
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Tindakan
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol" style="font-size: 12px;">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="add_list_pelayanan" style="width: 100%"
                                    onchange="var warn =$('#war_add_list_pelayanan').is(':visible'); if (warn){$('#cor_add_list_pelayanan').show().fadeOut(3000);$('#war_add_list_pelayanan').hide()};showTindakan(this.value, 'add')">
                                <option value=""> - </option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_add_list_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_add_list_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tindakan </label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="add_list_tindakan" style="width: 100%"
                                    onchange="var warn =$('#war_add_list_tindakan').is(':visible'); if (warn){$('#cor_add_list_tindakan').show().fadeOut(3000);$('#war_add_list_tindakan').hide()} getTindakan(this.value, 'add')">
                                <option value=""> - </option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_add_list_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_add_list_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <hr/>
                <div style="font-weight: bold; text-align: center">Non BPJS</div>
                <br/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal :  </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="add_harga_normal" readonly/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%) </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_diskon_non_bpjs" style="width: 100px;" onchange="hitungDiskon(this.value ,'add', 'non_bpjs')" max="100"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_tarif_non_bpjs"/>
                        </div>
                    </div>
                </div>

                <hr/>
                <div style="font-weight: bold; text-align: center">BPJS</div>
                <br/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="add_harga_normal_bpjs" readonly/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%) </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_diskon_bpjs" style="width: 100px;" onchange="hitungDiskon(this.value ,'add', 'bpjs')" max="100"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_tarif_bpjs"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" onclick="saveAdd()">
                    <i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Tindakan
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol" style="font-size: 12px;">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_list_pelayanan" style="width: 100%" disabled
                                    onchange="var warn =$('#war_edit_list_pelayanan').is(':visible'); if (warn){$('#cor_edit_list_pelayanan').show().fadeOut(3000);$('#war_edit_list_pelayanan').hide()};showTindakan(this.value, 'edit')">
                                <option value=""> - </option>
                            </select>
                            <input type="hidden" id="edit_id_pelayanan"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_list_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_list_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tindakan </label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_list_tindakan" style="width: 100%" disabled
                                    onchange="var warn =$('#war_edit_list_tindakan').is(':visible'); if (warn){$('#cor_edit_list_tindakan').show().fadeOut(3000);$('#war_edit_list_tindakan').hide()} getTindakan(this.value, 'edit')">
                                <option value=""> - </option>
                            </select>
                            <input type="hidden" id="edit_id_tindakan"/>
                            <input type="hidden" id="edit_id_detail_rekanan_ops"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_list_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_list_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <hr/>
                <div style="font-weight: bold; text-align: center">Non BPJS</div>
                <br/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="edit_harga_normal" readonly/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%) </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="edit_diskon_non_bpjs" style="width: 100px;" onchange="hitungDiskon(this.value ,'edit', 'non_bpjs')" max="100" maxlength="3"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="edit_tarif_non_bpjs"/>
                        </div>
                    </div>
                </div>

                <hr/>
                <div style="font-weight: bold; text-align: center">BPJS</div>
                <br/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="edit_harga_normal_bpjs" readonly/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%) </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="edit_diskon_bpjs" style="width: 100px;" onchange="hitungDiskon(this.value ,'edit', 'bpjs')" max="100" maxlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="edit_tarif_bpjs"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_edit" onclick="saveEdit()">
                    <i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-delete">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Delete Tindakan
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol" style="font-size: 12px;">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_delete">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_delete"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="delete_list_pelayanan" style="width: 100%" disabled
                                    onchange="var warn =$('#war_delete_list_pelayanan').is(':visible'); if (warn){$('#cor_delete_list_pelayanan').show().fadeOut(3000);$('#war_delete_list_pelayanan').hide()};showTindakan(this.value, 'delete')">
                                <option value=""> - </option>
                            </select>
                            <input type="hidden" id="delete_id_pelayanan"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_delete_list_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_delete_list_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tindakan </label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="delete_list_tindakan" style="width: 100%" disabled
                                    onchange="var warn =$('#war_delete_list_tindakan').is(':visible'); if (warn){$('#cor_delete_list_tindakan').show().fadeOut(3000);$('#war_delete_list_tindakan').hide()} getTindakan(this.value, 'delete')">
                                <option value=""> - </option>
                            </select>
                            <input type="hidden" id="delete_id_tindakan"/>
                            <input type="hidden" id="delete_id_detail_rekanan_ops"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_delete_list_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_delete_list_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <hr/>
                <div style="font-weight: bold; text-align: center">Non BPJS</div>
                <br/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="delete_harga_normal" readonly/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%) </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="delete_diskon_non_bpjs" style="width: 100px;" disabled/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="delete_tarif_non_bpjs" disabled/>
                        </div>
                    </div>
                </div>

                <hr/>
                <div style="font-weight: bold; text-align: center">BPJS</div>
                <br/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="delete_harga_normal_bpjs" readonly/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%) </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="delete_diskon_bpjs" style="width: 100px;" disabled/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="delete_tarif_bpjs" disabled/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" id="save_delete" onclick="saveDelete()">
                    <i class="fa fa-trash"></i> Delete
                </button>
            </div>
        </div>
    </div>
</div>

<script>

    function showModal(tipe, id){

        if (tipe == 'add'){
            showPelayanan(tipe);
            $("#modal-add").modal('show');
        }
        if (tipe == 'edit'){
            showEdit(id);
            $("#modal-edit").modal('show');
        }
        if (tipe == 'delete'){
            showDelete(id);
            $("#modal-delete").modal('show');
        }
    }
    
    function showEdit(id) {

        DetailRekananOpsAction.getSessionByIdItem(id, function (res) {
            showPelayanan("edit");
            showTindakan(res.idPelayanan, "edit");
            getTindakan(res.idItem,"edit");
            $("#edit_list_pelayanan").val(res.idPelayanan);
            $("#edit_id_pelayanan").val(res.idPelayanan);
            $("#edit_list_tindakan").val(res.idItem);
            $("#edit_id_tindakan").val(res.idItem);
            $("#edit_tarif_non_bpjs").val(res.tarif);
            $("#edit_tarif_bpjs").val(res.tarifBpjs);
            $("#edit_harga_normal").val(res.tarifNormalNonBpjs);
            $("#edit_harga_normal_bpjs").val(res.tarifNormalBpjs);
            $("#edit_diskon_non_bpjs").val(res.diskonNonBpjs);
            $("#edit_diskon_bpjs").val(res.diskonBpjs);
            $("#edit_id_detail_rekanan_ops").val(res.idDetailRekananOps);
        });
    }

    function showDelete(id) {
        DetailRekananOpsAction.getSessionByIdItem(id, function (res) {
            showPelayanan("delete");
            showTindakan(res.idPelayanan, "delete");
            getTindakan(res.idItem,"delete");
            $("#delete_list_pelayanan").val(res.idPelayanan);
            $("#delete_id_pelayanan").val(res.idPelayanan);
            $("#delete_list_tindakan").val(res.idItem);
            $("#delete_id_tindakan").val(res.idItem);
            $("#delete_tarif_non_bpjs").val(res.tarif);
            $("#delete_tarif_bpjs").val(res.tarifBpjs);
            $("#delete_harga_normal").val(res.tarifNormalNonBpjs);
            $("#delete_harga_normal_bpjs").val(res.tarifNormalBpjs);
            $("#delete_diskon_non_bpjs").val(res.diskonNonBpjs);
            $("#delete_diskon_bpjs").val(res.diskonBpjs);
            $("#delete_id_detail_rekanan_ops").val(res.idDetailRekananOps);
        });
    }

    function showPelayanan(tipe) {
        var branchId = $("#branchId").val();
        DetailRekananOpsAction.getListPelayananByBranchId(branchId, function (res) {

            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.idPelayanan+'">'+item.namaPelayanan+'</option>';
            })

            $("#" +tipe + "_list_pelayanan").html(str);
        });
    }

    function showTindakan(idPelayanan, tipe) {

        DetailRekananOpsAction.getListTindakanByPelayanan(idPelayanan, function (res) {

            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.idTindakan+'">'+item.namaTindakan+'</option>';
            })

            $("#" + tipe + "_list_tindakan").html(str);
        });
    }

    function getTindakan(idTindakan, tipe) {

        DetailRekananOpsAction.getTindakanById(idTindakan, function (res) {

            $("#" + tipe + "_harga_normal").val(res.bTarifNormal);
            $("#" + tipe + "_harga_normal_bpjs").val(res.bTarifBpjs);
        });

    }

    function saveAdd() {

        var idItem          = $("#add_list_tindakan option:selected").val();
        var namaTindakan    = $("#add_list_tindakan option:selected").text();
        var tarifNonBpjs    = $("#add_tarif_non_bpjs").val();
        var tarifBpjs       = $("#add_tarif_bpjs").val();
        var diskonNonBpjs   = $("#add_diskon_non_bpjs").val();
        var diskonBpjs      = $("#add_diskon_bpjs").val();
        var parentId        = $("#idDetailRekananOps").val();
        var branchId        = $("#branchId").val();

        //validasi
        var clean = true;
        var errMsg = "";
        if (parseInt(diskonNonBpjs) > 100 || parseInt(diskonBpjs) > 100){
            clean = false;
            errMsg += " - Diskon Terlalu Besar \n";
        }
        if (tarifNonBpjs == '' && tarifBpjs == ''){
            clean = false;
            errMsg += " - Tarif Harus Disisi \n";
        }
        // END

        if (clean){
            var obj = {
                id_item : idItem, nama_tindakan: namaTindakan, tarif : tarifNonBpjs,
                tarif_pbjs : tarifBpjs, diskon_non_bpjs : diskonNonBpjs, diskon_bpjs : diskonBpjs,
                parent_id : parentId, branch_id : branchId
            }

            var stJson = JSON.stringify(obj);

            DetailRekananOpsAction.saveAddToSessionTindakan(stJson, function (res) {

                if (res.status == "success"){
                    alert("Berhasil Ditambah");
                    refresh();
                } else {
                    $("#warning_add").show().fadeOut(5000);
                    $("#msg_add").text(res.msg);
                }
            });
        } else {
            $("#warning_add").show().fadeOut(5000);
            $("#msg_add").text(errMsg);
        }
    }

    function saveEdit() {

        var idDetailRekananOps  = $("#edit_id_detail_rekanan_ops").val();
        var tarifNonBpjs        = $("#edit_tarif_non_bpjs").val();
        var tarifBpjs           = $("#edit_tarif_bpjs").val();
        var diskonNonBpjs       = $("#edit_diskon_non_bpjs").val();
        var diskonBpjs          = $("#edit_diskon_bpjs").val();
        var parentId            = $("#idDetailRekananOps").val();
        var branchId            = $("#branchId").val();

        //validasi
        var clean = true;
        var errMsg = "";
        if (parseInt(diskonNonBpjs) > 100 || parseInt(diskonBpjs) > 100){
            clean = false;
            errMsg += " - Diskon Terlalu Besar \n";
        }
        if (tarifNonBpjs == '' && tarifBpjs == ''){
            clean = false;
            errMsg += " - Tarif Harus Disisi \n";
        }
        // END

        if (clean){
            var obj = {
                tarif : tarifNonBpjs, id_detail_rekanan_ops : idDetailRekananOps,
                tarif_bpjs : tarifBpjs, diskon_non_bpjs : diskonNonBpjs, diskon_bpjs : diskonBpjs,
                parent_id : parentId, branch_id : branchId
            }

            var stJson = JSON.stringify(obj);

            DetailRekananOpsAction.saveEditToSessionTindakan(stJson, function (res) {

                if (res.status == "success"){
                    alert("Berhasil Diedit");
                    refresh();
                } else {
                    $("#warning_edit").show().fadeOut(5000);
                    $("#msg_edit").text(res.msg);
                }
            });
        } else {
            $("#warning_edit").show().fadeOut(5000);
            $("#msg_edit").text(errMsg);
        }
    }

    function saveDelete() {

        var idDetailRekananOps  = $("#delete_id_detail_rekanan_ops").val();

        var obj = {
            id_detail_rekanan_ops : idDetailRekananOps
        }

        var stJson = JSON.stringify(obj);

        DetailRekananOpsAction.saveDeleteToSessionTindakan(stJson, function (res) {

            if (res.status == "success"){
                alert("Berhasil Dihapus");
                refresh();
            } else {
                $("#warning_delete").show().fadeOut(5000);
                $("#msg_delete").text(res.msg);
            }
        });
    }

    function refresh() {
        var parentId = $("#idDetailRekananOps").val();
        window.location.href = "initDetailTarif_detailrekananops.action?id="+parentId;
    }

    function hitungDiskon(diskon, tipe, jenis) {
        var net = "";

        if (tipe == "edit"){
            if (jenis == "non_bpjs"){
                net = $("#edit_harga_normal").val();
            } else {
                net = $("#edit_harga_normal_bpjs").val();
            }
        } else {
            if (jenis == "non_bpjs"){
                net = $("#add_harga_normal").val();
            } else {
                net = $("#add_harga_normal_bpjs").val();
            }
        }

        var hargajual = net - (net*(diskon/100));
        if (parseInt(diskon) > 100){
            hargajual = 0;
        }
        if (tipe == "edit"){
            if (jenis == "non_bpjs"){
                $("#edit_tarif_non_bpjs").val(hargajual);
            } else {
                $("#edit_tarif_bpjs").val(hargajual);
            }
        } else {
            if (jenis == "non_bpjs"){
                $("#add_tarif_non_bpjs").val(hargajual);
            } else {
                $("#add_tarif_bpjs").val(hargajual);
            }
        }
    }

</script>

<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>