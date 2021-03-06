<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="order.model.service.OrderService"%>
<%@page import="order.model.vo.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Teamwork</title>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/index.css/" />
<script type="text/javascript" href="<%= request.getContextPath()%>/js/index.js"></script>
</head>
<body>
	<!-- START TEAM -->
    <section id="team">
        <div class="container">
            <div class="row">
                <div class="col-md-5 col-sm-12 pull-right">
                    <div class="team-section-text">
                        <div class="section-count">
                            <span>05</span>
                        </div>
                        <!-- END section-count-->
                        <div class="section-text">
                            <h2 class="section-title">Git Team Project</h2>
                            <p>
                                blablabla
                            </p>
                        </div>
                        <!-- END section-text-->
                    </div>
                    <!-- END team-section-text-->
                </div>
                <!-- END col-md-5 col-sm-12 pull-right-->
                <div class="col-md-7 col-sm-12">
                    <div class="row">
                        <div class="col-md-3 col-sm-4">
                            <div class="team-list">
                                <ul>
                                    <li class="wow zoomIn" data-wow-duration="1s" data-wow-delay=".1s">
                                        <a href="#team-1" data-team="team-1">
                                            <figure>
                                                <img src="http://echotheme.com/naive-v2/static-image/img/man/1.jpg" alt="Team Member image One">
                                            </figure>
                                        </a>
                                    </li>
                                    <li class="active wow zoomIn" data-wow-duration="1s" data-wow-delay=".3s">
                                        <a href="#team-2" data-team="team-2">
                                            <figure>
                                                <img src="http://echotheme.com/naive-v2/static-image/img/man/2.jpg" alt="Team Member image two">
                                            </figure>
                                        </a>
                                    </li>
                                    <li class="wow zoomIn" data-wow-duration="1s" data-wow-delay=".5s">
                                        <a href="#team-3" data-team="team-3">
                                            <figure>
                                                <img src="http://echotheme.com/naive-v2/static-image/img/man/3.jpg" alt="Team Member image three">
                                            </figure>
                                        </a>
                                    </li>
                                    <li class="wow zoomIn" data-wow-duration="1s" data-wow-delay=".7s">
                                        <a href="#team-4" data-team="team-4">
                                            <figure>
                                                <img src="http://echotheme.com/naive-v2/static-image/img/man/4.jpg" alt="Team Member image four">
                                            </figure>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- END team-list-->
                        </div>
                        <!-- END col-sm-4-->
                        <div class="col-md-9 col-sm-8">

                            <div id="team-1" class="team-single">
                                <div class="team-img">
                                    <img src="http://echotheme.com/naive-v2/static-image/img/man/1.jpg" alt="">
                                    <div class="team-social">
                                        <ul>
                                            <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                            <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href=""><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- END team-social-->
                                </div>
                                <!-- END team-img-->
                                <div class="team-info text-center">
                                    <h4>Andy Maray</h4>
                                    <p>Founder &amp; CEO</p>
                                </div>
                                <!-- END team-info-->
                            </div>
                            <!-- END team-single-->


                            <div id="team-2" class="team-single active">
                                <div class="team-img">
                                    <img src="http://echotheme.com/naive-v2/static-image/img/man/2.jpg" alt="">
                                    <div class="team-social">
                                        <ul>
                                            <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                            <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href=""><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- END team-social-->
                                </div>
                                <!-- END team-img-->
                                <div class="team-info text-center">
                                    <h4>Gustavo Souza</h4>
                                    <p>Naive Designer</p>
                                </div>
                                <!-- END team-info-->
                            </div>
                            <!-- END team-single-->


                            <div id="team-3" class="team-single">
                                <div class="team-img">
                                    <img src="http://echotheme.com/naive-v2/static-image/img/man/3.jpg" alt="">
                                    <div class="team-social">
                                        <ul>
                                            <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                            <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href=""><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- END team-social-->
                                </div>
                                <!-- END team-img-->
                                <div class="team-info text-center">
                                    <h4>Nikolaos Markopoulos</h4>
                                    <p>Developer</p>
                                </div>
                                <!-- END team-info-->
                            </div>
                            <!-- END team-single-->


                            <div id="team-4" class="team-single">
                                <div class="team-img">
                                    <img src="http://echotheme.com/naive-v2/static-image/img/man/4.jpg" alt="">
                                    <div class="team-social">
                                        <ul>
                                            <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                            <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href=""><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- END team-social-->
                                </div>
                                <!-- END team-img-->
                                <div class="team-info text-center">
                                    <h4>Nur jaman</h4>
                                    <p>Developer</p>
                                </div>
                                <!-- END team-info-->
                            </div>
                            <!-- END team-single-->

                        </div>
                        <!-- END col-sm-8-->
                    </div>
                    <!-- END row-->
                </div>
                <!-- END col-md-7 col-sm-12 -->
            </div>
            <!-- END row-->
        </div>
        <!-- END container-->
    </section>
    <!-- END team-->
    <!-- END TEAM -->
</body>
</html>