<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 19/02/2018
  Time: 06.07
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

        function validateForm() {
//            var kodeAlat = document.forms["addForm"]["kodeAlat"].value;
            var namaArea = document.forms["addForm"]["areaName"].value;
            var ket = document.forms["addForm"]["keterangan"].value;
            if (namaArea == ""){
                alert("Nama Area must be filled out");
                return false;
            }  else {
                showSaveDialog();
                return true;
            }
        }

        function showSaveDialog(){
            $('#myModalSave').modal('show');
        }

        function closeModal(){
            document.alatForm.action='search_alat.action';
            document.alatForm.submit();
        }
    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
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
                    <h3 class="box-title">Add Form</h3>
                </div>
                <form role="form" method="post" id="addForm" action="saveAdd_area.action" onsubmit="return validateForm()">
                    <div class="box-body">
                        <div class="form-group">
                            <table align="center">

                                <tr>
                                    <td>
                                        <label>Area Name</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <s:textfield id="areaName" name="area.areaName" cssClass="form-control" cssStyle="margin-top: -30px; margin-left: 20px" />
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

                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="addForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                            <i class="fa fa-check"></i> Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal"> <i class="fa fa-close"></i> Close</button>--%>
                                        <%--<button type="button" class="btn btn-default"  data-dismiss="modal" onclick="closeModal()"><i class="fa fa-close"></i> Close</button>--%>

                                        <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_area.action"/>'">
                                        <i class="fa fa-close"></i> Close
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </form>
                <%--<s:form role="form" id="alatForm" method="post"  namespace="/alat" action="search_alat">--%>

                <%--</s:form>--%>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">

        </div>
    </div>
</section>
<!-- /.content -->


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

