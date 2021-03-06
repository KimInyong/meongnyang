package com.mall.meongnyang.client.shopping.dao;

import com.mall.meongnyang.admin.product.vo.AdminProductImageVO;
import com.mall.meongnyang.admin.product.vo.AdminProductSaleVO;
import com.mall.meongnyang.admin.product.vo.AdminProductVO;
import com.mall.meongnyang.client.shopping.vo.ClientOrderDetailVO;
import com.mall.meongnyang.client.shopping.vo.ClientOrderVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClientShoppingDAO")
public class ClientShoppingDAOImpl implements ClientShoppingDAO
{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Override
    public int selectShoppingProductCount(AdminProductVO adminProductVO)
    {
        return sqlSessionTemplate.selectOne("ProductDAO.selectProductCnt", adminProductVO);
    }

    @Override
    public List<AdminProductVO> selectShoppingList(AdminProductVO adminProductVO)
    {
        return sqlSessionTemplate.selectList("ProductDAO.selectShoppingList", adminProductVO);
    }

    @Override
    public AdminProductVO selectProductDetail(AdminProductVO adminProductVO)
    {
        return sqlSessionTemplate.selectOne("ProductDAO.selectProductDetail",adminProductVO);
    }

    @Override
    public List<AdminProductImageVO> selectProductImages(AdminProductImageVO adminProductImageVO)
    {
        return sqlSessionTemplate.selectList("ProductImageDAO.selectProductImages", adminProductImageVO);
    }

    @Override
    public List<AdminProductSaleVO> selectSingleProductSaleList(AdminProductSaleVO adminProductSaleVO)
    {
        return sqlSessionTemplate.selectList("ProductSaleDAO.selectSingleProductSaleList", adminProductSaleVO);
    }

    @Override
    public int updateProductRemainCount(ClientOrderDetailVO clientOrderDetailVO)
    {
        return sqlSessionTemplate.update("ProductSaleDAO.updateProductRemainCount", clientOrderDetailVO);
    }

    @Override
    public void updateProductStateWhenZero(ClientOrderVO clientOrderVO)
    {
        sqlSessionTemplate.update("ProductSaleDAO.updateSaleStateWhenZero", clientOrderVO);
    }

    @Override
    public void updateRollbackProductRemainCount(ClientOrderDetailVO clientOrderDetailVO)
    {
        sqlSessionTemplate.update("ProductSaleDAO.updateRollbackProductRemainCount", clientOrderDetailVO);
    }

    @Override
    public void updateProductSaleReadCount(AdminProductVO adminProductVO)
    {
        sqlSessionTemplate.update("ProductSaleDAO.updateProductSaleReadCount", adminProductVO);
    }

    @Override
    public void updatePromotionEndProductSale(AdminProductSaleVO adminProductSaleVO)
    {
        sqlSessionTemplate.update("ProductSaleDAO.updatePromotionEndProductSale", adminProductSaleVO);
    }

    @Override
    public void updateExpireDayOverProductSale(AdminProductSaleVO adminProductSaleVO)
    {
        sqlSessionTemplate.update("ProductSaleDAO.updateExpireDayOverProductSale", adminProductSaleVO);
    }
}
