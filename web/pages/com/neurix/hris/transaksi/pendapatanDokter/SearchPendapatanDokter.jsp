<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PendapatanDokterAction.js"/>'></script>
    <script type='text/javascript'>

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        function link(){
            window.location.href="<s:url action='initForm_laporan'/>";
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var unit    = document.getElementById("branchId").value;
            var periodeTahun = document.getElementById("periodeTahun").value;
            var periodeBulan = document.getElementById("periodeBulan").value;
            var tipeLaporan = "neraca_saldo";

            if ( unit != '' && periodeTahun != ''&& periodeBulan != '') {
                event.originalEvent.options.submit = false;
                var url = "printReportNeracaSaldo_laporanAkuntansi.action?tipeLaporan="+tipeLaporan+"&laporanAkuntansi.unit="+unit+"&laporanAkuntansi.tahun="+periodeTahun+"&laporanAkuntansi.bulan="+periodeBulan;
                window.open(url,'_blank');
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( unit == '') {
                    msg += 'Field <strong>Unit </strong> masih belum dipilih' + '<br/>';
                }
                if ( periodeTahun == '') {
                    msg += 'Field <strong>Tahun </strong> masih belum dipilih' + '<br/>';
                }
                if ( periodeBulan == '') {
                    msg += 'Field <strong>Bulan </strong> masih belum dipilih' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');
            }
        });
        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Pendapatan Dokter
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pendapatan Dokter</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pendapatanDokterForm" method="post"  theme="simple" namespace="/pendapatanDokter" action="search_pendapatanDokter.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Pendapatan ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="pendapatanId" name="pendapatanDokter.pendapatanDokterId" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Dokter ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="dokterId" name="pendapatanDokter.dokterId" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Dokter :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaDokter" name="pendapatanDokter.dokterName" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Bulan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                                        '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                  id="periodeBulan" name="pendapatanDokter.bulan"
                                                                  headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tahun :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periodeTahun"
                                                                  name="pendapatanDokter.tahun" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                                <script>
                                                    var dt = new Date();
                                                    $('#periodeBulan').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                    $('#periodeTahun').val(dt.getFullYear());
                                                </script>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test='pendapatanDokter.branchId == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pendapatanDokter.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="pendapatanDokter.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="pendapatanDokterForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="add_pendapatanDokter.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Pendapatan</a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_biodata"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="80%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="350" width="600" autoOpen="false" title="Loading ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <center>
                                                                <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                            </center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="620" width="900" autoOpen="false"
                                                                   title="Pendapatan Dokter">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <sj:dialog id="view_dialog_menu_pendapatan" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="700" autoOpen="false"
                                                                   title="Pendapatan Dokter">
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_keterangan" openTopics="showDialogMenuKeterangan" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Pendapatan Dokter">
                                                        </sj:dialog>
                                                        <s:set name="listOfPendapatanDokter" value="#session.listOfResultPendapatanDokter" scope="request" />
                                                        <display:table name="listOfPendapatanDokter" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_pendapatan.action" export="true" id="row" pagesize="30" style="font-size:10">
                                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                                <%--<s:url var="urlView" namespace="/pendapatanDokter" action="view_pendapatanDokter" escapeAmp="false">--%>
                                                                    <%--<s:param name="id"><s:property value="#attr.row.absensiPegawaiId" /></s:param>--%>
                                                                    <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                                <%--</s:url>--%>
                                                                <%--<sj:a onClickTopics="showDialogMenuView" href="%{urlView}">--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">--%>
                                                                <%--</sj:a>--%>
                                                                <img onclick="detailPendapatan('<s:property value="#attr.row.pendapatanDokterId"/>','<s:property value="#attr.row.dokterName"/>','<s:property value="#attr.row.branchName"/>','<s:property value="#attr.row.bruto"/>','<s:property value="#attr.row.pendapatanRs"/>','<s:property value="#attr.row.hrBruto"/>','<s:property value="#attr.row.dppPph50"/>','<s:property value="#attr.row.dppPph21Komulatif"/>','<s:property value="#attr.row.StTarif"/>','<s:property value="#attr.row.pphDipungut"/>','<s:property value="#attr.row.hrAktifitasNetto"/>','<s:property value="#attr.row.potKs"/>','<s:property value="#attr.row.gajiBersih"/>')" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash" style="cursor: pointer;">
                                                            </display:column>
                                                            <display:column property="branchName" sortable="true" title="Unit" />
                                                            <display:column property="dokterId" sortable="true" title="Id Dokter"  />
                                                            <display:column property="dokterName" sortable="true" title="Nama Dokter" />
                                                            <display:column property="bulan" sortable="true" title="Bulan" />
                                                            <display:column property="tahun" sortable="true" title="Tahun" />
                                                            <display:column property="bruto" sortable="true" title="Bruto" />
                                                            <display:column property="hrBruto" sortable="true" title="Hr. Bruto" />
                                                            <display:column property="pphDipungut" sortable="true" title="Pot. Pajak" />
                                                            <display:column property="potKs" sortable="true" title="Pot.Ks" />
                                                            <display:column property="gajiBersih" sortable="true" title="Total Pendapatan" />
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                                >
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                            <br/>
                                                                            <center><div id="errorValidationMessage"></div></center>
                                                                        </label>
                                                                    </div>
                                                                </sj:dialog>
                                                            </table>
                                                        </td>
                                                    </div>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<div class="modal fade" id="modal-detail-pendapatan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Pendapatan</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <tr>
                                <td><b>Nama</b></td>
                                <td><span id="nama_dokter"></span></td>
                            </tr>
                            <tr>
                                <td><b>Unit</b></td>
                                <td><span id="nama_unit"></span></td>
                            </tr>
                            <tr>
                                <td><b>Bruto</b></td>
                                <td><span id="bruto"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pendapatan Rs</b></td>
                                <td><span id="pendapatan_rs"></span></td>
                            </tr>
                            <tr>
                                <td><b>Hr Bruto</b></td>
                                <td><span id="hr_bruto"></span></td>
                            </tr>
                            <tr>
                                <td><b>Dpp Pph 21</b></td>
                                <td><span id="dpp_pph_21"></span></td>
                            </tr>
                            <tr>
                                <td><b>Dpp Pph Komulatif</b></td>
                                <td><span id="dpp_pph_komulatif"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pajak</b></td>
                                <td><span id="pajak"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pot. Pajak</b></td>
                                <td><span id="pot_pajak"></span></td>
                            </tr>
                            <tr>
                                <td><b>Hr Aktifitas Netto</b></td>
                                <td><span id="hr_aktifitas_netto"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pot. Ks</b></td>
                                <td><span id="pot_ks"></span></td>
                            </tr>
                            <tr>
                                <td><b>Hr Netto</b></td>
                                <td><span id="hr_netto"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <td>Nama Pasien</td>
                    <td>Tanggal</td>
                    <td>Master Id</td>
                    <td>Nama Poli</td>
                    <td>Nama Activity Id</td>
                    <td>Biaya</td>
                    </thead>
                    <tbody id="body_detail">
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
<script>
    function formaterDate(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = today.getHours();
            var min = today.getMinutes();
            var sec = today.getSeconds();
            today = dd + '-' + mm + '-' + yyyy + ' '+ hh +':'+ min;
        }
        return today;
    }

    function detailPendapatan(pendapatanDokterId,namaDokter,branchName,bruto, pendapatanRs,hrBruto,dppPph21,dppPphKomulatif,pajak,potPajak,hrAktifitas,potKs,gajiBersih){
        console.log(namaDokter);
        $('#nama_dokter').text(namaDokter);
        $('#nama_unit').text(branchName);
        $('#bruto').text(bruto);
        $('#pendapatan_rs').text(pendapatanRs);
        $('#hr_bruto').text(hrBruto);
        $('#dpp_pph_21').text(dppPph21);
        $('#dpp_pph_komulatif').text(dppPphKomulatif);
        $('#pajak').text(pajak);
        $('#pot_pajak').text(potPajak);
        $('#hr_aktifitas_netto').text(hrAktifitas);
        $('#pot_ks').text(potKs);
        $('#hr_netto').text(gajiBersih);
        $('#body_detail').html('');

        var table = "";
        PendapatanDokterAction.getDetailPendapatan(pendapatanDokterId, function (response) {
            if(response.length > 0){
                $.each(response, function (i, item) {
                    table += '<tr>' +
                            '<td>'+item.namaPasien+'</td>'+
                            '<td>'+formaterDate(item.tanggal)+'</td>'+
                            '<td>'+item.masterId+'</td>'+
                            '<td>'+item.poliName+'</td>'+
                            '<td>'+item.activityName+'</td>'+
                            '<td align="right">'+item.bruto+'</td>'+
                            '</tr>'
                });

                $('#body_detail').html(table);
            }else{

            }
        });

        $('#modal-detail-pendapatan').modal({show:true, backdrop:'static'});
    }

    window.cekKoneksi = function(){
        dwr.engine.setAsync(false);
        AbsensiAction.cekKoneksi(function(listdata) {
        })
    };
    window.saveTmp = function(){
        var values = new Array();
        var status ;
        $.each($("input[name='checkApprove[]']:checked"), function() {
            values.push($(this).val());
        });
        if(values.length > 0){
            dwr.engine.setAsync(false);
            $.each($("input[name='checkApprove[]']:checked"), function() {
                AbsensiAction.saveTmp($(this).val(),function(listdata) {
                    if (listdata == '00'){
                        status ="sukses";
                    }else{
                        status ="failed";
                        return false;
                    }
                });
            });
            if(status=="sukses"){
                $('#saveAdd').show();
                $('#cancel').show();
                alert("Proses Sukses");
                loadFinal();
                $('#save').hide();
            }else{
                $('#error_dialog').dialog('open');
                $('#saveAdd').hide();
                $('#cancel').hide();
            }
        }else{
            alert('Silahkan Centang Salah Satu Absensi !');
        }
    };

//    $('.row').on('click', '.item-view-pendapatan', function () {
////        var branchId = $(this).attr('data');
////        var bulan = $(this).attr('bulan');
////        var tahun = $(this).attr('tahun');
//        var dokterId = $(this).attr('dokter');
//        $('#nama_dokter').text($(this).attr('nama'));
//        $('#nama_unit').text($(this).attr('unit'));
//        $('#bruto').text($(this).attr('bruto'));
//        $('#pendapatan_rs').text($(this).attr('pdptnRs'));
//        $('#hr_bruto').text($(this).attr('hrbruto'));
//        $('#dpp_pph_21').text($(this).attr('pph21'));
//        $('#dpp_pph_komulatif').text($(this).attr('komulatif'));
//        $('#pajak').text($(this).attr('pajak'));
//        $('#pot_pajak').text($(this).attr('potPjk'));
//        $('#hr_aktifitas_netto').text($(this).attr('hrAktifitas'));
//        $('#pot_ks').text($(this).attr('potKs'));
//        $('#hr_netto').text($(this).attr('gajiBersih'));
//        $('#body_detail').html('');
//
////        dwr.engine.setAsync(false);
//        var tmp_table = "";
//        PendapatanDokterAction.searchDetailPendapatan(dokterId, function(response) {
//            if(response.length > 0){
//                $.each(response, function (i, item) {
//                    tmp_table += '<tr>' +
//                            '<td>'+item.namaPasien+'</td>'+
//                            '<td>'+formaterDate(item.tanggal)+'</td>'+
//                            '<td>'+item.masterId+'</td>'+
//                            '<td>'+item.poliName+'</td>'+
//                            '<td>'+item.activityName+'</td>'+
//                            '<td align="right">'+item.bruto+'</td>'+
//                            '</tr>'
//                });
//
//                $('#body_detail').html(tmp_table);
//            }else{
//
//            }
//        });
//        $('#modal-detail-pendapatan').find('.modal-title').text('View Detail Pendapatan');
//        $('#modal-detail-pendapatan').modal('show');
//    });
</script>