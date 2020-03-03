package yalantis.com.sidemenu.sample.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.sample.R;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class HomeFragment extends Fragment implements ScreenShotable {

    public static final String HOME="Home";

    private View containerView;
    protected int res;
    private Bitmap bitmap;

    public static HomeFragment newInstance(int resId) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.home);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_frag, container, false);

        final CardView cardView=rootView.findViewById(R.id.cv1);
        final CardView cardView1=rootView.findViewById(R.id.cv2);
        //cardView.setOutlineAmbientShadowColor(getResources().getColor(R.color.shadow));
        //cardView1.setOutlineAmbientShadowColor(getResources().getColor(R.color.shadow));
        /*final TextView tb=(TextView)rootView.findViewById(R.id.tt);
        final TextView tb1=(TextView)rootView.findViewById(R.id.tt1);
        final TextView tb2=(TextView)rootView.findViewById(R.id.tt2);
        final TextView tb3=(TextView)rootView.findViewById(R.id.tt3);*/



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

cardView.setVisibility(View.VISIBLE);
cardView1.setVisibility(View.VISIBLE);
               /* tb.setVisibility(View.VISIBLE);
                tb1.setVisibility(View.VISIBLE);
                tb2.setVisibility(View.VISIBLE);
                tb3.setVisibility(View.VISIBLE);*/
                if(isAdded()) {
                    Animation animation1 =
                            AnimationUtils.loadAnimation(getActivity(),
                                    R.anim.fade);
                    cardView.startAnimation(animation1);
                    cardView1.startAnimation(animation1);
                   /* tb.startAnimation(animation1);
                    tb1.startAnimation(animation1);
                    tb2.startAnimation(animation1);
                    tb3.startAnimation(animation1);*/
                }

            }
        }, 250);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                HomeFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void fade(View view){

    }
}

