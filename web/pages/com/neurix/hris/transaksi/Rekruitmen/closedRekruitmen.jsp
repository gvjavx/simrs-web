<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
    <style>
        #tanggalAktif{z-index: 2000!important}
        #modal-tambah-uraian-pekerjaan{z-index: 2300!important}
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <script type="text/javascript">

        function cekNip(nip)
        {
            dwr.engine.setAsync(false);
            RekruitmenAction.cekNip(nip, function (statusNip) {
                return statusNip;
            })
        }

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };
        $.subscribe('beforeProcessSave', function (event, data) {
            var nip = $('#nip').val();
            var tanggalAktif = $('#tanggalAktif').val();
            var tanggalDari = $('#kontrakDari').val();
            var tanggalSampai = $('#kontrakSelesai').val();
            var golongan = $('#golongan1').val();
            var poin = $('#poin').val();
            var upah = $('#upah').val();
            var nomorKontrak = $('#noKontrak').val();
            var keterangan = $('#keterangan').val();

            if (nip!=''&&tanggalAktif!=''&&tanggalDari!=''&&tanggalSampai!=''&&golongan!=''&&poin!=''&&upah!=''&&nomorKontrak!=''){
                var statusNip = cekNip(nip);
                dwr.engine.setAsync(false);
                if (statusNip){
                    if (confirm('Do you want to close this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    alert("NIP sudah ada");
                    event.originalEvent.options.submit = false;
                }
            }else{
                var msg = "";
                if (nip == "") {
                    msg+='nip masih kosong \n';
                }
                if (tanggalAktif == "") {
                    msg+='tanggal aktif masih kosong \n';
                }
                if (tanggalDari == "") {
                    msg+='Kontrak Dari masih kosong \n';
                }
                if (tanggalSampai == "") {
                    msg+='Kontrak Sampai masih kosong \n';
                }
                if (upah == "") {
                    msg+='Upah masih kosong \n';
                }
                if (golongan == "") {
                    msg+='Golongan masih kosong \n';
                }
                if (poin == "") {
                    msg+='Poin masih kosong \n';
                }
                if (nomorKontrak == "") {
                    msg+='Nomor Kontrak masih kosong \n';
                }

                alert(msg);
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }

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

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="closedRekruitmenForm" method="post" theme="simple" namespace="/rekruitmen" action="closedAction_rekruitmen" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Closed Rekruitmen</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <div style="float: left">
                    <img id="myImage" src="" class="img-thumbnail img-responsive" width="200" height="150">
                </div>
                <table>
                    <tr>
                        <td>
                            <label class="control-label"><small>Calon Pegawai Id :</small></label>
                        </td>
                        <td>
                            <s:textfield id="calonPegawaiId32" name="rekruitmen.calonPegawaiId" required="false" readonly="true" cssClass="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Calon Pegawai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="calonPegawaiName" name="rekruitmen.namaCalonPegawai" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Gelar Depan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="gelarDepan" name="rekruitmen.gelarDepan" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Gelar Belakang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="gelarBelakang" name="rekruitmen.gelarBelakang" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>No. KTP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="noKtp" name="rekruitmen.noKtp" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>No. HP :</small></label>
                        </td>
                        <td>
                            <s:textfield id="noTelp" name="rekruitmen.noTelp" required="false" readonly="true" cssClass="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Alamat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4"  id="tujuanRekruitmen" name="rekruitmen.alamat" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Provinsi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="provinsi1" cssStyle="display: none" name="rekruitmen.provinsiId" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                <s:textfield id="provinsi11" name="rekruitmen.provinsiName" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kabupaten :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kabupaten1" cssStyle="display: none" name="rekruitmen.kotaId" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                <s:textfield id="kabupaten11" name="rekruitmen.kotaName" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kecamatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kecamatan1" cssStyle="display: none" name="rekruitmen.kecamatanId" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                <s:textfield id="kecamatan11" name="rekruitmen.kecamatanName" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Desa :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="desa1" name="rekruitmen.desaId" required="true" readonly="true" disabled="false" cssClass="form-control"/>
                                <s:textfield id="desa11" name="rekruitmen.desaName" required="true" readonly="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Rt / Rw :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="rtRw" name="rekruitmen.rtRw" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tempat / Tanggal Lahir :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textfield id="tempatLahir" name="rekruitmen.tempatLahir" readonly="true" required="true" cssClass="form-control"/>
                                    </td>
                                    <td> / </td>
                                    <td>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalLahir" name="rekruitmen.stTanggalLahir" readonly="true" cssClass="form-control pull-right" required="false" />
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Kelamin :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="jkel" name="rekruitmen.jenisKelamin" required="false" readonly="true" cssStyle="display:none;" cssClass="form-control"/>
                                <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}" id="jenisKelamin" name="rekruitmen.jenisKelamin" disabled="true" readonly="true"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Status Keluarga :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="stsklg" name="rekruitmen.statusKeluarga" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:select list="#{'K':'Keluarga','B':'Single'}" id="statusKeluarga" name="rekruitmen.statusKeluarga"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jumlah Anak :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="jumlahAnak" name="rekruitmen.jumlahAnak" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Calon Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="clnjbtn" name="rekruitmen.positionId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="rekruitmen.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" readonly="true" disabled="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="div" name="rekruitmen.departmentId" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="departmentId" name="rekruitmen.departmentId" readonly="true" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="un" name="rekruitmen.branchId" required="false" readonly="true" cssStyle="display: none"  cssClass="form-control"/>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmen.branchId" readonly="true" disabled="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Status Pegawai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="stspgwaio" name="rekruitmen.statusPegawai" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:select list="#{'KNS' : 'Karyawan Non Struktural', 'KS':'Karyawan Struktural'}"
                                          id="statusPegawai" name="rekruitmen.statusPegawai" readonly="true" disabled="true"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Pegawai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tppgw" name="rekruitmen.tipePegawai" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                                <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai1" name="rekruitmen.tipePegawai" readonly="true" disabled="true"
                                          listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Status Giling :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="stsglg" name="rekruitmen.statusGiling" required="false" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:select list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}" id="statusGiling" name="rekruitmen.statusGiling"
                                         readonly="true" disabled="true" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <center><h4>Study Rekruitmen</h4></center>
                <br>
                <table id="showdata" width="40%">
                    <tr>
                        <td align="center">
                            <s:set name="listOfRekruitmenStudy" value="#session.ListOfResultRekruitmenStudy" scope="request" />
                            <display:table name="listOfRekruitmenStudy" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                           requestURI="paging_displaytag_rekruitmen.action" id="row" pagesize="20" style="font-size:12">
                                <display:column property="tipeStudy" sortable="true" title="Jenjang"  />
                                <display:column property="studyName" sortable="true" title="Nama Sekolah"  />
                                <display:column property="tahunAwal" sortable="true" title="Tahun Masuk" />
                                <display:column property="tahunAkhir" sortable="true" title="Tahun Lulus" />
                                <display:column property="nilai" sortable="true" title="Nilai" />
                            </display:table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <center><h4>Document Rekruitmen</h4></center>
                <br>
                <table id="showdata" width="30%">
                    <tr>
                        <td align="center">
                            <s:set name="listOfRekruitmenDocument" value="#session.listOfResultRekruitmenDocument" scope="request" />
                            <display:table name="listOfRekruitmenDocument" class="tableRekruitmen table table-condensed table-striped table-hover"
                                           requestURI="paging_displaytag_rekruitmen.action" id="row" pagesize="20" style="font-size:12;text-align:center">
                                <display:column media="html" title="View Doc" style="text-align:center">
                                    <s:url var="urlEdit" namespace="/rekruitmen" action="viewDoc_rekruitmen" escapeAmp="false">
                                        <s:param name="id"><s:property value="#attr.row.calonPegawaiId"/></s:param>
                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                    </s:url>
                                    <s:a href="%{urlEdit}">
                                        <i class="fa fa-download" style="font-size: 21px"></i>
                                    </s:a>
                                </display:column>
                                <display:column property="documentName" style="text-align:center" sortable="true" title="Document Name"  />
                            </display:table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <center><h4>Status Rekruitmen</h4></center>
                <br>
                <table id="showdata" width="50%">
                    <tr>
                        <td align="center">
                            <s:set name="listOfRekruitmenStatus" value="#session.listOfResultRekruitmenStatus" scope="request" />
                            <display:table name="listOfRekruitmenStatus" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                           requestURI="paging_displaytag_rekruitmen.action" id="row" pagesize="20" style="font-size:12">
                                <display:column property="statusRekruitmenName" sortable="true" title="Record Status"  />
                                <display:column property="tanggalProses" sortable="true" title="Tanggal Proses" />
                                <display:column property="keterangan" sortable="true" title="Keterangan" />
                            </display:table>
                        </td>
                    </tr>
                </table>
                <br>
                <table>
                    <tr>
                        <td>
                            <label class="control-label"><small>Status Rekruitmen :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="rekruitmenStatusId" name="rekruitmenStatus.calonPegawaiId" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="initComboStatus" namespace="/rekruitmen" name="searchStatusRekruitmen_rekruitmen"/>
                                <s:select list="#initComboStatus.listComboStatusRekruitmen" id="statusRekruitmen22" name="rekruitmenStatus.statusRekruitmen" listKey="statusRekruitmentId" listValue="statusRekruitmentName"
                                          headerKey="11" headerValue="Karyawan" cssClass="form-control" disabled="true" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nip" name="rekruitmen.nip" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Aktif :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tanggalAktif" name="rekruitmen.stTanggalAktif" cssClass="form-control pull-right" required="false" />
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kontrak Dari :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="kontrakDari" name="rekruitmen.stKontrakDari" cssClass="form-control pull-right" required="false" />
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kontrak Selesai :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="kontrakSelesai" name="rekruitmen.stKontrakSelesai" cssClass="form-control pull-right" required="false" />
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Golongan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#initComboTipe.listComboGolongan" id="golongan1" name="rekruitmen.golongan"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Poin :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="poin" type="number" name="rekruitmen.poin" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Upah :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="upah" type="number" name="rekruitmen.upah" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nomor Kontrak :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="noKontrak" name="rekruitmen.noKontrak" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="3"  id="keterangan" name="rekruitmenStatus.keterangan" required="false" readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <h4>
                    Daftar Uraian Pekerjaan
                    <button
                            id="btnAddUraianPekerjaan" type="button" class="btn btn-xs btn-default btn-info"><i
                            class="fa fa-plus"></i>
                    </button>
                </h4>
                <center>
                    <table id="showdata1" width="80%">
                        <tr>
                            <td align="center">
                                <table style="width: 80%;"
                                       class="rekruitmenUraianPekerjaanTable table table-condensed table-striped table-hover">
                                </table>
                            </td>
                        </tr>
                    </table>
                </center>
                <br>
                <div id="actions" class="form-actions">
                    <table align="center">
                        <tr>
                            <td>
                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="closedRekruitmenForm" id="save" name="save"
                                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                    <i class="fa fa-check"></i>
                                    Save
                                </sj:submit>
                            </td><td>&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_rekruitmen.action"/>'">
                                    <i class="fa fa-refresh"></i> Cancel
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
                                                                    //$(this).dialog('close');
                                                                      callSearch();
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
</body>
</html>
<div id="modal-tambah-uraian-pekerjaan" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tambah Uraian Pekerjaan</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-3">Pekerjaan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="uraianPekerjaan" name=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveUraianKerjaan" type="button" class="btn btn-default btn-success">Tambah</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    window.loadUraianPekerjaan = function () {
        $('.rekruitmenUraianPekerjaanTable').find('tbody').remove();
        $('.rekruitmenUraianPekerjaanTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        RekruitmenAction.searchUraianPekerjaan(function (listdata) {
            tmp_table = "<thead style='font-size: 10px' ><tr class='active'>" +
                "<th style='text-align: center;'>No</th>" +
                "<th style='text-align: center;'>Delete</th>" +
                "<th style='text-align: center;'>Uraian Pekerjaan</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 10px;" ">' +
                    '<td align="center" width="8%">' + (i + 1) + '</td>' +
                    '<td align="center"  width="12%">' +
                    "<a href='javascript:;' class ='item-delete-uraian' data ='" + item.uraianPekerjaan + "' >" +
                    "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_trash'>" +
                    '</a>' +
                    '</td>' +
                    '<td align="center">' + item.uraianPekerjaan + '</td>' +
                    "</tr>";
            });
            $('.rekruitmenUraianPekerjaanTable').append(tmp_table);
        });
    };

    $(document).ready(function() {

        $('#tanggalAktif').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });
        $('#kontrakDari').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });
        $('#kontrakSelesai').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });
        var id = $('#calonPegawaiId32').val();
        dwr.engine.setAsync(false);
        RekruitmenAction.searchProfilPhoto(id, function (listdata) {
            $("#myImage").attr("src",listdata);
        });
        $('#btnAddUraianPekerjaan').click(function () {
            $('#uraianPekerjaan').val("");
            $('#modal-tambah-uraian-pekerjaan').modal('show');
            $('#myForm').attr('action', 'addUraianPekerjaan');
            $('#modal-tambah-uraian-pekerjaan').find('.modal-title').text('Tambah Uraian Pekerjaan');
        });

        $('#btnSaveUraianKerjaan').click(function () {
            var url = $('#myForm').attr('action');
            var uraianPekerjaan = document.getElementById("uraianPekerjaan").value;

            if (url == 'addUraianPekerjaan') {
                if (uraianPekerjaan != '') {
                        dwr.engine.setAsync(false);
                        RekruitmenAction.saveAddUraianPekerjaan(uraianPekerjaan, function (data) {
                            alert('Data Successfully Added');
                            $('#modal-tambah-uraian-pekerjaan').modal('hide');
                            $('#myForm')[0].reset();
                            loadUraianPekerjaan();
                        });
                } else {
                    alert('Uraian Pekerjaan harus diisi');
                }
            }
        });
        $('.rekruitmenUraianPekerjaanTable').on('click', '.item-delete-uraian', function () {
            var id = $(this).attr('data');
            dwr.engine.setAsync(false);
            if (confirm('Are you sure you want to delete this Record?')) {
                RekruitmenAction.saveDeleteUraianPekerjaan(id, function (listdata) {
                    alert('Record has been Deleted successfully.');
                    loadUraianPekerjaan();
                });
            }
        });
    });
</script>
