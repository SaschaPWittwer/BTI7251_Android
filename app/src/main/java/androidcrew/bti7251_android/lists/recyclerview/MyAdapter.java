package androidcrew.bti7251_android.lists.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidcrew.bti7251_android.R;
import androidcrew.bti7251_android.lists.Person;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Person> mPeople;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView surname;
        public TextView age;
        public TextView gender;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textname);
            surname = view.findViewById(R.id.textsurname);
            age = view.findViewById(R.id.textageb);
            gender = view.findViewById(R.id.textgender);
        }
    }

    public MyAdapter(List<Person> mPeople) {
        super();
        this.mPeople = mPeople;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewrow, parent, false);
        itemView.setOnClickListener(v -> {
            int position = parent.indexOfChild(v);
            Person p = mPeople.get(position);
            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com/search?q=" + p.getName() + "+" + p.getSurname()));
            parent.getContext().startActivity(implicit);
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = mPeople.get(position);
        holder.name.setText(person.getName());
        holder.surname.setText(person.getSurname());
        holder.age.setText(Integer.toString(person.getAge()));
        holder.gender.setText(person.getGender());
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }
}
