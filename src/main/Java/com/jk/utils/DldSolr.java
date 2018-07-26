/**
 * <pre>项目名称:test-2 
 * 文件名称:Solr.java 
 * 包名:com.jk.utils 
 * 创建日期:2018年7月17日下午2:56:09 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */
package com.jk.utils;

/**
 * <pre>项目名称：test-2    
 * 类名称：Solr    
 * 类描述：    
 * 创建人：董良得 
 * 创建时间：2018年7月17日 下午2:56:09    
 * 修改人：董良得
 * 修改时间：2018年7月17日 下午2:56:09    
 * 修改备注：       
 * @version </pre>    
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.jk.model.Dldshop;
/**
 * API简单操作
 * @author Administrator
 *
 */
public class DldSolr {
    // solr 部署的url
    private static final String url = "http://localhost:8084/solr";
    // home
    private static final String uri = "my_core";

   //条件查询
    public static  List<Dldshop> selectDoc(String tile, Integer page, Integer rows, Integer price,String type,String barand) throws SolrServerException, IOException{
        SolrClient sc = getSolrClient();
        SolrQuery query = new SolrQuery();
        //设置分页
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        if(price!=null){
        if(price==0){
            SortClause sort=new SortClause("item_price", ORDER.desc);
            query.setSort(sort);//可以添加集合
        }else if (price==1){
            SortClause sort=new SortClause("item_price", ORDER.asc);
            query.setSort(sort);//可以添加集合
        }
        }
        //查询关键字
        if (tile.length() > 0) {
            query.set("q", "item_title:" + tile);
            //是否高亮
            query.setHighlight(true);
            query.addHighlightField("item_title");
            query.setHighlightSimplePre("<font color='red'>");
            query.setHighlightSimplePost("</font>");
        }
        else if (type!=null&&type!=""){
                query.set("q", "item_type:" + type);
        }
        else if (barand!=null&&barand!=""){
            query.set("q", "item_brand:" + barand);
        }
        else {
            query.set("q", "*:*");
        }

        SolrDocumentList solrDocumentList = null;
        long count = 0;
        //查询
        QueryResponse response = sc.query(query);
        //返回索引文本集合
        solrDocumentList = response.getResults();
        //获取solr索引数据的总条数
        count = solrDocumentList.getNumFound();
        // 高亮显示的返回结果
        Map<String, Map<String, List<String>>> maplist = response.getHighlighting();
        List<Dldshop> shopList = new ArrayList<Dldshop>();
        for (SolrDocument solrDocument : solrDocumentList) {
            String id = solrDocument.getFirstValue("id").toString();
            String item_name = solrDocument.getFirstValue("item_title").toString();
            String item_price = solrDocument.getFirstValue("item_price").toString();

            if (maplist != null) {
                Map<String, List<String>> fieldMap = maplist.get(solrDocument.get("id"));
                List<String> nameList = fieldMap.get("item_title");
                List<String> showList = fieldMap.get("item_price");
                if (nameList != null && nameList.size() > 0) {
                    item_name = nameList.get(0);
                }
                if (showList != null && showList.size() > 0) {
                    item_price = showList.get(0);
                }
            }
            Dldshop fz = new Dldshop();
            fz.setId(Integer.parseInt(id));
            fz.setPrice(item_price);
            fz.setFullname(item_name);
            fz.setTile(solrDocument.get("item_title").toString());
            fz.setImage(solrDocument.get("item_image").toString());
            fz.setCreatedate(solrDocument.get("item_createdate").toString());
            fz.setPrice(solrDocument.get("item_price").toString());
            fz.setMarketprice(solrDocument.get("item_marketprice").toString());
            fz.setSales(solrDocument.get("item_sales").toString());
            fz.setScore(solrDocument.get("item_score").toString());
            fz.setName(solrDocument.get("item_brand").toString());
            if(solrDocument.get("item_unit")==null){
                fz.setUnit("件");
            }else{
                fz.setUnit(solrDocument.get("item_unit").toString());
            }
            fz.setText(solrDocument.get("item_type").toString());
            fz.setPage(page);
            fz.setRows(rows);
            fz.setCount(count);
            shopList.add(fz);
        }
return shopList;
    }
    public static void main(String[] args) throws SolrServerException, IOException {
        System.out.println("测试开始：===================");
        //List<Shop> selectDoc = selectDoc(ss);
       // System.out.println(selectDoc);
       deleteAllDoc();
        //insert();
        //String tt="T";
        //selectDoc(tt);
    }
    // 删除全部数据
    private static void deleteAllDoc() throws SolrServerException, IOException {
        // 得到请求
        SolrClient sc = getSolrClient();
        sc.deleteByQuery("*:*");
        sc.commit();
    }
    /**
     * @return
     */
    public static SolrClient getSolrClient() {
        return new HttpSolrClient(url + "/" + uri);
    }

    // 添加一条数据
    public static void addDoc(List<Dldshop> list) throws SolrServerException, IOException {

      for (Dldshop shop : list) {
          SolrClient sc = getSolrClient();
          SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", shop.getId());
            doc.addField("item_title", shop.getFullname());
            doc.addField("item_brand", shop.getName());
            doc.addField("item_image", shop.getImage());
            doc.addField("item_price", shop.getPrice());
            doc.addField("item_unit", shop.getUnit());
            doc.addField("item_marketprice", shop.getMarketprice());
            doc.addField("item_sales", shop.getSales());
            doc.addField("item_createdate", shop.getCreatedate());
            doc.addField("item_score", shop.getScore());
            doc.addField("item_type",shop.getText());
            sc.add(doc);
            sc.commit();
        }


    }
    public static void insert()throws SolrServerException, IOException{
        SolrClient sc = getSolrClient();
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "999");
        doc.addField("item_title", "ads");
        doc.addField("item_brand", "999");
        doc.addField("item_image","999");
        doc.addField("item_price", "999.2");
        doc.addField("item_unit", "999");
        doc.addField("item_marketprice", "1243.0");
        doc.addField("item_sales", "50");
        doc.addField("item_createdate", "2018-07-05 12:07:28");
        doc.addField("item_score","adsa");
        doc.addField("item_type","sdas");
        sc.add(doc);
        sc.commit();
    }


}