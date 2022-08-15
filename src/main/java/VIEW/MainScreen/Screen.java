package VIEW.MainScreen;

import CONTROLLER.Appearance.DefaultFraming;

abstract class Screen {

    public void show(){
        new DefaultFraming().show(frame, main, 1000, 1000, "EXIT");
    }

    public void logout(){
        new DefaultFraming().defaultLogout(frame);
    }
}
