/**
  * @(#)frm.file.ConsolePane.java  2008-8-25  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �˴��������˵��
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-25 	С��     		�½�
  **/
package frm.file;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

 /**
 * �˴���������ϸ˵��
 * 2008-8-25
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class ConsolePane extends JPanel {

	private JTextArea area = new JTextArea("��ʱû��Ҫ��ʾ�Ŀ���̨");
	public ConsolePane() {
		area.setLineWrap(true);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public JTextArea getArea() {
		return area;
	}
	
	public void initArea(){
		area.setText("��ʱû��Ҫ��ʾ�Ŀ���̨");
	}
}
