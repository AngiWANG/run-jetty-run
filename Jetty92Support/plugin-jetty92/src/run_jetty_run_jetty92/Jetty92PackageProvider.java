package run_jetty_run_jetty92;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.osgi.framework.Bundle;

import runjettyrun.extensions.IJettyPackageProvider;
import runjettyrun.utils.ProjectUtil;
import runjettyrun.utils.VersionUtil;

public class Jetty92PackageProvider implements IJettyPackageProvider {

	public static final String VERSION = "Jetty 9.2.18.v20160721";
	public static final String[] VERSION_PREFIX = {"Jetty 9.2."};

	public IRuntimeClasspathEntry[] getPackage(String version, int type) {
		try {
			Bundle bundle = Activator.getDefault().getBundle();
			if (type == TYPE_JETTY_BUNDLE) {

				return ProjectUtil.getLibs(bundle,
						ProjectUtil.getJarFilesIn(bundle, "lib"));

			} else if (type == TYPE_UTIL) {
				return ProjectUtil.getLibs(bundle,
						ProjectUtil.getJarFilesIn(bundle, "lib/jndi"));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}


	public boolean acceptType(int type) {
		return ( type == TYPE_JETTY_BUNDLE) || (type == TYPE_UTIL) ;
	}

	public String getJettyVersion() {
		return VERSION;
	}

	public boolean accpet(String ver) {
		return VersionUtil.supportVersion(ver, VERSION, VERSION_PREFIX);
	}

	public String getName() {
		return VERSION;
	}

	public String getText() {
		return VERSION;
	}

	public static void main(String[] args) {
		//We have to update the list manually since I still didn't know how to fetch all the files in a folder in OSGi.

		for(File f :new File("lib").listFiles()){
			System.out.println("\"lib/"+f.getName()+"\",");
		}

		for(File f :new File("jndilib").listFiles()){
			System.out.println("\"jndilib/"+f.getName()+"\",");
		}
	}
}
