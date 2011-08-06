/*
 * Created on Aug 4, 2007
 *
 */
package org.digital.ui.components;

import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.digital.ds.util.BinaryTreeCreator;
import org.digital.exceptions.DigException;
import org.digital.map.KarnaughMapMinimizer;
import org.digital.map.helper.PersistenceHelper;
import org.digital.registry.ServiceRegistry;
import org.digital.registry.exception.RegistryException;
import org.digital.ui.GUIConstants;
import org.digital.ui.InternetView;
import org.digital.math.Number;
import org.digital.math.exceptions.InvalidNumberException;

/**
 * @author msahu
 *
 * 
 */
public class MainFrame extends Frame 
{
	MenuItem fLoad,fSave,fSaveAll,exit;
	MenuItem design;
	InternetView iView ;
	MenuBar mb = new MenuBar();
	public MainFrame(InternetView iView)
	{
		super();
		this.iView=iView;
		
       

	}
	
	public void display()
	{
		setTitle(GUIConstants.TITLE); 
		setMenuBar(mb);		
		createFileMenu();
		createDesignerMenu();
        
		add(iView);
		
        addWindowListener
			(
        		new WindowAdapter()
				{
        			public void windowClosing(WindowEvent e)
        			{
        				System.exit(0);
        			}
				}
        	);
        
        
        
        setSize(1000,750);
        setLocation(200,200);
        add(iView);
        iView.init();
        iView.start();
        setVisible(true);
	}
	/**
	 * 
	 */
	private void createDesignerMenu() 
	{
		
        
	    Menu designerMenu = new Menu(GUIConstants.DESIGNER_MENU);
	    
	    design = new MenuItem(GUIConstants.DESIGNER_MENU_DESIGN);
	    
	    
	    designerMenu.add(design);
	    
	    mb.add(designerMenu);
	    
		
		
	}

	private void createFileMenu()
	{
		
        
	    Menu fileMenu = new Menu(GUIConstants.FILE_MENU);
	    
	    fLoad = new MenuItem(GUIConstants.FILE_MENU_LOAD);
	    fSave=  new MenuItem(GUIConstants.FILE_MENU_SAVE);
	    fSaveAll = new MenuItem(GUIConstants.FILE_MENU_SAVE_ALL);
	    exit= new MenuItem(GUIConstants.FILE_MENU_EXIT);
	    
	    fileMenu.add(fLoad);
	    fileMenu.add(fSave);
	    fileMenu.add(fSaveAll);
	    fileMenu.add(exit);
	    
	    mb.add(fileMenu);
	    
	}
	
	public boolean action(Event e, Object o) 
	{
		if(e.target == fLoad)
		{
			 FileDialog fd =new FileDialog((Frame) this,GUIConstants.INPUT_FILE,FileDialog.LOAD);
			 fd.show();
			 Object obj=null;
			 try 
			 {
			 	if(fd.getDirectory()== null || fd.getFile()==null){return false;}
				obj = PersistenceHelper.load(fd.getDirectory()+GUIConstants.FILE_SEPARATOR+fd.getFile());
			 } catch (FileNotFoundException e1) 
			 {
				
				e1.printStackTrace();
			 }catch(Throwable th)
			 {
			 	th.printStackTrace();	
			 }
			 
			 if(obj instanceof BinaryTreeCreator)
			 {
			 	InternetView.incrementIndex();
				iView.showHistory((BinaryTreeCreator)obj);
			 }else if(obj instanceof BinaryTreeCreatorList)
			 {
			 	InternetView.incrementIndex();
			 	iView.showHistory(((BinaryTreeCreatorList)obj).first());
			 	
			 }
			 
			 
		}else if(e.target == fSave)
		{
			FileDialog fd =  new FileDialog((Frame) this,GUIConstants.OUTPUT_FILE,FileDialog.SAVE);
			fd.show();
			
			BinaryTreeCreator btc=null;
			try 
			{
				btc = (BinaryTreeCreator)ServiceRegistry.lookup(BinaryTreeCreator.class , InternetView.getIndex());
			} catch (RegistryException e1) 
			{
				
				e1.printStackTrace();
			}
			if(fd.getDirectory()== null || fd.getFile()==null){return false;}
			PersistenceHelper.save(fd.getDirectory()+GUIConstants.FILE_SEPARATOR+fd.getFile(),btc);
			

		}else if (e.target == fSaveAll)
		{
		
			FileDialog fd =  new FileDialog((Frame) this,GUIConstants.OUTPUT_FILE,FileDialog.SAVE);
			fd.show();
			
			List all =null;
			try 
			{
				all = ServiceRegistry.lookupAll(BinaryTreeCreator.class);
			} catch (RegistryException e1) 
			{
				
				e1.printStackTrace();
			}
			BinaryTreeCreatorList allBtc= new BinaryTreeCreatorList(all);
			if(fd.getDirectory()== null || fd.getFile()==null){return false;}
			PersistenceHelper.save(fd.getDirectory()+GUIConstants.FILE_SEPARATOR+fd.getFile(),allBtc);
			
			
		}else if(e.target == design)
		{
			 FileDialog fd =new FileDialog((Frame) this,GUIConstants.INPUT_FILE,FileDialog.LOAD);
			 fd.show();
			 DesignerFileProcessor processor = new DesignerFileProcessor(fd.getDirectory()+GUIConstants.FILE_SEPARATOR+fd.getFile());
			
			 AlertPopup pop = new AlertPopup(processor.minimize());
			 pop.setVisible(true);
			 pop.setLocationRelativeTo(this);
			
		}else if(e.target == exit)
		{
			System.exit(0);
		}
		return false;
	}
	
	private static class BinaryTreeCreatorList implements Externalizable
	{
		List list ;
		public BinaryTreeCreatorList(List list)
		{
			this.list=list;
		}
		public BinaryTreeCreatorList()
		{
			
		}
		public BinaryTreeCreator first()
		{
			return (BinaryTreeCreator)list.get(0);
		}
	
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
		{
			int size = in.readInt();
			list = new ArrayList(size);
			for(int i=0;i<size;i++)
			{
				BinaryTreeCreator btc = (BinaryTreeCreator)in.readObject();
				list.add(btc);
				
			}
			
			
		}
		public void writeExternal(ObjectOutput out) throws IOException 
		{
			out.writeInt(list.size());
			for(int i=0;i<list.size();i++)
			{
				BinaryTreeCreator btc = (BinaryTreeCreator)list.get(i);
				out.writeObject(btc);
				
			}
		
		}
	}
	
	
	private static class DesignerFileProcessor
	{
		private String file ;
		private int N;
		private List inputs=new ArrayList();
		
		public DesignerFileProcessor(String file )
		{
			this.file=file;
			initialize();
		}

		private void initialize() 
		{
			Properties designerFile = new Properties();
			
			try 
			{
				designerFile.load(new FileInputStream(new File(file)));
			} catch (FileNotFoundException e) 
			{
				
				System.out.println("Unable to load "+file);
				
			} catch (IOException e) 
			{
				
				e.printStackTrace();
			}
			N=Integer.parseInt(designerFile.getProperty("SIZE"));
			
			String str = designerFile.getProperty("INPUTS");
			
			StringTokenizer strTok = new StringTokenizer(str,",");
			
			while (strTok.hasMoreTokens()) 
			{
			    try 
				{
					inputs.add(new Number(Integer.parseInt(strTok.nextToken())));
				
				}catch (NumberFormatException e1) 
				{
					
						e1.printStackTrace();
				} catch (InvalidNumberException e1) 
				{
					
						e1.printStackTrace();
				}
			         
			}
			
		}
		
		public String minimize()
		{
			String rtn=null;
			KarnaughMapMinimizer mg = KarnaughMapMinimizer.getInstance(N);
			
			try 
			{
				rtn= mg.minimize(inputs,null);
			} catch (DigException e) 
			{
			
				e.printStackTrace();
			}
			return rtn;
		}
		
		
	}

}
