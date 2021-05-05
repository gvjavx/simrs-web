<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SmkAction.js"/>'></script>

    <style>
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
            window.location.href="<s:url action='initForm_alat'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.clos = function() {
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="initForm_smk.action";
            };

            var smkId = document.getElementById("evaluasiPegawaiId").value;
            var nip             = document.getElementById("personName1").value;

            if(nip != "US"){
                SmkAction.updatePoinPrestasi(smkId, function(listdata){});
            }

            window.cekSmkClose = function(branch, positionId, periode){
                var hasil = false;
                var nip = document.getElementById("personName1").value;

                if(nip != "US"){
                    SmkAction.cekApprove(branch, positionId, periode, function(listdata){
                        hasil = listdata;
                    });
                }else{
                    hasil = true;
                }

                return hasil;
            }

            $.subscribe('beforeProcessSave', function (event, data) {
                var nip             = document.getElementById("personName1").value;
                var periode         = document.getElementById("periodeId").value;
                var positionId      = document.getElementById("positionId").value;
                var branchId        = document.getElementById("branchId").value;
                var close           = document.getElementById("close").value;

                if (nip != '' && periode != '') {
                    //Close
                    if(close == 'Y'){
                        if(close != ''){
                            if(cekSmkClose(branchId, positionId, periode) == true){
                                if (confirm('Do you want to save this record?')) {
                                    event.originalEvent.options.submit = true;
                                    $.publish('showDialog');
                                } else {
                                    event.originalEvent.options.submit = false;
                                }
                            }else{
                                alert('Atasan harus diclose terlebih dahulu');
                                event.originalEvent.options.submit = false;
                            }
                        }else{
                            if (confirm('Do you want to save this record?')) {
                                event.originalEvent.options.submit = true;
                                $.publish('showDialog');
                            } else {
                                event.originalEvent.options.submit = false;
                            }
                        }
                    }
                    //Draft
                    else{
                        if (confirm('Do you want to save this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        } else {
                            event.originalEvent.options.submit = false;
                        }
                    }

                } else {

                    event.originalEvent.options.submit = false;

                    var msg = "";

                    if (nip == '') {
                        msg += 'Field <strong>Nip</strong> is required.' + '<br/>';
                    }

                    if (periode == '') {
                        msg += 'Field <strong>Periode</strong> is required.' + '<br/>';
                    }



                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });
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
            Edit Evaluasi Pegawai (SMK)
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/smk" action="saveEdit_smk.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>

                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >
                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="evaluasiPegawaiId" name="smk.evaluasiPegawaiId" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Periode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="%{smk.editNilai}">
                                            <s:select list="#{'2017':'2017', '2018':'2018', '2019':'2019'}" id="periodeId" name="smk.periode"
                                                      headerKey="" headerValue="[Pilih Tahun]" cssClass="form-control" />
                                        </s:if>
                                        <s:else>
                                            <s:select disabled="true" list="#{'2017':'2017', '2018':'2018', '2019':'2019'}" id="periodeId" name="smk.periode"
                                                      headerKey="" headerValue="[Pilih Tahun]" cssClass="form-control" />
                                        </s:else>
                                    </table>
                                </td>
                            </tr>

                            <tr id="usaha" style="display:none;">
                                <td>
                                    <label class="control-label"><small>Nilai Unit Usaha:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssStyle="" id="unitUsaha" name="smk.unitUsaha" required="false" readonly="false" cssClass="form-control"/>
                                        <%--<input type="text" id="unitUsaha" name="smk.unitUsaha" class="form-control">--%>
                                    </table>
                                </td>
                            </tr>

                            <tr id="nip1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Nip :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield readOnly="true" cssStyle="" id="personName1" name="smk.nip" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="nama1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="personName" name="smk.pegawaiName" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="smk.branchId"
                                                  readonly="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr id="jabatan1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="smk.positionId"
                                                  readonly="true"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr id="bagian1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Divisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smk.divisiId" readonly="true"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr id="golongan1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                        <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="smk.golonganId" readOnly="true"
                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr id="point1" style="display: none">
                                <td>
                                    <label class="control-label"><small>Point :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="point" name="smk.poin" readonly="true" cssClass="form-control"/>
                                        <s:textfield id="kelompokId" name="smk.kelompokId" cssStyle="display: none" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr id="masaKerjaGolongan" style="display: none">
                                <td>
                                    <label class="control-label"><small>Masa Kerja Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="mkgId" name="smk.strMasaKerjaGolongan" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Status Pegawai :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="statusPegawai" name="smk.statusPegawai" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Nilai Revisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield value="0" id="nilaiRevisi" name="smk.nilaiRevisi"
                                                     cssClass="nilaiRevisi form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Action :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="%{smk.editNilai}">
                                            <s:select list="#{'D':'Draft', 'Y':'Close'}" id="close" name="smk.closed"
                                                      headerKey="" headerValue="Select" cssClass="form-control" />
                                        </s:if>
                                        <s:else>
                                            <s:select disabled="true" list="#{'D':'Draft', 'Y':'Close'}" id="close" name="smk.closed"
                                                      headerKey="" headerValue="Select" cssClass="form-control" />
                                        </s:else>
                                        <s:textfield cssStyle="display: none" id="nipLogin" name="smk.nipLogin" readonly="true" cssClass="form-control"/>

                                    </table>
                                </td>
                                <td>
                                    <s:if test="%{smk.editTarget}">
                                        <s:if test="%{smk.editNilai}">
                                            <a id="btnUbahNilai" data-toggle="tooltip" title="Digunakan untuk merubah total nilai SMK yang nantinya akan terupdate pada poin pegawai"
                                               type="button" class="btn btn-primary">Ubah Nilai</a>
                                        </s:if>
                                    </s:if>
                                    <%--<button type="button" class="btn btn-info" onclick="">
                                        <i class="fa fa-refresh">Refresh Nilai</i>
                                    </button>--%>
                                    <a type="button" id="btnRefreshNilai" class="btn btn-info">Refresh Nilai</a>
                                </td>
                            </tr>

                            <tr id="poinBaru">
                                <td>
                                    <label class="control-label"><small>Point Baru:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield name="smk.pointBaru" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>

                        <br>
                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="70%">
                                <tr>
                                    <td align="center">
                                        <h4 id="labelA" style="text-align: left; display: none">A. Aspek Tugas dan Sasaran Kerja</h4>
                                        <table style="width: 100%;" class="tableAspekA table table-bordered">
                                        </table>
                                        <h5 style="text-align: right; display: none" id="nilaiTurunan">Nilai Turunan : </h5>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <br>
                            <table width="70%">
                                <tr>
                                    <td align="center">
                                        <h4 id="labelB" style="text-align: left; display:none">B. Aspek Perilaku kerja</h4>
                                        <table style="width: 100%;" class="tableAspekB1 table table-bordered">
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <br>
                            <table width="70%" id="labelAspekC">
                                <tr>
                                    <td align="center">
                                        <h4 id="labelC" style="text-align: left; display: none">C. Aspek Kepemimpinan dan Manajerial</h4>
                                        <table style="width: 100%;" class="tableAspekC table table-bordered">
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <s:if test="%{smk.editNilai}">
                            </s:if>
                            <s:else>
                                <br>
                                <br>
                                <table style="width: 20%;" class="tableHistory table table-bordered">
                                </table>
                            </s:else>


                        </center>

                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <s:if test="%{smk.editNilai}">
                                        <td>
                                            <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="sppdForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-check"></i>
                                                Save
                                            </sj:submit>
                                        </td>
                                    </s:if>
                                    <td style="display:none;">
                                        <button type="button" class="btn btn-warning" onclick="applySmk()">
                                            <i class="fa fa-check-square-o"></i> Apply
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="cancelSmk()">
                                            <i class="fa fa-close"></i> Cancel
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>


                        <div id="actions" class="form-actions">
                            <table>
                                <tr>
                                    <div id="crud">
                                        <td>
                                            <table>
                                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false" title="Saving ...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                              'OK':function() {
                                                                      clos();
                                                                   }
                                                            }"
                                                >
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                    Record has been saved successfully.
                                                </sj:dialog>

                                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                           height="250" width="600" autoOpen="false" title="Error Dialog"
                                                           buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                        </label>
                                                    </div>
                                                </sj:dialog>

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
<div id="modal-nilai" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:350px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-8" >Total Nilai Sekarang</label>
                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="nilaiSekarang" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-8" >Klasifikasi Nilai Prestasi</label>
                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="nilaiKlasifikasiSekarang" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-8" >Total Nilai Baru</label>
                        <div class="col-sm-4">
                            <input style="text-align: right" type="text" class="form-control nip" onkeyup="checkDec(this);" id="nilaiBaru" onchange="getKlasifikasiNilai(this)">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-8" >Klasifikasi Nilai Baru</label>
                        <div class="col-sm-4">
                            <input style="text-align: right" readonly type="text" class="form-control nip" id="nilaiKlasifikasiBaru" name="nip">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-success" id="btnSaveNilai">Simpan</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
</body>

</html>

<script>

    $(document).ready(function() {
        var nip = document.getElementById("personName1").value;
        var nipLogin = document.getElementById("nipLogin").value;
        var close = document.getElementById("close").value;
        var statusPegawai = document.getElementById("statusPegawai").value;
        var kelompokId  = document.getElementById("kelompokId").value;
        var positionId  = document.getElementById("positionId").value;
        var unitId  = document.getElementById("branchId").value;
        var smkId  = document.getElementById("evaluasiPegawaiId").value;

        if(kelompokId != ''){
            if(kelompokId == 'KL02' || kelompokId == 'KL03' || kelompokId == 'KL04' || kelompokId == 'KL05'){
                var nilai = 0;
                SmkAction.getNilaiShare(smkId, unitId, positionId, function(listdata) {
                    nilai = listdata;
                });

                $('#nilaiTurunan').empty();
                $('#nilaiTurunan').append("Nilai Share : " + toFixed(nilai, 2));
                $('#nilaiTurunan').show();
            }else{
                $('#nilaiTurunan').hide();
            }
        }

        if(nip == nipLogin){
            $('#btnUbahNilai').hide;
        }

        if(close == 'Y'){
            $('#poinBaru').show();
        }else{
            $('#poinBaru').hide();
        }

        if(nip != 'US'){
            tableAspekA();

            //KS
            if(statusPegawai == 'KS'){
                tableAspekB1();
                tableAspekC();
                $('#labelAspekC').show();
            }
            //KNS
            else{
                tableAspekB1_KNS();
                $('#labelAspekC').hide();
            }

            loadHistory();
            loadHistory();

            $('#personName2').prop("disabled", false);
            $('#usaha').hide();

            $('#nip1').show();
            $('#nama1').show();
            $('#unit1').show();
            $('#bagian1').show();
            $('#jabatan1').show();
            $('#golongan1').show();
            $('#point1').show();
            $('#masaKerjaGolongan').show();
            $('#tipePegawai2').show();
            $('#statusPegawai1').show();

            $('#btnAddSubTipe').show();
            $('#labelA').show();
            $('#labelB').show();
            $('#labelC').show();
            $('#labelHistory').show();
        }else{
            $('#labelA').hide();
            $('#labelB').hide();
            $('#labelC').hide();
            $('#labelHistory').hide();

            $('#personName2').prop("disabled", true);
            $('#usaha').show();
            $('#nip1').hide();

            $('#nama1').hide();
            $('#unit1').show();
            $('#bagian1').hide();
            $('#jabatan1').hide();
            $('#golongan1').hide();
            $('#point1').hide();
            $('#masaKerjaGolongan').hide();
            $('#tipePegawai2').hide();
            $('#statusPegawai1').hide();
        }

        $('#btnUbahNilai').click(function(){
            var smkId = document.getElementById("evaluasiPegawaiId").value;
            var nilai = 0;
            var nilaiPrestasi = '';

            SmkAction.getNilaiPrestasi(smkId, function(listdata) {
                nilai = listdata.nilai;
                nilaiPrestasi = listdata.nilaiPrestasi;
            });

            $('#nilaiSekarang').val(nilai);
            $('#nilaiKlasifikasiSekarang').val(nilaiPrestasi);

            $('#modal-nilai').find('.modal-title').text('Ubah Total Nilai');
            $('#modal-nilai').modal('show');
        });

        $('#btnRefreshNilai').click(function(){

            try{
                tableAspekA()
                alert('Nilai Aspek A berhasil direfresh');
                location.reload();
            }catch(Err){
                alert('Terjadi kesalahan ' + Err);
                location.reload();
            }
        });

        $('#btnSaveNilai').click(function(){
            var nilaiBaru = document.getElementById("nilaiBaru").value;

            $('.nilaiRevisi').val(nilaiBaru);
            alert('Nilai Berhasil Dirubah');
            $('#modal-nilai').modal('hide');
        });

    });

    window.checkDec = function(el){
        var ex = /^[0-9]+\.?[0-9]*$/;
        if(ex.test(el.value)==false){
            el.value = el.value.substring(0,el.value.length - 1);
        }
    }

    window.cancelSmk = function(){
        if (confirm('Apakah anda yakin untuk batalkan proses?')) {
            SmkAction.cancelSmk(function(listdata){});
            window.location.href='initForm_smk.action';
        }
    }

    window.applySmk = function(){
        var evaluasiPegawaiId = document.getElementById("evaluasiPegawaiId").value;
        var nip         = document.getElementById("personName1").value ;
        var branchId    = document.getElementById("branchId").value;;
        var periode     = document.getElementById("periodeId").value;
        var positionId  = document.getElementById("positionId").value;

        if (confirm('Apakah anda yakin untuk apply nilai SMK?')) {
            //alert(evaluasiPegawaiId + ' - ' + nip + ' - ' + branchId + ' - ' + periode + ' - ' + positionId);
            SmkAction.applySmk(evaluasiPegawaiId, nip, branchId, periode, positionId, function(listdata) {});
            //window.location.href = 'applySmk_smk?id='++'&nip='+nip+'&branchId='+branchId+'&positionId='+positionId+'&periode='+periode;
        }
    }
    
    window.loadHistory = function(){
        var nip         = document.getElementById("personName1").value;

        $('.tableHistory').find('tbody').remove();
        $('.tableHistory').find('thead').remove();
        var tableHistory = "";
        SmkAction.showDataAspekHistory(nip, function(listdata) {
            tableHistory = "<thead style='color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Prestasi</th>"+
                    "</tr></thead>";
            var i = i ;
            var jumlahBobot = 0 ;
            $.each(listdata, function (i, item) {
                tableHistory += '<tr >' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.periode+ '</td>' +
                        '<td >' + item.nilaiPrestasi+ '</td>' +
                        "</tr>";
                jumlahBobot += item.bobot;
            });
            $('.tableHistory').append(tableHistory);
        });
    }
    
    window.loadAspek = function(){
        $("#showdata").show();
    }

    window.tableAspekA = function(){
        $('.tableAspekA').find('tbody').remove();
        $('.tableAspekA').find('thead').remove();
        $('.tableAspekA').find('tfoot').remove();

        var smkId = document.getElementById("evaluasiPegawaiId").value;
        var nip         = document.getElementById("personName1").value;
        var periode         = document.getElementById("periodeId").value;
        var unit = document.getElementById("branchId").value;
        var jabatan = document.getElementById("positionId").value;

        SmkAction.getAspekA(nip, unit, jabatan, periode, smkId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    /*"<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+*/
                    "</tr></thead>";
            var x = 1 ;
            var jumlahBobot = 0;
            var jumlahPrestasi = 0;
            var pegawaiAspekId = '';


            $.each(listdata, function (i, item) {
                var edit = "";
                var cek = "";
                if(item.indikatorKinerjaAtasan == "N"){
                    edit = "<a href='editAspekA_smk?id="+item.evaluasiPegawaiAspekDetailId+"&checklist="+item.subAspekA+"&bobot="+item.bobot+"' class ='item-editDocument'>"
                            + "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"
                            + "</a>";
                }
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        edit +
                        '</td>' +
                        '<td align="center">' + x + '</td>' +
                        '<td >' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + toFixed(item.bobot, 2)+ ' %</td>' +
                        '<td align="center">' + item.target+ '</td>' +
                        '<td align="center">' + item.realisasi+ '</td>' +
                        '<td align="center">' + item.nilai.toFixed(2)+ '</td>' +
                        '<td align="center">' + toFixed(item.nilaiPrestasiItem, 2) + '</td>' +
                        /*'<td align="center"></td>' +
                        '<td align="center"></td>' +*/
                        "</tr>";
                x++;
                jumlahBobot += item.bobot;
                jumlahPrestasi += item.nilaiPrestasiItem;
                pegawaiAspekId = item.evaluasiPegawaiAspekId ;
            });
            tmp_table += '<tfoot >' +
                    '<tr style="font-size: 12px;">' +
                    '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                    '<td style="text-align: center"> '+toFixed(jumlahBobot, 2)+' %</td>' +
                    '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                    '<td style="text-align: center">'+ toFixed(jumlahPrestasi, 2) +'</td>' +
                    '</tr>' +
                    '</tfoot>';
            SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {});
            $('.tableAspekA').append(tmp_table);
        });
    }

    window.tableAspekB1 = function(){
        $('.tableAspekB1').find('tbody').remove();
        $('.tableAspekB1').find('thead').remove();
        $('.tableAspekB1').find('tfoot').remove();
        var nip         = document.getElementById("personName1").value;
        var periode     = document.getElementById("periodeId").value;
        var smkId = document.getElementById("evaluasiPegawaiId").value;

        var varB = "B1";
        SmkAction.getAspek(nip, periode, varB, smkId, function(listdata) {
            if(listdata.length == 0){
                varB = "B2";
            }
        });

        SmkAction.getAspek(nip, periode, varB, smkId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    /*"<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                         "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+*/
                    "</tr></thead>";
            if(listdata.length > 0){
                var x = 1 ;
                var jumlahBobot = 0;
                var jumlahPrestasi = 0;
                var pegawaiAspekId = '';
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' +
                            "<a href='editAspek_smk?id="+item.evaluasiPegawaiAspekDetailId+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>' +
                            '<td align="center">' + x + '</td>' +
                            '<td >' + item.indikatorKinerja + '</td>' +
                            '<td align="center">' + toFixed(item.bobot, 2)+ ' %</td>' +
                            '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                            '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                            '<td align="center">' + toFixed(item.nilai, 2) + '</td>' +
                            '<td align="center">' + toFixed(item.nilaiPrestasiItem, 2)+ '</td>' +
                            "</tr>";
                    x++;
                    jumlahBobot += item.bobot;
                    jumlahPrestasi += item.nilaiPrestasiItem;
                    pegawaiAspekId = item.evaluasiPegawaiAspekId ;
                });
                tmp_table += '<tfoot >' +
                        '<tr style="font-size: 12px;">' +
                        '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                        '<td style="text-align: center"> '+toFixed(jumlahBobot, 2)+' %</td>' +
                        '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                        '<td style="text-align: center">'+toFixed(jumlahPrestasi, 2)+'</td>' +
                        '</tr>' +
                        '</tfoot>';
                SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {
                });
            }

            $('.tableAspekB1').append(tmp_table);
        });
    }

    window.tableAspekB1_KNS = function(){
        $('.tableAspekB1').find('tbody').remove();
        $('.tableAspekB1').find('thead').remove();
        $('.tableAspekB1').find('tfoot').remove();
        var nip         = document.getElementById("personName1").value;
        var periode     = document.getElementById("periodeId").value;
        var smkId = document.getElementById("evaluasiPegawaiId").value;

        var varB = "B1";
        SmkAction.getAspek(nip, periode, varB, smkId, function(listdata) {
            if(listdata.length == 0){
                varB = "B2";
            }
        });

        var nilai = 0;
        var nilaiPrestasi = '';
        SmkAction.getNilaiPrestasi(smkId, function(listdata) {
            nilai = listdata.nilai;
            nilaiPrestasi = listdata.nilaiPrestasi;
        });

        SmkAction.getAspek(nip, periode, varB, smkId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    /*"<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                         "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+*/
                    "</tr></thead>";
            if(listdata.length > 0){
                var x = 1 ;
                var jumlahBobot = 0;
                var jumlahPrestasi = 0;
                var pegawaiAspekId = '';
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' +
                            "<a href='editAspek_smk?id="+item.evaluasiPegawaiAspekDetailId+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>' +
                            '<td align="center">' + x + '</td>' +
                            '<td >' + item.indikatorKinerja + '</td>' +
                            '<td align="center">' + toFixed(item.bobot, 2)+ ' %</td>' +
                            '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                            '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                            '<td align="center">' + toFixed(item.nilai, 2)+ '</td>' +
                            '<td align="center">' + toFixed(item.nilaiPrestasiItem, 2)+ '</td>' +
                            "</tr>";
                    x++;
                    jumlahBobot += item.bobot;
                    jumlahPrestasi += item.nilaiPrestasiItem;
                    pegawaiAspekId = item.evaluasiPegawaiAspekId ;
                });
                tmp_table += '<tfoot >' +
                        '<tr style="font-size: 12px;">' +
                            '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                            '<td style="text-align: center"> '+toFixed(jumlahBobot, 2)+' %</td>' +
                            '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                            '<td style="text-align: center">'+toFixed(jumlahPrestasi.toFixed(2), 2)+'</td>' +
                        '</tr>' +
                        '<tr style="font-size: 12px;">' +
                            '<td align="right" colspan="7">Total Nilai Prestasi</td>' +
                            '<td align="center">'+nilai.toFixed(2)+'</td>' +
                        '</tr>' +
                            '<tr style="font-size: 12px;">' +
                            '<td align="right" colspan="7">Klasifikasi Nilai Prestasi</td>' +
                            '<td align="center">'+nilaiPrestasi+'</td>' +
                        '</tr>' +

                        '</tfoot>';
                SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {
                });
            }

            $('.tableAspekB1').append(tmp_table);
        });
    }

    window.tableAspekB2 = function(){
        $('.tableAspekB2').find('tbody').remove();
        $('.tableAspekB2').find('thead').remove();
        $('.tableAspekB2').find('tfoot').remove();
        var nip         = document.getElementById("personName1").value;
        var periode     = document.getElementById("periodeId").value;
        var smkId = document.getElementById("evaluasiPegawaiId").value;

        SmkAction.getAspek(nip, periode, "B2", smkId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    /*"<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+*/
                    "</tr></thead>";
            if(listdata.length > 0){
                var x = 1 ;
                var jumlahBobot = 0;
                var jumlahPrestasi = 0;
                var pegawaiAspekId = '';
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' +
                            "<a href='editAspek_smk?id="+item.evaluasiPegawaiAspekDetailId+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>' +
                            '<td align="center">' + x + '</td>' +
                            '<td >' + item.indikatorKinerja + '</td>' +
                            '<td align="center">' + toFixed(item.bobot, 2)+ ' %</td>' +
                            '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                            '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                            '<td align="center">' + toFixed(item.nilai, 2)+ '</td>' +
                            '<td align="center">' + toFixed(item.nilaiPrestasiItem, 2)+ '</td>' +
                            /*'<td align="center"></td>' +
                            '<td align="center"></td>' +*/
                            "</tr>";
                    x++;
                    jumlahBobot += item.bobot;
                    jumlahPrestasi += item.nilaiPrestasiItem;
                    pegawaiAspekId = item.evaluasiPegawaiAspekId ;
                });
                tmp_table += '<tfoot >' +
                        '<tr style="font-size: 12px;">' +
                        '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                        '<td style="text-align: center"> '+toFixed(jumlahBobot, 2)+' %</td>' +
                        '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                        '<td style="text-align: center">'+toFixed(jumlahPrestasi, 2)+'</td>' +
                        '</tr>' +
                        '</tfoot>';
                SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {
                });
            }

            $('.tableAspekB2').append(tmp_table);
        });
    }

    window.tableAspekC = function(){
        $('.tableAspekC').find('tbody').remove();
        $('.tableAspekC').find('thead').remove();
        $('.tableAspekC').find('tfoot').remove();

        var nip         = document.getElementById("personName1").value;
        var periode     = document.getElementById("periodeId").value;
        var smkId = document.getElementById("evaluasiPegawaiId").value;

        var jumlahBobot = 0;
        var jumlahPrestasi = 0;
        var pegawaiAspekId = '';

        var nilai = 0;
        var nilaiPrestasi = '';
        SmkAction.getNilaiPrestasi(smkId, function(listdata) {
            nilai = listdata.nilai;
            nilaiPrestasi = listdata.nilaiPrestasi;
        });

        SmkAction.getAspek(nip, periode, "C", smkId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Uraian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bobot(B)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Target</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Realisasi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai (N)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai Prestasi(BxN)</th>"+
                    /*"<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Note</th>"+*/
                    "</tr></thead>";
            var x = 1 ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' +
                        "<a href='editAspek_smk?id="+item.evaluasiPegawaiAspekDetailId+"&bobot="+item.bobot+"' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' + x + '</td>' +
                        '<td >' + item.indikatorKinerja + '</td>' +
                        '<td align="center">' + toFixed(item.bobot, 2)+ ' %</td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td style="text-align: center; background-color:  #3c8dbc"></td>' +
                        '<td align="center">' + toFixed(item.nilai, 2)+ '</td>' +
                        '<td align="center">' + toFixed(item.nilaiPrestasiItem, 2)+ '</td>' +
                        /*'<td align="center"></td>' +
                        '<td align="center"></td>' +*/
                        "</tr>";
                x++;
                jumlahBobot += item.bobot;
                jumlahPrestasi += item.nilaiPrestasiItem;
                pegawaiAspekId = item.evaluasiPegawaiAspekId ;
            });
            tmp_table += '<tfoot >' +
                    '<tr style="font-size: 12px;">' +
                        '<td colspan = "3" style="text-align: center"> Jumlah Bobot </td>' +
                        '<td style="text-align: center"> '+toFixed(jumlahBobot, 2)+' %</td>' +
                        '<td style="text-align: center" colspan="3"> Jumlah Nilai Prestasi</td>' +
                        '<td style="text-align: center">'+jumlahPrestasi.toFixed(2)+'</td>' +
                    '</tr>' +
                    '<tr style="font-size: 12px;">' +
                        '<td align="right" colspan="7">Total Nilai Prestasi</td>' +
                        '<td align="center">'+toFixed(nilai, 2)+'</td>' +
                    '</tr>' +
                    '<tr style="font-size: 12px;">' +
                        '<td align="right" colspan="7">Klasifikasi Nilai Prestasi</td>' +
                        '<td align="center">'+nilaiPrestasi+'</td>' +
                    '</tr>' +
                    '</tfoot>';
            $('.tableAspekC').append(tmp_table);
            SmkAction.updateNilaiPrestasi(pegawaiAspekId, jumlahPrestasi, function(listdata) {
            });
        });
    }

    window.getKlasifikasiNilai = function(nilai){
        var nilai = document.getElementById("nilaiBaru").value;
        var nilaiPrestasi = '';

        if(parseInt(nilai) > 0){
            SmkAction.getKlasifikasiNilai(nilai, function(listdata) {
                nilaiPrestasi = listdata;
            });
        }else{
            nilaiPrestasi = '';
        }
        $('#nilaiKlasifikasiBaru').val(nilaiPrestasi);
    }

    function toFixed(number, decimals) {
        var x = Math.pow(10, Number(decimals) + 1);
        return (Number(number) + (1 / x)).toFixed(decimals)
    }
</script>




