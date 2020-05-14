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

        .treegrid-collapsed {
            background-color: #bfbfbf;
        }
        .treegrid-expanded {
            background-color: #e6e6e6;
        }

        .treegrid-indent {width:16px; height: 16px; display: inline-block; position: relative;}

        .treegrid-expander { width:16px; height: 16px; display: inline-block; position: relative; cursor: pointer;}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
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

    </script>

    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
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
                                                <option value="bulanan">Bulanan</option>
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
                            <strong><span id="label-trans"></span></strong>
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
                            <div class="col-md-12">
                                <table class="table table-bordered table-striped tree" style="font-size: 12px;">
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
                                        <s:if test='budgeting.tipe == "bulanan"'>
                                            <td align="center">Total</td>
                                            <td align="center">Januari</td>
                                            <td align="center">Februari</td>
                                            <td align="center">Maret</td>
                                            <td align="center">April</td>
                                            <td align="center">Mei</td>
                                            <td align="center">Juni</td>
                                            <td align="center">Juli</td>
                                            <td align="center">Agustus</td>
                                            <td align="center">September</td>
                                            <td align="center">Oktober</td>
                                            <td align="center">November</td>
                                            <td align="center">Desember</td>
                                            <td align="center">Selisih</td>
                                        </s:if>
                                        <td align="center">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body-budgeting">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-warning" onclick="initForm()"><i class="fa fa-arrow-left"></i> Back</button>
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
    $( document ).ready(function() {

        console.log("hasil >>> "+unit+tahun+tipe);

        $("#sel-tipe").val(tipe);
        $("#sel-tahun").val(tahun);
        $("#sel-unit").val(unit);

        if (trans == "APPROVE_DRAFT" || trans == "APPROVE_FINAL" || trans == "APPROVE_REVISI"){
//            $(".btn-edit").hide();
            $("#btn-save").html("<i class='fa fa-check'></i> Approve");
        }

        var met = trans.replace("_", " ");
        $("#label-trans").text(met);
        search();
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

    function search() {
        var data = [];
        var data2 = [];
        dwr.engine.setAsync(true);
        BudgetingAction.getListOfCoaBudgetingSession(function (response) {

            console.log(response);
            data = response;
            data2 = new Array();
            $.each(data, function(i,item){
                console.log(item.rekeningId);
                data2.push({_id : item.rekeningId, level : item.level,  nama : item.namaKodeRekening, parent : item.parentId, coa : item.kodeRekening,
                    nilaiTotal : item.nilaiTotal, quartal1 : item.quartal1, quartal2: item.quartal2, quartal3 : item.quartal3, quartal4 : item.quartal4,
                    semester1 : item.semester1, semester2:item.semester2, stLevel: item.stLevel, selisih : item.selisih,
                    januari : item.januari, februari : item.februari, maret : item.maret, april : item.april, mei : item.mei, juni : item.juni,
                    juli : item.juli, agustus : item.agustus, september : item.september, oktober : item.oktober, november : item.november, desember : item.desember
                });

            });
            function hierarhySort(hashArr, key, result) {
                if (hashArr[key] == undefined){
                    //level--;
                    return;
                }else{
                    var arr = [] ;
                    arr  = hashArr[key];
                }
                for (var i=0; i<arr.length; i++) {
                    result.push(arr[i]);
                    hierarhySort(hashArr, arr[i]._id, result);
                }
                return result;
            }
            var hashArr = {};
            for (var i=0; i<data2.length; i++) {
                if (hashArr[data2[i].parent] == undefined) {
                    hashArr[data2[i].parent] = [];
                }
                hashArr[data2[i].parent].push(data2[i]);
            }

            var strList = "";
            for(i = 0 ; i < data2.length ; i++){
                if(data2[i].parent == "-"){
                    strList += '<tr style="font-size: 10px;" class=" treegrid-' + data2[i]._id+ '">' +
                        '<td >' + data2[i].coa + '</td>' +
                        '<td >' + data2[i].nama + '</td>' + barisNilai(data2[i]) +
                        "<td align='right'>"+formatRupiah(data2[i].selisih)+"</td>"+
                        "<td align='center'>"+buttonEdit(data2[i]._id, data2[i].stLevel)+"</td>"+
                        "</tr>";
                } else {
                    strList += '<tr style="font-size: 10px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                        + '<td style="border: 2px solid black;">' +
                        '<td >' + data2[i].coa + '</td>' +
                        '<td >' + data2[i].nama + '</td>' + barisNilai(data2[i]) +
                        "<td align='right'>"+formatRupiah(data2[i].selisih)+"</td>"+
                        "<td align='center'>"+buttonEdit(data2[i]._id, data2[i].stLevel)+"</td>"+
                        "</tr>";
                }
            }
            $("#body-budgeting").html(strList);
            $('.tree').treegrid({
                expanderExpandedClass: 'glyphicon glyphicon-minus',
                expanderCollapsedClass: 'glyphicon glyphicon-plus'
            });
        });
    }

    function barisNilai(data){

        var str = "";
        if (tipe == "semester"){
            str += "<td align='right'>"+formatRupiah(data.nilaiTotal)+"</td>"+
                "<td align='right'>"+formatRupiah(data.semester1)+"</td>"+
                "<td align='right'>"+formatRupiah(data.semester2)+"</td>";
        }
        if (tipe == "quartal"){
            str += "<td align='right'>"+formatRupiah(data.nilaiTotal)+"</td>"+
                "<td align='right'>"+formatRupiah(data.quartal1)+"</td>"+
                "<td align='right'>"+formatRupiah(data.quartal2)+"</td>"+
                "<td align='right'>"+formatRupiah(data.quartal3)+"</td>"+
                "<td align='right'>"+formatRupiah(data.quartal4)+"</td>";

        }
        if (tipe == "tahunan"){
            str += "<td align='right'>"+formatRupiah(data.nilaiTotal)+"</td>";
        }
        if (tipe == "bulanan"){
            str += "<td align='right'>"+formatRupiah(data.nilaiTotal)+"</td>"+
                "<td align='right'>"+formatRupiah(data.januari)+"</td>"+
                "<td align='right'>"+formatRupiah(data.februari)+"</td>"+
                "<td align='right'>"+formatRupiah(data.maret)+"</td>"+
                "<td align='right'>"+formatRupiah(data.april)+"</td>"+
                "<td align='right'>"+formatRupiah(data.mei)+"</td>"+
                "<td align='right'>"+formatRupiah(data.juni)+"</td>"+
                "<td align='right'>"+formatRupiah(data.juli)+"</td>"+
                "<td align='right'>"+formatRupiah(data.agustus)+"</td>"+
                "<td align='right'>"+formatRupiah(data.september)+"</td>"+
                "<td align='right'>"+formatRupiah(data.oktober)+"</td>"+
                "<td align='right'>"+formatRupiah(data.november)+"</td>"+
                "<td align='right'>"+formatRupiah(data.desember)+"</td>";
        }

        return str;
    }

    function buttonEdit(rekeningId, level){

        var str = "";
        if (trans == "APPROVE_DRAFT" || trans == "APPROVE_FINAL" || trans == "APPROVE_REVISI"){
            str = "";
        } else {
            if (level == "5"){
                str = "<button class=\"btn btn-sm btn-primary btn-edit\" onclick=\"detail(\'"+rekeningId+"\')\"><i class=\"fa fa-edit\"></i></button>";
            } else {
                str = "";
            }
        }

        return str;
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>