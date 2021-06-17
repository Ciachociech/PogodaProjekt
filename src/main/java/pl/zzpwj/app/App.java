package pl.zzpwj.app;

import lombok.SneakyThrows;
import pl.zzpwj.logic.AppDataReceiver;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class App {
    AppDataReceiver appDataReceiver = new AppDataReceiver();
    AppModeEnum appMode = AppModeEnum.NONE;
    int selectedOption;
    boolean validSearching = false;
    //String textFieldContent;

    private JPanel appPanel;
    private JButton showHistoryBtn;
    private JButton findPointBtn;
    private JButton showForecastBtn;
    private JPanel mainDataPanel;
    private JTextField pointTextField;
    private JComboBox<String> chooseCBox;
    private JPanel showInformationField;
    private JTextArea informationTextArea;

    public App() throws IOException {
        appDataReceiver.init();

        showHistoryBtn.setText("Check history");
        findPointBtn.setText("Find your city");
        showForecastBtn.setText("City forecast");
        pointTextField.setToolTipText("");
        informationTextArea.setText("Here will be information about weather, choose proper option");

        showHistoryBtn.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                appMode = AppModeEnum.HISTORY;
                chooseCBox.removeAllItems();

                showHistoryBtn.setEnabled(false);
                findPointBtn.setEnabled(true);
                showForecastBtn.setEnabled(true);
                pointTextField.setText("<choose a entry from the list for more details>");
                pointTextField.setEditable(false);

                appDataReceiver.loadHistoryContainer();
                addToComboBox(appDataReceiver.fillHistoryComboBox());
                informationTextArea.setText(appDataReceiver.getHistoryInformationText());
            }

            private void addToComboBox(String[] items) {
                for(String item : items) {
                    chooseCBox.addItem(item);
                }
            }
        });
        findPointBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appMode = AppModeEnum.SEARCH;
                chooseCBox.removeAllItems();

                showHistoryBtn.setEnabled(true);
                findPointBtn.setEnabled(false);
                showForecastBtn.setEnabled(true);
                pointTextField.setText("");
                pointTextField.setEditable(true);
                informationTextArea.setText(appDataReceiver.getSearchInformationText());
            }

        });
        showForecastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appMode = AppModeEnum.FORECAST;
                chooseCBox.removeAllItems();

                showHistoryBtn.setEnabled(true);
                findPointBtn.setEnabled(true);
                showForecastBtn.setEnabled(false);
                pointTextField.setText("");
                pointTextField.setEditable(true);
                informationTextArea.setText(appDataReceiver.getSearchInformationText());

            }
        });
        chooseCBox.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(appMode) {
                    case NONE: { break; }
                    case HISTORY: {
                        if(chooseCBox.getSelectedIndex() >= 0 && chooseCBox.getSelectedIndex() < chooseCBox.getItemCount()) {
                            informationTextArea.setText(appDataReceiver.getHistoryDataTextArea(chooseCBox.getSelectedIndex()));
                        }
                        break;
                    }
                    case SEARCH: {
                        if(validSearching) {
                            if(chooseCBox.getItemCount() > 0) {
                                informationTextArea.setText(appDataReceiver.getSearchDataTextArea(chooseCBox.getSelectedIndex()));

                            }
                            else {
                                validSearching = false;
                            }
                        }
                        else {
                            validSearching = true;
                        }

                        break;
                    }
                    case FORECAST: { break; }
                }
            }

        });
        pointTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                chooseCBox.removeAllItems();
                addToComboBox(appDataReceiver.fillPointsComboBox(pointTextField.getText()));
            }

            private void addToComboBox(String[] items) {
                for(String item : items) {
                    chooseCBox.addItem(item);
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame("PogodaProject - ZZPWJ");
        jFrame.setContentPane(new App().appPanel);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
