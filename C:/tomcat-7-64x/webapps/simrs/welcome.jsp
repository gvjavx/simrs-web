<%--<%@ include file="/pages/common/taglib.jsp" %>--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Welcome</title>

    <link rel="shortcut icon" href="<s:url value="/pages/images/cloud.ico"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">

    <link type="text/css" href="<s:url value="/pages/mozilla/style.css"/>" rel="stylesheet"/>
    <link type="text/css" href="<s:url value="/pages/css/initial-form.css"/>" rel="stylesheet"/>

    <script type='text/javascript' src='<s:url value="/pages/mozilla/dmenu.js"/>'></script>

    <script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/util.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/FunctionAction.js"/>'></script>
    <sj:head jqueryui="true" jquerytheme="redmond"/>

    <script type='text/javascript' src="http://code.jquery.com/jquery.js"></script>
    <script type='text/javascript' src="<s:url value="/pages/script/jquery-1.7.2.min.js"/>"></script>
    <script type='text/javascript' src='<s:url value="/pages/bootstrap/js/bootstrap.js"/>'></script>



    <script type='text/javascript'>

        //        function test() {
        ////            alert('tes');
        ////            $(function () {
        //  $("#userInfo").popover({
        //                    html:true
        //                });
        //        }
//        $(document).ready(function() {

//        $.subscribe('onclik', function(event, data) {
//            $('#result').html('Textfield '+data.id+' value is '+data.value);

//        });

//            $("#userInfo").popover({
//                html:true
//            });
<%----%>
//        });

//        $(document).ready(function() {
//
//        $.subscribe('openpopover', function(event, data) {
//            $("#example").popover({
//                html:true, content:"Its so simple to create a tooltop for my website!", title:"Twitter Bootstrap Popover",placement:"bottom"
//            });
//        });
//
//        });
    </script>
    <style>
        .errorblock {
            color: #ff0000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>

</head>
<body>
<h3>Login with Username and Password (Authentication with Database) </h3>
SUCCESS !!!


<a href="<s:url value='j_spring_security_logout'/>">Logout</a>

<%--<sj:dialog--%>
<%--id="myremotelinkdialog"--%>
<%--autoOpen="false"--%>
<%--modal="true"--%>
<%--title="Dialog with different Content"--%>
<%--openTopics="openRemoteDialog"--%>
<%--/>--%>

<%--<div class="well">--%>
    <%--&lt;%&ndash;<a href="#" id="example" class="btn btn-danger" rel="popover"&ndash;%&gt;--%>
       <%--&lt;%&ndash;data-content="It's so simple to create a tooltop for my website!"&ndash;%&gt;--%>
       <%--&lt;%&ndash;data-original-title="Twitter Bootstrap Popover">hover for popover</a>&ndash;%&gt;--%>
    <%--<a href="#" id="example" class="btn btn-danger" rel="popover"--%>
       <%-->hover for popover</a>--%>

<%--<sj:a--%>
<%--href="#"--%>
<%--id="example"--%>
<%--cssClass="btn btn-danger"--%>
<%--onClickTopics="openpopover"--%>
<%-->--%>
<%--hover for popover--%>
<%--</sj:a>--%>

<%--</div>--%>
<%--<script>--%>
<%--$(function ()--%>
<%--{--%>
    <%--$("#example").popover({--%>
        <%--html:true, content:"Its so simple to create a tooltop for my website!", title:"Twitter Bootstrap Popover",placement:"bottom"--%>
<%--});--%>
<%--});--%>
<%--</script>--%>

<%--<a href="#" id="userInfo" class="label label-info" data-toggle="popover" data-placement="bottom" data-content="Company : test_company </br>Branch :  mks_01 </br>Area : makassar" data-original-title="<strong>User Information</strong>" >ferdi</a>--%>
<%--<script type='text/javascript'>--%>
<%--$(document).ready(function() {--%>

<%--$("#userInfo").popover({--%>
<%--html:true--%>
<%--});--%>

<%--});--%>
<%--</script>--%>

<%--<script>--%>
<%--$(function ()--%>
<%--{--%>
<%--$("#userInfo").popover({--%>
<%--html:true--%>
<%--});--%>
<%--});--%>
<%--</script>--%>

<%--<sj:a--%>
<%--id="userInfo"--%>
<%--cssClass="label label-info"--%>
<%----%>
<%--onClickTopics="openRemoteDialog"--%>
<%--href="%{remoteurl1}"--%>
<%-->--%>
<%--Open Dialog Two--%>
<%--</sj:a>--%>

<%--<s:url id="remoteurl1" namespace="/admin/function" action="add_function"/>--%>

<%--<sj:a--%>
<%--onClickTopics="openRemoteDialog"--%>
<%--href="%{remoteurl1}"--%>
<%--button="true"--%>
<%--buttonIcon="ui-icon-newwin"--%>
<%-->--%>
<%--Open Dialog Two--%>
<%--</sj:a>--%>

<%--<s:form id="functionForm" namespace="/admin/function" method="post" action="search_functionForm"--%>
                        <%--cssClass="well form-horizontal">--%>

    <%--<s:url id="comboParent" namespace="/admin/function" action="initComboParent_function"/>--%>
    <%--<sj:autocompleter id="parent" name="stParent" href="%{comboParent}"--%>
                      <%--list="listOfComboParent" listKey="stFuncId" listValue="funcName"--%>
                      <%--listLabel="label" delay="50" loadMinimumCount="2"--%>
                      <%--loadingText="Please wait..." />--%>




<%--<s:url id="urlSearch" namespace="/admin/function" action="search_functionForm"/>--%>
<%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true" resizable="false"--%>
           <%--position="center" height="250" width="600" autoOpen="false" title="Searching...">--%>
    <%--Please don't close this window, server is processing your request ...--%>
    <%--</br>--%>
    <%--</br>--%>
    <%--</br>--%>
    <%--<img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">--%>
<%--</sj:dialog>--%>
<%--<sj:submit type="button" cssClass="btn btn-primary" name="search" href="%{urlSearch}" targets="myDiv"--%>
           <%--onClickTopics="showDialog" onCompleteTopics="closeDialog"--%>
        <%-->--%>
    <%--<i class="icon-ok icon-white"></i>--%>
    <%--Search--%>
<%--</sj:submit>--%>

<%--<s:form id="functionForm" namespace="/admin/function" method="post" action="search_functionForm"--%>
                        <%--cssClass="well form-horizontal">--%>
<%--<s:url id="urlSearch" namespace="/admin/function" action="search_functionForm"/>--%>

<%--<sj:submit type="button" cssClass="btn btn-primary" href="%{urlSearch}" id="search" name="search" targets="myDiv"--%>
           <%-->--%>

<%--&lt;%&ndash;<sj:submit type="button" cssClass="btn btn-primary" formIds="functionForm" name="search"&ndash;%&gt;--%>
           <%--&lt;%&ndash;onClickTopics="showDialog" onCompleteTopics="closeDialog">&ndash;%&gt;--%>

    <%--<i class="icon-ok icon-white"></i>--%>
    <%--Search--%>
<%--</sj:submit>--%>

<%--<div id="myDiv">--%>

<%--</div>--%>
<%--</s:form>--%>

<%--<form name='logoutForm' action="/egatewaysimak/logout.action" method='POST'>--%>
<%--<s:submit value="Logout"/>--%>
<%--</form>--%>


<s:form action="initSearchAutoCustomerAction" namespace="/">

    <div class="well">
    <%--<input type="text" id="search" />--%>
        <s:textfield  name="stParent" label="Parent" id="search"/>
    </div>

    <script>
//        var subjects = ['PHP', 'MySQL', 'SQL', 'PostgreSQL', 'HTML', 'CSS', 'HTML5', 'CSS3', 'JSON'];
//        $('#search').typeahead({source: subjects})

        var functions,mapped;
        $('#search').typeahead({
            minLength:2,
            source: function (query, process) {
                // implementation
		//alert(query);
                functions = [];
                mapped = {};

                var data = [];
                dwr.engine.setAsync(false);
                FunctionAction.initComboParent(query,function(listdata) {
                    data=listdata;
                });

                $.each(data, function (i, item) {
                    mapped[item.funcName] = { id: item.stFuncId, label: item.funcName };
                    functions.push(item.funcName);
                });

                process(functions);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                //alert(selectedObj.id);
//                $('term').val(selectedObj.id);
                document.getElementById("term").value=selectedObj.id;
//                document.getElementById("parent").value=selectedObj.id;
                return selectedObj.label;
            }
        });
    </script>

    <s:textfield  name="term" label="term" id="term"/>

    <s:url id="jsoncustomers" action="initAutocompleterCustomerAction"/>
    <sj:autocompleter id="parent" name="stParent" href="%{jsoncustomers}"
                      list="listOfComboParent" listKey="funcId" listValue="funcName"
                      delay="50" loadMinimumCount="2" autocomplete="true"
                      loadingText="Please wait..." />

    <s:submit value="Search" />



</s:form>

</body>
</html>