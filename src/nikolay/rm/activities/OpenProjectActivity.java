package nikolay.rm.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import nikolay.rm.R;
import nikolay.rm.vo.RMProject;

/**
 * Created by nikolay on 13.04.14.
 */
public class OpenProjectActivity extends Activity {
    private RMProject currentProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_proj);

        Bundle bundle = getIntent().getExtras();
        currentProject = bundle.getParcelable("current");

        TextView textView = (TextView) findViewById(R.id.label_open_project);
        textView.setText("Проект "+currentProject.getName());

    }
}
