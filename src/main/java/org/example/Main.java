package org.example;

import java.io.File;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

                // Создаем фрейм
                JFrame frame = new JFrame("Импорт файла");
                frame.setSize(300, 100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Создаем панель для размещения элементов
                JPanel panel = new JPanel();
                frame.add(panel);

                // Создаем кнопку для выбора файла
                JButton button = new JButton("Выбрать файл");
                panel.add(button);

                // Создаем поле для отображения пути к файлу

                // Добавляем обработчик события нажатия на кнопку

        button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Создаем объект для выбора файла
                        JFileChooser fileChooser = new JFileChooser();

                        // Открываем диалоговое окно для выбора файла
                        int result = fileChooser.showOpenDialog(frame);

                        // Если файл был выбран
                            // Получаем выбранный файл
                            File selectedFile = fileChooser.getSelectedFile();

                            // Сохраняем путь к файлу в поле
                        try{
                        LoadReatorsJson loadReatorsJson = new LoadReatorsJson();
                        LoadReactorsXml loadReactorsXml = new LoadReactorsXml();
                        LoadReactorsYaml loadReactorsYaml= new LoadReactorsYaml();
                        loadReactorsYaml.setNextMessageSender(loadReactorsXml);
                        loadReactorsXml.setNextMessageSender(loadReatorsJson);
                        HashMap<String, Reactor> reactors = loadReactorsYaml.loadReactors(selectedFile.getAbsolutePath());
                        for (Reactor reactor : reactors.values()) {
                            System.out.println(reactor.classe);
                        }
                        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
                        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

                        // Добавляем реакторы в модель дерева
                        for (Map.Entry<String, Reactor> entry : reactors.entrySet()) {
                            // Создаем узел для реактора
                            DefaultMutableTreeNode reactorNode = new DefaultMutableTreeNode(entry.getKey());

                            // Добавляем атрибуты реактора в узел
                            reactorNode.add(new DefaultMutableTreeNode("Сгорание: " + entry.getValue().burnup));
                            reactorNode.add(new DefaultMutableTreeNode("Класс: " + entry.getValue().classe));
                            reactorNode.add(new DefaultMutableTreeNode("Электрическая мощность: " + entry.getValue().electricalCapacity));
                            reactorNode.add(new DefaultMutableTreeNode("Первая загрузка: " + entry.getValue().firstLoad));
                            reactorNode.add(new DefaultMutableTreeNode("КПД: " + entry.getValue().kpd));
                            reactorNode.add(new DefaultMutableTreeNode("Срок службы: " + entry.getValue().lifeTime));
                            reactorNode.add(new DefaultMutableTreeNode("Тепловая мощность: " + entry.getValue().terminalCapacity));
                            reactorNode.add(new DefaultMutableTreeNode("Тип файла: " + entry.getValue().fileType));
                            treeModel.insertNodeInto((MutableTreeNode) reactorNode, (MutableTreeNode) treeModel.getRoot(), treeModel.getChildCount(treeModel.getRoot()));
                            // Добавляем узел реактора в модель дерева
                        }

                        // Создаем дерево
                        JTree tree = new JTree(treeModel);

                        // Разрешаем только один выбор узлов
                        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                        // Создаем панель прокрутки для дерева
                        JScrollPane scrollPane = new JScrollPane(tree);

                        // Добавляем панель прокрутки в фрейм
                        JFrame frame = new JFrame("Дерево реакторов");
                        frame.add(scrollPane);

                        // Устанавливаем размер фрейма
                        frame.setSize(400, 300);

                        // Отображаем фрейм
                        frame.setVisible(true); }
                        catch (Exception ex) {
                                ex.printStackTrace();
                                System.out.println("Ошибка при импорте и чтении данных из файла");
                                String errorMessage = "Ошибка при импорте и чтении данных из файла";

                                // Отображение информационного сообщения
                                JOptionPane.showMessageDialog(null, errorMessage, "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                            }


                    }
                });


                // Отображаем фрейм
                frame.setVisible(true);

        }

    }
