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
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>

    <style>
        .checkApprove {width: 20px; height: 20px;}
        .ui-datepicker{z-index:2000 !important;}
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
    </style>
</head>

<script type="application/javascript">
    window.clos = function() {
        //$('#waiting_dialog').dialog('close');
        $('#view_dialog_menu').dialog('close');
        $('#info_dialog').dialog('close');
        window.location.href="<s:url action='initForm_sppd.action'/>";
    };

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
            }

            document.getElementById('errorValidationMessage').innerHTML = msg;

            $.publish('showErrorValidationDialog');

        }
    });

    $.subscribe('beforeProcessSaveEdit', function (event, data) {
        var note = document.getElementById("docNoteEdit").value ;
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
            }

            document.getElementById('errorValidationMessage').innerHTML = msg;

            $.publish('showErrorValidationDialog');

        }
    });

    $.subscribe('beforeProcessSaveEditClose', function (event, data) {

        var close = document.getElementById("close").value ;
        cekDokumenTraining();
            if (confirm('Doo you want to save this record?')) {
                if(close == "Y"){
                    if (confirm('Do you want to Close this SPPD?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    }else{
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                }
            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }

    });

    $.subscribe('beforeProcessSaveDelete', function (event, data) {
        var note = document.getElementById("docNoteDelete").value ;
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
</script>

<body class="hold-transition skin-blue sidebar-mini" >

    <div id="modal-edit" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:500px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Approve SPPD</h4>
                </div>
                <div class="modal-body">

                       <s:url id="urlProcess" namespace="/sppd" action="addSppdDoc_sppd" includeContext="false"/>
                       <s:form id="addUserForm" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass="well form-horizontal">

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="sppdId1" name="sppdDoc.sppdId">
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

                        <div class="form-group">
                            <label class="control-label col-sm-1" style="visibility: hidden"></label>

                            <div style="padding-left: 100px" class="col-sm-8">
                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                           resizable="false"
                                           height="250" width="600" autoOpen="false" title="Searching...">
                                    Please don't close this window, server is processing your request ...
                                    </br>
                                    </br>
                                    </br>
                                    <center>
                                        <img border="0" src="<s:url value="/pages/images/loading11.gif"/>" name="image_indicator_read">
                                    </center>
                                </sj:dialog>
                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                           buttons="{
                                                        'OK':function() {
                                                                 $('#info_dialog').dialog('close');
                                                                 //location.reload();
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
                                                                                'OK':function() { $('#error_dialog').dialog('close');
                                                                                 }
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

                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addUserForm" id="save" name="save"
                                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                    <i class="fa fa-check"></i>
                                    Save
                                </sj:submit>

                                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                            </div>

                        </div>
                    <%--</form>--%>
                    </s:form>
                </div>

               <%-- <div class="modal-footer">
                    <a id="btnAddDocument" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>--%>
            </div>
        </div>
    </div>

    <div id="modal-UploadEdit" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:1000px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Approve SPPD</h4>
                </div>
                <div class="modal-body">

                       <s:url id="urlProcess" namespace="/sppd" action="saveEditDoc_sppd" includeContext="false"/>
                       <s:form id="EditDocument" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass="well form-horizontal">

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="sppdId1Edit" name="sppdDoc.sppdId">
                            </div>
                        </div>

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Doc Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="docSppdId" name="sppdDoc.docSppdId">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >File : </label>
                            <div class="col-sm-8">
                                <input type="file" id="fileEdit" class="form-control" name="fileUpload" />
                                <%--<input type="upload" class="form-control" id="docFile" name="nip">--%>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Keterangan : </label>
                            <div class="col-sm-8">
                                <textarea rows="3" class="form-control" id="docNoteEdit" name="sppdDoc.note"></textarea>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" style="visibility: hidden"></label>

                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="EditDocument" id="saveEdit" name="saveEdit"
                                           onBeforeTopics="beforeProcessSaveEdit" onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                    <i class="fa fa-check"></i>
                                    Save
                                </sj:submit>

                                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                            </div>

                        </div>
                    <%--</form>--%>
                    </s:form>
                </div>

               <%-- <div class="modal-footer">
                    <a id="btnAddDocument" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>--%>
            </div>
        </div>

    <div id="modal-UploadDelete" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:1000px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Approve SPPD</h4>
                </div>
                <div class="modal-body">

                       <s:url id="urlProcess" namespace="/sppd" action="saveDeleteDoc_sppd" includeContext="false"/>
                       <s:form id="DeleteDocument" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass="well form-horizontal">

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Doc Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="docSppdIdDelete" name="sppdDoc.docSppdId">
                            </div>
                        </div>

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="sppdId1Edits" name="sppdDoc.sppdId">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >File : </label>
                            <div class="col-sm-8">
                                <input type="file" disabled id="fileDelete" class="form-control" name="fileUpload" />
                                <%--<input type="upload" class="form-control" id="docFile" name="nip">--%>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Keterangan : </label>
                            <div class="col-sm-8">
                                <textarea rows="3" readonly class="form-control" id="docNoteDelete" name="sppdDoc.note"></textarea>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" style="visibility: hidden"></label>

                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="DeleteDocument" id="saveDelete" name="saveDelete"
                                           onBeforeTopics="beforeProcessSaveDelete" onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                    <i class="fa fa-trash"></i>
                                    Delete
                                </sj:submit>

                                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                            </div>

                        </div>
                    <%--</form>--%>
                    </s:form>
                </div>

               <%-- <div class="modal-footer">
                    <a id="btnAddDocument" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>--%>
            </div>
    </div>

    <div id="modal-reroute" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:700px;">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="myFormReroute">

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="sppdIdReroute" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Nama : </label>
                            <div class="col-sm-8">
                                <input style="display: none" type="text" id="sppdRerouteId" name="sppdRerouteId" class="form-control" />
                                <s:select list="#session.sppdAnggota" id="sppdReroutePersonIdOld" name="sppdReroute.sppdPersonId" cssStyle="display: none"
                                          listKey="sppdPersonId" listValue="personName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                <s:select list="#session.sppdAnggota" id="sppdReroutePersonId" name="sppdReroute.sppdPersonId"
                                          listKey="sppdPersonId" listValue="personName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Dari : </label>
                            <div class="col-sm-8">
                                <input style="display: none;" type="text" id="rerouteDari" class="form-control" />
                                <input type="text" id="berangkatDari" class="form-control" name="sppdReroute.rerouteDari" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Ke : </label>
                            <div class="col-sm-8">
                                <input style="display: none;" type="text" id="rerouteKe" class="form-control" />
                                <input type="text" id="berangkatKe" class="form-control" name="sppdReroute.rerouteKe" />
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    $('#berangkatKe').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            ProvinsiAction.initComboKota(query, "", function (listdata) {
                                                data = listdata;
                                            });
                                            //alert(prov);
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
                                            document.getElementById("rerouteKe").value = selectedObj.id;
                                            //kab = selectedObj.id ;
                                            return namaAlat;
                                        }
                                    });
                                </script>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tanggal Dari : </label>
                            <div class="col-sm-8">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield onchange="" id="tanggalDariReroute" name="sppdReroute.stTanggalRerouteDari"
                                                 cssClass="form-control pull-right"
                                                 required="false"  cssStyle=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tanggal Ke : </label>
                            <div class="col-sm-8">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <%--cekTanggal()--%>
                                    <s:textfield onchange="" id="tanggalKeReroute" name="sppdReroute.stTanggalRerouteKe" cssClass="form-control pull-right"
                                                 required="false"  cssStyle=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Keterangan : </label>
                            <div class="col-sm-8">
                                <textarea rows="4" class="form-control" id="sppdRerouteKeterangan" name="sppdReroute.keterangan" ></textarea>

                            </div>
                        </div>

                        <div class="form-group">
                            <label style="padding-left: 0px" class="control-label col-sm-3" >Berangkat Via: </label>
                            <div class="col-sm-8">
                                <select class="form-control nip" id="berangkatViaReroute">
                                    <option value="Kereta Api">Kereta Api</option>
                                    <option value="Bis">Bis</option>
                                    <option value="Pesawat">Pesawat</option>
                                    <option value="Mobil">Mobil</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Harga Tiket Berangkat: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="rerouteTiketPergi" >
                            </div>
                        </div>

                        <div style="" class="form-group">
                            <label class="control-label col-sm-3" >Harga Tiket Kembali: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="rerouteTiketKembali" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Lainnya: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="rerouteBiayaTambahan" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Lokal: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="rerouteBiayaLokal" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Tujuan: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="rerouteBiayaTujuan" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Kembali: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="rerouteBiayaKembali" >
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <a id="btnAddReroute" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-tiket" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:750px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="myFormTiket">

                        <div style="display: none;" class="form-group">
                            <label class="control-label col-sm-3" >Sppd ID : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control nip" id="sppdIdTiket" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Nama : </label>
                            <div class="col-sm-8">
                                <input type="text" style="display: none;" class="form-control nip" id="sppdTiketSppd1" >
                                <input type="text" style="display: none;" class="form-control nip" id="sppdTiketPersonId1" >
                                <input readonly type="text" class="form-control nip" id="sppdTiketPersonId" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Branch : </label>
                            <div class="col-sm-8">
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchTiketId" name="sppd.branchId" disabled="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Divisi : </label>
                            <div class="col-sm-8">
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiTiketId" name="sppd.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-3" >Position : </label>
                            <div class="col-sm-8">
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionTiketId" name="sppd.positionId" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tanggal Berangkat: </label>
                            <div class="col-sm-8">
                                <input type="text" readonly class="form-control nip" id="tanggalBerangkat1" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tanggal Kembali: </label>
                            <div class="col-sm-8">
                                <input type="text" readonly class="form-control nip" id="tanggalKembali1" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label style="padding-left: 0px" class="control-label col-sm-3" >Berangkat Dari : </label>
                            <div class="col-sm-8">
                                <input type="text" style="display:none" class="form-control nip" id="editBerangkatDariId" >
                                <input type="text"  class="form-control nip" id="editBerangkatDari" >
                            </div>
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            $('#editBerangkatDari').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);
                                    ProvinsiAction.initComboKota(query, "", function (listdata) {
                                        data = listdata;
                                    });
                                    //alert(prov);
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
                                    document.getElementById("editBerangkatDariId").value = selectedObj.id;
                                    document.getElementById("editBerangkatDari").value = selectedObj.label;

                                    //kab = selectedObj.id ;
                                    return namaAlat;
                                }
                            });
                        </script>

                        <div class="form-group">
                            <label style="padding-left: 0px" class="control-label col-sm-3" >Tujuan Ke : </label>
                            <div class="col-sm-8">
                                <input type="text" style="display:none" class="form-control nip" id="editTujuanKeId" >
                                <input type="text"  class="form-control nip" id="editTujuanKe" >
                            </div>
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            $('#editTujuanKe').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);
                                    ProvinsiAction.initComboKota(query, "", function (listdata) {
                                        data = listdata;
                                    });
                                    //alert(prov);
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
                                    document.getElementById("editTujuanKeId").value = selectedObj.id;
                                    document.getElementById("editTujuanKe").value = selectedObj.label;

                                    //kab = selectedObj.id ;
                                    return namaAlat;
                                }
                            });
                        </script>

                        <div class="form-group">
                            <label style="padding-left: 0px" class="control-label col-sm-3" >Tgl Berangkat Realisasi: </label>
                            <div class="col-sm-8">
                                <input type="text"  class="form-control nip" id="tanggalBerangkatRealisasi" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tgl Kembali Realisasi: </label>
                            <div class="col-sm-8">
                                <input type="text"  class="form-control nip" id="tanggalKembaliRealisasi" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label style="padding-left: 0px" class="control-label col-sm-3" >Berangkat Via: </label>
                            <div class="col-sm-8">
                                <select class="form-control nip" id="berangkatVia1">
                                    <option value="Kereta Api">Kereta Api</option>
                                    <option value="Bis">Bis</option>
                                    <option value="Pesawat">Pesawat</option>
                                    <option value="Mobil">Mobil</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Kembali Via: </label>
                            <div class="col-sm-8">
                                <select class="form-control nip" id="kembaliVia1">
                                    <option value="Kereta Api">Kereta Api</option>
                                    <option value="Bis">Bis</option>
                                    <option value="Pesawat">Pesawat</option>
                                    <option value="Mobil">Mobil</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Harga Tiket Berangkat: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="hargaTiketPergi" >
                            </div>
                        </div>

                        <div style="" class="form-group">
                            <label class="control-label col-sm-3" >Harga Tiket Kembali: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="hargaTiketKembali" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Lainnya: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="biayaTambahan" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Lokal: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="biayaLokal" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Tujuan: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="biayaTujuan" >
                                <input type="text" style="display: none" class="form-control nip" id="sppdTanggalId1" >
                                <input type="text" style="display: none" class="form-control nip" id="sppdTanggalId2" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Biaya Transport Kembali: </label>
                            <div class="col-sm-8">
                                <input type="number"  class="form-control nip" id="biayaKembali" >
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a id="btnAddTiket" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-tanggal" class="modal fade" role="dialog">
        <div class="modal-dialog " style="width:600px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <input type="text" style="display: none" class="form-control" id="sppdTableTanggalId" >
                    <table style="width: 100%;" class="sppdTableTanggal table table-bordered">
                    </table>
                </div>
                <div class="modal-footer">
                    <a id="btnSaveTanggal" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Save</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Edit SPPD
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/sppd" action="saveEdit_sppd.action" cssClass="well form-horizontal">

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
                            <tr>
                                <td>
                                    <label class="control-label"><small>Sppd Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="sppdId" name="sppd.sppdId" required="false" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="nama" name="sppd.personName" required="false" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>--%>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Branch :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="sppd.branchId" disabled="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>Divisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="sppd.divisiId" disabled="true"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>--%>

                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>Position :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="sppd.positionId" disabled="true"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>--%>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Dinas :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'LN':'Luar Negeri'}" id="flag" name="sppd.dinas" disabled="true"
                                                  headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Keperluan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textarea rows="3" cssClass="form-control" id="keperluan" name="sppd.tugasSppd"></s:textarea>
                                    </table>

                                </td>
                            </tr>

                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Tanggal Berangkat :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalBerangkat" name="sppd.stTanggalSppdBerangkat" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display: none;">
                                <td>
                                    <label class="control-label"><small>Tanggal Kembali :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalKembali" name="sppd.stTanggalSppdKembali" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Berangkat Dari :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  cssClass="form-control nip" name="sppd.berangkatDari" readonly="true" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tujuan Ke :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="tujuanKe" cssClass="form-control nip" name="sppd.tujuanKe" readonly="true" />
                                        <s:textfield id="tujuanKeId" cssClass="form-control nip" cssStyle="display: none" name="sppd.tujuanKeId" readonly="true" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Berangkat Via :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssClass="form-control" name="sppd.berangkatVia" id="berangkatVia" readonly="true" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Kembali Via :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  cssClass="form-control " id="kembaliVia" name="sppd.pulangVia" readonly="true" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Close :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="isSppdClosed()">
                                            <s:textfield  cssClass="form-control " value="Closed" disabled="true" />
                                        </s:if>
                                        <s:else>
                                            <s:select list="#{'N':'Belum', 'Y' : 'Closed' }" id="close" name="sppd.closed"
                                                      cssClass="form-control" />
                                        </s:else>

                                    </table>
                                </td>
                            </tr>


                        </table>

                        <br>
                        <br>

                        <a class="btn btn-success btnDocument">Upload Document</a>
                        <%--<table width="40%">
                            <tr>
                                <td align="center">
                                    <br>
                                    <label>Document SPPD</label>
                                    <br>
                                    <sj:dialog id="view_dialog_view" openTopics="showDialogView" modal="true"
                                               height="500" width="900" autoOpen="false"
                                               title="View">
                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                    </sj:dialog>

                                    <s:set name="listOfResultBukti" value="#session.listSppdDocument" scope="request" />
                                    <display:table name="listOfResultBukti" class="table table-condensed table-striped table-hover"
                                                   export="false" id="row" style="font-size:10">
                                        <display:column media="html" title="View" style="text-align:center;font-size:9">
                                            <s:url var="urlViewDoc" namespace="/sppd" action="viewDoc_sppd" escapeAmp="false">
                                                <s:param name="id"><s:property value="#attr.row.docSppdId" /></s:param>
                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                            </s:url>
                                            <sj:a onClickTopics="showDialogView" href="%{urlViewDoc}">
                                                <i class='fa fa-download' style='font-size:20px'></i>
                                            </sj:a>
                                        </display:column>
                                        <display:column property="note" sortable="true" title="Keterangan" />
                                    </display:table>
                                </td>
                            </tr>
                        </table>--%>
                        <table style="width: 70%;" class="sppdTableUpload table table-bordered">
                        </table>

                        <br>
                        <br>

                        <a id="btnReroute" class="btn btn-success">Add Reroute</a>
                            <table style="width: 90%;" class="sppdTableReroute table table-bordered">
                        </table>

                        <br>
                        <br>
                        <table style="width: 90%;" class="sppdTableTiket table table-bordered">
                        </table>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td id="simpan">
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="sppdForm" id="saveClose" name="saveClose"
                                                   onBeforeTopics="beforeProcessSaveEditClose" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger"  onclick="resett()">
                                            <i class="fa fa-refresh"></i> Cancel
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

<script type="application/javascript">
    window.resett = function(){
        SppdAction.resetReroute(function(listdata) {
        });
        window.location.href = 'initForm_sppd.action';
    }

    window.cekTanggal = function(){
        var personId = document.getElementById("sppdReroutePersonId").value;
        var tanggal = document.getElementById("tanggalKeReroute").value;
        if(personId == ''){
            alert('Nama Harus diisi terlebih dahulu');
            $('#tanggalKeReroute').val('');
            /*$('#sppdRerouteId').val('');
            $('#sppdReroutePersonId').val('');
            $('#sppdReroutePersonIdOld').val('');*/
        }else{
            SppdAction.cekReroute(personId, tanggal, function(listdata) {
                if(listdata == false){
                    $('#tanggalKeReroute').val('');
                    alert('Tanggal awal reroute tidak boleh kurang dari tanggal Akhir SPPD');
                }
            });
        }
    }

    window.cekBiayaLokal = function(){
        var berangkatDari   = document.getElementById("berangkatDari").value;
        SppdAction.cekBiayaLokal(berangkatDari ,function(listdata) {
            if(listdata == false){
                $('#rerouteDari').val('');
                $('#berangkatDari').val('');
                alert('Data master berangkat belum ada');
            }
        });
    }

    window.cekDokumenTraining = function(){
        var sppdId = document.getElementById("sppdId").value;
        SppdAction.cekDokumenTraining(sppdId, function(listdata) {
            if(listdata == false){
                $('#rerouteKe').val('');
                $('#berangkatKe').val('');
                alert('Data master tujuan belum ada');
            }
        });
        alert(sppdId);
    }

    window.cekBiayaLokal2 = function(){
        var tujuanKe   = document.getElementById("rerouteKe").value;
        SppdAction.cekBiayaLokal(tujuanKe ,function(listdata) {
            if(listdata == false){
                $('#rerouteKe').val('');
                $('#berangkatKe').val('');
                alert('Data master tujuan belum ada');
            }
        });
    }

    $(document).ready(function() {
        var close = document.getElementById("close").value;

        if(close == "Y"){
            //$("#simpan").hide();
            $("#btnAddTiket").hide();
        }else{
            $("#simpan").show();
            $("#btnAddTiket").show();
        }

        $('#tanggalBerangkat').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKembali').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalBerangkatRealisasi').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKembaliRealisasi').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        var sppdId = document.getElementById("sppdId").value;
        loadUpload(sppdId);
        loadReroute(sppdId);
        loadTiket(sppdId);

        document.getElementById("sppdId1").value = sppdId;

        $('#btnAddDocument').click(function(){
            /*var file = document.getElementById("docFile").value;*/
            var file = dwr.util.getValue('file');
            var note = document.getElementById("docNote").value;
            var sppdId = document.getElementById("sppdId").value;

            SppdAction.addSppdDoc(sppdId, file, note, function(listdata) {
            });
        });

        $('#tanggalDariReroute').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKeReroute').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        //Upload Document Menegement
        $('.btnDocument').click(function(){
            $('#modal-edit').find('.modal-title').text('Edit Upload');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'Edit');
        });

        $('.sppdTableUpload').on('click', '.item-editDocument', function(){
            var id = $(this).attr('data');

            SppdAction.searchSppdDocumentEdit(id,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#docNoteEdit').val(item.note);
                    $('#sppdId1Edit').val(item.sppdId);
                    $('#docSppdId').val(item.docSppdId);
                });
            });

            $('#modal-UploadEdit').find('.modal-title').text('Edit');
            $('#modal-UploadEdit').modal('show');
            $('#urlProcess').attr('action', 'saveEdit_sppd');

        });

        $('.sppdTableUpload').on('click', '.item-deleteDocument', function(){
            var id = $(this).attr('data');

            SppdAction.searchSppdDocumentEdit(id,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#docNoteDelete').val(item.note);
                    $('#docSppdIdDelete').val(id);
                    $('#sppdId1Edits').val(item.sppdId);
                });
            });

            $('#modal-UploadDelete').find('.modal-title').text('Delete Reroute');
            $('#modal-UploadDelete').modal('show');
            $('#modal-UploadDelete').attr('action', 'Delete');
        });

        $('.sppdTableUpload').on('click', '.item-downloadDocument', function(){
            var id = $(this).attr('data');
            //alert(id);
            SppdAction.viewDoc(id,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#docNoteDelete').val(item.note);
                    $('#docSppdIdDelete').val(id);
                    $('#sppdId1Edits').val(item.sppdId);
                });
            });
        });
        //end Upload Document Menegement

        //Reroute Management
        $('#btnReroute').click(function(){
            var sppdId = document.getElementById("sppdId").value;
            var tujuanKe = document.getElementById("tujuanKe").value;
            var tujuanKeId = document.getElementById("tujuanKeId").value;

            SppdAction.searchAnggotaApproved(sppdId,function(listdata) {});

            $('#myFormReroute')[0].reset();
            $('#rerouteDari').val(tujuanKeId);
            $('#berangkatDari').val(tujuanKe);

            $('#sppdReroutePersonId').attr('readonly', false);
            $('#berangkatDari').attr('readonly', true);
            $('#rerouteKe').attr('readonly', false);
            $('#berangkatKe').attr('readonly', false);
            $('#sppdRerouteId').attr('readonly', false);
            $('#sppdRerouteKeterangan').attr('readonly', false)
            $('#tanggalKeReroute').attr('readonly', false)
            $('#berangkatViaReroute').attr('readonly', false)
            $('#tiketReroute').attr('readonly', false)

            $('#modal-reroute').find('.modal-title').text('Add Reroute');
            $('#modal-reroute').modal('show');
            $('#myFormReroute').attr('action', 'Add');
            $("#btnAddReroute").html('Save');
        });

        $('#btnAddReroute').click(function(){
            var url = $('#myFormReroute').attr('action');
            var id2 = document.getElementById("sppdRerouteId").value;

            var sppdId          = document.getElementById("sppdId").value;
            var sppdPersonId    = document.getElementById("sppdReroutePersonId").value;
            var id              = document.getElementById("sppdReroutePersonIdOld").value;
            var rerouteDari     = document.getElementById("rerouteDari").value;
            var rerouteKe       = document.getElementById("rerouteKe").value;
            var berangkatDari   = document.getElementById("berangkatDari").value;
            var berangkatKe     = document.getElementById("berangkatKe").value;
            var keterangan      = document.getElementById("sppdRerouteKeterangan").value;
            var personName1     = document.getElementById("sppdReroutePersonId");
            var tanggalDari     = document.getElementById("tanggalDariReroute").value;
            var tanggalKe       = document.getElementById("tanggalKeReroute").value;

            var tiketPergi      = document.getElementById("rerouteTiketPergi").value;
            var tiketKembali    = document.getElementById("rerouteTiketKembali").value;
            var biayaTambahan   = document.getElementById("rerouteBiayaTambahan").value;
            var biayaLokal      = document.getElementById("rerouteBiayaLokal").value;
            var biayaTujuan     = document.getElementById("rerouteBiayaTujuan").value;
            var biayaKembali    = document.getElementById("rerouteBiayaKembali").value;

            var berangkatVia = document.getElementById("berangkatViaReroute").value;
            var personName  = personName1.options[personName1.selectedIndex].text;

            if(url == 'Add'){
                if(sppdPersonId != '' && rerouteKe != '' && tanggalKe != '' ){
                    if(cekNipReroute()){
                        var hasil = cekTanggalReroute();
                        if(hasil == '-'){
                            if (confirm('Are you sure you want to save this Record?')) {
                                SppdAction.saveAddReroute(sppdId, sppdPersonId, personName,
                                        rerouteDari, rerouteKe, berangkatDari, berangkatKe, tanggalDari, tanggalKe , berangkatVia, tiketPergi, tiketKembali,
                                        biayaTambahan, biayaLokal, biayaTujuan, biayaKembali, keterangan, function(listdata) {
                                            alert('Data Successfully Added');
                                            $('#modal-reroute').modal('hide');
                                            $('#myFormReroute')[0].reset();
                                            loadReroute();
                                            location.reload();
                                        });
                            }
                        }else{
                            alert(hasil);
                        }
                    }else{
                        alert("Reroute hanya berlaku 1 kali");
                    }
                }else{
                    alert('Field must be Entry');
                }
            }else if(url == 'Edit'){
                if(sppdPersonId != '' && rerouteKe != '' && tanggalKe != '' ){
                    var hasil = cekTanggalReroute();
                    if(hasil == '-'){
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            if (confirm('Are you sure you want to save this Record?')) {
                                dwr.engine.setAsync(false);
                                SppdAction.saveEditReroute(id2, sppdPersonId, personName, rerouteDari, rerouteKe, berangkatDari, berangkatKe, tanggalDari, tanggalKe,
                                        keterangan, berangkatVia, tiketPergi, tiketKembali, biayaTambahan, biayaLokal, biayaTujuan, biayaKembali, function(listdata) {
                                            alert('Data Successfully Updated');
                                            $('#modal-reroute').modal('hide');
                                            $('#myFormReroute')[0].reset();
                                            loadReroute();
                                            location.reload();
                                        });
                            }
                        }
                    }else{
                        alert(hasil);
                    }
                }else{
                    alert('Cek kembali form reroute');
                }
            }else{
                if (confirm('Are you sure you want to delete this Record?')) {
                    SppdAction.saveDeleteReroute(id2, function(listdata) {
                        alert('Data Successfully Deleted');
                        $('#modal-reroute').modal('hide');
                        $('#myFormReroute')[0].reset();
                        loadReroute();
                        location.reload();
                    });
                }
            }
        });

        $('.sppdTableReroute').on('click', '.item-editReroute', function(){
            var sppdId = $(this).attr('sppdId');
            var id = $(this).attr('data');
            SppdAction.searchEditReroute(sppdId, id,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#sppdReroutePersonIdOld').val(item.sppdPersonId).change();
                    $('#sppdReroutePersonId').val(item.sppdPersonId).change();
                    $('#rerouteDari').val(item.rerouteDari);
                    $('#rerouteKe').val(item.rerouteKe);
                    $('#berangkatDari').val(item.kotaDari);
                    $('#berangkatKe').val(item.kota);
                    $('#sppdRerouteId').val(item.sppdRerouteId);
                    $('#sppdRerouteKeterangan').val(item.keterangan);
                    $('#tanggalDariReroute').val(item.stTanggalRerouteDari);
                    $('#tanggalKeReroute').val(item.stTanggalRerouteKe);
                    $('#berangkatViaReroute').val(item.berangkatVia);

                    $('#rerouteBiayaKembali').val(item.transportLokalKembali);
                    $('#rerouteBiayaTujuan').val(item.transportTujuan);
                    $('#rerouteBiayaLokal').val(item.transportLokalBerangkat);
                    $('#rerouteBiayaTambahan').val(item.biayaTambahan);
                    $('#rerouteTiketKembali').val(item.tiketKembali);
                    $('#rerouteTiketPergi').val(item.tiketPergi);
                });
            });

            $('#sppdReroutePersonId').attr('readonly', false);
            $('#rerouteKe').attr('readonly', false);
            $('#berangkatKe').attr('readonly', false);
            $('#sppdRerouteId').attr('readonly', false);
            $('#sppdRerouteKeterangan').attr('readonly', false);
            $('#tanggalKeReroute').attr('readonly', false);
            $('#berangkatViaReroute').attr('readonly', false);
            $('#tiketReroute').attr('readonly', false);

            $('#rerouteBiayaKembali').attr('readonly', false);
            $('#rerouteBiayaTujuan').attr('readonly', false);
            $('#rerouteBiayaLokal').attr('readonly', false);
            $('#rerouteBiayaTambahan').attr('readonly', false);
            $('#rerouteTiketKembali').attr('readonly', false);
            $('#rerouteTiketPergi').attr('readonly', false);

            $("#btnAddReroute").html('Save');
            $('#modal-reroute').find('.modal-title').text('Edit Reroute');
            $('#modal-reroute').modal('show');
            $('#myFormReroute').attr('action', 'Edit');
        });

        $('.sppdTableReroute').on('click', '.item-deleteReroute', function(){
            var id = $(this).attr('data');
            var sppdId = $(this).attr('sppdId');

            SppdAction.searchEditReroute(sppdId, id, function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#sppdReroutePersonIdOld').val(item.sppdPersonId).change();
                    $('#sppdReroutePersonId').val(item.sppdPersonId).change();
                    $('#rerouteDari').val(item.rerouteDari);
                    $('#rerouteKe').val(item.rerouteKe);
                    $('#berangkatDari').val(item.kotaDari);
                    $('#berangkatKe').val(item.kota);
                    $('#sppdRerouteId').val(item.sppdRerouteId);
                    $('#sppdRerouteKeterangan').val(item.keterangan);
                    $('#tanggalDariReroute').val(item.stTanggalRerouteDari);
                    $('#tanggalKeReroute').val(item.stTanggalRerouteKe);
                    $('#berangkatViaReroute').val(item.berangkatVia);

                    $('#rerouteBiayaKembali').val(item.transportLokalKembali);
                    $('#rerouteBiayaTujuan').val(item.transportTujuan);
                    $('#rerouteBiayaLokal').val(item.transportLokalBerangkat);
                    $('#rerouteBiayaTambahan').val(item.biayaTambahan);
                    $('#rerouteTiketKembali').val(item.tiketKembali);
                    $('#rerouteTiketPergi').val(item.tiketPergi);
                });
                $('#sppdReroutePersonId').attr('readonly', true);
                $('#rerouteKe').attr('readonly', true);
                $('#berangkatKe').attr('readonly', true);
                $('#sppdRerouteId').attr('readonly', true);
                $('#sppdRerouteKeterangan').attr('readonly', true);
                $('#tanggalKeReroute').attr('readonly', true);
                $('#berangkatViaReroute').attr('readonly', true);
                $('#tiketReroute').attr('readonly', true);

                $('#rerouteBiayaKembali').attr('readonly', true);
                $('#rerouteBiayaTujuan').attr('readonly', true);
                $('#rerouteBiayaLokal').attr('readonly', true);
                $('#rerouteBiayaTambahan').attr('readonly', true);
                $('#rerouteTiketKembali').attr('readonly', true);
                $('#rerouteTiketPergi').attr('readonly', true);
            });

            $("#btnAddReroute").html('Delete');
            $('#modal-reroute').find('.modal-title').text('Delete Reroute');
            $('#modal-reroute').modal('show');
            $('#myFormReroute').attr('action', 'Delete');

        });

        //end Reroute


        //TIKET MENEGEMENT
        $('.sppdTableTiket').on('click', '.item-editTiket', function(){
            var id = $(this).attr('data');
            var sppdId = $(this).attr('sppdd');

            var tanggalBerangkat = document.getElementById("tanggalBerangkatRealisasi").value;
            SppdAction.searchAnggota2(sppdId, id,function(listdata) {
                $.each(listdata, function (i, item) {
                    /*if(item.tipePerson == 'Anggota'){
                        $('#biayaTujuan').attr('readonly', true);
                    }else{
                        $('#biayaTujuan').attr('readonly', false);
                    }*/
                    $('#sppdTiketSppd1').val(sppdId);
                    $('#sppdTiketPersonId1').val(item.personId);
                    $('#sppdTiketPersonId').val(item.personName);
                    $('#hargaTiketPergi').val(item.tiketPergi);
                    $('#hargaTiketKembali').val(item.tiketKembali);
                    $('#tanggalBerangkat1').val(item.stTanggalBerangkat);
                    $('#tanggalKembali1').val(item.stTanggalKembali);
                    $('#tanggalBerangkatRealisasi').val(item.stTanggalBerangkatRealisasi);
                    $('#tanggalKembaliRealisasi').val(item.stTanggalKembaliRealisasi);
                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.kembaliVia);
                    $('#biayaTambahan').val(item.biayaTambahan);
                    $('#biayaLokal').val(item.biayaLokalBerangkat);
                    $('#biayaTujuan').val(item.biayaTujuan);
                    $('#biayaKembali').val(item.biayaLokalKembali);

                    $('#editBerangkatDariId').val(item.berangkatDari);
                    $('#editBerangkatDari').val(item.berangkatDariNama);
                    $('#editTujuanKeId').val(item.tujuanKe);
                    $('#editTujuanKe').val(item.tujuanKeNama);

                    $('#sppdTanggalId1').val(tanggalBerangkat);
                    $('#sppdTanggalId2').val(item.sppdTanggalId2);

                    $('#branchTiketId').val(item.branchId).change();
                    $('#divisiTiketId').val(item.divisiId).change();
                    $('#positionTiketId').val(item.positionId).change();
                });
            });

            $("#btnAddReroute").html('Edit');
            $('#modal-tiket').find('.modal-title').text('Realisasi SPPD');
            $('#modal-tiket').modal('show');
            $('#myFormTiket').attr('action', 'Edit');
        });

        $('.sppdTableTiket').on('click', '.item-editTanggal', function(){
            var id = $(this).attr('data');
            var sppdId = $(this).attr('sppdd');
            $('#sppdTableTanggalId').val(id);

            $('.sppdTableTanggal').find('tbody').remove();
            $('.sppdTableTanggal').find('thead').remove();

            var tmp_table = "<thead style='color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'><input type='checkbox' onclick='check_uncheck_checkbox(this.checked)' class='checkApprove' " +
                    "id='checkAll'></th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal Sppd</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Hari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Biaya Lain</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Biaya Makan</th>"+
                    "</tr></thead>";

            var i = i ;

            SppdAction.searchSppdTanggal(id,function(listdata) {
                $.each(listdata, function (i, item) {
                    var combo = "";
                    var biayaLain = "";
                    var biayaMakan = "";
                    var status = "";

                    if(item.sppdTanggalApprove == "Y"){
                        combo = '<input checked type="checkbox" id="checkApprove" name="checkAll" value="'+item.idSppdTanggal+'" class="checkApprove">';
                    }else{
                        combo = '<input type="checkbox" id="checkApprove" name="checkAll" value="'+item.idSppdTanggal+'" class="checkApprove">';
                    }
                    biayaLain = '<input type="text" idnya="'+item.idSppdTanggal+'" name="biayaLain_" class="form-control L_'+item.idSppdTanggal +'"  value="'+item.biayaLain+'" style="text-align: right" >';
                    biayaMakan = '<input type="text" idnya="'+item.idSppdTanggal+'" name="biayaMakan_" class="form-control M_'+item.idSppdTanggal+ '" value="'+item.biayaMakan+'" style="text-align: right" >';

                    tmp_table += '<tr  ">' +
                            '<td align="center">' + combo + '</td>' +
                            '<td align="center">' + item.stSppdTanggal+ '</td>' +
                            '<td align="center">' + item.hari+ '</td>' +
                            '<td align="center">' + biayaLain+ '</td>' +
                            '<td align="center">' + biayaMakan+ '</td>' +
                            "</tr>";
                });
            });

            $('.sppdTableTanggal').append(tmp_table);

            $("#btnAddTanggal").html('Edit');
            $('#modal-tanggal').find('.modal-title').text('Realisasi Tanggal');
            $('#modal-tanggal').modal('show');
            $('#myFormTanggal').attr('action', 'Edit');
        });

        $('#btnAddTiket').click(function(){
            var sppdId = document.getElementById("sppdTiketSppd1").value;
            var nip = document.getElementById("sppdTiketPersonId1").value;
            var hargaPergi = document.getElementById("hargaTiketPergi").value;
            var hargaKembali = document.getElementById("hargaTiketKembali").value;
            var biayaTambahan = document.getElementById("biayaTambahan").value;
            var biayaLokal = document.getElementById("biayaLokal").value;
            var biayaTujuan= document.getElementById("biayaTujuan").value;
            var biayaKembali= document.getElementById("biayaKembali").value;
            var tanggalBerangkat = document.getElementById("tanggalBerangkatRealisasi").value;
            var tanggalKembali = document.getElementById("tanggalKembaliRealisasi").value;
            var berangkatVia = document.getElementById("berangkatVia1").value;
            var kembaliVia = document.getElementById("kembaliVia1").value;

            var berangkatDariId = document.getElementById("editBerangkatDariId").value;
            var berangkatDari = document.getElementById("editBerangkatDari").value;
            var tujuanKeId = document.getElementById("editTujuanKeId").value;
            var tujuanKe = document.getElementById("editTujuanKe").value;

            var tgl1 = new Date();
            var tgl2 = new Date();

            var strTgl1 = tanggalBerangkat.split("-");
            var strTgl2 = tanggalKembali.split("-");

            var bln1 = parseInt(strTgl1[1]) - 1;
            var bln2 = parseInt(strTgl2[1]) - 1;

            tgl1.setFullYear(strTgl1[2], bln1 , strTgl1[0]);
            tgl2.setFullYear(strTgl2[2], bln2 , strTgl2[0]);

            if(tgl2 < tgl1){
                alert('Tanggal berangkat tidak boleh melebihi tanggal kembali / sebaliknya');
            }else{
                if(hargaPergi != '' && hargaKembali != '' && biayaTambahan != '' && biayaLokal != '' && biayaTujuan != ''){
                    SppdAction.cekAvailableSppdEdit(nip, tanggalBerangkat, tanggalKembali, sppdId, function(listdata) {
                        if (listdata == "-") {
                            if (confirm('Are you sure you want to Update this Record?')) {
                                SppdAction.SaveTiket2(sppdId, nip, hargaPergi, hargaKembali, biayaTambahan, tanggalBerangkat, tanggalKembali,
                                        berangkatVia, kembaliVia, biayaLokal, biayaTujuan, biayaKembali, berangkatDariId, tujuanKeId, function(listdata) {
                                            alert('Data Successfully Updated');
                                            $('#modal-tiket').modal('hide');
                                            $('#myFormTiket')[0].reset();
                                            loadTiket(sppdId);
                                        });
                            }
                        }else{
                            alert(listdata);
                        }
                    });
                }else{
                    alert('Field Harga Tiket tidak boleh Kosong !');
                }
            }

        });

        $('#btnSaveTanggal').click(function(){
            var sppdPersonId = document.getElementById("sppdTableTanggalId").value;

            var values = new Array();
            var biayaLain_ = new Array();
            var biayaMakan_ = new Array();

            var idd ;
            $.each($("input[name='checkAll']:checked"), function(val) {
                values.push($(this).val());
            });

            $.each($("input[name='biayaLain_']"), function() {
                var id = $(this).attr('idnya');
                biayaLain_.push(id + "_"+$(this).val());
            });


            $.each($("input[name='biayaMakan_']"), function() {
                var id = $(this).attr('idnya');
                biayaMakan_.push(id + "_"+$(this).val());
            });


            var i = 0 ;
            if(values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    SppdAction.removeTanggalSppd(sppdPersonId);
                    var biayaLain = new Array(values.length);
                    var biayaMakan = new Array(values.length);
                    var strBiayaLain = new Array(values.length);
                    var strBiayaMakan = new Array(values.length);

                    for(a = 0 ; a < values.length ; a++){
                        var makan = 0;
                        var lain = 0;
                        for(b = 0 ; b < biayaMakan_.length ; b++){
                            var strId = biayaMakan_[b].split("_");
                            if(strId[0] == values[a]){
                                makan = strId[1];
                                break;
                            }
                        }

                        for(c = 0 ; c < biayaLain_.length ; c++){
                            var strId = biayaLain_[c].split("_");
                            if(strId[0] == values[a]){
                                lain = strId[1];
                                break;
                            }
                        }

                        SppdAction.saveTanggalSppd(values[a], values.length, a, lain, makan, function(listdata) {
                            $('#modal-tanggal').modal('hide');
                            $('.sppdTableTanggal').find('tbody').remove();
                            $('.sppdTableTanggal').find('thead').remove();
                            $('.sppdTableTanggal').find('tfoot').remove();
                        });
                    }

                    /*$.each($("input[name='checkAll']:checked"), function() {


                        i = i + 1 ;
                    });*/
                    alert('Tanggal SPPD berhasil dirubah');
                }
            }else{
                alert('Silahkan Centang Tanggal yang akan di Approve !');
            }
        });

    });

    window.check_uncheck_checkbox= function (isChecked) {
        if(isChecked) {
            $('input[name="checkAll"]').each(function() {
                this.checked = true;
            });
        } else {
            $('input[name="checkAll"]').each(function() {
                this.checked = false;
            });
        }
    }

    window.loadUpload =  function(id, status){
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();

        $('.sppdTableUpload').find('tbody').remove();
        $('.sppdTableUpload').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchDocument(id, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Delete</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>File Doc</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-editDocument' data ='"+item.docSppdId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-deleteDocument' data ='"+item.docSppdId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='/hris/pages/upload/file/sppd/" + item.fileUploadDoc + "' >" +
                        "<i class='fa fa-download' style='font-size:20px'></i>"+
                        '</a>' +
                        '</td>' +
                        /*'<td> <a href="' + item.fileUploadDoc+ '"download>Download</a></td>' +*/
                        '<td>' + item.note+ '</td>' +
                        "</tr>";
            });
            $('.sppdTableUpload').append(tmp_table);

        });
    }
    window.loadReroute =  function(sppdId, status){
        $('.sppdTableReroute').find('tbody').remove();
        $('.sppdTableReroute').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchReroute(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Delete</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nip</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Person Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Transport Lokal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Transport Tujuan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Transport Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-editReroute' sppdId = '"+item.sppdId+"' data ='"+item.sppdRerouteId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-deleteReroute' sppdId = '"+item.sppdId+"' data ='"+item.sppdRerouteId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td >' + item.nip+ '</td>' +
                        '<td align="center">' + item.nama + '</td>' +
                        '<td align="center">' + item.posisi + '</td>' +
                        '<td align="center">' + item.transportLokalBerangkat+ '</td>' +
                        '<td align="center">' + item.transportTujuan+ '</td>' +
                        '<td align="center">' + item.transportLokalKembali+ '</td>' +
                        '<td align="left">' + item.keterangan+ '</td>' +
                        "</tr>";
            });
            $('.sppdTableReroute').append(tmp_table);
        });
    }
    window.loadTiket =  function(sppdId, status){
        $('.sppdTableTiket').find('tbody').remove();
        $('.sppdTableTiket').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggotaApproved(sppdId, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Person Id</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Person Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    /*"<th style='text-align: center; background-color:  #3c8dbc''>Tiket Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tiket Kembali</th>"+*/
                    /*"<th style='text-align: center; background-color:  #3c8dbc''>Biaya Tambahan</th>"+*/
                    "<th style='text-align: center; background-color:  #3c8dbc''>Transport Lokal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Transport Tujuan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Transport Kembali</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var link = "";
                var linkTanggal = "";
                if(item.sppdApproveSdm == true){
                    link = "<a href='javascript:;' class ='item-editTiket' data ='"+item.personId+"' sppdd ='"+item.sppdId+"' > <img border='0' src=<s:url value='/pages/images/icon_edit.ico'/> name='icon_edit'> </a>";
                    linkTanggal = "<a href='javascript:;' class ='item-editTanggal' data ='"+item.sppdPersonId+"' " +
                            "sppdd ='"+item.sppdId+"' > " +
                            "<span style='font-size: 15px' class='fa fa-calendar'></span> </a>";
                }else{
                    link = "<img src=<s:url value='/pages/images/icon_not_edit.png'/> name='icon_edit'> ";
                    linkTanggal = "<img src=<s:url value='/pages/images/icon_not_edit.png'/> name='icon_edit'> ";
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + link + '</td>' +
                        '<td align="center">' + linkTanggal + '</td>' +
                        '<td >' + item.personId+ '</td>' +
                        '<td align="center">' + item.personName + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.biayaLokalBerangkat + '</td>' +
                        '<td align="center">' + item.biayaTujuan + '</td>' +
                        '<td align="center">' + item.biayaLokalKembali+ '</td>' +
                        "</tr>";
            });
            $('.sppdTableTiket').append(tmp_table);
        });
    }

    window.cekTanggalReroute =  function(status){
        var tanggalAwal = document.getElementById("tanggalDariReroute").value;
        var tanggalAkhir = document.getElementById("tanggalKeReroute").value;
        var personId = document.getElementById("sppdReroutePersonId").value;

        var hasil = '';
        SppdAction.cekTanggalReroute(tanggalAwal, tanggalAkhir, personId, function(listdata) {
            hasil = listdata;
        });

        return hasil;
    }

    window.cekNipReroute =  function(status){
        var personId = document.getElementById("sppdReroutePersonId").value;

        var hasil = '';
        SppdAction.cekNipReroute(personId, function(listdata) {
            hasil = listdata;
        });

        return hasil;
    }

    var url = new URL(window.location);
    var c = url.searchParams.get("closed");
    if(c == "Y"){

    }

</script>