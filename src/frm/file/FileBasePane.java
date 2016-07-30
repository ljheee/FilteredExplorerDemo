/**
  * @(#)frm.file.FileBasePane.java  2008-8-22  
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
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tools.ExplorerUtil;

 /**
 * �˴���������ϸ˵��
 * 2008-8-22
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class FileBasePane extends JPanel {

	private JTextArea area = new JTextArea("");
	public FileBasePane() {
		area.setEditable(false);
		area.setText("��ѡ��һ���ļ�");
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public void init(File file){
		Properties prop = ExplorerUtil.getFileBaseInfo(file);
		Enumeration eu = prop.propertyNames();
		area.setText("�ļ�������Ϣ����:\n");
		while(eu.hasMoreElements()){
			String key = eu.nextElement().toString();
			String value = prop.getProperty(key);
			area.append("    "+key+" "+value+"\n");
		}
	}
}
