package com.shivek.multipleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class homeadapter extends RecyclerView.Adapter {

private List<homepagemodel> homepagemoddellist;

    public homeadapter(List<homepagemodel> homepagemoddellist) {
        this.homepagemoddellist = homepagemoddellist;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homepagemoddellist.get(position).getType()){
            case 0:
                return homepagemodel.GRID_LAYOUT;
            case 1:
                return homepagemodel.DEAL_Layout;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      switch (viewType){
          case homepagemodel.GRID_LAYOUT:
              View grid = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid , parent,false);
                  return new griddd(grid);
case homepagemodel.DEAL_Layout:
    View deal = LayoutInflater.from(parent.getContext()).inflate(R.layout.deall , parent,false);
    return new dealll(deal);
                  default:
                     return null;

      }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
             switch (homepagemoddellist.get(position).getType()){
                 case homepagemodel.GRID_LAYOUT:
                     String title = homepagemoddellist.get(position).getTitle();
                     List<commonmodel> a = homepagemoddellist.get(position).getModelist();
                     ((griddd)holder).setgridlayout(a,title);
                     break;
                     case homepagemodel.DEAL_Layout:
                         String titttle = homepagemoddellist.get(position).getTitle();
                         List<commonmodel> b = homepagemoddellist.get(position).getModelist();
                         ((dealll)holder).setdeallayout(b,titttle);
                         break;

                 default:return;
             }
    }

    @Override
    public int getItemCount() {
        return homepagemoddellist.size();
    }




    public class griddd extends RecyclerView.ViewHolder{
        private TextView gridtitle;
        private GridView gridView;

        public griddd(@NonNull View itemView) {
            super(itemView);
            gridtitle = itemView.findViewById(R.id.gridtext);
            gridView = itemView.findViewById(R.id.gridrv);
        }
        private void setgridlayout(List<commonmodel> a, String title){
            gridtitle.setText(title);
      gridadapter g = new gridadapter(a);
      gridView.setAdapter(g);
      g.notifyDataSetChanged();

        }
    }



    public class dealll extends RecyclerView.ViewHolder{
        private TextView dealtext;
        private RecyclerView dealrv;

        public dealll(@NonNull View itemView) {
            super(itemView);

            dealtext = itemView.findViewById(R.id.dealtext);
            dealrv = itemView.findViewById(R.id.dealrv);
        }
        private void setdeallayout(List<commonmodel> b,String title){

            dealtext.setText(title);
            dealadapter d = new dealadapter(b);
            LinearLayoutManager layoutManager =new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            dealrv.setLayoutManager(layoutManager);
            dealrv.setAdapter(d);
            d.notifyDataSetChanged();

        }
    }
}
