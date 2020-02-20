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
            Notifikasi
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

                    <form role="form" method="post" id="searchForm" action="search_training.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Notif Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="trainingId" name="notifikasi.notifId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Note </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textarea rows="10" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px"  name="notifikasi.note" readonly="true"/>
                                        </td>
                                    </tr>

                                </table>
                                <br>

                            </div>
                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <%--<td>--%>
                                            <%--<sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"--%>
                                                       <%--onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">--%>
                                                <%--<i class="fa fa-search"></i>--%>
                                                <%--Search--%>
                                            <%--</sj:submit>--%>
                                        <%--</td>--%>
                                        <%--<td>--%>
                                            <%--<button type="button" class="btn btn-success" onclick="window.location.href='<s:url action="add_training"/>' ">--%>
                                                <%--<i class="fa fa-plus"></i> Add Training--%>
                                            <%--</button>--%>

                                        <%--</td>--%>
                                        <%--<td>--%>

                                            <%--<button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_training"/>'">--%>
                                                <%--<i class="fa fa-repeat"></i> Reset--%>
                                            <%--</button>--%>
                                        <%--</td>--%>

                                    </tr>
                                </table>
                            </div>


                            <%--<center>--%>
                                <%--<table id="showdata" width="80%">--%>
                                    <%--<tr>--%>
                                        <%--<td align="center">--%>
                                            <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                       <%--height="500" width="900" autoOpen="false"--%>
                                                       <%--title="Training">--%>
                                                <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                            <%--</sj:dialog>--%>

                                            <%--<s:set name="listOfResult" value="#session.listOfResult" scope="request" />--%>
                                            <%--<display:table name="listOfResult" class="table table-condensed table-striped table-hover"--%>
                                                           <%--requestURI="paging_displaytag_medicalrecord.action" export="true" id="row" pagesize="20" style="font-size:10">--%>

                                                <%--<display:column media="html" title="Delete" style="text-align:center;font-size:9">--%>
                                                    <%--<s:url var="urlViewDelete" value="edit_training.action" >--%>
                                                        <%--<s:param name="id"><s:property value="#attr.row.trainingId" /></s:param>--%>
                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                        <%--<s:param name="delete">Y</s:param>--%>
                                                    <%--</s:url>--%>
                                                    <%--<s:a href="%{urlViewDelete}">--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                    <%--</s:a>--%>

                                                <%--</display:column>--%>


                                                <%--<display:column media="html" title="Edit">--%>
                                                    <%--<s:if test="%{#attr.row.edit}">--%>
                                                        <%--<s:url var="urlEdit" value="edit_training.action">--%>
                                                            <%--<s:param name="id"><s:property value="#attr.row.trainingId"/></s:param>--%>
                                                            <%--<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>--%>
                                                        <%--</s:url>--%>
                                                        <%--<s:a href="%{urlEdit}">--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                        <%--</s:a>--%>
                                                    <%--</s:if>--%>
                                                    <%--<s:else>--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                    <%--</s:else>--%>
                                                <%--</display:column>--%>


                                                <%--<display:column media="html" title="Close">--%>
                                                    <%--<s:if test="%{#attr.row.approve}">--%>
                                                        <%--<s:url var="urlApprove" namespace="/training" action="initClose_training" escapeAmp="false">--%>
                                                            <%--<s:param name="id"><s:property value="#attr.row.trainingId"/></s:param>--%>
                                                            <%--<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>--%>
                                                        <%--</s:url>--%>
                                                        <%--<sj:a onClickTopics="showDialogMenu" href="%{urlApprove}">--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">--%>
                                                        <%--</sj:a>--%>
                                                    <%--</s:if>--%>
                                                    <%--<s:else>--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                    <%--</s:else>--%>
                                                <%--</display:column>--%>


                                                <%--<display:column media="html" title="View" style="text-align:center;font-size:9">--%>
                                                    <%--<s:url var="urlViewDoc" namespace="/training" action="initClose_training" escapeAmp="false">--%>
                                                        <%--<s:param name="id"><s:property value="#attr.row.trainingId" /></s:param>--%>
                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                        <%--<s:param name="view">Y</s:param>--%>
                                                    <%--</s:url>--%>
                                                    <%--<sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/view_detail_project.png"/>" name="icon_trash">--%>
                                                    <%--</sj:a>--%>

                                                <%--</display:column>--%>



                                                <%--&lt;%&ndash;<display:column media="html" title="Approve">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<s:if test="%{#attr.row.approve}">&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;<s:url var="urlApprove" namespace="/medicalrecord" action="initApprove_medicalrecord" escapeAmp="false">&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;</s:url>&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;<sj:a onClickTopics="showDialogMenu" href="%{urlApprove}">&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;</sj:a>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;</s:if>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<s:else>&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;</s:else>&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;</display:column>&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;<display:column media="html" title="Print<br> Surat Jaminan">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<s:if test="%{#attr.row.print}">&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;<s:url var="urlPrint" namespace="/medicalrecord" action="printSuratJaminan_medicalrecord" escapeAmp="false">&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;</s:url>&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;<s:a href="%{urlPrint}">&ndash;%&gt;--%>
                                                            <%--&lt;%&ndash;<img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;</s:a>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;</s:if>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<s:else>&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;</s:else>&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;</display:column>&ndash;%&gt;--%>
                                                <%--<display:column property="trainingId" sortable="true" title="Training Id" />--%>
                                                <%--<display:column property="trainingName" sortable="true" title="Training Name"  />--%>
                                                <%--<display:column property="tipeTraining" sortable="true" title="Tipe Training"  />--%>
                                                <%--<display:column property="instansi" sortable="true" title="Instansi"  />--%>
                                                <%--<display:column property="trainingStartDate" sortable="true" title="Training Start Date"  />--%>
                                                <%--<display:column property="trainingEndDate" sortable="true" title="Training End Date"  />--%>
                                                <%--<display:column property="iconApprove" sortable="true" title="Closed Status"  />--%>
                                            <%--</display:table>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</table>--%>
                            <%--</center>--%>
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

    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'dd-mm-yy'
        });
//        $('#errorTimestampFrom').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
    });

</script>
