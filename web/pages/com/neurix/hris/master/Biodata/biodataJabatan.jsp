<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        }
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="" action="" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">View Jabatan</legend>


                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <center>
                    <table id="showdata" width="100%">
                        <tr>
                            <td align="center">
                                <s:set name="listOfJabatan" value="#session.listOfResultJabatan" scope="request" />
                                <display:table name="listOfJabatan" class="tableJabatan table table-condensed table-striped table-hover"
                                               requestURI="paging_displaytag_jabatan.action" export="false" id="row" pagesize="14" style="font-size:10">
                                    <display:column property="personilPositionId" sortable="true" title="ID" />
                                    <display:column property="positionName" sortable="true" title="Nama Jabatan"/>
                                    <display:column property="divisiName" sortable="true" title="Nama Divisi"/>
                                    <display:column property="branchName" sortable="true" title="Nama Unit"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>
                </center>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-close"/> Close
                        </button>
                    </div>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
