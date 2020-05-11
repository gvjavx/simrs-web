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
    <script type='text/javascript' src='<s:url value="/dwr/interface/SettingTutupPeriodAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TutuPeriodAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
        });

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
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select Value]" cssClass="form-control" onchange="changeAction(this.value)"/>
                                            </div>
                                        </div>
                                        <%--<div class="row">--%>
                                            <%--<label class="control-label col-sm-2">Status</label>--%>
                                            <%--<div class="col-sm-2">--%>
                                                <%--<select class="form-control" id="sel-status">--%>
                                                    <%--<option value="#">[Select One]</option>--%>
                                                    <%--<option value="DRAFT">DRAFT</option>--%>
                                                    <%--<option value="FINAL">FINAL</option>--%>
                                                    <%--<option value="REVISI">REVISI</option>--%>
                                                <%--</select>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="row">
                                            <label class="control-label col-sm-2">Coa</label>
                                            <div class="col-sm-2">
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
                                                                return selectedObj.kode;
                                                            }
                                                        });
                                                    });
                                                </script>
                                                <input type="hidden" id="rekeningid">
                                            </div>
                                        </div>


                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-md-offset-4" style="margin-top: 10px">
                                        <button class="btn btn-success" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                        <button class="btn btn-success" onclick="add()" id="btn-add" style="display: inline-block;"><i class="fa fa-plus"></i> Add</button>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Action</button>
                                            <button type="button" class="btn btn-success dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu" id="action-menu">
                                            </ul>
                                        </div>
                                        <button class="btn btn-success" onclick=""><i class="fa fa-refresh"></i> Reset</button>
                                    </div>
                                </div>
                            </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> List Data
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
                            <div class="col-md-12">
                                <table class="table table-bordered table-striped">
                                    <thead id="head-budgeting">
                                        <tr bgcolor="#90ee90">
                                            <td>Action</td>
                                            <td style="width: 20%">COA</td>
                                            <td align="center">Keterangan</td>
                                            <td align="center">Nilai Draf</td>
                                            <td align="center">Nilai Final</td>
                                            <td align="center">Nilai Revisi</td>
                                        </tr>
                                    </thead>
                                    <tbody id="body-budgeting">
                                    </tbody>
                                    <input type="hidden" id="index-period"/>
                                    <input type="hidden" id="index-branch"/>
                                    <input type="hidden" id="bulan"/>
                                    <input type="hidden" id="tahun"/>
                                </table>
                            </div>
                        </div>

                        <%--<div class="form-group">--%>
                            <%--<div class="col-md-4 col-md-offset-5">--%>
                                <%--<button class="btn btn-success" style="display: none" id="btn-save" onclick="saveBatasPeriod()"><i class="fa fa-check"></i> Save </button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<%--<div class="modal fade" id="modal-confirm-dialog">--%>
    <%--<div class="modal-dialog modal-sm">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title"><i class="fa fa-info"></i> Confirmation--%>
                <%--</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<h4 class="text-center">Do you want save this record?</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<script type='text/javascript'>

    function changeAction(var1){

        var tahun = $("#sel-tahun").val();
//        var unit = $("#sel-unit").val();
        var unit = var1;

        BudgetingAction.findLastStatus(unit, tahun, function(response){
            var str = "";
            if (response.status != ""){
                if ("ADJUST_REVISI" == response.status){
                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                        "</li><hr>";

                } else if ("APPROVE_REVISI" == response.status){

                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                        "</li><hr>";

                } else if ("EDIT_REVISI" == response.status){
                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                        "</li>";

                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                        "</li>";

                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                        "</li><hr>";
                } else {
                    if ("ADJUST_FINAL" ==  response.status){
                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                            "</li><hr>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                            "</li><hr>";

                    } else if ("APPROVE_FINAL" ==  response.status){
                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                            "</li><hr>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                            "</li><hr>";

                    } else if ("EDIT_FINAL" ==  response.status){
                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                            "</li><hr>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                            "</li><hr>";
                    } else {
                        if ("ADJUST_DRAFT" == response.status){
                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                                "</li><hr>";
                        } else if("APPROVE_DRAFT" == response.status){

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                                "</li><hr>";
                        } else {
                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Draft</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Draft</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                                "</li><hr>";
                        }
                    }
                }
            } else {
                str = "";
                str += "<li>"+
                    "<span>"+
                    "<i class=\"fa fa-info\"></i>Please Add</span>"+
                    "</li><hr>";
            }

            $("#action-menu").html(str);
        });
    }

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


    function add() {

        var tahun = $("#sel-tahun").val();
        var unit = $("#sel-unit").val();

        BudgetingAction.checkTransaksiBudgeting(unit, tahun, function(response){

            if (response.branchId == null && response.tahun == null){
                var host = firstpath()+"/budgeting/add_budgeting.action";
                post(host);
            } else {
                alert("Data Sudah Ada. di tahun "+response.tahun);
            }
        });
    }

    function search() {
        var tahun = $("#sel-tahun").val();
        var unit = $("#sel-unit").val();
        var status = $("#sel-status").val();
        var rekeningid = $("#rekeningid").val();

        if (unit != ""){
            var arr = [];
            arr.push({
                "tahun":tahun,
                "unit":unit,
                "status":"",
                "coa":rekeningid
            });

            var strJson = JSON.stringify(arr);
            BudgetingAction.getSearchListBudgeting(strJson, function (response) {
                if (response.status == "error"){
                    $("#alert-error").show().fadeOut(5000);
                    $("#error-msg").text(response.msg);
                } else {

                    var tipe = "";
                    var strList = "";
                    $.each(response.list, function (i, item) {
                        strList += "<tr>" +
                            "<td align='center'>"+actionView(item.idBudgeting, item.level)+"</td>"+
                            "<td>"+item.kodeRekening+"</td>"+
                            "<td>"+item.namaKodeRekening+"</td>"+
                            "<td>"+formatRupiah(item.nilaiDraf)+"</td>"+
                            "<td>"+formatRupiah(item.nilaiFinal)+"</td>"+
                            "<td>"+formatRupiah(item.nilaiRevisi)+"</td>"+
                            "</tr>";
                    });
                }

                $("#body-budgeting").html(strList);
            })
        } else {
            alert("Pilih Unit Terlebih Dahulu")
        }

    }

    function actionView(var1, var2) {
        var str = "";
        if (var2 == "5"){
        str = "<button class='btn btn-sm btn-success' onclick='"+view(var1)+"'><i class='fa fa-search'></i></button>";
        }
        return str;
    }

    function view(var1) {

    }

    function saveTutup(unit, tahun, bulan) {

        dwr.engine.setAsync(true);

        $("#btn-tutup-"+unit).hide();
        $("#btn-lock-"+unit).hide();
        $("#load-save-"+unit).text("Processing Tutup Period ... ");

        TutuPeriodAction.saveTutupPeriod(unit, tahun, bulan, function(response){
            dwr.engine.setAsync(false);
            if (response.status == "error"){
               searchPeriod();
               $("#alert-error").show().fadeOut(5000);
               $("#error-msg").text(response.msg);
           } else {
               searchPeriod();
               $("#btn-tutup-"+unit).show();
               $("#btn-lock-"+unit).show();
               $("#alert-error").hide();
               $("#alert-success").show().fadeOut(5000);

           }
       });
    }

    function saveLock(unit, tahun, bulan){
        TutuPeriodAction.saveLockPeriod(unit, tahun, bulan, function(response){
            if (response.status == "error"){
                $("#alert-error").show().fadeOut(5000);
                $("#error-msg").text(response.msg);
            } else {
                $("#alert-error").hide();
                $("#alert-success").show().fadeOut(5000);

                searchPeriod();
            }
        });
    }

    function setNullToString(params){
        if (params == null){
            return " - ";
        } else {
            return params;
        }
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function action(var1){

        var unit    = $("#sel-unit").val();
        var tahun   = $("#sel-tahun").val();

        if ((unit && tahun) != ""){

            var form = {"budgeting.branchId":unit, "budgeting.tahun":tahun};
            var host = firstpath()+"/budgeting/edit_budgeting.action?status=edit&trans="+var1;
            post(host, form);
        } else {
            alert("Pilih Unit dan Tahun Dulu.")
        }

    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>