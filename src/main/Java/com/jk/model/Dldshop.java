package com.jk.model;

import java.io.Serializable;

public class Dldshop implements Serializable {
    private static final long serialVersionUID = -3853097450295556668L;
    private Integer id;
    private String fullname;
    private String price;
    private String unit;
    private String marketprice;
    private String name;
    private  String image;
    private  String createdate;
    private  String score;
    private  String sales;

    private String text;
    /*业务字段启示页*/
    private  Integer page;
    /*业务字段每页条数*/
    private  Integer rows;

    private Long count;

    private String tile;
    /*商品id shopid
页面标题   seo_title
销量 sales
页面关键词 seo_keywords
页面描述 seo_description
评分 score
周销量  week_sales
品牌  brand
*/

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dldshop dldshop = (Dldshop) o;

        return id != null ? id.equals(dldshop.id) : dldshop.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Dldshop{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", price='" + price + '\'' +
                ", unit='" + unit + '\'' +
                ", sales='" + sales + '\'' +
                ", marketprice='" + marketprice + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", createdate='" + createdate + '\'' +
                ", score='" + score + '\'' +
                ", sales='" + sales + '\'' +
                '}';
    }
}
