/**
  * @(#)frm.file.JarPane.java  2008-8-22  
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

import javax.swing.JLabel;
import javax.swing.JPanel;

 /**
 * 此处加入类详细说明
 * 2008-8-22
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class JarPane extends JPanel {

	public JarPane() {
		setLayout(new BorderLayout());
		add(new JLabel("Jar文件属性"));
	}
	
	public void init(File file){
		
	}
}
