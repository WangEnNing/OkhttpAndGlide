package app.wen.com.okhttpdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenning on 16/1/8.
 */
public class PhotoBean implements Parcelable {
    /**
     * error : false
     * results : [{"who":"张涵宇","publishedAt":"2016-01-08T05:44:42.715Z","desc":"1.8","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1ezrtpmdv45j20u00spahy.jpg","used":true,"objectId":"568f0e7260b2a0990f68677b","createdAt":"2016-01-08T01:18:42.681Z","updatedAt":"2016-01-08T05:44:44.113Z"},{"who":"张涵宇","publishedAt":"2016-01-07T03:36:25.265Z","desc":"1.7","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1ezqon28qrzj20h80pamze.jpg","used":true,"objectId":"568dc1bb60b25396c50ca1c5","createdAt":"2016-01-07T01:39:07.601Z","updatedAt":"2016-01-07T03:36:26.800Z"},{"who":"张涵宇","publishedAt":"2016-01-06T05:04:37.020Z","desc":"1.6","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1ezplg7s8mdj20xc0m8jwf.jpg","used":true,"objectId":"568c83a100b09aa2f90554c6","createdAt":"2016-01-06T03:01:53.814Z","updatedAt":"2016-01-06T05:04:37.637Z"},{"who":"张涵宇","publishedAt":"2016-01-05T05:47:06.972Z","desc":"1.5","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezodh37eadj20n90qotfr.jpg","used":true,"objectId":"568b1f0160b2c297ddfafb64","createdAt":"2016-01-05T01:40:17.832Z","updatedAt":"2016-01-05T05:47:08.747Z"},{"who":"张涵宇","publishedAt":"2016-01-04T04:24:04.641Z","desc":"1.4","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1ezn79ievhzj20p00odwhr.jpg","used":true,"objectId":"5689c96760b2e57ba2c037c0","createdAt":"2016-01-04T01:22:47.291Z","updatedAt":"2016-01-04T04:24:05.294Z"},{"who":"张涵宇","publishedAt":"2015-12-31T04:29:30.902Z","desc":"12.31--2015最后一天福利满满","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezil3n0cqdj20p00ou776.jpg","used":true,"objectId":"5684856800b009a31af4d3bf","createdAt":"2015-12-31T01:31:20.602Z","updatedAt":"2015-12-31T04:29:31.714Z"},{"who":"张涵宇","publishedAt":"2015-12-30T04:14:13.083Z","desc":"12.30","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1ezhh5rh1r9j20hs0qoadi.jpg","used":true,"objectId":"5683416800b009a31aee069a","createdAt":"2015-12-30T02:28:56.847Z","updatedAt":"2015-12-30T04:14:13.598Z"},{"who":"张涵宇","publishedAt":"2015-12-29T05:04:33.048Z","desc":"12.29","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezgal5vpjfj20go0p0adq.jpg","used":true,"objectId":"5681e82e00b0f9a15fcb6d1c","createdAt":"2015-12-29T01:55:58.728Z","updatedAt":"2015-12-29T05:04:34.467Z"},{"who":"张涵宇","publishedAt":"2015-12-28T04:17:42.954Z","desc":"12.28","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1ezf3wrxcx2j20p011i7b2.jpg","used":true,"objectId":"56808e1700b0cff56c49d1ec","createdAt":"2015-12-28T01:19:19.298Z","updatedAt":"2015-12-28T07:39:44.919Z"},{"who":"张涵宇","publishedAt":"2015-12-25T04:01:12.066Z","desc":"12.25","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1ezbriom623j20hs0qoadv.jpg","used":true,"objectId":"567cbdca60b2e1871df8d45b","createdAt":"2015-12-25T03:53:46.566Z","updatedAt":"2015-12-25T04:01:13.957Z"}]
     */

    private boolean error;
    /**
     * who : 张涵宇
     * publishedAt : 2016-01-08T05:44:42.715Z
     * desc : 1.8
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/7a8aed7bjw1ezrtpmdv45j20u00spahy.jpg
     * used : true
     * objectId : 568f0e7260b2a0990f68677b
     * createdAt : 2016-01-08T01:18:42.681Z
     * updatedAt : 2016-01-08T05:44:44.113Z
     */

    private List<ResultsEntity> results;

    public  PhotoBean objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, PhotoBean.class);
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        private String who;
        private String publishedAt;
        private String desc;
        private String type;
        private String url;
        private boolean used;
        private String objectId;
        private String createdAt;
        private String updatedAt;

        public static ResultsEntity objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, ResultsEntity.class);
        }

        public void setWho(String who) {
            this.who = who;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getWho() {
            return who;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isUsed() {
            return used;
        }

        public String getObjectId() {
            return objectId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public PhotoBean() {
    }

    protected PhotoBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsEntity>();
        in.readList(this.results, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<PhotoBean> CREATOR = new Parcelable.Creator<PhotoBean>() {
        public PhotoBean createFromParcel(Parcel source) {
            return new PhotoBean(source);
        }

        public PhotoBean[] newArray(int size) {
            return new PhotoBean[size];
        }
    };
}
