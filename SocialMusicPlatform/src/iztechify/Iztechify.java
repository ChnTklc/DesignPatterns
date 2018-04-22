package iztechify;

import iztechify.controllers.LoginController;
import iztechify.models.Users;
import iztechify.models.Music;
import iztechify.util.GSON;
import iztechify.views.LoginWindow;
import iztechify.views.Window;

import java.util.Arrays;

public class Iztechify {
    public static void main(String... args) {
        //Models
        Music music = new Music(Arrays.asList(GSON.loadMusic()));
        Users users = new Users(Arrays.asList(GSON.loadUsers()));

        //Controllers
        LoginController loginController = new LoginController(music, users);

        //Views
        Window loginWindow = new LoginWindow(loginController);

        loginWindow.showWindow();
    }
}
