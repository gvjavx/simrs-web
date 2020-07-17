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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ResepOnlineAction.js"/>'></script>
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

        function formatFlag(var1) {
            if (var1 == "Y"){
                return "<label class=\"label label-success\"><i class=\"fa fa-check\"></i></label>";
            } else {
                return "<label class=\"label label-danger\"><i class=\"fa fa-times\"></i></label>";
            }
        }

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
                                <h3 class="box-title"><i class="fa fa-filter"></i> Monitoring Pengiriman Obat</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="transaksiForm" method="post" namespace="/reseponline"
                                    action="searchApprovedResep_reseponline.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">No. Pengriman</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_pengiriman" cssStyle="margin-top: 7px"
                                                     name="pengirimanObat.id" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                    <input type="hidden" name="pengirimanObat.tipe" value="monitoring"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Resep</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_resep" cssStyle="margin-top: 7px"
                                                     name="pengirimanObat.idResep" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Kurir</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_kurir" cssStyle="margin-top: 7px"
                                                     name="pengirimanObat.idKurir" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Alamat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="alamat" cssStyle="margin-top: 7px"
                                                     name="pengirimanObat.alamat" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'pickup':'Pickup','diterima':'Diterima Pasien'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="status" name="permintaanResep.status"
                                                  headerKey="assign" headerValue="Assign"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
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
                                        <a type="button" class="btn btn-danger" href="initForm_reseponline.action?tipe=monitoring">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a type="button" class="btn btn-warning" href="initForm_reseponline.action">
                                            <i class="fa fa-arrow-left"></i> Back
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pengiriman Obat</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="myTable" style="font-size: 12px;">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Pengiriman</td>
                                <td>ID Resep</td>
                                <td>ID Pasien</td>
                                <td>Nama</td>
                                <td>Kurir</td>
                                <td>Alamat</td>
                                <td>Flag Pickup</td>
                                <td>Flag Diterima</td>
                                <td align="center">View Detail</td>
                            </tr>
                            </thead>
                            <tbody id="list-body-pengiriman">
                            <s:iterator value="#session.listOfResults" id="listOfResultObat" var="row">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="idResep"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="pasienName"/></td>
                                    <td><s:property value="idKurir"/> - <s:property value="kurirName"/></td>
                                    <td><s:property value="alamat"/></td>
                                    <td align="center"><script>document.write(formatFlag('<s:property value="flagPickup"/>'))</script></td>
                                    <td align="center"><script>document.write(formatFlag('<s:property value="flagDiterimaPasien"/>'))</script></td>
                                    <td align="center">
                                        <button class="btn btn-sm btn-success" onclick="viewDetail()"><i class="fa fa-search"></i></button>
                                        <%--<img border="0" class="hvr-grow" onclick="viewAssign('<s:property value="idPasien"/>','<s:property value="idPelayanan"/>','<s:property value="idPermintaanResep"/>')"--%>
                                             <%--src="<s:url value="/pages/images/icons8-create-25.png"/>"--%>
                                             <%--style="cursor: pointer;">--%>
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

                    <input type="hidden" id="v-id-pasien"/>
                    <input type="hidden" id="v-id-resep"/>
                    <input type="hidden" id="v-id-pelayanan"/>
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
                        <p id="msg_fin">Success Assign</p>
                    </div>
                </div>
                <div class="box-header with-border"></div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="saveAssignKurir()"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button type="button" class="btn btn-success" id="ok_fin" onclick="searchAgain()"><i class="fa fa-arrow-right"></i> Ok
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
    
   function viewAssign(idPasien, idPelayanan, idResep) {
       $("#modal-kurir").modal('show');
       $("#ok_fin").hide();
       $("#save_fin").show();
       ResepOnlineAction.listKurirByBranchLogin(function (listKurir) {
           if (listKurir.length > 0){

               var str = "";
               $.each(listKurir, function (i, item) {
                   str += "<option value='"+item.idKurir+"'>"+item.nama+"</option>";
               });
           }

           $("#v-id-pasien").val(idPasien);
           $("#v-id-resep").val(idResep);
           $("#v-id-pelayanan").val(idPelayanan);
           $("#sel-kurir").html(str);
       })
   }
   
   function saveAssignKurir() {
       var idPasien = $("#v-id-pasien").val();
       var idResep = $("#v-id-resep").val();
       var idPelayanan = $("#v-id-pelayanan").val();
       var idKurir = $("#sel-kurir").val();

       ResepOnlineAction.saveAssignKurir(idKurir, idResep, idPasien, idPelayanan, function (reseponse) {

           if (reseponse.status == "success"){
               $("#success_fin").show().fadeOut(5000);
               $("#ok_fin").show();
               $("#save_fin").hide();
           } else {
               $("#warning_fin").show().fadeOut(5000);
               $("#msg_fin_error").text(reseponse.msg);
           };
       })
   }
   
   function searchAgain() {
       $("#transaksiForm").submit();
   }
   

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>