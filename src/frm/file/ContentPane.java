/**
  * @(#)frm.file.ContentPane.java  2008-8-22  
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
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tools.ExplorerUtil;
import tools.FileInfo;

 /**
 * �˴���������ϸ˵��
 * 2008-8-22
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class ContentPane extends JPanel {

	private JTextArea area = new JTextArea();
	public ContentPane() {
		area.setEditable(false);
		area.setLineWrap(true);
		area.append("��ѡ��һ���ļ����ļ�����:*.txt��*.java��*.mf��*.properties");
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public void init(File file){
		String type = FileInfo.getFileFormat(file);
		try {
			area.setText("");
			if(type.equals(".txt") ||type.equals(".java") || type.equals(".mf")|| type.equals(".properties"))
				area.append(ExplorerUtil.getContent(file));
			else
				area.append("��ѡ��һ���ļ����ļ�����:*.txt��*.java��*.mf��*.properties\n��֧�ֵ��ļ���ʽ����:"+FileInfo.getFileFormat(file));
		} catch (IOException e) {
			System.out.println("����:"+e.getMessage());
			area.append("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
		}
	}
}
