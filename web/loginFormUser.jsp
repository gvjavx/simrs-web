<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GO-MEDSYS NMU</title>
    <link rel="shortcut icon" href="<s:url value="/pages/images/logo-nmu.webp"/>"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/pages/css/style-login.css"/>"/>

    <script src="<s:url value="/pages/script/login.custom.js"/>"></script>
    <!--[if lte IE 7]>
    <style>.main {
        display: none;
    }

    .support-note .note-ie {
        display: block;
    }
    </style>
    <![endif]-->
    <sj:head jqueryui="true" jquerytheme="pepper-grinder"/>

    <style>
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
    </style>
</head>
<script type="text/javascript">
    function validationSubmit() {
        var username=window.document.getElementsByName('j_username')[0].value;
        var password=window.document.getElementsByName('j_password')[0].value;

        if (username== '' || password == '') {
            window.document.getElementById('messageError').innerHTML='';
            window.document.getElementById('validationSubmitError').innerHTML='Your username or password is empty. Please enter correctly.';
            return false;
        } else  {
            window.document.getElementById('validationSubmitError').innerHTML='';
            return true;
        }
    }
</script>
<style>
    /*body{*/
        /*background: #ddd url(/images/bg-login.jpg) center center;*/
    /*}*/
    .form-login{
        background-color: #FFF;
        width: 300px;
        height: 100px;
        border-radius: 5px;
        box-shadow: 0px 1px 5px 0px rgba(102, 102, 102, 0.4);
        margin: auto;
        padding: 20px;
    }
    .input{
        margin-left: -50px;
        border: 0;
        border-bottom: 1px solid #50d4a3;
        /*color: #50d4a3;*/
    }

    .input::placeholder{
        color: grey;
        opacity: 0.5;
        padding: 5px;
    }

    .icon{
        float: left;
        color: #50d4a3;
        width: 20px;
        margin-top: 3px;
    }

    .button{
        float: right;
        margin: auto;
        background-color: #FFF;
        color: #50d4a3;
        width: 40px;
        height: 60px;
        border: 1px solid #50d4a3;
        box-shadow: none;
        border-radius: 5px;
        margin-top: -55px;
    }

    .button:hover{
        cursor: pointer;
        background-color: #50d4a3;
        color: #FFF;
        transition:.5s;
    }

    .input:focus, .button:focus{
        outline: none;
    }
</style>
<body>

<div class="container">
    <br><br><br><br>
<div class="panel" style="background-color: rgba(255, 255, 255, .2); backdrop-filter: blur(8px); height: auto; width: 500px;margin:auto;text-align: center;padding: 50px;border-radius: 10px;box-shadow: 0px 4px 8px 0px rgba(102, 102, 102, 0.4);">
    <header>

        <h2></h2>

        <br>
        <p>
            <img  style="margin-left: -30px" width="80%" height="10%" border="0" src="<s:url value="/pages/images/logo-nmu.png"/>" name="image_cloud">
            <%--<img width="40%" height="10%" border="0" src="<s:url value="/pages/images/logo-nmu.png"/>" name="image_cloud">--%>
        </p>

        <div class="support-note">
            <span class="note-ie">Sorry, only modern browsers.</span>
        </div>

    </header>

    <s:if test="flagError">
        <div class="errorblock">
	            <span id="messageError" class="note-errorblock">
                    <br><br>
                  <div style="background-color: #edcccc; min-width: 400px; max-width: 500px; padding: 10px; text-align: center;font-size: 12px; border-radius: 10px;margin: auto">
                        Your login attempt was not successful, try again. Caused :
                      </br>
                      <s:if test='messageError=="Maximum sessions of 1 for this principal exceeded"'>
                          User yang anda masukkan sudah login pada device lain
                      </s:if>
                        <s:else>
                            <s:property value="%{messageError}"/>
                        </s:else>
                    </div>
                     <%--Your login attempt was not successful, try again. Caused :--%>
                    <%--</br>--%>
                    <%--<s:property value="%{messageError}"/>--%>
                    <%--<br>--%>

                        <%--<s:if test="flagSignUp">--%>
                        <%--</br>--%>
                        <%--<s:a action="signUpUser">Sign up for Neurix (e-payment) apps</s:a>--%>
                    <%--</s:if>--%>
                    <%--<s:else>--%>
                        <%--</br>--%>
                        <%--<img border="0" src="<s:url value="%{userPhotoUrl}"/>" name="image_user">--%>
                    <%--</s:else>--%>
	             </span>
        </div>
    </s:if>
    <s:if test="hasActionErrors()">
        <div class="errorblock">
                <span class="note-errorblock">
                    <s:actionerror/>
                </span>
        </div>
    </s:if>

    <div  class="errorblock" >
        <span id="validationSubmitError" class="note-errorblock"></span>
    </div>


    <section class="main">
        <br><br><br>
        <div class="form-login">
            <form id="formlogin" action="<s:url value='j_spring_security_check'/>" method='POST' onsubmit="return validationSubmit();">
                <div>
                    <div class="icon">
                        <i class="icon-user icon-large"></i>
                    </div>
                    <s:textfield name="j_username" value="%{userName}" cssClass="input" placeholder="User id or email"/>
                </div>
                <br>
                <div>
                    <div class="icon">
                        <i class="icon-lock icon-large"></i>
                    </div>
                    <s:password name="j_password" cssClass="input" placeholder="Password"/>
                </div>


                <div id="waiting_dialog" style="font-size:14px;font-family: Arial, Helvetica, sans-serif;text-align : center;background-color: rgba(255, 255, 255, 1);">
                    Please wait a moment, server is loging in your account ...
                    </br>
                    <%--</br>--%>
                    <%--<img border="0" src="<s:url value="/pages/images/spinner.gif"/>" width="20%" name="image_indicator_read">--%>
                    <center>
                        <div style="display: inline-block">
                        <img border="0" style="width: 100px; height: 90px; margin-top: 20px"
                             src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                             name="image_indicator_write">
                        <img class="spin" border="0" style="width: 40px; height: 40px; margin-left: -50px; margin-bottom: 10px"
                             src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                             name="image_indicator_write">
                        </div>
                    </center>
                </div>

                <script type='text/javascript'>
                    jQuery(document).ready(function () {
                        var options_waiting_dialog = {};
                        options_waiting_dialog.height = 200;
                        options_waiting_dialog.width = 500;
                        options_waiting_dialog.title = "Loging in...";
                        //options_waiting_dialog.position = "center";
                        options_waiting_dialog.resizable = false;
                        options_waiting_dialog.autoOpen = false;
                        options_waiting_dialog.modal = true;
                        options_waiting_dialog.opentopics = "showDialog";
                        options_waiting_dialog.closetopics = "closeDialog";
                        options_waiting_dialog.jqueryaction = "dialog";
                        options_waiting_dialog.id = "waiting_dialog";
                        options_waiting_dialog.href = "#";

                        jQuery.struts2_jquery_ui.bind(jQuery('#waiting_dialog'),options_waiting_dialog);
                        jQuery(".ui-dialog-titlebar-close").hide();
                    });
                </script>

                <script type='text/javascript'>
                    jQuery(document).ready(function () {
                        var options_search = {};
                        options_search.jqueryaction = "button";
                        options_search.id = "login";
                        options_search.name = "login";
                        options_search.oncom = "closeDialog";
                        options_search.href = "#";
                        options_search.formids = "formlogin";
                        options_search.onclick = "showDialog";

                        jQuery.struts2_jquery.bind(jQuery('#login'),options_search);
                    });
                </script>

                <button type="submit" id="login" name="login" class="button"><i class="icon-arrow-right icon-large"></i></button>
            </form>
        </div>


        <%--<form id="formlogin" class="form-1" action="<s:url value='j_spring_security_check'/>" method='POST' onsubmit="return validationSubmit();">--%>
            <%--<p class="field">--%>
                <%--<s:textfield name="j_username" value="%{userName}" placeholder="Userid or email"/>--%>
                <%--&lt;%&ndash;<input type="text" name="j_username" value="%{userName}" placeholder="Userid or email">&ndash;%&gt;--%>
                <%--<i class="icon-user icon-large"></i>--%>
            <%--</p>--%>

            <%--<p class="field">--%>
                <%--<s:password name="j_password" placeholder="Password"/>--%>
                <%--&lt;%&ndash;<input type="password" name="j_password" placeholder="Password">&ndash;%&gt;--%>
                <%--<i class="icon-lock icon-large"></i>--%>
            <%--</p>--%>
            <%--&lt;%&ndash;<p class="field">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="checkbox" name="_spring_security_remember_me">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<label for="remember" class="label">Remember Me</label>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</p>&ndash;%&gt;--%>

            <%--<div id="waiting_dialog" style="font-size:14px;font-family: Arial, Helvetica, sans-serif;text-align : center;background-color: rgba(255, 255, 255, 1);">--%>
                <%--Please wait a moment, server is loging in your account ...--%>
                <%--</br>--%>
                <%--</br>--%>
                <%--</br>--%>
                <%--<img border="0" src="<s:url value="/pages/images/spinner.gif"/>" width="20%" name="image_indicator_read">--%>
            <%--</div>--%>
            <%--<script type='text/javascript'>--%>
                <%--jQuery(document).ready(function () {--%>
                    <%--var options_waiting_dialog = {};--%>
                    <%--options_waiting_dialog.height = 200;--%>
                    <%--options_waiting_dialog.width = 500;--%>
                    <%--options_waiting_dialog.title = "Loging in...";--%>
                    <%--//options_waiting_dialog.position = "center";--%>
                    <%--options_waiting_dialog.resizable = false;--%>
                    <%--options_waiting_dialog.autoOpen = false;--%>
                    <%--options_waiting_dialog.modal = true;--%>
                    <%--options_waiting_dialog.opentopics = "showDialog";--%>
                    <%--options_waiting_dialog.closetopics = "closeDialog";--%>
                    <%--options_waiting_dialog.jqueryaction = "dialog";--%>
                    <%--options_waiting_dialog.id = "waiting_dialog";--%>
                    <%--options_waiting_dialog.href = "#";--%>

                    <%--jQuery.struts2_jquery_ui.bind(jQuery('#waiting_dialog'),options_waiting_dialog);--%>
                    <%--jQuery(".ui-dialog-titlebar-close").hide();--%>
                <%--});--%>
            <%--</script>--%>

            <%--<script type='text/javascript'>--%>
                <%--jQuery(document).ready(function () {--%>
                    <%--var options_search = {};--%>
                    <%--options_search.jqueryaction = "button";--%>
                    <%--options_search.id = "login";--%>
                    <%--options_search.name = "login";--%>
                    <%--options_search.oncom = "closeDialog";--%>
                    <%--options_search.href = "#";--%>
                    <%--options_search.formids = "formlogin";--%>
                    <%--options_search.onclick = "showDialog";--%>

                    <%--jQuery.struts2_jquery.bind(jQuery('#login'),options_search);--%>
                <%--});--%>
            <%--</script>--%>

            <%--<p class="submit">--%>
                <%--<button type="submit" id="login" name="login"><i class="icon-arrow-right icon-large"></i></button>--%>
            <%--</p>--%>
        <%--</form>--%>
    </section>

</div>
</div>
<a target="_blank" href="<%= request.getContextPath() %>/pages/antrian/choseeAntrian.jsp" style="color: blue; position: absolute; bottom: 10px ;float: right !important;padding: 10px">Antrian</a>
</body>
</html>