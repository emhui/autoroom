package com.ycxy.ymh.autoroom.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.activity.ClassRoomActivity;
import com.ycxy.ymh.autoroom.bean.BuildingBean;
import com.ycxy.ymh.autoroom.util.Constant;

import java.util.List;

/**
 * Created by Y&MH on 2017-10-12.
 */

public class BuildingAdapter extends RecyclerView.Adapter <BuildingAdapter.ViewHolder> {

    private List<BuildingBean> buildingList;
    private Context mContext;

    public BuildingAdapter(List<BuildingBean> buildingList) {
        this.buildingList = buildingList;
    }

    public BuildingAdapter(List<BuildingBean> buildingList, Context mContext) {
        this.buildingList = buildingList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_building,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BuildingBean building = buildingList.get(position);
        holder.tv_building.setText(building.getBuildingName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进行页面跳转， 传递数据过去
                // 获取党委位置的数据
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.BUILDING,building);
                Intent intent = new Intent(mContext,ClassRoomActivity.class);
                intent.putExtra(Constant.BUILDINGPOSITION,position);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_building;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_building = (TextView) itemView.findViewById(R.id.tv_building);
        }
    }
}
