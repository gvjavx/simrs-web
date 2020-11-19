<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StudyAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KeluargaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MutasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <style>
        .ui-datepicker{z-index:2002 !important;}
        .ui-dialog{z-index:2001 !important;}

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
        th{
            text-align: center;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            location.reload();
        }

    </script>
</head>







<body class="hold-transition skin-blue sidebar-mini" >
<div id="modal-thr" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right; display: none" readonly type="text" class="form-control nip" id="payrollId" name="nip">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Pph</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTunjPph" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="thrTotal" name="nip">
                        </div>
                    </div>


                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-primary" id="printThr"> <i class="fa fa-save"></i> Print</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-pendidikan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right"  readonly type="text" class="form-control nip" id="pendidikanTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjStrategis" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Kompensasi</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjKompensasi" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Air Listrik</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjAirListrik" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Pph</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTunjPph" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pendidikanTotal" name="nip">
                        </div>
                    </div>


                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-primary" id="printPendidikan"> <i class="fa fa-save"></i> Print</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jasprod" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jasprodMasKer1" name="nip">
                        </div>

                        <label style="padding-left: 0px" class="control-label col-sm-1" >Tahun</label>

                        <div class="col-sm-1" style="padding-left: 10px;">
                            <a href="javascript:;" class="detailJasprodMasaKerja" >
                                <span style="font-size: 25px" class="glyphicon glyphicon-search"></span>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="jasprodGolongan" name="nip">
                        </div>
                        <label class="control-label col-sm-1" >/</label>
                        <div class="col-sm-2">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPoint" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTunjStrategis" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji Bruto (A)</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTotalBruto" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji X Masa Kerja</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPengali" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Faktor</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodFaktor" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai SMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodNilaiSmk" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Persen SMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPersenSmk" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >A. Perhitungan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodPerhitungan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label style="padding-left: 0px" class="control-label col-sm-4" >B. Gaji Kotor X Faktor</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodGajiKotor" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >C. Tambahan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodTambahan" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai Jasprod</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jasprodNilai" name="nip">
                        </div>
                    </div>

                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-primary" id="printJasprod"> <i class="fa fa-save"></i> Print</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-jasprod-masaKerja" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-3" style="padding-right:0px;">Tanggal Aktif</label>
                        <div class="col-sm-4" >
                            <input style="text-align: left; padding-left: 5px; padding-right: 3px;" readonly type="text" class="form-control nip" id="jasprodTglAktif" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-4">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jasprodTglAktifSekarang" name="nip">
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jubileum" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumMasKer1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumMasKer2" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px;">
                            <a href="javascript:;" class="detailJubileumMasaKerja">
                                <span style="font-size: 25px" class="glyphicon glyphicon-search"></span>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="jubileumGolongan" name="nip">
                        </div>
                        <label class="control-label col-sm-1" >/</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPoint" name="nip">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Besarnya Jubileum  </label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumTotal" name="nip">
                        </div>
                        <label class="control-label col-sm-2" style="text-align: left" >X 5</label>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Grand Total</label>
                        <div class="col-sm-6">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="jubileumGrandTotal" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-primary" id="printJubileum"> <i class="fa fa-save"></i> Print</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jubileum-masaKerja" class="modal fade modal2" role="dialog">
    <div class="modal-dialog ">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">PKWT</label>
                        <div class="col-sm-2" style="padding-right: 0px">
                            <input style="text-align: left; padding-left: 5px; padding-right: 3px;" readonly type="text" class="form-control nip" id="jubileumPkwt1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-2" style="padding-right: 0px">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumPkwt2" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >=</label>

                        <div class="col-sm-4" style="padding-left: 0px">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumJumlahPkwt" name="nip">
                        </div>
                    </div>

                    <div class="form-group" style="padding-right:0px;">
                        <label class="control-label col-sm-2" style="padding-left:0px; padding-right:0px;">Pegawai Tetap</label>
                        <div class="col-sm-2" style="padding-right:0px;">
                            <input style="text-align: left; padding-left: 5px; padding-right:0px;" readonly type="text" class="form-control nip" id="jubileumTetap1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-2" style="padding-right:0px;">
                            <input style="text-align: left; padding-left: 5px; padding-right:0px;" readonly type="text" class="form-control nip" id="jubileumTetap2" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >=</label>

                        <div class="col-sm-4" style="padding-left: 0px">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="jubileumJumlahTetap" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-8" >Total Masa Kerja</label>
                        <div class="col-sm-4" style="padding-left: 0px">
                            <input readonly type="text" class="form-control nip" id="jubileumTotalMasaKerja" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-pensiun" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Aktif</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="pensiunTgl1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >s/d</label>

                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 5px" readonly type="text" class="form-control nip" id="pensiunTgl2" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan</label>
                        <div class="col-sm-3">
                            <input style="text-align: left; padding-left: 2px; padding-right: 2px" readonly type="text" class="form-control nip" id="pensiunGolongan" name="nip">
                        </div>
                        <label class="control-label col-sm-1" >/</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunPoint" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Kerja</label>
                        <div class="col-sm-2">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunMasaKerjaTahun" name="nip">
                        </div>
                        <label style="text-align: left; padding-left: 0px" class="control-label col-sm-1" >Tahun</label>
                        <div class="col-sm-2">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunMasaKerjaBulan" name="nip">
                        </div>
                        <label style="text-align: left; padding-left: 0px" class="control-label col-sm-1" >Bulan</label>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gaji</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunGaji" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan UMK</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjUmk" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjStruktural" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunPeralihan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tunj. Jab.Struktural</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunTunjJabStruktural" name="nip">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah (A)</label>
                        <div class="col-sm-8">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunJumlah" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Pensiun</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunUangPensiun" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">
                            <input style="text-align: center;" readonly type="text" class="form-control nip" id="pensiunFaktorPensiun" name="nip">
                        </div>

                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunKaliPensiun" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Penghargaan</label>
                        <div class="col-sm-3">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunUangPenghargaan" name="nip">
                        </div>

                        <div class="col-sm-1" style="padding-left: 0px; padding-right: 0px">
                            <input style="text-align: center;" readonly type="text" class="form-control nip" id="pensiunFaktorPenghargaan" name="nip">
                        </div>

                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="pensiunKaliPenghargaan" name="nip">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-sm-offset-5  control-label col-sm-3" >Total Pensiun</label>

                        <div class="col-sm-4">
                            <input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunBiaya" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-offset-5  control-label col-sm-3" >PPH Pensiun</label>

                        <div class="col-sm-4">
                            <input style="padding-left: 0px; text-align: right" readonly type="text" class="form-control nip" id="pensiunPph" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-primary" id="printPensiun"> <i class="fa fa-save"></i> Print</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%--MODAL SPPD--%>
<div id="modal-detailSppd" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1400px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve SPPD</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dinas : </label>
                        <div class="col-sm-3">
                            <s:select list="#{'LN':'Luar Negeri'}" id="dinas1" name="sppd.dinas" disabled="true"
                                      headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Berangkat : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalBerangkat1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tanggal Kembali : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalKembali1" name="nip">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Dari: </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatDari1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tujuan : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tujuan1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatVia1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Kembali Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="kembaliVia1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keperluan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" readonly class="form-control" id="keperluan1" name="nip"></textarea>

                        </div>
                    </div>

                </form>
                <center>

                        <h4><b>PERSON</b></h4>
                        <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                        </table>
                        <br>
                        <%--<h4><b>REROUTE</b></h4>--%>
                        <table style="width: 100%; display: none" id="sppdRerouteTable" class="sppdRerouteTable table table-bordered">
                        </table>
                        <br>
                        <h4><b>DOCUMENT</b></h4>
                        <table style="width: 30%;" id="sppdDocTable" class="sppdDocTable table table-bordered">
                        </table>
                </center>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-tiketSppd" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:700px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve SPPD</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Sppd ID : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="sppdIdTiket" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="sppdNipTiket" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Harga Tiket : </label>
                        <div class="col-sm-8">
                            <input type="number"  class="form-control nip" id="sppdHargaTiketPergi" >
                        </div>
                    </div>

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Harga Tiket Kembali: </label>
                        <div class="col-sm-8">
                            <input type="number"  class="form-control nip" id="sppdHargaTiketKembali" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Biaya Tambahan: </label>
                        <div class="col-sm-8">
                            <input type="number"  class="form-control nip" id="sppdBiayaTambahan" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Biaya Transport Lokal: </label>
                        <div class="col-sm-8">
                            <input type="number"  class="form-control nip" id="sppdBiayaLokal" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Biaya Transport Tujuan: </label>
                        <div class="col-sm-8">
                            <input type="number"  class="form-control nip" id="sppdBiayaTujuan" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a id="btnAddSppdTiket" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modalUploadSppd" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">

                <s:url id="urlProcess" namespace="/sppd" action="addSppdDoc_sppd" includeContext="false"/>
                <s:form id="addUserForm" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass="well form-horizontal">

                    <div style="" class="form-group">
                        <label class="control-label col-sm-3" >Sppd ID : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="sppdId1" name="sppdDoc.sppdId">
                        </div>
                    </div>

                    <div style="display: none;" class="form-group">
                        <label class="control-label col-sm-3" >Person ID : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="sppdPersonId1" name="sppdDoc.sppdPersonId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >File : </label>
                        <div class="col-sm-8">
                            <input type="file" id="file" class="form-control" name="fileUpload" />
                                <%--<input type="upload" class="form-control" id="docFile" name="nip">--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="docNote" name="sppdDoc.note"></textarea>

                        </div>
                    </div>

                    <%--<div class="form-group">

                        <div style="padding-left: 140px" class="col-sm-8">
                            <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                       <%--resizable="false"--%>
                                       <%--height="250" width="600" autoOpen="false" title="Searching...">--%>
                                <%--Please don't close this window, server is processing your request ...--%>
                                <%--</br>--%>
                                <%--</br>--%>
                                <%--</br>--%>
                                <%--<center>--%>
                                    <%--<img border="0" src="<s:url value="/pages/images/loading11.gif"/>" name="image_indicator_read">--%>
                                <%--</center>--%>
                            <%--</sj:dialog>--%>
                            <%--<sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"--%>
                                       <%--height="200" width="400" autoOpen="false" title="Infomation Dialog"--%>
                                       <%--buttons="{--%>
                                                        <%--'OK':function() {--%>
                                                                 <%--$('#info_dialog').dialog('close');--%>
                                                                 <%--location.reload();--%>
                                                             <%--}--%>
                                                    <%--}"--%>
                            <%-->--%>
                                <%--<img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">--%>
                                <%--Record has been saved successfully.--%>
                            <%--</sj:dialog>--%>

                            <%--<sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"--%>
                                       <%--height="250" width="600" autoOpen="false" title="Error Dialog"--%>
                                       <%--buttons="{--%>
                                                                                <%--'OK':function() { $('#error_dialog').dialog('close'); }--%>
                                                                            <%--}"--%>
                            <%-->--%>
                                <%--<div class="alert alert-error fade in">--%>
                                    <%--<label class="control-label" align="left">--%>
                                        <%--<img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</sj:dialog>--%>

                            <%--<sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"--%>
                                       <%--height="280" width="500" autoOpen="false" title="Warning"--%>
                                       <%--buttons="{--%>
                                                                                <%--'OK':function() { $('#error_validation_dialog').dialog('close'); }--%>
                                                                            <%--}"--%>
                            <%-->--%>
                                <%--<div class="alert alert-error fade in">--%>
                                    <%--<label class="control-label" align="left">--%>
                                        <%--<img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :--%>
                                        <%--<br/>--%>
                                        <%--<center><div id="errorValidationMessage"></div></center>--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</sj:dialog>--%>

                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addUserForm" id="save" name="save"
                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                <i class="fa fa-check"></i>
                                Save
                            </sj:submit>

                            <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                        </div>

                    </div>--%>
                    <%--</form>--%>
                </s:form>
            </div>
        </div>
    </div>
</div>

<%--END SPPD MODAL--%>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Data Person
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <br>
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-1">
                <div class="pull-left image">
                    <s:url id="urlProcess" namespace="/biodata" action="view_biodata" includeContext="false"/>
                    <s:form id="myFormProfile" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass=" form-horizontal">
                        <img id="profile-image1" width="100" height="100"  src="<s:url value='/pages/upload/image/profile/'/><s:property value="biodata.fotoUpload" />" class="img-responsive img-circle" alt="Cinque Terre">
                        <br>
                        <input id="profile-image-upload" class="hidden" type="file" name="fileUpload">
                        <s:textfield id="nipImage" name="biodata.nip"
                                     required="false" readonly="true"
                                     cssClass=" hidden form-control"/>
                        <div id="actions" class="form-actions">
                            <table align="center" class="hidden">
                                <tr>
                                    <td>
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="myFormProfile"
                                                   id="saveProfil" name="save" onCompleteTopics="loadfoto" onSuccessTopics="loadfoto">
                                            <i class="fa fa-save"></i>
                                        </sj:submit>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </s:form>
                    <%--<a class="update_caption btn btn-success"><i class="fa fa-check"></i></a>--%>
                </div>
                <br>
                <br>
            </div>
            <div class="col-md-3">
                <p style="color: black; font-size: 20px"><s:property value="biodata.namaPegawai" /></p>
                <%--<s:textfield  id="home1" name="biodata.namaPegawai" required="false" readonly="false" cssClass="form-control"/>--%>
                <s:textfield  id="statusCaption" name="biodata.statusCaption" required="false" readonly="false" cssClass="form-control"/>
                <s:textfield cssStyle="display: none;" id="biodataKeterangan" name="biodata.keterangan" required="false" readonly="false" cssClass="form-control"/>

                <div class="radio">
                    <a class="info_link btn btn-default btn-xs btn-flat"><i class="fa fa-circle-o"></i> Available</a>
                </div>
                <div class="radio">
                    <a class="info_link btn btn-default btn-xs btn-flat"><i class="fa fa-circle-o"></i> Iam Online</a>
                </div>
                <div class="radio">
                    <a class="info_link btn btn-default btn-xs btn-flat"><i class="fa fa-circle-o"></i> Iam Offline</a>
                </div>
                <script>
                    $(function(){
                        $('.info_link').click(function(){
                            $('#statusCaption').val($(this).text());
                            var nip = document.getElementById("biodataNip").value;
                            var caption = document.getElementById("statusCaption").value;

                            BiodataAction.saveCaption(nip, caption,function(listdata) {
                                alert('Successfully Update Caption');
                            });
                        });
                    });
                </script>
            </div>
            <%-- <div class="user-panel" style="padding-bottom: 110px;">
                 <div class=" info">
                 </div>
             </div>--%>
        </div>

        <div class="row">

            <br>
            <div class="col-md-12">

                <ul class="nav nav-tabs" style="font-size: 14px;">
                    <li class="active"><a href="#biodata">Biodata</a></li>
                    <li><a href="#keluarga">Keluarga</a></li>
                    <li><a href="#RiwayatPendidikan">Riwayat Pendidikan</a></li>
                    <li><a href="#notifikasi">Notifikasi</a></li>
                    <li><a href="#cuti">Cuti</a></li>
                    <li><a href="#ijin">Dispensasi</a></li>
                    <li><a href="#ijinKantor">Ijin Keluar Kantor</a></li>
                    <li><a href="#lembur">Lembur</a></li>
                    <li><a href="#absensi">Absensi</a></li>
                    <li><a href="#sppd">SPPD</a></li>
                    <li><a href="#payroll">Payroll</a></li>
                    <li><a href="#jabatan">Jabatan</a></li>
                    <li><a href="#mutasi">Mutasi</a></li>
                    <li><a href="#training">Training</a></li>
                </ul>
                <s:form id="homeForm" theme="simple" namespace="/biodata" action="search_personal.action" cssClass="well form-horizontal">
                    <div class="tab-content well">
                    <div id="biodata" class="tab-pane fade in active">
                        <h4>Biodata</h4>
                            <br>
                            <table width="40%">
                                <tr>
                                    <td>
                                        <label class="control-label"><small>NIP :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="biodataNip" name="biodata.nip" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Nama :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No KTP :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.noKtp" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Alamat :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textarea rows="5" id="home1" name="biodata.alamat" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Tempat / Tanggal Lahir :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.tempatLahir" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No TELP :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.noTelp" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Tanggal Aktif :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.stTanggalAktif" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Tanggal Pensiun:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.stTanggalPensiun" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Masa Kerja:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.masaKerja" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Unit:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.branchName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Bagian:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.divisiName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Jabatan:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.positionName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>NPWP:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.npwp" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No KTP:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.noKtp" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Tipe Pegawai :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.tipePegawaiName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Status Giling:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.statusGilingName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Status pegawai:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.statusPegawaiName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Dana Pensiun :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  name="biodata.danaPensiun" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No Anggota Dapen:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  name="biodata.noAnggotaDapen" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No BPJS Ketenagakerjaan:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  name="biodata.noBpjsKetenagakerjaan" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No BPJS (Pensiun):</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  name="biodata.noBpjsKetenagakerjaanPensiun" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No BPJS Kesehatan:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  name="biodata.noBpjsKesehatan" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Zakat Profesi:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.zakatName" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label class="control-label"><small>Nama Bank:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.namaBank" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Cabang Bank:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.cabangBank" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label"><small>No Rek. Bank:</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="home1" name="biodata.noRekBank" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>

                            </table>
                    </div>
                    <div id="keluarga" class="tab-pane fade">
                        <h4>Keluarga</h4>
                        <center>
                            <table style="width: 50%;" class="keluargaTable table table-bordered"></table>
                        </center>
                    </div>
                    <div id="RiwayatPendidikan" class="tab-pane fade">
                        <h4>Riwayat Pendidikan</h4>
                        <center>
                            <table style="width: 60%;" class="studyTable table table-bordered">
                        </center>
                        </table>
                    </div>
                        <div id="notifikasi" class="tab-pane fade">
                            <h4>Notifikasi</h4>
                            <center>
                                <table id="showdataNotifikasi" width="80%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu_notifikasi" openTopics="showDialogMenuNotifikasi" modal="true"
                                                       height="520" width="700" autoOpen="false"
                                                       title="Notifikasi ">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>
                                            <s:set name="listOfNotifikasi" value="#session.listOfResultNotifikasi" scope="request" />
                                            <display:table name="listOfNotifikasi" class="tableNotifikasi table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_notifikasi.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/notifikasi" action="delete_notifikasi" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.notifId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenuNotifikasi" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                    </sj:a>

                                                </display:column>
                                                <display:column property="notifId" sortable="true" title="notifikasi Id"  style="text-align:center"/>
                                                <display:column property="tipeNotifName" sortable="true" title="Nama Notifikasi"   style="text-align:center"/>
                                                <display:column property="note" sortable="true" title="Keterangan"   style="text-align:center"/>
                                                <display:column property="fromPerson" sortable="true" title="Dari" style="text-align:center"/>
                                                <display:column property="lastUpdate" sortable="true" title="Tanggal" style="text-align:center" />
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>
                    <div id="cuti" class="tab-pane fade">
                        <sj:dialog id="dialog_menu_cuti_pegawai" openTopics="showDialogMenuCutiPegawai" modal="true"
                                   height="650" width="900" autoOpen="false"
                                   title="Cuti Pegawai ">
                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                        </sj:dialog>
                        <sj:dialog id="view_dialog_menu_cuti_pegawai" openTopics="viewShowDialogMenuCutiPegawai" modal="true"
                                   height="620" width="900" autoOpen="false"
                                   title="Cuti Pegawai ">
                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                        </sj:dialog>
                        <h4>Cuti</h4><br>
                        <%--<s:url var="urlAdd" namespace="/cutiPegawai" action="add_cutiPegawai" escapeAmp="false">
                        </s:url>
                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuCutiPegawai" href="%{urlAdd}">
                            <i class="fa fa-plus"></i>
                            Add Cuti Pegawai<br>
                        </sj:a><br>--%>
                        <center>
                            <table id="showdataCutiPegawai" width="80%">
                                <tr>
                                    <td align="center">
                                        <s:set name="listOfResultCutiPegawai" value="#session.listOfResultCutiPegawai" scope="request" />
                                        <display:table name="listOfResultCutiPegawai" class="tableCutiPegawai table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_cutiPegawai.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Batal">
                                                <s:if test="#attr.row.cancel">
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                </s:if>
                                                <s:elseif test="#attr.row.canCancel">
                                                    <s:url var="urlCancel" namespace="/cutiPegawai" action="cancel_cutiPegawai" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="viewShowDialogMenuCutiPegawai" href="%{urlCancel}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:elseif>
                                            </display:column>
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.cancel">
                                                </s:if>
                                                <s:elseif test="#attr.row.flagYes">
                                                    <s:if test="#attr.row.cutiPegawaiApprove">
                                                    </s:if>
                                                    <s:else>
                                                        <s:url var="urlEdit" namespace="/cutiPegawai" action="edit_cutiPegawai" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.cutiPegawaiId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="viewShowDialogMenuCutiPegawai" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:else>
                                                </s:elseif>
                                            </display:column>
                                            <display:column media="html" title="Cetak Surat" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.cancel">
                                                </s:if>
                                                <s:elseif test="#attr.row.finish">
                                                    <s:url var="urlCetakSuratCuti" namespace="/cutiPegawai" action="cetakSuratCuti_cutiPegawai" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlCetakSuratCuti}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:elseif>
                                            </display:column>
                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/cutiPegawai" action="delete_cutiPegawai" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.cutiPegawaiId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="viewShowDialogMenuCutiPegawai" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                </sj:a>
                                            </display:column>
                                            <display:column property="cutiPegawaiId" sortable="true" title="Cuti Pegawai ID" style="text-align:center" />
                                            <display:column property="pegawaiPenggantiSementara" sortable="true" title="Pegawai Pengganti"/>
                                            <display:column property="alamatCuti" sortable="true" title="Alamat" style="text-align:center"/>
                                            <display:column property="keterangan" sortable="true" title="Keterangan" style="text-align:center"/>
                                            <display:column property="stTanggalDari" sortable="true" title="Tanggal Dari" style="text-align:center"/>
                                            <display:column property="stTanggalSelesai" sortable="true" title="Tanggal Selesai" style="text-align:center"/>
                                            <display:column media="html" title="Approval"  style="text-align:center">
                                                <s:if test="#attr.row.cancel">
                                                </s:if>
                                                <s:elseif test="#attr.row.cutiPegawaiApprove">
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                </s:elseif>
                                                <s:elseif test="#attr.row.notApprove">
                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                </s:elseif>
                                                <s:else>
                                                </s:else>
                                            </display:column>
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                        <center>
                            <table style="width: 80%;" class="cutiTable table table-bordered"></table>
                        </center>

                    </div>
                    <div id="ijin" class="tab-pane fade">
                        <h4>Dispensasi</h4><br>
                        <%--<s:url var="urlAdd" namespace="/ijinKeluar" action="add_ijinKeluar" escapeAmp="false">--%>
                        <%--</s:url>--%>
                        <%--<sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuIjinKeluar" href="%{urlAdd}">--%>
                            <%--<i class="fa fa-plus"></i>--%>
                            <%--Add Dispensasi--%>
                        <%--</sj:a>--%>
                        <center>
                            <table id="showdataIjinKeluar" width="60%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="dialog_menu_ijin_keluar" openTopics="showDialogMenuIjinKeluar" modal="true"
                                                   height="700" width="900" autoOpen="false"
                                                   title="Ijin Tidak Masuk ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_menu_ijin_keluar" openTopics="viewShowDialogMenuIjinKeluar" modal="true"
                                                   height="650" width="900" autoOpen="false"
                                                   title="Ijin Tidak Masuk ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                        <s:set name="listOfIjinKeluar" value="#session.listOfResultIjinKeluar" scope="request" />
                                        <display:table name="listOfIjinKeluar" class="tableIjinKeluar table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_ijinKeluar.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Batal">
                                                <s:if test="#attr.row.cancel">
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                </s:if>
                                                <s:elseif test="#attr.row.notApprove">
                                                </s:elseif>
                                                <s:elseif test="#attr.row.notApproveSdm">
                                                </s:elseif>
                                                <s:elseif test="#attr.row.canCancel">
                                                    <s:url var="urlCancel" namespace="/ijinKeluar" action="cancel_ijinKeluar" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenuView" href="%{urlCancel}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:elseif>
                                            </display:column>
                                            <display:column media="html" title="Cetak Surat" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.cancel">
                                                </s:if>
                                                <s:elseif test="#attr.row.finish">
                                                    <s:url var="urlCetakSuratIjinTidakMasuk" namespace="/ijinKeluar" action="cetakSuratIjinTidakMasuk_ijinKeluar" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlCetakSuratIjinTidakMasuk}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:elseif>
                                            </display:column>
                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/ijinKeluar" action="delete_ijinKeluar" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="viewShowDialogMenuIjinKeluar" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                </sj:a>
                                            </display:column>
                                            <display:column property="ijinKeluarId" sortable="true" title="Dispensasi ID" style="text-align:center"/>
                                            <display:column property="ijinName" sortable="true" title="Keterangan Ijin" style="text-align:center" />
                                            <display:column property="tanggalAwal" sortable="true" title="Tanggal Awal" style="text-align:center" />
                                            <display:column property="tanggalAkhir" sortable="true" title="Tanggal Akhir" style="text-align:center" />
                                            <display:column property="lamaIjin" sortable="true" title="Lama ( Hari )" style="text-align:center" />
                                            <display:column media="html" title="Approval Atasan" style="text-align:center">
                                                <s:if test="#attr.row.ijinKeluarApprove">
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                </s:if>
                                                <s:elseif test="#attr.row.notApprove">
                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                </s:elseif>
                                                <s:else></s:else>
                                            </display:column>
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                        <center>
                            <table style="width: 80%;" class="ijinTable table table-bordered"></table>
                        </center>
                    </div>
                        <div id="ijinKantor" class="tab-pane fade">
                            <h4>Ijin Keluar Kantor</h4><br>
                            <%--<s:url var="urlAdd" namespace="/ijinKeluar" action="addKantor_ijinKeluar" escapeAmp="false">
                            </s:url>
                            <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuIjinKeluarKantor" href="%{urlAdd}">
                                <i class="fa fa-plus"></i>
                                Add Ijin Keluar Kantor
                            </sj:a>--%>
                            <center>
                                <table id="showdataIjinKeluarKantor" width="80%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="dialog_menu_ijin_keluar_kantor" openTopics="showDialogMenuIjinKeluarKantor" modal="true"
                                                       height="650" width="900" autoOpen="false"
                                                       title="Ijin Keluar Kantor ">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>
                                            <sj:dialog id="view_dialog_menu_ijin_keluar_kantor" openTopics="viewShowDialogMenuIjinKeluarKantor" modal="true"
                                                       height="600" width="600" autoOpen="false"
                                                       title="Ijin Keluar Kantor">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>
                                            <s:set name="listOfIjinKeluarKantor" value="#session.listOfResultIjinKeluarKantor" scope="request" />
                                            <display:table name="listOfIjinKeluarKantor" class="tableIjinKeluarKantor table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_ijinKeluar.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/ijinKeluar" action="deleteKantor_ijinKeluar" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="viewShowDialogMenuIjinKeluarKantor" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                    </sj:a>
                                                </display:column>
                                                <display:column media="html" title="Cetak Surat Ijin" style="text-align:center">
                                                    <s:if test="#attr.row.ijinKeluarApprove">
                                                        <s:url var="urlCetakSurat" namespace="/ijinKeluar" action="cetakSurat_ijinKeluar" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.ijinKeluarId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <s:a href="%{urlCetakSurat}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                        </s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>
                                                <display:column property="ijinKeluarId" sortable="true" title="Ijin Keluar Kantor Id" style="text-align:center"/>
                                                <display:column property="keperluan" sortable="true" title="Keperluan"/>
                                                <display:column property="keterangan" sortable="true" title="Keterangan"/>
                                                <display:column property="stTanggalAwal" sortable="true" title="Tanggal" style="text-align:center"/>
                                                <display:column property="jamKeluar" sortable="true" title="Jam Keluar" style="text-align:center"/>
                                                <display:column property="jamKembali" sortable="true" title="Jam Kembali" style="text-align:center"/>
                                                <display:column media="html" title="Approval Atasan" style="text-align:center">
                                                    <s:if test="#attr.row.ijinKeluarApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else></s:else>
                                                </display:column>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                            <center>
                                <table style="width: 80%;" class="ijinKantorTable table table-bordered"></table>
                            </center>
                        </div>
                        <div id="lembur" class="tab-pane fade">
                            <h4>Lembur</h4><br>
                            <%--<s:url var="urlAdd" namespace="/lembur" action="add_lembur" escapeAmp="false">
                            </s:url>
                            <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuLembur" href="%{urlAdd}">
                                <i class="fa fa-plus"></i>
                                Add Lembur
                            </sj:a>--%>
                            <center>
                                <table id="showdataLembur" width="80%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="dialog_menu_lembur" openTopics="showDialogMenuLembur" modal="true"
                                                       height="650" width="900" autoOpen="false"
                                                       title="Lembur">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>
                                            <sj:dialog id="view_dialog_menu_lembur" openTopics="viewShowDialogMenuLembur" modal="true"
                                                       height="600" width="900" autoOpen="false"
                                                       title="Lembur">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>
                                            <s:set name="listOfLembur" value="#session.listOfResultLembur" scope="request" />
                                            <display:table name="listOfLembur" class="tableLembur table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_lembur.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                <display:column media="html" title="Delete">
                                                    <s:if test="#attr.row.terRealisasi">
                                                    </s:if>
                                                    <s:else>
                                                        <s:url var="urlEdit" namespace="/lembur" action="delete_lembur" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.lemburId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:else>
                                                </display:column>
                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.terRealisasi">
                                                    </s:if>
                                                    <s:else>
                                                        <s:if test="#attr.row.lemburApprove">
                                                        </s:if>
                                                        <s:else>
                                                            <s:url var="urlEdit" namespace="/lembur" action="edit_lembur" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.lemburId"/></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                            </sj:a>
                                                        </s:else>
                                                    </s:else>
                                                </display:column>
                                                <display:column property="lemburId" sortable="true" title="Lembur Id" style="text-align:center"/>
                                                <display:column property="tipeLemburName" sortable="true" title="Tipe Lembur" style="text-align:center"/>
                                                <display:column property="tanggalAwal" sortable="true" title="Tanggal Awal" style="text-align:center"/>
                                                <display:column property="tanggalAkhir" sortable="true" title="Tanggal Akhir" style="text-align:center"/>
                                                <display:column property="keterangan" sortable="true" title="Keterangan" style="text-align:center"/>
                                                <display:column property="lamaJam" sortable="true" title="Lama" style="text-align:center"/>
                                                <display:column property="jamRealisasi" sortable="true" title="Realisasi" style="text-align:center"/>
                                                <display:column media="html" title="Approve Atasan" style="text-align:center">
                                                    <s:if test="#attr.row.lemburApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                    </s:else>
                                                </display:column>
                                                <display:column media="html" title="Refresh" style="text-align:center">
                                                    <s:if test="#attr.row.lemburApprove">
                                                        <s:if test="#attr.row.terRealisasi">
                                                            <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_success">
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:;" tanggal="<s:property value="%{#attr.row.stTanggalAwal}"/>" nip="<s:property value="%{#attr.row.nip}"/>" jamAwal="<s:property value="%{#attr.row.jamAwal}"/>" jamAkhir="<s:property value="%{#attr.row.jamAkhir}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                                <img border="0" src="<s:url value="/pages/images/icon_reset.png"/>" name="icon_edit">
                                                            </a>
                                                        </s:else>
                                                    </s:if>
                                                </display:column>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                            <center>
                                <table style="width: 80%;" class="lemburTable table table-bordered"></table>
                            </center>
                        </div>
                    <div id="absensi" class="tab-pane fade">
                        <h4>Absensi</h4>
                        <center>
                            <table id="showdataAbsensi" width="60%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu_absensi" openTopics="showDialogMenuAbsensi" modal="true"
                                                   height="520" width="700" autoOpen="false"
                                                   title="Absensi ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                        <s:set name="listOfAbsensi" value="#session.listOfResultAbsensi" scope="request" />
                                        <display:table name="listOfAbsensi" class="tableAbsensi table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_absensi.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column property="stTanggal" sortable="true" title="Tanggal" style="text-align:center" />
                                            <display:column property="jamMasuk" sortable="true" title="Jam Masuk" style="text-align:center" />
                                            <display:column property="jamKeluar" sortable="true" title="Jam Pulang" style="text-align:center" />
                                            <display:column property="statusName" sortable="true" title="status" style="text-align:left" />
                                            <display:column property="ijin" sortable="true" title="ijin" style="text-align:center" />
                                            <display:column property="lembur" sortable="true" title="lembur" style="text-align:center" />
                                            <display:column property="realisasiJamLembur" sortable="true" title="Realisasi Lembur" style="text-align:center" />
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </div>
                    <div id="sppd" class="tab-pane fade">
                        <h4>SPPD</h4>
                        <center>
                            <table style="width: 80%;" class="sppdTable table table-bordered"></table>
                        </center>
                    </div>
                    <div id="payroll" class="tab-pane fade">
                        <h4>Payroll</h4>
                        <center>
                            <table style="width: 80%;" class="payrollTable table table-bordered"></table>
                        </center>
                    </div>
                    <div id="jabatan" class="tab-pane fade">
                        <h4>Jabatan</h4>
                        <center>
                            <table style="width: 60%;" class="jabatanTable table table-bordered"></table>
                        </center>
                    </div>
                        <div id="mutasi" class="tab-pane fade">
                            <h4>Daftar Mutasi</h4>
                            <center>
                                <table style="width: 80%;" class="mutasiTable table table-bordered"></table>
                            </center>
                        </div>
                        <div id="training" class="tab-pane fade">
                            <br>
                            <br>
                            <center>
                                <table id="showdata" width="60%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu_training" openTopics="showDialogMenuTraining" modal="true"
                                                       height="540" width="600" autoOpen="false"
                                                       title="Upload Document Training">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResult" value="#session.listOfResultPerson" scope="request" />
                                            <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_medicalrecord.action" export="false" id="row" pagesize="20" style="font-size:10">


                                                <display:column media="html" title="Unggah Dokumen Training" style="text-align:center;font-size:9">

                                                        <s:url var="urlViewDoc" namespace="/training" action="initUpload_training" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.trainingPersonId" /></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenuTraining" href="%{urlViewDoc}">
                                                            <img border="0" src="<s:url value="/pages/images/add_doc.png"/>" height="25px" name="icon_trash">
                                                        </sj:a>

                                                </display:column>
                                                <display:column property="trainingId" sortable="true" title="ID Training" />
                                                <display:column property="trainingName" sortable="true" title="Nama Training"  />
                                                <display:column property="tipeTraining" sortable="true" title="Tipe Training"  />
                                                <display:column property="instansi" sortable="true" title="Instansi"  />
                                                <display:column property="stTrainingStartdate" sortable="true" title="Tanggal Mulai"  />
                                                <display:column property="stTrainingEndDate" sortable="true" title="Tanggal Selesai"  />
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>
                </div>
                </s:form>
            </div>
            <div class="col-sm-offset-4 col-sm-10">
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
    function cek(){
        var home    = document.getElementById("home1").value;
        var person    = document.getElementById("person1").value;
        var training    = document.getElementById("training1").value;
        var menu1    = document.getElementById("menu11").value;
        var menu2    = document.getElementById("menu21").value;
        var menu3    = document.getElementById("menu31").value;

        alert('Home : ' + home + ', Person : ' + person + ', training : ' + training + ', menu1 : ' + menu1 + ', menu2 : ' + menu2 + ', menu3 : ' + menu3);
        //window.location.href="<s:url action='search_alat'/>";
    }
    $(document).ready(function(){
        var keterangan = document.getElementById("biodataKeterangan").value;
        if(keterangan == '--kosong--'){
            $('.info_link').attr('disabled', 'disabled');
            $('.info_link').off('click');;
        }

        var nip    = document.getElementById("biodataNip").value;
        $('#nipImage').val(nip)

        $('#profile-image1').on('click', function () {
            $('#profile-image-upload').click();
        });
        $("#profile-image-upload").on('change',function(){
            $('#saveProfil').click();
            location.reload();
        });



        function loadStudy(nip){
            //alert(nip);
            $('.studyTable').find('tbody').remove();
            $('.studyTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            StudyAction.searchData(nip, function(listdata) {

                tmp_table = "<thead style='font-size: 13px; color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Akhir</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    var tahunAkhir = "-";
                    if(item.tahunAkhir != null){
                        tahunAkhir = item.tahunAkhir;
                    }
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td >' + (i + 1) + '</td>' +
                            '<td >' + item.typeStudy + '</td>' +
                            '<td align="center">' + item.studyName + '</td>' +
                            '<td align="center">' + item.tahunAwal + '</td>' +
                            '<td align="center">' + tahunAkhir + '</td>' +

                            "</tr>";
                });
                $('.studyTable').append(tmp_table);
            });

        }

        function loadKeluarga(nip){
            //alert(nip);
            $('.keluargaTable').find('tbody').remove();
            $('.keluargaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            KeluargaAction.searchData(nip, "", function(listdata) {

                tmp_table = "<thead style='font-size: 13px; color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Status Keluarga</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Gender</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Lahir</th>"+
                        "</tr></thead>";

                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td >' + (i +1 ) + '</td>' +
                            '<td >' + item.name + '</td>' +
                            '<td align="center">' + '-' + '</td>' +
                            '<td align="center">' + item.statusKeluargaName + '</td>' +
                            '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                                /*'<td align="center">' + myDate.toTimeString("dd-mm-yy") + '</td>' +*/
                            "</tr>";
                });
                $('.keluargaTable').append(tmp_table);
            });

        }
        function loadAbsensi(nip){
            $('.absensiTable').find('tbody').remove();
            $('.absensiTable').find('thead').remove();
        }
        function loadCutiPegawai(nip){
            //alert(nip);
            $('.cutiTable').find('tbody').remove();
            $('.cutiTable').find('thead').remove();
        }
        function loadIjinKeluar(nip){
            //alert(nip);
            $('.ijinTable').find('tbody').remove();
            $('.ijinTable').find('thead').remove();
        }
        function loadIjinKeluarKantor(nip){
            //alert(nip);
            $('.ijinKeluarTable').find('tbody').remove();
            $('.ijinKeluarTable').find('thead').remove();
        }
        function loadLembur(nip){
            //alert(nip);
            $('.lemburTable').find('tbody').remove();
            $('.lemburTable').find('thead').remove();
        }
        function loadPayroll(nip){
            $('.payrollTable').find('tbody').remove();
            $('.payrollTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchPayroll(nip, function(listdata) {
                tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Download</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Kotor</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Potongan</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Pph</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Bersih</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Rapel</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Thr</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Pendidikan</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Jasprod</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Pensiun</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Jubilium</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    var link = "/hris/payroll/printReportPayroll_payroll.action?id=" + item.payrollId + "&flag=Y" + "&tipe=PR";
                    var thr = "";
                    if(item.totalThr != '0'){
                        thr = "<a onclick=detailThr('"+item.payrollId+"') > "+ item.totalThr +" </a>";
                    }else{
                        thr = item.totalThr ;
                    }

                    var pendidikan = "";
                    if(item.totalPendidikan != '0'){
                        pendidikan = "<a onclick=detailPendidikan('"+item.payrollId+"') > "+ item.totalThr +" </a>";
                    }else{
                        pendidikan= item.totalPendidikan ;
                    }

                    var jasprod = "";
                    if(item.totalJasProd != '0'){
                        jasprod = "<a onclick=detailJasprod('"+item.payrollId+"') > "+ item.totalJasProd +" </a>";
                    }else{
                        jasprod= item.totalJasProd;
                    }

                    var jubileum = "";
                    if(item.totalJubileum != '0'){
                        jubileum = "<a onclick=detailJubileum('"+item.payrollId+"') > "+ item.totalJubileum +" </a>";
                    }else{
                        jubileum = item.totalJubileum;
                    }

                    var pensiun = "";
                    if(item.totalPensiun != '0'){
                        pensiun = "<a onclick=detailPensiun('"+item.payrollId+"') > "+ item.totalPensiun+" </a>";
                    }else{
                        pensiun = item.totalPensiun;
                    }

                    tmp_table += '<tr  style="font-size: 12px">' +
                            '<td ><a href="'+link+'" >Download</a></td>' +
                            '<td >' + item.bulan+ '</td>' +
                            '<td >' + item.tahun+ '</td>' +
                            '<td >' + item.totalA+ '</td>' +
                            '<td >' + item.totalB+ '</td>' +
                            '<td >' + item.pphGaji+ '</td>' +
                            '<td >' + item.totalGajiBersih+ '</td>' +
                            '<td >' + item.totalRapel+ '</td>' +
                            '<td >' + thr + '</td>' +
                            '<td >' + pendidikan + '</td>' +
                            '<td >' + jasprod + '</td>' +
                            '<td >' + pensiun+ '</td>' +
                            '<td >' + jubileum+ '</td>' +

                            "</tr>";
                });
                $('.payrollTable').append(tmp_table);
            });
        }
        function loadJabatan(nip){
            $('.jabatanTable').find('tbody').remove();
            $('.jabatanTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.historyJabatan(nip, function(listdata) {
                tmp_table = "<thead style='color: white; font-size: 14px'><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Bidang</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>PJS</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                        "</tr></thead>";
                var i = i ;
                var totalPoin = 0;
                $.each(listdata, function (i, item) {
                    var bidang = "-";
                    var branchName = "-";
                    var positionName = "-";
                    var status = "-";
                    var pjs = "-";
                    var tahun = "-";

                    if(item.bidang!= null){
                        bidang = item.bidang;
                    }
                    if(item.tahun != null){
                        tahun = item.tahun;
                    }
                    if(item.branchName!= null){
                        branchName = item.branchName;
                    }
                    if(item.positionName!= null){
                        positionName = item.positionName;
                    }
                    if(item.status!= null){
                        status = item.status;
                    }
                    if(item.pjsFlag!= null){
                        pjs = "Iya";
                    }
                    tmp_table += '<tr  style="font-size: 12px">' +
                            '<td >' + bidang + '</td>' +
                            '<td >' + branchName+ '</td>' +
                            '<td >' + positionName+ '</td>' +
                            '<td >' + status+ '</td>' +
                            '<td >' + pjs+ '</td>' +
                            '<td >' + tahun+ '</td>' +
                            "</tr>";
                });
                $('.jabatanTable').append(tmp_table);
            });
        }
        function loadSppd(nip){
            //alert(nip);
            $('.sppdTable').find('tbody').remove();
            $('.sppdTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            SppdAction.searchSppdPerson(nip, function(listdata) {

                tmp_table = "<thead style='font-size: 13px; color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Sppd ID</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Dinas</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Dari</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tujuan Ke</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Berangkat</th>"+
                        /*"<th style='text-align: center; background-color:  #3c8dbc''>Reroute</th>"+*/
                        "<th style='text-align: center; background-color:  #3c8dbc''>Upload Doc</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Detail</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Tiket</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Approve</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Approve SDM</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Closed</th>"+
                        "</tr></thead>";

                $.each(listdata, function (i, item) {
                    var atasan = '';
                    var sdm = '';
                    var closed = '';
                    var tiket = '';

                    if(item.sppdApproveSdm == true){
                        sdm = item.linkSdm ;
                    }
                    if(item.approvalSdmFlag == "Y"){
                        tiket = '<a class="item-tiket" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-credit-card" style="font-size:19px"></i></a>';
                    }else{
                        tiket = "<img src=<s:url value='/pages/images/icon_not_edit.png'/> name='icon_edit'> ";;
                    }
                    if(item.sppdApprove == true){
                        atasan = item.linkAtasan ;
                    }
                    if(item.sppdClosed == true){
                        closed = item.tmpClosed ;
                    }

                    var myDate = new Date(item.tanggalBerangkat);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i +1) + '</td>' +
                            '<td >' + item.sppdId + '</td>' +
                            '<td >' + item.dinas + '</td>' +
                            '<td align="center">' + item.berangkatDari+ '</td>' +
                            '<td align="center">' + item.tujuanKe+ '</td>' +
                            '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                            /*'<td align="center"> <a class="item-reroute" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-refresh" style="font-size:19px"></i></a> </td>' +*/
                            '<td align="center"> <a class="item-upload" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-upload" style="font-size:19px"></i></a> </td>' +
                            '<td align="center"> <a class="item-edit" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-book" style="font-size:19px"></i></a> </td>' +
                            '<td align="center">  '+tiket+'</td>' +
                            '<td align="center"> <img src = "' + atasan + '"> </td>' +
                            '<td align="center"> <img src = "' + sdm + '"> </td>' +
                            '<td align="center"> <img src = "' + closed + '"> </td>' +
                                /*'<td align="center">' + myDate.toTimeString("dd-mm-yy") + '</td>' +*/
                            "</tr>";
                });
                $('.sppdTable').append(tmp_table);
            });
        }

        function loadMutasi(nip){
            $('.mutasiTable').find('tbody').remove();
            $('.mutasiTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            MutasiAction.searchMutasi(nip, function(listdata) {

                tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Mutasi ID</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>NIP</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Unit Lama</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Bidang Lama</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Jabatan Lama</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Init Baru</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Bidang Baru</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Jabatan Baru</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal Efektif</th>"+
                        "</tr></thead>";

                $.each(listdata, function (i, item) {
                    var atasan = '';
                    var sdm = '';
                    var closed = '';
                    var tiket = '';

                    if(item.sppdApproveSdm == true){
                        sdm = item.linkSdm ;
                    }
                    if(item.approvalSdmFlag == "Y"){
                        tiket = '<a class="item-tiket" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-credit-card" style="font-size:19px"></i></a>';
                    }else{
                        tiket = "<img src=<s:url value='/pages/images/icon_not_edit.png'/> name='icon_edit'> ";;
                    }
                    if(item.sppdApprove == true){
                        atasan = item.linkAtasan ;
                    }
                    if(item.sppdClosed == true){
                        closed = item.tmpClosed ;
                    }

                    var myDate = new Date(item.tanggalBerangkat);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i +1) + '</td>' +
                            '<td >' + item.mutasiId + '</td>' +
                            '<td >' + item.nip + '</td>' +
                            '<td align="center">' + item.branchLamaName+ '</td>' +
                            '<td align="center">' + item.divisiLamaName+ '</td>' +
                            '<td align="center">' + item.positionLamaName+ '</td>' +
                            '<td align="center">' + item.branchBaruName+ '</td>' +
                            '<td align="center">' + item.divisiBaruName+ '</td>' +
                            '<td align="center">' + item.positionBaruName+ '</td>' +
                            '<td align="center">' + item.stTanggalEfektif + '</td>' +
                            "</tr>";
                });
                $('.mutasiTable').append(tmp_table);
            });
        }

        $(".nav-tabs a").click(function(){
            var nip = document.getElementById("biodataNip").value;
            var target = $(this).attr('href');

            if(target == "#RiwayatPendidikan"){
                loadStudy(nip);
            }else if(target == "#keluarga"){
                loadKeluarga(nip);
            }else if(target == "#sppd"){
                loadSppd(nip);
            }
            else if(target == "#cuti"){
                loadCutiPegawai(nip);
            }
            else if(target == "#ijin"){
                loadIjinKeluar(nip);
            }
            else if(target == "#ijinKantor"){
                loadIjinKeluarKantor(nip);
            }
            else if(target == "#lembur"){
                loadLembur(nip);
            }
            else if(target == "#payroll"){
                loadPayroll(nip);
            }
            else if(target == "#jabatan"){
                loadJabatan(nip);
            }
            else if(target == "#absensi"){
                loadAbsensi(nip);
            }
            else if(target == "#mutasi"){
                loadMutasi(nip);
            }

            $(this).tab('show');
        });
    });
</script>

<script>
    //script SPPD
    $.subscribe('beforeProcessSave', function (event, data) {
        var note = document.getElementById("docNote").value ;
        var file = document.getElementById("file").value ;
        if ( note != '') {
            if (confirm('Do you want to save this record?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        } else {
            event.originalEvent.options.submit = false;
            var msg = "";
            if (note == '') {
                msg = 'Field <strong>Note</strong> is required.' + '<br/>';
                alert('Note Harus diisi');
            }
        }
    });

    $.subscribe('successDialog', function (event, data) {
        if (event.originalEvent.request.status == 200) {
            jQuery(".ui-dialog-titlebar-close").hide();
            $.publish('showInfoDialog');
        }
    });

    $('.sppdTable').on('click', '.item-edit', function(){
        var sppdId = $(this).attr('data');
        $('#sppdId1').val(sppdId);
        //alert(sppdId);
        SppdAction.searchSppd(sppdId, function(listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalSppdBerangkat);
                var myDate1 = new Date(item.tanggalSppdKembali);
                $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                $('#nama1').val(item.personName);
                $('#branchId1').val(item.branchId).change();
                $('#positionId1').val(item.positionId).change();
                $('#dinas1').val(item.dinas).change();
                $('#divisiId1').val(item.divisiId).change();
                $('#keperluan1').val(item.tugasSppd);
                $('#berangkatDari1').val(item.berangkatDari);
                $('#tujuan1').val(item.tujuanKe);
                $('#berangkatVia1').val(item.berangkatVia);
                $('#kembaliVia1').val(item.pulangVia);
                $('#notApprove').val(item.notApprovalNote);
                $('#approveAtasan').val(item.approvalName);
                $('#approveAtasanId').val(item.approvalId);
                loadPerson(sppdId, 'N');
                loadReroute(sppdId);
                loadDocSppd(sppdId);
            });
        });
        //alert( $('#branchId1').text);
        $('#modal-detailSppd').find('.modal-title').text('Detail SPPD Person');
        $('#modal-detailSppd').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('.sppdTable').on('click', '.item-upload', function(){
        var sppdId = $(this).attr('data');
        var nip = document.getElementById("biodataNip").value;
        $('#modalUploadSppd').find('.modal-title').text('Edit Upload');
        $('#sppdId1').val(sppdId);
        $('#sppdPersonId1').val(nip);
        $('#modalUploadSppd').modal('show');
        $('#myFormSppdUpload').attr('action', 'Edit');
    });
    $('.sppdTable').on('click', '.item-download', function(){
        var sppdId = $(this).attr('data');
        alert(sppdId);
        SppdAction.searchAnggota(sppdId, function(listdata) {

        });
    });
    $('.sppdTable').on('click', '.item-tiket', function(){
        var sppdId = $(this).attr('data');
        var nip = document.getElementById("biodataNip").value;

        SppdAction.searchAnggota(sppdId,function(listdata) {
            $.each(listdata, function (i, item) {
                $('#sppdTiketSppd').val(sppdId);
                $('#sppdTiketPersonId').val(item.personId);
                $('#sppdTiketPersonId').val(item.personName);
                $('#sppdHargaTiketPergi').val(item.tiketPergi);
                $('#sppdHargaTiketKembali').val(item.tiketKembali);
                $('#sppdBiayaTambahan').val(item.biayaTambahan);
                $('#sppdBiayaLokal').val(item.biayaLokal);
                $('#sppdBiayaTujuan').val(item.biayaTujuan);
            });
        });

        $('#modal-tiketSppd').find('.modal-title').text('Edit Biaya Tiket');
        $('#sppdIdTiket').val(sppdId);
        $('#sppdNipTiket').val(nip);
        $('#modal-tiketSppd').modal('show');
    });
    $('.sppdTable').on('click', '.item-reroute', function(){
        var sppdId = $(this).attr('data');
        var nip = document.getElementById("biodataNip").value;
        $('#myFormReroute')[0].reset();
        $('#modal-reroute').find('.modal-title').text('Add Reroute');
        $('#modal-reroute').modal('show');
        $('#myFormReroute').attr('action', 'Add');
    });
    $('#btnAddSppdTiket').click(function(){
        var sppdId = document.getElementById("sppdIdTiket").value;
        var nip = document.getElementById("sppdNipTiket").value;
        var hargaPergi = document.getElementById("sppdHargaTiketPergi").value;
        var hargaKembali = document.getElementById("sppdHargaTiketKembali").value;
        var biayaTambahan = document.getElementById("sppdBiayaTambahan").value;
        var biayaLokal = document.getElementById("sppdBiayaLokal").value;
        var biayaTujuan = document.getElementById("sppdBiayaTujuan").value;

        if(hargaPergi != '' ){
            if (confirm('Are you sure you want to Update this Record?')) {
                SppdAction.SaveTiket(sppdId, nip, hargaPergi, hargaKembali, biayaTambahan, biayaLokal, biayaTujuan, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-tiketSppd').modal('hide');
                    location.reload();
                });
            }
        }else{
            alert('Field Harga Tiket tidak boleh Kosong !');
        }
    });
    window.loadPerson =  function(id, status){
        //alert(nip);
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();
        //alert(branch);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Branch</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bidang</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Position</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Approval</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note For Not Approval</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Approval SDM Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Approval SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note For Not Approval SDM</th>"+
                        /*"<th style='text-align: center; background-color:  #3c8dbc''>Nip Pengganti</th>"+*/
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Pengganti</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';

                if(item.sppdApproveSdm == true){
                    sdm = item.linkSdm ;
                }
                if(item.sppdApprove == true){
                    atasan = item.linkAtasan ;
                }

                tmp_table += '<tr  style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName  + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.approvalName + '</td>' +
                        '<td align="center"><img src="' + atasan + '"</td>' +
                        '<td align="center">' + item.notApprovalNote + '</td>' +
                        '<td align="center">' + item.approvalSdmName+ '</td>' +
                        '<td align="center"><img src="' + sdm + '"</td>' +
                        '<td align="center">' + item.notApprovalSdmNote + '</td>' +
                        '<td align="center">' + item.pejabatSementaraNama + '</td>' +

                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);

        });
    }
    window.loadReroute =  function(sppdId){
        //alert(branch);
        $('.sppdRerouteTable').find('tbody').remove();
        $('.sppdRerouteTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchReroute(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Reroute Dari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Reroute Ke</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Dari Tanggal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sampai Tanggal</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalRerouteDari);
                var myDate1 = new Date(item.tanggalRerouteKe);

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + item.sppdPersonName + '</td>' +
                        '<td align="center">' + item.rerouteDari + '</td>' +
                        '<td align="center">' + item.rerouteKe  + '</td>' +
                        '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                        '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                        "</tr>";
            });
            $('.sppdRerouteTable').append(tmp_table);

        });
    }

    window.detailThr = function(payrollId){
        PayrollAction.getDetailEditThr(payrollId, function(listdata){
            $('#payrollId').val(payrollId);
            $('#thrGaji').val(listdata.gajiGolongan);
            $('#thrTunjUmk').val(listdata.tunjanganUmk);
            $('#thrTunjStruktural').val(listdata.tunjanganStruktural);
            $('#thrTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
            $('#thrTunjStrategis').val(listdata.tunjanganStrategis);
            $('#thrTunjPph').val(listdata.tunjanganPph);
            $('#thrTotal').val(listdata.totalThr);
        });

        $('#modal-thr').find('.modal-title').text('Komposisi THR');
        $('#modal-thr').modal('show');
    };
    $('#printThr').click(function(){
        //var link = "/hris/payroll/printReportPayroll_payroll.action?id=" + item.payrollId + "&flag=Y";
        var payrollId = document.getElementById("payrollId").value;
        window.location.href = '/hris/payroll/printReportThr_payroll.action?id='+payrollId;
    });

    window.detailPendidikan = function(payrollId){
        PayrollAction.getDetailEditPendidikan(payrollId, function(listdata){
            $('#payrollId').val(payrollId);
            $('#pendidikanGaji').val(listdata.gaji);
            $('#pendidikanTunjUmk').val(listdata.tunjanganUmk);
            $('#pendidikanTunjStruktural').val(listdata.tunjanganStruktural);
            $('#pendidikanTunjJabStruktural').val(listdata.tunjanganJabatanStruktural);
            $('#pendidikanTunjStrategis').val(listdata.tunjanganStrategis);
            $('#pendidikanTunjAirListrik').val(listdata.tunjanganAirListrik);
            $('#pendidikanTunjKompensasi').val(listdata.tunjanganKompensasi);
            $('#pendidikanTunjPph').val(listdata.tunjanganPph);
            $('#pendidikanTotal').val(listdata.totalPendidikan);
        });

        $('#modal-pendidikan').find('.modal-title').text('Komposisi Pendidikan');
        $('#modal-pendidikan').modal('show');
    };
    $('#printPendidikan').click(function(){
        var payrollId = document.getElementById("payrollId").value;
        window.location.href = '/hris/payroll/printReportPendidikan_payroll?id='+payrollId;
    });

    window.detailJasprod = function(payrollId){
        PayrollAction.getDetailEditJasprod(payrollId, function(listdata){
            if(listdata != null){
                $('#payrollId').val(payrollId);
                $('#jasprodMasKer1').val(listdata.masaKerja);
                $('#jasprodGaji').val(listdata.gajiGolongan);
                $('#jasprodTunjUmk').val(listdata.tunjanganUmk);
                $('#jasprodTunjStruktural').val(listdata.tunjanganStruktural);
                $('#jasprodTunjPeralihan').val(listdata.tunjPeralihan);
                $('#jasprodTunjStrategis').val(listdata.tunjanganStrategis);

                $('#jasprodTotalBruto').val(listdata.gajiBruto);
                $('#jasprodPengali').val(listdata.gajiMasaKerja);
                $('#jasprodFaktor').val(listdata.stFaktor);
                $('#jasprodNilaiSmk').val(listdata.stNilaiSmk);
                $('#jasprodPersenSmk').val(listdata.stPersenSmk);
                $('#jasprodPerhitungan').val(listdata.perhitungan);
                $('#jasprodGajiKotor').val(listdata.gajiMasaKerjaFaktor);
                $('#jasprodTambahan').val(listdata.tambahan);
                $('#jasprodNilai').val(listdata.nilaiJasprod);
            }else{
                $('#jasprodGaji').val('0,00');
                $('#jasprodTunjUmk').val('0,00');
                $('#jasprodTunjStruktural').val('0,00');
                $('#jasprodTunjJabStruktural').val('0,00');
                $('#jasprodTunjStrategis').val('0,00');
            }

        });

        $('#modal-jasprod').find('.modal-title').text('Komposisi Jasprod');
        $('#modal-jasprod').modal('show');
    };
    $('.detailJasprodMasaKerja').on('click', function(){
        $('#modal-jasprod-masaKerja').find('.modal-title').text('Masa Kerja');
        $('#modal-jasprod-masaKerja').modal('show');
    });
    $('#printJasprod').click(function(){
        var payrollId = document.getElementById("payrollId").value;
        window.location.href = 'printReportJasprod_payroll?id='+payrollId;
    });

    window.loadDocSppd =  function(sppdId){
        //alert(branch);
        $('.sppdDocTable').find('tbody').remove();
        $('.sppdDocTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchDoc(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='/hris/pages/upload/file/sppd/" + item.fileUploadDoc + "' >" +
                        "<i class='fa fa-download' style='font-size:20px'></i>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' + item.note + '</td>' +
                        "</tr>";
            });
            $('.sppdDocTable').append(tmp_table);

        });
    }
</script>