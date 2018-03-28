<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
			id="cbp-spmenu-s1">
			<!--left-fixed -navigation-->
			<aside class="sidebar-left">
				<nav class="navbar navbar-inverse">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target=".collapse"
							aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<h1>
							<a class="navbar-brand" href="index.html"><span
								class="fa fa-area-chart"></span>BKX<span
								class="dashboard_text"></span></a>
						</h1>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="sidebar-menu">
							<li class="header">MAIN NAVIGATION</li>
							<li class="treeview"><a href="index.html"> <i
									class="fa fa-dashboard"></i> <span>Thống kê</span>
							</a></li>
							
							<li class="treeview">
								<a href="#"> <i class="fa fa-laptop"></i> <span>Thông báo</span> 
									<i class="fa fa-angle-left pull-right"></i>
								</a>
						<ul class="treeview-menu">
							<li><a href="${pageContext.request.contextPath}/thongbao"><i class="fa fa-angle-right"></i> Thông báo</a></li>
							<li><a href="${pageContext.request.contextPath}/baocaotinhtrangcay"><i class="fa fa-angle-right"></i> Báo cáo tình trạng cây</a></li>
							<li><a href="${pageContext.request.contextPath}/baocaotinhtrangdcn"><i class="fa fa-angle-right"></i> Báo cáo tình trạng điểm cấp nước</a></li>
							

						</ul></li>
							
							<li class="treeview">
								<a href="#"> <i class="fa fa-laptop"></i> <span>Quản lý</span> 
									<i class="fa fa-angle-left pull-right"></i>
								</a>
						<ul class="treeview-menu">
							<li><a href="${pageContext.request.contextPath}/cay"><i class="fa fa-angle-right"></i> Cây</a></li>
							<li><a href="${pageContext.request.contextPath}/loaicay"><i class="fa fa-angle-right"></i> Loại Cây</a></li>
							<li><a href="${pageContext.request.contextPath}/diemcapnuoc"><i class="fa fa-angle-right"></i> Điểm cấp nước</a></li>
							<li><a href="${pageContext.request.contextPath}/thanhvien"><i class="fa fa-angle-right"></i> Thành Viên</a></li>
							<li><a href="${pageContext.request.contextPath}/loaithanhvien"><i class="fa fa-angle-right"></i> Loại thành viên</a></li>
							<li><a href="${pageContext.request.contextPath}/lichsutuoi"><i class="fa fa-angle-right"></i> Lịch sử tưới</a></li>

						</ul></li>
							<li class="treeview"><a href="charts.html"> <i
									class="fa fa-pie-chart"></i> <span>Charts</span> <span
									class="label label-primary pull-right">new</span>
							</a></li>
							<li class="treeview">
							<li class="treeview"><a href="#"> <i
									class="fa fa-laptop"></i> <span>UI Elements</span> <i
									class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="general.html"><i
											class="fa fa-angle-right"></i> General</a></li>
									<li><a href="icons.html"><i class="fa fa-angle-right"></i>
											Icons</a></li>
									<li><a href="buttons.html"><i
											class="fa fa-angle-right"></i> Buttons</a></li>
									<li><a href="typography.html"><i
											class="fa fa-angle-right"></i> Typography</a></li>
								</ul></li>
							<li><a href="widgets.html"> <i class="fa fa-th"></i> <span>Widgets</span>
									<small class="label pull-right label-info">08</small>
							</a></li>
							<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
									<span>Forms</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="forms.html"><i class="fa fa-angle-right"></i>
											General Forms</a></li>
									<li><a href="validation.html"><i
											class="fa fa-angle-right"></i> Form Validations</a></li>
								</ul></li>
							<li class="treeview"><a href="#"> <i class="fa fa-table"></i>
									<span>Tables</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="tables.html"><i class="fa fa-angle-right"></i>
											Simple tables</a></li>
								</ul></li>
							<li class="treeview"><a href="#"> <i
									class="fa fa-envelope"></i> <span>Mailbox </span> <i
									class="fa fa-angle-left pull-right"></i><small
									class="label pull-right label-info1">08</small><span
									class="label label-primary1 pull-right">02</span></a>
								<ul class="treeview-menu">
									<li><a href="inbox.html"><i class="fa fa-angle-right"></i>
											Mail Inbox </a></li>
									<li><a href="compose.html"><i
											class="fa fa-angle-right"></i> Compose Mail </a></li>
								</ul></li>
							<li class="treeview"><a href="#"> <i
									class="fa fa-folder"></i> <span>Examples</span> <i
									class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="login.html"><i class="fa fa-angle-right"></i>
											Login</a></li>
									<li><a href="signup.html"><i class="fa fa-angle-right"></i>
											Register</a></li>
									<li><a href="404.html"><i class="fa fa-angle-right"></i>
											404 Error</a></li>
									<li><a href="500.html"><i class="fa fa-angle-right"></i>
											500 Error</a></li>
									<li><a href="blank-page.html"><i
											class="fa fa-angle-right"></i> Blank Page</a></li>
								</ul></li>
							<li class="header">LABELS</li>
							<li><a href="#"><i class="fa fa-angle-right text-red"></i>
									<span>Important</span></a></li>
							<li><a href="#"><i class="fa fa-angle-right text-yellow"></i>
									<span>Warning</span></a></li>
							<li><a href="#"><i class="fa fa-angle-right text-aqua"></i>
									<span>Information</span></a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</nav>
			</aside>
		</div>