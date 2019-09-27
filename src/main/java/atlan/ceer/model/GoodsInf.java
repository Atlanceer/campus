package atlan.ceer.model;

import java.util.Date;
import java.util.List;

/**
 * 商品详情模型
 */
public class GoodsInf {
    private String goodsid;

    private String userid;

    private String goodsname;

    private String goodsinf;

    private String username;

    private String avatar;

    private Double price;

    private String isnew;

    private String location;

    private String category;

    private List images;

    private String mainimage;

    private Integer collect;

    private Integer browse;

    private Date createtime;

    private Integer status;

    private String tag;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoodsinf() {
        return goodsinf;
    }

    public void setGoodsinf(String goodsinf) {
        this.goodsinf = goodsinf;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        this.isnew = isnew;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
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
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "GoodsInf{" +
                "goodsid='" + goodsid + '\'' +
                ", userid='" + userid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodsinf='" + goodsinf + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", price=" + price +
                ", isnew='" + isnew + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", images=" + images +
                ", mainimage='" + mainimage + '\'' +
                ", collect=" + collect +
                ", browse=" + browse +
                ", createtime=" + createtime +
                ", status=" + status +
                ", tag='" + tag + '\'' +
                '}';
    }
}
