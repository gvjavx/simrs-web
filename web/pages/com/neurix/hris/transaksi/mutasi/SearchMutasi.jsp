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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MutasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
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

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>
<script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Mutasi
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="mutasiForm" method="post"  theme="simple" namespace="/mutasi" action="search_mutasi.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Mutasi Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="mutasiId" name="mutasi.mutasiId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssStyle="display: none" id="personName1" name="mutasi.nip" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield  id="personName" name="sppdPerson.personName" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#personName').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                data = listdata;
                                                //alert('aa');
                                            });
                                            //alert(prov);
                                            $.each(data, function (i, item) {
                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                var labelNip = item.nip;
                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem,
                                                    branchId : item.branch, divisiId: item.divisi, positionId : item.positionId,
                                                    golonganId : item.golonganId, point : item.point, tipePegawaiId : item.tipePegawai,
                                                    statusPegawaiId: item.statusPegawai};
                                                functions.push(labelItem);
                                            });


                                            process(functions);
                                        },

                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaAlat = selectedObj.id;
                                            document.getElementById("personName1").value = selectedObj.id;
                                            document.getElementById("personName").value = selectedObj.pegawai;

                                            $('#branchId').val(selectedObj.branchId).change();
                                            $('#divisiId').val(selectedObj.divisiId).change();
                                            $('#golonganId').val(selectedObj.golonganId).change();
                                            $('#point').val(selectedObj.point).change();
                                            $('#positionId').val(selectedObj.positionId).change();
                                            $('#tipePegawai1').val(selectedObj.tipePegawaiId).change();
                                            $('#statusPegawai1').val(selectedObj.statusPegawaiId).change();
                                            branc = selectedObj.branchId;
                                            dev = selectedObj.divisiId ;
                                            return namaAlat;
                                        }
                                    });
                                </script>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="mutasi.branchLamaId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'M':'Mutasi', 'R':'Resign', 'P':'Pensiun'}" id="flag" name="mutasi.tipeMutasi"
                                                  headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Divisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="mutasi.divisiLamaId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Position :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="mutasi.positionLamaId"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>


                        </table>


                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="mutasiForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <a href="add_mutasi.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Mutasi</a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_mutasi"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="100%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="700" autoOpen="false"
                                                   title="Mutasi">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfSppd" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfSppd" class=" tableSppd table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_sppd.action" id="row" pagesize="14" style="font-size:10">

                                            <display:column  title="Print">
                                                <s:url var="urlPrint" namespace="/mutasi" action="printReportMutasi_mutasi" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.mutasiId"/></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                </s:url>
                                                <s:a href="%{urlPrint}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                </s:a>
                                            </display:column>

                                            <%--<display:column media="html" title="Download SK" style="align= center">
                                                <s:if test="#attr.row.SK1">
                                                    &lt;%&ndash;<a href="<s:property value="%{#attr.row.filePath}"/>">
                                                        <i class="fa fa-download" style="font-size:20px"></i>
                                                    </a>&ndash;%&gt;
                                                    <s:url var="urlViewDoc" namespace="/mutasi" action="viewDoc_mutasi" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.mutasiId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">
                                                        <i class='fa fa-download' style='font-size:20px'></i>
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Upload SK">
                                                <s:url var="urlEdit" namespace="/mutasi" action="edit_mutasi" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.mutasiId"/></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                </sj:a>
                                            </display:column>--%>

                                            <display:column property="mutasiId" sortable="true" title="Mutasi ID"  />
                                            <display:column property="nip" sortable="true" title="NIP"  />
                                            <display:column property="nama" sortable="true" title="Nama Pegawai"  />
                                            <display:column property="branchLamaName" sortable="true" title="Branch Lama"  />
                                            <display:column property="divisiLamaName" sortable="true" title="Divisi Lama"  />
                                            <display:column property="positionLamaName" sortable="true" title="Position Lama"  />
                                            <display:column property="branchBaruName" sortable="true" title="Branch Baru"  />
                                            <display:column property="divisiBaruName" sortable="true" title="Divisi Baru"  />
                                            <display:column property="positionBaruName" sortable="true" title="Position Baru"  />
                                            <display:column property="stTanggalEfektif" sortable="true" title="Tanggal Efektif"  />

                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
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

