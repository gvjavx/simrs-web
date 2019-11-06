<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>

    <%@ include file="/pages/common/header.jsp" %>

    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("companyId").value != '' &&
                    document.getElementById("companyName").value != '' &&
                    document.getElementById("address").value != '' &&
                    document.getElementById("npwp").value != '') {

                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }

            } else {

                event.originalEvent.options.submit = false;

                var msg = "";
                if (document.getElementById("companyId").value == '') {
                    msg = 'Field <strong>Company Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("companyName").value == '') {
                    msg = msg +  'Field <strong>Company Name</strong> is required.' + '<br/>';
                }

                if (document.getElementById("address").value == '') {
                    msg = msg +  'Field <strong>Address</strong> is required.' + '<br/>';
                }

                if (document.getElementById("npwp").value == '') {
                    msg = msg +  'Field <strong>NPWP</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function onChangeButton() {

            document.getElementById('firstButton').style.visibility='hidden';
            document.getElementById('saveButton').style.visibility='visible';
            document.getElementById('companyName').disabled = false;
            document.getElementById('address').disabled = false;
            document.getElementById('npwp').disabled = false;
            document.getElementById('serviceOnOff').disabled = false;
            document.getElementById('mailServer').disabled = false;
            document.getElementById('portServer').disabled = false;
            document.getElementById('userNameServer').disabled = false;
            document.getElementById('passwordServer').disabled = false;
            document.getElementById('defaultMailSender').disabled = false;
            document.getElementById('defaultMailSubject').disabled = false;
            document.getElementById('defaultMailContent').disabled = false;
            document.getElementById('stMinimumLuasan').disabled = false;

        }

        function onLoadPage() {

            document.getElementById('firstButton').style.visibility='visible';
            document.getElementById('saveButton').style.visibility='hidden';
            document.getElementById('companyName').disabled = true;
            document.getElementById('address').disabled = true;
            document.getElementById('npwp').disabled = true;
            document.getElementById('serviceOnOff').disabled = true;
            document.getElementById('mailServer').disabled = true;
            document.getElementById('portServer').disabled = true;
            document.getElementById('userNameServer').disabled = true;
            document.getElementById('passwordServer').disabled = true;
            document.getElementById('defaultMailSender').disabled = true;
            document.getElementById('defaultMailSubject').disabled = true;
            document.getElementById('defaultMailContent').disabled = true;
            document.getElementById('stMinimumLuasan').disabled = true;

        }

    </script>
</head>


<body class="sidebar-push  sticky-footer" onload="onLoadPage()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="container-fluid">
    <div id="main">
        <div class="media">
            <div class="media-body">
                <table width="100%" align="center">
                    <tr>
                        <td align="center">
                            <div id="box">

                                <s:form id="companyForm" method="post" namespace="/admin/company" action="save_company" cssClass="well form-horizontal">

                                    <fieldset>
                                        <legend align="left">Company Information</legend>

                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.companyId">Company Id :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="companyId" name="company.companyId" required="true" readonly="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.companyName">Company Name :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="companyName" name="company.companyName" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.address">Address :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="address" name="company.address" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.npwp">NPWP :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="npwp" name="company.npwp" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.serviceOnOff">Service On Off :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="serviceOnOff" name="company.serviceOnOff" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.mailServer">Mail Server :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="mailServer" name="company.mailServer" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.portServer">Port Server :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="portServer" name="company.portServer" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.userNameServer">User Name Server :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="userNameServer" name="company.userNameServer" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.passwordServer">Password Server :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:password id="passwordServer" name="company.passwordServer" required="true" showPassword="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.defaultEmailSender">Default Mail Sender :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="defaultMailSender" name="company.defaultEmailSender" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.defaultEmailSubject">Default Mail Subject :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="defaultMailSubject" name="company.defaultEmailSubject" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.stMinimumLuasan">Minimum Luasan :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="stMinimumLuasan" name="company.stMinimumLuasan" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="company.defaultEmailContent">Default Mail Content :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textarea cols="10" rows="5" id="defaultMailContent" name="company.defaultEmailContent" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>

                                    <script>onLoadPage()</script>

                                    <div id="actions" class="form-actions">
                                        <table>
                                            <tr id="firstButton">
                                                <td>
                                                    <table>
                                                        <button type="button" class="btn btn-primary" onclick="onChangeButton();">
                                                            <i class="icon-check icon-white"></i> Edit
                                                        </button>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr id="saveButton" style="visibility: hidden;">
                                                <div id="crud">

                                                    <td>
                                                        <table>

                                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="companyForm" id="save" name="save"
                                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                                <i class="icon-ok-sign icon-white"></i>
                                                                Save
                                                            </sj:submit>

                                                            <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false" title="Saving ...">
                                                                Please don't close this window, server is processing your request ...
                                                                </br>
                                                                </br>
                                                                </br>
                                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                            </sj:dialog>

                                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                       position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                       buttons="{
                                                                            'OK':function() { $('#info_dialog').dialog('close'); }
                                                                        }"
                                                            >
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                                Record has been saved successfully.
                                                            </sj:dialog>

                                                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                                       position="center" height="250" width="600" autoOpen="false" title="Error Dialog"
                                                                       buttons="{
                                                                            'OK':function() { $('#error_dialog').dialog('close'); }
                                                                        }"
                                                            >
                                                                <div class="alert alert-error fade in">
                                                                    <label class="control-label" align="left">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                                    </label>
                                                                </div>
                                                            </sj:dialog>

                                                            <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                       position="center" height="280" width="500" autoOpen="false" title="Warning"
                                                                       buttons="{
                                                                            'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                        }"
                                                            >
                                                                <div class="alert alert-error fade in">
                                                                    <label class="control-label" align="left">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                        <br/>
                                                                        <center><div id="errorValidationMessage"></div></center>
                                                                    </label>
                                                                </div>
                                                            </sj:dialog>

                                                        </table>
                                                    </td>


                                                </div>
                                            </tr>
                                        </table>
                                    </div>

                                </s:form>

                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="/pages/common/footer.jsp" %>
</div>

<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>


