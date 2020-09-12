package com.example.gadsleaderboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String githubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        EditText firstNameEditText = findViewById(R.id.firstName);
        EditText lastNameEditText = findViewById(R.id.lastName);
        EditText emailAddressEditText = findViewById(R.id.emailAddress);
        EditText githubLinkEditText = findViewById(R.id.githubLink);
        firstName = firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        emailAddress = emailAddressEditText.getText().toString();
        githubLink = githubLinkEditText.getText().toString();

        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("SubmissionActivity", "Submit button clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
                builder.setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                submitProject();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                int color = ContextCompat.getColor(SubmissionActivity.this, android.R.color.holo_orange_dark);
                dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(color);
                dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(color);
            }
        });
    }

    private void submitProject() {
        SubmissionApi submissionApi = RetrofitService.createLeaderboardService(SubmissionApi.class);
        submissionApi.submitProject(firstName, lastName, emailAddress, githubLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e("SubmissionActivity", response.toString());
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SubmissionActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View dialoglayout = inflater.inflate(R.layout.success_dialog, null);
                    alertDialogBuilder.setView(dialoglayout);
                    alertDialogBuilder.show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                Log.e("SubmissionActivity", throwable.getMessage());
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SubmissionActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.failure_dialog, null);
                alertDialogBuilder.setView(dialoglayout);
                alertDialogBuilder.show();
            }
        });
    }
}