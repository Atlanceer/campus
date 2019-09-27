package atlan.ceer.pojo;

import java.util.Date;

public class Goodses {
    private String goodsid;

    private String userid;

    private String goodsname;

    private Double price;

    private String isnew;

    private String location;

    private String category;

    private String images;

    private String mainimage;

    private String goodsdes;

    private String address;

    private Integer collect;

    private Integer browse;

    private Date createtime;

    private Integer status;

    private String tag;

    private Integer lever;

    private String goodsinf;

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew == null ? null : isnew.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage == null ? null : mainimage.trim();
    }

    public String getGoodsdes() {
        return goodsdes;
    }

    public void setGoodsdes(String goodsdes) {
        this.goodsdes = goodsdes == null ? null : goodsdes.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
    }

    public String getGoodsinf() {
        return goodsinf;
    }

    public void setGoodsinf(String goodsinf) {
        this.goodsinf = goodsinf == null ? null : goodsinf.trim();
    }

    @Override
    public String toString() {
        return "Goodses{" +
                "goodsid='" + goodsid + '\'' +
                ", userid='" + userid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", price=" + price +
                ", isnew='" + isnew + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", images='" + images + '\'' +
                ", mainimage='" + mainimage + '\'' +
                ", goodsdes='" + goodsdes + '\'' +
                ", address='" + address + '\'' +
                ", collect=" + collect +
                ", browse=" + browse +
                ", createtime=" + createtime +
                ", status=" + status +
                ", tag='" + tag + '\'' +
                ", lever=" + lever +
                ", goodsinf='" + goodsinf + '\'' +
                '}';
    }
}