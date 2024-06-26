package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GUI {
    public static void startProgram() {
        JFrame frame = new JFrame("Импорт файла");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель для размещения элементов
        JPanel panel = new JPanel();
        frame.add(panel);

        // Создаем кнопку для выбора файла
        JButton button = new JButton("Выбрать базу данных");
        panel.add(button);

        // Создаем поле для отображения пути к файлу

        // Добавляем обработчик события нажатия на кнопку

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создаем объект для выбора файла
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                // Открываем диалоговое окно для выбора файла
                int result = fileChooser.showOpenDialog(frame);

                // Если файл был выбран
                // Получаем выбранный файл
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                Calculation calculation = new Calculation(ReactorDatabase.getReactors(selectedFile.getAbsolutePath()));
                if (calculation.getValuePerCountry().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ошибка импорта", "Информационное сообщение", JOptionPane.INFORMATION_MESSAGE);
                }
                try {
                    ExcelWriter.excelWriter(calculation.getValuePerOwner(),"владелец");
                    ExcelWriter.excelWriter(calculation.getValuePerRegion(),"регион");
                    ExcelWriter.excelWriter(calculation.getValuePerCountry(),"страна");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);

                }
                frame.dispose();
                // Создать информационное сообщение
                JOptionPane.showMessageDialog(null, "Данные выгружены в эксель", "Информационное сообщение", JOptionPane.INFORMATION_MESSAGE);

            }
        });


        // Отображаем фрейм
        frame.setVisible(true);
    }
}