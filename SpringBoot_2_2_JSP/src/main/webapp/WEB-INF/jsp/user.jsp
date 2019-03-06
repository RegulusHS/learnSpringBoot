<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <h3>一行 Java 代码</h3>
    <p>
        今天的日期是:<%=new Date()%>
    </p>
    <h3>
        多行 Java 代码
    </h3>
    <p>
        你的 IP 地址是:
    <%
        out.println("Your IP address is" + request.getRemoteAddr()+"</br>");
        out.println("一段代码 ");
    %>
    </p>
    <h3>For 循环实例</h3>
    <%
        int count = (int)session.getAttribute("count");
        for (int fontSize = 1; fontSize <= count; fontSize++) {
    %>
            Leo come!
    <br/>
    <%}%>
    <h3>标签 c:choose</h3>
    <c:choose>
        <c:when test="${salary <= 0}">
            太惨了。
        </c:when>
        <C:when test="${salary > 1000}">
            不错的薪水，还能生活
        </C:when>
        <c:otherwise>
            什么都没有。
        </c:otherwise>
    </c:choose>
    <h3>布局</h3>
    <%@ include file="footer.jsp"%>
</html>