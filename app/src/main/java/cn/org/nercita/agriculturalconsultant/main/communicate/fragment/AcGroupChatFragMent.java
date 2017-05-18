package cn.org.nercita.agriculturalconsultant.main.communicate.fragment;

import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * Created by nercita on 2017/4/10.
 */
/**
 * 描述：群组聊天界面
 * @author GaoWenXu
 * @date 2017/4/10 13:56
 * @version v1.0.0
 */
public class AcGroupChatFragMent extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper{
    private static final int REQUEST_CODE_CONTEXT_MENU = 14;
    public static final int GROUPMANAGER = 100;

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
