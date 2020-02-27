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
            window.location.href="<s:url action='initForm_kodeRekening'/>";
        }
    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
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
            Kode Rekening
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Kode Rekening</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="kodeRekeningForm" method="post" namespace="/kodeRekening" action="search_kodeRekening.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Rekening ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="rekeningId" cssStyle="margin-top: 7px"
                                                     name="kodeRekening.rekeningId" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Rekening</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="kodeRekeningName" name="kodeRekening.kodeRekeningName"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">kode Rekening ( COA )</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="kodeRekening" name="kodeRekening.kodeRekening"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                        <script>
                                            $(document).ready(function() {
                                                var functions, mapped;
                                                $('#kodeRekening').typeahead({
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
                                                                id: item.kodeRekening,
                                                                nama: item.namaKodeRekening
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
                                        </script>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tipe Rekening</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboTipeRekening" namespace="/tipeRekening" name="initComboTipeRekening_tipeRekening"/>
                                        <s:select list="#initComboTipeRekening.listOfComboTipeRekening" id="tipeRekeningId" name="kodeRekening.tipeRekeningId"
                                                  listKey="tipeRekeningId" listValue="tipeRekeningName"  headerKey="" headerValue="[Select one]" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <a type="button" class="btn btn-primary" onclick="searchFunc()"><i
                                                class="fa fa-search"></i> Search</a>
                                        <s:url var="urlAdd" namespace="/kodeRekening" action="add_kodeRekening" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Kode Rekening
                                        </sj:a>
                                        <a type="button" class="btn btn-danger" href="initForm_kodeRekening.action">
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
                                                   height="450" width="600" autoOpen="false" title="Add Kode Rekening"
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
                            <table style="width: 80%;" class="tree table table-bordered">
                            </table>
                        </center>
                    </div>
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
                <s:form id="kodeRekeningDeleteForm" method="post" theme="simple" namespace="/kodeRekening" action="saveEdit_kodeRekening" cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >Rekening Id :</label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="rekeningIdDelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Kode Rekening :</label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="kodeRekeningNameDelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >COA :</label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="kodeRekeningDelete">
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
                <s:form id="kodeRekeningEditForm" method="post" theme="simple" namespace="/kodeRekening" action="saveEdit_kodeRekening" cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >Rekening Id :</label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="rekeningIdEdit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Kode Rekening :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="kodeRekeningNameEdit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >COA :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="kodeRekeningEdit" readonly >
                            <%--<script>
                                $(document).ready(function() {
                                    var functions, mapped;
                                    $('#kodeRekeningEdit').typeahead({
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
                                                    id: item.kodeRekening,
                                                    nama: item.namaKodeRekening
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
        //btn Save
        $('#btnEdit').click(function(){
            var id      = $('#rekeningIdEdit').val();
            var kodeRekeningName  = $('#kodeRekeningNameEdit').val();
            var kodeRekening= $('#kodeRekeningEdit').val();
            var tipeRekeningId  = $('#tipeRekeningIdEdit').val();
            var result = '';
            if(id != ''&&kodeRekeningName != ''&&kodeRekening != ''&&tipeRekeningId != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    KodeRekeningAction.saveEdit(id, kodeRekeningName, kodeRekening, tipeRekeningId,"edit",function(listdata) {
                        alert('Record has been saved successfully.');
                        location.reload();
                    });
                }
            }else{
                if (id==""){
                    result+="ID kode rekening tidak terdeteksi \n";
                }
                if (kodeRekeningName==""){
                    result+="Nama Kode Rekening masih kosong \n";
                }
                if (kodeRekening==""){
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
                    KodeRekeningAction.saveEdit(id, "", "", "","delete",function(listdata) {
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
            KodeRekeningAction.initKodeRekeningSearch(id,"","",function(listdata) {
                $.each(listdata, function(i,item){
                    $('#rekeningIdEdit').val(item.rekeningId);
                    $('#kodeRekeningNameEdit').val(item.namaKodeRekening);
                    $('#kodeRekeningEdit').val(item.kodeRekening);
                    $('#tipeRekeningIdEdit').val(item.tipeRekeningId);
                });
            });
            $('#modal-edit').modal('show');
        });

        $('.tree').on('click', '.item-delete', function(){
            var id = $(this).attr('data');
            KodeRekeningAction.initKodeRekeningSearch(id,"","",function(listdata) {
                $.each(listdata, function(i,item){
                    $('#rekeningIdDelete').val(item.rekeningId);
                    $('#kodeRekeningNameDelete').val(item.namaKodeRekening);
                    $('#kodeRekeningDelete').val(item.kodeRekening);
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
        var rekeningId = document.getElementById("rekeningId").value;
        var namaRekening = document.getElementById("kodeRekeningName").value;
        var kodeRekening = document.getElementById("kodeRekening").value;
        var tmp_table = "";
        var data = [];
        var data2 = [];
        dwr.engine.setAsync(false);
        KodeRekeningAction.initKodeRekeningSearch(rekeningId, namaRekening, kodeRekening, function(listdata){
            data = listdata;
            data2 = new Array();
            data2_hasil = new Array();
            data2Tmp= new Array();
            $.each(data, function(i,item){
                data2.push({_id : item.rekeningId, level : item.level,  nama : item.namaKodeRekening, parent : item.parentId, coa : item.kodeRekening,
                    tipeRekening : item.tipeRekeningId, status : item.flag,tipeRekeningName: item.tipeRekeningName});
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
                "<th style='text-align: center; background-color:  #30d196''>Level</th>"+
                "<th style='text-align: center; background-color:  #30d196''>Tipe Rekening </th>"+
                "<th style='text-align: center; background-color:  #30d196'>Edit</th>"+
                "<th style='text-align: center; background-color:  #30d196'>Delete</th>"+
                "</tr></thead>";
            for(i = 0 ; i < data2.length ; i++){
                if(data2[i].parent == "-"){
                    tmp_table += '<tr style="font-size: 12px;" class=" treegrid-' + data2[i]._id+ '">' +
                        '<td >' + data2[i].coa + '</td>' +
                        '<td >' + data2[i].nama + '</td>' +
                        '<td align="center">' + data2[i].level+ '</td>' +
                        '<td align="center">' + data2[i].tipeRekeningName + '</td>' +
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
                        "</tr>";
                } else {
                    tmp_table += '<tr style="font-size: 12px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                        + '<td style="border: 2px solid black;">' +
                        '<td >' + data2[i].coa + '</td>' +
                        '<td >' + data2[i].nama + '</td>' +
                        '<td align="center">' + data2[i].level + '</td>' +
                        '<td align="center">' + data2[i].tipeRekeningName + '</td>' +
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
                        "</tr>";
                }

            }
            $('.tree').append(tmp_table);
        });
    }
    function cekAvailableCoaEdit(nilai){
        var coa = nilai.value;
        var length = nilai.length;
        if (length!=0){
            dwr.engine.setAsync(false);
            KodeRekeningAction.cekAvailableCoa(coa, function(listdata) {
                if (listdata.length!=0){
                    alert("COA sudah ada");
                    $('#kodeRekeningEdit').val("");
                }
            });
        }
    }
    function cekAvailableParentEdit(nilai){
        var coa = nilai.value;
        var length = nilai.length;
        if (length!=0){
            dwr.engine.setAsync(false);
            KodeRekeningAction.cekAvailableParent(coa, function(adaParent) {
                if (!adaParent){
                    alert("COA induk tidak ada");
                    $('#kodeRekeningEdit').val("");
                }
            });
        }
    }
</script>