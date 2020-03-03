package yalantis.com.sidemenu.sample;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GettingDeviceTokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String deviceToken= FirebaseInstanceId.getInstance().getToken();
    }
}
