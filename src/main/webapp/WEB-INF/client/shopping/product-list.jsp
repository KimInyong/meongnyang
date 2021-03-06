<%--
  Created by IntelliJ IDEA.
  User: stage
  Date: 2020-02-09
  Time: 오후 6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<!-- Start Bradcaump area -->
<div class="ht__bradcaump__area" style="background: rgba(0, 0, 0, 0) url(/resources/client/images/bg/product-list.jpg) no-repeat scroll center center / cover ;">
    <div class="ht__bradcaump__wrap">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="bradcaump__inner">
                        <nav class="bradcaump-inner">
                            <a class="breadcrumb-item" href="index.do">Home</a>
                            <span class="brd-separetor"><i class="zmdi zmdi-chevron-right"></i></span>
                            <span class="breadcrumb-item active">쇼핑</span>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Bradcaump area -->
<!-- Start Product Grid -->
<section class="htc__product__grid bg__white ptb--100">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 col-lg-push-3 col-md-9 col-md-push-3 col-sm-12 col-xs-12">
                <div class="htc__product__rightidebar">
                    <div class="htc__grid__top">
                        <div class="htc__select__option">
                            <select class="ht__select" name="orderMethod" id="orderMethod">
                                <option value="none">정렬 방식</option>
                                <option value="popularity">인기순</option>
                                <option value="recent">최신순</option>
                            </select>

                        </div>
                        <div class="ht__pro__qun">
                            <span>pages ${pageInfo.startBlock}-${pageInfo.allBlockCnt} of ${pageInfo.pageCnt}</span>
                        </div>
                        <!-- Start List And Grid View -->
                        <ul class="view__mode" role="tablist">
                            <li role="presentation" class="grid-view active"><a href="#grid-view" role="tab" data-toggle="tab"><i class="zmdi zmdi-grid"></i></a></li>
                            <li role="presentation" class="list-view"><a href="#list-view" role="tab" data-toggle="tab"><i class="zmdi zmdi-view-list"></i></a></li>
                        </ul>
                        <!-- End List And Grid View -->
                    </div>
                    <!-- Start Product View -->
                    <div class="row">
                        <div class="shop__grid__view__wrap">
                            <div role="tabpanel" id="grid-view" class="single-grid-view tab-pane fade in active clearfix">
                                <!-- Start Single Product -->
                                <c:forEach var="product" items="${productList}">
                                    <div class="col-md-4 col-lg-4 col-sm-6 col-xs-12">
                                        <div class="category">
                                            <div class="ht__cat__thumb">
                                                <a href="shoppingDetail.do?productTbCode=${product.productTbCode}">
                                                    <img src="${product.pdImageTbPath}" alt="product images" width="290" height="385">
                                                </a>
                                            </div>
                                            <div class="fr__hover__info">
                                                <ul class="product__action">
                                                   <c:if test="${sessionScope.customer.customerTbNo != null}">
                                                   	 	<li><a href="/wishlistInsert.do?pdSaleTbSize=${product.pdSaleTbSize}&productTbCode=${product.productTbCode}&pdImageTbPath=${product.pdImageTbPath}"><i class="icon-heart icons"></i></a></li>                                                   	 	
                                                   	</c:if>
                                                   	<c:if test="${sessionScope.customer.customerTbNo == null || sessionScope.customer.customerTbNo == '' || sessionScope.customer.customerTbNo eq '' || sessionScope.customer.customerTbNo eq null}">                                                   
                                                   	 	<li><a href="void(0);" onclick="alert('로그인한 회원만 이용할 수 있습니다.');return false;"><i class="icon-heart icons"></i></a></li>
                                                   	</c:if> 	
                                                   	 	<li><a onclick="addCart('${product.productTbCode}', '${product.pdSaleTbSize}')" style="cursor: pointer"><i class="icon-handbag icons"></i></a></li>
                                                </ul>
                                            </div>
                                            <div class="fr__product__inner">
                                                <h4><a href="shoppingDetail.do?productTbCode=${product.productTbCode}">${product.productTbName}</a></h4>
                                                <ul class="fr__pro__prize">
                                                    <li class="old__prize">${product.pdSaleTbSalesPrice}원</li>
                                                    <li><c:if test="${product.pdSaleTbDiscountRate != product.pdSaleTbSalesPrice}">${product.pdSaleTbDiscountRate}원</c:if></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- End Single Product -->
                            </div>
                            <div role="tabpanel" id="list-view" class="single-grid-view tab-pane fade clearfix">
                                <div class="col-xs-12">
                                    <div class="ht__list__wrap">
                                        <!-- Start List Product -->
                                        <c:forEach var="product" items="${productList}">
                                            <input type="hidden" id="psNo-${product.productTbCode}" value="${product.pdSaleTbNo}">
                                            <div class="ht__list__product">
                                                <div class="ht__list__thumb">
                                                    <a href="shoppingDetail.do?productTbCode=${product.productTbCode}"><img id="productImg-${product.productTbCode}" src="${product.pdImageTbPath}" alt="product images" width="290" height="385"></a>
                                                </div>
                                                <div class="htc__list__details">
                                                    <h2><a id="productName-${product.productTbCode}" href="shoppingDetail.do?productTbCode=${product.productTbCode}">${product.productTbName}</a></h2>
                                                    <ul  class="pro__prize">
                                                        <li id="nowPrice-${product.productTbCode}" class="old__prize">${product.pdSaleTbSalesPrice}원</li>
                                                        <li id="nowDiscount-${product.productTbCode}"><c:if test="${product.pdSaleTbDiscountRate != product.pdSaleTbSalesPrice}">${product.pdSaleTbDiscountRate}원</c:if></li>
                                                    </ul>
                                                    <h6>평점 : ${product.pdSaleTbRating}</h6>
                                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>                                                    
                                                    <div class="fr__list__btn">
                                                        <a class="fr__btn" onclick="addCart('${product.productTbCode}', '${product.pdSaleTbSize}')" style="cursor: pointer">카트 추가</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <!-- End List Product -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Product View -->
                </div>
                <!-- Start Pagenation -->
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="htc__pagenation">
                            <!-- /shopping.do?productCategoryTbNo=&minPrice=&maxPrice=&orderMethod=&searchWord=&nowPage= -->
                            <c:if test="${pageInfo.nowPage > 10}">
                                <li><a href="javascript:goToPage('${pageInfo.nowPage-1}')"><i class="zmdi zmdi-chevron-left"></i></a></li>
                            </c:if>
                            <c:forEach var="i" begin="${pageInfo.startBlock}" end="${pageInfo.endBlock}" step="1">
                                <c:if test="${i eq pageInfo.nowPage}">
                                    <li class="active"><a href="javascript:goToPage('${i}')">${i}</a></li>
                                </c:if>
                                <c:if test="${i ne pageInfo.nowPage}">
                                    <li><a href="javascript:goToPage('${i}')">${i}</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${pageInfo.endBlock < pageInfo.allBlockCnt}">
                                <li><a href="javascript:goToPage('${pageInfo.nowPage+1}')"><i class="zmdi zmdi-chevron-right"></i></a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- End Pagenation -->
            </div>
            <div class="col-lg-3 col-lg-pull-9 col-md-3 col-md-pull-9 col-sm-12 col-xs-12 smt-40 xmt-40">
                <div class="htc__product__leftsidebar">
                    <!-- Start Prize Range -->
                    <div class="htc-grid-range">
                        <h4 class="title__line--4">Price</h4>
                        <div class="content-shopby">
                            <div class="price_filter s-filter clear">
                                <form action="shopping.do" method="GET">
                                    <input type="hidden" id="minPrice" value="${pageInfo.minPrice}">
                                    <input type="hidden" id="maxPrice" value="${pageInfo.maxPrice}">
                                    <div id="slider-range"></div>
                                    <div class="slider__range--output">
                                        <div class="price__output--wrap">
                                            <div class="price--output">
                                                <input type="text" id="amount" readonly>
                                            </div>
                                            <div class="price--filter">
                                                <span onclick="priceFilter()">검색</span>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- End Prize Range -->
                    <!-- Start Category Area -->
                    <div class="htc__category">
                        <h4 class="title__line--4">${categoryList.get(0).productCategoryTbParent}-${categoryList.get(0).productCategoryTbMedian}</h4>
                        <ul class="ht__cat__list">
                            <c:forEach var="subCate" items="${categoryList}">
                                <li><a href="shopping.do?productCategoryTbNo=${subCate.productCategoryTbNo}">${subCate.productCategoryTbSub}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- End Category Area -->


                    <!-- End Tag Area -->


                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Product Grid -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#orderMethod").change(function () {
            var selectedValue = $("#orderMethod option:selected").val();
            console.log(selectedValue);

            var filterPrice = $("#amount").val();
            location.href="shopping.do?productCategoryTbNo=${nowCategory}&filterPrice="+filterPrice+"&orderMethod="+selectedValue;
        })
    });

    function goToPage(nowPage) {
        var selectedValue = $("#orderMethod option:selected").val();
        var filterPrice = $("#amount").val();
        var searchWord = '${searchWord}';
        if(searchWord === '')
        {
            location.href = '/shopping.do?productCategoryTbNo=${nowCategory}&filterPrice='+filterPrice+'&orderMethod='+selectedValue+'&nowPage='+nowPage;
        }
        else
        {
            location.href = '/shopping.do?productCategoryTbNo=${nowCategory}&filterPrice='+filterPrice+'&orderMethod='+selectedValue+'&searchWord='+searchWord+'&nowPage='+nowPage;
        }
    }

    function priceFilter() {
        var filterPrice = $("#amount").val();
        location.href="shopping.do?productCategoryTbNo=${nowCategory}&filterPrice="+filterPrice;
    }



</script>

<jsp:include page="../include/footer.jsp"/>