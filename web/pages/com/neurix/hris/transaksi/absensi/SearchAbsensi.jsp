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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <style>
        hr{
            border: 0;
            height: 1px;
            background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
        }
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
        /** SPINNER CREATION **/

        .loader {
            position: relative;
            text-align: center;
            margin: 15px auto 35px auto;
            z-index: 9999;
            display: block;
            width: 80px;
            height: 80px;
            border: 10px solid rgba(116, 185, 255,1.0);
            border-radius: 50%;
            border-top-color: rgba(9, 132, 227,1.0);
            animation: spin 1s ease-in-out infinite;
            -webkit-animation: spin 1s ease-in-out infinite;
        }

        @keyframes spin {
            to {
                -webkit-transform: rotate(360deg);
            }
        }

        @-webkit-keyframes spin {
            to {
                -webkit-transform: rotate(360deg);
            }
        }


        /** MODAL STYLING **/

        .modal-content {
            border-radius: 0px;
            box-shadow: 0 0 20px 8px rgba(0, 0, 0, 0.7);
        }

        .modal-backdrop.show {
            opacity: 0.75;
        }

        #success_tic .page-body{
            max-width:300px;
            background-color:#FFFFFF;
            margin:10% auto;
        }
        #success_tic .page-body .head{
            text-align:center;
        }
        /* #success_tic .tic{
          font-size:186px;
        } */
        #success_tic .close{
            opacity: 1;
            position: absolute;
            right: 0px;
            font-size: 30px;
            padding: 3px 15px;
            margin-bottom: 10px;
        }
        #success_tic .checkmark-circle {
            width: 150px;
            height: 150px;
            position: relative;
            display: inline-block;
            vertical-align: top;
        }
        .checkmark-circle .background {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            background: #1ab394;
            position: absolute;
        }
        #success_tic .checkmark-circle .checkmark {
            border-radius: 5px;
        }
        #success_tic .checkmark-circle .checkmark.draw:after {
            -webkit-animation-delay: 300ms;
            -moz-animation-delay: 300ms;
            animation-delay: 300ms;
            -webkit-animation-duration: 1s;
            -moz-animation-duration: 1s;
            animation-duration: 1s;
            -webkit-animation-timing-function: ease;
            -moz-animation-timing-function: ease;
            animation-timing-function: ease;
            -webkit-animation-name: checkmark;
            -moz-animation-name: checkmark;
            animation-name: checkmark;
            -webkit-transform: scaleX(-1) rotate(135deg);
            -moz-transform: scaleX(-1) rotate(135deg);
            -ms-transform: scaleX(-1) rotate(135deg);
            -o-transform: scaleX(-1) rotate(135deg);
            transform: scaleX(-1) rotate(135deg);
            -webkit-animation-fill-mode: forwards;
            -moz-animation-fill-mode: forwards;
            animation-fill-mode: forwards;
        }
        #success_tic .checkmark-circle .checkmark:after {
            opacity: 1;
            height: 75px;
            width: 37.5px;
            -webkit-transform-origin: left top;
            -moz-transform-origin: left top;
            -ms-transform-origin: left top;
            -o-transform-origin: left top;
            transform-origin: left top;
            border-right: 15px solid #fff;
            border-top: 15px solid #fff;
            border-radius: 2.5px !important;
            content: '';
            left: 35px;
            top: 80px;
            position: absolute;
        }

        @-webkit-keyframes checkmark {
            0% {
                height: 0;
                width: 0;
                opacity: 1;
            }
            20% {
                height: 0;
                width: 37.5px;
                opacity: 1;
            }
            40% {
                height: 75px;
                width: 37.5px;
                opacity: 1;
            }
            100% {
                height: 75px;
                width: 37.5px;
                opacity: 1;
            }
        }
        @-moz-keyframes checkmark {
            0% {
                height: 0;
                width: 0;
                opacity: 1;
            }
            20% {
                height: 0;
                width: 37.5px;
                opacity: 1;
            }
            40% {
                height: 75px;
                width: 37.5px;
                opacity: 1;
            }
            100% {
                height: 75px;
                width: 37.5px;
                opacity: 1;
            }
        }
        @keyframes checkmark {
            0% {
                height: 0;
                width: 0;
                opacity: 1;
            }
            20% {
                height: 0;
                width: 37.5px;
                opacity: 1;
            }
            40% {
                height: 75px;
                width: 37.5px;
                opacity: 1;
            }
            100% {
                height: 75px;
                width: 37.5px;
                opacity: 1;
            }
        }

    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_absensi'/>";
        }
        var unit = '<s:property value="Absensi.unitId"/>'
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
            Absensi

        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="absensiForm" method="post"  theme="simple" namespace="/absensi" action="search_absensi.action" cssClass=" form-horizontal">
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
                                                    <label class="control-label"><small>Tanggal :</small></label>
                                                </td>
                                                <td>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl1" name="absensiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false" size="7"  cssStyle=""/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl2" name="absensiPegawai.stTanggalSelesai" cssClass="form-control pull-right"
                                                                     required="false" size="7"  cssStyle=""/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="absensiPegawai.branchId" required="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                    </table>
                                                </td>
                                            </tr>

                                            <%-- Updated 06-11-2018--%>
                                            <%--<tr>
                                                <td>
                                                    <label class="control-label"><small>Bidang :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                                        <s:select cssClass="form-control" list="#comboDivisi.listComboDepartment" id="divisiId" name="absensiPegawai.divisiId" required="true"
                                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" />
                                                    </table>
                                                </td>
                                            </tr>--%>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Bagian :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>
                                                        <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"
                                                                  listKey="bagian" listValue="bagianName" name="absensiPegawai.bagian" headerKey="" headerValue="" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><hr></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip" name="absensiPegawai.nip" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <script type='text/javascript'>
                                                var functions, mapped;
                                                $('#nip').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};
                                                        var data = [];
                                                        var unit = $('#branchid').val();
                                                        if (unit==""){
                                                            alert("unit is empty");
                                                            $('#namaId').val("");
                                                            $('#nip').val("");
                                                        }else {
                                                            dwr.engine.setAsync(false);
                                                            CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
                                                                data = listdata;
                                                            });
                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.nip+" "+item.namaPegawai;
                                                                mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                                functions.push(labelItem);
                                                            });
                                                            process(functions);
                                                        }
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaMember = selectedObj.label;
                                                        document.getElementById("namaId").value = selectedObj.nama;
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaId" name="" required="true" readonly="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Lembur :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'Y':'Y','N':'N'}" id="lembur" name="absensiPegawai.lembur"
                                                                  headerKey="" headerValue="" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Ijin :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'Y':'Y','N':'N'}" id="ijin" name="absensiPegawai.ijin"
                                                                  headerKey="" headerValue="" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Status :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'00':'Mangkir','01':'Sesuai','02':'Telat masuk','03':'Pulang/Masuk tidak Izin','04':'Sesuai Lebih','05':'Sesuai dengan Ijin','06':'Tidak Sesuai Ijin','07':'Ijin Lebih Dari Sehari','08':'Cuti','09':'Lembur','10':'Blokir','11':'Ijin Tidak Masuk','12':'SPPD','13':'Training'}" id="statusAbsensi" name="absensiPegawai.statusAbsensi"
                                                                  headerKey="" headerValue="" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="absensiForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <div class="btn-group">
                                                            <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                <i class="fa fa-plus"></i>
                                                                Add Absensi
                                                                <span class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li id="btnSync">
                                                                    <s:a href="#"><i class="fa fa-refresh"></i> Sync Data</s:a>
                                                                </li>
                                                                <li>
                                                                    <s:a href="add_absensi.action"><i class="fa fa-plus"></i> Add Absensi</s:a>
                                                                </li>
                                                                <%--<li>
                                                                    <s:a href="addTambahan_absensi.action"><i class="fa fa-plus"></i> Add Absensi Tambahan</s:a>
                                                                </li>--%>
                                                                    <%--<li id="btnRefreshAll">
                                                                        <s:a href="#"><i class="fa fa-refresh"></i> Refresh Data Absensi</s:a>
                                                                    </li>--%>
                                                            </ul>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_absensi"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="80%">
                                                <tr>
                                                    <td align="center">
                                                        <%--<sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"--%>
                                                                   <%--resizable="false"--%>
                                                                   <%--height="350" width="600" autoOpen="false" title="Loading ...">--%>
                                                            <%--Please don't close this window, server is processing your request ...--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--<center>--%>
                                                                <%--<img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">--%>
                                                            <%--</center>--%>
                                                        <%--</sj:dialog>--%>

                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading"
                                                                   closeTopics="closeDialogLoading" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Save Data ...">
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

                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="620" width="900" autoOpen="false"
                                                                   title="Absensi">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_absensi" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="700" autoOpen="false"
                                                                   title="Absensi">
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_keterangan" openTopics="showDialogMenuKeterangan" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Absensi">
                                                        </sj:dialog>
                                                        <s:set name="listOfAbsensi" value="#session.listOfResultAbsensi" scope="request" />
                                                        <display:table name="listOfAbsensi" class="tableAbsensi table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_absensi.action" export="true" id="row" pagesize="30" style="font-size:10">
                                                            <%--<display:column media="html" title="Refresh">
                                                                <s:if test="#attr.row.cekAdmin">
                                                                    <s:if test="#attr.row.cekMangkir">
                                                                        <a href="javascript:;"  tanggal="<s:property value="%{#attr.row.stTanggal}"/>" nip="<s:property value="%{#attr.row.nip}"/>" href="javascript:;" class="item-refresh" cssClass="item-refresh">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_reset.png"/>" name="icon_refresh">
                                                                        </a>
                                                                    </s:if>
                                                                </s:if>
                                                            </display:column>--%>
                                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.cekAdmin">
                                                                    <s:url var="urlDelete" namespace="/absensi" action="delete_absensi" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.absensiPegawaiId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenuView" href="%{urlDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>
                                                            <%--<display:column media="html" title="Edit" style="text-align:center;font-size:9">--%>
                                                                <%--<s:if test="#attr.row.cekAdmin">--%>
                                                                    <%--<s:url var="urlEdit" namespace="/absensi" action="editAbsensi_absensi" escapeAmp="false">--%>
                                                                        <%--<s:param name="id"><s:property value="#attr.row.absensiPegawaiId" /></s:param>--%>
                                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                                    <%--</s:url>--%>
                                                                    <%--<sj:a onClickTopics="showDialogMenuView" href="%{urlEdit}">--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_trash">--%>
                                                                    <%--</sj:a>--%>
                                                                <%--</s:if>--%>
                                                            <%--</display:column>--%>
                                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                                <s:url var="urlViewDelete" namespace="/absensi" action="view_absensi" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.absensiPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                                </sj:a>
                                                            </display:column>
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="nama" sortable="true" title="Nama"  />
                                                            <display:column property="jabatan" sortable="true" title="Jabatan" />
                                                            <display:column property="stTanggal" sortable="true" title="Tanggal" style="text-align:center" />
                                                            <display:column property="jamMasuk" sortable="true" title="Jam Masuk" style="text-align:center" />
                                                            <display:column property="jamKeluar" sortable="true" title="Jam Pulang" style="text-align:center" />
                                                            <display:column property="statusName" sortable="true" title="status" style="text-align:left" />
                                                            <%--<display:column property="ijin" sortable="true" title="ijin" style="text-align:center" />--%>
                                                            <display:column property="lembur" sortable="true" title="lembur" style="text-align:center" />
                                                            <display:column property="realisasiJamLembur" sortable="true" title="Realisasi Lembur" style="text-align:center" />
                                                            <%--<display:column media="html" title="Keterangan" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.cekAdmin">
                                                                    <s:if test="#attr.row.absensiApprove">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test="#attr.row.notApprove">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test="#attr.row.noted">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_list_outstanding.png"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test="#attr.row.clear">
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <s:url var="urlKeterangan" namespace="/absensi" action="addKeteranganById_absensi" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.absensiPegawaiId" /></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                        </s:url>
                                                                        <sj:a onClickTopics="showDialogMenuKeterangan" href="%{urlKeterangan}">
                                                                            <img border="0" src="<s:url value="/pages/images/edit_task.png"/>" name="icon_edit">
                                                                        </sj:a>
                                                                    </s:else>
                                                                </s:if>
                                                            </display:column>--%>
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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
<!-- Modal -->
<div class="modal fade" id="onLoading" tabindex="-1" role="dialog" aria-labelledby="loadMeLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body text-center">
                <div class="loader"></div>
                <div class="loader-txt">
                    <p>Please wait <br><br><small>Server is processing your request ...</small></p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div id="success_tic" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <a class="close" href="#" data-dismiss="modal">&times;</a>
            <div class="page-body">
                <div class="head">
                    <h3 style="margin-top:5px;">SUCCESS</h3>
                    <h4>Sync dengan mesin sukses</h4>
                </div>
                <h1 style="text-align:center;"><div class="checkmark-circle">
                    <div class="background"></div>
                    <div class="checkmark draw"></div>
                </div></h1>
            </div>
        </div>
    </div>

</div>
<script>
    $(document).ready(function() {
        $('#tgl1').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tgl2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#btnPrintAbsensi').click(function(){
            if($('#branchid').val()==""){
                alert("Unit belum diisi");
            } else if($('#tgl1').val()!=""&&$('#tgl2').val()!="") {
                var nip = $('#nip').val();
                var tgl1 = $('#tgl1').val();
                var tgl2 = $('#tgl2').val();
                var branch = $('#branchid').val();
                var bidang = $('#divisiId').val();
                var bagian = $('#bagian').val();
                window.location.href = "printReportAbsensi_absensi.action?tglFrom="+tgl1+"&tglTo="+tgl2+"&branchId="+branch+"&divisiId="+bidang+"&bagian="+bagian+"&nip="+nip;
            } else {
                alert("Tanggal ada yang kosong ");
            }
        });
        $('#btnPrintUangMakan').click(function(){
            if($('#branchid').val()==""){
                alert("Unit belum diisi");
            } else if($('#tgl1').val()!=""&&$('#tgl2').val()!="") {
                var nip = $('#nip').val();
                var tgl1 = $('#tgl1').val();
                var tgl2 = $('#tgl2').val();
                var branch = $('#branchid').val();
                var bidang = $('#divisiId').val();
                var bagian = $('#bagian').val();
                // window.location.href = "downloadXls_absensi.action?tglFrom="+tgl1+"&tglTo="+tgl2+"&branchId="+branch+"&divisiId="+bidang;
                window.location.href = "printReportUangMakan_absensi.action?tglFrom="+tgl1+"&tglTo="+tgl2+"&branchId="+branch+"&divisiId="+bidang+"&bagian="+bagian+"&nip="+nip;
            } else {
                alert("Tanggal ada yang kosong ");
            }
        });
        $('#btnPrintLembur').click(function(){
            if($('#branchid').val()==""){
                alert("Unit belum diisi");
            } else if($('#tgl1').val()!=""&&$('#tgl2').val()!="") {
                var nip = $('#nip').val();
                var tgl1 = $('#tgl1').val();
                var tgl2 = $('#tgl2').val();
                var branch = $('#branchid').val();
                window.location.href = "printReportLembur_absensi.action?tglFrom="+tgl1+"&tglTo="+tgl2+"&branchId="+branch+"&nip="+nip;
            } else {
                alert("Tanggal ada yang kosong ");
            }
        });
        $('#btnPrintRekapLembur').click(function(){
            if($('#branchid').val()==""){
                alert("Unit belum diisi");
            } else if($('#tgl1').val()!=""&&$('#tgl2').val()!="") {
                var tgl1 = $('#tgl1').val();
                var tgl2 = $('#tgl2').val();
                var branch = $('#branchid').val();
                window.location.href = "printReportRekapitulasiLembur_absensi.action?tglFrom="+tgl1+"&tglTo="+tgl2+"&branchId="+branch;
            } else {
                alert("Tanggal ada yang kosong ");
            }
        });
        $('#btnPrintTriwulan').click(function(){
            if($('#branchid').val()==""){
                alert("Unit belum diisi");
            } else if($('#tgl1').val()!=""&&$('#tgl2').val()!="") {
                var tgl1 = $('#tgl1').val();
                var tgl2 = $('#tgl2').val();
                var branch = $('#branchid').val();
                var bagian = "";
                window.location.href = "printReportAbsensiTriwulan_absensi.action?tglFrom="+tgl1+"&tglTo="+tgl2+"&branchId="+branch+"&bagian="+bagian;
            } else {
                alert("Tanggal ada yang kosong ");
            }
        });
        $('#btnSync').click(function(){
            if(confirm("apakah anda ingin melakukan sinkronisasi data dengan mesin absensi ?")){
                $("#onLoading").modal({
                    backdrop: "static", //remove ability to close modal with click
                    keyboard: false, //remove option to close with keyboard
                    show: true //Display loader!
                });
                dwr.engine.setAsync(true);
                AbsensiAction.getDataFromMesin({
                    callback : function(response){
                        $("#onLoading").modal("hide");
                        $("#success_tic").modal("show");
                    }
                });
            }
        });

        $('#btnRefreshAll').click(function(){
            if(confirm("apakah anda ingin melakukan refresh data dengan mesin absensi ?")){
                dwr.engine.setAsync(false);
                AbsensiAction.getDataFromMesin(tgl1,tgl2,function(status) {
                    if (status=="00"){
                        alert("Sync Success");
                    }
                });
            }
        });

        $('.tableAbsensi').on('click', '.item-refresh', function() {
            var tanggal = $(this).attr('tanggal');
            var nip = $(this).attr('nip');
            if (confirm("apakah anda ingin merefresh data ini ?")){
                AbsensiAction.refreshAbsensi(nip,tanggal, function (data) {
                    alert(data);
                    window.location.reload();
                });
            }
        });


    });
</script>
