package iztechify.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import iztechify.models.Music;
import iztechify.controllers.AdminController;
import iztechify.models.music.Album;
import iztechify.models.music.Artist;
import iztechify.models.music.Song;
import iztechify.util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class AdminWindow extends AbstractWindow {
    private AdminController adminController;
    private JList<String> artistList;
    private JList<String> albumList;
    private JList<String> songList;
    private JButton artistDeleteButton;
    private JButton albumDeleteButton;
    private JButton songDeleteButton;
    private JPanel root;
    private JButton artistCreateButton;
    private JButton albumCreateButton;
    private JButton newSongButton;
    private JButton editArtistButton;
    private JButton editAlbumButton;
    private JButton editSongButton;

    private DefaultListModel<String> artistListModel = new DefaultListModel<>();
    private DefaultListModel<String> albumListModel = new DefaultListModel<>();
    private DefaultListModel<String> songListModel = new DefaultListModel<>();

    private Music music;

    public AdminWindow(AdminController adminController, Music music) {
        super("Admin");
        this.adminController = adminController;
        this.music = music;

        artistList.setModel(artistListModel);
        albumList.setModel(albumListModel);
        songList.setModel(songListModel);

        addSelectListeners();
        addDeleteListeners();
        addEditListeners();
        addCreateListeners();

        loadArtists();
    }

    private void addSelectListeners() {
        artistList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() != 2)
                    return;
                loadAlbums();
            }
        });

        albumList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() != 2)
                    return;
                loadSongs();
            }
        });
    }

    private void addDeleteListeners() {
        artistDeleteButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            if(artist == null)
                return;
            adminController.remove(artist);
            artistListModel.removeElement(artist);
        });
        albumDeleteButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            String album = albumList.getSelectedValue();
            if(album == null)
                return;
            adminController.remove(artist, album);
            albumListModel.removeElement(album);
        });
        songDeleteButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            String album = albumList.getSelectedValue();
            String song = songList.getSelectedValue();
            if(song == null)
                return;
            adminController.remove(artist, album, song);
            songListModel.removeElement(song);
        });
    }

    private void addEditListeners() {
        editArtistButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            if(artist == null)
                return;
            adminController.editArtist(artist);
        });
        editAlbumButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            String album = albumList.getSelectedValue();
            if(artist == null || album == null)
                return;
            adminController.editAlbum(artist, album);
        });
        editSongButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            String album = albumList.getSelectedValue();
            String song = songList.getSelectedValue();
            if(artist == null || album == null || song == null)
                return;
            adminController.editSong(artist, album, song);
        });
    }

    private void addCreateListeners() {
        artistCreateButton.addActionListener(e -> adminController.newArtist());
        albumCreateButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            if(artist == null)
                return;
            adminController.newAlbum(artist);
        });
        newSongButton.addActionListener(e -> {
            String artist = artistList.getSelectedValue();
            String album = albumList.getSelectedValue();
            if(artist == null || album == null)
                return;
            adminController.newSong(artist, album);
        });
    }

    private void loadArtists() {
        artistListModel.clear();
        albumListModel.clear();
        songListModel.clear();
        for(Artist artist : music.getArtists())
            artistListModel.addElement(artist.getName());
    }

    private void loadAlbums() {
        albumListModel.clear();
        songListModel.clear();
        for(Album album : music.getAlbums(artistList.getSelectedValue()))
            albumListModel.addElement(album.getTitle());
    }

    private void loadSongs() {
        songListModel.clear();
        for(Song song : music.getSongs(artistList.getSelectedValue(),
                albumList.getSelectedValue()))
            songListModel.addElement(song.getTitle());
    }

    @Override
    public void showWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(root);
        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        int artistSelected = artistList.getSelectedIndex();
        int albumSelected = albumList.getSelectedIndex();
        int songSelected = songList.getSelectedIndex();

        loadArtists();
        if(artistSelected != -1) {
            artistList.setSelectedIndex(Utility.clamp(0, artistSelected, artistListModel.size()));
            loadAlbums();
            if(albumSelected != -1) {
                albumList.setSelectedIndex(Utility.clamp(0, albumSelected, albumListModel.size()));
                loadSongs();
                if(songSelected != -1) {
                    songList.setSelectedIndex(Utility.clamp(0, songSelected, songListModel.size()));
                }
            }
        }
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
        root.setLayout(new GridLayoutManager(5, 3, new Insets(10, 10, 10, 10), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Artists");
        root.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Albums");
        root.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Songs");
        root.add(label3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        root.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        artistList = new JList();
        scrollPane1.setViewportView(artistList);
        final JScrollPane scrollPane2 = new JScrollPane();
        root.add(scrollPane2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        albumList = new JList();
        scrollPane2.setViewportView(albumList);
        final JScrollPane scrollPane3 = new JScrollPane();
        root.add(scrollPane3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        songList = new JList();
        scrollPane3.setViewportView(songList);
        artistDeleteButton = new JButton();
        artistDeleteButton.setText("Delete");
        root.add(artistDeleteButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        albumDeleteButton = new JButton();
        albumDeleteButton.setText("Delete");
        root.add(albumDeleteButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        songDeleteButton = new JButton();
        songDeleteButton.setText("Delete");
        root.add(songDeleteButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        artistCreateButton = new JButton();
        artistCreateButton.setText("New Artist");
        root.add(artistCreateButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        albumCreateButton = new JButton();
        albumCreateButton.setText("New Album");
        root.add(albumCreateButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newSongButton = new JButton();
        newSongButton.setText("New Song");
        root.add(newSongButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editArtistButton = new JButton();
        editArtistButton.setText("Edit Artist");
        root.add(editArtistButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editAlbumButton = new JButton();
        editAlbumButton.setText("Edit Album");
        root.add(editAlbumButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editSongButton = new JButton();
        editSongButton.setText("Edit Song");
        root.add(editSongButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}
