package com.ericcode.labelview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.ericcode.mylabelview.LabelView;

public class MainActivity extends AppCompatActivity {
    LabelView labelView;
    SeekBar labelRound;
    SeekBar labelWidth;
    SeekBar labelRadius;
    SeekBar textSize;
    SeekBar textRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        labelView = findViewById(R.id.labelView);
        labelRound = findViewById(R.id.labelRound);
        labelWidth = findViewById(R.id.labelWidth);
        labelRadius = findViewById(R.id.labelRadius);
        textSize = findViewById(R.id.textSize);
        textRadius = findViewById(R.id.textRadius);
        labelRound.setProgress(labelView.getLabelRound());
        labelWidth.setProgress(labelView.getLabelWidth());
        labelRadius.setProgress(labelView.getLabelAngle());
        textSize.setProgress(labelView.getTextSize());
        textRadius.setProgress(labelView.getTextAngle());

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("MainActivity", progress + "");
                if (!fromUser) {
                    return;
                }
                if (seekBar == labelRound) {
                    labelView.setLabelRound(progress);
                } else if (seekBar == labelWidth) {
                    labelView.setLabelWidth(progress);
                } else if (seekBar == labelRadius) {
                    labelView.setLabelAngle(progress);
                } else if (seekBar == textSize) {
                    labelView.setTextSize(progress);
                } else if (seekBar == textRadius) {
                    labelView.setTextAngle(progress);
                }
                labelView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        labelRound.setOnSeekBarChangeListener(seekBarChangeListener);
        labelWidth.setOnSeekBarChangeListener(seekBarChangeListener);
        labelRadius.setOnSeekBarChangeListener(seekBarChangeListener);
        textSize.setOnSeekBarChangeListener(seekBarChangeListener);
        textRadius.setOnSeekBarChangeListener(seekBarChangeListener);

    }

}
