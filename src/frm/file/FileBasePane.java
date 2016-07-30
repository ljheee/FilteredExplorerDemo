/**
  * @(#)frm.file.FileBasePane.java  2008-8-22  
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
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tools.ExplorerUtil;

 /**
 * 此处加入类详细说明
 * 2008-8-22
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class FileBasePane extends JPanel {

	private JTextArea area = new JTextArea("");
	public FileBasePane() {
		area.setEditable(false);
		area.setText("请选择一个文件");
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public void init(File file){
		Properties prop = ExplorerUtil.getFileBaseInfo(file);
		Enumeration eu = prop.propertyNames();
		area.setText("文件基本信息如下:\n");
		while(eu.hasMoreElements()){
			String key = eu.nextElement().toString();
			String value = prop.getProperty(key);
			area.append("    "+key+" "+value+"\n");
		}
	}
}
