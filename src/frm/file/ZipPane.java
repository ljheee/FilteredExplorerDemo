/**
  * @(#)frm.file.ZipPane.java  2008-8-22  
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipException;

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
public class ZipPane extends JPanel {

	private JTextArea area = new JTextArea("");
	public ZipPane() {
		area.setEditable(false);
		area.append("��ѡ��һ���ļ����ļ�����:*.zip��*.jar��*.ear��.war");
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public void init(File file){
		String type = FileInfo.getFileFormat(file);
		area.setText("");
		if(type.equals(".jar") || type.equals(".ear") || type.equals(".war") || type.equals(".zip")){
			try {
				Enumeration eu = ExplorerUtil.getZipList(file);
				area.append(file.getName()+"���ļ��б�����:\n");
				while(eu.hasMoreElements())
					area.append(eu.nextElement().toString()+"\n");
			} catch (ZipException e) {
				System.out.println("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
				area.append("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage()+"\n");
			} catch (IOException e) {
				System.out.println("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
				area.append("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage()+"\n");
			}
		}
		else
			area.append("��ѡ��һ���ļ����ļ�����:*.zip��*.jar��*.ear��.war\n��֧�ֵ��ļ���ʽ:"+type+"\n");
	}
}
