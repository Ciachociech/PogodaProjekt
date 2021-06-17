package pl.zzpwj.app;

import lombok.SneakyThrows;
import pl.zzpwj.logic.AppDataReceiver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    AppDataReceiver appDataReceiver = new AppDataReceiver();

    private JPanel appPanel;
    private JButton showHistoryBtn;
    private JButton findPointBtn;
    private JButton showForecastBtn;
    private JPanel mainDataPanel;
    private JTextField pointTextField;
    private JComboBox chooseCBox;
    private JPanel showInformationField;
    private JTextArea informationTextArea;

    public App() {
        showHistoryBtn.setText("Check history");
        findPointBtn.setText("Find your city");
        showForecastBtn.setText("City forecast");
        pointTextField.setToolTipText("");
        informationTextArea.setText("Here will be information about weather, choose proper option");

        showHistoryBtn.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                pointTextField.setText("");
                appDataReceiver.loadHistoryContainer();
                appDataReceiver.fillHistoryComboBox(chooseCBox);
                informationTextArea.setText(appDataReceiver.setHistoryInformationText());
            }
        });
        findPointBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        showForecastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("PogodaProject - ZZPWJ");
        jFrame.setContentPane(new App().appPanel);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
