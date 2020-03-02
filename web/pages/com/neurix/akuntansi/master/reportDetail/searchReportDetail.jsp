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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ReportDetailAction.js"/>'></script>
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
                                        <s:action id="comboLaporan" namespace="/laporanAkuntansi" name="searchLaporanAkuntansi_laporanAkuntansi"/>
                                        <s:select list="#comboLaporan.listComboLaporanAkuntansi" id="report_id"
                                                  listKey="laporanAkuntansiId" listValue="laporanAkuntansiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
<div id="modal-delete" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Data</h4>
            </div>
            <div class="modal-body">
                <s:form id="reportDetailDeleteForm" method="post" theme="simple" namespace="/reportDetail" action="saveEdit_reportDetail" cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >Rekening Id :</label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="rekeningIdDelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Report Detail :</label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="reportDetailNameDelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >COA :</label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="reportDetailDelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe Rekening :</label>
                        <div class="col-sm-6">
                            <s:select list="#{'00':'Jurnal', '01' : 'Neraca'}" id="tipeRekeningIdDelete" disabled="true"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" class="btn btn-default btn-primary"><i class="fa fa-trash"></i> Delete</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Data</h4>
            </div>
            <div class="modal-body">
                <s:form id="reportDetailEditForm" method="post" theme="simple" namespace="/reportDetail" action="saveEdit_reportDetail" cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >Rekening Id :</label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="rekeningIdEdit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Report Detail :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="reportDetailNameEdit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >COA :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="reportDetailEdit" readonly >
                            <%--<script>
                                $(document).ready(function() {
                                    var functions, mapped;
                                    $('#reportDetailEdit').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};
                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            ReportDetailAction.initTypeaheadReportDetail(query,function (listdata) {
                                                data = listdata;
                                            });
                                            $.each(data, function (i, item) {
                                                var labelItem = item.reportDetail + " | " + item.namaReportDetail;
                                                mapped[labelItem] = {
                                                    id: item.reportDetail,
                                                    nama: item.namaReportDetail
                                                };
                                                functions.push(labelItem);
                                            });
                                            process(functions);
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            return selectedObj.id;
                                        }
                                    });
                                });
                            </script>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe Rekening :</label>
                        <div class="col-sm-6">
                            <s:select list="#{'00':'Jurnal', '01' : 'Neraca'}" id="tipeRekeningIdEdit"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button id="btnEdit" type="button" class="btn btn-default btn-primary"><i class="fa fa-pencil"></i> Edit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#btnSaveDetailReport").hide();
        //btn Save
        $('#btnEdit').click(function(){
            var id      = $('#rekeningIdEdit').val();
            var reportDetailName  = $('#reportDetailNameEdit').val();
            var reportDetail= $('#reportDetailEdit').val();
            var tipeRekeningId  = $('#tipeRekeningIdEdit').val();
            var result = '';
            if(id != ''&&reportDetailName != ''&&reportDetail != ''&&tipeRekeningId != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    ReportDetailAction.saveEdit(id, reportDetailName, reportDetail, tipeRekeningId,"edit",function(listdata) {
                        alert('Record has been saved successfully.');
                        location.reload();
                    });
                }
            }else{
                if (id==""){
                    result+="ID kode rekening tidak terdeteksi \n";
                }
                if (reportDetailName==""){
                    result+="Nama Report Detail masih kosong \n";
                }
                if (reportDetail==""){
                    result+="COA masih Kosong \n";
                }
                if (tipeRekeningId==""){
                    result+="Tipe Rekening ID masih kosong \n";
                }
                alert(result);
            }
        });

        $('#btnDelete').click(function(){
            var id      = $('#rekeningIdDelete').val();
            var result = '';
            if(id != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    ReportDetailAction.saveEdit(id, "", "", "","delete",function(listdata) {
                        alert('Record has been delete successfully.');
                        location.reload();
                    });
                }
            }else{
                if (id==""){
                    result+="ID kode rekening tidak terdeteksi \n";
                }
                alert(result);
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
        if (reportId==""){
            alert("Report belum dipilih");
        } else{
            var tmp_table = "";
            var data = [];
            var data2 = [];
            dwr.engine.setAsync(false);
            ReportDetailAction.initReportDetailSearch(reportId, function(listdata){
                data = listdata;
                data2 = new Array();
                data2_hasil = new Array();
                data2Tmp= new Array();
                $.each(data, function(i,item){
                    data2.push({_id : item.rekeningId, level : item.level,  nama : item.namaKodeRekening, parent : item.parentId, coa : item.kodeRekening,
                        tipeRekening : item.tipeRekeningId, status : item.flag,tipeRekeningName: item.tipeRekeningName,adaCetak:item.adaRekeningReport});
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
                    "<th style='text-align: center; background-color:  #30d196''>Nama Kode Rekening</th>"+
                    "<th style='text-align: center; background-color:  #30d196''>cetak ?</th>"+
                    "</tr></thead>";
                for(i = 0 ; i < data2.length ; i++){
                    var cekbox ="";
                    if (data2[i].adaCetak) {
                        cekbox='<td align="center" class="ceknull">' + '<input type="checkbox" checked>' + '</td>';
                    }else{
                        cekbox='<td align="center" class="ceknull">' + '<input type="checkbox">' + '</td>';
                    }

                    if(data2[i].parent == "-"){
                        tmp_table += '<tr style="font-size: 12px;" class=" treegrid-' + data2[i]._id+ '">' +
                            '<td >' + data2[i].coa + '</td>' +
                            '<td >' + data2[i].nama + '</td>' +
                            cekbox +
                            "</tr>";
                    } else {
                        tmp_table += '<tr style="font-size: 12px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                            + '<td style="border: 2px solid black;">' +
                            '<td >' + data2[i].coa + '</td>' +
                            '<td >' + data2[i].nama + '</td>' +
                            cekbox+
                            '</td>' +
                            "</tr>";
                    }
                }
                $('.tree').append(tmp_table);
                $(".tree .ceknull:contains('null')").html("-");
                $("#btnSaveDetailReport").show();
            });
        }
    }
</script>