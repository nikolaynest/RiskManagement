package nikolay.rm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import nikolay.rm.R;
import nikolay.rm.dao.EventDBManager;
import nikolay.rm.vo.Event;


public class AddEventActivity extends Activity{

    private EventDBManager eventDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        eventDBManager.init(this);

        Button button = (Button) findViewById(R.id.add_event_button);
        final String eventName = ((EditText) findViewById(R.id.add_event_editText)).getText().toString();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event(eventName);
                eventDBManager.addEvent(event);
                Intent intent = new Intent(AddEventActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });
    }
}
