package yalantis.com.sidemenu.sample.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;

import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.sample.R;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class BugFragment extends Fragment implements ScreenShotable {

    public static final String BUG="Bug";

    private View containerView;
    protected int res;
    private Bitmap bitmap;

    public static BugFragment newInstance(int resId) {
        BugFragment bugFragment = new BugFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        bugFragment.setArguments(bundle);
        return bugFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.bug);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bug_frag, container, false);
        final Handler handler = new Handler();
        final ScrollView scrollView=rootView.findViewById(R.id.scro2);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                scrollView.setVisibility(View.VISIBLE);
                if(isAdded()) {
                    Animation animation1 =
                            AnimationUtils.loadAnimation(getActivity(),
                                    R.anim.fade);
                    scrollView.startAnimation(animation1);
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
                BugFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

