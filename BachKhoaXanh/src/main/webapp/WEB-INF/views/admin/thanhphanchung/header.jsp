<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sticky-header header-section ">
	<div class="header-left">
		<button id="showLeftPush" class="" style="float: left;">
			<i class="fa fa-bars"></i>
		</button>
	</div>

	<div class="header-right">
		<div class="profile_details">
			<ul>
				<li class="dropdown profile_details_drop"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false">
						<div class="profile_img">
							<span class="prfil-img"><img src="images/lily_lutta.png"
								alt=""> </span>
							<div class="user-name">
								<p>Admin</p>
								<span>Administrator</span>
							</div>
							<i class="fa fa-angle-down lnr"></i> 
							<i class="fa fa-angle-up lnr"></i>
							<div class="clearfix"></div>
						</div>
				</a>
					<ul class="dropdown-menu drp-mnu">
						<li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
						<li><a href="#"><i class="fa fa-user"></i> My Account</a></li>
						<li><a href="#"><i class="fa fa-suitcase"></i> Profile</a></li>
						<li><a href="#"><i class="fa fa-sign-out"></i> Logout</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>

</div>