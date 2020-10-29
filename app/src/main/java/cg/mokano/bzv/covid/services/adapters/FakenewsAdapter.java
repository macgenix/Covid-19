package cg.mokano.bzv.covid.services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Actualite;
import cg.mokano.bzv.covid.models.Fakenews;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;

import static cg.mokano.bzv.covid.services.utils.GlideAppli.glideWeb;
import static cg.mokano.bzv.covid.services.utils.GlideAppli.glideWebResize;

public class FakenewsAdapter extends RecyclerView.Adapter<FakenewsAdapter.FakenewsHolder>{
    RecyclerViewOnclickListernner recyclerViewOnclickListernner;
    private Context context;
    private ArrayList<Fakenews> allFake;
    private int lastPosition = -1;

    public FakenewsAdapter(Context context, ArrayList<Fakenews> allFake) {
        this.context = context;
        this.allFake = allFake;
    }

    public void setRecyclerViewOnclickListernner(RecyclerViewOnclickListernner r) {
        recyclerViewOnclickListernner = r;
    }

    public void addFakenews(Fakenews fakenews) {
        allFake.add(fakenews);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FakenewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fakenews, parent,false);
        FakenewsHolder holder = new FakenewsHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FakenewsHolder holder, int position) {
        bind(allFake.get(position), holder);
        holder.position = position;
        //setAnimation(holder.imgCapture, position);
    }

    @Override
    public int getItemCount() {
        if (allFake.size() > 0)
            return allFake.size();
        else
            return 0;
    }


    void bind(Fakenews fakenews, FakenewsHolder holder){
        holder.tvUrl.setText(fakenews.getUrlRS());
        holder.tvDescription.setText(fakenews.getDescription());
        glideWebResize(context, fakenews.getUrlFb(), holder.imgCapture);
        holder.fakenews = fakenews;
    }

    public class FakenewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvUrl;
        TextView tvDescription;
        ImageView imgCapture;
        Fakenews fakenews;
        int position;

        public FakenewsHolder(@NonNull View itemView) {
            super(itemView);
            tvUrl = itemView.findViewById(R.id.tv_url);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgCapture = itemView.findViewById(R.id.img_capture);

            itemView.setOnClickListener(this);
            setAnimationItem(itemView, position);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnclickListernner != null){
                recyclerViewOnclickListernner.onClickListernner(fakenews);
            }
        }

        void setAnimationItem(View view, int position){
            Animation animation = AnimationUtils.loadAnimation(context, (position>lastPosition ? R.anim.up_from_bottom:R.anim.down_from_top));
            view.startAnimation(animation);
        }
    }

    public void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_enter);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
