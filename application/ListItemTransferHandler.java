/*
					The MIT License (MIT)

	Copyright (c) <year> <copyright holders>

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.

	source : http://opensource.org/licenses/mit-license.php
*/

package playlistmaker.application;

import java.awt.datatransfer.*;
import javax.activation.*;
import javax.swing.*;

/*
 *  Title : TransferHandlerを使ったJListのドラック＆ドロップによる並べ替え
 *  URL : http://terai.xrea.jp/Swing/DnDReorderList.html
 */

/*
 * <Usage>
 * JList list = new JList(listModel);
 * list.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
 * list.setTransferHandler(new ListItemTransferHandler());
 * list.setDropMode(DropMode.INSERT);
 * list.setDragEnabled(true);
 */

public class ListItemTransferHandler extends TransferHandler {
	  private final DataFlavor localObjectFlavor;
	  private Object[] transferedObjects = null;
	  public ListItemTransferHandler() {
	    localObjectFlavor = new ActivationDataFlavor(
	      Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
	  }
	  @Override protected Transferable createTransferable(JComponent c) {
	    JList list = (JList) c;
	    indices = list.getSelectedIndices();
	    transferedObjects = list.getSelectedValues();
	    return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
	  }
	  @Override public boolean canImport(TransferSupport info) {
	    if (!info.isDrop() || !info.isDataFlavorSupported(localObjectFlavor)) {
	      return false;
	    }
	    return true;
	  }
	  @Override public int getSourceActions(JComponent c) {
	    return TransferHandler.MOVE; //TransferHandler.COPY_OR_MOVE;
	  }
	  @Override public boolean importData(TransferSupport info) {
	    if (!canImport(info)) {
	      return false;
	    }
	    JList target = (JList)info.getComponent();
	    JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
	    DefaultListModel listModel = (DefaultListModel)target.getModel();
	    int index = dl.getIndex();
	    //boolean insert = dl.isInsert();
	    int max = listModel.getSize();
	    if(index<0 || index>max) {
	      index = max;
	    }
	    addIndex = index;

	    try {
	      Object[] values = (Object[])info.getTransferable().getTransferData(localObjectFlavor);
	      addCount = values.length;
	      for(int i=0;i<values.length;i++) {
	        int idx = index++;
	        listModel.add(idx, values[i]);
	        target.addSelectionInterval(idx, idx);
	      }
	      return true;
	    }catch(UnsupportedFlavorException ufe) {
	      ufe.printStackTrace();
	    }catch(java.io.IOException ioe) {
	      ioe.printStackTrace();
	    }
	    return false;
	  }
	  @Override protected void exportDone(JComponent c, Transferable data, int action) {
	    cleanup(c, action == TransferHandler.MOVE);
	  }
	  private void cleanup(JComponent c, boolean remove) {
	    if(remove && indices != null) {
	      JList source = (JList)c;
	      DefaultListModel model  = (DefaultListModel)source.getModel();
	      if(addCount > 0) {
	        for(int i=0;i<indices.length;i++) {
	          if(indices[i]>=addIndex) {
	            indices[i] += addCount;
	          }
	        }
	      }
	      for(int i=indices.length-1;i>=0;i--) {
	        model.remove(indices[i]);
	      }
	    }
	    indices  = null;
	    addCount = 0;
	    addIndex = -1;
	  }
	  private int[] indices = null;
	  private int addIndex  = -1; //Location where items were added
	  private int addCount  = 0;  //Number of items added.
}
