/**
  * @(#)frm.file.JarPane.java  2008-8-22  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �˴��������˵��
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-22 	С��     		�½�
  **/
package frm.file;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

 /**
 * �˴���������ϸ˵��
 * 2008-8-22
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class JarPane extends JPanel {

	public JarPane() {
		setLayout(new BorderLayout());
		add(new JLabel("Jar�ļ�����"));
	}
	
	public void init(File file){
		
	}
}
