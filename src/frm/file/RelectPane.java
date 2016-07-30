/**
  * @(#)frm.file.RelectPane.java  2008-8-22  
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
import java.util.HashMap;
import java.util.Iterator;

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
public class RelectPane extends JPanel {

	
	private JTextArea area = new JTextArea("");
	public RelectPane() {
		area.setEditable(false);
		area.setText("��ѡ��һ���ļ����ļ�����:*.class");
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public void init(File file){
		String type = FileInfo.getFileFormat(file);
		area.setText("");
		if(type.equals(".class")){
			try {
				HashMap map = ExplorerUtil.getRelectInfo(file);
				Iterator iterator = map.values().iterator();
				while(iterator.hasNext())
					area.append(iterator.next().toString()+"\n");
			} catch (ClassNotFoundException e) {
				System.out.println("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
				area.append("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
			} catch (IOException e) {
				System.out.println("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
				area.append("��ȡ�ļ�"+file.getName()+"ʱ��������:"+e.getMessage());
			}
		}
		else
			area.append("��ѡ��һ���ļ����ļ�����:*.class\n��֧�ֵ��ļ���ʽ:"+type);
	}
}
