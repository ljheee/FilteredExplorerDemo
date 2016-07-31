/**
  * @(#)main.ExplorerMain.java  2008-8-18  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: ��Դ�������������ࡣ
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-18 	С��     		�½�
  **/
package main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import about.About;

import tools.FileInfo;

import frm.DirTree;
import frm.FileList;

 /**
 * ��Դ�������������ࡣ<br>
 * ����Ŀ��Ŀ������ϰ�߼�Swing�������ʹ�ã�<br>
 * ͬʱ����java��������VM�Ĺ�������
 * Ϊ��������MS����Դ��������������Ƴɳ���Java��ϰ�ߵ�java��class��mf�ļ�����Դ�ļ��������������£�<br>
 * ������·����������չ·����������ʾ*.java��*.class��*.mf��*.jar��*.war��*.ear��*.properties���ļ���<br>
 * �ܲ쿴�༭����*.java��*.mf��*.properties���ļ���<br>
 * �ܱ�������*.class��*.jar�ļ���<br>
 * �ܲ쿴jar��ear��war���ļ���Ϣ��<br>
 * �����в����ڲ����塣<br>
 * 2008-8-18
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 */
public class ExplorerMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/** �ļ�Ŀ¼�� */
	public static DirTree tree = null;
	/** �ļ��б� */
	public static FileList tableInfoPane = null;
	/** �ļ���Ϣ */
	public static FileInfo fileInfo = null;
	
	private JMenuBar menuBar=new JMenuBar();
	private JMenu menuOperation=new JMenu("����(O)");
	private JMenu menuHelp=new JMenu("����(H)");
	private JCheckBox boxShowHiden = new JCheckBox("��ʾ�����ļ�");
	private JMenuItem menuItemAbout=new JMenuItem("������Դ������",KeyEvent.VK_A);
	
	public ExplorerMain() {
		setTitle("[������]��Դ������ - Java");
		setSize(800,600);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		fileInfo = new FileInfo();
		
		tree = new DirTree();
		//tree.setBorder(new LineBorder(Color.black));
		tableInfoPane = new FileList();
		
		JScrollPane spLeft=new JScrollPane(tree);
		
		JSplitPane sp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,spLeft,tableInfoPane);
		sp.setContinuousLayout(true);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(200);
		
		boxShowHiden.addActionListener(this);
		menuOperation.setMnemonic(KeyEvent.VK_O);
		menuOperation.add(boxShowHiden);
		menuItemAbout.addActionListener(this);	
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(menuItemAbout);
		menuBar.add(menuOperation);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);
		
		add(sp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new ExplorerMain();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuItemAbout){
			new About(this,"����[������]��Դ������",true);
		}
		if(e.getSource()==boxShowHiden){
			if(tree.isShowHiden())
				tree.setShowHiden(false);
			else
				tree.setShowHiden(true);
		}
	}
}
