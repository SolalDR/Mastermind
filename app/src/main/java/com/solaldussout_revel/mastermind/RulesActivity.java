package com.solaldussout_revel.mastermind;
import android.os.Bundle;
public class RulesActivity extends MenuParentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        initToolbar();
    }
}
