<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:url value="/" var="appname" />


<TITLE>GO-MEDSYS NMU</TITLE>
<link rel="shortcut icon" href="<s:url value="/pages/images/logo-nmu.webp"/>"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<s:url value="/pages/bootstraplte/css/bootstrap.css"/>" rel="stylesheet" media="screen">
<link href="<s:url value="/pages/bootstrap/css/hover.css"/>" rel="stylesheet">

<%--<!--<link type="text/css" href="<s:url value="/pages/mozilla/style.css"/>" rel="stylesheet"/> -->--%>
<%--<link type="text/css" href="<s:url value="/pages/css/initial-form.css"/>" rel="stylesheet"/>--%>
<%--<link type="text/css" href="<s:url value="/pages/css/style_2.css"/>" rel="stylesheet"/>--%>
<link type="text/css" href="<s:url value="/pages/bootstraplte/css/bootstrap.min.css"/>" rel="stylesheet"/>
<link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/select2.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/plugins/morris/morris.css"/>">

<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
<link rel="stylesheet"  href="<s:url value="/pages/bootstrap/css/font-awesome.min.css"/>"/>
<!-- Ionicons -->
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">--%>
<link rel="stylesheet" href="<s:url value="/pages/bootstrap/css/ionicons.min.css"/>"/>
<!-- Theme style -->
<link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/AdminLTE.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/dist/css/timepicker.css"/>">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
page. However, you can choose any other skin. Make sure you
apply the skin class to the body tag so the changes take effect.
-->
<%--<link rel="stylesheet" href="/pages/dist/css/skins/skin-blue.min.css">--%>
<link rel="stylesheet" href="<s:url value="/pages/dist/css/skins/skin-blue.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/dist/css/dataTables.bootstrap.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/plugins/pace/pace.min.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/plugins/iCheck/all.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/modal-style.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/info_box.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/w3switch.css"/>">
<link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/header.css"/>">
<%--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/css/select2.min.css" rel="stylesheet" />--%>

<%--<link rel="stylesheet" href="<s:url value="/pages/css/style-form.css"/>">--%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>-->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/js/select2.min.js"></script>-->

<script src="<s:url value="/pages/bootstraplte/js/html5shiv.min.js"/>"></script>
<script src="<s:url value="/pages/bootstraplte/js/respond.min.js"/>"></script>
<%--<![endif]-->--%>


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
<script type='text/javascript' src='<s:url value="/pages/plugins/select2/select2.full.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/chartjs/chartjs-plugin-labels.min.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/flot/jquery.flot.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/flot/jquery.flot.resize.js"/>'></script>
<%--<script type='text/javascript' src='<s:url value="/pages/plugins/flot/jquery.flot.canvas.js"/>'></script>--%>


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
<link rel="<s:url value="/pages/bootstraplte/css/toast.css"/>">
<%--<link rel="stylesheet" href="../../plugins/datepicker/datepicker3.css">--%>
<%--<script src="<s:url value="/pages/dist/js/adminlte.min.js"/>"></script>--%>

<script src="<s:url value="/pages/plugins/input-mask/jquery.inputmask.js"/>"></script>
<script src="<s:url value="/pages/plugins/input-mask/jquery.inputmask.date.extensions.js"/>"></script>
<script src="<s:url value="/pages/plugins/input-mask/jquery.inputmask.extensions.js"/>"></script>
<script src="<s:url value="/pages/bootstraplte/js/jquery.tabletojson.js"/>"></script>
<script src="<s:url value="/pages/plugins/pace/pace.min.js"/>"></script>
<script src="<s:url value="/pages/plugins/iCheck/icheck.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/bootstraplte/js/jquery-ui.js"/>"></script>
<script src="<s:url value="/pages/plugins/morris/morris.min.js"/>"></script>
<script src="<s:url value="/pages/plugins/morris/raphael.min.js"/>"></script>
<script src="<s:url value="/pages/dist/js/spinner.js"/>"></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/toast.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/plugins/ckeditor/ckeditor.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/header.js"/>'></script>
<%--<script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>--%>
<%--<script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>--%>

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
        z-index: 1030;
        position: fixed;
    }
    .ui-datepicker{
        z-index: 1100 !important;
    }

    .ui-tooltip{
        display: none !important;
    }

    .ui-button .ui-corner-all .ui-widget .ui-button-icon-only .ui-dialog .ui-dialog-titlebar-close .ui-icon-closethick{
        display: none !important;
    }

    .ui-dialog-titlebar{
        background-color: #367fa9;
    }

    .ui-widget-header {
        border : 1px solid #367fa9;
        background: #367fa9
    }

    .ui-widget-overlay {
        background: black !important;
        opacity: .5 !important;
    }

    .ui-dialog-titlebar-close{
        display: none;
    }
    .modal { overflow-y: auto}
</style>

<script>
    var contextPathHeader = '<%= request.getContextPath() %>';
    $( document ).ready(function() {

        $('#myTableAllRows').DataTable({
            paging: false,
            "order": [[ 0, "desc" ]]
        });

        $('#myTable').DataTable();

        $('#sortTable').DataTable({
            "order": [[ 0, "desc" ]]
        });

        $('#myTable').css('width', '100%');
        $('#sortTable').css('width', '100%');

        $("#tanggal_lahir").datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear:true,
            dateFormat:'yy-mm-dd'
        });

        $(".datepicker").datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear:true,
            dateFormat:'yy-mm-dd'
        });

        $(".datepicker2").datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear:true,
            dateFormat:'dd-mm-yy'
        });

        $("#tgl_from, #tgl_to").datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear:true,
            dateFormat:'dd-mm-yy'
        });

        $('.dropdown').on('show.bs.dropdown', function(e){
            $(this).find('.dropdown-menu').first().stop(true, true).slideDown(350);
        });

        $('.dropdown').on('hide.bs.dropdown', function(e){
            $(this).find('.dropdown-menu').first().stop(true, true).slideUp(350);
        });

        $(':input').on('focus', function () {
            $(this).attr('autocomplete', 'off');
        });

        $('.select2').css('width', '100%');

        $(".tgl_lahir_validasi").datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear:true,
            dateFormat:'yy-mm-dd',
            maxDate: new Date()
        });

        $(".tgl_maju").datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear:true,
            dateFormat:'dd-mm-yy',
            minDate: new Date()
        });
    });

    $(function () {
        $('.select2').select2({});
        $('.datemask').inputmask('yyyy-mm-dd', { 'placeholder': 'yyyy-mm-dd' });
        $('.datemask2').inputmask('dd-mm-yyyy', { 'placeholder': 'dd-mm-yyyy' });
        $('[data-mask]').inputmask();

        $('.editors').each(function(){
            CKEDITOR.replace( $(this).attr('id') );
        });
    });

</script>