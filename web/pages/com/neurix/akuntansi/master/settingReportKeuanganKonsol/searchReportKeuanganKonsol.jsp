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
            window.location.href="<s:url action='initForm_reportKeuanganKonsol'/>";
        }

    </script>

    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/SettingReportKeuanganKonsolAction.js"/>'></script>--%>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SettingReportKeuanganKonsolAction.js"/>'></script>
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
            Setting Report Keuangan Konsol
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Form Setting Report Keuangan Konsol </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="reportKeuanganKonsolForm" method="post"  theme="simple" namespace="/reportKeuanganKonsol" action="search_reportKeuanganKonsol.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Report Konsol ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="settingReportKonsolId" name="akunSettingReportKeuanganKonsol.settingReportKonsolId" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Kode Rekening Alias :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="kodeRekeningAlias" name="akunSettingReportKeuanganKonsol.kodeRekeningAlias" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Kode Rekening Alias :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaKodeRekeningAlias" name="akunSettingReportKeuanganKonsol.namaKodeRekeningAlias" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="akunSettingReportKeuanganKonsol.flag"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <a type="button" class="btn btn-primary" onclick="searchFunc()"><i
                                                                class="fa fa-search"></i> Search</a>
                                                            <%--<sj:submit type="button" cssClass="btn btn-primary" formIds="reportKeuanganKonsolForm" id="search" name="search"--%>
                                                            <%--onClickTopics="showDialog" onCompleteTopics="closeDialog" >--%>
                                                            <%--<i class="fa fa-search"></i>--%>
                                                            <%--Search--%>
                                                            <%--</sj:submit>--%>
                                                    </td>
                                                    <td>
                                                        <a href="add_reportKeuanganKonsol.action" class="btn btn-success" ><i class="fa fa-plus"></i>Add Report Keuangan Konsol</a>
                                                            <%--<s:url var="urlAdd" namespace="/reportKeuanganKonsol" action="add_reportKeuanganKonsol" escapeAmp="false">--%>
                                                            <%--</s:url>--%>
                                                            <%--<sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">--%>
                                                            <%--<i class="fa fa-plus"></i>--%>
                                                            <%--Add Report Keuangan Konsol--%>
                                                            <%--</sj:a>--%>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_reportKeuanganKonsol"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="80%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialog"
                                                                   closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Search Data ...">
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
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="400" width="600" autoOpen="false"
                                                                   title="Report Keuangan Konsol ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <sj:dialog id="view_dialog_menu_pendapatan" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="700" autoOpen="false"
                                                                   title="Report Keuangan Konsol">
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_keterangan" openTopics="showDialogMenuKeterangan" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Report Keuangan Konsol">
                                                        </sj:dialog>


                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }">
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                            <br/>
                                                                            <center><div id="errorValidationMessage"></div></center>
                                                                        </label>
                                                                    </div>
                                                                </sj:dialog>
                                                            </table>
                                                        </td>
                                                    </div>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> REPORT KEUANGAN</h3>
                    </div>
                    <div class="box-body">
                        <center>
                            <table style="width: 85%;" class="tree table table-bordered">
                            </table>
                        </center>
                    </div>

                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

<script>
    $(document).ready(function () {
        $('.tree').treegrid({
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        });

        function openEdit(id,flag) {
            var curURL = window.location.href;
            var splitURL = curURL.split("/");
            var nowURL = "http://" + splitURL[2] + "/" + splitURL[3] + "/reportKeuanganKonsol/edit_reportKeuanganKonsol.action?id=" + id + "&flag=" + flag;
            window.open(nowURL,'_self');
        }

        function openDelete(id,flag) {
            var curURL = window.location.href;
            var splitURL = curURL.split("/");
            var nowURL = "http://" + splitURL[2] + "/" + splitURL[3] + "/reportKeuanganKonsol/delete_reportKeuanganKonsol.action?id=" + id + "&flag=" + flag;
            window.open(nowURL,'_self');
        }

        $('.tree').on('click', '.item-edit', function(){
            var id = $(this).attr('data');
            var flag;
            SettingReportKeuanganKonsolAction.initSettingReportKeuanganKonsolSearch(id,"","",function(listdata) {
                $.each(listdata, function(i,item){
                    flag = item.flag;
                });
            });
            openEdit(id,flag);
        });

        $('.tree').on('click', '.item-delete', function(){
            var id = $(this).attr('data');
            var flag;
            SettingReportKeuanganKonsolAction.initSettingReportKeuanganKonsolSearch(id,"","",function(listdata) {
                $.each(listdata, function(i,item){
                    flag = item.flag;
                });
            });
            openDelete(id,flag);
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
        var reportId = document.getElementById("settingReportKonsolId").value;
        var kodeAlias = document.getElementById("kodeRekeningAlias").value;
        var namaRekeningAlias = document.getElementById("namaKodeRekeningAlias").value;
        var flag = document.getElementById("flag")

        var tmp_table = "";
        var data = [];
        var data2 = [];
        dwr.engine.setAsync(false);
        SettingReportKeuanganKonsolAction.initSettingReportKeuanganKonsolSearch(reportId, namaRekeningAlias, kodeAlias, function(listdata){
            data = listdata;
            data2 = new Array();
            data2_hasil = new Array();
            data2Tmp= new Array();
            $.each(data, function(i,item){
                data2.push({levelID : levelID(item.kodeRekeningAlias), _id : item.settingReportKonsolId, level : whatLevel(item.kodeRekeningAlias), parent : whichParent(item.kodeRekeningAlias),  nama : item.namaKodeRekeningAlias, kode : item.kodeRekeningAlias,
                    label : item.flagLabel, flag : item.flag, action : item.action, created_date : item.stCreatedDate, created_who : item.createdWho, update_last : item.stLastUpdate, update_who : item.lastUpdatewWho});
            });

            function whatLevel(kodeAlias, result) {
                var splitKode = kodeAlias.split(".");
                if(splitKode.length == 3){
                    result = "3";
                } else if(splitKode.length == 2){
                    result = "2";
                } else {
                    result = "1";
                }
                return result;
            }

            function levelID(kodeAlias, result) {
                var splitKode = kodeAlias.split(".");
                if(splitKode.length == 3){
                    result = splitKode[0] + "_" + splitKode[1] + "_" + splitKode[2];
                } else if(splitKode.length == 2){
                    result = splitKode[0] + "_" + splitKode[1];}
                else {
                    result = splitKode[0];
                }
                return result;
            }

            function whichParent(kodeAlias, result) {
                var splitKode = kodeAlias.split(".");
                if(splitKode.length == 3){
                    result = splitKode[0] + "_" + splitKode[1];
                } else if(splitKode.length == 2){
                    result = splitKode[0];}
                else {
                    result = "-";
                }
                return result;
            }

            // === UNDONE ===
            // function hierarhySort(hashArr, key, result) {
            //     if (hashArr[key] == undefined){
            //         //level--;
            //         return;
            //     }else{
            //         var arr = [] ;
            //         arr  = hashArr[key];
            //     }
            //     for (var i=0; i<arr.length; i++) {
            //         result.push(arr[i]);
            //         hierarhySort(hashArr, arr[i]._id, result);
            //     }
            //     return result;
            // }

            var hashArr = {};
            for (var i=0; i<data2.length; i++) {
                if (hashArr[data2[i].parent] == undefined) {
                    hashArr[data2[i].parent] = [];
                }
                hashArr[data2[i].parent].push(data2[i]);
            }
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                // "<th style='text-align: center; background-color:  #30d196'>ID Report Konsol</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Kode Rekening</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Nama Kode Rekening</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Level</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Label</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Flag</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Action</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Created Date</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Created Who</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Last Update</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Last Update Who</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Edit</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Delete</th>"+
                // "<th style='text-align: center; background-color:  #30d196'>Parent</th>"+
                "</tr></thead>";
            for(i = 0 ; i < data2.length ; i++){
                if(data2[i].parent == "-"){
                    tmp_table += '<tr style="font-size: 12px;" class=" treegrid-' + data2[i].levelID + '">' +
                        '<td >' + data2[i].kode + '</td>' +
                        '<td >' + data2[i].nama + '</td>' +
                        '<td align="center" class="ceknull">' + data2[i].level+ '</td>' +
                        '<td align="center">' + data2[i].label+ '</td>' +
                        '<td align="center">' + data2[i].flag+ '</td>' +
                        '<td align="center">' + data2[i].action+ '</td>' +
                        '<td >' + data2[i].created_date + '</td>' +
                        '<td >' + data2[i].created_who + '</td>' +
                        '<td >' + data2[i].update_last + '</td>' +
                        '<td >' + data2[i].update_who + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+data2[i]._id+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='"+data2[i]._id+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        // '<td >' + data2[i].parent + '</td>' +
                        "</tr>";
                } else {
                    tmp_table += '<tr style="font-size: 12px" class=" treegrid-' + data2[i].levelID + ' treegrid-parent-' + data2[i].parent + '">' +
                        + '<td style="border: 2px solid black;">' +
                        '<td >' + data2[i].kode + '</td>' +
                        '<td >' + data2[i].nama + '</td>' +
                        '<td align="center" class="ceknull">' + data2[i].level+ '</td>' +
                        '<td align="center">' + data2[i].label+ '</td>' +
                        '<td align="center">' + data2[i].flag+ '</td>' +
                        '<td align="center">' + data2[i].action+ '</td>' +
                        '<td >' + data2[i].created_date + '</td>' +
                        '<td >' + data2[i].created_who + '</td>' +
                        '<td >' + data2[i].update_last + '</td>' +
                        '<td >' + data2[i].update_who + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+data2[i]._id+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='"+data2[i]._id+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        // '<td >' + data2[i].parent + '</td>' +
                        "</tr>";
                }
            }
            $('.tree').append(tmp_table);
            $(".tree .ceknull:contains('null')").html("-");
        });
    }
</script>