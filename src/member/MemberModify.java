package member;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import res.WinCalendar;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MemberModify extends JDialog {
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
					MemberModify dialog = new MemberModify();
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
	public MemberModify() {
		setResizable(false);
		setTitle("회원가입");
		setBounds(100, 100, 428, 450);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 34));
		lblNewLabel.setBounds(46, 0, 247, 55);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sign up");
		lblNewLabel_1.setForeground(SystemColor.windowBorder);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 22));
		lblNewLabel_1.setBounds(251, 18, 114, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblID = new JLabel("아이디");
		lblID.setFont(new Font("굴림", Font.PLAIN, 14));
		lblID.setBounds(51, 58, 96, 15);
		getContentPane().add(lblID);
		
		
		tfID = new JTextField();
		tfID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordPW.requestFocus();
				}
			}
		});
		tfID.setBounds(51, 83, 193, 32);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setFont(new Font("굴림", Font.PLAIN, 14));
		lblPW.setBounds(51, 125, 96, 23);
		getContentPane().add(lblPW);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("굴림", Font.PLAIN, 14));
		lblName.setBounds(51, 192, 96, 26);
		getContentPane().add(lblName);
		
		JLabel lblMobile = new JLabel("전화번호");
		lblMobile.setFont(new Font("굴림", Font.PLAIN, 14));
		lblMobile.setBounds(51, 259, 96, 23);
		getContentPane().add(lblMobile);
		
		JLabel lblYear = new JLabel("생년월일");
		lblYear.setFont(new Font("굴림", Font.PLAIN, 14));
		lblYear.setBounds(51, 315, 96, 36);
		getContentPane().add(lblYear);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfMobile.requestFocus();
				}
			}
		});
		tfName.setColumns(10);
		tfName.setBounds(51, 217, 193, 32);
		getContentPane().add(tfName);
		
		tfMobile = new JTextField();
		tfMobile.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfBirth.requestFocus();
				}
			}
		});
		tfMobile.setColumns(10);
		tfMobile.setBounds(51, 284, 193, 32);
		getContentPane().add(tfMobile);
		
		tfBirth = new JTextField();
		tfBirth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String id = tfID.getText();
		        String pw = String.valueOf(passwordPW.getPassword());                
		        if(id.length() < 8) {
		            JOptionPane.showMessageDialog(null, "아이디는 8글자 이상으로 입력해주세요.");
		            return;
		        }else
		        
		       
		        if(pw.length() < 12) {
		            JOptionPane.showMessageDialog(null, "비밀번호는 12글자 이상으로 입력해주세요.");
		            return;
		        }
		        
		        InsertSignUp();
		        clean();
		        JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
		    
				
			}
		});
		tfBirth.setColumns(10);
		tfBirth.setBounds(51, 351, 193, 32);
		getContentPane().add(tfBirth);
		
		passwordPW = new JPasswordField();
		passwordPW.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfName.requestFocus();
				}
			}
		});
		passwordPW.setBounds(51, 150, 193, 32);
		getContentPane().add(passwordPW);
		
		JButton btntwo = new JButton("중복확인");
		btntwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkDup()) {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
					passwordPW.requestFocus();
				}else {
					JOptionPane.showMessageDialog(null, "아이디가 존재합니다", "중복오류", JOptionPane.ERROR_MESSAGE);
					tfID.setText("");
					tfID.requestFocus();
				}
			}
		});
		
		btntwo.setBounds(268, 83, 108, 32);
		getContentPane().add(btntwo);
		
		JButton btnSignUp = new JButton("가입하기 》");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String id = tfID.getText();
			        String pw = String.valueOf(passwordPW.getPassword());  
//			        String name = tfName.getText();
//			        String mobile = tfMobile.getText();
//			        String birth = tfYear.getText();
			        if(id.length() < 8) {
			            JOptionPane.showMessageDialog(null, "아이디는 8글자 이상으로 입력해주세요.");
			            return;
			        }else if(pw.length() < 12) {
			            JOptionPane.showMessageDialog(null, "비밀번호는 12글자 이상으로 입력해주세요.");
			            return;
			        }
//			        else if(name.length() < 12) {
//			            JOptionPane.showMessageDialog(null, "비밀번호는 12글자 이상으로 입력해주세요.");
//			            return;
//			        }else if(pw.length() < 11) {
//			            JOptionPane.showMessageDialog(null, "전화번호를 확인해 주세요");
//			            return;
//			        }else if(pw.length() < 12) {
//			            JOptionPane.showMessageDialog(null, "비밀번호는 12글자 이상으로 입력해주세요.");
//			            return;
//			        }
			        
			        InsertSignUp();
			        clean();
			        JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			    }
			});
			
	
		btnSignUp.setFont(new Font("굴림", Font.BOLD, 15));
		btnSignUp.setBounds(268, 305, 130, 78);
		getContentPane().add(btnSignUp);
		
		JButton btnbirth = new JButton("달력보기");
		btnbirth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar=new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfBirth.setText(winCalendar.getDate());
			}
		});
		btnbirth.setBounds(120, 322, 91, 23);
		getContentPane().add(btnbirth);

	}

	protected boolean checkDup() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","yourso0");			
			//=============================================		
			String sql = "select * from loginTBL where id='" + tfID.getText() + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
				return true;			
			//==============================================
			con.close();
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		} 
		return false;
		}
	

	protected void clean() {
		tfID.setText("");
		passwordPW.setText("");
		tfName.setText("");
		tfMobile.setText("");
		tfBirth.setText("");
		
	}

	protected void InsertSignUp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","yourso0");			
			//=============================================		
			String sql = "insert into loginTBL values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tfID.getText());
			pstmt.setString(2, String.valueOf(passwordPW.getPassword()));
			pstmt.setString(3, tfName.getText());
			pstmt.setString(4, tfMobile.getText());
			pstmt.setString(5, tfBirth.getText());
				
			pstmt.executeUpdate();
			
			pstmt.close();			
			//==============================================
			con.close();
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		} 		
		
		
		
	}
}
