package cg.mokano.bzv.covid.services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Actualite;
import cg.mokano.bzv.covid.models.Statistique;
import cg.mokano.bzv.covid.models.Ville;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;

import static cg.mokano.bzv.covid.services.utils.GlideAppli.glideWeb;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.StatHolder>{
    RecyclerViewOnclickListernner recyclerViewOnclickListernner;
    Context context;
    ArrayList<Statistique> stats;

    public StatAdapter(Context context, ArrayList<Statistique> stats) {
        this.context = context;
        this.stats = stats;
    }

    public void setRecyclerViewOnclickListernner(RecyclerViewOnclickListernner r) {
        recyclerViewOnclickListernner = r;
    }

    @NonNull
    @Override
    public StatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stat, parent,false);
        StatHolder holder = new StatHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatHolder holder, int position) {
        bind(holder, stats.get(position));
    }

    @Override
    public int getItemCount() {
        if (stats.size() > 0)
            return stats.size();
        else
            return 0;
    }

    public void addStat(DataSnapshot snapshot, String libelle) {
        int gueris = 0;
        int confirmer = 0;
        int deces = 0;

        for (DataSnapshot snap :snapshot.getChildren()){
            Statistique stat = snap.getValue(Statistique.class);
            stat.setId(snap.getKey());

            confirmer = confirmer + stat.getConfimer();
            gueris = gueris + stat.getGueris();
            deces = deces + stat.getDeces();
        }

        Statistique statistique = new Statistique();
        statistique.setVille(new Ville(libelle));
        statistique.setConfimer(confirmer);
        statistique.setGueris(gueris);
        statistique.setDeces(deces);

        stats.add(statistique);
        notifyDataSetChanged();
    }

    void bind(StatHolder hoder, Statistique stat){
        hoder.tvConfirmer.setText(stat.getConfimer() + "");
        hoder.tvGueris.setText(stat.getGueris()+"");
        hoder.tvDeces.setText(stat.getDeces()+"");
        hoder.tvVille.setText(stat.getVille().getLibelle());

        hoder.tvTotalCas.setText(totalCas(stat)+" Cas");

        hoder.stat = stat;
    }

    int totalCas(Statistique stat){

        return stat.getConfimer();
    }

    public class StatHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvConfirmer;
        TextView tvGueris;
        TextView tvDeces;
        TextView tvVille;
        TextView tvTotalCas;
        Statistique stat;

        StatHolder(@NonNull View itemView) {
            super(itemView);

            tvConfirmer = itemView.findViewById(R.id.tv_confirmer);
            tvGueris= itemView.findViewById(R.id.tv_gueris);
            tvDeces = itemView.findViewById(R.id.tv_deces);
            tvVille = itemView.findViewById(R.id.tv_ville);
            tvTotalCas = itemView.findViewById(R.id.tv_total_cas);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnclickListernner != null){
                recyclerViewOnclickListernner.onClickListernner(stat);
            }
        }
    }
}
