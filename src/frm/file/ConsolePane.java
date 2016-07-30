/**
  * @(#)frm.file.ConsolePane.java  2008-8-25  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-25 	小猪     		新建
  **/
package frm.file;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

 /**
 * 此处加入类详细说明
 * 2008-8-25
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class ConsolePane extends JPanel {

	private JTextArea area = new JTextArea("暂时没有要显示的控制台");
	public ConsolePane() {
		area.setLineWrap(true);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public JTextArea getArea() {
		return area;
	}
	
	public void initArea(){
		area.setText("暂时没有要显示的控制台");
	}
}
