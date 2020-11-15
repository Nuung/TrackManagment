package viewer;

import java.awt.Color;

/*
 * 
 */

import javax.swing.JFrame;
import javax.swing.UIManager;

import viewer.ui.ViewFirstSet;

// 시스템의 전체 화면을 담아줄 클래스, JFrame을 상속받음
public class ViewFrame extends JFrame{

	// Size of Frame, 프레임의 사이즈, 2:1 비율
	private final int WIDTH = 1024, HEIGHT = 512;
	
	public ViewFrame() {
		
		// UI (front) Setting
		this.SettingLookAndFeel();
		new ViewFirstSet(this);
		
		// Main Frame Setting
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Track Manager System");
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	} // ViewFrame()

	// 버튼 등과 같은 컴포넌트들을 좀 더 예쁘게 처리하기 위해 UIManager를 적용시키기 위한 메서드
	private void SettingLookAndFeel() {
		// look and fell setting
		try {

			// Nimbus
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//			// Windows
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		} // try - catch
	} // SettingLookAndFeel()
	
}
