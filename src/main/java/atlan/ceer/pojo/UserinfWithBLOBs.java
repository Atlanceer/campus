package atlan.ceer.pojo;

public class UserinfWithBLOBs extends Userinf {
    private String collect;

    private String publishgoods;

    private String publishneeds;

    private String record;

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
}