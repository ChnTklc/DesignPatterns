package iztechify.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import iztechify.controllers.UserController;
import iztechify.models.user.Playlist;
import iztechify.models.user.PlaylistEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

public class UserWindow extends AbstractWindow {
    private JPanel root;
    private JList<String> friendList;
    private JList<String> playlistList;
    private JTable songTable;
    private JButton addFriendButton;
    private JButton addPlaylistButton;
    private JButton addSongButton;
    private JButton removeSongButton;

    private DefaultListModel<String> friendListModel = new DefaultListModel<>();
    private DefaultListModel<String> playlistListModel = new DefaultListModel<>();
    private DefaultTableModel songTableModel
            = new DefaultTableModel(new Object[]{"Title", "Album", "Artist"}, 0);

    private UserController userController;

    public UserWindow(String username, UserController userController) {
        super("User " + username);
        this.userController = userController;
        songTable.setModel(songTableModel);
        friendListModel.addElement("Me");
        friendList.setModel(friendListModel);
        playlistList.setModel(playlistListModel);

        friendList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playlistListModel.clear();
                songTableModel.setRowCount(0);

                if(e.getClickCount() != 2)    // double click
                    return;
                int idx = friendList.locationToIndex(e.getPoint());
                if(idx == -1)
                    return;
                onFriendSelected(idx);
            }
        });

        playlistList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                songTableModel.setRowCount(0);

                if(e.getClickCount() != 2)    // double click
                    return;
                int idx = playlistList.locationToIndex(e.getPoint());
                if(idx == -1)
                    return;
                onPlaylistSelected(idx);
            }
        });

        addFriendButton.addActionListener(e -> userController.addFriend());
        addPlaylistButton.addActionListener(e -> {
            String name = userController.addPlaylist();
            if(name != null){
                this.userController.getThisUser().getPlaylist(name).addObserver(this);
            }
        });
        addSongButton.addActionListener(e -> userController.addSong());
        removeSongButton.addActionListener(e -> {
            int selectedRow = songTable.getSelectedRow();
            Vector songData = (Vector) songTableModel.getDataVector().get(selectedRow);
            userController.removeSong(playlistList.getSelectedValue(), songData);
            songTableModel.removeRow(selectedRow);
        });

        for(String s : userController.getFriends()) {
            friendListModel.addElement(s);
        }
    }

    private void onFriendSelected(int friendIndex) {
        List<Playlist> playlists;
        if(friendIndex == 0) {      // my playlist
            addPlaylistButton.setEnabled(true);
            addSongButton.setEnabled(true);
            removeSongButton.setEnabled(true);
            playlists = userController.getPlaylists();
        } else {
            addPlaylistButton.setEnabled(false);
            addSongButton.setEnabled(false);
            removeSongButton.setEnabled(false);
            String friend = friendListModel.get(friendIndex);
            playlists = userController.getFriendPlaylists(friend);
        }

        for(Playlist playlist : playlists)
            playlistListModel.addElement(playlist.getName());
    }

    private void onPlaylistSelected(int playlistIndex) {
        /*List<PlaylistEntry> entries;
        if(friendList.getSelectedIndex() == 0) {
            entries = userController.getPlaylist(playlistListModel.get(playlistIndex)).getEntries();
        } else {
            entries = userController.getFriendPlaylist(friendList.getSelectedValue(),
                    playlistListModel.get(playlistIndex))
                    .getEntries();
        }

        for(PlaylistEntry e : entries)
            songTableModel.addRow(new Object[]{e.getSongName(), e.getAlbumName(), e.getArtistName()});*/
    }

    @Override
    public void showWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));
        setContentPane(root);
        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        // todo update friends, playlists and songs if and update happens
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new GridLayoutManager(4, 3, new Insets(10, 10, 10, 10), -1, -1));
        root.setAutoscrolls(false);
        friendList = new JList();
        friendList.setSelectionMode(0);
        root.add(friendList, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 300), null, 0, false));
        playlistList = new JList();
        playlistList.setSelectionMode(0);
        root.add(playlistList, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 300), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Friends");
        root.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Playlists");
        root.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addFriendButton = new JButton();
        addFriendButton.setText("Add Friend");
        root.add(addFriendButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addPlaylistButton = new JButton();
        addPlaylistButton.setText("Add Playlist");
        root.add(addPlaylistButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Songs");
        root.add(label3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        root.add(scrollPane1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        songTable = new JTable();
        songTable.setAutoResizeMode(0);
        songTable.setPreferredScrollableViewportSize(new Dimension(150, 300));
        songTable.setShowHorizontalLines(true);
        songTable.setShowVerticalLines(true);
        scrollPane1.setViewportView(songTable);
        addSongButton = new JButton();
        addSongButton.setText("Add Song");
        root.add(addSongButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeSongButton = new JButton();
        removeSongButton.setText("Remove Song");
        root.add(removeSongButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}
