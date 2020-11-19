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
            Training
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval Training</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN23">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>ID Training</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="trainingId" name="trainingPerson.trainingId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                            <s:hidden name="trainingPerson.approvalId"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>NIP</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="personId" name="trainingPerson.personId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
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
                                    </tr>
                                </table>
                            </div>

                            <center>
                                <table id="showdata" width="70%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="420" width="600" autoOpen="false"
                                                       title="Training">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResult" value="#session.listOfResultPerson" scope="request" />
                                            <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_medicalrecord.action" export="false" id="row" pagesize="20" style="font-size:10">

                                                <display:column media="html" title="Approve Kabid/GM" style="text-align:center;font-size:9">
                                                    <s:if test="%{#attr.row.approveKepala}">
                                                        <s:url var="urlViewDoc" namespace="/training" action="approveAtasan_training" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.trainingPersonId" /></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_trash">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column media="html" title="Approve Atasan" style="text-align:center;font-size:9">
                                                    <s:if test="%{#attr.row.approveAtasan}">
                                                        <s:url var="urlViewDoc" namespace="/training" action="approveAtasan_training" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.trainingPersonId" /></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_trash">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>
                                                <display:column property="trainingId" sortable="true" title="ID Training" />
                                                <display:column property="trainingName" sortable="true" title="Nama Training"  />
                                                <display:column property="trainingStartdate" sortable="true" title="Tanggal Mulai"  />
                                                <display:column property="trainingEndDate" sortable="true" title="Tanggal Selesai"  />
                                                <display:column property="personName" sortable="true" title="Nama"  />
                                                <display:column property="iconApproveAtasan" sortable="true" title="Approve Atasan"  />
                                                <display:column property="iconApproveSdm" sortable="true" title="Approve SDM"  />
                                                <display:column property="iconApproveKepala" sortable="true" title="Approve Kabid/GM"  />
                                                <display:setProperty name="paging.banner.item_name">Approval</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">Approval</display:setProperty>
                                                <display:setProperty name="export.excel.filename">Approval.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">Approval.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">Approval.pdf</display:setProperty>
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

    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'dd-mm-yy'
        });
//        $('#errorTimestampFrom').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
    });

</script>


