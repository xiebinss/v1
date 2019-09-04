package com.qianfeng.v1searchservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.qianfeng.api.ISearchService;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.entity.TProduct;
import com.qianfeng.v1.mapper.TProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService implements ISearchService {
    @Autowired
    private TProductMapper productMapper;
    @Autowired
    private SolrClient solrClient;
    @Override
    public ResultBean sysalldata() {
        List<TProduct> list=productMapper.getList();
        for(TProduct product:list){
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id",product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_price",product.getPrice());
        document.setField("product_sale_point",product.getSalePoint());
        document.setField("product_images",product.getImages());
        //添加
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean("500","同步失败");
            }
            //提交

            try {
                solrClient.commit();
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean("500","同步失败");
            }

        }
        return new ResultBean("200","同步完成");
    }

    @Override
    public ResultBean queryByKeyWords(String keywords) {
        SolrQuery query=new SolrQuery();

        if(!StringUtils.isAnyEmpty(keywords)){
            query.setQuery("product_name:"+keywords);
        }else{
            query.setQuery("product_name:mate30");
        }
        //添加高亮的效果--step1
        query.setHighlight(true);
        query.addHighlightField("product_name");
        //queryCondition.addHighlightField("other");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        try {
            QueryResponse response=solrClient.query(query);
            SolrDocumentList results=response.getResults();
            List<TProduct> target=new ArrayList<>(results.size());
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for(SolrDocument document:results){
                TProduct product=new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                //product.setName(document.getFieldValue("product_name").toString());
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setImages(document.getFieldValue("product_images").toString());
                //设置高亮信息
                Map<String, List<String>> idHighlight = highlighting.get(document.getFieldValue("id").toString());
                //
                List<String> productNameHighLight = idHighlight.get("product_name");
                //
                if(productNameHighLight != null){
                    product.setName(productNameHighLight.get(0));
                }else{
                    product.setName(document.getFieldValue("product_name").toString());
                }
                target.add(product);
            }
            return new ResultBean("200",target);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500","查询失败");
        }

    }

    @Override
    public ResultBean updateById(Long id) {
        TProduct product=productMapper.selectByPrimaryKey(id);
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id",product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_price",product.getPrice());
        document.setField("product_sale_point",product.getSalePoint());
        document.setField("product_images",product.getImages());
        //添加
        try {
            solrClient.add(document);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500","同步失败");
        }
        //提交

        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500","同步失败");
        }

        return new ResultBean("200","同步完成");
    }
}
