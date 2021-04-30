<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<%@ taglib prefix="S" uri="/struts-tags" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var idPasien = document.getElementById("id_pasien").value;
            var namePasien    = document.getElementById("nama_pasien1").value;



            if (namePasien != '' ) {
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

                if (namePasien == '') {
                    msg += 'Field <strong>Nama Pasien</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

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
            <s:form id="editPasienForm" method="post" theme="simple" namespace="/pasien" action="saveEdit_pasien" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Pasien</legend>


                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <S:hidden name="pasien.idPasien">

                    </S:hidden>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>No BPJS</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="no_bpjs" cssStyle="margin-top: 7px"
                                             name="pasien.noBpjs" required="false"
                                             readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>No KTP</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="no_ktp" name="pasien.noKtp"
                                             required="false" readonly="false"
                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Nama Pasien</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nama_pasien1" name="pasien.nama"
                                             required="false" readonly="false"
                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Jenis Kelamin :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}" cssStyle="margin-top: 7px"
                                          id="jenis_kelamin" name="pasien.jenisKelamin"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Tempat / Tanggal Lahir :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textfield cssStyle="margin-top: 7px" id="tempat_Lahir" name="pasien.tempatLahir"
                                                     required="true" cssClass="form-control"/>
                                    </td>
                                    <td> /</td>
                                    <td>
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon" >
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggal_lahir"
                                                         name="pasien.tglLahir"
                                                         cssClass="form-control pull-right"
                                                         required="false"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Agama</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'Islam':'Islam','Kristen':'Kristen','Hindu':'Hindu','Budha':'Budha'}" cssStyle="margin-top: 7px"
                                          id="agama" name="pasien.agama"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>No. Telp</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="no_telp" type="text" name="pasien.noTelp" required="false"
                                             readonly="false" cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Suku</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="suku" type="text" name="pasien.suku" required="false"
                                             readonly="false" cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Alamat :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="alamat" name="pasien.jalan"
                                            required="false" readonly="false"
                                            cssClass="form-control" cssStyle="margin-top: 7px"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Provinsi :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield cssStyle="margin-top: 7px" id="provinsi" name="pasien.provinsiId" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                             name="pasien.provinsiId" required="true"
                                             disabled="false" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        $('#provinsi').typeahead({
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
                                    mapped[labelItem] = {id: item.provinsiId, label: labelItem};
                                    functions.push(labelItem);
                                });

                                process(functions);
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                var namaAlat = selectedObj.label;
                                document.getElementById("provinsi11").value = selectedObj.id;
                                prov = selectedObj.id;
                                return namaAlat;
                            }
                        });
                    </script>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Kabupaten :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name="pasien.kotaId" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                             name="pasien.kotaId" required="true"
                                             disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        // var prov = document.getElementById("provinsi1").value;
                        $('#kabupaten').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};

                                var data = [];
                                dwr.engine.setAsync(false);
                                ProvinsiAction.initComboKota(query, prov, function (listdata) {
                                    data = listdata;
                                });
                                //alert(prov);
                                $.each(data, function (i, item) {
                                    //alert(item.kotaName);
                                    var labelItem = item.kotaName;
                                    mapped[labelItem] = {id: item.kotaId, label: labelItem};
                                    functions.push(labelItem);
                                });

                                process(functions);
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                var namaAlat = selectedObj.label;
                                document.getElementById("kabupaten11").value = selectedObj.id;

                                kab = selectedObj.id;
                                return namaAlat;
                            }
                        });

                        //
                        //
                    </script>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Kecamatan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name="pasien.kecamatanId" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none; margin-top: 7px" id="kecamatan11"
                                             name="pasien.kecamatanId" required="true"
                                             disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        var kab = document.getElementById("kabupaten").value;
                        $('#kecamatan').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};

                                var data = [];
                                dwr.engine.setAsync(false);
                                ProvinsiAction.initComboKecamatan(query, kab, function (listdata) {
                                    data = listdata;
                                });
                                //alert(prov);
                                $.each(data, function (i, item) {
                                    //alert(item.kotaName);
                                    var labelItem = item.kecamatanName;
                                    mapped[labelItem] = {id: item.kecamatanId, label: labelItem};
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
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Desa :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield cssStyle="margin-top: 7px" id="desa" name="pasien.desa" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                             name="pasien.desaId" required="true"
                                             disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        $('#desa').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};

                                var data = [];
                                dwr.engine.setAsync(false);
                                ProvinsiAction.initComboDesa(query, kec, function (listdata) {
                                    data = listdata;
                                });
                                //alert(prov);
                                $.each(data, function (i, item) {
                                    //alert(item.kotaName);
                                    var labelItem = item.desaName;
                                    mapped[labelItem] = {id: item.desaId, label: labelItem};
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



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editPasienForm" id="edit" name="edit"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
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
