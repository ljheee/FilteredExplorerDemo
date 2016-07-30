/**
  * @(#)frm.file.ExcutePane.java  2008-8-22  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-22 	小猪     		新建
  **/
package frm.file;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class ExcutePane extends JPanel implements ActionListener{

	private File file = null;
	private JButton btnExcute = new JButton("运行");
	private JLabel lblInfo = new JLabel("请选择一个文件，文件类型:*.jar、*.class  ");
	
	private Process process = null;
	
	private boolean hasRun = false;
	public ExcutePane() {
		btnExcute.setEnabled(false);
		btnExcute.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		add(lblInfo);
		add(btnExcute);
	}
	
	public void init(File file){
		String type = FileInfo.getFileFormat(file);
		this.file = null;
		if(type.equals(".jar") || type.equals(".class")){
			this.file = file;
			btnExcute.setEnabled(true);
		}
		else
			btnExcute.setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(file!=null){
			try {
				if(!hasRun){
					hasRun = true;
					btnExcute.setText("停止");
					process= ExplorerUtil.excuteClass(file);
					
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
				
					//new Thread(new InputStreamConsole(FileInfoPane.consolePane.getArea(),br)).start();
					
					//FileInfoPane.infotab.setSelectedIndex(8);
				}else{
					hasRun = false;
					btnExcute.setText("运行");
					try {
						if(process!=null)
							process.destroy();
					} catch (RuntimeException e1) {
						System.out.println("关闭进程时，发生错误，可能其已经关闭，或发生其他问题。原因描述如下:"+e1.getMessage());
					}
				}		
			} catch (IOException e1) {
				System.out.println("错误:"+e1.getMessage());
				JOptionPane.showMessageDialog(null,"读取文件"+file.getName()+"时发生错误:"+e1.getMessage());
			}
			
		}else
			JOptionPane.showMessageDialog(null, "错误!");
	}
	
	private class InputStreamConsole implements Runnable{
		private BufferedReader br = null;
		private JTextArea area = null;
		public InputStreamConsole(JTextArea area,BufferedReader br) {
			this.area = area;
			this.br = br;
		}
		
		public void run() {
			area.setText("");
			String line = null;
			try {
				while((line = br.readLine())!=null)
				area.append(line+"\n");
			} catch (IOException e) {
				System.out.println("错误:"+e.getMessage());
			}			
		}
	}
	
	private class OutputStreamConsole implements Runnable{
		private BufferedWriter bw;
		private JTextArea area = null;
		public OutputStreamConsole(JTextArea area,BufferedWriter bw) {
			this.area = area;
			this.bw = bw;
		}
		
		public void run() {
			
		}
	}
}
