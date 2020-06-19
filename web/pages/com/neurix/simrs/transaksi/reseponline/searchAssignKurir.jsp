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

            $('#transaksi_obat').addClass('active');
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
            Transaksi Apotek
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <%--<h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Transaksi Obat Apotek</h3>--%>
                        <div class="row">
                            <div class="col-md-4">
                                <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Pengiriman Obat</h3>
                            </div>
                            <%--<div class="col-md-3 pull-right">--%>
                                <%--<div class="input-group date">--%>
                                    <%--<input class="form-control" id="add_resep" placeholder="Antrian"--%>
                                           <%--onchange="saveAntrian()">--%>
                                    <%--<div class="input-group-addon btn btn-success" onclick="saveAntrian()"--%>
                                         <%--id="save_resep">--%>
                                        <%--<i class="fa fa-arrow-right" style="cursor: pointer"></i> Save--%>
                                    <%--</div>--%>
                                    <%--<div class="input-group-addon btn btn-success" id="load_resep"--%>
                                         <%--style="display: none">--%>
                                        <%--<i class="fa fa-spinner fa-spin" style="cursor: no-drop"></i> Sedang--%>
                                        <%--menyimpan...--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="transaksiForm" method="post" namespace="/reseponline"
                                    action="searchApprovedResep_reseponline.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Resep</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_resep" cssStyle="margin-top: 7px"
                                                     name="permintaanResep.idPermintaanResep" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">No Checkup Detail</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:textfield id="id_detail_checkup" cssStyle="margin-top: 7px"--%>
                                                     <%--name="permintaanResep.idDetailCheckup" required="false"--%>
                                                     <%--readonly="false" cssClass="form-control"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama" cssStyle="margin-top: 7px"
                                                     name="permintaanResep.namaPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Status</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'3':'Proses','4':'Selesai'}"--%>
                                                  <%--cssStyle="margin-top: 7px"--%>
                                                  <%--id="status" name="permintaanResep.status"--%>
                                                  <%--headerKey="0" headerValue="Antrian"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <s:hidden name="permintaanResep.isUmum" value="Y"></s:hidden>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="transaksiForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-primary" href="pembelianObat_transaksi.action">
                                            <i class="fa fa-plus"></i> Beli Obat
                                        </a>
                                        <a type="button" class="btn btn-danger" href="initForm_transaksi.action">
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Transaski Obat</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="myTable">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Resep</td>
                                <td>No Checkup Detail</td>
                                <td>Nama</td>
                                <td>Alamat</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResults" id="listOfResultObat" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanResep"/></td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="status"/></td>
                                    <td align="center">
                                        <img border="0" class="hvr-grow" onclick="viewAssign('<s:property value="idPermintaanResep"/>')"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>"
                                             style="cursor: pointer;">
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

<div class="modal fade" id="modal-kurir">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Assign Kurir</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">

                    <div class="row">
                        <div class="col-md-2 col-md-offset-2">
                            <label>Kurir</label>
                        </div>
                        <div class="col-md-4">
                            <select class="form-control" id="sel-kurir">

                            </select>
                        </div>
                    </div>

                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_fin_error"></p>
                    </div>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_fin"></p>
                    </div>
                </div>
                <div class="box-header with-border"></div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="saveKonfirm()"><i class="fa fa-arrow-right"></i> Save
                </button>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                <%--id="load_fin"><i--%>
                <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

   function viewAssign(var1) {
       $("#modal-kurir").modal('show');
   }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>