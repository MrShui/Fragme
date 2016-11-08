package com.shuiyouwen.fragme.network.bean.resp;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class LoadingResp {
    private String guide_page_id;
    private String image;
    private String updated_at;

    public String getGuide_page_id() {
        return guide_page_id;
    }

    public void setGuide_page_id(String guide_page_id) {
        this.guide_page_id = guide_page_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "LoadingResp{" +
                "guide_page_id='" + guide_page_id + '\'' +
                ", image='" + image + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
