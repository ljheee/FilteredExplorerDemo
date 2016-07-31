/**
  * @(#)main.ExplorerMain.java  2008-8-18  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 资源管理器主程序类。
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-18 	小猪     		新建
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
 * 资源管理器主程序类。<br>
 * 该项目的目的是练习高级Swing的组件的使用：<br>
 * 同时掌握java编译器与VM的工作机理
 * 为了区别于MS的资源管理器，我们设计成初级Java练习者的java，class，mf文件等资源文件管理器功能如下：<br>
 * 设置类路径、设置扩展路径、过滤显示*.java，*.class，*.mf，*.jar，*.war，*.ear，*.properties等文件。<br>
 * 能察看编辑保存*.java，*.mf，*.properties等文件。<br>
 * 能编译运行*.class，*.jar文件。<br>
 * 能察看jar，ear，war等文件信息。<br>
 * 本类中采用内部窗体。<br>
 * 2008-8-18
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 */
public class ExplorerMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/** 文件目录树 */
	public static DirTree tree = null;
	/** 文件列表 */
	public static FileList tableInfoPane = null;
	/** 文件信息 */
	public static FileInfo fileInfo = null;
	
	private JMenuBar menuBar=new JMenuBar();
	private JMenu menuOperation=new JMenu("操作(O)");
	private JMenu menuHelp=new JMenu("关于(H)");
	private JCheckBox boxShowHiden = new JCheckBox("显示隐藏文件");
	private JMenuItem menuItemAbout=new JMenuItem("关于资源管理器",KeyEvent.VK_A);
	
	public ExplorerMain() {
		setTitle("[过滤型]资源管理器 - Java");
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
			new About(this,"关于[过滤性]资源管理器",true);
		}
		if(e.getSource()==boxShowHiden){
			if(tree.isShowHiden())
				tree.setShowHiden(false);
			else
				tree.setShowHiden(true);
		}
	}
}
