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
    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <%--<link href="<s:url value="/pages/plugins/tab/tab.css"/>" rel="stylesheet" media="screen">--%>
    <style>
        .checkZakat {width: 20px; height: 20px;}
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
            window.location.href="<s:url action='search_biodata.action'/>";
        }

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StudyAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KeluargaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StudyJurusanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DepartmentAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/GolonganAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>


    <script type="text/javascript">

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var nip                 = document.getElementById("nip1").value;
            var namaPegawai         = document.getElementById("namaPegawai1").value;
            var noKtp               = document.getElementById("noKtp1").value;
            var tempatLahir         = document.getElementById("tempatLahir1").value;
            var tanggalLahir        = document.getElementById("tanggalLahir1").value;
            var tipePegawai         = document.getElementById("tipePegawai1").value;
            var branch              = document.getElementById("branch1").value;
            var divisi              = document.getElementById("divisi1").value;
            var golongan            = document.getElementById("golongan1").value;
            var statusPegawai       = document.getElementById("statusPegawai1").value;
            var flag                = document.getElementById("flagAktif").value;
            var shift               = document.getElementById("shift").value;
            var tglMasuk            = document.getElementById("tanggalMasuk").value;
            var msKerjaGol          = document.getElementById("poinLebih").value;

            if(tipePegawai == "TP03") { //PEG. TETAP
                var level               = document.getElementById("golongan1").value;
            } else {
                var level               = document.getElementById("golongan3").value;
            }

            if (statusPegawai != '' && nip != '' && namaPegawai != '' && noKtp != '' && tempatLahir != '' && tipePegawai != '' && tanggalLahir != '' && branch != '' && tglMasuk !='' && level !='') {
                if (confirm('Do you want to save this record?')) {
                    alert("Pegawai akan secara otomatis dibuatka Akun User!" +
                        "\n\tUSER ID : " + nip + "\n\tPASSWORD: 123");
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            } else {
                event.originalEvent.options.submit = false;

                var msg = "";

                if (nip == '') {
                    msg += 'Field <strong>NIP</strong> is required.' + '<br/>';
                }
                if (namaPegawai == '') {
                    msg += 'Field <strong>Nama </strong> is required.' + '<br/>';
                    $("#namaPegawai1").css("background","lightcoral");
                }
                if (noKtp == '') {
                    msg += 'Field <strong>No KTP</strong> is required.' + '<br/>';
                    $("#noKtp1").css("background","lightcoral");
                }
                if (tanggalLahir == '') {
                    msg += 'Field <strong>Tanggal Lahir</strong> is required.' + '<br/>';
                    $("#tanggalLahir1").css("background","lightcoral");
                }
                if (tempatLahir == '') {
                    msg += 'Field <strong>Tempat Lahir</strong> is required.' + '<br/>';
                    $("#tempatLahir1").css("background","lightcoral");
                }
                if (tipePegawai == '') {
                    msg += 'Field <strong>Tipe Pegawai</strong> is required.' + '<br/>';
                    $("#tipePegawai1").css("background","lightcoral");
                }
                if (level == '') {
                    msg += 'Field <strong>Level</strong> is required.' + '<br/>';
                }
                if (branch == '') {
                    msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                    $("#branch1").css("background","lightcoral");
                }
                /*if (divisi == '') {
                 msg += 'Field <strong>divisi</strong> is required.' + '<br/>';
                 }*/
                if (statusPegawai == '') {
                    msg += 'Field <strong>Status Pegawai</strong> is required.' + '<br/>';
                    $("#statusPegawai1").css("background","lightcoral");
                }
                if (tglMasuk == '') {
                    msg += 'Field <strong>Tanggal Masuk / Kontrak</strong> is required.' + '<br/>';
                    $("#tanggalMasuk").css("background","lightcoral");
                }
                if (level == '') {
                    msg += 'Field <strong>Level</strong> is required.' + '<br/>';
                    $("#golongan1").css("background","lightcoral");
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('beforeProcessSaveStudy2', function (event, data){
            if (confirm('Do you want to save this record?')){
                event.originalEvent.options.submit = true;
                $('#modal-edit').modal('hide');
                $('#myFormDocument')[0].reset();
                alert('Record has been Saved successfully.');
                var nip = document.getElementById("nip1").value;
                loadStudy(nip);
            } else{
                event.originalEvent.options.submit = false;
            }
        });

        $.subscribe('successDialogDocument2', function(event, data){
            var nip = document.getElementById("nip1").value;
            loadStudy(nip);
        });

        $.subscribe('beforeProcessSaveStudy1', function (event, data){
            if (confirm('Do you want to save this record?')){
                event.originalEvent.options.submit = true;
                $('#modal-edit-study').modal('hide');
                $('#myFormDocument1')[0].reset();
                alert('Record has been Saved successfully.');
//                var nip = document.getElementById("nip1").value;
//                loadSessionStudy();
//                loadStudy(nip);
            } else{
                event.originalEvent.options.submit = false;
            }
        });

        $.subscribe('successDialogDocument1', function(event, data){
//            var nip = document.getElementById("nip1").value;
//                loadSessionStudy();
//            loadStudy(nip);
            <s:if test="isAdd()">
            loadSessionStudy();
            </s:if>
            <s:else>
            var nip = document.getElementById("nip1").value;
            loadStudy(nip);
            </s:else>
        });

        $.subscribe('beforeProcessSaveStudy', function (event, data){
            var studyName = document.getElementById("studyName").value;
            var programStudy = document.getElementById("pendidikanProgramStudi").value;
            var tahunAwal = document.getElementById("studyTahunAwal").value;
            var tahunAkhir = document.getElementById("studyTahunAkhir").value;
            dwr.engine.setAsync(false);
            if( studyName != ''|| programStudy != ''|| tahunAwal != ''|| tahunAkhir !=''){
                if (confirm('Do you want to save this record?')){
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                    $('#modal-edit').modal('hide');
                    $('#myFormDocument')[0].reset();
                    alert('Record has been Saved successfully.');
//                loadSessionStudy();

                    <s:if test="isAdd()">
                    loadSessionStudy();
                    </s:if>
                    <s:else>
                    var nip = document.getElementById("nip1").value;
                    loadStudy(nip);
                    </s:else>
                } else{
                    event.originalEvent.options.submit = false;
                }
            } else{
                event.originalEvent.options.submit = false;
                var msg = "";
                if (studyName == '') {
                    msg += 'Field <strong>Study Name</strong> is required.' + '<br/>';
                }
                if (programStudy == '') {
                    msg += 'Field <strong>Program Studi</strong> is required.' + '<br/>';
                }
                if (tahunAwal == '') {
                    msg += 'Field <strong>Tahun Awal</strong> is required.' + '<br/>';
                }
                if (tahunAkhir == '') {
                    msg += 'Field <strong>Tahun Akhir</strong> is required.' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $("#modal-edit").modal('hide');
                $.publish('showErrorValidationDialog');
            }
        });

        $.subscribe('successDialogDocument', function(event, data){
//            loadSessionStudy();
            <s:if test="isAdd()">
            loadSessionStudy();
            </s:if>
            <s:else>
            var nip = document.getElementById("nip1").value;
            loadStudy(nip);
            </s:else>
        });

        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>
    <style>
        .box-shadowed {
            background-color: #FFF;
            border-radius: 10px;
            box-shadow: grey 3px 3px 5px;
            padding: 30px;
        }
    </style>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Biodata Form
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">

            <div class="col-md-12">

                <ul class="nav nav-tabs" style="font-size: 16px;">
                    <li class="active"><a href="#biodata">Biodata</a></li>
                    <li><a tes = 'work' href="#keluarga">Keluarga</a></li>
                    <li><a href="#RiwayatPendidikan">Riwayat Pendidikan</a></li>
                    <li><a href="#pelatihanJabatan">Riwayat Pelatihan</a></li>

                    <li><a href="#pengalamanKerja">Jabatan & Riwayat</a></li>
                    <%--<li><a href="#reward">Reward</a></li>
                    <li><a href="#sertifikat">Sertifikat</a></li>--%>

                </ul>

                <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                <%--height="500" width="900" autoOpen="false"--%>
                <%--title="keluarga ">--%>
                <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                <%--</sj:dialog>--%>

                <s:form id="homeForm" theme="simple" namespace="/biodata" enctype="multipart/form-data" action="save_biodata.action" cssClass="well form-horizontal">
                    <s:hidden name="addOrEdit"/>
                    <s:hidden id="add" name="add"/>
                    <s:hidden name="delete"/>

                    <div class="tab-content well box-shadowed" >
                        <div id="biodata" class="tab-pane fade in active">
                            <h3>Biodata Karyawan Kantor</h3>
                            <br>
                            <div class="row">
                                <div class="col-md-12" style="text-align: center;">
                                    <img  align="center" width="300px" id="detailImg" style="border-radius: 50%; box-shadow: grey 3px 3px 5px;"
                                          src="" alt="">
                                    <s:textfield cssStyle="display: none" id="pathFoto" name="biodata.pathFoto" required="true" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <div id="panel-biodata">
                                <div class="row">
                                    <div class="col-md-6">
                                        <table style="width:100%;">
                                            <tr>
                                                <td>
                                                    <label><small>NIP <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--RAKA-09JAN2021 ==> generate NIP berdasarkan tanggal lahir (SEMENTARA TIDAK DIGUNAKAN)--%>
                                                            <%--<s:if test="isAdd()">--%>
                                                                <%--<s:textfield  id="nip1" name="biodata.nip" required="true" cssClass="form-control"/>--%>
                                                            <%--</s:if>--%>
                                                            <%--<s:elseif test="isDelete()">--%>
                                                                <%--<s:textfield  id="nip1" name="biodata.nip" required="true" readonly="true" cssClass="form-control"/>--%>
                                                            <%--</s:elseif>--%>
                                                            <%--<s:else>--%>
                                                                <s:textfield  id="nip1" name="biodata.nip" required="true" readonly="true" cssClass="form-control"/>
                                                            <%--</s:else>--%>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Nama  <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="namaPegawai1" name="biodata.namaPegawai" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="namaPegawai1" name="biodata.namaPegawai" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Gender <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#{'L':'Laki laki', 'P' : 'Perempuan'}" id="gender" name="biodata.gender"
                                                                      cssClass="form-control" disabled="true" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#{'L':'Laki laki', 'P' : 'Perempuan'}" id="gender" name="biodata.gender"
                                                                      cssClass="form-control" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Agama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#{'islam':'Islam', 'kristen' : 'Kristen', 'katolik' : 'Katolik', 'hindu' : 'Hindu',
                                            'budha' : 'Buddha', 'kong hu cu' : 'Kong Hu Cu'}" id="agama" name="biodata.agama"
                                                                      cssClass="form-control" disabled="true" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#{'islam':'Islam', 'kristen' : 'Kristen', 'katolik' : 'Katolik', 'hindu' : 'Hindu',
                                            'budha' : 'Buddha', 'kong hu cu' : 'Kong Hu Cu'}" id="agama" name="biodata.agama"
                                                                      cssClass="form-control" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Status Keluarga <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#{'K':'Keluarga', 'B' : 'Belum'}" id="statusKawin" name="biodata.statusKeluarga"
                                                                      cssClass="form-control" disabled="true" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#{'K':'Keluarga', 'B' : 'Belum'}" id="statusKawin" name="biodata.statusKeluarga"
                                                                      cssClass="form-control" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <s:if test="isDelete()">
                                                <tr>
                                                    <td>
                                                        <label><small>Jumlah Anak :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:textfield type="number" id="jumlahAnak" name="biodata.jumlahAnak" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <s:if test="isEdit()">
                                                <tr>
                                                    <td>
                                                        <label><small>Jumlah Anak :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:textfield type="number" id="jumlahAnak" name="biodata.jumlahAnak" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <tr>
                                                <td>
                                                    <label><small>Tempat Lahir <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="tempatLahir1" name="biodata.tempatLahir" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="tempatLahir1" name="biodata.tempatLahir" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tanggal Lahir <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield cssStyle="text-align: left;" readonly="true"
                                                                         cssClass="form-control" id="tanggalLahir1" name="biodata.stTanggalLahir" />
                                                        </s:if>
                                                        <s:elseif test="isAdd()">
                                                            <s:textfield cssStyle="text-align: left;" onchange=" getNip(this.value);getTanggalPensiun();"
                                                                         cssClass="form-control" id="tanggalLahir1" name="biodata.stTanggalLahir" />
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:textfield cssStyle="text-align: left;" onchange="getTanggalPensiun();"
                                                                         cssClass="form-control" id="tanggalLahir1" name="biodata.stTanggalLahir" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <s:if test="isDelete()">
                                                <tr>
                                                    <td>
                                                        <label><small>Unit <span style="color:red;">*</span> :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:if test="isDelete()">
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="biodata.branch" disabled="true"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            </s:if>
                                                            <s:else>
                                                                <%--<s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="biodata.branch" onchange="listPosisi()"--%>
                                                                <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>

                                                                <s:if test='biodata.branch == "01"'>
                                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="biodata.branch" onchange="listPosisi()"
                                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="biodata.branch" disabled="true"
                                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                                    <s:hidden id="branchId" name="biodata.branch"/>
                                                                </s:else>

                                                            </s:else>
                                                            <s:textfield type="text" cssStyle="display: none" id="posisi2" name="biodata.positionId2"/>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <s:else>
                                                <div style="display: none" class="form-group">
                                                    <label class="col-sm-3" >Unit : </label>
                                                    <div class="col-sm-8">
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:if test='biodata.branch == "01"'>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="biodata.branch" onchange="listPosisi()"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="biodata.branch" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="biodata.branch"/>
                                                        </s:else>
                                                        <s:textfield type="text" cssStyle="display: none" id="posisi2" name="biodata.positionId2"/>
                                                    </div>
                                                </div>
                                            </s:else>

                                            <s:if test="isDelete()">
                                                <tr>
                                                    <td>
                                                        <label><small>Bidang :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                                            <s:if test="isDelete()">
                                                                <s:select list="#comboDivisi.listComboDepartment" id="divisi1" name="biodata.divisi" disabled="true" readonly="true"
                                                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                            </s:if>
                                                            <s:else>
                                                                <s:select list="#comboDivisi.listComboDepartment" id="divisi1" name="biodata.divisi" onchange="listPosisi()"
                                                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                            </s:else>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <s:else>
                                                <div style="display: none" class="form-group">
                                                    <label><small>Bidang :</small></label>
                                                    <div class="col-sm-8">
                                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                                        <s:select list="#comboDivisi.listComboDepartment" id="divisi1" name="biodata.divisi" onchange="listPosisi()"
                                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </s:else>

                                            <s:if test="isDelete()">
                                                <tr>
                                                    <td>
                                                        <label><small>Jabatan :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                                            <s:if test="isDelete()">
                                                                <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="biodata.positionId" disabled="true"
                                                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            </s:if>
                                                            <s:else>
                                                                <select id="positionId1" name="biodata.positionId" class="form-control"></select>
                                                            </s:else>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <s:else>
                                                <div style="display: none" class="form-group">
                                                    <label><small>Jabatan :</small></label>
                                                    <div class="col-sm-8">
                                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                                        <select id="positionId1" name="biodata.positionId" class="form-control"></select>
                                                    </div>
                                                </div>
                                            </s:else>

                                            <s:if test="isDelete()">
                                                <tr>
                                                    <td>
                                                        <label><small>Profesi :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                                                            <s:if test="isDelete()">
                                                                <s:select list="#comboProfesi.listComboProfesi" id="profesi1" name="biodata.profesiId" disabled="true" readonly="true"
                                                                          listKey="profesiId" listValue="profesiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                            </s:if>
                                                            <s:else>
                                                                <s:select list="#comboProfesi.listComboProfesi" id="profesi1" name="biodata.profesiId"
                                                                          listKey="profesiId" listValue="profesiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                            </s:else>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <s:else>
                                                <div style="display: none" class="form-group">
                                                    <label><small>Profesi :</small></label>
                                                    <div class="col-sm-8">
                                                        <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                                                        <s:select list="#comboProfesi.listComboProfesi" id="profesi1" name="biodata.profesiId"
                                                                  listKey="profesiId" listValue="profesiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </s:else>

                                            <s:if test="isDelete()">
                                                <%--<tr>--%>
                                                <%--<td>--%>
                                                <%--<label><small>PJS :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                <%--<table>--%>
                                                <%--<input type="checkbox" id="pjs" class="checkZakat" disabled onchange="cekPjs()" />--%>
                                                <%--<s:textfield cssStyle="display: none" id="flagPjs" name="biodata.pjs"  />--%>
                                                <%--</table>--%>
                                                <%--</td>--%>
                                                <%--</tr>--%>
                                                <div style="display: none" class="form-group">
                                                    <label><small>PJS :</small></label>
                                                    <div class="col-sm-8">
                                                        <input type="checkbox" id="pjs" class="checkZakat" disabled onchange="cekPjs()" />
                                                        <s:textfield cssStyle="display: none" id="flagPjs" name="biodata.pjs"  />
                                                    </div>
                                                </div>
                                            </s:if>
                                            <s:elseif test="isAdd()">
                                                <div style="display: none" class="form-group">
                                                    <label><small>PJS :</small></label>
                                                    <div class="col-sm-8">
                                                        <input type="checkbox" id="pjs" class="checkZakat" onchange="cekPjs()" />
                                                        <s:textfield cssStyle="display: none" id="flagPjs" name="biodata.pjs"  />
                                                    </div>
                                                </div>
                                            </s:elseif>
                                            <s:else>
                                                <div style="display: none" class="form-group">
                                                    <label><small>PJS :</small></label>
                                                    <div class="col-sm-8">
                                                        <input type="checkbox" id="pjs" class="checkZakat" disabled onchange="cekPjs()" />
                                                        <s:textfield cssStyle="display: none" id="flagPjs" name="biodata.pjs"  />
                                                            <%--<s:hidden name="biodata.pjs"/>--%>
                                                    </div>
                                                </div>
                                            </s:else>
                                            <tr>
                                                <td>
                                                    <label><small>NPWP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="npwp" name="biodata.npwp" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="npwp" name="biodata.npwp" required="true" disabled="false" readonly="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Gelar Depan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="gelarDepan1" name="biodata.gelarDepan" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="gelarDepan1" name="biodata.gelarDepan" required="true" disabled="false" readonly="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Gelar Belakang :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="gelarBelakang1" name="biodata.gelarBelakang" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="gelarBelakang1" name="biodata.gelarBelakang" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>No KTP <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noKtp1" type="number" name="biodata.noKtp" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noKtp1" type="number" name="biodata.noKtp" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>No Telp :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noTelp1" type="number" name="biodata.noTelp" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noTelp1" type="number" name="biodata.noTelp" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Alamat :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textarea id="alamat1" rows="3" name="biodata.alamat" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textarea id="alamat1" rows="3" name="biodata.alamat" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Provinsi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="provinsi11" name="biodata.provinsiName" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="provinsi1" name="biodata.provinsiName"  required="true" disabled="false" cssClass="form-control"/>
                                                            <s:textfield cssStyle="display: none" id="provinsi11" name="biodata.provinsiId" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                        <script type='text/javascript'>
                                                            var functions, mapped;
                                                            $('#provinsi1').typeahead({
                                                                minLength: 1,
                                                                source: function (query, process) {
                                                                    functions = [];
                                                                    mapped = {};

                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    ProvinsiAction.initComboProvinsi(query, function (listdata) {
                                                                        data = listdata;
                                                                    });

                                                                    $.each(data, function (i, item) {
                                                                        var labelItem = item.provinsiName;
                                                                        mapped[labelItem] = { id: item.provinsiId, label: labelItem };
                                                                        functions.push(labelItem);
                                                                    });

                                                                    process(functions);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    var namaAlat = selectedObj.label;
                                                                    document.getElementById("provinsi11").value = selectedObj.id;
                                                                    prov = selectedObj.id ;
                                                                    return namaAlat;
                                                                }
                                                            });
                                                            //
                                                            //
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Kabupaten :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="kabupaten1" name="biodata.kotaName" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="kabupaten1" required="true" disabled="false" name="biodata.kotaName" cssClass="form-control"/>
                                                            <s:textfield cssStyle="display: none" id="kabupaten11" name="biodata.kabupatenId" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                        <script type='text/javascript'>
                                                            var functions, mapped;
                                                            // var prov = document.getElementById("provinsi1").value;
                                                            $('#kabupaten1').typeahead({
                                                                minLength: 1,
                                                                source: function (query, process) {
                                                                    functions = [];
                                                                    mapped = {};

                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    ProvinsiAction.initComboKota(query, prov, function (listdata) {
                                                                        data = listdata;
                                                                    });
                                                                    $.each(data, function (i, item) {
                                                                        //alert(item.kotaName);
                                                                        var labelItem = item.kotaName;
                                                                        mapped[labelItem] = { id: item.kotaId, label: labelItem };
                                                                        functions.push(labelItem);
                                                                    });

                                                                    process(functions);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    var namaAlat = selectedObj.label;
                                                                    document.getElementById("kabupaten11").value = selectedObj.id;

                                                                    kab = selectedObj.id ;
                                                                    return namaAlat;
                                                                }
                                                            });

                                                            //
                                                            //
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Kecamatan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="kecamatan1" name="biodata.kecamatanName" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="kecamatan1" name="biodata.kecamatanName" required="true" disabled="false" cssClass="form-control"/>
                                                            <s:textfield cssStyle="display: none" id="kecamatan11" name="biodata.kecamatanId" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                        <script type='text/javascript'>
                                                            var functions, mapped;
                                                            var kab = document.getElementById("kabupaten1").value;
                                                            $('#kecamatan1').typeahead({
                                                                minLength: 1,
                                                                source: function (query, process) {
                                                                    functions = [];
                                                                    mapped = {};

                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    ProvinsiAction.initComboKecamatan(query, kab, function (listdata) {
                                                                        data = listdata;
                                                                    });
                                                                    $.each(data, function (i, item) {
                                                                        //alert(item.kotaName);
                                                                        var labelItem = item.kecamatanName;
                                                                        mapped[labelItem] = { id: item.kecamatanId, label: labelItem };
                                                                        functions.push(labelItem);
                                                                    });

                                                                    process(functions);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    var namaAlat = selectedObj.label;
                                                                    document.getElementById("kecamatan11").value = selectedObj.id;

                                                                    kec = selectedObj.id;
                                                                    return namaAlat;
                                                                }
                                                            });
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Desa :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="desa1" name="biodata.desaName" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="desa1" required="true" disabled="false" name="biodata.desaName" cssClass="form-control"/>
                                                            <s:textfield cssStyle="display: none" id="desa11" name="biodata.desaId" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                        <script type='text/javascript'>
                                                            var functions, mapped;
                                                            $('#desa1').typeahead({
                                                                minLength: 1,
                                                                source: function (query, process) {
                                                                    functions = [];
                                                                    mapped = {};

                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    ProvinsiAction.initComboDesa(query, kec, function (listdata) {
                                                                        data = listdata;
                                                                    });
                                                                    $.each(data, function (i, item) {
                                                                        //alert(item.kotaName);
                                                                        var labelItem = item.desaName;
                                                                        mapped[labelItem] = { id: item.desaId, label: labelItem };
                                                                        functions.push(labelItem);
                                                                    });

                                                                    process(functions);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    var namaAlat = selectedObj.label;
                                                                    document.getElementById("desa11").value = selectedObj.id;

                                                                    desa = selectedObj.id;
                                                                    return namaAlat;
                                                                }
                                                            });
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>RT / RW :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="rtRw1" name="biodata.rtRw" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="rtRw1" name="biodata.rtRw" required="true" disabled="false" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <table style="width:100%;">
                                            <tr>
                                                <td>
                                                    <label><small>Tipe Pegawai <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai1" name="biodata.tipePegawai" disabled="true"
                                                                      listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden name="biodata.tipePegawai" />
                                                        </s:if>
                                                        <s:elseif test='biodata.tipePegawai=="TP03"'>
                                                            <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai1" name="biodata.tipePegawai" disabled="true"
                                                                      listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden name="biodata.tipePegawai" />
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai1" name="biodata.tipePegawai" onchange="changePegawai(this.value),loadStatusPegawai(),getPensiun()"
                                                                      listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Status Pegawai <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#{'KNS' : 'Pelaksana', 'KS':'Pimpinan'}"
                                                                      id="statusPegawai1" name="biodata.statusPegawai" disabled="true"
                                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden name="biodata.statusPegawai" />
                                                        </s:if>
                                                        <s:elseif test='biodata.statusPegawai=="KS"'>
                                                            <s:select list="#{'KNS' : 'Pelaksana', 'KS':'Pimpinan'}"
                                                                      id="statusPegawai1" name="biodata.statusPegawai" disabled="true"
                                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden name="biodata.statusPegawai" />
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:select list="#{'KNS' : 'Pelaksana', 'KS':'Pimpinan'}"
                                                                      id="statusPegawai1" name="biodata.statusPegawai"
                                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Level <span style="color:red;">*</span>:</small></label>
                                                </td>
                                                <td id="golongan1Group">
                                                    <table>
                                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#initComboTipe.listComboGolongan" id="golongan1" name="biodata.golongan" disabled="true"
                                                                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:elseif test="isAdd()">
                                                            <s:select list="#initComboTipe.listComboGolongan" id="golongan1" name="biodata.golongan"
                                                                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:select list="#initComboTipe.listComboGolongan" id="golongan1" name="biodata.golongan"
                                                                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:else>

                                                    </table>
                                                </td>
                                                <td id="golongan2Group">
                                                    <table>
                                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolonganPkwt_golongan"/>
                                                        <s:if test="isDelete()">
                                                            <%--Untuk list Golongan PKWT--%>
                                                            <s:select list="#initComboTipe.listComboGolonganPkwt" id="golongan3" name="biodata.golongan" disabled="true"
                                                                      listKey="golonganPkwtId" listValue="golonganPkwtName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <%--Untuk list Golongan PKWT--%>
                                                            <s:select list="#initComboTipe.listComboGolonganPkwt" id="golongan3" name="biodata.golongan"
                                                                      listKey="golonganPkwtId" listValue="golonganPkwtName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="label-tanggal-masuk"><small>Tanggal Masuk <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield cssStyle="text-align: left;" readonly="true"
                                                                         cssClass="form-control" id="tanggalMasuk" name="biodata.stTanggalMasuk" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield cssStyle="text-align: left;"
                                                                         cssClass="form-control" id="tanggalMasuk" name="biodata.stTanggalMasuk" />
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="label-tanggal-aktif"><small>Tanggal Aktif  :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isAdd()">
                                                            <s:textfield cssStyle="text-align: left;"
                                                                         cssClass="form-control" id="tanggalAktif" name="biodata.stTanggalAktif" />
                                                        </s:if>
                                                        <s:elseif test="isDelete()">
                                                            <s:textfield cssStyle="text-align: left;"
                                                                         cssClass="form-control" id="tanggalAktif" name="biodata.stTanggalAktif" disabled="true" readonly="true"/>
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:textfield cssStyle="text-align: left;"
                                                                         cssClass="form-control" id="tanggalAktif" name="biodata.stTanggalAktif" disabled="true"/>
                                                            <%--<s:hidden name="biodata.stTanggalAktif" id="tanggalAktifHid"/>--%>
                                                            <s:hidden id="tanggalAktifTmp"/>
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="label-prapensiun"><small>Tanggal MBT :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield cssStyle="text-align: left;" readonly="true"
                                                                         cssClass="form-control" id="tanggalPraPensiun" name="biodata.stTanggalPraPensiun" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield cssStyle="text-align: left;"
                                                                         cssClass="form-control" id="tanggalPraPensiun" name="biodata.stTanggalPraPensiun" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="label-pensiun"><small>Tanggal Pensiun :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield cssStyle="text-align: left;" readonly="true"
                                                                         cssClass="form-control" id="tanggalPensiun" name="biodata.stTanggalPensiun" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield cssStyle="text-align: left;"
                                                                         cssClass="form-control" id="tanggalPensiun" name="biodata.stTanggalPensiun" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Dana Pensiun :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="listComboDanaPensiun" namespace="/payrollDanaPensiun" name="searchPayrollDanaPensiun_payrollDanaPensiun"/>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#listComboDanaPensiun.listComboPayrollDanaPensiun"
                                                                      id="danaPensiun" name="biodata.danaPensiun" disabled="true"
                                                                      listKey="danaPensiunId" listValue="danaPensiun" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                            <s:hidden id="danaPensiunHid" name="biodata.danaPensiun"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#listComboDanaPensiun.listComboPayrollDanaPensiun"
                                                                      id="danaPensiun" name="biodata.danaPensiun" listKey="danaPensiunId"
                                                                      listValue="danaPensiun" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Golongan Pensiun :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolonganDapen_golongan"/>

                                                        <s:if test="isDelete()">
                                                            <s:select list="#initComboTipe.listComboGolonganDapen" id="golongan2" name="biodata.golonganDapenId"
                                                                      listKey="golonganDapenId" listValue="golonganDapenName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true" readonly="true"/>
                                                            <%--<s:textfield id="golongan2" name="biodata.golonganDapenId" required="true" disabled="true" cssClass="form-control" readonly="true"/>--%>
                                                            <s:hidden name="biodata.golonganDapenId"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#initComboTipe.listComboGolonganDapen" id="golongan2" name="biodata.golonganDapenId" listKey="golonganDapenId"
                                                                      listValue="golonganDapenName" headerKey="" headerValue="[Select one]" cssClass="form-control" onchange="getRangeMsKerja(this.value)"/>
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Ms. Kerja Gol. Pensiun : </small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="poinLebih" type="number" name="biodata.stMasaKerjaGol" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="poinLebih"  type="number" name="biodata.stMasaKerjaGol" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>No. Anggota Dapen :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noAnggotaDapen" type="text" name="biodata.noAnggotaDapen" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noAnggotaDapen"  type="text" name="biodata.noAnggotaDapen" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>No.BPJS Tenagakerjaan : </small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noBpjsKetenagakerjaan" type="text" name="biodata.noBpjsKetenagakerjaan" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noBpjsKetenagakerjaan"  type="text" name="biodata.noBpjsKetenagakerjaan" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>No. BPJS (Pensiun):</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noBpjsKetenagakerjaanPensiun" type="text" name="biodata.noBpjsKetenagakerjaanPensiun" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noBpjsKetenagakerjaanPensiun" type="text" name="biodata.noBpjsKetenagakerjaanPensiun" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>No. BPJS Kesehatan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noBpjsKesehatan" type="text" name="biodata.noBpjsKesehatan" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noBpjsKesehatan" type="text" name="biodata.noBpjsKesehatan" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Foto :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:file id="fileUpload" name="fileUpload" cssClass="form-control" disabled="true" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:file id="fileUpload" name="fileUpload" cssClass="form-control" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td>
                                                    <label><small>NIP Lama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield  id="nipLama" name="biodata.nipLama" required="true" readonly="true" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield  id="nipLama" name="biodata.nipLama" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td>
                                                    <label><small>Pin :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="pin" type="number" name="biodata.pin" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="pin"  type="number" name="biodata.pin" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                                <%--<tr>--%>
                                                <%--<td>--%>
                                                <%--<label><small>Kelompok Pegawai :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                <%--<table>--%>
                                                <%--<s:if test="isDelete()">--%>
                                                <%--<s:action id="comboJenisPegawai" namespace="/jenisPegawai" name="initComboJenisPegawai_jenisPegawai"/>--%>
                                                <%--<s:select list="#comboJenisPegawai.listOfComboJenisPegawai" id="jenisPegawai" name="biodata.jenisPegawai" disabled="true"--%>
                                                <%--listKey="jenisPegawaiId" listValue="jenisPegawaiName" headerKey="" headerValue="" cssClass="form-control"/>--%>
                                                <%--</s:if>--%>
                                                <%--<s:else>--%>
                                                <%--<s:action id="comboJenisPegawai" namespace="/jenisPegawai" name="initComboJenisPegawai_jenisPegawai"/>--%>
                                                <%--<s:select list="#comboJenisPegawai.listOfComboJenisPegawai" id="jenisPegawai" name="biodata.jenisPegawai"--%>
                                                <%--listKey="jenisPegawaiId" listValue="jenisPegawaiName" headerKey="" headerValue="" cssClass="form-control"/>--%>
                                                <%--</s:else>--%>
                                                <%--</table>--%>
                                                <%--</td>--%>
                                                <%--</tr>--%>
                                            <%--RAKA-28MAR2021 jabatan PLT dipilih di Tab JABTAN & RIWAYAT--%>
                                            <%--<tr>--%>
                                                <%--<td>--%>
                                                    <%--<label><small>Jabatan PLT :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                    <%--<table>--%>
                                                        <%--<s:if test="isDelete()">--%>
                                                            <%--<s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>--%>
                                                            <%--<s:select list="#comboPosition.listOfComboPosition" id="positionPltId" name="biodata.positionPltId" disabled="true"--%>
                                                                      <%--listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>--%>
                                                        <%--</s:if>--%>
                                                        <%--<s:else>--%>
                                                            <%--<s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>--%>
                                                            <%--<s:select list="#comboPosition.listOfComboPosition" id="positionPltId" name="biodata.positionPltId"--%>
                                                                      <%--listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>--%>
                                                        <%--</s:else>--%>
                                                    <%--</table>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <tr>
                                                <td>
                                                    <label><small>Shift <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#{'Y':'Y'}" id="shift" name="biodata.shift" disabled="true"
                                                                      headerKey="N" headerValue="N" cssClass="form-control" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#{'Y':'Y'}" id="shift" name="biodata.shift"
                                                                      headerKey="N" headerValue="N" cssClass="form-control" />
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Dokter Tamu <span style="color:red;">*</span> :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isAdd()">
                                                            <s:textfield id="dokterKso" name="biodata.flagDokterKso" value="N" readonly="true"  cssClass="form-control"/>
                                                        </s:if>
                                                        <s:elseif test="isDelete()">
                                                            <s:textfield id="dokterKso" name="biodata.flagDokterKso" readonly="true" cssClass="form-control" />
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:textfield id="dokterKso" name="biodata.flagDokterKso" readonly="true" cssClass="form-control" />
                                                        </s:else>

                                                    </table>
                                                </td>
                                            </tr>

                                            <s:if test='biodata.flagCutiDiluarTanggungan == "Y"'>
                                                <tr>
                                                    <td>
                                                        <label><small>Cuti Luar Tanggungan:</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" class="checkZakat" id="flagCutiLuar" disabled onchange="cekCuti()"/>
                                                                <s:textfield cssStyle="display: none"  id="flagCuti" name="biodata.flagCutiDiluarTanggungan"/>
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" class="checkZakat"  id="flagCutiLuar" onchange="cekCuti()"/>
                                                                <s:textfield cssStyle="display: none"  id="flagCuti" name="biodata.flagCutiDiluarTanggungan"/>
                                                            </s:else>
                                                        </table>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>
                                                        <label><small>Tanggal Cuti :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <div class="row">
                                                                <div class="col-md-5">
                                                                    <s:textfield id="cutiLuarTanggungAwal" name="biodata.stTanggalCutiDiluarTanggunganAwal" disabled="false" cssClass="form-control col-md-4" readonly="true"/>
                                                                </div>
                                                                <div class="col-md-2">
                                                                    <label style="padding-top: 20%;" ><small>hingga</small></label>
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <s:textfield id="cutiLuarTanggungAkhir" name="biodata.stTanggalCutiDiluarTanggunganAkhir" disabled="false" cssClass="form-control col-md-4" readonly="true"/>
                                                                </div>
                                                            </div>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </s:if>

                                                <%--<tr>--%>
                                                <%--<td>--%>
                                                <%--<label><small>Zakat Profesi :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                <%--<table>--%>
                                                <%--<s:if test="isDelete()">--%>
                                                <%--<input type="checkbox" id="zakatProfesi" class="checkZakat" disabled onchange="cekZakat()" />--%>
                                                <%--<s:textfield cssStyle="display: none" id="flagZakat" name="biodata.flagZakat"  />--%>
                                                <%--</s:if>--%>
                                                <%--<s:else>--%>
                                                <%--<input type="checkbox" id="zakatProfesi" class="checkZakat" onchange="cekZakat()" />--%>
                                                <%--<s:textfield cssStyle="display: none" id="flagZakat" name="biodata.flagZakat"  />--%>
                                                <%--</s:else>--%>
                                                <%--</table>--%>
                                                <%--</td>--%>
                                                <%--</tr>--%>

                                            <tr>
                                                <td>
                                                    <label style="color: red;"><small>Aktif :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="aktif" class="checkZakat" disabled onchange="cekAktif()"/>
                                                            <s:textfield cssStyle="display: none" id="flagAktif" name="biodata.flag" value="Y" />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="aktif" class="checkZakat" disabled onchange="cekAktif()"/>
                                                            <s:textfield cssStyle="display: none" id="flagAktif" name="biodata.flag" value="Y" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <s:if test="isAdd()">
                                                <s:textfield cssStyle="display: none" id="createUser" name="biodata.createUser" value="Y" />
                                            </s:if>
                                        </table>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-md-6">
                                        <table style="width:100%;">
                                            <tr>
                                                <td>
                                                    <label><small>Bank :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:select list="#{'-':'', 'mandiri':'Mandiri', 'bri' : 'BRI', 'bni' : 'BNI',
                                            'bca' : 'BCA', 'bca syariah' : 'BCA Syariah', 'btn' : 'BTN'}" id="namaBank" name="biodata.namaBank"
                                                                      cssClass="form-control" disabled="true" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#{'-':'', 'mandiri':'Mandiri', 'bri' : 'BRI', 'bni' : 'BNI',
                                            'bca' : 'BCA', 'bca syariah' : 'BCA Syariah', 'btn' : 'BTN'}" id="namaBank" name="biodata.namaBank"
                                                                      cssClass="form-control" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Cabang Bank :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="cabangBank" name="biodata.cabangBank" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="cabangBank" name="biodata.cabangBank" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>No. Rek Bank :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <s:textfield id="noRekBank" type="text" name="biodata.noRekBank" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="noRekBank" type="text" name="biodata.noRekBank" required="true" cssClass="form-control"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Finger Mobile :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="fingerMobile" class="checkZakat" disabled onchange="cekFingerMobile()" />
                                                            <s:textfield cssStyle="display: none" id="flagFingerMobile" name="biodata.flagFingerMobile"  />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="fingerMobile" class="checkZakat" onchange="cekFingerMobile()" />
                                                            <s:hidden id="flagFingerMobile" name="biodata.flagFingerMobile"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunjangan Supervisi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="supervisi" class="checkZakat" disabled onchange="cekSupervisi()" />
                                                                <s:hidden id="flagTunjSupervisi" name="biodata.flagTunjSupervisi"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="supervisi" class="checkZakat" onchange="cekSupervisi()"  />
                                                                <s:hidden id="flagTunjSupervisi" name="biodata.flagTunjSupervisi"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjSupervisi" type="number" name="biodata.stTunjSupervisi" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjSupervisi" type="number" name="biodata.stTunjSupervisi" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunjangan Lokasi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="lokasi" class="checkZakat" disabled onchange="cekLokasi()" />
                                                                <s:hidden id="flagTunjLokasi" name="biodata.flagTunjLokasi"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="lokasi" class="checkZakat" onchange="cekLokasi()" />
                                                                <s:hidden id="flagTunjLokasi" name="biodata.flagTunjLokasi"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjLokasi" type="number" name="biodata.stTunjLokasi" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjLokasi" type="number" name="biodata.stTunjLokasi" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Tunjangan Siaga :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="siaga" class="checkZakat" disabled onchange="cekSiaga()" />
                                                                <s:hidden id="flagTunjSiaga" name="biodata.flagTunjSiaga"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="siaga" class="checkZakat" onchange="cekSiaga()" />
                                                                <s:hidden id="flagTunjSiaga" name="biodata.flagTunjSiaga"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjSiaga" type="number" name="biodata.stTunjSiaga" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjSiaga" type="number" name="biodata.stTunjSiaga" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Tunjangan Profesional :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="profesional" class="checkZakat" disabled onchange="cekProfesional()" />
                                                            <s:textfield cssStyle="display: none" id="flagTunjProfesional" name="biodata.flagTunjProfesional"  />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="profesional" class="checkZakat" onchange="cekProfesional()" />
                                                            <s:hidden id="flagTunjProfesional" name="biodata.flagTunjProfesional"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunj. Peralihan Gapok :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="peralihanGapok" class="checkZakat" disabled onchange="cekPeralihanGapok()" />
                                                                <s:hidden id="flagPeralihanGapok" name="biodata.flagTunjPeralihanGapok"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="peralihanGapok" class="checkZakat" onchange="cekPeralihanGapok()"  />
                                                                <s:hidden id="flagPeralihanGapok" name="biodata.flagTunjPeralihanGapok"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjPeralihanGapok" type="number" name="biodata.stTunjPeralihanGapok" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjPeralihanGapok" type="number" name="biodata.stTunjPeralihanGapok" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunj. Peralihan Sankhus :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="peralihanSankhus" class="checkZakat" disabled onchange="cekPeralihanSankhus()" />
                                                                <s:hidden id="flagPeralihanSankhus" name="biodata.flagTunjPeralihanSankhus"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="peralihanSankhus" class="checkZakat" onchange="cekPeralihanSankhus()"  />
                                                                <s:hidden id="flagPeralihanSankhus" name="biodata.flagTunjPeralihanSankhus"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjPeralihanSankhus" type="number" name="biodata.stTunjPeralihanSankhus" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjPeralihanSankhus" type="number" name="biodata.stTunjPeralihanSankhus" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunj. Peralihan Tunjangan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="peralihanTunjangan" class="checkZakat" disabled onchange="cekPeralihanTunjangan()" />
                                                                <s:hidden id="flagPeralihanTunjangan" name="biodata.flagTunjPeralihanTunjangan"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="peralihanTunjangan" class="checkZakat" onchange="cekPeralihanTunjangan()"  />
                                                                <s:hidden id="flagPeralihanTunjangan" name="biodata.flagTunjPeralihanTunjangan"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjPeralihanTunjangan" type="number" name="biodata.stTunjPeralihanTunjangan" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjPeralihanTunjangan" type="number" name="biodata.stTunjPeralihanTunjangan" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Tunj.Pemondokan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <td>
                                                            <s:if test="isDelete()">
                                                                <input type="checkbox" id="tunjPemondokan" class="checkZakat" disabled onchange="cekTunjPemondokan()" />
                                                                <s:textfield cssStyle="display: none" id="flagTunjPemondokan" name="biodata.flagTunjPemondokan"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="checkbox" id="tunjPemondokan" class="checkZakat" onchange="cekTunjPemondokan()" />
                                                                <s:hidden id="flagTunjPemondokan" name="biodata.flagTunjPemondokan"  />
                                                            </s:else>
                                                        </td>
                                                        <td style="padding-left: 10px;">
                                                            <s:if test="isDelete()">
                                                                <s:textfield id="nomTunjPemondokan" type="number" name="biodata.stTunjPemondokan" required="true" disabled="false" cssClass="form-control" readonly="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:textfield id="nomTunjPemondokan" type="number" name="biodata.stTunjPemondokan" required="true" cssClass="form-control"/>
                                                            </s:else>
                                                        </td>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunj.Rumah :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="tunjRumah" class="checkZakat" disabled onchange="cekTunjRumah()" />
                                                            <s:textfield cssStyle="display: none" id="flagTunjRumah" name="biodata.flagTunjRumah"  />
                                                        </s:if>
                                                        <s:elseif test="isAdd()">
                                                            <input type="checkbox" id="tunjRumah" class="checkZakat" onchange="cekTunjRumah()" />
                                                            <s:hidden id="flagTunjRumah" name="biodata.flagTunjRumah"/>
                                                        </s:elseif>
                                                        <s:else>
                                                            <input type="checkbox" id="tunjRumah" class="checkZakat" onchange="cekTunjRumah()" />
                                                            <s:hidden id="flagTunjRumah" name="biodata.flagTunjRumah"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Tunj.Air :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="tunjAir" class="checkZakat" disabled onchange="cekTunjAir()" />
                                                            <s:textfield cssStyle="display: none" id="flagTunjAir" name="biodata.flagTunjAir"  />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="tunjAir" class="checkZakat" onchange="cekTunjAir()" />
                                                            <s:hidden id="flagTunjAir" name="biodata.flagTunjAir"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Tunj.Listrik :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="tunjListrik" class="checkZakat" disabled onchange="cekTunjListrik()" />
                                                            <s:textfield cssStyle="display: none" id="flagTunjListrik" name="biodata.flagTunjListrik"  />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="tunjListrik" class="checkZakat" onchange="cekTunjListrik()" />
                                                            <s:hidden id="flagTunjListrik" name="biodata.flagTunjListrik"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label><small>Tunj.Bbm :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="tunjBbm" class="checkZakat" disabled onchange="cekTunjBbm()" />
                                                            <s:textfield cssStyle="display: none" id="flagTunjBbm" name="biodata.flagTunjBbm"  />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="tunjBbm" class="checkZakat" onchange="cekTunjBbm()" />
                                                            <s:hidden id="flagTunjBbm" name="biodata.flagTunjBbm"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label><small>Mess :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="isDelete()">
                                                            <input type="checkbox" id="mess" class="checkZakat" disabled onchange="cekMess()" />
                                                            <s:textfield cssStyle="display: none" id="flagMess" name="biodata.flagMess"  />
                                                        </s:if>
                                                        <s:else>
                                                            <input type="checkbox" id="mess" class="checkZakat" onchange="cekMess()" />
                                                            <s:hidden id="flagMess" name="biodata.flagMess"  />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <table style="width:100%;">

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="keluarga" class="tab-pane fade">
                            <h3>Keluarga
                                <s:if test="isAddOrEdit()">
                                    <button id="btnAddKeluarga" type="button" class="btn btn-default btn-success" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i> </button>
                                </s:if>
                            </h3>
                            <table style="width: 80%;" class="keluargaTable table table-bordered">
                            </table>
                        </div>
                        <div id="RiwayatPendidikan" class="tab-pane fade">
                            <h3>Riwayat Pendidikan
                                <s:if test="isAddOrEdit()">
                                    <button id="btnAdd" type="button" class="btn btn-default btn-success" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i> </button>
                                </s:if>
                            </h3>
                            <table style="width: 80%;" class="studyTable table table-bordered">
                            </table>
                        </div>

                        <div id="pengalamanKerja" class="tab-pane fade">

                            <h3>Jabatan Existing
                                <s:if test="isAdd()">
                                    <button id="btnAddJabatan" type="button" class="btn btn-success" onclick="initAddJabatan()"><i class="fa fa-plus"></i> Add</button>
                                    <button id="btnAddJabatan" type="button" class="btn btn-danger" onclick="clearPositionJabatanSession()"><i class="fa fa-refresh"></i> Clear</button>
                                </s:if>
                            </h3>
                            <table style="width: 100%;" class="table table-bordered">
                                <thead style="font-size: 15px;font-weight:bold;background-color:  #3c8dbc; color: white" align="center">
                                    <tr>
                                        <td>No</td>
                                        <td>Unit</td>
                                        <td>Jabatan</td>
                                        <td>Profesi</td>
                                        <td>Jenis Jabatan</td>
                                        <td>Digaji</td>
                                        <td style="text-align: center">Action</td>
                                    </tr>
                                </thead>
                                <tbody id="tbody-position" style="font-size: 12px" align="center">

                                </tbody>
                            </table>

                            <%--//RAKA-18FEB2021==>FAILED--%>
                                <%--<table style="width: 40%; margin-left: auto; margin-right: auto; border: 5px solid dodgerblue;">--%>
                                    <%--<tr>--%>
                                        <%--<td colspan="2"><h4 style="text-align: center;"><b>DPJB & SIP Dokter</b></h4></td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td>--%>
                                            <%--<label><small>Kode DPJP :</small></label>--%>
                                        <%--</td>--%>
                                        <%--<td>--%>
                                            <%--<s:textfield type="number" id="kodeDpjp" name="biodata.dpjpDokter" class="form-control"/>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td>--%>
                                            <%--<label><small>SIP :</small></label>--%>
                                        <%--</td>--%>
                                        <%--<td>--%>
                                            <%--<s:textfield type="number" id="sip" name="biodata.sipDokter" class="form-control"/>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <%--<br>--%>
                                <%--</table>--%>

                            <h3>Riwayat Jabatan
                                <%--<s:if test="isAddOrEdit()">--%>
                                    <%--<button id="btnAddPengalamanKerja" type="button" class="btn btn-default btn-success" data-toggle="modal" ><i class="fa fa-plus"></i> </button>--%>
                                <%--</s:if>--%>
                            </h3>
                            <table style="width: 100%;" class="pengalamanKerjaTable table table-bordered">
                            </table>
                        </div>

                        <div id="reward" class="tab-pane fade">
                            <h3>Reward
                                <s:if test="isAddOrEdit()">
                                    <button id="btnAddReward" type="button" class="btn btn-default btn-success" data-toggle="modal" ><i class="fa fa-plus"></i> </button>
                                </s:if>
                            </h3>
                            <table style="width: 80%;" class="rewardTable table table-bordered">
                            </table>
                        </div>

                        <div id="sertifikat" class="tab-pane fade">
                            <h3>Sertifikat
                                <s:if test="isAddOrEdit()">
                                    <button id="btnAddSertifikat" type="button" class="btn btn-default btn-success" data-toggle="modal" ><i class="fa fa-plus"></i> </button>
                                </s:if>
                            </h3>
                            <table style="width: 100%;" class="sertifikatTable table table-bordered">
                            </table>
                        </div>

                        <div id="pelatihanJabatan" class="tab-pane fade">
                            <h3>Pelatihan
                                <s:if test="isAddOrEdit()">
                                    <button id="btnAddPelatihanJabatan" type="button" class="btn btn-default btn-success" data-toggle="modal" ><i class="fa fa-plus"></i> </button>
                                </s:if>
                            </h3>
                            <table style="width: 100%;" class="pelatihanJabatanTable table table-bordered">
                            </table>
                        </div>

                    </div>

                    <s:if test="isAddOrEdit()">
                        <sj:submit targets="crusd" type="button" cssClass="btn btn-primary" formIds="homeForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                    </s:if>

                    <%--<sj:submit targets="crusd" type="button" cssClass="btn btn-primary" formIds="homeForm" id="save" name="save"--%>
                    <%--onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"--%>
                    <%--onSuccessTopics="successDialog" onErrorTopics="errorDialog" >                        --%>
                    <%--<s:if test="isDelete()">--%>
                    <%--<i class="fa fa-trash"></i>--%>
                    <%--Delete--%>
                    <%--</s:if>--%>
                    <%--<s:else>--%>
                    <%--<i class="fa fa-check"></i>--%>
                    <%--Save--%>
                    <%--</s:else>--%>
                    <%--</sj:submit>--%>

                    <s:if test="isDelete()">
                        <a href="search_biodata.action" class="btn btn-danger"><i class="fa fa-close"></i> Back</a>
                    </s:if>
                    <s:else>
                        <a href="search_biodata.action" class="btn btn-danger"><i class="fa fa-close"></i> Cancel</a>
                    </s:else>
                    <%--<a href="search_biodata.action" class="btn btn-danger"><i class="fa fa-close"></i> Tes</a>--%>
                    <div id="actions" class="form-actions">
                        <table>
                            <tr>
                                <div id="crud">
                                    <td>
                                        <table>
                                            <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                       closeTopics="closeDialog" modal="true"
                                                       resizable="false"
                                                       height="250" width="600" autoOpen="false">
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

                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                       buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                      link();
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
                                                    <label align="left">
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
                                                    <label align="left">
                                                        <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                        <br/>
                                                        <center>
                                                        </center>
                                                        <div id="errorValidationMessage"></div>
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
                <%--<a class="btn btn-success" onclick="cek()"><i class="fa fa-check"></i> Save</a>--%>
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


<%--<div id="modal-edit-study" class="modal fade" role="dialog">--%>
<%--<div class="modal-dialog" style="width: 450px">--%>

<%--<!-- Modal content-->--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--<h4 class="modal-title">Edit Study</h4>--%>
<%--</div>--%>
<%--<div class="modal-body">--%>
<%--<s:url id="urlProcess" namespace="/study" action="editStudy_study"--%>
<%--includeContext="false"/>--%>
<%--<s:form id="myFormDocument1" enctype="multipart/form-data" method="post" action="%{urlProcess}"--%>
<%--theme="simple" cssClass="form-horizontal">--%>
<%--<s:hidden name="addOrEdit"/>--%>
<%--<s:hidden id="add" name="add"/>--%>
<%--<s:hidden name="delete"/>--%>

<%--<s:if test="isAddOrEdit()">--%>
<%--<div style="display: none" class="form-group">--%>
<%--<label class="control-label col-sm-3" >Id : </label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" id="studyId" name="study.studyId">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4" for="gender">Type Study :</label>--%>
<%--<div class="col-sm-8">--%>
<%--<select class="form-control" id="studyTypeStudy" name="study.typeStudy">--%>
<%--<option value="SD">SD</option>--%>
<%--<option value="SMP">SMP</option>--%>
<%--<option value="SMA">SMA</option>--%>
<%--<option value="D1">Diploma D1</option>--%>
<%--<option value="D2">Diploma D2</option>--%>
<%--<option value="D3">Diploma D3</option>--%>
<%--<option value="S1">Sarjana (S1)</option>--%>
<%--<option value="S2">Sarjana (S2)</option>--%>
<%--<option value="S3">Sarjana (S3)</option>--%>
<%--</select>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4" >Study Name : </label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" id="studyName" name="study.studyName">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4" for="gender">Fakultas :</label>--%>
<%--<div class="col-sm-8">--%>
<%--<select class="form-control" id="studyFakultas1" name="study.studyFakultas" >--%>
<%--</select>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4" >Program Studi : </label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" id="pendidikanProgramStudi" name="study.programStudy">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4" >Tahun Awal : </label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" id="studyTahunAwal" name="study.tahunAwal">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4" >Tahun Lulus : </label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" id="studyTahunAkhir" name="study.tahunAkhir">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-4">Ijazah (Jpeg) : </label>--%>

<%--<div class="col-sm-8">--%>
<%--<input type="file" id="file" class="form-control" name="fileUpload"/>--%>
<%--<input type="text" id="cpiddoc" class="form-control" accept="application/pdf,image/jpeg"--%>
<%--name="study.uploadFile" readonly />--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="modal-footer">--%>
<%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="myFormDocument1"--%>
<%--id="saveDocument1" name="save" onBeforeTopics="beforeProcessSaveStudy1"--%>
<%--onCompleteTopics="closeDialog,successDialogDocument1"--%>
<%--onSuccessTopics="successDialogDocument1" onErrorTopics="errorDialog">--%>
<%--<i class="fa fa-check"></i>--%>
<%--Save--%>
<%--</sj:submit>--%>
<%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
<%--</div>--%>
<%--</s:if>--%>
<%--</s:form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


<!-- Modal Edit-->
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 450px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Study</h4>
            </div>
            <div class="modal-body">
                <s:url id="urlProcess" namespace="/study" action="addStudy_study"
                       includeContext="false"/>
                <s:form id="myFormDocument" enctype="multipart/form-data" method="post" action="%{urlProcess}" proses="addStudy"
                        theme="simple" cssClass="form-horizontal">
                    <s:hidden name="addOrEdit"/>
                    <s:hidden id="add" name="add"/>
                    <s:hidden name="delete"/>
                    <s:hidden name="biodata.nip"/>

                    <s:if test="isAddOrEdit()">
                        <div style="display: none" class="form-group">
                            <label class="control-label col-sm-3" >Id : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="studyId" name="txtStdudyName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="gender">Type Study :</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="studyTypeStudy" name="study.typeStudy">
                                    <option value="SD">SD</option>
                                    <option value="SMP">SMP</option>
                                    <option value="SMA">SMA</option>
                                    <option value="D1">Diploma D1</option>
                                    <option value="D2">Diploma D2</option>
                                    <option value="D3">Diploma D3</option>
                                    <option value="S1">Sarjana (S1)</option>
                                    <option value="S2">Sarjana (S2)</option>
                                    <option value="S3">Sarjana (S3)</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Study Name : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="studyName" name="study.studyName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="gender">Fakultas :</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="studyFakultas" name="study.studyFakultas" >
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Program Studi : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pendidikanProgramStudi" name="study.programStudy">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tahun Awal : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="studyTahunAwal" name="study.tahunAwal">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tahun Lulus : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="studyTahunAkhir" name="study.tahunAkhir">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Ijazah (Jpg): </label>--%>
                        <%--<div class="col-sm-8">--%>
                        <%--<s:file id="fileUploadIjazah" name="fileUploadIjazah" cssClass="form-control" />--%>
                        <%--</div>--%>
                        <%--</div>--%>

                        <div class="form-group">
                            <label class="control-label col-sm-4">Ijazah (Jpeg) : </label>

                            <div class="col-sm-8">
                                <input type="file" id="file" class="form-control" name="fileUpload"/>
                                <input type="text" id="cpiddoc" class="form-control" accept="application/pdf,image/jpeg"
                                       name="study.uploadFile" readonly style="display: none;"/>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="myFormDocument"
                                       id="saveDocument" name="save" onBeforeTopics="beforeProcessSaveStudy"
                                       onCompleteTopics="closeDialog,successDialogDocument"
                                       onSuccessTopics="successDialogDocument" onErrorTopics="errorDialog">
                                <i class="fa fa-check"></i>
                                Save
                            </sj:submit>
                            <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                        </div>
                    </s:if>
                </s:form>

                <%--<form class="form-horizontal" id="myForm">--%>

                <%--</form>--%>
            </div>
            <%--<div class="modal-footer">--%>
            <%--<button id="btnSave" type="button" class="btn btn-default btn-success">Simpan</button>--%>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
            <%--</div>--%>
        </div>
    </div>
</div>

<div id="modal-view-document" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Document</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-pengalamanKerja" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 600px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormPengalaman">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >: </label>
                        <div class="col-sm-8">
                            <%--<input type="text" class="form-control" id="pengalamanId" name="txtStdudyName">--%>
                            <input type="text" class="form-control" id="pengalamanId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="gender">Nama Cabang :</label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch"
                                      name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdRiwayatKerja"
                                      name="biodata.branchId" onchange="listDivisiHistory()"
                                      listKey="branchId" listValue="branchName" headerKey=""
                                      headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group" id="namaBidang">
                        <label class="control-label col-sm-4" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department"
                                      name="searchDepartment2_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="departmentId"
                                      name="biodata.departmentId" onchange="listPosisiHistory()"
                                      listKey="departmentId" listValue="departmentName"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control"/>
                        </div>
                    </div>
                    <div id="namaBidangLain" class="form-group" style="display: none">
                        <label class="control-label col-sm-4" for="gender">Nama Bidang Lain:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bidangLain">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position"
                                      name="searchPosition3_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="positionId3"
                                      name="biodata.positionId" onchange="cekPosisiLain()"
                                      listKey="positionId" listValue="positionName" headerKey=""
                                      headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>
                    <div id="namaJabatanLain" class="form-group" style="display: none">
                        <label class="control-label col-sm-4" for="gender">Nama Jabatan Lain: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="jabatanLain">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Profesi : </label>
                        <div class="col-sm-8">
                            <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                            <s:select list="#comboProfesi.listComboProfesi" id="profesi3" name="biodata.profesiId"
                                      listKey="profesiId" listValue="profesiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Diangkat: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="pengalamanTanggalMasuk" readonly="true" style="background-color: #fff;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Selesai: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="pengalamanTanggalKeluar" readonly="true" style="background-color: #fff;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe Pegawai: </label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                            <s:select list="#initComboTipe.listComboTipePegawai" id="pengalamanTipePegawaiId" onchange="changePegawaiHistory(this.value)"
                                      listKey="tipePegawaiId" listValue="tipePegawaiName" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan: </label>
                        <div class="col-sm-8" id="golonganHistory1Group">
                            <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#comboGolongan.listComboGolongan" id="pengalamanGolonganId1"
                                      listKey="golonganId" listValue="stLevel" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                        <div class="col-sm-8" id="golonganHistory2Group">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolonganPkwtHistory_golongan"/>
                            <s:select list="#initComboTipe.listComboGolonganPkwtHistory" id="golonganHistory3" name="biodata.golongan"
                                      listKey="golonganPkwtId" listValue="golonganPkwtName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="control-label col-sm-4" >Pjs: </label>--%>
                    <%--<div class="col-sm-8">--%>
                    <%--<s:select list="#{'Y':'Ya'}" id="pjsFlag1"--%>
                    <%--headerKey="N" headerValue="Tidak" cssClass="form-control" />--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="control-label col-sm-4" > Jenis Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboJenisPegawai" namespace="/jenisPegawai" name="initComboJenisPegawai_jenisPegawai"/>
                            <s:select list="#comboJenisPegawai.listOfComboJenisPegawai" id="jenisPegawaiId" name="biodata.jenisPegawai"
                                      listKey="jenisPegawaiId" listValue="jenisPegawaiName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" > Digaji : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'N':'Tidak'}" id="flagDigaji"
                                      headerKey="Y" headerValue="Ya" cssClass="form-control" />
                        </div>
                    </div>
                    <s:hidden id="flagAktif1" value="Y"></s:hidden>


                    <%--<div class="form-group">--%>
                    <%--<label class="control-label col-sm-4" >Jabatan Aktif?: </label>--%>
                    <%--<div class="col-sm-8">--%>
                    <%--<s:select list="#{'Y':'Ya'}" id="flagAktif1"--%>
                    <%--headerKey="N" headerValue="Tidak" cssClass="form-control" />--%>
                    <%--</div>--%>
                    <%--</div>--%>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSavePengalaman" type="button" class="btn btn-default btn-success">Simpan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-reward" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Reward</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormReward">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="rewardId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="gender">Tanggal :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="rewardTanggal">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jenis Reward : </label>
                        <div class="col-sm-8">
                            <select class="form-control" id="rewardJenis" name="rewardJenis" >
                                <option value="uangTunai">Uang Tunai</option>
                                <option value="piagam">Piagam</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keterangan: </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="rewardKeterangan" rows="4"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveReward" type="button" class="btn btn-default btn-success">Simpan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-sertifikat" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Sertifikat</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormSertifikat">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jenis :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatJenis">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Pengesahan:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatTanggalPengesahan">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Berlaku:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatMasaBerlaku">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Berakhir:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatMasaBerakhir">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Sertifikat:</label>
                        <div class="col-sm-8">
                            <textarea cols="3" type="text" class="form-control" id="sertifikatNama"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Lembaga:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatLembaga">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tempat Pelaksana:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatTempatPelaksana">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah Hari:</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="sertifikatJumlahHari">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai:</label>
                        <div class="col-sm-8">
                            <input onkeyup="checkDec(this);" type="text" class="form-control" id="sertifikatNilai">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Lulus:</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="sertifikatLulus">
                                <option value="lulus">Lulus</option>
                                <option value="tidak">Tidak</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Prestasi:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sertifikatPrestasi">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveSertifikat" type="button" class="btn btn-default btn-success">Simpan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-pelatihanJabatan" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Pelatihan</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormPelatihanJabatan">
                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="pelatihanId" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="namaPelatihan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Judul Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="judulPelatihan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Penyelenggara :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="pelatihanLembaga">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Lama Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="jumlahJamPelatihan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Sertifikasi :</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="sertifikasiPelatihan">
                                <option value="Y">Ya</option>
                                <option value="N">Tidak</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="tanggalPelatihan" readonly style="background-color: #fff">
                            <script>
                                $('#tanggalPelatihan').datepicker({
                                    dateFormat: 'dd-mm-yy'
                                });
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Berlaku Sert.:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="masaBerlakuSertifikat">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Sertifikat (Jpeg) : </label>
                        <div class="col-sm-8">
                            <input type="file" id="fileSertifikat"  accept=".jpg" class="form-control" name="fileUpload"/>
                            <input type="hidden" id="base64sertifikat" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSavePelatihan" type="button" class="btn btn-default btn-success">Simpan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="deleteModalPelatihan" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Pelatihan</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormDeletePelatihanJabatan">
                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="delpelatihanId" readonly >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="delnamaPelatihan" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Judul Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="deljudulPelatihan" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Penyelenggara :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="delpelatihanLembaga" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Lama Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="deljumlahJamPelatihan" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Sertifikasi :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="delsertifikasiPelatihan" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Pelatihan :</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="deltanggalPelatihan" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Berlaku Sert.:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="delmasaBerlakuSertifikat" readonly>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDeletePelatihan" type="button" class="btn btn-default btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="deleteModalPengalamanKerja" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Riwayat Kerja</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormDeletePengalaman">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="deletePengalamanId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="gender">Nama Cabang :</label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="deletePengalamanPerusahaan">
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jabatan : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="deletePengalamanJabatan" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Masuk: </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="deletePengalamanTanggalMasuk">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDeletePengalamanKerja" type="button" class="btn btn-default btn-error">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="deleteModalSertifikat" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Sertifikat</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormDeleteSertifikat">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jenis :</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatJenis">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Pengesahan:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatTanggalPengesahan">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Berlaku:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatMasaBerlaku">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Masa Berakhir:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatMasaBerakhir">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama Sertifikat:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatNama">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Lembaga:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatLembaga">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tempat Pelaksana:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatTempatPelaksana">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nilai:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatNilai">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Lulus:</label>
                        <div class="col-sm-8">
                            <select disabled class="form-control" id="deleteSertifikatLulus">
                                <option value="lulus">Lulus</option>
                                <option value="tidak">Tidak</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Prestasi:</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteSertifikatPrestasi">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteSertifikat" type="button" class="btn btn-default btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="deleteModalReward" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Reward</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormDeleteReward">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="deleteRewardId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="gender">Tanggal :</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="deleteRewardTanggal">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jenis Reward : </label>
                        <div class="col-sm-8">
                            <select disabled class="form-control" id="deleteRewardJenis" name="rewardJenis" >
                                <option value="uangTunai">Uang Tunai</option>
                                <option value="piagam">Piagam</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keterangan: </label>
                        <div class="col-sm-8">
                            <textarea readonly class="form-control" id="deleteRewardKeterangan" rows="4"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteReward" type="button" class="btn btn-default btn-error">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-editKeluarga" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 410px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeluarga">

                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-4" >Id : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="keluargaIdLama" >
                            <input type="text" class="form-control" id="keluargaId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Name : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="keluargaName" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Status Keluarga:</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="statusKeluarga" name="statusKeluarga" >
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Gender:</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="genderKeluarga" name="gender" >
                                <option value="">-</option>
                                <option value="L">Laki - Laki</option>
                                <option value="P">Perempuan</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Lahir : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="tanggalLahirkeluarga" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveKeluarga" type="button" class="btn btn-default btn-success">Simpan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="deleteModalKeluarga" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 420px">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Keluarga</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeluargaDelete">
                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-4" >Id : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="keluargaIdDelete" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Name : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control" id="keluargaNameDelete" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Status Keluarga:</label>
                        <div class="col-sm-8">
                            <select readonly="true" class="form-control" id="statusKeluargaDelete" >
                                <option value="Istri">Istri</option>
                                <option value="Suami">Suami</option>
                                <option value="Anak">Anak</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Lahir : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="tanggalLahirkeluargaDelete" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteKeluarga" type="button" class="btn btn-default btn-success">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- End of modal edit -->

<div id="deleteModal" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width:350px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormDelete">
                    <div style="display: none" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="studyIdDelete" name="txtStdudyName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="gender">Type Study :</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="studyTypeStudyDelete" disabled name="txtGender">
                                <option value="SD">SD</option>
                                <option value="SMP">SMP</option>
                                <option value="SMA">SMA</option>
                                <option value="D3">Sarjana (D3)</option>
                                <option value="S1">Sarjana (S1)</option>
                                <option value="S2">Sarjana (S2)</option>
                                <option value="S3">Sarjana (S3)</option>
                            </select>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-4" >Study Name : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="studyNameDelete" readonly name="txtStdudyName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun Awal : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="studyTahunAwalDelete" readonly name="txtStdudyName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun Lulus : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="studyTahunAkhirDelete" readonly name="txtStdudyName">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" class="btn btn-default btn-info">Delete</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-view-lampiran" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Sertifikat</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image2">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-edit-jabatan" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 40%;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 id="modal-edit-jabatan-title"></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="personil-position-id"/>
                <input type="hidden" id="personil-position-flag"/>
                <input type="hidden" id="position-id-lama"/>
                <input type="hidden" id="position-name-lama"/>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-9">
                            <s:action id="initComboBranch" namespace="/admin/branch"
                                      name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="position-branch-id"
                                      name="biodata.branchId"
                                      onchange="listDivisiJabatan()"
                                      listKey="branchId" listValue="branchName" headerKey=""
                                      headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-9">
                                <select class="form-control" id="department-jabatan" onchange="listPositionJabatan()">

                                </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Posisi : </label>
                        <div class="col-sm-9">
                            <select class="form-control" id="posisi-jabatan">

                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="gender">Profesi :</label>
                        <div class="col-sm-9">
                            <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                            <s:select list="#comboProfesi.listComboProfesi" id="profesi-jabatan"
                                      listKey="profesiId" listValue="profesiName" headerKey="" headerValue="" cssClass="form-control" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jenis Jabatan : </label>
                        <div class="col-sm-9">
                            <select class="form-control" id="jenis-jabatan">

                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Digaji : </label>
                        <div class="col-sm-9">
                            <s:select list="#{'N':'Tidak'}" id="flag-digaji"
                                      headerKey="Y" headerValue="Ya" cssClass="form-control" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="saveJabatan" type="button" class="btn btn-success">Save</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</html>
<script type='text/javascript' src='<s:url value="/pages/dist/js/biodata.js"/>'></script>

<script>
    window.cekPerusahaanLain = function(){
        var branch = document.getElementById("branchIdRiwayatKerja").value;
        if (branch=='0'){
            $('#perusahaanLain').val("");
            $('#namaPerusahaanLain').show();
        }
        else{
            $('#perusahaanLain').val("");
            $('#namaPerusahaanLain').hide();
        }
    };
    window.cekBidangLain = function(){
        var divisi = document.getElementById("departmentId").value;
        if (divisi=='0'){
            $('#bidangLain').val("");
            $('#namaBidangLain').show();
        }
        else{
            $('#bidangLain').val("");
            $('#namaBidangLain').hide();
        }
    };
    window.cekPosisiLain = function(){
        var position = document.getElementById("positionId3").value;
//        alert(position);
        if (position=='0'){
            $('#jabatanLain').val("");
            $('#namaJabatanLain').show();
        }
        else{
            $('#jabatanLain').val("");
            $('#namaJabatanLain').hide();
        }
    };
    window.listDivisi= function(){
        var branch = document.getElementById("branchIdRiwayatKerja").value;
        $('#departmentId').empty();
        PositionAction.searchDivisi2(branch, function(listdata){
            $.each(listdata, function (i, item) {
                $('#departmentId').append($("<option></option>")
                    .attr("value",item.departmentId)
                    .text(item.departmentName));
            });
        });
        listPosisi();

    };
    window.listDivisiHistory= function(){
        var branch = document.getElementById("branchIdRiwayatKerja").value;
        $('#departmentId').empty();
        PositionAction.searchDivisi2(branch, function(listdata){
            $.each(listdata, function (i, item) {
                $('#departmentId').append($("<option></option>")
                    .attr("value",item.departmentId)
                    .text(item.departmentName));
            });
        });
        listPosisiHistory();
        listPositionJabatan();
    };
    window.listDivisiJabatan= function(){
        var branch = document.getElementById("position-branch-id").value;
        $('#department-jabatan').empty();
        PositionAction.searchDivisi2(branch, function(listdata){
            $.each(listdata, function (i, item) {
                $('#department-jabatan').append($("<option></option>")
                    .attr("value",item.departmentId)
                    .text(item.departmentName));
            });
        });
        listPosisiHistory();
        listPositionJabatan();
    };

    function cek(){
        var home    = document.getElementById("home1").value;
        var person    = document.getElementById("person1").value;
        var training    = document.getElementById("training1").value;
        var menu1    = document.getElementById("menu11").value;
        var menu2    = document.getElementById("menu21").value;
        var menu3    = document.getElementById("menu31").value;

        //alert('Home : ' + home + ', Person : ' + person + ', training : ' + training + ', menu1 : ' + menu1 + ', menu2 : ' + menu2 + ', menu3 : ' + menu3);
        //window.location.href="<s:url action='search_alat'/>";
    }

    window.listPosisi = function (branch, divisi) {
        var branch = document.getElementById("branch1").value;
        var divisi = document.getElementById("divisi1").value;
        $('#positionId1').empty();
        $('#positionId1').append($("<option></option>")
            .attr("value", '')
            .text(''));
        PositionAction.searchPositionBiodata(divisi, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#positionId1').append($("<option></option>")
                    .attr("value", item.positionId)
                    .text(item.positionName));
            });
        });
    }
    window.listPosisiHistory = function (branch, divisi) {
        var branch = document.getElementById("branch1").value;
        var divisi = document.getElementById("departmentId").value;
        $('#positionId3').empty();
        $('#positionId3').append($("<option></option>")
            .attr("value", '')
            .text(''));
        PositionAction.searchPositionBiodataHistory(divisi, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#positionId3').append($("<option></option>")
                    .attr("value", item.positionId)
                    .text(item.positionName));
            });
        });
    }

    window.changePegawai = function (id) {
        if (id == "TP03") {
            $('#golongan1Group').show();
            $('#golongan2Group').hide();
            $('#golongan3').val("");
            $('#tanggalAktif').removeAttr('disabled');
            $('#tanggalPraPensiun').removeAttr('disabled');
            $('tanggalAktifTmp').val($('#tanggalAktifHid').val());
            $('#tanggalAktifHid').val("");
        } else {
            $('#golongan1Group').hide();
            $('#golongan2Group').show();
            $('#golongan1').val("");
            $('#point').prop('disabled', 'true');
            $('#tanggalAktif').prop('disabled', 'true');
            $('#tanggalPraPensiun').prop('disabled', 'true');
            $('tanggalAktifHid').val($('#tanggalAktifTmp').val());
        }
    }
    window.changePegawaiHistory = function (id) {
        if (id == "TP03") {
            $('#golonganHistory1Group').show();
            $('#golonganHistory2Group').hide();
        } else {
            $('#golonganHistory1Group').hide();
            $('#golonganHistory2Group').show();
        }
    }

    function loadStatusPegawai(){
        var statusPegawai = $('#tipePegawai1').val();
        if (statusPegawai=="TP04"){
            $('.label-prapensiun').html("<small>Tgl Pra Kontak Berakhir : </small>");
            $('.label-pensiun').html("<small>Tgl Kontrak Berakhir : </small>");
            $('.label-tanggal-masuk').html("<small>Tanggal Kontrak : </small>");
            $('.label-tanggal-aktif').html("<small>Tanggal Aktif : </small>");

        } else{
            $('.label-prapensiun').html("<small>Tanggal MBT : </small>");
            $('.label-pensiun').html("<small>Tanggal Pensiun : </small>");
            $('.label-tanggal-masuk').html("<small>Tanggal Masuk <span style=\"color:red;\">*</span> : </small>");
            $('.label-tanggal-aktif').html("<small>Tanggal Pengangkatan : </small>");
        }
    }

    // function detLmtMsGol(gol){
    //
    // }

    $(document).ready(function() {
        loadStatusPegawai();
        loadPositionJabatan();
        getAllDepartment();
        getAllJenisPegawai();

        // var tglLahir = $('#tanggalLahir1').val();
        // var tglPensiun = $('#tanggalPensiun').val();
        // if(tglLahir != "" && tglPensiun == "") getTanggalPensiun();

        window.checkDec = function(el){
            var ex = /^[0-9]+\.?[0-9]*$/;
            if(ex.test(el.value)==false){
                el.value = el.value.substring(0,el.value.length - 1);
            }
        }

        // BranchAction.getDataBranch(function (listdata) {
        //     $.each(listdata, function (i, item) {
        //         $('#branchIdRiwayatKerja').append($("<option></option>")
        //                 .attr("value", item.branchId)
        //                 .text(item.branchName));
        //     });
        //     $('#branchIdRiwayatKerja').append($("<option></option>")
        //             .attr("value", "lain")
        //             .text("Lain"));
        // });

        window.cekPerusahaan = function(){
            var branch = document.getElementById("branchIdRiwayatKerja").value;
            var branchName = $("#branchIdRiwayatKerja option:selected").text();

            if(branch != "lain"){
                $('#namaPerusahaanLain').hide();
                $('#pengalamanPerusahaan').val(branchName);
            }else{
                $('#pengalamanPerusahaan').val("");
                $('#namaPerusahaanLain').show();
            }

        }

        window.cekPosition = function(){
            var positionId = document.getElementById("positionIdRiwayatKerja").value;
            var positionName = $("#positionIdRiwayatKerja option:selected").text();

            if(branch != "lain"){
                $('#namaPerusahaanLain').hide();
                $('#pengalamanPerusahaan').val(branchName);
            }else{
                $('#pengalamanPerusahaan').val("");
                $('#namaPerusahaanLain').show();
            }

        }

        function toFixed(number, decimals) {
            var x = Math.pow(10, Number(decimals) + 1);
            return (Number(number) + (1 / x)).toFixed(decimals)
        }

        var branch = document.getElementById("branch1").value;
        var divisi = document.getElementById("divisi1").value;
        var posisi = document.getElementById("posisi2").value;
        var tipePegawai = document.getElementById("tipePegawai1").value;
//        var strukturGaji = document.getElementById("strukturGaji2").value;
        listPosisi(branch, divisi);
        changePegawai(tipePegawai);
        $('#positionId1').val(posisi);
//        $('#strukturGaji').val(strukturGaji);

        var pathFoto = document.getElementById("pathFoto").value;
        var nama = document.getElementById("namaPegawai1").value;
        var jenis = document.getElementById("gender").value;
        if (pathFoto != '') {
            if (pathFoto == 'null') {
                $('#detailImg').attr('src', '/go-medsys/pages/upload/image/profile/man_employee.png');
                $('#detailImg').attr('alt', nama);
            } else {
                $('#detailImg').attr('src', pathFoto);
                $('#detailImg').attr('alt', nama);
            }
        } else {
            $('#detailImg').attr('src', '/go-medsys/pages/upload/image/profile/man_employee.png');
            $('#detailImg').attr('alt', nama);
        }
//        var zakat = document.getElementById("flagZakat").value;
//        if (zakat == "Y") {
//            document.getElementById("zakatProfesi").checked = true;
//        } else {
//            document.getElementById("zakatProfesi").checked = false;
//        }

        var pjs = document.getElementById("flagPjs").value;
        if (pjs == "Y") {
            document.getElementById("pjs").checked = true;
        } else {
            document.getElementById("pjs").checked = false;
        }

        var aktif = document.getElementById("flagAktif").value;
        if (aktif == "Y") {
            document.getElementById("aktif").checked = true;
        } else {
            document.getElementById("aktif").checked = false;
        }
        var pemondokan = document.getElementById("flagTunjPemondokan").value;
        if (pemondokan == "Y") {
            document.getElementById("tunjPemondokan").checked = true;
            $("#nomTunjPemondokan").show();
        } else {
            document.getElementById("tunjPemondokan").checked = false;
            $("#nomTunjPemondokan").hide();
        }
        var flagTunjSupervisi = document.getElementById("flagTunjSupervisi").value;
        if (flagTunjSupervisi == "Y") {
            document.getElementById("supervisi").checked = true;
            $("#nomTunjSupervisi").hide();
        } else {
            document.getElementById("supervisi").checked = false;
            $("#nomTunjSupervisi").hide();
        }
        var flagTunjLokasi = document.getElementById("flagTunjLokasi").value;
        if (flagTunjLokasi == "Y") {
            document.getElementById("lokasi").checked = true;
            $("#nomTunjLokasi").show();
        } else {
            document.getElementById("lokasi").checked = false;
            $("#nomTunjLokasi").hide();
        }
        var flagTunjSiaga = document.getElementById("flagTunjSiaga").value;
        if (flagTunjSiaga == "Y") {
            document.getElementById("siaga").checked = true;
            $("#nomTunjSiaga").show();
        } else {
            document.getElementById("siaga").checked = false;
            $("#nomTunjSiaga").hide();
        }


        var flagTunjProfesional = document.getElementById("flagTunjProfesional").value;
        if (flagTunjProfesional == "Y") {
            document.getElementById("profesional").checked = true;
        } else {
            document.getElementById("profesional").checked = false;
        }

        var flagTunjPeralihanGapok = document.getElementById("flagPeralihanGapok").value;
        if (flagTunjPeralihanGapok == "Y") {
            document.getElementById("peralihanGapok").checked = true;
            $("#nomTunjPeralihanGapok").show();
        } else {
            document.getElementById("peralihanGapok").checked = false;
            $("#nomTunjPeralihanGapok").hide();
        }

        var flagTunjPeralihanSankhus = document.getElementById("flagPeralihanSankhus").value;
        if (flagTunjPeralihanSankhus == "Y") {
            document.getElementById("peralihanSankhus").checked = true;
            $("#nomTunjPeralihanSankhus").show();
        } else {
            document.getElementById("peralihanSankhus").checked = false;
            $("#nomTunjPeralihanSankhus").hide();
        }

        var flagTunjPeralihanTunjangan = document.getElementById("flagPeralihanTunjangan").value;
        if (flagTunjPeralihanTunjangan == "Y") {
            document.getElementById("peralihanTunjangan").checked = true;
            $("#nomTunjPeralihanTunjangan").show();
        } else {
            document.getElementById("peralihanTunjangan").checked = false;
            $("#nomTunjPeralihanTunjangan").hide();
        }

        // var flagMess = document.getElementById("flagMess").value;
        // if (flagMess == "Y") {
        //     document.getElementById("mess").checked = true;
        // } else {
        //     document.getElementById("mess").checked = false;
        // }
//        var flagPLT = document.getElementById("flagPLT").value;
//        if (flagPLT == "Y") {
//            document.getElementById("plt").checked = true;
//        } else {
//            document.getElementById("plt").checked = false;
//        }
        var flagFingerMobile = document.getElementById("flagFingerMobile").value;
        if (flagFingerMobile == "Y") {
            document.getElementById("fingerMobile").checked = true;
        } else {
            document.getElementById("fingerMobile").checked = false;
        }
        var flagTunjRumah = document.getElementById("flagTunjRumah").value;
        if (flagTunjRumah == "Y") {
            document.getElementById("tunjRumah").checked = true;
        } else {
            document.getElementById("tunjRumah").checked = false;
        }
        var flagTunjAir = document.getElementById("flagTunjAir").value;
        if (flagTunjAir == "Y") {
            document.getElementById("tunjAir").checked = true;
        } else {
            document.getElementById("tunjAir").checked = false;
        }
        var flagTunjListrik = document.getElementById("flagTunjListrik").value;
        if (flagTunjListrik == "Y") {
            document.getElementById("tunjListrik").checked = true;
        } else {
            document.getElementById("tunjListrik").checked = false;
        }
        var flagTunjBbm = document.getElementById("flagTunjBbm").value;
        if (flagTunjBbm == "Y") {
            document.getElementById("tunjBbm").checked = true;
        } else {
            document.getElementById("tunjBbm").checked = false;
        }

        // var flagCutiLuar = document.getElementById("flagCuti").value;
        var flagCutiLuar = $("#flagCuti").val();
        if(flagCutiLuar == "Y") {
            $("#flagCutiLuar").prop('checked', true);
        } else {
            $("#flagCutiLuar").prop('checked', false);
        }

        window.loadStudy= function(nip){
            $('.studyTable').find('tbody').remove();
            $('.studyTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            StudyAction.searchData(nip, function (listdata) {

                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Fakultas</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Program Studi</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Ijazah</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Fakultas</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Program Studi</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Ijazah</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        '<td align="center">' + item.fakultasName + '</td>' +
                        '<td align="center">' + item.programStudy + '</td>' +
                        '<td align="center">' + item.tahunAwal + '</td>' +
                        '<td align="center">' + item.tahunAkhir + '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-document' data ='" + item.uploadFile + "' judul ='" + item.studyName + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                        '</a>' +
                        '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        '<td align="center">' + item.fakultasName + '</td>' +
                        '<td align="center">' + item.programStudy + '</td>' +
                        '<td align="center">' + item.tahunAwal + '</td>' +
                        '<td align="center">' + item.tahunAkhir + '</td>' +
                        "</tr>";
                    </s:else>
                });
                $('.studyTable').append(tmp_table);
            });
        };

        function loadStudy(nip) {
            $('.studyTable').find('tbody').remove();
            $('.studyTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            StudyAction.searchData(nip, function (listdata) {
                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Study Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Fakultas</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Program Studi</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tahun Awal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Ijazah</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Fakultas</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Program Studi</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Ijazah</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        '<td align="center">' + item.fakultasName + '</td>' +
                        '<td align="center">' + item.programStudy + '</td>' +
                        '<td align="center">' + item.tahunAwal + '</td>' +
                        '<td align="center">' + item.tahunAkhir + '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-document' data ='" + item.uploadFile + "' judul ='" + item.studyName + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                        '</a>' +
                        '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        '<td align="center">' + item.fakultasName + '</td>' +
                        '<td align="center">' + item.programStudy + '</td>' +
                        '<td align="center">' + item.tahunAwal + '</td>' +
                        '<td align="center">' + item.tahunAkhir + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-document' data ='" + item.uploadFile + "' judul ='" + item.studyName + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:else>
                });
                $('.studyTable').append(tmp_table);
            });

        }

        function loadPengalamanKerja(nip) {
            $('.pengalamanKerjaTable').find('tbody').remove();
            $('.pengalamanKerjaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataPengalamanKerja(nip, function (listdata) {

                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Unit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Profesi</th>" +
                    //                        "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal / Tahun</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Masuk</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Keluar</th>" +

                    "<th style='text-align: center; background-color:  #3c8dbc''>Tipe Pegawai</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Golongan</th>" +
//                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
//                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Cabang</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Profesi</th>" +
                    //                        "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal / Tahun</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Masuk</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Keluar</th>" +

                    "<th style='text-align: center; background-color:  #3c8dbc''>Tipe Pegawai</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Golongan</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.namaPerusahaan + '</td>' +
                        '<td align="center">' + item.jabatan + '</td>' +
                        '<td align="center">' + item.profesiName + '</td>' +
                        '<td align="center">' + item.stTtahunMasuk + '</td>' +
                        '<td align="center">' + item.stTahunKeluar + '</td>' +

                        '<td align="center">' + item.tipePegawai + '</td>' +
                        '<td align="center">' + item.golonganName + '</td>' +
                        <%--'<td align="center">' +--%>
                        <%--"<a href='javascript:;' class ='item-edit' data ='" + item.pengalamanId + "' >" +--%>
                        <%--"<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +--%>
                        <%--'</a>' +--%>
                        <%--'</td>' +--%>
                        <%--'<td align="center">' +--%>
                        <%--"<a href='javascript:;' class ='item-delete' data ='" + item.pengalamanId + "' >" +--%>
                        <%--"<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +--%>
                        <%--'</a>' +--%>
                        <%--'</td>' +--%>
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.namaPerusahaan + '</td>' +
                        '<td align="center">' + item.jabatan + '</td>' +
                        '<td align="center">' + item.profesiName + '</td>' +
                        '<td align="center">' + item.stTtahunMasuk + '</td>' +
                        '<td align="center">' + item.stTahunKeluar + '</td>' +

                        '<td align="center">' + item.tipePegawai + '</td>' +
                        '<td align="center">' + item.golonganName + '</td>' +
                        "</tr>";
                    </s:else>
                });
                $('.pengalamanKerjaTable').append(tmp_table);
            });
        }
        function loadReward(nip) {
            $('.rewardTable').find('tbody').remove();
            $('.rewardTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataReward(nip, function (listdata) {

                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jenis</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jenis</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td align="center">' + item.stTanggal + '</td>' +
                        '<td >' + item.jenis + '</td>' +
                        '<td align="center">' + item.keterangan+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.rewardId+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.rewardId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.stTanggal+ '</td>' +
                        '<td align="center">' + item.jenis+ '</td>' +
                        '<td align="center">' + item.keterangan+ '</td>' +

                        "</tr>";
                    </s:else>
                });
                $('.rewardTable').append(tmp_table);
            });

        }

        function loadSertifikat(nip) {
            $('.pelatihanJabatanTable').find('tbody').remove();
            $('.pelatihanJabatanTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataSertifikat(nip, function (listdata) {

                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Judul Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Penyelenggara</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jml Jam Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Sertifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Ms. Berlaku Setifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Judul Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Penyelenggara</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jml. Jam Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Sertifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Ms. Berlaku Setifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center" >' + (i + 1) + '</td>' +
                        '<td >' + item.jenis+ '</td>' +
                        '<td >' + item.nama + '</td>' +
                        '<td align="center">' + item.lembaga+ '</td>' +
                        '<td align="center">' + item.jumlahHari+ '</td>' +
                        '<td align="center">' + item.lulus+ '</td>' +
                        '<td align="center">' + item.stTanggalPengesahan+ '</td>' +
                        '<td align="center">' + item.prestasiGrade+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-lampiran' sertifikat ='" + item.tempatPelaksana+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.sertifikatId+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.sertifikatId+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.jenis+ '</td>' +
                        '<td >' + item.nama + '</td>' +
                        '<td align="center">' + item.lembaga+ '</td>' +
                        '<td align="center">' + item.jumlahHari+ '</td>' +
                        '<td align="center">' + item.lulus+ '</td>' +
                        '<td align="center">' + item.stTanggalPengesahan+ '</td>' +
                        '<td align="center">' + item.prestasiGrade+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-lampiran' sertifikat ='" + item.tempatPelaksana+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:else>
                });
                $('.pelatihanJabatanTable').append(tmp_table);
            });

        }

        function loadPelatihanJabatan(nip) {
            $('.pelatihanJabatanTable').find('tbody').remove();
            $('.pelatihanJabatanTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataSertifikat(nip, function (listdata) {

                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Judul Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Penyelenggara</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jml Jam Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Sertifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Ms. Berlaku Setifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Judul Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Penyelenggara</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jml Jam Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Sertifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal Pelatihan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Ms. Berlaku Setifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center" >' + (i + 1) + '</td>' +
                        '<td >' + item.jenis + '</td>' +
                        '<td >' + item.nama + '</td>' +
                        '<td align="center">' + item.lembaga + '</td>' +
                        '<td align="center">' + item.jumlahHari + '</td>' +
                        '<td align="center">' + item.lulus + '</td>' +
                        '<td align="center">' + item.stTanggalPengesahan + '</td>' +
                        '<td align="center">' + item.prestasiGrade + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-lampiran' sertifikat ='" + item.tempatPelaksana+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.sertifikatId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.sertifikatId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.jenis + '</td>' +
                        '<td >' + item.nama + '</td>' +
                        '<td align="center">' + item.lembaga + '</td>' +
                        '<td align="center">' + item.jumlahHari + '</td>' +
                        '<td align="center">' + item.lulus + '</td>' +
                        '<td align="center">' + item.stTanggalPengesahan + '</td>' +
                        '<td align="center">' + item.prestasiGrade + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-lampiran' sertifikat ='" + item.tempatPelaksana+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:else>
                });
            })
            $('.pelatihanJabatanTable').append(tmp_table);
        }

        function loadRiwayatPendidikan(nip) {
            //alert(nip);
            $('.studyTable').find('tbody').remove();
            $('.studyTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            StudyAction.searchData(nip, function (listdata) {

                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    /*"<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +*/
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    /*"<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +*/
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "</tr></thead>";
                </s:else>


                var i = i;
                $.each(listdata, function (i, item) {
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        /*'<td align="center">' + item.tahunAwal + '</td>' +*/
                        '<td align="center">' + item.tahunAkhir + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        /*'<td align="center">' + item.tahunAwal + '</td>' +*/
                        '<td align="center">' + item.tahunAkhir + '</td>' +

                        "</tr>";
                    </s:else>
                });
                $('.studyTable').append(tmp_table);
            });

        }
        window.loadSessionStudy= function(){
            $('.studyTable').find('tbody').remove();
            $('.studyTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            StudyAction.searchDataSession(function (listdata) {

                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Ijazah</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        '<td align="center">' + item.tahunAwal + '</td>' +
                        '<td align="center">' + item.tahunAkhir + '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-document' data ='" + item.uploadFile + "' judul ='" + item.studyName + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                        '</a>' +
                        '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.studyTable').append(tmp_table);
            });
        };

        function loadSessionStudy() {
            $('.studyTable').find('tbody').remove();
            $('.studyTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            StudyAction.searchDataSession(function (listdata) {

                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Ijazah</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.typeStudy + '</td>' +
                        '<td align="center">' + item.studyName + '</td>' +
                        '<td align="center">' + item.tahunAwal + '</td>' +
                        '<td align="center">' + item.tahunAkhir + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-document' data ='" + item.uploadFile + "' judul ='" + item.studyName + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                        '</a>' +
                        '</td>' +

                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.studyId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.studyTable').append(tmp_table);
            });
        }

        function loadSessionPengalamanKerja() {
            $('.pengalamanKerjaTable').find('tbody').remove();
            $('.pengalamanKerjaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataSessionPengalamanKerja(function (listdata) {

                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Perusahaan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Masuk</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Keluar</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.branchName + '</td>' +
                        '<td align="center">' + item.jabatanName + '</td>' +
                        '<td align="center">' + item.stTtahunMasuk + '</td>' +
                        '<td align="center">' + item.stTahunKeluar + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.pengalamanId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.pengalamanId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.pengalamanKerjaTable').append(tmp_table);
            });
        }

        function loadSessionReward() {
            $('.rewardTable').find('tbody').remove();
            $('.rewardTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataSessionReward(function (listdata) {
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jenis</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.stTanggal + '</td>' +
                        '<td align="center">' + item.jenis+ '</td>' +
                        '<td align="center">' + item.keterangan+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.rewardId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.rewardId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.rewardTable').append(tmp_table);
            });
        }

        function loadSessionSertifikat() {
            $('.pelatihanJabatanTable').find('tbody').remove();
            $('.pelatihanJabatanTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataSessionSertifikat(function (listdata) {
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jenis</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Sertifikat</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Lembaga</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nilai</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Lulus</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.jenis+ '</td>' +
                        '<td align="center">' + item.nama+ '</td>' +
                        '<td align="center">' + item.lembaga+ '</td>' +
                        '<td align="center">' + item.nilai+ '</td>' +
                        '<td align="center">' + item.lulus+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.sertifikatId+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.sertifikatId+ "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.pelatihanJabatanTable').append(tmp_table);
            });
        }

        function loadSession() {
            $('.pengalamanKerjaTable').find('tbody').remove();
            $('.pengalamanKerjaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            BiodataAction.searchDataSessionPengalamanKerja(function (listdata) {
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Perusahaan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Masuk</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Keluar</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.namaPerusahaan + '</td>' +
                        '<td align="center">' + item.jabatan + '</td>' +
                        '<td align="center">' + item.stTtahunMasuk + '</td>' +
                        '<td align="center">' + item.stTahunKeluar + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.pengalamanId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.pengalamanId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.pengalamanKerjaTable').append(tmp_table);
            });
        }


        function loadKeluarga(nip) {
            //alert(nip);
            $('.keluargaTable').find('tbody').remove();
            $('.keluargaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            KeluargaAction.searchData(nip, "", function (listdata) {
                <s:if test="isAddOrEdit()">
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status Keluarga</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Gender</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Lahir</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";
                </s:if>
                <s:else>
                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status Keluarga</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Gender</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Lahir</th>" +
                    "</tr></thead>";
                </s:else>

                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    <s:if test="isAddOrEdit()">
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.name + '</td>' +
                        '<td align="center">' + item.statusKeluargaName + '</td>' +
                        '<td align="center">' + item.gender + '</td>' +
                        '<td align="center">' + (myDate.getDate()) + ' - ' + ("0" + (myDate.getMonth() + 1)).slice(-2) + ' - ' + myDate.getFullYear() + '</td>' +
                        /*'<td align="center">' + myDate.toTimeString("dd-mm-yy") + '</td>' +*/
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.keluargaId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.keluargaId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                    </s:if>
                    <s:else>
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1 ) + '</td>' +
                        '<td >' + item.name + '</td>' +
                        '<td align="center">' + item.statusKeluargaName + '</td>' +
                        '<td align="center">' + item.gender + '</td>' +
                        '<td align="center">' + (myDate.getDate()) + ' - ' + ("0" + (myDate.getMonth() + 1)).slice(-2) + ' - ' + myDate.getFullYear() + '</td>' +
                        /*'<td align="center">' + myDate.toTimeString("dd-mm-yy") + '</td>' +*/
                        "</tr>";
                    </s:else>
                });
                $('.keluargaTable').append(tmp_table);
            });

        }

        function loadSessionKeluarga() {
            $('.keluargaTable').find('tbody').remove();
            $('.keluargaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            KeluargaAction.searchDataSession(function (listdata) {

                tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status Keluarga</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Lahir</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>" +
                    "</tr></thead>";

                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.name + '</td>' +
                        '<td align="center">' + item.statusKeluargaName + '</td>' +
                        '<td align="center">' + (myDate.getDate()) + ' - ' + ("0" + (myDate.getMonth() + 1)).slice(-2) + ' - ' + myDate.getFullYear() + '</td>' +
                        /*'<td align="center">' + myDate.toTimeString("dd-mm-yy") + '</td>' +*/
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='" + item.statusKeluargaId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='" + item.statusKeluargaId + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.keluargaTable').append(tmp_table);
            });

        }

        $('#btnSave').click(function () {
            var url = $('#myForm').attr('action');
            var data = $('#myForm').serialize();

            var dataStudy = "";
            var id = document.getElementById("studyId").value;
            var nip = document.getElementById("nip1").value;
            var typeStudy = document.getElementById("studyTypeStudy").value;
            var studyName = document.getElementById("studyName").value;
            var programStudy = document.getElementById("pendidikanProgramStudi").value;
            var fakultasId = document.getElementById("studyFakultas").value;
            var tahunAwal = document.getElementById("studyTahunAwal").value;
//            var tahunAwal = "";
            var tahunAkhir = document.getElementById("studyTahunAkhir").value;
//            var uploadIjazah = document.getElementById("img_ijazah_canvas");
//            var dataURL = uploadIjazah.toDataURL("image/png"),
//                dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

//            dataStudy = {
//                'nip':nip,
//                'typeStudy':typeStudy,
//                'studyName':studyName,
//                'programStudy':programStudy,
//                'fakultasId':fakultasId,
//                'tahunAwal':tahunAwal,
//                'tahunAkhir':tahunAkhir,
//                'img_ijazah':dataURL
//            };

//            var objectString = JSON.stringify(dataStudy);
            var result = '';
            <s:if test="isAdd()">
            if (url == 'addStudy') {
                if (studyName == '') {
                    alert('Study Name Must be Entry')
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        StudyAction.saveAdd(typeStudy, studyName, tahunAwal, tahunAkhir, programStudy, function (listdata) {
                            alert('Data Successfully Added');
                            $('#modal-edit').modal('hide');
                            $('#myForm')[0].reset();
                            loadSessionStudy();
                        });
//                        StudyAction.saveAdd(objectString, {callback: function (response) {
//                            if (response.status == "success"){
//                                alert('Data Successfully Added');
//                                $('#modal-edit').modal('hide');
//                                $('#myForm')[0].reset();
//                                loadSessionStudy();
//                            }
//                        }});
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    StudyAction.initEdit(id, typeStudy, studyName, tahunAwal, tahunAkhir, function (listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        loadSessionStudy();
                    });
                }
            }
            </s:if>
            <s:else>
            if (url == 'addStudy') {
                if (studyName == '') {
                    alert('Study Name Must be Entry')
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        StudyAction.saveAddData(nip, typeStudy, studyName, tahunAwal, tahunAkhir, fakultasId, programStudy, function (listdata) {
                            alert('Data Successfully Added');
                            $('#modal-edit').modal('hide');
                            $('#myForm')[0].reset();
                            loadStudy(nip);
                        });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    StudyAction.saveEdit(id, nip, typeStudy, studyName, tahunAwal, tahunAkhir, fakultasId, programStudy, function (listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        loadStudy(nip);
                    });
                }
            }
            </s:else>


        });

        $('#btnSavePengalaman').click(function () {
            var url = $('#myFormPengalaman').attr('action');
            var data = $('#myFormPengalaman').serialize();

            var id = document.getElementById("pengalamanId").value;
            var nip = document.getElementById("nip1").value;
            var branchId = document.getElementById("branchIdRiwayatKerja").value;
            var divisiId = document.getElementById("departmentId").value;
            var posisiId = document.getElementById("positionId3").value;
            var profesiId = document.getElementById("profesi3").value;
            var tanggal = document.getElementById("pengalamanTanggalMasuk").value;
            var tanggalKeluar = document.getElementById("pengalamanTanggalKeluar").value;
            var tipePegawaiId = document.getElementById("pengalamanTipePegawaiId").value;
            if(tipePegawaiId=="TP03"){
                var golonganId = document.getElementById("pengalamanGolonganId1").value;
            }
            if(tipePegawaiId=="TP04"){
                var golonganId = document.getElementById("golonganHistory3").value;
            }
//            var pjsFlag = document.getElementById("pjsFlag1").value;
            var aktifFlag = document.getElementById("flagAktif1").value;
            var bidangLain = document.getElementById("namaBidangLain").value;
            var jabatanLain = document.getElementById("namaJabatanLain").value;
            var jenisPegawaiId = document.getElementById("jenisPegawaiId").value;
            var flagDigaji = document.getElementById("flagDigaji").value;

            var result = '';
            <s:if test="isAdd()">
            if (url == 'addPengalamanKerja') {
                if (branchId == '' || divisiId == '' || posisiId == '' || tanggal == ''|| tipePegawaiId =='' || profesiId =='' || jenisPegawaiId == '' || flagDigaji == '') {
                    alert('Semua Field Harus Diisi !');
                } else {

                    var msg ="Field:  \n";
                    var msg2 ="";
                    if (branchId == '' || divisiId == '' || posisiId == '' || tanggal == ''||tipePegawaiId =='') {
                        if(branchId == ''){
                            msg+="- Nama Perusahaan\n";
                        }
                        if(divisiId == ''){
                            msg+="- Bidang\n";
                        }
                        if(posisiId == ''){
                            msg+="- Jabatan\n";
                        }
                        if(profesiId == ''){
                            msg+="- Profesi\n";
                        }
                        if(tanggal == ''){
                            msg+="- Tanggal Diangkat\n";
                        }else{
                            if(tanggal.length <10){
                                msg2+="- Format Tanggal Diangkat Salah\n";
                            }
                        }
                        if(tipePegawaiId ==''){
                            msg+="- Tipe Pegawai\n";
                        }
                        alert(msg+"Harus Diisi\n"+msg2);
                    }else if(tanggalKeluar == ''){

                        if(aktifFlag == 'N'){
                            msg+="- Jabatan aktif harus 'YA' apabila Tanggal Selesai kosong\n";
                            alert(msg);
                        }else {
                            savePengalaman(nip, branchId, posisiId, divisiId, profesiId,
                                tanggal, tanggalKeluar, tipePegawaiId, golonganId, "",
                                aktifFlag, jenisPegawaiId, flagDigaji);

//                            if (confirm('Are you sure you want to save this Record?')) {

//                                    dwr.engine.setAsync(false);
//                                    dwr.engine.beginBatch();
//                                    BiodataAction.saveAddPengalaman(nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, jenisPegawaiId, flagDigaji, function (listdata) {
//                                        alert('Data Berhasil Disimpan');
//                                        $('#modal-pengalamanKerja').modal('hide');
//                                        $('#myFormPengalaman')[0].reset();
//                                        loadSessionPengalamanKerja();
//                                    });
//                                    dwr.engine.endBatch({
//                                        errorHandler:function(errorString, exception){
//                                            alert('Jabatan aktif sudah ada');
//                                        }
//                                    });
//                            }
                        }

                    }else {

                        if(aktifFlag == 'N'){
                            if(tanggalKeluar.length <10) {
                                msg += "- Format Tanggal Selesai Salah\n";
                                alert(msg);
                            }
                        } else if (aktifFlag == 'Y'){
                            msg+="- Tanggal Selesai harus kosong apabila jabatan masih aktif\n";
                            alert(msg);
                            $('#pengalamanTanggalKeluar').val('');
                        }else {
                            if (confirm('Apakah anda yakin ingin menyimpan data?')) {
                                dwr.engine.setAsync(false);
                                dwr.engine.beginBatch();
                                BiodataAction.saveAddPengalaman(nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag,function (listdata) {
                                    alert('Data Berhasil Disimpan');
                                    $('#modal-pengalamanKerja').modal('hide');
                                    $('#myFormPengalaman')[0].reset();
                                    loadSessionPengalamanKerja();
                                });

                                dwr.engine.endBatch({
                                    errorHandler:function(errorString, exception){
                                        alert(exception+' Jabatan aktif sudah ada');
                                    }
                                });
                            }
                        }
                    }
                }
            } else {
                if (branchId == '' || divisiId == '' || posisiId == '' || tanggal == ''|| tipePegawaiId =='' || profesiId =='') {
                    alert('Semua Field Harus Diisi !');
                }else{
                    var msg ="Field:  \n";
                    var msg2 ="";
                    if (branchId == '' || divisiId == '' || posisiId == '' || tanggal == ''||tipePegawaiId =='') {
                        if(branchId == ''){
                            msg+="- Nama Perusahaan\n";
                        }
                        if(divisiId == ''){
                            msg+="- Bidang\n";
                        }
                        if(posisiId == ''){
                            msg+="- Jabatan\n";
                        }
                        if(profesiId == ''){
                            msg+="- Profesi\n";
                        }
                        if(tanggal == ''){
                            msg+="- Tanggal Diangkat\n";
                        }else{
                            if(tanggal.length <10){
                                msg2+="- Format Tanggal Diangkat Salah\n";
                            }
                        }
//                        if(tanggalKeluar == ''){
//                            if(aktifFlag == 'N'){
//                                msg+="- Tanggal Selesai\n";
//                            }
//                        }else{
//                            if(tanggalKeluar.length <10){
//                                if(aktifFlag == 'N'){
//                                    msg2+="- Format Tanggal Selesai Salah\n";
//                                }
//                            }
//                        }
                        if(tipePegawaiId ==''){
                            msg+="- Tipe Pegawai\n";
                        }
                        alert(msg+"Harus Diisi\n"+msg2);
                    }else if(tanggalKeluar == ''){
                        if (tanggalKeluar == ''){
                            if(aktifFlag == 'N'){
                                msg+="- Jabatan aktif harus 'YA' apabila Tanggal Selesai kosong\n";
                                alert(msg);
                            }else{
                                if (confirm('Are you sure you want to save this Record?')) {
                                    dwr.engine.setAsync(false);
//                            BiodataAction.initEditPengalaman(id, nip, namaPerusahaan, posisiId, tanggalMasuk, tanggalKeluar, function (listdata) {
//                                alert('Data Successfully Updated');
//                                $('#modal-pengalamanKerja').modal('hide');
//                                $('#myFormPengalaman')[0].reset();
//                                loadSessionPengalamanKerja();
//                            });
                                    BiodataAction.initEditPengalaman(id, nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, function (listdata) {
                                        alert('Data Successfully Updated');
                                        $('#modal-pengalamanKerja').modal('hide');
                                        $('#myFormPengalaman')[0].reset();
                                        loadSessionPengalamanKerja();
                                    });
                                }
                            }
                        }
                    }else{
                        if (aktifFlag == 'Y'){
                            msg+="- Tanggal Selesai harus kosong apabila jabatan masih aktif\n";
                            alert(msg);
                            $('#pengalamanTanggalKeluar').val('');
                        }else{
                            if (confirm('Are you sure you want to save this Record?')) {
                                dwr.engine.setAsync(false);
//                            BiodataAction.initEditPengalaman(id, nip, namaPerusahaan, posisiId, tanggalMasuk, tanggalKeluar, function (listdata) {
//                                alert('Data Successfully Updated');
//                                $('#modal-pengalamanKerja').modal('hide');
//                                $('#myFormPengalaman')[0].reset();
//                                loadSessionPengalamanKerja();
//                            });
                                BiodataAction.initEditPengalaman(id, nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, function (listdata) {
                                    alert('Data Successfully Updated');
                                    $('#modal-pengalamanKerja').modal('hide');
                                    $('#myFormPengalaman')[0].reset();
                                    loadSessionPengalamanKerja();
                                });
                            }
                        }
                    }
                }
            }
            </s:if>
            <s:else>
            if (url == 'addPengalamanKerja') {
                if (branchId == '' && divisiId == '' && posisiId == '' && tanggal == ''&& tanggalKeluar ==''&& tipePegawaiId ==''|| profesiId=='') {
                    alert('Isi Field Terlebih Dahulu');
                } else {
                    var msg ="Field:  \n";
                    var msg2 ="";
                    if (branchId == '' || divisiId == '' || posisiId == '' || tanggal == ''||tipePegawaiId =='') {
                        if(branchId == ''){
                            msg+="- Nama Perusahaan\n";
                        }
                        if(divisiId == ''){
                            msg+="- Bidang\n";
                        }
                        if(posisiId == ''){
                            msg+="- Jabatan\n";
                        }
                        if(profesiId == ''){
                            msg+="- Profesi\n";
                        }
                        if(tanggal == ''){
                            msg+="- Tanggal Diangkat\n";
                        }else{
                            if(tanggal.length <10){
                                msg2+="- Format Tanggal Diangkat Salah\n";
                            }
                        }
                        if(tipePegawaiId ==''){
                            msg+="- Tipe Pegawai\n";
                        }

                        alert(msg+"Harus Diisi\n"+msg2);
                    }
                    else if(tanggalKeluar == ''){
                        if(tanggalKeluar == ''){
                            if(aktifFlag == 'N'){
                                msg+="- Jabatan aktif harus 'YA' apabila Tanggal Selesai kosong\n";
                                alert(msg);
                            }else {
                                if (confirm('Are you sure you want to save this Record?')) {
                                    dwr.engine.setAsync(false);
                                    dwr.engine.beginBatch();
                                    BiodataAction.saveAddDataPengalamaKerja(nip, branchId, divisiId, posisiId, tanggal,tanggalKeluar, tipePegawaiId,
                                        golonganId, pjsFlag, perusahaanLain, bidangLain, jabatanLain, aktifFlag,profesiId, function (listdata) {
                                            alert('Data Successfully Added');
                                            $('#modal-pengalamanKerja').modal('hide');
                                            $('#myFormPengalaman')[0].reset();
                                            loadPengalamanKerja(nip);
                                        });
                                    dwr.engine.endBatch({
                                        errorHandler:function(errorString, exception){
                                            alert('Jabatan aktif sudah ada');
                                        }
                                    });
                                }
                            }
                        }else{
                            if(tanggalKeluar.length <10){
                                if(aktifFlag == 'N'){
                                    msg+="- Format Tanggal Selesai Salah\n";
                                    alert(msg);
                                }
                            }
                        }
                    }
                    else{
                        if (aktifFlag == 'Y'){
                            msg+="- Tanggal Selesai harus kosong apabila jabatan masih aktif\n";
                            alert(msg);
                            $('#pengalamanTanggalKeluar').val('');
                        }else{
                            if (confirm('Are you sure you want to save this Record?')) {
                                dwr.engine.setAsync(false);
                                BiodataAction.saveAddDataPengalamaKerja(nip, branchId, divisiId, posisiId, tanggal,tanggalKeluar, tipePegawaiId,
                                    golonganId, pjsFlag, perusahaanLain, bidangLain, jabatanLain, aktifFlag,profesiId,  function (listdata) {
                                        alert('Data Successfully Added');
                                        $('#modal-pengalamanKerja').modal('hide');
                                        $('#myFormPengalaman')[0].reset();
                                        loadPengalamanKerja(nip);
                                    });
                            }
                        }

                    }

                }
            } else {
                if (branchId == '' && divisiId == '' && posisiId == '' && tanggal == ''&& tipePegawaiId == '') {
                    alert('Isi Field Terlebih Dahulu');
                } else {
                    var msg ="Field:  \n";
                    var msg2 ="";
                    if (branchId == '' || divisiId == '' || posisiId == '' || tanggal == ''||  tipePegawaiId=='') {
                        if(branchId == ''){
                            msg+="- Nama Perusahaan\n";
                        }
                        if(divisiId == ''){
                            msg+="- Bidang\n";
                        }
                        if(posisiId == ''){
                            msg+="- Jabatan\n";
                        }
                        if(tanggal == ''){
                            msg+="- Tanggal Diangkat\n";
                        }else{
                            if(tanggal.length <10){
                                msg2+="- Format Tanggal Diangkat Salah\n";
                            }
                        }
                        if(tipePegawaiId ==''){
                            msg+="- Tipe Pegawai\n";
                        }
                        alert(msg+"Harus Diisi\n"+msg2);
                    }else if(tanggalKeluar==''&&aktifFlag =='N'){
                        var msg ="Field:  \n";
                        var msg2 ="";
                        if(tanggalKeluar == ''){
                            if(aktifFlag =='N'){
                                msg+="- Tanggal Selesai\n";
                            }
                        }else{
                            if(tanggalKeluar.length <10){
                                msg2+="- Format Tanggal Selesai Salah\n";
                            }
                        }
                        alert(msg+"Harus Diisi\n"+msg2);
                    }
                    else{
                        if (aktifFlag == 'Y' && tanggalKeluar != ''){
                            msg+="- Tanggal Selesai harus kosong apabila jabatan masih aktif\n";
                            alert(msg);
                            $('#pengalamanTanggalKeluar').val('');
                        }
                        else{
                            if (confirm('Are you sure you want to save this Record?')) {
                                dwr.engine.setAsync(false);
                                dwr.engine.beginBatch();
                                BiodataAction.saveEditPengalamanKerja(id, nip, branchId, divisiId, posisiId, tanggal,tanggalKeluar, tipePegawaiId,
                                    golonganId, perusahaanLain, bidangLain, jabatanLain, aktifFlag, profesiId, pjsFlag, function (listdata) {
                                        alert('Data Successfully Updated');
                                        $('#modal-pengalamanKerja').modal('hide');
                                        $('#myFormPengalaman')[0].reset();
                                        loadPengalamanKerja(nip);
                                    });
                                dwr.engine.endBatch({
                                    errorHandler:function(errorString, exception){
                                        alert('Jabatan aktif sudah ada');
                                    }
                                });
                            }
                        }
                    }
                }
            }
            </s:else>
        });

        $('#btnSaveReward').click(function () {
            var url = $('#myFormReward').attr('action');
            var data = $('#myFormReward').serialize();

            var id = document.getElementById("rewardId").value;
            var nip = document.getElementById("nip1").value;
            var tanggal = document.getElementById("rewardTanggal").value;
            var jenis = document.getElementById("rewardJenis").value;
            var keterangan = document.getElementById("rewardKeterangan").value;

            var result = '';
            <s:if test="isAdd()">
            if (url == 'addReward') {
                if (tanggal == '' && jenis == '' && keterangan == '') {
                    alert('Semua Field Harus Diisi !');
                } else {
                    if (confirm('Apakah anda yakin ingin menyimpan data?')) {
                        dwr.engine.setAsync(false);
                        BiodataAction.saveAddReward(nip, tanggal, jenis, keterangan, function (listdata) {
                            alert('Data Berhasil Disimpan');
                            $('#modal-reward').modal('hide');
                            $('#myFormReward')[0].reset();
                            loadSessionReward();
                        });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    BiodataAction.initEditReward(id, nip, tanggal, jenis, keterangan, function (listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-reward').modal('hide');
                        $('#myFormReward')[0].reset();
                        loadSessionReward();
                    });
                }
            }
            </s:if>
            <s:else>
            if (url == 'addReward') {
                if (tanggal == '') {
                    alert('Tanggal Reward Harus Diisi');
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        BiodataAction.saveAddDataReward(nip, tanggal, jenis, keterangan, function (listdata) {
                            alert('Data Successfully Added');
                            $('#modal-reward').modal('hide');
                            $('#myFormReward')[0].reset();
                            loadReward(nip);
                        });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    BiodataAction.saveEditReward(id, nip, tanggal, jenis, keterangan, function (listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-reward').modal('hide');
                        $('#myFormReward')[0].reset();
                        loadReward(nip);
                    });
                }
            }
            </s:else>
        });

        $('#btnSaveSertifikat').click(function () {
            var url = $('#myFormSertifikat').attr('action');
            var data = $('#myFormSertifikat').serialize();

            var id = document.getElementById("sertifikatId").value;
            var nip = document.getElementById("nip1").value;
            var jenis = document.getElementById("sertifikatJenis").value;
            var tanggalPengesahan = document.getElementById("sertifikatTanggalPengesahan").value;
            var masaBerlaku = document.getElementById("sertifikatMasaBerlaku").value;
            var masaBerakhir = document.getElementById("sertifikatMasaBerakhir").value;
            var nama = document.getElementById("sertifikatNama").value;
            var lembaga = document.getElementById("sertifikatLembaga").value;
            var tempatPelaksana = document.getElementById("sertifikatTempatPelaksana").value;
            var jumlahHari = document.getElementById("sertifikatJumlahHari").value;
            var nilai = document.getElementById("sertifikatNilai").value;
            var lulus = document.getElementById("sertifikatLulus").value;
            var prestasi = document.getElementById("sertifikatPrestasi").value;

            var result = '';
            <s:if test="isAdd()">
            if (url == 'addSertifikat') {
                if (jenis == '' && tanggalPengesahan == '' && masaBerlaku != '' && masaBerakhir != '' && nama != '' && lembaga != '' && tempatPelaksana != '' &&
                    nilai != '' && lulus != '' && prestasi != '') {
                    alert('Semua Field Harus Diisi !');
                } else {
                    if (confirm('Apakah anda yakin ingin menyimpan data?')) {
                        dwr.engine.setAsync(false);
                        BiodataAction.saveAddSertifikat(nip, jenis, tanggalPengesahan, masaBerlaku, masaBerakhir, nama, lembaga, tempatPelaksana,
                            nilai, lulus, prestasi, function (listdata) {
                                alert('Data Berhasil Disimpan');
                                $('#modal-sertifikat').modal('hide');
                                $('#myFormSertifikat')[0].reset();
                                loadSessionSertifikat();
                            });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    BiodataAction.initEditSertifikat(id, nip, jenis, tanggalPengesahan, masaBerlaku, masaBerakhir, nama, lembaga, tempatPelaksana,
                        nilai, lulus, prestasi, function (listdata) {
                            alert('Data Successfully Updated');
                            $('#modal-sertifikat').modal('hide');
                            $('#myFormSertifikat')[0].reset();
                            loadSessionSertifikat();
                        });
                }
            }
            </s:if>
            <s:else>
            if (url == 'addSertifikat') {
                if (jenis == '') {
                    alert('Jenis Sertifikat Harus Diisi');
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        BiodataAction.saveAddDataSertifikat(nip, jenis, tanggalPengesahan, masaBerlaku, masaBerakhir, nama, lembaga, tempatPelaksana,
                            nilai, lulus, prestasi, jumlahHari, function (listdata) {
                                alert('Data Successfully Added');
                                $('#modal-sertifikat').modal('hide');
                                $('#myFormSertifikat')[0].reset();
                                loadSertifikat(nip);
                            });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    BiodataAction.saveEditSertifikat(id, nip, jenis, tanggalPengesahan, masaBerlaku, masaBerakhir, nama, lembaga, tempatPelaksana,
                        nilai, lulus, prestasi, jumlahHari, function (listdata) {
                            alert('Data Successfully Updated');
                            $('#modal-sertifikat').modal('hide');
                            $('#myFormSertifikat')[0].reset();
                            loadSertifikat(nip);
                        });
                }
            }
            </s:else>
        });

        function handleFileSelect(evt) {
            var f = evt.target.files[0]; // FileList object
            var reader = new FileReader();
            // Closure to capture the file information.
            reader.onload = (function(theFile) {
                return function(e) {
                    var binaryData = e.target.result;
                    //Converting Binary Data to base 64
                    var base64String = window.btoa(binaryData);
                    //showing file converted to base64
                    document.getElementById('base64sertifikat').value = base64String;
                };
            })(f);
            // Read in the image file as a data URL.
            reader.readAsBinaryString(f);
        }

        // Check for the File API support.
        if (window.File && window.FileReader && window.FileList && window.Blob) {
            document.getElementById('fileSertifikat').addEventListener('change', handleFileSelect, false);
        } else {
            alert('The File APIs are not fully supported in this browser.');
        }

        $('#btnSavePelatihan').click(function () {
            var url = $('#myFormPelatihanJabatan').attr('action');
            var data = $('#myFormPelatihanJabatan').serialize();

            var id = document.getElementById("pelatihanId").value;
            var nip = document.getElementById("nip1").value;
            var namaPelatihan = document.getElementById("namaPelatihan").value;
            var judulPelatihan = document.getElementById("judulPelatihan").value;
            var penyelenggara = document.getElementById("pelatihanLembaga").value;
            var jumlahJamPelatihan = document.getElementById("jumlahJamPelatihan").value;
            var sertifikatPelatihan = document.getElementById("sertifikasiPelatihan").value;
            var tanggalPelatihan = document.getElementById("tanggalPelatihan").value;
            var masaBerlakuSertifikat = document.getElementById("masaBerlakuSertifikat").value;
            var gambar = document.getElementById("base64sertifikat").value;

            if (nip == ''||namaPelatihan==""||judulPelatihan==""||penyelenggara==""||jumlahJamPelatihan==""
                ||sertifikatPelatihan==""||tanggalPelatihan==""||masaBerlakuSertifikat=="") {
                var msg="Field \n"
                if (nip==""){
                    msg+="- NIP \n";
                }
                if (namaPelatihan==""){
                    msg+="- Nama Pelatihan \n";
                }
                if (judulPelatihan==""){
                    msg+="- Judul Pelatihan \n";
                }
                if (penyelenggara==""){
                    msg+="- Penyelenggara \n";
                }
                if (jumlahJamPelatihan==""){
                    msg+="- Jumlah Jam Pelatihan \n";
                }
                if (sertifikatPelatihan==""){
                    msg+="- Sertifikat Pelatihan \n";
                }
                if (tanggalPelatihan==""){
                    msg+="- TanggalPelatihan \n";
                }
                if (masaBerlakuSertifikat==""){
                    msg+="- Masa Berlaku Sertifikat \n";
                }
                msg+="Wajib diisi";
                alert(msg);
            } else {
                var result = '';
                <s:if test="isAdd()">
                if (url == 'addPelatihanJabatan') {
                    if (jenis == '') {
                        alert('Semua Field Harus Diisi !');
                    } else {
                        if (confirm('Apakah anda yakin ingin menyimpan data?')) {
                            dwr.engine.setAsync(false);
                            BiodataAction.saveAddSertifikat(nip, namaPelatihan , judulPelatihan , penyelenggara , jumlahJamPelatihan ,
                                sertifikatPelatihan , tanggalPelatihan , masaBerlakuSertifikat,gambar, function () {
                                    alert('Data Berhasil Disimpan');
                                    $('#modal-sertifikat').modal('hide');
                                    $('#myFormSertifikat')[0].reset();
                                    loadSessionSertifikat();
                                });
                        }
                    }
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        BiodataAction.initEditSertifikat(id, nip, namaPelatihan , judulPelatihan , penyelenggara , jumlahJamPelatihan ,
                            sertifikatPelatihan , tanggalPelatihan , masaBerlakuSertifikat,gambar, function () {
                                alert('Data Successfully Updated');
                                $('#modal-sertifikat').modal('hide');
                                $('#myFormSertifikat')[0].reset();
                                loadSessionSertifikat();
                            });
                    }
                }
                </s:if>
                <s:else>
                if (url == 'addPelatihanJabatan') {
                    if (jenis == '') {
                        alert('Jenis Pelatihan Harus Diisi');
                    } else {
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            BiodataAction.saveAddDataPelatihan(nip, namaPelatihan , judulPelatihan , penyelenggara , jumlahJamPelatihan ,
                                sertifikatPelatihan , tanggalPelatihan , masaBerlakuSertifikat,gambar, function () {
                                    alert('Data Successfully Added');
                                    $('#modal-pelatihanJabatan').modal('hide');
                                    $('#myFormPelatihanJabatan')[0].reset();
                                    loadPelatihanJabatan(nip);
                                });
                        }
                    }
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        BiodataAction.saveEditPelatihan(id, nip, namaPelatihan , judulPelatihan , penyelenggara , jumlahJamPelatihan ,
                            sertifikatPelatihan , tanggalPelatihan , masaBerlakuSertifikat,gambar, function () {
                                alert('Data Successfully Updated');
                                $('#modal-pelatihanJabatan').modal('hide');
                                $('#myFormPelatihanJabatan')[0].reset();
                                loadPelatihanJabatan(nip);
                            });
                    }
                }
                </s:else>
            }
        });

        $('#btnSaveKeluarga').click(function () {
            var url = $('#myFormKeluarga').attr('action');

            var keluargaIdLama = document.getElementById("keluargaIdLama").value;
            var keluargaId = document.getElementById("keluargaId").value;
            var genderKeluarga = document.getElementById("genderKeluarga").value;
            var nip = document.getElementById("nip1").value;
            var keluargaName = document.getElementById("keluargaName").value;
            var statusKeluarga = document.getElementById("statusKeluarga").value;
            var statusKeluargaName = $('select[name=statusKeluarga] option:selected').text();
            var tanggalLahir = document.getElementById("tanggalLahirkeluarga").value;

            var result = '';

            <s:if test="isAdd()">
            if (url == 'addKeluarga') {
                if (keluargaName == '') {
                    alert('Name Must be Entry')
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        KeluargaAction.saveAdd(keluargaName, statusKeluarga, statusKeluargaName, tanggalLahir, genderKeluarga,function (listdata) {
                            alert('Data Successfully Added');
                            $('#modal-editKeluarga').modal('hide');
                            $('#myFormKeluarga')[0].reset();
                            loadSessionKeluarga();
                        });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    KeluargaAction.initEdit(keluargaId, keluargaName, statusKeluarga, statusKeluargaName, tanggalLahir, genderKeluarga,function (listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-editKeluarga').modal('hide');
                        $('#myFormKeluarga')[0].reset();
                        loadSessionKeluarga();
                    });
                }
            }
            </s:if>
            <s:else>
            if (url == 'addKeluarga') {
                if (keluargaName == '') {
                    alert('Name Must be Entry')
                } else {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        KeluargaAction.saveAddData(nip, keluargaName, statusKeluarga, genderKeluarga, tanggalLahir, function (listdata) {
                            alert('Data Successfully Added');
                            $('#modal-editKeluarga').modal('hide');
                            $('#myFormKeluarga')[0].reset();
                            loadKeluarga(nip);
                        });
                    }
                }
            } else {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    KeluargaAction.saveEdit(keluargaId, keluargaName, statusKeluarga, genderKeluarga, tanggalLahir, function (listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-editKeluarga').modal('hide');
                        $('#myFormKeluarga')[0].reset();
                        loadKeluarga(nip);
                    });
                }
            }
            </s:else>
        });

        $(".nav-tabs a").click(function () {
            var nip = document.getElementById("nip1").value;
            var target = $(this).attr('href');

            if (target == "#RiwayatPendidikan") {
                <s:if test="isAdd()">
                loadSessionStudy();
                </s:if>
                <s:else>
                loadStudy(nip);
                </s:else>

            } else if (target == "#keluarga") {
                <s:if test="isAdd()">
                loadSessionKeluarga();
                </s:if>
                <s:else>
                loadKeluarga(nip);
                </s:else>
            } else if (target == "#pengalamanKerja") {
                <s:if test="isAdd()">
                loadSessionPengalamanKerja();
                </s:if>
                <s:else>
                loadPengalamanKerja(nip);
                </s:else>
            }else if (target == "#reward") {
                <s:if test="isAdd()">
                loadSessionReward();
                </s:if>
                <s:else>
                loadReward(nip);
                </s:else>
            }else if (target == "#sertifikat") {
                <s:if test="isAdd()">
                loadSessionSertifikat();
                </s:if>
                <s:else>
                loadSertifikat(nip);
                </s:else>
            }else if (target == "#pelatihanJabatan") {
                <s:if test="isAdd()">
                loadSessionSertifikat();
                </s:if>
                <s:else>
                loadSertifikat(nip);
                </s:else>
            }
            $(this).tab('show');
        });

        $('#btnAdd').click(function () {
            listPendidikanFakultas();
//            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myFormDocument').attr('proses', 'addStudy');
            $('#modal-edit').find('.modal-title').text('Add Pendidikan');
        });

        $('#btnAddReward').click(function () {
            $('#myFormReward')[0].reset();
            $('#modal-reward').modal('show');
            $('#myFormReward').attr('action', 'addReward');
            $('#modal-reward').find('.modal-title').text('Add Reward');
        });

        $('#btnAddSertifikat').click(function () {
            $('#myFormSertifikat')[0].reset();
            $('#modal-sertifikat').modal('show');
            $('#myFormSertifikat').attr('action', 'addSertifikat');
            $('#modal-sertifikat').find('.modal-title').text('Add Sertifikat');
        });

        $('#btnAddPelatihanJabatan').click(function () {
            $('#myFormPelatihanJabatan')[0].reset();
            $('#modal-pelatihanJabatan').modal('show');
            $('#myFormPelatihanJabatan').attr('action', 'addPelatihanJabatan');
            $('#modal-pelatihanJabatan').find('.modal-title').text('Add Pelatihan');
        });

        $('#btnAddPengalamanKerja').click(function () {
            $('#myFormPengalaman')[0].reset();
            $('#pengalamanGolonganId1').show();
            $('#golonganHistory2Group').hide();
            cekPerusahaan();
            $('#modal-pengalamanKerja').modal('show');
            $('#myFormPengalaman').attr('action', 'addPengalamanKerja');
            $('#modal-pengalamanKerja').find('.modal-title').text('Add Riwayat Kerja');
        });

        $('#btnAddKeluarga').click(function () {
            listStatusKeluarga();
            $('#myFormKeluarga')[0].reset();
            $('#modal-editKeluarga').modal('show');
            $('#myFormKeluarga').attr('action', 'addKeluarga');
            $('#modal-editKeluarga').find('.modal-title').text('Add Keluarga');
        });

        $('.studyTable').on('click', '.item-view-document', function(){
            var id = $(this).attr('data');
            var judul = $(this).attr('judul');
            dwr.engine.setAsync(false);
            $("#my-image").attr("src","/mnt/ijazah/" + id);
            $('#modal-view-document').find('.modal-title').text(judul);
            $('#modal-view-document').modal('show');
            $('#ViewDocument').attr('action', 'editPerson');
        })

        $('.studyTable').on('click', '.item-edit', function () {
            var id = $(this).attr('data');
            listPendidikanFakultas1();

            <s:if test="isAdd()">
//            StudyAction.initSearch(id, function (listdata) {
//                $('#studyName').val(listdata.studyName);
//                $('#studyTahunAwal').val(listdata.tahunAwal);
//                $('#studyTahunAkhir').val(listdata.tahunAkhir);
//                $('#pendidikanProgramStudi').val(item.programStudy);
//                $('#studyFakultas').val(item.fakultasId).change();
//                $('#studyTypeStudy').val(listdata.typeStudy).change();
//                $('#studyId').val(listdata.studyId);
//                $('#fileUpload').val(listdata.uploadFile);
//            });
            StudyAction.initSearch(id, function (listdata) {
                $.each(listdata, function(i, item){
                    $('#studyName').val(listdata.studyName);
                    $('#studyTahunAwal').val(listdata.tahunAwal);
                    $('#studyTahunAkhir').val(listdata.tahunAkhir);
                    $('#pendidikanProgramStudi').val(listdata.programStudy);
                    $('#studyFakultas1').val(listdata.studyFakultas).change();
                    $('#studyTypeStudy').val(listdata.typeStudy).change();
                    $('#studyId').val(listdata.studyId);
                    $('#cpiddoc').val(listdata.uploadFile);
                });
            });
            </s:if>
            <s:else>
            StudyAction.searchDataEdit(id, function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#studyName').val(item.studyName);
                    $('#studyTahunAwal').val(item.tahunAwal);
                    $('#studyTahunAkhir').val(item.tahunAkhir);
                    $('#pendidikanProgramStudi').val(item.programStudy);
                    $('#studyFakultas1').val(item.fakultasId).change();
                    $('#studyTypeStudy').val(item.typeStudy).change();
                    $('#studyId').val(item.studyId);
                    $('#cpiddoc').val(item.uploadFile);
                });
            });
            </s:else>

            $('#modal-edit-study').find('.modal-title').text('Edit Data');
            $('#modal-edit-study').modal('show');
            $('#myForm').attr('action', 'editStudy');
        });

        $('.pengalamanKerjaTable').on('click', '.item-edit', function () {
            var id = $(this).attr('data');
            <s:if test="isAdd()">
            BiodataAction.initSearchPengalamanKerja(id, function (listdata) {
//                $('#pengalamanPerusahaan').val(listdata.namaPerusahaan);
//                $('#pengalamanJabatan').val(listdata.jabatan);
//                $('#pengalamanTanggalMasuk').val(listdata.stTtahunMasuk);
//                $('#pengalamanTanggalKeluar').val(listdata.stTahunKeluar);
//                $('#pengalamanId').val(listdata.pengalamanId);

                $('#branchIdRiwayatKerja').val(listdata.branchId);
                $('#departmentId').val(listdata.divisiId);
                $('#positionId3').val(listdata.jabatan);
                $('#profesi3').val(listdata.profesiId);
                $('#pengalamanTanggalMasuk').val(listdata.stTtahunMasuk);
                $('#pengalamanTanggalKeluar').val(listdata.stTahunKeluar);
                $('#pengalamanId').val(listdata.pengalamanId);
                $('#pengalamanGolonganId1').val(listdata.golonganId);

                $('#pengalamanTipePegawaiId').val(listdata.tipePegawaiId);
//                $('#pjsFlag1').val(listdata.pjsFlag);
                $('#flagAktif1').val(listdata.flagJabatanAktif);
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditPengalamanKerja(id, function (listdata) {
                $('#branchIdRiwayatKerja').val(listdata.branchId).change();
                $('#departmentId').val(listdata.divisiId).change();
                $('#positionId3').val(listdata.posisiId).change();
                $('#profesi3').val(listdata.profesiId).change();
                $('#pengalamanPerusahaan').val(listdata.namaPerusahaan);
                $('#pengalamanJabatan').val(listdata.jabatan);
                $('#pengalamanTanggalMasuk').val(listdata.tanggalMasuk);
                $('#pengalamanTanggalKeluar').val(listdata.tanggalKeluar);
                $('#pengalamanTipePegawaiId').val(listdata.tipePegawaiId).change();
                if(listdata.tipePegawaiId == "TP03"){
                    $('#pengalamanGolonganId1').val(listdata.golonganId).change();
                    $('#golonganHistory1Group').show();
                    $('#golonganHistory2Group').hide();
                }
                if(listdata.tipePegawaiId == "TP04"){
                    $('#golonganHistory3').val(listdata.golonganId).change();
                    $('#golonganHistory1Group').hide();
                    $('#golonganHistory2Group').show();
                }
//                if(listdata.tanggalKeluar!=null){
//                    if(listdata.tanggalKeluar!='-'){
//                        $('#flagAktif1').val("N").change();
//                    }else{
//                        $('#flagAktif1').val("Y").change();
//                    }
//                }else{
//                    $('#flagAktif1').val("Y").change();
//                }
//                $('#pjsFlag1').val(listdata.pjsFlag);
                $('#flagAktif1').val(listdata.flagJabatanAktif);
                $('#pengalamanId').val(listdata.pengalamanId);
                $('#pengalamanGolonganName').val(listdata.golonganName);
            });
            </s:else>

            $('#modal-pengalamanKerja').find('.modal-title').text('Edit Data');
            $('#modal-pengalamanKerja').modal('show');
            $('#myFormPengalaman').attr('action', 'editPengalamanKerja');
        });

        $('.keluargaTable').on('click', '.item-edit', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;
            listStatusKeluarga();
            <s:if test="isAdd()">
            KeluargaAction.initSearch(id, function (listdata) {
                var myDate = new Date(listdata.tanggalLahir);
                $('#keluargaName').val(listdata.name);
                $('#statusKeluarga').val(listdata.statusKeluargaId);
                $('#tanggalLahirkeluarga').val((myDate.getDate()) + '-' + ("0" + (myDate.getMonth() + 1)).slice(-2) + '-' + myDate.getFullYear());
                $('#keluargaId').val(listdata.keluargaId);
                $('#genderKeluarga').val(listdata.gender);
            });
            </s:if>
            <s:else>
            KeluargaAction.searchData(nip, id, function (listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    $('#keluargaName').val(item.name);
                    $('#genderKeluarga').val(item.gender).change();
                    $('#statusKeluarga').val(item.statusKeluargaId);
                    $('#tanggalLahirkeluarga').val((myDate.getDate()) + '-' + ("0" + (myDate.getMonth() + 1)).slice(-2) + '-' + myDate.getFullYear());
                    $('#keluargaId').val(item.keluargaId);
                });
            });
            </s:else>

            $('#modal-editKeluarga').find('.modal-title').text('Edit Data');
            $('#modal-editKeluarga').modal('show');
            $('#myFormKeluarga').attr('action', 'editKeluarga');
        });

        $('.studyTable').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            <s:if test="isAdd()">
            StudyAction.initSearch(id, function (listdata) {
                $('#studyNameDelete').val(listdata.studyName);
                $('#studyTahunAwalDelete').val(listdata.tahunAwal);
                $('#studyTahunAkhirDelete').val(listdata.tahunAkhir);
                $('#studyTypeStudyDelete').val(listdata.typeStudy).change();
                $('#studyIdDelete').val(listdata.studyId).change();
            });

            $('#deleteModal').modal('show');
            //prevent previous handler - unbind()
            $('#btnDelete').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    StudyAction.initDelete(id, function (listdata) {
                        $('#deleteModal').modal('hide');
                        $('#myFormDelete')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSessionStudy();
                    });
                }
            });
            </s:if>
            <s:else>
            StudyAction.searchDataEdit(id, function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#studyNameDelete').val(item.studyName);
                    $('#studyTahunAwalDelete').val(item.tahunAwal);
                    $('#studyTahunAkhirDelete').val(item.tahunAkhir);
                    $('#studyTypeStudyDelete').val(item.typeStudy).change();
                    $('#studyIdDelete').val(item.studyId);
                });
            });

            $('#deleteModal').modal('show');
            //prevent previous handler - unbind()
            $('#btnDelete').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    StudyAction.saveDelete(id, function (listdata) {
                        $('#deleteModal').modal('hide');
                        $('#myFormDelete')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadStudy(nip);
                    });
                }
            });
            </s:else>

        });

        $('.pengalamanKerjaTable').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            <s:if test="isAdd()">
            BiodataAction.initSearchPengalamanKerja(id, function (listdata) {
                $('#deletePengalamanPerusahaan').val(listdata.branchName);
                $('#deletePengalamanJabatan').val(listdata.jabatanName);
                $('#deletePengalamanTanggalMasuk').val(listdata.stTtahunMasuk);
                $('#deletePengalamanTanggalKeluar').val(listdata.stTahunKeluar);
                $('#deletePengalamanId').val(listdata.pengalamanId);
            });

            $('#deleteModalPengalamanKerja').modal('show');
            $('#btnDeletePengalamanKerja').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.initDeletePengalamanKerja(id, function (listdata) {
                        $('#deleteModalPengalamanKerja').modal('hide');
                        $('#myFormDeletePengalaman')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSessionPengalamanKerja();
                    });
                }
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditPengalamanKerja(id, function (listdata) {
                $('#deletePengalamanPerusahaan').val(listdata.namaPerusahaan);
                $('#deletePengalamanJabatan').val(listdata.jabatan);
                $('#deletePengalamanTanggalMasuk').val(listdata.stTtahunMasuk);
                $('#deletePengalamanTanggalKeluar').val(listdata.stTahunKeluar);
                $('#deletePengalamanId').val(listdata.pengalamanId);
            });

            $('#deleteModalPengalamanKerja').modal('show');
            $('#btnDeletePengalamanKerja').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    dwr.engine.setAsync(false);
                    dwr.engine.beginBatch();
                    BiodataAction.saveDeletePengalamanKerja(id, function (listdata) {
                        $('#deleteModalPengalamanKerja').modal('hide');
                        $('#myFormDeletePengalaman')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadPengalamanKerja(nip);
                    });

                    dwr.engine.endBatch({
                        errorHandler:function(errorString, exception){
                            alert('Peringatan!! Riwayat Kerja mutasi tidak dapat dihapus.');
                        }
                    });
                }
            });
            </s:else>

        });

        $('.keluargaTable').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;
            listStatusKeluargaDelete();

            <s:if test="isAdd()">
            KeluargaAction.initSearch(id, function (listdata) {
                var myDate = new Date(listdata.tanggalLahir);
                $('#keluargaNameDelete').val(listdata.name);
                $('#statusKeluargaDelete').val(listdata.statusKeluargaId);
                $('#tanggalLahirkeluargaDelete').val((myDate.getDate()) + '-' + ("0" + (myDate.getMonth() + 1)).slice(-2) + '-' + myDate.getFullYear());
                $('#keluargaIdDelete').val(listdata.keluargaId);
            });

            $('#deleteModalKeluarga').modal('show');
            //prevent previous handler - unbind()
            $('#btnDeleteKeluarga').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    KeluargaAction.initDelete(id, function (listdata) {
                        $('#deleteModalKeluarga').modal('hide');
                        $('#myFormKeluarga')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSessionKeluarga();
                    });
                }
            });
            </s:if>
            <s:else>
            KeluargaAction.searchData(nip, id, function (listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    $('#keluargaNameDelete').val(item.name);
                    $('#statusKeluargaDelete').val(item.statusKeluargaId);
                    $('#tanggalLahirkeluargaDelete').val((myDate.getDate()) + '-' + ("0" + (myDate.getMonth() + 1)).slice(-2) + '-' + myDate.getFullYear());
                    $('#keluargaIdDelete').val(item.keluargaId);
                });
            });

            $('#deleteModalKeluarga').modal('show');
            //prevent previous handler - unbind()
            $('#btnDeleteKeluarga').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    KeluargaAction.saveDelete(id, function (listdata) {
                        $('#deleteModalKeluarga').modal('hide');
                        $('#myFormKeluarga')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadKeluarga(nip);
                    });
                }
            });
            </s:else>
        });

        $('.rewardTable').on('click', '.item-edit', function () {
            var id = $(this).attr('data');
            <s:if test="isAdd()">
            BiodataAction.initSearchReward(id, function (listdata) {
                $('#rewardId').val(listdata.rewardId);
                $('#rewardTanggal').val(listdata.stTanggal);
                $('#rewardJenis').val(listdata.jenis);
                $('#rewardKeterangan').val(listdata.keterangan);
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditReward(id, function (listdata) {
                $('#rewardId').val(listdata.rewardId);
                $('#rewardTanggal').val(listdata.stTanggal);
                $('#rewardJenis').val(listdata.jenis);
                $('#rewardKeterangan').val(listdata.keterangan);
            });
            </s:else>

            $('#modal-reward').find('.modal-title').text('Edit Data');
            $('#modal-reward').modal('show');
            $('#myFormReward').attr('action', 'editReward');
        });

        $('.rewardTable').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            <s:if test="isAdd()">
            BiodataAction.initSearchReward(id, function (listdata) {
                $('#deleteRewardId').val(listdata.rewardId);
                $('#deleteRewardTanggal').val(listdata.stTanggal);
                $('#deleteRewardJenis').val(listdata.jenis);
                $('#deleteRewardKeterangan').val(listdata.keterangan);
            });

            $('#deleteModalReward').modal('show');
            $('#btnDeleteReward').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.initDeleteReward(id, function (listdata) {
                        $('#deleteModalReward').modal('hide');
                        $('#myFormDeleteReward')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSessionReward();
                    });
                }
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditReward(id, function (listdata) {
                $('#deleteRewardId').val(listdata.rewardId);
                $('#deleteRewardTanggal').val(listdata.stTanggal);
                $('#deleteRewardJenis').val(listdata.jenis);
                $('#deleteRewardKeterangan').val(listdata.keterangan);
            });

            $('#deleteModalReward').modal('show');
            $('#btnDeleteReward').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.saveDeleteReward(id, function (listdata) {
                        $('#deleteModalReward').modal('hide');
                        $('#myFormReward')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadReward(nip);
                    });
                }
            });
            </s:else>

        });

        $('.sertifikatTable').on('click', '.item-edit', function () {
            var id = $(this).attr('data');
            <s:if test="isAdd()">
            BiodataAction.initSearchSertifikat(id, function (listdata) {

                $('#sertifikatId').val(listdata.sertifikatId);
                $('#nip1').val(listdata.nip);
                $('#sertifikatJenis').val(listdata.jenis);
                $('#sertifikatTanggalPengesahan').val(listdata.stTanggalPengesahan);
                $('#sertifikatMasaBerlaku').val(listdata.stMasaBerlaku);
                $('#sertifikatMasaBerakhir').val(listdata.stMasaBerakhir);
                $('#sertifikatNama').val(listdata.nama);
                $('#sertifikatLembaga').val(listdata.lembaga);
                $('#sertifikatTempatPelaksana').val(listdata.tempatPelaksana);
                $('#sertifikatNilai').val(listdata.nilai);
                $('#sertifikatLulus').val(listdata.lulus);
                $('#sertifikatPrestasi').val(listdata.prestasiGrade);
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditSertifikat(id, function (listdata) {
                $('#sertifikatId').val(listdata.sertifikatId);
                $('#nip1').val(listdata.nip);
                $('#sertifikatJenis').val(listdata.jenis);
                $('#sertifikatTanggalPengesahan').val(listdata.stTanggalPengesahan);
                $('#sertifikatMasaBerlaku').val(listdata.stMasaBerlaku);
                $('#sertifikatMasaBerakhir').val(listdata.stMasaBerakhir);
                $('#sertifikatNama').val(listdata.nama);
                $('#sertifikatLembaga').val(listdata.lembaga);
                $('#sertifikatTempatPelaksana').val(listdata.tempatPelaksana);
                $('#sertifikatNilai').val(listdata.nilai);
                $('#sertifikatJumlahHari').val(listdata.jumlahHari);
                $('#sertifikatLulus').val(listdata.lulus);
                $('#sertifikatPrestasi').val(listdata.prestasiGrade);
            });
            </s:else>

            $('#modal-sertifikat').find('.modal-title').text('Edit Data');
            $('#modal-sertifikat').modal('show');
            $('#myFormSertifikat').attr('action', 'editSertifikat');
        });

        $('.pelatihanJabatanTable').on('click', '.item-view-lampiran', function(){
            var data = $(this).attr('sertifikat');
            dwr.engine.setAsync(false);
            BiodataAction.loadImageSertifikat(data,function (result) {
                $("#my-image2").attr("src", result);
            });
            $('#modal-view-lampiran').find('.modal-title').text("Sertifikat");
            $('#modal-view-lampiran').modal('show');
        });

        $('.pelatihanJabatanTable').on('click', '.item-edit', function () {
            var id = $(this).attr('data');
            <s:if test="isAdd()">
            BiodataAction.initSearchPelatihan(id, function (data) {
                $('#pelatihanId').val(data.sertifikatId);
                $('#namaPelatihan').val(data.jenis);
                $('#judulPelatihan').val(data.nama);
                $('#pelatihanLembaga').val(data.lembaga);
                $('#jumlahJamPelatihan').val(data.jumlahHari);
                $('#sertifikasiPelatihan').val(data.lulus);
                $('#tanggalPelatihan').val(data.stTanggalPengesahan);
                $('#masaBerlakuSertifikat').val(data.prestasiGrade);
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditSertifikat(id, function (data) {
                $('#pelatihanId').val(data.sertifikatId);
                $('#namaPelatihan').val(data.jenis);
                $('#judulPelatihan').val(data.nama);
                $('#pelatihanLembaga').val(data.lembaga);
                $('#jumlahJamPelatihan').val(data.jumlahHari);
                $('#sertifikasiPelatihan').val(data.lulus);
                $('#tanggalPelatihan').val(data.stTanggalPengesahan);
                $('#masaBerlakuSertifikat').val(data.prestasiGrade);
            });
            </s:else>

            $('#modal-pelatihanJabatan').find('.modal-title').text('Edit Data');
            $('#modal-pelatihanJabatan').modal('show');
            $('#myFormPelatihanJabatan').attr('action', 'editPelatihanJabatan');
        });

        $('.pelatihanJabatanTable').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            <s:if test="isAdd()">
            BiodataAction.initSearchSertifikat(id, function (data) {
                $('#delpelatihanId').val(data.sertifikatId);
                $('#delnamaPelatihan').val(data.jenis);
                $('#deljudulPelatihan').val(data.nama);
                $('#delpelatihanLembaga').val(data.lembaga);
                $('#deljumlahJamPelatihan').val(data.jumlahHari);
                $('#delsertifikasiPelatihan').val(data.lulus);
                $('#deltanggalPelatihan').val(data.stTanggalPengesahan);
                $('#delmasaBerlakuSertifikat').val(data.prestasiGrade);
            });

            $('#deleteModalSertifikat').modal('show');
            $('#btnDeleteSertifikat').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.initDeleteSertifikat(id, function (listdata) {
                        $('#deleteModalSertifikat').modal('hide');
                        $('#myFormDeleteSertifikat')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSessionSertifikat();
                    });
                }
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditSertifikat(id, function (data) {
                $('#delpelatihanId').val(data.sertifikatId);
                $('#delnamaPelatihan').val(data.jenis);
                $('#deljudulPelatihan').val(data.nama);
                $('#delpelatihanLembaga').val(data.lembaga);
                $('#deljumlahJamPelatihan').val(data.jumlahHari);
                $('#delsertifikasiPelatihan').val(data.lulus);
                $('#deltanggalPelatihan').val(data.stTanggalPengesahan);
                $('#delmasaBerlakuSertifikat').val(data.prestasiGrade);
            });

            $('#deleteModalPelatihan').modal('show');
            $('#btnDeletePelatihan').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.saveDeleteSertifikat(id, function (listdata) {
                        $('#deleteModalPelatihan').modal('hide');
                        $('#myFormDeletePelatihanJabatan')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadPelatihanJabatan(nip);
                    });
                }
            });
            </s:else>

        });


        $('.sertifikatTable').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            <s:if test="isAdd()">
            BiodataAction.initSearchSertifikat(id, function (listdata) {
                $('#deleteSertifikatId').val(listdata.sertifikatId);
                $('#deleteNip1').val(listdata.nip);
                $('#deleteSertifikatJenis').val(listdata.jenis);
                $('#deleteSertifikatTanggalPengesahan').val(listdata.stTanggalPengesahan);
                $('#deleteSertifikatMasaBerlaku').val(listdata.stMasaBerlaku);
                $('#deleteSertifikatMasaBerakhir').val(listdata.stMasaBerakhir);
                $('#deleteSertifikatNama').val(listdata.nama);
                $('#deleteSertifikatLembaga').val(listdata.lembaga);
                $('#deleteSertifikatTempatPelaksana').val(listdata.tempatPelaksana);
                $('#deleteSertifikatNilai').val(listdata.nilai);
                $('#deleteSertifikatLulus').val(listdata.lulus);
                $('#deleteSertifikatPrestasi').val(listdata.prestasiGrade);
            });

            $('#deleteModalSertifikat').modal('show');
            $('#btnDeleteSertifikat').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.initDeleteSertifikat(id, function (listdata) {
                        $('#deleteModalSertifikat').modal('hide');
                        $('#myFormDeleteSertifikat')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSessionSertifikat();
                    });
                }
            });
            </s:if>
            <s:else>
            BiodataAction.searchDataEditSertifikat(id, function (listdata) {
                $('#deleteSertifikatId').val(listdata.sertifikatId);
                $('#nip1').val(listdata.nip);
                $('#deleteSertifikatJenis').val(listdata.jenis);
                $('#deleteSertifikatTanggalPengesahan').val(listdata.stTanggalPengesahan);
                $('#deleteSertifikatMasaBerlaku').val(listdata.stMasaBerlaku);
                $('#deleteSertifikatMasaBerakhir').val(listdata.stMasaBerakhir);
                $('#deleteSertifikatNama').val(listdata.nama);
                $('#deleteSertifikatLembaga').val(listdata.lembaga);
                $('#deleteSertifikatTempatPelaksana').val(listdata.tempatPelaksana);
                $('#deleteSertifikatNilai').val(listdata.nilai);
                $('#deleteSertifikatLulus').val(listdata.lulus);
                $('#deleteSertifikatPrestasi').val(listdata.prestasiGrade);
            });

            $('#deleteModalSertifikat').modal('show');
            $('#btnDeleteSertifikat').unbind().click(function () {
                if (confirm('Are you sure you want to Delete this Record?')) {
                    BiodataAction.saveDeleteSertifikat(id, function (listdata) {
                        $('#deleteModalSertifikat').modal('hide');
                        $('#myFormReward')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadSertifikat(nip);
                    });
                }
            });
            </s:else>

        });

        $('#tanggalLahirkeluarga').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });

        $('#pengalamanTanggalMasuk').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });
        $('#pengalamanTanggalKeluar').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });

        $('#tanggalLahir1').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });

        $('#tanggalMasuk').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-50:+10"
        });

        $('#tanggalAktif').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-50:+10"
        });

        $('#tanggalPraPensiun').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-30:+30"
        });

        $('#tanggalPensiun').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-30:+30"
        });

        $('#rewardTanggal').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-30:+30"
        });

        $('#sertifikatTanggalPengesahan').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-30:+30"
        });

        $('#sertifikatMasaBerlaku').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-30:+30"
        });

        $('#sertifikatMasaBerakhir').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-30:+30"
        });

        var prov = '';
        var kab = '';
        var kec = '';
        var desa = '';

    });

    //    window.cekZakat = function () {
    //        if ($('#zakatProfesi').is(":checked")) {
    //            $("#flagZakat").val("Y");
    //        } else {
    //            $("#flagZakat").val("N");
    //        }
    //    }


    window.cekAktif = function () {
        if ($('#aktif').is(":checked")) {
            $("#flagAktif").val("Y");
        } else {
            $("#flagAktif").val("N");
        }
    }

    window.cekCuti = function () {
        if (document.getElementById('flagCutiLuar').checked == true) {
            $("#flagCuti").val("Y");
        } else {
            if (confirm('Apakah anda yakin mengakhiri Cuti Diluar Tanggungan ?')) {
                $("#flagCuti").val("N");
            }
        }
    }


    window.cekSupervisi = function () {
        if (document.getElementById("supervisi").checked == true) {
            $("#flagTunjSupervisi").val("Y");
            $("#nomTunjSupervisi").show();
        } else {
            $("#flagTunjSupervisi").val("N");
            $("#nomTunjSupervisi").val("");
            $("#nomTunjSupervisi").hide();
        }
    }

    window.cekLokasi = function () {
        if (document.getElementById("lokasi").checked == true) {
            $("#flagTunjLokasi").val("Y");
            $("#nomTunjLokasi").show();
        } else {
            $("#flagTunjLokasi").val("N");
            $("#nomTunjLokasi").val("");
            $("#nomTunjLokasi").hide();
        }
    }

    window.cekSiaga = function () {
        if (document.getElementById("siaga").checked == true) {
            $("#flagTunjSiaga").val("Y");
            $("#nomTunjSiaga").show();
        } else {
            $("#flagTunjSiaga").val("N");
            $("#nomTunjSiaga").val("");
            $("#nomTunjSiaga").hide();
        }
    }

    window.cekProfesional = function () {
        if (document.getElementById("profesional").checked == true) {
            $("#flagTunjProfesional").val("Y");
        } else {
            $("#flagTunjProfesional").val("N");
        }
    }

    window.cekPeralihanGapok = function() {
        if(document.getElementById("peralihanGapok").checked == true) {
            $("#flagPeralihanGapok").val("Y");
            $("#nomTunjPeralihanGapok").show();
        } else {
            $("#flagPeralihanGapok").val("N");
            $("#nomTunjPeralihanGapok").val("");
            $("#nomTunjPeralihanGapok").hide();
        }
    }

    window.cekPeralihanSankhus = function() {
        if(document.getElementById("peralihanSankhus").checked == true) {
            $("#flagPeralihanSankhus").val("Y");
            $("#nomTunjPeralihanSankhus").show();
        } else {
            $("#flagPeralihanSankhus").val("N");
            $("#nomTunjPeralihanSankhus").val("");
            $("#nomTunjPeralihanSankhus").hide();

        }
    }

    window.cekPeralihanTunjangan = function() {
        if(document.getElementById("peralihanTunjangan").checked == true) {
            $("#flagPeralihanTunjangan").val("Y");
            $("#nomTunjPeralihanTunjangan").show();
        } else {
            $("#flagPeralihanTunjangan").val("N");
            $("#nomTunjPeralihanTunjangan").val("");
            $("#nomTunjPeralihanTunjangan").hide();
        }
    }

    window.cekTunjPemondokan = function () {
        if (document.getElementById("tunjPemondokan").checked == true) {
            $("#flagTunjPemondokan").val("Y");
            $("#nomTunjPemondokan").show();
        } else {
            $("#flagTunjPemondokan").val("N");
            $("#nomTunjPemondokan").val("");
            $("#nomTunjPemondokan").hide();
        }
    }

    // window.cekMess = function () {
    //     if (document.getElementById("mess").checked == true) {
    //         $("#flagMess").val("Y");
    //     } else {
    //         $("#flagMess").val("N");
    //     }
    // }
    //    window.cekPLT = function () {
    //        if (document.getElementById("plt").checked == true) {
    //            $("#flagPLT").val("Y");
    //        } else {
    //            $("#flagPLT").val("N");
    //        }
    //    }

    window.cekFingerMobile = function () {
        if (document.getElementById("fingerMobile").checked == true) {
            $("#flagFingerMobile").val("Y");
        } else {
            $("#flagFingerMobile").val("N");
        }
    }
    window.cekTunjRumah = function () {
        if (document.getElementById("tunjRumah").checked == true) {
            $("#flagTunjRumah").val("Y");
        } else {
            $("#flagTunjRumah").val("N");
        }
    }
    window.cekTunjAir = function () {
        if (document.getElementById("tunjAir").checked == true) {
            $("#flagTunjAir").val("Y");
        } else {
            $("#flagTunjAir").val("N");
        }
    }
    window.cekTunjListrik = function () {
        if (document.getElementById("tunjListrik").checked == true) {
            $("#flagTunjListrik").val("Y");
        } else {
            $("#flagTunjListrik").val("N");
        }
    }
    window.cekTunjBbm = function () {
        if (document.getElementById("tunjBbm").checked == true) {
            $("#flagTunjBbm").val("Y");
        } else {
            $("#flagTunjBbm").val("N");
        }
    }

    window.cekDireksi = function () {
        if ($('#direksi').is(":checked")) {
            $("#flagTunjDireksi").val("Y");
        } else {
            $("#flagTunjDireksi").val("N");
        }
    }

    window.cekKomisaris = function () {
        if ($('#komisaris').is(":checked")) {
            $("#flagTunjKomisaris").val("Y");
        } else {
            $("#flagTunjKomisaris").val("N");
        }
    }
    window.cekKoiteAudit = function () {
        if ($('#koiteAudit').is(":checked")) {
            $("#flagTunjKoiteAudit").val("Y");
        } else {
            $("#flagTunjKoiteAudit").val("N");
        }
    }




    window.listStatusKeluarga = function () {
        var gender = document.getElementById("gender").value;
        $('#statusKeluarga').empty();
        if (gender == 'L') {
            $('#statusKeluarga').append($("<option></option>")
                .attr("value", "I")
                .text("Istri"));
        } else {
            $('#statusKeluarga').append($("<option></option>")
                .attr("value", "S")
                .text("Suami"));
        }

        $('#statusKeluarga').append($("<option></option>")
            .attr("value", "A1")
            .text("Anak Pertama"));
        $('#statusKeluarga').append($("<option></option>")
            .attr("value", "A2")
            .text("Anak Kedua"));
        $('#statusKeluarga').append($("<option></option>")
            .attr("value", "A3")
            .text("Anak Ketiga"));
        $('#statusKeluarga').append($("<option></option>")
            .attr("value", "A4")
            .text("Anak Keempat"));
        $('#statusKeluarga').append($("<option></option>")
            .attr("value", "A5")
            .text("Anak Kelima"));
    }

    window.listPendidikanFakultas = function () {
        $('#studyFakultas').empty();

        StudyJurusanAction.getAllData( function (listdata) {
            // $('#studyFakultas').append($("<option></option>")
            //         .attr("value", "")
            //         .text(""));
            $.each(listdata, function (i, item) {
                $('#studyFakultas').append($("<option></option>")
                    .attr("value", item.jurusanId)
                    .text(item.jurusanName));
            });
        });
    }

    window.listPendidikanFakultas1 = function () {
        $('#studyFakultas1').empty();

        StudyJurusanAction.getAllData( function (listdata) {
            // $('#studyFakultas').append($("<option></option>")
            //         .attr("value", "")
            //         .text(""));
            $.each(listdata, function (i, item) {
                $('#studyFakultas1').append($("<option></option>")
                    .attr("value", item.jurusanId)
                    .text(item.jurusanName));
            });
        });
    }

    window.listStatusKeluargaDelete = function () {
        var gender = document.getElementById("gender").value;
        $('#statusKeluargaDelete').empty();
        if (gender == 'L') {
            $('#statusKeluargaDelete').append($("<option></option>")
                .attr("value", "istri")
                .text("Istri"));
        } else {
            $('#statusKeluargaDelete').append($("<option></option>")
                .attr("value", "suami")
                .text("Suami"));
        }
        $('#statusKeluargaDelete').append($("<option></option>")
            .attr("value", "A1")
            .text("Anak Pertama"));
        $('#statusKeluargaDelete').append($("<option></option>")
            .attr("value", "A2")
            .text("Anak Kedua"));
        $('#statusKeluargaDelete').append($("<option></option>")
            .attr("value", "A3")
            .text("Anak Ketiga"));
        $('#statusKeluargaDelete').append($("<option></option>")
            .attr("value", "A4")
            .text("Anak Keempat"));
        $('#statusKeluargaDelete').append($("<option></option>")
            .attr("value", "A5")
            .text("Anak Kelima"));
    }

    //    window.getTanggalPensiun = function (tanggal) {
    //        var tanggalPensiun = document.getElementById("tanggalPensiun").value;
    //        var res = tanggal.split("-");
    //        var tahun = parseInt(res[2]) + 55;
    //
    //        if(tanggalPensiun == ''){
    //            $('#tanggalPensiun').val(res[0] + "-" + res[1] + "-" + tahun);
    //        }
    //    };

    window.getTanggalPensiun = function () {
        var tipePegawai = $('#tipePegawai1').val();
        if(tipePegawai !=null && tipePegawai != '' && tipePegawai =="TP03") {
            var tanggal = $('#tanggalLahir1').val();
            var tanggalPensiun = document.getElementById("tanggalPensiun").value;
            var tanggalPraPensiun = document.getElementById("tanggalPraPensiun").value;
            var res = tanggal.split("-");
            var tahun = parseInt(res[2]) + 55;

            if (tanggalPraPensiun == '') {
                if (res[1] > 06) {
                    var bulan = parseInt(res[1] - 6);
                    $('#tanggalPraPensiun').val(res[0] + "-0" + bulan + "-" + tahun);
                } else {
                    if (res[1] == 06) {
                        var bulan = parseInt(12);
                        var tahun1 = parseInt(tahun - 1);
                        $('#tanggalPraPensiun').val(res[0] + "-" + bulan + "-" + tahun1);
                    } else {
                        var bulan = parseInt(12 - (6 - res[1]));
                        var tahun1 = parseInt(tahun - 1);
                        if (bulan > 9) {
                            $('#tanggalPraPensiun').val(res[0] + "-" + bulan + "-" + tahun1);
                        } else {
                            $('#tanggalPraPensiun').val(res[0] + "-0" + bulan + "-" + tahun1);
                        }
                    }
                }
            } else {
                if (res[1] > 06) {
                    var bulan = parseInt(res[1] - 6);
                    $('#tanggalPraPensiun').val(res[0] + "-0" + bulan + "-" + tahun);
                } else {
                    if (res[1] == 06) {
                        var bulan = parseInt(12);
                        var tahun1 = parseInt(tahun - 1);
                        $('#tanggalPraPensiun').val(res[0] + "-" + bulan + "-" + tahun1);
                    } else {
                        var bulan = parseInt(12 - (6 - res[1]));
                        var tahun1 = parseInt(tahun - 1);
                        if (bulan > 9) {
                            $('#tanggalPraPensiun').val(res[0] + "-" + bulan + "-" + tahun1);
                        } else {
                            $('#tanggalPraPensiun').val(res[0] + "-0" + bulan + "-" + tahun1);
                        }
                    }
                }
            }

            if (tanggalPensiun == '') {
                if (res[0] > 1) {
                    res[0] = 31;
                    $('#tanggalPensiun').val(res[0] + "-" + res[1] + "-" + tahun);
                } else {
                    $('#tanggalPensiun').val(res[0] + "-" + res[1] + "-" + tahun);
                }
            } else {
                if (res[0] > 1) {
                    res[0] = 31;
                    $('#tanggalPensiun').val(res[0] + "-" + res[1] + "-" + tahun);
                } else {
                    $('#tanggalPensiun').val(res[0] + "-" + res[1] + "-" + tahun);
                }
            }
        }
    };


    function savePengalaman(nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, jenisPegawaiId, flagDigaji){

        PositionAction.checkAndGetPositionAktif(posisiId, branchId, function(res){
            if (res.status == "error"){
                alert(res.msg);
            } else {
                BiodataAction.checkAvailJenisPegawaiDefault(function(res){
                    if (res.status == "error"){
                        alert(res.msg);
                    } else {
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            BiodataAction.saveAddPengalaman(nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, jenisPegawaiId, flagDigaji, function (res) {
                                if (res.status == 'success'){
                                    alert('Data Berhasil Disimpan');
                                    $('#modal-pengalamanKerja').modal('hide');
                                    $('#myFormPengalaman')[0].reset();
                                    loadSessionPengalamanKerja();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    // function savePengalaman(nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, jenisPegawaiId, flagDigaji){
    //     dwr.engine.setAsync(false);
    //     dwr.engine.beginBatch();
    //     BiodataAction.saveAddPengalaman(nip, branchId, posisiId, divisiId, profesiId, tanggal, tanggalKeluar, tipePegawaiId, golonganId, pjsFlag, aktifFlag, jenisPegawaiId, flagDigaji, function (listdata) {
    //         alert('Data Berhasil Disimpan');
    //         $('#modal-pengalamanKerja').modal('hide');
    //         $('#myFormPengalaman')[0].reset();
    //         loadSessionPengalamanKerja();
    //     });
    //     dwr.engine.endBatch({
    //         errorHandler:function(errorString, exception){
    //             alert('Jabatan aktif sudah ada');
    //         }
    //     });
    // }

    function getNip(birthDate){
        var nip = $("#nip1").val();
        var headNip = birthDate.split("-");
        if(nip==""){
            BiodataAction.getSeqNip(function(seq){
                $("#nip1").val(headNip[0] + headNip[1] + headNip[2] + seq);
            })
        } else {
            var seq = nip.slice(nip.length - 4);
            $("#nip1").val(headNip[0] + headNip[1] + headNip[2] + seq);
        }
    }

    function getRangeMsKerja(gol){
        console.log(gol);
        GolonganAction.getRangeMasaGol(gol, function(listData){
            $.each(listData, function (i, item) {
                console.log(item.msKerjaAwal);
                console.log(item.msKerjaAkhir);
                $("#poinLebih").attr({"min":item.msKerjaAwal, "max":item.msKerjaAkhir});
            })
        })
    }

    function getPensiun(){
        var tipePegawai = $('#tipePegawai1').val();
        var tglLahir = $('#tanggalLahir1').val();
        console.log("tipe pegawai : " + tipePegawai);
        if(tipePegawai != "TP03"){
            $('#tanggalPraPensiun').val('');
            $('#tanggalPensiun').val('');

            $('#danaPensiun').val('');
            $('#golongan2').val('');
            $('#poinLebih').val('');

            $('#danaPensiun').prop('disabled', true);
            $('#golongan2').prop('disabled', true);
            $('#poinLebih').prop('disabled', true);
            $('#noAnggotaDapen').prop('disabled', true);

        } else{
            if (tglLahir !=null && tglLahir != '') getTanggalPensiun();

            $('#danaPensiun').prop('disabled', false);
            $('#golongan2').prop('disabled', false);
            $('#poinLebih').prop('disabled', false);
            $('#noAnggotaDapen').prop('disabled', false);
        }
    }

</script>