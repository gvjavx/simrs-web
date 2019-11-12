<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<TITLE>e-HEALTH</TITLE>
<link rel="shortcut icon" href="<s:url value="/pages/images/logo-nmu.webp"/>"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<s:url value="/pages/bootstraplte/css/bootstrap.css"/>" rel="stylesheet" media="screen">

<%--<!--<link type="text/css" href="<s:url value="/pages/mozilla/style.css"/>" rel="stylesheet"/> -->--%>
<%--<link type="text/css" href="<s:url value="/pages/css/initial-form.css"/>" rel="stylesheet"/>--%>
<%--<link type="text/css" href="<s:url value="/pages/css/style_2.css"/>" rel="stylesheet"/>--%>
<link type="text/css" href="<s:url value="/pages/bootstraplte/css/bootstrap.min.css"/>" rel="stylesheet"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<%--<link type="text/css" href="<s:url value="/pages/bootstraplte/css/font-awesome.min.css"/>" rel="stylesheet"/>--%>
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<%--<link type="text/css" href="<s:url value="/pages/bootstraplte/css/ionicons.min.css"/>" rel="stylesheet"/>--%>
<!-- Theme style -->
<link rel="stylesheet" href="<s:url value="/pages/dist/css/AdminLTE.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/dist/css/timepicker.css"/>">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
page. However, you can choose any other skin. Make sure you
apply the skin class to the body tag so the changes take effect.
-->
<%--<link rel="stylesheet" href="/pages/dist/css/skins/skin-blue.min.css">--%>
<link rel="stylesheet" href="<s:url value="/pages/dist/css/skins/skin-blue.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/dist/css/dataTables.bootstrap.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/plugins/select2/select2.css"/>">
<%--<link rel="stylesheet" href="<s:url value="/pages/css/style-form.css"/>">--%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->


<%--<!--<script type='text/javascript' src='<s:url value="/pages/mozilla/dmenu.js"/>'></script>-->--%>

<script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/util.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/UserLoginAction.js"/>'></script>
<%--<sj:head jqueryui="true" jquerytheme="redmond"/>--%>
<%--<sj:head jqueryui="true" jquerytheme="custom" customBasepath="/pages/custom/"/>--%>
<sj:head jqueryui="true" jquerytheme="redmond"/>
<sb:head />
<script type='text/javascript' src='<s:url value="/pages/bootstraplte/js/bootstrap.js"/>'></script>
<%--<script type='text/javascript' src='<s:url value="/pages/css/html5shiv.min.js"/>'></script>--%>
<%--<script type='text/javascript' src='<s:url value="/pages/css/respond.min.js"/>'></script>--%>
<script type='text/javascript' src='<s:url value="/pages/plugins/datepicker/bootstrap-datepicker.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/datatables/jquery.dataTables.min.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/datatables/dataTables.bootstrap.min.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/slimScroll/jquery.slimscroll.min.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/chartjs/Chart.min.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/select2/select2.full.min.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/chartjs/chartjs-plugin-labels.min.js"/>'></script>

<!-- jQuery 2.2.3 -->
<%--<script src="<s:url value="/pages/plugins/jQuery/jquery-2.2.3.min.js"/>"></script>--%>
<!-- Bootstrap 3.3.6 -->
<script src="<s:url value="/pages/bootstraplte/js/bootstrap.min.js"/>"></script>
<!-- AdminLTE App -->
<script src="<s:url value="/pages/dist/js/app.min.js"/>"></script>
<script src="<s:url value="/pages/dist/js/timepicker.js"/>"></script>
<%--TypeAhead--%>
<script src="<s:url value="/pages/plugins/typeahead/bootstrap3-typeahead.js"/>"></script>
<%--<script src="<s:url value="/pages/js/jquery-ui.js"/>"></script>--%>
<link rel="<s:url value="/pages/plugins/datepicker/datepicker3.css"/>">
<%--<link rel="stylesheet" href="../../plugins/datepicker/datepicker3.css">--%>
<style>
    .ui-datepicker-title{
        color: black !important;    
    }
    
    .ui-widget-header {
        border: 1px solid #367fa9 !important;
        background: #367fa9 ;
        color: #ffffff;
        font-weight: bold;
    }

    label {
        font-weight: normal !important;
    }
    .ui-dialog{
        top: 113px !important;
        /*left: 424px !important;*/
        z-index: 1030;
    }
    .ui-datepicker{
        z-index: 1100 !important;
    }
    .ui-dialog-titlebar{
        background-color: #367fa9;
    }

    .ui-widget-header {
        border : 1px solid #367fa9;
        background: #367fa9
    }

    .form-group {
        margin-bottom: 1px !important;
    }

    .popover {
        z-index: 5000;
    }

    hr {
        -moz-border-bottom-colors: none;
        -moz-border-image: none;
        -moz-border-left-colors: none;
        -moz-border-right-colors: none;
        -moz-border-top-colors: none;
        border-color: #EEEEEE -moz-use-text-color #FFFFFF;
        border-style: solid none;
        border-width: 1px 0;
        margin: 18px 0;
    }

</style>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
<script>
    $( document ).ready(function() {
        console.log( "ready!" );
        $('#popoverData').popover();
        //$('#popoverData').tooltip({container: 'body'});
        $('#myTable').DataTable();
    });
    $(function () {
        $('.select2').select2();
    });

    $('#datepicker').datepicker({
        autoclose: true
    })
</script>


