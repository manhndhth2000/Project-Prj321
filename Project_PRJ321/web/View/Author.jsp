<%-- 
    Document   : Author
    Created on : Jun 30, 2021, 4:25:39 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="listCategory" class="Model.Category" scope="request"></jsp:useBean>
        <jsp:useBean id="listAdmin" class="Model.Admin" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Author Page</title>
            <!-- Bootstrap Core CSS -->
            <link href="../css/bootstrap.min.css" rel="stylesheet">

            <!-- Custom CSS -->
            <link href="../css/blog-post.css" rel="stylesheet">

        </head>
        <body>
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="../View/Home.jsp">Home</a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="../View/About.jsp">About</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Category<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                <c:forEach items="${listCategory.allCategory}" var="i">
                                    <li><a href="${pageContext.request.contextPath}//CategoryController?name=${i.name}&index=1">${i.name}</a></li>
                                    <li class="divider"></li>
                                    </c:forEach>
                                    <c:if test="${admin != null}">
                                    <li>
                                        <a href="../View/AddNewCategory.jsp"> + Add new category</a>
                                    </li>
                                </c:if>    

                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Author<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <c:forEach items="${listAdmin.all}" var="i">
                                    <li><a href="${pageContext.request.contextPath}//AuthorController?name=${i.name}">${i.name}</a>
                                    <li class="divider"></li>
                                    </c:forEach>
                            </ul>
                        </li>
                        <c:if test="${sessionScope.admin != null}">
                            <li><a href="../View/AddPost.jsp">New Post</a></li>
                        </c:if>
                        <c:if test="${sessionScope.admin != null}">
                        <li><a href="${pageContext.request.contextPath}//ListComment">Comment</a></li>
                        </c:if>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${sessionScope.user.getUsername() != null}">
                                <c:choose>
                                    <c:when test="${sessionScope.admin != null}">
                                        <li class="dropdown active">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.getName()} <img style="height: 28px; width: 28px;"  class="img-thumbnail img-circle img-rounded img-responsive" src="../image/Admin/${sessionScope.user.getImage()}"> <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="../View/AdminProfile.jsp">View profile</a></li>
                                                <li><a href="../View/ForgetPassword.jsp">Forget password</a></li>
                                                <li><a href="${pageContext.request.contextPath}//LogoutController">Logout</a></li>
                                            </ul>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="dropdown active">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.getName()} <img style="height: 28px; width: 28px;"  class="img-thumbnail img-circle img-rounded img-responsive" src="../image/User/${sessionScope.user.getImage()}"> <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="${pageContext.request.contextPath}//ProfileController">View profile</a></li>
                                                <li><a href="../View/ForgetPassword.jsp">Forget password</a></li>
                                                <li><a href="${pageContext.request.contextPath}//LogoutController">Logout</a></li>
                                            </ul>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="../View/Login.jsp">Login</a></li>
                                        <li class="divider"></li>
                                        <li><a href="../View/Register.jsp">Register</a></li>
                                    </ul>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- Page Content -->
        <div class="container">

            <div class="row">

                <!-- Blog Post Content Column -->
                <div class="col-lg-8">

                    <!-- Blog Post -->

                    <!-- Title -->
                    <h1 class="page-header">
                        ${author.name}
                    </h1>
                    <h4><strong>Introduction</strong></h4>

                    <!-- Post Content -->
                    <p style="text-align: justify;font-size: 125%; text-indent: 15px;">${author.information}</p>

                </div>

                <!-- Blog Sidebar Widgets Column -->
                <!-- Blog Sidebar Widgets Column -->
                <div class="col-md-4">

                    <!-- Blog Search Well -->
                    <div class="well">
                        <img class="img-responsive" src="<% out.print(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/image/Admin/");%>${author.image}" alt="">
                    </div>

                    <!-- Blog Categories Well -->
                    <div class="well">
                        <h4><strong>Information</strong></h4>
                        <div class="row">
                            <table class="table">
                                <tr>
                                    <td>Name: </td>
                                    <td>${author.name}</td>
                                </tr>
                                <tr>
                                    <td>Email: </td>
                                    <td>${author.email}</td>
                                </tr>
                                <tr>
                                    <td>Date of birth: </td>
                                    <td>${author.dob}</td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.row -->
                    </div>

                    <!-- Side Widget Well -->
                    <div class="well">
                        <h4><strong>Posted</strong></h4>
                        <div class="row">
                            <c:forEach items="${listPost}" var="i">
                                <div class="col-lg-6">
                                    <ul class="list-unstyled">
                                        <li><a href="${pageContext.request.contextPath}//DetailController?title=${i.title}&timePost=${i.timePost}">${i.title}</a>
                                    </ul>
                                </div>
                                <div class="w-100"></div>
                            </c:forEach>
                        </div>
                        <!-- /.row -->
                    </div>

                </div>

            </div>
            <!-- /.row -->

            <hr>

            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
                <!-- /.row -->
            </footer>

        </div>
        <!-- /.container -->
        <!-- jQuery -->
        <script src="../js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../js/bootstrap.min.js"></script>
    </body>
</html>
