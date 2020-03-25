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
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PlanKegiatanRawatAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_obat').addClass('active');
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
            Rencana Kegiatan Rawat
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Rencana Kegiatan Rawat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <%--<s:form id="permintaanForm" method="post" namespace="/planrawat" action="search_checkup.action"--%>
                                    <%--theme="simple" cssClass="form-horizontal">--%>
                                <div class="form-group form-horizontal">
                                    <div class="row">
                                        <label class="control-label col-sm-4" for="headerCheckup.idPasien">No. RM</label>
                                        <div class="col-sm-4">
                                            <s:textfield id="idPasien" cssStyle="margin-top: 7px"
                                                         name="headerCheckup.idPasien" required="false"
                                                         readonly="false" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group form-horizontal">
                                    <div class="row">
                                        <label class="control-label col-sm-4">Poli</label>
                                        <div class="col-sm-4">
                                            <s:action id="initComboPoli" namespace="/checkup"
                                                      name="getComboPelayanan_checkup"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      list="#initComboPoli.listOfPelayanan" id="idPelayanan"
                                                      name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                      listValue="namaPelayanan"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group form-horizontal">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <button class="btn btn-primary" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                        <%--<sj:submit type="button" cssClass="btn btn-success" formIds="permintaanForm"--%>
                                                   <%--id="search" name="search"--%>
                                                   <%--onClickTopics="showDialogLoading"--%>
                                                   <%--onCompleteTopics="closeDialogLoading">--%>
                                            <%--<i class="fa fa-search"></i>--%>
                                            <%--Search--%>
                                        <%--</sj:submit>--%>
                                        <a type="button" class="btn btn-danger" href="initForm_planrawat.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien dirawat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tgl Masuk</td>
                                <td>Nama Pasien</td>
                                <td>Nama Pelayanan</td>
                                <td>Diagnosa Terakhir</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body-list-plan">
                            <%--<s:iterator value="#session.listOfResult" status="listOfUsers">--%>
                                <%--<tr>--%>
                                    <%--<td><s:property value="noCheckup"/></td>--%>
                                    <%--<td><s:property value="idPasien"/></td>--%>
                                    <%--<td><s:property value="nama"/></td>--%>
                                    <%--<td><s:property value="namaPelayanan"/></td>--%>
                                    <%--<td><s:property value="statusPeriksaName"/></td>--%>
                                    <%--<td align="center">--%>
                                        <%--<img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>" src="<s:url value="/pages/images/search_flat.png"/>"--%>
                                             <%--style="cursor: pointer; width: 25px; height: 25px" onclick="detail_pasien('<s:property value="noCheckup"/>')">--%>
                                        <%--<s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">--%>
                                            <%--<s:param name="id"><s:property value="noCheckup"/></s:param>--%>
                                        <%--</s:url>--%>
                                        <%--<s:a href="%{edit}">--%>
                                            <%--<img border="0" class="hvr-grow" src="<s:url value="/pages/images/edit-flat-new.png"/>"--%>
                                                 <%--style="cursor: pointer; width: 25px; height: 25px">--%>
                                        <%--</s:a>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            <%--</s:iterator>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-view-plan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> View Daftar Rencana Rawat</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered">
                    <thead id="head-view-plan">
                    <tr>
                        <td>Tgl Mulai</td>
                        <td>Created Who</td>
                        <td>Last Update Who</td>
                        <td>Last Update</td>
                        <td>Action</td>
                    </tr>
                    </thead>
                    <tbody id="body-view-plan">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-plan-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> View Detail Rencana Rawat</h4>
            </div>
            <div class="modal-body" id="body-view-plan-detail">
                <%--<table class="table table-striped table-bordered">--%>
                    <%--<thead id="head-view-plan-detail">--%>
                    <%--<tr>--%>
                        <%--<td>Tgl Mulai</td>--%>
                        <%--<td>Created Who</td>--%>
                        <%--<td>Last Update Who</td>--%>
                        <%--<td>Last Update</td>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody id="body-view-plan-detail">--%>
                    <%--</tbody>--%>
                <%--</table>--%>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-plan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Rencana Rawat </h4>
            </div>
            <div class="modal-body">
                <div style="margin-bottom:20px">
                    <button type="button" class="btn btn-success" onclick="addMonVitalSign()">
                        <i class="fa fa-plus"></i> Add Monitoring Vital Sign
                    </button>
                    <%--<button type="button" class="btn btn-info" onclick="showGrafVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">--%>
                        <%--<i class="fa fa-pie-chart"></i> View Graf--%>
                    <%--</button>--%>
                    <button type="button" class="btn btn-success" onclick="addObCairan()">
                        <i class="fa fa-plus"></i> Add Monitoring Cairan
                    </button>
                    <button type="button" class="btn btn-success" onclick="addPemberianObat('parenteral')">
                        <i class="fa fa-plus"></i> Add Obat Parenteral
                    </button>
                    <button type="button" class="btn btn-success" onclick="addPemberianObat('nonparenteral')">
                        <i class="fa fa-plus"></i> Add Obat Non Parenteral
                    </button>
                </div>
                <br>

                <h4> Vital Sign </h4>
                <table class="table table-bordered" style="width: 50%">
                    <thead>
                    <td>Jam</td>
                    <td>Created Who</td>
                    <td>Created Date</td>
                    </thead>
                    <tbody id="body-list-vital-sign">

                    </tbody>
                </table>
                <br>

                <h4> Monitoring Cairan </h4>
                <table class="table table-bordered" style="font-size:11px;">
                    <thead>
                    <td width="10%">Tgl</td>
                    <td>Macam Cairan</td>
                    <td>Melalui</td>
                    <td>Jumlah</td>
                    <td>Jam Mulai</td>
                    <td>Jam Selesai</td>
                    <td>Cek Tambahan Obat</td>
                    <td>Sisa</td>
                    <td>Jam Ukur Buang</td>
                    <td>Dari</td>
                    <td>Balance Cairan</td>
                    <td>Keterangan</td>
                    <td>Created Who</td>
                    </thead>
                    <tbody id="body-list-cairan">

                    </tbody>
                </table>
                <br>

                <h4> Obat Parenteral </h4>
                <table class="table table-bordered">
                    <thead>
                    <td>Nama Obat</td>
                    <td>Cara Pemberian</td>
                    <td>Dosis</td>
                    <td>Skin Test</td>
                    <td>Waktu</td>
                    <td>Keterangan</td>
                    <td>Created Who</td>
                    <td>Created Date</td>
                    </thead>
                    <tbody id="body-list-perenteral">

                    </tbody>
                </table>
                <br>

                <h4> Obat Non Parenteral </h4>
                <table class="table table-bordered">
                    <thead>
                    <td>Nama Obat</td>
                    <td>Dosis</td>
                    <td>Waktu</td>
                    <td>Keterangan</td>
                    <td>Created Who</td>
                    <td>Created Date</td>
                    </thead>
                    <tbody id="body-list-nonparenteral">

                    </tbody>
                </table>
                <br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" data-dismiss="modal"><i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function search() {

        var idPasien = $("#idPasien").val();
        var idPelayanan = $("#idPelayanan").val();

        var arrJson = [];
        arrJson.push({"id_pasien":idPasien, "id_pelayanan":idPelayanan});
        var stJson = JSON.stringify(arrJson);

        PlanKegiatanRawatAction.getSearchKegiatanRawat(stJson, function (response) {
            if (response.length > 0){

                var str = "";
                $.each(response, function (i, item) {
                    str += "<tr>" +
                        "<td>" + item.stCreatedDate + "</td>" +
                        "<td>" + item.namaPasien + "</td>" +
                        "<td>" + item.namaPelayanan + "</td>" +
                        "<td>" + item.diagnosa + "</td>" +
                        "<td align='center'>" +
                        "<button class='btn btn-primary' onclick=\"viewAddPlan('"+item.idDetailCheckup+"')\"><i class='fa fa-plus'></i></button> " +
                        "<button class='btn btn-primary' onclick=\"viewPlan('"+item.idDetailCheckup+"')\"><i class='fa fa-search'></i></button>" +
                        "</td>" +
                        "</tr>";
                });

                $("#body-list-plan").html(str);

            } else {

            }
        })
    }

    function viewPlan(idDetail){
        $("#modal-view-plan").modal('show');

        PlanKegiatanRawatAction.getListPlanKegiatan(idDetail, function (response) {

            var str = "";
            $.each(response, function (i, item) {
                str += "<tr>" +
                    "<td>"+ item.tglMasuk +"</td>" +
                    "<td>"+ item.createdWho +"</td>" +
                    "<td>"+ item.lastUpdateWho +"</td>" +
                    "<td>"+ item.stLastUpdate +"</td>" +
                    "<td><button class='btn btn-primary' onclick=\"viewPlanDetail('"+item.idDetailCheckup+"','"+item.tglMasuk+"')\"><i class='fa fa-search'></i></button></td>" +
                    "</tr>";
            })

            $("#body-view-plan").html(str);

        })
    }

    function viewPlanDetail(idDetail, tglMasuk){
        $("#modal-view-plan-detail").modal('show');
    }

    function viewAddPlan(idDetail) {
        $("#modal-add-plan").modal('show');
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>