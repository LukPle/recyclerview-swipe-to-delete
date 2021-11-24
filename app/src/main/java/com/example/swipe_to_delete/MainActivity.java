package com.example.swipe_to_delete;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The MainActivity creates a Shopping List using a ArrayList with a RecyclerView.
 * The RecyclerView in combination with an Adapter is the recommended way for creating lists in Android.
 * It is possible to delete an item of the shopping list by swiping it to the left.
 *
 * The documentation focuses on the swipe to delete functionality.
 * For Javadoc information about the RecyclerView and Adapter check out the specific project.
 *
 * Layout File: activity_main.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        fillItemList();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final Adapter adapter = new Adapter(itemList);
        recyclerView.setAdapter(adapter);

        /**
         * This is a predefined class that handles touch events of list items.
         * The 0 means that there will be no move functionality.
         * The ItemTouchHelper.LEFT implies that the swipe mechanism only works for swiping left.
         * For both directions write "ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT".
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            /**
             * This method needs to be implemented and handles move events.
             * It is not used for this project.
             * @param recyclerView is the RecyclerView that contains the single views.
             * @param viewHolder is a ViewHolder that manages the view itself.
             * @param target is the specific item.
             * @return false for not using this method.
             */
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            /**
             * The swipe mechanism gets defined in this method that needs to be implemented.
             * @param viewHolder is a ViewHolder that manages the view itself.
             * @param direction is the direction of the swipe that is useful for different actions depending on direction.
             */
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                itemList.remove(adapter.getItemAt(viewHolder.getAdapterPosition()));
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void fillItemList() {

        itemList.add(new Item("Noodles", "1"));
        itemList.add(new Item("Cheese", "1"));
        itemList.add(new Item("Pepper", "2"));
        itemList.add(new Item("Onions", "4"));
        itemList.add(new Item("Carrots", "2"));
        itemList.add(new Item("Tomato", "1"));
        itemList.add(new Item("Fish", "4"));
        itemList.add(new Item("Grill Sauce", "1"));
        itemList.add(new Item("Baguette", "1"));
        itemList.add(new Item("Mushrooms", "6"));
        itemList.add(new Item("Cooking Oil", "1"));
    }
}