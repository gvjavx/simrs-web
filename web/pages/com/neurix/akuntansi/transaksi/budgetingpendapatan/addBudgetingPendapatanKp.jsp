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
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgPendapatanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>

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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Budgeting Pendapatan</h3>
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
                                            <s:if test='budgeting.flagKp == "Y"'>
                                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                <s:select list="#initComboBranch.listOfComboBranch" id="sel-unit" name="budgeting.branchId"
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select Value]" cssClass="form-control" onchange="changeAction(this.value)"/>

                                            </s:if>
                                            <s:else>
                                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                <s:select list="#initComboBranch.listOfComboBranch" name="budgeting.branchId"
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select Value]" cssClass="form-control" onchange="changeAction(this.value)" disabled="true"/>
                                                <%--<input type="hidden" id="sel-unit" name="budgeting.branchId"/>--%>
                                                <s:hidden id="sel-unit" name="budgeting.branchId"/>
                                            </s:else>
                                        </div>
                                    </div>

                                    <%--<div class="row">--%>
                                        <%--<label class="control-label col-sm-2">Tipe Budgeting</label>--%>
                                        <%--<div class="col-sm-2">--%>
                                            <%--<select class="form-control" id="sel-tipe">--%>
                                                <%--<option value="tahunan">Tahunan</option>--%>
                                                <%--<option value="bulanan">Bulanan</option>--%>
                                                <%--<option value="semester">Semester</option>--%>
                                                <%--<option value="quartal">Quartal</option>--%>
                                            <%--</select>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <input type="hidden" id="add-coa-tipe" value="investasi" name="budgeting.tipeBudgeting"/>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 col-md-offset-5" style="margin-top: 10px">
                                    <button class="btn btn-primary" onclick="choose()"><i class="fa fa-arrow-right"></i> Choose</button>
                                </div>
                            </div>
                        </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i>
                            List Parameter Budgeting Pendapatan <strong><span id="label-tahun"></span> - <span id="label-branch"></span></strong>
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
                                <table class="table table-bordered table-striped tree">
                                    <thead id="head-budgeting">
                                    <tr bgcolor="#90ee90">
                                        <%--<td style="width: 20%">COA</td>--%>
                                        <td align="">Parameter Budgeting</td>
                                        <td align="center">Action</td>
                                        <%--<td align="center">Action</td>--%>
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
                                <button class="btn btn-success" id="btn-save" onclick="saveAdd()"><i class="fa fa-arrow-check"></i> Save </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-edit"></i> Edit Nilai Budgeting
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                    <label class="col-md-2 col-md-offset-1">Jenis Investasi</label>
                        <div class="col-md-6">
                        <input type="text" class="form-control" id="nama-coa">
                            <script>
                                $(document).ready(function() {
                                    var tipe = $("#add-coa-tipe").val();
                                    var functions, mapped;
                                    $('#nama-coa').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};
                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            BudgetingAction.getListKodeRekeningByLevel(query, tipe, function (listdata) {
                                                data = listdata;
                                            });
                                            $.each(data, function (i, item) {
                                                var labelItem = item.namaKodeRekening;
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
                                            $('#coa').val(selectedObj.kode);
                                            return selectedObj.nama;
                                        }
                                    });
                                });
                            </script>

                            <input type="hidden" id="rekeningid">
                            <input type="hidden" id="namacoa">
                            <input type="hidden" id="parentid">
                            <input type="hidden" id="coa">
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

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_conf"><i class="fa fa-arrow-right"></i> Yes
                </button>
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

    var listOfCoa = [];
    $( document ).ready(function() {
        chekTipe();
    });

    var tipe = '<s:property value="budgeting.tipe"/>';
    var flagDisable = '<s:property value="budgeting.flagDisable"/>';

    function chekTipe() {
        if ("Y" == flagDisable){
            $("#sel-tipe").attr('readonly',true);
            $("#sel-tipe").val(tipe);
        }
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
        if (tipe == "close"){
            $("#modal-loading-dialog").modal('hide');
            $("#modal-success-dialog").modal('hide');
        }
    }

    function choose() {
        var tahun = $("#sel-tahun").val();
        var branch = $("#sel-unit").val();
        var labelBranch = $("#sel-unit option:selected").text();

        showDialog('loading');
        dwr.engine.setAsync(true);
        BgPendapatanAction.getListParameterBudgeting("PDT", function (list) {
            dwr.engine.setAsync(false);
            showDialog("success");

            var str = "";
            $.each(list, function (i, item) {
                str += "<tr>" +
                        "<td>"+item.nama+"</td>" +
                        "<td align='center'>" +
                        "<button class='btn btn-sm btn-primary' onclick=\"edit(\'"+item.id+"\',\'"+branch+"\',\'"+tahun+"\')\"><i class='fa fa-edit'></i></button> " +
                        "<button class='btn btn-sm btn-success'><i class='fa fa-search'></i></button>" +
                        "</td>" +
                        "</tr>";
            });
            $("#body-budgeting").html(str);
            $("#label-tahun").text(tahun);
            $("#label-branch").text(labelBranch);
            $('#ok_con').on('click', function () {
                showDialog('close');
            });
        });
    }

    function edit(idParam, branch, tahun) {
        $("#modal-edit").modal('show');
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
        var host = firstpath()+"/bgpendapatan/initForm_bgpendapatan.action";
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
            alert("COA Harus Diisi !");
        }
    }

    function refreshAdd() {
        var host = firstpath()+"/bgpendapatan/add_bgpendapatan.action?status=add";
        post(host);
    }

    function saveAdd() {
        var tahun = $("#sel-tahun").val();
        var unit = $("#sel-unit").val();
        var tipe = $("#sel-tipe").val();

        BudgetingAction.checkTransaksiBudgeting(unit, tahun, function(response){

            if (response.branchId == null && response.tahun == null){

                BudgetingAction.checkNilaiDasarByTahun(tahun, function(flag){

                    if (flag == "Y"){
                        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };
                        var host = firstpath()+"/bgpendapatan/add_bgpendapatan.action?status=add&tipe=detail&trans=ADD_DRAFT";
                        post(host, form);
                    } else {
                        alert("Belum Ada Nilai Dasar untuk Tahun "+ tahun);
                    }
                });
            } else {
                alert("Data Sudah Ada. di tahun "+response.tahun);
            }
        });

//        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };
//        var host = firstpath()+"/budgeting/add_budgeting.action?status=add&tipe=detail&trans=ADD_DRAFT";
//        post(host, form);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>