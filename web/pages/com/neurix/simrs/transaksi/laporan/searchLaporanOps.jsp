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
        .jarak_atas {
            margin-top: 7px
        }
    </style>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/LaporanOpsAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#laporan_ops').addClass('active');
            getLaporan();
        });

    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="content-wrapper">
    <section class="content-header">
        <h1>
            Laporan Operasional
        </h1>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Laporan</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" id="warning_search" style="display: none">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_search"></p>
                        </div>
                        <div class="form-group">
                            <s:form onsubmit="return cekSearch()" id="laporanForm" method="post" namespace="/laporanops"
                                    action="search_laporanops.action" target="_blank"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Laporan</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" onchange="setTipePelayayan(this.value)"
                                                class="form-control select2" id="jenis_laporan"
                                                name="laporanOps.tipeLaporan">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_branch">
                                    <label class="control-label col-sm-4">Unit</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="branch">
                                            <option value="">[Select One]</option>
                                        </select>
                                        <s:hidden name="laporanOps.branchId" id="h_branch"></s:hidden>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_tipe_penunjang">
                                    <label class="control-label col-sm-4">Tipe Penunjang</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="tipe_penunjang"
                                                name="laporanOps.tipePenunjang"
                                                onchange="setDetailPenunjang(this.value)">
                                            <option value="farmasi" selected>Farmasi</option>
                                            <option value="kamar">Kamar</option>
                                            <option value="radiologi">Radiologi</option>
                                            <option value="lab">Laboratorium</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_tipe_pelayanan">
                                    <label class="control-label col-sm-4"><span id="text_tipe">Farmasi</span></label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="tipe_pelayanan"
                                                name="laporanOps.tipePelayanan">
                                        </select>
                                    </div>
                                </div>
                                <%--<div class="form-group" style="display: none" id="form_detail_penunjang">--%>
                                    <%--<label class="control-label col-sm-4"><span id="text_tipe">Farmasi</span></label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<select style="width: 100%" class="form-control select2" id="detail_penunjang"--%>
                                                <%--name="laporanOps.tipePelayanan" multiple>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group" style="display: none" id="form_bulan">
                                    <label class="control-label col-sm-4">Bulan</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="bulan"
                                                name="laporanOps.listBulan" multiple>
                                            <option value="1">Januari</option>
                                            <option value="2">Februari</option>
                                            <option value="3">Maret</option>
                                            <option value="4">April</option>
                                            <option value="5">Mei</option>
                                            <option value="6">Juni</option>
                                            <option value="7">Juli</option>
                                            <option value="8">Agustus</option>
                                            <option value="9">September</option>
                                            <option value="10">Oktober</option>
                                            <option value="11">November</option>
                                            <option value="12">Desember</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_pelayanan">
                                    <label class="control-label col-sm-4">Pelayanan</label>
                                    <div class="col-sm-4">
                                        <select multiple style="width: 100%" class="form-control select2" id="pelayanan"
                                                name="laporanOps.listIdPelayanan">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_kelas">
                                    <label class="control-label col-sm-4">Kelas Ruangan</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="kelas_kamar"
                                                name="laporanOps.idKelasRuangan"
                                                onchange="getRuangan(this.value); inputWarning('war_kelas','')">
                                            <option value=''>[Select One]</option>
                                        </select>
                                        <s:hidden name="laporanOps.namaKelasRuangan" id="nama_kelas_ruangan"></s:hidden>
                                    </div>
                                    <div class="col-md-2">
                                        <div id="war_kelas"
                                             style="color: #d9534f; margin-left: -20px; margin-top: 12px; display: none">
                                            <i class="fa fa-warning"></i> reqiured
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_ruangan">
                                    <label class="control-label col-sm-4">Nama Ruangan</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="nama_kamar"
                                                name="laporanOps.listIdRuangan" multiple>
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none; margin-top: 8px; margin-bottom: -20px"
                                     id="form_choice">
                                    <div class="col-md-offset-4 col-md-2">
                                        <div class="custom02">
                                            <input onclick="setChoose(this.value)" type="radio" value="tanggal"
                                                   id="choice1" name="choice"/>
                                            <label for="choice1">Tanggal</label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="custom02">
                                            <input onclick="setChoose(this.value)" type="radio" value="tahun"
                                                   id="choice2" name="choice"/>
                                            <label for="choice2">Tahun</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none;" id="form_tanggal">
                                    <label class="control-label col-sm-4">Tanggal</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="laporanOps.dateFrom"
                                                         cssClass="form-control datemask2"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="laporanOps.dateTo"
                                                         cssClass="form-control datemask2"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_tahun">
                                    <label class="control-label col-sm-4">Tahun</label>
                                    <div class="col-sm-4">
                                        <select style="width: 100%" class="form-control select2" id="tahun"
                                                name="laporanOps.tahun">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none" id="form_button">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="laporanForm"
                                                   id="search" name="search">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_laporanops.action">
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
                </div>
            </div>
        </div>
    </section>
</div>

<script type='text/javascript'>

    function getLaporan() {
        var option = '<option value="">[Select One]</option>';
        LaporanOpsAction.getListLaporanOps(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idLaporanOps + '">' + item.namaLaporan + '</option>';
                });
                $('#jenis_laporan').html(option);
            } else {
                $('#jenis_laporan').html(option);
            }
        });
    }

    function getBranch() {
        var selectOne = '<option value="">[Select One]</option>';
        var option = '';
        var idDef = "";
        var isDis = "";
        TindakanAction.getComboBranch(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    if (i == 0) {
                        idDef = item.branchId;
                    }
                    if (item.isDisabled == "Y") {
                        isDis = "Y";
                    }
                    option += '<option value="' + item.branchId + '">' + item.branchName + '</option>';
                });
            }
            if (isDis == "Y") {
                $('#branch').html(option);
                $('#h_branch').val(idDef);
                $('#branch').val(idDef).trigger('change');
                $('#branch').attr('disabled', true);
            } else {
                $('#branch').html(selectOne + option);
            }
        });
    }

    function getPelayanan(idbranch) {
        if (idbranch != '') {
            $('#h_branch').val(idbranch);
            var option = '';
            TindakanAction.getComboJustPelayanan(idbranch, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        option += '<option value="' + item.idPelayanan + '">' + item.namaPelayanan + '</option>'
                    })
                }
                $('#pelayanan').html(option);
            });
        }
    }

    function getKelasKamar() {
        var option = '<option value="">[Select One]</option>';
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListKelasKamar('rawat_inap', function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idKelasRuangan + '">' + item.namaKelasRuangan + '</option>';
                });
                $('#kelas_kamar').html(option);
            } else {
                $('#kelas_kamar').html(option);
            }
        });
    }

    function getRuangan(id) {
        var option = "";
        var branchId = $('#branch').val();
        $('#nama_kelas_ruangan').val($('#kelas_kamar option:selected').text());
        if (id != '') {
            LaporanOpsAction.listRuangan(id, branchId, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
                    });
                    $('#nama_kamar').html(option);
                } else {
                    $('#nama_kamar').html(option);
                }
            });
        } else {
            $('#nama_kamar').html(option);
        }
    }

    function cekSearch() {
        var jenis = $('#jenis_laporan').val();
        if (jenis != '') {
            if ("rawat_inap" == jenis) {
                var kelas = $('#kelas_kamar').val();
                if (kelas != '') {
                    return true;
                } else {
                    $('#warning_search').show().fadeOut(5000);
                    $('#msg_search').text("Silahkan cek kembali inputan berikut..!");
                    $('#war_kelas').show();
                    return false;
                }
            } else if ("unggulan" == jenis) {
                var tgla = $('#tgl_from').val();
                var tglb = $('#tgl_from').val();
                var choice = $('[name=choice]:checked').val();
                if (choice != undefined) {
                    if ("tanggal" == choice) {
                        if (tgla && tglb != '') {
                            return true;
                        } else {
                            $('#warning_search').show().fadeOut(5000);
                            $('#msg_search').text("Silahkan cek kembali inputan berikut..!");
                            $('#war_kelas').show();
                            return false;
                        }
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    function setTipePelayayan(tipe) {
        if (tipe != '') {
            $('#form_button').show();
            if ("rawat_jalan" == tipe) {
                $('#form_branch, #form_tahun').show();
                $('#form_pelayanan').show();
                $('#branch').attr('onchange', 'getPelayanan(this.value)');

                $('#form_kelas, #form_ruangan, #form_tanggal, #form_tipe_pelayanan, #form_bulan').hide();
                $('#form_detail_penunjang, #form_tipe_penunjang').hide();
                $('#form_choice').hide();
                getTahun();
                getBranch();
                $('#tipe_pelayanan').html('');

            } else if ("rawat_inap" == tipe) {
                $('#form_branch, #form_tahun').show();
                $('#form_kelas, #form_ruangan').show();

                getTahun();
                getBranch();
                getKelasKamar();
                $('#form_pelayanan, #form_tanggal, #form_tipe_pelayanan, #form_bulan').hide();
                $('#form_detail_penunjang, #form_tipe_penunjang').hide();
                $('#form_choice').hide();
                $('#tipe_pelayanan').html('');

            } else if ("unggulan" == tipe) {
                $('#form_branch, #form_tahun, #form_choice').show();

                getTahun();
                getBranch();
                $('#choice1').attr('checked', false);
                $('#choice2').attr('checked', true);
                $('#form_kelas, #form_ruangan, #form_tipe_pelayanan').hide();
                $('#form_pelayanan').hide();
                $('#form_detail_penunjang, #form_tipe_penunjang').hide();
                $('#tipe_pelayanan').html('');

            } else if ("diagnosa" == tipe) {
                $('#form_tipe_pelayanan, #form_branch, #form_bulan, #form_tahun').show();

                getTahun();
                getBranch();
                setBulan();
                $('#form_tanggal').hide();
                $('#form_pelayanan').hide();
                $('#form_kelas, #form_ruangan').hide();
                $('#form_choice').hide();
                $('#form_detail_penunjang, #form_tipe_penunjang').hide();

                $('#text_tipe').text("Tipe Pelayanan");
                $("#tipe_pelayanan").removeAttr('multiple');
                var option = '<option value="rawat_jalan">Rawat Jalan</option>\n' +
                    '<option value="rawat_inap">Rawat Inap</option>';
                $('#tipe_pelayanan').html(option);

            } else if ("penunjang_medis" == tipe) {
                $('#form_tipe_penunjang, #form_tipe_pelayanan, #form_detail_penunjang, #form_branch, #form_tahun').show();

                getTahun();
                getBranch();
                $('#form_tanggal').hide();
                $('#form_pelayanan').hide();
                $('#form_kelas, #form_ruangan').hide();
                $('#form_choice').hide();
                $('#form_bulan').hide();
                $('#text_tipe').text("Farmasi");
                $('#tipe_penunjang').val('farmasi').trigger('change');

            } else {
                $('#form_branch, #form_tahun, #form_tanggal').hide();
                $('#form_pelayanan, #form_tipe_pelayanan, #form_bulan').hide();
                $('#form_kelas, #form_ruangan').hide();
                $('#form_choice').hide();
                $('#form_detail_penunjang, #form_tipe_penunjang').hide();
                $('#tipe_pelayanan').html('');
            }
        } else {
            $('#form_button').hide();
            $('#form_branch, #form_tahun').hide();
            $('#form_pelayanan, #form_tipe_pelayanan, #form_bulan').hide();
            $('#form_choice').hide();
            $('#form_detail_penunjang, #form_tipe_penunjang').hide();
            $('#tipe_pelayanan').html('');
        }
    }

    function setChoose(value) {
        if (value == "tanggal") {
            $('#form_tanggal').show();
            $('#form_tahun').hide();
            $('#tahun').val('');
        } else {
            $('#form_tanggal').hide();
            $('#form_tahun').show();
            $('#tgl_from, #tgl_to').val('');
        }
    }

    function getTahun() {
        var option = "";
        var dt = new Date();
        var year = dt.getFullYear();
        LaporanOpsAction.getListTahunOps(function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.tahun + "'>" + item.tahun + "</option>";
                });
                $('#tahun').html(option);
                $('#tahun').val(year).trigger('change');
            } else {
                $('#tahun').html(option);
            }
        });
    }

    function setBulan() {
        var dt = new Date();
        var bulan = dt.getMonth() + 1;
        $('#bulan').val(bulan).trigger('change');
    }

    function setDetailPenunjang(tipe) {
        if (tipe == "farmasi") {
            $("#text_tipe").text("Farmasi");
            $("#tipe_pelayanan").removeAttr('multiple');
        } else if (tipe == "kamar") {
            $("#text_tipe").text("Kamar");
            $("#tipe_pelayanan").attr('multiple', 'multiple');
        } else if (tipe == "radiologi") {
            $("#text_tipe").text("Radiologi");
            $("#tipe_pelayanan").attr('multiple', 'multiple');
        } else if (tipe == "lab") {
            $("#text_tipe").text("Laboratorium");
            $("#tipe_pelayanan").attr('multiple', 'multiple');
        } else {
            $("#text_tipe").text("Tidak sesuai");
        }

        var branchId = $('#branch').val();
        var option = '';
        LaporanOpsAction.getListPenunjangMedis(tipe, branchId, {
            callback:function (res) {
                if(res.length > 0){
                    $.each(res, function (i, item) {
                        option += '<option value="'+item.idPelayanan+'">'+item.namaPelayanan+'</option>';
                    });
                    $('#tipe_pelayanan').html(option);
                }else{
                    $('#tipe_pelayanan').html(option);
                }
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>