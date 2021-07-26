<%-- 
    Document   : Detail
    Created on : Jun 29, 2021, 6:18:06 PM
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
            <title>Detail Page</title>
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
                    <h1>${BlogPost.title}</h1>
                    <c:if test="${admin != null}">
                        <p class="navbar-right">
                            <a href="${pageContext.request.contextPath}//getPostController?title=${BlogPost.title}&timePost=${BlogPost.timePost}"><button>Edit</button></a>  <a href="${pageContext.request.contextPath}//deletePostController?title=${BlogPost.title}&timePost=${BlogPost.timePost}"><button>Delete</button></a>
                        </p>
                    </c:if>
                    <!-- Author -->
                    <p class="lead">
                        by <a href="${pageContext.request.contextPath}//AuthorController?name=${BlogPost.author}">${BlogPost.author}</a>
                    </p>

                    <hr>

                    <!-- Date/Time -->
                    <p> Posted on ${BlogPost.timePost}</p>

                    <hr>

                    <!-- Preview Image -->
                    <img class="img-responsive" src="../image/Detail/${BlogPost.image}" alt="">

                    <hr>

                    <!-- Post Content -->
                    <p style="text-align: justify;font-size: 125%; text-indent: 15px;">${BlogPost.description}</p>
                    <hr>

                    <!-- Blog Comments -->

                    <!-- Comments Form -->
                    <div class="well">
                        <h4>Leave a Comment:</h4>
                        <form role="form" action="${pageContext.request.contextPath}//CommentController?blog_id=${BlogPost.id}" method="POST">
                            <div class="form-group">
                                <textarea style="resize: none" class="form-control" rows="3" name="comment" ${sessionScope.admin != null?"disabled=''":""}></textarea>
                            </div>
                            <input type="submit" name ="submit" class="btn btn-primary" value="Send" ${sessionScope.admin != null?"disabled=''":""}>
                        </form>
                    </div>

                    <hr>

                    <!-- Posted Comments -->

                    <!-- Comment -->
                    <c:if test="${listComment != null}">
                        <c:forEach items="${listComment}" var="i">
                            <div class="media">
                                <a class="pull-left">
                                    <img src="../image/User/${i.image}" style="border: 1px solid black; height: 38px; width: 38px" class="img-circle img-thumbnail" alt="avatar">
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading">${i.user}
                                        <small> on ${i.timePost}</small> 
                                        <c:if test="${admin == null}">
                                            <c:set var="check2" scope="request" value="false"></c:set>
                                            <c:forEach items="${user_liked}" var="o">
                                                <c:if test="${o.cmtID == i.id && check2==false}">
                                                    <a href="${pageContext.request.contextPath}//unlikeController?like=${i.like}&id=${i.id}&postId=${BlogPost.id}"><button>Unlike</button></a>${i.like}      
                                                    <c:set var="check2" scope="request" value="true"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${check2==false}">
                                                <a href="${pageContext.request.contextPath}//likeController?like=${i.like}&id=${i.id}&postId=${BlogPost.id}"><button>Like</button></a>${i.like}
                                            </c:if>
                                        </c:if>
                                        <c:if test="${admin != null && bool=='true'}">
                                            <a style="margin-left: 200px" href="${pageContext.request.contextPath}//DeleteComment?id=${i.id}"><button>Delete</button></a>
                                        </c:if>    
                                    </h4>
                                    ${i.description}
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>

                <!-- Blog Sidebar Widgets Column -->
                <div class="col-md-4">

                    <!-- Blog Search Well -->
                    <div class="well">
                        <h4><strong>Search</strong></h4>
                        <form class="input-group" action="${pageContext.request.contextPath}//SearchController?index=1" method="POST">
                            <input type="text" name="input" class="form-control">
                            <span class="input-group-btn">
                                <input class="btn btn-default" type="submit" name="search" value="Search">
                            </span>
                        </form>
                        <!-- /.input-group -->
                    </div>

                    <!-- Blog Categories Well -->
                    <div class="well">
                        <h4><strong>Categories</strong></h4>
                        <div class="row">
                            <c:forEach items="${listCategory.allCategory}" var="i">
                                <div class="col-lg-6">
                                    <ul class="list-unstyled">
                                        <li><a href="${pageContext.request.contextPath}//CategoryController?name=${i.name}&index=1">${i.name}</a>
                                    </ul>
                                </div>
                                <div class="w-100"></div>
                            </c:forEach>
                            <c:if test="${admin != null}">
                                <div class="col-lg-6">
                                    <ul class="list-unstyled">
                                        <li><a href="../View/ListCategory.jsp"><strong> + View category</strong></a></li>
                                    </ul>
                                </div>
                            </c:if>    
                        </div>
                        <!-- /.row -->
                    </div>

                    <!-- Side Widget Well -->
                    <div class="well">
                        <h4><strong>Summary</strong></h4>
                        <p>${BlogPost.shortDes}</p>
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
