package nikolay.rm.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import nikolay.rm.R;
import nikolay.rm.db.RMProjectDBHelper;
import nikolay.rm.vo.RMProject;

import java.util.*;

public class ProjectsActivity extends Activity {

    private RMProject rmProject = null;
    private RMProjectDBHelper projectDBHelper = null;
    ArrayList<Map<String, Object>> data = new ArrayList<>();
    SimpleAdapter simpleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rmProject = new RMProject();
        projectDBHelper = new RMProjectDBHelper(this);
        final ListView lv = (ListView) findViewById(R.id.list);
        data = projectDBHelper.getAllProjects();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.list_item,
                new String[]{"name"}, new int[]{R.id.project_name});
        simpleAdapter.notifyDataSetChanged();
        lv.setAdapter(simpleAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProjectsActivity.this);
                builder.setTitle("Что сделать с проектом?");
                builder.setPositiveButton("Открыть", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HashMap<String, Object> currentMap = (HashMap) lv.getItemAtPosition(position);

                        rmProject = projectDBHelper.getProject((int) currentMap.get("id"));
                        Intent intent = new Intent(ProjectsActivity.this, EventsActivity.class);
                        intent.putExtra("current", rmProject);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selected = ((TextView) view.findViewById(R.id.project_name)).getText().toString();
                        int projId = projectDBHelper.findIdByName(selected);
                        projectDBHelper.deleteProject(projId);
                        Toast toast = Toast.makeText(getApplicationContext(), "проект " + selected + " удален", Toast.LENGTH_SHORT);
                        toast.show();
                        data.clear();
                        data = projectDBHelper.getAllProjects();
                        simpleAdapter.notifyDataSetChanged();

                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        data = projectDBHelper.getAllProjects();
        simpleAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_item) {
            final Dialog dialog = new Dialog(ProjectsActivity.this);
            dialog.setContentView(R.layout.add_proj);
            dialog.setTitle("Введите название проекта:");
            dialog.setCancelable(false);

            Button okBtn = (Button) dialog.findViewById(R.id.btn_create_proj);
            Button cancelBtn = (Button) dialog.findViewById(R.id.btn_cancel_proj);

            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editProjName = (EditText) dialog.findViewById(R.id.edit_proj_name);
                    String projName = editProjName.getText().toString();
                    if (rmProject == null) {
                        rmProject = new RMProject();
                    }
                    rmProject.setName(projName);
                    if (projectDBHelper == null) {
                        projectDBHelper = new RMProjectDBHelper(ProjectsActivity.this);
                    }
                    projectDBHelper.addProject(rmProject);
                    //todo: send project information as parameter
                    Intent intent = new Intent(ProjectsActivity.this, EventsActivity.class);
                    intent.putExtra("current", rmProject);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
