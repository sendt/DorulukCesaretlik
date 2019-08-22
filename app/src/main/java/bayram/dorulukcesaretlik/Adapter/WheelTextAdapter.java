package bayram.dorulukcesaretlik.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import bayram.dorulukcesaretlik.Data.MenuItemData;
import bayram.dorulukcesaretlik.R;
import github.hellocsl.cursorwheel.CursorWheelLayout;

/**
 * Created by Bayram on 12/21/2017.
 */

public class WheelTextAdapter extends CursorWheelLayout.CycleWheelAdapter {

    private Context mContext;
    private List<MenuItemData> menuItems;
    private LayoutInflater inflater;
    private int gravity;

    public WheelTextAdapter(Context mContext, List<MenuItemData> menuItems) {
        this.mContext = mContext;
        this.menuItems = menuItems;
        gravity= Gravity.CENTER;
        inflater = LayoutInflater.from(mContext);

    }

    public WheelTextAdapter(Context mContext, List<MenuItemData> menuItems, int gravity) {
        this.mContext = mContext;
        this.menuItems = menuItems;
        this.gravity = gravity;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public View getView(View parent, int position) {
    MenuItemData itemData=getItem(position);
    View root= inflater.inflate(R.layout.wheel_text_layout,null,false);
        TextView textView=(TextView) root.findViewById(R.id.wheel_menu_item_tv);
        textView.setVisibility(View.VISIBLE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        textView.setText(itemData.mTitle);
        if(textView.getLayoutParams() instanceof FrameLayout.LayoutParams)
            ((FrameLayout.LayoutParams)textView.getLayoutParams()).gravity = gravity;
        if(position==4)
            textView.setTextColor(ActivityCompat.getColor(mContext,R.color.red));
        return root;
    }

    @Override
    public MenuItemData getItem(int position) {
        return menuItems.get(position);
    }
}
