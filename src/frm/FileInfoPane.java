/**
  * @(#)frm.FileInfoPane.java  2008-8-19  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: ��ͬ�ļ��Ĳ�ͬ������
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-19 	С��     		�½�
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
 * ��ͬ�ļ��Ĳ�ͬ�����ࡣ
 * ����summery��java���ı��ļ�����ʾ��������<br>
 * ��classʹ�÷�����ʾ���ĳ�Ա����<br>
 * ��jar��ʾ���е��ļ��嵥��ִ������<br>
 * ��war��ʾ�ļ��嵥��web.xml����Ҫ����<br>
 * ��ear��ʾapplication.xml��xml�ļ��е�������Ϣ�����ģ�顣<br><br><br>
 * �����ʵ�ֿ���ʹ�ø������<br>
 * Ҳ����ʹ��Java2D�ļ���<br>
 * 2008-8-19
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class FileInfoPane extends JPanel {
	
	/** �ļ��б� */
	private File file = null;
	/** �ļ���� */
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
		infotab.add("������Ϣ",basePane);
		infotab.add("�ļ�����",contentPane);
		infotab.add("������Ϣ",relectPane);
		//infotab.add("ִ��",excutePane);
		infotab.add("�ļ��б�",zipPane);
		infotab.add("Jar",jarPane);
		infotab.add("War",warPane);
		infotab.add("Ear",earPane);
		infotab.add("����̨",consolePane);
		
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
	 * ����ļ���
	 * @return �ļ���
	 */
	public File getFile() {
		return file;
	}

	/**
	 * �����ļ���
	 * @param file �ļ���
	 */
	public void setFile(File file) {
		this.file = file;
	}

	public JTabbedPane getInfotab() {
		return infotab;
	}
	
	//���������������޸��ı��ļ���ִ�б�����ࡣ
	/*private class RecentFile{
		private JButton btnSave = new JButton("�����޸�");
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
					System.out.println("����:"+e.getMessage());
				} catch (IOException e) {
					System.out.println("����:"+e.getMessage());
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
					System.out.println("����:"+e.getMessage());
				}
			}
		}
	}*/
}
