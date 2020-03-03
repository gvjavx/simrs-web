<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<TITLE>GO-HEALTH NMU</TITLE>
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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<%--<link type="text/css" href="<s:url value="/pages/bootstraplte/css/font-awesome.min.css"/>" rel="stylesheet"/>--%>
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<%--<link type="text/css" href="<s:url value="/pages/bootstraplte/css/ionicons.min.css"/>" rel="stylesheet"/>--%>
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
<%--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/css/select2.min.css" rel="stylesheet" />--%>

<%--<link rel="stylesheet" href="<s:url value="/pages/css/style-form.css"/>">--%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>-->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/js/select2.min.js"></script>-->

<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
<%--<link rel="stylesheet" href="../../plugins/datepicker/datepicker3.css">--%>
<%--<script src="<s:url value="/pages/dist/js/adminlte.min.js"/>"></script>--%>

<script src="<s:url value="/pages/plugins/input-mask/jquery.inputmask.js"/>"></script>
<script src="<s:url value="/pages/plugins/input-mask/jquery.inputmask.date.extensions.js"/>"></script>
<script src="<s:url value="/pages/plugins/input-mask/jquery.inputmask.extensions.js"/>"></script>
<script src="<s:url value="/pages/bootstraplte/js/jquery.tabletojson.js"/>"></script>
<script src="<s:url value="/pages/plugins/pace/pace.min.js"/>"></script>
<script src="<s:url value="/pages/plugins/iCheck/icheck.min.js"/>"></script>

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

    .card {
        background: #fff;
        border-radius: 2px;
        display: inline-block;
        height: 100px;
        margin: 1rem;
        position: relative;
        width: 200px;
    }

    .card-4 {
        box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    }
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    html, body {
        height: 100%;
    }

    .img-list {
        text-align: center;
    }
    .img-list li {
        width: 250px;
        display: inline-block;
        list-style-type: none;
    }
    .img-list li img {
        width: 100%;
    }

    .mask {
        display: none;
        position: fixed;
        top: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.8);
    }
    .mask .img-box {
        width: 100%;
        max-width: 650px;
        padding: 10px;
        background: #fff;
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    .mask .img-box img {
        width: 100%;
    }
    .mask .img-box .close {
        color: #000;
        background: rgba(255, 255, 255, 0.8);
        width: 30px;
        height: 30px;
        text-align: center;
        line-height: 30px;
        position: absolute;
        top: -35px;
        right: -35px;
        font-size: 24px;
        font-weight: bold;
        border-radius: 50%;
        cursor: pointer;
    }
    .mask .img-box .close:hover {
        background: white;
    }

    .is-visible {
        display: block !important;
    }

    .fadein {
        animation: fadein 400ms ease-in-out;
    }

    @keyframes fadein {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }
    .fadeout {
        animation: fadeout 400ms ease-in-out;
    }

    @keyframes fadeout {
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }

    .spin {
        -webkit-animation: rotation 1s infinite linear;
    }

    @-webkit-keyframes rotation {
        from {
            -webkit-transform: rotate(0deg);
        }
        to {
            -webkit-transform: rotate(359deg);
        }
    }

    <%--.se-pre-con {--%>
        <%--position: fixed;--%>
        <%--left: 0px;--%>
        <%--top: 0px;--%>
        <%--width: 100%;--%>
        <%--height: 100%;--%>
        <%--z-index: 9999;--%>
        <%--background: url("<s:url value="/pages/images/logo-nmu-copy.png"/>") center no-repeat #fff;--%>
        <%--background-size: 100px 100px;--%>
    <%--}--%>
    /*.pulse-button {*/

        /*position: relative;*/
        /*width: 100px;*/
        /*height: 100px;*/
        /*border: none;*/
        /*box-shadow: 0 0 0 0 rgba(232, 76, 61, 0.7);*/
        /*border-radius: 50%;*/
        /*background-color: #e84c3d;*/
        /*background-image: url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/173024/jonathanlarradet_copy.png");*/
        /*background-size:cover;*/
        /*background-repeat: no-repeat;*/
        /*cursor: pointer;*/
        /*-webkit-animation: pulse 1.25s infinite cubic-bezier(0.66, 0, 0, 1);*/
        /*-moz-animation: pulse 1.25s infinite cubic-bezier(0.66, 0, 0, 1);*/
        /*-ms-animation: pulse 1.25s infinite cubic-bezier(0.66, 0, 0, 1);*/
        /*animation: pulse 1.25s infinite cubic-bezier(0.66, 0, 0, 1);*/
    /*}*/
    /*.pulse-button:hover*/
    /*{*/
        /*-webkit-animation: none;-moz-animation: none;-ms-animation: none;animation: none;*/
    /*}*/

    /*@-webkit-keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}*/
    /*@-moz-keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}*/
    /*@-ms-keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}*/
    /*@keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}*/

    @-webkit-keyframes sploosh {
        0% {
            box-shadow: 0 0 0 0px rgba(71, 225, 141, .7);
            background: rgba(71, 225, 141, .7);
        }
        80% {
            background: rgba(66, 166, 223, 0);
        }
        100% {
            box-shadow: 0 0 0 120px rgba(66, 166, 223, 0);
        }
    }

    @-webkit-keyframes pulse {
        0% {
            -webkit-transform: scale(1);
        }
        3.3% {
            -webkit-transform: scale(1.1);
        }
        16.5% {
            -webkit-transform: scale(1);
        }
        33% {
            -webkit-transform: scale(1.1);
        }
        100% {
            -webkit-transform: scale(1);
        }
    }

    .relative {
        position: fixed;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
        z-index: 9999;
    }

    .pull {
        position: absolute;
        top: 0;
        left: 0;
        border: 0;

        width: 30px;
        height: 30px;
        border-radius: 50%;

        -webkit-animation: sploosh 2s cubic-bezier(0.165, 0.84, 0.44, 1);
        -webkit-animation-iteration-count: infinite;
    }

    .pull:nth-child(2) {
        -webkit-animation-delay: .33s;
        -webkit-animation-duration: 2.2s;
    }
    .modal { overflow-y: auto}

</style>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
<script>

    $(window).load(function() {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $( document ).ready(function() {
//        $('#popoverData').popover();
        //$('#popoverData').tooltip({container: 'body'});
        $('#myTable').DataTable();

        $('#sortTable').DataTable({
            "order": [[ 0, "desc" ]]
        });

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

//        cekSession();

    });

    <%--function cekSession(){--%>
        <%--var persons = '<%= session.getAttribute("user_name") %>';--%>
        <%--setInterval(function (){--%>
<%--//            if(null == persons){--%>
<%--//                logout();--%>
<%--//            }--%>
        <%--},1000);--%>
    <%--}--%>

    $(function () {
        $('.select2').select2({});
        //Datemask dd/mm/yyyy
        $('.datemask').inputmask('yyyy-mm-dd', { 'placeholder': 'yyyy-mm-dd' })
        $('.datemask2').inputmask('dd-mm-yyyy', { 'placeholder': 'dd-mm-yyyy' })
        //Money Euro
        $('[data-mask]').inputmask()

    });

    function logout(){
        var host = window.location.hostname;
        window.location.href = host+"/simrs/j_spring_security_logout";
    }
    window.checkDec = function(el){
        var ex = /^[0-9]+\.?[0-9]*$/;
        if(ex.test(el.value)==false){
            el.value = el.value.substring(0,el.value.length - 1);
        }
    }
</script>


