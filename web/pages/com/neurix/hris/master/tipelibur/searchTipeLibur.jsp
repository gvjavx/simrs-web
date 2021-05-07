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
            Tipe Libur
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

                    <form role="form" method="post" id="tipeliburForm" action="search_tipelibur.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <table align="center" width="100%">
                                <tr>
                                    <td align="center">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label>Tipe Libur Id </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="tipeLiburId" name="tipeLibur.tipeLiburId" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>Tipe Libur Name </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="tipeLiburName" name="tipeLibur.tipeLiburName" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>Flag </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:select list="#{'N':'Non-Active'}" id="flag" name="tipeLibur.flag"
                                                              headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </td>
                                            </tr>
                                        </table>
                                        <br><br>
                                        <table>
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="tipeliburForm" id="search" name="search"
                                                               onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <s:url var="urlAdd" namespace="/tipelibur" action="add_tipelibur" escapeAmp="false">
                                                    </s:url>
                                                    <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}"
                                                    >
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                        <i class="fa fa-plus"></i>
                                                        Add Tipe Libur
                                                    </sj:a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_tipelibur"/>'">
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
                                <table id="showdata" width="40%">
                                    <tr>
                                        <td align="center">
                                            <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                       <%--height="500" width="900" autoOpen="false"--%>
                                                       <%--title="Tipe Libur">--%>
                                                <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                            <%--</sj:dialog>--%>
                                                <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu"
                                                           closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false"
                                                           title="Proses Tipe Lembur">
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

                                            <s:set name="listOfAlat" value="#session.listOfResult" scope="request" />
                                            <display:table name="listOfAlat" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_tipelibur.action" export="true" id="row" pagesize="10" style="font-size:10">
                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/tipelibur" action="edit_tipelibur" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.tipeLiburId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/tipelibur" action="delete_tipelibur" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.tipeLiburId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>

                                                </display:column>
                                                <display:column property="tipeLiburId" sortable="true" title="Tipe Libur Id" />
                                                <display:column property="tipeLiburName" sortable="true" title="Tipe Libur Name"  />
                                                <display:column property="flag" sortable="true" title="Flag" />
                                                <display:column property="action" sortable="true" title="Action" />
                                                <display:column property="stCreatedDate" sortable="true" title="Created Date" />
                                                <display:column property="stLastUpdate" sortable="true" title="Last Update" />
                                                <display:column property="createdWho" sortable="true" title="Created Who" />
                                                <display:column property="lastUpdateWho" sortable="true" title="Last Update Who" />
                                                <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>

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


