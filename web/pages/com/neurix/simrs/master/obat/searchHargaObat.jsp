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

        function formatRupiah(angka) {
            if(angka != null && angka != '' && angka > 0){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
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
            Harga Obat
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
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_obat" cssStyle="margin-top: 7px"
                                                     name="obat.idObat" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
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
                                        <%--<s:action id="initObat" namespace="/obat"--%>
                                                  <%--name="getListObat_obat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initObat.listOfObat" id="nama_obat"--%>
                                                  <%--listKey="idObat"--%>
                                                  <%--listValue="namaObat"--%>
                                                  <%--name="obat.idObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                        <s:textfield id="nama_obat" name="obat.namaObat"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
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
                        <table id="myTable" class="table table-bordered" style="font-size: 12px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Merk</td>
                                <td>Standar Margin</td>
                                <td>Margin Umum</td>
                                <td>Margin Khusus</td>
                                <td>Harga Beli (Bijian)</td>
                                <td>Harga Rata-rata (Bijian)</td>
                                <td>Harga Jual (Umum)</td>
                                <td>Harga Jual (Khusus)</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <s:if test='#row.flagKurangMargin == "Y"'>
                                    <tr style="background-color: #d9534f; color: #ffffff">
                                </s:if>
                                <s:elseif test='#row.flagKurangMargin == "R"'>
                                    <tr style="background-color: #EBEADF; color: black">
                                </s:elseif>
                                <s:else>
                                    <tr>
                                </s:else>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="merk"/></td>
                                    <td><s:property value="standarMargin"/></td>
                                    <td><s:property value="marginUmum"/></td>
                                    <td><s:property value="margin"/></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaBeli"/>'))</script></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="averageHargaBiji"/>'))</script></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaJualUmum"/>'))</script></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaJual"/>'))</script></td>
                                    <td align="center">
                                        <img onclick="editObat('<s:property value="idObat"/>','<s:property value="idBarang"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
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
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">

            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Harga Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger" id="alert-margin" style="display: none;">Harga Item Kurang dari Standart Margin.</div>
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

                <div class="row">
                    <div class="col-md-6">
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">ID Obat</label>
                                <div class="col-md-7">
                                    <input id="mod-id-obat" class="form-control" readonly="true">
                                    <input type="hidden" id="mod-id-harga-obat">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Nama Obat</label>
                                <div class="col-md-7">
                                    <input id="mod-nama-obat" class="form-control" readonly="true">
                                </div>
                            </div>
                        </div>
                        <%--<div class="row" style="margin-top: 7px">--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-md-5" style="margin-top: 7px">Merek</label>--%>
                                <%--<div class="col-md-7">--%>
                                    <%--<input id="mod-merk" class="form-control" readonly="true">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Standar Margin</label>
                                <div class="col-md-7">
                                    <div class="input-group">
                                        <input type="number" id="mod-standar-margin" class="form-control" readonly>
                                        <div class="input-group-addon">
                                            %
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr/>
                <div class="row" style="margin-top: 10px">

                    <%--SIDE HARGA OBAT UMUM--%>
                    <div class="col-md-6">
                        <h4>Harga Obat Normal (Non BPJS)</h4>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Beli (Bijian)</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-beli" class="form-control" readonly="true">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Margin</label>
                                <div class="col-md-7">
                                    <div class="input-group">
                                        <input type="number" id="mod-margin-umum" onchange="hitungHargaJual('umum')" class="form-control">
                                        <div class="input-group-addon">
                                            %
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-net-umum" onchange="hitungMargin('umum')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Diskon</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-diskon-umum" onchange="hitungDiskon('umum')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Jual</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-jual-umum" class="form-control" readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--SIDE HARGA OBAT KHUSUS--%>
                    <div class="col-md-6">
                        <h4>Harga Obat Khusus (Non BPJS)</h4>
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
                                <label class="col-md-5" style="margin-top: 7px">Margin</label>
                                <div class="col-md-7">
                                    <div class="input-group">
                                        <input type="number" id="mod-margin" onchange="hitungHargaJual('khusus')" class="form-control">
                                        <div class="input-group-addon">
                                            %
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-net" onchange="hitungMargin('khusus')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Diskon</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-diskon" onchange="hitungDiskon('khusus')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Jual</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-jual" class="form-control" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr/>
                <div class="row" style="margin-top: 10px">

                    <%--SIDE HARGA OBAT UMUM BPJS--%>
                    <div class="col-md-6">
                        <h4>Harga Obat Normal (BPJS)</h4>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Beli (Bijian)</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-beli-bpjs" class="form-control" readonly="true">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Margin</label>
                                <div class="col-md-7">
                                    <div class="input-group">
                                        <input type="number" id="mod-margin-umum-bpjs" onchange="hitungHargaJual('umum')" class="form-control">
                                        <div class="input-group-addon">
                                            %
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-net-umum-bpjs" onchange="hitungMargin('umum')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Diskon</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-diskon-umum-bpjs" onchange="hitungDiskon('umum')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Jual</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-jual-umum-bpjs" class="form-control" readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--SIDE HARGA OBAT KHUSUS--%>
                    <div class="col-md-6">
                        <h4>Harga Obat Khusus (BPJS)</h4>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Rata-Rata (Bijian)</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-rata-bpjs" class="form-control" readonly="true">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Margin</label>
                                <div class="col-md-7">
                                    <div class="input-group">
                                        <input type="number" id="mod-margin-bpjs" onchange="hitungHargaJual('khusus')" class="form-control">
                                        <div class="input-group-addon">
                                            %
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-net-bpjs" onchange="hitungMargin('khusus')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Diskon</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-diskon-bpjs" onchange="hitungDiskon('khusus')" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Harga Jual</label>
                                <div class="col-md-7">
                                    <input type="number" id="mod-harga-jual-bpjs" class="form-control" readonly>
                                </div>
                            </div>
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

        if (checkMargin())
            return false;

        var net     = $("#mod-harga-net").val();
        var diskon  = $("#mod-diskon").val();
        var jual    = $("#mod-harga-jual").val();
        var margin  = $("#mod-margin").val();

        var netUmum = $("#mod-harga-net-umum").val();
        var diskonUmum = $("#mod-diskon-umum").val();
        var jualUmum = $("#mod-harga-jual-umum").val();
        var marginUmum = $("#mod-margin-umum").val();

        var netKhususBpjs = $("#mod-harga-net-bpjs").val() == '' ? 0 : $("#mod-harga-net-bpjs").val();
        var diskonKhususBpjs = $("#mod-diskon-bpjs").val() == '' ? 0 : $("#mod-diskon-bpjs").val();
        var jualKhususBpjs = $("#mod-harga-jual-bpjs").val() == '' ? 0 : $("#mod-harga-jual-bpjs").val();
        var marginKhususBpjs = $("#mod-margin-bpjs").val() == '' ? 0 : $("#mod-margin-bpjs").val();

        var netUmumBpjs = $("#mod-harga-net-umum-bpjs").val() == '' ? 0 : $("#mod-harga-net-umum-bpjs").val();
        var diskonUmumBpjs = $("#mod-diskon-umum-bpjs").val() == '' ? 0 : $("#mod-diskon-umum-bpjs").val();
        var jualUmumBpjs = $("#mod-harga-jual-umum-bpjs").val() == '' ? 0 : $("#mod-harga-jual-umum-bpjs").val();
        var marginUmumBpjs = $("#mod-margin-umum-bpjs").val() == '' ? 0 : $("#mod-harga-jual-umum-bpjs").val();

        var arJson = [];
        arJson.push({"harga_net":net, "diskon":diskon, "harga_jual":jual, "margin" : margin,
            "harga_net_umum" : netUmum, "diskon_umum" : diskonUmum, "harga_jual_umum" : jualUmum, "margin_umum" : marginUmum,
            "net_khusus_bpjs" : netKhususBpjs, "diskon_khusus_bpjs" : diskonKhususBpjs, "jual_khusus_bpjs" : jualKhususBpjs, "margin_khusus_bpjs" : marginKhususBpjs,
            "net_umum_bpjs" : netUmumBpjs, "diskon_umum_bpjs" : diskonUmumBpjs, "jual_umum_bpjs" : jualUmumBpjs, "margin_umum_bpjs" : marginUmumBpjs
        });
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
        $("#id_obat").val(idObat);
        $("#obatForm").submit();
    }

    function editObat(id, idBarang) {
        $('#modal-obat').modal('show');
        ObatAction.searchHargaObat(id, function (response) {
           if (response.length > 0){
               $.each(response, function(i, item){
                   $('#mod-id-obat').val(item.idObat);
                   $('#mod-id-harga-obat').val(item.idHargaObat);
                   $("#mod-nama-obat").val(item.namaObat);
                   $("#mod-merk").val(item.merk);

                   $("#mod-harga-rata").val(item.averageHargaBiji);
                   $("#mod-harga-net").val(item.hargaNet);
                   $("#mod-diskon").val(item.diskon);
                   $("#mod-harga-jual").val(item.hargaJual);
                   $("#mod-margin").val(item.margin);

                   $("#mod-standar-margin").val(item.standarMargin);

                   $("#mod-harga-beli").val(item.hargaBeli);
                   $("#mod-harga-net-umum").val(item.hargaNetUmum);
                   $("#mod-diskon-umum").val(item.diskonUmum);
                   $("#mod-harga-jual-umum").val(item.hargaJualUmum);
                   $("#mod-margin-umum").val(item.marginUmum);
               });
               $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\',\''+idBarang+'\')');
           }
        });
    }

    function hitungMargin(tipe) {

        var harga       = "";
        var hargarata   = "";
        if (tipe == "khusus"){
            harga       = $("#mod-harga-net").val();
            hargarata   = $("#mod-harga-rata").val();
        } else {
            harga       = $("#mod-harga-net-umum").val();
            hargarata   = $("#mod-harga-beli").val();
        }

        var selisih     = parseFloat(harga) - parseFloat(hargarata);
        var margin      = parseFloat(selisih) / parseFloat(hargarata) * 100;

        if (tipe == "khusus"){
            $("#mod-margin").val(parseInt(margin));
            $("#mod-harga-jual").val(harga);
        } else {
            $("#mod-margin-umum").val(parseInt(margin));
            $("#mod-harga-jual-umum").val(harga);
        }

        checkMargin();
    }

    function hitungHargaJual(tipe) {
        var margin      = "";
        var hargarata   = "";

        if (tipe == "khusus"){
            margin      = $("#mod-margin").val();
            hargarata   = $("#mod-harga-rata").val();
        } else {
            //umum
            margin      = $("#mod-margin-umum").val();
            hargarata   = $("#mod-harga-beli").val();
        }

        var selisih = parseFloat(hargarata) * parseFloat(margin) / 100;
        var harga   = parseFloat(hargarata) + selisih;

        if (tipe == "khusus"){
            $("#mod-harga-net").val(parseInt(harga));
            $("#mod-harga-jual").val(parseInt(harga));
        } else {
            //umum
            $("#mod-harga-net-umum").val(parseInt(harga));
            $("#mod-harga-jual-umum").val(parseInt(harga));
        }

        checkMargin();
    }

    function checkMargin() {
        var margin = $("#mod-margin").val();
        var standar = $("#mod-standar-margin").val() == null ? 0 : $("#mod-standar-margin").val();
        if (parseInt(margin) < parseInt(standar)){
            $("#alert-margin").show().fadeOut(7000);
            return true;
        }
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

    function hitungDiskon(tipe) {
        var net = "";
        var diskon = "";

        if (tipe == "khusus"){
            net = $("#mod-harga-net").val();
            diskon = $("#mod-diskon").val();
        } else {
            // umum
            net = $("#mod-harga-net-umum").val();
            diskon = $("#mod-diskon-umum").val();
        }

        var hargajual = net - (net*(diskon/100));

        if (tipe == "khusus"){
            $("#mod-harga-jual").val(hargajual);
        } else {
            // umum
            $("#mod-harga-jual-umum").val(hargajual);
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>