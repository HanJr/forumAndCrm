<%@ tag language="java" body-content="empty" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ tag import="java.time.format.DateTimeFormatter"%>
<%@ attribute name="value" type="java.time.temporal.TemporalAccessor" %>
<%@ attribute name="pattern" type="java.lang.String"%>

<% if(pattern == null) pattern = "yyyyMMdd"; %>

<%= DateTimeFormatter.ofPattern(pattern).format(value) %>

