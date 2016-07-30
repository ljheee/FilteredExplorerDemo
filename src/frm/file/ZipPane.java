/**
  * @(#)frm.file.ZipPane.java  2008-8-22  
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
 * 此处加入类详细说明
 * 2008-8-22
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class ZipPane extends JPanel {

	private JTextArea area = new JTextArea("");
	public ZipPane() {
		area.setEditable(false);
		area.append("请选择一个文件，文件类型:*.zip、*.jar、*.ear、.war");
		setLayout(new BorderLayout());
		add(new JScrollPane(area));
	}
	
	public void init(File file){
		String type = FileInfo.getFileFormat(file);
		area.setText("");
		if(type.equals(".jar") || type.equals(".ear") || type.equals(".war") || type.equals(".zip")){
			try {
				Enumeration eu = ExplorerUtil.getZipList(file);
				area.append(file.getName()+"的文件列表如下:\n");
				while(eu.hasMoreElements())
					area.append(eu.nextElement().toString()+"\n");
			} catch (ZipException e) {
				System.out.println("读取文件"+file.getName()+"时发生错误:"+e.getMessage());
				area.append("读取文件"+file.getName()+"时发生错误:"+e.getMessage()+"\n");
			} catch (IOException e) {
				System.out.println("读取文件"+file.getName()+"时发生错误:"+e.getMessage());
				area.append("读取文件"+file.getName()+"时发生错误:"+e.getMessage()+"\n");
			}
		}
		else
			area.append("请选择一个文件，文件类型:*.zip、*.jar、*.ear、.war\n不支持的文件格式:"+type+"\n");
	}
}
