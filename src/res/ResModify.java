package res;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class ResModify extends JDialog {
	private JTable table;
	private JTextField tfDate;
	private JTextField tfName;
	private JTextField tfbirth;
	private JTextField tfMobile;
	private JComboBox cbdep;
	private JComboBox cbpra;
	private JComboBox cbtime;
	private JTextField tfNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResModify dialog = new ResModify();
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
	public ResModify() {
		setResizable(false);
		setTitle("예약하기");
		setBounds(100, 100, 387, 374);
		getContentPane().setLayout(null);
		
		JButton btnDate = new JButton("예약날짜");
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResCalendar resCalendar  = new ResCalendar();
				resCalendar.setModal(true);
				resCalendar.setVisible(true);
				tfDate.setText(resCalendar.getDate());
			}
		});
		btnDate.setBounds(158, 74, 89, 21);
		getContentPane().add(btnDate);
		
		tfDate = new JTextField();
		tfDate.setEnabled(false);
		tfDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		tfDate.setBounds(12, 74, 134, 21);
		getContentPane().add(tfDate);
		tfDate.setColumns(10);
		
		JButton btnRes = new JButton("예약하기");
		btnRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertRes();
				
			}
		});
		btnRes.setBounds(276, 294, 91, 33);
		getContentPane().add(btnRes);
		
		cbtime = new JComboBox();
		cbtime.setModel(new DefaultComboBoxModel(new String[] {"08:00~08:30", "08:30~09:00", "09:00~09:30", "09:30~10:00", "10:00~10:30", "10:30~11:00", "11:00~11:30", "11:30~12:00", "점심시간", "13:00~13:30", "13:30~14:00", "14:00~14:30", "14:30~15:00", "15:00~15:30", "15:30~16:00", "16:00~16:30", "16:30~17:00", "17:00~17:30", "17:30~18:00"}));
		cbtime.setBounds(259, 73, 108, 23);
		getContentPane().add(cbtime);
		
		tfName = new JTextField();
		tfName.setEnabled(false);
		tfName.setToolTipText("로그인해서 입력해주세요.");
		tfName.setBounds(74, 156, 96, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		tfbirth = new JTextField();
		tfbirth.setEnabled(false);
		tfbirth.setColumns(10);
		tfbirth.setBounds(84, 187, 113, 21);
		getContentPane().add(tfbirth);
		
		tfMobile = new JTextField();
		tfMobile.setEnabled(false);
		tfMobile.setColumns(10);
		tfMobile.setBounds(254, 156, 96, 21);
		getContentPane().add(tfMobile);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(12, 159, 50, 15);
		getContentPane().add(lblName);
		
		JLabel lblbirth = new JLabel("생년월일:");
		lblbirth.setBounds(12, 190, 71, 15);
		getContentPane().add(lblbirth);
		
		JLabel lblMobile = new JLabel("전화번호:");
		lblMobile.setBounds(182, 159, 65, 15);
		getContentPane().add(lblMobile);
		
		cbdep = new JComboBox();
		cbdep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selecteddep = (String) cbdep.getSelectedItem();
		        if (selecteddep.equals("내과")) {
		            cbpra.setModel(new DefaultComboBoxModel(new String[] {"한상희", "손지원","선택안함"}));
		        } else if (selecteddep.equals("이비인후과")) {
		            cbpra.setModel(new DefaultComboBoxModel(new String[] {"이병홍", "이근해","선택안함"}));
		        } else if (selecteddep.equals("치과")) {
		            cbpra.setModel(new DefaultComboBoxModel(new String[] {"김병욱", "이병운","선택안함"}));
		        } else {
		            
		            cbpra.setModel(new DefaultComboBoxModel());
		        }
			}
		});
		cbdep.setModel(new DefaultComboBoxModel(new String[] {"진료과 선택", "내과", "이비인후과", "치과"}));
		cbdep.setBounds(74, 123, 96, 23);
		getContentPane().add(cbdep);
		
		cbpra = new JComboBox();
		cbpra.setModel(new DefaultComboBoxModel(new String[] {"진료의 선택"}));
		cbpra.setBounds(244, 123, 108, 23);
		getContentPane().add(cbpra);
		
		JLabel lblDep = new JLabel("진료과:");
		lblDep.setBounds(12, 127, 50, 15);
		getContentPane().add(lblDep);
		
		JLabel lblpra = new JLabel("진료의:");
		lblpra.setBounds(182, 127, 50, 15);
		getContentPane().add(lblpra);
		
		JLabel lblNewLabel_3 = new JLabel("예약정보입력");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3.setBounds(137, 26, 95, 25);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("시리얼 넘버");
		lblNewLabel.setBounds(22, 239, 71, 15);
		getContentPane().add(lblNewLabel);
		
		tfNum = new JTextField();
		tfNum.setToolTipText("예약정보를 확인하려면 시리얼넘버를 더블클릭하여 복사하세요!");
		tfNum.setEditable(false);
		tfNum.setBounds(94, 236, 173, 21);
		getContentPane().add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(12, 264, 350, 20);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("발급하기");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Set<Integer> set = new HashSet<>();
				Random random = new Random();
				int randomNumber;

				do {
					 randomNumber = random.nextInt(999999 - 10000 + 1) + 10000;
				} while (!set.add(randomNumber));

				tfNum.setText(Integer.toString(randomNumber));
				 
			}
		});
		btnNewButton.setBounds(276, 235, 91, 23);
		getContentPane().add(btnNewButton);

	}

	
		
	

//	private void selectID() {
//	    try {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root", "yourso0");
//
//	        
//	        String sql = "SELECT name, mobile, birth FROM loginTBL WHERE id = ? and pw = ?";
//	        PreparedStatement pstmt = con.prepareStatement(sql);
//	        pstmt.setString(1, tfID.getText());
//	        pstmt.setString(2, String.valueOf(passwordPW.getPassword())); 
//	        ResultSet rs = pstmt.executeQuery();
//	        if (rs.next()) {
//	            tfName.setText(rs.getString("name"));
//	            tfMobile.setText(rs.getString("mobile"));
//	            tfbirth.setText(rs.getString("birth"));
//	        } else {
//	          JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
//	        }
//
//	        
//	        rs.close();
//	        pstmt.close();
//	        con.close();
//	    } catch (ClassNotFoundException e1) {
//	        System.out.println("JDBC 드라이버 로드 에러");
//	    } catch (SQLException e1) {
//	        System.out.println("DB 연결 오류");
//	    }
//	}

		
	public ResModify(String snum) {
	    this();
	    ShowRecord(snum);
	}

	private void ShowRecord(String snum) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root", "yourso0");

	        String sql = "select * from restbl where sNum=?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, snum);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	        	tfNum.setText(rs.getString("sNum"));
	            tfName.setText(rs.getString("name"));
	            tfMobile.setText(rs.getString("mobile"));
	            tfbirth.setText(rs.getString("birth"));
	            // 나머지 정보도 필요한 대로 추가
	        }

	        rs.close();
	        pstmt.close();
	        con.close();
	    } catch (ClassNotFoundException e1) {
	        System.out.println("JDBC 드라이버 로드 에러");
	    } catch (SQLException e1) {
	        System.out.println("DB 연결 오류");
	        e1.printStackTrace();
	    }
	}
	protected void insertRes() {
		
     	 
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","yourso0");

	        // 중복 예약 처리
	        if (isDuplicateReservation(con)) {
	            JOptionPane.showMessageDialog(null, "이미 예약된 정보입니다.");
	            return;
	        }

	        String sql = "update resTBL set name=?, mobile=?, birth=?, dep=?, pra=?, day=?, date=? where sNum=?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, tfName.getText());
	        pstmt.setString(2, tfMobile.getText());
	        pstmt.setString(3, tfbirth.getText());
	        pstmt.setString(4, (String) cbdep.getSelectedItem());
	        pstmt.setString(5, (String) cbpra.getSelectedItem());
	        pstmt.setString(6, tfDate.getText());
	        pstmt.setString(7, (String) cbtime.getSelectedItem());
	        pstmt.setString(8, tfNum.getText());

	        pstmt.executeUpdate();
	        pstmt.close();
	        con.close();
	        
	       
	     
	        JOptionPane.showMessageDialog(null, "예약이 변경되었습니다.");
	        dispose(); 
	        	
	        	
	    } catch (ClassNotFoundException e1) {
	        System.out.println("JDBC 드라이버 로드 에러");
	    } catch (SQLException e1) {
	        System.out.println("DB 연결 오류");
	        e1.printStackTrace();
	    }
	}

	// 중복 예약 여부 확인
	private boolean isDuplicateReservation(Connection con) {
	    try {
	        String sql = "SELECT * FROM resTBL WHERE dep=? AND pra=? AND day=? AND date=?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, (String) cbdep.getSelectedItem());
	        pstmt.setString(2, (String) cbpra.getSelectedItem());
	        pstmt.setString(3, tfDate.getText());
	        pstmt.setString(4, (String) cbtime.getSelectedItem());

	        ResultSet rs = pstmt.executeQuery();
	        boolean isDuplicate = rs.next();

	        rs.close();
	        pstmt.close();

	        return isDuplicate;
	    } catch (SQLException e1) {
	        // 예외 처리
	        e1.printStackTrace();
	    }

	    return false;
	}
}
	

