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
            window.location.href="<s:url action='initForm_golongan'/>";
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
            Level / Golongan
            <small>e-HEALTH</small>
        </h1>
        <%--<ol class="breadcrumb">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
        <%--<li class="active">Here</li>--%>
        <%--</ol>--%>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="golonganForm" method="post"  theme="simple" namespace="/golongan" action="search_golongan.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Golongan Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="golonganId" name="golongan.golonganId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Golongan Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="golonganName" name="golongan.golonganName" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Grade Level :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield type="number" min="0"  id="gradeLevel" name="golongan.stLevel" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="golongan.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="golonganForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/golongan" action="add_golongan" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Golongan
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_golongan"/>'">
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
                                                   height="500" width="900" autoOpen="false"
                                                   title="Golongan ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfGolongan" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfGolongan" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_golongan.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/golongan" action="edit_golongan" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.golonganId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/golongan" action="delete_golongan" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.golonganId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <%--<display:column property="golonganId" sortable="true" title="Golongan ID" />--%>
                                            <display:column property="golonganName" sortable="true" title="Nama"  />
                                            <display:column property="stLevel" sortable="true" title="Grade Level"  />

                                            <%--<display:column property="flag" sortable="true" title="Flag" />
                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>--%>

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
                <%--<div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>



                    <form role="form" method="post" id="alatForm" action="search_alat.action">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Kode Alat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="kodeAlatSearch" name="alat.kodeAlat" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
                                            <script type='text/javascript'>
                                                var functions, mapped;
                                                $('#kodeAlatSearch').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);
                                                        AlatAction.initComboAlat(query, function (listdata) {
                                                            data = listdata;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = item.namaAlat;
                                                            mapped[labelItem] = { id: item.kodeAlat, label: labelItem };
                                                            functions.push(labelItem);
                                                        });

                                                        process(functions);
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaAlat = selectedObj.label;
                                                        document.getElementById("namaAlat").value = namaAlat;

                                                        return selectedObj.id;
                                                    }
                                                });
                                                //
                                                //
                                            </script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama Alat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="namaAlat" name="alat.namaAlat" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Flag </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:select list="#{'N':'Non-Active'}" id="flag" name="alat.flag"
                                                      headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px"/>
                                        </td>
                                    </tr>
                                </table>
                                <br>

                            </div>
                            <div class="box-footer">

                            </div>


                            <center>
                                <table id="showdata" width="40%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Alat">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfAlat" value="#session.listOfResult" scope="request" />
                                            <display:table name="listOfAlat" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_alat.action" export="true" id="row" pagesize="10" style="font-size:10">
                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/alat" action="edit_alat" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.kodeAlat"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/alat" action="delete_alat" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.kodeAlat" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>

                                                </display:column>
                                                <display:column property="kodeAlat" sortable="true" title="Kode Alat" />
                                                <display:column property="namaAlat" sortable="true" title="Nama Alat"  />
                                                <display:column property="flag" sortable="true" title="Flag" />
                                                &lt;%&ndash;<display:column property="action" sortable="true" title="CreatedWho"/>&ndash;%&gt;

                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>

                    </form>

                </div>--%>
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

