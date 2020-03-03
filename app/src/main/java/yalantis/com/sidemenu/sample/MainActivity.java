package yalantis.com.sidemenu.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.sample.databinding.ActivityMainBinding;
import yalantis.com.sidemenu.sample.fragment.BugFragment;
import yalantis.com.sidemenu.sample.fragment.ContactUsFragment;
import yalantis.com.sidemenu.sample.fragment.FaqsFragment;
import yalantis.com.sidemenu.sample.fragment.HomeFragment;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.graphics.Color.TRANSPARENT;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private int res = R.drawable.content_music;
    private LinearLayout linearLayout;
    String nm=HomeFragment.HOME;
    int fabstatus=0;


    private DisplayMetrics mDisplayMetrics;
    private ActivityMainBinding mBinding;
    private float mStartX;
    private HorizontalScrollView hsv;
    private float mStartY;
    private int mBottomY;
    private int mBottomX;
    private TextView name;
    private ImageView imageView;
    private boolean mIsCancel;
    private float mBottomListStartY;
    private boolean resetBottomList;
    private TextView desc;
    private TextView name1;
    private TextView Date;
    private TextView Dat;
    private TextView Reg;
    private TextView Regf;
    private TextView Cont;

    private TextView name2;
    private TextView nth_tv;
    private TextView num1;
    private FloatingActionButton floatingActionButton;
    private TextView num2;
    private TextView nccTitle;
    private TextView inq;
    private TextView unr;
    LinearLayout detail;
    LinearLayout linearLayout1;
    private FrameLayout title;
    HomeFragment homeFragment;
    ContactUsFragment contactUsFragment;
    BugFragment bugFragment;
    FaqsFragment faqsFragment;
    private ScrollView scrollView1;
    Resourceble slideMenuItem;
    ScreenShotable screenShotable;
    int position=-1;
    Animation anim;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorAccent));
        //setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        linearLayout1=findViewById(R.id.list);
        scrollView1=findViewById(R.id.scroll_view);
        homeFragment = HomeFragment.newInstance(R.drawable.content_music);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, homeFragment)
                .commit();
        floatingActionButton=findViewById(R.id.fab);
        Date=(TextView)findViewById(R.id.date);
        Dat=(TextView)findViewById(R.id.dat);
        Reg=(TextView)findViewById(R.id.reg);
        Regf=(TextView)findViewById(R.id.regf);
        Cont=(TextView)findViewById(R.id.cont);
        nccTitle=findViewById(R.id.ncc_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        nth_tv= findViewById(R.id.title_nth);
        unr=findViewById(R.id.unravel_tv);
        inq=findViewById(R.id.inq_tv);
        hsv=findViewById(R.id.scroll);

        linearLayout = findViewById(R.id.left_drawer);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });


        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, homeFragment, drawerLayout, this);




        setSupportActionBar(mBinding.toolbar);
        name= (TextView) findViewById(R.id.name);
        imageView=(ImageView)findViewById(R.id.image_view);
        detail=(LinearLayout)findViewById(R.id.detail);
        desc=(TextView) findViewById(R.id.description);
        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);
        num1=(TextView)findViewById(R.id.number1);
        num2=(TextView)findViewById(R.id.number2);
        title=(FrameLayout)findViewById(R.id.titles);


        setDisplayMetrics();

        mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.sheet)));
        //accept();
        Drawable d = mBinding.bottomListBackground.getBackground();
        final GradientDrawable gd = (GradientDrawable) d;

        gd.setCornerRadius(0f);

         anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000); //You can manage the blinking time with this parameter
        anim.setStartOffset(50);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        floatingActionButton.startAnimation(anim);



    }

    public class RelativeLayoutTouchListener implements View.OnTouchListener {

        static final String logTag = "ActivitySwipeDetector";
        private Activity activity;
        static final int MIN_DISTANCE = 100;// TODO change this runtime based on screen resolution. for 1920x1080 is to small the 100 distance
        private float downX, downY, upX, upY;

        // private MainActivity mMainActivity;
        int y;

        public RelativeLayoutTouchListener(MainActivity mainActivity,int x) {
            activity = mainActivity;
            y=x;
        }

        public void onRightToLeftSwipe() {

            switch (y){
                case 1:
                    nth1();
                    break;
                case 2:
                    inq1();
                    break;
                case 3:
                    unr1();
                    break;
            }

            // activity.doSomething();
        }

        public void onLeftToRightSwipe() {
            switch (y)
            {
                case 4:
                    inq1();
                    break;
                case 3:
                    nth1();
                    break;
                case 2:
                    ncc1();

            }
            // activity.doSomething();
        }

        public void onTopToBottomSwipe() {
            Log.i(logTag, "onTopToBottomSwipe!");
            Toast.makeText(activity, "onTopToBottomSwipe", Toast.LENGTH_SHORT).show();
            // activity.doSomething();
        }

        public void onBottomToTopSwipe() {
            Log.i(logTag, "onBottomToTopSwipe!");
            Toast.makeText(activity, "onBottomToTopSwipe", Toast.LENGTH_SHORT).show();
            // activity.doSomething();
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    downX = event.getX();
                    downY = event.getY();
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    upX = event.getX();
                    upY = event.getY();

                    float deltaX = downX - upX;
                    float deltaY = downY - upY;

                    // swipe horizontal?
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        // left or right
                        if (deltaX < 0) {
                            this.onLeftToRightSwipe();
                            return true;
                        }
                        if (deltaX > 0) {
                            this.onRightToLeftSwipe();
                            return true;
                        }
                    } else {
                        Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long horizontally, need at least " + MIN_DISTANCE);
                        // return false; // We don't consume the event
                    }

                    // swipe vertical?
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
                            this.onTopToBottomSwipe();
                            return true;
                        }
                        if (deltaY > 0) {
                            this.onBottomToTopSwipe();
                            return true;
                        }
                    } else {
                        Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long vertically, need at least " + MIN_DISTANCE);
                        // return false; // We don't consume the event
                    }

                    return false; // no swipe horizontally and no swipe vertically
                }// case MotionEvent.ACTION_UP:
            }
            return false;
        }

    }



    public void animate(View view) {


        linearLayout1.setOnTouchListener(new RelativeLayoutTouchListener(this,1));






        if (!mIsCancel) {
            if (mStartX == 0.0f) {
                mStartX = view.getX();
                mStartY = view.getY();

                mBottomX = getBottomFilterXPosition();
                mBottomY = getBottomFilterYPosition();

                mBottomListStartY = mBinding.bottomListBackground.getY();
            }

            final int x = getFinalXPosition();
            final int y = getFinalYPosition();


            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                   /* fabstatus=1;
                    if(nm.equals(HomeFragment.HOME) || nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(homeFragment).commit();
                    if(nm.equals(ContactUsFragment.CONTACTUS)|| nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(contactUsFragment).commit();
                    if(nm.equals(BugFragment.BUG)|| nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(bugFragment).commit();
                    if(nm.equals(FaqsFragment.FAQS)|| nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(faqsFragment).commit();*/
                    float v = (float) animation.getAnimatedValue();

                    mBinding.fab.setX(
                            x + (mStartX - x - ((mStartX - x) * v))
                    );

                    mBinding.fab.setY(
                            y + (mStartY - y - ((mStartY - y) * (v * v)))
                    );
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    removeFabBackground();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.fab.animate()
                                    .y(mBottomY)
                                    .setDuration(200)
                                    .start();

                        }
                    },50);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           /* mBinding.cancel.setVisibility(VISIBLE);
                            mBinding.cancel.setTranslationX(-(mBottomX - x));

                            mBinding.cancel.animate()
                                    .translationXBy(mBottomX - x)
                                    .setDuration(200)
                                    .start();*/

                            mBinding.fab.animate()
                                    .x(mBottomX+x)
                                    .setDuration(200)
                                    .start();

//                            mBinding.fab.animate()
//                                    .x(mBottomX)
//                                    .setDuration(200)
//                                    .start();

                            mBinding.scroll.setScaleY(0f);
                            mBinding.scroll.setVisibility(VISIBLE);

                            mBinding.scroll.animate()
                                    .scaleY(1f)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            mBinding.scroll.setVisibility(VISIBLE);
                                        }
                                    })
                                    .setDuration(200)
                                    .start();
                        }
                    }, 200);

                    if (resetBottomList) {
                        //Log.d(LOG_TAG, "onAnimationEnd: reset");
                        resetBottomListBackground();
                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.bottomListBackground.animate()
                                    .alpha(1f)
                                    .setDuration(500)
                                    .setListener(new AnimatorListenerAdapter() {

                                        @SuppressLint("RestrictedApi")
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            mBinding.fab.setImageResource(R.drawable.ctdlogo);
                                            mBinding.fab.setVisibility(INVISIBLE);
                                           // mBinding.fab.setX(mBinding.cancel.getX() - mDisplayMetrics.density * 4);
                                            mBinding.fab.setY(getBottomFilterYPosition());
                                            mBinding.applyFilters.setVisibility(VISIBLE);
                                            mBinding.frame.setVisibility(VISIBLE);
                                            mBinding.scrollView.setVisibility(VISIBLE);
                                            name.setVisibility(VISIBLE);

                                        }
                                    })
                                    .start();
                        }
                    }, 100);
                    fabstatus=1;
                    if(nm.equals(HomeFragment.HOME) || nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(homeFragment).commit();
                    if(nm.equals(ContactUsFragment.CONTACTUS)|| nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(contactUsFragment).commit();
                    if(nm.equals(BugFragment.BUG)|| nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(bugFragment).commit();
                    if(nm.equals(FaqsFragment.FAQS)|| nm.equals("CLOSE"))
                        getSupportFragmentManager().beginTransaction().remove(faqsFragment).commit();
                    revealFilterSheet(y);
                }
            });

            animator.start();
        } else {
            mBinding.fab.setImageResource(R.drawable.ctdlogo);
            mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.sheet)));
            mIsCancel = false;
        }
        anim.cancel();
    }

    private void resetBottomListBackground() {
        resetBottomList = false;
        mBinding.bottomListBackground.setVisibility(VISIBLE);
        Drawable d = mBinding.bottomListBackground.getBackground();
        final GradientDrawable gd = (GradientDrawable) d;
        mBinding.bottomListBackground.setAlpha(0f);
        gd.setCornerRadius(0f);


        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBinding.bottomListBackground.getLayoutParams();
        params.width = -1;
        params.height = (int) (mDisplayMetrics.density * 64);
        mBinding.bottomListBackground.setY(mBottomListStartY + mDisplayMetrics.density * 8);
        mBinding.bottomListBackground.requestLayout();
    }

    private int getBottomFilterYPosition() {
        return (int) (
                mBinding.applyFilters.getY()
                        + (mDisplayMetrics.heightPixels - getStatusBarHeight() - mDisplayMetrics.density * 64)
                        - mDisplayMetrics.density * 4);
    }

    private int getBottomFilterXPosition() {
        return (int) (
                mBinding.applyFilters.getX()
                        + mDisplayMetrics.widthPixels / 2
                        - mDisplayMetrics.density * 4);
    }

//    public  void abc(View view)
//    { ncc.setText("NCC");
//    }
//    public  void bcd(View view){
//        ncc.setText("NTH");
//    }

    private void removeFabBackground() {
        mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(TRANSPARENT));

        mBinding.fab.setElevation(0f);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ncc(View view)
    {
        ncc1();
    }
    public void ncc1()
    {
        scrollView1.setVisibility(INVISIBLE);
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation1 =
                        AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
                scrollView1.startAnimation(animation1);
                scrollView1.setVerticalScrollbarPosition(0);
                scrollView1.setVisibility(VISIBLE);
            }
        },250);
        linearLayout1.setOnTouchListener(new RelativeLayoutTouchListener(this,1));
        int x,y;
        x = title.getLeft();
        y = nccTitle.getTop();
        hsv.scrollTo(x, y);
        y=title.getBaseline();
        x=scrollView1.getRight();
        scrollView1.smoothScrollTo(x,y);
        nccTitle.setTextColor(getResources().getColor(R.color.nccu));
        nth_tv.setTextColor(getResources().getColor(R.color.ncc));
        inq.setTextColor(getResources().getColor(R.color.ncc));
        unr.setTextColor(getResources().getColor(R.color.ncc));
        name.setText("National Computing Contest");
        name.setVisibility(VISIBLE);
        imageView.setImageResource(R.drawable.ncc);
        detail.setVisibility(VISIBLE);
        desc.setText("Here's an opportunity to dive into the great depths of competitive coding! PICT IEEE Student Branch brings to you the National Computing Contest (NCC).\n\nNCC is a platform where you can not only sharpen your problem solving ability but also showcase your programming skills. So, get ready to code, compile and run your C/C++ code, to stay ahead of the time as well as your friends. See you on the leaderboard!\n");
//        desc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        name1.setText("Tanmay Pardeshi");
        num1.setText(getText(R.string.tanmay_no));
        name2.setText("Rushikesh Deshpande");
        num2.setText(getText(R.string.rushikesh_no));
        Date.setText("Date: ");
        Dat.setText("11th March, 2020\n");
        Regf.setText("Registration Fee: ");
        Reg.setText("Participate in a team of two.\n" +"IEEE Members: ₹80/-\n" +"Non-IEEE Members: ₹100/-\n");
        Cont.setText("Contact: ");

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nth(View view)
    {
        nth1();
    }
    public void nth1(){

        scrollView1.setVisibility(INVISIBLE);
       final Handler handler=new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
                   Animation animation1 =
                           AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
                   scrollView1.startAnimation(animation1);
                   scrollView1.setVisibility(VISIBLE);
           }
       },250);
        linearLayout1.setOnTouchListener(new RelativeLayoutTouchListener(this,2));
        int x, y;
        x = nccTitle.getRight();
        int c=inq.getLeft();
        y = nth_tv.getTop();
        hsv.scrollTo(x, y);
        y=title.getBaseline();
        x=scrollView1.getRight();
        scrollView1.smoothScrollTo(x,y);
        nth_tv.setTextColor(getResources().getColor(R.color.nccu));
        nccTitle.setTextColor(getResources().getColor(R.color.ncc));
        inq.setTextColor(getResources().getColor(R.color.ncc));
        unr.setTextColor(getResources().getColor(R.color.ncc));
        name.setText("Network Treasure Hunt");
        name.setVisibility(VISIBLE);
        imageView.setImageResource(R.drawable.nth);
        detail.setVisibility(VISIBLE);
        desc.setText("Network Treasure Hunt is an online cryptic hunt played worldwide. Don't just see, observe! Follow the trails of clues you have and piece together every bit of the puzzle. You can use every tool at your disposal. Just Google will not be enough to get you through this brain racking experience.\n\n" +
                "Strive to get your name on top of the leaderboard and win exciting prizes. Don't forget to be a part of this nail-biting online contest. The questions will surely leave you bewildered.\n" +
                "The game is on!\n");
//        desc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        name1.setText("Garvita Jain");
        num1.setText(getText(R.string.garvita_no));
        name2.setText("Shubham Chorage");
        num2.setText(getText(R.string.shubham_no));
        Dat.setText("7th March, 2020\n");
        Regf.setText("Registration Fee: ");
        Reg.setText("Participate in a team of two.\n" +"IEEE Members: ₹0/-\n" +"Non-IEEE Members: ₹0/-\n");
        Cont.setText("Contact: ");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void inq(View view)
    {
       inq1();
    }
    public void inq1()
    {
        scrollView1.setVisibility(INVISIBLE);
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation1 =
                        AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
                scrollView1.startAnimation(animation1);
                scrollView1.setVisibility(VISIBLE);
            }
        },250);
        linearLayout1.setOnTouchListener(new RelativeLayoutTouchListener(this,3));
        int x, y;
        x = nth_tv.getRight();
        y = inq.getTop();
        int c=nth_tv.getRight();
        hsv.scrollTo(x, y);
        y=title.getBaseline();
        x=scrollView1.getRight();
        scrollView1.smoothScrollTo(x,y);
        nccTitle.setTextColor(getResources().getColor(R.color.ncc));
        nth_tv.setTextColor(getResources().getColor(R.color.ncc));
        inq.setTextColor(getResources().getColor(R.color.nccu));
        unr.setTextColor(getResources().getColor(R.color.ncc));
        name.setText("InQUIZitivE");
        name.setVisibility(VISIBLE);
        imageView.setImageResource(R.drawable.inquizituve);
        detail.setVisibility(VISIBLE);
        desc.setText("Here's the opportunity for all of you to put your minds to test, as you rack your brains in InQuizitive.\n\n" +
                "Are you ready to tickle your grey cells and take your IQ to the next level by competing in with the best in a battle of wits?\n\n" +
                "If you’ve always wondered if you have what it takes to win in a competition that requires only your presence of mind and general awareness, here is your chance to test it. PISB brings you InQuizitive, a general quiz that will make you rack your brains, with topics ranging from pop culture to current affairs.\n\n" +
                "Open for all students of FE, SE, TE and BE, InQuizitive does not have any particular syllabus or criteria for participating. So come forward and take part in this classic showdown of general knowledge and trivia!\n");
//        desc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        name1.setText("Asawari Walkade");
        num1.setText(getText(R.string.asawari_no));
        name2.setText("Aparna Ranade");
        num2.setText(getText(R.string.aparna_noo));
        Dat.setText("29th February, 2020\n");
        Regf.setText("Registration Fee: ");
        Reg.setText("Participate in a team of two.\n" +"IEEE Members: ₹0/-\n" +"Non-IEEE Members: ₹0/-\n");
        Cont.setText("Contact: ");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void unr(View view)
    {
        unr1();
    }
    public void unr1()
    {
        scrollView1.setVisibility(INVISIBLE);
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation1 =
                        AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
                scrollView1.startAnimation(animation1);
                scrollView1.setVisibility(VISIBLE);
            }
        },250);
        linearLayout1.setOnTouchListener(new RelativeLayoutTouchListener(this,4));
        int x, y;
        x = inq.getRight();
        y = unr.getTop();
        hsv.scrollTo(x, y);
        y=title.getBaseline();
        x=scrollView1.getRight();
        scrollView1.smoothScrollTo(x,y);
        nccTitle.setTextColor(getResources().getColor(R.color.ncc));
        nth_tv.setTextColor(getResources().getColor(R.color.ncc));
        inq.setTextColor(getResources().getColor(R.color.ncc));
        unr.setTextColor(getResources().getColor(R.color.nccu));
        name.setText("Unravel");
        name.setVisibility(VISIBLE);
        imageView.setImageResource(R.drawable.unravel);
        detail.setVisibility(VISIBLE);
        desc.setText("Have you ever got lost in the maze of your own mind while solving a riddle?\n\n" +
                "PICT IEEE Student Branch presents Unravel!" +
                " Rise victorious by being the fastest team to find answers to riddles, puzzles, pop culture references and math equations in this campus-wide treasure hunt as time is of essence here.\n\n" +
                "Pace your wits, gather your squad and unravel the mysterious clues to find glory.\n\n" +
                "For registrations visit desks near the F-building during breaks and after college.\n\n" +
                "The game is on!\n");
//        desc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        name1.setText("Sreya Patranabish");
        num1.setText(getText(R.string.sreya_no));
        name2.setText("Nishita Pali");
        num2.setText(getText(R.string.nishita_no));
        Dat.setText("12th March, 2020\n");
        Regf.setText("Registration Fee: ");
        Reg.setText("Participate in a team of two.\n" +"IEEE Members: ₹30/-\n" +"Non-IEEE Members: ₹50/-\n");
        Cont.setText("Contact: ");
    }


    private void revealFilterSheet(int y) {
        mBinding.reveal.setVisibility(VISIBLE);

        Animator a = android.view.ViewAnimationUtils.createCircularReveal(
                mBinding.reveal,
                mDisplayMetrics.widthPixels / 2,
                (int) (y - mBinding.reveal.getY()) + getFabSize() / 2,
                getFabSize() / 2,
                mBinding.reveal.getHeight() * .7f);
        a.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBinding.list.setVisibility(VISIBLE);
            }
        });
        a.start();
    }

    public int getFinalXPosition() {
        return mDisplayMetrics.widthPixels / 2 - getFabSize() / 2;
    }

    public int getFinalYPosition() {
        int marginFromBottom = getFinalYPositionFromBottom();
        return mDisplayMetrics.heightPixels - marginFromBottom + getFabSize() / 2;
    }

    public void setDisplayMetrics() {
        mDisplayMetrics = getResources().getDisplayMetrics();
    }

    public int getFinalYPositionFromBottom() {
        return (int) (mDisplayMetrics.density * 250);
    }

    public int getFabSize() {
        return (int) (mDisplayMetrics.density * 56);
    }

    @SuppressLint("RestrictedApi")
    void accept()
    {
        if(position==-1 || nm.equals(HomeFragment.HOME))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment).commit();
        else
        if(nm.equals(ContactUsFragment.CONTACTUS))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contactUsFragment).commit();
        else
        if(nm.equals(BugFragment.BUG))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, bugFragment).commit();
        else
        if(nm.equals(FaqsFragment.FAQS))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, faqsFragment).commit();
        fabstatus=0;
        mBinding.fab.setVisibility(VISIBLE);
        mBinding.list.setVisibility(INVISIBLE);
        mBinding.scroll.setVisibility(INVISIBLE);

        mIsCancel = false;
        final int x = getFinalXPosition();
        final int y = getFinalYPosition();


        mBinding.applyFilters.setVisibility(INVISIBLE);
        //mBinding.cancel.setVisibility(INVISIBLE);
        mBinding.frame.setVisibility(INVISIBLE);
        mBinding.scrollView.setVisibility(INVISIBLE);

        final int startX = (int) mBinding.fab.getX();
        final int startY = (int) mBinding.fab.getY();

        mBinding.scroll.setVisibility(INVISIBLE);
        Animator reveal = android.view.ViewAnimationUtils.createCircularReveal(
                mBinding.reveal,
                mDisplayMetrics.widthPixels / 2,
                (int) (y - mBinding.reveal.getY()) + getFabSize() / 2,
                mBinding.reveal.getHeight() * .5f,
                getFabSize() / 2);



        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBinding.reveal.setVisibility(INVISIBLE);
                mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity
                        .this, R.color.colorAccent)));
                mBinding.fab.setElevation(mDisplayMetrics.density * 4);

            }
        });
        reveal.start();

        animateBottomSheet();

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();

                mBinding.fab.setX(
                        x - (x - startX - ((x - startX) * v))
                );

                mBinding.fab.setY(
                        y + (startY - y - ((startY - y) * (v * v)))
                );


            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBinding.fab.animate()
                        .rotationBy(720)
                        .setDuration(1000)
                        .start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        returnFabToInitialPosition();
                        mBinding.bottomListBackground.setVisibility(INVISIBLE);
                    }
                }, 200);
            }
        });
        animator.start();
    }
    public void acceptFilters(View view) {
        accept();
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
//        mBinding.fab.setVisibility(VISIBLE);
//        mBinding.list.setVisibility(INVISIBLE);
//        mBinding.scroll.setVisibility(INVISIBLE);
//
//        mIsCancel = true;
//        final int x = getFinalXPosition();
//        final int y = getFinalYPosition();
//
//
//        mBinding.applyFilters.setVisibility(INVISIBLE);
//        //mBinding.cancel.setVisibility(INVISIBLE);
//        mBinding.frame.setVisibility(INVISIBLE);
//        mBinding.scrollView.setVisibility(INVISIBLE);
//
//        final int startX = (int) mBinding.fab.getX();
//        final int startY = (int) mBinding.fab.getY();
//
//        mBinding.sheetTop.setVisibility(INVISIBLE);
//        Animator reveal = android.view.ViewAnimationUtils.createCircularReveal(
//                mBinding.reveal,
//                mDisplayMetrics.widthPixels / 2,
//                (int) (y - mBinding.reveal.getY()) + getFabSize() / 2,
//                mBinding.reveal.getHeight() * .5f,
//                getFabSize() / 2);
//
//        reveal.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                mBinding.reveal.setVisibility(INVISIBLE);
//                mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity
//                        .this, R.color.colorAccent)));
//                mBinding.fab.setElevation(mDisplayMetrics.density * 4);
//
//            }
//        });
//        reveal.start();
//
//        animateBottomSheet();
//
//        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float v = (float) animation.getAnimatedValue();
//
//                mBinding.fab.setX(
//                        x - (x - startX - ((x - startX) * v))
//                );
//
//                mBinding.fab.setY(
//                        y + (startY - y - ((startY - y) * (v * v)))
//                );
//
//
//            }
//        });
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                mBinding.fab.animate()
//                        .rotationBy(360)
//                        .setDuration(1000)
//                        .start();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        returnFabToInitialPosition();
//                        mBinding.bottomListBackground.setVisibility(INVISIBLE);
//                    }
//                }, 1000);
//            }
//        });
//        animator.start();
    }

    private void animateBottomSheet() {
        Drawable d = mBinding.bottomListBackground.getBackground();
        final GradientDrawable gd = (GradientDrawable) d;


        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                mBinding.bottomListBackground.getLayoutParams();

        final int startWidth = mBinding.bottomListBackground.getWidth();
        final int startHeight = mBinding.bottomListBackground.getHeight();
        final int startY = (int) mBinding.bottomListBackground.getY();


        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                gd.setCornerRadius(mDisplayMetrics.density * 50 * v);

                int i = (int) (startWidth - (startWidth - getFabSize()) * v);
                params.width = i;
                params.height = (int) (startHeight - (startHeight - getFabSize()) * v);
                mBinding.bottomListBackground.setY(getFinalYPosition() + (startY
                        - getFinalYPosition()) - ((startY - getFinalYPosition()) * v));

                mBinding.bottomListBackground.requestLayout();
            }
        });
        animator.start();
    }

    private void returnFabToInitialPosition() {
       /* if(position==-1 || nm.equals(HomeFragment.HOME) || position==185)
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment).commit();
        else
            if(nm.equals(ContactUsFragment.CONTACTUS)|| position==185)
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contactUsFragment).commit();
            else
                if(nm.equals(BugFragment.BUG) || position==185)
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, bugFragment).commit();
                else
                    if(nm.equals(FaqsFragment.FAQS) || position==185)
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, faqsFragment).commit();*/
        final int x = getFinalXPosition();
        final int y = getFinalYPosition();
        resetBottomList = true;




        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();

                mBinding.fab.setX(
                        x + ((mStartX - x) * v)
                );

                mBinding.fab.setY(
                        (float) (y + (mStartY - y) * (Math.pow(v, .5f)))
                );
            }
        });
        animator.start();
    }
    public void call(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + num1.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void call2(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +"+91 7701864159"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit" ,Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    public void call1(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + num2.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void call3(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +"+91 8999412539"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void call4(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +"+91 9423929049"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void call5(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +"+91 7397865565"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void call6(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +"+91 8999426215"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public int getStatusBarHeight() {
        return (int) (mDisplayMetrics.density * 24);
    }





    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem("CLOSE", R.drawable.ic_close_black_24dp, "Close");
        list.add(menuItem0);
        SlideMenuItem menuItem1= new SlideMenuItem(HomeFragment.HOME , R.drawable.ic_home_black_24dp, "Home");
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContactUsFragment.CONTACTUS, R.drawable.ic_call_black_24dp, "Contact Us");
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(BugFragment.BUG, R.drawable.ic_bug_report_black_24dp, "Developers");
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(FaqsFragment.FAQS, R.drawable.ic_question_answer_black_24dp, "FAQs");
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem("Website", R.drawable.ic_web_black_24dp, "Website");
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem("Share", R.drawable.ic_share_black_24dp, "Share");
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem("Feedback", R.drawable.ic_feedback_black_24dp, "Feedback");
        list.add(menuItem7);


    }


    private void setActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                Uri web=Uri.parse("https://docs.google.com/document/d/e/2PACX-1vT8t4hcnFxSfFvcZ3WvVnXALUfbGVDW6wcwgIgxmx00NuI50ICztZNjVkceL6BhLw1vABHq1COTFF9P/pub");
                Intent intent=new Intent(Intent.ACTION_VIEW, web);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public ScreenShotable rep(Resourceble slideMenuItem, ScreenShotable screenShotable, int position)
    {
        switch (slideMenuItem.getName()) {
            case "CLOSE":
                //nm=ContentFragment.CLOSE;
                return screenShotable;
            case HomeFragment.HOME:
                if(!(nm.equals(HomeFragment.HOME)))
                {
                    this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
                    View view = findViewById(R.id.content_frame);
                    int finalRadius = Math.max(view.getWidth(), view.getHeight());
                    Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, position, 0, finalRadius);
                    animator.setInterpolator(new AccelerateInterpolator());
                    animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
                    findViewById(R.id.content_overlay).setBackground(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
                    animator.start();
                    homeFragment = HomeFragment.newInstance(this.res);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment).commit();
                    nm=HomeFragment.HOME;
                    return homeFragment;
                }
                else
                    return screenShotable;
            case ContactUsFragment.CONTACTUS:
                if(!(nm.equals(ContactUsFragment.CONTACTUS))) {
                    this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
                    View view1 = findViewById(R.id.content_frame);
                    int finalRadius1 = Math.max(view1.getWidth(), view1.getHeight());
                    Animator animator1 = ViewAnimationUtils.createCircularReveal(view1, 0, position, 0, finalRadius1);
                    animator1.setInterpolator(new AccelerateInterpolator());
                    animator1.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

                    findViewById(R.id.content_overlay).setBackground(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
                    animator1.start();
                    contactUsFragment = ContactUsFragment.newInstance(this.res);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contactUsFragment).commit();
                    nm = ContactUsFragment.CONTACTUS;
                    return contactUsFragment;
                }
                else
                    return screenShotable;
            case BugFragment.BUG:
                if(!(nm.equals(BugFragment.BUG))) {
                    this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
                    View view2 = findViewById(R.id.content_frame);
                    int finalRadius2 = Math.max(view2.getWidth(), view2.getHeight());
                    Animator animator2 = ViewAnimationUtils.createCircularReveal(view2, 0, position, 0, finalRadius2);
                    animator2.setInterpolator(new AccelerateInterpolator());
                    animator2.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

                    findViewById(R.id.content_overlay).setBackground(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
                    animator2.start();
                    bugFragment = BugFragment.newInstance(this.res);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, bugFragment).commit();
                    nm=BugFragment.BUG;
                    return bugFragment;
                }
                else
                    return screenShotable;
            case FaqsFragment.FAQS:
                if(!(nm.equals(FaqsFragment.FAQS))) {
                    this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
                    View view3 = findViewById(R.id.content_frame);
                    int finalRadius3 = Math.max(view3.getWidth(), view3.getHeight());
                    Animator animator3 = ViewAnimationUtils.createCircularReveal(view3, 0, position, 0, finalRadius3);
                    animator3.setInterpolator(new AccelerateInterpolator());
                    animator3.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

                    findViewById(R.id.content_overlay).setBackground(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
                    animator3.start();
                    faqsFragment = FaqsFragment.newInstance(this.res);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, faqsFragment).commit();
                    nm=FaqsFragment.FAQS;
                    return faqsFragment;
                }
                else
                    return screenShotable;
            case "Website":
                Uri web=Uri.parse("http://ctd.pictieee.in/");
                Intent intent=new Intent(Intent.ACTION_VIEW, web);
                startActivity(intent);
                return screenShotable;
            case "Share":
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.cricbuzz.android&hl=en_IN" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return screenShotable;
            case "Feedback":
                Uri web1=Uri.parse("https://www.facebook.com/");
                Intent intent1=new Intent(Intent.ACTION_VIEW, web1);
                startActivity(intent1);
                return screenShotable;
            default:
                return screenShotable;
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        //View view=new View();
        //acceptFilters(view);
        //floatingActionButton.setVisibility(VISIBLE);
       // title.setVisibility(INVISIBLE);
        this.slideMenuItem=slideMenuItem;
        this.screenShotable=screenShotable;
        this.position=position;
        System.out.println(position);
        if(fabstatus==1 && !(slideMenuItem.getName().equals("CLOSE")) )
            accept();
        return rep(slideMenuItem,screenShotable,position);




    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
}
