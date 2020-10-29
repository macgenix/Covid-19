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
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;

import static cg.mokano.bzv.covid.services.utils.ConvertDateUtils.convertToDate;
import static cg.mokano.bzv.covid.services.utils.GlideAppli.glideWeb;

public class ActualiteAdapter extends RecyclerView.Adapter<ActualiteAdapter.ActualiteHoder>{
    RecyclerViewOnclickListernner recyclerViewOnclickListernner;
    Context context;
    ArrayList<Actualite> actualites;
    private int lastPosition = -1;

    public ActualiteAdapter(Context context, ArrayList<Actualite> actualites) {
        this.context = context;
        this.actualites = actualites;
    }

    public void setRecyclerViewOnclickListernner(RecyclerViewOnclickListernner r) {
        recyclerViewOnclickListernner = r;
    }

    @NonNull
    @Override
    public ActualiteHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actualite, parent,false);
        ActualiteHoder holder = new ActualiteHoder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActualiteHoder holder, int position) {
        bind(holder, actualites.get(position));
        setAnimation(holder.imgActu, position);
    }

    @Override
    public int getItemCount() {
        if (actualites.size() > 0)
            return actualites.size();
        else
            return 0;
    }

    public void addActualite(Actualite actualite) {
        actualites.add(actualite);
        notifyDataSetChanged();
    }

    void bind(ActualiteHoder hoder, Actualite actualite){
        hoder.tvTitre.setText(actualite.getTitre());
        hoder.tvAuteur.setText(actualite.getSource());
        hoder.tvTiming.setText(convertToDate(actualite.getDate()));

        glideWeb(context, actualite.getImage(), hoder.imgActu);

        hoder.actualite = actualite;
    }

    public class ActualiteHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvAuteur;
        TextView tvTitre;
        TextView tvTiming;
        ImageView imgActu;
        Actualite actualite;

        public ActualiteHoder(@NonNull View itemView) {
            super(itemView);

            tvAuteur = itemView.findViewById(R.id.tv_auteur_actu);
            tvTitre = itemView.findViewById(R.id.tv_titre_actu);
            tvTiming = itemView.findViewById(R.id.tv_timing);
            imgActu = itemView.findViewById(R.id.img_actu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnclickListernner != null){
                recyclerViewOnclickListernner.onClickListernner(actualite);
            }
        }
    }

    public void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_enter);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
