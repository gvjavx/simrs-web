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
            window.location.href="<s:url action='initForm_strukturJabatan'/>";
        }

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StrukturJabatanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>

    <script>

        $(document).ready(function () {
            window.getBagian = function(){
                StrukturJabatanAction.getPerBagian(function(listdata) {
                });
            }

            //btn Save
            $('#btnSave').click(function(){
                var id      = $('#strukturJabatanIdEdit').val();
                var branch  = $('#branchIdEdit').val();
                var position= $('#positionIdEdit').val();
                var parent  = $('#parentIdEdit').val();

                var result = '';

                //alert(id + ' ' + branch + ' ' + position + ' ' + parent);
                if(branch == ''){
                    alert('Unit Harus dipilih');
                    result = '';
                }else{
                    if (confirm('Are you sure you want to save this Record?')) {
                        console.log("Test1 "+id);
                        console.log("Test2 "+branch);
                        console.log("Test3 "+position);
                        console.log("Test4 "+parent);
                        StrukturJabatanAction.saveEditStruktur(id, branch, position, parent,function(listdata) {
                            alert('Record has been saved successfully.');
                            location.reload();
                        });
                    }
                }

            });

            $('#btnDelete').click(function(){
                var id      = $('#strukturJabatanIdDelete').val();
                var branch  = $('#branchIdDelete').val();
                var position= $('#positionIdDelete').val();
                var parent  = $('#parentIdDelete').val();

                if(branch == ''){
                    alert('Unit harus dipilih');
                }else{
                    if (confirm('Are you sure you want to Delete this Record?')) {
                        StrukturJabatanAction.saveDelete(id, branch, position, parent,function(listdata) {
                            alert('Record has been Deleted successfully.');
                            location.reload();
                        });
                    }
                }

            });

            $('.tree').treegrid({
                expanderExpandedClass: 'glyphicon glyphicon-minus',
                expanderCollapsedClass: 'glyphicon glyphicon-plus'
            });

            $('.tree').on('click', '.item-edit', function(){
                var id = $(this).attr('data');
                var branch = $(this).attr('branch');

                StrukturJabatanAction.initStrukturJabatanSearch(id,function(item) {
                    $('#strukturJabatanIdEdit').val(item.strukturJabatanId);
                    $('#branchIdEdit').val(item.branchId);
                    $('#positionIdEdit').val(item.positionId);
                    $('#levelEdit').val(item.level);
                    $('#createDateEdit').val(item.cDate);
                    $('#lastUpdateEdit').val(item.lUpdate);
                    $('#lastUpdateWhoEdit').val(item.lastUpdateWho);
                    $('#createdWhoEdit').val(item.createdWho);
                    selectParent(branch, 'Edit');
                    var level = parseInt(item.level) - 1;
                    $('#parentIdEdit').val(item.parentId + '-' + level).change();
                });

                $('#modal-edit').find('.modal-title').text('Edit Data');
                /*$('#myForm').attr('action', '<?php echo base_url() ?>Contact/updateContact/'+id);*/
                $('#modal-edit').modal('show');
            });

            $('.tree').on('click', '.item-delete', function(){
                var id = $(this).attr('data');
                var branch = $(this).attr('branch');
                var nip =$(this).attr('nip');
                console.log(nip);
                if (nip=="-"){
                    StrukturJabatanAction.initStrukturJabatanSearch(id, function(item) {
                        selectParent(branch, 'Delete');
                        $('#strukturJabatanIdDelete').val(item.strukturJabatanId);
                        $('#branchIdDelete').val(item.branchId);
                        $('#positionIdDelete').val(item.positionId);
                        var level = parseInt(item.level) - 1;
                        $('#levelDelete').val(level);
                        $('#parentIdDelete').val(item.parentId + '-' + level).change();
                    });

                    $('#modal-delete').find('.modal-title').text('Delete Data');
                    $('#modal-delete').modal('show');
                } else{
                    alert("Struktur Jabatan Masih Digunakan tidak bisa dihapus");
                }

                //alert(id);

            });
        });

        function selectParent(nilai, type){
            //alert(type);
            dataParent = [] ;
            dataParentList = new Array();
            dwr.engine.setAsync(false);
            StrukturJabatanAction.initStrukturJabatanList(nilai, function(listdata) {
                $("#parentId"+type+" option").remove();
                $.each(listdata, function (i, item) {
                    //alert(item.strukturJabatanId + ' - ' + item.positionName);
                    $("#parentId"+type).append($('<option/>', {
                        value: item.strukturJabatanId + '-' + item.level,
                        text : item.strukturJabatanId + ' - ' + item.positionName
                    }));
                });

            });
        }

        function searchFunc(){
            if(document.getElementById("branchId").value == ''){
                alert('Unit harus dipilih');
            }else{
                $('.tree').find('tbody').remove();
                $('.tree').find('thead').remove();
                f1();
                $('.tree').treegrid({
                    expanderExpandedClass: 'glyphicon glyphicon-minus',
                    expanderCollapsedClass: 'glyphicon glyphicon-plus'
                });
            }
        }

        function f1() {
            var positionId = document.getElementById("positionId").value;
            var branchId = document.getElementById("branchId").value;
            var tmp_table = "";

            var data = [];
            var data2 = [];
            var index = 0 ;
            dwr.engine.setAsync(false);
            var nip = document.getElementById("nip1").value;
            //alert(strukturId + ' ' + positionId + ' ' + flagId);


            StrukturJabatanAction.initStrukturJabatanSearch(branchId, positionId, nip, function(listdata){
            //StrukturJabatanAction.initStrukturJabatanSearch(strukturId, positionId, flagId, branchId,function(listdata){

                data = listdata;
                data2 = new Array();
                data2_hasil = new Array();
                data2Tmp= new Array();

                $.each(data, function(i,item){
                    data2.push({_id : item.strukturJabatanId, level : item.level,  nama : item.positionName, parent : item.parentId, branchId : item.branchId,
                        branchName : item.branchName, status : 'Y', jabatan : item.positionName, nip  : item.nip, namaPegawai : item.name,
                        positionId : item.positionId});
                });


                console.log(data2);
                //var level = Number(0);
                function hierarhySort(hashArr, key, result) {
                    //level++;

                    if (hashArr[key] == undefined){
                        //level--;
                        return;
                    }else{
                        var arr = [] ;
                        arr  = hashArr[key];
                    }


                    for (var i=0; i<arr.length; i++) {
                        //return;
                        //arr[i].level = level ;
                        result.push(arr[i]);
                        hierarhySort(hashArr, arr[i]._id, result);
                    }
                    //level--;
                    return result;
                }

                var hashArr = {};

                for (var i=0; i<data2.length; i++) {
                    if (hashArr[data2[i].parent] == undefined) {
                        hashArr[data2[i].parent] = [];
                        //alert('data ke - ' + i +' spesial - ' + data2[i].parent);
                    }
                    hashArr[data2[i].parent].push(data2[i]);
                    //alert('data ke - ' + i + data2[i].parent);
                }
                var result = hierarhySort(hashArr, "", []);
                console.log(hashArr);
                //console.log(result);


                //alert(result.length);
                tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #30d196'>Struktur Id</th>"+
                        "<th style='text-align: center; background-color:  #30d196''>Nama Jabatan</th>"+
                        "<th style='text-align: center; background-color:  #30d196''>Level</th>"+
                        "<th style='text-align: center; background-color:  #30d196''>Nip </th>"+
                        "<th style='text-align: center; background-color:  #30d196''>Nama </th>"+
                        "<th style='text-align: center; background-color:  #30d196'>Edit</th>"+
                        "<th style='text-align: center; background-color:  #30d196'>Delete</th>"+
                        "</tr></thead>";
                for(i = 0 ; i < data2.length ; i++){
                    //data2.push([item.funcId, item.funcName, item.url, item.menu, item.parent, item.funcLevel, 'Y']);
                    if(data2[i].parent == "-"){
                        tmp_table += '<tr style="font-size: 11px;" class=" treegrid-' + data2[i]._id+ '">' +
                                '<td >' + data2[i]._id + '</td>' +
                                '<td >' + data2[i].nama + '</td>' +
                                '<td align="center">' + data2[i].level+ '</td>' +
                                '<td align="center">' + data2[i].nip + '</td>' +
                                '<td align="center">' + data2[i].namaPegawai + '</td>' +
                                '<td align="center">' +
                                    "<a href='javascript:;' class ='item-edit' branch='"+data2[i].branchId+"' data ='"+data2[i]._id+"' >" +
                                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                                    '</a>' +
                                '</td>' +
                                '<td align="center">' +
                                    "<a href='javascript:;' class ='item-delete' branch='"+data2[i].branchId+"' data ='"+data2[i]._id+"' nip ='"+data2[i].nip+"' >" +
                                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                                    '</a>' +
                                '</td>' +
                                "</tr>";
                    } else {
                        tmp_table += '<tr style="font-size: 11px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                                + '<td style="border: 2px solid black;">' +
                                '<td >' + data2[i]._id + '</td>' +
                                '<td >' + data2[i].nama + '</td>' +
                                '<td align="center">' + data2[i].level + '</td>' +
                                '<td align="center">' + data2[i].nip + '</td>' +
                                '<td align="center">' + data2[i].namaPegawai + '</td>' +
                                '<td align="center">' +
                                    "<a href='javascript:;' class ='item-edit' branch='"+data2[i].branchId+"' data ='"+data2[i]._id+"' >" +
                                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                                    '</a>' +
                                '</td>' +
                                '<td align="center">' +
                                    "<a href='javascript:;' class ='item-delete' branch='"+data2[i].branchId+"' data ='"+data2[i]._id+"'  nip ='"+data2[i].nip+"' >" +
                                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                                    '</a>' +
                                '</td>' +
                                "</tr>";
                    }

                }
                $('.tree').append(tmp_table);

            });
        }
    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Struktur Jabatan
        </h1>

    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="strukturJabatanSearch" method="post"  theme="simple" namespace="/strukturJabatan" action="search_strukturJabatan.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>



                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >
                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>StrukturJabatan Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="strukturJabatanId" name="strukturJabatan.strukturJabatanId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>--%>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test='strukturJabatan.branchId == "KP"'>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="strukturJabatan.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:if>
                                        <s:else>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="strukturJabatan.branchId" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            <s:hidden id="branchId" name="strukturJabatan.branchId"/>
                                        </s:else>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Posisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                            <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="strukturJabatan.positionId"
                                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>NIP :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="nip1" cssClass="form-control"></s:textfield>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="nipNama" cssClass="form-control" readonly="true"></s:textfield>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#nip1').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);

                                            MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                data = listdata;
                                            });
                                            //alert(prov);
                                            $.each(data, function (i, item) {
                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                var labelNip = item.nip;
                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch, divisiId: item.divisi, positionId : item.positionId };
                                                functions.push(labelItem);
                                            });

                                            process(functions);
                                        },

                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaAlat = selectedObj.id;
                                            document.getElementById("nipNama").value = selectedObj.pegawai;

                                            $('#positionId1').val(selectedObj.positionId).change();
                                            $('#branchId1').val(selectedObj.branchId).change();
                                            $('#divisiId1').val(selectedObj.divisiId).change();
                                            return namaAlat;
                                        }
                                    });

                                </script>
                            </tr>


                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="strukturJabatan.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>--%>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <a onclick="searchFunc()" class="btn btn-primary"><i class="fa fa-search"></i> Search</a>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/strukturJabatan" action="add_strukturJabatan" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Struktur Jabatan
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_strukturJabatan"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                    <%--<td>
                                        <button type="button" class="btn btn-default" onclick="getBagian()">
                                            <i class="fa fa-download"></i> Get Bagian
                                        </button>
                                    </td>--%>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="40%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="600" autoOpen="false"
                                                   title="StrukturJabatan ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                    </td>
                                </tr>
                            </table>

                            <table style="width: 90%;" class="tree table table-bordered">
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

    <div id="modal-delete" class="modal fade" role="dialog">
        <div class="modal-dialog modal-md">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Delete Data</h4>
                </div>
                <div class="modal-body">
                    <s:form id="strukturJabatanEdit" method="post" theme="simple" namespace="/strukturJabatan" action="saveEdit_strukturJabatan" cssClass="well form-horizontal">

                        <div  class="form-group">
                            <label class="control-label col-sm-4" >Struktur Jabatan Id :</label>
                            <div class="col-sm-6">
                                <input readonly type="text" class="form-control" id="strukturJabatanIdDelete" name="strukturJabatan.strukturJabatanId">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="strukturJabatan.branchId">Unit Id :</label>
                            <div class="col-sm-6">
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select disabled="true" list="#initComboBranch.listOfComboBranch" id="branchIdDelete" name="strukturJabatan.branchId" onchange="selectParent(this.value, 'Delete')"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Parent :</label>
                            <div class="col-sm-6">
                                <select disabled="true" class="form-control" id="parentIdDelete" name="strukturJabatan.parentId">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Level :</label>
                            <div class="col-sm-6">
                                <s:textfield disabled="true" id="levelDelete" name="StrukturJabatan.level" required="false" readonly="false" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.positionId">Position Id :</label>
                            <div class="col-sm-6">
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select disabled="true" list="#comboPosition.listOfComboPosition" id="positionIdDelete" name="StrukturJabatan.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                    <s:form id="strukturJabatanEdit" method="post" theme="simple" namespace="/strukturJabatan" action="saveEdit_strukturJabatan" cssClass="well form-horizontal">

                        <div  class="form-group">
                            <label class="control-label col-sm-4" >Struktur Jabatan Id :</label>
                            <div class="col-sm-6">
                                <input readonly type="text" class="form-control" id="strukturJabatanIdEdit" name="strukturJabatan.strukturJabatanId">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="strukturJabatan.branchId">Unit Id :</label>
                            <div class="col-sm-6">
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select disabled="true" list="#initComboBranch.listOfComboBranch" id="branchIdEdit" name="strukturJabatan.branchId" onchange="selectParent(this.value, 'Edit')"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Parent :</label>
                            <div class="col-sm-6">
                                <select class="form-control" id="parentIdEdit" name="strukturJabatan.parentId">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.positionId">Position Id :</label>
                            <div class="col-sm-6">
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select disabled="true" list="#comboPosition.listOfComboPosition" id="positionIdEdit" name="StrukturJabatan.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Level :</label>
                            <div class="col-sm-6">
                                <s:textfield  id="levelEdit" name="strukturJabatan.level" required="false" readonly="true" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Created Date :</label>
                            <div class="col-sm-6">
                                <s:textfield  id="createDateEdit" name="strukturJabatan.cDate" required="false" readonly="true" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Lats Update :</label>
                            <div class="col-sm-6">
                                <s:textfield  id="lastUpdateEdit" name="strukturJabatan.lastUpdate" required="false" readonly="true" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Create Who :</label>
                            <div class="col-sm-6">
                                <s:textfield  id="createdWhoEdit" name="strukturJabatan.lUpdate" required="false" readonly="true" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="StrukturJabatan.level">Last Update Who :</label>
                            <div class="col-sm-6">
                                <s:textfield  id="lastUpdateWhoEdit" name="strukturJabatan.lastUpdateWho" required="false" readonly="true" cssClass="form-control"/>
                            </div>
                        </div>

                    </s:form>

                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
