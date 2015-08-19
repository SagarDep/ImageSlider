package me.zsj.imageslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class TransformerAdapter extends BaseAdapter {

    private List<String> mTransLists;
    private LayoutInflater mInflater;

    public TransformerAdapter(Context context, List<String> transLists) {

        mInflater = LayoutInflater.from(context);
        this.mTransLists = transLists;
    }


    @Override
    public int getCount() {
        return mTransLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mTransLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mTransLists.get(position));

        return convertView;
    }

    private final class ViewHolder {

        private TextView mTextView;
    }
}
