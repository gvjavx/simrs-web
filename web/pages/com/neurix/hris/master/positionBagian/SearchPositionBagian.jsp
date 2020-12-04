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


        /*frans*/

        /*#demo {*/
            /*-webkit-box-shadow: 2px 0px 24px 1px rgba(0,0,0,0.44);*/
            /*box-shadow:2px 0px 24px 1px rgba(0,0,0,0.44);*/
        /*}*/

    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_positionBagian'/>";
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
            Sub Bidang/Divisi
        </h1>
        <%--<ol class="breadcrumb">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
        <%--<li class="active">Here</li>--%>
        <%--</ol>--%>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="positionBagianForm" method="post"  theme="simple" namespace="/positionBagian" action="search_positionBagian.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>

                        <table>
                            <tr >
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >
                            <tr>
                                <td width="20%">
                                    <label class="control-label"><small>Sub Bidang/Divisi Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="positionBagianId" name="positionBagian.bagianId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td width="18%">
                                    <label class="control-label"><small>Sub Bidang/Divisi Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="positionBagianName" name="positionBagian.bagianName"
                                                      required="false" readonly="false" cssClass="form-control" cssStyle="margin-top: 5px"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Bidang/Devisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDept" namespace="/department" name="initComboDepartment_department"/>
                                        <s:select list="#comboDept.listComboDepartment" id="departmentId" name="positionBagian.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        <%--<s:textfield  id="positionBagianId" name="positionBagian.bagianId"--%>
                                                      <%--required="false" readonly="false" cssClass="form-control" cssStyle="margin-top: 5px"/>--%>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="positionBagian.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="margin-top: 5px" />
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                        <%--<td>--%>
                                        <%--<sj:submit type="button" cssClass="btn btn-primary" formIds="positionBagianForm" id="search" name="search"--%>
                                        <%--onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">--%>
                                        <%--<i class="fa fa-search"></i>--%>
                                        <%--Search--%>
                                        <%--</sj:submit>--%>
                                        <%--</td>--%>
                                            <td >
                                                <a class="btn btn-primary" Style="margin-right: 5px" onclick="searchData() "><i class="fa fa-search"></i> Search</a>
                                            </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/positionBagian" action="add_positionBagian" escapeAmp="false">
                                    </s:url>
                                        <sj:a cssClass="btn btn-success" cssStyle="margin-right: 5px" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Bagian
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger"
                                                onclick="window.location.href='<s:url action="initForm_positionBagian"/>'">
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
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="auto" width="auto" autoOpen="false"
                                                   title="Position Bagian ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>


                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      saveEdit();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        ></sj:dialog>
                                        <%--<s:set name="listOfPositionBagian" value="#session.listOfResult" scope="request" />--%>
                                        <%--<display:table name="listOfPositionBagian" class="table table-condensed table-striped table-hover"--%>
                                                       <%--requestURI="paging_displaytag_positionBagian.action" export="true" id="row" pagesize="14" style="font-size:10">--%>
                                            <%--<display:column media="html" title="Edit">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                    <%--<s:url var="urlEdit" namespace="/positionBagian" action="edit_positionBagian" escapeAmp="false">--%>
                                                        <%--<s:param name="id"><s:property value="#attr.row.bagianId"/></s:param>--%>
                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>--%>
                                                    <%--</s:url>--%>
                                                    <%--<sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                    <%--</sj:a>--%>
                                                <%--</s:if>--%>
                                            <%--</display:column>--%>

                                            <%--<display:column media="html" title="Delete" style="text-align:center;font-size:9">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                    <%--<s:url var="urlViewDelete" namespace="/positionBagian" action="delete_positionBagian" escapeAmp="false">--%>
                                                        <%--<s:param name="id"><s:property value="#attr.row.bagianId" /></s:param>--%>
                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                    <%--</s:url>--%>
                                                    <%--<sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                    <%--</sj:a>--%>
                                                <%--</s:if>--%>
                                            <%--</display:column>--%>
                                            <%--<display:column property="bagianId" sortable="true" title="Sub Bidang/Divisi Id" />--%>
                                            <%--<display:column property="bagianName" sortable="true" title="Sub Bidang/Divisi Name"  />--%>
                                            <%--<display:column property="divisiName" sortable="true" title="Bidang/Divisi"  />--%>
                                            <%--<display:column property="kodering" sortable="true" title="Kodering"  />--%>
                                            <%--<display:column property="flag" sortable="true" title="flag"  />--%>
                                            <%--<display:column property="action" sortable="true" title="action"  />--%>
                                            <%--<display:column property="createdDate" sortable="true" title="Created date"  />--%>
                                            <%--<display:column property="createdWho" sortable="true" title="Created who"  />--%>
                                            <%--<display:column property="lastUpdate" sortable="true" title="Last update"  />--%>
                                            <%--<display:column property="lastUpdateWho" sortable="true" title="Last update who"  />--%>
                                        <%--</display:table>--%>
                                        <table class="table table-bordered" style="font-size: 13px;">
                                            <thead >
                                            <tr style="background-color:#30d196;">
                                                <%--<td>Departement ID</td>--%>
                                                <td>Name</td>
                                                <td>Created Date</td>
                                                <td>Last Update</td>
                                                <td>Created Who</td>
                                                <td>Last Update Who</td>
                                                <td>Edit</td>
                                                <td>Delete</td>

                                            </tr>
                                            </thead>
                                            <tbody id="body_masuk">

                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
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

<%--modal--%>
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Data</h4>
            </div>
            <div class="modal-body">
                <s:form cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >Id sub bidang :</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="iddevisiedit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama devisi :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="devisiedit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Bidang/Divisi :</label>
                        <div class="col-sm-8">
                            <select id="department-id-edit" class="form-control">
                            </select>
                            <%--<s:action id="comboDept" namespace="/department" name="initComboDepartment_department"/>--%>
                            <%--<s:select list="#comboDept.listComboDepartment" id="departmentId-edit"--%>
                                      <%--listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button id="btnEdit" type="button" class="btn btn-default btn-primary" onclick="saveEdit()"><i class="fa fa-pencil"></i>Save</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
            </div>
        </div>
    </div>
</div>
<%--modal delete--%>
<div id="modal-delete" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Data</h4>
            </div>
            <div class="modal-body">
                <s:form cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >Id sub bidang :</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="iddevisidelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama devisi :</label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="devisidelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Bidang/Divisi :</label>
                        <div class="col-sm-8">
                            <select id="department-id-delete" class="form-control" disabled>
                            </select>
                                <%--<s:action id="comboDept" namespace="/department" name="initComboDepartment_department"/>--%>
                                <%--<s:select list="#comboDept.listComboDepartment" id="departmentId-edit"--%>
                                <%--listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" class="btn btn-default btn-primary" onclick="saveDelete()"><i class="fa fa-trash"></i> Delete</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->

<script type='text/javascript' src='<s:url value="/dwr/interface/PositionBagianAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/DepartmentAction.js"/>'></script>
<script>

    function searchData(){
        var id = $('#positionBagianId').val();
        var dp = $ ('#positionBagianName').val();
        PositionBagianAction.searchHead(id,dp, function (response) {
//            console.log(response);
            if(response.length > 0){
                var body = '';
                $.each(response, function (i, item) {
                    var icon = '';
//                    if(item.departmentId != null && item.departmentId != ''){
//                        icon = '<i id="icon_'+item.departmentId+'" class="fa fa-plus" style="cursor: pointer" onclick="anakNya(\''+item.departmentId+'\')"></i>';
//                    }

                    if(item.departmentId != null && item.departmentId != ''){
                        icon = '<i id="icon_'+item.departmentId+'" class="fa fa-plus" style="cursor: pointer" onclick="anakNya(\''+item.departmentId+'\')"></i>';
                    }

                    body += '<tr id="row_'+item.departmentId+'" style="background-color: gainsboro">' +
//                            '<td>'+icon+" "+item.departmentId+'</td>'+
                            '<td>'+icon+" "+item.departmentName+'</td>'+
                        '<td>'+item.stCreatedDate+'</td>'+
                        '<td>'+item.createdWho+'</td>'+
                        '<td>'+item.stLastUpdate+'</td>'+
                        '<td>'+item.lastUpdateWho+'</td>'+
                        '<td></td>'+
                        '<td></td>'+
                            '<tr>';
                });

                $('#body_masuk').html(body);
            }
        });
    }

    function anakNya(id){
        // console.log(id);
        //function dwr ke belakang dengan id di parameter
        // var pb = $('#positionBagianId').val();
        PositionBagianAction.searchDetail(id,function (response) {
            if(response.length > 0){
                var tr = '';
                $.each(response, function (i, item) {
                    tr += '<tr class="del_'+id+'">' +
//                        '<td><span style="margin-left: 20px">'+item.bagianId+'</span></td>'+
                        '<td><span style="margin-left: 20px">'+item.bagianName+'</span></td>'+
                        '<td>'+item.stCreatedDate+'</td>'+
                        '<td>'+item.createdWho+'</td>'+
                        '<td>'+item.stLastUpdate+'</td>'+
                        '<td>'+item.lastUpdateWho+'</td>'+
                        '<td><button type="button" id="demo" class="btn btn-success " onclick="edit(\''+item.bagianId+'\')" > <span class="glyphicon glyphicon-pencil"></span></button></td>'+
                        '<td><button type="button" id="demo" class="btn btn-danger "  onclick="delet(\''+item.bagianId+'\')" > <span class="glyphicon glyphicon-trash"></span></button></td>'+
                        '</tr>';
                });
                var newRecord = $(tr);
                newRecord.insertAfter($('table').find('#row_'+id));
                $('#icon_'+id).removeClass("fa fa-plus");
                $('#icon_'+id).addClass("fa fa-minus");
                $('#icon_'+id).attr('onclick', 'delRow(\''+id+'\')');

            }
        });
    }

    function edit(id) {

        PositionBagianAction.getDataById(id, function (postionBagianObj) {
            getDepartment(postionBagianObj.divisiId);
            $("#iddevisiedit").val(postionBagianObj.bagianId);
            $("#devisiedit").val(postionBagianObj.bagianName);
            $("#departmentid").val(postionBagianObj.divisiId);
        });
        $('#modal-edit').modal("show");
    }

    function getDepartment(divisiId) {
        console.log(divisiId);
        DepartmentAction.getListDepartmentAll( function (res) {
            console.log(res);
            var str = "";
            $.each(res, function (i, item) {
               if (item.departmentId == divisiId){
                   str += '<option value="'+item.departmentId+'" selected>'+item.departmentName+'</option>';
               } else {
                   str += '<option value="'+item.departmentId+'">'+item.departmentName+'</option>';
               }
            });
            $('#department-id-edit').html(str);
            $('#department-id-delete').html(str);
        });
    }

    function delet(id){

        PositionBagianAction.getDataById(id, function (postionBagianObj) {
            getDepartment(postionBagianObj.divisiId);
            $("#iddevisidelete").val(postionBagianObj.bagianId);
            $("#devisidelete").val(postionBagianObj.bagianName);
            $("#departmentid").val(postionBagianObj.divisiId);
        });
        $('#modal-delete').modal("show");
    }

    function saveEdit() {
      var iddevisi = $("#iddevisiedit").val();
      var namadevisi = $("#devisiedit").val();
      var bidang = $("#department-id-edit").val();

      PositionBagianAction.saveEditDwr(iddevisi,namadevisi, bidang, function (CrudResponse) {
          if(CrudResponse.status == "succes"){
              // alert("berhasil");
              $('#modal-edit').modal("hide");
              // $('#info_dialog').dialog('open');
              window.location.reload(true);
          }else {
              alert(CrudResponse.msg);
          }
            $('#modal-edit').modal("hide");

          searchData();
      })
      // console.log(iddevisi);
      // console.log(namadevisi);
    }

    function saveDelete() {
    var iddevisi= $("#iddevisidelete").val();
        PositionBagianAction.saveDeleteDwr(iddevisi, function (CrudResponse) {
            if(CrudResponse.status == "succes"){
                alert("delete berhasil");
            }else {
                alert(CrudResponse.msg);
            }
            $('#modal-delete').modal("hide");

            searchData();
        })
    }

    function delRow(id) {
        $('.del_'+id).remove();
        $('#icon_'+id).removeClass("fa fa-minus");
        $('#icon_'+id).addClass("fa fa-plus");
        $('#icon_'+id).attr('onclick', 'anakNya(\''+id+'\')');

    }



</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

