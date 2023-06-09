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

    .form-group {
        margin-bottom: 1px !important;
    }

    .popover {
        z-index: 5000;
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

    .ttd-paint-canvas {
        border: 1px black solid;
        margin: 1rem;
    }

    .span-success{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: #0F9E5E;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-warning{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: darkorange;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-danger{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: #d33724;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-primary{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: #367fa9;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-biru{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: #4d4dff;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-hijau-muda{
        font-size: 13px;
        padding: 5px;
        color: black;
        background-color: #66ff33;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-ungu{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: #cc3399;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-kuning{
        font-size: 13px;
        padding: 5px;
        color: black;
        background-color: #ffff00;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }
    .span-orange{
        font-size: 13px;
        padding: 5px;
        color: white;
        background-color: #f56954;
        border-radius: 5px;
        box-shadow: 1px 3px 8px grey
    }

    .top_margin{
        margin-top: 7px;
    }

</style>
<script>

    var contextPathHeader = '<%= request.getContextPath() %>';

    $(window).load(function() {
        $(".se-pre-con").fadeOut("slow");
    });

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

        $('#myTable').css('width', '100%');
    });

    $(function () {
        $('.select2').select2({});
        $('.datemask').inputmask('yyyy-mm-dd', { 'placeholder': 'yyyy-mm-dd' });
        $('.datemask2').inputmask('dd-mm-yyyy', { 'placeholder': 'dd-mm-yyyy' });
        $('[data-mask]').inputmask();
    });

    function cekSession(){
        var timeout = false;
        UserLoginAction.getTimeOutSession(function (isTimeout) {
            if (isTimeout) {
                timeout = isTimeout;
                $('#modal-session').modal({show:true, backdrop:'static'});
                countDownNumber();
            }
        });
        return timeout;
    }

    function countDownNumber(){
        var jumlah = 10;
        var i = 0;
        var interval = setInterval(function () {
            if (jumlah >= i) {
                $('#hitung_mundur').text(jumlah);
                jumlah--;
            } else {
                clearInterval(interval);
                var contextPath = '<%= request.getContextPath() %>';
                document.location = contextPath + '/loginUser.action';
            }
        }, 500);
    }

    function toLogin(){
        var contextPath = '<%= request.getContextPath() %>';
        document.location = contextPath + '/loginUser.action';
    }

    window.checkDec = function(el){
        var ex = /^[0-9]+\.?[0-9]*$/;
        if(ex.test(el.value)==false){
            el.value = el.value.substring(0,el.value.length - 1);
        }
    }

    function formatRupiahAtas(angka) {
        if(angka != null && angka != '' && angka > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }else{
            return 0;
        }
    }

    function formatRupiahAtas2(angka) {
        var number_string = angka.replace(/[^,\d]/g, '').toString(),
            split = number_string.split(','),
            sisa = split[0].length % 3,
            rupiah = split[0].substr(0, sisa),
            ribuan = split[0].substr(sisa).match(/\d{3}/gi);

        if (ribuan) {
            separator = sisa ? '.' : '';
            rupiah += separator + ribuan.join('.');
        }

        rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
        return rupiah;
    }

    function converterDateTime(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = dd + '-' + mm + '-' + yyyy + ' ' + hh + ':' + min;
        }
        return today;
    }

    function converterDate(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = dd + '-' + mm + '-' + yyyy;
        }
        return today;
    }

    function converterTime(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = hh + ':' + min;
        }
        return today;
    }

    function isCanvasBlank(canvas) {
        const blank = document.createElement("canvas");
        blank.width = canvas.width;
        blank.height = canvas.height;
        return canvas.toDataURL() === blank.toDataURL();
    }

    function cekIcons(val) {
        var fa = val;
        if (val == "Ya") {
            fa = '<i class="fa fa-check"></i>'
        }
        return fa;
    }

    function cekIconsIsNotNull(val) {
        var fa = "";
        if (val == "Ya") {
            fa = '<i class="fa fa-check"></i>'
        }
        return fa;
    }

    function converterDateYmd(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = yyyy + '-' + mm + '-' + dd;
        }
        return today;
    }

    function converterDateYmdHms(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = yyyy + '-' + mm + '-' + dd + ' ' +hh+':'+min+':'+sec;
        }
        return today;
    }

    function diff(start, end) {
        start = start.split(":");
        end = end.split(":");
        var startDate = new Date(0, 0, 0, start[0], start[1], 0);
        var endDate = new Date(0, 0, 0, end[0], end[1], 0);
        var diff = endDate.getTime() - startDate.getTime();
        var hours = Math.floor(diff / 1000 / 60 / 60);
        diff -= hours * 1000 * 60 * 60;
        var minutes = Math.floor(diff / 1000 / 60);
        if (hours < 0){
            hours = hours + 24;
        }
        return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes;
    }

    function convertSentenceCaseUp(myString){
        if(myString != null && myString != ''){
            var rg = /(^\w{1}|\ \s*\w{1})/gi;
            myString = myString.replace(rg, function(toReplace) {
                return toReplace.toUpperCase();
            });
            return myString;
        }else{
            return "";
        }
    }

    function replaceTitik(val){
        var res = 0;
        if(val != ''){
            res = val.replace(/[.]/g, '');
        }
    }

    function replaceStrip(val, id){
        var res = "";
        if(val != ''){
            res = val.replace(/[-]/g, '');
            res.replace(/[_]/g, '');
            $('#'+id).val(res);
        }else{
            $('#'+id).val(res);
        }
    }

    function replaceUnderLine(val){
        var res = '';
        if(val != ''){
            res = val.replace(/[_]/g, '');
        }
        return res;
    }

    function convertRpAtas(id, val, idHidden) {
        $('#'+id).val(formatRupiahAtas2(val));
        if(idHidden != '' && idHidden != null){
            val = val.replace(/[.]/g, '');
            var numbers = /^[0-9]+$/;
            if(val != ''){
                if(val.match(numbers)) {
                    $('#' + idHidden).val(val);
                }
            }else{
                $('#' + idHidden).val('');
            }
        }
    }

    function cekNumber(id, val){
        var numbers = /^[0-9]+$/;
        if(val.match(numbers)) {
            $('#' + id).val(val);
        }else{
            $('#' + id).val('');
        }
    }

    function converterRupiah(value){
        var res = "";
        if(value != null && value != ''){
            res = formatRupiahAtas(value);
        }
        document.write(res);
    }

    function cekScrol(id, idTujuan) {
        var cek = $('#'+id).hasClass("fa fa-unlock");
        if(cek){
            $('#'+id).removeClass("fa fa-unlock");
            $('#'+id).addClass("fa fa-lock");
            $('#' + idTujuan).attr('style', 'height: 70%; overflow-y: scroll;');
        }else {
            $('#'+id).removeClass("fa fa-lock");
            $('#'+id).addClass("fa fa-unlock");
            $('#' + idTujuan).removeAttr('style');
        }
    }

    function setCanvasAtas(id) {
        var canvas = document.getElementById(id);
        var ctx = canvas.getContext('2d');
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.onload = function () {
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(img, 0, 0);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }

    function setCanvasAtasWithText(id, tujuan) {
        var canvas = document.getElementById(id);
        var ctx = canvas.getContext('2d');
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.onload = function () {
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(img, 0, 0);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
        $('#'+tujuan).val(event.target.files[0].name);
    }

    function setProvAtas(id, idHidden){
        var functions, mapped;
        $('#'+id).typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};
                var data = [];
                dwr.engine.setAsync(false);
                ProvinsiAction.initComboProvinsi(query, function (listdata) {
                    data = listdata;
                });

                $.each(data, function (i, item) {
                    var labelItem = item.provinsiName;
                    mapped[labelItem] = {id: item.provinsiId, label: labelItem};
                    functions.push(labelItem);
                });
                process(functions);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                var namaAlat = selectedObj.label;
                document.getElementById(idHidden).value = selectedObj.id;
                return namaAlat;
            }
        });
    }

    function setKabAtas(id, idHidden, idProv){
        var prov = $('#'+idProv).val();
        var functions, mapped;
        $('#'+id).typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};
                var data = [];
                dwr.engine.setAsync(false);
                ProvinsiAction.initComboKota(query, prov, function (listdata) {
                    data = listdata;
                });
                $.each(data, function (i, item) {
                    var labelItem = item.kotaName;
                    mapped[labelItem] = {id: item.kotaId, label: labelItem};
                    functions.push(labelItem);
                });
                process(functions);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                var namaAlat = selectedObj.label;
                document.getElementById(idHidden).value = selectedObj.id;
                return namaAlat;
            }
        });
    }

    function setKecAtas(id, idHidden, idKab){
        var functions, mapped;
        var kab = $('#'+idKab).val();
        $('#'+id).typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};
                var data = [];
                dwr.engine.setAsync(false);
                ProvinsiAction.initComboKecamatan(query, kab, function (listdata) {
                    data = listdata;
                });
                $.each(data, function (i, item) {
                    var labelItem = item.kecamatanName;
                    mapped[labelItem] = {id: item.kecamatanId, label: labelItem};
                    functions.push(labelItem);
                });
                process(functions);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                var namaAlat = selectedObj.label;
                document.getElementById(idHidden).value = selectedObj.id;
                return namaAlat;
            }
        });
    }

    function setDesAtas(id, idHidden, idKec){
        var kec = $('#'+idKec).val();
        var functions, mapped;
        $('#'+id).typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};
                var data = [];
                dwr.engine.setAsync(false);
                ProvinsiAction.initComboDesa(query, kec, function (listdata) {
                    data = listdata;
                });
                $.each(data, function (i, item) {
                    var labelItem = item.desaName;
                    mapped[labelItem] = {id: item.desaId, label: labelItem};
                    functions.push(labelItem);
                });
                process(functions);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                var namaAlat = selectedObj.label;
                document.getElementById(idHidden).value = selectedObj.id;
                return namaAlat;
            }
        });
    }

    function setKotaKab(id){
        $('#'+id).typeahead({
            minLength: 3,
            source: function (query, process) {
                functions = [];
                mapped = {};
                var data = [];
                dwr.engine.setAsync(false);
                ProvinsiAction.initComboKota(query, "", function (listdata) {
                    data = listdata;
                });
                $.each(data, function (i, item) {
                    var labelItem = item.kotaName;
                    mapped[labelItem] = {
                        id: item.kotaId,
                        label: labelItem
                    };
                    functions.push(labelItem);
                });
                process(functions);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                var remove = selectedObj.label.substring(5);
                var namaKota = remove;
                return namaKota;
            }
        });
    }

    function cekDatePicker(val){
        var tgl = val.split("-");
        var cek = false;
        $.each(tgl, function (i, item) {
            var numbers = /^[0-9]+$/;
            if(!item.match(numbers)){
                cek = true;
            }
        });
        return cek;
    }

    function changeJenisPasien(jenis, value){
        var res = "";
        if(jenis == 'umum'){
            res = '<span class="span-biru">'+value+'</span>';
        }else if (jenis == 'bpjs'){
            res = '<span class="span-success">'+value+'</span>';
        }else if(jenis == 'rekanan'){
            res = '<span class="span-hijau-muda">'+value+'</span>';
        }else if(jenis == 'asuransi'){
            res = '<span class="span-kuning">'+value+'</span>';
        }else if(jenis == 'paket_perusahaan'){
            res = '<span class="span-ungu">'+value+'</span>';
        }else if(jenis == 'paket_individu'){
            res = '<span class="span-orange">'+value+'</span>';
        }
        return res;
    }

    function convertToDataURLAtas(id){
        var ttd = "";
        if(id != ''){
            ttd = id.toDataURL("image/png"),
                ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");
        }
        return ttd;
    }

    function cekImages(url){
        var http = new XMLHttpRequest();
        http.open('HEAD', url, false);
        http.send();
        return http.status!=404;
    }

    function imagesDefault(url){
        var res = contextPathHeader+'/pages/images/no-images.png';
        if(url != null && url != ''){
            if(cekImages(url)){
                res = url;
            }
        }
        var set = '<div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"\n' +
            'class="card card-4 pull-right">\n' +
            '<img border="2" id="img_ktp" src="'+res+'"\n' +
            'style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">\n' +
            '</div>';
        return set;
    }

    function inputWarning(war, suc){
        var warn =$('#'+war).is(':visible');
        if (warn){
            $('#'+suc).show().fadeOut(3000);
            $('#'+war).hide()
        }
    }

</script>


