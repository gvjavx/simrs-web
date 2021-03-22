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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Detail Rekanan Operasional</h3>
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
                                                    <td>Tarif Bpjs</td>
                                                    <td>Diskon Bpjs</td>
                                                    <td>Action</td>
                                                </thead>
                                                <tbody>
                                                <s:iterator value="#session.listOfResultPelayanan" var="row">
                                                    <tr>
                                                        <td><s:property value="namaTindakan"/></td>
                                                        <td><s:property value="namaPelayanan"/></td>
                                                        <td></td>
                                                        <td><s:property value="tarif"/></td>
                                                        <td align="center" width="10%">
                                                            <%--<img class="hvr-grow"--%>
                                                                 <%--onclick="showModal('detail', '<s:property value="idItem"/>')"--%>
                                                                 <%--style="cursor: pointer"--%>
                                                                 <%--src="<s:url value="/pages/images/icons8-view-25.png"/>">--%>
                                                            <img class="hvr-grow"
                                                                 onclick="showModal('edit', '<s:property value="idItem"/>')"
                                                                 style="cursor: pointer"
                                                                 src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                                            <img class="hvr-grow"
                                                                 onclick="showModal('delete', '<s:property value="idItem"/>')"
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
            <div class="modal-body" id="temp_scrol">
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
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal Non Bpjs :  </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="add_harga_normal" readonly/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon Tarif Rekanan Non Bpjs :  </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_diskon_non_bpjs" style="width: 100px;"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan Non Bpjs :  </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_tarif_non_bpjs"/>
                        </div>
                    </div>
                </div>

                <hr/>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Normal Bpjs :  </label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="add_harga_normal_bpjs" readonly/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon Tarif Rekanan Bpjs :  </label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="add_diskon_bpjs" style="width: 100px;"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Rekanan Bpjs :  </label>
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

<script>

    function showModal(tipe, id){

        if (tipe == 'add'){
            showPelayanan(tipe)
            $("#modal-add").modal('show');
        }
        if (tipe == 'edit'){
            $("#modal-edit").modal('show');
        }
        if (tipe == 'delete'){
            $("#modal-delete").modal('show');
        }
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

        var obj = {
            id_item : idItem, nama_tindakan: namaTindakan, tarif_non_bpjs : tarifNonBpjs,
            tarif_bpjs : tarifBpjsm, diskon_non_bpjs : diskonNonBpjs, diskon_bpjs : diskonBpjs,
            parent_id : parentId
        }

        var stJson = JSON.stringify(obj);

        DetailRekananOpsAction.saveAddToSessionTindakan(stJson, function (res) {

            if (res.status == "success"){
                refresh();
            } else {
                $("#warning_add").show().fadeOut(5000);
                $("#msg_add").text(res.msg);
            }
        });
    }

    function refresh() {
        window.location.href = "initDetailTarif_detailrekananops.action?tipe=refresh";
    }

</script>

<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>