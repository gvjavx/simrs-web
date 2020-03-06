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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#harga_obat').addClass('active');
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
            Harga Obat
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Harga Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatForm" method="post" namespace="/hargaobat" action="searchHargaObat_hargaobat.action" theme="simple" cssClass="form-horizontal">
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">ID Obat</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:textfield id="id_pabrik" cssStyle="margin-top: 7px"--%>
                                                     <%--name="obat.idObat" required="false"--%>
                                                     <%--readonly="false" cssClass="form-control"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Jenis Obat</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                                                  <%--name="getListJenisObat_jenisobat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                                                  <%--listKey="idJenisObat"--%>
                                                  <%--listValue="namaJenisObat"--%>
                                                  <%--name="obat.idJenisObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:action id="initObat" namespace="/obat"
                                                  name="getListObat_obat"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initObat.listOfObat" id="nama_obat"
                                                  listKey="idObat"
                                                  listValue="namaObat"
                                                  name="obat.idObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        <%--<s:textfield id="nama_pasien" name="obat.namaObat"--%>
                                                     <%--required="false" readonly="false"--%>
                                                     <%--cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Flag</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select cssClass="form-control" cssStyle="margin-top: 7px" list="#{'N':'Non Active'}" headerKey="Y" headerValue="Active" name="obat.flag"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_hargaobat.action">
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
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         document.obatForm.action = 'search_obat.action';
                                                                                         document.obatForm.submit();
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Harga Obat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Merk</td>
                                <td>Harga Rata-rata (Bijian)</td>
                                <td>Harga Net</td>
                                <td>Diskon</td>
                                <td>Harga Jual</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfPeriksaLab" var="row">
                                <tr>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="merk"/></td>
                                    <td><s:property value="averageHargaBiji"/></td>
                                    <td><s:property value="hargaNet"/></td>
                                    <td><s:property value="diskon"/>%</td>
                                    <td><s:property value="hargaJual"/></td>
                                    <td align="center">
                                        <img onclick="editObat('<s:property value="idObat"/>','<s:property value="idBarang"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">

                                            <%--<s:a href="%{print_id_pabrik}" target="_blank">--%>
                                                <%--<img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">--%>
                                            <%--</s:a>--%>
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

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Harga Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_obat">
                    <h4><i class="icon fa fa-ban"></i> Success !</h4>
                    <p id="">Berhasil menyimpan </p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_exits"></p>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">ID Obat</label>
                        <div class="col-md-7">
                            <input id="mod-id-obat" class="form-control" readonly="true">
                            <%--<span id="mod-id-obat"></span>--%>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <input id="mod-nama-obat" class="form-control" readonly="true">
                            <%--<span id="mod-nama-obat"></span>--%>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">Merek</label>
                        <div class="col-md-7">
                            <input id="mod-merk" class="form-control" readonly="true">
                            <%--<span id=""></span>--%>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">Harga Rata-Rata (Bijian)</label>
                        <div class="col-md-7">
                            <input type="number" id="mod-harga-rata" class="form-control" readonly="true">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">Harga Net</label>
                        <div class="col-md-7">
                            <input type="number" id="mod-harga-net" onchange="hitungDiskon()" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">Diskon</label>
                        <div class="col-md-7">
                            <input type="number" id="mod-diskon" onchange="hitungDiskon()" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px">Harga Jual</label>
                        <div class="col-md-7">
                            <input type="number" id="mod-harga-jual" class="form-control" readonly="true">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="close_obat"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat" ><i class="fa fa-arrow-right"></i> Save
                </button>
                <button type="button" class="btn btn-success" id="ok_obat" style="display: none"><i class="fa fa-arrow-right"></i> Ok
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function showModal(){

        $('#add_nama_obat, #add_harga_box, #add_harga_lembar, #add_harga_biji, #add_merek, #add_pabrik, #add_box, #add_lembar_box, #add_lembar, #add_biji_lembar, #add_biji').val('');
        $('#add_jenis_obat').val('').trigger('change');

        var id = "";
        $('#load_obat, #war_nama, #war_jenis, #war_pabrik, #war_merek, #war_biji, #war_harga_box, #war_harga_lembar, #war_harga_biji').hide();
        $('#add_box, #add_lembar_box, #add_lembar, #add_biji_lembar').css('border','');
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal('show');

    }

    function saveObat(id, idBarang){

        var net = $("#mod-harga-net").val();
        var diskon = $("#mod-diskon").val();
        var jual = $("#mod-harga-jual").val();

        var arJson = [];
        arJson.push({"harga_net":net, "diskon":diskon, "harga_jual":jual});
        var stJson = JSON.stringify(arJson);
        ObatAction.saveHargaObat(id, idBarang, stJson, function (response) {
           if (response.status == "success"){
               $("#success_obat").show();
               $("#ok_obat").show();
               $("#ok_obat").attr("onclick", "searchForm('"+id+"')");
               $("#save_obat").hide();
               $("#close_obat").hide();
           } else {
               $("#warning_obat").show();
               $("#obat_error").text(response.msg);
           }
        });
    }

    function searchForm(idObat){
        $("#nama_obat").val(idObat);
        $("#obatForm").submit();
    }

    function editObat(id, idBarang) {
        $('#modal-obat').modal('show');
        ObatAction.searchHargaObat(id, function (response) {
           if (response.length > 0){
               $.each(response, function(i, item){
                   $('#mod-id-obat').val(item.idObat);
                   $("#mod-nama-obat").val(item.namaObat);
                   $("#mod-merk").val(item.merk);
                   $("#mod-harga-rata").val(item.averageHargaBiji);
                   $("#mod-harga-net").val(item.hargaNet);
                   $("#mod-diskon").val(item.diskon);
                   $("#mod-harga-jual").val(item.hargaJual);
               });
               $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\',\''+idBarang+'\')');
           }
        });

    }

    function listSelectObatEdit(idObat){
        var data = [];
        if (idObat != '') {
            ObatAction.getJenisObatByIdObat(idObat, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        data.push(item.idJenisObat)
                    });
                }
            });
        } else {
            alert('id obat kosong');
        }
        return data;
    }

    function hitungDiskon() {
        var net = $("#mod-harga-net").val();
        var diskon = $("#mod-diskon").val();

        var hargajual = net - (net*(diskon/100));
        $("#mod-harga-jual").val(hargajual);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>