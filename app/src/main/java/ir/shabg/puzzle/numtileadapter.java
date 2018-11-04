package ir.shabg.puzzle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class numtileadapter extends BaseAdapter {
    private final Context mContext;
    private final int[] nums;

    // 1
    public numtileadapter(Context context, int[] numbers) {
        this.mContext = context;
        this.nums = numbers;
    }

    // 2
    @Override
    public int getCount() {
        return nums.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return nums[position];
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linear_tiles, null);
        }

        // 3
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        // 4
        switch (this.nums[position]) {
            case 1:
                imageView.setImageResource(R.drawable.n1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.n2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.n3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.n4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.n5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.n6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.n7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.n8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.n9);
                break;
        }

        return convertView;
    }

}
