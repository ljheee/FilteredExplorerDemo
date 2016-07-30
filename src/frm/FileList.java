/**
  * @(#)frm.FileList.java  2008-8-19  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �ļ����б���������ࡣ
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-19 	С��     		�½�
  **/
package frm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import main.ExplorerMain;

import tools.ExplorerUtil;
import tools.FileInfo;

 /**
 * �ļ����б���������ࡣ
 * 2008-8-19
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class FileList extends JPanel implements ActionListener{

	/** ���ģ�� */
	private DefaultTableModel dtm=null;
	/** ��ʾ�ļ�����ı�� */
	private JTable table = null;
	/** ��ʾ�ļ���ϸ��Ϣ�������ļ��Ĳ�ͬ������ʾ��ͬ���ļ��Ͷ��ļ��Ĳ��� */
	private FileInfoPane pane = null;
	/** �ļ����ڵ��ļ��� */
	private String filePosition = null;
	
	/** �����˵� */
	private JPopupMenu popupMenu = new JPopupMenu();
	/** ���� */
	private JMenuItem menuRun = new JMenuItem("����");
	/** ֹͣ */
	private JMenuItem menuStop = new JMenuItem("ֹͣ");
	
	/** �û�ѡ�е��ļ� */
	private File file = null;
	
	private Process process = null;
	private boolean hasRun = false;
	
	private int x = 0;
	private int y = 0;
	
	public FileList() {
		dtm = new DefaultTableModel();
		table=new JTable(dtm);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new FileListSelectionListenerImpl());
		table.addMouseListener(new MyMouseEvent());
				
		dtm.addColumn("����");
		dtm.addColumn("��С");
		dtm.addColumn("����");
		dtm.addColumn("�޸�����");
		
		menuRun.addActionListener(this);
		menuStop.addActionListener(this);
		popupMenu.add(menuRun);
		popupMenu.addSeparator();
		popupMenu.add(menuStop);
		
		pane = new FileInfoPane();
		
		JScrollPane spUp=new JScrollPane(table);
		//JScrollPane spDown=new JScrollPane(pane);
		
		JSplitPane sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,spUp,pane);
		sp.setContinuousLayout(true);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(400);
		
		setLayout(new BorderLayout());
		add(sp,BorderLayout.CENTER);
		
	}
	
	/**
	 * ����ļ�Ŀ¼�ϣ���ʾĿ¼�ڱ����˵��ļ�����Ϣ��
	 * @param list Ŀ¼���ļ��б�
	 */
	public void setFileList(File[] list,String filePosition){
		this.filePosition = filePosition;
		dtm.setRowCount(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(list!=null){
			FileInfo info = ExplorerMain.fileInfo;
			for(File file:list){
				String[] s={file.getName(),
    						(file.length()/1024>1?file.length()/1024:1)+"KB",
    						info.getFileType(file),//�˴�������Ҫ���ķ��������þ�̬����
    						format.format(new Date(file.lastModified()))};
				dtm.addRow(s);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuRun){
			if(process==null){
				processRun();
			}else{
				int n = JOptionPane.showConfirmDialog(null, "���ȹر�֮ǰ�򿪵ĳ���ȷ�Ϲر���?");
				if(n==JOptionPane.OK_OPTION)
					processStop();
			}
		}
		if(e.getSource()==menuStop)
			processStop();
	}
	/**
	 * �������С�
	 */
	private void processRun(){
		hasRun = true;
		try {
			process= ExplorerUtil.excuteClass(file);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

			new Thread(new InputStreamConsole(pane.getConsolePane().getArea(),br)).start();
			pane.getInfotab().setSelectedIndex(7);
		} catch (IOException e1) {
			System.out.println("����:"+e1.getMessage());
		}
	}
	
	/**
	 * ����ֹͣ��
	 */
	private void processStop(){
		hasRun = false;
		try {
			if(process!=null)
				process.destroy();
			process = null;
		} catch (RuntimeException e1) {
			System.out.println("�رս���ʱ���������󣬿������Ѿ��رգ������������⡣ԭ����������:"+e1.getMessage());
		}
	}
	
	private class FileListSelectionListenerImpl implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			try {
				//�������뿪table�л�����ʱ���ܷ����쳣��
				if(table.getRowCount()>0){
					setSelectFile();
					if(file!=null && file.exists()){
						pane.setFile(file);
						pane.changeFileInfo();
					}
				}
			} catch (RuntimeException e1) {
				System.out.println("����"+e1.getMessage());
			}
		}
	}
	
	private void setSelectFile(){
		int row = table.getSelectedRow();
		if(row != -1){
			String fileName =filePosition + table.getValueAt(row, 0).toString();
			file = new File(fileName);
		}else
			file = null;
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
				System.out.println("����:"+e.getMessage());
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
	
	private class MyMouseEvent extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			x = e.getX();
			y = e.getY();
			//System.out.println(e.getButton());
			if(e.getButton()==1){
				setSelectFile();
				if(file!=null && file.exists()){
					String type = FileInfo.getFileFormat(file);
					if(type.equals(".jar") || type.equals(".class")){
						popupMenu.show(table,x,y);
						if(!hasRun){
							menuRun.setEnabled(true);
							menuStop.setEnabled(false);
						}
						else{
							menuRun.setEnabled(false);
							menuStop.setEnabled(true);
						}
					}
				}
			}
		}
	}
}

