package com.ycxy.ymh.autoroom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.bean.FloorBean;

import java.util.List;

/**
 * Created by Y&MH on 2017-10-12.
 */

public class ClassRoomAdapter extends RecyclerView.Adapter<ClassRoomAdapter.ViewHolder> {

    private List<FloorBean> classroomBeanList;
    private Context mContext;

    public ClassRoomAdapter(List<FloorBean> classroomBeanList, Context mContext) {
        this.classroomBeanList = classroomBeanList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {/*
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_class_room, null);*/
        //View view = View.inflate(parent.getContext(),R.layout.item_class_room,false);
        // out of memory
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_class_room, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FloorBean classroomBean = classroomBeanList.get(position);
        final int light = classroomBean.getLamp();
        final int dark = (classroomBean.getLampTotal() - classroomBean.getLamp());
        //final int dark = ( dark1 > 0 ) ? dark1 : 0;
        final int total = classroomBean.getLampTotal();

        holder.tv_class_room_bh.setText(classroomBean.getClassroomName());
        holder.tv_class_room_light.setText(light + "");
        holder.tv_class_room_dart.setText(dark + "");

        holder.tv_class_room_bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进行页面跳转， 传递数据过去
                // 获取党委位置的数据
                Toast.makeText(mContext, classroomBean.getClassroomName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btn_light_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBulb(total, classroomBean);
            }
        });

        holder.btn_dark_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBulb(total, classroomBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classroomBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_class_room_bh;
        private Button btn_light_bulb;
        private Button btn_dark_bulb;
        private TextView tv_class_room_dart;
        private TextView tv_class_room_light;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_class_room_bh = (TextView) itemView.findViewById(R.id.tv_class_room_bh);
            btn_light_bulb = (Button) itemView.findViewById(R.id.btn_light_bulb);
            btn_dark_bulb = (Button) itemView.findViewById(R.id.btn_dark_bulb);
            tv_class_room_dart = (TextView) itemView.findViewById(R.id.tv_class_room_dart);
            tv_class_room_light = (TextView) itemView.findViewById(R.id.tv_class_room_light);
        }
    }

    public void closeBulb(int total, FloorBean classroomBean) {
        classroomBean.setLamp(0);
        this.notifyDataSetChanged();
    }

    public void openBulb(int total, FloorBean classroomBean) {
        classroomBean.setLamp(total);
        this.notifyDataSetChanged();
    }
}
