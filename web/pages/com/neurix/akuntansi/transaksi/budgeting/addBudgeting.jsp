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
        .modal { overflow-y: auto}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript'>


    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Akutansi
            <%--<small>e-HEALTH</small>--%>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Budgeting </h3>
                    </div>
                    <div class="box-body">
                        <%--<s:form id="kasirjalanForm" method="post" namespace="/kasirjalan" action="search_kasirjalan.action" theme="simple" cssClass="form-horizontal">--%>
                        <div class="form-group form-horizontal">
                            <div class="row">
                                <div class="col-md-12 col-md-offset-3">
                                    <div class="row">
                                        <label class="control-label col-sm-2">Tahun</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" id="sel-tahun">
                                                <option value="2020">2020</option>
                                                <option value="2021">2021</option>
                                                <option value="2022">2022</option>
                                                <option value="2023">2023</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="control-label col-sm-2">Unit</label>
                                        <div class="col-sm-2">

                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="sel-unit" name="budgeting.branchId"
                                                      listKey="branchId" listValue="branchName" cssClass="form-control"/>
                                            <%--<select class="form-control" id="sel-unit">--%>
                                            <%--</select>--%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <label class="control-label col-sm-2">Tipe Budgeting</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" id="sel-tipe">
                                                <option value="tahunan">Tahunan</option>
                                                <option value="semester">Semester</option>
                                                <option value="quartal">Quartal</option>
                                            </select>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 col-md-offset-6" style="margin-top: 10px">
                                    <button class="btn btn-success" onclick="add()"><i class="fa fa-plus"></i> Add</button>
                                </div>
                            </div>
                        </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i>
                            <%--List Tutup Period <strong><span id="label-tahun"></span> - <span id="label-bulan"></span></strong> --%>
                        </h3>
                    </div>
                    <div class="box-body">

                        <%--<div class="alert alert-info alert-dismissable" id="alert-info">--%>
                        <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
                        <%--<strong>Info!</strong> Pilih Priode Kemudian Choose--%>
                        <%--</div>--%>

                        <div class="alert alert-success alert-dismissable" id="alert-success" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Success!</strong> Berhasil Menyimpan data
                        </div>

                        <div class="alert alert-warning alert-dismissable" id="alert-error" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Error!</strong><span id="error-msg"></span>
                        </div>

                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <table id="sortTable" class="table table-bordered table-striped">
                                    <thead id="head-budgeting">
                                    <tr bgcolor="#90ee90">
                                        <td style="width: 20%">COA</td>
                                        <td align="center">Keterangan</td>
                                        <%--<td align="center">Action</td>--%>
                                    </tr>
                                    </thead>
                                    <tbody id="body-budgeting">
                                    <s:iterator value="#session.listOfCoa" status="listOfCoa" var="row">
                                        <tr>
                                            <td><s:property value="kodeRekening"/></td>
                                            <td><s:property value="namaKodeRekening"/></td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-success" onclick="initForm()"><i class="fa fa-arrow-left"></i> Back</button>
                                <button class="btn btn-success" id="btn-save" onclick="saveAdd()"><i class="fa fa-arrow-right"></i> Next </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add-coa">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-plus"></i> Add COA
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                    <label class="col-md-2 col-md-offset-1">COA</label>
                    <div class="col-md-6">
                    <input type="text" class="form-control" id="coa">

                        <script>
                            $(document).ready(function() {
                                var functions, mapped;
                                $('#coa').typeahead({
                                    minLength: 1,
                                    source: function (query, process) {
                                        functions = [];
                                        mapped = {};
                                        var data = [];
                                        dwr.engine.setAsync(false);
                                        BudgetingAction.getListKodeRekeningByLevel(query,function (listdata) {
                                            data = listdata;
                                        });
                                        $.each(data, function (i, item) {
                                            var labelItem = item.kodeRekening + " | " + item.namaKodeRekening;
                                            mapped[labelItem] = {
                                                id: item.rekeningId,
                                                nama: item.namaKodeRekening,
                                                kode : item.kodeRekening,
                                                parent :item.parentId
                                            };
                                            functions.push(labelItem);
                                        });
                                        process(functions);
                                    },
                                    updater: function (item) {
                                        var selectedObj = mapped[item];
                                        $('#rekeningid').val(selectedObj.id);
                                        $('#namacoa').val(selectedObj.nama);
                                        $('#parentid').val(selectedObj.parent);
                                        return selectedObj.kode;
                                    }
                                });
                            });
                        </script>

                    <input type="hidden" id="rekeningid">
                    <input type="hidden" id="namacoa">
                    <input type="hidden" id="parentid">
                    </div>
                    </div>

                    <div class="alert alert-warning alert-dismissable" id="alert-error-modal" style="display: none">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Error!</strong><span id="error-msg-modal">Gagal Menambahkan No. Rekening : No. Rekening Sudah Ada</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="save_con" onclick="addCoa()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    var listOfCoa = [];
    $( document ).ready(function() {

    });


    // exemple : post('/contact/', {name: 'Johnny Bravo'});
    function post(path, params) {

        var method='post';
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        const form = document.createElement('form');
        form.method = method;
        form.action = path;

        for (const key in params) {
            if (params.hasOwnProperty(key)) {
                const hiddenField = document.createElement('input');
                hiddenField.type = 'hidden';
                hiddenField.name = key;
                hiddenField.value = params[key];

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }



    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function initForm() {
        var host = firstpath()+"/budgeting/initForm_budgeting.action";
        post(host);
    }

    function add() {
        $("#modal-add-coa").modal('show');
        $("#rekeningid").val("");
        $("#coa").val("");
        $("#namacoa").val("");
    }

    function addCoa() {
        var rekeningId = $("#rekeningid").val();
        var coa = $("#coa").val();
        var namacoa = $("#namacoa").val();
        var parent = $("#parentid").val();


        if (rekeningId != ""){

            var arrCoa = [];
            arrCoa.push({kode:coa, nama:namacoa, id:rekeningId, parent:parent});
            var jsonString = JSON.stringify(arrCoa);

            BudgetingAction.setToSessionCoa(jsonString, function (response) {
                if(response.status == "success"){
                    refreshAdd();
                    $("#btn-save").show();
                } else {
                    $("#alert-error-modal").show().fadeOut(5000);
                    $("#error-msg-modal").text(response.msg);
                }
            });

//            listOfCoa.push({kode:coa, nama:namacoa, id:rekeningId, parent:parent});
//
//
//            var strBody = "";
//            $.each(listOfCoa, function (i, item) {
//                strBody += "<tr>" +
//                        "<td>"+item.kode+"</td>"+
//                        "<td>"+item.nama+"</td>"+
//                        "</tr>";
//            });
//            console.log(listOfCoa);
//            $("#body-budgeting").html(strBody);
//            $("#modal-add-coa").modal('hide');
//            $("#btn-save").show();
        } else {
            alert("COA Harus Diisi !");
        }
    }

    function refreshAdd() {
        var host = firstpath()+"/budgeting/add_budgeting.action?status=add";
        post(host);
    }

    function saveAdd() {
        var tahun = $("#sel-tahun").val();
        var unit = $("#sel-unit").val();
        var tipe = $("#sel-tipe").val();

        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };

        var host = firstpath()+"/budgeting/add_budgeting.action?status=add&tipe=detail&trans=ADD_DRAFT";
        post(host, form);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>