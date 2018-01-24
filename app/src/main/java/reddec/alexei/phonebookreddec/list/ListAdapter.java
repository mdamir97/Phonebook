package reddec.alexei.phonebookreddec.list;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import reddec.alexei.phonebookreddec.R;
import reddec.alexei.phonebookreddec.model.ModelPhonebook;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ModelPhonebook> mList;
    private OnProductRequestClickedListener mListener;
    //SharedStatesMap mSharedStates;
    Context mContext;


    public ListAdapter(List<ModelPhonebook> posts, OnProductRequestClickedListener listener) {
        this.mList = posts;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_phonebook, parent, false);

        //mSharedStates = SharedStatesMap.getInstance();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModelPhonebook contact = mList.get(position);

        holder.tvName.setText(contact.getName() + "\n" + contact.getSurname());
        //holder.tvSurname.setText(product.getSurname());
        if (!contact.getPhone1().equals("")) {
            holder.ivNumber1.setVisibility(View.VISIBLE);
            holder.tvNumber1.setVisibility(View.VISIBLE);
            holder.tvNumber1.setText(mContext.getString(R.string.phone) + ": " + contact.getPhone1());
        }
        if (!contact.getPhone2().equals("")) {
            holder.ivNumber2.setVisibility(View.VISIBLE);
            holder.tvNumber2.setVisibility(View.VISIBLE);
            holder.tvNumber2.setText(mContext.getString(R.string.phone) + ": " + contact.getPhone2());
        }
        if (!contact.getPhone3().equals("")) {
            holder.ivNumber3.setVisibility(View.VISIBLE);
            holder.tvNumber3.setVisibility(View.VISIBLE);
            holder.tvNumber3.setText(mContext.getString(R.string.phone) + ": " + contact.getPhone3());
        }
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onProfileRequestClicked(contact.getId(), contact);
            }
        });
        /*
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onProfileRequestClicked(contact.getId(), contact);
            }
        });
*/

        holder.tvNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallRequestClicked(contact.getPhone1());
            }
        });
        holder.ivNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallRequestClicked(contact.getPhone1());
            }
        });


        holder.tvNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallRequestClicked(contact.getPhone2());
            }
        });
        holder.ivNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallRequestClicked(contact.getPhone2());
            }
        });


        holder.tvNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallRequestClicked(contact.getPhone3());
            }
        });
        holder.ivNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallRequestClicked(contact.getPhone3());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvName;
        //TextView tvSurname;
        TextView tvNumber1, tvNumber2, tvNumber3;
        ImageView ivNumber1, ivNumber2, ivNumber3;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            tvName = (TextView) itemView.findViewById(R.id.listitem_tv_fullname);
            //tvSurname = (TextView) itemView.findViewById(R.id.listitem_tv_surname);
            tvNumber1 = (TextView) itemView.findViewById(R.id.listitem_tv_number1);
            tvNumber2 = (TextView) itemView.findViewById(R.id.listitem_tv_number2);
            tvNumber3 = (TextView) itemView.findViewById(R.id.listitem_tv_number3);

            ivNumber1 = (ImageView) itemView.findViewById(R.id.listitem_iv_number1);
            ivNumber2 = (ImageView) itemView.findViewById(R.id.listitem_iv_number2);
            ivNumber3 = (ImageView) itemView.findViewById(R.id.listitem_iv_number3);
        }
    }
}