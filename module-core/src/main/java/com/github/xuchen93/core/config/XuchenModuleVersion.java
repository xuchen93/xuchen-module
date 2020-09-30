package com.github.xuchen93.core.config;

/**
 * @author edwin
 */
public final class XuchenModuleVersion {
	private XuchenModuleVersion(){

	}

	public static String getVersion() {
		Package pkg = XuchenModuleVersion.class.getPackage();
		return (pkg != null ? pkg.getImplementationVersion() : null);
	}
}
