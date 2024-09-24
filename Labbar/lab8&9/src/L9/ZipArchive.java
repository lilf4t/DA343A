package L9;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ZipArchive {
	private File file;
	private String archive;
	private final StringBuffer buffer;

	public ZipArchive(File file, StringBuffer buffer) {
		this.file = file;
		this.buffer = buffer;
	}

	public void zip() {
		new Zip().start();
	}

	public void unzip() {
		new Unzip().start();
	}

	private class Zip extends Thread {
		public void run() {
			buffer.put("TO ZIP: " + file.getAbsolutePath());
			if(file.isDirectory()) {
				archive = file.getAbsolutePath()+".zip";
			} else if(file.isFile()) {
				archive = getFileName(file.getAbsolutePath())+".zip";
			} else {
				buffer.put("NOT directory or file: " + file.getAbsolutePath());
				return;
			}

			try (ZipOutputStream zos = new ZipOutputStream(new
					BufferedOutputStream(new FileOutputStream(archive)))){
				zip(file, zos, "");
			}catch(Exception e) {
				buffer.put("EXEPTION: " + e.getMessage());
				return;
			}
			buffer.put("ZIP-FILE: " + archive);
		}

		private String getFileName(String filename) {
			int index = filename.indexOf('.');
			if(index>=0)
				return filename.substring(0,index);
			else
				return filename;
		}

		private void zip(File file, ZipOutputStream zos, String directories) throws
				IOException {
			if(file.isFile()) {
				buffer.put("ZIP: " + file.getAbsolutePath());
				zos.putNextEntry(new ZipEntry(directories+file.getName()));
				try (BufferedInputStream bis = new BufferedInputStream(new
						FileInputStream(file))) {
					int b = bis.read();
					while(b!=-1) {
						zos.write(b);
						b = bis.read();
					}
					zos.flush();
				}
				zos.closeEntry();
			} else if(file.isDirectory()) {
				for(File f : file.listFiles()) {
					zip(f,zos,directories+file.getName()+"/");
				}
			}
		}
	}
	private class Unzip extends Thread {
		private File directory;
		private HashMap<String,File> directories = new HashMap<String,File>();

		public void run() {
			directory = file.getParentFile();
			buffer.put("TO UNZIP: " + file.getAbsolutePath() + " to " +
					directory.toString());
			try (ZipInputStream zis = new ZipInputStream(new
					BufferedInputStream(new FileInputStream(file)))){
				unzip(file, zis, "");
			}catch(Exception e) {
				buffer.put("EXEPTION: " + e.getMessage());
				return;
			}
		}

		private void unzip(File file, ZipInputStream zis, String directories)
				throws IOException {
			ZipEntry entry;
			while((entry=zis.getNextEntry())!=null) {
				buffer.put("UNZIP: " + entry.getName() +" to " + directory + "/");
				checkDirectories(entry.getName());
				try (BufferedOutputStream bos = new BufferedOutputStream(new
						FileOutputStream(directory+"/"+entry.getName()))) {
					int b = zis.read();
					while(b!=-1) {
						bos.write(b);
						b = zis.read();
					}
					bos.flush();
				}
			}
		}


		private void checkDirectories(String file) {
			int index = file.indexOf("/");
			while(index>=0) {
				String path = file.substring(0,index);
				if(directories.get(path)==null) {
					new File(directory+"/"+path).mkdir();
				}
				index = file.indexOf("/",index+1);
			}
		}
	}
}