/**
  * @(#)frm.file.RelectPane.java  2008-8-22  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-22 	小猪     		新建
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
 * 此处加入类详细说明
 * 2008-8-22
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class RelectPane extends JPanel {

	
	private JTextArea area = new JTextArea("");
	public RelectPane() {
		area.setEditable(false);
		area.setText("请选择一个文件，文件类型:*.class");
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
				System.out.println("读取文件"+file.getName()+"时发生错误:"+e.getMessage());
				area.append("读取文件"+file.getName()+"时发生错误:"+e.getMessage());
			} catch (IOException e) {
				System.out.println("读取文件"+file.getName()+"时发生错误:"+e.getMessage());
				area.append("读取文件"+file.getName()+"时发生错误:"+e.getMessage());
			}
		}
		else
			area.append("请选择一个文件，文件类型:*.class\n不支持的文件格式:"+type);
	}
}
