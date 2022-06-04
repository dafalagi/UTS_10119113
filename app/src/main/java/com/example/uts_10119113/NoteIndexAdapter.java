package com.example.uts_10119113;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteIndexAdapter extends RecyclerView.Adapter<NoteIndexAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Notes> notes;

    public NoteIndexAdapter(Context context, List<Notes> notes)
    {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteIndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_note_index, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteIndexAdapter.ViewHolder holder, int position) {
        long id = notes.get(position).getId();
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();

        holder.id.setText(String.valueOf(id));
        holder.title.setText(title);
        holder.date.setText(date);
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, title, date, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.notesId);
            title = itemView.findViewById(R.id.notesTitle);
            date = itemView.findViewById(R.id.notesDate);
            time = itemView.findViewById(R.id.notesTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}