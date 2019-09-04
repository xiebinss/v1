package com.qianfeng.v1productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.common.base.BaseServiceImpl;
import com.qianfeng.common.base.IBaseDao;
import com.qianfeng.v1.api.IProductService;
import com.qianfeng.v1.api.vo.ProductVo;
import com.qianfeng.v1.entity.TProduct;
import com.qianfeng.v1.entity.TProductDesc;
import com.qianfeng.v1.mapper.TProductDescMapper;
import com.qianfeng.v1.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductService extends BaseServiceImpl<TProduct> implements IProductService {
@Autowired
private TProductMapper productMapper;
@Autowired
private TProductDescMapper productDescMapper;
    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }

    @Override
    public List<TProduct> gettList() {
        return productMapper.getList();
    }

    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        PageInfo pageInfo=new PageInfo<TProduct>(productMapper.getList(),4);
        return pageInfo;
    }

    @Override
    public Long add(ProductVo vo) {
        TProduct product=vo.getProduct();
        productMapper.insertSelective(product);
        TProductDesc productDesc=new TProductDesc();
        productDesc.setProductDesc(vo.getProductDesc());
        productDesc.setProductId(product.getId());
        productDescMapper.insertSelective(productDesc);
        return product.getId();
    }

    @Override
    public ProductVo getUserById(Long id) {
        ProductVo productVo=new ProductVo();
        TProduct product=productMapper.selectByPrimaryKey(id);
        productVo.setProduct(product);
        TProductDesc productDesc=productDescMapper.selextByProductId(id);
        productVo.setProductDesc(productDesc.getProductDesc());

        return productVo;
    }

    @Override
    public int deleteById(Long id) {
        return productMapper.delById(id);
    }

    @Override
    public Long batchDel(List<Long> ids) {
        return productMapper.batchDel(ids);
    }


}


