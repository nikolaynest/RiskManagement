package nikolay.rm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import nikolay.rm.R;
import nikolay.rm.dao.EventDBManager;
import nikolay.rm.vo.Event;
import nikolay.rm.vo.RMProject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 15.04.14.
 */
public class EventsActivity extends Activity {
    public static final String KEY_EVENT_ID = "eventListId";
    private RMProject currentProject;

    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orm_event_list);
        EventDBManager.init(this);

        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.orm_event_list, null);
        listView = (ListView) contentView.findViewById(R.id.ormListView);

        Bundle bundle = getIntent().getExtras();
        currentProject = bundle.getParcelable("current");

        TextView textView = (TextView) contentView.findViewById(R.id.ormTextView);
        textView.setText("Проект " + currentProject.getName());

        Button btn = (Button) contentView.findViewById(R.id.ormButtonAdd);
        setupButton(btn);
        setContentView(contentView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupListView(listView);
    }

    private void setupButton(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupListView(ListView lv) {
        final List<Event> eventList = EventDBManager.getInstance().getAllEvents();

        List<String> titles = new ArrayList<>();
        if (eventList != null) {
            for (Event ev : eventList) {
                titles.add(ev.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event selected = eventList.get(position);
                Intent intent = new Intent(EventsActivity.this, Event.class);
                intent.putExtra(KEY_EVENT_ID, selected.getId());
                startActivity(intent);
            }
        });
    }
}
