package part3.voyager.gui;

import part3.voyager.world.Way;
import part3.voyager.world.World;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Диалог информации обо всех путях
 * @author Михаил Глухих
 */
public class WaysInfoDialog extends javax.swing.JDialog {
    /** Мир */
    private World world;
    /** Номер первого пути в таблице */
    private int startWay;

    /** Заполнение таблицы */
    private void fillWayTable() {
        int wayix = 0;
        for (Way way: world.getWays()) {
            if (wayix < startWay) {
                wayix++;
                continue;
            }
            int row = wayix - startWay;
            if (row >= wayTable.getRowCount())
                break;
            wayTable.setValueAt(way.getStartName(), row, 0);
            wayTable.setValueAt(way.getFinishName(), row, 1);
            wayTable.setValueAt(way.getKind().toString(), row, 2);
            wayTable.setValueAt(way.getCost(), row, 3);
            wayTable.setValueAt(way.getTime(), row, 4);
            wayix++;
        }
        jShownLabel.setText("Показаны пути с " + (startWay+1) +
                " по " + wayix + " из существующих " + world.getWays().size());
    }

    /**
     * Инициализация таблицы
     */
    private void initWayTable() {
        startWay = 0;
        fillWayTable();
    }

    /**
     * Конструктор
     * @param parent родительский фрейм
     * @param modal модальность
     * @param world мир
     */
    public WaysInfoDialog(java.awt.Frame parent, boolean modal, World world) {
        super(parent, modal);
        this.world = world;
        initComponents();
        initWayTable();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        wayTable = new JTable();
        JButton next = new JButton();
        JButton prev = new JButton();
        jShownLabel = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Существующие пути");

        wayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Начало", "Конец", "Тип", "Стоимость", "Время"
            }
        ));
        wayTable.setName("wayTable"); // NOI18N
        jScrollPane1.setViewportView(wayTable);

        next.setText("Следующие");
        next.addActionListener(this::onNext);

        prev.setText("Предыдущие");
        prev.addActionListener(this::onPrev);

        jShownLabel.setText("jLabel1");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jShownLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(next)
                        .addGap(51, 51, 51)
                        .addComponent(prev)))
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jShownLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(prev)
                    .addComponent(next))
                .addContainerGap())
        );

        pack();
    }

    /**
     * Обработчик клавиши "Предыдущие"
     * @param evt событие
     */
    private void onPrev(ActionEvent evt) {
        if (startWay==0)
            return;
        startWay -= wayTable.getRowCount();
        if (startWay < 0)
            startWay = 0;
        fillWayTable();
    }
    /**
     * Обработчик клавиши "Следующие"
     * @param evt событие
     */
    private void onNext(ActionEvent evt) {
        if (startWay + wayTable.getRowCount() >= world.getWays().size())
            return;
        startWay += wayTable.getRowCount();
        fillWayTable();
    }

    private JLabel jShownLabel;
    private JTable wayTable;

}
