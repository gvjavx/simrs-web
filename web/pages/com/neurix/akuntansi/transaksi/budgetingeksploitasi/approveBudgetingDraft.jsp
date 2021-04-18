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

        /*--body { background-color:#fafafa; font-family:'Open Sans';}*/
        /*.container { margin:150px auto;}*/
        .treegrid-collapsed {
            background-color: #bfbfbf;
        }
        .treegrid-expanded {
            background-color: #e6e6e6;
        }

        .treegrid-indent {width:16px; height: 16px; display: inline-block; position: relative;}

        .treegrid-expander { width:16px; height: 16px; display: inline-block; position: relative; cursor: pointer;}

        /*.treegrid-expander-expanded{background-image: url(collapse.png); }
        .treegrid-expander-collapsed{background-image: url(expand.png);}*/
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SettingTutupPeriodAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TutuPeriodAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgEksploitasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgPendapatanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgNominasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgInvestasiAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
//            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
//            $('#pembayaran_open').addClass('menu-open');
//            changeAction('');
            getSelectTahun('sel-tahun')
        });

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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Approve Budgeting </h3>
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
                                                </select>
                                            </div>
                                        </div>
                                        <input type="hidden" id="rekeningid">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-md-offset-5" style="margin-top: 10px">
                                        <button class="btn btn-primary" onclick="search()"><i class="fa fa-arrow-right"></i> Pilih</button>
                                        <%--<s:if test='budgeting.flagKp == "Y"'>--%>
                                            <%--<button class="btn btn-primary" onclick="add()" id="btn-add"><i class="fa fa-plus"></i> Add</button>--%>
                                        <%--</s:if>--%>
                                        <%--<div class="btn-group">--%>
                                            <%--<button type="button" class="btn btn-primary"><i class="fa fa-plus"></i> Action</button>--%>
                                            <%--<button type="button" class="btn btn-primary dropdown-toggle"--%>
                                                    <%--data-toggle="dropdown" style="height: 34px">--%>
                                                <%--<span class="caret"></span>--%>
                                                <%--<span class="sr-only">Toggle Dropdown</span>--%>
                                            <%--</button>--%>
                                            <%--<ul class="dropdown-menu" role="menu" id="action-menu">--%>
                                            <%--</ul>--%>
                                        <%--</div>--%>
                                        <button class="btn btn-danger" onclick="reset()"><i class="fa fa-refresh"></i> Reset</button>
                                    </div>
                                </div>
                            </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> List Data Draft Budgeting :
                        <strong><span id="label-tahun"></span></strong>
                        </h3>
                    </div>
                    <div class="box-body">

                        <%--<div class="alert alert-info alert-dismissable" id="alert-info">--%>
                        <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
                        <%--<strong>Info!</strong> Pilih Priode Kemudian Choose--%>
                        <%--</div>--%>

                        <div class="alert alert-success alert-dismissable" id="alert-success" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <center><h5><strong>Loading ...</strong> Mencari data </h5></center>
                        </div>

                        <div class="alert alert-warning alert-dismissable" id="alert-error" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Error!</strong><span id="error-msg"></span>
                        </div>


                        <div id="body-budgeting">

                        </div>

                        <%--<div class="row">--%>
                            <%--<div class="col-md-8 col-md-offset-2">--%>
                                <%--<table class="tree table table-bordered table-striped">--%>
                                    <%--<thead id="head-budgeting">--%>
                                        <%--<tr bgcolor="#90ee90">--%>
                                            <%--<td align="">Branch</td>--%>
                                            <%--<td align="center">Nilai Total</td>--%>
                                            <%--<td>Action</td>--%>
                                        <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody id="body-budgeting" style="font-size: 13px">--%>
                                    <%--</tbody>--%>
                                    <%--&lt;%&ndash;<input type="hidden" id="index-period"/>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<input type="hidden" id="index-branch"/>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<input type="hidden" id="bulan"/>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<input type="hidden" id="tahun"/>&ndash;%&gt;--%>
                                <%--</table>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <div class="form-group">
                            <div class="col-md-12" align="center" id="body-btn-save">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> View Budgeting
                </h4>
            </div>
            <div class="modal-body">
                <h4 id="label-view-budgeting" style="margin: auto"></h4>
                <br>
                <div id="body-view-budgeting"></div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> View Detail Budgeting
                </h4>
            </div>
            <div class="modal-body">
                <h4 id="label-view-budgeting-detail" style="margin: auto"></h4>
                <br>
                <div id="body-view-budgeting-detail"></div>
                <input type="hidden" id="jenis-budgeting">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-loading-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Saving ...
                </h4>
            </div>
            <div class="modal-body">
                <div id="waiting-content" style="text-align: center">
                    <h4>Please don't close this window, server is processing your request ...</h4>
                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0"
                         style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                </div>

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_waiting">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_waiting"></p>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Success
                </h4>
            </div>
            <div class="modal-body" style="text-align: center">
                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                     name="icon_success">
                Record has been saved successfully.
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-pengadaan">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Data Pengadaan </h4>
            </div>
            <div class="modal-body">
                <span id="label-tipe"></span> <span id="label-periode"></span>
                <br>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-12" id="body-list-pengadaan">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>


<script type='text/javascript'>

//    function changeAction(var1){
//
//        var tahun = $("#sel-tahun").val();
//        var unit = "";
//        if (var1 == null || var1 == "")
//            unit = $("#sel-unit").val();
//        else
//            unit = var1;
//        console.log(unit);
//
//        if (unit != null || unit != ""){
//            BudgetingAction.findLastStatus(unit, tahun, "eksploitasi", function(response){
//                var str = "";
//                if (response.status != ""){
//                    if ("ADJUST_REVISI" == response.status){
//                        str += "<li>"+
//                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                            "</li><hr>";
//
//                    } else if ("APPROVE_REVISI" == response.status){
//
//                        str += "<li>"+
//                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                            "</li><hr>";
//
//                    } else if ("EDIT_REVISI" == response.status){
//                        str += "<li>"+
//                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                            "</li>";
//
//                        str += "<li>"+
//                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                            "</li>";
//
//                        str += "<li>"+
//                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                            "</li><hr>";
//                    } else {
//                        if ("ADJUST_FINAL" ==  response.status){
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
//                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
//                                "</li><hr>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                                "</li><hr>";
//
//                        } else if ("APPROVE_FINAL" ==  response.status){
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
//                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
//                                "</li><hr>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                                "</li><hr>";
//
//                        } else if ("EDIT_FINAL" ==  response.status){
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
//                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
//                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
//                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
//                                "</li><hr>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                                "</li>";
//
//                            str += "<li>"+
//                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                                "</li><hr>";
//                        } else {
//                            if ("ADJUST_DRAFT" == response.status){
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
//                                    "</li><hr>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
//                                    "</li><hr>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                                    "</li><hr>";
//                            } else if("APPROVE_DRAFT" == response.status){
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
//                                    "</li><hr>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
//                                    "</li><hr>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                                    "</li><hr>";
//                            } else {
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_DRAFT')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Draft</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_DRAFT')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Draft</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
//                                    "</li><hr>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
//                                    "</li><hr>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
//                                    "</li>";
//
//                                str += "<li>"+
//                                    "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
//                                    "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
//                                    "</li><hr>";
//                            }
//                        }
//                    }
//                } else {
//                    str = "";
//                    str += "<li>"+
//                        "<span>"+
//                        "<i class=\"fa fa-info\"></i>Please Add</span>"+
//                        "</li><hr>";
//                }
//
//                $("#action-menu").html(str);
//            });
//        }
//    }

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
        if(angka != null && angka != ''){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            if (angka > 0){
                return ribuan;
            } else {
                return '<span style="color: red"> - '+ribuan+'</span>';
            }
        }else{
            return 0;
        }
    }

    function formatSelisih(angka) {
        if(angka != null && angka != ''){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');

            if (angka < 0){
                return "<span style='color: indianred'> - "+ribuan+"</span>";
            } else {
                return "<span style='color: darkgreen'> + "+ribuan+"</span>";
            }

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
        var host = firstpath()+"/bgeksploitasi/add_bgeksploitasi.action";
        post(host);
    }

    function reset() {
        var host = firstpath()+"/bgeksploitasi/initForm_bgeksploitasi.action";
        post(host);
    }


    function search() {
        var tahun = $("#sel-tahun").val();
        $("#label-tahun").text(tahun);
        BgEksploitasiAction.getListBranchBudgeting(tahun, function (list) {

            var str = '';
            $.each(list, function (i, item) {
            str +=
                    '<div class="row">' +
                        '<div class="col-md-12">' +
                            '<h4 id="branch-name-'+item.branchId+'">'+item.branchName+'</h4>' +
                            '<table class="table table-bordered table-striped">' +
                                '<thead id="head-budgeting">'+
                                    '<tr>'+
                                    '<td align="">Jenis</td>'+
                                    '<td align="right">Nilai Total</td>' +
                                    '<td align="center" width="100px">Action</td>' +
                                    '</tr>' +
                                '</thead>' +
                                '<tbody id="body-data-budgeting" style="font-size: 13px">';

                                BgEksploitasiAction.getJenisBudgeting(tahun, item.branchId, function (datas) {

                                    $.each(datas, function (n, data) {
                                        str += '<tr>' +
                                                '<td>'+ warnaLabel(data.idJenisBudgeting, data.nama) +'</td>' +
                                                '<td align="right">'+ formatRupiah(data.nilaiTotal) +'</td>' +
                                                '<td align="center">'+ showBtn(data.idJenisBudgeting, item.branchId) +'</td>' +
                                                '</tr>';
                                    });
                                });

                         str += '</tbody>' +
                            '</table>' +
                        '</div>' +
                    '</div>';
            });

            BgEksploitasiAction.checkIsAvaliable(tahun, function (flag) {
                if ("N" == flag){
                    $("#body-btn-save").html('<button class="btn btn-success" id="btn-save" onclick="saveApprove()"><i class="fa fa-check"></i> Approve </button>');
                }
                if ("Y" == flag){
                    $("#body-btn-save").html('<div class="alert alert-success" align="center" style="width: 65%">Telah Diapprove <i class="fa fa-check"><i/></div>');
                }
            });
            $("#body-budgeting").html(str);
        })
    }

    function warnaLabel(id, label){
        if ("rugi" == id){
            return '<span style="color: red;">'+label+'</span>';
        } else {
            return '<span>'+label+'</span>';
        }
    }

    function showBtn(id, branchId){
        if ("rugi" != id && "laba" != id){
            return '<buttton class="btn btn-sm btn-default" onclick="viewDetail(\''+id+'\', \''+branchId+'\')"><i class="fa fa-search"></i> View</buttton>';
        }
        return "";
    }

    function viewDetail(id, branchId) {
        $("#modal-view").modal('show');
        var branchName = $("#branch-name-"+branchId).text();
        $("#label-view-budgeting").text(branchName);
        var tahun = $("#sel-tahun").val();

        if (id == "INV"){
            BgInvestasiAction.getListKodeRekeningInParameterBudgeting(id, tahun, branchId, function (res) {
                var str = '<table class="table table-bordered table-striped" style="font-size: 13px">' +
                    '<thead>' +
                    '<tr>' +
                    '<td>Item Investasi / Pengadaan</td>' +
                    '<td align="right">Nilai</td>' +
                    '<td align="center" width="100px">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';

                $.each(res, function (i, item) {
                    str += '<tr>' +
                        '<td>'+item.namaKodeRekening+'</td>' +
                        '<td align="right">'+formatRupiah(item.nilaiTotal)+'</td>' +
                        '<td align="center" width="150px"><button class="btn btn-sm btn-default" onclick="viewDataDetail(\''+id+'\',\''+branchId+'\',\''+tahun+'\',\''+item.rekeningId+'\')"><i class="fa fa-search"></i> View</button></td>' +
                        '</tr>';
                });

                str += '</tbody>' +
                    '</table>';

                $("#body-view-budgeting").html(str);
            });
        } else if (id == "BYA"){

        } else {
            BgEksploitasiAction.getListKategoriBudgeting(tahun, branchId, id, function (res) {
                var str = '<table class="table table-bordered table-striped" style="font-size: 13px">' +
                    '<thead>' +
                    '<tr>' +
                    '<td>Nama</td>' +
                    '<td align="right">Nilai</td>' +
                    '<td align="center" width="100px">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';

                $.each(res, function (i, item) {
                    str += '<tr>' +
                        '<td>'+item.nama+'</td>' +
                        '<td align="right">'+formatRupiah(item.nilaiTotal)+'</td>' +
                        '<td align="center" width="150px"><button class="btn btn-sm btn-default" onclick="viewDataDetail(\''+id+'\',\''+branchId+'\',\''+tahun+'\',\''+item.idKategoriBudgeting+'\')"><i class="fa fa-search"></i> View</button></td>' +
                        '</tr>';
                });

                str += '</tbody>' +
                    '</table>';

                $("#body-view-budgeting").html(str);
            });
        }
    }

    function viewDataDetail(idJenis, unit, tahun, rekeningId) {
        $("#modal-view-detail").modal('show');
        $("#jenis-budgeting").val(idJenis);
        if ("PDT" == idJenis){
            BgPendapatanAction.getListMasterBudgeting(idKategori, unit, tahun, "DRAFT", function (list) {
                var str = '<table class="table table-bordered table-striped">' +
                    '<thead>' +
                    '<tr>' +
                    '<td>Master</td>' +
                    '<td align="right">Nilai</td>' +
                    '<td align="center">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';

                $.each(list, function (i, item) {
                    str += '<tr>' +
                        '<td id="label-master-'+item.masterId+'">'+item.namaMaster+'</td>' +
                        '<td align="right">'+ formatRupiah( item.nilaiTotal )+'</td>' +
                        '<td align="center" id="btn-span-'+i+'"><button class="btn btn-sm btn-success" onclick="spanRowPdt(\''+i+'\', \''+item.masterId+'\',\''+idKategori+'\')"><i class="fa fa-plus"></i></button></td>' +
                        '</tr>' +
                        '<tr style="display: none" id="row-master-'+i+'">' +
                        '<td colspan="3" id="body-divisi-'+i+'">' +
                        '</td>' +
                        '</tr>';
                });

                str += '</tbody>' +
                    '</table>';

                $("#body-view-budgeting-detail").html(str);
            });
        } else if ("INV" == idJenis){
            BgInvestasiAction.getListInvestasiByRekeningId(idJenis, rekeningId, 'all', tahun, unit, function (list) {
                var str = '<table class="table table-bordered table-striped" style="font-size: 13px">' +
                    '<thead>' +
                    '<tr>' +
                    '<td>Investasi / Pengadaan</td>' +
                    '<td align="right">Nilai</td>' +
                    '<td align="center" width="100px">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';

                $.each(list, function (i, item) {

                    str += "<tr>" +
                        "<td>"+item.nama+"</td>" +
                        "<td align='right'>"+formatRupiah(item.nilaiTotal)+"</td>" +
                        "<td align='center'><button class='btn btn-sm btn-default'><i class='fa fa-search'></i> View</button></td>" +
                        "</tr>";

//                    str += '<tr>' +
//                        '<td id="label-divisi-'+item.divisiId+'">' + item.namaDivisi + '</td>' +
//                        '<td align="right">' + formatRupiah(item.nilaiTotal) + '</td>' +
//                        '<td align="center" id="btn-span-' + i + '"><button class="btn btn-sm btn-success" onclick="spanRow(\'' + i + '\', \'' + item.divisiId + '\', \''+idKategori+'\')"><i class="fa fa-plus"></i></button></td>' +
//                        '</tr>' +
//                        '<tr style="display: none" id="row-master-' + i + '">' +
//                        '<td colspan="3" id="body-divisi-' + i + '">' +
//                        '</td>' +
//                        '</tr>';
                });

                str += '</tbody>' +
                    '</table>';


                $("#body-view-budgeting-detail").html(str);
            });
        } else if ("BYA" == idJenis){
            BgNominasiAction.getListDivisiBudgeting(idKategori, idJenis, unit, tahun, "DRAFT", function (list) {
                var str = '<table class="table table-bordered table-striped">' +
                    '<thead>' +
                    '<tr>' +
                    '<td>Divisi</td>' +
                    '<td align="right">Nilai</td>' +
                    '<td align="center">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';
                $.each(list, function (i, item) {
                    str += '<tr>' +
                        '<td id="label-divisi-'+item.divisiId+'">' + item.namaDivisi + '</td>' +
                        '<td align="right">' + formatRupiah(item.nilaiTotal) + '</td>' +
                        '<td align="center" id="btn-span-' + i + '"><button class="btn btn-sm btn-success" onclick="spanRow(\'' + i + '\', \'' + item.divisiId + '\', \''+idKategori+'\')"><i class="fa fa-plus"></i></button></td>' +
                        '</tr>' +
                        '<tr style="display: none" id="row-master-' + i + '">' +
                        '<td colspan="3" id="body-divisi-' + i + '">' +
                        '</td>' +
                        '</tr>';
                });

                str += '</tbody>' +
                    '</table>';


                $("#body-view-budgeting-detail").html(str);
            });
        }
    }

    function spanRowPdt(i, master, idKategori) {
        $("#row-master-"+i).show();
        var btn = '<button class="btn btn-sm btn-success" onclick="unSpanRowPdt(\''+i+'\', \''+master+'\',\''+idKategori+'\')"><i class="fa fa-minus"></i></button>';
        $("#btn-span-"+i).html(btn);
        listDivisi(i, master, idKategori);
    }

    function unSpanRowPdt(i, master, idKategori) {
        $("#row-master-"+i).hide();
        var btn = '<button class="btn btn-sm btn-success" onclick="spanRowPdt(\''+i+'\', \''+master+'\',\''+idKategori+'\')"><i class="fa fa-plus"></i></button>';
        $("#btn-span-"+i).html(btn);
    }

    function spanRow(i, divisiId, idKategori) {
        $("#row-master-"+i).show();
        var btn = '<button class="btn btn-sm btn-success" onclick="unSpanRow(\''+i+'\', \''+divisiId+'\', \''+idKategori+'\')"><i class="fa fa-minus"></i></button>';
        $("#btn-span-"+i).html(btn);
        listRekening(i, divisiId, idKategori);
    }

    function unSpanRow(i, divisiId, idKategori) {
        $("#row-master-"+i).hide();
        var btn = '<button class="btn btn-sm btn-success" onclick="spanRow(\''+i+'\', \''+divisiId+'\', \''+idKategori+'\')"><i class="fa fa-plus"></i></button>';
        $("#btn-span-"+i).html(btn);
    }

    function listDivisi(i, masterid, idKategori) {

        BgPendapatanAction.getListDivisiBudgeting(idKategori, masterid, function (list) {

            var str = '';
            $.each(list, function (i, item) {

                str += '<div class="row">' +
                    '<div class="col-md-8 col-md-offset-2">' +
                    '<h4 id="label-divisi-'+item.divisiId+'">' + item.namaDivisi +'</h4>' +
//                    '<button class="btn btn-sm btn-primary" style="float: right;" onclick="showAdd(\''+item.id+'\', \''+item.divisiId+'\', \''+masterid+'\')"><i class="fa fa-plus"></i> Tambah</button>' +
                    '<table class="table table-bordered table-striped">' +
                    '<thead id="head-budgeting">' +
                    '<tr bgcolor="#90ee90">' +
                    '<td>Periode</td>' +
                    '<td align="right">Nilai</td>' +
                    //                    '<td align="center">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody id="body-budgeting-data-'+ item.id +'">';

                BgPendapatanAction.getListDataParam(item.id, function (listDatas) {

                    $.each(listDatas, function (n, data) {
                        str += '<tr>' +
                            '<td>'+data.periode+'</td>' +
                            '<td align="right">'+ formatRupiah( data.nilaiTotal )+'</td>' +
                            '</tr>';
                    });
                });

                str += '</tbody>' +
                    '</table>' +
                    '</div>' +
                    '</div>' +
                    '<br/>' ;
            });
            $("#body-divisi-"+i).html(str);
        })
    }

    function listRekening(i, divisiId, idKategori) {
        var jenis = $("#jenis-budgeting").val();
        BgInvestasiAction.getListRekeningByDivisi(idKategori, divisiId, function (list) {
            var str = '';
            $.each(list, function (i, item) {

                str += '<div class="row">' +
                    '<div class="col-md-8 col-md-offset-2">' +
                    '<h4 id="label-head-'+item.idParameter+'">' + item.nama +'</h4>' +
    //                '<button class="btn btn-sm btn-primary" style="float: right;" onclick="showAdd(\''+item.idParameter+'\', \''+item.divisiId+'\', \'INV\')"><i class="fa fa-plus"></i> Tambah</button>' +
                    '<table class="table table-bordered table-striped">' +
                    '<thead id="head-budgeting">' +
                    '<tr bgcolor="#90ee90">' +
                    '<td>Periode</td>' +
                    '<td>Nilai</td>' +
                    '<td align="center">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody id="body-budgeting-data-'+ item.idParameter +'">';

                BgInvestasiAction.getListDataParam(item.idParameter, function (listDatas) {

                    $.each(listDatas, function (n, data) {
                        str += '<tr>' +
                            '<td>'+data.periode+'</td>' +
                            '<td align="right">'+ formatRupiah( data.nilaiTotal )+'</td>' +
                            '<td align="center">';

                        if ("INV" == jenis){
                            str += '<button class="btn btn-sm btn-success" onclick="viewListPengadaan(\''+ data.idNilaiParameter +'\')"><i class="fa fa-search"></i></button>';
                        }

                           str += '</td>' +
                            '</tr>';
                    });
                });

                str += '</tbody>' +
                    '</table>' +
                    '</div>' +
                    '</div>' +
                    '<br/>' ;
            });
            $("#body-divisi-"+i).html(str);
        })
    }

function viewListPengadaan(id) {
    $("#modal-view-pengadaan").modal('show');
    BgInvestasiAction.getListPengadaan(id, function (res) {
        var str = '<table class="table table-bordered table-striped">' +
            '<thead>' +
            '<tr>' +
            '<td>Nama</td>' +
            '<td align="right">Nilai</td>' +
            '<td align="center">Qty</td>' +
            '<td align="right">Total</td>' +
            '</tr>' +
            '</thead>' +
            '<tbody>';
        $.each(res, function (i,item) {
            str += '<tr>' +
                '<td>'+item.nama+'</td>' +
                '<td align="right">'+ formatRupiah(item.nilai) +'</td>' +
                '<td align="center">'+item.qty+'</td>' +
                '<td align="right">'+ formatRupiah(item.nilaiTotal)+'</td>' +
                '</tr>';
        });
        str += '</tbody>' +
            '</table>';
        $("#body-list-pengadaan").html(str);
    });
}


function actionView(var1, var2) {
        var str = "";
        if (var2 == "5"){
        str = "<button class='item-view btn btn-sm btn-success' data='"+var1+"'><i class='fa fa-search'></i></button>";
        }
        return str;
    }

    // view Budgeting
    $(".tree").on('click', ".item-view", function () {
        var id = $(this).attr('data');

        BudgetingAction.view(id, function(response){
            console.log(response);

            $("#tahun-view").text(response.tahun);
            $("#unit-view").text(response.branchName);
            $("#coa-view").text(response.kodeRekening + " - " + response.namaKodeRekening);

            var str = "";
            if (response.budgetingDetailList.length > 0){
                var investasi = false;
                $.each(response.budgetingDetailList, function (i, item) {
                    str += "<tr>"+
                            "<td>"+item.tipe+"</td>";
                        if(item.divisiId == "INVS"){
                            investasi = true;
                            str += "<td class='val-master-id' style='display: none'>"+item.masterId+"</td>"+
                                "<td class='val-master-name' style='display: none'>"+item.masterName+"</td>" +
                                "<td style='display: none'>"+item.divisiId+"</td>";
                        } else {
                            str += "<td class='val-master-id' >"+item.masterId+"</td>"+
                                    "<td class='val-master-name' >"+item.masterName+"</td>"+
                                    "<td>"+item.divisiId+"</td>";
                        }

                    str +=
                            "<td>"+item.divisiName+"</td>"+
                            "<td align='center'>"+item.qty+"</td>"+
                            "<td align='right'>"+ formatRupiah(item.nilai)+"</td>"+
                            "<td align='right'>"+ formatRupiah(item.subTotal)+"</td>"+
                            "<td align='right'>"+ formatRupiah(item.saldoAkhir) +"</td>"+
                            "<td align='right'>"+ formatSelisih(item.selisihSaldoAkhir) +"</td>"+
                            "<td align='center'>" + actionViewPengadaan(investasi, item.idBudgetingDetail, item.tipe) + "</td>"+
                            "</tr>";
                });

                if (investasi){
                    str +=  "<td colspan='4'>Nilai Total</td>"+
                        "<td align='right'>"+ formatRupiah(response.nilaiTotal) +"</td>"+
                        "<td align='right'>"+ formatRupiah(response.saldoAkhir) +"</td>"+
                        "<td align='right'>"+ formatSelisih(response.selisihSaldoAkhir) +"</td>"+
                        "<td></td>"+
                        "</tr>";

                    $("#label-master-id").hide();
                    $("#label-master-name").hide();
                    $("#label-divisi-id").hide();
                    $("#label-divisi-id").text("Id");
                    $("#label-divisi-name").text("Name");
                } else {

                    str +=  "<td colspan='7'>Nilai Total</td>"+
                        "<td align='right'>"+ formatRupiah(response.nilaiTotal) +"</td>"+
                        "<td align='right'>"+ formatRupiah(response.saldoAkhir) +"</td>"+
                        "<td align='right'>"+ formatSelisih(response.selisihSaldoAkhir) +"</td>"+
                        "<td></td>"+
                        "</tr>";

                    $("#label-master-id").show();
                    $("#label-master-name").show();
                    $("#label-divisi-id").show();
                    $("#label-divisi-id").text("Divisi Id");
                    $("#label-divisi-name").text("Divisi Name");
                }
            }

            $("#body-budgeting-view").html(str);
        });

        $("#modal-view").modal('show');
        $("#view-id").val(id);
    });

    function actionViewPengadaan(var1, var2, var3) {
        if (var1){
            return "<button class='item-view btn btn-sm btn-success' onclick=\"viewPengadaan('"+var2+"','"+var3+"')\"><i class='fa fa-bars'></i></button>";
        } else {
            return "";
        }
    }

    function viewPengadaan(var1, var2) {

        $("#modal-view-pengadaan").modal('show');

        var tahun = $("#tahun-view").text();
        var unit = $("#unit-view").text();
        var coa = $("#coa-view").text();

        $("#tahun-view-pengadaan").text(tahun);
        $("#unit-view-pengadaan").text(unit);
        $("#coa-view-pengadaan").text(coa);
        $("#periode-view-pengadaan").text(var2.toUpperCase());

        BudgetingAction.viewPengadaan(var1, function (response) {

            if (response.length > 0){

                var str = "";
                $.each(response, function(i, item){
                    str += "<tr>" +
                        "<td>"+ item.namPengadaan+"</td>" +
                        "<td>"+item.noKontrak+"</td>" +
                        "<td align='right'>"+ item.qty+"</td>" +
                        "<td align='right'>"+ formatRupiah( item.nilai ) +"</td>" +
                        "<td align='right'>"+ formatRupiah( item.subTotal )+"</td>" +
                        "<td align='right'>"+ formatRupiah( item.nilaiKontrak ) +"</td>" +
                        "<td align='right'>"+ formatRupiah( item.realisasi ) +"</td>" +
                        "<td align='right'>"+ formatSelisih( item.selisih ) +"</td>" +
                        "</tr>";
                });

                $("#body-budgeting-view-pengadaan").html(str);
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
            var host = firstpath()+"/bgeksploitasi/edit_bgeksploitasi.action?status=edit&trans="+var1;
            post(host, form);
        } else {
            alert("Pilih Unit dan Tahun Dulu.")
        }
    }

    function initForm() {
        var host = firstpath()+"/bgeksploitasi/initForm_bgeksploitasi.action";
        post(host);
    }

    function saveApprove() {
        var tahun = $("#sel-tahun").val();
        showDialog("loading");
        dwr.engine.setAsync(true);
        BgEksploitasiAction.approveFinal(tahun, function (res) {
            dwr.engine.setAsync(false);
            if (res.status == "success"){
                showDialog("success");
                $('#ok_con').on('click', function () {
                    initForm();
                });
            } else {
                showDialog("error");
                $("#msg_fin_error_waiting").text(res.msg);
            }
        });
    }

function showDialog(tipe) {
    if (tipe == "loading"){
        $("#modal-loading-dialog").modal('show');
    }
    if (tipe == "error"){
        $("#modal-loading-dialog").modal('show');
        $("#waiting-content").hide();
        $("#warning_fin_waiting").show();
    }
    if (tipe == "success"){
        $("#modal-loading-dialog").modal('hide');
        $("#modal-success-dialog").modal('show');
    }
}

function getSelectTahun(idelement) {

    //var str = "<option value=''> - </option>";
    var str = "";
    TutuPeriodAction.getListTahunKedepan('5', function (res) {

        $.each(res, function (i, item) {
            str += "<option value='"+item+"'>" + item + "</option>";
        });

        $("#"+idelement).html(str);
    });
}


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>