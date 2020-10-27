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
    <script type='text/javascript' src='<s:url value="/dwr/interface/AlatAction.js"/>'></script>


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
            Status Pegawai
        </h1>
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

                    <form role="form" method="post" id="searchForm" action="search_tipepegawai.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label>Status Pegawai ID </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="tipePegawaiId" name="tipePegawai.tipePegawaiId" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>Status Pegawai Name </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="tipePegawaiName" name="tipePegawai.tipePegawaiName" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>Flag </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:select list="#{'N':'Non-Active'}" id="flag" name="tipePegawai.flag"
                                                              headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </td>
                                            </tr>
                                        </table>
                                        <br><br>
                                        <table>
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary " formIds="searchForm" id="search" name="search"
                                                               onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <s:url var="urlAdd" namespace="/tipepegawai" action="add_tipepegawai" escapeAmp="false">
                                                    </s:url>
                                                    <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                        <i class="fa fa-plus"></i>
                                                        Add Status Pegawai
                                                    </sj:a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_tipepegawai"/>'">
                                                        <i class="fa fa-repeat"></i> Reset
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br><br>

                            <center>
                                <table id="showdata" width="70%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="950" autoOpen="false"
                                                       title="Status Pegawai">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfAlat" value="#session.listOfResult" scope="request" />
                                            <display:table name="listOfAlat" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_tipepegawai.action" export="true" id="row" pagesize="10" style="font-size:10">
                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/tipepegawai" action="edit_tipepegawai" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.tipePegawaiId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlViewDelete" namespace="/tipepegawai" action="delete_tipepegawai" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.tipePegawaiId" /></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>
                                                <display:column property="tipePegawaiId" sortable="true" title="Status Pegawai ID" />
                                                <display:column property="tipePegawaiName" sortable="true" title="Status Pegawai Name"  />
                                                <display:column property="flag" sortable="true" title="flag"  />
                                                <display:column property="action" sortable="true" title="action"  />
                                                <display:column property="createdDate" sortable="true" title="Created date"  />
                                                <display:column property="createdWho" sortable="true" title="Created who"  />
                                                <display:column property="lastUpdate" sortable="true" title="Last update"  />
                                                <display:column property="lastUpdateWho" sortable="true" title="Last update who"  />
                                                <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>
                                                <display:setProperty name="export.excel.filename">StatusPegawai.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">StatusPegawai.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">StatusPegawai.pdf</display:setProperty>
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


