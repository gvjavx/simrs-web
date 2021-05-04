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
    <%--<script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>--%>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/AlatAction.js"/>'></script>--%>
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
        th {
            text-align: center;
        }
    </style>
    <script type='text/javascript'>
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };

        function cancelBtn2() {
            $('#view_dialog_function2').dialog('close');
        };

        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });


        function showLoadingDialog(){
            $('#myModalLoading').modal('show');
        }

        function showAlert(){
            var verif = document.getElementById('verif').value;
            var erVerif = document.getElementById('erVerif').value;
            if(verif !=  ""){
                document.getElementById('succesAlert').style.display = 'block';
                var sc = document.getElementById('succesAlert').value;
                if ( sc != ""){
                    sc = "";
                }
                $("#succesAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("succesAlert").slideUp(500);
                });
            }else if(erVerif != "") {
                document.getElementById('errorAlert').style.display = 'block';
                erVerif = null;
                $("#errorAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("errorAlert").slideUp(500);
                });
            }
        }

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Group Shift
            <small>e-HEALTH</small>
        </h1>
        <%--<ol class="breadcrumb">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
        <%--<li class="active">Here</li>--%>
        <%--</ol>--%>
    </section>


    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="search_groupShift.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label class="control-label"><small>Unit :</small></label>
                                        </td>
                                        <td>
                                            <table>
                                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="groupShift.branchId" required="true"
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="control-label"><small>NIP :</small></label>
                                        </td>
                                        <td>
                                            <table>
                                                <s:textfield id="nip32" name="groupShift.nip" required="true" disabled="false" cssClass="form-control"/>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="control-label"><small>Group Id :</small></label>
                                        </td>
                                        <td>
                                            <table>
                                                <s:action id="comboGroup" namespace="/group" name="initComboGroup_group"/>
                                                <s:select cssClass="form-control" list="#comboGroup.listOfComboGroup" id="groupId" name="groupShift.groupId" required="true"
                                                          listKey="groupId" listValue="groupName" headerKey="" headerValue="[Select one]" />
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="control-label"><small>Shift Id :</small></label>
                                        </td>
                                        <td>
                                            <table>
                                                <s:action id="comboShift" namespace="/shift" name="initComboShift_shift"/>
                                                <s:select cssClass="form-control" list="#comboShift.listOfComboShift" id="shiftId" name="groupShift.shiftId" required="true"
                                                          listKey="shiftId" listValue="shiftName" headerKey="" headerValue="[Select one]" />
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                            </div>
                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>
                                        </td>
                                        <td>
                                            <s:url var="urlAdd" namespace="/groupShift" action="add_groupShift" escapeAmp="false">
                                            </s:url>
                                            <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                <i class="fa fa-plus"></i>
                                                Add Group Shift
                                            </sj:a>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_groupShift"/>'">
                                                <i class="fa fa-repeat"></i> Reset
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <center>
                                <table id="showdata" width="40%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="600" autoOpen="false"
                                                       title="Group Shift">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfGroupShift" value="#session.listOfResult" scope="request" />
                                            <display:table name="listOfGroupShift" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_groupShift.action" export="true" id="row" pagesize="10" style="font-size:10">
                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/groupShift" action="edit_groupShift" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.groupShiftId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>
                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/groupShift" action="delete_groupShift" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.groupShiftId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </display:column>
                                                <display:column property="groupShiftName" sortable="true" title="Nama"/>
                                                <display:column property="shiftName" sortable="true" title="Shift"/>
                                                <display:column property="groupName" sortable="true" title="Group"/>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>
                    </form>
                </div>
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

</body>
</html>

<script type="application/javascript">


    var tmp_data = new Array();
    var namaMt = [];

    function cek(id, flag){
        alert(id + " " + flag);
    }

    function ok(id, flag){
        //alert(id + " " + flag);

        dwr.engine.setAsync(false);
        AlatAction.init(id, flag, function (response) {
            namaMt = response;
        });

        //alert('idnya : ' + namaMt.kodeAlat + ' flag : ' + namaMt.flag);
        $('input[id=kodeAlat]').val(namaMt.kodeAlat);
        $('input[id=namaAlat]').val(namaMt.namaAlat);
        $('input[id=flag]').val(namaMt.flag);


        $('#modal-edit').modal('show');
    }

</script>


