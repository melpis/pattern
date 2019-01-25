package launch;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {


    public static void main(String[] args) throws Exception {

        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
        Tomcat tomcat = new Tomcat();
        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());

        tomcat.setPort(8080);
        File webContentFolder = new File("embedded-tomcat/src/main/webapp/");
        if (!webContentFolder.exists()) {
            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
        }
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder.getAbsolutePath());
        ctx.setParentClassLoader(Main.class.getClassLoader());


        File additionWebInfClassesFolder = new File( "embedded-tomcat/target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        WebResourceSet resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
        resources.addPreResources(resourceSet);
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}
