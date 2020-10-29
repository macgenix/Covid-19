package cg.mokano.bzv.covid.services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Conseil;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;
import cg.mokano.bzv.covid.services.utils.ExpandAndCollapseViewUtil;


/**
 * Created by MOKANO on 07/12/2017.
 */

public class ConseilAdapter extends RecyclerView.Adapter<ConseilAdapter.ConseilHolder>{
    private static final int DURATION = 700;
    private Context context;
    ArrayList<Conseil> lstConseil;
    RecyclerViewOnclickListernner recyclerViewOnclickListernner;

    public ConseilAdapter(ArrayList<Conseil> lstConseil, Context context) {
        this.context = context;
        this.lstConseil = lstConseil;
    }

    public void setRecyclerViewOnclickListernner(RecyclerViewOnclickListernner r) {
        recyclerViewOnclickListernner = r;
    }

    @Override
    public ConseilHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conseils, parent,false);
        ConseilHolder holder = new ConseilHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ConseilHolder holder, int position) {
        holder.setTitre(lstConseil.get(position));
    }

    @Override
    public int getItemCount() {
        if (lstConseil != null){
            return lstConseil.size();
        }else
            return 0;
    }

    public class ConseilHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvReponse;
        Conseil mConseil;
        LinearLayout layout;

        public ConseilHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_question);
            tvReponse = itemView.findViewById(R.id.tv_reponse);
            layout = itemView.findViewById(R.id.ll_expand);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            toggleLinear(layout);
        }

        private void toggleLinear(LinearLayout linearLayout){
            if (linearLayout.getVisibility() == View.GONE) {
                ExpandAndCollapseViewUtil.expand(linearLayout, DURATION);
            } else {
                ExpandAndCollapseViewUtil.collapse(linearLayout, DURATION);
            }
        }


        void setTitre(Conseil conseil){
            tvTitle.setText(conseil.getTitre());
            tvReponse.setText(conseil.getDescription());
        }
    }
}
