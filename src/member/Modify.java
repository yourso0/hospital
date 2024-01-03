package member;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Modify extends JDialog {
	private JTextField tfLoginID;
	private JPasswordField passwordLoginPw;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfBirth;
	private JPasswordField passwordPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modify dialog = new Modify();
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
	public Modify() {
		setTitle("정보 수정");
		setBounds(100, 100, 372, 405);
		getContentPane().setLayout(null);
		
		JLabel lblID = new JLabel("아이디");
		lblID.setBounds(48, 26, 50, 15);
		getContentPane().add(lblID);
		
		tfLoginID = new JTextField();
		tfLoginID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordLoginPw.requestFocus();
				}
			}
		});
		tfLoginID.setBounds(110, 23, 96, 21);
		getContentPane().add(tfLoginID);
		tfLoginID.setColumns(10);
		
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setBounds(48, 60, 50, 15);
		getContentPane().add(lblPW);
		
		JButton btnNewButton = new JButton("정보수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyMember();
			
				
			}
		});
		btnNewButton.setBounds(253, 302, 91, 56);
		getContentPane().add(btnNewButton);
		
		passwordLoginPw = new JPasswordField();
		passwordLoginPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					loginModify();
				}
			}
		});
		passwordLoginPw.setBounds(110, 57, 96, 21);
		getContentPane().add(passwordLoginPw);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(74, 140, 96, 21);
		getContentPane().add(tfID);
		
		JLabel lblID_1 = new JLabel("아이디");
		lblID_1.setBounds(12, 143, 50, 15);
		getContentPane().add(lblID_1);
		
		JLabel lblID_2 = new JLabel("비밀번호");
		lblID_2.setBounds(186, 143, 50, 15);
		getContentPane().add(lblID_2);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(74, 199, 96, 21);
		getContentPane().add(tfName);
		
		JLabel lblID_3 = new JLabel("이름");
		lblID_3.setBounds(12, 202, 50, 15);
		getContentPane().add(lblID_3);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBounds(248, 199, 96, 21);
		getContentPane().add(tfMobile);
		
		JLabel lblID_4 = new JLabel("전화번호");
		lblID_4.setBounds(186, 202, 50, 15);
		getContentPane().add(lblID_4);
		
		tfBirth = new JTextField();
		tfBirth.setEditable(false);
		tfBirth.setColumns(10);
		tfBirth.setBounds(74, 261, 96, 21);
		getContentPane().add(tfBirth);
		
		JLabel lblID_5 = new JLabel("생년월일");
		lblID_5.setBounds(12, 264, 50, 15);
		getContentPane().add(lblID_5);
		
		passwordPW = new JPasswordField();
		passwordPW.setBounds(248, 140, 96, 21);
		getContentPane().add(passwordPW);
		
		JButton btnNewButton_1 = new JButton("로그인");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginModify();
			}
		});
		btnNewButton_1.setBounds(218, 22, 91, 56);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("정보수정");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(108, 95, 128, 35);
		getContentPane().add(lblNewLabel);

	}

	protected void ModifyMember() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","yourso0");			
			//=============================================		
			
			String sql = "Update logintbl set  pw=?,name=?,";
			sql = sql + "mobile=?,birth=? where id=? ";
	         PreparedStatement pstmt = con.prepareStatement(sql);
	         String pw = String.valueOf(passwordPW.getPassword());
	         pstmt.setString(1, pw);
//	          if(pw.length() < 12) {
//		            JOptionPane.showMessageDialog(null, "비밀번호는 12글자 이상으로 입력해주세요."); 
//		            return; // 패스워드 길이가 12글자 이하면 메시지 출력
//		        }
	         pstmt.setString(2, tfName.getText());
	         pstmt.setString(3, tfMobile.getText());
	         pstmt.setString(4, tfBirth.getText());
	         pstmt.setString(5, tfID.getText());
	         JOptionPane.showMessageDialog(null, "정보수정 완료");
	         cleaninfo();
	     
	 
	         
			pstmt.executeUpdate();
			
			pstmt.close();			
			//==============================================
			con.close();
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
			e1.printStackTrace();
		} 		
		
	}

	private void cleaninfo() {
		tfLoginID.setText("");
		passwordLoginPw.setText("");
		tfID.setText("");
		passwordPW.setText("");
		tfName.setText("");
		tfMobile.setText("");
		tfBirth.setText("");
		
	}

	protected void loginModify() {
		  try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root", "yourso0");

		       
		        if(tfLoginID.getText().equals("") || String.valueOf(passwordLoginPw.getPassword()).equals("")) {
		            JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String sql = "SELECT * FROM logintbl WHERE id = ? AND pw = ?";
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, tfLoginID.getText());
		        pstmt.setString(2, String.valueOf(passwordLoginPw.getPassword()));
		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");
		            tfID.setText(rs.getString(1));
		            passwordPW.setText(rs.getString(2));
		            tfName.setText(rs.getString(3));
		            tfMobile.setText(rs.getString(4));
		            tfBirth.setText(rs.getString(5));
		    
		           
		        } else {
		            JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
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

