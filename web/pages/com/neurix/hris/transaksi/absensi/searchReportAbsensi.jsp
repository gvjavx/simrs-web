<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StrukturJabatanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <style>
        .checkApprove {width: 20px; height: 20px;}
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

        function link(){
            window.location.href="<s:url action='initForm_laporan'/>";
        }

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
            Report Absensi
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="absensiForm" method="post"  theme="simple" namespace="/absensi" action="searchReportAbsensi_absensi.action" cssClass="well form-horizontal">
                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>
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
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdAbsensi" name="absensiPegawai.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" onchange="listBagian()"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                            <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>
                                            <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"
                                                      listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="absensiPegawai.bagian" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Tanggal :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglFrom" name="absensiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglTo" name="absensiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>NIP :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="nip" name="absensiPegawai.nip" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    $('#nip').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};
                                            var data = [];
                                            var unit = $('#branchIdAbsensi').val();
                                            if (unit==""){
                                                alert("unit is empty");
                                                $('#namaPegawai').val("");
                                            }else {
                                                dwr.engine.setAsync(false);
                                                CutiPegawaiAction.initComboPersonilOnlyName(query, unit, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.namaPegawai;
                                                    var labelNip = item.nip;
                                                    mapped[labelItem] = {pegawai: item.namaPegawai, id: item.nip};
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            }
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaPegawai = selectedObj.id;
                                            document.getElementById("nip").value = selectedObj.id;
                                            document.getElementById("namaPegawai").value = selectedObj.pegawai;
                                            return namaPegawai;
                                        }
                                    });
                                </script>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield name="absensiPegawai.nama" id="namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center" style="border-spacing: 10px;">
                                <tr>
                                    <td>
                                        <button type="button" class="btn btn-success" id="btnPrintAbsensi">
                                            <i class="fa fa-print"></i> Cetak Laporan
                                        </button>
                                    </td>
                                    <td width="2%"></td>
                                    <td>
                                        <button type="button" class="btn btn-success" id="btnPrintUangMakan">
                                            <i class="fa fa-print"></i> Cetak Uang Makan
                                        </button>
                                    </td>
                                    <td width="2%"></td>
                                    <td>
                                        <button type="button" class="btn btn-success" id="btnPrintTriwulan">
                                            <i class="fa fa-print"></i> Cetak Evaluasi
                                        </button>
                                    </td>
                                    <td width="2%"></td>
                                    <td>
                                        <div class="btn-group">
                                            <button class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
                                                <i class="fa fa-print"></i>
                                                Cetak Tambahan
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li id="btnCetakKoperasi">
                                                    <a><i class="fa fa-print"></i>Uang Makan Koperasi</a>
                                                </li>
                                                <li id="btnCetakDapur">
                                                    <a><i class="fa fa-print"></i>Uang Makan Dapur</a>
                                                </li>
                                                <li id="btnCetakAbsensiOutsourcing">
                                                    <a><i class="fa fa-print"></i>Absensi Outsourcing</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td width="2%"></td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="reportAbsensi_absensi"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br>
                        <br>
                    </s:form>
                </td>
            </tr>
        </table>
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

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

<script>
    $(document).ready(function() {
        $('#tglFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tglTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#btnPrintAbsensi').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var branchId = document.getElementById("branchIdAbsensi").value;
            var bagian = document.getElementById("bagian").value;
            var nip = document.getElementById("nip").value;
            if (tglFrom!=""&&tglTo!=""&&branchId!=""){
                var msg='Apakah Anda ingin mencetak laporan absensi tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (bagian!=""){
                    var msg='Apakah Anda ingin mencetak laporan absensi tanggal '+tglFrom+' sampai tanggal '+tglTo+'  ?';
                }
                if (confirm(msg)) {
                    window.location.href = "printReportAbsensi_absensi.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&branchId="+branchId+"&bagian="+bagian+"&nip="+nip+"&divisiId=''";
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
        $('#btnPrintUangMakan').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var branchId = document.getElementById("branchIdAbsensi").value;
            var bagian = document.getElementById("bagian").value;
            var nip = document.getElementById("nip").value;
            if (tglFrom!=""&&tglTo!=""&&branchId!=""){
                var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (bagian!=""){
                    var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+'  ?';
                }
                if (confirm(msg)) {
                    window.location.href = "printReportUangMakan_absensi.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&branchId="+branchId+"&bagian="+bagian+"&nip="+nip+"&divisiId=''";
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
        $('#btnPrintTriwulan').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var branchId = document.getElementById("branchIdAbsensi").value;
            var bagian = document.getElementById("bagian").value;
            var nip = document.getElementById("nip").value;
            if (tglFrom!=""&&tglTo!=""&&branchId!=""){
                var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (bagian!=""){
                    var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+'  ?';
                }
                if (confirm(msg)) {
                    window.location.href = "printReportAbsensiTriwulan_absensi.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&branchId="+branchId+"&bagian="+bagian;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
        $('#btnCetakKoperasi').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var bagian = "KOPERASI";
            if (tglFrom!=""&&tglTo!=""){
                var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (confirm(msg)) {
                    window.location.href = "printReportUangMakanTambahan_absensi.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&bagian="+bagian;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
        $('#btnCetakDapur').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var bagian = "DAPUR";
            if (tglFrom!=""&&tglTo!=""){
                var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (confirm(msg)) {
                    window.location.href = "printReportUangMakanTambahan_absensi.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&bagian="+bagian;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
        $('#btnCetakAbsensiOutsourcing').click(function(){
            var tglFrom = document.getElementById("tglFrom").value;
            var tglTo = document.getElementById("tglTo").value;
            var bagian = "OUTSOURCING";
            if (tglFrom!=""&&tglTo!=""){
                var msg='Apakah Anda ingin mencetak laporan uang makan tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?';
                if (confirm(msg)) {
                    window.location.href = "printReportAbsensiOutsourcing_absensi.action?tglFrom="+tglFrom+"&tglTo="+tglTo+"&bagian="+bagian;
                }
            }else{
                alert("ada yang masih kosong");
            }
        });
    });
</script>