package app.wen.com.okhttpdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.wen.com.okhttpdemo.R;
import app.wen.com.okhttpdemo.bean.PhotoBean;
import app.wen.com.okhttpdemo.utils.ImageLoader;

/**
 * Created by wangenning on 16/1/8.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Activity activity;
    private List<PhotoBean.ResultsEntity> data;

    public ImageAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                activity).inflate(R.layout.image_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        holder.text_item.setText(data.get(position).getUpdatedAt());
        new ImageLoader(activity).loadImage(data.get(position).getUrl(),holder.image_item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setdata(List<PhotoBean.ResultsEntity> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_item;
        TextView text_item;

        public ViewHolder(View view) {
            super(view);
            image_item = (ImageView) view.findViewById(R.id.image_item);
            text_item = (TextView) view.findViewById(R.id.tv_item);

        }
    }
}
