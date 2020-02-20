<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>

    <style>
        .btn-trans {
            background-color: #404040;
            width: 110px;
            height: 130px;
            border-radius: 10px;
            opacity: 0.9;
            /*padding-right: 20px;
            padding-left: 20px;*/
            padding: 6px;
            float: left;
            margin: 5px;
            border: 1px solid #f7f7f7;
            font-size: 12px;
            text-align: center;
            color: #fff;
        }

        .box-blue {
            background-color: #367fa9;
        }

        .box-bluemuda {
            background-color: #00acd6;
        }

        .box-green {
            background-color: #449d44;
        }

        .box-red {
            background-color: #c9302c;
        }

        .box-yellow {
            background-color: #e08e0b;
        }

        .btn-orange {
            background-color: orange;
        }

        .btn-default {
            /*background-color: #e7e7e7;*/
            /*color:#fff;*/
            width: 100%;
            height: 20px;
            margin: 0 auto;
            padding: 0;
            display: inline-block;
            line-height: 0px;
            text-align: center;
        }

        .btn-transparent {
            background-color: transparent;
            color: #fff;
        }

        .btn-white:hover {
            color: #fff;
        }

        .btn-trans:active {
            background-color: #2caaea;
        }

        .btn-trans:visited {
            background-color: #2caaea;
        }
    </style>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#monitor_ruangan').addClass('active');
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
            Monitor Ruangan
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Monitor Ruangan</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="monitorruanganForm" method="post" namespace="/monitorruangan"
                                    action="search_monitorruangan.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kelas Ruangan</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select cssStyle="margin-top: 7px"
                                                  onchange="$(this).css('border',''); listSelectRuangan(this)"
                                                  list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                  name="ruang.idKelasRuangan"
                                                  listKey="idKelasRuangan"
                                                  listValue="namaKelasRuangan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                    <div class="col-sm-3" style="display: none;" id="load_ruang">
                                        <img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                             style="cursor: pointer; width: 45px; height: 45px"><b
                                            style="color: #00a157;">Sedang diproses...</b></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Ruangan</label>
                                    <div class="col-sm-4">
                                        <select id="ruangan_ruang" style="margin-top: 7px" class="form-control select2"
                                                id="nama_ruangan" name="ruang.idRuangan">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="monitorruanganForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_monitorruangan.action">
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
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
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
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Status Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12" style="display:inline;">
                                <s:iterator value="#session.listOfResult" var="row">
                                <div class="btn-wrapper">
                                    <s:if test='#row.statusRuangan == "Y"'>
                                    <div id="id_box" class="btn-trans box-green">
                                        </s:if>
                                        <s:else>
                                        <div id="id_box" class="btn-trans">
                                            </s:else>
                                            <button class="btn btn-default" style="height: 20px; width: 100%; font-size: 10px; align-content: center">
                                                <s:property value="namaRuangan"/></button>
                                            <div style="text-align:left; cursor:pointer; font-size:11px;">
                                                <table align="center"
                                                       style="width:100%; border-radius:5px; margin-top:2px;">
                                                    <td colspan="2" style="text-align: center">
                                                        <img style="background-color:transparent; height:70px;"
                                                             src="<s:url value="/pages/images/room.png"/>">
                                                    </td>
                                                    <tr class="hiddenx">
                                                        <td colspan="2"><s:property value="tarifTindakan"/></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </s:iterator>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Order Gizi Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_gizi">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_gizi2"></p>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_gizi">
                        <thead>
                        <tr>
                            <td>Tanggal Order</td>
                            <td>Diet Pagi</td>
                            <td>Diet Siang</td>
                            <td>Diet Malam</td>
                            <td align="center" rowspan="2">Status</td>
                            <td align="center" rowspan="2">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_gizi">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_gizi" onclick="saveVerif()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_gizi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function listOrderGizi(idRawatInap, noCheckup) {
        $('#modal-detail-pasien').modal({show: true, backdrop: 'static'});
        var table = "";
        dwr.engine.setAsync(true);
        PermintaanGiziAction.getListOrderGizi(idRawatInap, {
            callback: function (response) {
                $.each(response, function (i, item) {

                    var tanggal = $.datepicker.formatDate("dd-mm-yy", new Date(item.createdDate));
                    var jenisPagi = "";
                    var bentukPagi = "";
                    var jenisSiang = "";
                    var bentukSiang = "";
                    var jenisMalam = "";
                    var bentukMalam = "";
                    var label = "";
                    var btn = "";

                    if (item.bentukMakanPagi != null) {
                        bentukPagi = item.bentukMakanPagi;
                    }
                    if (item.dietPagi != null) {
                        jenisPagi = item.dietPagi;
                    }
                    if (item.bentukMakanSiang != null) {
                        bentukSiang = item.bentukMakanSiang;
                    }
                    if (item.dietSiang != null) {
                        jenisSiang = item.dietSiang;
                    }
                    if (item.dietMalam != null) {
                        bentukMalam = item.bentukMakanMalam;
                    }
                    if (item.bentukMakanMalam != null) {
                        jenisMalam = item.dietMalam;
                    }

                    if (item.approveFlag == "Y") {
                        label = '<label class="label label-info"> siap kirim</label>';
                        btn = '<img onclick="printBarcodeGizi(\'' + noCheckup + '\',\'' + item.idOrderGizi + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>" style="cursor: pointer;">';
                    } else {
                        label = '<label class="label label-warning"> menunggu</label>';
                        btn = '<img id="bot' + item.idOrderGizi + '" onclick="saveApprove(\'' + item.idOrderGizi + '\',\'' + idRawatInap + '\',\'' + noCheckup + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-edit-25.png"/>" style="cursor: pointer;">';
                    }

                    if (item.diterimaFlag == "Y") {
                        label = '<label class="label label-success"> selesai</label>';
                        btn = '';
                    }
                    table += '<tr>' +
                        '<td>' + tanggal + '</td>' +
                        '<td>' + bentukPagi + '</td>' +
                        '<td>' + bentukSiang + '</td>' +
                        '<td>' + bentukMalam + '</td>' +
                        '<td style="vertical-align: middle" align="center">' + label + '</td>' +
                        '<td align="center">' + btn + '</td>' +
                        '</tr>'
                });
                $('#body_gizi').html(table);
            }
        });
    }

    function saveApprove(idOrder, idRawatInap, noCheckup) {
        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#bot' + idOrder).attr('src', url).css('width', '30px', 'height', '40px');

        setTimeout(function () {
            PermintaanGiziAction.updateApproveFLag(idOrder, function (response) {
                if (response.status == "success") {
                    $('#bot' + idOrder).removeAttr("src");
                    $('#success_gizi').show().fadeOut(5000);
                    $('#msg_gizi2').text(response.message);
                    listOrderGizi(idRawatInap, noCheckup);
                } else {
                    $('#bot' + idOrder).removeAttr("src");
                    $('#warning_gizi').show().fadeOut(5000);
                    $('#msg_gizi').text(response.message);
                }
            });
        }, 200);
    }

    function printBarcodeGizi(noCheckup, idorderGizi) {
        window.open('printBarcodeGizi_ordergizi.action?id=' + noCheckup + '&order=' + idorderGizi, '_blank');
    }

    function saveVerif() {
        $('#save_gizi').hide();
        $('#load_gizi').show();
        setTimeout(function () {
            $('#save_gizi').show();
            $('#load_gizi').hide();
            $('#modal-detail-pasien').modal('hide');
            $('#info_dialog').dialog('open');
        }, 500);
    }

    function listSelectRuangan(id) {
        var idx = id.selectedIndex;
        var idKelas = id.options[idx].value;
        var flag = false;
        var option = "";
        CheckupDetailAction.listRuangan(idKelas, flag, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
                });
                $('#ruangan_ruang').html(option);
            } else {
                $('#ruangan_ruang').html(option);
                ;
            }
        });

    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>