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
                                                        <s:if test='absensiPegawai.branchIdUser == "01"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="absensiPegawai.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="absensiPegawai.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                                            <s:hidden id="branchId" name="pembayaranUtangPiutang.branchId" />
                                                        </s:else>
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

                                            <%--<tr>--%>
                                                <%--<td>--%>
                                                    <%--<label class="control-label"><small>Bagian :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                    <%--<table>--%>
                                                        <%--<s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagian_strukturJabatan"/>--%>
                                                        <%--<s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"--%>
                                                                  <%--listKey="bagian" listValue="bagianName" name="absensiPegawai.bagian" headerKey="" headerValue="" />--%>
                                                    <%--</table>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
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
                                                    minLength: 2,
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
                                                        <button type="button" class="btn btn-success" onclick="window.location.href='<s:url action="add_absensi.action"/>'">
                                                            <i class="fa fa-plus"></i> Add Absensi
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_absensi"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div class="form-group">
                                                <label class="control-label col-sm-5"></label>
                                                <div class="col-sm-5" style="display: none">

                                                    <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                               closeTopics="closeDialog" modal="true"
                                                               resizable="false"
                                                               height="250" width="600" autoOpen="false"
                                                               title="Searching ...">
                                                        Please don't close this window, server is processing your request ...
                                                        <br>
                                                        <center>
                                                            <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                 src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                 name="image_indicator_write">
                                                            <br>
                                                            <img class="spin" border="0"
                                                                 style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                 src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                 name="image_indicator_write">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                               resizable="false"
                                                               closeOnEscape="false"
                                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                                    >
                                                        <s:hidden id="close_pos"></s:hidden>
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                             name="icon_success">
                                                        Record has been saved successfully.
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                               resizable="false" cssStyle="text-align:left;"
                                                               height="650" width="900" autoOpen="false" title="View Detail"
                                                    >
                                                        <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                                     alt="Loading..."/></center>
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
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <br>
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Absensi</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="tableAbsensi" class="tableAbsensi table table-bordered table-striped" style="font-size: 11px">
                                                    <thead >
                                                    <tr bgcolor="#90ee90" style="text-align: center">
                                                        <td>ID </td>
                                                        <td>Tanggal </td>
                                                        <td>NIP </td>
                                                        <td>Nama</td>
                                                        <td>Posisi </td>
                                                        <td>Masuk 1</td>
                                                        <td>Pulang 1</td>
                                                        <td>Status </td>
                                                        <%--<td>Masuk 2</td>--%>
                                                        <%--<td>Pulang 2</td>--%>
                                                        <%--<td>Status 2</td>--%>
                                                        <td>Lembur </td>
                                                        <td align="center">Refresh</td>
                                                        <td align="center">View</td>
                                                        <td align="center">On Call</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResultAbsensi" var="row">
                                                        <tr>
                                                            <td style="text-align: center"><s:property value="absensiPegawaiId"/></td>
                                                            <td style="text-align: center"><s:property value="stTanggal"/></td>
                                                            <td style="text-align: center"><s:property value="nip"/></td>
                                                            <td><s:property value="nama"/></td>
                                                            <td><s:property value="jabatan"/></td>
                                                            <td style="text-align: center"><s:property value="jamMasuk"/></td>
                                                            <td style="text-align: center"><s:property value="jamPulang"/></td>
                                                            <td style="text-align: center"><s:property value="statusName"/></td>
                                                            <%--<td style="text-align: center"><s:property value="jamMasuk2"/></td>--%>
                                                            <%--<td style="text-align: center"><s:property value="jamPulang2"/></td>--%>
                                                            <%--<td style="text-align: center"><s:property value="statusName2"/></td>--%>
                                                            <td style="text-align: center"><s:property value="lembur"/></td>
                                                            <td align="center">
                                                                <s:if test="#attr.row.cekAdmin">
                                                                    <s:if test="#attr.row.cekMangkir">
                                                                        <a href="javascript:;"  tanggal="<s:property value="%{#attr.row.stTanggal}"/>" nip="<s:property value="%{#attr.row.nip}"/>" href="javascript:;" class="item-refresh" cssClass="item-refresh">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_reset.png"/>" name="icon_refresh">
                                                                        </a>
                                                                    </s:if>
                                                                </s:if>
                                                            </td>
                                                            <td align="center">
                                                                <s:url var="urlViewDelete" namespace="/absensi" action="view_absensi" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.absensiPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                                </sj:a>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#attr.row.tipeHari=="on_call"'>
                                                                    <a href="javascript:;"
                                                                       tanggal="<s:property value="%{#attr.row.stTanggal}"/>"
                                                                       nip="<s:property value="%{#attr.row.nip}"/>"
                                                                       nama="<s:property value="%{#attr.row.nama}"/>"
                                                                       posisi="<s:property value="%{#attr.row.jabatan}"/>"
                                                                       href="javascript:;" class="item-view-oncall">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>">
                                                                    </a>
                                                                </s:if>
                                                            </td>
                                                        </tr>
                                                    </s:iterator>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<div class="modal fade" id="modal-detail-oncall">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail On Call</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">NIP</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_nip" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Nama</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_nama" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Jabatan</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_posisi" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <table style="width: 100%;"
                                           class="historyOncallTable table table-bordered">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
<script>
    $(document).ready(function() {
        window.loadHistoryOnCall = function () {
            $('.historyOncallTable').find('tbody').remove();
            $('.historyOncallTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            AbsensiAction.searchAbsensiOnCallSession(function (listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196''>Absensi On Call ID</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196''>NIP</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jam Masuk</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jam Pulang</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196''>Lama Lembur</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.absensiOnCallId + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.jamMasuk + '</td>' +
                        '<td align="center">' + item.jamPulang + '</td>' +
                        '<td align="center">' + item.lamaLembur + '</td>' +
                        "</tr>";
                });
                $('.historyOncallTable').append(tmp_table);
            });
        };

        $('#tgl1').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tgl2').datepicker({
            dateFormat: 'dd-mm-yy'
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
        $('#tableAbsensi').DataTable({
            "pageLength": 100,
            "order": []
        });
        $('.tableAbsensi').on('click', '.item-view-oncall', function() {
            var nip =$(this).attr('nip');
            var tanggal =$(this).attr('tanggal');
            $('#mod_tanggal').val(tanggal);
            $('#mod_nama').val($(this).attr('nama'));
            $('#mod_nip').val($(this).attr('nip'));
            $('#mod_posisi').val($(this).attr('posisi'));
            AbsensiAction.searchAbsensiOnCall(nip,tanggal,function () {});

            loadHistoryOnCall();

            $("#modal-detail-oncall").modal('show');
        });

    });
</script>
