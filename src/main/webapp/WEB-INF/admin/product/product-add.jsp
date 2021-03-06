<%--
  Created by IntelliJ IDEA.
  User: stage
  Date: 2020-02-09
  Time: 오후 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kor">
<jsp:include page="../include/head.jsp"/>
<body>

<!-- Page Container -->
<div class="page-container">
    <!-- Page Sidebar -->
    <jsp:include page="../include/page-sidebar.jsp"/>
    <!-- /Page Sidebar -->

    <!-- Page Content -->
    <div class="page-content">
        <!-- Page Header -->
        <jsp:include page="../include/page-header.jsp"/>
        <!-- /Page Header -->
        <!-- Page Inner -->
        <div class="page-inner">
            <div class="page-title">
                <h3 class="breadcrumb-header">상품 추가</h3>
            </div>
            <!-- Main Wrapper -->
            <div id="main-wrapper">
                <!-- Row -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-white">
                            <div class="panel-body">
                                <form class="form-horizontal" action="productAdd.ado" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">카테고리</label>
                                        <div class="col-sm-10">
                                            <select style="margin-bottom:15px;" class="form-control" name="productCategoryTbNo">
                                                <c:forEach var="category" items="${categoryList}">
                                                    <option value="${category.productCategoryTbNo}">${category.productCategoryTbParent}-${category.productCategoryTbMedian}-${category.productCategoryTbSub}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="productTbName" class="col-sm-2 control-label">상품명</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="productTbName" name="productTbName" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="uploadFile" class="col-sm-2 control-label">상품상세</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="file" id="uploadFile" name="uploadFile" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2"></div>
                                    <div class="col-md-10">
                                        <button class="btn btn-primary">상품등록</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- !Row -->
            </div>
            <!-- !Main Wrapper -->
            <jsp:include page="../include/page-footer.jsp"/>
        </div>
        <!-- /Page Inner -->
        <jsp:include page="../include/right-sidebar.jsp"/>
    </div>
    <!-- /Page Content -->
</div>
<!-- /Page Container -->


<!-- Javascripts -->
<jsp:include page="../include/scripts-load.jsp"/>

</body>
</html>
