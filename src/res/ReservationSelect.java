package res;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReservationSelect extends JDialog {
    private JTable table;
    private DefaultTableModel dtm;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservationSelect dialog = new ReservationSelect();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog.
     */
    public ReservationSelect() {
        setTitle("예약확인");
        setBounds(100, 100, 859, 490);
        getContentPane().setLayout(null);

        JButton btnDelete = new JButton("예약취소");
        // 예약 취소 버튼 클릭 시 동작
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "예약을 선택해주세요.");
                    return;
                }

                String sNum = table.getValueAt(selectedRow, 0).toString();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root",
                            "yourso0");
                    String sql = "DELETE FROM restbl WHERE sNum = ?";
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, sNum);
                    int result = pstmt.executeUpdate();

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.");
                        dtm.removeRow(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(null, "예약 취소에 실패했습니다.");
                    }

                    pstmt.close();
                    con.close();
                } catch (ClassNotFoundException e1) {
                    System.out.println("JDBC 드라이버 로드 에러");
                } catch (SQLException e1) {
                    System.out.println("DB 연결 오류");
                }
            }
        });

        btnDelete.setBounds(699, 398, 134, 45);
        getContentPane().add(btnDelete);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 10, 675, 433);
        getContentPane().add(scrollPane);
        String columnNames[] = { "시리얼", "이름","전화번호", "생년월일", "진료과", "진료의", "예약날짜", "예약시간" };
        dtm = new DefaultTableModel(columnNames, 0);
        table = new JTable(dtm);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              
            }
        });









        scrollPane.setViewportView(table);
        
        

        JButton btnNum = new JButton("예약확인");
        // 시리얼 넘버 찾기 버튼 클릭 시 동작
        btnNum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FindNum();
            }
        });
        btnNum.setBounds(699, 10, 134, 45);
        getContentPane().add(btnNum);

        JButton btnModify = new JButton("예약 변경");
        btnModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "예약을 선택해주세요.");
                    return;
                }

                String sNum = table.getValueAt(selectedRow, 0).toString();
                ResModify resModify = new ResModify(sNum);
                resModify.setModal(true);
                resModify.setVisible(true);
            }
        });
        btnModify.setBounds(699, 65, 134, 45);
        getContentPane().add(btnModify);

    }



    // 이름과 전화번호로 시리얼 넘버 조회
    protected void FindNum() {
        dtm.setRowCount(0);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root", "yourso0");
            String name = JOptionPane.showInputDialog(null, "이름을 입력하세요.");
            String mobile = JOptionPane.showInputDialog(null, "전화번호를 입력하세요.");
            String sql = "SELECT * FROM restbl WHERE name = ? AND mobile = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, mobile);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) });
            }

            pstmt.close();
            con.close();
        } catch (ClassNotFoundException e1) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e1) {
            System.out.println("DB 연결 오류");
        }
    }
}

           

