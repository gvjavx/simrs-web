<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 19/02/2018
  Time: 06.35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script>


        function showSaveDialog(){
            $('#myModalSave').modal('show');
        }
    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%--<%@ include file="/pages/common/headerNav.jsp" %>--%>

<%--<ivelincloud:mainMenu/>--%>
<%--<div class="content-wrapper">--%>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Alat
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
                        <h3 class="box-title">Delete Form</h3>
                    </div>
                    <form role="form" method="post" id="deleteForm" action="saveDelete_alat.action">
                        <div class="box-body">
                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Kode Alat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield name="alat.kodeAlat" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" disabled="true" />
                                            <s:hidden id="kodeAlat" name="alat.kodeAlat"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Nama Alat </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield name="alat.namaAlat" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" disabled="true" />
                                            <s:hidden id="namaAlat" name="alat.namaAlat"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Keterangan </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield name="alat.keterangan" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" disabled="true" />
                                            <s:hidden id="keterangan" name="alat.keterangan" />
                                            <s:hidden id="flag" name="alat.flag" />
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <!-- Modal -->
                            <div class="modal fade" id="myModalSave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                        </div>
                                        <div class="modal-body">
                                            <center>
                                                <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>
                                                <span> Saving...</span>
                                            </center>
                                        </div>
                                        <%--<div class="modal-footer">--%>
                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                                        <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                                        <%--</div>--%>
                                    </div>
                                </div>
                            </div>

                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="deleteForm" id="search" name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showSaveDialog()">
                                                <i class="fa fa-check"></i> Delete
                                            </sj:submit>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_alat"/>'">
                                                <i class="fa fa-close"></i> Close
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
<%--</div>--%>
<!-- /.content-wrapper -->

<%--<%@ include file="/pages/common/footer.jsp" %>--%>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>


