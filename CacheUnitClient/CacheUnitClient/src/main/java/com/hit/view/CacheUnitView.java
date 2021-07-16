  package com.hit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Observable;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import com.hit.model.messages.SetTextInfoModelMessage;
import com.hit.model.messages.ShowInfoDialogModelMessage;
import com.hit.utils.Utils;
import com.hit.view.messages.ShowStatisticsViewMessage;
import com.hit.view.messages.UploadFileViewMessage;

public class CacheUnitView extends Observable implements View {

    final private JFileChooser fileChooser;
    private JTextArea infoJTextArea;
    private JFrame frame;

    public CacheUnitView() {
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }

                String extension = Utils.getExtension(f);
                if (extension != null && extension.equals(Utils.JSON)) {
                    return true;
                }

                return false;
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);
    }

    @SuppressWarnings("serial")
	public void start() {
        frame = new JFrame("MMU Project");
        frame.setSize(1040, 440);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel buttonsJPanel = new JPanel();
        jSplitPane.add(buttonsJPanel);
        buttonsJPanel.setBorder(new LineBorder(new Color(75, 150, 255), 2, true));
        buttonsJPanel.setBackground(null);


        JButton loadRequestButton = new JButton("Load a Request");
        loadRequestButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadRequestButton) {
                    int returnValue = fileChooser.showOpenDialog(buttonsJPanel);

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        updateChangeForController(new UploadFileViewMessage(file));
                    }
                    else {
                       
                        showAlertBox("File not chosen");
                    }
                }
            }
        });
        loadRequestButton.setToolTipText("you can Add/Delete/Get models");

        JButton showStatisticsJButton = new JButton("Show Statistics");
        showStatisticsJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showStatisticsJButton) {
                    setTextInfo("press on button");
                    updateChangeForController(new ShowStatisticsViewMessage());
                }
            }
        });

        JPanel infoJPanel = new JPanel();
        infoJTextArea = new JTextArea();
        infoJTextArea.setEditable(false);
       
        Font font = new Font("David", Font.BOLD, 30);
        infoJTextArea.setFont(font);
        	
        infoJPanel.add(infoJTextArea);
        jSplitPane.add(infoJPanel);

        buttonsJPanel.add(loadRequestButton);
        buttonsJPanel.add(showStatisticsJButton);

        frame.add(jSplitPane);
        frame.setVisible(true);

    }

    private void setTextInfo(String textInfo) {
        infoJTextArea.setText(textInfo);
    }

    private void showAlertBox(String alertInfo) {
        JOptionPane.showMessageDialog(frame, alertInfo);

    }

    public <T> void updateUIData(T t) {
        if (t instanceof ShowInfoDialogModelMessage) {
            ShowInfoDialogModelMessage showInfoDialog = (ShowInfoDialogModelMessage) t;
            String infoMessage = showInfoDialog.getInfoMessage();
            showAlertBox(infoMessage);
        } else if (t instanceof SetTextInfoModelMessage) {
            SetTextInfoModelMessage setTextInfoModelMessage = (SetTextInfoModelMessage) t;
            String infoMessage = setTextInfoModelMessage.getTextInfo();
            setTextInfo(infoMessage);
        } else {
            throw new RuntimeException("not implement updateUIDate " + t);
        }
    }

    private void updateChangeForController(Object o) {
        setChanged();
        notifyObservers(o);
    }
}