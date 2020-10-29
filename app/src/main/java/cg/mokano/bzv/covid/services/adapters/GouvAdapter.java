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
import cg.mokano.bzv.covid.models.Gouv;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;

import static cg.mokano.bzv.covid.services.utils.ConvertDateUtils.convertToDate;
import static cg.mokano.bzv.covid.services.utils.GlideAppli.glideWeb;

public class GouvAdapter extends RecyclerView.Adapter<GouvAdapter.GouvHoder>{
    RecyclerViewOnclickListernner recyclerViewOnclickListernner;
    Context context;
    ArrayList<Gouv> gouvs;
    private int lastPosition = -1;

    public GouvAdapter(Context context, ArrayList<Gouv> gouvs) {
        this.context = context;
        this.gouvs = gouvs;
    }

    public void setRecyclerViewOnclickListernner(RecyclerViewOnclickListernner r) {
        recyclerViewOnclickListernner = r;
    }

    @NonNull
    @Override
    public GouvHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_declaration, parent,false);
       GouvHoder holder = new GouvHoder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GouvHoder holder, int position) {
        bind(holder, gouvs.get(position));
    }

    @Override
    public int getItemCount() {
        if (gouvs.size() > 0)
            return gouvs.size();
        else
            return 0;
    }

    public void addGouvPdf(Gouv gouv) {
        gouvs.add(gouv);
        notifyDataSetChanged();
    }

    void bind(GouvHoder hoder, Gouv gouv){
        hoder.tvTitre.setText(gouv.getTitre());
        hoder.tvDate.setText(convertToDate(gouv.getDate()));

        hoder.gouv = gouv;
    }

    public class GouvHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitre;
        TextView tvDate;
        Gouv gouv;
        int position;

        public GouvHoder(@NonNull View itemView) {
            super(itemView);

            tvTitre = itemView.findViewById(R.id.tv_declaration);
            tvDate = itemView.findViewById(R.id.tv_date);

            setAnimationItem(itemView, position);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnclickListernner != null){
                recyclerViewOnclickListernner.onClickListernner(gouv);
            }
        }

        void setAnimationItem(View view, int position){
            Animation animation = AnimationUtils.loadAnimation(context, (position>lastPosition ? R.anim.up_from_bottom:R.anim.down_from_top));
            view.startAnimation(animation);
        }
    }
}
