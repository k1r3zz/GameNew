package until;

public class ZMList {
    private String name;
    private int is_subject;//0为不是专题
    private String imageUel;
    private String data;
    private String arturl;
    private String summary;//内容
    private Boolean isViedo;//是否为视频

    public Boolean getIsViedo() {
        return isViedo;
    }

    public void setIsViedo(Boolean isViedo) {
        this.isViedo = isViedo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_subject() {
        return is_subject;
    }

    public void setIs_subject(int is_subject) {
        this.is_subject = is_subject;
    }

    public String getImageUel() {
        return imageUel;
    }

    public void setImageUel(String imageUel) {
        this.imageUel = imageUel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArturl() {
        return arturl;
    }

    public void setArturl(String arturl) {
        this.arturl = arturl;
    }

    @Override
    public String toString() {
        return "ZMList [name=" + name + ", is_subject=" + is_subject
                + ", imageUel=" + imageUel + ", data=" + data + ", arturl="
                + arturl + "]";
    }


}
