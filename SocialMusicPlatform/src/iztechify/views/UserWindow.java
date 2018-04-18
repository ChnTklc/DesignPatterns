package iztechify.views;

import javax.swing.*;

public class UserWindow extends AbstractWindow {
    private JFrame fu;
    private int counterU = 0, counterM = 0;

    public UserWindow(String username) {
        // todo: check is the user exist before show the profile
        super(username + " Profile");
        fu = new JFrame(username + " Profile");

        JTextField searchUser = new JTextField();
        searchUser.setBounds(0, 0, 280, 40);
        JButton addUser = new JButton("Add User");
        addUser.setBounds(280, 0, 100, 40);

        addUser.addActionListener(e -> {
            addNewUser(searchUser.getText());
            counterU++;
        });

        JTextField searchMusic = new JTextField();
        searchMusic.setBounds(500, 0, 200, 40);
        JButton addMusic = new JButton("Add Music");
        addMusic.setBounds(700, 0, 100, 40);

        addMusic.addActionListener(e -> {
            addNewMusic(searchMusic.getText());
            counterM++;
        });

        fu.add(searchUser);
        fu.add(addUser);
        fu.add(searchMusic);
        fu.add(addMusic);
        fu.setSize(800, 800);
        fu.setLayout(null);
        fu.setVisible(true);
    }

    public void addNewUser(String username) {
        // todo: check if user exists
        JLabel user = new JLabel();
        user.setBounds(0, 40 + (counterU * 20), 200, 20);
        user.setText("- " + username);

        JButton showPlaylist = new JButton("Show Playlist");
        showPlaylist.setBounds(200, 40 + (counterU * 20), 100, 20);
        showPlaylist.addActionListener(e -> {
            showUserPlaylist(user);
        });

        JButton remove = new JButton("Remove");
        remove.setBounds(300, 40 + (counterU * 20), 80, 20);
        remove.addActionListener(e -> {
            removeUser(user, remove, showPlaylist);
        });

        fu.add(user);
        fu.add(showPlaylist);
        fu.add(remove);
        fu.revalidate();
        fu.repaint();
    }

    public void removeUser(JLabel user, JButton removeButton, JButton showPlaylist) {
        // todo: remove the user who deletes from the deleted user's friends list
        fu.remove(user);
        fu.remove(showPlaylist);
        fu.remove(removeButton);
        fu.revalidate();
        fu.repaint();
    }

    public void addNewMusic(String musicName) {
        // todo: check if music exists
        JLabel music = new JLabel();
        music.setBounds(500, 40 + (counterM * 20), 220, 20);
        music.setText("- " + musicName);

        JButton remove = new JButton("Remove");
        remove.setBounds(720, 40 + (counterM * 20), 80, 20);
        remove.addActionListener(e -> {
            // todo: remove music click
            removeMusic(music, remove);
        });

        fu.add(music);
        fu.add(remove);
        fu.revalidate();
        fu.repaint();
    }

    public void removeMusic(JLabel music, JButton removeButton) {
        // Call if music deleted from json
        fu.remove(music);
        fu.remove(removeButton);
        fu.revalidate();
        fu.repaint();
    }

    public void showUserPlaylist(JLabel user) {
        // todo: get user's playlist show
        /*
        JFrame playlist = new JFrame(user + "'s Playlist")
        Label music;
        // loop for user's playlist to add musics as labels on frame
        music = new JLabel();
        music.setBounds(0, 40 + (counterM * 20), 220, 20);
        music.setText("- " + musicName);
        playlist.add(music)
        //finish loop
        fu.setSize(300, 500);
        fu.setLayout(null);
        fu.setVisible(true);
        */

    }
}