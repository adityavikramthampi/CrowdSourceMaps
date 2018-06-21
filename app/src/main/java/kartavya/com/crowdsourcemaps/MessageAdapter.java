package kartavya.com.crowdsourcemaps;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kartavya.com.crowdsourcemaps.Entity.Place;

/**
 * Created by karta on 19-03-2018.
 */

public class MessageAdapter extends ArrayAdapter<Place> {
    public MessageAdapter(Context context, int resource, List<Place> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);

        Place message = getItem(position);

        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(message.getPlaceName());

        return convertView;
    }
}
