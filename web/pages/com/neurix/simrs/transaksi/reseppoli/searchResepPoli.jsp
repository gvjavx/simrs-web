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
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TransaksiObatAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {

            $('#resep_poli').addClass('active');
            $('#add_resep').focus();

        });

        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $("html, body").animate({scrollTop: 0}, 600);
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $("html, body").animate({scrollTop: 0}, 600);
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Resep Poli
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
                        <%--<h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Resep Poli</h3>--%>
                            <div class="row">
                                <div class="col-md-4">
                                    <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Resep Poli</h3>
                                </div>
                                <%--<div class="col-md-3 pull-right">--%>
                                    <%--<div class="input-group date">--%>
                                        <%--<input class="form-control" id="add_resep" placeholder="Antrian" onchange="saveAntrian()">--%>
                                        <%--<div class="input-group-addon btn btn-success" onclick="saveAntrian()" id="save_resep">--%>
                                            <%--<i class="fa fa-arrow-right" style="cursor: pointer"></i> Save--%>
                                        <%--</div>--%>
                                        <%--<div class="input-group-addon btn btn-success" id="load_resep" style="display: none">--%>
                                            <%--<i class="fa fa-spinner fa-spin" style="cursor: no-drop"></i> Sedang menyimpan...--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group">

                            <s:form id="resepPoliForm" method="post" namespace="/reseppoli"
                                    action="searchResepPasien_reseppoli.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Resep</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_resep" cssStyle="margin-top: 7px"
                                                     name="permintaanResep.idPermintaanResep" required="false"
                                                     readonly="false" cssClass="form-control" onchange="saveAntrian()"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No Checkup Detail</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_detail_checkup" cssStyle="margin-top: 7px"
                                                     name="permintaanResep.idDetailCheckup" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama" cssStyle="margin-top: 7px"
                                                     name="permintaanResep.namaPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'4':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="permintaanResep.status"
                                                  headerKey="3" headerValue="Proses"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>

                                <s:hidden name="permintaanResep.isUmum" value="N"></s:hidden>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="resepPoliForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_reseppoli.action">
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Resep Poli</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="sortTable">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Resep</td>
                                <td>No Checkup Detail</td>
                                <td>Nama</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanResep"/></td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="status"/></td>
                                    <td align="center">
                                        <s:if test='#row.flag == "N"'>
                                            <s:url var="add_print" namespace="/reseppoli" action="printStrukResepPasien_reseppoli" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="idResep"><s:property value="idPermintaanResep"/></s:param>
                                                <s:param name="idApprove"><s:property value="idApprovalObat"/></s:param>
                                            </s:url>
                                            <s:a href="%{add_print}" target="_blank">
                                                <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">
                                            </s:a>
                                        </s:if>
                                        <s:else>
                                            <s:url var="add_proses" namespace="/reseppoli" action="searchResep_reseppoli" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="idPermintaan"><s:property value="idPermintaanResep"/></s:param>
                                            </s:url>
                                            <s:a href="%{add_proses}">
                                                <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                            </s:a>
                                        </s:else>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<script src="https://code.responsivevoice.org/responsivevoice.js"></script>
<script type='text/javascript'>

    function tesSuara(){
       var text =  $('#namaOrang').val();
        responsiveVoice.speak(text,"Indonesian Female");
    }

    function saveAntrian(){
        var idResep = $('#add_resep').val();

        if(idResep != ''){
            $('#save_resep').hide();
            $('#load_resep').show();

            setTimeout(function () {
                TransaksiObatAction.saveAntrianResep(idResep, "N", function (response) {
                    if (response == "success") {
                        $('#save_resep').show();
                        $('#load_resep').hide();
                        $('#no_resep').val(idResep);
                        document.resepPoliForm.action = 'searchResepPasien_reseppoli.action';
                        document.resepPoliForm.submit();
                    } else {
                        $('#save_resep').show();
                        $('#load_resep').hide();
                    }
                })
            },1500)
//            dwr.engine.setAsync(true);
        }else{

        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>