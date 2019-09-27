package atlan.ceer.model;

/**
 * 商品简易信息模型
 */
public class SimpleGoods {
    private String userid;
    private String username;
    private String goodsid;
    private String goodsname;
    //private StringBuilder avatar=new StringBuilder("http://117.50.55.202:8080");
    //香港服务器
    private StringBuilder avatar=new StringBuilder("http://149.129.69.102:8080");
    private Double price;
    private String isnew;
    //private String mainimage;
    //private StringBuilder mainimage=new StringBuilder("http://117.50.55.202:8080");
    //香港服务器
    private StringBuilder mainimage=new StringBuilder("http://149.129.69.102:8080");
    private Integer collect;
    private Integer browse;
    private String location;
    private String tag;

    @Override
    public String toString() {
        return "SimpleGoods{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", avatar=" + avatar +
                ", price=" + price +
                ", isnew='" + isnew + '\'' +
                ", mainimage=" + mainimage +
                ", collect=" + collect +
                ", browse=" + browse +
                ", location='" + location + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar.toString();
    }

    public void setAvatar(String avatar) {
        this.avatar.append(avatar);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
//private String headPath="http://117.50.55.202:8080";
    /*@Value("${headPath}")
    private String headPath;*/

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

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
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

    public String getMainimage() {
        //return mainimage;
        return mainimage.toString();
    }

    public void setMainimage(String mainimage) {
        //this.mainimage = mainimage;
        this.mainimage.append(mainimage);
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

    /*public String getHeadPath() {
        return headPath;
    }*/

    /*public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }*/

}
