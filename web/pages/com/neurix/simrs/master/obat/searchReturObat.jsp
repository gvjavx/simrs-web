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

        *, *:before, *:after {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .new {
            padding: 50px;
        }

        .form-check {
            display: block;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content:'';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {

            $('#retur_obat').addClass('active');

            $('#sortTable2').DataTable({
                "order": [[1, "desc"]],
                "columnDefs": [
                    { "orderable": false, "targets": 0 }
                ]
            })

            $('#selectall').click(function () {
                $('.selectedId').prop('checked', this.checked);

                var checkbox = document.getElementsByName('selectedId');
                var fpk = $('#no_fpk_search').val();

                var ln = 0;
                for(var i=0; i< checkbox.length; i++) {
                    if(checkbox[i].checked)
                        ln++
                }
                if(ln > 0){
                    $('#btn_create').show();
                }else{
                    $('#btn_create').hide();
                }

                if(fpk != '' && ln > 0){
                    $('#btn_create').hide();
                    $('#form-bayar').show();
                }else{
                    $('#form-bayar').hide();
                }

                console.log(fpk);
            });

            $('.selectedId').change(function () {
                var check = ($('.selectedId').filter(":checked").length == $('.selectedId').length);
                $('#selectall').prop("checked", check);

                var checkbox = document.getElementsByName('selectedId');
                var fpk = $('#no_fpk_search').val();

                var ln = 0;
                for(var i=0; i< checkbox.length; i++) {
                    if(checkbox[i].checked)
                        ln++
                }
                if(ln > 0){
                    $('#btn_create').show();
                }else{
                    $('#btn_create').hide();
                }

                if(fpk != '' && ln > 0){
                    $('#btn_create').hide();
                    $('#form-bayar').show();
                }else{
                    $('#form-bayar').hide();
                }
                console.log(fpk);
            });


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
            Retur Obat
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Reture Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="returobatForm" method="post" namespace="/returobat"
                                    action="searchRetureObat_returobat.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Vendor</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_fpk_search" cssStyle="margin-top: 7px"
                                                     name="obat.namVendor" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Retur</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="obat.stTglRetur"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="returobatForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_returobat.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a href="add_returobat.action" class="btn btn-primary" id="btn_create"><i class="fa fa-refresh"></i>
                                            Reture</a>
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
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                        $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <s:hidden id="close_val"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat Retur</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable2" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Nama Vendor</td>
                                <td>Tanggal Retur</td>
                                <td align="center">Jumlah Retur</td>
                                <td align="center">Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="namaVendor"/></td>
                                    <td><s:property value="stTglRetur"/></td>
                                    <td align="center"><s:property value="qty"/></td>
                                    <td align="center" style="vertical-align: middle">
                                        <s:if test='#row.keterangan == "Telah Dikonfirmasi"'>
                                            <label class="label label-success"><s:property value="keterangan"/></label>
                                        </s:if>
                                        <s:else>
                                            <label class="label label-warning"><s:property value="keterangan"/></label>
                                        </s:else>
                                    </td>
                                    <td align="center">
                                        <img onclick="detailRetur('<s:property value="idRetureObat"/>','<s:property value="namaVendor"/>','<s:property value="stTglRetur"/>','<s:property value="qty"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer">
                                        <s:if test='#row.keterangan == "Telah Dikonfirmasi"'>

                                        </s:if>
                                        <s:else>
                                            <a href="/simrs/permintaanpo/edit_permintaanpo.action?id=<s:property value="idPermintaanVendor"/>&tipe=reture">
                                                <img class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer">
                                            </a>
                                        </s:else>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <div class="box-header with-border"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-retur">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Retur Obat</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped">
                    <tr>
                        <td width="35%">Nama Vendor</td>
                        <td><p id="det_nama_vendor"></p></td>
                    </tr>
                    <tr>
                        <td>Tanggal Retur</td>
                        <td><p id="det_tanggal"></p></td>
                    </tr>
                    <tr>
                        <td>Jumlah Retur</td>
                        <td><p id="det_jumlah"></p></td>
                    </tr>
                </table>
                <table class="table table-striped table-bordered">
                    <thead>
                    <td>ID Barang</td>
                    <td>ID Obat</td>
                    <td>Nama Obat</td>
                    <td>Jumlah Retur</td>
                    </thead>
                    <tbody id="body_retur">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function toContent(){
        var cos = $('#close_pos').val();
        var data = $('#close_val').val();

        if(cos == 1){
            $('#no_fpk_search').val(data);
            document.createfpkForm.action = 'searchFPK_createfpk.action';
            document.createfpkForm.submit();
        }else{
            window.location.reload(true);
        }
    }

    function detailRetur(idRetur, nama, tgl, qty){
        $('#modal-retur').modal({show:true, backdrop:'static'});
        $('#det_nama_vendor').text(nama);
        $('#det_tanggal').text(tgl);
        $('#det_jumlah').text(qty);
        dwr.engine.setAsync(true);
        ObatAction.detailReturObat(idRetur, {callback: function (response) {
           var table = "";
            if(response.length > 0){
               $.each(response, function (i, item) {
                   table += '<tr>' +
                       '<td>'+item.idBarang+'</td>'+
                       '<td>'+item.idObat+'</td>'+
                       '<td>'+item.namaObat+'</td>'+
                       '<td>'+item.qty+'</td>'+
                       '</tr>'
               });
               $('#body_retur').html(table);
           }else{

           }
        }});
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>