package lb.edu.aub.cmps253.yzo01.ABO;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private List<Request> requestList;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mobileText;
        public TextView centerText;
        public TextView neededBloodTypeText;
        public TextView unitsNumberText;

        public MyViewHolder(View view) {
            super(view);
            mobileText = (TextView) view.findViewById(R.id.mobile);
            centerText = (TextView) view.findViewById(R.id.center);
            neededBloodTypeText = (TextView) view.findViewById(R.id.neededBloodType);
            unitsNumberText = (TextView) view.findViewById(R.id.unitsNumber);
        }
    }

    public CountryAdapter(List<Request> requestList) {
        this.requestList = requestList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println("Bind ["+holder+"] - Pos ["+position+"]");
        Request c = requestList.get(position);
        holder.mobileText.setText(c.mobile);
        holder.centerText.setText(String.valueOf(c.center));
        holder.neededBloodTypeText.setText(String.valueOf(c.neededBloodType));
        holder.unitsNumberText.setText(String.valueOf(c.unitsNumber));
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size ["+ requestList.size()+"]");
        return requestList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent, false);
        return new MyViewHolder(v);
    }
}
