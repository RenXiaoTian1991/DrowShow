package com.example.dropshow;

import android.support.v7.app.ActionBarActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends ActionBarActivity {

	private LinearLayout ll_show,ll_hide;
	private int mHiddenVIewHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_show = (LinearLayout) findViewById(R.id.ll_show);
        ll_hide = (LinearLayout) findViewById(R.id.ll_hide);
        
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // ÆÁÄ»¿í¶È£¨ÏñËØ£©
        int height = metric.heightPixels;  // ÆÁÄ»¸ß¶È£¨ÏñËØ£©
        float density = metric.density;  // ÆÁÄ»ÃÜ¶È£¨0.75 / 1.0 / 1.5£©
        int densityDpi = metric.densityDpi;  // ÆÁÄ»ÃÜ¶ÈDPI£¨120 / 160 / 240£©
        
        mHiddenVIewHeight = (int) (density*(40 +0.5));
        ll_show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(ll_hide.getVisibility() == View.GONE){
				    showAnim(ll_hide);	
				}else{
					hideAnim(ll_show);
				}
			}

			private void hideAnim(View view) {
				ValueAnimator anim = ValueAnimator.ofInt(mHiddenVIewHeight,0);
				anim.addUpdateListener(new AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator arg0) {
                        int value = (Integer) arg0.getAnimatedValue();
                        LinearLayout.LayoutParams params = (LayoutParams) ll_hide.getLayoutParams();
                        params.height = value;
                        ll_hide.setLayoutParams(params);
					}
				});
				anim.addListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						ll_hide.setVisibility(View.GONE);
					}
				});
				anim.start();
			}

			private void showAnim(View view) {
				view.setVisibility(View.VISIBLE);
				ValueAnimator anim = ValueAnimator.ofInt(0,mHiddenVIewHeight);
				anim.addUpdateListener(new AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator arg0) {
                        int value = (Integer) arg0.getAnimatedValue();
                        LinearLayout.LayoutParams params = (LayoutParams) ll_hide.getLayoutParams();
                        params.height = value;
                        ll_hide.setLayoutParams(params);
					}
				});
				anim.start();
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
