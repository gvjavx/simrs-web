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
    <script type='text/javascript' src='<s:url value="/dwr/interface/SmkAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <style>
        .checkApprove {width: 20px; height: 20px;}
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

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Evaluasi Pegawai
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="smkForm" method="post"  theme="simple" namespace="/smk" action="search_smk.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>NIP:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="nip" name="smk.smkId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Periode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}" id="periodeId" name="smk.periode"
                                                  headerKey="" headerValue="[Pilih Tahun]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="smk.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bidang :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smk.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="smk.positionId"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>


                        </table>


                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <button type="button" class="btn btn-primary"
                                                onclick="searchDataSmk()">
                                            <i class="fa fa-search"></i> Search
                                        </button>

                                    </td>
                                    <td>
                                        <a href="add_smk.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add SMK</a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_smk"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="40%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="800" width="1000" autoOpen="false"
                                                   title="Sppd ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                    </td>
                                </tr>
                            </table>
                        </center>
                        <center>
                            <table id="dataSmk" width="100%">
                                <tr>
                                    <td align="center">
                                        <table class="tableDataSmk table table-bordered">
                                            <%--<thead>
                                                <tr>
                                                    <td>ulil</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        azmi
                                                    </td>
                                                </tr>
                                            </tbody>--%>
                                        </table>
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

</div>

</div>
</html>

<script>
    $(document).ready(function() {
    });

    window.searchDataSmk = function(){
        var nip         = document.getElementById("nip").value;
        var periode     = document.getElementById("periodeId").value;
        var unit        = document.getElementById("branchId").value;
        var divisi      = document.getElementById("divisiId").value;
        var jabatan     = document.getElementById("positionId").value;


        if(unit == '' || periode == ''){
            alert('Unit dan periode harus dipilih terlebih dahulu');
        }else{
            $('.tableDataSmk').find('tbody').remove();
            $('.tableDataSmk').find('thead').remove();
            SmkAction.getDataSearch(nip, periode, unit, divisi, jabatan, function(listdata) {

                tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Print</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Draft</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>View</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Periode</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Nip</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Nama</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                        "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    var closed = "";
                    var printt = "";
                    var view = "";
                    var draft = "";

                    if(item.printDraft == "Y"){
                        draft = "<a href='printReportSmk_smk?id="+item.evaluasiPegawaiId+"&positionId="+item.positionId+"&nip="+item.nip+
                                "&periode="+item.periode+"&flag=Draft" +"&status="+item.statusPegawai+
                                "' class ='item-edit' >" +
                                "<img border='0' src='<s:url value='/pages/images/icon_printer_new.ico'/>' name='icon_edit'></a>";
                    }

                    if(item.smkClosed == true){
                        var view = "<a href='edit_smk?id="+item.evaluasiPegawaiId+"&positionId="+item.positionId+"&nip="+item.nip+
                                "&branchId="+item.branchId+"&positionId="+item.positionId+
                                "&periode="+item.periode+"&status="+item.statusPegawai+"&view=Y'" +
                                "class ='item-edit' >" +
                                "<img border='0' src='<s:url value='/pages/images/icon_lup.ico'/>' name='icon_lup'></a>";
                        printt = "<a href='printReportSmk_smk?id="+item.evaluasiPegawaiId+"&nip="+item.nip+"&periode="+item.periode + "&flag=" +"&status="+item.statusPegawai+
                                "' class ='item-edit' >" +
                                "<img border='0' src='<s:url value='/pages/images/icon_printer_lama.ico'/>' name='icon_edit'></a>";
                        draft = "";
                        /*if(item.editAtasan){
                            closed = "<a href='edit_smk?id="+item.evaluasiPegawaiId+"&nip="+item.nip+"&branchId="+item.branchId+
                                    "&positionId="+item.positionId+"&periode="+item.periode+"&status="+item.statusPegawai+"&view=Y'" +
                                    "class ='item-edit' >" +
                                    "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'></a>";
                        }else{
                            closed = "";
                        }*/
                    }else{
                        closed = "<a href='edit_smk?id="+item.evaluasiPegawaiId+"&nip="+item.nip+"&branchId="+item.branchId+
                                "&positionId="+item.positionId+"&periode="+item.periode+"&status="+item.statusPegawai+"&view=N'" +
                                "class ='item-edit' >" +
                                "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'></a>";
                    }


                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td >' + printt +'</td>' +
                            '<td >' +draft+ '</td>' +
                            '<td >'+ closed +'</td>' +
                            '<td >'+view+'</td>' +
                            '<td align="center">' + item.periode+ '</td>' +
                            '<td>' + item.nip + '</td>' +
                            '<td>' + item.pegawaiName + '</td>' +
                            '<td>' + item.branchName+ '</td>' +
                            '<td>' + item.divisiName+ '</td>' +

                            '<td>' + item.positionName+ '</td>' +
                            "</tr>";
                });
                $('.tableDataSmk').append(tmp_table);

            });
        }

    }

</script>

