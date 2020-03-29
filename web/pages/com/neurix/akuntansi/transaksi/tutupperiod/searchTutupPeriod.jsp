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
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Tutup Period</h3>
                    </div>
                    <div class="box-body">
                        <%--<s:form id="kasirjalanForm" method="post" namespace="/kasirjalan" action="search_kasirjalan.action" theme="simple" cssClass="form-horizontal">--%>
                            <div class="form-group form-horizontal">
                                <label class="control-label col-sm-2 col-sm-offset-1">Periode</label>
                                <div class="col-sm-3">
                                    <select class="form form-control" id="sel-bulan">
                                        <option value="1">Januari</option>
                                        <option value="2">Februari</option>
                                        <option value="3">Maret</option>
                                        <option value="4">April</option>
                                        <option value="5">Mei</option>
                                        <option value="6">Juni</option>
                                        <option value="7">Juli</option>
                                        <option value="8">Agustus</option>
                                        <option value="9">September</option>
                                        <option value="10">Oktober</option>
                                        <option value="11">November</option>
                                        <option value="12">Desember</option>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <select class="form form-control" id="sel-tahun">
                                        <option value="2020">2020</option>
                                        <option value="2021">2021</option>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <button class="btn btn-success" onclick="searchPeriod()"><i class="fa fa-check"></i> Choose</button>
                                </div>
                            </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> List Tutup Period <strong><span id="label-tahun"></span> - <span id="label-bulan"></span></strong> </h3>
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
                                    <thead id="head-period">
                                    <tr bgcolor="#90ee90">
                                        <td>Unit</td>
                                        <td>Tgl Tutup Period</td>
                                        <td>Action</td>
                                        <td>Status</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body-period">
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

<script type='text/javascript'>

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

    function searchPeriod(){

        var tahun = $("#sel-tahun").val();
        var bulan = $("#sel-bulan").val();
        var labebulan = $("#sel-bulan option:selected").text();

        $("#label-bulan").text(labebulan);
        $("#label-tahun").text(tahun);
        $("#bulan").val(bulan);
        $("#tahun").val(tahun);

        BranchAction.getListBranchByArea( function (response) {
            var strBody = "";
            var indexperiod = 0;
            var indexbranch = "";
            if(response.length > 0){
                $.each(response, function (i, item) {

                    TutuPeriodAction.getDataBatasTutupPeriod(item.branchId, tahun, bulan, function (batas) {
                        if ( batas.stTglBatas == null ){
                            strBody += "<tr>" +
                                "<td>"+item.branchName+"</td>" +
                                "<td>"+setNullToString(batas.stTglBatas)+"</td>" +
                                "<td align='center'>" +
                                "<button class='btn btn-primary' onclick=\"saveTutup('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Tutup</button>" +
                                " <button class='btn btn-info' onclick=\"saveLock('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Lock</button>" +
                                "</td>" +
                                "<td align='center'><span class='label label-default'>Belum Set Tgl</span></td>" +
                                "</tr>";
                        } else {

                            if (batas.flagTutup == "Y"){
                                strBody += "<tr>" +
                                    "<td>"+item.branchName+"</td>" +
                                    "<td>"+setNullToString(batas.stTglBatas)+"</td>" +
                                    "<td align='center'>" +
                                    "<button class='btn btn-primary' onclick=\"saveTutup('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Tutup</button>" +
                                    " <button class='btn btn-info' onclick=\"saveLock('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Lock</button>" +
                                    "</td>" +
                                    "<td align='center'><span class='label label-danger'>Sudah Ditutup</span></td>" +
                                    "</tr>";
                            } else if ( batas.flagTutup == "P" ){
                                strBody += "<tr>" +
                                    "<td>"+item.branchName+"</td>" +
                                    "<td>"+setNullToString(batas.stTglBatas)+"</td>" +
                                    "<td align='center'>" +
                                    "<button class='btn btn-primary' onclick=\"saveTutup('"+item.branchId+"','"+tahun+"','"+bulan+"')\">Tutup</button>" +
                                    " <button class='btn btn-info' onclick=\"saveLock('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Lock</button>" +
                                    "</td>" +
                                    "<td align='center'><span class='label label-warning'>Lock Process</span></td>" +
                                    "</tr>";
                            } else if (batas.stTglBatas != formatDate(Date.now())){
                                strBody += "<tr>" +
                                    "<td>"+item.branchName+"</td>" +
                                    "<td>"+setNullToString(batas.stTglBatas)+"</td>" +
                                    "<td align='center'>" +
                                    "<button class='btn btn-primary' onclick=\"saveTutup('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Tutup</button>" +
                                    " <button class='btn btn-info' onclick=\"saveLock('"+item.branchId+"','"+tahun+"','"+bulan+"')\" disabled>Lock</button>" +
                                    "</td>" +
                                    "<td align='center'><span class='label label-default'>Belum Waktu Tutup</span></td>" +
                                    "</tr>";
                            } else {
                                strBody += "<tr>" +
                                    "<td>" + item.branchName + "</td>" +
                                    "<td>" + setNullToString(batas.stTglBatas) + "</td>" +
                                    "<td align='center'>" +
                                    "<button class='btn btn-primary' onclick=\"saveTutup('" + item.branchId + "','" + tahun + "','" + bulan + "')\">Tutup</button>" +
                                    " <button class='btn btn-info' onclick=\"saveLock('" + item.branchId + "','" + tahun + "','" + bulan + "')\">Lock</button>" +
                                    "</td>" +
                                    "<td align='center'><span class='label label-success'>Siap Ditutup</span></td>" +
                                    "</tr>";
                            }
                        }
                    });

                    indexbranch += "_"+item.branchId;
                });

            }
            $("#body-period").html(strBody);
            $("#index-period").val(indexperiod);
            $("#index-branch").val(indexbranch);
        });
    }

    function saveTutup(unit, tahun, bulan) {
        TutuPeriodAction.saveTutupPeriod(unit, tahun, bulan, function(response){
           if (response.status == "error"){
               $("#alert-error").show();
               $("#error-msg").text(response.msg);
           } else {
               $("#alert-error").hide();
               $("#alert-success").show();

               searchPeriod();
           }
       });
    }

    function saveLock(unit, tahun, bulan){
        TutuPeriodAction.saveLockPeriod(unit, tahun, bulan, function(response){
            if (response.status == "error"){
                $("#alert-error").show();
                $("#error-msg").text(response.msg);
            } else {
                $("#alert-error").hide();
                $("#alert-success").show();

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

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>