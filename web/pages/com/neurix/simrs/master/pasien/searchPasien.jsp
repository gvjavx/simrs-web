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
            window.location.href="<s:url action='initForm_pasien'/>";
        }

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Pasien
            <small>e-HEALTH</small>
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
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="pasienForm" method="post"  theme="simple" namespace="/pasien" action="search_pasien.action" cssClass="form-horizontal">

                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>

                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                    <table>
                                        <tr >
                                            <td>
                                                <label class="control-label"><small>ID Pasien</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="id_pasien" name="pasien.idPasien" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No KTP</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px"  id="no_ktp" name="pasien.noKtp" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Pasien</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="nama_pasien" name="pasien.nama" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Alamat</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea cssStyle="margin-top: 7px"  id="alamat" name="pasien.alamat" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="pasienForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <a href="add_pasien.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Pasien</a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_pasien"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-primary" onclick="showModalUpload()">
                                                        <i class="fa fa-plus"></i> Upload Rekam Medik Lama
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="90%">
                                            <tr>
                                                <td align="center">
                                                    <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"
                                                               resizable="false"
                                                               height="350" width="600" autoOpen="false" title="Loading ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <center>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                               height="800" width="1100" autoOpen="false"
                                                               title="Edit Pasien ">
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"
                                                               height="700" width="1100" autoOpen="false"
                                                               title="Rekruitmen ">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>

                                                    <sj:dialog id="view_dialog_user" openTopics="showDialogMenuUser" modal="true"
                                                               height="300" width="500" autoOpen="false"
                                                               title="Edit for Mobile User">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>

                                                    <s:set name="listOfPasien" value="#session.listOfResult" scope="request" />
                                                    <display:table name="listOfPasien" class="tablePasien table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_rekruitmen.action" export="true" id="row" pagesize="14" style="font-size:12">
                                                        <display:column media="html" title="Edit">
                                                                <s:url var="urlEdit" namespace="/pasien" action="edit_pasien" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idPasien"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </sj:a>
                                                        </display:column>
                                                        <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                            <s:url var="urlViewDelete" namespace="/pasien" action="delete_pasien" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.idPasien" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                            </sj:a>
                                                        </display:column>
                                                        <display:column property="idPasien" sortable="true" title="Pasien ID"  />
                                                        <display:column property="nama" sortable="true" title="Nama"  />
                                                        <display:column property="jenisKelamin" sortable="true" title="Gender"  />
                                                        <display:column property="email" sortable="true" title="Email" />
                                                        <display:column property="password" sortable="true" title="Password" />
                                                        <display:column property="noKtp" sortable="true" title="No. KTP" />
                                                        <display:column property="noBpjs" sortable="true" title="No. BPJS" />
                                                        <display:column property="tempatLahir" sortable="true" title="Temp. Lahir" />
                                                        <display:column property="tglLahir" sortable="true" title="Tgl Lahir" />
                                                        <display:column property="jalan" sortable="true" title="Jalan" />
                                                        <display:column property="agama" sortable="true" title="Agama" />
                                                        <display:column property="noTelp" sortable="true" title="No. Telp" />

                                                        <display:column media="html" title="User" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.password == null">
                                                                <s:url var="urlViewDelete" namespace="/pasien" action="view_pasien" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idPasien" /></s:param>
                                                                    <s:param name="tipe">create</s:param>
                                                                </s:url>
                                                                <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenuUser" href="%{urlViewDelete}">
                                                                    <i class="fa fa-plus"></i> Create User
                                                                </sj:a>
                                                            </s:if>
                                                            <s:else>
                                                                <s:url var="urlViewDelete" namespace="/pasien" action="view_pasien" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idPasien" /></s:param>
                                                                    <s:param name="tipe">edit</s:param>
                                                                </s:url>
                                                                <sj:a cssClass="btn btn-primary" onClickTopics="showDialogMenuUser" href="%{urlViewDelete}">
                                                                   <i class="fa fa-edit"></i> Edit Password
                                                                </sj:a>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Print Card" style="text-align:center;font-size:9">
                                                            <s:url var="urlViewDelete" namespace="/pasien" action="printCard_pasien" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.idPasien" /></s:param>
                                                            </s:url>
                                                            <s:a cssClass="btn btn-danger" onClickTopics="showDialogMenuUser" href="%{urlViewDelete}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_printer_new">
                                                            </s:a>
                                                        </display:column>
                                                        <display:column media="html" title="Add Finger">
                                                            <a href="javascript:;" data="<s:property value="%{#attr.row.idPasien}" />" class="item-register-finger">
                                                                <img border="0" src="<s:url value="/pages/images/finger.png"/>" name="icon-register-finger">
                                                            </a>
                                                        </display:column>
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

        <div class="modal fade" id="modal-upload">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #00a65a">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Pasien <span id="nama_medik"></span></h4>
                    </div>
                    <div class="modal-body">
                            <s:form id="uploadForm" method="post" enctype="multipart/form-data" theme="simple" namespace="/pasien" action="saveUploadRmLama_pasien.action" cssClass="form-horizontal" onsubmit="cekData()">
                                <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">ID Pasien</label>
                                    <div class="col-md-7">
                                        <div class="input-group">
                                            <s:textfield id="upload_pasien" name="pasien.idPasien"
                                                         onkeypress="$(this).css('border','');"
                                                         cssClass="form-control"/>
                                            <div class="input-group-btn">
                                                <a href="#" class="btn btn-primary pull-right" onclick="addInputUpload()"><i class="fa fa-plus"></i> Add Upload</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3" style="margin-top: 7px">Nama Pasien</label>
                                    <div class="col-md-7">
                                        <s:textfield id="upload_nama_pasien" name="pasien.idPasien"
                                                     cssStyle="margin-top: 7px"
                                                         onkeypress="$(this).css('border','');"
                                                         cssClass="form-control" readonly="true"/>
                                    </div>
                                </div>
                                    <script>
                                        function tesPasien(val) {
                                            $('#isi').html('<a href="#">Link 1</a><a href="#">Link 2</a><a href="#">Link 3</a>');
                                        }
                                    </script>
                                    <script type="application/javascript">
                                        var functions, mapped;
                                        $('#upload_pasien').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};

                                                var data = [];
                                                dwr.engine.setAsync(false);

                                                PasienAction.getListComboPasien(query, function (listdata) {
                                                    data = listdata;
                                                });

                                                $.each(data, function (i, item) {
                                                    var labelItem = "";

                                                    if (item.noBpjs != '' && item.noBpjs != null) {
                                                        labelItem = item.noKtp + "-" + item.noBpjs + "-" + item.nama;
                                                    } else {
                                                        labelItem = item.noKtp + "-" + item.nama;
                                                    }
                                                    mapped[labelItem] = {
                                                        id: item.idPasien,
                                                        nama: item.nama
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);

                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                $('#upload_nama_pasien').val(selectedObj.nama);
                                                return selectedObj.id;
                                            }
                                        });
                                    </script>
                                <br/>
                                <div id="body-rm">
                                </div>
                            </s:form>
                    </div>
                    <div class="modal-footer" style="background-color: #cacaca">
                        <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                        </button>
                        <sj:submit type="button" cssClass="btn btn-success" formIds="uploadForm" id="save" name="save"
                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                            <i class="fa fa-arrow-right"></i>
                            Save
                        </sj:submit>
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
<script>
    $('.tablePasien').on('click', '.item-register-finger', function() {
        var idPasien = $(this).attr('data');
        var url=btoa('http://localhost:8080/simrs/registerFinger.action?userId='+idPasien);
        console.log(url);
        var href ='finspot:FingerspotReg;'+url;
        window.location.href =href ;
    });

    function showModalUpload(){
        $("#modal-upload").modal("show");
    }

    var n = 0;
    var i = 1;
    function addInputUpload() {

        var str = "";
        str += '<div class="form-group">'+
                '<label class="col-md-3" style="margin-top: 8px">Upload Foto Rekam Medik '+i+'</label>'+
                '<div class="col-md-7">'+
                '<input type="file" name="fileUploadImage" class="form form-control" style="margin-top: 7px">'+
                <%--'<s:file id="upload-img" name="fileUploadImage" cssClass="form form-control"/>'+--%>
                '</div>'+
                '</div>';
        if (n > 0){
            $("#body-rekam-medic-"+n+"").html(str+'<div id="body-rekam-medic-'+i+'"></div>');
        } else {
            $("#body-rm").html('<div id="body-rekam-medic-'+n+'">'+str+'</div><div id="body-rekam-medic-'+i+'"></div>');
        }
        n++;
        i++;
    }

    function cekData(){
        var idPasien = $('#upload_pasien').val();
        if(idPasien != null){

        }else{
            document.uploadForm.action = false;
            alert("tes");
        }
    }

    /*function user_register(user_id, user_name) {
        regStats = 0;
        regCt = -1;
        try
        {
            timer_register.stop();
        }
        catch(err)
        {
            console.log('Registration timer has been init');
        }
        var limit = 4;
        var ct = 1;
        var timeout = 5000;

        timer_register = $.timer(timeout, function() {
            console.log("'"+user_name+"' registration checking...");
            user_checkregister(user_id,$("#user_finger_"+user_id).html());
            if (ct>=limit || regStats==1)
            {
                timer_register.stop();
                console.log("'"+user_name+"' registration checking end");
                if (ct>=limit && regStats==0)
                {
                    alert("'"+user_name+"' registration fail!");
                }
                if (regStats==1)
                {
                    $("#user_finger_"+user_id).html(regCt);
                    alert("'"+user_name+"' registration success!");
                    load('http://localhost:8080/simrs/');
                }
            }
            ct++;
        });
    }

    function user_checkregister(user_id, current) {
        $.ajax({
            url			:	"user.php?action=checkreg&user_id="+user_id+"&current="+current,
            type		:	"GET",
            success		:	function(data)
            {
                try
                {
                    var res = jQuery.parseJSON(data);
                    if (res.result)
                    {
                        regStats = 1;
                        $.each(res, function(key, value){
                            if (key=='current')
                            {
                                regCt = value;
                            }
                        });
                    }
                }
                catch(err)
                {
                    alert(err.message);
                }
            }
        });
    }*/
</script>

