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
                                                        <display:column property="flag" sortable="true" title="flag" />

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
                                                        <display:column media="html" title="Finger Print">
                                                            <a href="javascript:;" data="<s:property value="%{#attr.row.idPasien}"/>" class="item-register-finger">
                                                                <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon-register-finger">
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
        console.log(href);
        window.location.href =href ;
    });
</script>

