<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PlanKegiatanRawatAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_obat').addClass('active');
        });

    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Rencana Kegiatan Rawat
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Rencana Kegiatan Rawat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <%--<s:form id="permintaanForm" method="post" namespace="/planrawat" action="search_checkup.action"--%>
                                    <%--theme="simple" cssClass="form-horizontal">--%>
                                <div class="form-group form-horizontal">
                                    <div class="row">
                                        <label class="control-label col-sm-4" for="headerCheckup.idPasien">No. RM</label>
                                        <div class="col-sm-4">
                                            <s:textfield id="idPasien" cssStyle="margin-top: 7px"
                                                         name="headerCheckup.idPasien" required="false"
                                                         readonly="false" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group form-horizontal">
                                    <div class="row">
                                        <label class="control-label col-sm-4">Poli</label>
                                        <div class="col-sm-4">
                                            <s:action id="initComboPoli" namespace="/checkup"
                                                      name="getComboPelayanan_checkup"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      list="#initComboPoli.listOfPelayanan" id="idPelayanan"
                                                      name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                      listValue="namaPelayanan"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group form-horizontal">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <button class="btn btn-primary" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                        <%--<sj:submit type="button" cssClass="btn btn-success" formIds="permintaanForm"--%>
                                                   <%--id="search" name="search"--%>
                                                   <%--onClickTopics="showDialogLoading"--%>
                                                   <%--onCompleteTopics="closeDialogLoading">--%>
                                            <%--<i class="fa fa-search"></i>--%>
                                            <%--Search--%>
                                        <%--</sj:submit>--%>
                                        <a type="button" class="btn btn-danger" href="initForm_planrawat.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien dirawat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tgl Masuk</td>
                                <td>Nama Pasien</td>
                                <td>Nama Pelayanan</td>
                                <td>Diagnosa Terakhir</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body-list-plan">
                            <%--<s:iterator value="#session.listOfResult" status="listOfUsers">--%>
                                <%--<tr>--%>
                                    <%--<td><s:property value="noCheckup"/></td>--%>
                                    <%--<td><s:property value="idPasien"/></td>--%>
                                    <%--<td><s:property value="nama"/></td>--%>
                                    <%--<td><s:property value="namaPelayanan"/></td>--%>
                                    <%--<td><s:property value="statusPeriksaName"/></td>--%>
                                    <%--<td align="center">--%>
                                        <%--<img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>" src="<s:url value="/pages/images/search_flat.png"/>"--%>
                                             <%--style="cursor: pointer; width: 25px; height: 25px" onclick="detail_pasien('<s:property value="noCheckup"/>')">--%>
                                        <%--<s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">--%>
                                            <%--<s:param name="id"><s:property value="noCheckup"/></s:param>--%>
                                        <%--</s:url>--%>
                                        <%--<s:a href="%{edit}">--%>
                                            <%--<img border="0" class="hvr-grow" src="<s:url value="/pages/images/edit-flat-new.png"/>"--%>
                                                 <%--style="cursor: pointer; width: 25px; height: 25px">--%>
                                        <%--</s:a>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            <%--</s:iterator>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<script type='text/javascript'>

    function search() {

        var idPasien = $("#idPasien").val();
        var idPelayanan = $("#idPelayanan").val();

        var arrJson = [];
        arrJson.push({"id_pasien":idPasien, "id_pelayanan":idPelayanan});
        var stJson = JSON.stringify(arrJson);

        PlanKegiatanRawatAction.getSearchKegiatanRawat(stJson, function (response) {
            if (response.length > 0){

                var str = "";
                $.each(response, function (i, item) {
                    str += "<tr>" +
                        "<td>" + item.stCreatedDate + "</td>" +
                        "<td>" + item.namaPasien + "</td>" +
                        "<td>" + item.namaPelayanan + "</td>" +
                        "<td>" + item.diagnosa + "</td>" +
                        "<td><button class='btn btn-primary'><i class='fa fa-search'></i> View </button> <button class='btn btn-primary'><i class='fa fa-edit'></i> Edit</button> </td>" +
                        "</tr>";
                });

                $("#body-list-plan").html(str);

            } else {

            }
        })

    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>