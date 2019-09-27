package atlan.ceer.model;

public class UserInfAll {
    private String userid;

    //private StringBuilder avatar=new StringBuilder("http://117.50.55.202:8080");
    //香港服务器
    private StringBuilder avatar=new StringBuilder("http://149.129.69.102:8080");

    private String signature;

    private String location;

    private String username;

    private String phone;

    private String email;

    private String school;

    private String birthday;

    private String sex;

    private String collect;

    private String publishgoods;

    private String publishneeds;

    private String record;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAvatar() {
        return avatar.toString();
    }

    public void setAvatar(String avatar) {
        this.avatar.append(avatar);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /*public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getPublishgoods() {
        return publishgoods;
    }

    public void setPublishgoods(String publishgoods) {
        this.publishgoods = publishgoods;
    }

    public String getPublishneeds() {
        return publishneeds;
    }

    public void setPublishneeds(String publishneeds) {
        this.publishneeds = publishneeds;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }*/
    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect == null ? null : collect.trim();
    }

    public String getPublishgoods() {
        return publishgoods;
    }

    public void setPublishgoods(String publishgoods) {
        this.publishgoods = publishgoods == null ? null : publishgoods.trim();
    }

    public String getPublishneeds() {
        return publishneeds;
    }

    public void setPublishneeds(String publishneeds) {
        this.publishneeds = publishneeds == null ? null : publishneeds.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    @Override
    public String toString() {
        return "UserInfAll{" +
                "userid='" + userid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", location='" + location + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", collect='" + collect + '\'' +
                ", publishgoods='" + publishgoods + '\'' +
                ", publishneeds='" + publishneeds + '\'' +
                ", record='" + record + '\'' +
                '}';
    }
}
