<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>BKX</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Glance Design Dashboard Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap_admin.css" rel='stylesheet' type='text/css' />

<!-- Custom CSS -->
<link href="css/style_admin.css" rel='stylesheet' type='text/css' />

<!-- font-awesome icons CSS -->
<link href="css/font-awesome_admin.css" rel="stylesheet">
<!-- //font-awesome icons CSS-->

<!-- side nav css file -->
<link href='css/SidebarNav.min_admin.css' media='all' rel='stylesheet'
	type='text/css' />
<!-- //side nav css file -->

<!-- js-->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/modernizr.custom.js"></script>

<!--webfonts-->
<link
	href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext"
	rel="stylesheet">
<!--//webfonts-->

<!-- chart -->
<script src="js/Chart.js"></script>
<!-- //chart -->

<!-- Metis Menu -->
<script src="js/metisMenu.min.js"></script>
<script src="js/custom.js"></script>
<link href="css/custom_admin.css" rel="stylesheet">
<!--//Metis Menu -->
<style>
#chartdiv {
	width: 100%;
	height: 295px;
}
</style>
<!--pie-chart -->
<!-- index page sales reviews visitors pie chart -->
<script src="js/pie-chart.js" type="text/javascript"></script>
<script type="text/javascript">

        $(document).ready(function () {
            $('#demo-pie-1').pieChart({
                barColor: '#2dde98',
                trackColor: '#eee',
                lineCap: 'round',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

            $('#demo-pie-2').pieChart({
                barColor: '#8e43e7',
                trackColor: '#eee',
                lineCap: 'butt',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

            $('#demo-pie-3').pieChart({
                barColor: '#ffc168',
                trackColor: '#eee',
                lineCap: 'square',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

           
        });

    </script>
<!-- //pie-chart -->
<!-- index page sales reviews visitors pie chart -->

<!-- requried-jsfiles-for owl -->
<link href="css/owl.carousel_admin.css" rel="stylesheet">
<script src="js/owl.carousel.js"></script>
<script>
							$(document).ready(function() {
								$("#owl-demo").owlCarousel({
									items : 3,
									lazyLoad : true,
									autoPlay : true,
									pagination : true,
									nav:true,
								});
							});
						</script>
<!-- //requried-jsfiles-for owl -->
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		
		<jsp:include page="../thanhphanchung/menu.jsp"></jsp:include>
		<jsp:include page="../thanhphanchung/header.jsp"></jsp:include>
		
		<!-- //header-ends -->
		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<div class="col-md-9 ">
					<!-- Phần code của bản đồ -->
				</div>
				<div class="col-md-3">
				<div class="activity_box">
					<h2>Danh sách thành viên</h2>
					<div class="scrollbar" id="style-1">
						<div class="activity-row">
							<div class="col-xs-4 activity-img"><img src="images/4.jpg" class="img-responsive" alt=""></div>
							<div class="col-xs-7 activity-desc1"><h6>Nguyễn Văn Công</h6></div>
							
							<div class="col-xs-1 activity-desc1"><h6>o</h6></div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="activity-row">
							<div class="col-xs-4 activity-img"><img src="images/4.jpg" class="img-responsive" alt=""></div>
							<div class="col-xs-7 activity-desc1"><h6>Dương Thế Cường</h6></div>
							
							<div class="col-xs-1 activity-desc1"><h6>o</h6></div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="activity-row">
							<div class="col-xs-4 activity-img"><img src="images/4.jpg" class="img-responsive" alt=""></div>
							<div class="col-xs-7 activity-desc1"><h6>Nguyễn Phúc Đạc</h6></div>
							
							<div class="col-xs-1 activity-desc1"><h6>o</h6></div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="activity-row">
							<div class="col-xs-4 activity-img"><img src="images/4.jpg" class="img-responsive" alt=""></div>
							<div class="col-xs-7 activity-desc1"><h6>Vũ Đức Việt</h6></div>
							
							<div class="col-xs-1 activity-desc1"><h6>o</h6></div>
							<div class="clearfix"> </div>
						</div>
					</div>
					
				</div>
			</div>
				
			</div>
		</div>
		<!--footer-->
		<jsp:include page="../thanhphanchung/footer.jsp"></jsp:include>
		<!--//footer-->
	</div>

	

	<!-- Classie -->
	<!-- for toggle left push menu script -->
	<script src="js/classie.js"></script>
	<script>
			var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
				showLeftPush = document.getElementById( 'showLeftPush' ),
				body = document.body;
				
			showLeftPush.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( body, 'cbp-spmenu-push-toright' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
				disableOther( 'showLeftPush' );
			};
			

			function disableOther( button ) {
				if( button !== 'showLeftPush' ) {
					classie.toggle( showLeftPush, 'disabled' );
				}
			}
		</script>
	<!-- //Classie -->
	<!-- //for toggle left push menu script -->

	<!--scrolling js-->
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/scripts.js"></script>
	<!--//scrolling js-->

	<!-- side nav js -->
	<script src='js/SidebarNav.min.js' type='text/javascript'></script>
	<script>
      $('.sidebar-menu').SidebarNav()
    </script>
	<!-- //side nav js -->

	<!-- for index page weekly sales java script -->
	
	
	<!-- //for index page weekly sales java script -->


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.js"> </script>
	<!-- //Bootstrap Core JavaScript -->

</body>
</html>