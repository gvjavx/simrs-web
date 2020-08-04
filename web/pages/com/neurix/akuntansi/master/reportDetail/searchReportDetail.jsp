<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
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
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <style>
        /*--body { background-color:#fafafa; font-family:'Open Sans';}*/
        .container { margin:150px auto;}
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
    <script type='text/javascript'>
        function link(){
            window.location.href="<s:url action='initForm_reportDetail'/>";
        }
    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ReportDetailAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LaporanAkuntansiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Setting Report Akuntansi
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Detail Report</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="reportDetailForm" method="post" namespace="/reportDetail" action="search_reportDetail.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Report ID</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboLaporan" namespace="/laporanAkuntansi" name="searchSelectReport_laporanAkuntansi"/>
                                        <s:select list="#comboLaporan.listComboLaporanAkuntansi" id="report_id" onchange="listTipeLaporan()"
                                                  listKey="laporanAkuntansiId" listValue="laporanAkuntansiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div id="grup-tipe-laporan" class="form-group">
                                    <label class="control-label col-sm-4">Tipe Laporan</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'hutang_usaha':'Hutang Usaha', 'piutang_usaha' : 'Piutang Usaha', 'uang_muka':'Uang Muka','piutang_pasien':'Piutang Pasien','uang_muka_p':'Uang Muka Pasien','pph_dokter_kso':'PPH21 Dokter','pph_pegawai':'PPH21 Pegawai','pph_rekanan':'PPH21 Rekanan'}"
                                                  id="tipeLaporan" name="laporanAkuntansi.tipeLaporanId"
                                                  headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <a type="button" class="btn btn-primary" onclick="searchFunc()"><i
                                                class="fa fa-search"></i> Search</a>
                                        <s:url var="urlAdd" namespace="/reportDetail" action="add_reportDetail" escapeAmp="false">
                                        </s:url>
                                        <a type="button" class="btn btn-danger" href="initForm_reportDetail.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">
                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Searching ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="450" width="600" autoOpen="false" title="Add Report Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Struktur COA ( Chart of Account )</h3>
                    </div>
                    <div class="box-body">
                        <center>
                            <table style="width: 60%;" class="tree table table-bordered">
                            </table>
                        </center>
                    </div>
                    <br>
                    <center>
                        <a type="button" class="btn btn-primary" style="display: none" id="btnSaveDetailReport">
                            <i class="fa fa-save"></i> Save
                        </a>
                    </center>
                    <br>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<script>
    window.listTipeLaporan = function(){
        var reportId = document.getElementById("report_id").value;
        LaporanAkuntansiAction.getAdaTipeLaporan(reportId,function (status) {
            console.log(status);
           if (status=="Y"){
               $('#tipeLaporan').empty();
               LaporanAkuntansiAction.searchTipeLaporan(function(listdata){
                   $.each(listdata, function (i, item) {
                       $('#tipeLaporan').append($("<option></option>")
                           .attr("value",item.tipeLaporan)
                           .text(item.tipeLaporanName));
                   });
               });
               $('#grup-tipe-laporan').show();
           } else{
               $('#tipeLaporan').empty();
               $('#tipeLaporan').append($("<option></option>")
                   .attr("value","")
                   .text(""));
               $('#grup-tipe-laporan').hide();
           }
        });
    };

    $(document).ready(function () {
        $('#grup-tipe-laporan').hide();
        $("#btnSaveDetailReport").hide();
        $("#btnSaveDetailReport").click(function () {
            if (confirm("Apakah anda ingin menyimpan perubahan COA pada report ini ?")){
                var data = $('.tree').tableToJSON();
                var reportId = $('#report_id').val();
                var tipeLaporan = $('#tipeLaporan').val();
                ReportDetailAction.deleteReportDetail(reportId,tipeLaporan,function (listData) {});
                $.each(data, function (i, item) {
                    var rekId = data[i]["Rekening Id"];
                    if ($('#check_' + data[i]["Rekening Id"]).prop("checked") == true) {
                        ReportDetailAction.addReportDetail(reportId,rekId,tipeLaporan,function (listData) {
                        })
                    }
                });
                alert("Sukses edit report detail");
                window.location.reload();
            }
        });
        $('.tree').treegrid({
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        });

        $('.tree').on('click', '.item-edit', function(){
            var id = $(this).attr('data');
            ReportDetailAction.initReportDetailSearch(id,"","",function(listdata) {
                $.each(listdata, function(i,item){
                    $('#rekeningIdEdit').val(item.rekeningId);
                    $('#reportDetailNameEdit').val(item.namaReportDetail);
                    $('#reportDetailEdit').val(item.reportDetail);
                    $('#tipeRekeningIdEdit').val(item.tipeRekeningId);
                });
            });
            $('#modal-edit').modal('show');
        });

        $('.tree').on('click', '.item-delete', function(){
            var id = $(this).attr('data');
            ReportDetailAction.initReportDetailSearch(id,"","",function(listdata) {
                $.each(listdata, function(i,item){
                    $('#rekeningIdDelete').val(item.rekeningId);
                    $('#reportDetailNameDelete').val(item.namaReportDetail);
                    $('#reportDetailDelete').val(item.reportDetail);
                    $('#tipeRekeningIdDelete').val(item.tipeRekeningId);
                });
            });
            $('#modal-delete').modal('show');
        });
    });

    function searchFunc(){
        $('.tree').find('tbody').remove();
        $('.tree').find('thead').remove();
        f1();
        $('.tree').treegrid({
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        });
    }

    function f1() {
        var reportId = document.getElementById("report_id").value;
        var tipeLaporan = document.getElementById("tipeLaporan").value;
        if (reportId==""){
            alert("Report belum dipilih");
        } else{
            var tmp_table = "";
            var data = [];
            var data2 = [];
            dwr.engine.setAsync(false);
            ReportDetailAction.initReportDetailSearch(reportId,tipeLaporan, function(listdata){
                data = listdata;
                data2 = new Array();
                data2_hasil = new Array();
                data2Tmp= new Array();
                $.each(data, function(i,item){
                    data2.push({_id : item.rekeningId, level : item.level,  nama : item.namaKodeRekening, parent : item.parentId, coa : item.kodeRekening,
                        tipeRekening : item.tipeRekeningId, status : item.flag,tipeRekeningName: item.tipeRekeningName,adaCetak:item.adaRekeningReport,bisaCek:item.bisaCek});
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
                tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #30d196'>COA ( Chart of Account )</th>"+
                    "<th style='text-align: center; background-color:  #30d196' class='tableHide'>Rekening Id</th>"+
                    "<th style='text-align: center; background-color:  #30d196''>Nama Kode Rekening</th>"+
                    "<th style='text-align: center; background-color:  #30d196''>Level</th>"+
                    "<th style='text-align: center; background-color:  #30d196''>cetak ?</th>"+
                    "</tr></thead>";
                for(i = 0 ; i < data2.length ; i++){
                    var cekbox ="";
                    if (!data2[i].bisaCek){
                        cekbox='<td align="center" class="ceknull"></td>';
                    }else if (data2[i].adaCetak) {
                        cekbox='<td align="center" class="ceknull">' + '<input type="checkbox" value="'+data2[i]._id+'" checked id="check_'+data2[i]._id+'">' + '</td>';
                    }else{
                        cekbox='<td align="center" class="ceknull">' + '<input type="checkbox" id="check_'+data2[i]._id+'">' + '</td>';
                    }

                    if(data2[i].parent == "-"){
                        tmp_table += '<tr style="font-size: 12px;" class=" treegrid-' + data2[i]._id+ '">' +
                            '<td >' + data2[i].coa + '</td>' +
                            '<td class="tableHide">' + data2[i]._id + '</td>' +
                            '<td >' + data2[i].nama + '</td>' +
                            '<td style="text-align: center;">' + data2[i].level + '</td>' +
                            cekbox +
                            "</tr>";
                    } else {
                        tmp_table += '<tr style="font-size: 12px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                            + '<td style="border: 2px solid black;">' +
                            '<td >' + data2[i].coa + '</td>' +
                            '<td class="tableHide">' + data2[i]._id + '</td>' +
                            '<td >' + data2[i].nama + '</td>' +
                            '<td  style="text-align: center;">' + data2[i].level + '</td>' +
                            cekbox+
                            '</td>' +
                            "</tr>";
                    }
                }
                $('.tree').append(tmp_table);
                $(".tree .ceknull:contains('null')").html("-");
                $('.tableHide').hide();
                $("#btnSaveDetailReport").show();
            });
        }
    }
</script>