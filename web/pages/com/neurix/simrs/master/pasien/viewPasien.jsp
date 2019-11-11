<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        }
    </script>
    <style>
        th{text-align: center;}
    </style>
</head>
<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/department" action="saveDelete_department" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">View Rekruitmen</legend>
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
                <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuEdit" modal="true"
                           height="500" width="900" autoOpen="false"
                           title="View Document">
                </sj:dialog>
                <table id="showdata" width="30%">
                    <tr>
                        <td align="center">
                            <s:set name="listOfRekruitmenDocument" value="#session.listOfResultRekruitmenDocument" scope="request" />
                            <display:table name="listOfRekruitmenDocument" class="tableRekruitmen table table-condensed table-striped table-hover"
                                           requestURI="paging_displaytag_rekruitmen.action" id="row" pagesize="20" style="font-size:12;text-align:center">
                                <display:column media="html" title="View Doc" style="text-align:center">
                                    <s:url var="urlEdit" namespace="/rekruitmen" action="viewDoc_rekruitmen" escapeAmp="false">
                                        <s:param name="id"><s:property value="#attr.row.uploadDocRekId"/></s:param>
                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                    </s:url>
                                    <sj:a onClickTopics="showDialogMenuEdit" href="%{urlEdit}">
                                        <i class="fa fa-download" style="font-size: 21px"></i>
                                    </sj:a>
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
                                <display:column media="html" title="Cetak Report" style="text-align:center">
                                    <s:url var="urlCetakKontrak" namespace="/rekruitmen" action="printReportPerStatusRekruitmen_rekruitmen" escapeAmp="false">
                                        <s:param name="id"><s:property value="#attr.row.calonPegawaiId"/></s:param>
                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                        <s:param name="statusId"><s:property value="#attr.row.statusRekruitmen"/></s:param>
                                    </s:url>
                                    <s:a href="%{urlCetakKontrak}">
                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                    </s:a>
                                </display:column>
                            </display:table>
                        </td>
                    </tr>

                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="button" id="cancel" class="btn btn-default" onclick="window.location.href='<s:url action="search_rekruitmen.action"/>'">
                            <i class="fa fa-close"/> Close
                        </button>
                    </div>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
<script>
    $(document).ready(function(){
        var id = $('#calonPegawaiId32').val();
        dwr.engine.setAsync(false);
        RekruitmenAction.searchProfilPhoto(id, function (listdata) {
            $("#myImage").attr("src",listdata);
        });
    })
</script>


