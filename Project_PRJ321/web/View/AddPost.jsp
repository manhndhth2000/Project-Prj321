<%-- 
    Document   : AddPost.jsp
    Created on : Jul 17, 2021, 5:58:40 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="a" class="Model.BlogPost" scope="request"></jsp:useBean>
        <jsp:useBean id="listCategory" class="Model.Category" scope="request"></jsp:useBean>
        <jsp:useBean id="listAdmin" class="Model.Admin" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Blog Page</title>
            <link href="../css/bootstrap.min.css" rel="stylesheet">

            <!-- Custom CSS -->
            <link href="../css/blog-home.css" rel="stylesheet">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script type="text/javascript">
                $(document).ready(function () {


                    var readURL = function (input) {
                        if (input.files && input.files[0]) {
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                $('.avatar').attr('src', e.target.result);
                            }

                            reader.readAsDataURL(input.files[0]);
                        }
                    }


                    $(".file-upload").on('change', function () {
                        readURL(this);
                    });
                });
            </script>
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
        <div class="container">

            <div class="row">

                <!-- Blog Entries Column -->
                <div class="col-md-8">
                    <form action="${pageContext.request.contextPath}//AddPostController" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <img id="img" src="http://placehold.it/900x300" class="avatar img-responsive" alt="avatar">
                            <h3>Upload a different photo...</h3>
                            <input type="file" name="photo" class="file-upload" accept="image/png, image/jpeg">
                        </div>
                        <div class="form-group">
                            <label>Title</label>
                            <input class="form-control" type="text" name="title">
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category" class="form-control">
                                <c:forEach items="${listCategory.allCategory}" var="i">
                                    <option>${i.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Short description</label>
                            <textarea style="resize: none" class="form-control" name="shortDes"  rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea style="resize: none" class="form-control" name="description"  rows="10"></textarea>
                        </div>
                        <input type="submit" name ="submit" class="btn btn-default" value="Submit">
                    </form>
                    <br><br>
                    <a href="${pageContext.request.contextPath}//HomeController?index=1">Back to HomePage</a>
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
                        <h4><strong>Contact us</strong></h4>
                        <p>Address: 21A, Tran Phu, Ha Tinh</p>
                        <p>Email: manhndhe141634@fpt.edu.com</p>
                        <p>Phone number: (+084 )0996-923-232</p>
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
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </footer>

        </div>   
    </body>
</html>
