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
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingNilaiDasarAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
            getSelectTahun('sel-tahun');
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Nilai Dasar </h3>
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
                                        <button class="btn btn-success" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                        <button class="btn btn-primary" onclick="modalAdd()" id="btn-add"><i class="fa fa-plus"></i> Add</button>
                                        <button class="btn btn-danger" onclick="reset()"><i class="fa fa-refresh"></i> Reset</button>
                                    </div>
                                </div>
                            </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <%--<div class="box-header with-border">--%>
                        <%--<h3 class="box-title"><i class="fa fa-th-list"></i> List Data Budgeting :--%>
                        <%--<strong><span id="label-tahun"></span> - <span id="label-unit"></span></strong>--%>
                        <%--</h3>--%>
                    <%--</div>--%>
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

                        <div class="row">
                            <div class="col-md-12" align="center">
                                <table class="tree table table-bordered table-striped" style="width: 40%">
                                    <thead id="head-budgeting">
                                        <tr bgcolor="#90ee90">
                                            <td align="">Keterangan</td>
                                            <td align="center">Action</td>
                                        </tr>
                                    </thead>
                                    <tbody id="body-budgeting" style="font-size: 13px">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> Tambah Nilai Dasar
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12" align="center">
                        <div class="row">
                            <label class="control-label col-sm-4" style="text-align: right">Tahun</label>
                            <div class="col-sm-4">
                                <select type="text" class="form-control" id="sel-add-tahun">

                                </select>
                            </div>
                        </div>
                        <div id="body-param-add">

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="save_add" onclick="save('add')"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> Edit Nilai Dasar
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12" align="center">
                        <div class="row">
                            <label class="control-label col-sm-4" style="text-align: right">Tahun</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="sel-edit-tahun" readonly="true">
                                </select>
                            </div>
                        </div>
                        <div id="body-param-edit">

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-success" id="save_edit" onclick="save('edit')"><i class="fa fa-check"></i> Save</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> View Detail Budgeting
                </h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="view-id">
                <table style="font-size: 15px; margin-bottom: 10px;" class="table">
                    <tbody>
                        <tr>
                            <td width="20%">Tahun</td>
                            <td>:</td>
                            <td id="tahun-view"></td>
                        </tr>
                        <tr>
                            <td>Unit</td>
                            <td>:</td>
                            <td id="unit-view"></td>
                        </tr>
                        <tr>
                            <td>COA </td>
                            <td>:</td>
                            <td id="coa-view"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="table table-bordered table-striped">
                    <thead id="head-budgeting-view" style="font-size: 13px">
                    <tr bgcolor="#90ee90">
                        <td>Periode</td>
                        <td id="label-master-id">Master Id</td>
                        <td align="" id="label-master-name">Master Name</td>
                        <td align="" id="label-divisi-id">Divisi Id</td>
                        <td align="" id="label-divisi-name">Divisi Name</td>
                        <td align="center">Qty</td>
                        <td align="center">Nilai</td>
                        <td align="center">Sub Total</td>
                        <td align="center">Realisasi</td>
                        <td align="center">Selisih</td>
                        <td align="center">Action</td>
                    </tr>
                    </thead>
                    <tbody id="body-budgeting-view" style="font-size: 11px">
                    </tbody>
                </table>
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


<script type='text/javascript'>

    //    $('.yearpicker').yearpicker();
//    $('#sel-tahun').yearpicker();

    var listOfId = [];
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
        var host = firstpath()+"/budgeting/add_budgeting.action";
        post(host);
    }

    function reset() {
        var host = firstpath()+"/budgeting/initForm_budgeting.action";
        post(host);
    }

    function search() {
        var tahun = $("#sel-tahun").val();

        $("#alert-success").show();
        $("#label-tahun").text(tahun);

        dwr.engine.setAsync(true);
        BudgetingNilaiDasarAction.searchByTahun(tahun, function (response) {
            dwr.engine.setAsync(false);
            $("#alert-success").hide();
            if (response.status == "error"){
                $("#alert-error").show().fadeOut(5000);
                $("#error-msg").text(response.msg);
            } else {
                var str = "";
                var list = response.list;
                $.each(list, function (i, item) {
                   str += "<tr>" +
                           "<td>"+ item.keterangan +"</td>" +
                           "<td align='center'><button class='btn btn-success' onclick=\"modalEdit(\'"+item.tahun+"\')\"><i class='fa fa-search'></i></button></td>" +
                           "</tr>";
                });
                $("#body-budgeting").html(str);
            }
        });
    }

    function modalAdd() {
        listOfId = [];
        $("#modal-add").modal('show');
        getSelectTahun('sel-add-tahun');
        BudgetingNilaiDasarAction.getListNilaiDasarAdd(function (list) {
            var str = "";
            $.each(list, function (i, item) {
                str += "<div class=\"row\">"+
                        "<label class=\"control-label col-sm-4\" style=\"text-align: right\">"+item.keterangan+"</label>" +
                        "<div class=\"col-sm-4\">" +
                        "<input type=\"number\" class=\"form-control\" id=\"add_"+item.idNilaiDasar+"\" value=\""+item.nilai+"\"/>" +
                        "</div>" +
                        "</div>";

                listOfId.push({"id":"add_"+item.idNilaiDasar});
            });
            $("#body-param-add").html(str);
            //console.log(str);
            //console.log(listOfId);
        });
    }

    function modalEdit(tahun) {
        listOfId = [];
        $("#modal-edit").modal('show');
        getSelectTahun('sel-edit-tahun');
        BudgetingNilaiDasarAction.getListNilaiDasarEdit(tahun, function (list) {
            var str = "";
            $.each(list, function (i, item) {
                str += "<div class=\"row\">"+
                    "<label class=\"control-label col-sm-4\" style=\"text-align: right\">"+item.keterangan+"</label>" +
                    "<div class=\"col-sm-4\">" +
                    "<input type=\"number\" class=\"form-control\" id=\"edit_"+item.idNilaiDasar+"\" value=\""+item.nilai+"\" readonly/>" +
                    "</div>" +
                    "</div>";

                listOfId.push({"id":"edit_"+item.idNilaiDasar});
            });
            $("#body-param-edit").html(str);
            $("#sel-edit-tahun").val(tahun);
            //console.log(str);
            //console.log(listOfId);
        });
    }



    function save(tipe) {
        //console.log(listOfId);
        var tahun = tipe == "add" ? $("#sel-add-tahun").val() :  $("#sel-edit-tahun").val();
        var listOfParam = [];
        $.each(listOfId, function (i, item) {
            var str = item.id;
            var res = str.split("_");

            var valItem = $("#"+str).val();
            listOfParam.push({"tahun" : tahun, "id_nilai_dasar":res[1], "nilai":valItem});
        });
        //console.log(listOfParam);
        dwr.engine.setAsync(true);
        var strJson = JSON.stringify(listOfParam);
        BudgetingNilaiDasarAction.saveBudgetingNilaiDasar(strJson, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                showDialog("error");
                $("#msg_fin_error_waiting").text(response.message);
            } else {
                showDialog("success");
                $('#ok_con').on('click', function () {
                    $("#modal-success-dialog").modal('hide');
                    $("#modal-add").modal('hide');
                    search();
                });
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