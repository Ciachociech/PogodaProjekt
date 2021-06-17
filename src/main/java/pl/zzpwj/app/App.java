package pl.zzpwj.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
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
        showHistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("PogodaProject - ZZPWJ");
        jFrame.setContentPane(new App().appPanel);

    }
}
