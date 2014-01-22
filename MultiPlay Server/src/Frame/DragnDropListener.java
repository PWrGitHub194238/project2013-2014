package Frame;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DragnDropListener implements DropTargetListener {
	private DataFlavor Linux = null;
	private DataFlavor Windows = null;

	public void drop(DropTargetDropEvent dropEvent) {
		try {
			Transferable droppedItem = dropEvent.getTransferable();
			DataFlavor[] droppedItemFlavors = droppedItem
					.getTransferDataFlavors();
			droppedItemFlavors = (droppedItemFlavors.length == 0) ? dropEvent
					.getCurrentDataFlavors() : droppedItemFlavors;
			DataFlavor flavor = DataFlavor
					.selectBestTextFlavor(droppedItemFlavors);

			flavor = (flavor == null) ? droppedItemFlavors[0] : flavor;
			Linux = new DataFlavor("text/uri-list;class=java.io.Reader");
			Windows = DataFlavor.javaFileListFlavor;
			handleDrop(dropEvent, droppedItem, flavor);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err
					.println("DnD not initalized properly, please try again.");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (UnsupportedFlavorException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	private void handleDrop(DropTargetDropEvent dropEvent,
			Transferable droppedItem, DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (flavor.equals(Linux)) {
			acceptLinuxDrop(dropEvent, droppedItem, flavor);
		} else if (flavor.equals(Windows)) {
			acceptWindowsDrop(dropEvent, droppedItem, flavor);
		} else {
			System.err.println("DnD Error");
			dropEvent.rejectDrop();
		}
	}

	private void acceptWindowsDrop(DropTargetDropEvent dropTargetEvent,
			Transferable droppedItem, DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		dropTargetEvent.acceptDrop(DnDConstants.ACTION_COPY);
		// Get the transfer which can provide the dropped item data
		Transferable transferable = dropTargetEvent.getTransferable();
		// Get the data formats of the dropped item
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		// Loop through the flavors
		for (DataFlavor flavores : flavors) {
			try {
				if (flavor.isFlavorJavaFileListType()) {
					// Get all of the dropped files
					List<File> files = (List<File>) transferable
							.getTransferData(flavor);
					// Loop them through
					for (File file : files) {
						// Print out the file path
						System.out.println("File path is '" + file.getPath()
								+ "'.");
						ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
								file.getPath());
						try {
							Process p = pb.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				// Print out the error stack
				e.printStackTrace();
			}
		}
		// Inform that the drop is complete
		dropTargetEvent.dropComplete(true);
	}

	private void acceptLinuxDrop(DropTargetDropEvent dropTargetEvent,
			Transferable tr, DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		dropTargetEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		BufferedReader read = new BufferedReader(flavor.getReaderForText(tr));
		// Remove 'file://' from file name
		String fileName = read.readLine().substring(7).replace("%20", " ");
		// Remove 'localhost' from OS X file names
		if (fileName.substring(0, 9).equals("localhost")) {
			fileName = fileName.substring(9);
		}
		read.close();
		dropTargetEvent.dropComplete(true);
		System.out.println("File Dragged:" + fileName);
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", fileName);
		try {
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void dragEnter(DropTargetDragEvent event) {
	}

	@Override
	public void dragExit(DropTargetEvent event) {
	}

	@Override
	public void dragOver(DropTargetDragEvent event) {
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent event) {
	}

}
