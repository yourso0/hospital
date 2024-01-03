package member;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Delete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfID;
	private JPasswordField passwordPW;
	private JLabel lblPW;
	private JButton btnResult;
	private int result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Delete dialog = new Delete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Delete() {
		setTitle("회원탈퇴");
		setBounds(100, 100, 344, 126);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfID = new JTextField();
			tfID.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordPW.requestFocus();
					};
				}
			});
			tfID.setBounds(105, 23, 96, 21);
			contentPanel.add(tfID);
			tfID.setColumns(10);
		}
		
		passwordPW = new JPasswordField();
		passwordPW.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(passwordPW.equals(0)) {
						JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요.");
					}else {
					int result = JOptionPane.showConfirmDialog(null, "탈퇴를 진행하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
					    delete();				    
					} 
					}
					
				}
				
			}
		});
		passwordPW.setBounds(105, 54, 96, 21);
		contentPanel.add(passwordPW);
		
		JLabel lblID = new JLabel("아이디:");
		lblID.setBounds(30, 26, 50, 15);
		contentPanel.add(lblID);
		
		lblPW = new JLabel("비밀번호:");
		lblPW.setBounds(30, 57, 63, 15);
		contentPanel.add(lblPW);
		
		btnResult = new JButton("확인");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "탈퇴를 진행하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
				    delete();				    
				} else {;}
					
				
			}
		});
		btnResult.setBounds(226, 22, 96, 53);
		contentPanel.add(btnResult);
	}

	protected void delete() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","yourso0");			
			//=============================================		
			String sql = "delete from logintbl where id='";
			sql = sql + tfID.getText() + "'" + "and pw='";
			sql = sql +	String.valueOf(passwordPW.getPassword()) + "'";
		
			Statement stmt = con.createStatement();
			if(stmt.executeUpdate(sql) > 0 ) {		
				JOptionPane.showMessageDialog(null,"회원 탈퇴 성공");
		}else {
				JOptionPane.showMessageDialog(null, "아이디/비밀번호를 확인해주세요.");
				}
			
			
			stmt.close();
			//==============================================
			con.close();
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		} 		
	}
		protected void deleteRes() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","yourso0");			
				//=============================================		
				String sql = "delete from restbl where name='";
			
			
				Statement stmt = con.createStatement();
				
				
				
				stmt.close();
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e1) {
				System.out.println("DB 연결 오류");
			} 		
			
		
	}
}
