<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var nameSmkKategori    = document.getElementById("smkKategoriAspekName1").value;
            var bobot               = document.getElementById("smkKategoriAspekBobot1").value;
            var tipeAspek            = document.getElementById("branchId1").value;
            var branchId            = document.getElementById("tipeAspekId1").value;


            if (nameSmkKategori != '' && bobot != '' && branchId != '' && tipeAspek != '') {
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

                if (nameSmkKategori == '') {
                    msg += 'Field <strong> Kategori Aspek Name</strong> is required.' + '<br/>';
                }

                if (bobot == '') {
                    msg += 'Field <strong>Bobot</strong> is required.' + '<br/>';
                }

                if (branchId == '') {
                    msg += 'Field <strong>Branch </strong> is required.' + '<br/>';
                }

                if (tipeAspek == '') {
                    msg += 'Field <strong>Tipe Aspek</strong> is required.' + '<br/>';
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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/smkKategoriAspek" action="saveDelete_smkKategoriAspek" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Data Peristiwa</legend>


                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                    <tr style="display: none;">
                        <td>
                            <label class="control-label"><small>Peristiwa:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="aspekActivityMonthlyId" name="smkEvaluasiPegawaiAspekActivityPeristiwa.aspekActivityMonthlyId" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>

                <center>
                    <sj:dialog id="viewMonthlyPeristiwa" openTopics="showDialogMenu1" modal="true"
                               height="375" width="500" autoOpen="false"
                               title="Peristiwa">
                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                    </sj:dialog>

                    <h4>
                        Add
                        <s:url var="urlEdit" namespace="/smk" value="addPeristiwa_smk" escapeAmp="false">
                            <s:param name="id"><s:property value="%{smkEvaluasiPegawaiAspekActivityPeristiwa.aspekActivityMonthlyId}"/></s:param>
                        </s:url>
                        <sj:a onClickTopics="showDialogMenu1" href="%{urlEdit}">
                            <button
                                    id="btnAddAnggota" type="button" class="btn-md btn-info"><i class="fa fa-plus"></i>
                            </button>
                        </sj:a>
                        <%--<button
                                id="btnAddAnggota" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                        </button>--%>
                    </h4>
                    <table id="showdata" width="80%">
                        <tr>
                            <td align="center">


                                    <s:set name="listAspekMonthlyPeristiwa" value="#session.listAspekMonthlyPeristiwa" scope="request" />
                                <display:table name="listAspekMonthlyPeristiwa" varTotals="totals" class="table table-condensed table-striped table-hover"
                                               id="row" pagesize="14" style="font-size:10">

                                <%--<display:column media="html" title="Edit">
                                    <s:if test="#attr.row.flagYes">
                                        <s:url var="urlEdit" namespace="/smk" action="addPeristiwa_smk" escapeAmp="false">
                                            <s:param name="id"><s:property value="#attr.row.evaluasiActivityPeristiwaId"/></s:param>
                                        </s:url>
                                        <s:a href="%{urlEdit}">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                        </s:a>
                                    </s:if>
                                </display:column>

                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                    <s:url var="urlViewDelete" namespace="/smk" action="delete_smkJabatan" escapeAmp="false">
                                        <s:param name="id"><s:property value="#attr.row.evaluasiActivityPeristiwaId" /></s:param>
                                    </s:url>
                                    <s:a  href="%{urlViewDelete}">
                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                    </s:a>
                                </display:column>--%>

                                    <display:column style="width: 23%" property="tanggalKejadian" sortable="true" title="Tanggal Kejadian" />
                                    <display:column property="kejadian" sortable="true" title="Kejadian" />
                                </display:table>
                            </td>
                        </tr>
                    </table>
                </center>

                <br>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-8">
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
                </div>

                <br>

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
