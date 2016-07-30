/**
  * @(#)appearance.DirRenderer.java  2008-8-19  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �˴��������˵��
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-19 	С��     		�½�
  **/
package appearance;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

 /**
 * JTree������ࡣ
 * ����������״Ŀ¼����ʾ��ۣ�����ǿ������ͼ��<br>
 * ʵ��TreeCellRenderer<br>
 * 2008-8-19
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class DirRenderer extends DefaultTreeCellRenderer {

	private Icon openIcon = null;
	private Icon closedIcon = null;
	private Icon leafIcon = null;
	
	public DirRenderer(Icon openIcon,Icon closedIcon,Icon leafIcon) {
		this.openIcon = openIcon;
		this.closedIcon = closedIcon;
		this.leafIcon = leafIcon;
		setOpenIcon(openIcon);
		setLeafIcon(leafIcon);
		//setClosedIcon(closedIcon);
	}
	
//	@Override
//	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
//		
//		super.getTreeCellRendererComponent( tree, value, sel, expanded, leaf, row,hasFocus);
//		if (leaf) 
//			setIcon(leafIcon);
//		else
//			setIcon(leafIcon);
//		if(expanded)
//			setIcon(closedIcon);
//		else
//			setIcon(openIcon);
//		return this;
//	}
}
