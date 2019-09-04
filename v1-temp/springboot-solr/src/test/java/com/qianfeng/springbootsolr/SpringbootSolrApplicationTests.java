package com.qianfeng.springbootsolr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSolrApplicationTests {
    @Autowired
    private SolrClient solrClient;
    @Test
    public void contextLoads() throws IOException, SolrServerException {
        //添加对象
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id","123");
        document.setField("product_name","mate40");
        document.setField("product_price","8888");
        document.setField("product_sale_point","麒麟980");
        document.setField("product_images","999");
        //添加
        solrClient.add(document);
        //提交
        solrClient.commit();
    }
    @Test
    public void queryTest() throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("product_name:mate30");

        QueryResponse response= solrClient.query(solrQuery);

        SolrDocumentList results=response.getResults();
        for (SolrDocument result:results){
            System.out.println(result.getFieldValue("product_name "));
        }

    }
@Test
    public void del() throws IOException, SolrServerException {
        solrClient.deleteByQuery("product_name:mate30");
        solrClient.commit();
}
}
