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


        function formatRupiah(angka) {
            if(angka != null && angka != '' && angka > 0){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
            }
        }

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
                                            <select class="form-control" id="sel-tahun" name="budgeting.tahun" disabled="true">
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
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                                            <%--<select class="form-control" id="sel-unit">--%>
                                            <%--</select>--%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <label class="control-label col-sm-2">Tipe Budgeting</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" id="sel-tipe" name="budgeting.tipe" disabled="true">
                                                <option value="tahunan">Tahunan</option>
                                                <option value="semester">Semester</option>
                                                <option value="quartal">Quartal</option>
                                            </select>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 col-md-offset-5" style="margin-top: 10px">
                                    <%--<button class="btn btn-success" onclick="add()"><i class="fa fa-plus"></i> Add</button>--%>
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
                            <div class="col-md-10 col-md-offset-1">
                                <table id="sortTable" class="table table-bordered table-striped" style="font-size: 15px;">
                                    <thead id="head-budgeting">
                                    <tr bgcolor="#90ee90">
                                        <td style="width: 20%">COA</td>
                                        <td align="center">Keterangan</td>
                                        <s:if test='budgeting.tipe == "quartal"'>
                                            <td align="center">Total</td>
                                            <td align="center">Kuartal 1</td>
                                            <td align="center">Kuartal 2</td>
                                            <td align="center">Kuartal 3</td>
                                            <td align="center">Kuartal 4</td>
                                            <td align="center">Selisih</td>
                                        </s:if>
                                        <s:if test='budgeting.tipe == "semester"'>
                                            <td align="center">Total</td>
                                            <td align="center">Semester 1</td>
                                            <td align="center">Semester 2</td>
                                            <td align="center">Selisih</td>
                                        </s:if>
                                        <s:if test='budgeting.tipe == "tahunan"'>
                                            <td align="center">Total</td>
                                            <td align="center">Selisih</td>
                                        </s:if>
                                        <td align="center">Action</td>
                                    <%--<td align="center">Action</td>--%>
                                    </tr>
                                    </thead>
                                    <tbody id="body-budgeting">
                                    <s:iterator value="#session.listOfCoa" status="listOfCoa" var="row">
                                        <tr>
                                            <td><s:property value="kodeRekening"/></td>
                                            <td><s:property value="namaKodeRekening"/></td>
                                            <s:if test='budgeting.tipe == "quartal"'>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="nilaiTotal"/>'))</script></td>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="quartal1"/>'))</script></td>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="quartal2"/>'))</script></td>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="quartal3"/>'))</script></td>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="quartal4"/>'))</script></td>
                                                <td align="center"><s:property value="selisih"/></td>
                                            </s:if>
                                            <s:if test='budgeting.tipe == "semester"'>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="nilaiTotal"/>'))</script></td>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="semester1"/>'))</script></td>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="semester2"/>'))</script></td>
                                                <td align="center"><s:property value="selisih"/></td>
                                            </s:if>
                                            <s:if test='budgeting.tipe == "tahunan"'>
                                                <td align="center"><script>document.write(formatRupiah('<s:property value="nilaiTotal"/>'))</script></td>
                                                <td align="center"><s:property value="selisih"/></td>
                                            </s:if>
                                            <td align="center">
                                                <s:if test='#row.stLevel == "5"'>
                                                    <button class="btn btn-sm btn-success btn-edit" onclick="detail('<s:property value="rekeningId"/>')"><i class="fa fa-edit"></i></button>
                                                </s:if>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-success" onclick="initForm()"><i class="fa fa-arrow-left"></i> Back</button>
                                <button class="btn btn-success" id="btn-save" onclick="saveBudgeting()"><i class="fa fa-check"></i> Save </button>
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
                                            KodeRekeningAction.initTypeaheadKodeRekening(query,function (listdata) {
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

    var unit = '<s:property value="budgeting.branchId" />';
    var tahun = '<s:property value="budgeting.tahun" />';
    var tipe = '<s:property value="budgeting.tipe" />';
    var status = '<s:property value="status" />';
    var trans = '<s:property value="trans" />';

    var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };

    var listOfCoa = [];
    $( document ).ready(function() {

        console.log("hasil >>> "+unit+tahun+tipe);

        $("#sel-tipe").val(tipe);
        $("#sel-tahun").val(tahun);
        $("#sel-unit").val(unit);

        if (trans == "APPROVE_DRAFT" || trans == "APPROVE_FINAL" || trans == "APPROVE_REVISI"){
            $(".btn-edit").hide();
            $("#btn-save").html("<i class='fa fa-check'></i> Approve");
        }
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

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function detail(id){
        var host = firstpath()+"/budgeting/edit_budgeting.action?status="+status+"&id="+id+"&trans="+trans;
        post(host, form);
    }

    function refresh() {
        var host = firstpath()+"/budgeting/add_budgeting.action?status="+status+"&tipe=detail";
        post(host, form);
    }

    function setNullToString(params){
        if (params == null){
            return " - ";
        } else {
            return params;
        }
    }

    function initForm() {
        var host = firstpath()+"/budgeting/initForm_budgeting.action";
        post(host);
    }

    function saveBudgeting() {
        BudgetingAction.saveBudgeting(status, tahun, unit, tipe, trans, function (response) {
            if (response.status == "success"){
                initForm();
            } else {
                $("#alert-error").show().fadeOut(5000);
                $("#error-msg").text(response.msg);
            }
        })

    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>