<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="main-sidebar">
  <!-- Inner sidebar -->
  <div class="sidebar">
    <!-- user panel (Optional) -->
    <div class="user-panel">
      <div class="pull-left image">
        <img src="${pageContext.request.contextPath}/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
      </div>
      <div class="pull-left info">
        <p>userName</p>

        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
      </div>
    </div><!-- /.user-panel -->

    <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
          <!-- /.search form -->

    <!-- Sidebar Menu -->
    <ul class="sidebar-menu">
      <li class="header">HEADER</li>
      <!-- Optionally, you can add icons to the links -->
      <li class="active"><a href="#"><span>Link</span></a><</li>
      <li><a href="#"><span>Another Link</span></a></li>
      <li class="treeview">
        <a href="#"><span>Yelp功能</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
          <li><a href="${pageContext.request.contextPath}/yelpForm.action">Yelp查詢評價</a></li>
        </ul>
      </li>
       <li class="treeview">
        <a href="#"><span>youtube功能</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
          <li><a href="${pageContext.request.contextPath}/youtubeQueryForm.action">查詢youtube</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#"><span>商品功能</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
          <li><a href="${pageContext.request.contextPath}/listItem.action">查詢商品</a></li>
        </ul>
      </li>
    </ul><!-- /.sidebar-menu -->

  </div><!-- /.sidebar -->
</div><!-- /.main-sidebar -->

