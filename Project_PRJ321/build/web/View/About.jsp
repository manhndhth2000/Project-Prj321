<%-- 
    Document   : home
    Created on : Jun 12, 2021, 10:49:40 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css"
              integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/styles.css" />
        <link rel="stylesheet" href="../css/grid.css">
        <link rel="stylesheet" href="../css/queries.css">
        <style>
            header{
                background-image: linear-gradient(45deg,rgba(35, 43, 38, 0.8), 60%,rgba(51, 59, 56, 0.85)), url(../image/anh-bia-dong-ho-18.jpg);
                background-size: cover;
                background-position: center;
                height: 100vh;
                /* view height */
                background-attachment: fixed;
            }

            .logo{
                width: 200px;
                float: left;
                margin-left: 70px;
                margin-top: 30px;
                margin-bottom: -100px;
            }

            .main-nav{
                float:right;
                list-style: none;
                margin-right: 50px;
                margin-top: 55px;
            }

            .main-nav li{
                display: inline-block;
                margin-right: 30px;
            }

            .main-nav li a{
                text-decoration: none;
                color: white;
                font-size: 100%;
                font-weight: 700;
                transition: border-bottom 0.5s;
                padding: 3px 0;
            }

            .main-nav li a:hover,
            .main-nav li a:active{
                border-bottom: 2px solid #cccccc;
            }

            .heading-main-box{
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-45%, -55%);
                width: 1140px;
            }
            
        </style>
    </head>
    <body>
        <header id="home">
            <nav >
                <ul class="main-nav">
                    <!--unordered list-->
                    <li ><a href="../View/Home.jsp">Home</a>
                    <li><a href="#sponsor">Sponsor</a></li>
                    <!--list item-->
                    <li><a href="#about">About us</a></li> 
                    <li><a href="#contact">Contact</a></li>
                </ul>
                <i class="fas fa-bars mobile-nav-icon"></i>
            </nav>
            <div class="clearfix"></div>
            <div class="row">
                <div class="heading-main-box">
                    <h1 class="heading-main-text" style="font-size: 150%">
                        We will bring to you  
                        the most objective <br>
                        and accurate reviews
                        of watch's products.<br>
                        So, join with us
                    </h1>
                    <a href="#about" class="btn">About us</a>
                    <a href="#testimonials" class="btn">Evaluation</a>
                </div>
            </div>
        </header>
        <section class="about-section" id="about">
            <div class="row">
                <h2>About Us</h2>
                <p class="p-long">
                    We bring quality and reputable articles, reviews objectively, attractive content
                </p>
            </div>
            <div class="row">
                <div class="col span-1-of-4 about-picture">
                    <img src="../image/other/chatluong1-.jpg" alt="activity1">
                    <p class="picture-title">
                        Quality
                    </p>
                </div>
                <div class="col span-1-of-4 about-picture">
                    <img src="../image/other/uytin1.jpg" alt="activity2">
                    <p class="picture-title">
                        Reputation
                    </p>
                </div>
                <div class="col span-1-of-4 about-picture">
                    <img style="height: 300%" src="../image/other/hapdan.jpg" alt="activity3">
                    <p class="picture-title">
                        Interesting
                    </p>
                </div>
                <div class="col span-1-of-4 about-picture">
                    <img src="../image/other/khachquan.jpg" alt="activity4">
                    <p class="picture-title">
                        Objectivity
                    </p>
                </div>
            </div>
        </section>
        <section class="testimonials-section" id="testimonials">
            <h2>Evaluation</h2>
            <div class="row">
                <div class="col span-1-of-3">
                    <blockquote>
                        The review is quite interesting, giving a lot of knowledge about famous watches. Thanks the author
                        <cite>
                            <img src="../image/other/anhdaidien.jpg" alt="person1"> Nguyen Van A
                        </cite>
                    </blockquote>
                </div>
                <div class="col span-1-of-3">
                    <blockquote>
                        Trust me, this is one of the best watch review sites I've ever seen. You should read and feel what I am saying. 10 points for the quality

                        <cite>
                            <img src="../image/other/anhdaidien.jpg" alt="person2"> Tran Van B
                        </cite>
                    </blockquote>
                </div>
                <div class="col span-1-of-3">
                    <blockquote>
                        Although there are still a few flaws, in general the reviews are quite quality, objective, and the content is quite attractive.
                        <cite>
                            <img src="../image/other/anhdaidien.jpg" alt="person3"> Le Van C
                        </cite>
                    </blockquote>
                </div>
            </div>
        </section>
        <section class="sponsor-section" id="sponsor">
            <h2>Sponsor</h2>
            <div class="row">
                <ul class="sponsors-showcase clearfix">
                    <li>
                        <figure>
                            <img style="box-shadow: 0px 5px 10px 5px rgba(51, 59, 56, 0.85);border-radius: 50%" class="logo-sponsor" src="../image/logo/AP-logo.jpg" alt="Audemars Piguet">
                            <figcaption>
                                Audemars Piguet
                            </figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img style="box-shadow: 0px 5px 10px 5px rgba(51, 59, 56, 0.85);border-radius: 50%" class="logo-sponsor" src="../image/logo/vacheron-constantin-logo_1.jpg" alt="Vacheron Constantin">
                            <figcaption>
                                Vacheron Constantin
                            </figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img style="box-shadow: 0px 5px 10px 5px rgba(51, 59, 56, 0.85);border-radius: 50%" class="logo-sponsor" src="../image/logo/TAG-Heuer-logo.jpg" alt="TAG-Heuer">
                            <figcaption>
                                TAG-Heuer
                            </figcaption>
                        </figure>
                    </li>
                </ul>
                <ul class="sponsors-showcase clearfix">
                    <li>
                        <figure>
                            <img style="box-shadow: 0px 5px 10px 5px rgba(51, 59, 56, 0.85);border-radius: 50%" class="logo-sponsor" src="../image/logo/jaeger-lecoultre-logo.png" alt="Jaeger-lecoultre">
                            <figcaption>
                                Jaeger-lecoultre
                            </figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img style="box-shadow: 0px 5px 10px 5px rgba(51, 59, 56, 0.85);border-radius: 50%" class="logo-sponsor" src="../image/logo/patek-logo-sq.jpg" alt="Patek Philippe">
                            <figcaption>
                                Patek Philippe
                            </figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img style="box-shadow: 0px 5px 10px 5px rgba(51, 59, 56, 0.85); border-radius: 50%" class="logo-sponsor" src="../image/logo/rolex-logo.jpg" alt="Rolex">
                            <figcaption>
                                Rolex
                            </figcaption>
                        </figure>
                    </li>
                </ul>
            </div>
        </section>
        <section class="contact-section" id="contact">
            <div class="row">
                <div class="col span-1-of-3">
                    <ul class="information">
                        <li><i style="color: orange" class="fas fa-map-marker-alt small-icon"></i>Address: 21A, Tran Phu, Ha Tinh</li>
                        <li><i style="color: orange" class="fas fa-envelope small-icon"></i>Email: manhndhe141634@fpt.edu.com</li>
                        <li><i style="color: orange" class="fas fa-phone small-icon"></i>Phone number: (+084 )0996-923-232</li>
                    </ul>
                    <ul class="social-icons">
                        <li><i class="fab fa-facebook"></i></li>
                        <li><i class="fab fa-twitter-square"></i></li>
                        <li><i class="fab fa-instagram"></i></li>
                        <li><i class="fab fa-google-plus-square"></i></li>
                    </ul>
                </div>
                <div class="col span-2-of-3">
                    <form>
                        <div class="row">
                            <div class="col span-1-of-3">
                                <label>Name</label>
                            </div>
                            <div class="col span-2-of-3">
                                <input type="text" placeholder="Your Name">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col span-1-of-3">
                                <label>Email</label>
                            </div>
                            <div class="col span-2-of-3">
                                <input type="email" placeholder="Your Email">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col span-1-of-3">
                                <label>Message</label>
                            </div>
                            <div class="col span-2-of-3">
                                <textarea type="text" placeholder="Your Message"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit" class="btn" value="Send It">
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <footer>
            <p>
                Copyright &copy; 2021 by Nguyen Duy Manh
            </p>
        </footer>
    </body>

</html>
</html>
