/**
  * @(#)frm.FileInfoPane.java  2008-8-19  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 不同文件的不同操作类
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-19 	小猪     		新建
  **/
package frm;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import frm.file.ConsolePane;
import frm.file.ContentPane;
import frm.file.EarPane;
import frm.file.ExcutePane;
import frm.file.FileBasePane;
import frm.file.JarPane;
import frm.file.RelectPane;
import frm.file.WarPane;
import frm.file.ZipPane;

import main.ExplorerMain;

 /**
 * 不同文件的不同操作类。
 * 其中summery是java等文本文件就显示他的内容<br>
 * 是class使用反射显示他的成员属性<br>
 * 是jar显示其中的文件清单与执行主类<br>
 * 是war显示文件清单与web.xml的主要内容<br>
 * 是ear显示application.xml等xml文件中的配置信息与相关模块。<br><br><br>
 * 该类的实现可以使用复合组件<br>
 * 也可以使用Java2D的技术<br>
 * 2008-8-19
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class FileInfoPane extends JPanel {
	
	/** 文件列表 */
	private File file = null;
	/** 文件面板 */
	private JTabbedPane infotab = null;
	
	private ContentPane contentPane = new ContentPane();
	private EarPane earPane = new EarPane();
	private ExcutePane excutePane = new ExcutePane();
	private FileBasePane basePane = new FileBasePane();
	private JarPane jarPane = new JarPane();
	private RelectPane relectPane = new RelectPane();
	private WarPane warPane = new WarPane();
	private ZipPane zipPane = new ZipPane();
	private ConsolePane consolePane = new ConsolePane();
		
	public ConsolePane getConsolePane() {
		return consolePane;
	}

	public FileInfoPane() {
		infotab = new JTabbedPane();
		infotab.add("基本信息",basePane);
		infotab.add("文件内容",contentPane);
		infotab.add("反射信息",relectPane);
		//infotab.add("执行",excutePane);
		infotab.add("文件列表",zipPane);
		infotab.add("Jar",jarPane);
		infotab.add("War",warPane);
		infotab.add("Ear",earPane);
		infotab.add("控制台",consolePane);
		
		setLayout(new BorderLayout());
		add(infotab,BorderLayout.CENTER);
	}
	
	public void changeFileInfo(){
		if(file!=null){
			basePane.init(file);
			contentPane.init(file);
			relectPane.init(file);
			excutePane.init(file);
			zipPane.init(file);
			jarPane.init(file);
			warPane.init(file);
			earPane.init(file);
			consolePane.initArea();
		}
	}

	/**
	 * 获得文件。
	 * @return 文件。
	 */
	public File getFile() {
		return file;
	}

	/**
	 * 设置文件。
	 * @param file 文件。
	 */
	public void setFile(File file) {
		this.file = file;
	}

	public JTabbedPane getInfotab() {
		return infotab;
	}
	
	//下面的这段内容是修改文本文件后，执行保存的类。
	/*private class RecentFile{
		private JButton btnSave = new JButton("保存修改");
		private JTextArea txtInfo = new JTextArea();
		private void getRelectInfo(){
			
			txtInfo.setLineWrap(true);
			txtInfo.setWrapStyleWord(true);
			
			readTextFile(txtInfo);
			
			removeAll();
			validate();
			
			btnSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					saveTextFile(txtInfo);
					
				}
			});
			JPanel pan = new JPanel();
			pan.add(btnSave);
			
			
			setLayout(new BorderLayout());
			add(new JScrollPane(txtInfo),BorderLayout.CENTER);
			add(pan,BorderLayout.SOUTH);
			
			validate();
		}
		
		private void readTextFile(JTextArea txt){
			if(file!=null){
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					while((line=reader.readLine())!=null){
						txt.append(line+"\n");
					}
					reader.close();
				} catch (FileNotFoundException e) {
					System.out.println("错误:"+e.getMessage());
				} catch (IOException e) {
					System.out.println("错误:"+e.getMessage());
				}
			}
			
		}
		
		private void saveTextFile(JTextArea txt){
			if(file!=null){
				System.out.println("come");
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write(txt.getText());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					System.out.println("错误:"+e.getMessage());
				}
			}
		}
	}*/
}
