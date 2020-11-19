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
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_mesinAbsensiDetail'/>";
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
            Mesin Absensi Detail
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="mesinAbsensiDetailForm" method="post"  theme="simple" namespace="/mesinAbsensiDetail" action="search_mesinAbsensiDetail.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Mesin Abs. Detail Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="mesinAbsensiDetailId" name="mesinAbsensiDetail.mesinAbsensiDetailId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Pin :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="pin" name="mesinAbsensiDetail.pin" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Status :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="status" name="mesinAbsensiDetail.status" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Scan Date :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="loginTimestampFrom" name="mesinAbsensiDetail.stTanggalDari" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="loginTimestampTo" name="mesinAbsensiDetail.stTanggalSelesai" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Verify Mode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="verifyMode" name="mesinAbsensiDetail.verifyMode" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Work Kode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="workCode" name="mesinAbsensiDetail.workCode" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="mesinAbsensiDetailForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                <i class="fa fa-plus"></i>
                                                Sync Mesin Absensi
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li id="btnNewSync">
                                                    <s:a href="#"><i class="fa fa-refresh"></i> Sync New Data</s:a>
                                                </li>
                                                <li id="btnAllSync">
                                                    <s:a href="#"><i class="fa fa-refresh"></i> Sync All Data</s:a>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_mesinAbsensiDetail"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="60%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Mesin Abs. Detail">
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

                                        <s:set name="listOfMesinAbsensiDetail" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfMesinAbsensiDetail" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_mesinAbsensiDetail.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column property="mesinAbsensiDetailId" sortable="true" title="ID" />
                                            <display:column property="pin" sortable="true" title="Pin"  />
                                            <display:column property="status" sortable="true" title="Status"  />
                                            <display:column property="scanDate" sortable="true" title="Scan Date"  />
                                            <display:column property="verifyMode" sortable="true" title="Verify Mode"  />
                                            <display:column property="workCode" sortable="true" title="Work Kode"  />
                                            <display:column property="flag" sortable="true" title="Flag" />
                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                            <display:setProperty name="paging.banner.item_name">MesinAbsensiDetail</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">MesinAbsensiDetail</display:setProperty>
                                            <display:setProperty name="export.excel.filename">MesinAbsensiDetail.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">MesinAbsensiDetail.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">MesinAbsensiDetail.pdf</display:setProperty>
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
    $(document).ready(function(){
        $('#btnNewSync').click(function(){
            if(confirm("apakah anda ingin melakukan sinkronisasi data baru dengan mesin absensi ?")){
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
                    },
                    errorHandler:function(message) {
                        alert("Mesin tidak terkoneksi dengan server , harap mengecek koneksi antara mesin absensi dan server");
                        $("#onLoading").modal("hide");
                    }
                });
            }
        });
        $('#btnAllSync').click(function(){
            if(confirm("apakah anda ingin melakukan sinkronisasi semua data dengan mesin absensi ?")){
                $("#onLoading").modal({
                    backdrop: "static", //remove ability to close modal with click
                    keyboard: false, //remove option to close with keyboard
                    show: true //Display loader!
                });
                dwr.engine.setAsync(true);
                AbsensiAction.getAllDataFromMesin({
                    callback : function(response){
                        $("#onLoading").modal("hide");
                        $("#success_tic").modal("show");
                    },
                    errorHandler:function(message) {
                        alert("Mesin tidak terkoneksi dengan server , harap mengecek koneksi antara mesin absensi dan server");
                        $("#onLoading").modal("hide");
                    }
                });
            }
        });
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    })
</script>



