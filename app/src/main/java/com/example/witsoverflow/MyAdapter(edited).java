package com.example.witsoverflow;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Post> list;
    ArrayList<String> keys;
    //static String unam = "0907feb1-b0b2-4612-8b38-0b0e26fdf5c9";
    //static String id;

    public MyAdapter(Context context, ArrayList<Post> list, ArrayList<String> keys) {
        this.context = context;
        this.list = list;

        //initialise array with user ids
        //this.keys = keys;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post pst = list.get(position);
        String x,y;

        holder.username.setText(pst.getusername());
        holder.post.setText(pst.getPost());
        holder.up.setText((String.valueOf(pst.getUpvote())));
        holder.down.setText((String.valueOf(pst.getDownvote())));
        //get id value
        holder.id.setText(pst.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView post, username, up, down, id;
        ImageView upBtn, dnBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.userName);
            post = itemView.findViewById(R.id.post);
            up = itemView.findViewById(R.id.upVoteNo);
            down = itemView.findViewById(R.id.downVoteNo);
            id = itemView.findViewById(R.id.uid);


            //upVote and downVote buttons' functionality

            upBtn = itemView.findViewById(R.id.imageVupVote);
            dnBtn = itemView.findViewById(R.id.imageVdownVote);
            upBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    id = itemView.findViewById(R.id.uid);
                    String uid = id.getText().toString();

                    //instantiate class
                    temp t = new temp(uid);

                    //int votes = t.getVotes();
                    TextView upno = itemView.findViewById(R.id.upVoteNo);
                    int oldvotes = Integer.parseInt(upno.getText().toString());
                    t.setVotes(oldvotes);
                    int newVotes = t.getVotes();

                    //updates database vote value
                    t.updateupVotes(newVotes);

                    //upno.setText(Integer.toString(newVotes));

                  //  oldvotes = oldvotes+1;
                   // upno.setText(Integer.toString(oldvotes));


                    //upBtn.setEnabled(false);

                }
            });

            dnBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    id = itemView.findViewById(R.id.uid);
                    String uid = id.getText().toString();

                    //instantiate class
                    temp t = new temp(uid);

                    //int votes = t.getVotes();
                    TextView dnno = itemView.findViewById(R.id.downVoteNo);
                    int oldvotes = Integer.parseInt(dnno.getText().toString());
                    t.setVotes(oldvotes);
                    int newVotes = t.getVotes();

                    //updates database vote value
                    t.updatednVotes(newVotes);

                    //upno.setText(Integer.toString(newVotes));

                   // oldvotes = oldvotes+1;
                    //dnno.setText(Integer.toString(oldvotes));


                    //upBtn.setEnabled(false);

                }
            });
        }
    }

}
