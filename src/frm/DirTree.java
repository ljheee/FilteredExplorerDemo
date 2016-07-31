/**
  * @(#)frm.DirTree.java  2008-8-18  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: ��Դ����������״��ʾ�ࡣ
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-18 	С��     		�½�
  **/
package frm;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import main.ExplorerMain;

 /**
 * ��Դ����������״��ʾ�ࡣ<br>
 * ��ʾ�ļ���������MS�ķ��.
 * Ϊ���ٶȣ�����ݹ����3�㡣<br>
 * 2008-8-18
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 */
public class DirTree extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/** ���ڵ� */
	private DefaultMutableTreeNode node = null;
	/** �� */
	private JTree tree = null;
	
	/** �Ƿ���ʾ�����ļ� */
	private boolean isShowHiden = false;
	
	public DirTree() {
		
		node = new DefaultMutableTreeNode(getOsName());
		addRootNode();
		tree = new JTree(node);
		//tree.setCellRenderer(new DirRenderer(new ImageIcon("/images/open.gif"),new ImageIcon("images/close.gif"),new ImageIcon("images/leaf.ico")));
		
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(new ImageIcon("images/close.gif"));
		tree.setCellRenderer(renderer);
		
		tree.addTreeWillExpandListener(new DirTreeWillExpandListenerImpl());
		tree.addTreeSelectionListener(new DirTreeSelectionListenerImpl());
		setLayout(new BorderLayout());
		add(new JScrollPane(tree));
	}

	/**
	 * ��ʾ�Ƿ���ʾ�����ļ���
	 * @return ����true����ʾ�����ļ���������ʾ�����ļ���
	 */
	public boolean isShowHiden() {
		return isShowHiden;
	}

	/**
	 * �����Ƿ���ʾ�����ļ���
	 * @param isShowHiden true����ʾ�����ļ���false������ʾ��
	 */
	public void setShowHiden(boolean isShowHiden) {
		this.isShowHiden = isShowHiden;
	}
	
	/**
	 * ��file��Ŀ¼��ӵ�ĳ�ڵ��ϡ�
	 * @param file �ļ�Ŀ¼��
	 * @param node �ڵ㡣
	 */
	private void addNodeToParentNode(File file,DefaultMutableTreeNode node){
		if(file.isDirectory()){
			node.removeAllChildren();
			File[] childFile = file.listFiles();
			if(childFile!=null)
				for(File f:childFile)
					if((isShowHiden  || !f.isHidden()) && f.isDirectory()){
						DefaultMutableTreeNode child = new DefaultMutableTreeNode(f.getName());
						node.add(child);
					}
		}	
	}
	
	/**
	 * ���������ӽڵ㡣����Ϊ�ҵĵ�����Ӹ����̷���.
	 *
	 */
	private void addRootNode(){
		File[] childFile = File.listRoots();
		for(File f:childFile){
			//if(isShowHiden || !f.isHidden()){
			//System.out.println(f.getName());
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
			node.add(child);
			if(f.canWrite())
			//	child.add(new DefaultMutableTreeNode("NoUse"));
			addNodeToParentNode(f, child);
			//}
		}
	}
	
	private class DirTreeWillExpandListenerImpl implements TreeWillExpandListener{
		/**
		 * 
		 */
		public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		}
		
		/**
		 * �ڵ㼴��չ����ʱ�򣬶Խڵ��µ��ӽڵ��������������ӽڵ���¼��ڵ㡣
		 */
		public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
			TreePath tPath=event.getPath();
	    	DefaultMutableTreeNode td=(DefaultMutableTreeNode)tPath.getLastPathComponent();

	    	String nodePath=tPath.toString();
	    	String filePath = getFilePath(nodePath);
	    	
	    	Enumeration<DefaultMutableTreeNode> nodes = td.children();
	    	while(nodes.hasMoreElements()){
	    		DefaultMutableTreeNode node = nodes.nextElement();
	    		File file = new File(filePath+node.toString());
	    		addNodeToParentNode(file, node);
	    	}
	    	
		}
	}
	
	private class DirTreeSelectionListenerImpl implements TreeSelectionListener{
		public void valueChanged(TreeSelectionEvent e) {
			//System.out.println(e.getPath());
			TreePath tPath=e.getPath();
			String filePath = getFilePath(tPath.toString());
			File file = new File(filePath);
			File[] children = file.listFiles(ExplorerMain.fileInfo);
			//System.out.println("��ߴ�ӡ��"+filePath);
			ExplorerMain.tableInfoPane.setFileList(children, filePath);
		}
	}
	
	/**
	 * �����ڵ���ַ���ֵ�õ��ڵ����������ļ���·����
	 * @param nodePath �ڵ���ַ���ֵ��
	 * @return ���ظýڵ���������ļ���·����
	 */
	private String getFilePath(String nodePath){
		String filePath = "";
		nodePath = nodePath.substring(1+getOsName().length(),nodePath.length()-1);
		StringTokenizer tokenizer = new StringTokenizer(nodePath,", ");
		while (tokenizer.hasMoreTokens()) {
			
			String s = tokenizer.nextToken();
			filePath += s.endsWith(File.separator)?s:s+"\\";
		}
		return filePath;
	}
	
	/**
	 * ��ò���ϵͳ�����ơ�
	 * @return ���ز���ϵͳ�����ơ�
	 */
	private String getOsName(){
		 return System.getProperty("os.name").trim();
	}
}
