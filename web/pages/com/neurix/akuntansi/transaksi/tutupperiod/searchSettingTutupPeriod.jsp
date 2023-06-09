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
            Setting
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Setting Batas Tutup Period</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group form-horizontal">
                            <label class="control-label col-sm-4">Tahun</label>
                            <div class="col-sm-4">
                                <select class="form form-control" id="tahun">
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <button class="btn btn-success" onclick="searchPeriod()"><i class="fa fa-check"></i> Choose</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> List Period <span id="label-tahun"></span></h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                <table id="sortTable" class="table table-bordered table-striped">
                                    <thead id="head-period">
                                    <%--<tr bgcolor="#90ee90" id="head-period"></tr>--%>
                                    </thead>
                                    <tbody id="body-period">
                                    </tbody>
                                    <input type="hidden" id="index-period"/>
                                    <input type="hidden" id="index-branch"/>
                                </table>
                            </div>
                        </div>


                        <div class="alert alert-info alert-dismissable" id="alert-info">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Info!</strong> Pilih Tahun Kemudian Choose
                        </div>

                        <div class="alert alert-success alert-dismissable" id="alert-success" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Success!</strong> Berhasil Menyimpan data
                        </div>

                        <div class="alert alert-warning alert-dismissable" id="alert-error" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Error!</strong><span id="error-msg"></span>
                        </div>

                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-success" style="display: none" id="btn-save" onclick="saveBatasPeriod()"><i class="fa fa-check"></i> Save </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<script type='text/javascript'>

    function searchPeriod(){

        var tahun = $("#tahun").val();

        var arrbodyperiod = [
            {"bulan" : "Januari",   "id" : "1"},
            {"bulan" : "February",  "id" : "2"},
            {"bulan" : "Maret",     "id" : "3"},
            {"bulan" : "April",     "id" : "4"},
            {"bulan" : "Mei",       "id" : "5"},
            {"bulan" : "Juni",      "id" : "6"},
            {"bulan" : "Juli",      "id" : "7"},
            {"bulan" : "Agustus",   "id" : "8"},
            {"bulan" : "September", "id" : "9"},
            {"bulan" : "Oktober",   "id" : "10"},
            {"bulan" : "November",  "id" : "11"},
            {"bulan" : "Desember",  "id" : "12"}
            ];

        BranchAction.getListBranchByArea( function (response) {
            var strHead = "";
            var strBody = "";
            var indexperiod = 0;
            var indexbranch = "";
            var arrbatas = [];
            if(response.length > 0){
                $.each(response, function (i, item) {
                    strHead += "<td>"+item.branchName+"</td>";
                    indexbranch += "_"+item.branchId;
                });

                $.each(arrbodyperiod, function (i, item) {
                    var period = "<td>"+item.bulan+"</td>";
                    var strbranch = "";
                    if(response.length > 0){
                        $.each(response, function (i, itembranch) {
                            strbranch += "<td><input type='date' id='"+item.id+"_"+itembranch.branchId+"' class='form form-control'/></td>";
                            arrbatas.push({"unit":itembranch.branchId, "tahun":tahun, "bulan":item.id, "id":item.id+"_"+itembranch.branchId});
                        });
                    }

                    strBody += "<tr>"+ period + strbranch + "</tr>";
                    indexperiod++;
                });

                $("#btn-save").show();
                $("#alert-info").hide();
            } else {
                $("#btn-save").hide();
            }

            $("#head-period").html("<tr bgcolor=\"#90ee90\"><td>Periode</td>"+strHead+"</tr>");
            $("#body-period").html(strBody);
            $("#index-period").val(indexperiod);
            $("#index-branch").val(indexbranch);
            $("#label-tahun").text(tahun);

//            console.log(arrbatas);
//            console.log("ATAS");
            $.each(arrbatas, function (i, item) {
                SettingTutupPeriodAction.getBatasTutupPeriod(item.unit, item.tahun, item.bulan, function(batas){
                    $("#"+item.id+"").val(batas.stTglBatas);
                    if (batas.flagTutup == "Y"){
                        $("#"+item.id+"").attr("disabled",'disabled');
                    }
//                    console.log(batas);
//                    console.log("#"+item.id+" nilai : "+batas.stTglBatas);
                });
            });
        });
    }

    function saveBatasPeriod() {

       var tahun = $("#tahun").val();
       var indexperiod = $("#index-period").val();
       var indexbranch = $("#index-branch").val();
       var arbranch = indexbranch.split("_");

       var arperiod = [];
       for (i=1; i <= indexperiod; i++){

           for (n=1; n < arbranch.length ; n++){
               var valperiod = $("#"+i+"_"+arbranch[n]).val();
               if (valperiod != ""){
                   arperiod.push({"tahun" : tahun, "bulan" : i, "unit": arbranch[n], "tgl" : valperiod});
               }
           }
       }

       var jsonString = JSON.stringify(arperiod);
       SettingTutupPeriodAction.saveBatasTutupPeriod(jsonString, function(response){
//           console.log(response);
           if (response.status == "error"){
               $("#alert-error").show();
               $("#error-msg").text(response.msg);
           } else {
               $("#alert-error").hide();
               $("#alert-success").show();
           }
       });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>