<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>招聘信息</title>
    <meta name="robots" content="index, follow">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <link rel="stylesheet" type="text/css" href="/static/main.css">
    <script src="/static/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/static/bootstrap.css">
</head>
<style>
</style>
<body>
<div class="sidebar">
    <header class="sidebar__logo" role="banner">
        <span style="font-size: 20px">武汉高校招聘信息</span>
    </header>
    <nav class="sidebar__nav">
        <h3 class="section__header" style="font-size: x-large">学校</h3>
        <ul class="section__links">
            <li><a href="/career?school=武汉大学" class="section__link" style="font-size: large">武汉大学</a></li>
            <li><a href="/career?school=华中科技大学" class="section__link" style="font-size: large">华中科技大学</a></li>
            <li><a href="/career?school=华中师范大学" class="section__link" style="font-size: large">华中师范大学</a></li>
            <li><a href="/career?school=武汉理工大学" class="section__link" style="font-size: large">武汉理工大学</a></li>
        </ul>
    </nav>
</div>
<div class="content__overlay"></div>
<main class="content__area" role="main">
    <div class="content">

        <div class="home-banner">
            <div style="padding: 70px 0px 10px;">
                <form class="bs-example bs-example-form" role="form" method="post" action="/career/findByKeyWord">
                    <div class="col-lg-6">
                        <div class="input-group">
                            <input type="text" class="form-control" name="keyWord" placeholder="公司名 职位名 学校名"
                                   required="required">
                            <span class="input-group-btn">
						<button class="btn btn-primary" type="button">查找</button>
					</span>
                        </div><!-- /input-group -->
                    </div><!-- /.col-lg-6 -->
                </form>
            </div>
        </div>

        <div>
            <table class="table table-hover table-condensed">
                <thead>
                <tr>
                    <th>时间</th>
                    <th>信息</th>
                    <th>学校</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${page.list==null || fn:length(page.list) == 0}">
                        <span>无数据</span>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="career" items="${page.list}">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${career.endDate !=null && career.endDate !=''}">
                                            (结束时间)<fmt:formatDate value="${career.endDate}"
                                                                  pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </c:when>
                                        <c:when test="${career.startDate !=null && career.startDate !=''}">
                                            (开始时间)<fmt:formatDate value="${career.startDate}"
                                                                  pattern="yyyy-MM-dd"/>
                                        </c:when>
                                        <c:otherwise>
                                            无时间
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${career.title != null && career.title !=''}">
                                            <a href="/career/findById/${career.id}">${career.title}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/career/findById/${career.id}">${career.careerName}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="${career.url}">${career.school}</a></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <ul class="pagination pagination-lg">
                <c:if test="${page.hasPreviousPage}">
                    <li><a href="/career?pageNum=${page.prePage}&school=${school}">&laquo;</a></li>
                </c:if>
                <c:forEach items="${page.navigatepageNums}" var="nav">
                    <c:if test="${nav == page.pageNum}">
                        <li style="font-weight: bold;"><a href="/career?pageNum=${nav}&school=${school}">${nav}</a></li>
                    </c:if>
                    <c:if test="${nav != page.pageNum}">
                        <li><a href="/career?pageNum=${nav}&school=${school}">${nav}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${page.hasNextPage}">
                    <li><a href="/career?pageNum=${page.nextPage}&school=${school}">&raquo;</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</main>

</body>
</html>