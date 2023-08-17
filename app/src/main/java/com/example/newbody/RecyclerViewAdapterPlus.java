package com.example.newbody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterPlus extends RecyclerView.Adapter<RecyclerViewAdapterPlus.ViewHolder> {

    private List<FriendData> mFriendList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerViewAdapterPlus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendplusitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterPlus.ViewHolder holder, int position) {
        holder.onBind(mFriendList.get(position));
    }

    public void setFriendList(ArrayList<FriendData> list){
        this.mFriendList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        FirebaseUser user;
        TextView uid;
        ImageView imageUrl;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            uid = view.findViewById(R.id.uid);
            imageUrl = view.findViewById(R.id.profile);

        }

        void onBind(FriendData user){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("users").document(user.getUid());

            // 기본 이미지를 미리 설정
            imageUrl.setImageResource(R.drawable.baseline_person_24);

            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String imageUrl = documentSnapshot.getString("imageUrl");
                    user.setImageUrl(imageUrl);
                    // 이미지 로딩
                    if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                        Picasso.get()
                                .load(user.getImageUrl())
                                .placeholder(R.drawable.baseline_person_24)
                                .error(R.drawable.baseline_person_24)
                                .into(this.imageUrl);
                    }
                }
            });
            name.setText(user.getName());
            uid.setText(user.getUid());
        }

    }
}
