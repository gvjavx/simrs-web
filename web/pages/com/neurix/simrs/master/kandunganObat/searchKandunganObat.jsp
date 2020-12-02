<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

    <script type='text/javascript'>
        function link(){
            window.location.href="<s:url action='initForm_kandunganObat'/>";
        }
    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KandunganObatAction.js"/>'></script>

    <style>
        .top-7{
            margin-top: 7px;
        }
    </style>
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
            Kandungan Obat
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Kandungan Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="kandunganObatForm" method="post" namespace="/kandunganObat" action="search_kandunganObat.action" theme="simple" cssClass="form-horizontal">
                                <s:hidden name="kandunganObat.isKp"/>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID :</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_kandungan" cssStyle="margin-top: 7px"
                                                     name="kandunganObat.idKandungan" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kandungan Obat :</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_kandunagan" cssStyle="margin-top: 7px"
                                                     name="kandunganObat.kandungan" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select cssClass="form-control" cssStyle="margin-top: 7px" list="#{'N':'Non Active'}" headerKey="Y" headerValue="Active" name="kandunganObat.flag"/>
                                    </div>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <%--<a type="button" class="btn btn-primary" onclick="searchFunc()"><i--%>
                                                <%--class="fa fa-search"></i> Search</a>--%>
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="kandunganObatForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>

                                        <s:if test='kandunganObat.isKp == "Y"'>
                                            <s:url var="urlAdd" namespace="/kandunganObat" action="add_kandunganObat" escapeAmp="false"></s:url>
                                            <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                <i class="fa fa-plus"></i>
                                                Add Kandungan Obat
                                            </sj:a>
                                        </s:if>

                                        <a type="button" class="btn btn-danger" href="initForm_kandunganObat.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
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
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="450" width="600" autoOpen="false" title="Add Kode Rekening"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>

                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Kandungan Obat</h3>
                    </div>

                    <div class="box-body">
                        <table id="tb_kandunganObat" class="table table-bordered" style="font-size: 13px;">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID</td>
                                <td>Kandungan Obat</td>
                                <td>Created Date</td>
                                <td>Created By</td>
                                <td>Last Update</td>
                                <td>Updated By</td>
                                <td>Flag</td>
                                <td>Action</td>
                                <s:if test='kandunganObat.isKp == "Y"'>
                                    <td>Edit</td>
                                    <td>Delete</td>
                                </s:if>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfPeriksaLab" var="row">
                                <tr class="tb-item">
                                    <td><s:property value="idKandungan"/></td>
                                    <td><s:property value="kandungan"/></td>
                                    <td><s:property value="createdDate"/></td>
                                    <td><s:property value="createdWho"/></td>
                                    <td><s:property value="lastUpdate"/></td>
                                    <td><s:property value="lastUpdateWho"/></td>
                                    <td><s:property value="flag"/></td>
                                    <td><s:property value="action"/></td>
                                    <s:if test='kandunganObat.isKp == "Y"'>
                                        <td align="center">
                                            <a href="javascript:" class="item-edit" data="<s:property value="idKandungan"/>">
                                                <img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>
                                            </a>
                                        </td>
                                        <td align="center">
                                            <a href="javascript:" class="item-delete" data="<s:property value="idKandungan"/>">
                                                <img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_delete'>
                                            </a>
                                        </td>
                                    </s:if>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<%--MODAL EDIT--%>
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Data</h4>
            </div>
            <div class="modal-body">
                <s:form id="kandunganObatEditForm" method="post" theme="simple" namespace="/kandunganObat" action="saveEdit_kandunganObat" cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >ID :</label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="idKandunganEdit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Kandungan Obat :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="kandunganObatEdit">
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button id="btnEdit" type="button" class="btn btn-default btn-primary"><i class="fa fa-pencil"></i> Edit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<%--MODAL DELETE--%>
<div id="modal-delete" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Data</h4>
            </div>
            <div class="modal-body">
                <s:form id="kandunganObatDeleteForm" method="post" theme="simple" namespace="/kandunganObat" action="saveEdit_kandunganObat" cssClass="well form-horizontal">
                    <div  class="form-group">
                        <label class="control-label col-sm-4" >ID :</label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="idKandunganDelete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Kandungan Obat :</label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="kandunganObatDelete">
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" class="btn btn-default btn-primary"><i class="fa fa-trash"></i> Delete</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-refresh"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    $(document).ready(function () {
        //btn Save
        $('#btnEdit').click(function(){
            var idKandungan         = $('#idKandunganEdit').val();
            var kandunganObat       = $('#kandunganObatEdit').val();
            var result              = '';

            if(idKandungan != '' && kandunganObat != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    KandunganObatAction.saveEdit(idKandungan, kandunganObat, "edit", function(result) {
                        if (result==""){
                            alert('Record has been saved successfully.');
                            location.reload();
                        } else{
                            alert(result);
                        }
                    });
                }
            }else{
                if (idKandungan==""){
                    result+="ID kandungan obat tidak terdeteksi \n";
                }
                if (kandunganObat==""){
                    result+="Kandungan Obat masih kosong \n";
                }
                alert(result);
            }
        });

        $('#btnDelete').click(function(){
            var idKandungan = $('#idKandunganDelete').val();
            var result = '';
            if(idKandungan != ''){
                if (confirm('Are you sure you want to delete this Record?')) {
                    KandunganObatAction.saveEdit(idKandungan, "","delete",function(listdata) {
                        if (listdata==""){
                            alert('Record has been delete successfully.');
                            location.reload();
                        } else{
                            alert(listdata);
                        }
                    });
                }
            }else{
                if (idKandungan==""){
                    result+="ID kode rekening tidak terdeteksi \n";
                }
                alert(result);
            }
        });

        $('#tb_kandunganObat').on('click', '.item-edit', function(){
            var id = $(this).attr('data');
            KandunganObatAction.initKandunganObatSearch(id,"",function(listdata) {
                $.each(listdata, function(i,item){
                    $('#idKandunganEdit').val(item.idKandungan);
                    $('#kandunganObatEdit').val(item.kandungan);
                });
            });
            $('#modal-edit').modal('show');
        });

        $('#tb_kandunganObat').on('click', '.item-delete', function(){
            var id = $(this).attr('data');
            KandunganObatAction.initKandunganObatSearch(id,"",function(listdata) {
                $.each(listdata, function(i,item){
                    $('#idKandunganDelete').val(item.idKandungan);
                    $('#kandunganObatDelete').val(item.kandungan);
                });
            });
            $('#modal-delete').modal('show');
        });
    });
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>