package cn.org.nercita.agriculturalconsultant.nav;


import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import net.qiujuer.genius.ui.drawable.shape.BorderShape;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.LoginActivity;
import cn.org.nercita.agriculturalconsultant.main.NetWork.NetWorkFragment;
import cn.org.nercita.agriculturalconsultant.main.communicate.CommunicateBaseFragment;
import cn.org.nercita.agriculturalconsultant.main.home.HomeFragment;
import cn.org.nercita.agriculturalconsultant.main.me.MeFragment;
import cn.org.nercita.agriculturalconsultant.main.service.fragment.ServiceFragment;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;


/**
 * 首页Fragment的管理
 */
public class NavFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.nav_item_news)
    NavigationButton mNavNews;
    @Bind(R.id.nav_item_tweet)
    NavigationButton mNavTweet;
    @Bind(R.id.nav_item_explore)
    NavigationButton mNavExplore;
    @Bind(R.id.nav_item_four)
    NavigationButton mNavFour;
    @Bind(R.id.nav_item_me)
    NavigationButton mNavMe;

    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;

    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    /**
     * 初始化首页五个界面
     *
     * @param root
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:26
     */
    @SuppressWarnings("deprecation")
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(R.color.white)),
                lineDrawable
        });
        root.setBackgroundDrawable(layerDrawable);

        mNavNews.init(R.drawable.tab_icon_one,
                R.string.one,
                HomeFragment.class);

        mNavTweet.init(R.drawable.tab_icon_two,
                R.string.two,
                NetWorkFragment.class);

        mNavExplore.init(R.drawable.tab_icon_three,
                R.string.three,
                CommunicateBaseFragment.class);

        mNavFour.init(R.drawable.tab_icon_four,
                R.string.four,
                ServiceFragment.class);

        mNavMe.init(R.drawable.tab_icon_five,
                R.string.five,
                MeFragment.class);

    }

    @OnClick({R.id.nav_item_news, R.id.nav_item_tweet,
            R.id.nav_item_explore, R.id.nav_item_four, R.id.nav_item_me})
    @Override
    public void onClick(View v) {
        if (v instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) v;
            String tag = nav.getTag();
            //如果没有登录，那么跳转到登录页面  2017.4.12 范博文
            if (tag.contains(CommunicateBaseFragment.class.getSimpleName()) || tag.contains
                    (MeFragment.class.getSimpleName()) || tag.contains(NetWorkFragment.class
                    .getSimpleName())) {
                String id = SPUtil.getString(getContext(), AppConfig.ID, "");
                String nickname = SPUtil.getString(getContext(), AppConfig.NICKNAME, "");
                if (TextUtils.isEmpty(id)||TextUtils.isEmpty(nickname)){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    ToastUtil.showShort(getContext(),"您未登录，请登录");
                    return;
                }
            }
            /*****************************************************/
            doSelect(nav);
        }
//        else if (v.getId() == R.id.nav_item_tweet_pub) {
//            Toast.makeText(getContext(),"什么", Toast.LENGTH_SHORT).show();
////            TweetPublishActivity.show(getContext(), mRoot.findViewById(R.id
// .nav_item_tweet_pub));
//        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId,
                      OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(mNavNews);
    }

    public void select(int index) {
        if (mNavMe != null)
            doSelect(mNavMe);
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {
        // If the new navigation is me info fragment, we intercept it
        /*
        if (newNavButton == mNavMe) {
            if (interceptMessageSkip())
                return;
        }
        */
        Log.e("TAG",newNavButton.getTag()+"--");

        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }

    /**
     * 拦截底部点击，当点击个人按钮时进行消息跳转
     */
//    private boolean interceptMessageSkip() {
//        NoticeBean bean = NoticeManager.getNotice();
//        if (bean.getAllCount() > 0) {
//            if (bean.getLetter() + bean.getMention() + bean.getReview() > 0)
//                UserMessageActivity.show(getActivity());
//            else
//                UserFansActivity.show(getActivity(), AccountHelper.getUserId());
//            return true;
//        }
//        return false;
//    }
    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }


    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
