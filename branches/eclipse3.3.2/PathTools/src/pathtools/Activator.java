package pathtools;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Sandip V. Chitale
 * 
 */
public class Activator extends AbstractUIPlugin {
	static final String FOLDER_EXPLORE_COMMAND_KEY = "folderExploreCommand";
	static final String FILE_EXPLORE_COMMAND_KEY = "fileExploreCommand";

	static String defaultFolderExploreCommand = "";
	static String defaultFileExploreCommand = "";

	static final String FOLDER_EDIT_COMMAND_KEY = "folderEditCommand";
	static final String FILE_EDIT_COMMAND_KEY = "fileEditCommand";

	static String defaultFolderEditCommand = "";
	static String defaultFileEditCommand = "";

	static {
		if (Platform.OS_MACOSX.equals(Platform.getOS())) {
			defaultFolderExploreCommand = "/usr/bin/open -a /System/Library/CoreServices/Finder.app \""
					+ Utilities.FILE_PATH + "\"";
			defaultFileExploreCommand = "/usr/bin/open -a /System/Library/CoreServices/Finder.app \""
					+ Utilities.FILE_PARENT_PATH + "\"";
			defaultFolderEditCommand = "/usr/bin/open -a /System/Library/CoreServices/Finder.app \""
					+ Utilities.FILE_PATH + "\"";
			defaultFileEditCommand = "/usr/bin/open -a /Applications/TextEdit.app \""
					+ Utilities.FILE_PATH + "\"";
		} else if (Platform.OS_WIN32.equals(Platform.getOS())) {
			defaultFolderExploreCommand = "cmd /C start explorer /select,/e \""
					+ Utilities.FILE_PATH + "\"";
			defaultFileExploreCommand = "cmd /C start explorer /select,/e \""
					+ Utilities.FILE_PARENT_PATH + "\"";
			defaultFolderEditCommand = "cmd /C start explorer /select,/e \""
					+ Utilities.FILE_PATH + "\"";
			defaultFileEditCommand = "cmd /C start notepad \""
					+ Utilities.FILE_PATH + "\"";
		} else if (Platform.OS_LINUX.equals(Platform.getOS())) {
			if (new File("/usr/bin/konqueror").exists()) {
				defaultFolderExploreCommand = "/usr/bin/konqueror \""
						+ Utilities.FILE_PATH + "\"";
				defaultFileExploreCommand = "/usr/bin/konqueror \""
						+ Utilities.FILE_PARENT_PATH + "\"";
				defaultFolderEditCommand = "/usr/bin/konqueror \""
						+ Utilities.FILE_PATH + "\"";
			} else if (new File("/usr/bin/nautilus").exists()) {
				defaultFolderExploreCommand = "/usr/bin/nautilus \""
						+ Utilities.FILE_PATH + "\"";
				defaultFileExploreCommand = "/usr/bin/nautilus \""
						+ Utilities.FILE_PARENT_PATH + "\"";
				defaultFolderEditCommand = "/usr/bin/nautilus \""
						+ Utilities.FILE_PATH + "\"";
			}
			if (new File("/usr/bin/kedit").exists()) {
				defaultFileEditCommand = "/usr/bin/kedit \""
						+ Utilities.FILE_PATH + "\"";
			} else if (new File("/usr/bin/gedit").exists()) {
				defaultFileEditCommand = "/usr/bin/gedit \""
						+ Utilities.FILE_PATH + "\"";
			}
		} else if (Platform.OS_SOLARIS.equals(Platform.getOS())) {
			if (new File("/usr/bin/konqueror").exists()) {
				defaultFolderExploreCommand = "/usr/bin/konqueror \""
						+ Utilities.FILE_PATH + "\"";
				defaultFileExploreCommand = "/usr/bin/konqueror \""
						+ Utilities.FILE_PARENT_PATH + "\"";
				defaultFolderEditCommand = "/usr/bin/konqueror \""
						+ Utilities.FILE_PATH + "\"";
			} else if (new File("/usr/bin/nautilus").exists()) {
				defaultFolderExploreCommand = "/usr/bin/nautilus \""
						+ Utilities.FILE_PATH + "\"";
				defaultFileExploreCommand = "/usr/bin/nautilus \""
						+ Utilities.FILE_PARENT_PATH + "\"";
				defaultFolderEditCommand = "/usr/bin/nautilus \""
						+ Utilities.FILE_PATH + "\"";
			} else {
				defaultFolderExploreCommand = "filemgr -c -d \""
						+ Utilities.FILE_PATH + "\"";
				defaultFolderExploreCommand = "filemgr -c -d \""
						+ Utilities.FILE_PATH + "\"";
				defaultFileEditCommand = "filemgr -c -d \""
						+ Utilities.FILE_PARENT_PATH + "\"";
			}
		}
	}

	// The plug-in ID
	public static final String PLUGIN_ID = "PathTools";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(FOLDER_EXPLORE_COMMAND_KEY,
				defaultFolderExploreCommand);
		store.setDefault(FILE_EXPLORE_COMMAND_KEY, defaultFileExploreCommand);
		store.setDefault(FOLDER_EDIT_COMMAND_KEY, defaultFolderEditCommand);
		store.setDefault(FILE_EDIT_COMMAND_KEY, defaultFileEditCommand);
		super.initializeDefaultPreferences(store);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
