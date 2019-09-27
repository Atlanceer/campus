package atlan.ceer.model;

import java.util.Date;
import java.util.List;

/**
 * 需求模板
 */
public class NeedsInf {

    private String needsid;

    private String userid;

    private String username;

    private StringBuilder avatar=new StringBuilder("http://149.129.69.102:8080");

    private String goodsname;

    private String information;

    private Double price;

    private Double tip;

    private String location;

    private Integer collect;

    private Integer browse;

    private Date createtime;

    private Integer status;

    public String getNeedsid() {
        return needsid;
    }

    public void setNeedsid(String needsid) {
        this.needsid = needsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar.toString();
    }

    public void setAvatar(String avatar) {
        this.avatar.append(avatar);
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
