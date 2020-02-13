package com.mall.meongnyang.admin.product.dao;

import com.mall.meongnyang.admin.product.vo.AdminProductSaleVO;

public interface AdminProductSaleDAO
{
    public void insertProductSale(AdminProductSaleVO adminProductSaleVO);
    public void updateProductSaleState(AdminProductSaleVO adminProductSaleVO);
    public AdminProductSaleVO selectProductSale(AdminProductSaleVO adminProductSaleVO);
    public void updateProductSale(AdminProductSaleVO adminProductSaleVO);
}
