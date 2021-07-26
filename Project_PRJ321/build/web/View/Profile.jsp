<%-- 
    Document   : Profile
    Created on : Jul 5, 2021, 1:59:11 PM
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
            <title>Profile Page</title>
            <link href="../css/bootstrap.min.css" rel="stylesheet">

            <!-- Custom CSS -->
            <link href="../css/blog-home.css" rel="stylesheet">
            <!-- jQuery -->

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
            <hr>
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
                            <li><a href="../View/Admin.jsp">Admin</a></li>
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
        <div class="container bootstrap snippet">
            <div class="row">
                <div class="col-sm-3"><!--left col-->
                    <div class="text-center">
                        <form action="${pageContext.request.contextPath}//UploadImageController?username=${sessionScope.user.getUsername()}" method="post" enctype="multipart/form-data">
                            <c:choose>
                                <c:when test="${sessionScope.user.getImage() == null}">
                                    <img src="image/other/anhdaidien.jpg" style="border: 1px solid black" class="avatar img-circle img-thumbnail" alt="avatar">
                                </c:when>
                                <c:otherwise>
                                    <img id="img" src="../image/User/${sessionScope.user.getImage()}" class="avatar img-circle img-thumbnail" alt="avatar">
                                </c:otherwise>
                            </c:choose>
                            <h6>Upload a different photo...</h6>
                            <input type="file" name="photo" class="text-center center-block file-upload" accept="image/png, image/jpeg" >
                            <br>
                            <input class="btn btn-primary btn-sm btn-success " type="submit" name="submit" value="Save Image" onclick="reload()">
                            <br>
                            <br>
                        </form>  
                        <ul class="list-group">
                            <li class="list-group-item text-muted">Activity <i class="fa fa-dashboard fa-1x"></i></li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span> 125</li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span> 13</li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span> 37</li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span> 78</li>
                        </ul>     
                    </div></hr><br>
                </div><!--/col-3-->
                <div class="col-sm-9">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#home">Information</a></li>
                        <li><a data-toggle="tab" href="#settings">Activity</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="home">
                            <form action="${pageContext.request.contextPath}//UpdateProfileController">
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label><h4>Username</h4></label>
                                        <input type="text" class="form-control" name="username" value="${sessionScope.user.getUsername() == "" ? "":user.getUsername()}" placeholder="Username" readonly="" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label><h4>Password</h4></label>
                                        <input type="password" class="form-control" name="password" value="${sessionScope.user.getPassword() == "" ? "":user.getPassword()}" placeholder="password" readonly="" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label for="email"><h4>Email</h4></label>
                                        <input type="email" class="form-control" name="email" value="${sessionScope.user.getEmail() == "" ? "":sessionScope.user.getEmail()}" placeholder="you@email.com" required="">
                                    </div>
                                </div>
                                <div class="form-group">

                                    <div class="col-xs-6">
                                        <label><h4>Name</h4></label>
                                        <input type="text" class="form-control" name="name" value="${sessionScope.user.getName() == "" ? "":sessionScope.user.getName()}" placeholder="Your name" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label><h4>Gender</h4></label>
                                        <div>
                                            <div class="radio-inline">
                                                <label style="margin-top: 10px"><input type="radio" name="optradio" value="Male" ${sessionScope.user.getGender() == "" || sessionScope.user.getGender() == "Male" ? "checked":""}>Male</label>
                                            </div>
                                            <div class="radio-inline">
                                                <label style="margin-top: 10px"><input type="radio" name="optradio" value="Female" ${sessionScope.user.getGender() == "Female" ? "checked":""}>Female</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label><h4>Date of birth</h4></label>
                                        <input type="date" class="form-control" name="dob" value="${sessionScope.user.getDateofbirth() == null?"":sessionScope.user.getDateofbirth()}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-10">
                                        <label><h4>Address</h4></label>
                                        <input type="text" class="form-control" name="address" value="${sessionScope.user.getAddress() == null ? "":sessionScope.user.getAddress()}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12 text-center">
                                        <br>                                     
                                        <input class="btn btn-primary btn-lg btn-success " type="submit" name="submit" value="Save">
                                        <input class="btn btn-primary btn-lg" type="reset" value="Reset">
                                    </div>
                                </div>
                                <hr>
                            </form>
                        </div><!--/tab-pane-->
                        <div class="tab-pane" id="settings">
                            <hr>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Time Post</th>
                                            <th>Blog Post</th>
                                            <th>Comment</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.listComment}" var="i">
                                            <tr>
                                                <td>${i.getId()}</td>
                                                <td>${i.getTimePost()}</td>
                                                <td>${i.getBlogPost()}</td>
                                                <td>${i.getDescription()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div><!--/tab-pane-->
                </div><!--/tab-content-->
            </div><!--/col-9-->
        </div><!--/row-->
    </div>
</body>
</html>
