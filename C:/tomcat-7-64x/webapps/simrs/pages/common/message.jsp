<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- ActionError Messages - usually set in Actions --%>
<s:if test="hasActionErrors() || exception.message!=null">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <s:iterator value="actionErrors">
            <s:property/><br/>
        </s:iterator>
        <s:property value="%{exception.message}"/>
    </div>
</s:if>
<%--<s:if test="exception.message!=null">--%>
    <%--<div class="alert alert-error fade in">--%>
        <%--<a href="#" data-dismiss="alert" class="close">&times;</a>--%>
        <%--<s:property value="%{exception.message}"/>--%>
    <%--</div>--%>
<%--</s:if>--%>
<%-- FieldError Messages - usually set by validation rules --%>
<s:if test="hasFieldErrors()">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <s:iterator value="fieldErrors">
            <s:iterator value="value">
                <s:property/><br/>
            </s:iterator>
        </s:iterator>
    </div>
</s:if>
<%-- Success Messages --%>
<s:if test="hasActionMessages()">
    <div class="alert alert-success fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <s:actionmessage />
    </div>
</s:if>

