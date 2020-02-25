package com.simpleinstagram.fragments;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.simpleinstagram.Post;
import java.util.List;

public class ProfileFragment extends PostsFragment {
    private static String TAG = "ProfileFragment";

    @Override
    protected void queryPosts() {
        super.queryPosts();
        final ParseQuery<Post> query = new ParseQuery<Post>(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                adapter.clear();
                adapter.addAll(posts);
                swipeContainer.setRefreshing(false);

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: " + post.getDescription() + " Username: " + post.getUser().getUsername());
                }

            }
        });
    }
}
